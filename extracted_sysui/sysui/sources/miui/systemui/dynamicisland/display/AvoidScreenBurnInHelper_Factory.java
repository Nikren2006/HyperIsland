package miui.systemui.dynamicisland.display;

import F0.e;
import G0.a;
import g1.E;

/* JADX INFO: loaded from: classes3.dex */
public final class AvoidScreenBurnInHelper_Factory implements e {
    private final a scopeProvider;

    public AvoidScreenBurnInHelper_Factory(a aVar) {
        this.scopeProvider = aVar;
    }

    public static AvoidScreenBurnInHelper_Factory create(a aVar) {
        return new AvoidScreenBurnInHelper_Factory(aVar);
    }

    public static AvoidScreenBurnInHelper newInstance(E e2) {
        return new AvoidScreenBurnInHelper(e2);
    }

    @Override // G0.a
    public AvoidScreenBurnInHelper get() {
        return newInstance((E) this.scopeProvider.get());
    }
}
