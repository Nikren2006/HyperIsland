package miui.systemui.controls;

import android.util.Log;
import android.view.ViewRootImpl;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class ExposeUtils$GET_SURFACE_CONTROL_METHOD$2 extends o implements Function0 {
    public static final ExposeUtils$GET_SURFACE_CONTROL_METHOD$2 INSTANCE = new ExposeUtils$GET_SURFACE_CONTROL_METHOD$2();

    public ExposeUtils$GET_SURFACE_CONTROL_METHOD$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            return ViewRootImpl.class.getDeclaredMethod("getSurfaceControl", null);
        } catch (Throwable th) {
            Log.e("ExposeUtils", "get ViewRootImpl.getSurfaceControl method failed.", th);
            return null;
        }
    }
}
