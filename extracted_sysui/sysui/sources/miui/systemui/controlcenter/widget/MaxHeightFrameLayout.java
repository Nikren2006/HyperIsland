package miui.systemui.controlcenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public class MaxHeightFrameLayout extends FrameLayout implements IMaxHeight {
    private int maxHeight;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MaxHeightFrameLayout(Context context) {
        this(context, null, 0, 0, 14, null);
        n.g(context, "context");
    }

    @Override // miui.systemui.controlcenter.widget.IMaxHeight
    public int getMaxHeight() {
        return this.maxHeight;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        n.g(info, "info");
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClickable(false);
        info.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE && View.MeasureSpec.getSize(i3) > getMaxHeight()) {
            i3 = View.MeasureSpec.makeMeasureSpec(getMaxHeight(), Integer.MIN_VALUE);
        }
        super.onMeasure(i2, i3);
    }

    @Override // miui.systemui.controlcenter.widget.IMaxHeight
    public void setMaxHeight(int i2) {
        this.maxHeight = i2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MaxHeightFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MaxHeightFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0, 8, null);
        n.g(context, "context");
    }

    public /* synthetic */ MaxHeightFrameLayout(Context context, AttributeSet attributeSet, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i4 & 2) != 0 ? null : attributeSet, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MaxHeightFrameLayout(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        n.g(context, "context");
        this.maxHeight = Integer.MAX_VALUE;
    }
}
