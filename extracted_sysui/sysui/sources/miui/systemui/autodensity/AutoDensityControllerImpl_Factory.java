package miui.systemui.autodensity;

import F0.e;
import G0.a;
import android.content.Context;
import android.os.Handler;
import g1.E;
import java.util.Optional;

/* JADX INFO: loaded from: classes2.dex */
public final class AutoDensityControllerImpl_Factory implements e {
    private final a pluginContextProvider;
    private final a scopeProvider;
    private final a sysuiContextProvider;
    private final a uiHandlerProvider;

    public AutoDensityControllerImpl_Factory(a aVar, a aVar2, a aVar3, a aVar4) {
        this.pluginContextProvider = aVar;
        this.sysuiContextProvider = aVar2;
        this.uiHandlerProvider = aVar3;
        this.scopeProvider = aVar4;
    }

    public static AutoDensityControllerImpl_Factory create(a aVar, a aVar2, a aVar3, a aVar4) {
        return new AutoDensityControllerImpl_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static AutoDensityControllerImpl newInstance(Context context, Optional<Context> optional, Handler handler, E e2) {
        return new AutoDensityControllerImpl(context, optional, handler, e2);
    }

    @Override // G0.a
    public AutoDensityControllerImpl get() {
        return newInstance((Context) this.pluginContextProvider.get(), (Optional) this.sysuiContextProvider.get(), (Handler) this.uiHandlerProvider.get(), (E) this.scopeProvider.get());
    }
}
