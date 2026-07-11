package miui.systemui.view;

import android.util.Log;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class CompositionSamplingListenerCompat$Companion$PluginCompositionSamplingListener_class$2 extends o implements Function0 {
    public static final CompositionSamplingListenerCompat$Companion$PluginCompositionSamplingListener_class$2 INSTANCE = new CompositionSamplingListenerCompat$Companion$PluginCompositionSamplingListener_class$2();

    public CompositionSamplingListenerCompat$Companion$PluginCompositionSamplingListener_class$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Class<?> invoke() {
        try {
            return Class.forName("com.miui.systemui.plugin.view.PluginCompositionSamplingListener");
        } catch (Throwable th) {
            Log.e("CompositionSamplingListenerCompat", "get PluginCompositionSamplingListener_class failed.", th);
            return null;
        }
    }
}
