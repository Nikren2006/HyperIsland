package miui.systemui.util.kotlin;

import F0.e;
import F0.i;
import g1.B;

/* JADX INFO: loaded from: classes4.dex */
public final class CoroutinesModule_MainDispatcherFactory implements e {

    public static final class InstanceHolder {
        private static final CoroutinesModule_MainDispatcherFactory INSTANCE = new CoroutinesModule_MainDispatcherFactory();

        private InstanceHolder() {
        }
    }

    public static CoroutinesModule_MainDispatcherFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static B mainDispatcher() {
        return (B) i.d(CoroutinesModule.INSTANCE.mainDispatcher());
    }

    @Override // G0.a
    public B get() {
        return mainDispatcher();
    }
}
