package androidx.core.view;

import android.R;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.RequiresApi;
import androidx.core.view.SoftwareKeyboardControllerCompat;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class SoftwareKeyboardControllerCompat {
    private final Impl mImpl;

    public static class Impl {
        public void hide() {
        }

        public void show() {
        }
    }

    @RequiresApi(20)
    public static class Impl20 extends Impl {
        private final View mView;

        public Impl20(View view) {
            this.mView = view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$show$0(View view) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
        }

        @Override // androidx.core.view.SoftwareKeyboardControllerCompat.Impl
        public void hide() {
            View view = this.mView;
            if (view != null) {
                ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mView.getWindowToken(), 0);
            }
        }

        @Override // androidx.core.view.SoftwareKeyboardControllerCompat.Impl
        public void show() {
            final View viewFindViewById = this.mView;
            if (viewFindViewById == null) {
                return;
            }
            if (viewFindViewById.isInEditMode() || viewFindViewById.onCheckIsTextEditor()) {
                viewFindViewById.requestFocus();
            } else {
                viewFindViewById = viewFindViewById.getRootView().findFocus();
            }
            if (viewFindViewById == null) {
                viewFindViewById = this.mView.getRootView().findViewById(R.id.content);
            }
            if (viewFindViewById == null || !viewFindViewById.hasWindowFocus()) {
                return;
            }
            viewFindViewById.post(new Runnable() { // from class: androidx.core.view.h
                @Override // java.lang.Runnable
                public final void run() {
                    SoftwareKeyboardControllerCompat.Impl20.lambda$show$0(viewFindViewById);
                }
            });
        }
    }

    public SoftwareKeyboardControllerCompat(View view) {
        this.mImpl = new Impl30(view);
    }

    public void hide() {
        this.mImpl.hide();
    }

    public void show() {
        this.mImpl.show();
    }

    @RequiresApi(30)
    public static class Impl30 extends Impl20 {
        private View mView;
        private WindowInsetsController mWindowInsetsController;

        public Impl30(View view) {
            super(view);
            this.mView = view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$hide$0(AtomicBoolean atomicBoolean, WindowInsetsController windowInsetsController, int i2) {
            atomicBoolean.set((i2 & 8) != 0);
        }

        @Override // androidx.core.view.SoftwareKeyboardControllerCompat.Impl20, androidx.core.view.SoftwareKeyboardControllerCompat.Impl
        public void hide() {
            View view;
            WindowInsetsController windowInsetsController = this.mWindowInsetsController;
            if (windowInsetsController == null) {
                View view2 = this.mView;
                windowInsetsController = view2 != null ? view2.getWindowInsetsController() : null;
            }
            if (windowInsetsController == null) {
                super.hide();
                return;
            }
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListener = new WindowInsetsController.OnControllableInsetsChangedListener() { // from class: androidx.core.view.i
                @Override // android.view.WindowInsetsController.OnControllableInsetsChangedListener
                public final void onControllableInsetsChanged(WindowInsetsController windowInsetsController2, int i2) {
                    SoftwareKeyboardControllerCompat.Impl30.lambda$hide$0(atomicBoolean, windowInsetsController2, i2);
                }
            };
            windowInsetsController.addOnControllableInsetsChangedListener(onControllableInsetsChangedListener);
            if (!atomicBoolean.get() && (view = this.mView) != null) {
                ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mView.getWindowToken(), 0);
            }
            windowInsetsController.removeOnControllableInsetsChangedListener(onControllableInsetsChangedListener);
            windowInsetsController.hide(WindowInsets.Type.ime());
        }

        @Override // androidx.core.view.SoftwareKeyboardControllerCompat.Impl20, androidx.core.view.SoftwareKeyboardControllerCompat.Impl
        public void show() {
            View view = this.mView;
            WindowInsetsController windowInsetsController = this.mWindowInsetsController;
            if (windowInsetsController == null) {
                windowInsetsController = view != null ? view.getWindowInsetsController() : null;
            }
            if (windowInsetsController != null) {
                windowInsetsController.show(WindowInsets.Type.ime());
            }
            super.show();
        }

        public Impl30(WindowInsetsController windowInsetsController) {
            super(null);
            this.mWindowInsetsController = windowInsetsController;
        }
    }

    @RequiresApi(30)
    @Deprecated
    public SoftwareKeyboardControllerCompat(WindowInsetsController windowInsetsController) {
        this.mImpl = new Impl30(windowInsetsController);
    }
}
