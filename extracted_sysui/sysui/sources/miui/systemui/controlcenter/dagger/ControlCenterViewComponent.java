package miui.systemui.controlcenter.dagger;

import com.android.systemui.plugins.statusbar.StatusBarStateController;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelAnimController;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelModeController;
import miui.systemui.controlcenter.windowview.BlurController;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.controlcenter.windowview.ControlCenterScreenshot;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.controlcenter.windowview.GestureDispatcher;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public interface ControlCenterViewComponent {

    public interface Factory {
        ControlCenterViewComponent create(ControlCenterWindowViewImpl controlCenterWindowViewImpl);
    }

    BlurController getBlurController();

    ControlCenterExpandController getControlCenterExpandController();

    ControlCenterScreenshot getControlCenterScreenshot();

    ControlCenterWindowViewController getControlCenterWindowViewController();

    GestureDispatcher getGestureDispatcher();

    MainPanelAnimController getMainPanelAnimController();

    MainPanelController getMainPanelController();

    MainPanelModeController getMainPanelModeController();

    SecondaryPanelRouter getSecondaryPanelRouter();

    StatusBarStateController getStatusBarStateController();
}
