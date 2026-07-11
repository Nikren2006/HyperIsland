package miui.systemui.controlcenter.panel.secondary.brightness;

import F0.e;
import android.content.Context;
import android.os.Handler;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import miui.systemui.controlcenter.databinding.BrightnessPanelBinding;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.util.HapticFeedback;

/* JADX INFO: loaded from: classes.dex */
public final class BrightnessPanelTilesDelegate_Factory implements e {
    private final G0.a animatorProvider;
    private final G0.a bindingProvider;
    private final G0.a hapticFeedbackProvider;
    private final G0.a hostProvider;
    private final G0.a mainHandlerProvider;
    private final G0.a secondaryBindingProvider;
    private final G0.a sysUIContextProvider;

    public BrightnessPanelTilesDelegate_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        this.sysUIContextProvider = aVar;
        this.mainHandlerProvider = aVar2;
        this.secondaryBindingProvider = aVar3;
        this.bindingProvider = aVar4;
        this.hostProvider = aVar5;
        this.hapticFeedbackProvider = aVar6;
        this.animatorProvider = aVar7;
    }

    public static BrightnessPanelTilesDelegate_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7) {
        return new BrightnessPanelTilesDelegate_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7);
    }

    public static BrightnessPanelTilesDelegate newInstance(Context context, Handler handler, ControlCenterSecondaryBinding controlCenterSecondaryBinding, BrightnessPanelBinding brightnessPanelBinding, MiuiQSHost miuiQSHost, HapticFeedback hapticFeedback, BrightnessPanelAnimator brightnessPanelAnimator) {
        return new BrightnessPanelTilesDelegate(context, handler, controlCenterSecondaryBinding, brightnessPanelBinding, miuiQSHost, hapticFeedback, brightnessPanelAnimator);
    }

    @Override // G0.a
    public BrightnessPanelTilesDelegate get() {
        return newInstance((Context) this.sysUIContextProvider.get(), (Handler) this.mainHandlerProvider.get(), (ControlCenterSecondaryBinding) this.secondaryBindingProvider.get(), (BrightnessPanelBinding) this.bindingProvider.get(), (MiuiQSHost) this.hostProvider.get(), (HapticFeedback) this.hapticFeedbackProvider.get(), (BrightnessPanelAnimator) this.animatorProvider.get());
    }
}
