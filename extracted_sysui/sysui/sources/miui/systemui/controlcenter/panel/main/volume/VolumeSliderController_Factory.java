package miui.systemui.controlcenter.panel.main.volume;

import android.os.Looper;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.controlcenter.BrightnessControllerBase;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import java.util.concurrent.Executor;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.controlcenter.panel.main.recyclerview.ToggleSliderViewHolder;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes.dex */
public final class VolumeSliderController_Factory implements F0.e {
    private final G0.a bgExecutorProvider;
    private final G0.a bgLooperProvider;
    private final G0.a brightnessControllerProvider;
    private final G0.a broadcastDispatcherProvider;
    private final G0.a controlCenterWindowViewControllerProvider;
    private final G0.a hapticFeedbackProvider;
    private final G0.a itemFactoryProvider;
    private final G0.a lifecycleProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a mainPanelModeControllerProvider;
    private final G0.a mainPanelStyleControllerProvider;
    private final G0.a msgHeaderProvider;
    private final G0.a secondaryPanelRouterProvider;
    private final G0.a superSaveModeControllerProvider;
    private final G0.a uiExecutorProvider;
    private final G0.a uiLooperProvider;
    private final G0.a volumePanelControllerProvider;
    private final G0.a windowViewProvider;

    public VolumeSliderController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14, G0.a aVar15, G0.a aVar16, G0.a aVar17, G0.a aVar18) {
        this.windowViewProvider = aVar;
        this.lifecycleProvider = aVar2;
        this.itemFactoryProvider = aVar3;
        this.uiLooperProvider = aVar4;
        this.uiExecutorProvider = aVar5;
        this.bgLooperProvider = aVar6;
        this.bgExecutorProvider = aVar7;
        this.broadcastDispatcherProvider = aVar8;
        this.hapticFeedbackProvider = aVar9;
        this.controlCenterWindowViewControllerProvider = aVar10;
        this.mainPanelControllerProvider = aVar11;
        this.mainPanelStyleControllerProvider = aVar12;
        this.mainPanelModeControllerProvider = aVar13;
        this.secondaryPanelRouterProvider = aVar14;
        this.volumePanelControllerProvider = aVar15;
        this.msgHeaderProvider = aVar16;
        this.brightnessControllerProvider = aVar17;
        this.superSaveModeControllerProvider = aVar18;
    }

    public static VolumeSliderController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14, G0.a aVar15, G0.a aVar16, G0.a aVar17, G0.a aVar18) {
        return new VolumeSliderController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13, aVar14, aVar15, aVar16, aVar17, aVar18);
    }

    public static VolumeSliderController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, Lifecycle lifecycle, ToggleSliderViewHolder.Factory factory, Looper looper, Executor executor, Looper looper2, DelayableExecutor delayableExecutor, BroadcastDispatcher broadcastDispatcher, HapticFeedback hapticFeedback, E0.a aVar, E0.a aVar2, E0.a aVar3, E0.a aVar4, E0.a aVar5, E0.a aVar6, E0.a aVar7, BrightnessControllerBase brightnessControllerBase, SuperSaveModeController superSaveModeController) {
        return new VolumeSliderController(controlCenterWindowViewImpl, lifecycle, factory, looper, executor, looper2, delayableExecutor, broadcastDispatcher, hapticFeedback, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, brightnessControllerBase, superSaveModeController);
    }

    @Override // G0.a
    public VolumeSliderController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (Lifecycle) this.lifecycleProvider.get(), (ToggleSliderViewHolder.Factory) this.itemFactoryProvider.get(), (Looper) this.uiLooperProvider.get(), (Executor) this.uiExecutorProvider.get(), (Looper) this.bgLooperProvider.get(), (DelayableExecutor) this.bgExecutorProvider.get(), (BroadcastDispatcher) this.broadcastDispatcherProvider.get(), (HapticFeedback) this.hapticFeedbackProvider.get(), F0.d.a(this.controlCenterWindowViewControllerProvider), F0.d.a(this.mainPanelControllerProvider), F0.d.a(this.mainPanelStyleControllerProvider), F0.d.a(this.mainPanelModeControllerProvider), F0.d.a(this.secondaryPanelRouterProvider), F0.d.a(this.volumePanelControllerProvider), F0.d.a(this.msgHeaderProvider), (BrightnessControllerBase) this.brightnessControllerProvider.get(), (SuperSaveModeController) this.superSaveModeControllerProvider.get());
    }
}
