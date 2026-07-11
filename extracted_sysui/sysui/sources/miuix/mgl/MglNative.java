package miuix.mgl;

/* JADX INFO: loaded from: classes3.dex */
public class MglNative {
    private MglNative() {
        throw new IllegalStateException("Utility class");
    }

    public static String getVersion() {
        return BuildConfig.SDK_VERSION;
    }

    public static void init() {
        System.loadLibrary("mglnative");
    }
}
