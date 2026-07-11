package miui.systemui.util.settings;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.Settings;

/* JADX INFO: loaded from: classes4.dex */
public class SecureSettingsImpl implements SecureSettings {
    private final ContentResolver mContentResolver;

    public SecureSettingsImpl(ContentResolver contentResolver) {
        this.mContentResolver = contentResolver;
    }

    @Override // miui.systemui.util.settings.SettingsProxy
    public ContentResolver getContentResolver() {
        return this.mContentResolver;
    }

    @Override // miui.systemui.util.settings.SettingsProxy
    public String getStringForUser(String str, int i2) {
        return Settings.Secure.getStringForUser(this.mContentResolver, str, i2);
    }

    @Override // miui.systemui.util.settings.SettingsProxy
    public Uri getUriFor(String str) {
        return Settings.Secure.getUriFor(str);
    }

    @Override // miui.systemui.util.settings.SettingsProxy
    public boolean putStringForUser(String str, String str2, int i2) {
        return Settings.Secure.putStringForUser(this.mContentResolver, str, str2, i2);
    }
}
