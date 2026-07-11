package com.miui.maml.elements.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.text.TextUtils;
import com.miui.maml.ScreenElementRoot;
import com.miui.maml.data.IndexedVariable;
import com.miui.maml.elements.AnimatedScreenElement;
import com.miui.maml.util.Utils;
import d.AbstractC0315p;
import d.C0307h;
import d.F;
import d.H;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes2.dex */
public class LottieScreenElement extends AnimatedScreenElement {
    public static final String LOG_TAG = "LottieScreenElement";
    public static final String TAG_NAME = "Lottie";
    private boolean mAutoPlay;
    private F mDrawable;
    private IndexedVariable mDurationProperty;
    private int mLoop;
    private String mLottiePath;
    private IndexedVariable mProgressProperty;

    public LottieScreenElement(Element element, ScreenElementRoot screenElementRoot) {
        super(element, screenElementRoot);
        this.mLottiePath = element.getAttribute("src");
        this.mLoop = Utils.getAttrAsInt(element, "loop", -1);
        this.mAutoPlay = com.xiaomi.onetrack.util.a.f3424i.equals(element.getAttribute("autoplay"));
        load();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$load$0(C0307h c0307h) {
        this.mDrawable.N0(c0307h);
        int i2 = this.mLoop;
        if (i2 < 0) {
            this.mDrawable.i1(-1);
        } else {
            this.mDrawable.i1(i2);
        }
        IndexedVariable indexedVariable = this.mDurationProperty;
        if (indexedVariable != null) {
            indexedVariable.set((int) (this.mDrawable.T() - this.mDrawable.U()));
        }
        if (this.mAutoPlay) {
            this.mDrawable.A0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$load$1(ValueAnimator valueAnimator) {
        IndexedVariable indexedVariable = this.mProgressProperty;
        if (indexedVariable != null) {
            indexedVariable.set(this.mDrawable.W());
        }
        performAction("update");
    }

    private void load() {
        String str;
        if (!TextUtils.isEmpty(this.mName)) {
            this.mProgressProperty = new IndexedVariable(this.mName + ".progress", getVariables(), true);
            this.mDurationProperty = new IndexedVariable(this.mName + ".duration", getVariables(), true);
        }
        if (this.mLottiePath != null) {
            this.mDrawable = new F();
            if (this.mRoot.getContext().mResourceManager == null || this.mRoot.getContext().mResourceManager.getResourceLoader() == null) {
                str = null;
            } else {
                str = this.mRoot.getContext().mResourceManager.getResourceLoader().getID() + this.mLottiePath;
            }
            AbstractC0315p.n(getRoot().getContext().mResourceManager.getInputStream(this.mLottiePath), str).d(new H() { // from class: com.miui.maml.elements.lottie.a
                @Override // d.H
                public final void onResult(Object obj) {
                    this.f2553a.lambda$load$0((C0307h) obj);
                }
            });
            this.mDrawable.t(new ValueAnimator.AnimatorUpdateListener() { // from class: com.miui.maml.elements.lottie.b
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f2554a.lambda$load$1(valueAnimator);
                }
            });
            this.mDrawable.r(new Animator.AnimatorListener() { // from class: com.miui.maml.elements.lottie.LottieScreenElement.1
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    if (LottieScreenElement.this.mProgressProperty != null) {
                        LottieScreenElement.this.mProgressProperty.set(1.0d);
                    }
                    LottieScreenElement.this.performAction("complete");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                    if (LottieScreenElement.this.mProgressProperty != null) {
                        LottieScreenElement.this.mProgressProperty.set(1.0d);
                    }
                    LottieScreenElement.this.performAction("loopComplete");
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }
            });
        }
    }

    @Override // com.miui.maml.elements.ScreenElement
    public void doRender(Canvas canvas) {
        if (this.mDrawable != null) {
            float x2 = getX();
            float y2 = getY();
            float width = getWidth();
            float height = getHeight();
            float left = getLeft(0.0f, width);
            float top = getTop(0.0f, height);
            float f2 = left <= 0.0f ? x2 + left : left;
            float f3 = top <= 0.0f ? y2 + top : top;
            if (width <= 0.0f) {
                width = 0.0f;
            }
            float f4 = width + f2;
            if (height <= 0.0f) {
                height = 0.0f;
            }
            canvas.translate(left, top);
            this.mDrawable.setBounds((int) f2, (int) f3, (int) f4, (int) (height + f3));
            this.mDrawable.setAlpha(this.mAlpha);
            this.mDrawable.draw(canvas);
        }
    }

    public void goToAndPlay(int i2) {
        F f2 = this.mDrawable;
        if (f2 != null) {
            f2.P0(i2);
            if (this.mDrawable.g0()) {
                return;
            }
            playAnimation();
        }
    }

    public void goToAndStop(int i2) {
        F f2 = this.mDrawable;
        if (f2 != null) {
            f2.P0(i2);
            if (this.mDrawable.g0()) {
                stopAnimation();
            }
        }
    }

    public void pauseAnimation() {
        F f2 = this.mDrawable;
        if (f2 != null) {
            f2.z0();
        }
    }

    public void playAnimation() {
        F f2 = this.mDrawable;
        if (f2 != null) {
            f2.A0();
        }
    }

    public void playSegments(int i2, int i3) {
        F f2 = this.mDrawable;
        if (f2 != null) {
            f2.X0(i2, i3);
            IndexedVariable indexedVariable = this.mDurationProperty;
            if (indexedVariable != null) {
                indexedVariable.set((int) (this.mDrawable.T() - this.mDrawable.U()));
            }
        }
    }

    public void resumeAnimation() {
        F f2 = this.mDrawable;
        if (f2 != null) {
            f2.I0();
        }
    }

    public void setLoopCount(int i2) {
        F f2 = this.mDrawable;
        if (f2 != null) {
            f2.i1(i2);
        }
    }

    public void setSpeed(float f2) {
        F f3 = this.mDrawable;
        if (f3 != null) {
            f3.l1(f2);
        }
    }

    public void stopAnimation() {
        F f2 = this.mDrawable;
        if (f2 != null) {
            f2.stop();
        }
    }
}
