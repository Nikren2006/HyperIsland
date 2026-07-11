package com.miui.maml.util;

import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import miui.systemui.util.FoldUtils;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUtils {
    private static final float DEFAULT_RESIZE_SCALE_FOLD = 0.5f;
    private static final float DEFAULT_RESIZE_SCALE_PAD = 0.383f;

    private DeviceUtils() {
    }

    public static int getDeviceLevel() {
        if (isLiteV1Covered()) {
            return 0;
        }
        return miuix.device.DeviceUtils.getDeviceLevel();
    }

    private static int getPhysicalScreenSmallWidthDip(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return (int) (Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) / displayMetrics.density);
    }

    private static float getSpecialPadRatio(String str) {
        if (isSpecital(str)) {
            return 0.766f;
        }
        return DEFAULT_RESIZE_SCALE_PAD;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int[] getTargetScreenWH(android.view.WindowManager r7, float r8, java.lang.String r9) {
        /*
            if (r7 == 0) goto L60
            android.view.Display r0 = r7.getDefaultDisplay()
            android.graphics.Point r1 = new android.graphics.Point
            r1.<init>()
            r0.getRealSize(r1)
            int r0 = r0.getRotation()
            r2 = 1
            if (r0 == r2) goto L1a
            r3 = 3
            if (r0 != r3) goto L19
            goto L1a
        L19:
            r2 = 0
        L1a:
            boolean r7 = needScaleDevice(r7)
            if (r7 != 0) goto L25
            r7 = 0
            int r7 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r7 <= 0) goto L47
        L25:
            float r7 = java.lang.Math.abs(r8)
            double r3 = (double) r7
            r5 = 4517329193108106637(0x3eb0c6f7a0b5ed8d, double:1.0E-6)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 > 0) goto L49
            boolean r7 = isPad()
            if (r7 == 0) goto L3e
            float r8 = getSpecialPadRatio(r9)
            goto L49
        L3e:
            boolean r7 = isFold()
            if (r7 == 0) goto L47
            r8 = 1056964608(0x3f000000, float:0.5)
            goto L49
        L47:
            r8 = 1065353216(0x3f800000, float:1.0)
        L49:
            if (r2 == 0) goto L50
            int r7 = r1.y
        L4d:
            float r7 = (float) r7
            float r7 = r7 * r8
            goto L53
        L50:
            int r7 = r1.x
            goto L4d
        L53:
            int r7 = (int) r7
            if (r2 == 0) goto L59
            int r8 = r1.x
            goto L5b
        L59:
            int r8 = r1.y
        L5b:
            int[] r7 = new int[]{r7, r8}
            return r7
        L60:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.String r8 = "wm is null"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.maml.util.DeviceUtils.getTargetScreenWH(android.view.WindowManager, float, java.lang.String):int[]");
    }

    public static boolean isFold() {
        return SystemProperties.getInt(FoldUtils.MUILT_DISPLAY_TYPE, 0) == 2;
    }

    private static boolean isLiteV1Covered() {
        return isMiuiLite() || isMiuiMiddle();
    }

    private static boolean isMiuiLite() {
        return miuix.device.DeviceUtils.isMiuiLiteRom();
    }

    private static boolean isMiuiMiddle() {
        return miuix.device.DeviceUtils.isMiuiMiddle();
    }

    public static boolean isPad() {
        return SystemProperties.get("ro.build.characteristics").contains("tablet");
    }

    private static boolean isSpecital(String str) {
        return !TextUtils.isEmpty(str) && ("1x2".equals(str) || "2x1".equals(str));
    }

    private static boolean needScaleDevice(WindowManager windowManager) {
        return isPad() || (isFold() && getPhysicalScreenSmallWidthDip(windowManager) >= 600);
    }
}
