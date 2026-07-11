package androidx.core.view.insets;

import android.graphics.drawable.ColorDrawable;
import androidx.annotation.ColorInt;

/* JADX INFO: loaded from: classes.dex */
public class ColorProtection extends Protection {

    @ColorInt
    private int mColor;
    private final ColorDrawable mDrawable;
    private boolean mHasColor;

    public ColorProtection(int i2) {
        super(i2);
        this.mDrawable = new ColorDrawable();
        this.mColor = 0;
    }

    private void setColorInner(@ColorInt int i2) {
        if (this.mColor != i2) {
            this.mColor = i2;
            this.mDrawable.setColor(i2);
            setDrawable(this.mDrawable);
        }
    }

    @Override // androidx.core.view.insets.Protection
    public void dispatchColorHint(@ColorInt int i2) {
        if (this.mHasColor) {
            return;
        }
        setColorInner(i2);
    }

    @ColorInt
    public int getColor() {
        return this.mColor;
    }

    @Override // androidx.core.view.insets.Protection
    public boolean occupiesCorners() {
        return true;
    }

    public void setColor(@ColorInt int i2) {
        this.mHasColor = true;
        setColorInner(i2);
    }

    public ColorProtection(int i2, @ColorInt int i3) {
        this(i2);
        setColor(i3);
    }
}
