package miui.systemui.clouddata;

import F0.e;
import G0.a;
import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public final class CloudDataManager_Factory implements e {
    private final a ctxProvider;

    public CloudDataManager_Factory(a aVar) {
        this.ctxProvider = aVar;
    }

    public static CloudDataManager_Factory create(a aVar) {
        return new CloudDataManager_Factory(aVar);
    }

    public static CloudDataManager newInstance(Context context) {
        return new CloudDataManager(context);
    }

    @Override // G0.a
    public CloudDataManager get() {
        return newInstance((Context) this.ctxProvider.get());
    }
}
