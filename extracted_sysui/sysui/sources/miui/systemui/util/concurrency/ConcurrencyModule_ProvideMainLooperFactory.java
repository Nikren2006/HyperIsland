package miui.systemui.util.concurrency;

import F0.e;
import F0.i;
import android.os.Looper;

/* JADX INFO: loaded from: classes4.dex */
public final class ConcurrencyModule_ProvideMainLooperFactory implements e {

    public static final class InstanceHolder {
        private static final ConcurrencyModule_ProvideMainLooperFactory INSTANCE = new ConcurrencyModule_ProvideMainLooperFactory();

        private InstanceHolder() {
        }
    }

    public static ConcurrencyModule_ProvideMainLooperFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Looper provideMainLooper() {
        return (Looper) i.d(ConcurrencyModule.INSTANCE.provideMainLooper());
    }

    @Override // G0.a
    public Looper get() {
        return provideMainLooper();
    }
}
