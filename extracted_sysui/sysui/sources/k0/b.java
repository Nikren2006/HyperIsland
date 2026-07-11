package k0;

import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f4946a;

    static {
        String name = b.class.getName();
        f4946a = name.substring(0, name.lastIndexOf(".") + 1);
    }

    public static void a(String str, String str2) {
        c(str, false, str2, null);
    }

    public static void b(String str, String str2, Throwable th) {
        c(str, false, str2, th);
    }

    public static void c(String str, boolean z2, String str2, Throwable th) {
        if (th != null) {
            str2 = str2 + th.toString();
        }
        g(6, str, z2, str2);
    }

    public static String d() {
        if (!AbstractC0426a.c()) {
            return "Cir_";
        }
        AbstractC0426a.b();
        throw null;
    }

    public static void e(String str, String str2) {
        f(str, false, str2);
    }

    public static void f(String str, boolean z2, String str2) {
        g(4, str, z2, str2);
    }

    public static void g(int i2, String str, boolean z2, String str2) {
        if (AbstractC0426a.c()) {
            AbstractC0426a.b();
            throw null;
        }
        Log.println(i2, h(str), str2);
    }

    public static String h(String str) {
        if (TextUtils.equals(d(), str)) {
            return str;
        }
        return d() + str;
    }

    public static void i(String str, String str2) {
        j(str, false, str2);
    }

    public static void j(String str, boolean z2, String str2) {
        g(5, str, z2, str2);
    }
}
