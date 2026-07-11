package miuix.appcompat.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import miuix.internal.util.ViewUtils;

/* JADX INFO: loaded from: classes2.dex */
public class TextAlignLayout extends LinearLayout {
    private boolean mDialogPanelHasCheckbox;

    public TextAlignLayout(Context context) {
        super(context);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int childCount = getChildCount();
        boolean z2 = true;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (z2 && (childAt instanceof TextView)) {
                TextView textView = (TextView) childAt;
                boolean z3 = textView.getLineCount() <= 1 && !this.mDialogPanelHasCheckbox;
                if (z3) {
                    textView.setGravity(1);
                } else {
                    textView.setGravity(ViewUtils.isLayoutRtl(childAt) ? 5 : 3);
                }
                z2 = z3;
            }
        }
    }

    public void setDialogPanelHasCheckbox(boolean z2) {
        this.mDialogPanelHasCheckbox = z2;
    }

    public TextAlignLayout(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TextAlignLayout(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public TextAlignLayout(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
