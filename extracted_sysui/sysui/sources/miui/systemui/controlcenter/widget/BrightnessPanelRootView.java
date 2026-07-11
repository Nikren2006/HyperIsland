package miui.systemui.controlcenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import androidx.lifecycle.Lifecycle;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class BrightnessPanelRootView extends MaxHeightFrameLayout {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "BrightnessPanelRootView";
    private Lifecycle lifecycle;
    private boolean touching;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BrightnessPanelRootView(Context context) {
        this(context, null, 0, 0, 14, null);
        n.g(context, "context");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getActionMasked()) : null;
        this.touching = !((numValueOf != null && numValueOf.intValue() == 3) || (numValueOf != null && numValueOf.intValue() == 1));
        return super.dispatchTouchEvent(motionEvent);
    }

    public final Lifecycle getLifecycle() {
        return this.lifecycle;
    }

    public final boolean getTouching() {
        return this.touching;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Lifecycle.State currentState;
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle == null || (currentState = lifecycle.getCurrentState()) == null || currentState.compareTo(Lifecycle.State.RESUMED) >= 0) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        Log.i(TAG, "intercept touch event " + currentState);
        return true;
    }

    public final void setLifecycle(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public final void setTouching(boolean z2) {
        this.touching = z2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BrightnessPanelRootView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BrightnessPanelRootView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0, 8, null);
        n.g(context, "context");
    }

    public /* synthetic */ BrightnessPanelRootView(Context context, AttributeSet attributeSet, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i4 & 2) != 0 ? null : attributeSet, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BrightnessPanelRootView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        n.g(context, "context");
    }
}
