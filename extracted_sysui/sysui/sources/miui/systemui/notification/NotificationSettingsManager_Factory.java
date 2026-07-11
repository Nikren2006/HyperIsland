package miui.systemui.notification;

import android.content.Context;
import android.os.Handler;
import miui.systemui.clouddata.CloudDataManager;

/* JADX INFO: loaded from: classes4.dex */
public final class NotificationSettingsManager_Factory implements F0.e {
    private final G0.a bgHandleProvider;
    private final G0.a contextProvider;
    private final G0.a managerProvider;

    public NotificationSettingsManager_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.contextProvider = aVar;
        this.managerProvider = aVar2;
        this.bgHandleProvider = aVar3;
    }

    public static NotificationSettingsManager_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new NotificationSettingsManager_Factory(aVar, aVar2, aVar3);
    }

    public static NotificationSettingsManager newInstance(Context context, CloudDataManager cloudDataManager, Handler handler) {
        return new NotificationSettingsManager(context, cloudDataManager, handler);
    }

    @Override // G0.a
    public NotificationSettingsManager get() {
        return newInstance((Context) this.contextProvider.get(), (CloudDataManager) this.managerProvider.get(), (Handler) this.bgHandleProvider.get());
    }
}
