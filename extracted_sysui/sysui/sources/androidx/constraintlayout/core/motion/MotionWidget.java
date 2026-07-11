package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.WidgetFrame;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class MotionWidget implements TypedValues {
    public static final int FILL_PARENT = -1;
    public static final int GONE_UNSET = Integer.MIN_VALUE;
    private static final int INTERNAL_MATCH_CONSTRAINT = -3;
    private static final int INTERNAL_MATCH_PARENT = -1;
    private static final int INTERNAL_WRAP_CONTENT = -2;
    private static final int INTERNAL_WRAP_CONTENT_CONSTRAINED = -4;
    public static final int INVISIBLE = 0;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int MATCH_PARENT = -1;
    public static final int PARENT_ID = 0;
    public static final int ROTATE_LEFT_OF_PORTRATE = 4;
    public static final int ROTATE_NONE = 0;
    public static final int ROTATE_PORTRATE_OF_LEFT = 2;
    public static final int ROTATE_PORTRATE_OF_RIGHT = 1;
    public static final int ROTATE_RIGHT_OF_PORTRATE = 3;
    public static final int UNSET = -1;
    public static final int VISIBILITY_MODE_IGNORE = 1;
    public static final int VISIBILITY_MODE_NORMAL = 0;
    public static final int VISIBLE = 4;
    public static final int WRAP_CONTENT = -2;
    Motion mMotion;
    private float mProgress;
    PropertySet mPropertySet;
    float mTransitionPathRotate;
    WidgetFrame mWidgetFrame;

    public static class Motion {
        private static final int INTERPOLATOR_REFERENCE_ID = -2;
        private static final int INTERPOLATOR_UNDEFINED = -3;
        private static final int SPLINE_STRING = -1;
        public String mAnimateRelativeTo = null;
        public int mAnimateCircleAngleTo = 0;
        public String mTransitionEasing = null;
        public int mPathMotionArc = -1;
        public int mDrawPath = 0;
        public float mMotionStagger = Float.NaN;
        public int mPolarRelativeTo = -1;
        public float mPathRotate = Float.NaN;
        public float mQuantizeMotionPhase = Float.NaN;
        public int mQuantizeMotionSteps = -1;
        public String mQuantizeInterpolatorString = null;
        public int mQuantizeInterpolatorType = -3;
        public int mQuantizeInterpolatorID = -1;
    }

    public static class PropertySet {
        public int visibility = 4;
        public int mVisibilityMode = 0;
        public float alpha = 1.0f;
        public float mProgress = Float.NaN;
    }

    public MotionWidget() {
        this.mWidgetFrame = new WidgetFrame();
        this.mMotion = new Motion();
        this.mPropertySet = new PropertySet();
    }

    public MotionWidget findViewById(int i2) {
        return null;
    }

    public float getAlpha() {
        return this.mWidgetFrame.alpha;
    }

    public int getBottom() {
        return this.mWidgetFrame.bottom;
    }

    public CustomVariable getCustomAttribute(String str) {
        return this.mWidgetFrame.getCustomAttribute(str);
    }

    public Set<String> getCustomAttributeNames() {
        return this.mWidgetFrame.getCustomAttributeNames();
    }

    public int getHeight() {
        WidgetFrame widgetFrame = this.mWidgetFrame;
        return widgetFrame.bottom - widgetFrame.top;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String str) {
        int id = TypedValues.AttributesType.getId(str);
        return id != -1 ? id : TypedValues.MotionType.getId(str);
    }

    public int getLeft() {
        return this.mWidgetFrame.left;
    }

    public String getName() {
        return this.mWidgetFrame.getId();
    }

    public MotionWidget getParent() {
        return null;
    }

    public float getPivotX() {
        return this.mWidgetFrame.pivotX;
    }

    public float getPivotY() {
        return this.mWidgetFrame.pivotY;
    }

    public int getRight() {
        return this.mWidgetFrame.right;
    }

    public float getRotationX() {
        return this.mWidgetFrame.rotationX;
    }

    public float getRotationY() {
        return this.mWidgetFrame.rotationY;
    }

    public float getRotationZ() {
        return this.mWidgetFrame.rotationZ;
    }

    public float getScaleX() {
        return this.mWidgetFrame.scaleX;
    }

    public float getScaleY() {
        return this.mWidgetFrame.scaleY;
    }

    public int getTop() {
        return this.mWidgetFrame.top;
    }

    public float getTranslationX() {
        return this.mWidgetFrame.translationX;
    }

    public float getTranslationY() {
        return this.mWidgetFrame.translationY;
    }

    public float getTranslationZ() {
        return this.mWidgetFrame.translationZ;
    }

    public float getValueAttributes(int i2) {
        switch (i2) {
            case 303:
                return this.mWidgetFrame.alpha;
            case 304:
                return this.mWidgetFrame.translationX;
            case 305:
                return this.mWidgetFrame.translationY;
            case 306:
                return this.mWidgetFrame.translationZ;
            case 307:
            default:
                return Float.NaN;
            case 308:
                return this.mWidgetFrame.rotationX;
            case 309:
                return this.mWidgetFrame.rotationY;
            case 310:
                return this.mWidgetFrame.rotationZ;
            case 311:
                return this.mWidgetFrame.scaleX;
            case 312:
                return this.mWidgetFrame.scaleY;
            case 313:
                return this.mWidgetFrame.pivotX;
            case 314:
                return this.mWidgetFrame.pivotY;
            case 315:
                return this.mProgress;
            case TypedValues.AttributesType.TYPE_PATH_ROTATE /* 316 */:
                return this.mTransitionPathRotate;
        }
    }

    public int getVisibility() {
        return this.mPropertySet.visibility;
    }

    public WidgetFrame getWidgetFrame() {
        return this.mWidgetFrame;
    }

    public int getWidth() {
        WidgetFrame widgetFrame = this.mWidgetFrame;
        return widgetFrame.right - widgetFrame.left;
    }

    public int getX() {
        return this.mWidgetFrame.left;
    }

    public int getY() {
        return this.mWidgetFrame.top;
    }

    public void layout(int i2, int i3, int i4, int i5) {
        setBounds(i2, i3, i4, i5);
    }

    public void setBounds(int i2, int i3, int i4, int i5) {
        if (this.mWidgetFrame == null) {
            this.mWidgetFrame = new WidgetFrame((ConstraintWidget) null);
        }
        WidgetFrame widgetFrame = this.mWidgetFrame;
        widgetFrame.top = i3;
        widgetFrame.left = i2;
        widgetFrame.right = i4;
        widgetFrame.bottom = i5;
    }

    public void setCustomAttribute(String str, int i2, float f2) {
        this.mWidgetFrame.setCustomAttribute(str, i2, f2);
    }

    public void setInterpolatedValue(CustomAttribute customAttribute, float[] fArr) {
        this.mWidgetFrame.setCustomAttribute(customAttribute.mName, TypedValues.Custom.TYPE_FLOAT, fArr[0]);
    }

    public void setPivotX(float f2) {
        this.mWidgetFrame.pivotX = f2;
    }

    public void setPivotY(float f2) {
        this.mWidgetFrame.pivotY = f2;
    }

    public void setRotationX(float f2) {
        this.mWidgetFrame.rotationX = f2;
    }

    public void setRotationY(float f2) {
        this.mWidgetFrame.rotationY = f2;
    }

    public void setRotationZ(float f2) {
        this.mWidgetFrame.rotationZ = f2;
    }

    public void setScaleX(float f2) {
        this.mWidgetFrame.scaleX = f2;
    }

    public void setScaleY(float f2) {
        this.mWidgetFrame.scaleY = f2;
    }

    public void setTranslationX(float f2) {
        this.mWidgetFrame.translationX = f2;
    }

    public void setTranslationY(float f2) {
        this.mWidgetFrame.translationY = f2;
    }

    public void setTranslationZ(float f2) {
        this.mWidgetFrame.translationZ = f2;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, boolean z2) {
        return false;
    }

    public boolean setValueAttributes(int i2, float f2) {
        switch (i2) {
            case 303:
                this.mWidgetFrame.alpha = f2;
                return true;
            case 304:
                this.mWidgetFrame.translationX = f2;
                return true;
            case 305:
                this.mWidgetFrame.translationY = f2;
                return true;
            case 306:
                this.mWidgetFrame.translationZ = f2;
                return true;
            case 307:
            default:
                return false;
            case 308:
                this.mWidgetFrame.rotationX = f2;
                return true;
            case 309:
                this.mWidgetFrame.rotationY = f2;
                return true;
            case 310:
                this.mWidgetFrame.rotationZ = f2;
                return true;
            case 311:
                this.mWidgetFrame.scaleX = f2;
                return true;
            case 312:
                this.mWidgetFrame.scaleY = f2;
                return true;
            case 313:
                this.mWidgetFrame.pivotX = f2;
                return true;
            case 314:
                this.mWidgetFrame.pivotY = f2;
                return true;
            case 315:
                this.mProgress = f2;
                return true;
            case TypedValues.AttributesType.TYPE_PATH_ROTATE /* 316 */:
                this.mTransitionPathRotate = f2;
                return true;
        }
    }

    public boolean setValueMotion(int i2, int i3) {
        switch (i2) {
            case TypedValues.MotionType.TYPE_ANIMATE_CIRCLEANGLE_TO /* 606 */:
                this.mMotion.mAnimateCircleAngleTo = i3;
                return true;
            case TypedValues.MotionType.TYPE_PATHMOTION_ARC /* 607 */:
                this.mMotion.mPathMotionArc = i3;
                return true;
            case TypedValues.MotionType.TYPE_DRAW_PATH /* 608 */:
                this.mMotion.mDrawPath = i3;
                return true;
            case TypedValues.MotionType.TYPE_POLAR_RELATIVETO /* 609 */:
                this.mMotion.mPolarRelativeTo = i3;
                return true;
            case TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS /* 610 */:
                this.mMotion.mQuantizeMotionSteps = i3;
                return true;
            case TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_TYPE /* 611 */:
                this.mMotion.mQuantizeInterpolatorType = i3;
                return true;
            case TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_ID /* 612 */:
                this.mMotion.mQuantizeInterpolatorID = i3;
                return true;
            default:
                return false;
        }
    }

    public void setVisibility(int i2) {
        this.mPropertySet.visibility = i2;
    }

    public String toString() {
        return this.mWidgetFrame.left + ", " + this.mWidgetFrame.top + ", " + this.mWidgetFrame.right + ", " + this.mWidgetFrame.bottom;
    }

    public void updateMotion(TypedValues typedValues) {
        if (this.mWidgetFrame.getMotionProperties() != null) {
            this.mWidgetFrame.getMotionProperties().applyDelta(typedValues);
        }
    }

    public void setCustomAttribute(String str, int i2, int i3) {
        this.mWidgetFrame.setCustomAttribute(str, i2, i3);
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, int i3) {
        if (setValueAttributes(i2, i3)) {
            return true;
        }
        return setValueMotion(i2, i3);
    }

    public void setCustomAttribute(String str, int i2, boolean z2) {
        this.mWidgetFrame.setCustomAttribute(str, i2, z2);
    }

    public void setCustomAttribute(String str, int i2, String str2) {
        this.mWidgetFrame.setCustomAttribute(str, i2, str2);
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, float f2) {
        if (setValueAttributes(i2, f2)) {
            return true;
        }
        return setValueMotion(i2, f2);
    }

    public MotionWidget(WidgetFrame widgetFrame) {
        this.mWidgetFrame = new WidgetFrame();
        this.mMotion = new Motion();
        this.mPropertySet = new PropertySet();
        this.mWidgetFrame = widgetFrame;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, String str) {
        if (i2 == 605) {
            this.mMotion.mAnimateRelativeTo = str;
            return true;
        }
        return setValueMotion(i2, str);
    }

    public boolean setValueMotion(int i2, String str) {
        if (i2 == 603) {
            this.mMotion.mTransitionEasing = str;
            return true;
        }
        if (i2 != 604) {
            return false;
        }
        this.mMotion.mQuantizeInterpolatorString = str;
        return true;
    }

    public boolean setValueMotion(int i2, float f2) {
        switch (i2) {
            case 600:
                this.mMotion.mMotionStagger = f2;
                return true;
            case 601:
                this.mMotion.mPathRotate = f2;
                return true;
            case TypedValues.MotionType.TYPE_QUANTIZE_MOTION_PHASE /* 602 */:
                this.mMotion.mQuantizeMotionPhase = f2;
                return true;
            default:
                return false;
        }
    }
}
