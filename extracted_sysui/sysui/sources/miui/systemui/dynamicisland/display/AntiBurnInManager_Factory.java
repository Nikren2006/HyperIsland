package miui.systemui.dynamicisland.display;

import F0.d;
import F0.e;
import G0.a;
import android.content.Context;
import g1.E;

/* JADX INFO: loaded from: classes3.dex */
public final class AntiBurnInManager_Factory implements e {
    private final a bigIslandStateHandlerProvider;
    private final a contextProvider;
    private final a scopeProvider;
    private final a smallIslandStateHandlerProvider;

    public AntiBurnInManager_Factory(a aVar, a aVar2, a aVar3, a aVar4) {
        this.contextProvider = aVar;
        this.bigIslandStateHandlerProvider = aVar2;
        this.smallIslandStateHandlerProvider = aVar3;
        this.scopeProvider = aVar4;
    }

    public static AntiBurnInManager_Factory create(a aVar, a aVar2, a aVar3, a aVar4) {
        return new AntiBurnInManager_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static AntiBurnInManager newInstance(Context context, E0.a aVar, E0.a aVar2, E e2) {
        return new AntiBurnInManager(context, aVar, aVar2, e2);
    }

    @Override // G0.a
    public AntiBurnInManager get() {
        return newInstance((Context) this.contextProvider.get(), d.a(this.bigIslandStateHandlerProvider), d.a(this.smallIslandStateHandlerProvider), (E) this.scopeProvider.get());
    }
}
