package miuix.autodensity;

import android.os.Build;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes3.dex */
public class AutoDensityPolicy {
    public static final float ACCESSIBILITY_ZOOM_BIG = 1.05f;
    public static final float ACCESSIBILITY_ZOOM_SMALL;
    public static final float STANDARD_DPI = 440.0f;
    public static final float STANDARD_PPI = 386.0f;
    public static final float STANDARD_SCALE = 1.1398964f;

    static {
        ACCESSIBILITY_ZOOM_SMALL = TextUtils.equals(Build.DEVICE, "zizhan") ? 0.85f : 0.8f;
    }

    public static float calcPadScale(float f2) {
        return Math.max(1.0f, Math.min((f2 / 9.3f) * 1.06f, 1.15f));
    }

    public static float calcPhoneRearScale(float f2) {
        return 0.94f;
    }

    public static float calcPhoneScale(float f2) {
        return Math.min(1.0f, f2 / 2.8f);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static double getDeviceScale(android.content.Context r2, float r3, float r4, boolean r5) {
        /*
            boolean r0 = miuix.autodensity.SkuScale.hasSkuScale()
            if (r0 == 0) goto Lc
            float r2 = miuix.autodensity.SkuScale.getSkuScale(r2)
        La:
            double r2 = (double) r2
            goto L3d
        Lc:
            boolean r2 = miuix.os.Build.IS_FOLDABLE
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r2 == 0) goto L23
            java.lang.String r2 = "cetus"
            java.lang.String r3 = android.os.Build.DEVICE
            boolean r2 = r2.contentEquals(r3)
            if (r2 == 0) goto L1e
        L1c:
            r2 = r0
            goto L3d
        L1e:
            float r2 = calcPhoneScale(r4)
            goto La
        L23:
            boolean r2 = miuix.os.Build.IS_TABLET
            if (r2 == 0) goto L2c
            float r2 = calcPadScale(r3)
            goto La
        L2c:
            boolean r2 = miuix.os.Build.IS_AUTOMOTIVE
            if (r2 == 0) goto L31
            goto L1c
        L31:
            if (r5 == 0) goto L38
            float r2 = calcPhoneRearScale(r3)
            goto La
        L38:
            float r2 = calcPhoneScale(r4)
            goto La
        L3d:
            boolean r4 = miuix.autodensity.DebugUtil.isEnableDebug()
            if (r4 == 0) goto L57
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "getDeviceScale "
            r4.append(r5)
            r4.append(r2)
            java.lang.String r4 = r4.toString()
            miuix.autodensity.DebugUtil.printDensityLog(r4)
        L57:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: miuix.autodensity.AutoDensityPolicy.getDeviceScale(android.content.Context, float, float, boolean):double");
    }
}
