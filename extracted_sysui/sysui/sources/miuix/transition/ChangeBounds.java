package miuix.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.ViewCompat;
import androidx.transition.MiuixTransitionUtils;
import java.lang.ref.WeakReference;
import java.util.Map;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.property.IIntValueProperty;
import miuix.animation.property.ViewProperty;
import miuix.internal.util.ViewUtils;
import miuix.transition.MiuixTransition;

/* JADX INFO: loaded from: classes5.dex */
public class ChangeBounds extends MiuixTransition {
    private static final String END_TAG = "changebounds_end";
    protected static final String PROPNAME_BOUNDS = "android:transition:bounds";
    protected static final String PROPNAME_HEIGHT = "android:transition:height";
    protected static final String PROPNAME_PARENT = "android:transition:parent";
    protected static final String PROPNAME_WIDTH = "android:transition:width";
    protected static final String PROPNAME_WINDOW_X = "android:transition:windowX";
    protected static final String PROPNAME_WINDOW_Y = "android:transition:windowY";
    protected static final String PROPNAME_X = "android:transition:x";
    protected static final String PROPNAME_Y = "android:transition:y";
    private static final String START_TAG = "changebounds_start";
    private static final String[] sTransitionProperties;
    static Map<String, ViewProperty> sViewPropertyMap;
    protected final BottomProperty mBottomProperty;
    protected final LeftProperty mLeftProperty;
    protected boolean mReparent;
    protected final RightProperty mRightProperty;
    private int[] mTempLocation;
    protected final TopProperty mTopProperty;

    public static class BottomProperty extends ViewProperty implements IIntValueProperty<ViewBounds> {
        public BottomProperty() {
            super("bottom");
        }

        @Override // miuix.animation.property.FloatProperty
        public float getValue(View view) {
            return 0.0f;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(View view, float f2) {
        }

        @Override // miuix.animation.property.IIntValueProperty
        public int getIntValue(ViewBounds viewBounds) {
            return viewBounds.getBottom();
        }

        @Override // miuix.animation.property.IIntValueProperty
        public void setIntValue(ViewBounds viewBounds, int i2) {
            viewBounds.setBottom(i2);
        }
    }

    public static class LeftProperty extends ViewProperty implements IIntValueProperty<ViewBounds> {
        public LeftProperty() {
            super("left");
        }

        @Override // miuix.animation.property.FloatProperty
        public float getValue(View view) {
            return 0.0f;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(View view, float f2) {
        }

        @Override // miuix.animation.property.IIntValueProperty
        public int getIntValue(ViewBounds viewBounds) {
            return viewBounds.getLeft();
        }

        @Override // miuix.animation.property.IIntValueProperty
        public void setIntValue(ViewBounds viewBounds, int i2) {
            viewBounds.setLeft(i2);
        }
    }

    public static class RightProperty extends ViewProperty implements IIntValueProperty<ViewBounds> {
        public RightProperty() {
            super("right");
        }

        @Override // miuix.animation.property.FloatProperty
        public float getValue(View view) {
            return 0.0f;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(View view, float f2) {
        }

        @Override // miuix.animation.property.IIntValueProperty
        public int getIntValue(ViewBounds viewBounds) {
            return viewBounds.getRight();
        }

        @Override // miuix.animation.property.IIntValueProperty
        public void setIntValue(ViewBounds viewBounds, int i2) {
            viewBounds.setRight(i2);
        }
    }

    public static class TopProperty extends ViewProperty implements IIntValueProperty<ViewBounds> {
        public TopProperty() {
            super("top");
        }

        @Override // miuix.animation.property.FloatProperty
        public float getValue(View view) {
            return 0.0f;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(View view, float f2) {
        }

        @Override // miuix.animation.property.IIntValueProperty
        public int getIntValue(ViewBounds viewBounds) {
            return viewBounds.getTop();
        }

        @Override // miuix.animation.property.IIntValueProperty
        public void setIntValue(ViewBounds viewBounds, int i2) {
            viewBounds.setTop(i2);
        }
    }

    public static class ViewBounds {
        private WeakReference<View> mView;
        int left = -1;
        int top = -1;
        int right = -1;
        int bottom = -1;

        public ViewBounds(View view) {
            this.mView = new WeakReference<>(view);
        }

        private void setLeftTopRightBottom() {
            View view = this.mView.get();
            if (view != null) {
                if (!(view instanceof TextView)) {
                    ViewUtils.setLeftTopRightBottom(view, this.left, this.top, this.right, this.bottom);
                } else {
                    view.measure(View.MeasureSpec.makeMeasureSpec(this.right - this.left, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(this.bottom - this.top, BasicMeasure.EXACTLY));
                    view.layout(this.left, this.top, this.right, this.bottom);
                }
            }
        }

        public int getBottom() {
            return this.bottom;
        }

        public int getLeft() {
            return this.left;
        }

        public int getRight() {
            return this.right;
        }

        public int getTop() {
            return this.top;
        }

        public void setBottom(int i2) {
            if (this.bottom != i2) {
                this.bottom = i2;
                setLeftTopRightBottom();
            }
        }

        public void setLeft(int i2) {
            if (this.left != i2) {
                this.left = i2;
                setLeftTopRightBottom();
            }
        }

        public void setRight(int i2) {
            if (this.right != i2) {
                this.right = i2;
                setLeftTopRightBottom();
            }
        }

        public void setTop(int i2) {
            if (this.top != i2) {
                this.top = i2;
                setLeftTopRightBottom();
            }
        }
    }

    static {
        ArrayMap arrayMap = new ArrayMap();
        sViewPropertyMap = arrayMap;
        sTransitionProperties = new String[]{PROPNAME_BOUNDS, PROPNAME_PARENT, PROPNAME_WINDOW_X, PROPNAME_WINDOW_Y};
        arrayMap.put(PROPNAME_X, ViewProperty.f6001X);
        sViewPropertyMap.put(PROPNAME_Y, ViewProperty.f6002Y);
        sViewPropertyMap.put(PROPNAME_WIDTH, ViewProperty.WIDTH);
        sViewPropertyMap.put(PROPNAME_HEIGHT, ViewProperty.HEIGHT);
    }

    public ChangeBounds() {
        this.mReparent = false;
        this.mTempLocation = new int[2];
        this.mLeftProperty = new LeftProperty();
        this.mTopProperty = new TopProperty();
        this.mRightProperty = new RightProperty();
        this.mBottomProperty = new BottomProperty();
    }

    @Override // miuix.transition.MiuixTransition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // miuix.transition.MiuixTransition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (!ViewCompat.isLaidOut(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        transitionValues.values.put(PROPNAME_BOUNDS, new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        for (Map.Entry<String, ViewProperty> entry : sViewPropertyMap.entrySet()) {
            transitionValues.values.put(entry.getKey(), Float.valueOf(entry.getValue().getValue(view)));
        }
        transitionValues.values.put(PROPNAME_PARENT, transitionValues.view.getParent());
        if (this.mReparent) {
            transitionValues.view.getLocationInWindow(this.mTempLocation);
            transitionValues.values.put(PROPNAME_WINDOW_X, Integer.valueOf(this.mTempLocation[0]));
            transitionValues.values.put(PROPNAME_WINDOW_Y, Integer.valueOf(this.mTempLocation[1]));
        }
    }

    @Override // miuix.transition.MiuixTransition
    public void createAnimator(final ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return;
        }
        Map<String, Object> map = transitionValues.values;
        Map<String, Object> map2 = transitionValues2.values;
        ViewGroup viewGroup2 = (ViewGroup) map.get(PROPNAME_PARENT);
        ViewGroup viewGroup3 = (ViewGroup) map2.get(PROPNAME_PARENT);
        if (viewGroup2 == null || viewGroup3 == null) {
            return;
        }
        final View view = transitionValues2.view;
        final AnimConfig animConfig = new AnimConfig();
        animConfig.addListeners(new TransitionListener() { // from class: miuix.transition.ChangeBounds.1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                if (obj == ChangeBounds.END_TAG) {
                    ChangeBounds changeBounds = ChangeBounds.this;
                    if (changeBounds.mNumInstances == 0) {
                        changeBounds.onTransitionStart();
                    }
                    ChangeBounds.this.mNumInstances++;
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                if (obj == ChangeBounds.END_TAG) {
                    ChangeBounds changeBounds = ChangeBounds.this;
                    int i2 = changeBounds.mNumInstances - 1;
                    changeBounds.mNumInstances = i2;
                    if (i2 == 0) {
                        animConfig.removeListeners(this);
                        ChangeBounds.this.onTransitionEnd();
                    }
                }
            }
        });
        if (parentMatches(viewGroup2, viewGroup3)) {
            Rect rect = (Rect) transitionValues.values.get(PROPNAME_BOUNDS);
            Rect rect2 = (Rect) transitionValues2.values.get(PROPNAME_BOUNDS);
            int i2 = rect.left;
            int i3 = rect2.left;
            int i4 = rect.top;
            int i5 = rect2.top;
            int i6 = rect.right;
            int i7 = rect2.right;
            int i8 = rect.bottom;
            int i9 = rect2.bottom;
            if (i2 == i3 && i4 == i5 && i6 == i7 && i8 == i9) {
                return;
            }
            final ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            ((ViewGroup) view.getParent()).suppressLayout(true);
            animConfig.addListeners(new TransitionListener() { // from class: miuix.transition.ChangeBounds.2
                @Override // miuix.animation.listener.TransitionListener
                public void onComplete(Object obj) {
                    if (obj == ChangeBounds.END_TAG) {
                        animConfig.removeListeners(this);
                        viewGroup4.suppressLayout(false);
                    }
                }
            });
            ViewBounds viewBounds = new ViewBounds(view);
            ViewUtils.setLeftTopRightBottom(view, i2, i4, i6, i8);
            IStateStyle iStateStyleUseValue = Folme.useValue(viewBounds);
            AnimState animStateAdd = new AnimState(START_TAG).add((ViewProperty) this.mLeftProperty, i2, 4).add((ViewProperty) this.mTopProperty, i4, 4).add((ViewProperty) this.mRightProperty, i6, 4).add((ViewProperty) this.mBottomProperty, i8, 4);
            AnimState animStateAdd2 = new AnimState(END_TAG).add((ViewProperty) this.mLeftProperty, i3, 4).add((ViewProperty) this.mTopProperty, i5, 4).add((ViewProperty) this.mRightProperty, i7, 4).add((ViewProperty) this.mBottomProperty, i9, 4);
            iStateStyleUseValue.setTo(animStateAdd);
            addTransitionRunner(new MiuixTransition.TransitionRunner(viewBounds, animStateAdd, animStateAdd2, getAnimConfig(), animConfig));
            return;
        }
        viewGroup.getLocationInWindow(this.mTempLocation);
        int iIntValue = ((Integer) transitionValues.values.get(PROPNAME_WINDOW_X)).intValue() - this.mTempLocation[0];
        int iIntValue2 = ((Integer) transitionValues.values.get(PROPNAME_WINDOW_Y)).intValue() - this.mTempLocation[1];
        int iIntValue3 = ((Integer) transitionValues2.values.get(PROPNAME_WINDOW_X)).intValue() - this.mTempLocation[0];
        int iIntValue4 = ((Integer) transitionValues2.values.get(PROPNAME_WINDOW_Y)).intValue() - this.mTempLocation[1];
        float fFloatValue = ((Float) transitionValues.values.get(PROPNAME_WIDTH)).floatValue() + 0.0f;
        float fFloatValue2 = ((Float) transitionValues.values.get(PROPNAME_HEIGHT)).floatValue() + 0.0f;
        float fFloatValue3 = ((Float) transitionValues2.values.get(PROPNAME_WIDTH)).floatValue() + 0.0f;
        float fFloatValue4 = ((Float) transitionValues2.values.get(PROPNAME_HEIGHT)).floatValue() + 0.0f;
        Rect rect3 = new Rect();
        transitionValues.view.getLocalVisibleRect(rect3);
        if (rect3.top > 0 || rect3.bottom < fFloatValue2) {
            return;
        }
        if (iIntValue == iIntValue3 && iIntValue2 == iIntValue4) {
            return;
        }
        final View viewCopyViewImage = MiuixTransitionUtils.copyViewImage(viewGroup, view, viewGroup2);
        viewGroup.getOverlay().add(viewCopyViewImage);
        final float alpha = view.getAlpha();
        view.setAlpha(0.0f);
        AnimState animState = new AnimState(START_TAG);
        ViewProperty viewProperty = ViewProperty.f6001X;
        AnimState animStateAdd3 = animState.add(viewProperty, iIntValue);
        ViewProperty viewProperty2 = ViewProperty.f6002Y;
        AnimState animStateAdd4 = animStateAdd3.add(viewProperty2, iIntValue2);
        ViewProperty viewProperty3 = ViewProperty.WIDTH;
        AnimState animStateAdd5 = animStateAdd4.add(viewProperty3, fFloatValue);
        ViewProperty viewProperty4 = ViewProperty.HEIGHT;
        AnimState animStateAdd6 = animStateAdd5.add(viewProperty4, fFloatValue2);
        AnimState animStateAdd7 = new AnimState(END_TAG).add(viewProperty, iIntValue3).add(viewProperty2, iIntValue4).add(viewProperty3, fFloatValue3).add(viewProperty4, fFloatValue4);
        animConfig.addListeners(new TransitionListener() { // from class: miuix.transition.ChangeBounds.3
            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                if (obj == ChangeBounds.END_TAG) {
                    animConfig.removeListeners(this);
                    viewGroup.getOverlay().remove(viewCopyViewImage);
                    view.setAlpha(alpha);
                }
            }
        });
        Folme.useAt(viewCopyViewImage).state().setTo(animStateAdd6);
        addTransitionRunner(new MiuixTransition.TransitionRunner(viewCopyViewImage, animStateAdd6, animStateAdd7, getAnimConfig(), animConfig));
    }

    @Override // miuix.transition.MiuixTransition
    @Nullable
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public boolean parentMatches(View view, View view2) {
        if (!this.mReparent) {
            return true;
        }
        TransitionValues matchedTransitionValues = getMatchedTransitionValues(view, true);
        if (matchedTransitionValues == null) {
            if (view == view2) {
                return true;
            }
        } else if (view2 == matchedTransitionValues.view) {
            return true;
        }
        return false;
    }

    public ChangeBounds setReparent(boolean z2) {
        this.mReparent = z2;
        return this;
    }

    public ChangeBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mReparent = false;
        this.mTempLocation = new int[2];
        this.mLeftProperty = new LeftProperty();
        this.mTopProperty = new TopProperty();
        this.mRightProperty = new RightProperty();
        this.mBottomProperty = new BottomProperty();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ChangeBounds);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ChangeBounds_reparent, false);
        typedArrayObtainStyledAttributes.recycle();
        setReparent(z2);
    }
}
