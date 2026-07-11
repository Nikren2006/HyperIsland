package miuix.appcompat.internal.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import miuix.appcompat.R;

/* JADX INFO: loaded from: classes3.dex */
public class SecondaryTabExpandContainerView extends SecondaryTabContainerView {
    public SecondaryTabExpandContainerView(Context context) {
        super(context);
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabContainerView
    public int getDefaultTabTextStyle() {
        return R.attr.actionBarTabTextSecondaryExpandStyle;
    }

    @Override // miuix.appcompat.internal.app.widget.SecondaryTabContainerView
    public int getTabActivatedTextStyle() {
        return R.attr.actionBarTabActivatedTextSecondaryExpandStyle;
    }

    public SecondaryTabExpandContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SecondaryTabExpandContainerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
