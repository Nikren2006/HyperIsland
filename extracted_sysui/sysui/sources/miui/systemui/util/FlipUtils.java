package miui.systemui.util;

import android.content.Context;
import android.view.Display;
import android.view.DisplayCutout;

/* JADX INFO: loaded from: classes4.dex */
public final class FlipUtils {
    public static final FlipUtils INSTANCE = new FlipUtils();
    private static int flipOutInsetLeft;
    private static int flipOutInsetRight;
    private static boolean isFlip;
    private static boolean isFlipTiny;
    private static boolean isFlipTinyLand;

    private FlipUtils() {
    }

    public static final boolean isCutoutBottom(Display display) {
        DisplayCutout cutout;
        return ((display == null || (cutout = display.getCutout()) == null) ? 0 : cutout.getSafeInsetBottom()) > 0;
    }

    public static final boolean isCutoutLeft(Display display) {
        DisplayCutout cutout;
        return ((display == null || (cutout = display.getCutout()) == null) ? 0 : cutout.getSafeInsetLeft()) > 0;
    }

    public static final boolean isCutoutRight(Display display) {
        DisplayCutout cutout;
        return ((display == null || (cutout = display.getCutout()) == null) ? 0 : cutout.getSafeInsetRight()) > 0;
    }

    public static final boolean isCutoutTop(Display display) {
        DisplayCutout cutout;
        return ((display == null || (cutout = display.getCutout()) == null) ? 0 : cutout.getSafeInsetTop()) > 0;
    }

    public static final boolean isFlip() {
        return isFlip;
    }

    public static /* synthetic */ void isFlip$annotations() {
    }

    public static final boolean isFlipTiny() {
        return isFlipTiny;
    }

    public static /* synthetic */ void isFlipTiny$annotations() {
    }

    public static final boolean isFlipTinyLand() {
        return isFlipTinyLand;
    }

    public static /* synthetic */ void isFlipTinyLand$annotations() {
    }

    public static final void setFlip(boolean z2) {
        isFlip = z2;
    }

    private static final void setFlipTiny(boolean z2) {
        isFlipTiny = isFlip && z2;
    }

    private static final void setFlipTinyLand(boolean z2) {
        isFlipTinyLand = isFlipTiny && z2;
    }

    public static final boolean updateFlipTiny(boolean z2) {
        if (isFlip && z2 != isFlipTiny) {
            setFlipTiny(z2);
        }
        return isFlipTiny;
    }

    public static final boolean updateFlipTinyLand(Context context) {
        if (isFlipTiny && context != null) {
            Display display = context.getDisplay();
            Integer numValueOf = display != null ? Integer.valueOf(display.getRotation()) : null;
            boolean z2 = true;
            if ((numValueOf == null || numValueOf.intValue() != 1) && (numValueOf == null || numValueOf.intValue() != 3)) {
                z2 = false;
            }
            setFlipTinyLand(z2);
        }
        return isFlipTinyLand;
    }

    public final int getFlipOutInsetLeft() {
        return flipOutInsetLeft;
    }

    public final int getFlipOutInsetRight() {
        return flipOutInsetRight;
    }

    public final void setFlipOutInsetLeft(int i2) {
        flipOutInsetLeft = i2;
    }

    public final void setFlipOutInsetRight(int i2) {
        flipOutInsetRight = i2;
    }

    public final void updateFlipOutInsetRight(Context context) {
        DisplayCutout cutout;
        DisplayCutout cutout2;
        kotlin.jvm.internal.n.g(context, "context");
        int safeInsetRight = 0;
        if (!CommonUtils.isFlipDevice() || !CommonUtils.isTinyScreen(context)) {
            flipOutInsetLeft = 0;
            flipOutInsetRight = 0;
            return;
        }
        Display display = context.getDisplay();
        flipOutInsetLeft = (display == null || (cutout2 = display.getCutout()) == null) ? 0 : cutout2.getSafeInsetLeft();
        Display display2 = context.getDisplay();
        if (display2 != null && (cutout = display2.getCutout()) != null) {
            safeInsetRight = cutout.getSafeInsetRight();
        }
        flipOutInsetRight = safeInsetRight;
    }
}
