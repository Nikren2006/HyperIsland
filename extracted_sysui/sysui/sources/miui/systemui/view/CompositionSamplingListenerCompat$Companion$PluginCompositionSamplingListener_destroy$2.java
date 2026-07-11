package miui.systemui.view;

import android.util.Log;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class CompositionSamplingListenerCompat$Companion$PluginCompositionSamplingListener_destroy$2 extends o implements Function0 {
    public static final CompositionSamplingListenerCompat$Companion$PluginCompositionSamplingListener_destroy$2 INSTANCE = new CompositionSamplingListenerCompat$Companion$PluginCompositionSamplingListener_destroy$2();

    public CompositionSamplingListenerCompat$Companion$PluginCompositionSamplingListener_destroy$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            Class pluginCompositionSamplingListener_class = CompositionSamplingListenerCompat.Companion.getPluginCompositionSamplingListener_class();
            if (pluginCompositionSamplingListener_class != null) {
                return pluginCompositionSamplingListener_class.getDeclaredMethod("destroy", null);
            }
            return null;
        } catch (Throwable th) {
            Log.e("CompositionSamplingListenerCompat", "get PluginCompositionSamplingListener_destroy failed.", th);
            return null;
        }
    }
}
