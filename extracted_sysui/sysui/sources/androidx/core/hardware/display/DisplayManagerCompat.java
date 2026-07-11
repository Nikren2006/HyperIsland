package androidx.core.hardware.display;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class DisplayManagerCompat {

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @VisibleForTesting
    static final String DISPLAY_CATEGORY_ALL = "android.hardware.display.category.ALL_INCLUDING_DISABLED";

    @ExperimentalDisplayApi
    public static final String DISPLAY_CATEGORY_BUILT_IN_DISPLAYS = "android.hardware.display.category.BUILT_IN_DISPLAYS";
    public static final String DISPLAY_CATEGORY_PRESENTATION = "android.hardware.display.category.PRESENTATION";

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @VisibleForTesting
    static final int DISPLAY_TYPE_INTERNAL = 1;
    private final Context mContext;

    private DisplayManagerCompat(Context context) {
        this.mContext = context;
    }

    private static Display[] computeBuiltInDisplays(DisplayManager displayManager) {
        Display[] displays = displayManager.getDisplays(DISPLAY_CATEGORY_ALL);
        Display[] displayArr = new Display[numberOfDisplaysByType(1, displays)];
        int i2 = 0;
        for (Display display : displays) {
            if (1 == getTypeCompat(display)) {
                displayArr[i2] = display;
                i2++;
            }
        }
        return displayArr;
    }

    public static DisplayManagerCompat getInstance(Context context) {
        return new DisplayManagerCompat(context);
    }

    @SuppressLint({"BanUncheckedReflection"})
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @VisibleForTesting
    public static int getTypeCompat(Display display) {
        try {
            Object objInvoke = Display.class.getMethod("getType", null).invoke(display, null);
            Objects.requireNonNull(objInvoke);
            return ((Integer) objInvoke).intValue();
        } catch (NoSuchMethodException unused) {
            return 0;
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    private static int numberOfDisplaysByType(int i2, Display[] displayArr) {
        int i3 = 0;
        for (Display display : displayArr) {
            if (i2 == getTypeCompat(display)) {
                i3++;
            }
        }
        return i3;
    }

    public Display getDisplay(int i2) {
        return ((DisplayManager) this.mContext.getSystemService("display")).getDisplay(i2);
    }

    public Display[] getDisplays() {
        return ((DisplayManager) this.mContext.getSystemService("display")).getDisplays();
    }

    public Display[] getDisplays(String str) {
        return DISPLAY_CATEGORY_BUILT_IN_DISPLAYS.equals(str) ? computeBuiltInDisplays((DisplayManager) this.mContext.getSystemService("display")) : ((DisplayManager) this.mContext.getSystemService("display")).getDisplays(str);
    }
}
