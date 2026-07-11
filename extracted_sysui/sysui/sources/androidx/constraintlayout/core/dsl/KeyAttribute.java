package androidx.constraintlayout.core.dsl;

import androidx.constraintlayout.core.motion.utils.TypedValues;

/* JADX INFO: loaded from: classes.dex */
public class KeyAttribute extends Keys {
    private int mFrame;
    private String mTarget;
    private String mTransitionEasing;
    protected String TYPE = TypedValues.AttributesType.NAME;
    private Fit mCurveFit = null;
    private Visibility mVisibility = null;
    private float mAlpha = Float.NaN;
    private float mRotation = Float.NaN;
    private float mRotationX = Float.NaN;
    private float mRotationY = Float.NaN;
    private float mPivotX = Float.NaN;
    private float mPivotY = Float.NaN;
    private float mTransitionPathRotate = Float.NaN;
    private float mScaleX = Float.NaN;
    private float mScaleY = Float.NaN;
    private float mTranslationX = Float.NaN;
    private float mTranslationY = Float.NaN;
    private float mTranslationZ = Float.NaN;

    public enum Fit {
        SPLINE,
        LINEAR
    }

    public enum Visibility {
        VISIBLE,
        INVISIBLE,
        GONE
    }

    public KeyAttribute(int i2, String str) {
        this.mTarget = str;
        this.mFrame = i2;
    }

    public void attributesToString(StringBuilder sb) {
        append(sb, TypedValues.AttributesType.S_TARGET, this.mTarget);
        sb.append("frame:");
        sb.append(this.mFrame);
        sb.append(",\n");
        append(sb, "easing", this.mTransitionEasing);
        if (this.mCurveFit != null) {
            sb.append("fit:'");
            sb.append(this.mCurveFit);
            sb.append("',\n");
        }
        if (this.mVisibility != null) {
            sb.append("visibility:'");
            sb.append(this.mVisibility);
            sb.append("',\n");
        }
        append(sb, "alpha", this.mAlpha);
        append(sb, "rotationX", this.mRotationX);
        append(sb, "rotationY", this.mRotationY);
        append(sb, "rotationZ", this.mRotation);
        append(sb, "pivotX", this.mPivotX);
        append(sb, "pivotY", this.mPivotY);
        append(sb, "pathRotate", this.mTransitionPathRotate);
        append(sb, "scaleX", this.mScaleX);
        append(sb, "scaleY", this.mScaleY);
        append(sb, "translationX", this.mTranslationX);
        append(sb, "translationY", this.mTranslationY);
        append(sb, "translationZ", this.mTranslationZ);
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public Fit getCurveFit() {
        return this.mCurveFit;
    }

    public float getPivotX() {
        return this.mPivotX;
    }

    public float getPivotY() {
        return this.mPivotY;
    }

    public float getRotation() {
        return this.mRotation;
    }

    public float getRotationX() {
        return this.mRotationX;
    }

    public float getRotationY() {
        return this.mRotationY;
    }

    public float getScaleX() {
        return this.mScaleX;
    }

    public float getScaleY() {
        return this.mScaleY;
    }

    public String getTarget() {
        return this.mTarget;
    }

    public String getTransitionEasing() {
        return this.mTransitionEasing;
    }

    public float getTransitionPathRotate() {
        return this.mTransitionPathRotate;
    }

    public float getTranslationX() {
        return this.mTranslationX;
    }

    public float getTranslationY() {
        return this.mTranslationY;
    }

    public float getTranslationZ() {
        return this.mTranslationZ;
    }

    public Visibility getVisibility() {
        return this.mVisibility;
    }

    public void setAlpha(float f2) {
        this.mAlpha = f2;
    }

    public void setCurveFit(Fit fit) {
        this.mCurveFit = fit;
    }

    public void setPivotX(float f2) {
        this.mPivotX = f2;
    }

    public void setPivotY(float f2) {
        this.mPivotY = f2;
    }

    public void setRotation(float f2) {
        this.mRotation = f2;
    }

    public void setRotationX(float f2) {
        this.mRotationX = f2;
    }

    public void setRotationY(float f2) {
        this.mRotationY = f2;
    }

    public void setScaleX(float f2) {
        this.mScaleX = f2;
    }

    public void setScaleY(float f2) {
        this.mScaleY = f2;
    }

    public void setTarget(String str) {
        this.mTarget = str;
    }

    public void setTransitionEasing(String str) {
        this.mTransitionEasing = str;
    }

    public void setTransitionPathRotate(float f2) {
        this.mTransitionPathRotate = f2;
    }

    public void setTranslationX(float f2) {
        this.mTranslationX = f2;
    }

    public void setTranslationY(float f2) {
        this.mTranslationY = f2;
    }

    public void setTranslationZ(float f2) {
        this.mTranslationZ = f2;
    }

    public void setVisibility(Visibility visibility) {
        this.mVisibility = visibility;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.TYPE);
        sb.append(":{\n");
        attributesToString(sb);
        sb.append("},\n");
        return sb.toString();
    }
}
