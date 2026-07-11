package miui.systemui.notification.focus;

import F0.e;
import miui.systemui.notification.NotificationSettingsManager;
import miui.systemui.util.AlarmScheduler;

/* JADX INFO: loaded from: classes4.dex */
public final class HideDeletedFocusController_Factory implements e {
    private final G0.a alarmSchedulerFactoryProvider;
    private final G0.a notificationSettingsManagerProvider;

    public HideDeletedFocusController_Factory(G0.a aVar, G0.a aVar2) {
        this.alarmSchedulerFactoryProvider = aVar;
        this.notificationSettingsManagerProvider = aVar2;
    }

    public static HideDeletedFocusController_Factory create(G0.a aVar, G0.a aVar2) {
        return new HideDeletedFocusController_Factory(aVar, aVar2);
    }

    public static HideDeletedFocusController newInstance(AlarmScheduler.Factory factory, NotificationSettingsManager notificationSettingsManager) {
        return new HideDeletedFocusController(factory, notificationSettingsManager);
    }

    @Override // G0.a
    public HideDeletedFocusController get() {
        return newInstance((AlarmScheduler.Factory) this.alarmSchedulerFactoryProvider.get(), (NotificationSettingsManager) this.notificationSettingsManagerProvider.get());
    }
}
