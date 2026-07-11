package miuix.animation.utils;

import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import miuix.animation.FolmeEase;
import miuix.animation.easing.AccelerateDecelerateEasing;
import miuix.animation.easing.AccelerateEasing;
import miuix.animation.easing.AndroidDampingEasing;
import miuix.animation.easing.AndroidSpringEasing;
import miuix.animation.easing.AndroidSpringGravityEasing;
import miuix.animation.easing.BounceEasing;
import miuix.animation.easing.BounceInEasing;
import miuix.animation.easing.BounceInOutEasing;
import miuix.animation.easing.BounceOutEasing;
import miuix.animation.easing.CubicBezierEasing;
import miuix.animation.easing.CubicInEasing;
import miuix.animation.easing.CubicInOutEasing;
import miuix.animation.easing.CubicOutEasing;
import miuix.animation.easing.DecelerateEasing;
import miuix.animation.easing.LinearEasing;
import miuix.animation.easing.PerlinEasing;
import miuix.animation.easing.QuadInEasing;
import miuix.animation.easing.QuadInOutEasing;
import miuix.animation.easing.QuadOutEasing;
import miuix.animation.easing.QuartInEasing;
import miuix.animation.easing.QuartInOutEasing;
import miuix.animation.easing.QuartOutEasing;
import miuix.animation.easing.SimpleEasing;
import miuix.animation.easing.SineInEasing;
import miuix.animation.easing.SineInOutEasing;
import miuix.animation.easing.SineOutEasing;
import miuix.animation.easing.SpringEasing;
import miuix.animation.internal.DesignReview;
import miuix.animation.internal.FolmeCore;
import miuix.animation.motion.AndroidMotion;
import miuix.animation.motion.Motion;
import miuix.animation.motion.MotionConverter;
import miuix.animation.physics.FactorOperator;
import miuix.animation.physics.PhysicsOperator;
import miuix.view.animation.BounceEaseInInterpolator;
import miuix.view.animation.BounceEaseInOutInterpolator;
import miuix.view.animation.BounceEaseOutInterpolator;
import miuix.view.animation.CubicEaseInInterpolator;
import miuix.view.animation.CubicEaseInOutInterpolator;
import miuix.view.animation.CubicEaseOutInterpolator;
import miuix.view.animation.ExponentialEaseInInterpolator;
import miuix.view.animation.ExponentialEaseInOutInterpolator;
import miuix.view.animation.ExponentialEaseOutInterpolator;
import miuix.view.animation.QuadraticEaseInInterpolator;
import miuix.view.animation.QuadraticEaseInOutInterpolator;
import miuix.view.animation.QuadraticEaseOutInterpolator;
import miuix.view.animation.QuarticEaseInInterpolator;
import miuix.view.animation.QuarticEaseInOutInterpolator;
import miuix.view.animation.QuarticEaseOutInterpolator;
import miuix.view.animation.QuinticEaseInInterpolator;
import miuix.view.animation.QuinticEaseInOutInterpolator;
import miuix.view.animation.QuinticEaseOutInterpolator;
import miuix.view.animation.SineEaseInInterpolator;
import miuix.view.animation.SineEaseInOutInterpolator;
import miuix.view.animation.SineEaseOutInterpolator;

/* JADX INFO: loaded from: classes4.dex */
public class EaseManager {
    public static final long DEFAULT_DURATION = 300;
    static final ConcurrentHashMap<Integer, TimeInterpolator> sInterpolatorCache = new ConcurrentHashMap<>();
    static final ConcurrentHashMap<Integer, Motion> sDurationMotionCache = new ConcurrentHashMap<>();

    public static class DurationMotionEaseStyle extends EaseStyle {
        public DurationMotionEaseStyle(int i2, double... dArr) {
            super(i2, dArr);
        }

        @Override // miuix.animation.utils.EaseManager.EaseStyle
        public void setFactors(double... dArr) {
            if (dArr == null || dArr.length == 0) {
                this.factors = new double[]{300.0d};
            } else {
                this.factors = dArr;
            }
        }

        @Override // miuix.animation.utils.EaseManager.EaseStyle
        public String toString() {
            return "DurationMotion{style=" + this.style + ", duration=" + this.factors[0] + ", factors=" + Arrays.toString(this.factors) + '}';
        }
    }

    public interface EaseStyleDef {
        public static final int ACCELERATE = -3;
        public static final int ACCELERATE_DECELERATE = 21;
        public static final int ACCELERATE_INTERPOLATOR = 22;
        public static final int BEZIER = 100;
        public static final int BOUNCE = 23;
        public static final int BOUNCE_EASE_IN = 24;
        public static final int BOUNCE_EASE_INOUT = 26;
        public static final int BOUNCE_EASE_OUT = 25;
        public static final int CUBIC_IN = 5;
        public static final int CUBIC_INOUT = 7;
        public static final int CUBIC_OUT = 6;
        public static final int DAMPING = 103;
        public static final int DECELERATE = 20;
        public static final int DURATION = -1;
        public static final int EXPO_IN = 17;
        public static final int EXPO_INOUT = 19;
        public static final int EXPO_OUT = 18;
        public static final int FRICTION = -4;
        public static final int LINEAR = 1;
        public static final int PERLIN = 201;
        public static final int PERLIN2 = 200;
        public static final int QUAD_IN = 2;
        public static final int QUAD_INOUT = 4;
        public static final int QUAD_OUT = 3;
        public static final int QUART_IN = 8;
        public static final int QUART_INOUT = 10;
        public static final int QUART_OUT = 9;
        public static final int QUINT_IN = 11;
        public static final int QUINT_INOUT = 13;
        public static final int QUINT_OUT = 12;
        public static final int REBOUND = -6;
        public static final int SINE_IN = 14;
        public static final int SINE_INOUT = 16;
        public static final int SINE_OUT = 15;
        public static final int SIN_IN = 14;
        public static final int SIN_INOUT = 16;
        public static final int SIN_OUT = 15;
        public static final int SPRING = 0;
        public static final int SPRING_FUNCTION = 102;
        public static final int SPRING_GRAVITY = 101;
        public static final int SPRING_PHY = -2;
        public static final int STOP = -5;
    }

    public static class PhysicsMotionEaseStyle extends EaseStyle {
        public Motion motion;

        public PhysicsMotionEaseStyle(int i2, double... dArr) {
            super(i2, dArr);
            FolmeEase folmeEase = FolmeEase.get(i2, dArr);
            if (!(folmeEase instanceof SpringEasing)) {
                this.motion = folmeEase.newMotion();
                return;
            }
            Motion motionNewMotion = ((SpringEasing) FolmeEase.get(this.style, dArr)).newMotion(1.0d);
            this.motion = motionNewMotion;
            ((AndroidMotion) motionNewMotion).setThreshold(9.999999974752427E-7d);
        }

        @Override // miuix.animation.utils.EaseManager.EaseStyle
        public String toString() {
            return "PhyMotion{style=" + this.style + ", factors=" + Arrays.toString(this.factors) + '}';
        }
    }

    public static class SpringInterpolator implements TimeInterpolator {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f6004c;
        private float c2;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private float f6006k;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        private float f6008r;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        private float f6009w;
        private float damping = 0.95f;
        private float response = 0.6f;
        private float initial = -1.0f;

        /* JADX INFO: renamed from: c1, reason: collision with root package name */
        private float f6005c1 = -1.0f;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private float f6007m = 1.0f;
        private long duration = 1000;

        public SpringInterpolator() {
            updateParameters();
        }

        private void updateParameters() {
            double d2 = this.damping;
            double d3 = 6.283185307179586d / ((double) this.response);
            float f2 = this.f6007m;
            float f3 = (float) (d3 * d3 * ((double) f2));
            this.f6006k = f3;
            float f4 = (float) (d2 * 2.0d * d3 * ((double) f2));
            this.f6004c = f4;
            double d4 = f4 / f2;
            this.f6008r = (float) (-(d4 / 2.0d));
            float fSqrt = ((float) Math.sqrt(-((d4 * d4) - (((double) (f3 / f2)) * 4.0d)))) / 2.0f;
            this.f6009w = fSqrt;
            this.c2 = (0.0f - (this.f6008r * this.initial)) / fSqrt;
        }

        public float getDamping() {
            return this.damping;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = (f2 / 1000.0f) * this.duration;
            return (float) ((Math.pow(2.718281828459045d, this.f6008r * f3) * ((((double) this.f6005c1) * Math.cos(this.f6009w * f3)) + (((double) this.c2) * Math.sin(this.f6009w * f3)))) + 1.0d);
        }

        public float getResponse() {
            return this.response;
        }

        public SpringInterpolator setDamping(float f2) {
            this.damping = f2;
            updateParameters();
            return this;
        }

        public SpringInterpolator setDuration(long j2) {
            this.duration = j2;
            return this;
        }

        public SpringInterpolator setResponse(float f2) {
            this.response = f2;
            updateParameters();
            return this;
        }
    }

    public static class StepPhysicsEaseStyle extends EaseStyle {
        public StepPhysicsEaseStyle(int i2, double... dArr) {
            super(i2, dArr);
        }

        private static void setParameters(EaseStyle easeStyle, double[] dArr) {
            PhysicsOperator phyOperator = easeStyle == null ? null : FolmeCore.getPhyOperator(easeStyle.style);
            if (phyOperator != null) {
                phyOperator.getParameters(easeStyle.factors, dArr);
            } else {
                Arrays.fill(dArr, 0.0d);
            }
        }

        @Override // miuix.animation.utils.EaseManager.EaseStyle
        public int hashCode() {
            return (Objects.hash(Integer.valueOf(this.style)) * 31) + Arrays.hashCode(this.factors);
        }

        @Override // miuix.animation.utils.EaseManager.EaseStyle
        public void setFactors(FactorOperator... factorOperatorArr) {
            this.factors = new double[factorOperatorArr.length];
            for (int i2 = 0; i2 < factorOperatorArr.length; i2++) {
                this.factors[i2] = factorOperatorArr[i2].getFactor();
            }
            setParameters(this, this.parameters);
        }

        @Override // miuix.animation.utils.EaseManager.EaseStyle
        public String toString() {
            return "StepPhyEase{style=" + this.style + ", factors=" + Arrays.toString(this.factors) + ", parameters = " + Arrays.toString(this.parameters) + '}';
        }

        public StepPhysicsEaseStyle(int i2, FactorOperator... factorOperatorArr) {
            super(i2, factorOperatorArr);
        }

        @Override // miuix.animation.utils.EaseManager.EaseStyle
        public void setFactors(double... dArr) {
            this.factors = dArr;
            setParameters(this, this.parameters);
        }
    }

    private static Motion createDurationMotion(int i2) {
        FolmeEase easing = getEasing(i2, 1.0d);
        if (!(easing instanceof SimpleEasing)) {
            return easing.newMotion();
        }
        MotionConverter motionConverter = new MotionConverter(easing.newMotion(), 0.0d, 1.0d);
        motionConverter.setInitialV(((SimpleEasing) easing).startSpeed() * 1.0d);
        return motionConverter;
    }

    private static Motion createDurationMotionNoCache(DurationMotionEaseStyle durationMotionEaseStyle) {
        double[] dArrCopyOf = Arrays.copyOf(durationMotionEaseStyle.factors, durationMotionEaseStyle.factors.length);
        dArrCopyOf[0] = 1.0d;
        FolmeEase easing = getEasing(durationMotionEaseStyle.style, dArrCopyOf);
        if (!(easing instanceof SimpleEasing)) {
            return easing.newMotion();
        }
        MotionConverter motionConverter = new MotionConverter(easing.newMotion(), 0.0d, 1.0d);
        motionConverter.setInitialV(((SimpleEasing) easing).startSpeed() * 1.0d);
        return motionConverter;
    }

    @Deprecated
    public static TimeInterpolator createTimeInterpolator(int i2, double... dArr) {
        switch (i2) {
            case 0:
                return new SpringInterpolator().setDamping((float) dArr[0]).setResponse((float) dArr[1]);
            case 1:
                return new LinearInterpolator();
            case 2:
                return new QuadraticEaseInInterpolator();
            case 3:
                return new QuadraticEaseOutInterpolator();
            case 4:
                return new QuadraticEaseInOutInterpolator();
            case 5:
                return new CubicEaseInInterpolator();
            case 6:
                return new CubicEaseOutInterpolator();
            case 7:
                return new CubicEaseInOutInterpolator();
            case 8:
                return new QuarticEaseInInterpolator();
            case 9:
                return new QuarticEaseOutInterpolator();
            case 10:
                return new QuarticEaseInOutInterpolator();
            case 11:
                return new QuinticEaseInInterpolator();
            case 12:
                return new QuinticEaseOutInterpolator();
            case 13:
                return new QuinticEaseInOutInterpolator();
            case 14:
                return new SineEaseInInterpolator();
            case 15:
                return new SineEaseOutInterpolator();
            case 16:
                return new SineEaseInOutInterpolator();
            case 17:
                return new ExponentialEaseInInterpolator();
            case 18:
                return new ExponentialEaseOutInterpolator();
            case 19:
                return new ExponentialEaseInOutInterpolator();
            case 20:
                return new DecelerateInterpolator();
            case 21:
                return new AccelerateDecelerateInterpolator();
            case 22:
                return new AccelerateInterpolator();
            case 23:
                return new BounceInterpolator();
            case 24:
                return new BounceEaseInInterpolator();
            case 25:
                return new BounceEaseOutInterpolator();
            case 26:
                return new BounceEaseInOutInterpolator();
            default:
                return null;
        }
    }

    private static void ensureParamsLength(double[] dArr, int i2, String str) {
        if (dArr.length == i2) {
            return;
        }
        throw new IllegalArgumentException(str + " must provide " + i2 + " param(s)");
    }

    public static Motion getDurationMotion(DurationMotionEaseStyle durationMotionEaseStyle) {
        int i2 = durationMotionEaseStyle.style;
        if (i2 == 100 || i2 == 20 || i2 == 22) {
            return createDurationMotionNoCache(durationMotionEaseStyle);
        }
        ConcurrentHashMap<Integer, Motion> concurrentHashMap = sDurationMotionCache;
        Motion motionCreateDurationMotion = concurrentHashMap.get(Integer.valueOf(i2));
        if (motionCreateDurationMotion == null && (motionCreateDurationMotion = createDurationMotion(durationMotionEaseStyle.style)) != null) {
            concurrentHashMap.put(Integer.valueOf(durationMotionEaseStyle.style), motionCreateDurationMotion);
        }
        return motionCreateDurationMotion;
    }

    public static FolmeEase getEasing(int i2, double... dArr) {
        if (i2 != -1) {
            if (i2 == 200) {
                ensureParamsLength(dArr, 2, FolmeEase.PERLIN2);
                return new PerlinEasing(dArr[0], dArr[1], PerlinEasing.INTERPOLATOR2);
            }
            if (i2 == 201) {
                ensureParamsLength(dArr, 2, FolmeEase.PERLIN);
                return new PerlinEasing(dArr[0], dArr[1], PerlinEasing.INTERPOLATOR);
            }
            switch (i2) {
                case 1:
                    break;
                case 2:
                    ensureParamsLength(dArr, 1, FolmeEase.QUAD_IN);
                    return new QuadInEasing(dArr[0]);
                case 3:
                    ensureParamsLength(dArr, 1, FolmeEase.QUAD_OUT);
                    return new QuadOutEasing(dArr[0]);
                case 4:
                    ensureParamsLength(dArr, 1, FolmeEase.QUAD_INOUT);
                    return new QuadInOutEasing(dArr[0]);
                case 5:
                    ensureParamsLength(dArr, 1, FolmeEase.CUBIC_IN);
                    return new CubicInEasing(dArr[0]);
                case 6:
                    ensureParamsLength(dArr, 1, FolmeEase.CUBIC_OUT);
                    return new CubicOutEasing(dArr[0]);
                case 7:
                    ensureParamsLength(dArr, 1, FolmeEase.CUBIC_INOUT);
                    return new CubicInOutEasing(dArr[0]);
                case 8:
                    ensureParamsLength(dArr, 1, FolmeEase.QUAD_IN);
                    return new QuartInEasing(dArr[0]);
                case 9:
                    ensureParamsLength(dArr, 1, FolmeEase.QUART_OUT);
                    return new QuartOutEasing(dArr[0]);
                case 10:
                    ensureParamsLength(dArr, 1, FolmeEase.QUART_INOUT);
                    return new QuartInOutEasing(dArr[0]);
                case 11:
                    ensureParamsLength(dArr, 1, FolmeEase.QUINT_IN);
                    return new CubicBezierEasing(dArr[0], 0.64d, 0.0d, 0.78d, 0.0d);
                case 12:
                    ensureParamsLength(dArr, 1, FolmeEase.QUINT_OUT);
                    return new CubicBezierEasing(dArr[0], 0.22d, 1.0d, 0.36d, 1.0d);
                case 13:
                    ensureParamsLength(dArr, 1, FolmeEase.QUINT_INOUT);
                    return new CubicBezierEasing(dArr[0], 0.83d, 0.0d, 0.17d, 1.0d);
                case 14:
                    ensureParamsLength(dArr, 1, FolmeEase.SINE_IN);
                    return new SineInEasing(dArr[0]);
                case 15:
                    ensureParamsLength(dArr, 1, FolmeEase.SINE_OUT);
                    return new SineOutEasing(dArr[0]);
                case 16:
                    ensureParamsLength(dArr, 1, FolmeEase.SINE_INOUT);
                    return new SineInOutEasing(dArr[0]);
                case 17:
                    ensureParamsLength(dArr, 1, FolmeEase.EXPO_IN);
                    return new CubicBezierEasing(dArr[0], 0.7d, 0.0d, 0.84d, 0.0d);
                case 18:
                    ensureParamsLength(dArr, 1, FolmeEase.EXPO_OUT);
                    return new CubicBezierEasing(dArr[0], 0.16d, 1.0d, 0.3d, 1.0d);
                case 19:
                    ensureParamsLength(dArr, 1, FolmeEase.EXPO_INOUT);
                    return new CubicBezierEasing(dArr[0], 0.87d, 0.0d, 0.13d, 1.0d);
                case 20:
                    if (dArr.length != 0) {
                        return dArr.length > 1 ? new DecelerateEasing(dArr[1], dArr[0]) : new DecelerateEasing(dArr[0]);
                    }
                    throw new IllegalArgumentException("decelerate must provide more than 1 param(s)");
                case 21:
                    ensureParamsLength(dArr, 1, FolmeEase.ACCELERATE_DECELERATE);
                    return new AccelerateDecelerateEasing(dArr[0]);
                case 22:
                    if (dArr.length != 0) {
                        return dArr.length > 1 ? new AccelerateEasing(dArr[1], dArr[0]) : new AccelerateEasing(dArr[0]);
                    }
                    throw new IllegalArgumentException("accelerateInterpolator must provide more than 1 param(s)");
                case 23:
                    ensureParamsLength(dArr, 1, FolmeEase.BOUNCE);
                    return new BounceEasing(dArr[0]);
                case 24:
                    ensureParamsLength(dArr, 1, FolmeEase.BOUNCE_EASE_IN);
                    return new BounceInEasing(dArr[0]);
                case 25:
                    ensureParamsLength(dArr, 1, FolmeEase.BOUNCE_EASE_OUT);
                    return new BounceOutEasing(dArr[0]);
                case 26:
                    ensureParamsLength(dArr, 1, FolmeEase.BOUNCE_EASE_INOUT);
                    return new BounceInOutEasing(dArr[0]);
                default:
                    switch (i2) {
                        case 100:
                            ensureParamsLength(dArr, 5, FolmeEase.BEZIER);
                            return new CubicBezierEasing(dArr[0], dArr[1], dArr[2], dArr[3], dArr[4]);
                        case 101:
                            ensureParamsLength(dArr, 3, FolmeEase.SPRING_GRAVITY);
                            return new AndroidSpringGravityEasing(dArr[0], dArr[1], dArr[2]);
                        case 102:
                            ensureParamsLength(dArr, 2, FolmeEase.SPRING_FUNCTION);
                            return new AndroidSpringEasing(dArr[0], dArr[1]);
                        case 103:
                            ensureParamsLength(dArr, 2, FolmeEase.DAMPING);
                            return new AndroidDampingEasing(dArr[0], dArr[1]);
                        default:
                            throw new IllegalArgumentException("unknown style: " + i2);
                    }
            }
        }
        ensureParamsLength(dArr, 1, FolmeEase.LINEAR);
        return new LinearEasing(dArr[0]);
    }

    @Deprecated
    public static TimeInterpolator getInterpolator(int i2, float... fArr) {
        double[] dArr = new double[fArr.length];
        for (int i3 = 0; i3 < fArr.length; i3++) {
            dArr[i3] = fArr[i3];
        }
        return getInterpolator(i2, dArr);
    }

    private static InterpolateEaseStyle getInterpolatorStyle(int i2, double... dArr) {
        return new InterpolateEaseStyle(i2, dArr);
    }

    @NonNull
    @Deprecated
    public static EaseStyle getStyle(int i2) {
        return getStyle(i2, 300.0d);
    }

    public static boolean isDurationMotionStyle(int i2) {
        return i2 == -1 || (i2 > 0 && i2 <= 100);
    }

    public static boolean isPhysicsMotionStyle(int i2) {
        return i2 > 100;
    }

    public static boolean isPhysicsStyle(int i2) {
        return i2 <= -2;
    }

    @NonNull
    public static EaseStyle getStyle(int i2, FactorOperator... factorOperatorArr) {
        double[] dArr = new double[factorOperatorArr.length];
        for (int i3 = 0; i3 < factorOperatorArr.length; i3++) {
            dArr[i3] = factorOperatorArr[i3].getFactor();
        }
        return getStyle(i2, dArr);
    }

    public static class EaseStyle implements DesignReview {
        public volatile double[] factors;

        @Nullable
        public double[] parameters;
        public boolean stopAtTarget;
        public final int style;

        @Deprecated
        public EaseStyle(int i2, float... fArr) {
            this.style = i2;
            if (fArr == null || fArr.length <= 0) {
                this.factors = new double[]{300.0d};
            } else {
                this.factors = new double[fArr.length];
                for (int i3 = 0; i3 < fArr.length; i3++) {
                    this.factors[i3] = fArr[i3];
                }
            }
            double[] dArr = {0.0d, 0.0d, 0.0d};
            this.parameters = dArr;
            setParameters(this, dArr);
        }

        private static void setParameters(EaseStyle easeStyle, double[] dArr) {
            PhysicsOperator phyOperator = easeStyle == null ? null : FolmeCore.getPhyOperator(easeStyle.style);
            if (phyOperator != null) {
                phyOperator.getParameters(easeStyle.factors, dArr);
            } else {
                Arrays.fill(dArr, 0.0d);
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EaseStyle)) {
                return false;
            }
            EaseStyle easeStyle = (EaseStyle) obj;
            return this.style == easeStyle.style && Arrays.equals(this.factors, easeStyle.factors);
        }

        @Override // miuix.animation.internal.DesignReview
        public String getDesignInfo() {
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            sb.append("\"");
            sb.append(FolmeEase.getStyleName(this.style));
            sb.append("\", ");
            for (int i2 = 0; i2 < this.factors.length; i2++) {
                sb.append(this.factors[i2]);
                if (i2 == this.factors.length - 1) {
                    break;
                }
                sb.append(", ");
            }
            sb.append(']');
            return sb.toString();
        }

        public int hashCode() {
            return (Objects.hash(Integer.valueOf(this.style)) * 31) + Arrays.hashCode(this.factors);
        }

        public void setFactors(FactorOperator... factorOperatorArr) {
            this.factors = new double[factorOperatorArr.length];
            for (int i2 = 0; i2 < factorOperatorArr.length; i2++) {
                this.factors[i2] = factorOperatorArr[i2].getFactor();
            }
        }

        public String toString() {
            return "Ease{style=" + this.style + ", factors=" + Arrays.toString(this.factors) + '}';
        }

        public void setFactors(double... dArr) {
            this.factors = dArr;
        }

        public EaseStyle(int i2, double... dArr) {
            this.style = i2;
            if (dArr != null && dArr.length > 0) {
                this.factors = new double[dArr.length];
                for (int i3 = 0; i3 < dArr.length; i3++) {
                    this.factors[i3] = dArr[i3];
                }
            } else {
                this.factors = new double[]{300.0d};
            }
            double[] dArr2 = {0.0d, 0.0d, 0.0d};
            this.parameters = dArr2;
            setParameters(this, dArr2);
        }

        public EaseStyle(int i2, FactorOperator... factorOperatorArr) {
            this.style = i2;
            this.factors = new double[factorOperatorArr.length];
            for (int i3 = 0; i3 < factorOperatorArr.length; i3++) {
                this.factors[i3] = factorOperatorArr[i3].getFactor();
            }
            double[] dArr = {0.0d, 0.0d};
            this.parameters = dArr;
            setParameters(this, dArr);
        }
    }

    @Deprecated
    public static class InterpolateEaseStyle extends EaseStyle {
        public long duration;

        @Deprecated
        public InterpolateEaseStyle(int i2) {
            super(i2, 0.0d);
            this.duration = 300L;
            this.parameters = null;
        }

        public InterpolateEaseStyle setDuration(long j2) {
            this.duration = j2;
            return this;
        }

        @Override // miuix.animation.utils.EaseManager.EaseStyle
        public String toString() {
            return "Interpolate{style=" + this.style + ", duration=" + this.duration + ", factors=" + Arrays.toString(this.factors) + '}';
        }

        @Deprecated
        public InterpolateEaseStyle(int i2, float... fArr) {
            super(i2, fArr);
            this.duration = 300L;
            this.parameters = null;
        }

        @Deprecated
        public InterpolateEaseStyle(int i2, double... dArr) {
            super(i2, dArr);
            this.duration = 300L;
            this.parameters = null;
        }
    }

    public static TimeInterpolator getInterpolator(int i2, double... dArr) {
        return getInterpolator(getInterpolatorStyle(i2, dArr));
    }

    @NonNull
    public static EaseStyle getStyle(int i2, float... fArr) {
        double[] dArr = new double[fArr.length];
        for (int i3 = 0; i3 < fArr.length; i3++) {
            dArr[i3] = fArr[i3];
        }
        return getStyle(i2, dArr);
    }

    public static TimeInterpolator getInterpolator(InterpolateEaseStyle interpolateEaseStyle) {
        if (interpolateEaseStyle == null) {
            return null;
        }
        ConcurrentHashMap<Integer, TimeInterpolator> concurrentHashMap = sInterpolatorCache;
        TimeInterpolator timeInterpolatorCreateTimeInterpolator = concurrentHashMap.get(Integer.valueOf(interpolateEaseStyle.style));
        if (timeInterpolatorCreateTimeInterpolator == null && (timeInterpolatorCreateTimeInterpolator = createTimeInterpolator(interpolateEaseStyle.style, interpolateEaseStyle.factors)) != null) {
            concurrentHashMap.put(Integer.valueOf(interpolateEaseStyle.style), timeInterpolatorCreateTimeInterpolator);
        }
        return timeInterpolatorCreateTimeInterpolator;
    }

    @NonNull
    public static EaseStyle getStyle(int i2, double... dArr) {
        if (i2 == 0) {
            InterpolateEaseStyle interpolateEaseStyle = new InterpolateEaseStyle(i2, dArr.length > 1 ? Arrays.copyOfRange(dArr, 1, dArr.length) : new double[0]);
            if (dArr.length > 0) {
                interpolateEaseStyle.setDuration((int) dArr[0]);
            }
            return interpolateEaseStyle;
        }
        if (isDurationMotionStyle(i2)) {
            return new DurationMotionEaseStyle(i2, dArr);
        }
        if (isPhysicsMotionStyle(i2)) {
            return new PhysicsMotionEaseStyle(i2, dArr);
        }
        return new StepPhysicsEaseStyle(i2, dArr);
    }
}
