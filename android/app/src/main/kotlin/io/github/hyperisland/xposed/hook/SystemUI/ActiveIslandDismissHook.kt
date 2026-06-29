package io.github.hyperisland.xposed.hook

import android.os.Handler
import android.os.Looper
import io.github.hyperisland.xposed.utils.HookUtils
import io.github.libxposed.api.XposedModule
import io.github.libxposed.api.XposedModuleInterface.PackageLoadedParam
import java.lang.ref.WeakReference
import java.util.concurrent.ConcurrentHashMap

object ActiveIslandDismissHook : BaseHook() {
    private const val TAG = "HyperIsland[IslandDismiss]"
    private const val WINDOW_CONTROLLER_CLASS =
        "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController"

    private val mainHandler = Handler(Looper.getMainLooper())
    private val hookedClassLoaders = ConcurrentHashMap.newKeySet<Int>()
    private val controllers = ConcurrentHashMap<String, WeakReference<Any>>()

    override fun getTag() = TAG

    override fun onConfigChanged() {
        controllers.clear()
    }

    override fun onInit(module: XposedModule, param: PackageLoadedParam) {
        hookWindowController(module, param.defaultClassLoader)
        HookUtils.hookDynamicClassLoaders(module, ClassLoader.getSystemClassLoader()) { cl ->
            hookWindowController(module, cl)
        }
    }

    fun dismiss(key: String) {
        if (key.isBlank()) return
        mainHandler.post {
            val controller = controllers[key]?.get()
            if (controller == null) {
                controllers.remove(key)
                return@post
            }
            try {
                val method = controller.javaClass.getMethod("removeDynamicIslandView", String::class.java)
                method.invoke(controller, key)
            } catch (e: Throwable) {
                controllers.remove(key)
            }
        }
    }

    private fun hookWindowController(module: XposedModule, classLoader: ClassLoader) {
        val clId = System.identityHashCode(classLoader)
        if (!hookedClassLoaders.add(clId)) return
        try {
            val clazz = try {
                classLoader.loadClass(WINDOW_CONTROLLER_CLASS)
            } catch (_: ClassNotFoundException) {
                hookedClassLoaders.remove(clId)
                return
            }
            clazz.declaredMethods
                .filter { it.name == "updateDynamicIslandView" && it.parameterCount >= 1 }
                .forEach { method ->
                    module.hook(method).intercept { chain ->
                        val result = chain.proceed()
                        val islandData = chain.args.getOrNull(0)
                        val key = runCatching {
                            islandData?.javaClass?.getMethod("getKey")?.invoke(islandData) as? String
                        }.getOrNull()
                        if (!key.isNullOrBlank()) recordController(key, chain.thisObject)
                        result
                    }
                }
        } catch (_: Throwable) {
            hookedClassLoaders.remove(clId)
        }
    }

    private fun recordController(key: String, controller: Any) {
        controllers[key] = WeakReference(controller)
        if (controllers.size <= 64) return
        controllers.entries.removeIf { it.value.get() == null }
        if (controllers.size <= 128) return
        controllers.keys.iterator().let { iterator ->
            while (controllers.size > 128 && iterator.hasNext()) {
                controllers.remove(iterator.next())
            }
        }
    }
}
