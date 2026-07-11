package com.android.systemui.miui.globalactions;

import android.app.KeyguardManager;
import android.content.Context;
import android.provider.Settings;

/* JADX INFO: loaded from: classes2.dex */
public class ShutDownPasswordUtils {
    private static final int DISABLE = 0;
    private static final int ENABLE = 1;
    private static final String SHUT_DOWN_PASSWORD_ENABLE = "miui_shut_down_password_enabled";

    private static boolean getKeyguardLocked(Context context) {
        return ((KeyguardManager) context.getSystemService("keyguard")).isDeviceLocked(-2);
    }

    public static boolean isShutDownPasswordEnabled(Context context) {
        return getKeyguardLocked(context) && Settings.Secure.getIntForUser(context.getContentResolver(), SHUT_DOWN_PASSWORD_ENABLE, 0, -2) == 1;
    }
}
