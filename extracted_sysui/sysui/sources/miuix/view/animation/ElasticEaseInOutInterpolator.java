package miuix.view.animation;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes5.dex */
public class ElasticEaseInOutInterpolator implements Interpolator {
    private final float mAmplitude;
    private final float mPeriod;

    public ElasticEaseInOutInterpolator() {
        this(0.0f, 0.0f);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        float fAsin;
        float f3 = this.mPeriod;
        float f4 = this.mAmplitude;
        if (f2 == 0.0f) {
            return 0.0f;
        }
        float f5 = f2 / 0.5f;
        if (f5 == 2.0f) {
            return 1.0f;
        }
        if (f3 == 0.0f) {
            f3 = 0.45000002f;
        }
        if (f4 == 0.0f || f4 < 1.0f) {
            fAsin = f3 / 4.0f;
            f4 = 1.0f;
        } else {
            fAsin = (float) ((((double) f3) / 6.283185307179586d) * Math.asin(1.0f / f4));
        }
        if (f5 < 1.0f) {
            return ((float) (((double) f4) * Math.pow(2.0d, 10.0f * r11) * Math.sin((((double) ((f5 - 1.0f) - fAsin)) * 6.283185307179586d) / ((double) f3)))) * (-0.5f);
        }
        return (float) ((((double) f4) * Math.pow(2.0d, (-10.0f) * r11) * Math.sin((((double) ((f5 - 1.0f) - fAsin)) * 6.283185307179586d) / ((double) f3)) * 0.5d) + 1.0d);
    }

    public ElasticEaseInOutInterpolator(float f2, float f3) {
        this.mAmplitude = f2;
        this.mPeriod = f3;
    }
}
