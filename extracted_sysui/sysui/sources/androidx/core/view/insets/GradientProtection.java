package androidx.core.view.insets;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.animation.PathInterpolator;
import androidx.annotation.ColorInt;

/* JADX INFO: loaded from: classes.dex */
public class GradientProtection extends Protection {
    private static final float[] ALPHAS;

    @ColorInt
    private int mColor;
    private final int[] mColors;
    private final GradientDrawable mDrawable;
    private boolean mHasColor;
    private float mScale;

    static {
        float[] fArr = new float[100];
        ALPHAS = fArr;
        PathInterpolator pathInterpolator = new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f);
        int length = fArr.length - 1;
        for (int i2 = length; i2 >= 0; i2--) {
            ALPHAS[i2] = pathInterpolator.getInterpolation((length - i2) / length);
        }
    }

    public GradientProtection(int i2) {
        super(i2);
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.mDrawable = gradientDrawable;
        this.mColors = new int[ALPHAS.length];
        this.mColor = 0;
        this.mScale = 1.2f;
        if (i2 == 1) {
            gradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            return;
        }
        if (i2 == 2) {
            gradientDrawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
        } else if (i2 == 4) {
            gradientDrawable.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
        } else {
            if (i2 != 8) {
                return;
            }
            gradientDrawable.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        }
    }

    private void setColorInner(@ColorInt int i2) {
        if (this.mColor != i2) {
            this.mColor = i2;
            toColors(i2, this.mColors);
            this.mDrawable.setColors(this.mColors);
            setDrawable(this.mDrawable);
        }
    }

    private static void toColors(int i2, int[] iArr) {
        for (int length = iArr.length - 1; length >= 0; length--) {
            iArr[length] = Color.argb((int) (ALPHAS[length] * Color.alpha(i2)), Color.red(i2), Color.green(i2), Color.blue(i2));
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

    public float getScale() {
        return this.mScale;
    }

    @Override // androidx.core.view.insets.Protection
    public int getThickness(int i2) {
        return (int) (this.mScale * i2);
    }

    public void setColor(@ColorInt int i2) {
        this.mHasColor = true;
        setColorInner(i2);
    }

    public void setScale(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Scale must not be negative.");
        }
        this.mScale = f2;
        updateLayout();
    }

    public GradientProtection(int i2, @ColorInt int i3) {
        this(i2);
        setColor(i3);
    }
}
