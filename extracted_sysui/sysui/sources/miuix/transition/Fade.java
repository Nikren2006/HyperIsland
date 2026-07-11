package miuix.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.property.ViewProperty;
import miuix.transition.MiuixTransition;

/* JADX INFO: loaded from: classes5.dex */
public class Fade extends Visibility {
    protected static final String FADE_IN_END_TAG = "fade_in_end";
    protected static final String FADE_OUT_END_TAG = "fade_out_end";
    public static final int IN = 1;
    public static final int OUT = 2;
    protected static final String PROPNAME_TRANSITION_ALPHA = "android:fade:transitionAlpha";
    AnimState invisibleState;
    AnimState visibleState;

    public Fade(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AnimState animState = new AnimState(FADE_IN_END_TAG);
        ViewProperty viewProperty = ViewProperty.AUTO_ALPHA;
        this.visibleState = animState.add(viewProperty, 1.0d);
        this.invisibleState = new AnimState(FADE_OUT_END_TAG).add(viewProperty, 0.0d);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Fade);
        setMode(typedArrayObtainStyledAttributes.getInt(R.styleable.Fade_fadingMode, getMode()));
        typedArrayObtainStyledAttributes.recycle();
    }

    private static float getStartAlpha(TransitionValues transitionValues, float f2) {
        Float f3;
        return (transitionValues == null || (f3 = (Float) transitionValues.values.get(PROPNAME_TRANSITION_ALPHA)) == null) ? f2 : f3.floatValue();
    }

    @Override // miuix.transition.Visibility, miuix.transition.MiuixTransition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
        transitionValues.values.put(PROPNAME_TRANSITION_ALPHA, Float.valueOf(transitionValues.view.getAlpha()));
    }

    @Override // miuix.transition.Visibility, miuix.transition.MiuixTransition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        transitionValues.values.put(PROPNAME_TRANSITION_ALPHA, Float.valueOf(transitionValues.view.getAlpha()));
    }

    @Override // miuix.transition.Visibility
    public void onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        AnimState animStateAdd = new AnimState().add(ViewProperty.ALPHA, getStartAlpha(transitionValues, 0.0f) != 1.0f ? r5 : 0.0f);
        final AnimConfig animConfig = new AnimConfig();
        animConfig.addListeners(new TransitionListener() { // from class: miuix.transition.Fade.1
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                if (obj == Fade.FADE_IN_END_TAG) {
                    Fade fade = Fade.this;
                    if (fade.mNumInstances == 0) {
                        fade.onTransitionStart();
                    }
                    Fade.this.mNumInstances++;
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                if (obj == Fade.FADE_IN_END_TAG) {
                    Fade fade = Fade.this;
                    int i2 = fade.mNumInstances - 1;
                    fade.mNumInstances = i2;
                    if (i2 == 0) {
                        animConfig.removeListeners(this);
                        Fade.this.onTransitionEnd();
                    }
                }
            }
        });
        IStateStyle iStateStyleState = Folme.useAt(view).state();
        iStateStyleState.setTo(animStateAdd);
        addTransitionRunner(new MiuixTransition.TransitionRunner(iStateStyleState, (Object) animStateAdd, (Object) this.visibleState, getAnimConfig(), animConfig));
    }

    @Override // miuix.transition.Visibility
    public void onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2, TransitionListener transitionListener) {
        AnimState animStateAdd = new AnimState().add(ViewProperty.AUTO_ALPHA, getStartAlpha(transitionValues, 1.0f));
        final AnimConfig animConfig = new AnimConfig();
        animConfig.addListeners(new TransitionListener() { // from class: miuix.transition.Fade.2
            @Override // miuix.animation.listener.TransitionListener
            public void onBegin(Object obj) {
                if (obj == Fade.FADE_OUT_END_TAG) {
                    Fade fade = Fade.this;
                    if (fade.mNumInstances == 0) {
                        fade.onTransitionStart();
                    }
                    Fade.this.mNumInstances++;
                }
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                if (obj == Fade.FADE_OUT_END_TAG) {
                    Fade fade = Fade.this;
                    int i2 = fade.mNumInstances - 1;
                    fade.mNumInstances = i2;
                    if (i2 == 0) {
                        animConfig.removeListeners(this);
                        Fade.this.onTransitionEnd();
                    }
                }
            }
        });
        IStateStyle iStateStyleState = Folme.useAt(view).state();
        iStateStyleState.setTo(animStateAdd);
        addTransitionRunner(new MiuixTransition.TransitionRunner(iStateStyleState, (Object) animStateAdd, (Object) this.invisibleState, getAnimConfig(), animConfig));
    }

    public Fade(int i2) {
        AnimState animState = new AnimState(FADE_IN_END_TAG);
        ViewProperty viewProperty = ViewProperty.AUTO_ALPHA;
        this.visibleState = animState.add(viewProperty, 1.0d);
        this.invisibleState = new AnimState(FADE_OUT_END_TAG).add(viewProperty, 0.0d);
        setMode(i2);
    }
}
