package miui.systemui.devicecontrols.management;

import android.content.Context;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import miui.systemui.devicecontrols.CustomIconCache;
import miui.systemui.devicecontrols.ui.ControlActionCoordinator;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class ViewHolderFactory_Factory implements F0.e {
    private final G0.a activityStarterProvider;
    private final G0.a bgExecutorProvider;
    private final G0.a contextProvider;
    private final G0.a controlActionCoordinatorProvider;
    private final G0.a controlsControllerProvider;
    private final G0.a editControlsModelControllerProvider;
    private final G0.a iconCacheProvider;
    private final G0.a statusBarStateControllerProvider;
    private final G0.a uiExecutorProvider;

    public ViewHolderFactory_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9) {
        this.contextProvider = aVar;
        this.controlsControllerProvider = aVar2;
        this.uiExecutorProvider = aVar3;
        this.bgExecutorProvider = aVar4;
        this.activityStarterProvider = aVar5;
        this.iconCacheProvider = aVar6;
        this.controlActionCoordinatorProvider = aVar7;
        this.editControlsModelControllerProvider = aVar8;
        this.statusBarStateControllerProvider = aVar9;
    }

    public static ViewHolderFactory_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9) {
        return new ViewHolderFactory_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9);
    }

    public static ViewHolderFactory newInstance(Context context, E0.a aVar, DelayableExecutor delayableExecutor, DelayableExecutor delayableExecutor2, ActivityStarter activityStarter, CustomIconCache customIconCache, ControlActionCoordinator controlActionCoordinator, EditControlsModelController editControlsModelController, StatusBarStateController statusBarStateController) {
        return new ViewHolderFactory(context, aVar, delayableExecutor, delayableExecutor2, activityStarter, customIconCache, controlActionCoordinator, editControlsModelController, statusBarStateController);
    }

    @Override // G0.a
    public ViewHolderFactory get() {
        return newInstance((Context) this.contextProvider.get(), F0.d.a(this.controlsControllerProvider), (DelayableExecutor) this.uiExecutorProvider.get(), (DelayableExecutor) this.bgExecutorProvider.get(), (ActivityStarter) this.activityStarterProvider.get(), (CustomIconCache) this.iconCacheProvider.get(), (ControlActionCoordinator) this.controlActionCoordinatorProvider.get(), (EditControlsModelController) this.editControlsModelControllerProvider.get(), (StatusBarStateController) this.statusBarStateControllerProvider.get());
    }
}
