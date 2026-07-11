package miui.systemui.util;

import android.os.SystemProperties;

/* JADX INFO: loaded from: classes4.dex */
public final class Constant {
    public static final String ACTION_SHOW_UNLOCK_SCREEN = "xiaomi.intent.action.SHOW_SECURE_KEYGUARD";
    public static final Constant INSTANCE = new Constant();
    private static final boolean DEBUG = SystemProperties.getBoolean("debug.sysui", false);

    private Constant() {
    }

    public final boolean getDEBUG() {
        return DEBUG;
    }
}
