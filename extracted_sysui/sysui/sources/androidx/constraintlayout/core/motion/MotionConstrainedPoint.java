package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.Rect;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.Utils;
import com.miui.maml.folme.AnimatedProperty;
import com.xiaomi.onetrack.util.aa;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
class MotionConstrainedPoint implements Comparable<MotionConstrainedPoint> {
    static final int CARTESIAN = 2;
    public static final boolean DEBUG = false;
    static final int PERPENDICULAR = 1;
    public static final String TAG = "MotionPaths";
    static String[] sNames = {"position", AnimatedProperty.PROPERTY_NAME_X, AnimatedProperty.PROPERTY_NAME_Y, "width", "height", "pathRotate"};
    private float mHeight;
    private Easing mKeyFrameEasing;
    private float mPosition;
    int mVisibility;
    private float mWidth;
    private float mX;
    private float mY;
    private float mAlpha = 1.0f;
    int mVisibilityMode = 0;
    private boolean mApplyElevation = false;
    private float mElevation = 0.0f;
    private float mRotation = 0.0f;
    private float mRotationX = 0.0f;
    public float rotationY = 0.0f;
    private float mScaleX = 1.0f;
    private float mScaleY = 1.0f;
    private float mPivotX = Float.NaN;
    private float mPivotY = Float.NaN;
    private float mTranslationX = 0.0f;
    private float mTranslationY = 0.0f;
    private float mTranslationZ = 0.0f;
    private int mDrawPath = 0;
    private float mPathRotate = Float.NaN;
    private float mProgress = Float.NaN;
    private int mAnimateRelativeTo = -1;
    LinkedHashMap<String, CustomVariable> mCustomVariable = new LinkedHashMap<>();
    int mMode = 0;
    double[] mTempValue = new double[18];
    double[] mTempDelta = new double[18];

    private boolean diff(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    public void addValues(HashMap<String, SplineSet> map, int i2) {
        SplineSet splineSet;
        for (String str : map.keySet()) {
            splineSet = map.get(str);
            str.hashCode();
            switch (str) {
                case "rotationX":
                    splineSet.setPoint(i2, Float.isNaN(this.mRotationX) ? 0.0f : this.mRotationX);
                    break;
                case "rotationY":
                    splineSet.setPoint(i2, Float.isNaN(this.rotationY) ? 0.0f : this.rotationY);
                    break;
                case "rotationZ":
                    splineSet.setPoint(i2, Float.isNaN(this.mRotation) ? 0.0f : this.mRotation);
                    break;
                case "translationX":
                    splineSet.setPoint(i2, Float.isNaN(this.mTranslationX) ? 0.0f : this.mTranslationX);
                    break;
                case "translationY":
                    splineSet.setPoint(i2, Float.isNaN(this.mTranslationY) ? 0.0f : this.mTranslationY);
                    break;
                case "translationZ":
                    splineSet.setPoint(i2, Float.isNaN(this.mTranslationZ) ? 0.0f : this.mTranslationZ);
                    break;
                case "progress":
                    splineSet.setPoint(i2, Float.isNaN(this.mProgress) ? 0.0f : this.mProgress);
                    break;
                case "pivotX":
                    splineSet.setPoint(i2, Float.isNaN(this.mPivotX) ? 0.0f : this.mPivotX);
                    break;
                case "pivotY":
                    splineSet.setPoint(i2, Float.isNaN(this.mPivotY) ? 0.0f : this.mPivotY);
                    break;
                case "scaleX":
                    splineSet.setPoint(i2, Float.isNaN(this.mScaleX) ? 1.0f : this.mScaleX);
                    break;
                case "scaleY":
                    splineSet.setPoint(i2, Float.isNaN(this.mScaleY) ? 1.0f : this.mScaleY);
                    break;
                case "alpha":
                    splineSet.setPoint(i2, Float.isNaN(this.mAlpha) ? 1.0f : this.mAlpha);
                    break;
                case "pathRotate":
                    splineSet.setPoint(i2, Float.isNaN(this.mPathRotate) ? 0.0f : this.mPathRotate);
                    break;
                default:
                    if (!str.startsWith("CUSTOM")) {
                        Utils.loge("MotionPaths", "UNKNOWN spline " + str);
                        break;
                    } else {
                        String str2 = str.split(aa.f3429b)[1];
                        if (this.mCustomVariable.containsKey(str2)) {
                            CustomVariable customVariable = this.mCustomVariable.get(str2);
                            if (splineSet instanceof SplineSet.CustomSpline) {
                                ((SplineSet.CustomSpline) splineSet).setPoint(i2, customVariable);
                            } else {
                                Utils.loge("MotionPaths", str + " ViewSpline not a CustomSet frame = " + i2 + ", value" + customVariable.getValueToInterpolate() + splineSet);
                            }
                        }
                        break;
                    }
                    break;
            }
        }
    }

    public void applyParameters(MotionWidget motionWidget) {
        this.mVisibility = motionWidget.getVisibility();
        this.mAlpha = motionWidget.getVisibility() != 4 ? 0.0f : motionWidget.getAlpha();
        this.mApplyElevation = false;
        this.mRotation = motionWidget.getRotationZ();
        this.mRotationX = motionWidget.getRotationX();
        this.rotationY = motionWidget.getRotationY();
        this.mScaleX = motionWidget.getScaleX();
        this.mScaleY = motionWidget.getScaleY();
        this.mPivotX = motionWidget.getPivotX();
        this.mPivotY = motionWidget.getPivotY();
        this.mTranslationX = motionWidget.getTranslationX();
        this.mTranslationY = motionWidget.getTranslationY();
        this.mTranslationZ = motionWidget.getTranslationZ();
        for (String str : motionWidget.getCustomAttributeNames()) {
            CustomVariable customAttribute = motionWidget.getCustomAttribute(str);
            if (customAttribute != null && customAttribute.isContinuous()) {
                this.mCustomVariable.put(str, customAttribute);
            }
        }
    }

    public void different(MotionConstrainedPoint motionConstrainedPoint, HashSet<String> hashSet) {
        if (diff(this.mAlpha, motionConstrainedPoint.mAlpha)) {
            hashSet.add("alpha");
        }
        if (diff(this.mElevation, motionConstrainedPoint.mElevation)) {
            hashSet.add("translationZ");
        }
        int i2 = this.mVisibility;
        int i3 = motionConstrainedPoint.mVisibility;
        if (i2 != i3 && this.mVisibilityMode == 0 && (i2 == 4 || i3 == 4)) {
            hashSet.add("alpha");
        }
        if (diff(this.mRotation, motionConstrainedPoint.mRotation)) {
            hashSet.add("rotationZ");
        }
        if (!Float.isNaN(this.mPathRotate) || !Float.isNaN(motionConstrainedPoint.mPathRotate)) {
            hashSet.add("pathRotate");
        }
        if (!Float.isNaN(this.mProgress) || !Float.isNaN(motionConstrainedPoint.mProgress)) {
            hashSet.add("progress");
        }
        if (diff(this.mRotationX, motionConstrainedPoint.mRotationX)) {
            hashSet.add("rotationX");
        }
        if (diff(this.rotationY, motionConstrainedPoint.rotationY)) {
            hashSet.add("rotationY");
        }
        if (diff(this.mPivotX, motionConstrainedPoint.mPivotX)) {
            hashSet.add("pivotX");
        }
        if (diff(this.mPivotY, motionConstrainedPoint.mPivotY)) {
            hashSet.add("pivotY");
        }
        if (diff(this.mScaleX, motionConstrainedPoint.mScaleX)) {
            hashSet.add("scaleX");
        }
        if (diff(this.mScaleY, motionConstrainedPoint.mScaleY)) {
            hashSet.add("scaleY");
        }
        if (diff(this.mTranslationX, motionConstrainedPoint.mTranslationX)) {
            hashSet.add("translationX");
        }
        if (diff(this.mTranslationY, motionConstrainedPoint.mTranslationY)) {
            hashSet.add("translationY");
        }
        if (diff(this.mTranslationZ, motionConstrainedPoint.mTranslationZ)) {
            hashSet.add("translationZ");
        }
        if (diff(this.mElevation, motionConstrainedPoint.mElevation)) {
            hashSet.add("elevation");
        }
    }

    public void fillStandard(double[] dArr, int[] iArr) {
        int i2 = 0;
        float[] fArr = {this.mPosition, this.mX, this.mY, this.mWidth, this.mHeight, this.mAlpha, this.mElevation, this.mRotation, this.mRotationX, this.rotationY, this.mScaleX, this.mScaleY, this.mPivotX, this.mPivotY, this.mTranslationX, this.mTranslationY, this.mTranslationZ, this.mPathRotate};
        for (int i3 : iArr) {
            if (i3 < 18) {
                dArr[i2] = fArr[r5];
                i2++;
            }
        }
    }

    public int getCustomData(String str, double[] dArr, int i2) {
        CustomVariable customVariable = this.mCustomVariable.get(str);
        if (customVariable.numberOfInterpolatedValues() == 1) {
            dArr[i2] = customVariable.getValueToInterpolate();
            return 1;
        }
        int iNumberOfInterpolatedValues = customVariable.numberOfInterpolatedValues();
        customVariable.getValuesToInterpolate(new float[iNumberOfInterpolatedValues]);
        int i3 = 0;
        while (i3 < iNumberOfInterpolatedValues) {
            dArr[i2] = r0[i3];
            i3++;
            i2++;
        }
        return iNumberOfInterpolatedValues;
    }

    public int getCustomDataCount(String str) {
        return this.mCustomVariable.get(str).numberOfInterpolatedValues();
    }

    public boolean hasCustomData(String str) {
        return this.mCustomVariable.containsKey(str);
    }

    public void setBounds(float f2, float f3, float f4, float f5) {
        this.mX = f2;
        this.mY = f3;
        this.mWidth = f4;
        this.mHeight = f5;
    }

    public void setState(MotionWidget motionWidget) {
        setBounds(motionWidget.getX(), motionWidget.getY(), motionWidget.getWidth(), motionWidget.getHeight());
        applyParameters(motionWidget);
    }

    @Override // java.lang.Comparable
    public int compareTo(MotionConstrainedPoint motionConstrainedPoint) {
        return Float.compare(this.mPosition, motionConstrainedPoint.mPosition);
    }

    public void setState(Rect rect, MotionWidget motionWidget, int i2, float f2) {
        setBounds(rect.left, rect.top, rect.width(), rect.height());
        applyParameters(motionWidget);
        this.mPivotX = Float.NaN;
        this.mPivotY = Float.NaN;
        if (i2 == 1) {
            this.mRotation = f2 - 90.0f;
        } else {
            if (i2 != 2) {
                return;
            }
            this.mRotation = f2 + 90.0f;
        }
    }

    public void different(MotionConstrainedPoint motionConstrainedPoint, boolean[] zArr, String[] strArr) {
        zArr[0] = zArr[0] | diff(this.mPosition, motionConstrainedPoint.mPosition);
        zArr[1] = zArr[1] | diff(this.mX, motionConstrainedPoint.mX);
        zArr[2] = zArr[2] | diff(this.mY, motionConstrainedPoint.mY);
        zArr[3] = zArr[3] | diff(this.mWidth, motionConstrainedPoint.mWidth);
        zArr[4] = diff(this.mHeight, motionConstrainedPoint.mHeight) | zArr[4];
    }
}
