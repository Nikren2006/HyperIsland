package miui.systemui.view;

import H0.d;
import H0.e;
import android.util.Log;
import android.view.SurfaceControl;
import java.lang.reflect.Constructor;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class SurfaceControlCompatKt {
    private static final d SurfaceControl_constructor$delegate = e.b(SurfaceControlCompatKt$SurfaceControl_constructor$2.INSTANCE);
    private static final String TAG = "SurfaceControlCompat";

    public static final SurfaceControl createSurfaceControl(SurfaceControl other, String callsite) {
        n.g(other, "other");
        n.g(callsite, "callsite");
        try {
            Constructor<SurfaceControl> surfaceControl_constructor = getSurfaceControl_constructor();
            if (surfaceControl_constructor != null) {
                return surfaceControl_constructor.newInstance(other, callsite);
            }
            return null;
        } catch (Throwable th) {
            Log.e(TAG, "create SurfaceControl failed.", th);
            return null;
        }
    }

    private static final Constructor<SurfaceControl> getSurfaceControl_constructor() {
        return (Constructor) SurfaceControl_constructor$delegate.getValue();
    }
}
