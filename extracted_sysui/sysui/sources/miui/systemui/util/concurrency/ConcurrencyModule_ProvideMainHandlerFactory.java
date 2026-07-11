package miui.systemui.util.concurrency;

import F0.e;
import F0.i;
import android.os.Handler;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule_ProvideMainHandlerFactory implements e {

    public static final class InstanceHolder {
        private static final ConcurrencyModule_ProvideMainHandlerFactory INSTANCE = new ConcurrencyModule_ProvideMainHandlerFactory();

        private InstanceHolder() {
        }
    }

    public static ConcurrencyModule_ProvideMainHandlerFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Handler provideMainHandler() {
        return (Handler) i.d(ConcurrencyModule.INSTANCE.provideMainHandler());
    }

    @Override // G0.a
    public Handler get() {
        return provideMainHandler();
    }
}
