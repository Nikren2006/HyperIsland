package miui.systemui.dynamicisland.touch.data.repository;

import F0.e;
import G0.a;
import android.content.Context;
import g1.E;
import miui.systemui.autodensity.AutoDensityController;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandTouchConstantsRepository_Factory implements e {
    private final a autoDensityControllerProvider;
    private final a contextProvider;
    private final a scopeProvider;

    public DynamicIslandTouchConstantsRepository_Factory(a aVar, a aVar2, a aVar3) {
        this.scopeProvider = aVar;
        this.contextProvider = aVar2;
        this.autoDensityControllerProvider = aVar3;
    }

    public static DynamicIslandTouchConstantsRepository_Factory create(a aVar, a aVar2, a aVar3) {
        return new DynamicIslandTouchConstantsRepository_Factory(aVar, aVar2, aVar3);
    }

    public static DynamicIslandTouchConstantsRepository newInstance(E e2, Context context, AutoDensityController autoDensityController) {
        return new DynamicIslandTouchConstantsRepository(e2, context, autoDensityController);
    }

    @Override // G0.a
    public DynamicIslandTouchConstantsRepository get() {
        return newInstance((E) this.scopeProvider.get(), (Context) this.contextProvider.get(), (AutoDensityController) this.autoDensityControllerProvider.get());
    }
}
