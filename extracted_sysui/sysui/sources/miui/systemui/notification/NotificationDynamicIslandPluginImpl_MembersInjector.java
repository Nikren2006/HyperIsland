package miui.systemui.notification;

import android.content.Context;
import miui.systemui.autodensity.AutoDensityControllerImpl;
import miui.systemui.dynamicisland.window.DynamicIslandWindowViewCreator;
import miui.systemui.notification.focus.FocusNotificationController;
import miui.systemui.plugins.PluginBase_MembersInjector;
import miui.systemui.plugins.data.repository.PluginInstancesRepositoryImpl;

/* JADX INFO: loaded from: classes4.dex */
public final class NotificationDynamicIslandPluginImpl_MembersInjector implements E0.b {
    private final G0.a autoDensityControllerProvider;
    private final G0.a focusNotificationControllerProvider;
    private final G0.a pluginContextProvider;
    private final G0.a pluginInstancesRepositoryProvider;
    private final G0.a sysuiContextProvider;
    private final G0.a windowViewCreatorProvider;

    public NotificationDynamicIslandPluginImpl_MembersInjector(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.pluginInstancesRepositoryProvider = aVar;
        this.autoDensityControllerProvider = aVar2;
        this.pluginContextProvider = aVar3;
        this.sysuiContextProvider = aVar4;
        this.windowViewCreatorProvider = aVar5;
        this.focusNotificationControllerProvider = aVar6;
    }

    public static E0.b create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new NotificationDynamicIslandPluginImpl_MembersInjector(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static void injectFocusNotificationController(NotificationDynamicIslandPluginImpl notificationDynamicIslandPluginImpl, FocusNotificationController focusNotificationController) {
        notificationDynamicIslandPluginImpl.focusNotificationController = focusNotificationController;
    }

    public static void injectWindowViewCreator(NotificationDynamicIslandPluginImpl notificationDynamicIslandPluginImpl, DynamicIslandWindowViewCreator dynamicIslandWindowViewCreator) {
        notificationDynamicIslandPluginImpl.windowViewCreator = dynamicIslandWindowViewCreator;
    }

    public void injectMembers(NotificationDynamicIslandPluginImpl notificationDynamicIslandPluginImpl) {
        PluginBase_MembersInjector.injectPluginInstancesRepository(notificationDynamicIslandPluginImpl, (PluginInstancesRepositoryImpl) this.pluginInstancesRepositoryProvider.get());
        PluginBase_MembersInjector.injectAutoDensityController(notificationDynamicIslandPluginImpl, (AutoDensityControllerImpl) this.autoDensityControllerProvider.get());
        PluginBase_MembersInjector.injectPluginContext(notificationDynamicIslandPluginImpl, (Context) this.pluginContextProvider.get());
        PluginBase_MembersInjector.injectSysuiContext(notificationDynamicIslandPluginImpl, (Context) this.sysuiContextProvider.get());
        injectWindowViewCreator(notificationDynamicIslandPluginImpl, (DynamicIslandWindowViewCreator) this.windowViewCreatorProvider.get());
        injectFocusNotificationController(notificationDynamicIslandPluginImpl, (FocusNotificationController) this.focusNotificationControllerProvider.get());
    }
}
