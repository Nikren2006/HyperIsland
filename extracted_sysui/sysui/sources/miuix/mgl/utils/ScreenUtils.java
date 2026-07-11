package miuix.mgl.utils;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public class ScreenUtils {
    public static final float INCHES_TO_CM = 0.3937f;

    private ScreenUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static int getScreenDPCM(Context context) {
        return (int) (getScreenDPI(context) * 0.3937f);
    }

    public static int getScreenDPI(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }
}
