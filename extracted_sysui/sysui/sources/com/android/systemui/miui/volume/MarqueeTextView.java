package com.android.systemui.miui.volume;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes2.dex */
class MarqueeTextView extends TextView {
    private boolean mFocused;

    public MarqueeTextView(Context context) {
        this(context, null);
    }

    public void forceGetFocus(boolean z2) {
        this.mFocused = z2;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.mFocused;
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFocused = false;
    }
}
