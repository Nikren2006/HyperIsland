package miui.systemui.view;

import H0.d;
import H0.e;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.ViewRootImpl;
import java.lang.reflect.Method;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class ViewRootImplCompatKt {
    private static final String TAG = "SurfaceControlCompat";
    private static final d ViewRootImpl_getSurfaceControl$delegate = e.b(ViewRootImplCompatKt$ViewRootImpl_getSurfaceControl$2.INSTANCE);

    public static final SurfaceControl getSurfaceControlCompat(ViewRootImpl viewRootImpl) {
        n.g(viewRootImpl, "<this>");
        try {
            Method viewRootImpl_getSurfaceControl = getViewRootImpl_getSurfaceControl();
            Object objInvoke = viewRootImpl_getSurfaceControl != null ? viewRootImpl_getSurfaceControl.invoke(viewRootImpl, null) : null;
            if (objInvoke instanceof SurfaceControl) {
                return (SurfaceControl) objInvoke;
            }
            return null;
        } catch (Throwable th) {
            Log.e(TAG, "invoke ViewRootImpl_getSurfaceControl failed.", th);
            return null;
        }
    }

    private static final Method getViewRootImpl_getSurfaceControl() {
        return (Method) ViewRootImpl_getSurfaceControl$delegate.getValue();
    }
}
