package androidx.constraintlayout.core.state;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.Motion;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.key.MotionKeyAttributes;
import androidx.constraintlayout.core.motion.key.MotionKeyCycle;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.SpringStopEngine;
import androidx.constraintlayout.core.motion.utils.StopEngine;
import androidx.constraintlayout.core.motion.utils.StopLogicEngine;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import miuix.animation.FolmeEase;
import miuix.appcompat.internal.app.widget.SearchActionModeView;

/* JADX INFO: loaded from: classes.dex */
public class Transition implements TypedValues {
    static final int ANTICIPATE = 6;
    static final int BOUNCE = 4;
    private static final boolean DEBUG = false;
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    public static final int END = 1;
    public static final int INTERPOLATED = 2;
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    static final int LINEAR = 3;
    static final int OVERSHOOT = 5;
    private static final int SPLINE_STRING = -1;
    public static final int START = 0;
    int mParentEndHeight;
    int mParentEndWidth;
    int mParentInterpolateHeight;
    int mParentInterpolatedWidth;
    int mParentStartHeight;
    int mParentStartWidth;
    final CorePixelDp mToPixel;
    boolean mWrap;
    private HashMap<Integer, HashMap<String, KeyPosition>> mKeyPositions = new HashMap<>();
    private HashMap<String, WidgetState> mState = new HashMap<>();
    private TypedBundle mBundle = new TypedBundle();
    private int mDefaultInterpolator = 0;
    private String mDefaultInterpolatorString = null;
    private Easing mEasing = null;
    private int mAutoTransition = 0;
    private int mDuration = SearchActionModeView.ANIMATION_DURATION;
    private float mStagger = 0.0f;
    private OnSwipe mOnSwipe = null;

    public static class KeyPosition {
        int mFrame;
        String mTarget;
        int mType;
        float mX;
        float mY;

        public KeyPosition(String str, int i2, int i3, float f2, float f3) {
            this.mTarget = str;
            this.mFrame = i2;
            this.mType = i3;
            this.mX = f2;
            this.mY = f3;
        }
    }

    public static class OnSwipe {
        public static final int ANCHOR_SIDE_BOTTOM = 3;
        public static final int ANCHOR_SIDE_END = 6;
        public static final int ANCHOR_SIDE_LEFT = 1;
        public static final int ANCHOR_SIDE_MIDDLE = 4;
        public static final int ANCHOR_SIDE_RIGHT = 2;
        public static final int ANCHOR_SIDE_START = 5;
        public static final int ANCHOR_SIDE_TOP = 0;
        public static final int BOUNDARY_BOUNCE_BOTH = 3;
        public static final int BOUNDARY_BOUNCE_END = 2;
        public static final int BOUNDARY_BOUNCE_START = 1;
        public static final int BOUNDARY_OVERSHOOT = 0;
        public static final int DRAG_ANTICLOCKWISE = 7;
        public static final int DRAG_CLOCKWISE = 6;
        public static final int DRAG_DOWN = 1;
        public static final int DRAG_END = 5;
        public static final int DRAG_LEFT = 2;
        public static final int DRAG_RIGHT = 3;
        public static final int DRAG_START = 4;
        public static final int DRAG_UP = 0;
        public static final int MODE_CONTINUOUS_VELOCITY = 0;
        public static final int MODE_SPRING = 1;
        public static final int ON_UP_AUTOCOMPLETE = 0;
        public static final int ON_UP_AUTOCOMPLETE_TO_END = 2;
        public static final int ON_UP_AUTOCOMPLETE_TO_START = 1;
        public static final int ON_UP_DECELERATE = 4;
        public static final int ON_UP_DECELERATE_AND_COMPLETE = 5;
        public static final int ON_UP_NEVER_COMPLETE_TO_END = 7;
        public static final int ON_UP_NEVER_COMPLETE_TO_START = 6;
        public static final int ON_UP_STOP = 3;
        String mAnchorId;
        private int mAnchorSide;
        private StopEngine mEngine;
        String mLimitBoundsTo;
        private String mRotationCenterId;
        private long mStart;
        public static final String[] SIDES = {"top", "left", "right", "bottom", "middle", "start", "end"};
        private static final float[][] TOUCH_SIDES = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
        public static final String[] DIRECTIONS = {"up", "down", "left", "right", "start", "end", "clockwise", "anticlockwise"};
        public static final String[] MODE = {"velocity", FolmeEase.SPRING};
        public static final String[] TOUCH_UP = {"autocomplete", "toStart", "toEnd", "stop", FolmeEase.DECELERATE, "decelerateComplete", "neverCompleteStart", "neverCompleteEnd"};
        public static final String[] BOUNDARY = {"overshoot", "bounceStart", "bounceEnd", "bounceBoth"};
        private static final float[][] TOUCH_DIRECTION = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
        private boolean mDragVertical = true;
        private int mDragDirection = 0;
        private float mDragScale = 1.0f;
        private float mDragThreshold = 10.0f;
        private int mAutoCompleteMode = 0;
        private float mMaxVelocity = 4.0f;
        private float mMaxAcceleration = 1.2f;
        private int mOnTouchUp = 0;
        private float mSpringMass = 1.0f;
        private float mSpringStiffness = 400.0f;
        private float mSpringDamping = 10.0f;
        private float mSpringStopThreshold = 0.01f;
        private float mDestination = 0.0f;
        private int mSpringBoundary = 0;

        public void config(float f2, float f3, long j2, float f4) {
            SpringStopEngine springStopEngine;
            StopLogicEngine stopLogicEngine;
            StopLogicEngine.Decelerate decelerate;
            this.mStart = j2;
            float fAbs = Math.abs(f3);
            float f5 = this.mMaxVelocity;
            if (fAbs > f5) {
                f3 = Math.signum(f3) * f5;
            }
            float f6 = f3;
            float destinationPosition = getDestinationPosition(f2, f6, f4);
            this.mDestination = destinationPosition;
            if (destinationPosition == f2) {
                this.mEngine = null;
                return;
            }
            if (this.mOnTouchUp == 4 && this.mAutoCompleteMode == 0) {
                StopEngine stopEngine = this.mEngine;
                if (stopEngine instanceof StopLogicEngine.Decelerate) {
                    decelerate = (StopLogicEngine.Decelerate) stopEngine;
                } else {
                    decelerate = new StopLogicEngine.Decelerate();
                    this.mEngine = decelerate;
                }
                decelerate.config(f2, this.mDestination, f6);
                return;
            }
            if (this.mAutoCompleteMode == 0) {
                StopEngine stopEngine2 = this.mEngine;
                if (stopEngine2 instanceof StopLogicEngine) {
                    stopLogicEngine = (StopLogicEngine) stopEngine2;
                } else {
                    stopLogicEngine = new StopLogicEngine();
                    this.mEngine = stopLogicEngine;
                }
                stopLogicEngine.config(f2, this.mDestination, f6, f4, this.mMaxAcceleration, this.mMaxVelocity);
                return;
            }
            StopEngine stopEngine3 = this.mEngine;
            if (stopEngine3 instanceof SpringStopEngine) {
                springStopEngine = (SpringStopEngine) stopEngine3;
            } else {
                springStopEngine = new SpringStopEngine();
                this.mEngine = springStopEngine;
            }
            springStopEngine.springConfig(f2, this.mDestination, f6, this.mSpringMass, this.mSpringStiffness, this.mSpringDamping, this.mSpringStopThreshold, this.mSpringBoundary);
        }

        public float getDestinationPosition(float f2, float f3, float f4) {
            float fAbs = (((Math.abs(f3) * 0.5f) * f3) / this.mMaxAcceleration) + f2;
            switch (this.mOnTouchUp) {
                case 1:
                    if (f2 >= 1.0f) {
                    }
                    break;
                case 2:
                    if (f2 <= 0.0f) {
                    }
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    if (fAbs <= 0.2f || fAbs >= 0.8f) {
                        if (fAbs > 0.5f) {
                        }
                    }
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    if (fAbs > 0.5d) {
                    }
                    break;
            }
            return 1.0f;
        }

        public float[] getDirection() {
            return TOUCH_DIRECTION[this.mDragDirection];
        }

        public float getScale() {
            return this.mDragScale;
        }

        public float[] getSide() {
            return TOUCH_SIDES[this.mAnchorSide];
        }

        public float getTouchUpProgress(long j2) {
            return this.mEngine.isStopped() ? this.mDestination : this.mEngine.getInterpolation((j2 - this.mStart) * 1.0E-9f);
        }

        public boolean isNotDone(float f2) {
            StopEngine stopEngine;
            return (this.mOnTouchUp == 3 || (stopEngine = this.mEngine) == null || stopEngine.isStopped()) ? false : true;
        }

        public void printInfo() {
            if (this.mAutoCompleteMode == 0) {
                PrintStream printStream = System.out;
                printStream.println("velocity = " + this.mEngine.getVelocity());
                printStream.println("mMaxAcceleration = " + this.mMaxAcceleration);
                printStream.println("mMaxVelocity = " + this.mMaxVelocity);
                return;
            }
            PrintStream printStream2 = System.out;
            printStream2.println("mSpringMass          = " + this.mSpringMass);
            printStream2.println("mSpringStiffness     = " + this.mSpringStiffness);
            printStream2.println("mSpringDamping       = " + this.mSpringDamping);
            printStream2.println("mSpringStopThreshold = " + this.mSpringStopThreshold);
            printStream2.println("mSpringBoundary      = " + this.mSpringBoundary);
        }

        public void setAnchorId(String str) {
            this.mAnchorId = str;
        }

        public void setAnchorSide(int i2) {
            this.mAnchorSide = i2;
        }

        public void setAutoCompleteMode(int i2) {
            this.mAutoCompleteMode = i2;
        }

        public void setDragDirection(int i2) {
            this.mDragDirection = i2;
            this.mDragVertical = i2 < 2;
        }

        public void setDragScale(float f2) {
            if (Float.isNaN(f2)) {
                return;
            }
            this.mDragScale = f2;
        }

        public void setDragThreshold(float f2) {
            if (Float.isNaN(f2)) {
                return;
            }
            this.mDragThreshold = f2;
        }

        public void setLimitBoundsTo(String str) {
            this.mLimitBoundsTo = str;
        }

        public void setMaxAcceleration(float f2) {
            if (Float.isNaN(f2)) {
                return;
            }
            this.mMaxAcceleration = f2;
        }

        public void setMaxVelocity(float f2) {
            if (Float.isNaN(f2)) {
                return;
            }
            this.mMaxVelocity = f2;
        }

        public void setOnTouchUp(int i2) {
            this.mOnTouchUp = i2;
        }

        public void setRotationCenterId(String str) {
            this.mRotationCenterId = str;
        }

        public void setSpringBoundary(int i2) {
            this.mSpringBoundary = i2;
        }

        public void setSpringDamping(float f2) {
            if (Float.isNaN(f2)) {
                return;
            }
            this.mSpringDamping = f2;
        }

        public void setSpringMass(float f2) {
            if (Float.isNaN(f2)) {
                return;
            }
            this.mSpringMass = f2;
        }

        public void setSpringStiffness(float f2) {
            if (Float.isNaN(f2)) {
                return;
            }
            this.mSpringStiffness = f2;
        }

        public void setSpringStopThreshold(float f2) {
            if (Float.isNaN(f2)) {
                return;
            }
            this.mSpringStopThreshold = f2;
        }
    }

    public Transition(@NonNull CorePixelDp corePixelDp) {
        this.mToPixel = corePixelDp;
    }

    private void calculateParentDimensions(float f2) {
        this.mParentInterpolatedWidth = (int) (this.mParentStartWidth + 0.5f + ((this.mParentEndWidth - r0) * f2));
        this.mParentInterpolateHeight = (int) (this.mParentStartHeight + 0.5f + ((this.mParentEndHeight - r0) * f2));
    }

    public static Interpolator getInterpolator(int i2, final String str) {
        switch (i2) {
            case -1:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.a
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f2) {
                        return Transition.lambda$getInterpolator$0(str, f2);
                    }
                };
            case 0:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.b
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f2) {
                        return Transition.lambda$getInterpolator$1(f2);
                    }
                };
            case 1:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.c
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f2) {
                        return Transition.lambda$getInterpolator$2(f2);
                    }
                };
            case 2:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.d
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f2) {
                        return Transition.lambda$getInterpolator$3(f2);
                    }
                };
            case 3:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.e
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f2) {
                        return Transition.lambda$getInterpolator$4(f2);
                    }
                };
            case 4:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.h
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f2) {
                        return Transition.lambda$getInterpolator$7(f2);
                    }
                };
            case 5:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.g
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f2) {
                        return Transition.lambda$getInterpolator$6(f2);
                    }
                };
            case 6:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.f
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f2) {
                        return Transition.lambda$getInterpolator$5(f2);
                    }
                };
            default:
                return null;
        }
    }

    private WidgetState getWidgetState(String str) {
        return this.mState.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$0(String str, float f2) {
        return (float) Easing.getInterpolator(str).get(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$1(float f2) {
        return (float) Easing.getInterpolator("standard").get(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$2(float f2) {
        return (float) Easing.getInterpolator(FolmeEase.ACCELERATE).get(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$3(float f2) {
        return (float) Easing.getInterpolator(FolmeEase.DECELERATE).get(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$4(float f2) {
        return (float) Easing.getInterpolator(FolmeEase.LINEAR).get(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$5(float f2) {
        return (float) Easing.getInterpolator("anticipate").get(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$6(float f2) {
        return (float) Easing.getInterpolator("overshoot").get(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$7(float f2) {
        return (float) Easing.getInterpolator("spline(0.0, 0.2, 0.4, 0.6, 0.8 ,1.0, 0.8, 1.0, 0.9, 1.0)").get(f2);
    }

    public void addCustomColor(int i2, String str, String str2, int i3) {
        getWidgetState(str, null, i2).getFrame(i2).addCustomColor(str2, i3);
    }

    public void addCustomFloat(int i2, String str, String str2, float f2) {
        getWidgetState(str, null, i2).getFrame(i2).addCustomFloat(str2, f2);
    }

    public void addKeyAttribute(String str, TypedBundle typedBundle) {
        getWidgetState(str, null, 0).setKeyAttribute(typedBundle);
    }

    public void addKeyCycle(String str, TypedBundle typedBundle) {
        getWidgetState(str, null, 0).setKeyCycle(typedBundle);
    }

    public void addKeyPosition(String str, TypedBundle typedBundle) {
        getWidgetState(str, null, 0).setKeyPosition(typedBundle);
    }

    public void calcStagger() {
        float fMin;
        float fMax;
        float f2 = this.mStagger;
        if (f2 == 0.0f) {
            return;
        }
        boolean z2 = ((double) f2) < 0.0d;
        float fAbs = Math.abs(f2);
        Iterator<String> it = this.mState.keySet().iterator();
        do {
            fMin = Float.MAX_VALUE;
            fMax = -3.4028235E38f;
            if (!it.hasNext()) {
                Iterator<String> it2 = this.mState.keySet().iterator();
                while (it2.hasNext()) {
                    Motion motion = this.mState.get(it2.next()).mMotionControl;
                    float finalX = motion.getFinalX() + motion.getFinalY();
                    fMin = Math.min(fMin, finalX);
                    fMax = Math.max(fMax, finalX);
                }
                Iterator<String> it3 = this.mState.keySet().iterator();
                while (it3.hasNext()) {
                    Motion motion2 = this.mState.get(it3.next()).mMotionControl;
                    float finalX2 = motion2.getFinalX() + motion2.getFinalY();
                    float f3 = fMax - fMin;
                    float f4 = fAbs - (((finalX2 - fMin) * fAbs) / f3);
                    if (z2) {
                        f4 = fAbs - (((fMax - finalX2) / f3) * fAbs);
                    }
                    motion2.setStaggerScale(1.0f / (1.0f - fAbs));
                    motion2.setStaggerOffset(f4);
                }
                return;
            }
        } while (Float.isNaN(this.mState.get(it.next()).mMotionControl.getMotionStagger()));
        Iterator<String> it4 = this.mState.keySet().iterator();
        while (it4.hasNext()) {
            float motionStagger = this.mState.get(it4.next()).mMotionControl.getMotionStagger();
            if (!Float.isNaN(motionStagger)) {
                fMin = Math.min(fMin, motionStagger);
                fMax = Math.max(fMax, motionStagger);
            }
        }
        Iterator<String> it5 = this.mState.keySet().iterator();
        while (it5.hasNext()) {
            Motion motion3 = this.mState.get(it5.next()).mMotionControl;
            float motionStagger2 = motion3.getMotionStagger();
            if (!Float.isNaN(motionStagger2)) {
                float f5 = 1.0f / (1.0f - fAbs);
                float f6 = fMax - fMin;
                float f7 = fAbs - (((motionStagger2 - fMin) * fAbs) / f6);
                if (z2) {
                    f7 = fAbs - (((fMax - motionStagger2) / f6) * fAbs);
                }
                motion3.setStaggerScale(f5);
                motion3.setStaggerOffset(f7);
            }
        }
    }

    public void clear() {
        this.mState.clear();
    }

    public boolean contains(String str) {
        return this.mState.containsKey(str);
    }

    public OnSwipe createOnSwipe() {
        OnSwipe onSwipe = new OnSwipe();
        this.mOnSwipe = onSwipe;
        return onSwipe;
    }

    public float dragToProgress(float f2, int i2, int i3, float f3, float f4) {
        float fAbs;
        float scale;
        Iterator<WidgetState> it = this.mState.values().iterator();
        WidgetState next = it.hasNext() ? it.next() : null;
        OnSwipe onSwipe = this.mOnSwipe;
        if (onSwipe == null || next == null) {
            if (next != null) {
                return (-f4) / next.mParentHeight;
            }
            return 1.0f;
        }
        String str = onSwipe.mAnchorId;
        if (str == null) {
            float[] direction = onSwipe.getDirection();
            int i4 = next.mParentHeight;
            float f5 = i4;
            float f6 = i4;
            float f7 = direction[0];
            fAbs = f7 != 0.0f ? (f3 * Math.abs(f7)) / f5 : (f4 * Math.abs(direction[1])) / f6;
            scale = this.mOnSwipe.getScale();
        } else {
            WidgetState widgetState = this.mState.get(str);
            float[] direction2 = this.mOnSwipe.getDirection();
            float[] side = this.mOnSwipe.getSide();
            float[] fArr = new float[2];
            widgetState.interpolate(i2, i3, f2, this);
            widgetState.mMotionControl.getDpDt(f2, side[0], side[1], fArr);
            float f8 = direction2[0];
            fAbs = f8 != 0.0f ? (f3 * Math.abs(f8)) / fArr[0] : (f4 * Math.abs(direction2[1])) / fArr[1];
            scale = this.mOnSwipe.getScale();
        }
        return fAbs * scale;
    }

    public void fillKeyPositions(WidgetFrame widgetFrame, float[] fArr, float[] fArr2, float[] fArr3) {
        KeyPosition keyPosition;
        int i2 = 0;
        for (int i3 = 0; i3 <= 100; i3++) {
            HashMap<String, KeyPosition> map = this.mKeyPositions.get(Integer.valueOf(i3));
            if (map != null && (keyPosition = map.get(widgetFrame.widget.stringId)) != null) {
                fArr[i2] = keyPosition.mX;
                fArr2[i2] = keyPosition.mY;
                fArr3[i2] = keyPosition.mFrame;
                i2++;
            }
        }
    }

    public KeyPosition findNextPosition(String str, int i2) {
        KeyPosition keyPosition;
        while (i2 <= 100) {
            HashMap<String, KeyPosition> map = this.mKeyPositions.get(Integer.valueOf(i2));
            if (map != null && (keyPosition = map.get(str)) != null) {
                return keyPosition;
            }
            i2++;
        }
        return null;
    }

    public KeyPosition findPreviousPosition(String str, int i2) {
        KeyPosition keyPosition;
        while (i2 >= 0) {
            HashMap<String, KeyPosition> map = this.mKeyPositions.get(Integer.valueOf(i2));
            if (map != null && (keyPosition = map.get(str)) != null) {
                return keyPosition;
            }
            i2--;
        }
        return null;
    }

    public int getAutoTransition() {
        return this.mAutoTransition;
    }

    public WidgetFrame getEnd(String str) {
        WidgetState widgetState = this.mState.get(str);
        if (widgetState == null) {
            return null;
        }
        return widgetState.mEnd;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String str) {
        return 0;
    }

    public WidgetFrame getInterpolated(String str) {
        WidgetState widgetState = this.mState.get(str);
        if (widgetState == null) {
            return null;
        }
        return widgetState.mInterpolated;
    }

    public int getInterpolatedHeight() {
        return this.mParentInterpolateHeight;
    }

    public int getInterpolatedWidth() {
        return this.mParentInterpolatedWidth;
    }

    public int getKeyFrames(String str, float[] fArr, int[] iArr, int[] iArr2) {
        return this.mState.get(str).mMotionControl.buildKeyFrames(fArr, iArr, iArr2);
    }

    public Motion getMotion(String str) {
        return getWidgetState(str, null, 0).mMotionControl;
    }

    public int getNumberKeyPositions(WidgetFrame widgetFrame) {
        int i2 = 0;
        for (int i3 = 0; i3 <= 100; i3++) {
            HashMap<String, KeyPosition> map = this.mKeyPositions.get(Integer.valueOf(i3));
            if (map != null && map.get(widgetFrame.widget.stringId) != null) {
                i2++;
            }
        }
        return i2;
    }

    public float[] getPath(String str) {
        WidgetState widgetState = this.mState.get(str);
        float[] fArr = new float[124];
        widgetState.mMotionControl.buildPath(fArr, 62);
        return fArr;
    }

    public WidgetFrame getStart(String str) {
        WidgetState widgetState = this.mState.get(str);
        if (widgetState == null) {
            return null;
        }
        return widgetState.mStart;
    }

    public float getTouchUpProgress(long j2) {
        OnSwipe onSwipe = this.mOnSwipe;
        if (onSwipe != null) {
            return onSwipe.getTouchUpProgress(j2);
        }
        return 0.0f;
    }

    public boolean hasOnSwipe() {
        return this.mOnSwipe != null;
    }

    public boolean hasPositionKeyframes() {
        return this.mKeyPositions.size() > 0;
    }

    public void interpolate(int i2, int i3, float f2) {
        if (this.mWrap) {
            calculateParentDimensions(f2);
        }
        Easing easing = this.mEasing;
        if (easing != null) {
            f2 = (float) easing.get(f2);
        }
        Iterator<String> it = this.mState.keySet().iterator();
        while (it.hasNext()) {
            this.mState.get(it.next()).interpolate(i2, i3, f2, this);
        }
    }

    public boolean isEmpty() {
        return this.mState.isEmpty();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isFirstDownAccepted(float f2, float f3) {
        OnSwipe onSwipe = this.mOnSwipe;
        if (onSwipe == null) {
            return false;
        }
        String str = onSwipe.mLimitBoundsTo;
        if (str == null) {
            return true;
        }
        WidgetState widgetState = this.mState.get(str);
        if (widgetState == null) {
            System.err.println("mLimitBoundsTo target is null");
            return false;
        }
        WidgetFrame frame = widgetState.getFrame(2);
        return f2 >= ((float) frame.left) && f2 < ((float) frame.right) && f3 >= ((float) frame.top) && f3 < ((float) frame.bottom);
    }

    public boolean isTouchNotDone(float f2) {
        return this.mOnSwipe.isNotDone(f2);
    }

    public void resetProperties() {
        this.mOnSwipe = null;
        this.mBundle.clear();
    }

    public void setTouchUp(float f2, long j2, float f3, float f4) {
        OnSwipe onSwipe = this.mOnSwipe;
        if (onSwipe != null) {
            WidgetState widgetState = this.mState.get(onSwipe.mAnchorId);
            float[] fArr = new float[2];
            float[] direction = this.mOnSwipe.getDirection();
            float[] side = this.mOnSwipe.getSide();
            widgetState.mMotionControl.getDpDt(f2, side[0], side[1], fArr);
            if (Math.abs((direction[0] * fArr[0]) + (direction[1] * fArr[1])) < 0.01d) {
                fArr[0] = 0.01f;
                fArr[1] = 0.01f;
            }
            this.mOnSwipe.config(f2, (direction[0] != 0.0f ? f3 / fArr[0] : f4 / fArr[1]) * this.mOnSwipe.getScale(), j2, this.mDuration * 0.001f);
        }
    }

    public void setTransitionProperties(TypedBundle typedBundle) {
        typedBundle.applyDelta(this.mBundle);
        typedBundle.applyDelta(this);
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, int i3) {
        return false;
    }

    public void updateFrom(ConstraintWidgetContainer constraintWidgetContainer, int i2) {
        ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidgetContainer.mListDimensionBehaviors;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        boolean z2 = dimensionBehaviour == dimensionBehaviour2;
        this.mWrap = z2;
        this.mWrap = z2 | (dimensionBehaviourArr[1] == dimensionBehaviour2);
        if (i2 == 0) {
            int width = constraintWidgetContainer.getWidth();
            this.mParentStartWidth = width;
            this.mParentInterpolatedWidth = width;
            int height = constraintWidgetContainer.getHeight();
            this.mParentStartHeight = height;
            this.mParentInterpolateHeight = height;
        } else {
            this.mParentEndWidth = constraintWidgetContainer.getWidth();
            this.mParentEndHeight = constraintWidgetContainer.getHeight();
        }
        ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
        int size = children.size();
        WidgetState[] widgetStateArr = new WidgetState[size];
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget = children.get(i3);
            WidgetState widgetState = getWidgetState(constraintWidget.stringId, null, i2);
            widgetStateArr[i3] = widgetState;
            widgetState.update(constraintWidget, i2);
            String pathRelativeId = widgetState.getPathRelativeId();
            if (pathRelativeId != null) {
                widgetState.setPathRelative(getWidgetState(pathRelativeId, null, i2));
            }
        }
        calcStagger();
    }

    public void addKeyAttribute(String str, TypedBundle typedBundle, CustomVariable[] customVariableArr) {
        getWidgetState(str, null, 0).setKeyAttribute(typedBundle, customVariableArr);
    }

    public void addKeyPosition(String str, int i2, int i3, float f2, float f3) {
        TypedBundle typedBundle = new TypedBundle();
        typedBundle.add(TypedValues.PositionType.TYPE_POSITION_TYPE, 2);
        typedBundle.add(100, i2);
        typedBundle.add(TypedValues.PositionType.TYPE_PERCENT_X, f2);
        typedBundle.add(TypedValues.PositionType.TYPE_PERCENT_Y, f3);
        getWidgetState(str, null, 0).setKeyPosition(typedBundle);
        KeyPosition keyPosition = new KeyPosition(str, i2, i3, f2, f3);
        HashMap<String, KeyPosition> map = this.mKeyPositions.get(Integer.valueOf(i2));
        if (map == null) {
            map = new HashMap<>();
            this.mKeyPositions.put(Integer.valueOf(i2), map);
        }
        map.put(str, keyPosition);
    }

    public WidgetState getWidgetState(String str, ConstraintWidget constraintWidget, int i2) {
        WidgetState widgetState = this.mState.get(str);
        if (widgetState == null) {
            widgetState = new WidgetState();
            this.mBundle.applyDelta(widgetState.mMotionControl);
            widgetState.mMotionWidgetStart.updateMotion(widgetState.mMotionControl);
            this.mState.put(str, widgetState);
            if (constraintWidget != null) {
                widgetState.update(constraintWidget, i2);
            }
        }
        return widgetState;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, boolean z2) {
        return false;
    }

    public static class WidgetState {
        Motion mMotionControl;
        boolean mNeedSetup = true;
        KeyCache mKeyCache = new KeyCache();
        int mParentHeight = -1;
        int mParentWidth = -1;
        WidgetFrame mStart = new WidgetFrame();
        WidgetFrame mEnd = new WidgetFrame();
        WidgetFrame mInterpolated = new WidgetFrame();
        MotionWidget mMotionWidgetStart = new MotionWidget(this.mStart);
        MotionWidget mMotionWidgetEnd = new MotionWidget(this.mEnd);
        MotionWidget mMotionWidgetInterpolated = new MotionWidget(this.mInterpolated);

        public WidgetState() {
            Motion motion = new Motion(this.mMotionWidgetStart);
            this.mMotionControl = motion;
            motion.setStart(this.mMotionWidgetStart);
            this.mMotionControl.setEnd(this.mMotionWidgetEnd);
        }

        public WidgetFrame getFrame(int i2) {
            return i2 == 0 ? this.mStart : i2 == 1 ? this.mEnd : this.mInterpolated;
        }

        public String getPathRelativeId() {
            return this.mMotionControl.getAnimateRelativeTo();
        }

        public void interpolate(int i2, int i3, float f2, Transition transition) {
            this.mParentHeight = i3;
            this.mParentWidth = i2;
            if (this.mNeedSetup) {
                this.mMotionControl.setup(i2, i3, 1.0f, System.nanoTime());
                this.mNeedSetup = false;
            }
            WidgetFrame.interpolate(i2, i3, this.mInterpolated, this.mStart, this.mEnd, transition, f2);
            this.mInterpolated.interpolatedPos = f2;
            this.mMotionControl.interpolate(this.mMotionWidgetInterpolated, f2, System.nanoTime(), this.mKeyCache);
        }

        public void setKeyAttribute(TypedBundle typedBundle) {
            MotionKeyAttributes motionKeyAttributes = new MotionKeyAttributes();
            typedBundle.applyDelta(motionKeyAttributes);
            this.mMotionControl.addKey(motionKeyAttributes);
        }

        public void setKeyCycle(TypedBundle typedBundle) {
            MotionKeyCycle motionKeyCycle = new MotionKeyCycle();
            typedBundle.applyDelta(motionKeyCycle);
            this.mMotionControl.addKey(motionKeyCycle);
        }

        public void setKeyPosition(TypedBundle typedBundle) {
            MotionKeyPosition motionKeyPosition = new MotionKeyPosition();
            typedBundle.applyDelta(motionKeyPosition);
            this.mMotionControl.addKey(motionKeyPosition);
        }

        public void setPathRelative(WidgetState widgetState) {
            this.mMotionControl.setupRelative(widgetState.mMotionControl);
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public void update(ConstraintWidget constraintWidget, int i2) {
            if (i2 == 0) {
                this.mStart.update(constraintWidget);
                MotionWidget motionWidget = this.mMotionWidgetStart;
                motionWidget.updateMotion(motionWidget);
                this.mMotionControl.setStart(this.mMotionWidgetStart);
                this.mNeedSetup = true;
            } else if (i2 == 1) {
                this.mEnd.update(constraintWidget);
                this.mMotionControl.setEnd(this.mMotionWidgetEnd);
                this.mNeedSetup = true;
            }
            this.mParentWidth = -1;
        }

        public void setKeyAttribute(TypedBundle typedBundle, CustomVariable[] customVariableArr) {
            MotionKeyAttributes motionKeyAttributes = new MotionKeyAttributes();
            typedBundle.applyDelta(motionKeyAttributes);
            if (customVariableArr != null) {
                for (int i2 = 0; i2 < customVariableArr.length; i2++) {
                    motionKeyAttributes.mCustom.put(customVariableArr[i2].getName(), customVariableArr[i2]);
                }
            }
            this.mMotionControl.addKey(motionKeyAttributes);
        }
    }

    public WidgetFrame getEnd(ConstraintWidget constraintWidget) {
        return getWidgetState(constraintWidget.stringId, null, 1).mEnd;
    }

    public WidgetFrame getInterpolated(ConstraintWidget constraintWidget) {
        return getWidgetState(constraintWidget.stringId, null, 2).mInterpolated;
    }

    public WidgetFrame getStart(ConstraintWidget constraintWidget) {
        return getWidgetState(constraintWidget.stringId, null, 0).mStart;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, float f2) {
        if (i2 != 706) {
            return false;
        }
        this.mStagger = f2;
        return false;
    }

    @Override // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int i2, String str) {
        if (i2 != 705) {
            return false;
        }
        this.mDefaultInterpolatorString = str;
        this.mEasing = Easing.getInterpolator(str);
        return false;
    }

    public Interpolator getInterpolator() {
        return getInterpolator(this.mDefaultInterpolator, this.mDefaultInterpolatorString);
    }
}
