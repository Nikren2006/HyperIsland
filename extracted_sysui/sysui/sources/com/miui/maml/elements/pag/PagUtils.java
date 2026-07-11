package com.miui.maml.elements.pag;

import android.text.TextUtils;
import com.miui.maml.util.SystemProperties;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class PagUtils {
    public static final int HYPER_OS_2_VERSION_CODE = 2;
    public static final File PAG_LIB_FILE = new File("/system_ext/framework/pag-hyperos-release.jar");
    public static final int HYPER_OS_VERSION_CODE = getHyperOsVersionCode();

    public static int ProgressToFrame(double d2, int i2) {
        if (i2 <= 1) {
            return 0;
        }
        double dFmod = fmod(d2, 1.0d);
        if (dFmod <= 0.0d && d2 != 0.0d) {
            dFmod += 1.0d;
        }
        int iFloor = (int) Math.floor(dFmod * ((double) i2));
        return iFloor == i2 ? i2 - 1 : iFloor;
    }

    private static double fmod(double d2, double d3) {
        return d2 - (((double) ((int) Math.floor(d2 / d3))) * d3);
    }

    public static int getHyperOsVersionCode() {
        String str = SystemProperties.get("ro.mi.os.version.code", "");
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.parseInt(str);
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    public static boolean supportPagFeature() {
        return HYPER_OS_VERSION_CODE >= 2 && PAG_LIB_FILE.exists();
    }
}
