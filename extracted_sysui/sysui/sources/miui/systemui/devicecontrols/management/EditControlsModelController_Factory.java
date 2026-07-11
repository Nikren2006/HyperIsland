package miui.systemui.devicecontrols.management;

import android.content.Context;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class EditControlsModelController_Factory implements F0.e {
    private final G0.a bgExecutorProvider;
    private final G0.a contextProvider;
    private final G0.a controlsControllerProvider;
    private final G0.a controlsUiControllerProvider;
    private final G0.a mainExecutorProvider;
    private final G0.a statusBarStateControllerProvider;

    public EditControlsModelController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.contextProvider = aVar;
        this.controlsControllerProvider = aVar2;
        this.controlsUiControllerProvider = aVar3;
        this.statusBarStateControllerProvider = aVar4;
        this.mainExecutorProvider = aVar5;
        this.bgExecutorProvider = aVar6;
    }

    public static EditControlsModelController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new EditControlsModelController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static EditControlsModelController newInstance(Context context, E0.a aVar, E0.a aVar2, StatusBarStateController statusBarStateController, DelayableExecutor delayableExecutor, DelayableExecutor delayableExecutor2) {
        return new EditControlsModelController(context, aVar, aVar2, statusBarStateController, delayableExecutor, delayableExecutor2);
    }

    @Override // G0.a
    public EditControlsModelController get() {
        return newInstance((Context) this.contextProvider.get(), F0.d.a(this.controlsControllerProvider), F0.d.a(this.controlsUiControllerProvider), (StatusBarStateController) this.statusBarStateControllerProvider.get(), (DelayableExecutor) this.mainExecutorProvider.get(), (DelayableExecutor) this.bgExecutorProvider.get());
    }
}
