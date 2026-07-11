package miui.systemui.dynamicisland.window;

import android.content.Context;
import g1.E;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandWindowController_Factory implements F0.e {
    private final G0.a contextProvider;
    private final G0.a scopeProvider;
    private final G0.a windowStateInteractorProvider;
    private final G0.a windowViewProvider;

    public DynamicIslandWindowController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        this.scopeProvider = aVar;
        this.windowViewProvider = aVar2;
        this.contextProvider = aVar3;
        this.windowStateInteractorProvider = aVar4;
    }

    public static DynamicIslandWindowController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4) {
        return new DynamicIslandWindowController_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static DynamicIslandWindowController newInstance(E e2, DynamicIslandWindowView dynamicIslandWindowView, Context context, DynamicIslandWindowStateInteractor dynamicIslandWindowStateInteractor) {
        return new DynamicIslandWindowController(e2, dynamicIslandWindowView, context, dynamicIslandWindowStateInteractor);
    }

    @Override // G0.a
    public DynamicIslandWindowController get() {
        return newInstance((E) this.scopeProvider.get(), (DynamicIslandWindowView) this.windowViewProvider.get(), (Context) this.contextProvider.get(), (DynamicIslandWindowStateInteractor) this.windowStateInteractorProvider.get());
    }
}
