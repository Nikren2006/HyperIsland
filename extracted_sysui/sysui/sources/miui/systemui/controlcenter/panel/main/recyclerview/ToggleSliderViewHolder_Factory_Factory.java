package miui.systemui.controlcenter.panel.main.recyclerview;

import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.miui.controlcenter.BrightnessControllerBase;
import com.android.systemui.plugins.miui.shade.ShadeSwitchController;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import miui.systemui.controlcenter.panel.main.recyclerview.ToggleSliderViewHolder;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.controlcenter.windowview.GestureDispatcher;
import miui.systemui.util.BlurUtilsExt;

/* JADX INFO: loaded from: classes.dex */
public final class ToggleSliderViewHolder_Factory_Factory implements F0.e {
    private final G0.a activityStarterProvider;
    private final G0.a blurUtilsExtProvider;
    private final G0.a brightnessControllerProvider;
    private final G0.a expandControllerProvider;
    private final G0.a gestureDispatcherProvider;
    private final G0.a shadeSwitchControllerProvider;
    private final G0.a statusBarStateControllerProvider;

    public ToggleSliderViewHolder_Factory_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        this.gestureDispatcherProvider = aVar;
        this.activityStarterProvider = aVar2;
        this.shadeSwitchControllerProvider = aVar3;
        this.brightnessControllerProvider = aVar4;
        this.statusBarStateControllerProvider = aVar5;
        this.blurUtilsExtProvider = aVar6;
        this.expandControllerProvider = aVar7;
    }

    public static ToggleSliderViewHolder_Factory_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        return new ToggleSliderViewHolder_Factory_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7);
    }

    public static ToggleSliderViewHolder.Factory newInstance(GestureDispatcher gestureDispatcher, ActivityStarter activityStarter, ShadeSwitchController shadeSwitchController, BrightnessControllerBase brightnessControllerBase, StatusBarStateController statusBarStateController, BlurUtilsExt blurUtilsExt, ControlCenterExpandController controlCenterExpandController) {
        return new ToggleSliderViewHolder.Factory(gestureDispatcher, activityStarter, shadeSwitchController, brightnessControllerBase, statusBarStateController, blurUtilsExt, controlCenterExpandController);
    }

    @Override // G0.a
    public ToggleSliderViewHolder.Factory get() {
        return newInstance((GestureDispatcher) this.gestureDispatcherProvider.get(), (ActivityStarter) this.activityStarterProvider.get(), (ShadeSwitchController) this.shadeSwitchControllerProvider.get(), (BrightnessControllerBase) this.brightnessControllerProvider.get(), (StatusBarStateController) this.statusBarStateControllerProvider.get(), (BlurUtilsExt) this.blurUtilsExtProvider.get(), (ControlCenterExpandController) this.expandControllerProvider.get());
    }
}
