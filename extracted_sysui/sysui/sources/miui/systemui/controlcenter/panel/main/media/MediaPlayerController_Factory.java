package miui.systemui.controlcenter.panel.main.media;

import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.settings.IUserTracker;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import java.util.Optional;
import miui.systemui.controlcenter.media.MediaPlayerAdapter;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.util.HapticFeedback;

/* JADX INFO: loaded from: classes.dex */
public final class MediaPlayerController_Factory implements F0.e {
    private final G0.a contentControllerProvider;
    private final G0.a hapticFeedbackProvider;
    private final G0.a lifecycleProvider;
    private final G0.a mainPanelModeControllerProvider;
    private final G0.a mainPanelStyleControllerProvider;
    private final G0.a mediaPlayerAdapterOptionalProvider;
    private final G0.a secondaryPanelRouterProvider;
    private final G0.a superSaveModeControllerProvider;
    private final G0.a userTrackerProvider;
    private final G0.a windowViewProvider;

    public MediaPlayerController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10) {
        this.windowViewProvider = aVar;
        this.lifecycleProvider = aVar2;
        this.secondaryPanelRouterProvider = aVar3;
        this.mediaPlayerAdapterOptionalProvider = aVar4;
        this.mainPanelStyleControllerProvider = aVar5;
        this.mainPanelModeControllerProvider = aVar6;
        this.hapticFeedbackProvider = aVar7;
        this.contentControllerProvider = aVar8;
        this.superSaveModeControllerProvider = aVar9;
        this.userTrackerProvider = aVar10;
    }

    public static MediaPlayerController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8, G0.a aVar9, G0.a aVar10) {
        return new MediaPlayerController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, aVar10);
    }

    public static MediaPlayerController newInstance(ControlCenterWindowViewImpl controlCenterWindowViewImpl, Lifecycle lifecycle, E0.a aVar, Optional<MediaPlayerAdapter> optional, E0.a aVar2, E0.a aVar3, HapticFeedback hapticFeedback, E0.a aVar4, SuperSaveModeController superSaveModeController, IUserTracker iUserTracker) {
        return new MediaPlayerController(controlCenterWindowViewImpl, lifecycle, aVar, optional, aVar2, aVar3, hapticFeedback, aVar4, superSaveModeController, iUserTracker);
    }

    @Override // G0.a
    public MediaPlayerController get() {
        return newInstance((ControlCenterWindowViewImpl) this.windowViewProvider.get(), (Lifecycle) this.lifecycleProvider.get(), F0.d.a(this.secondaryPanelRouterProvider), (Optional) this.mediaPlayerAdapterOptionalProvider.get(), F0.d.a(this.mainPanelStyleControllerProvider), F0.d.a(this.mainPanelModeControllerProvider), (HapticFeedback) this.hapticFeedbackProvider.get(), F0.d.a(this.contentControllerProvider), (SuperSaveModeController) this.superSaveModeControllerProvider.get(), (IUserTracker) this.userTrackerProvider.get());
    }
}
