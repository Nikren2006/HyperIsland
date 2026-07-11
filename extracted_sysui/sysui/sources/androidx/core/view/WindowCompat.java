package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.Window;
import androidx.annotation.IdRes;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: classes.dex */
public final class WindowCompat {
    public static final int FEATURE_ACTION_BAR = 8;
    public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;

    public static class Api16Impl {
        private Api16Impl() {
        }

        public static void setDecorFitsSystemWindows(Window window, boolean z2) {
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(z2 ? systemUiVisibility & (-1793) : systemUiVisibility | 1792);
        }
    }

    @RequiresApi(28)
    public static class Api28Impl {
        private Api28Impl() {
        }

        public static <T> T requireViewById(Window window, int i2) {
            return (T) window.requireViewById(i2);
        }
    }

    @RequiresApi(30)
    public static class Api30Impl {
        private Api30Impl() {
        }

        public static void setDecorFitsSystemWindows(Window window, boolean z2) {
            View decorView = window.getDecorView();
            int systemUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(z2 ? systemUiVisibility & (-257) : systemUiVisibility | 256);
            window.setDecorFitsSystemWindows(z2);
        }
    }

    @RequiresApi(35)
    public static class Api35Impl {
        private Api35Impl() {
        }

        public static void setDecorFitsSystemWindows(Window window, boolean z2) {
            window.setDecorFitsSystemWindows(z2);
        }
    }

    private WindowCompat() {
    }

    public static WindowInsetsControllerCompat getInsetsController(Window window, View view) {
        return new WindowInsetsControllerCompat(window, view);
    }

    public static <T extends View> T requireViewById(Window window, @IdRes int i2) {
        return (T) Api28Impl.requireViewById(window, i2);
    }

    public static void setDecorFitsSystemWindows(Window window, boolean z2) {
        if (Build.VERSION.SDK_INT >= 35) {
            Api35Impl.setDecorFitsSystemWindows(window, z2);
        } else {
            Api30Impl.setDecorFitsSystemWindows(window, z2);
        }
    }
}
