package z0;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile boolean f7130a = Log.isLoggable("debugmiplay", 2);

    public static void a(String str, String str2) {
        if (f7130a) {
            Log.d(str, str2);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        Log.e(str, str2 + "\n" + Log.getStackTraceString(th));
    }

    public static void c(String str, String str2) {
        Log.i(str, str2);
    }

    public static void d(String str, String str2) {
        Log.w(str, str2);
    }
}
