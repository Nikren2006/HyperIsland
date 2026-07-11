package miui.systemui.notification.focus;

import F0.e;
import android.content.Context;
import com.android.systemui.plugins.ActivityStarter;
import miui.systemui.controlcenter.data.repository.ControlCenterExpandRepository;
import miui.systemui.notification.NotificationSettingsManager;
import miui.systemui.notification.focus.templateV3.TemplateFactoryV3;

/* JADX INFO: loaded from: classes4.dex */
public final class FocusNotifPreHandler_Factory implements e {
    private final G0.a activityStarterProvider;
    private final G0.a contextProvider;
    private final G0.a controlCenterExpandRepositoryProvider;
    private final G0.a factoryV3Provider;
    private final G0.a settingsManagerProvider;
    private final G0.a utilsProvider;

    public FocusNotifPreHandler_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.settingsManagerProvider = aVar;
        this.contextProvider = aVar2;
        this.activityStarterProvider = aVar3;
        this.factoryV3Provider = aVar4;
        this.utilsProvider = aVar5;
        this.controlCenterExpandRepositoryProvider = aVar6;
    }

    public static FocusNotifPreHandler_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new FocusNotifPreHandler_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static FocusNotifPreHandler newInstance(NotificationSettingsManager notificationSettingsManager, Context context, ActivityStarter activityStarter, TemplateFactoryV3 templateFactoryV3, FocusNotifUtils focusNotifUtils, ControlCenterExpandRepository controlCenterExpandRepository) {
        return new FocusNotifPreHandler(notificationSettingsManager, context, activityStarter, templateFactoryV3, focusNotifUtils, controlCenterExpandRepository);
    }

    @Override // G0.a
    public FocusNotifPreHandler get() {
        return newInstance((NotificationSettingsManager) this.settingsManagerProvider.get(), (Context) this.contextProvider.get(), (ActivityStarter) this.activityStarterProvider.get(), (TemplateFactoryV3) this.factoryV3Provider.get(), (FocusNotifUtils) this.utilsProvider.get(), (ControlCenterExpandRepository) this.controlCenterExpandRepositoryProvider.get());
    }
}
