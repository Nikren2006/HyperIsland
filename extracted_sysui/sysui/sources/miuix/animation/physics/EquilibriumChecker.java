package miuix.animation.physics;

import miuix.animation.property.FloatProperty;

/* JADX INFO: loaded from: classes4.dex */
public class EquilibriumChecker {
    public static final float MIN_VISIBLE_CHANGE_ALPHA = 0.00390625f;
    public static final float MIN_VISIBLE_CHANGE_PIXELS = 1.0f;
    public static final float MIN_VISIBLE_CHANGE_ROTATION_DEGREES = 0.1f;
    public static final float MIN_VISIBLE_CHANGE_SCALE = 0.004f;
    public static final float THRESHOLD_MULTIPLIER = 0.75f;
    public static final float VELOCITY_THRESHOLD_MULTIPLIER = 16.666666f;
    public static final float VELOCITY_THRESHOLD_MULTIPLIER_HIGH_FPS = 8.333333f;
    private double mTargetValue = Double.MAX_VALUE;
    private float mValueThreshold;
    private float mVelocityThreshold;

    private boolean isAt(double d2, double d3) {
        return Math.abs(this.mTargetValue) == 3.4028234663852886E38d || Math.abs(d2 - d3) < ((double) this.mValueThreshold);
    }

    public float getVelocityThreshold() {
        return this.mVelocityThreshold;
    }

    public void init(FloatProperty floatProperty, double d2) {
        float minVisibleChange = floatProperty.getMinVisibleChange() * 0.75f;
        this.mValueThreshold = minVisibleChange;
        this.mVelocityThreshold = minVisibleChange * 16.666666f;
        this.mTargetValue = d2;
    }

    public boolean isAtEquilibrium(int i2, double d2, double d3) {
        return (i2 != -2 || isAt(d2, this.mTargetValue)) && i2 != -3 && Math.abs(d3) < ((double) this.mVelocityThreshold);
    }
}
