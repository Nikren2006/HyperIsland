package miui.systemui.dynamicisland.window;

import android.content.Context;
import com.android.systemui.settings.UserTracker;
import g1.E;
import java.util.concurrent.Executor;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.ui.data.repository.ConfigurationRepository;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandWindowViewRefactor_Factory implements F0.e {
    private final G0.a configurationRepositoryProvider;
    private final G0.a contextProvider;
    private final G0.a eventCoordinatorProvider;
    private final G0.a scopeProvider;
    private final G0.a sysUIContextProvider;
    private final G0.a uiExecutorProvider;
    private final G0.a userTrackerProvider;
    private final G0.a windowViewProvider;

    public DynamicIslandWindowViewRefactor_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8) {
        this.sysUIContextProvider = aVar;
        this.contextProvider = aVar2;
        this.scopeProvider = aVar3;
        this.windowViewProvider = aVar4;
        this.eventCoordinatorProvider = aVar5;
        this.userTrackerProvider = aVar6;
        this.uiExecutorProvider = aVar7;
        this.configurationRepositoryProvider = aVar8;
    }

    public static DynamicIslandWindowViewRefactor_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8) {
        return new DynamicIslandWindowViewRefactor_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8);
    }

    public static DynamicIslandWindowViewRefactor newInstance(Context context, Context context2, E e2, DynamicIslandWindowView dynamicIslandWindowView, DynamicIslandEventCoordinator dynamicIslandEventCoordinator, UserTracker userTracker, Executor executor, ConfigurationRepository configurationRepository) {
        return new DynamicIslandWindowViewRefactor(context, context2, e2, dynamicIslandWindowView, dynamicIslandEventCoordinator, userTracker, executor, configurationRepository);
    }

    @Override // G0.a
    public DynamicIslandWindowViewRefactor get() {
        return newInstance((Context) this.sysUIContextProvider.get(), (Context) this.contextProvider.get(), (E) this.scopeProvider.get(), (DynamicIslandWindowView) this.windowViewProvider.get(), (DynamicIslandEventCoordinator) this.eventCoordinatorProvider.get(), (UserTracker) this.userTrackerProvider.get(), (Executor) this.uiExecutorProvider.get(), (ConfigurationRepository) this.configurationRepositoryProvider.get());
    }
}
