package miui.systemui.controls;

import android.graphics.Color;

/* JADX INFO: loaded from: classes.dex */
public final class ColorUtils {
    public static final ColorUtils INSTANCE = new ColorUtils();

    private ColorUtils() {
    }

    public final int blendARGB(int i2, int i3, float f2) {
        float f3 = 1 - f2;
        return Color.argb((int) ((Color.alpha(i2) * f3) + (Color.alpha(i3) * f2)), (int) ((Color.red(i2) * f3) + (Color.red(i3) * f2)), (int) ((Color.green(i2) * f3) + (Color.green(i3) * f2)), (int) ((Color.blue(i2) * f3) + (Color.blue(i3) * f2)));
    }
}
