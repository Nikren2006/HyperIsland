package io.github.hyperisland.xposed.hook

import android.content.Context
import android.view.View
import android.view.ViewGroup
import io.github.hyperisland.xposed.ConfigManager
import io.github.hyperisland.xposed.utils.HookUtils
import io.github.libxposed.api.XposedModule
import io.github.libxposed.api.XposedModuleInterface.PackageLoadedParam
import java.lang.reflect.Method

/**
 * Диагностический хук liquid glass для Dynamic Island.
 *
 * НЕ меняет поведение — только подробно логирует, что происходит с View-иерархией
 * острова в SystemUI, чтобы было видно, почему эффект не работает:
 *   - какие классы острова создаются (BackgroundView / BigIslandView /
 *     DynamicGlowEffectView / LightBgView) и в каком размере;
 *   - активен ли glow-шейдер (startGlowEffect / setGlowEffectPosition);
 *   - доступен ли RuntimeShader у LightBgView (mContainer) — туда мы бы вшивали
 *     liquid glass;
 *   - какие значения префов liquid glass сейчас в ConfigManager.
 *
 * Включите «Отладочные логи» в приложении, чтобы видеть DEBUG-сообщения.
 */
object IslandLiquidGlassHook : BaseHook() {

    private const val TAG = "HyperIsland[LiquidGlass]"

    private const val K_ENABLED = "pref_liquid_glass_enabled"
    private const val K_REFRACTION = "pref_liquid_glass_refraction"
    private const val K_THICKNESS = "pref_liquid_glass_thickness"
    private const val K_VIBRANCY = "pref_liquid_glass_vibrancy"
    private const val K_ABERRATION = "pref_liquid_glass_aberration"
    private const val K_TINT = "pref_liquid_glass_tint_color"
    private const val K_TINT_OP = "pref_liquid_glass_tint_opacity"
    private const val K_RADIUS = "pref_liquid_glass_corner_radius"
    private const val K_INNER = "pref_liquid_glass_inner_shadow"
    private const val K_INNER_R = "pref_liquid_glass_inner_shadow_radius"

    override fun getTag() = TAG

    override fun onInit(module: XposedModule, param: PackageLoadedParam) {
        HookUtils.hookDynamicClassLoaders(module, ClassLoader.getSystemClassLoader()) { cl ->
            onClassLoaderLoaded(module, cl)
        }
    }

    private fun onClassLoaderLoaded(module: XposedModule, cl: ClassLoader) {
        val bgClass = try {
            cl.loadClass("miui.systemui.dynamicisland.DynamicIslandBackgroundView")
        } catch (_: Throwable) {
            null
        } ?: return

        logWarn(module, ">>> IslandLiquidGlassHook: SystemUI classloader загружен, начинаем диагностику")
        logConfig(module)

        hookViewCreation(module, bgClass, "DynamicIslandBackgroundView")
        tryHook(module, cl, "miui.systemui.dynamicisland.view.DynamicIslandBigIslandView") { c ->
            hookViewCreation(module, c, "DynamicIslandBigIslandView")
            hookBigIslandContainer(module, c)
        }
        tryHook(module, cl, "miui.systemui.dynamicisland.view.DynamicGlowEffectView") { c ->
            hookViewCreation(module, c, "DynamicGlowEffectView")
            hookGlowEffectView(module, c)
        }
        tryHook(module, cl, "com.mi.widget.view.LightBgView") { c ->
            hookViewCreation(module, c, "LightBgView")
            hookLightBgView(module, c)
        }
    }

    // ── префы ────────────────────────────────────────────────────────────────

    private fun logConfig(module: XposedModule) {
        val cfg = buildString {
            append("prefs: enabled=").append(ConfigManager.getBoolean(K_ENABLED, false))
            append(" refraction=").append(ConfigManager.getDouble(K_REFRACTION, Double.NaN))
            append(" thickness=").append(ConfigManager.getDouble(K_THICKNESS, Double.NaN))
            append(" vibrancy=").append(ConfigManager.getDouble(K_VIBRANCY, Double.NaN))
            append(" aberration=").append(ConfigManager.getDouble(K_ABERRATION, Double.NaN))
            append(" tint=0x").append(ConfigManager.getInt(K_TINT, 0).toString(16))
            append(" tintOpacity=").append(ConfigManager.getDouble(K_TINT_OP, Double.NaN))
            append(" radius=").append(ConfigManager.getDouble(K_RADIUS, Double.NaN))
            append(" innerShadow=").append(ConfigManager.getBoolean(K_INNER, false))
            append(" innerShadowR=").append(ConfigManager.getDouble(K_INNER_R, Double.NaN))
        }
        logWarn(module, ">>> LiquidGlass $cfg")
    }

    // ── создание View ─────────────────────────────────────────────────────────

    private fun hookViewCreation(module: XposedModule, cls: Class<*>, label: String) {
        // вешаемся на ВСЕ конструкторы — у декомпилированных классов MIUI
        // сигнатуры не всегда (Context), бывают (Context, AttributeSet) и др.
        val ctors = try {
            cls.declaredConstructors.toList()
        } catch (_: Throwable) {
            emptyList()
        }
        if (ctors.isEmpty()) {
            log(module, "$label: конструкторы недоступны, пропускаем хук создания")
            return
        }
        var hooked = 0
        for (ctor in ctors) {
            try {
                ctor.isAccessible = true
                module.hook(ctor).intercept { chain ->
                    chain.proceed()
                    val v = chain.thisObject as? View
                    log(module, "$label created (${ctor.parameterTypes.size} params): ${describe(v)}")
                    null
                }
                hooked++
            } catch (e: Throwable) {
                log(module, "$label: не удалось подцепить конструктор: ${e.message}")
            }
        }
        log(module, "$label: подцеплено конструкторов=$hooked/${ctors.size}")
    }

    private fun hookBigIslandContainer(module: XposedModule, cls: Class<*>) {
        // DynamicIslandBigIslandView.getContentView() возвращает mContainer (LightBgView)
        val m = cls.methodOrNull("getContentView") ?: cls.methodOrNull("getMContainer")
        if (m == null) {
            log(module, "DynamicIslandBigIslandView: нет getContentView()/getMContainer()")
            return
        }
        module.hook(m).intercept { chain ->
            val result = chain.proceed()
            log(module, "DynamicIslandBigIslandView.getContent(): container=${describe(result as? View)}")
            result
        }
    }

    // ── glow-шейдер ─────────────────────────────────────────────────────────────

    private fun hookGlowEffectView(module: XposedModule, cls: Class<*>) {
        cls.methodOrNull("startGlowEffect")?.let { m ->
            module.hook(m).intercept { chain ->
                logWarn(module, "DynamicGlowEffectView.startGlowEffect() ВЫЗВАН — glow-шейдер активен")
                chain.proceed()
                null
            }
        } ?: log(module, "DynamicGlowEffectView: нет startGlowEffect()")

        // setGlowEffectPosition(float,float,float,float,float) — вызывается при анимации формы
        cls.methodOrNull(
            "setGlowEffectPosition",
            Float::class.javaPrimitiveType, Float::class.javaPrimitiveType,
            Float::class.javaPrimitiveType, Float::class.javaPrimitiveType,
            Float::class.javaPrimitiveType,
        )?.let { m ->
            module.hook(m).intercept { chain ->
                log(module, "DynamicGlowEffectView.setGlowEffectPosition(${chain.args.contentToString()})")
                chain.proceed()
                null
            }
        } ?: log(module, "DynamicGlowEffectView: нет setGlowEffectPosition()")

        // mContainer == LightBgView, у которого берём RuntimeShader
        cls.methodOrNull("getMContainer")?.let { m ->
            module.hook(m).intercept { chain ->
                val result = chain.proceed()
                probeLightBgShader(module, result as? View, "DynamicGlowEffectView.getMContainer()")
                result
            }
        }
    }

    // ── LightBgView (стеклянный shader-вью MIUI) ──────────────────────────────────

    private fun hookLightBgView(module: XposedModule, cls: Class<*>) {
        // навешиваемся на onAttachedToWindow, чтобы зафиксировать размеры после layout
        val m = cls.methodOrNull("onAttachedToWindow")
        if (m != null) {
            module.hook(m).intercept { chain ->
                chain.proceed()
                val v = chain.thisObject as? View
                log(module, "LightBgView attached: ${describe(v)}")
                probeLightBgShader(module, v, "LightBgView.onAttachedToWindow")
                null
            }
        } else {
            log(module, "LightBgView: нет onAttachedToWindow()")
        }
    }

    private fun probeLightBgShader(module: XposedModule, v: View?, from: String) {
        if (v == null) {
            log(module, "$from: LightBgView == null")
            return
        }
        val cls = v.javaClass
        val getShader = cls.methodOrNull("getMShader") ?: cls.methodOrNull("getMTextureShader")
        if (getShader == null) {
            log(module, "$from: у LightBgView нет getMShader()/getMTextureShader() — инъекция шейдера невозможна этим путём")
            return
        }
        try {
            val shader = getShader.invoke(v)
            if (shader == null) {
                log(module, "$from: getMShader() вернул null")
            } else {
                val uniformMethods = shader.javaClass.methods
                    .filter { it.name.contains("Uniform", ignoreCase = true) || it.name.contains("Float", ignoreCase = true) }
                    .map { it.name }.distinct().take(12)
                log(module, "$from: shader=${shader.javaClass.name}, " +
                        "uniform-API=[${uniformMethods.joinToString()}]")
                logWarn(module, "$from: RuntimeShader доступен -> сюда можно вшить liquid glass")
            }
        } catch (e: Throwable) {
            logError(module, "$from: ошибка доступа к шейдеру: ${e.message}")
        }
    }

    // ── утилиты ────────────────────────────────────────────────────────────────

    private fun describe(v: View?): String {
        if (v == null) return "null"
        val id = try { v.id } catch (_: Throwable) { 0 }
        val resName = try { v.resources?.getResourceEntryName(id) ?: "no-id" } catch (_: Throwable) { "no-id" }
        val w = try { v.width } catch (_: Throwable) { -1 }
        val h = try { v.height } catch (_: Throwable) { -1 }
        val vis = when (v.visibility) {
            View.VISIBLE -> "VISIBLE"
            View.INVISIBLE -> "INVISIBLE"
            else -> "GONE"
        }
        return "${v.javaClass.simpleName}(id=$resName, ${w}x$h, $vis)"
    }

    private fun Class<*>.methodOrNull(name: String, vararg params: Class<*>): Method? =
        try {
            getDeclaredMethod(name, *params).apply { isAccessible = true }
        } catch (_: Throwable) {
            null
        }

    private fun tryHook(
        module: XposedModule,
        cl: ClassLoader,
        name: String,
        block: (Class<*>) -> Unit,
    ) {
        try {
            val c = cl.loadClass(name)
            log(module, "найден класс $name")
            block(c)
        } catch (e: Throwable) {
            log(module, "класс $name не найден: ${e.message}")
        }
    }
}
