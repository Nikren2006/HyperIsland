package miui.systemui.view;

import android.util.Log;
import android.view.SurfaceControl;
import java.lang.reflect.Constructor;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class SurfaceControlCompatKt$SurfaceControl_constructor$2 extends o implements Function0 {
    public static final SurfaceControlCompatKt$SurfaceControl_constructor$2 INSTANCE = new SurfaceControlCompatKt$SurfaceControl_constructor$2();

    public SurfaceControlCompatKt$SurfaceControl_constructor$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Constructor<SurfaceControl> invoke() {
        try {
            return SurfaceControl.class.getConstructor(SurfaceControl.class, String.class);
        } catch (Throwable th) {
            Log.e("SurfaceControlCompat", "get SurfaceControl_constructor method failed.", th);
            return null;
        }
    }
}
