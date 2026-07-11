package miui.systemui.devicecontrols.ui;

import android.content.Context;
import android.content.SharedPreferences;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import miui.systemui.devicecontrols.CustomIconCache;
import miui.systemui.devicecontrols.management.EditControlsModelController;
import miui.systemui.devicecontrols.management.ViewHolderFactory;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class MiuiControlsUiControllerImpl_Factory implements F0.e {
    private final G0.a activityStarterProvider;
    private final G0.a bgExecutorProvider;
    private final G0.a contextProvider;
    private final G0.a controlActionCoordinatorProvider;
    private final G0.a controlsControllerProvider;
    private final G0.a controlsListingControllerProvider;
    private final G0.a editControlsModelControllerProvider;
    private final G0.a iconCacheProvider;
    private final G0.a sharedPreferencesProvider;
    private final G0.a statusBarStateControllerProvider;
    private final G0.a uiExecutorProvider;
    private final G0.a viewHolderFactoryProvider;

    public MiuiControlsUiControllerImpl_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12) {
        this.controlsControllerProvider = aVar;
        this.contextProvider = aVar2;
        this.uiExecutorProvider = aVar3;
        this.bgExecutorProvider = aVar4;
        this.controlsListingControllerProvider = aVar5;
        this.sharedPreferencesProvider = aVar6;
        this.controlActionCoordinatorProvider = aVar7;
        this.activityStarterProvider = aVar8;
        this.iconCacheProvider = aVar9;
        this.statusBarStateControllerProvider = aVar10;
        this.viewHolderFactoryProvider = aVar11;
        this.editControlsModelControllerProvider = aVar12;
    }

    public static MiuiControlsUiControllerImpl_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12) {
        return new MiuiControlsUiControllerImpl_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12);
    }

    public static MiuiControlsUiControllerImpl newInstance(E0.a aVar, Context context, DelayableExecutor delayableExecutor, DelayableExecutor delayableExecutor2, E0.a aVar2, SharedPreferences sharedPreferences, ControlActionCoordinator controlActionCoordinator, ActivityStarter activityStarter, CustomIconCache customIconCache, StatusBarStateController statusBarStateController, ViewHolderFactory viewHolderFactory, EditControlsModelController editControlsModelController) {
        return new MiuiControlsUiControllerImpl(aVar, context, delayableExecutor, delayableExecutor2, aVar2, sharedPreferences, controlActionCoordinator, activityStarter, customIconCache, statusBarStateController, viewHolderFactory, editControlsModelController);
    }

    @Override // G0.a
    public MiuiControlsUiControllerImpl get() {
        return newInstance(F0.d.a(this.controlsControllerProvider), (Context) this.contextProvider.get(), (DelayableExecutor) this.uiExecutorProvider.get(), (DelayableExecutor) this.bgExecutorProvider.get(), F0.d.a(this.controlsListingControllerProvider), (SharedPreferences) this.sharedPreferencesProvider.get(), (ControlActionCoordinator) this.controlActionCoordinatorProvider.get(), (ActivityStarter) this.activityStarterProvider.get(), (CustomIconCache) this.iconCacheProvider.get(), (StatusBarStateController) this.statusBarStateControllerProvider.get(), (ViewHolderFactory) this.viewHolderFactoryProvider.get(), (EditControlsModelController) this.editControlsModelControllerProvider.get());
    }
}
