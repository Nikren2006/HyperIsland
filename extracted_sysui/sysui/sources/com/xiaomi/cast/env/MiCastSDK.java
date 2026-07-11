package com.xiaomi.cast.env;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.xiaomi.cast.api.IMiCastSDK;
import miui.systemui.util.SmartDeviceUtils;

/* JADX INFO: loaded from: classes2.dex */
public class MiCastSDK {

    public static class Holder {
        private static volatile IMiCastSDK instance;

        private Holder() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void initialize() {
            instance = new MiCastSDKImpl();
        }
    }

    public static IMiCastSDK getInstance() {
        if (Holder.instance == null) {
            synchronized (MiCastSDK.class) {
                try {
                    if (Holder.instance == null) {
                        Holder.initialize();
                    }
                } finally {
                }
            }
        }
        return Holder.instance;
    }

    public static boolean isEnable(Context context) {
        if (context == null) {
            return false;
        }
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(SmartDeviceUtils.MI_LINK_PACKAGE_NAME, 128).metaData;
            if (bundle == null) {
                return false;
            }
            Object obj = bundle.get("com.xiaomi.googlecast.enable");
            if (obj instanceof String) {
                if ("enable".equals(obj.toString())) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        return false;
    }
}
