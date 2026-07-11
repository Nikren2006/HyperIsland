package miui.systemui.animation.drawable;

import android.graphics.drawable.AnimatedVectorDrawable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class VectorDrawableSetParams {
    private AnimatedVectorDrawable mDrawable;
    private float rotationFraction;
    private float scaleXFraction;
    private float scaleYFraction;
    float strokeAlphaFraction;
    float strokeColorFraction;
    float strokeWidthFraction;
    private float translateXFraction;
    private float translateYFraction;
    private final ArrayList<VectorDrawableParamsExt> mDrawableParams = new ArrayList<>();
    private long mTotalTime = 0;
    private float fraction = Float.NaN;
    private long mMinDelay = 2147483647L;
    private float mCustomScale = -1.0f;

    public void addDrawableParams(VectorDrawableParamsExt vectorDrawableParamsExt) {
        this.mDrawableParams.add(vectorDrawableParamsExt);
        this.mTotalTime = Math.max(this.mTotalTime, vectorDrawableParamsExt.getDelay() + vectorDrawableParamsExt.getDuration());
        this.mMinDelay = Math.min(this.mMinDelay, vectorDrawableParamsExt.getDelay());
        if (this.mCustomScale > 0.0f) {
            if ("scaleX".equals(vectorDrawableParamsExt.getPropertyName())) {
                if (vectorDrawableParamsExt.getStartScaleX() != 1.0f) {
                    vectorDrawableParamsExt.setStartScaleX(this.mCustomScale);
                }
                if (vectorDrawableParamsExt.getEndScaleX() != 1.0f) {
                    vectorDrawableParamsExt.setEndScaleX(this.mCustomScale);
                    return;
                }
                return;
            }
            if ("scaleY".equals(vectorDrawableParamsExt.getPropertyName())) {
                if (vectorDrawableParamsExt.getStartScaleY() != 1.0f) {
                    vectorDrawableParamsExt.setStartScaleY(this.mCustomScale);
                }
                if (vectorDrawableParamsExt.getEndScaleY() != 1.0f) {
                    vectorDrawableParamsExt.setEndScaleY(this.mCustomScale);
                }
            }
        }
    }

    public void clearDrawableParams() {
        this.fraction = Float.NaN;
        this.mDrawableParams.clear();
    }

    public AnimatedVectorDrawable getDrawable() {
        return this.mDrawable;
    }

    public ArrayList<VectorDrawableParamsExt> getDrawableParams() {
        return this.mDrawableParams;
    }

    public float getFraction() {
        if (Float.isNaN(this.fraction)) {
            return 0.0f;
        }
        return this.fraction;
    }

    public float getRotationFraction() {
        return this.rotationFraction;
    }

    public float getScaleXFraction() {
        return this.scaleXFraction;
    }

    public float getScaleYFraction() {
        return this.scaleYFraction;
    }

    public float getStrokeAlphaFraction() {
        return this.strokeAlphaFraction;
    }

    public float getStrokeColorFraction() {
        return this.strokeColorFraction;
    }

    public float getStrokeWidthFraction() {
        return this.strokeWidthFraction;
    }

    public float getTranslateXFraction() {
        return this.translateXFraction;
    }

    public float getTranslateYFraction() {
        return this.translateYFraction;
    }

    public void setCustomScale(float f2) {
        this.mCustomScale = f2;
    }

    public void setDrawable(AnimatedVectorDrawable animatedVectorDrawable) {
        this.mDrawable = animatedVectorDrawable;
    }

    public void setFraction(float f2) {
        if (this.fraction == f2) {
            return;
        }
        int i2 = (int) (this.mTotalTime * f2);
        for (VectorDrawableParamsExt vectorDrawableParamsExt : this.mDrawableParams) {
            long j2 = i2;
            if (j2 > vectorDrawableParamsExt.getDelay() + vectorDrawableParamsExt.getDuration() && vectorDrawableParamsExt.getFraction() != 1.0f) {
                vectorDrawableParamsExt.setFraction(1.0f);
            } else if (j2 < vectorDrawableParamsExt.getDelay() && vectorDrawableParamsExt.getFraction() != 0.0f) {
                vectorDrawableParamsExt.setFraction(0.0f);
            } else if (j2 >= vectorDrawableParamsExt.getDelay() && j2 <= vectorDrawableParamsExt.getDelay() + vectorDrawableParamsExt.getDuration()) {
                vectorDrawableParamsExt.setFraction((j2 - vectorDrawableParamsExt.getDelay()) / vectorDrawableParamsExt.getDuration());
            }
        }
        this.fraction = f2;
    }

    public void setRotationFraction(float f2) {
        for (VectorDrawableParamsExt vectorDrawableParamsExt : this.mDrawableParams) {
            if ("rotation".equals(vectorDrawableParamsExt.getPropertyName())) {
                vectorDrawableParamsExt.setRotationFraction(f2);
            }
        }
        this.rotationFraction = f2;
    }

    public void setScaleXFraction(float f2) {
        for (VectorDrawableParamsExt vectorDrawableParamsExt : this.mDrawableParams) {
            if ("scaleX".equals(vectorDrawableParamsExt.getPropertyName())) {
                vectorDrawableParamsExt.setScaleXFraction(f2);
            }
        }
        this.scaleXFraction = f2;
    }

    public void setScaleYFraction(float f2) {
        for (VectorDrawableParamsExt vectorDrawableParamsExt : this.mDrawableParams) {
            if ("scaleY".equals(vectorDrawableParamsExt.getPropertyName())) {
                vectorDrawableParamsExt.setScaleYFraction(f2);
            }
        }
        this.scaleYFraction = f2;
    }

    public void setStrokeAlphaFraction(float f2) {
        for (VectorDrawableParamsExt vectorDrawableParamsExt : this.mDrawableParams) {
            if ("strokeAlpha".equals(vectorDrawableParamsExt.getPropertyName())) {
                vectorDrawableParamsExt.setStrokeAlphaFraction(f2);
            }
        }
        this.strokeAlphaFraction = f2;
    }

    public void setStrokeColorFraction(float f2) {
        for (VectorDrawableParamsExt vectorDrawableParamsExt : this.mDrawableParams) {
            if ("strokeColor".equals(vectorDrawableParamsExt.getPropertyName())) {
                vectorDrawableParamsExt.setStrokeColorFraction(f2);
            }
        }
        this.strokeColorFraction = f2;
    }

    public void setStrokeWidthFraction(float f2) {
        for (VectorDrawableParamsExt vectorDrawableParamsExt : this.mDrawableParams) {
            if ("strokeWidth".equals(vectorDrawableParamsExt.getPropertyName())) {
                vectorDrawableParamsExt.setStrokeWidthFraction(f2);
            }
        }
        this.strokeWidthFraction = f2;
    }

    public void setTranslateXFraction(float f2) {
        for (VectorDrawableParamsExt vectorDrawableParamsExt : this.mDrawableParams) {
            if ("translateX".equals(vectorDrawableParamsExt.getPropertyName())) {
                vectorDrawableParamsExt.setTranslateXFraction(f2);
            }
        }
        this.translateXFraction = f2;
    }

    public void setTranslateYFraction(float f2) {
        for (VectorDrawableParamsExt vectorDrawableParamsExt : this.mDrawableParams) {
            if ("translateY".equals(vectorDrawableParamsExt.getPropertyName())) {
                vectorDrawableParamsExt.setTranslateYFraction(f2);
            }
        }
        this.translateYFraction = f2;
    }

    public void toFirstFrame() {
        for (VectorDrawableParamsExt vectorDrawableParamsExt : this.mDrawableParams) {
            if (this.mMinDelay == vectorDrawableParamsExt.getDelay()) {
                vectorDrawableParamsExt.setFraction(0.0f);
            }
        }
    }

    public String toString() {
        return "VectorDrawableSetParams{this@" + hashCode() + " fraction=" + this.fraction + " mCustomScale=" + this.mCustomScale + '}';
    }
}
