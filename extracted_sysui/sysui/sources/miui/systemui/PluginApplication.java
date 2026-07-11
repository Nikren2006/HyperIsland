package miui.systemui;

import android.app.Application;
import android.content.pm.PackageManager;
import miui.systemui.PluginAppComponentFactory;
import systemui.plugin.eventtracking.utils.EventsUtils;

/* JADX INFO: loaded from: classes2.dex */
public class PluginApplication extends Application implements PluginAppComponentFactory.ContextInitializer {
    private PluginAppComponentFactory.ContextAvailableCallback mContextAvailableCallback;

    @Override // android.app.Application
    public void onCreate() throws PackageManager.NameNotFoundException {
        super.onCreate();
        EventsUtils.INSTANCE.init(this);
        this.mContextAvailableCallback.onContextAvailable(this);
    }

    @Override // miui.systemui.PluginAppComponentFactory.ContextInitializer
    public void setContextAvailableCallback(PluginAppComponentFactory.ContextAvailableCallback contextAvailableCallback) {
        this.mContextAvailableCallback = contextAvailableCallback;
    }
}
