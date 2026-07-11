package miui.systemui.util;

import android.view.MotionEvent;

/* JADX INFO: loaded from: classes4.dex */
public final class MotionEventKt {
    public static final String getMotionEventAction(int i2) {
        return MotionEvent.actionToString(i2);
    }

    public static final String getMotionEventAction(MotionEvent motionEvent) {
        kotlin.jvm.internal.n.g(motionEvent, "<this>");
        return getMotionEventAction(motionEvent.getActionMasked());
    }
}
