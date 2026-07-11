package miuix.androidbasewidget.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import miuix.animation.Folme;
import miuix.animation.IHoverStyle;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes4.dex */
public class SingleCenterTextView extends AppCompatTextView {
    private boolean mEnableSingleCenter;

    public SingleCenterTextView(Context context) {
        this(context, null);
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.mEnableSingleCenter && getLayout().getLineCount() == 1 && getGravity() != 1) {
            setGravity(1);
        }
    }

    public void setEnableSingleCenter(boolean z2) {
        this.mEnableSingleCenter = z2;
    }

    public SingleCenterTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SingleCenterTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Folme.useAt(this).hover().setEffect(IHoverStyle.HoverEffect.NORMAL).handleHoverOf(this, new AnimConfig[0]);
    }
}
