package m0;

import android.os.Build;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public abstract class F extends Build {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f5278a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f5279b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f5280c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f5281d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f5282e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static String f5283f;

    static {
        Class<?> cls = null;
        try {
            cls = Class.forName("miui.os.Build");
            f5282e = true;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (cls != null) {
            f5278a = a(cls, "IS_INTERNATIONAL_BUILD", f5278a);
            f5279b = a(cls, "IS_TABLET", f5279b);
            f5280c = a(cls, "IS_DEVELOPMENT_VERSION", f5280c);
            f5281d = a(cls, "IS_STABLE_VERSION", f5281d);
        }
        f5283f = b("ro.product.marketname", Build.MODEL);
    }

    public static boolean a(Class cls, String str, boolean z2) {
        try {
            Field field = cls.getField(str);
            field.setAccessible(true);
            return field.getBoolean(null);
        } catch (Exception e2) {
            e2.printStackTrace();
            return z2;
        }
    }

    public static String b(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return str2;
        }
    }
}
