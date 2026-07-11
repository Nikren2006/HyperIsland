package miui.systemui.dagger;

import android.content.ContentProvider;
import com.android.systemui.BaseMiPlayController;
import miui.systemui.PluginAppComponentFactory;
import miui.systemui.controlcenter.MiuiControlCenter;
import miui.systemui.notification.FocusNotificationPluginImpl;
import miui.systemui.notification.NotificationDynamicIslandPluginImpl;
import miui.systemui.plugins.PluginBase;
import miui.systemui.quicksettings.LocalMiuiQSTilePlugin;
import miui.systemui.util.SystemUIResourcesHelper;
import miui.systemui.volume.VolumeDialogPlugin;
import systemui.plugin.eventtracking.trackers.BaseEventTracker;

/* JADX INFO: loaded from: classes.dex */
public interface PluginComponent {
    ViewCreator createViewCreator();

    ContextComponentHelper getContextComponentHelper();

    SystemUIResourcesHelper getSystemUIResourcesHelper();

    void inject(ContentProvider contentProvider);

    void inject(BaseMiPlayController baseMiPlayController);

    void inject(PluginAppComponentFactory pluginAppComponentFactory);

    void inject(MiuiControlCenter miuiControlCenter);

    void inject(FocusNotificationPluginImpl focusNotificationPluginImpl);

    void inject(NotificationDynamicIslandPluginImpl notificationDynamicIslandPluginImpl);

    void inject(PluginBase pluginBase);

    void inject(LocalMiuiQSTilePlugin localMiuiQSTilePlugin);

    void inject(VolumeDialogPlugin volumeDialogPlugin);

    void inject(BaseEventTracker baseEventTracker);
}
