package H;

import android.R;
import android.content.Context;
import android.view.Window;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c {
    public static void a(Window window, boolean z2, Integer num, Integer num2) {
        boolean z3 = num == null || num.intValue() == 0;
        boolean z4 = num2 == null || num2.intValue() == 0;
        if (z3 || z4) {
            int iB = C.a.b(window.getContext(), R.attr.colorBackground, ViewCompat.MEASURED_STATE_MASK);
            if (z3) {
                num = Integer.valueOf(iB);
            }
            if (z4) {
                num2 = Integer.valueOf(iB);
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, !z2);
        int iC = c(window.getContext(), z2);
        int iB2 = b(window.getContext(), z2);
        window.setStatusBarColor(iC);
        window.setNavigationBarColor(iB2);
        f(window, d(iC, C.a.h(num.intValue())));
        e(window, d(iB2, C.a.h(num2.intValue())));
    }

    public static int b(Context context, boolean z2) {
        if (z2) {
            return 0;
        }
        return C.a.b(context, R.attr.navigationBarColor, ViewCompat.MEASURED_STATE_MASK);
    }

    public static int c(Context context, boolean z2) {
        if (z2) {
            return 0;
        }
        return C.a.b(context, R.attr.statusBarColor, ViewCompat.MEASURED_STATE_MASK);
    }

    public static boolean d(int i2, boolean z2) {
        return C.a.h(i2) || (i2 == 0 && z2);
    }

    public static void e(Window window, boolean z2) {
        WindowCompat.getInsetsController(window, window.getDecorView()).setAppearanceLightNavigationBars(z2);
    }

    public static void f(Window window, boolean z2) {
        WindowCompat.getInsetsController(window, window.getDecorView()).setAppearanceLightStatusBars(z2);
    }
}
