package miui.systemui.devicecenter;

import F0.e;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceCenterController_Factory implements e {
    private final G0.a bgLooperProvider;
    private final G0.a contextProvider;
    private final G0.a sharedPreferencesProvider;
    private final G0.a uiExecutorProvider;

    public DeviceCenterController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        this.contextProvider = aVar;
        this.bgLooperProvider = aVar2;
        this.uiExecutorProvider = aVar3;
        this.sharedPreferencesProvider = aVar4;
    }

    public static DeviceCenterController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        return new DeviceCenterController_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static DeviceCenterController newInstance(Context context, Looper looper, Executor executor, SharedPreferences sharedPreferences) {
        return new DeviceCenterController(context, looper, executor, sharedPreferences);
    }

    @Override // G0.a
    public DeviceCenterController get() {
        return newInstance((Context) this.contextProvider.get(), (Looper) this.bgLooperProvider.get(), (Executor) this.uiExecutorProvider.get(), (SharedPreferences) this.sharedPreferencesProvider.get());
    }
}
