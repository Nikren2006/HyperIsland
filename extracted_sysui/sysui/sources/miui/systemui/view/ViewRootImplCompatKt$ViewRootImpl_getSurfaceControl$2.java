package miui.systemui.view;

import android.util.Log;
import android.view.ViewRootImpl;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class ViewRootImplCompatKt$ViewRootImpl_getSurfaceControl$2 extends o implements Function0 {
    public static final ViewRootImplCompatKt$ViewRootImpl_getSurfaceControl$2 INSTANCE = new ViewRootImplCompatKt$ViewRootImpl_getSurfaceControl$2();

    public ViewRootImplCompatKt$ViewRootImpl_getSurfaceControl$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            return ViewRootImpl.class.getDeclaredMethod("getSurfaceControl", null);
        } catch (Throwable th) {
            Log.e("SurfaceControlCompat", "get ViewRootImpl_getSurfaceControl method failed.", th);
            return null;
        }
    }
}
