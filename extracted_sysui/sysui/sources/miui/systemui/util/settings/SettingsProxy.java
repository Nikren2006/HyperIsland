package miui.systemui.util.settings;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.provider.Settings;

/* JADX INFO: loaded from: classes4.dex */
public interface SettingsProxy {
    ContentResolver getContentResolver();

    default float getFloat(String str, float f2) {
        return getFloatForUser(str, f2, getUserId());
    }

    default float getFloatForUser(String str, float f2, int i2) {
        String stringForUser = getStringForUser(str, i2);
        if (stringForUser == null) {
            return f2;
        }
        try {
            return Float.parseFloat(stringForUser);
        } catch (NumberFormatException unused) {
            return f2;
        }
    }

    default int getInt(String str, int i2) {
        return getIntForUser(str, i2, getUserId());
    }

    default int getIntForUser(String str, int i2, int i3) {
        String stringForUser = getStringForUser(str, i3);
        if (stringForUser == null) {
            return i2;
        }
        try {
            return Integer.parseInt(stringForUser);
        } catch (NumberFormatException unused) {
            return i2;
        }
    }

    default long getLong(String str, long j2) {
        return getLongForUser(str, j2, getUserId());
    }

    default long getLongForUser(String str, long j2, int i2) {
        String stringForUser = getStringForUser(str, i2);
        if (stringForUser == null) {
            return j2;
        }
        try {
            return Long.parseLong(stringForUser);
        } catch (NumberFormatException unused) {
            return j2;
        }
    }

    default String getString(String str) {
        return getStringForUser(str, getUserId());
    }

    String getStringForUser(String str, int i2);

    Uri getUriFor(String str);

    default int getUserId() {
        return getContentResolver().getUserId();
    }

    default boolean putFloat(String str, float f2) {
        return putFloatForUser(str, f2, getUserId());
    }

    default boolean putFloatForUser(String str, float f2, int i2) {
        return putStringForUser(str, Float.toString(f2), i2);
    }

    default boolean putInt(String str, int i2) {
        return putIntForUser(str, i2, getUserId());
    }

    default boolean putIntForUser(String str, int i2, int i3) {
        return putStringForUser(str, Integer.toString(i2), i3);
    }

    default boolean putLong(String str, long j2) {
        return putLongForUser(str, j2, getUserId());
    }

    default boolean putLongForUser(String str, long j2, int i2) {
        return putStringForUser(str, Long.toString(j2), i2);
    }

    default boolean putString(String str, String str2) {
        return putStringForUser(str, str2, getUserId());
    }

    boolean putStringForUser(String str, String str2, int i2);

    default void registerContentObserver(String str, ContentObserver contentObserver) {
        registerContentObserver(getUriFor(str), contentObserver);
    }

    default void registerContentObserverForUser(String str, ContentObserver contentObserver, int i2) {
        registerContentObserverForUser(getUriFor(str), contentObserver, i2);
    }

    default void unregisterContentObserver(ContentObserver contentObserver) {
        getContentResolver().unregisterContentObserver(contentObserver);
    }

    default float getFloat(String str) {
        return getFloatForUser(str, getUserId());
    }

    default int getInt(String str) {
        return getIntForUser(str, getUserId());
    }

    default long getLong(String str) {
        return getLongForUser(str, getUserId());
    }

    default void registerContentObserver(Uri uri, ContentObserver contentObserver) {
        registerContentObserverForUser(uri, contentObserver, getUserId());
    }

    default float getFloatForUser(String str, int i2) throws Settings.SettingNotFoundException {
        String stringForUser = getStringForUser(str, i2);
        if (stringForUser != null) {
            try {
                return Float.parseFloat(stringForUser);
            } catch (NumberFormatException unused) {
                throw new Settings.SettingNotFoundException(str);
            }
        }
        throw new Settings.SettingNotFoundException(str);
    }

    default int getIntForUser(String str, int i2) throws Settings.SettingNotFoundException {
        try {
            return Integer.parseInt(getStringForUser(str, i2));
        } catch (NumberFormatException unused) {
            throw new Settings.SettingNotFoundException(str);
        }
    }

    default long getLongForUser(String str, int i2) throws Settings.SettingNotFoundException {
        try {
            return Long.parseLong(getStringForUser(str, i2));
        } catch (NumberFormatException unused) {
            throw new Settings.SettingNotFoundException(str);
        }
    }

    default void registerContentObserver(String str, boolean z2, ContentObserver contentObserver) {
        registerContentObserver(getUriFor(str), z2, contentObserver);
    }

    default void registerContentObserverForUser(Uri uri, ContentObserver contentObserver, int i2) {
        registerContentObserverForUser(uri, false, contentObserver, i2);
    }

    default void registerContentObserver(Uri uri, boolean z2, ContentObserver contentObserver) {
        registerContentObserverForUser(uri, z2, contentObserver, getUserId());
    }

    default void registerContentObserverForUser(String str, boolean z2, ContentObserver contentObserver, int i2) {
        registerContentObserverForUser(getUriFor(str), z2, contentObserver, i2);
    }

    default void registerContentObserverForUser(Uri uri, boolean z2, ContentObserver contentObserver, int i2) {
        getContentResolver().registerContentObserver(uri, z2, contentObserver, i2);
    }
}
