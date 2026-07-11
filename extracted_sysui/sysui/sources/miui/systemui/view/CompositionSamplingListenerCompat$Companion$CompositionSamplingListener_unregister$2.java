package miui.systemui.view;

import android.util.Log;
import android.view.CompositionSamplingListener;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class CompositionSamplingListenerCompat$Companion$CompositionSamplingListener_unregister$2 extends o implements Function0 {
    public static final CompositionSamplingListenerCompat$Companion$CompositionSamplingListener_unregister$2 INSTANCE = new CompositionSamplingListenerCompat$Companion$CompositionSamplingListener_unregister$2();

    public CompositionSamplingListenerCompat$Companion$CompositionSamplingListener_unregister$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            return CompositionSamplingListener.class.getDeclaredMethod("unregister", CompositionSamplingListener.class);
        } catch (Throwable th) {
            Log.e("CompositionSamplingListenerCompat", "get CompositionSamplingListener_unregister method failed.", th);
            return null;
        }
    }
}
