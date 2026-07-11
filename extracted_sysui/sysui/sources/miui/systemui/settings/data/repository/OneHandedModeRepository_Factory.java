package miui.systemui.settings.data.repository;

import F0.e;
import G0.a;
import android.content.Context;
import android.os.Handler;
import g1.E;
import miui.systemui.ui.data.repository.ConfigurationRepository;
import miui.systemui.util.SystemUIResourcesHelper;

/* JADX INFO: loaded from: classes4.dex */
public final class OneHandedModeRepository_Factory implements e {
    private final a bgHandlerProvider;
    private final a configurationRepositoryProvider;
    private final a contextProvider;
    private final a scopeProvider;
    private final a sysUIResHelperProvider;

    public OneHandedModeRepository_Factory(a aVar, a aVar2, a aVar3, a aVar4, a aVar5) {
        this.scopeProvider = aVar;
        this.contextProvider = aVar2;
        this.bgHandlerProvider = aVar3;
        this.configurationRepositoryProvider = aVar4;
        this.sysUIResHelperProvider = aVar5;
    }

    public static OneHandedModeRepository_Factory create(a aVar, a aVar2, a aVar3, a aVar4, a aVar5) {
        return new OneHandedModeRepository_Factory(aVar, aVar2, aVar3, aVar4, aVar5);
    }

    public static OneHandedModeRepository newInstance(E e2, Context context, Handler handler, ConfigurationRepository configurationRepository, SystemUIResourcesHelper systemUIResourcesHelper) {
        return new OneHandedModeRepository(e2, context, handler, configurationRepository, systemUIResourcesHelper);
    }

    @Override // G0.a
    public OneHandedModeRepository get() {
        return newInstance((E) this.scopeProvider.get(), (Context) this.contextProvider.get(), (Handler) this.bgHandlerProvider.get(), (ConfigurationRepository) this.configurationRepositoryProvider.get(), (SystemUIResourcesHelper) this.sysUIResHelperProvider.get());
    }
}
