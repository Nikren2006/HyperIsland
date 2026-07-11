package miui.systemui.util.settings;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.Settings;

/* JADX INFO: loaded from: classes4.dex */
public class GlobalSettingsImpl implements GlobalSettings {
    private final ContentResolver mContentResolver;

    public GlobalSettingsImpl(ContentResolver contentResolver) {
        this.mContentResolver = contentResolver;
    }

    @Override // miui.systemui.util.settings.SettingsProxy
    public ContentResolver getContentResolver() {
        return this.mContentResolver;
    }

    @Override // miui.systemui.util.settings.SettingsProxy
    public String getStringForUser(String str, int i2) {
        return Settings.Global.getStringForUser(this.mContentResolver, str, i2);
    }

    @Override // miui.systemui.util.settings.SettingsProxy
    public Uri getUriFor(String str) {
        return Settings.Global.getUriFor(str);
    }

    @Override // miui.systemui.util.settings.SettingsProxy
    public boolean putStringForUser(String str, String str2, int i2) {
        return Settings.Global.putStringForUser(this.mContentResolver, str, str2, i2);
    }

    public GlobalSettingsImpl(ContentResolver contentResolver, String str) {
        this.mContentResolver = contentResolver;
    }
}
