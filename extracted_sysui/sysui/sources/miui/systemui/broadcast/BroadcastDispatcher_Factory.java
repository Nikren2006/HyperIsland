package miui.systemui.broadcast;

import F0.e;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes2.dex */
public final class BroadcastDispatcher_Factory implements e {
    private final G0.a bgLooperProvider;
    private final G0.a contextProvider;
    private final G0.a mainHandlerProvider;

    public BroadcastDispatcher_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.contextProvider = aVar;
        this.mainHandlerProvider = aVar2;
        this.bgLooperProvider = aVar3;
    }

    public static BroadcastDispatcher_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new BroadcastDispatcher_Factory(aVar, aVar2, aVar3);
    }

    public static BroadcastDispatcher newInstance(Context context, Handler handler, Looper looper) {
        return new BroadcastDispatcher(context, handler, looper);
    }

    @Override // G0.a
    public BroadcastDispatcher get() {
        return newInstance((Context) this.contextProvider.get(), (Handler) this.mainHandlerProvider.get(), (Looper) this.bgLooperProvider.get());
    }
}
