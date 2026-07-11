package miui.systemui.view;

import H0.d;
import H0.e;
import android.graphics.Rect;
import android.util.Log;
import android.view.CompositionSamplingListener;
import android.view.SurfaceControl;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class CompositionSamplingListenerCompat {
    private static final String TAG = "CompositionSamplingListenerCompat";
    private final CompositionSamplingListener samplingListener;
    public static final Companion Companion = new Companion(null);
    private static final d PluginCompositionSamplingListener_class$delegate = e.b(CompositionSamplingListenerCompat$Companion$PluginCompositionSamplingListener_class$2.INSTANCE);
    private static final d PluginCompositionSamplingListener_constructor$delegate = e.b(CompositionSamplingListenerCompat$Companion$PluginCompositionSamplingListener_constructor$2.INSTANCE);
    private static final d PluginCompositionSamplingListener_destroy$delegate = e.b(CompositionSamplingListenerCompat$Companion$PluginCompositionSamplingListener_destroy$2.INSTANCE);
    private static final d CompositionSamplingListener_register$delegate = e.b(CompositionSamplingListenerCompat$Companion$CompositionSamplingListener_register$2.INSTANCE);
    private static final d CompositionSamplingListener_unregister$delegate = e.b(CompositionSamplingListenerCompat$Companion$CompositionSamplingListener_unregister$2.INSTANCE);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final CompositionSamplingListener PluginCompositionSamplingListener(Executor executor, Consumer<Float> consumer) {
            try {
                Constructor<? extends Object> pluginCompositionSamplingListener_constructor = getPluginCompositionSamplingListener_constructor();
                Object objNewInstance = pluginCompositionSamplingListener_constructor != null ? pluginCompositionSamplingListener_constructor.newInstance(executor, consumer) : null;
                if (objNewInstance instanceof CompositionSamplingListener) {
                    return (CompositionSamplingListener) objNewInstance;
                }
                return null;
            } catch (Throwable th) {
                Log.e(CompositionSamplingListenerCompat.TAG, "create PluginCompositionSamplingListener failed.", th);
                return null;
            }
        }

        private final Method getCompositionSamplingListener_register() {
            return (Method) CompositionSamplingListenerCompat.CompositionSamplingListener_register$delegate.getValue();
        }

        private final Method getCompositionSamplingListener_unregister() {
            return (Method) CompositionSamplingListenerCompat.CompositionSamplingListener_unregister$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Class<?> getPluginCompositionSamplingListener_class() {
            return (Class) CompositionSamplingListenerCompat.PluginCompositionSamplingListener_class$delegate.getValue();
        }

        private final Constructor<? extends Object> getPluginCompositionSamplingListener_constructor() {
            return (Constructor) CompositionSamplingListenerCompat.PluginCompositionSamplingListener_constructor$delegate.getValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Method getPluginCompositionSamplingListener_destroy() {
            return (Method) CompositionSamplingListenerCompat.PluginCompositionSamplingListener_destroy$delegate.getValue();
        }

        public final void register(CompositionSamplingListenerCompat listener, int i2, SurfaceControl surfaceControl, Rect rect) {
            CompositionSamplingListener compositionSamplingListener;
            n.g(listener, "listener");
            try {
                Method compositionSamplingListener_register = getCompositionSamplingListener_register();
                if (compositionSamplingListener_register == null || (compositionSamplingListener = listener.samplingListener) == null) {
                    return;
                }
                compositionSamplingListener_register.invoke(null, compositionSamplingListener, Integer.valueOf(i2), surfaceControl, rect);
            } catch (Throwable th) {
                Log.e(CompositionSamplingListenerCompat.TAG, "invoke CompositionSamplingListener_register failed.", th);
            }
        }

        public final void unregister(CompositionSamplingListenerCompat listener) {
            CompositionSamplingListener compositionSamplingListener;
            n.g(listener, "listener");
            try {
                Method compositionSamplingListener_unregister = getCompositionSamplingListener_unregister();
                if (compositionSamplingListener_unregister == null || (compositionSamplingListener = listener.samplingListener) == null) {
                    return;
                }
                compositionSamplingListener_unregister.invoke(null, compositionSamplingListener);
            } catch (Throwable th) {
                Log.e(CompositionSamplingListenerCompat.TAG, "invoke CompositionSamplingListener_unregister failed.", th);
            }
        }

        private Companion() {
        }
    }

    public CompositionSamplingListenerCompat(Executor executor, Consumer<Float> onSampledCollected) {
        n.g(executor, "executor");
        n.g(onSampledCollected, "onSampledCollected");
        this.samplingListener = Companion.PluginCompositionSamplingListener(executor, onSampledCollected);
    }

    public static final void register(CompositionSamplingListenerCompat compositionSamplingListenerCompat, int i2, SurfaceControl surfaceControl, Rect rect) {
        Companion.register(compositionSamplingListenerCompat, i2, surfaceControl, rect);
    }

    public static final void unregister(CompositionSamplingListenerCompat compositionSamplingListenerCompat) {
        Companion.unregister(compositionSamplingListenerCompat);
    }

    public final void destroy() {
        CompositionSamplingListener compositionSamplingListener;
        try {
            Method pluginCompositionSamplingListener_destroy = Companion.getPluginCompositionSamplingListener_destroy();
            if (pluginCompositionSamplingListener_destroy == null || (compositionSamplingListener = this.samplingListener) == null) {
                return;
            }
            pluginCompositionSamplingListener_destroy.invoke(compositionSamplingListener, null);
        } catch (Throwable th) {
            Log.e(TAG, "invoke PluginCompositionSamplingListener_destroy failed.", th);
        }
    }
}
