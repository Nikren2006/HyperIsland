package z0;

import android.text.TextUtils;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Class f7134a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f7135b;

    static {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            f7134a = cls;
            f7135b = cls.getDeclaredMethod("get", String.class, String.class);
        } catch (Exception e2) {
            e.b("MarketSdkUtils", e2.getMessage(), e2);
        }
    }

    public static String a(String str, String str2) {
        try {
            String str3 = (String) f7135b.invoke(f7134a, str, str2);
            return !TextUtils.isEmpty(str3) ? str3 : str2;
        } catch (Exception e2) {
            e.b("MarketSdkUtils", e2.getMessage(), e2);
            return str2;
        }
    }
}
