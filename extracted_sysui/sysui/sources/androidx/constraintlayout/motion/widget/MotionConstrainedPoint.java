package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
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
    public float rotationY = 0.0f;
    int mVisibilityMode = 0;
    LinkedHashMap<String, ConstraintAttribute> mAttributes = new LinkedHashMap<>();
    int mMode = 0;
    double[] mTempValue = new double[18];
    double[] mTempDelta = new double[18];
    private float mAlpha = 1.0f;
    private boolean mApplyElevation = false;
    private float mElevation = 0.0f;
    private float mRotation = 0.0f;
    private float mRotationX = 0.0f;
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

    private boolean diff(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    public void addValues(HashMap<String, ViewSpline> map, int i2) {
        for (String str : map.keySet()) {
            ViewSpline viewSpline = map.get(str);
            if (viewSpline != null) {
                str.hashCode();
                switch (str) {
                    case "rotationX":
                        viewSpline.setPoint(i2, Float.isNaN(this.mRotationX) ? 0.0f : this.mRotationX);
                        break;
                    case "rotationY":
                        viewSpline.setPoint(i2, Float.isNaN(this.rotationY) ? 0.0f : this.rotationY);
                        break;
                    case "translationX":
                        viewSpline.setPoint(i2, Float.isNaN(this.mTranslationX) ? 0.0f : this.mTranslationX);
                        break;
                    case "translationY":
                        viewSpline.setPoint(i2, Float.isNaN(this.mTranslationY) ? 0.0f : this.mTranslationY);
                        break;
                    case "translationZ":
                        viewSpline.setPoint(i2, Float.isNaN(this.mTranslationZ) ? 0.0f : this.mTranslationZ);
                        break;
                    case "progress":
                        viewSpline.setPoint(i2, Float.isNaN(this.mProgress) ? 0.0f : this.mProgress);
                        break;
                    case "scaleX":
                        viewSpline.setPoint(i2, Float.isNaN(this.mScaleX) ? 1.0f : this.mScaleX);
                        break;
                    case "scaleY":
                        viewSpline.setPoint(i2, Float.isNaN(this.mScaleY) ? 1.0f : this.mScaleY);
                        break;
                    case "transformPivotX":
                        viewSpline.setPoint(i2, Float.isNaN(this.mPivotX) ? 0.0f : this.mPivotX);
                        break;
                    case "transformPivotY":
                        viewSpline.setPoint(i2, Float.isNaN(this.mPivotY) ? 0.0f : this.mPivotY);
                        break;
                    case "rotation":
                        viewSpline.setPoint(i2, Float.isNaN(this.mRotation) ? 0.0f : this.mRotation);
                        break;
                    case "elevation":
                        viewSpline.setPoint(i2, Float.isNaN(this.mElevation) ? 0.0f : this.mElevation);
                        break;
                    case "transitionPathRotate":
                        viewSpline.setPoint(i2, Float.isNaN(this.mPathRotate) ? 0.0f : this.mPathRotate);
                        break;
                    case "alpha":
                        viewSpline.setPoint(i2, Float.isNaN(this.mAlpha) ? 1.0f : this.mAlpha);
                        break;
                    default:
                        if (!str.startsWith("CUSTOM")) {
                            Log.e("MotionPaths", "UNKNOWN spline " + str);
                            break;
                        } else {
                            String str2 = str.split(aa.f3429b)[1];
                            if (this.mAttributes.containsKey(str2)) {
                                ConstraintAttribute constraintAttribute = this.mAttributes.get(str2);
                                if (viewSpline instanceof ViewSpline.CustomSet) {
                                    ((ViewSpline.CustomSet) viewSpline).setPoint(i2, constraintAttribute);
                                } else {
                                    Log.e("MotionPaths", str + " ViewSpline not a CustomSet frame = " + i2 + ", value" + constraintAttribute.getValueToInterpolate() + viewSpline);
                                }
                            }
                            break;
                        }
                        break;
                }
            }
        }
    }

    public void applyParameters(View view) {
        this.mVisibility = view.getVisibility();
        this.mAlpha = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.mApplyElevation = false;
        this.mElevation = view.getElevation();
        this.mRotation = view.getRotation();
        this.mRotationX = view.getRotationX();
        this.rotationY = view.getRotationY();
        this.mScaleX = view.getScaleX();
        this.mScaleY = view.getScaleY();
        this.mPivotX = view.getPivotX();
        this.mPivotY = view.getPivotY();
        this.mTranslationX = view.getTranslationX();
        this.mTranslationY = view.getTranslationY();
        this.mTranslationZ = view.getTranslationZ();
    }

    public void different(MotionConstrainedPoint motionConstrainedPoint, HashSet<String> hashSet) {
        if (diff(this.mAlpha, motionConstrainedPoint.mAlpha)) {
            hashSet.add("alpha");
        }
        if (diff(this.mElevation, motionConstrainedPoint.mElevation)) {
            hashSet.add("elevation");
        }
        int i2 = this.mVisibility;
        int i3 = motionConstrainedPoint.mVisibility;
        if (i2 != i3 && this.mVisibilityMode == 0 && (i2 == 0 || i3 == 0)) {
            hashSet.add("alpha");
        }
        if (diff(this.mRotation, motionConstrainedPoint.mRotation)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.mPathRotate) || !Float.isNaN(motionConstrainedPoint.mPathRotate)) {
            hashSet.add("transitionPathRotate");
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
            hashSet.add(Key.PIVOT_X);
        }
        if (diff(this.mPivotY, motionConstrainedPoint.mPivotY)) {
            hashSet.add(Key.PIVOT_Y);
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
        ConstraintAttribute constraintAttribute = this.mAttributes.get(str);
        if (constraintAttribute.numberOfInterpolatedValues() == 1) {
            dArr[i2] = constraintAttribute.getValueToInterpolate();
            return 1;
        }
        int iNumberOfInterpolatedValues = constraintAttribute.numberOfInterpolatedValues();
        constraintAttribute.getValuesToInterpolate(new float[iNumberOfInterpolatedValues]);
        int i3 = 0;
        while (i3 < iNumberOfInterpolatedValues) {
            dArr[i2] = r0[i3];
            i3++;
            i2++;
        }
        return iNumberOfInterpolatedValues;
    }

    public int getCustomDataCount(String str) {
        return this.mAttributes.get(str).numberOfInterpolatedValues();
    }

    public boolean hasCustomData(String str) {
        return this.mAttributes.containsKey(str);
    }

    public void setBounds(float f2, float f3, float f4, float f5) {
        this.mX = f2;
        this.mY = f3;
        this.mWidth = f4;
        this.mHeight = f5;
    }

    public void setState(View view) {
        setBounds(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        applyParameters(view);
    }

    @Override // java.lang.Comparable
    public int compareTo(MotionConstrainedPoint motionConstrainedPoint) {
        return Float.compare(this.mPosition, motionConstrainedPoint.mPosition);
    }

    public void setState(Rect rect, View view, int i2, float f2) {
        setBounds(rect.left, rect.top, rect.width(), rect.height());
        applyParameters(view);
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

    public void setState(Rect rect, ConstraintSet constraintSet, int i2, int i3) {
        setBounds(rect.left, rect.top, rect.width(), rect.height());
        applyParameters(constraintSet.getParameters(i3));
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        return;
                    }
                }
            }
            float f2 = this.mRotation + 90.0f;
            this.mRotation = f2;
            if (f2 > 180.0f) {
                this.mRotation = f2 - 360.0f;
                return;
            }
            return;
        }
        this.mRotation -= 90.0f;
    }

    public void applyParameters(ConstraintSet.Constraint constraint) {
        ConstraintSet.PropertySet propertySet = constraint.propertySet;
        int i2 = propertySet.mVisibilityMode;
        this.mVisibilityMode = i2;
        int i3 = propertySet.visibility;
        this.mVisibility = i3;
        this.mAlpha = (i3 == 0 || i2 != 0) ? propertySet.alpha : 0.0f;
        ConstraintSet.Transform transform = constraint.transform;
        this.mApplyElevation = transform.applyElevation;
        this.mElevation = transform.elevation;
        this.mRotation = transform.rotation;
        this.mRotationX = transform.rotationX;
        this.rotationY = transform.rotationY;
        this.mScaleX = transform.scaleX;
        this.mScaleY = transform.scaleY;
        this.mPivotX = transform.transformPivotX;
        this.mPivotY = transform.transformPivotY;
        this.mTranslationX = transform.translationX;
        this.mTranslationY = transform.translationY;
        this.mTranslationZ = transform.translationZ;
        this.mKeyFrameEasing = Easing.getInterpolator(constraint.motion.mTransitionEasing);
        ConstraintSet.Motion motion = constraint.motion;
        this.mPathRotate = motion.mPathRotate;
        this.mDrawPath = motion.mDrawPath;
        this.mAnimateRelativeTo = motion.mAnimateRelativeTo;
        this.mProgress = constraint.propertySet.mProgress;
        for (String str : constraint.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = constraint.mCustomConstraints.get(str);
            if (constraintAttribute.isContinuous()) {
                this.mAttributes.put(str, constraintAttribute);
            }
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
