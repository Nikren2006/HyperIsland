package androidx.core.view;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.CancellationSignal;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.view.WindowInsetsController;
import android.view.animation.Interpolator;
import androidx.annotation.RequiresApi;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.accessibility.AccessibilityEventCompat;

/* JADX INFO: loaded from: classes.dex */
public final class WindowInsetsControllerCompat {
    public static final int BEHAVIOR_DEFAULT = 1;

    @Deprecated
    public static final int BEHAVIOR_SHOW_BARS_BY_SWIPE = 1;

    @Deprecated
    public static final int BEHAVIOR_SHOW_BARS_BY_TOUCH = 0;
    public static final int BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE = 2;
    private final Impl mImpl;

    public static class Impl {
        static final int KEY_BEHAVIOR = 356039078;

        public void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        public void controlWindowInsetsAnimation(int i2, long j2, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
        }

        public int getSystemBarsBehavior() {
            return 1;
        }

        public void hide(int i2) {
        }

        public boolean isAppearanceLightNavigationBars() {
            return false;
        }

        public boolean isAppearanceLightStatusBars() {
            return false;
        }

        public void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        public void setAppearanceLightNavigationBars(boolean z2) {
        }

        public void setAppearanceLightStatusBars(boolean z2) {
        }

        public void setSystemBarsBehavior(int i2) {
        }

        public void show(int i2) {
        }
    }

    @RequiresApi(20)
    public static class Impl20 extends Impl {
        private final SoftwareKeyboardControllerCompat mSoftwareKeyboardControllerCompat;
        protected final Window mWindow;

        public Impl20(Window window, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            this.mWindow = window;
            this.mSoftwareKeyboardControllerCompat = softwareKeyboardControllerCompat;
        }

        private void hideForType(int i2) {
            if (i2 == 1) {
                setSystemUiFlag(4);
            } else if (i2 == 2) {
                setSystemUiFlag(2);
            } else {
                if (i2 != 8) {
                    return;
                }
                this.mSoftwareKeyboardControllerCompat.hide();
            }
        }

        private void showForType(int i2) {
            if (i2 == 1) {
                unsetSystemUiFlag(4);
                unsetWindowFlag(1024);
            } else if (i2 == 2) {
                unsetSystemUiFlag(2);
            } else {
                if (i2 != 8) {
                    return;
                }
                this.mSoftwareKeyboardControllerCompat.show();
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void controlWindowInsetsAnimation(int i2, long j2, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public int getSystemBarsBehavior() {
            Object tag = this.mWindow.getDecorView().getTag(356039078);
            if (tag != null) {
                return ((Integer) tag).intValue();
            }
            return 1;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void hide(int i2) {
            for (int i3 = 1; i3 <= 512; i3 <<= 1) {
                if ((i2 & i3) != 0) {
                    hideForType(i3);
                }
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setSystemBarsBehavior(int i2) {
            this.mWindow.getDecorView().setTag(356039078, Integer.valueOf(i2));
            if (i2 == 0) {
                unsetSystemUiFlag(6144);
                return;
            }
            if (i2 == 1) {
                unsetSystemUiFlag(4096);
                setSystemUiFlag(2048);
            } else {
                if (i2 != 2) {
                    return;
                }
                unsetSystemUiFlag(2048);
                setSystemUiFlag(4096);
            }
        }

        public void setSystemUiFlag(int i2) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility(i2 | decorView.getSystemUiVisibility());
        }

        public void setWindowFlag(int i2) {
            this.mWindow.addFlags(i2);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void show(int i2) {
            for (int i3 = 1; i3 <= 512; i3 <<= 1) {
                if ((i2 & i3) != 0) {
                    showForType(i3);
                }
            }
        }

        public void unsetSystemUiFlag(int i2) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility((~i2) & decorView.getSystemUiVisibility());
        }

        public void unsetWindowFlag(int i2) {
            this.mWindow.clearFlags(i2);
        }
    }

    @RequiresApi(23)
    public static class Impl23 extends Impl20 {
        public Impl23(Window window, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            super(window, softwareKeyboardControllerCompat);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightStatusBars() {
            return (this.mWindow.getDecorView().getSystemUiVisibility() & 8192) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightStatusBars(boolean z2) {
            if (!z2) {
                unsetSystemUiFlag(8192);
                return;
            }
            unsetWindowFlag(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
            setWindowFlag(Integer.MIN_VALUE);
            setSystemUiFlag(8192);
        }
    }

    @RequiresApi(26)
    public static class Impl26 extends Impl23 {
        public Impl26(Window window, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            super(window, softwareKeyboardControllerCompat);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightNavigationBars() {
            return (this.mWindow.getDecorView().getSystemUiVisibility() & 16) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightNavigationBars(boolean z2) {
            if (!z2) {
                unsetSystemUiFlag(16);
                return;
            }
            unsetWindowFlag(134217728);
            setWindowFlag(Integer.MIN_VALUE);
            setSystemUiFlag(16);
        }
    }

    @RequiresApi(31)
    public static class Impl31 extends Impl30 {
        public Impl31(Window window, WindowInsetsControllerCompat windowInsetsControllerCompat, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            super(window, windowInsetsControllerCompat, softwareKeyboardControllerCompat);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl30, androidx.core.view.WindowInsetsControllerCompat.Impl
        @SuppressLint({"WrongConstant"})
        public int getSystemBarsBehavior() {
            return this.mInsetsController.getSystemBarsBehavior();
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl30, androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setSystemBarsBehavior(int i2) {
            this.mInsetsController.setSystemBarsBehavior(i2);
        }

        public Impl31(WindowInsetsController windowInsetsController, WindowInsetsControllerCompat windowInsetsControllerCompat, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            super(windowInsetsController, windowInsetsControllerCompat, softwareKeyboardControllerCompat);
        }
    }

    @RequiresApi(35)
    public static class Impl35 extends Impl31 {
        public Impl35(Window window, WindowInsetsControllerCompat windowInsetsControllerCompat, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            super(window, windowInsetsControllerCompat, softwareKeyboardControllerCompat);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl30, androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightNavigationBars() {
            return (this.mInsetsController.getSystemBarsAppearance() & 16) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl30, androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightStatusBars() {
            return (this.mInsetsController.getSystemBarsAppearance() & 8) != 0;
        }

        public Impl35(WindowInsetsController windowInsetsController, WindowInsetsControllerCompat windowInsetsControllerCompat, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            super(windowInsetsController, windowInsetsControllerCompat, softwareKeyboardControllerCompat);
        }
    }

    public interface OnControllableInsetsChangedListener {
        void onControllableInsetsChanged(WindowInsetsControllerCompat windowInsetsControllerCompat, int i2);
    }

    @RequiresApi(30)
    @Deprecated
    private WindowInsetsControllerCompat(WindowInsetsController windowInsetsController) {
        if (Build.VERSION.SDK_INT >= 35) {
            this.mImpl = new Impl35(windowInsetsController, this, new SoftwareKeyboardControllerCompat(windowInsetsController));
        } else {
            this.mImpl = new Impl30(windowInsetsController, this, new SoftwareKeyboardControllerCompat(windowInsetsController));
        }
    }

    @RequiresApi(30)
    @Deprecated
    public static WindowInsetsControllerCompat toWindowInsetsControllerCompat(WindowInsetsController windowInsetsController) {
        return new WindowInsetsControllerCompat(windowInsetsController);
    }

    public void addOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        this.mImpl.addOnControllableInsetsChangedListener(onControllableInsetsChangedListener);
    }

    public void controlWindowInsetsAnimation(int i2, long j2, Interpolator interpolator, CancellationSignal cancellationSignal, WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
        this.mImpl.controlWindowInsetsAnimation(i2, j2, interpolator, cancellationSignal, windowInsetsAnimationControlListenerCompat);
    }

    @SuppressLint({"WrongConstant"})
    public int getSystemBarsBehavior() {
        return this.mImpl.getSystemBarsBehavior();
    }

    public void hide(int i2) {
        this.mImpl.hide(i2);
    }

    public boolean isAppearanceLightNavigationBars() {
        return this.mImpl.isAppearanceLightNavigationBars();
    }

    public boolean isAppearanceLightStatusBars() {
        return this.mImpl.isAppearanceLightStatusBars();
    }

    public void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
        this.mImpl.removeOnControllableInsetsChangedListener(onControllableInsetsChangedListener);
    }

    public void setAppearanceLightNavigationBars(boolean z2) {
        this.mImpl.setAppearanceLightNavigationBars(z2);
    }

    public void setAppearanceLightStatusBars(boolean z2) {
        this.mImpl.setAppearanceLightStatusBars(z2);
    }

    public void setSystemBarsBehavior(int i2) {
        this.mImpl.setSystemBarsBehavior(i2);
    }

    public void show(int i2) {
        this.mImpl.show(i2);
    }

    @RequiresApi(30)
    public static class Impl30 extends Impl {
        final WindowInsetsControllerCompat mCompatController;
        final WindowInsetsController mInsetsController;
        private final SimpleArrayMap<OnControllableInsetsChangedListener, WindowInsetsController.OnControllableInsetsChangedListener> mListeners;
        final SoftwareKeyboardControllerCompat mSoftwareKeyboardControllerCompat;
        protected Window mWindow;

        public Impl30(Window window, WindowInsetsControllerCompat windowInsetsControllerCompat, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            this(window.getInsetsController(), windowInsetsControllerCompat, softwareKeyboardControllerCompat);
            this.mWindow = window;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$addOnControllableInsetsChangedListener$0(OnControllableInsetsChangedListener onControllableInsetsChangedListener, WindowInsetsController windowInsetsController, int i2) {
            if (this.mInsetsController == windowInsetsController) {
                onControllableInsetsChangedListener.onControllableInsetsChanged(this.mCompatController, i2);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void addOnControllableInsetsChangedListener(final OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
            if (this.mListeners.containsKey(onControllableInsetsChangedListener)) {
                return;
            }
            WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListener2 = new WindowInsetsController.OnControllableInsetsChangedListener() { // from class: androidx.core.view.o
                @Override // android.view.WindowInsetsController.OnControllableInsetsChangedListener
                public final void onControllableInsetsChanged(WindowInsetsController windowInsetsController, int i2) {
                    this.f1236a.lambda$addOnControllableInsetsChangedListener$0(onControllableInsetsChangedListener, windowInsetsController, i2);
                }
            };
            this.mListeners.put(onControllableInsetsChangedListener, onControllableInsetsChangedListener2);
            this.mInsetsController.addOnControllableInsetsChangedListener(onControllableInsetsChangedListener2);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void controlWindowInsetsAnimation(int i2, long j2, Interpolator interpolator, CancellationSignal cancellationSignal, final WindowInsetsAnimationControlListenerCompat windowInsetsAnimationControlListenerCompat) {
            this.mInsetsController.controlWindowInsetsAnimation(i2, j2, interpolator, cancellationSignal, new WindowInsetsAnimationControlListener() { // from class: androidx.core.view.WindowInsetsControllerCompat.Impl30.1
                private WindowInsetsAnimationControllerCompat mCompatAnimController = null;

                @Override // android.view.WindowInsetsAnimationControlListener
                public void onCancelled(WindowInsetsAnimationController windowInsetsAnimationController) {
                    windowInsetsAnimationControlListenerCompat.onCancelled(windowInsetsAnimationController == null ? null : this.mCompatAnimController);
                }

                @Override // android.view.WindowInsetsAnimationControlListener
                public void onFinished(WindowInsetsAnimationController windowInsetsAnimationController) {
                    windowInsetsAnimationControlListenerCompat.onFinished(this.mCompatAnimController);
                }

                @Override // android.view.WindowInsetsAnimationControlListener
                public void onReady(WindowInsetsAnimationController windowInsetsAnimationController, int i3) {
                    WindowInsetsAnimationControllerCompat windowInsetsAnimationControllerCompat = new WindowInsetsAnimationControllerCompat(windowInsetsAnimationController);
                    this.mCompatAnimController = windowInsetsAnimationControllerCompat;
                    windowInsetsAnimationControlListenerCompat.onReady(windowInsetsAnimationControllerCompat, i3);
                }
            });
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        @SuppressLint({"WrongConstant"})
        public int getSystemBarsBehavior() {
            Window window = this.mWindow;
            if (window == null) {
                return this.mInsetsController.getSystemBarsBehavior();
            }
            Object tag = window.getDecorView().getTag(356039078);
            if (tag != null) {
                return ((Integer) tag).intValue();
            }
            return 1;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void hide(int i2) {
            if ((i2 & 8) != 0) {
                this.mSoftwareKeyboardControllerCompat.hide();
            }
            this.mInsetsController.hide(i2 & (-9));
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightNavigationBars() {
            this.mInsetsController.setSystemBarsAppearance(0, 0);
            return (this.mInsetsController.getSystemBarsAppearance() & 16) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightStatusBars() {
            this.mInsetsController.setSystemBarsAppearance(0, 0);
            return (this.mInsetsController.getSystemBarsAppearance() & 8) != 0;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void removeOnControllableInsetsChangedListener(OnControllableInsetsChangedListener onControllableInsetsChangedListener) {
            WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListenerRemove = this.mListeners.remove(onControllableInsetsChangedListener);
            if (onControllableInsetsChangedListenerRemove != null) {
                this.mInsetsController.removeOnControllableInsetsChangedListener(onControllableInsetsChangedListenerRemove);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightNavigationBars(boolean z2) {
            if (z2) {
                if (this.mWindow != null) {
                    setSystemUiFlag(16);
                }
                this.mInsetsController.setSystemBarsAppearance(16, 16);
            } else {
                if (this.mWindow != null) {
                    unsetSystemUiFlag(16);
                }
                this.mInsetsController.setSystemBarsAppearance(0, 16);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightStatusBars(boolean z2) {
            if (z2) {
                if (this.mWindow != null) {
                    setSystemUiFlag(8192);
                }
                this.mInsetsController.setSystemBarsAppearance(8, 8);
            } else {
                if (this.mWindow != null) {
                    unsetSystemUiFlag(8192);
                }
                this.mInsetsController.setSystemBarsAppearance(0, 8);
            }
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setSystemBarsBehavior(int i2) {
            Window window = this.mWindow;
            if (window == null) {
                this.mInsetsController.setSystemBarsBehavior(i2);
                return;
            }
            window.getDecorView().setTag(356039078, Integer.valueOf(i2));
            if (i2 == 0) {
                unsetSystemUiFlag(6144);
                return;
            }
            if (i2 == 1) {
                unsetSystemUiFlag(4096);
                setSystemUiFlag(2048);
            } else {
                if (i2 != 2) {
                    return;
                }
                unsetSystemUiFlag(2048);
                setSystemUiFlag(4096);
            }
        }

        public void setSystemUiFlag(int i2) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility(i2 | decorView.getSystemUiVisibility());
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void show(int i2) {
            if ((i2 & 8) != 0) {
                this.mSoftwareKeyboardControllerCompat.show();
            }
            this.mInsetsController.show(i2 & (-9));
        }

        public void unsetSystemUiFlag(int i2) {
            View decorView = this.mWindow.getDecorView();
            decorView.setSystemUiVisibility((~i2) & decorView.getSystemUiVisibility());
        }

        public Impl30(WindowInsetsController windowInsetsController, WindowInsetsControllerCompat windowInsetsControllerCompat, SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat) {
            this.mListeners = new SimpleArrayMap<>();
            this.mInsetsController = windowInsetsController;
            this.mCompatController = windowInsetsControllerCompat;
            this.mSoftwareKeyboardControllerCompat = softwareKeyboardControllerCompat;
        }
    }

    public WindowInsetsControllerCompat(Window window, View view) {
        SoftwareKeyboardControllerCompat softwareKeyboardControllerCompat = new SoftwareKeyboardControllerCompat(view);
        if (Build.VERSION.SDK_INT >= 35) {
            this.mImpl = new Impl35(window, this, softwareKeyboardControllerCompat);
        } else {
            this.mImpl = new Impl30(window, this, softwareKeyboardControllerCompat);
        }
    }
}
