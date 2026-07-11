package miui.systemui.controlcenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class VisibleFocusedTextView extends TextView {
    public VisibleFocusedTextView(Context context) {
        super(context);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i2) {
        return false;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return getVisibility() == 0 && getAlpha() == 1.0f;
    }

    public VisibleFocusedTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VisibleFocusedTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
