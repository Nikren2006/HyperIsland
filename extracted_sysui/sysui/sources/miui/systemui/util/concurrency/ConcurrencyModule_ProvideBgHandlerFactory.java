package miui.systemui.util.concurrency;

import F0.e;
import F0.i;
import android.os.Handler;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule_ProvideBgHandlerFactory implements e {

    public static final class InstanceHolder {
        private static final ConcurrencyModule_ProvideBgHandlerFactory INSTANCE = new ConcurrencyModule_ProvideBgHandlerFactory();

        private InstanceHolder() {
        }
    }

    public static ConcurrencyModule_ProvideBgHandlerFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Handler provideBgHandler() {
        return (Handler) i.d(ConcurrencyModule.INSTANCE.provideBgHandler());
    }

    @Override // G0.a
    public Handler get() {
        return provideBgHandler();
    }
}
