package miuix.animation.internal;

import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.util.Log;
import miuix.animation.FolmeFactory;
import miuix.animation.IAnimTarget;
import miuix.animation.function.Differentiable;
import miuix.animation.motion.Motion;
import miuix.animation.physics.AccelerateOperator;
import miuix.animation.physics.EquilibriumChecker;
import miuix.animation.physics.FrictionOperator;
import miuix.animation.physics.PhysicsOperator;
import miuix.animation.physics.SpringOperator;
import miuix.animation.property.FloatProperty;
import miuix.animation.property.IIntValueProperty;
import miuix.animation.utils.CommonUtils;
import miuix.animation.utils.EaseManager;
import miuix.animation.utils.LogUtils;

/* JADX INFO: loaded from: classes4.dex */
public class FolmeCore {
    private static final long LONGEST_DURATION_NANOS = 10000000000L;
    public static final long NANOS_TO_MS = 1000000;
    public static final long NANOS_TO_S = 1000000000;
    static final SpringOperator sSpring = new SpringOperator();
    static final AccelerateOperator sAccelerate = new AccelerateOperator();
    static final FrictionOperator sFriction = new FrictionOperator();
    static IntEvaluator sIntEvaluator = new IntEvaluator();
    static FloatEvaluator sFloatEvaluator = new FloatEvaluator();
    static final ThreadLocal<EquilibriumChecker> mCheckerLocal = new ThreadLocal<>();

    public static void doAnimationFrame(IAnimTarget iAnimTarget, boolean z2, AnimData animData, long j2, double d2, int i2) {
        long j3 = j2 - animData.startTime;
        if (EaseManager.isPhysicsStyle(animData.ease.style)) {
            updatePhysicsAnim(iAnimTarget, animData, z2, j3, d2, i2);
            return;
        }
        if (EaseManager.isPhysicsMotionStyle(animData.ease.style)) {
            updatePhysicsMotionAnim(animData, j3);
            return;
        }
        EaseManager.EaseStyle easeStyle = animData.ease;
        if (easeStyle instanceof EaseManager.InterpolateEaseStyle) {
            updateInterpolatorAnim(animData, z2, j3 / NANOS_TO_MS);
        } else if (EaseManager.isDurationMotionStyle(easeStyle.style)) {
            updateDurationMotionAnim(animData, z2, j3);
        }
    }

    private static void doArgbPhysicsCalculation(AnimData animData, double d2) {
        boolean z2;
        double d3 = animData.velocity;
        PhysicsOperator phyOperator = getPhyOperator(animData.ease.style);
        if (phyOperator == null || (((z2 = phyOperator instanceof SpringOperator)) && AnimValueUtils.isInvalid(animData.targetValue))) {
            animData.value = 1.0d;
            animData.velocity = 0.0d;
        } else if (z2) {
            if (animData.frameCount == 1) {
                double d4 = animData.progress;
                double d5 = animData.ease.factors[0];
                double[] dArr = animData.ease.parameters;
                SpringOperator.updateValues(animData, d5, dArr[1], dArr[2], d2, true);
                animData.progress = d4;
            }
            double d6 = animData.ease.factors[0];
            double[] dArr2 = animData.ease.parameters;
            SpringOperator.updateValues(animData, d6, dArr2[1], dArr2[2], d2, true);
        } else {
            double[] dArr3 = animData.ease.parameters;
            double dUpdateVelocity = phyOperator.updateVelocity(d3, dArr3[0], dArr3[1], d2, 1.0d, animData.progress);
            double d7 = animData.progress + ((animData.velocity + dUpdateVelocity) * 0.5d * d2);
            animData.progress = d7;
            if (d7 > 1.0d) {
                animData.progress = 1.0d;
            } else if (d7 < 0.0d) {
                animData.progress = 0.0d;
            }
            animData.velocity = dUpdateVelocity;
        }
        Integer num = (Integer) CommonUtils.sArgbEvaluator.evaluate((float) animData.progress, Integer.valueOf((int) animData.startValue), Integer.valueOf((int) animData.targetValue));
        if (LogUtils.isLogFrameEnable() || LogUtils.isLogDetailEnable()) {
            LogUtils.debug("doArgbPhysics p='" + animData.property.getName() + "' color=" + Integer.toHexString(num.intValue()) + " fraction=" + animData.progress, new Object[0]);
        }
        animData.value = num.doubleValue();
    }

    private static void doPhysicsCalculation(AnimData animData, double d2) {
        boolean z2;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        double d3;
        String str6;
        double d4 = animData.velocity;
        PhysicsOperator phyOperator = getPhyOperator(animData.ease.style);
        if (phyOperator == null || (((z2 = phyOperator instanceof SpringOperator)) && AnimValueUtils.isInvalid(animData.targetValue))) {
            animData.value = animData.targetValue;
            animData.velocity = 0.0d;
            return;
        }
        if (!z2) {
            double[] dArr = animData.ease.parameters;
            double dUpdateVelocity = phyOperator.updateVelocity(d4, dArr[0], dArr[1], d2, animData.targetValue, animData.value);
            double d5 = animData.value + ((animData.velocity + dUpdateVelocity) * 0.5d * d2);
            animData.value = d5;
            if (Double.isInfinite(d5)) {
                Log.e(CommonUtils.TAG, "doPhysicsCalculation data.value isInfinite! startVelocity " + d4 + " velocity " + dUpdateVelocity + " data.ease.parameters " + animData.ease.parameters + " delta " + d2 + " data.targetValue " + animData.targetValue + " data.velocity " + animData.velocity);
            }
            animData.velocity = dUpdateVelocity;
            return;
        }
        if (animData.frameCount == 1) {
            double d6 = animData.value;
            double d7 = animData.ease.factors[0];
            double[] dArr2 = animData.ease.parameters;
            double d8 = dArr2[1];
            double d9 = dArr2[2];
            str = CommonUtils.TAG;
            str2 = " data.ease.parameters ";
            str3 = "doPhysicsCalculation data.value isInfinite! startVelocity ";
            str4 = " data.targetValue ";
            str5 = " delta ";
            d3 = d4;
            str6 = " data.velocity ";
            SpringOperator.updateValues(animData, d7, d8, d9, d2, false);
            animData.value = d6;
        } else {
            str = CommonUtils.TAG;
            str2 = " data.ease.parameters ";
            str3 = "doPhysicsCalculation data.value isInfinite! startVelocity ";
            str4 = " data.targetValue ";
            str5 = " delta ";
            d3 = d4;
            str6 = " data.velocity ";
        }
        double d10 = animData.ease.factors[0];
        double[] dArr3 = animData.ease.parameters;
        SpringOperator.updateValues(animData, d10, dArr3[1], dArr3[2], d2, false);
        if (Double.isInfinite(animData.value)) {
            Log.e(str, str3 + d3 + str2 + animData.ease.parameters + str5 + d2 + str4 + animData.targetValue + str6 + animData.velocity);
        }
    }

    private static double evaluateValue(AnimData animData, float f2) {
        TypeEvaluator evaluator = getEvaluator(animData.property);
        return evaluator instanceof IntEvaluator ? ((IntEvaluator) evaluator).evaluate(f2, Integer.valueOf((int) animData.startValue), Integer.valueOf((int) animData.targetValue)).doubleValue() : ((FloatEvaluator) evaluator).evaluate(f2, (Number) Float.valueOf((float) animData.startValue), (Number) Float.valueOf((float) animData.targetValue)).doubleValue();
    }

    private static TypeEvaluator getEvaluator(FloatProperty floatProperty) {
        return floatProperty instanceof IIntValueProperty ? sIntEvaluator : sFloatEvaluator;
    }

    public static PhysicsOperator getPhyOperator(int i2) {
        if (i2 == -4) {
            return sFriction;
        }
        if (i2 == -3) {
            return sAccelerate;
        }
        if (i2 != -2) {
            return null;
        }
        return sSpring;
    }

    @Deprecated
    public static float getVelocityThreshold() {
        EquilibriumChecker equilibriumChecker = (EquilibriumChecker) CommonUtils.getLocal(FolmeFactory.getEngine().getObjPool(), mCheckerLocal, EquilibriumChecker.class);
        if (equilibriumChecker != null) {
            return equilibriumChecker.getVelocityThreshold();
        }
        return 0.0f;
    }

    public static boolean isAnimRunning(EquilibriumChecker equilibriumChecker, FloatProperty floatProperty, int i2, double d2, double d3, long j2) {
        boolean zIsAtEquilibrium = equilibriumChecker.isAtEquilibrium(i2, d2, d3);
        boolean z2 = !zIsAtEquilibrium;
        if (!zIsAtEquilibrium && j2 > LONGEST_DURATION_NANOS) {
            z2 = false;
            if (LogUtils.isLogMainEnabled()) {
                LogUtils.debug("animation for " + floatProperty.getName() + " stopped for running time too long, totalTime_nanos = " + j2, new Object[0]);
            }
        }
        return z2;
    }

    private static boolean isUsingSpringPhy(AnimData animData) {
        return animData.ease.style == -2;
    }

    private static float regulateProgress(float f2) {
        if (f2 > 1.0f) {
            return 1.0f;
        }
        if (f2 < 0.0f) {
            return 0.0f;
        }
        return f2;
    }

    private static void setFinishValue(AnimData animData) {
        if (isUsingSpringPhy(animData)) {
            animData.value = animData.targetValue;
        }
    }

    private static void updateDurationMotionAnim(AnimData animData, boolean z2, long j2) {
        EaseManager.DurationMotionEaseStyle durationMotionEaseStyle = (EaseManager.DurationMotionEaseStyle) animData.ease;
        double d2 = j2 / (durationMotionEaseStyle.factors[0] * 1000000.0d);
        Motion durationMotion = EaseManager.getDurationMotion(durationMotionEaseStyle);
        if (d2 > 1.0d) {
            animData.progress = durationMotion.stopPosition();
            animData.value = animData.targetValue;
            animData.velocity = durationMotion.stopSpeed();
            animData.setOp((byte) 3);
        } else {
            Differentiable differentiableSolve = durationMotion.solve();
            double dApply = differentiableSolve.apply(d2);
            animData.progress = dApply;
            if (z2) {
                animData.value = ((Integer) CommonUtils.sArgbEvaluator.evaluate((float) dApply, Integer.valueOf((int) animData.startValue), Integer.valueOf((int) animData.targetValue))).doubleValue();
            } else {
                double d3 = animData.startValue;
                animData.value = d3 + ((animData.targetValue - d3) * dApply);
            }
            animData.velocity = differentiableSolve.derivative().apply(d2);
        }
        if (Double.isInfinite(animData.value)) {
            Log.e(CommonUtils.TAG, "updateDurationMotionAnim data.value isInfinite!  data.ease " + durationMotionEaseStyle + " totalTimeNanos " + j2 + " data.progress " + animData.progress);
        }
    }

    @Deprecated
    private static void updateInterpolatorAnim(AnimData animData, boolean z2, long j2) {
        EaseManager.InterpolateEaseStyle interpolateEaseStyle = (EaseManager.InterpolateEaseStyle) animData.ease;
        TimeInterpolator interpolator = EaseManager.getInterpolator(interpolateEaseStyle);
        if (j2 < interpolateEaseStyle.duration) {
            animData.progress = interpolator.getInterpolation(j2 / r2);
        } else {
            animData.setOp((byte) 3);
            animData.progress = 1.0d;
        }
        if (z2) {
            animData.value = ((Integer) CommonUtils.sArgbEvaluator.evaluate((float) animData.progress, Integer.valueOf((int) animData.startValue), Integer.valueOf((int) animData.targetValue))).doubleValue();
        } else {
            animData.value = evaluateValue(animData, (float) animData.progress);
        }
        if (Double.isInfinite(animData.value)) {
            Log.e(CommonUtils.TAG, "updateInterpolatorAnim data.value isInfinite!  data.ease " + interpolateEaseStyle + " totalTime_ms " + j2 + " interpolator " + interpolator + " data.progress " + animData.progress);
        }
    }

    private static void updatePhysicsAnim(IAnimTarget iAnimTarget, AnimData animData, boolean z2, long j2, double d2, int i2) {
        EquilibriumChecker equilibriumChecker = (EquilibriumChecker) CommonUtils.getLocal(FolmeFactory.getEngine().getObjPool(), mCheckerLocal, EquilibriumChecker.class);
        if (animData.property.getMinVisibleChange() == -1.0f) {
            FloatProperty floatProperty = animData.property;
            floatProperty.setMinVisibleChange(iAnimTarget.getMinVisibleChange(floatProperty.getName()));
        }
        equilibriumChecker.init(animData.property, animData.targetValue);
        for (int i3 = 0; i3 < i2; i3++) {
            if (z2) {
                doArgbPhysicsCalculation(animData, d2);
            } else {
                doPhysicsCalculation(animData, d2);
            }
            if (!isAnimRunning(equilibriumChecker, animData.property, animData.ease.style, animData.value, animData.velocity, j2)) {
                animData.setOp((byte) 3);
                if (LogUtils.isLogMoreEnable()) {
                    LogUtils.debug("----- updatePhysicsAnim data.setOp(AnimTask.OP_END)", new Object[0]);
                }
                setFinishValue(animData);
                return;
            }
        }
    }

    private static void updatePhysicsMotionAnim(AnimData animData, long j2) {
        Motion motion = ((EaseManager.PhysicsMotionEaseStyle) animData.ease).motion;
        double dFinishTime = motion.finishTime();
        double d2 = animData.duration;
        if (!Double.isFinite(dFinishTime) || d2 < dFinishTime) {
            Differentiable differentiableSolve = motion.solve();
            animData.value = ((animData.targetValue - animData.startValue) * differentiableSolve.apply(d2)) + animData.startValue;
            animData.velocity = differentiableSolve.derivative().apply(d2);
        } else {
            animData.value = ((animData.targetValue - animData.startValue) * motion.stopPosition()) + animData.startValue;
            animData.velocity = motion.stopSpeed();
            animData.setOp((byte) 3);
        }
    }
}
