package miui.systemui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
@SuppressLint({"AppCompatCustomView"})
public final class VisibleFocusedTextView extends android.widget.TextView {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VisibleFocusedTextView(Context context) {
        this(context, null, 0, 0, 14, null);
        n.g(context, "context");
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        return false;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return getVisibility() == 0 && getAlpha() == 1.0f;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VisibleFocusedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VisibleFocusedTextView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0, 8, null);
        n.g(context, "context");
    }

    public /* synthetic */ VisibleFocusedTextView(Context context, AttributeSet attributeSet, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i4 & 2) != 0 ? null : attributeSet, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VisibleFocusedTextView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        n.g(context, "context");
    }
}
