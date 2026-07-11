package z0;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class i {
    public static String a(String str) {
        return (TextUtils.isEmpty(str) || str.length() <= 5) ? str : str.substring(str.length() - 5);
    }

    public static boolean b(String str) {
        return "LocalSpeaker".equals(str);
    }

    public static String c(String str) {
        return (str == null || str.length() <= 6) ? str : str.substring(0, 6);
    }
}
