package miui.systemui.controlcenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import kotlin.jvm.internal.n;
import miui.systemui.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class NoTransformTouchFrameLayout extends FrameLayout {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoTransformTouchFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0, 12, null);
        n.g(context, "context");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEventObtain = null;
        if (motionEvent != null && (getScaleX() < 1.0f || getScaleY() < 1.0f)) {
            motionEventObtain = MotionEvent.obtain(motionEvent);
            if (getMatrix() != null) {
                motionEventObtain.transform(getMatrix());
            }
        }
        if (motionEventObtain != null) {
            motionEvent = motionEventObtain;
        }
        boolean zDispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (motionEventObtain != null) {
            motionEventObtain.recycle();
        }
        return zDispatchTouchEvent;
    }
}
