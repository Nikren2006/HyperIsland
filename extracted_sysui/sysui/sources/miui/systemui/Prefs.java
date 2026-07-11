package miui.systemui;

import android.content.Context;
import android.content.SharedPreferences;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class Prefs {

    @Retention(RetentionPolicy.SOURCE)
    public @interface Key {

        @Deprecated
        public static final String CONTROLS_STRUCTURE_SWIPE_TOOLTIP_COUNT = "ControlsStructureSwipeTooltipCount";
    }

    private Prefs() {
    }

    public static SharedPreferences get(Context context) {
        Context contextCreateDeviceProtectedStorageContext = context.createDeviceProtectedStorageContext();
        if (contextCreateDeviceProtectedStorageContext == null) {
            contextCreateDeviceProtectedStorageContext = context;
        }
        return contextCreateDeviceProtectedStorageContext.getSharedPreferences(context.getPackageName(), 0);
    }

    public static Map<String, ?> getAll(Context context) {
        return get(context).getAll();
    }

    public static boolean getBoolean(Context context, String str, boolean z2) {
        return get(context).getBoolean(str, z2);
    }

    public static int getInt(Context context, String str, int i2) {
        return get(context).getInt(str, i2);
    }

    public static long getLong(Context context, String str, long j2) {
        return get(context).getLong(str, j2);
    }

    public static SharedPreferences getNotif(Context context) {
        Context contextCreateDeviceProtectedStorageContext = context.createDeviceProtectedStorageContext();
        if (contextCreateDeviceProtectedStorageContext != null) {
            context = contextCreateDeviceProtectedStorageContext;
        }
        return context.getSharedPreferences("app_notification", 0);
    }

    public static String getString(Context context, String str, String str2) {
        return get(context).getString(str, str2);
    }

    public static Set<String> getStringSet(Context context, String str, Set<String> set) {
        return get(context).getStringSet(str, set);
    }

    public static void putBoolean(Context context, String str, boolean z2) {
        get(context).edit().putBoolean(str, z2).apply();
    }

    public static void putInt(Context context, String str, int i2) {
        get(context).edit().putInt(str, i2).apply();
    }

    public static void putLong(Context context, String str, long j2) {
        get(context).edit().putLong(str, j2).apply();
    }

    public static void putString(Context context, String str, String str2) {
        get(context).edit().putString(str, str2).apply();
    }

    public static void putStringSet(Context context, String str, Set<String> set) {
        get(context).edit().putStringSet(str, set).apply();
    }

    public static void registerListener(Context context, SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        get(context).registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public static void remove(Context context, String str) {
        get(context).edit().remove(str).apply();
    }

    public static void unregisterListener(Context context, SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        get(context).unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }
}
