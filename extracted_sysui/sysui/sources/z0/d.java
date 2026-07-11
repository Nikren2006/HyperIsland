package z0;

import android.app.KeyguardManager;
import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d {
    public static boolean a(Context context) {
        return ((KeyguardManager) context.getSystemService("keyguard")).isKeyguardLocked();
    }
}
