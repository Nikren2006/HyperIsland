package miui.systemui.dagger;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.UserManager;
import com.android.systemui.settings.UserContentResolverProvider;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.util.settings.GlobalSettings;
import miui.systemui.util.settings.GlobalSettingsImpl;
import miui.systemui.util.settings.SecureSettings;
import miui.systemui.util.settings.SecureSettingsImpl;
import miui.systemui.util.settings.SystemSettings;
import miui.systemui.util.settings.SystemSettingsImpl;

/* JADX INFO: loaded from: classes.dex */
public abstract class SettingsModule {
    public static UserTracker provideUserTracker(Context context, UserManager userManager, @Background Handler handler) {
        int currentUser = ActivityManager.getCurrentUser();
        UserTrackerImpl userTrackerImpl = new UserTrackerImpl(context, userManager, handler);
        userTrackerImpl.initialize(currentUser);
        return userTrackerImpl;
    }

    public abstract GlobalSettings bindGlobalSettings(GlobalSettingsImpl globalSettingsImpl);

    public abstract SecureSettings bindSecureSettings(SecureSettingsImpl secureSettingsImpl);

    public abstract SystemSettings bindSystemSettings(SystemSettingsImpl systemSettingsImpl);

    public abstract UserContentResolverProvider bindUserContentResolverProvider(UserTracker userTracker);

    public abstract UserContextProvider bindUserContextProvider(UserTracker userTracker);
}
