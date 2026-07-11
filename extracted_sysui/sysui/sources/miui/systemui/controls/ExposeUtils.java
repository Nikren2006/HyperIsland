package miui.systemui.controls;

import H0.d;
import H0.e;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Process;
import android.os.UserHandle;
import android.service.controls.actions.ControlAction;
import android.service.controls.actions.ControlActionWrapper;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewRootImpl;
import java.lang.reflect.Method;
import kotlin.jvm.internal.n;
import miui.service.controls.actions.ControlActionWrapperExpose;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi", "BlockedPrivateApi"})
public final class ExposeUtils {
    private static final String TAG = "ExposeUtils";
    public static final ExposeUtils INSTANCE = new ExposeUtils();
    private static final d GET_USER_METHOD$delegate = e.b(ExposeUtils$GET_USER_METHOD$2.INSTANCE);
    private static final d GET_SURFACE_CONTROL_METHOD$delegate = e.b(ExposeUtils$GET_SURFACE_CONTROL_METHOD$2.INSTANCE);

    private ExposeUtils() {
    }

    private final Method getGET_SURFACE_CONTROL_METHOD() {
        return (Method) GET_SURFACE_CONTROL_METHOD$delegate.getValue();
    }

    private final Method getGET_USER_METHOD() {
        return (Method) GET_USER_METHOD$delegate.getValue();
    }

    public final ControlActionWrapper ControlActionWrapper(ControlAction action) {
        n.g(action, "action");
        return ControlActionWrapperExpose.newInstance(action);
    }

    public final UserHandle getUserExpose(Context context) {
        n.g(context, "<this>");
        try {
            Method get_user_method = getGET_USER_METHOD();
            Object objInvoke = get_user_method != null ? get_user_method.invoke(context, null) : null;
            n.e(objInvoke, "null cannot be cast to non-null type android.os.UserHandle");
            return (UserHandle) objInvoke;
        } catch (Throwable unused) {
            Log.e(TAG, "get user from context failed.");
            UserHandle userHandleMyUserHandle = Process.myUserHandle();
            n.d(userHandleMyUserHandle);
            return userHandleMyUserHandle;
        }
    }

    public final boolean isSurfaceControlValid(ViewRootImpl viewRootImpl) {
        n.g(viewRootImpl, "<this>");
        try {
            Method get_surface_control_method = getGET_SURFACE_CONTROL_METHOD();
            SurfaceControl surfaceControl = (SurfaceControl) (get_surface_control_method != null ? get_surface_control_method.invoke(viewRootImpl, null) : null);
            if (surfaceControl != null) {
                return surfaceControl.isValid();
            }
            return true;
        } catch (Throwable th) {
            Log.e(TAG, " error when getSurfaceControl", th);
            return true;
        }
    }

    public final boolean isSurfaceControlValid(View view) {
        n.g(view, "<this>");
        ViewRootImpl viewRootImpl = view.getViewRootImpl();
        if (viewRootImpl != null) {
            return isSurfaceControlValid(viewRootImpl);
        }
        return true;
    }
}
