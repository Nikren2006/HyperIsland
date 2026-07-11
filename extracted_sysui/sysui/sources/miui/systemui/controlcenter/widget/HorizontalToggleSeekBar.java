package miui.systemui.controlcenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.windowview.GestureDispatcher;

/* JADX INFO: loaded from: classes.dex */
public final class HorizontalToggleSeekBar extends HorizontalSeekBar implements GestureDispatcher.GestureAcceptor {
    private String accessibilityLabel;
    private GestureDispatcher.GestureHelper gestureHelper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HorizontalToggleSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 4, null);
        n.g(context, "context");
    }

    @Override // miui.systemui.controlcenter.windowview.GestureDispatcher.GestureAcceptor
    public GestureDispatcher.GestureHelper createGestureHelper(GestureDispatcher gestureDispatcher) {
        n.g(gestureDispatcher, "gestureDispatcher");
        GestureDispatcher.GestureHelper gestureHelper = new GestureDispatcher.GestureHelper(this, gestureDispatcher, Boolean.FALSE);
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

    public final String getAccessibilityLabel() {
        return this.accessibilityLabel;
    }

    public void internalSetPadding(int i2, int i3, int i4, int i5) {
    }

    public boolean isInScrollingContainer() {
        return false;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        String str = this.accessibilityLabel;
        if (str == null || accessibilityNodeInfo == null) {
            return;
        }
        accessibilityNodeInfo.setText(str);
    }

    @Override // miui.systemui.controlcenter.widget.HorizontalSeekBar, android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.d("VerticalToggleSeekBar", "toggle onTouch " + (motionEvent != null ? Integer.valueOf(motionEvent.getActionMasked()) : null) + " " + (this.gestureHelper != null));
        if (!isEnabled()) {
            setEnabled(true);
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void setAccessibilityLabel(String str) {
        this.accessibilityLabel = str;
    }
}
