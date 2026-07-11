package miui.systemui.ui.data.repository;

import F0.e;
import G0.a;
import android.content.Context;
import g1.E;
import miui.systemui.autodensity.AutoDensityController;

/* JADX INFO: loaded from: classes4.dex */
public final class ConfigurationRepository_Factory implements e {
    private final a autoDensityControllerProvider;
    private final a contextProvider;
    private final a scopeProvider;

    public ConfigurationRepository_Factory(a aVar, a aVar2, a aVar3) {
        this.scopeProvider = aVar;
        this.contextProvider = aVar2;
        this.autoDensityControllerProvider = aVar3;
    }

    public static ConfigurationRepository_Factory create(a aVar, a aVar2, a aVar3) {
        return new ConfigurationRepository_Factory(aVar, aVar2, aVar3);
    }

    public static ConfigurationRepository newInstance(E e2, Context context, AutoDensityController autoDensityController) {
        return new ConfigurationRepository(e2, context, autoDensityController);
    }

    @Override // G0.a
    public ConfigurationRepository get() {
        return newInstance((E) this.scopeProvider.get(), (Context) this.contextProvider.get(), (AutoDensityController) this.autoDensityControllerProvider.get());
    }
}
