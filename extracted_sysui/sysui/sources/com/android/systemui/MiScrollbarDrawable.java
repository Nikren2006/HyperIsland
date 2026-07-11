package com.android.systemui;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class MiScrollbarDrawable extends InsetDrawable {
    private int verticalOffset;

    public MiScrollbarDrawable(@Nullable Drawable drawable, int i2) {
        super(drawable, i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i2, int i3, int i4, int i5) {
        int i6 = this.verticalOffset;
        if (i3 > i6) {
            super.setBounds(i2, i3 - i6, i4, i5 - i6);
        } else {
            super.setBounds(i2, i3, i4, i5);
        }
    }

    public void setVerticalOffset(int i2) {
        this.verticalOffset = i2;
    }

    public MiScrollbarDrawable(@Nullable Drawable drawable, float f2) {
        super(drawable, f2);
    }

    public MiScrollbarDrawable(@Nullable Drawable drawable, int i2, int i3, int i4, int i5) {
        super(drawable, i2, i3, i4, i5);
    }

    public MiScrollbarDrawable(@Nullable Drawable drawable, float f2, float f3, float f4, float f5) {
        super(drawable, f2, f3, f4, f5);
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(@NonNull Rect rect) {
        int i2 = rect.top;
        int i3 = this.verticalOffset;
        if (i2 > i3) {
            rect.top = i2 - i3;
            rect.bottom -= i3;
        }
        super.setBounds(rect);
    }
}
