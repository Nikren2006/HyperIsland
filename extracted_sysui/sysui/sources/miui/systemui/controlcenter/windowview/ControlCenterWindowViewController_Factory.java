package miui.systemui.controlcenter.windowview;

import android.os.Handler;
import android.os.UserManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleCoroutineScope;
import com.android.systemui.plugins.miui.dump.PluginDumpManager;
import com.android.systemui.plugins.miui.settings.IUserTracker;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import java.util.concurrent.Executor;
import miui.systemui.autodensity.AutoDensityController;
import miui.systemui.broadcast.BroadcastDispatcher;
import miui.systemui.controlcenter.databinding.ControlCenterBinding;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.secondary.brightness.BrightnessPanelController;
import miui.systemui.controlcenter.panel.secondary.media.MediaPanelController;
import miui.systemui.controlcenter.panel.secondary.volume.VolumePanelController;
import miui.systemui.util.BlurUtilsExt;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterWindowViewController_Factory implements F0.e {
    private final G0.a autoDensityControllerProvider;
    private final G0.a bgExecutorProvider;
    private final G0.a bindingProvider;
    private final G0.a blurControllerProvider;
    private final G0.a blurUtilsExtProvider;
    private final G0.a brightnessPanelControllerProvider;
    private final G0.a broadcastDispatcherProvider;
    private final G0.a controlCenterEventTrackerProvider;
    private final G0.a dumpManagerProvider;
    private final G0.a expandControllerProvider;
    private final G0.a gestureDispatcherProvider;
    private final G0.a lifecycleProvider;
    private final G0.a mainHandlerProvider;
    private final G0.a mainPanelControllerProvider;
    private final G0.a mediaPanelControllerProvider;
    private final G0.a scopeProvider;
    private final G0.a screenshotProvider;
    private final G0.a secondaryPanelRouterProvider;
    private final G0.a statusBarStateControllerProvider;
    private final G0.a superPowerControllerProvider;
    private final G0.a superSaveModeControllerProvider;
    private final G0.a uiExecutorProvider;
    private final G0.a userManagerProvider;
    private final G0.a userTrackerProvider;
    private final G0.a volumePanelControllerProvider;

    public ControlCenterWindowViewController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14, G0.a aVar15, G0.a aVar16, G0.a aVar17, G0.a aVar18, G0.a aVar19, G0.a aVar20, G0.a aVar21, G0.a aVar22, G0.a aVar23, G0.a aVar24, G0.a aVar25) {
        this.bindingProvider = aVar;
        this.lifecycleProvider = aVar2;
        this.broadcastDispatcherProvider = aVar3;
        this.bgExecutorProvider = aVar4;
        this.mainHandlerProvider = aVar5;
        this.uiExecutorProvider = aVar6;
        this.scopeProvider = aVar7;
        this.screenshotProvider = aVar8;
        this.expandControllerProvider = aVar9;
        this.mainPanelControllerProvider = aVar10;
        this.blurControllerProvider = aVar11;
        this.gestureDispatcherProvider = aVar12;
        this.secondaryPanelRouterProvider = aVar13;
        this.userManagerProvider = aVar14;
        this.statusBarStateControllerProvider = aVar15;
        this.autoDensityControllerProvider = aVar16;
        this.controlCenterEventTrackerProvider = aVar17;
        this.mediaPanelControllerProvider = aVar18;
        this.brightnessPanelControllerProvider = aVar19;
        this.volumePanelControllerProvider = aVar20;
        this.userTrackerProvider = aVar21;
        this.superSaveModeControllerProvider = aVar22;
        this.dumpManagerProvider = aVar23;
        this.superPowerControllerProvider = aVar24;
        this.blurUtilsExtProvider = aVar25;
    }

    public static ControlCenterWindowViewController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10, G0.a aVar11, G0.a aVar12, G0.a aVar13, G0.a aVar14, G0.a aVar15, G0.a aVar16, G0.a aVar17, G0.a aVar18, G0.a aVar19, G0.a aVar20, G0.a aVar21, G0.a aVar22, G0.a aVar23, G0.a aVar24, G0.a aVar25) {
        return new ControlCenterWindowViewController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10, aVar11, aVar12, aVar13, aVar14, aVar15, aVar16, aVar17, aVar18, aVar19, aVar20, aVar21, aVar22, aVar23, aVar24, aVar25);
    }

    public static ControlCenterWindowViewController newInstance(ControlCenterBinding controlCenterBinding, Lifecycle lifecycle, BroadcastDispatcher broadcastDispatcher, Executor executor, Handler handler, Executor executor2, LifecycleCoroutineScope lifecycleCoroutineScope, ControlCenterScreenshot controlCenterScreenshot, ControlCenterExpandController controlCenterExpandController, MainPanelController mainPanelController, BlurController blurController, GestureDispatcher gestureDispatcher, SecondaryPanelRouter secondaryPanelRouter, UserManager userManager, StatusBarStateController statusBarStateController, AutoDensityController autoDensityController, ControlCenterEventTracker controlCenterEventTracker, MediaPanelController mediaPanelController, BrightnessPanelController brightnessPanelController, VolumePanelController volumePanelController, IUserTracker iUserTracker, SuperSaveModeController superSaveModeController, PluginDumpManager pluginDumpManager, ControlCenterSuperPowerController controlCenterSuperPowerController, BlurUtilsExt blurUtilsExt) {
        return new ControlCenterWindowViewController(controlCenterBinding, lifecycle, broadcastDispatcher, executor, handler, executor2, lifecycleCoroutineScope, controlCenterScreenshot, controlCenterExpandController, mainPanelController, blurController, gestureDispatcher, secondaryPanelRouter, userManager, statusBarStateController, autoDensityController, controlCenterEventTracker, mediaPanelController, brightnessPanelController, volumePanelController, iUserTracker, superSaveModeController, pluginDumpManager, controlCenterSuperPowerController, blurUtilsExt);
    }

    @Override // G0.a
    public ControlCenterWindowViewController get() {
        return newInstance((ControlCenterBinding) this.bindingProvider.get(), (Lifecycle) this.lifecycleProvider.get(), (BroadcastDispatcher) this.broadcastDispatcherProvider.get(), (Executor) this.bgExecutorProvider.get(), (Handler) this.mainHandlerProvider.get(), (Executor) this.uiExecutorProvider.get(), (LifecycleCoroutineScope) this.scopeProvider.get(), (ControlCenterScreenshot) this.screenshotProvider.get(), (ControlCenterExpandController) this.expandControllerProvider.get(), (MainPanelController) this.mainPanelControllerProvider.get(), (BlurController) this.blurControllerProvider.get(), (GestureDispatcher) this.gestureDispatcherProvider.get(), (SecondaryPanelRouter) this.secondaryPanelRouterProvider.get(), (UserManager) this.userManagerProvider.get(), (StatusBarStateController) this.statusBarStateControllerProvider.get(), (AutoDensityController) this.autoDensityControllerProvider.get(), (ControlCenterEventTracker) this.controlCenterEventTrackerProvider.get(), (MediaPanelController) this.mediaPanelControllerProvider.get(), (BrightnessPanelController) this.brightnessPanelControllerProvider.get(), (VolumePanelController) this.volumePanelControllerProvider.get(), (IUserTracker) this.userTrackerProvider.get(), (SuperSaveModeController) this.superSaveModeControllerProvider.get(), (PluginDumpManager) this.dumpManagerProvider.get(), (ControlCenterSuperPowerController) this.superPowerControllerProvider.get(), (BlurUtilsExt) this.blurUtilsExtProvider.get());
    }
}
