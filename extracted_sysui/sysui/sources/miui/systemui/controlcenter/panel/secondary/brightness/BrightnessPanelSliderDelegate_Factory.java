package miui.systemui.controlcenter.panel.secondary.brightness;

import F0.e;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.miui.controlcenter.BrightnessControllerBase;
import miui.systemui.controlcenter.databinding.BrightnessPanelBinding;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.windowview.GestureDispatcher;
import miui.systemui.util.HapticFeedback;

/* JADX INFO: loaded from: classes.dex */
public final class BrightnessPanelSliderDelegate_Factory implements e {
    private final G0.a activityStarterProvider;
    private final G0.a bindingProvider;
    private final G0.a brightnessControllerProvider;
    private final G0.a gestureDispatcherProvider;
    private final G0.a hapticFeedbackProvider;
    private final G0.a secondaryBindingProvider;

    public BrightnessPanelSliderDelegate_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        this.secondaryBindingProvider = aVar;
        this.bindingProvider = aVar2;
        this.gestureDispatcherProvider = aVar3;
        this.activityStarterProvider = aVar4;
        this.brightnessControllerProvider = aVar5;
        this.hapticFeedbackProvider = aVar6;
    }

    public static BrightnessPanelSliderDelegate_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6) {
        return new BrightnessPanelSliderDelegate_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6);
    }

    public static BrightnessPanelSliderDelegate newInstance(ControlCenterSecondaryBinding controlCenterSecondaryBinding, BrightnessPanelBinding brightnessPanelBinding, GestureDispatcher gestureDispatcher, ActivityStarter activityStarter, BrightnessControllerBase brightnessControllerBase, HapticFeedback hapticFeedback) {
        return new BrightnessPanelSliderDelegate(controlCenterSecondaryBinding, brightnessPanelBinding, gestureDispatcher, activityStarter, brightnessControllerBase, hapticFeedback);
    }

    @Override // G0.a
    public BrightnessPanelSliderDelegate get() {
        return newInstance((ControlCenterSecondaryBinding) this.secondaryBindingProvider.get(), (BrightnessPanelBinding) this.bindingProvider.get(), (GestureDispatcher) this.gestureDispatcherProvider.get(), (ActivityStarter) this.activityStarterProvider.get(), (BrightnessControllerBase) this.brightnessControllerProvider.get(), (HapticFeedback) this.hapticFeedbackProvider.get());
    }
}
