package miuix.transition;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.transition.MiuixTransitionUtils;
import java.util.Map;
import miuix.animation.Folme;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.property.ViewProperty;
import miuix.internal.util.ViewUtils;
import miuix.transition.ChangeBounds;
import miuix.transition.MiuixTransition;

/* JADX INFO: loaded from: classes5.dex */
public class TrendTransition extends ChangeBounds {
    private static final String END_TAG = "trendtransition_end";
    private static final String PROPNAME_ALPHA = "android:transition:alpha";
    private static final String START_TAG = "trendtransition_start";
    AnimState invisibleState;
    private AnimConfig mEnterAnimConfig;
    private AnimConfig mExitAnimConfig;
    private int[] mTempLocation;

    public TrendTransition() {
        this.mTempLocation = new int[2];
        this.invisibleState = new AnimState().add(ViewProperty.ALPHA, 0.0d);
    }

    @Override // miuix.transition.ChangeBounds, miuix.transition.MiuixTransition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // miuix.transition.ChangeBounds, miuix.transition.MiuixTransition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // miuix.transition.ChangeBounds
    public void captureValues(TransitionValues transitionValues) {
        super.captureValues(transitionValues);
        View view = transitionValues.view;
        if (!ViewCompat.isLaidOut(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        transitionValues.values.put(PROPNAME_ALPHA, Float.valueOf(view.getAlpha()));
    }

    @Override // miuix.transition.ChangeBounds, miuix.transition.MiuixTransition
    public void createAnimator(final ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        View view;
        if (transitionValues == null || transitionValues2 == null) {
            return;
        }
        Map<String, Object> map = transitionValues.values;
        Map<String, Object> map2 = transitionValues2.values;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:transition:parent");
        final ViewGroup viewGroup3 = (ViewGroup) map2.get("android:transition:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return;
        }
        final View view2 = transitionValues2.view;
        final View view3 = transitionValues.view;
        final float alpha = view2.getAlpha();
        AnimState animStateAdd = new AnimState().add(ViewProperty.ALPHA, alpha);
        AnimConfig animConfig = this.mEnterAnimConfig;
        if (animConfig == null) {
            animConfig = getAnimConfig();
        }
        AnimConfig animConfig2 = this.mExitAnimConfig;
        if (animConfig2 == null) {
            animConfig2 = getAnimConfig();
        }
        final AnimConfig animConfig3 = new AnimConfig();
        animConfig3.addListeners(new TransitionListener() { // from class: miuix.transition.TrendTransition.1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                if (obj == TrendTransition.END_TAG) {
                    TrendTransition trendTransition = TrendTransition.this;
                    if (trendTransition.mNumInstances == 0) {
                        trendTransition.onTransitionStart();
                    }
                    TrendTransition.this.mNumInstances++;
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                if (obj == TrendTransition.END_TAG) {
                    TrendTransition trendTransition = TrendTransition.this;
                    int i2 = trendTransition.mNumInstances - 1;
                    trendTransition.mNumInstances = i2;
                    if (i2 == 0) {
                        animConfig3.removeListeners(this);
                        TrendTransition.this.onTransitionEnd();
                    }
                }
            }
        });
        if (parentMatches(viewGroup2, viewGroup3)) {
            viewGroup2.removeViewInLayout(view3);
            viewGroup3.getOverlay().add(view3);
            Rect rect = (Rect) transitionValues.values.get("android:transition:bounds");
            Rect rect2 = (Rect) transitionValues2.values.get("android:transition:bounds");
            int i2 = rect.left;
            int i3 = rect2.left;
            int i4 = rect.top;
            int i5 = rect2.top;
            int i6 = rect.right;
            int i7 = rect2.right;
            int i8 = rect.bottom;
            int i9 = rect2.bottom;
            if (i2 == i3 && i4 == i5 && i6 == i7 && i8 == i9) {
                view = view2;
            } else {
                AnimConfig animConfig4 = animConfig;
                ChangeBounds.ViewBounds viewBounds = new ChangeBounds.ViewBounds(view3);
                ChangeBounds.ViewBounds viewBounds2 = new ChangeBounds.ViewBounds(view2);
                ViewUtils.setLeftTopRightBottom(view2, i2, i4, i6, i8);
                view = view2;
                AnimState animStateAdd2 = new AnimState(START_TAG).add((ViewProperty) this.mLeftProperty, i2, 4).add((ViewProperty) this.mTopProperty, i4, 4).add((ViewProperty) this.mRightProperty, i6, 4).add((ViewProperty) this.mBottomProperty, i8, 4);
                AnimState animStateAdd3 = new AnimState(END_TAG).add((ViewProperty) this.mLeftProperty, i3, 4).add((ViewProperty) this.mTopProperty, i5, 4).add((ViewProperty) this.mRightProperty, i7, 4).add((ViewProperty) this.mBottomProperty, i9, 4);
                animConfig3.addListeners(new TransitionListener() { // from class: miuix.transition.TrendTransition.2
                    @Override // miuix.animation.listener.TransitionListener
                    public void onComplete(Object obj) {
                        if (obj == TrendTransition.END_TAG) {
                            animConfig3.removeListeners(this);
                            viewGroup3.getOverlay().remove(view3);
                        }
                    }
                });
                animConfig2 = animConfig2;
                addTransitionRunner(new MiuixTransition.TransitionRunner(viewBounds, animStateAdd2, animStateAdd3, animConfig2, animConfig3));
                animConfig = animConfig4;
                addTransitionRunner(new MiuixTransition.TransitionRunner(viewBounds2, animStateAdd2, animStateAdd3, animConfig, animConfig3));
            }
            Folme.useAt(view).state().setTo(this.invisibleState);
            addTransitionRunner(new MiuixTransition.TransitionRunner(view, this.invisibleState, animStateAdd, animConfig));
            addTransitionRunner(new MiuixTransition.TransitionRunner(view3, animStateAdd, this.invisibleState, animConfig2));
            return;
        }
        viewGroup.getLocationInWindow(this.mTempLocation);
        int iIntValue = ((Integer) transitionValues.values.get("android:transition:windowX")).intValue() - this.mTempLocation[0];
        AnimConfig animConfig5 = animConfig;
        int iIntValue2 = ((Integer) transitionValues.values.get("android:transition:windowY")).intValue() - this.mTempLocation[1];
        int iIntValue3 = ((Integer) transitionValues2.values.get("android:transition:windowX")).intValue() - this.mTempLocation[0];
        int iIntValue4 = ((Integer) transitionValues2.values.get("android:transition:windowY")).intValue() - this.mTempLocation[1];
        AnimConfig animConfig6 = animConfig2;
        float fFloatValue = ((Float) transitionValues.values.get("android:transition:width")).floatValue();
        float fFloatValue2 = ((Float) transitionValues.values.get("android:transition:height")).floatValue();
        float fFloatValue3 = ((Float) transitionValues2.values.get("android:transition:width")).floatValue();
        float fFloatValue4 = ((Float) transitionValues2.values.get("android:transition:height")).floatValue();
        if (iIntValue == iIntValue3 && iIntValue2 == iIntValue4) {
            return;
        }
        final View viewCopyViewImage = MiuixTransitionUtils.copyViewImage(viewGroup, view3, viewGroup2);
        final View viewCopyViewImage2 = MiuixTransitionUtils.copyViewImage(viewGroup, view2, viewGroup3);
        viewGroup.getOverlay().add(viewCopyViewImage);
        viewGroup.getOverlay().add(viewCopyViewImage2);
        view2.setAlpha(0.0f);
        viewCopyViewImage.setAlpha(1.0f);
        AnimState animState = new AnimState();
        ViewProperty viewProperty = ViewProperty.f6001X;
        AnimState animStateAdd4 = animState.add(viewProperty, iIntValue);
        ViewProperty viewProperty2 = ViewProperty.f6002Y;
        AnimState animStateAdd5 = animStateAdd4.add(viewProperty2, iIntValue2);
        ViewProperty viewProperty3 = ViewProperty.WIDTH;
        AnimState animStateAdd6 = animStateAdd5.add(viewProperty3, fFloatValue);
        ViewProperty viewProperty4 = ViewProperty.HEIGHT;
        AnimState animStateAdd7 = animStateAdd6.add(viewProperty4, fFloatValue2);
        AnimState animStateAdd8 = new AnimState(END_TAG).add(viewProperty, iIntValue3).add(viewProperty2, iIntValue4).add(viewProperty3, fFloatValue3).add(viewProperty4, fFloatValue4);
        animConfig3.addListeners(new TransitionListener() { // from class: miuix.transition.TrendTransition.3
            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                if (obj == TrendTransition.END_TAG) {
                    animConfig3.removeListeners(this);
                    viewGroup.getOverlay().remove(viewCopyViewImage2);
                    viewGroup.getOverlay().remove(viewCopyViewImage);
                    view2.setAlpha(alpha);
                }
            }
        });
        Folme.useAt(viewCopyViewImage).state().setTo(animStateAdd7);
        Folme.useAt(viewCopyViewImage2).state().setTo(animStateAdd7).setTo(this.invisibleState);
        addTransitionRunner(new MiuixTransition.TransitionRunner(viewCopyViewImage, animStateAdd7, animStateAdd8, animConfig6, animConfig3));
        addTransitionRunner(new MiuixTransition.TransitionRunner(viewCopyViewImage, animStateAdd, this.invisibleState, animConfig6));
        addTransitionRunner(new MiuixTransition.TransitionRunner(viewCopyViewImage2, animStateAdd7, animStateAdd8, animConfig5, animConfig3));
        addTransitionRunner(new MiuixTransition.TransitionRunner(viewCopyViewImage2, this.invisibleState, animStateAdd, animConfig5));
    }

    public void setEnterAnimConfig(AnimConfig animConfig) {
        this.mEnterAnimConfig = animConfig;
    }

    public void setExitAnimConfig(AnimConfig animConfig) {
        this.mExitAnimConfig = animConfig;
    }

    public TrendTransition(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTempLocation = new int[2];
        this.invisibleState = new AnimState().add(ViewProperty.ALPHA, 0.0d);
    }
}
