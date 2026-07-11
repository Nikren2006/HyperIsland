package miui.systemui.util.kotlin;

import F0.e;
import F0.i;
import L0.g;

/* JADX INFO: loaded from: classes4.dex */
public final class CoroutinesModule_MainCoroutineContextFactory implements e {

    public static final class InstanceHolder {
        private static final CoroutinesModule_MainCoroutineContextFactory INSTANCE = new CoroutinesModule_MainCoroutineContextFactory();

        private InstanceHolder() {
        }
    }

    public static CoroutinesModule_MainCoroutineContextFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static g mainCoroutineContext() {
        return (g) i.d(CoroutinesModule.INSTANCE.mainCoroutineContext());
    }

    @Override // G0.a
    public g get() {
        return mainCoroutineContext();
    }
}
