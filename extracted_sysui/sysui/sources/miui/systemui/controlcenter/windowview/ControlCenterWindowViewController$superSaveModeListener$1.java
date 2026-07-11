package miui.systemui.controlcenter.windowview;

import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import miui.systemui.controlcenter.utils.ControlCenterUtils;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.util.MiBlurCompat;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterWindowViewController$superSaveModeListener$1 implements SuperSaveModeController.SuperSaveModeChangeListener {
    final /* synthetic */ ControlCenterWindowViewController this$0;

    public ControlCenterWindowViewController$superSaveModeListener$1(ControlCenterWindowViewController controlCenterWindowViewController) {
        this.this$0 = controlCenterWindowViewController;
    }

    public void onSuperSaveModeChange(boolean z2) {
        this.this$0.superPowerMode = z2;
        ControlCenterUtils.INSTANCE.setSuperPowerModeOn(z2);
        if (this.this$0.getInited()) {
            this.this$0.updateThemeRes();
            for (ControlCenterViewController controlCenterViewController : this.this$0.childControllers) {
                controlCenterViewController.dispatchSuperPowerModeChanged(z2);
                controlCenterViewController.dispatchConfigurationChanged(MiBlurCompat.INSTANCE.getCONFIG_BLUR());
            }
        }
    }
}
