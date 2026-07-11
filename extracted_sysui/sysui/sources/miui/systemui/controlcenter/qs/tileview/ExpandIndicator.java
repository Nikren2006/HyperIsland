package miui.systemui.controlcenter.qs.tileview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.windowview.GestureDispatcher;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public final class ExpandIndicator extends ImageView implements GestureDispatcher.GestureAcceptor {
    private GestureDispatcher.GestureHelper gestureHelper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpandIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
    }

    @Override // miui.systemui.controlcenter.windowview.GestureDispatcher.GestureAcceptor
    public GestureDispatcher.GestureHelper createGestureHelper(GestureDispatcher gestureDispatcher) {
        n.g(gestureDispatcher, "gestureDispatcher");
        GestureDispatcher.GestureHelper gestureHelper = new GestureDispatcher.GestureHelper(this, gestureDispatcher) { // from class: miui.systemui.controlcenter.qs.tileview.ExpandIndicator.createGestureHelper.1
            @Override // miui.systemui.controlcenter.windowview.GestureDispatcher.GestureHelper
            public boolean check(boolean z2, boolean z3) {
                return true;
            }
        };
        this.gestureHelper = gestureHelper;
        n.d(gestureHelper);
        return gestureHelper;
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        GestureDispatcher.GestureHelper gestureHelper;
        if (motionEvent != null && (gestureHelper = this.gestureHelper) != null) {
            gestureHelper.onInterceptTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
