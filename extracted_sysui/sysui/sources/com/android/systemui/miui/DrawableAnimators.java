package com.android.systemui.miui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes2.dex */
public class DrawableAnimators {
    private static Interpolator DECELERATE = new DecelerateInterpolator(2.0f);
    private static final int DURATION = 300;
    private static final String TAG = "DrawableAnimatorHelper";

    public static class CornerRadiiTypeEvaluator implements TypeEvaluator<float[]> {
        private float[] mFallbackStartValue;
        private float[] mResult;

        public CornerRadiiTypeEvaluator(Drawable drawable) {
            if (drawable instanceof GradientDrawable) {
                this.mFallbackStartValue = getFallBackArray(drawable);
            }
        }

        private float[] getFallBackArray(Drawable drawable) {
            float cornerRadius = GradientDrawableCompat.getCornerRadius((GradientDrawable) drawable);
            return new float[]{cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius};
        }

        @Override // android.animation.TypeEvaluator
        public float[] evaluate(float f2, float[] fArr, float[] fArr2) {
            if (fArr == null) {
                fArr = this.mFallbackStartValue;
            }
            if (this.mResult == null) {
                this.mResult = new float[fArr.length];
            }
            int i2 = 0;
            while (true) {
                float[] fArr3 = this.mResult;
                if (i2 >= fArr3.length) {
                    return fArr3;
                }
                float f3 = fArr[i2];
                fArr3[i2] = f3 + ((fArr2[i2] - f3) * f2);
                i2++;
            }
        }
    }

    private DrawableAnimators() {
    }

    public static Animator fade(Drawable drawable, boolean z2) {
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, "alpha", z2 ? 255 : 0);
        objectAnimatorOfInt.setDuration(300L);
        objectAnimatorOfInt.setInterpolator(DECELERATE);
        objectAnimatorOfInt.setAutoCancel(true);
        objectAnimatorOfInt.start();
        return objectAnimatorOfInt;
    }

    public static Animator updateCornerRadii(Context context, Drawable drawable, int i2) {
        TypedArray typedArrayObtainTypedArray = context.getResources().obtainTypedArray(i2);
        int length = typedArrayObtainTypedArray.length();
        float[] fArr = new float[length];
        for (int i3 = 0; i3 < length; i3++) {
            fArr[i3] = typedArrayObtainTypedArray.getDimension(i3, 0.0f);
        }
        typedArrayObtainTypedArray.recycle();
        return updateCornerRadii(drawable, fArr);
    }

    public static Animator updateCornerRadius(Drawable drawable, float... fArr) {
        if (!(drawable instanceof GradientDrawable)) {
            Log.e(TAG, "cornerRadius change cannot be applied to " + drawable);
            return null;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(drawable, "cornerRadius", fArr);
        objectAnimatorOfFloat.setDuration(300L);
        objectAnimatorOfFloat.setInterpolator(DECELERATE);
        objectAnimatorOfFloat.setAutoCancel(true);
        objectAnimatorOfFloat.start();
        return objectAnimatorOfFloat;
    }

    public static Animator updateCornerRadii(Drawable drawable, float[] fArr) {
        if (!(drawable instanceof GradientDrawable)) {
            Log.e(TAG, "cornerRadii change cannot be applied to " + drawable);
            return null;
        }
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(drawable, "cornerRadii", new CornerRadiiTypeEvaluator(drawable), fArr);
        objectAnimatorOfObject.setDuration(300L);
        objectAnimatorOfObject.setInterpolator(DECELERATE);
        objectAnimatorOfObject.setAutoCancel(true);
        objectAnimatorOfObject.start();
        return objectAnimatorOfObject;
    }
}
