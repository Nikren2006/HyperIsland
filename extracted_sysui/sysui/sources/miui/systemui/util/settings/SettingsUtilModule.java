package miui.systemui.util.settings;

/* JADX INFO: loaded from: classes4.dex */
public interface SettingsUtilModule {
    GlobalSettings bindsGlobalSettings(GlobalSettingsImpl globalSettingsImpl);

    SecureSettings bindsSecureSettings(SecureSettingsImpl secureSettingsImpl);

    SystemSettings bindsSystemSettings(SystemSettingsImpl systemSettingsImpl);
}
