package miui.systemui.controlcenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class NonTouchableFrameLayout extends FrameLayout {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NonTouchableFrameLayout(Context context) {
        this(context, null, 0, 6, null);
        n.g(context, "context");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public NonTouchableFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        n.g(context, "context");
    }

    public /* synthetic */ NonTouchableFrameLayout(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NonTouchableFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2, 0, 8, null);
        n.g(context, "context");
    }
}
