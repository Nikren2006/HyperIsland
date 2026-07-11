package miui.systemui.notification;

import android.content.Context;
import miui.systemui.autodensity.AutoDensityControllerImpl;
import miui.systemui.plugins.PluginBase_MembersInjector;
import miui.systemui.plugins.data.repository.PluginInstancesRepositoryImpl;

/* JADX INFO: loaded from: classes4.dex */
public final class FocusNotificationPluginImpl_MembersInjector implements E0.b {
    private final G0.a autoDensityControllerProvider;
    private final G0.a focusNotificationControllerProvider;
    private final G0.a mFocusNotifUtilsProvider;
    private final G0.a mNotifSettingsMgrProvider;
    private final G0.a pluginContextProvider;
    private final G0.a pluginInstancesRepositoryProvider;
    private final G0.a sysuiContextProvider;

    public FocusNotificationPluginImpl_MembersInjector(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        this.pluginInstancesRepositoryProvider = aVar;
        this.autoDensityControllerProvider = aVar2;
        this.pluginContextProvider = aVar3;
        this.sysuiContextProvider = aVar4;
        this.mNotifSettingsMgrProvider = aVar5;
        this.focusNotificationControllerProvider = aVar6;
        this.mFocusNotifUtilsProvider = aVar7;
    }

    public static E0.b create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        return new FocusNotificationPluginImpl_MembersInjector(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7);
    }

    public static void injectFocusNotificationController(FocusNotificationPluginImpl focusNotificationPluginImpl, E0.a aVar) {
        focusNotificationPluginImpl.focusNotificationController = aVar;
    }

    public static void injectMFocusNotifUtils(FocusNotificationPluginImpl focusNotificationPluginImpl, E0.a aVar) {
        focusNotificationPluginImpl.mFocusNotifUtils = aVar;
    }

    public static void injectMNotifSettingsMgr(FocusNotificationPluginImpl focusNotificationPluginImpl, NotificationSettingsManager notificationSettingsManager) {
        focusNotificationPluginImpl.mNotifSettingsMgr = notificationSettingsManager;
    }

    public void injectMembers(FocusNotificationPluginImpl focusNotificationPluginImpl) {
        PluginBase_MembersInjector.injectPluginInstancesRepository(focusNotificationPluginImpl, (PluginInstancesRepositoryImpl) this.pluginInstancesRepositoryProvider.get());
        PluginBase_MembersInjector.injectAutoDensityController(focusNotificationPluginImpl, (AutoDensityControllerImpl) this.autoDensityControllerProvider.get());
        PluginBase_MembersInjector.injectPluginContext(focusNotificationPluginImpl, (Context) this.pluginContextProvider.get());
        PluginBase_MembersInjector.injectSysuiContext(focusNotificationPluginImpl, (Context) this.sysuiContextProvider.get());
        injectMNotifSettingsMgr(focusNotificationPluginImpl, (NotificationSettingsManager) this.mNotifSettingsMgrProvider.get());
        injectFocusNotificationController(focusNotificationPluginImpl, F0.d.a(this.focusNotificationControllerProvider));
        injectMFocusNotifUtils(focusNotificationPluginImpl, F0.d.a(this.mFocusNotifUtilsProvider));
    }
}
