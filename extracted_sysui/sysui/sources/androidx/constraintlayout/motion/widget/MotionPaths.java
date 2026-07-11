package androidx.constraintlayout.motion.widget;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import com.miui.maml.folme.AnimatedProperty;
import java.util.Arrays;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
class MotionPaths implements Comparable<MotionPaths> {
    static final int CARTESIAN = 0;
    public static final boolean DEBUG = false;
    static final int OFF_HEIGHT = 4;
    static final int OFF_PATH_ROTATE = 5;
    static final int OFF_POSITION = 0;
    static final int OFF_WIDTH = 3;
    static final int OFF_X = 1;
    static final int OFF_Y = 2;
    public static final boolean OLD_WAY = false;
    static final int PERPENDICULAR = 1;
    static final int SCREEN = 2;
    public static final String TAG = "MotionPaths";
    static String[] sNames = {"position", AnimatedProperty.PROPERTY_NAME_X, AnimatedProperty.PROPERTY_NAME_Y, "width", "height", "pathRotate"};
    int mAnimateCircleAngleTo;
    int mAnimateRelativeTo;
    LinkedHashMap<String, ConstraintAttribute> mAttributes;
    float mHeight;
    Easing mKeyFrameEasing;
    int mMode;
    int mPathMotionArc;
    float mPosition;
    float mRelativeAngle;
    MotionController mRelativeToController;
    double[] mTempDelta;
    double[] mTempValue;
    float mTime;
    float mWidth;
    float mX;
    float mY;
    int mDrawPath = 0;
    float mPathRotate = Float.NaN;
    float mProgress = Float.NaN;

    public MotionPaths() {
        int i2 = Key.UNSET;
        this.mPathMotionArc = i2;
        this.mAnimateRelativeTo = i2;
        this.mRelativeAngle = Float.NaN;
        this.mRelativeToController = null;
        this.mAttributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
    }

    private boolean diff(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    private static float xRotate(float f2, float f3, float f4, float f5, float f6, float f7) {
        return (((f6 - f4) * f3) - ((f7 - f5) * f2)) + f4;
    }

    private static float yRotate(float f2, float f3, float f4, float f5, float f6, float f7) {
        return ((f6 - f4) * f2) + ((f7 - f5) * f3) + f5;
    }

    public void applyParameters(ConstraintSet.Constraint constraint) {
        this.mKeyFrameEasing = Easing.getInterpolator(constraint.motion.mTransitionEasing);
        ConstraintSet.Motion motion = constraint.motion;
        this.mPathMotionArc = motion.mPathMotionArc;
        this.mAnimateRelativeTo = motion.mAnimateRelativeTo;
        this.mPathRotate = motion.mPathRotate;
        this.mDrawPath = motion.mDrawPath;
        this.mAnimateCircleAngleTo = motion.mAnimateCircleAngleTo;
        this.mProgress = constraint.propertySet.mProgress;
        this.mRelativeAngle = constraint.layout.circleAngle;
        for (String str : constraint.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = constraint.mCustomConstraints.get(str);
            if (constraintAttribute != null && constraintAttribute.isContinuous()) {
                this.mAttributes.put(str, constraintAttribute);
            }
        }
    }

    public void configureRelativeTo(MotionController motionController) {
        motionController.getPos(this.mProgress);
    }

    public void different(MotionPaths motionPaths, boolean[] zArr, String[] strArr, boolean z2) {
        boolean zDiff = diff(this.mX, motionPaths.mX);
        boolean zDiff2 = diff(this.mY, motionPaths.mY);
        zArr[0] = zArr[0] | diff(this.mPosition, motionPaths.mPosition);
        boolean z3 = zDiff | zDiff2 | z2;
        zArr[1] = zArr[1] | z3;
        zArr[2] = z3 | zArr[2];
        zArr[3] = zArr[3] | diff(this.mWidth, motionPaths.mWidth);
        zArr[4] = diff(this.mHeight, motionPaths.mHeight) | zArr[4];
    }

    public void fillStandard(double[] dArr, int[] iArr) {
        float[] fArr = {this.mPosition, this.mX, this.mY, this.mWidth, this.mHeight, this.mPathRotate};
        int i2 = 0;
        for (int i3 : iArr) {
            if (i3 < 6) {
                dArr[i2] = fArr[r1];
                i2++;
            }
        }
    }

    public void getBounds(int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f2 = this.mWidth;
        float f3 = this.mHeight;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f4 = (float) dArr[i3];
            int i4 = iArr[i3];
            if (i4 == 3) {
                f2 = f4;
            } else if (i4 == 4) {
                f3 = f4;
            }
        }
        fArr[i2] = f2;
        fArr[i2 + 1] = f3;
    }

    public void getCenter(double d2, int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f2 = this.mX;
        float fCos = this.mY;
        float f3 = this.mWidth;
        float f4 = this.mHeight;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f5 = (float) dArr[i3];
            int i4 = iArr[i3];
            if (i4 == 1) {
                f2 = f5;
            } else if (i4 == 2) {
                fCos = f5;
            } else if (i4 == 3) {
                f3 = f5;
            } else if (i4 == 4) {
                f4 = f5;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] fArr2 = new float[2];
            motionController.getCenter(d2, fArr2, new float[2]);
            float f6 = fArr2[0];
            float f7 = fArr2[1];
            double d3 = f2;
            double d4 = fCos;
            float fSin = (float) ((((double) f6) + (Math.sin(d4) * d3)) - ((double) (f3 / 2.0f)));
            fCos = (float) ((((double) f7) - (d3 * Math.cos(d4))) - ((double) (f4 / 2.0f)));
            f2 = fSin;
        }
        fArr[i2] = f2 + (f3 / 2.0f) + 0.0f;
        fArr[i2 + 1] = fCos + (f4 / 2.0f) + 0.0f;
    }

    public void getCenterVelocity(double d2, int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f2 = this.mX;
        float fCos = this.mY;
        float f3 = this.mWidth;
        float f4 = this.mHeight;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f5 = (float) dArr[i3];
            int i4 = iArr[i3];
            if (i4 == 1) {
                f2 = f5;
            } else if (i4 == 2) {
                fCos = f5;
            } else if (i4 == 3) {
                f3 = f5;
            } else if (i4 == 4) {
                f4 = f5;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] fArr2 = new float[2];
            motionController.getCenter(d2, fArr2, new float[2]);
            float f6 = fArr2[0];
            float f7 = fArr2[1];
            double d3 = f2;
            double d4 = fCos;
            float fSin = (float) ((((double) f6) + (Math.sin(d4) * d3)) - ((double) (f3 / 2.0f)));
            fCos = (float) ((((double) f7) - (d3 * Math.cos(d4))) - ((double) (f4 / 2.0f)));
            f2 = fSin;
        }
        fArr[i2] = f2 + (f3 / 2.0f) + 0.0f;
        fArr[i2 + 1] = fCos + (f4 / 2.0f) + 0.0f;
    }

    public int getCustomData(String str, double[] dArr, int i2) {
        ConstraintAttribute constraintAttribute = this.mAttributes.get(str);
        int i3 = 0;
        if (constraintAttribute == null) {
            return 0;
        }
        if (constraintAttribute.numberOfInterpolatedValues() == 1) {
            dArr[i2] = constraintAttribute.getValueToInterpolate();
            return 1;
        }
        int iNumberOfInterpolatedValues = constraintAttribute.numberOfInterpolatedValues();
        constraintAttribute.getValuesToInterpolate(new float[iNumberOfInterpolatedValues]);
        while (i3 < iNumberOfInterpolatedValues) {
            dArr[i2] = r1[i3];
            i3++;
            i2++;
        }
        return iNumberOfInterpolatedValues;
    }

    public int getCustomDataCount(String str) {
        ConstraintAttribute constraintAttribute = this.mAttributes.get(str);
        if (constraintAttribute == null) {
            return 0;
        }
        return constraintAttribute.numberOfInterpolatedValues();
    }

    public void getRect(int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f2 = this.mX;
        float fCos = this.mY;
        float f3 = this.mWidth;
        float f4 = this.mHeight;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f5 = (float) dArr[i3];
            int i4 = iArr[i3];
            if (i4 == 1) {
                f2 = f5;
            } else if (i4 == 2) {
                fCos = f5;
            } else if (i4 == 3) {
                f3 = f5;
            } else if (i4 == 4) {
                f4 = f5;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float centerX = motionController.getCenterX();
            float centerY = this.mRelativeToController.getCenterY();
            double d2 = f2;
            double d3 = fCos;
            float fSin = (float) ((((double) centerX) + (Math.sin(d3) * d2)) - ((double) (f3 / 2.0f)));
            fCos = (float) ((((double) centerY) - (d2 * Math.cos(d3))) - ((double) (f4 / 2.0f)));
            f2 = fSin;
        }
        float f6 = f3 + f2;
        float f7 = f4 + fCos;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        fArr[i2] = f2 + 0.0f;
        fArr[i2 + 1] = fCos + 0.0f;
        fArr[i2 + 2] = f6 + 0.0f;
        fArr[i2 + 3] = fCos + 0.0f;
        fArr[i2 + 4] = f6 + 0.0f;
        fArr[i2 + 5] = f7 + 0.0f;
        fArr[i2 + 6] = f2 + 0.0f;
        fArr[i2 + 7] = f7 + 0.0f;
    }

    public boolean hasCustomData(String str) {
        return this.mAttributes.containsKey(str);
    }

    public void initAxis(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = keyPosition.mFramePosition / 100.0f;
        this.mTime = f2;
        this.mDrawPath = keyPosition.mDrawPath;
        float f3 = Float.isNaN(keyPosition.mPercentWidth) ? f2 : keyPosition.mPercentWidth;
        float f4 = Float.isNaN(keyPosition.mPercentHeight) ? f2 : keyPosition.mPercentHeight;
        float f5 = motionPaths2.mWidth;
        float f6 = motionPaths.mWidth;
        float f7 = f5 - f6;
        float f8 = motionPaths2.mHeight;
        float f9 = motionPaths.mHeight;
        float f10 = f8 - f9;
        this.mPosition = this.mTime;
        float f11 = (f6 / 2.0f) + motionPaths.mX;
        float f12 = motionPaths.mY + (f9 / 2.0f);
        float f13 = motionPaths2.mX + (f5 / 2.0f);
        float f14 = motionPaths2.mY + (f8 / 2.0f);
        if (f11 > f13) {
            f11 = f13;
            f13 = f11;
        }
        if (f12 <= f14) {
            f12 = f14;
            f14 = f12;
        }
        float f15 = f13 - f11;
        float f16 = f12 - f14;
        float f17 = (f7 * f3) / 2.0f;
        this.mX = (int) ((r13 + (f15 * f2)) - f17);
        float f18 = (f10 * f4) / 2.0f;
        this.mY = (int) ((r1 + (f16 * f2)) - f18);
        this.mWidth = (int) (f6 + r9);
        this.mHeight = (int) (f9 + r12);
        float f19 = Float.isNaN(keyPosition.mPercentX) ? f2 : keyPosition.mPercentX;
        float f20 = Float.isNaN(keyPosition.mAltPercentY) ? 0.0f : keyPosition.mAltPercentY;
        if (!Float.isNaN(keyPosition.mPercentY)) {
            f2 = keyPosition.mPercentY;
        }
        float f21 = Float.isNaN(keyPosition.mAltPercentX) ? 0.0f : keyPosition.mAltPercentX;
        this.mMode = 0;
        this.mX = (int) (((motionPaths.mX + (f19 * f15)) + (f21 * f16)) - f17);
        this.mY = (int) (((motionPaths.mY + (f15 * f20)) + (f16 * f2)) - f18);
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition.mTransitionEasing);
        this.mPathMotionArc = keyPosition.mPathMotionArc;
    }

    public void initCartesian(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = keyPosition.mFramePosition / 100.0f;
        this.mTime = f2;
        this.mDrawPath = keyPosition.mDrawPath;
        float f3 = Float.isNaN(keyPosition.mPercentWidth) ? f2 : keyPosition.mPercentWidth;
        float f4 = Float.isNaN(keyPosition.mPercentHeight) ? f2 : keyPosition.mPercentHeight;
        float f5 = motionPaths2.mWidth;
        float f6 = motionPaths.mWidth;
        float f7 = motionPaths2.mHeight;
        float f8 = motionPaths.mHeight;
        this.mPosition = this.mTime;
        float f9 = motionPaths.mX;
        float f10 = motionPaths.mY;
        float f11 = (motionPaths2.mX + (f5 / 2.0f)) - ((f6 / 2.0f) + f9);
        float f12 = (motionPaths2.mY + (f7 / 2.0f)) - (f10 + (f8 / 2.0f));
        float f13 = ((f5 - f6) * f3) / 2.0f;
        this.mX = (int) ((f9 + (f11 * f2)) - f13);
        float f14 = ((f7 - f8) * f4) / 2.0f;
        this.mY = (int) ((f10 + (f12 * f2)) - f14);
        this.mWidth = (int) (f6 + r9);
        this.mHeight = (int) (f8 + r12);
        float f15 = Float.isNaN(keyPosition.mPercentX) ? f2 : keyPosition.mPercentX;
        float f16 = Float.isNaN(keyPosition.mAltPercentY) ? 0.0f : keyPosition.mAltPercentY;
        if (!Float.isNaN(keyPosition.mPercentY)) {
            f2 = keyPosition.mPercentY;
        }
        float f17 = Float.isNaN(keyPosition.mAltPercentX) ? 0.0f : keyPosition.mAltPercentX;
        this.mMode = 0;
        this.mX = (int) (((motionPaths.mX + (f15 * f11)) + (f17 * f12)) - f13);
        this.mY = (int) (((motionPaths.mY + (f11 * f16)) + (f12 * f2)) - f14);
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition.mTransitionEasing);
        this.mPathMotionArc = keyPosition.mPathMotionArc;
    }

    public void initPath(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = keyPosition.mFramePosition / 100.0f;
        this.mTime = f2;
        this.mDrawPath = keyPosition.mDrawPath;
        float f3 = Float.isNaN(keyPosition.mPercentWidth) ? f2 : keyPosition.mPercentWidth;
        float f4 = Float.isNaN(keyPosition.mPercentHeight) ? f2 : keyPosition.mPercentHeight;
        float f5 = motionPaths2.mWidth - motionPaths.mWidth;
        float f6 = motionPaths2.mHeight - motionPaths.mHeight;
        this.mPosition = this.mTime;
        if (!Float.isNaN(keyPosition.mPercentX)) {
            f2 = keyPosition.mPercentX;
        }
        float f7 = motionPaths.mX;
        float f8 = motionPaths.mWidth;
        float f9 = motionPaths.mY;
        float f10 = motionPaths.mHeight;
        float f11 = (motionPaths2.mX + (motionPaths2.mWidth / 2.0f)) - ((f8 / 2.0f) + f7);
        float f12 = (motionPaths2.mY + (motionPaths2.mHeight / 2.0f)) - ((f10 / 2.0f) + f9);
        float f13 = f11 * f2;
        float f14 = (f5 * f3) / 2.0f;
        this.mX = (int) ((f7 + f13) - f14);
        float f15 = f2 * f12;
        float f16 = (f6 * f4) / 2.0f;
        this.mY = (int) ((f9 + f15) - f16);
        this.mWidth = (int) (f8 + r7);
        this.mHeight = (int) (f10 + r8);
        float f17 = Float.isNaN(keyPosition.mPercentY) ? 0.0f : keyPosition.mPercentY;
        this.mMode = 1;
        float f18 = (int) ((motionPaths.mX + f13) - f14);
        float f19 = (int) ((motionPaths.mY + f15) - f16);
        this.mX = f18 + ((-f12) * f17);
        this.mY = f19 + (f11 * f17);
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition.mTransitionEasing);
        this.mPathMotionArc = keyPosition.mPathMotionArc;
    }

    public void initPolar(int i2, int i3, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float fMin;
        float f2;
        float f3 = keyPosition.mFramePosition / 100.0f;
        this.mTime = f3;
        this.mDrawPath = keyPosition.mDrawPath;
        this.mMode = keyPosition.mPositionType;
        float f4 = Float.isNaN(keyPosition.mPercentWidth) ? f3 : keyPosition.mPercentWidth;
        float f5 = Float.isNaN(keyPosition.mPercentHeight) ? f3 : keyPosition.mPercentHeight;
        float f6 = motionPaths2.mWidth;
        float f7 = motionPaths.mWidth;
        float f8 = motionPaths2.mHeight;
        float f9 = motionPaths.mHeight;
        this.mPosition = this.mTime;
        this.mWidth = (int) (f7 + ((f6 - f7) * f4));
        this.mHeight = (int) (f9 + ((f8 - f9) * f5));
        if (keyPosition.mPositionType != 2) {
            float f10 = Float.isNaN(keyPosition.mPercentX) ? f3 : keyPosition.mPercentX;
            float f11 = motionPaths2.mX;
            float f12 = motionPaths.mX;
            this.mX = (f10 * (f11 - f12)) + f12;
            if (!Float.isNaN(keyPosition.mPercentY)) {
                f3 = keyPosition.mPercentY;
            }
            float f13 = motionPaths2.mY;
            float f14 = motionPaths.mY;
            this.mY = (f3 * (f13 - f14)) + f14;
        } else {
            if (Float.isNaN(keyPosition.mPercentX)) {
                float f15 = motionPaths2.mX;
                float f16 = motionPaths.mX;
                fMin = ((f15 - f16) * f3) + f16;
            } else {
                fMin = Math.min(f5, f4) * keyPosition.mPercentX;
            }
            this.mX = fMin;
            if (Float.isNaN(keyPosition.mPercentY)) {
                float f17 = motionPaths2.mY;
                float f18 = motionPaths.mY;
                f2 = (f3 * (f17 - f18)) + f18;
            } else {
                f2 = keyPosition.mPercentY;
            }
            this.mY = f2;
        }
        this.mAnimateRelativeTo = motionPaths.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition.mTransitionEasing);
        this.mPathMotionArc = keyPosition.mPathMotionArc;
    }

    public void initScreen(int i2, int i3, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f2 = keyPosition.mFramePosition / 100.0f;
        this.mTime = f2;
        this.mDrawPath = keyPosition.mDrawPath;
        float f3 = Float.isNaN(keyPosition.mPercentWidth) ? f2 : keyPosition.mPercentWidth;
        float f4 = Float.isNaN(keyPosition.mPercentHeight) ? f2 : keyPosition.mPercentHeight;
        float f5 = motionPaths2.mWidth;
        float f6 = motionPaths.mWidth;
        float f7 = motionPaths2.mHeight;
        float f8 = motionPaths.mHeight;
        this.mPosition = this.mTime;
        float f9 = motionPaths.mX;
        float f10 = motionPaths.mY;
        float f11 = motionPaths2.mX + (f5 / 2.0f);
        float f12 = motionPaths2.mY + (f7 / 2.0f);
        float f13 = (f5 - f6) * f3;
        this.mX = (int) ((f9 + ((f11 - ((f6 / 2.0f) + f9)) * f2)) - (f13 / 2.0f));
        float f14 = (f7 - f8) * f4;
        this.mY = (int) ((f10 + ((f12 - (f10 + (f8 / 2.0f))) * f2)) - (f14 / 2.0f));
        this.mWidth = (int) (f6 + f13);
        this.mHeight = (int) (f8 + f14);
        this.mMode = 2;
        if (!Float.isNaN(keyPosition.mPercentX)) {
            this.mX = (int) (keyPosition.mPercentX * (i2 - ((int) this.mWidth)));
        }
        if (!Float.isNaN(keyPosition.mPercentY)) {
            this.mY = (int) (keyPosition.mPercentY * (i3 - ((int) this.mHeight)));
        }
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition.mTransitionEasing);
        this.mPathMotionArc = keyPosition.mPathMotionArc;
    }

    public void setBounds(float f2, float f3, float f4, float f5) {
        this.mX = f2;
        this.mY = f3;
        this.mWidth = f4;
        this.mHeight = f5;
    }

    public void setDpDt(float f2, float f3, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f8 = (float) dArr[i2];
            double d2 = dArr2[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f4 = f8;
            } else if (i3 == 2) {
                f6 = f8;
            } else if (i3 == 3) {
                f5 = f8;
            } else if (i3 == 4) {
                f7 = f8;
            }
        }
        float f9 = f4 - ((0.0f * f5) / 2.0f);
        float f10 = f6 - ((0.0f * f7) / 2.0f);
        fArr[0] = (f9 * (1.0f - f2)) + (((f5 * 1.0f) + f9) * f2) + 0.0f;
        fArr[1] = (f10 * (1.0f - f3)) + (((f7 * 1.0f) + f10) * f3) + 0.0f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setView(float f2, View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3, boolean z2) {
        float f3;
        float f4;
        float f5 = this.mX;
        float f6 = this.mY;
        float f7 = this.mWidth;
        float f8 = this.mHeight;
        if (iArr.length != 0 && this.mTempValue.length <= iArr[iArr.length - 1]) {
            int i2 = iArr[iArr.length - 1] + 1;
            this.mTempValue = new double[i2];
            this.mTempDelta = new double[i2];
        }
        Arrays.fill(this.mTempValue, Double.NaN);
        for (int i3 = 0; i3 < iArr.length; i3++) {
            double[] dArr4 = this.mTempValue;
            int i4 = iArr[i3];
            dArr4[i4] = dArr[i3];
            this.mTempDelta[i4] = dArr2[i3];
        }
        float f9 = Float.NaN;
        int i5 = 0;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        float f13 = 0.0f;
        while (true) {
            double[] dArr5 = this.mTempValue;
            if (i5 >= dArr5.length) {
                break;
            }
            if (Double.isNaN(dArr5[i5]) && (dArr3 == null || dArr3[i5] == 0.0d)) {
                f4 = f9;
            } else {
                double d2 = dArr3 != null ? dArr3[i5] : 0.0d;
                if (!Double.isNaN(this.mTempValue[i5])) {
                    d2 = this.mTempValue[i5] + d2;
                }
                f4 = f9;
                float f14 = (float) d2;
                float f15 = (float) this.mTempDelta[i5];
                if (i5 == 1) {
                    f9 = f4;
                    f5 = f14;
                    f10 = f15;
                } else if (i5 == 2) {
                    f9 = f4;
                    f6 = f14;
                    f11 = f15;
                } else if (i5 == 3) {
                    f9 = f4;
                    f7 = f14;
                    f12 = f15;
                } else if (i5 == 4) {
                    f9 = f4;
                    f8 = f14;
                    f13 = f15;
                } else if (i5 == 5) {
                    f9 = f14;
                }
                i5++;
            }
            f9 = f4;
            i5++;
        }
        float f16 = f9;
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            motionController.getCenter(f2, fArr, fArr2);
            float f17 = fArr[0];
            float f18 = fArr[1];
            float f19 = fArr2[0];
            float f20 = fArr2[1];
            double d3 = f5;
            double d4 = f6;
            float fSin = (float) ((((double) f17) + (Math.sin(d4) * d3)) - ((double) (f7 / 2.0f)));
            f3 = f8;
            float fCos = (float) ((((double) f18) - (Math.cos(d4) * d3)) - ((double) (f8 / 2.0f)));
            double d5 = f10;
            double d6 = f11;
            float fSin2 = (float) (((double) f19) + (Math.sin(d4) * d5) + (Math.cos(d4) * d3 * d6));
            float fCos2 = (float) ((((double) f20) - (d5 * Math.cos(d4))) + (d3 * Math.sin(d4) * d6));
            if (dArr2.length >= 2) {
                dArr2[0] = fSin2;
                dArr2[1] = fCos2;
            }
            if (!Float.isNaN(f16)) {
                view.setRotation((float) (((double) f16) + Math.toDegrees(Math.atan2(fCos2, fSin2))));
            }
            f5 = fSin;
            f6 = fCos;
        } else {
            f3 = f8;
            if (!Float.isNaN(f16)) {
                view.setRotation(f16 + ((float) Math.toDegrees(Math.atan2(f11 + (f13 / 2.0f), f10 + (f12 / 2.0f)))) + 0.0f);
            }
        }
        if (view instanceof FloatLayout) {
            ((FloatLayout) view).layout(f5, f6, f7 + f5, f6 + f3);
            return;
        }
        float f21 = f5 + 0.5f;
        int i6 = (int) f21;
        float f22 = f6 + 0.5f;
        int i7 = (int) f22;
        int i8 = (int) (f21 + f7);
        int i9 = (int) (f22 + f3);
        int i10 = i8 - i6;
        int i11 = i9 - i7;
        if (i10 != view.getMeasuredWidth() || i11 != view.getMeasuredHeight() || z2) {
            view.measure(View.MeasureSpec.makeMeasureSpec(i10, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(i11, BasicMeasure.EXACTLY));
        }
        view.layout(i6, i7, i8, i9);
    }

    public void setupRelative(MotionController motionController, MotionPaths motionPaths) {
        double d2 = ((this.mX + (this.mWidth / 2.0f)) - motionPaths.mX) - (motionPaths.mWidth / 2.0f);
        double d3 = ((this.mY + (this.mHeight / 2.0f)) - motionPaths.mY) - (motionPaths.mHeight / 2.0f);
        this.mRelativeToController = motionController;
        this.mX = (float) Math.hypot(d3, d2);
        if (Float.isNaN(this.mRelativeAngle)) {
            this.mY = (float) (Math.atan2(d3, d2) + 1.5707963267948966d);
        } else {
            this.mY = (float) Math.toRadians(this.mRelativeAngle);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull MotionPaths motionPaths) {
        return Float.compare(this.mPosition, motionPaths.mPosition);
    }

    public MotionPaths(int i2, int i3, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        int i4 = Key.UNSET;
        this.mPathMotionArc = i4;
        this.mAnimateRelativeTo = i4;
        this.mRelativeAngle = Float.NaN;
        this.mRelativeToController = null;
        this.mAttributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
        if (motionPaths.mAnimateRelativeTo != Key.UNSET) {
            initPolar(i2, i3, keyPosition, motionPaths, motionPaths2);
            return;
        }
        int i5 = keyPosition.mPositionType;
        if (i5 == 1) {
            initPath(keyPosition, motionPaths, motionPaths2);
            return;
        }
        if (i5 == 2) {
            initScreen(i2, i3, keyPosition, motionPaths, motionPaths2);
        } else if (i5 != 3) {
            initCartesian(keyPosition, motionPaths, motionPaths2);
        } else {
            initAxis(keyPosition, motionPaths, motionPaths2);
        }
    }

    public void getCenter(double d2, int[] iArr, double[] dArr, float[] fArr, double[] dArr2, float[] fArr2) {
        float f2;
        float f3;
        float f4 = this.mX;
        float f5 = this.mY;
        float f6 = this.mWidth;
        float f7 = this.mHeight;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f12 = (float) dArr[i2];
            float f13 = (float) dArr2[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f4 = f12;
                f8 = f13;
            } else if (i3 == 2) {
                f5 = f12;
                f10 = f13;
            } else if (i3 == 3) {
                f6 = f12;
                f9 = f13;
            } else if (i3 == 4) {
                f7 = f12;
                f11 = f13;
            }
        }
        float f14 = (f9 / 2.0f) + f8;
        float fCos = (f11 / 2.0f) + f10;
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] fArr3 = new float[2];
            float[] fArr4 = new float[2];
            motionController.getCenter(d2, fArr3, fArr4);
            float f15 = fArr3[0];
            float f16 = fArr3[1];
            float f17 = fArr4[0];
            float f18 = fArr4[1];
            double d3 = f4;
            double d4 = f5;
            f2 = f6;
            float fSin = (float) ((((double) f15) + (Math.sin(d4) * d3)) - ((double) (f6 / 2.0f)));
            float fCos2 = (float) ((((double) f16) - (d3 * Math.cos(d4))) - ((double) (f7 / 2.0f)));
            double d5 = f8;
            double d6 = f10;
            float fSin2 = (float) (((double) f17) + (Math.sin(d4) * d5) + (Math.cos(d4) * d6));
            fCos = (float) ((((double) f18) - (d5 * Math.cos(d4))) + (Math.sin(d4) * d6));
            f4 = fSin;
            f5 = fCos2;
            f14 = fSin2;
            f3 = 2.0f;
        } else {
            f2 = f6;
            f3 = 2.0f;
        }
        fArr[0] = f4 + (f2 / f3) + 0.0f;
        fArr[1] = f5 + (f7 / f3) + 0.0f;
        fArr2[0] = f14;
        fArr2[1] = fCos;
    }
}
