package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.annotation.FloatRange;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class WindowInsetsAnimationCompat {
    private static final boolean DEBUG = false;
    private static final String TAG = "WindowInsetsAnimCompat";
    private Impl mImpl;

    public static abstract class Callback {
        public static final int DISPATCH_MODE_CONTINUE_ON_SUBTREE = 1;
        public static final int DISPATCH_MODE_STOP = 0;
        WindowInsetsCompat mDispachedInsets;
        private final int mDispatchMode;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public @interface DispatchMode {
        }

        public Callback(int i2) {
            this.mDispatchMode = i2;
        }

        public final int getDispatchMode() {
            return this.mDispatchMode;
        }

        public void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        }

        public void onPrepare(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        }

        public abstract WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List<WindowInsetsAnimationCompat> list);

        public BoundsCompat onStart(WindowInsetsAnimationCompat windowInsetsAnimationCompat, BoundsCompat boundsCompat) {
            return boundsCompat;
        }
    }

    public static class Impl {
        private float mAlpha = 1.0f;
        private final long mDurationMillis;
        private float mFraction;
        private final Interpolator mInterpolator;
        private final int mTypeMask;

        public Impl(int i2, Interpolator interpolator, long j2) {
            this.mTypeMask = i2;
            this.mInterpolator = interpolator;
            this.mDurationMillis = j2;
        }

        public float getAlpha() {
            return this.mAlpha;
        }

        public long getDurationMillis() {
            return this.mDurationMillis;
        }

        public float getFraction() {
            return this.mFraction;
        }

        public float getInterpolatedFraction() {
            Interpolator interpolator = this.mInterpolator;
            return interpolator != null ? interpolator.getInterpolation(this.mFraction) : this.mFraction;
        }

        public Interpolator getInterpolator() {
            return this.mInterpolator;
        }

        public int getTypeMask() {
            return this.mTypeMask;
        }

        public void setAlpha(float f2) {
            this.mAlpha = f2;
        }

        public void setFraction(float f2) {
            this.mFraction = f2;
        }
    }

    @RequiresApi(21)
    public static class Impl21 extends Impl {
        private static final Interpolator SHOW_IME_INTERPOLATOR = new PathInterpolator(0.0f, 1.1f, 0.0f, 1.0f);
        private static final Interpolator HIDE_IME_INTERPOLATOR = new FastOutLinearInInterpolator();
        private static final Interpolator SHOW_SYSTEM_BAR_INTERPOLATOR = new DecelerateInterpolator(1.5f);
        private static final Interpolator HIDE_SYSTEM_BAR_INTERPOLATOR = new AccelerateInterpolator(1.5f);

        @RequiresApi(21)
        public static class Impl21OnApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
            private static final int COMPAT_ANIMATION_DURATION_IME = 160;
            private static final int COMPAT_ANIMATION_DURATION_SYSTEM_BAR = 250;
            final Callback mCallback;
            private WindowInsetsCompat mLastInsets;

            public Impl21OnApplyWindowInsetsListener(View view, Callback callback) {
                this.mCallback = callback;
                WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(view);
                this.mLastInsets = rootWindowInsets != null ? new WindowInsetsCompat.Builder(rootWindowInsets).build() : null;
            }

            @Override // android.view.View.OnApplyWindowInsetsListener
            public WindowInsets onApplyWindowInsets(final View view, WindowInsets windowInsets) {
                if (!view.isLaidOut()) {
                    this.mLastInsets = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, view);
                    return Impl21.forwardToViewIfNeeded(view, windowInsets);
                }
                final WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, view);
                if (this.mLastInsets == null) {
                    this.mLastInsets = ViewCompat.getRootWindowInsets(view);
                }
                if (this.mLastInsets == null) {
                    this.mLastInsets = windowInsetsCompat;
                    return Impl21.forwardToViewIfNeeded(view, windowInsets);
                }
                Callback callback = Impl21.getCallback(view);
                if (callback != null && Objects.equals(callback.mDispachedInsets, windowInsetsCompat)) {
                    return Impl21.forwardToViewIfNeeded(view, windowInsets);
                }
                int[] iArr = new int[1];
                int[] iArr2 = new int[1];
                Impl21.buildAnimationMask(windowInsetsCompat, this.mLastInsets, iArr, iArr2);
                int i2 = iArr[0];
                int i3 = iArr2[0];
                final int i4 = i2 | i3;
                if (i4 == 0) {
                    this.mLastInsets = windowInsetsCompat;
                    return Impl21.forwardToViewIfNeeded(view, windowInsets);
                }
                final WindowInsetsCompat windowInsetsCompat2 = this.mLastInsets;
                final WindowInsetsAnimationCompat windowInsetsAnimationCompat = new WindowInsetsAnimationCompat(i4, Impl21.createInsetInterpolator(i2, i3), (WindowInsetsCompat.Type.ime() & i4) != 0 ? 160L : 250L);
                windowInsetsAnimationCompat.setFraction(0.0f);
                final ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(windowInsetsAnimationCompat.getDurationMillis());
                final BoundsCompat boundsCompatComputeAnimationBounds = Impl21.computeAnimationBounds(windowInsetsCompat, windowInsetsCompat2, i4);
                Impl21.dispatchOnPrepare(view, windowInsetsAnimationCompat, windowInsetsCompat, false);
                duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.core.view.WindowInsetsAnimationCompat.Impl21.Impl21OnApplyWindowInsetsListener.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        windowInsetsAnimationCompat.setFraction(valueAnimator.getAnimatedFraction());
                        Impl21.dispatchOnProgress(view, Impl21.interpolateInsets(windowInsetsCompat, windowInsetsCompat2, windowInsetsAnimationCompat.getInterpolatedFraction(), i4), Collections.singletonList(windowInsetsAnimationCompat));
                    }
                });
                duration.addListener(new AnimatorListenerAdapter() { // from class: androidx.core.view.WindowInsetsAnimationCompat.Impl21.Impl21OnApplyWindowInsetsListener.2
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        windowInsetsAnimationCompat.setFraction(1.0f);
                        Impl21.dispatchOnEnd(view, windowInsetsAnimationCompat);
                    }
                });
                OneShotPreDrawListener.add(view, new Runnable() { // from class: androidx.core.view.WindowInsetsAnimationCompat.Impl21.Impl21OnApplyWindowInsetsListener.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Impl21.dispatchOnStart(view, windowInsetsAnimationCompat, boundsCompatComputeAnimationBounds);
                        duration.start();
                    }
                });
                this.mLastInsets = windowInsetsCompat;
                return Impl21.forwardToViewIfNeeded(view, windowInsets);
            }
        }

        public Impl21(int i2, Interpolator interpolator, long j2) {
            super(i2, interpolator, j2);
        }

        @SuppressLint({"WrongConstant"})
        public static void buildAnimationMask(WindowInsetsCompat windowInsetsCompat, WindowInsetsCompat windowInsetsCompat2, int[] iArr, int[] iArr2) {
            for (int i2 = 1; i2 <= 512; i2 <<= 1) {
                Insets insets = windowInsetsCompat.getInsets(i2);
                Insets insets2 = windowInsetsCompat2.getInsets(i2);
                int i3 = insets.left;
                int i4 = insets2.left;
                boolean z2 = i3 > i4 || insets.top > insets2.top || insets.right > insets2.right || insets.bottom > insets2.bottom;
                if (z2 != (i3 < i4 || insets.top < insets2.top || insets.right < insets2.right || insets.bottom < insets2.bottom)) {
                    if (z2) {
                        iArr[0] = iArr[0] | i2;
                    } else {
                        iArr2[0] = iArr2[0] | i2;
                    }
                }
            }
        }

        public static BoundsCompat computeAnimationBounds(WindowInsetsCompat windowInsetsCompat, WindowInsetsCompat windowInsetsCompat2, int i2) {
            Insets insets = windowInsetsCompat.getInsets(i2);
            Insets insets2 = windowInsetsCompat2.getInsets(i2);
            return new BoundsCompat(Insets.of(Math.min(insets.left, insets2.left), Math.min(insets.top, insets2.top), Math.min(insets.right, insets2.right), Math.min(insets.bottom, insets2.bottom)), Insets.of(Math.max(insets.left, insets2.left), Math.max(insets.top, insets2.top), Math.max(insets.right, insets2.right), Math.max(insets.bottom, insets2.bottom)));
        }

        public static Interpolator createInsetInterpolator(int i2, int i3) {
            if ((WindowInsetsCompat.Type.ime() & i2) != 0) {
                return SHOW_IME_INTERPOLATOR;
            }
            if ((WindowInsetsCompat.Type.ime() & i3) != 0) {
                return HIDE_IME_INTERPOLATOR;
            }
            if ((i2 & WindowInsetsCompat.Type.systemBars()) != 0) {
                return SHOW_SYSTEM_BAR_INTERPOLATOR;
            }
            if ((WindowInsetsCompat.Type.systemBars() & i3) != 0) {
                return HIDE_SYSTEM_BAR_INTERPOLATOR;
            }
            return null;
        }

        private static View.OnApplyWindowInsetsListener createProxyListener(View view, Callback callback) {
            return new Impl21OnApplyWindowInsetsListener(view, callback);
        }

        public static void dispatchOnEnd(View view, WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
            Callback callback = getCallback(view);
            if (callback != null) {
                callback.onEnd(windowInsetsAnimationCompat);
                if (callback.getDispatchMode() == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    dispatchOnEnd(viewGroup.getChildAt(i2), windowInsetsAnimationCompat);
                }
            }
        }

        public static void dispatchOnPrepare(View view, WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsetsCompat windowInsetsCompat, boolean z2) {
            Callback callback = getCallback(view);
            if (callback != null) {
                callback.mDispachedInsets = windowInsetsCompat;
                if (!z2) {
                    callback.onPrepare(windowInsetsAnimationCompat);
                    z2 = callback.getDispatchMode() == 0;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    dispatchOnPrepare(viewGroup.getChildAt(i2), windowInsetsAnimationCompat, windowInsetsCompat, z2);
                }
            }
        }

        public static void dispatchOnProgress(View view, WindowInsetsCompat windowInsetsCompat, List<WindowInsetsAnimationCompat> list) {
            Callback callback = getCallback(view);
            if (callback != null) {
                windowInsetsCompat = callback.onProgress(windowInsetsCompat, list);
                if (callback.getDispatchMode() == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    dispatchOnProgress(viewGroup.getChildAt(i2), windowInsetsCompat, list);
                }
            }
        }

        public static void dispatchOnStart(View view, WindowInsetsAnimationCompat windowInsetsAnimationCompat, BoundsCompat boundsCompat) {
            Callback callback = getCallback(view);
            if (callback != null) {
                callback.onStart(windowInsetsAnimationCompat, boundsCompat);
                if (callback.getDispatchMode() == 0) {
                    return;
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    dispatchOnStart(viewGroup.getChildAt(i2), windowInsetsAnimationCompat, boundsCompat);
                }
            }
        }

        public static WindowInsets forwardToViewIfNeeded(View view, WindowInsets windowInsets) {
            return view.getTag(R.id.tag_on_apply_window_listener) != null ? windowInsets : view.onApplyWindowInsets(windowInsets);
        }

        public static Callback getCallback(View view) {
            Object tag = view.getTag(R.id.tag_window_insets_animation_callback);
            if (tag instanceof Impl21OnApplyWindowInsetsListener) {
                return ((Impl21OnApplyWindowInsetsListener) tag).mCallback;
            }
            return null;
        }

        @SuppressLint({"WrongConstant"})
        public static WindowInsetsCompat interpolateInsets(WindowInsetsCompat windowInsetsCompat, WindowInsetsCompat windowInsetsCompat2, float f2, int i2) {
            WindowInsetsCompat.Builder builder = new WindowInsetsCompat.Builder(windowInsetsCompat);
            for (int i3 = 1; i3 <= 512; i3 <<= 1) {
                if ((i2 & i3) == 0) {
                    builder.setInsets(i3, windowInsetsCompat.getInsets(i3));
                } else {
                    Insets insets = windowInsetsCompat.getInsets(i3);
                    Insets insets2 = windowInsetsCompat2.getInsets(i3);
                    float f3 = 1.0f - f2;
                    builder.setInsets(i3, WindowInsetsCompat.insetInsets(insets, (int) (((double) ((insets.left - insets2.left) * f3)) + 0.5d), (int) (((double) ((insets.top - insets2.top) * f3)) + 0.5d), (int) (((double) ((insets.right - insets2.right) * f3)) + 0.5d), (int) (((double) ((insets.bottom - insets2.bottom) * f3)) + 0.5d)));
                }
            }
            return builder.build();
        }

        public static void setCallback(View view, Callback callback) {
            View.OnApplyWindowInsetsListener onApplyWindowInsetsListenerCreateProxyListener = callback != null ? createProxyListener(view, callback) : null;
            view.setTag(R.id.tag_window_insets_animation_callback, onApplyWindowInsetsListenerCreateProxyListener);
            if (view.getTag(R.id.tag_compat_insets_dispatch) == null && view.getTag(R.id.tag_on_apply_window_listener) == null) {
                view.setOnApplyWindowInsetsListener(onApplyWindowInsetsListenerCreateProxyListener);
            }
        }
    }

    public WindowInsetsAnimationCompat(int i2, Interpolator interpolator, long j2) {
        this.mImpl = new Impl30(i2, interpolator, j2);
    }

    public static void setCallback(View view, Callback callback) {
        Impl30.setCallback(view, callback);
    }

    @RequiresApi(30)
    public static WindowInsetsAnimationCompat toWindowInsetsAnimationCompat(WindowInsetsAnimation windowInsetsAnimation) {
        return new WindowInsetsAnimationCompat(windowInsetsAnimation);
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getAlpha() {
        return this.mImpl.getAlpha();
    }

    public long getDurationMillis() {
        return this.mImpl.getDurationMillis();
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getFraction() {
        return this.mImpl.getFraction();
    }

    public float getInterpolatedFraction() {
        return this.mImpl.getInterpolatedFraction();
    }

    public Interpolator getInterpolator() {
        return this.mImpl.getInterpolator();
    }

    public int getTypeMask() {
        return this.mImpl.getTypeMask();
    }

    public void setAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        this.mImpl.setAlpha(f2);
    }

    public void setFraction(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        this.mImpl.setFraction(f2);
    }

    @RequiresApi(30)
    public static class Impl30 extends Impl {
        private final WindowInsetsAnimation mWrapped;

        @RequiresApi(30)
        public static class ProxyCallback extends WindowInsetsAnimation.Callback {
            private final HashMap<WindowInsetsAnimation, WindowInsetsAnimationCompat> mAnimations;
            private final Callback mCompat;
            private List<WindowInsetsAnimationCompat> mRORunningAnimations;
            private ArrayList<WindowInsetsAnimationCompat> mTmpRunningAnimations;

            public ProxyCallback(Callback callback) {
                super(callback.getDispatchMode());
                this.mAnimations = new HashMap<>();
                this.mCompat = callback;
            }

            private WindowInsetsAnimationCompat getWindowInsetsAnimationCompat(WindowInsetsAnimation windowInsetsAnimation) {
                WindowInsetsAnimationCompat windowInsetsAnimationCompat = this.mAnimations.get(windowInsetsAnimation);
                if (windowInsetsAnimationCompat != null) {
                    return windowInsetsAnimationCompat;
                }
                WindowInsetsAnimationCompat windowInsetsAnimationCompat2 = WindowInsetsAnimationCompat.toWindowInsetsAnimationCompat(windowInsetsAnimation);
                this.mAnimations.put(windowInsetsAnimation, windowInsetsAnimationCompat2);
                return windowInsetsAnimationCompat2;
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
                this.mCompat.onEnd(getWindowInsetsAnimationCompat(windowInsetsAnimation));
                this.mAnimations.remove(windowInsetsAnimation);
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
                this.mCompat.onPrepare(getWindowInsetsAnimationCompat(windowInsetsAnimation));
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public WindowInsets onProgress(WindowInsets windowInsets, List<WindowInsetsAnimation> list) {
                ArrayList<WindowInsetsAnimationCompat> arrayList = this.mTmpRunningAnimations;
                if (arrayList == null) {
                    ArrayList<WindowInsetsAnimationCompat> arrayList2 = new ArrayList<>(list.size());
                    this.mTmpRunningAnimations = arrayList2;
                    this.mRORunningAnimations = Collections.unmodifiableList(arrayList2);
                } else {
                    arrayList.clear();
                }
                for (int size = list.size() - 1; size >= 0; size--) {
                    WindowInsetsAnimation windowInsetsAnimation = list.get(size);
                    WindowInsetsAnimationCompat windowInsetsAnimationCompat = getWindowInsetsAnimationCompat(windowInsetsAnimation);
                    windowInsetsAnimationCompat.setFraction(windowInsetsAnimation.getFraction());
                    this.mTmpRunningAnimations.add(windowInsetsAnimationCompat);
                }
                return this.mCompat.onProgress(WindowInsetsCompat.toWindowInsetsCompat(windowInsets), this.mRORunningAnimations).toWindowInsets();
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
                return this.mCompat.onStart(getWindowInsetsAnimationCompat(windowInsetsAnimation), BoundsCompat.toBoundsCompat(bounds)).toBounds();
            }
        }

        public Impl30(WindowInsetsAnimation windowInsetsAnimation) {
            super(0, null, 0L);
            this.mWrapped = windowInsetsAnimation;
        }

        public static WindowInsetsAnimation.Bounds createPlatformBounds(BoundsCompat boundsCompat) {
            return new WindowInsetsAnimation.Bounds(boundsCompat.getLowerBound().toPlatformInsets(), boundsCompat.getUpperBound().toPlatformInsets());
        }

        public static Insets getHigherBounds(WindowInsetsAnimation.Bounds bounds) {
            return Insets.toCompatInsets(bounds.getUpperBound());
        }

        public static Insets getLowerBounds(WindowInsetsAnimation.Bounds bounds) {
            return Insets.toCompatInsets(bounds.getLowerBound());
        }

        public static void setCallback(View view, Callback callback) {
            view.setWindowInsetsAnimationCallback(callback != null ? new ProxyCallback(callback) : null);
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public float getAlpha() {
            return this.mWrapped.getAlpha();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public long getDurationMillis() {
            return this.mWrapped.getDurationMillis();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public float getFraction() {
            return this.mWrapped.getFraction();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public float getInterpolatedFraction() {
            return this.mWrapped.getInterpolatedFraction();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public Interpolator getInterpolator() {
            return this.mWrapped.getInterpolator();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public int getTypeMask() {
            return this.mWrapped.getTypeMask();
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public void setAlpha(float f2) {
            this.mWrapped.setAlpha(f2);
        }

        @Override // androidx.core.view.WindowInsetsAnimationCompat.Impl
        public void setFraction(float f2) {
            this.mWrapped.setFraction(f2);
        }

        public Impl30(int i2, Interpolator interpolator, long j2) {
            this(new WindowInsetsAnimation(i2, interpolator, j2));
        }
    }

    public static final class BoundsCompat {
        private final Insets mLowerBound;
        private final Insets mUpperBound;

        public BoundsCompat(Insets insets, Insets insets2) {
            this.mLowerBound = insets;
            this.mUpperBound = insets2;
        }

        @RequiresApi(30)
        public static BoundsCompat toBoundsCompat(WindowInsetsAnimation.Bounds bounds) {
            return new BoundsCompat(bounds);
        }

        public Insets getLowerBound() {
            return this.mLowerBound;
        }

        public Insets getUpperBound() {
            return this.mUpperBound;
        }

        public BoundsCompat inset(Insets insets) {
            return new BoundsCompat(WindowInsetsCompat.insetInsets(this.mLowerBound, insets.left, insets.top, insets.right, insets.bottom), WindowInsetsCompat.insetInsets(this.mUpperBound, insets.left, insets.top, insets.right, insets.bottom));
        }

        @RequiresApi(30)
        public WindowInsetsAnimation.Bounds toBounds() {
            return Impl30.createPlatformBounds(this);
        }

        public String toString() {
            return "Bounds{lower=" + this.mLowerBound + " upper=" + this.mUpperBound + "}";
        }

        @RequiresApi(30)
        private BoundsCompat(WindowInsetsAnimation.Bounds bounds) {
            this.mLowerBound = Impl30.getLowerBounds(bounds);
            this.mUpperBound = Impl30.getHigherBounds(bounds);
        }
    }

    @RequiresApi(30)
    private WindowInsetsAnimationCompat(WindowInsetsAnimation windowInsetsAnimation) {
        this(0, null, 0L);
        this.mImpl = new Impl30(windowInsetsAnimation);
    }
}
