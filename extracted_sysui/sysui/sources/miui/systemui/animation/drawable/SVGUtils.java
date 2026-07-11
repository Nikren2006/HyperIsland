package miui.systemui.animation.drawable;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.KeyframeSet;
import android.animation.Keyframes;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawableParams;
import android.text.TextUtils;
import android.util.PathParser;
import androidx.core.graphics.drawable.DrawableCompat;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SVGUtils {
    public static void addStrokeColor(AnimatedVectorDrawable animatedVectorDrawable, VectorDrawableSetParams vectorDrawableSetParams, String str, long j2, long j3, int i2, int i3) {
        VectorDrawableParamsExt vectorDrawableParamsExt = new VectorDrawableParamsExt();
        vectorDrawableParamsExt.setVectorDrawable(SVGUtilsExt.getVectorDrawableCompat(animatedVectorDrawable));
        vectorDrawableParamsExt.setTargetName(str);
        vectorDrawableParamsExt.setPropertyName("strokeColor");
        vectorDrawableParamsExt.setDuration(j2);
        vectorDrawableParamsExt.setDelay(j3);
        vectorDrawableParamsExt.setStartStrokeColor(i2);
        vectorDrawableParamsExt.setEndStrokeColor(i3);
        vectorDrawableSetParams.addDrawableParams(vectorDrawableParamsExt);
    }

    public static void analyzeAnimator(Animator animator, VectorDrawableSetParams vectorDrawableSetParams, int i2) {
        analyzeAnimator(animator, vectorDrawableSetParams, i2, 0, 1000);
    }

    private static void convertToVectorDrawableParams(ObjectAnimator objectAnimator, VectorDrawableSetParams vectorDrawableSetParams, VectorDrawableParamsExt vectorDrawableParamsExt, int i2, int i3, int i4) {
        long j2 = i3;
        if (objectAnimator.getStartDelay() < j2 || objectAnimator.getStartDelay() >= i4) {
            return;
        }
        PropertyValuesHolder[] values = objectAnimator.getValues();
        for (int i5 = 0; i5 < values.length; i5++) {
            KeyframeSet keyframesCompat = SVGUtilsExt.getKeyframesCompat(values[i5]);
            if (keyframesCompat instanceof KeyframeSet) {
                List keyframes = keyframesCompat.getKeyframes();
                if (keyframes.size() == 2) {
                    if ("pathData".equals(objectAnimator.getPropertyName())) {
                        vectorDrawableParamsExt.setStartPathData((PathParser.PathData) ((Keyframe) keyframes.get(0)).getValue());
                        vectorDrawableParamsExt.setEndPathData((PathParser.PathData) ((Keyframe) keyframes.get(1)).getValue());
                    } else if ("strokeWidth".equals(objectAnimator.getPropertyName())) {
                        vectorDrawableParamsExt.setStartStrokeWidth(((Float) ((Keyframe) keyframes.get(0)).getValue()).floatValue());
                        vectorDrawableParamsExt.setEndStrokeWidth(((Float) ((Keyframe) keyframes.get(1)).getValue()).floatValue());
                    } else if ("strokeColor".equals(objectAnimator.getPropertyName())) {
                        vectorDrawableParamsExt.setStartStrokeColor(((Integer) ((Keyframe) keyframes.get(0)).getValue()).intValue());
                        vectorDrawableParamsExt.setEndStrokeColor(((Integer) ((Keyframe) keyframes.get(1)).getValue()).intValue());
                    } else if ("strokeAlpha".equals(objectAnimator.getPropertyName())) {
                        vectorDrawableParamsExt.setStartStrokeAlpha(((Float) ((Keyframe) keyframes.get(0)).getValue()).floatValue());
                        vectorDrawableParamsExt.setEndStrokeAlpha(((Float) ((Keyframe) keyframes.get(1)).getValue()).floatValue());
                    } else if ("fillColor".equals(objectAnimator.getPropertyName())) {
                        int iIntValue = ((Integer) ((Keyframe) keyframes.get(0)).getValue()).intValue();
                        int iIntValue2 = ((Integer) ((Keyframe) keyframes.get(1)).getValue()).intValue();
                        boolean z2 = iIntValue == iIntValue2;
                        if (i2 != 0) {
                            iIntValue = i2;
                        }
                        if (z2) {
                            iIntValue2 = iIntValue;
                        }
                        vectorDrawableParamsExt.setStartFillColor(iIntValue);
                        vectorDrawableParamsExt.setEndFillColor(iIntValue2);
                    } else if ("fillAlpha".equals(objectAnimator.getPropertyName())) {
                        vectorDrawableParamsExt.setStartFillAlpha(((Float) ((Keyframe) keyframes.get(0)).getValue()).floatValue());
                        vectorDrawableParamsExt.setEndFillAlpha(((Float) ((Keyframe) keyframes.get(1)).getValue()).floatValue());
                    } else if ("translateX".equals(objectAnimator.getPropertyName())) {
                        float fFloatValue = ((Float) ((Keyframe) keyframes.get(0)).getValue()).floatValue();
                        float fFloatValue2 = ((Float) ((Keyframe) keyframes.get(1)).getValue()).floatValue();
                        vectorDrawableParamsExt.setStartTranslateX(fFloatValue);
                        vectorDrawableParamsExt.setEndTranslateX(fFloatValue2);
                    } else if ("translateY".equals(objectAnimator.getPropertyName())) {
                        float fFloatValue3 = ((Float) ((Keyframe) keyframes.get(0)).getValue()).floatValue();
                        float fFloatValue4 = ((Float) ((Keyframe) keyframes.get(1)).getValue()).floatValue();
                        vectorDrawableParamsExt.setStartTranslateY(fFloatValue3);
                        vectorDrawableParamsExt.setEndTranslateY(fFloatValue4);
                    } else if ("scaleX".equals(objectAnimator.getPropertyName())) {
                        vectorDrawableParamsExt.setStartScaleX(((Float) ((Keyframe) keyframes.get(0)).getValue()).floatValue());
                        vectorDrawableParamsExt.setEndScaleX(((Float) ((Keyframe) keyframes.get(1)).getValue()).floatValue());
                    } else if ("scaleY".equals(objectAnimator.getPropertyName())) {
                        vectorDrawableParamsExt.setStartScaleY(((Float) ((Keyframe) keyframes.get(0)).getValue()).floatValue());
                        vectorDrawableParamsExt.setEndScaleY(((Float) ((Keyframe) keyframes.get(1)).getValue()).floatValue());
                    } else if ("pivotX".equals(objectAnimator.getPropertyName())) {
                        vectorDrawableParamsExt.setStartPivotX(((Float) ((Keyframe) keyframes.get(0)).getValue()).floatValue());
                        vectorDrawableParamsExt.setEndPivotX(((Float) ((Keyframe) keyframes.get(1)).getValue()).floatValue());
                    } else if ("pivotY".equals(objectAnimator.getPropertyName())) {
                        vectorDrawableParamsExt.setStartPivotY(((Float) ((Keyframe) keyframes.get(0)).getValue()).floatValue());
                        vectorDrawableParamsExt.setEndPivotY(((Float) ((Keyframe) keyframes.get(1)).getValue()).floatValue());
                    } else if ("rotation".equals(objectAnimator.getPropertyName())) {
                        vectorDrawableParamsExt.setStartRotation(((Float) ((Keyframe) keyframes.get(0)).getValue()).floatValue());
                        vectorDrawableParamsExt.setEndRotation(((Float) ((Keyframe) keyframes.get(1)).getValue()).floatValue());
                    }
                    vectorDrawableParamsExt.setPropertyName(objectAnimator.getPropertyName());
                    vectorDrawableParamsExt.setDelay(objectAnimator.getStartDelay() - j2);
                    vectorDrawableParamsExt.setDuration(objectAnimator.getDuration());
                    vectorDrawableSetParams.addDrawableParams(vectorDrawableParamsExt);
                }
            } else if (keyframesCompat instanceof Keyframes.FloatKeyframes) {
                if ("translateX".equals(values[i5].getPropertyName())) {
                    vectorDrawableParamsExt.setPropertyName(objectAnimator.getPropertyName());
                    vectorDrawableParamsExt.setTranslateXPath((Keyframes.FloatKeyframes) keyframesCompat);
                    vectorDrawableParamsExt.setDelay(objectAnimator.getStartDelay() - j2);
                    vectorDrawableParamsExt.setDuration(objectAnimator.getDuration());
                    vectorDrawableSetParams.addDrawableParams(vectorDrawableParamsExt);
                } else if ("translateY".equals(values[i5].getPropertyName())) {
                    vectorDrawableParamsExt.setPropertyName(objectAnimator.getPropertyName());
                    vectorDrawableParamsExt.setTranslateYPath((Keyframes.FloatKeyframes) keyframesCompat);
                    vectorDrawableParamsExt.setDelay(objectAnimator.getStartDelay() - j2);
                    vectorDrawableParamsExt.setDuration(objectAnimator.getDuration());
                    vectorDrawableSetParams.addDrawableParams(vectorDrawableParamsExt);
                }
            }
        }
    }

    public static Drawable initSvgColor(Drawable drawable, int i2) {
        return tintDrawable(drawable, i2);
    }

    private static Drawable tintDrawable(Drawable drawable, int i2) {
        Drawable drawableMutate = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(drawableMutate, i2);
        return drawableMutate;
    }

    public static void analyzeAnimator(Animator animator, VectorDrawableSetParams vectorDrawableSetParams, int i2, int i3, int i4) {
        if (animator instanceof AnimatorSet) {
            for (Animator animator2 : ((AnimatorSet) animator).getChildAnimations()) {
                if (animator2 instanceof ObjectAnimator) {
                    ObjectAnimator objectAnimator = (ObjectAnimator) animator2;
                    Object target = objectAnimator.getTarget();
                    if (!TextUtils.isEmpty(VectorDrawableParams.getTargetName(target))) {
                        VectorDrawableParamsExt vectorDrawableParamsExt = new VectorDrawableParamsExt();
                        vectorDrawableParamsExt.setTarget(target);
                        convertToVectorDrawableParams(objectAnimator, vectorDrawableSetParams, vectorDrawableParamsExt, i2, i3, i4);
                    }
                } else if (animator2 instanceof AnimatorSet) {
                    analyzeAnimator(animator2, vectorDrawableSetParams, i2, i3, i4);
                }
            }
        }
    }
}
