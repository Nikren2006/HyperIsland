package miui.systemui.controlcenter.windowview;

import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import com.android.systemui.plugins.miui.shade.ShadeSwitchController;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.utils.ControlCenterViewController;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class ControlCenterSuperPowerController extends ControlCenterViewController<ControlCenterWindowViewImpl> {
    private final ControlCenterExpandController expandController;
    private final ShadeSwitchController shadeSwitchController;
    private boolean superPowerMode;
    private final SuperSaveModeController superSaveModeController;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlCenterSuperPowerController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, SuperSaveModeController superSaveModeController, ShadeSwitchController shadeSwitchController, ControlCenterExpandController expandController) {
        super(windowView);
        kotlin.jvm.internal.n.g(windowView, "windowView");
        kotlin.jvm.internal.n.g(superSaveModeController, "superSaveModeController");
        kotlin.jvm.internal.n.g(shadeSwitchController, "shadeSwitchController");
        kotlin.jvm.internal.n.g(expandController, "expandController");
        this.superSaveModeController = superSaveModeController;
        this.shadeSwitchController = shadeSwitchController;
        this.expandController = expandController;
        this.superPowerMode = superSaveModeController.isActive();
    }

    private final void setSuperPowerMode(boolean z2) {
        if (this.superPowerMode == z2) {
            return;
        }
        this.superPowerMode = z2;
        if (this.shadeSwitchController.getSwitching()) {
            this.expandController.hidePanel(true, true);
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onSuperPowerModeChanged(boolean z2) {
        setSuperPowerMode(z2);
    }
}
