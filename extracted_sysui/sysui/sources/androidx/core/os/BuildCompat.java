package androidx.core.os;

import android.os.Build;
import android.os.ext.SdkExtensions;
import androidx.annotation.ChecksSdkIntAtLeast;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.exifinterface.media.ExifInterface;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;
import kotlin.jvm.internal.n;
import miuix.animation.controller.AnimState;

/* JADX INFO: loaded from: classes.dex */
public final class BuildCompat {

    @ChecksSdkIntAtLeast(extension = AnimState.VIEW_SIZE)
    public static final int AD_SERVICES_EXTENSION_INT;
    public static final BuildCompat INSTANCE = new BuildCompat();

    @ChecksSdkIntAtLeast(extension = 30)
    public static final int R_EXTENSION_INT;

    @ChecksSdkIntAtLeast(extension = 31)
    public static final int S_EXTENSION_INT;

    @ChecksSdkIntAtLeast(extension = 33)
    public static final int T_EXTENSION_INT;

    @RequiresApi(30)
    public static final class Api30Impl {
        public static final Api30Impl INSTANCE = new Api30Impl();

        private Api30Impl() {
        }

        public final int getExtensionVersion(int i2) {
            return SdkExtensions.getExtensionVersion(i2);
        }
    }

    @Retention(RetentionPolicy.CLASS)
    public @interface PrereleaseSdkCheck {
    }

    static {
        Api30Impl api30Impl = Api30Impl.INSTANCE;
        R_EXTENSION_INT = api30Impl.getExtensionVersion(30);
        S_EXTENSION_INT = api30Impl.getExtensionVersion(31);
        T_EXTENSION_INT = api30Impl.getExtensionVersion(33);
        AD_SERVICES_EXTENSION_INT = api30Impl.getExtensionVersion(AnimState.VIEW_SIZE);
    }

    private BuildCompat() {
    }

    @ChecksSdkIntAtLeast(api = 36, codename = "Baklava")
    public static final boolean isAtLeastB() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 36) {
            if (i2 >= 35) {
                String CODENAME = Build.VERSION.CODENAME;
                n.f(CODENAME, "CODENAME");
                if (isAtLeastPreReleaseCodename("Baklava", CODENAME)) {
                }
            }
            return false;
        }
        return true;
    }

    @ChecksSdkIntAtLeast(api = 24)
    public static final boolean isAtLeastN() {
        return true;
    }

    @ChecksSdkIntAtLeast(api = 25)
    public static final boolean isAtLeastNMR1() {
        return true;
    }

    @ChecksSdkIntAtLeast(api = 26)
    public static final boolean isAtLeastO() {
        return true;
    }

    @ChecksSdkIntAtLeast(api = 27)
    public static final boolean isAtLeastOMR1() {
        return true;
    }

    @ChecksSdkIntAtLeast(api = 28)
    public static final boolean isAtLeastP() {
        return true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    @VisibleForTesting
    public static final boolean isAtLeastPreReleaseCodename(String codename, String buildCodename) {
        n.g(codename, "codename");
        n.g(buildCodename, "buildCodename");
        if (n.c("REL", buildCodename)) {
            return false;
        }
        Integer numIsAtLeastPreReleaseCodename$codenameToInt = isAtLeastPreReleaseCodename$codenameToInt(buildCodename);
        Integer numIsAtLeastPreReleaseCodename$codenameToInt2 = isAtLeastPreReleaseCodename$codenameToInt(codename);
        if (numIsAtLeastPreReleaseCodename$codenameToInt != null && numIsAtLeastPreReleaseCodename$codenameToInt2 != null) {
            return numIsAtLeastPreReleaseCodename$codenameToInt.intValue() >= numIsAtLeastPreReleaseCodename$codenameToInt2.intValue();
        }
        if (numIsAtLeastPreReleaseCodename$codenameToInt != null || numIsAtLeastPreReleaseCodename$codenameToInt2 != null) {
            return numIsAtLeastPreReleaseCodename$codenameToInt != null;
        }
        Locale locale = Locale.ROOT;
        String upperCase = buildCodename.toUpperCase(locale);
        n.f(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        String upperCase2 = codename.toUpperCase(locale);
        n.f(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        return upperCase.compareTo(upperCase2) >= 0;
    }

    private static final Integer isAtLeastPreReleaseCodename$codenameToInt(String str) {
        String upperCase = str.toUpperCase(Locale.ROOT);
        n.f(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        return n.c(upperCase, "BAKLAVA") ? 0 : null;
    }

    @ChecksSdkIntAtLeast(api = 29)
    public static final boolean isAtLeastQ() {
        return true;
    }

    @ChecksSdkIntAtLeast(api = 30)
    public static final boolean isAtLeastR() {
        return true;
    }

    @ChecksSdkIntAtLeast(api = 31, codename = ExifInterface.LATITUDE_SOUTH)
    public static final boolean isAtLeastS() {
        return true;
    }

    @ChecksSdkIntAtLeast(api = 32, codename = "Sv2")
    public static final boolean isAtLeastSv2() {
        return true;
    }

    @ChecksSdkIntAtLeast(api = 33, codename = "Tiramisu")
    public static final boolean isAtLeastT() {
        return true;
    }

    @ChecksSdkIntAtLeast(api = 34, codename = "UpsideDownCake")
    public static final boolean isAtLeastU() {
        return true;
    }

    @ChecksSdkIntAtLeast(api = 35, codename = "VanillaIceCream")
    public static final boolean isAtLeastV() {
        if (Build.VERSION.SDK_INT < 35) {
            String CODENAME = Build.VERSION.CODENAME;
            n.f(CODENAME, "CODENAME");
            if (!isAtLeastPreReleaseCodename("VanillaIceCream", CODENAME)) {
                return false;
            }
        }
        return true;
    }
}
