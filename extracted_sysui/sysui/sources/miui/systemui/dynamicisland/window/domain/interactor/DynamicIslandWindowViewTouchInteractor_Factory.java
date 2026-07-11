package miui.systemui.dynamicisland.window.domain.interactor;

import F0.e;
import G0.a;
import android.content.Context;
import g1.E;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.statusbar.data.repository.StatusBarAreaRepository;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandWindowViewTouchInteractor_Factory implements e {
    private final a contextProvider;
    private final a scopeProvider;
    private final a statusBarAreaRepositoryProvider;
    private final a windowViewProvider;

    public DynamicIslandWindowViewTouchInteractor_Factory(a aVar, a aVar2, a aVar3, a aVar4) {
        this.scopeProvider = aVar;
        this.contextProvider = aVar2;
        this.windowViewProvider = aVar3;
        this.statusBarAreaRepositoryProvider = aVar4;
    }

    public static DynamicIslandWindowViewTouchInteractor_Factory create(a aVar, a aVar2, a aVar3, a aVar4) {
        return new DynamicIslandWindowViewTouchInteractor_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static DynamicIslandWindowViewTouchInteractor newInstance(E e2, Context context, DynamicIslandWindowView dynamicIslandWindowView, StatusBarAreaRepository statusBarAreaRepository) {
        return new DynamicIslandWindowViewTouchInteractor(e2, context, dynamicIslandWindowView, statusBarAreaRepository);
    }

    @Override // G0.a
    public DynamicIslandWindowViewTouchInteractor get() {
        return newInstance((E) this.scopeProvider.get(), (Context) this.contextProvider.get(), (DynamicIslandWindowView) this.windowViewProvider.get(), (StatusBarAreaRepository) this.statusBarAreaRepositoryProvider.get());
    }
}
