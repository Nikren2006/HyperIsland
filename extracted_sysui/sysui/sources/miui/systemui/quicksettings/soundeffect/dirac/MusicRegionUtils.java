package miui.systemui.quicksettings.soundeffect.dirac;

import android.text.TextUtils;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class MusicRegionUtils {
    private static final Set<String> EU = new HashSet(Arrays.asList("AT", "BE", "BG", "CY", "CZ", "DE", "DK", "EE", "ES", "FI", "FR", "GB", "GR", "HR", "HU", "IE", "IT", "LT", "LU", "LV", "MT", "NL", "PL", "PT", "RO", "SE", "SI", "SK"));

    private static String get(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return str2;
        }
    }

    public static boolean isInEURegion() {
        String str = get("ro.miui.region", "unknown");
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, "unknown")) {
            return true;
        }
        return EU.contains(str);
    }

    public static boolean isIndiaVersion() {
        try {
            Field field = Class.forName("miui.os.Build").getField("IS_INTERNATIONAL_BUILD");
            field.setAccessible(true);
            if (field.getBoolean(null)) {
                return "IN".equals(get("ro.miui.region", "CN"));
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
