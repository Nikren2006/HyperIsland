package miui.systemui.dagger;

import F0.i;
import android.content.Context;
import android.os.Handler;
import android.os.UserManager;
import com.android.systemui.settings.UserTracker;

/* JADX INFO: loaded from: classes.dex */
public final class SettingsModule_ProvideUserTrackerFactory implements F0.e {
    private final G0.a contextProvider;
    private final G0.a handlerProvider;
    private final G0.a userManagerProvider;

    public SettingsModule_ProvideUserTrackerFactory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.contextProvider = aVar;
        this.userManagerProvider = aVar2;
        this.handlerProvider = aVar3;
    }

    public static SettingsModule_ProvideUserTrackerFactory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new SettingsModule_ProvideUserTrackerFactory(aVar, aVar2, aVar3);
    }

    public static UserTracker provideUserTracker(Context context, UserManager userManager, Handler handler) {
        return (UserTracker) i.d(SettingsModule.provideUserTracker(context, userManager, handler));
    }

    @Override // G0.a
    public UserTracker get() {
        return provideUserTracker((Context) this.contextProvider.get(), (UserManager) this.userManagerProvider.get(), (Handler) this.handlerProvider.get());
    }
}
