package miui.systemui.controlcenter.panel;

import android.view.MotionEvent;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public interface TouchController {
    static /* synthetic */ boolean handleMotionEvent$default(TouchController touchController, MotionEvent motionEvent, boolean z2, boolean z3, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: handleMotionEvent");
        }
        if ((i2 & 2) != 0) {
            z2 = false;
        }
        if ((i2 & 4) != 0) {
            z3 = false;
        }
        return touchController.handleMotionEvent(motionEvent, z2, z3);
    }

    default boolean handleMotionEvent(MotionEvent event) {
        n.g(event, "event");
        return false;
    }

    default boolean onInterceptTouchEvent(MotionEvent event) {
        n.g(event, "event");
        return false;
    }

    default boolean handleMotionEvent(MotionEvent event, boolean z2, boolean z3) {
        n.g(event, "event");
        return handleMotionEvent(event);
    }
}
