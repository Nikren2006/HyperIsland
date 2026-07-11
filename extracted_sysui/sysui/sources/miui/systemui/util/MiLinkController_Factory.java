package miui.systemui.util;

import android.content.Context;
import android.os.Handler;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes4.dex */
public final class MiLinkController_Factory implements F0.e {
    private final G0.a bgHandlerProvider;
    private final G0.a contextProvider;
    private final G0.a uiExecutorProvider;

    public MiLinkController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.contextProvider = aVar;
        this.bgHandlerProvider = aVar2;
        this.uiExecutorProvider = aVar3;
    }

    public static MiLinkController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new MiLinkController_Factory(aVar, aVar2, aVar3);
    }

    public static MiLinkController newInstance(Context context, Handler handler, DelayableExecutor delayableExecutor) {
        return new MiLinkController(context, handler, delayableExecutor);
    }

    @Override // G0.a
    public MiLinkController get() {
        return newInstance((Context) this.contextProvider.get(), (Handler) this.bgHandlerProvider.get(), (DelayableExecutor) this.uiExecutorProvider.get());
    }
}
