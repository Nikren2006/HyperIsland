package miuix.core.util.variable;

import android.view.View;
import android.view.Window;
import java.lang.reflect.Method;
import miuix.reflect.Reflects;

/* JADX INFO: loaded from: classes3.dex */
public class WindowWrapper {
    private static final int EXTRA_FLAG_STATUS_BAR_DARK_MODE = 16;
    private static final int EXTRA_FLAG_STATUS_BAR_TRANSPARENT = 1;
    private static final int EXTRA_FLAG_STATUS_BAR_TRANSPARENT_MASK = 17;
    private static final int FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS = Integer.MIN_VALUE;
    private static final int FLAG_TRANSLUCENT_STATUS = 67108864;
    protected static Method setExtraFlags;

    static {
        try {
            Class cls = Integer.TYPE;
            setExtraFlags = Reflects.getMethod((Class<?>) Window.class, "setExtraFlags", (Class<?>[]) new Class[]{cls, cls});
        } catch (Exception unused) {
            setExtraFlags = null;
        }
    }

    public static boolean setTranslucentStatus(Window window, int i2) {
        boolean z2 = false;
        if (setExtraFlags == null) {
            return false;
        }
        if (i2 == 0) {
            window.clearFlags(Integer.MIN_VALUE);
        } else {
            window.addFlags(Integer.MIN_VALUE);
            View decorView = window.getDecorView();
            if (i2 == 1) {
                decorView.setSystemUiVisibility(8208);
            } else {
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (-8209));
            }
        }
        if (i2 == 0) {
            window.clearFlags(67108864);
        } else {
            window.setFlags(67108864, 67108864);
        }
        try {
            if (i2 == 0) {
                setExtraFlags.invoke(window, 0, 17);
            } else {
                setExtraFlags.invoke(window, Integer.valueOf(i2 == 1 ? 17 : 1), 17);
            }
            z2 = true;
            return true;
        } catch (Exception unused) {
            return z2;
        }
    }
}
