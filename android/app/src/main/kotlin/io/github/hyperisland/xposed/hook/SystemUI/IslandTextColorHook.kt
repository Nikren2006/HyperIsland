package io.github.hyperisland.xposed.hook

import android.graphics.Color
import android.widget.TextView
import io.github.hyperisland.xposed.ConfigManager
import io.github.hyperisland.xposed.utils.HookUtils
import io.github.libxposed.api.XposedModule
import io.github.libxposed.api.XposedModuleInterface.PackageLoadedParam
import java.util.Collections
import java.util.concurrent.ConcurrentHashMap
import java.util.WeakHashMap

/**
 * Overrides Dynamic Island default text colors while preserving explicit highlight colors.
 */
object IslandTextColorHook : BaseHook() {

    private const val TAG = "HyperIsland[IslandTextColor]"
    private const val KEY_TEXT_COLOR_MODE = "pref_island_text_color_mode"

    private const val MODE_DEFAULT = "default"
    private const val MODE_BLACK = "black"
    private const val MODE_FOLLOW_BACKGROUND = "follow_background"
    private const val MODE_INVERT_BACKGROUND = "invert_background"

    private val hookedClassLoaders = ConcurrentHashMap.newKeySet<Int>()
    private val defaultTextViews = Collections.synchronizedSet(
        Collections.newSetFromMap(WeakHashMap<TextView, Boolean>())
    )

    @Volatile private var isRegionDark = true

    override fun getTag() = TAG

    override fun onInit(module: XposedModule, param: PackageLoadedParam) {
        HookUtils.hookDynamicClassLoaders(module, ClassLoader.getSystemClassLoader()) { classLoader ->
            hookClasses(module, classLoader)
        }
    }

    override fun onConfigChanged() {
        applyTextColorToTrackedViews()
    }

    private fun hookClasses(module: XposedModule, classLoader: ClassLoader) {
        val clId = System.identityHashCode(classLoader)
        if (!hookedClassLoaders.add(clId)) return

        var hookedAny = false
        runCatching {
            val baseHolderClass = classLoader.loadClass(
                "miui.systemui.dynamicisland.module.BaseIslandModuleViewHolder"
            )
            hookTextColorMethods(module, baseHolderClass)
            hookedAny = true
        }.onFailure { error ->
            if (error !is ClassNotFoundException) {
                logError(module, "failed to hook BaseIslandModuleViewHolder: ${error.message}")
            }
        }

        runCatching {
            val contentViewClass = classLoader.loadClass(
                "miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView"
            )
            hookUpdateDarkLightMode(module, contentViewClass)
            hookedAny = true
        }.onFailure { error ->
            if (error !is ClassNotFoundException) {
                logError(module, "failed to hook DynamicIslandBaseContentView: ${error.message}")
            }
        }

        if (!hookedAny) hookedClassLoaders.remove(clId)
    }

    private fun hookTextColorMethods(module: XposedModule, baseHolderClass: Class<*>) {
        baseHolderClass.declaredMethods
            .filter { method ->
                method.name == "setTitleHighlightColor" || method.name == "setContentHighlightColor"
            }
            .filter { method ->
                method.parameterTypes.size == 4 && TextView::class.java.isAssignableFrom(method.parameterTypes[2])
            }
            .forEach { method ->
                module.hook(method).intercept { chain ->
                    val result = chain.proceed()
                    val mode = ConfigManager.getString(KEY_TEXT_COLOR_MODE, MODE_DEFAULT)
                    val textView = chain.args.getOrNull(2) as? TextView
                    if (usesHighlightColor(chain.args)) {
                        if (textView != null) defaultTextViews.remove(textView)
                    } else if (mode != MODE_DEFAULT) {
                        val text = chain.args.getOrNull(3) as? String
                        if (textView != null && text != null) {
                            defaultTextViews.add(textView)
                            applyTextColor(textView, text, resolveDefaultTextColor(mode))
                        }
                    }
                    result
                }
                log(module, "hooked ${method.name}")
            }
    }

    private fun hookUpdateDarkLightMode(module: XposedModule, contentViewClass: Class<*>) {
        contentViewClass.declaredMethods
            .filter { method -> method.name == "updateDarkLightMode" && method.parameterTypes.size >= 3 }
            .forEach { method ->
                module.hook(method).intercept { chain ->
                    (chain.args.getOrNull(2) as? Boolean)?.let { useDarkText ->
                        val nextIsRegionDark = !useDarkText
                        val changed = isRegionDark != nextIsRegionDark
                        isRegionDark = nextIsRegionDark
                        if (changed) applyTextColorToTrackedViews()
                    }
                    chain.proceed()
                }
                log(module, "hooked updateDarkLightMode")
            }
    }

    private fun usesHighlightColor(args: List<*>): Boolean {
        val template = args.getOrNull(0) ?: return false
        val showHighlight = args.getOrNull(1) as? Boolean ?: false
        if (!showHighlight) return false
        val highlightColor = runCatching {
            template.javaClass.methods.firstOrNull { it.name == "getHighlightColor" }?.invoke(template) as? String
        }.getOrNull()
        return !highlightColor.isNullOrBlank()
    }

    private fun resolveDefaultTextColor(mode: String): Int {
        return when (mode) {
            MODE_BLACK -> Color.BLACK
            MODE_FOLLOW_BACKGROUND -> if (isRegionDark) Color.WHITE else Color.BLACK
            MODE_INVERT_BACKGROUND -> if (isRegionDark) Color.BLACK else Color.WHITE
            else -> Color.WHITE
        }
    }

    private fun applyTextColorToTrackedViews() {
        val mode = ConfigManager.getString(KEY_TEXT_COLOR_MODE, MODE_DEFAULT)
        if (mode == MODE_DEFAULT) return
        val color = resolveDefaultTextColor(mode)
        val views = synchronized(defaultTextViews) { defaultTextViews.toList() }
        views.forEach { textView ->
            val text = textView.text?.toString() ?: return@forEach
            applyTextColor(textView, text, color)
        }
    }

    private fun applyTextColor(textView: TextView, text: String, color: Int) {
        runCatching {
            val updateMethod = textView.javaClass.methods.firstOrNull { candidate ->
                candidate.name == "updateTextWithNewAppearance" && candidate.parameterTypes.size == 2
            }
            if (updateMethod != null) {
                updateMethod.invoke(textView, text, color)
            } else {
                textView.setTextColor(color)
                textView.text = text
            }
        }.onFailure {
            textView.setTextColor(color)
        }
    }
}
