package miui.systemui.statusbar.data.repository;

import F0.e;
import G0.a;
import android.content.Context;
import g1.E;
import miui.systemui.settings.data.repository.OneHandedModeRepository;
import miui.systemui.ui.data.repository.ConfigurationRepository;

/* JADX INFO: loaded from: classes4.dex */
public final class StatusBarAreaRepository_Factory implements e {
    private final a configurationRepositoryProvider;
    private final a contextProvider;
    private final a oneHandedModeRepositoryProvider;
    private final a scopeProvider;

    public StatusBarAreaRepository_Factory(a aVar, a aVar2, a aVar3, a aVar4) {
        this.scopeProvider = aVar;
        this.contextProvider = aVar2;
        this.configurationRepositoryProvider = aVar3;
        this.oneHandedModeRepositoryProvider = aVar4;
    }

    public static StatusBarAreaRepository_Factory create(a aVar, a aVar2, a aVar3, a aVar4) {
        return new StatusBarAreaRepository_Factory(aVar, aVar2, aVar3, aVar4);
    }

    public static StatusBarAreaRepository newInstance(E e2, Context context, ConfigurationRepository configurationRepository, OneHandedModeRepository oneHandedModeRepository) {
        return new StatusBarAreaRepository(e2, context, configurationRepository, oneHandedModeRepository);
    }

    @Override // G0.a
    public StatusBarAreaRepository get() {
        return newInstance((E) this.scopeProvider.get(), (Context) this.contextProvider.get(), (ConfigurationRepository) this.configurationRepositoryProvider.get(), (OneHandedModeRepository) this.oneHandedModeRepositoryProvider.get());
    }
}
