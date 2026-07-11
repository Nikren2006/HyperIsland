package miuix.appcompat.internal.app;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import java.lang.ref.WeakReference;
import miuix.animation.utils.SpringInterpolator;
import miuix.device.DeviceUtils;

/* JADX INFO: loaded from: classes3.dex */
public class FragmentAnimator extends ValueAnimator implements View.OnLayoutChangeListener, Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
    private static final float DIM_ALPHA = 0.3f;
    private static final float ENTER_OFFSET = 1.0f;
    private static final float EXIT_OFFSET = -0.25f;
    private int mFromDim;
    private float mFromXDelta;
    private float mFromXValue;
    private WeakReference<Object> mTarget;
    private int mToDim;
    private float mToXDelta;
    private float mToXValue;
    private static final boolean USE_DIM = !DeviceUtils.isMiuiLiteV2();
    private static final SpringInterpolator INTERPOLATOR = new SpringInterpolator(0.95f, 0.4f);

    public FragmentAnimator(Fragment fragment, boolean z2, boolean z3) {
        Context context = fragment.getContext();
        boolean z4 = false;
        if (context != null && context.getResources().getConfiguration().getLayoutDirection() == 1) {
            z4 = true;
        }
        if (z2) {
            if (!z3) {
                if (z4) {
                    initValues(0.0f, 0.25f);
                } else {
                    initValues(0.0f, EXIT_OFFSET);
                }
                if (USE_DIM) {
                    this.mToDim = Math.round(76.5f);
                }
            } else if (z4) {
                initValues(-1.0f, 0.0f);
            } else {
                initValues(1.0f, 0.0f);
            }
        } else if (z3) {
            if (z4) {
                initValues(0.25f, 0.0f);
            } else {
                initValues(EXIT_OFFSET, 0.0f);
            }
            if (USE_DIM) {
                this.mFromDim = Math.round(76.5f);
            }
        } else if (z4) {
            initValues(0.0f, -1.0f);
        } else {
            initValues(0.0f, 1.0f);
        }
        addListener(this);
        addUpdateListener(this);
        setFloatValues(0.0f, 1.0f);
        SpringInterpolator springInterpolator = INTERPOLATOR;
        setInterpolator(springInterpolator);
        setDuration(springInterpolator.getDuration());
    }

    @RequiresApi(api = 23)
    private void clearForeground(View view) {
        view.setForeground(null);
    }

    private void initValues(float f2, float f3) {
        this.mFromXValue = f2;
        this.mToXValue = f3;
    }

    @RequiresApi(api = 23)
    private void setForegroundDim(View view, int i2) {
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 > 255) {
            i2 = 255;
        }
        Drawable foreground = view.getForeground();
        if (foreground == null) {
            foreground = new ColorDrawable(ViewCompat.MEASURED_STATE_MASK);
            view.setForeground(foreground);
        }
        foreground.setAlpha(i2);
    }

    private void updateTargetParams() {
        Object target = getTarget();
        float width = target instanceof View ? ((View) target).getWidth() : 0;
        this.mFromXDelta = this.mFromXValue * width;
        this.mToXDelta = this.mToXValue * width;
    }

    @Nullable
    public Object getTarget() {
        WeakReference<Object> weakReference = this.mTarget;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(@NonNull Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(@NonNull Animator animator) {
        if (getTarget() instanceof View) {
            View view = (View) getTarget();
            view.removeOnLayoutChangeListener(this);
            view.setTranslationX(0.0f);
            if (this.mFromDim != this.mToDim) {
                clearForeground(view);
            }
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(@NonNull Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(@NonNull Animator animator) {
        if (getTarget() instanceof View) {
            View view = (View) getTarget();
            updateTargetParams();
            view.addOnLayoutChangeListener(this);
            view.setTranslationX(this.mFromXDelta);
            int i2 = this.mFromDim;
            if (i2 != this.mToDim) {
                setForegroundDim(view, i2);
            }
        }
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
        if (getTarget() instanceof View) {
            View view = (View) getTarget();
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            float f2 = this.mFromXDelta;
            float f3 = this.mToXDelta;
            if (f2 != f3) {
                f2 += (f3 - f2) * fFloatValue;
            }
            view.setTranslationX(f2);
            int i2 = this.mFromDim;
            if (i2 != this.mToDim) {
                setForegroundDim(view, Math.round(i2 + ((r2 - i2) * fFloatValue)));
            }
        }
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        updateTargetParams();
        if (isRunning()) {
            onAnimationUpdate(this);
        }
    }

    @Override // android.animation.Animator
    public void setTarget(@Nullable Object obj) {
        Object target = getTarget();
        if (target != obj) {
            if (isStarted()) {
                cancel();
            }
            if (target instanceof View) {
                ((View) target).removeOnLayoutChangeListener(this);
            }
            this.mTarget = obj == null ? null : new WeakReference<>(obj);
        }
    }
}
