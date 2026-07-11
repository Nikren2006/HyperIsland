package miui.systemui.util.concurrency;

import F0.e;
import F0.i;
import android.os.Looper;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule_ProvideBgLooperFactory implements e {

    public static final class InstanceHolder {
        private static final ConcurrencyModule_ProvideBgLooperFactory INSTANCE = new ConcurrencyModule_ProvideBgLooperFactory();

        private InstanceHolder() {
        }
    }

    public static ConcurrencyModule_ProvideBgLooperFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Looper provideBgLooper() {
        return (Looper) i.d(ConcurrencyModule.INSTANCE.provideBgLooper());
    }

    @Override // G0.a
    public Looper get() {
        return provideBgLooper();
    }
}
