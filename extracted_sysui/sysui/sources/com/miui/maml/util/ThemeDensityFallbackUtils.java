package com.miui.maml.util;

import android.content.res.Configuration;
import android.content.res.Resources;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import miuix.appcompat.app.DialogContract;
import miuix.responsive.ResponsivePolicy;

/* JADX INFO: loaded from: classes2.dex */
public class ThemeDensityFallbackUtils {
    private static final int DENSITY_NONE = 1;
    private static final int[] DENSITIES = {ResponsivePolicy.THRESHOLD_REGULAR_WINDOW, DialogContract.BUTTON_SCROLL_WINDOW_HEIGHT_LIMIT_DP, 320, 240, 0};
    private static final int[] ALL_SUPPORT_DENSITY = {ResponsivePolicy.THRESHOLD_REGULAR_WINDOW, DialogContract.BUTTON_SCROLL_WINDOW_HEIGHT_LIMIT_DP, 320, 440, 240, 160, 120};

    private ThemeDensityFallbackUtils() {
    }

    public static String findNearestDir(String str, final int i2) {
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        while (true) {
            int[] iArr = ALL_SUPPORT_DENSITY;
            if (i3 >= iArr.length) {
                break;
            }
            arrayList.add(Integer.valueOf(iArr[i3]));
            i3++;
        }
        Collections.sort(arrayList, new Comparator<Integer>() { // from class: com.miui.maml.util.ThemeDensityFallbackUtils.1
            @Override // java.util.Comparator
            public int compare(Integer num, Integer num2) {
                return Math.abs(num.intValue() - i2) - Math.abs(num2.intValue() - i2);
            }
        });
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            String str2 = "drawable" + getScreenWidthSuffix(Resources.getSystem().getConfiguration()) + getDensitySuffix(((Integer) arrayList.get(i4)).intValue());
            if (new File(str + str2).exists()) {
                return str2;
            }
        }
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            String str3 = "drawable" + getDensitySuffix(((Integer) arrayList.get(i5)).intValue());
            if (new File(str + str3).exists()) {
                return str3;
            }
        }
        if (new File(str + "drawable").exists()) {
            return "drawable";
        }
        return null;
    }

    public static int findNearestSupportDensity(int i2) {
        int i3 = 440;
        int i4 = Integer.MAX_VALUE;
        int i5 = 0;
        while (true) {
            int[] iArr = ALL_SUPPORT_DENSITY;
            if (i5 >= iArr.length) {
                return i3;
            }
            int iAbs = Math.abs(i2 - iArr[i5]);
            if (iAbs < i4) {
                i3 = iArr[i5];
                i4 = iAbs;
            }
            i5++;
        }
    }

    public static String getDensitySuffix(int i2) {
        if (i2 == 0) {
            return "";
        }
        if (i2 == 1) {
            return "-nodpi";
        }
        if (i2 == 120) {
            return "-ldpi";
        }
        if (i2 == 160) {
            return "-mdpi";
        }
        if (i2 == 240) {
            return "-hdpi";
        }
        if (i2 == 320) {
            return "-xhdpi";
        }
        if (i2 == 440) {
            return "-nxhdpi";
        }
        if (i2 == 480) {
            return "-xxhdpi";
        }
        if (i2 == 640) {
            return "-xxxhdpi";
        }
        int length = DENSITIES.length;
        int i3 = length - 1;
        for (int i4 = length - 2; i4 >= 0; i4--) {
            int[] iArr = DENSITIES;
            if (Math.abs(iArr[i4] - i2) <= Math.abs(iArr[i3] - i2)) {
                i3 = i4;
            }
        }
        return getDensitySuffix(DENSITIES[i3]);
    }

    public static String getScreenWidthSuffix(Configuration configuration) {
        return configuration.smallestScreenWidthDp >= 720 ? "-sw720dp" : "";
    }
}
