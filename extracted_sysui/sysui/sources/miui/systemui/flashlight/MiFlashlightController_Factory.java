package miui.systemui.flashlight;

import F0.e;
import android.content.Context;
import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import miui.systemui.flashlight.effect.MiFlashlightUiOpenGl;
import miui.systemui.flashlight.view.MiFlashlightLayout;
import miui.systemui.util.HapticFeedback;
import miui.systemui.util.TalkBackUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController_Factory implements e {
    private final G0.a contextProvider;
    private final G0.a hapticFeedbackProvider;
    private final G0.a lifecycleProvider;
    private final G0.a mainHandlerProvider;
    private final G0.a miFlashlightLayoutProvider;
    private final G0.a miFlashlightManagerProvider;
    private final G0.a miFlashlightUiProvider;
    private final G0.a talkBackUtilsProvider;

    public MiFlashlightController_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8) {
        this.contextProvider = aVar;
        this.miFlashlightLayoutProvider = aVar2;
        this.lifecycleProvider = aVar3;
        this.miFlashlightManagerProvider = aVar4;
        this.mainHandlerProvider = aVar5;
        this.miFlashlightUiProvider = aVar6;
        this.hapticFeedbackProvider = aVar7;
        this.talkBackUtilsProvider = aVar8;
    }

    public static MiFlashlightController_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3, G0.a aVar4, G0.a aVar5, G0.a aVar6, G0.a aVar7, G0.a aVar8) {
        return new MiFlashlightController_Factory(aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8);
    }

    public static MiFlashlightController newInstance(Context context, MiFlashlightLayout miFlashlightLayout, Lifecycle lifecycle, MiFlashlightManager miFlashlightManager, Handler handler, MiFlashlightUiOpenGl miFlashlightUiOpenGl, HapticFeedback hapticFeedback) {
        return new MiFlashlightController(context, miFlashlightLayout, lifecycle, miFlashlightManager, handler, miFlashlightUiOpenGl, hapticFeedback);
    }

    @Override // G0.a
    public MiFlashlightController get() {
        MiFlashlightController miFlashlightControllerNewInstance = newInstance((Context) this.contextProvider.get(), (MiFlashlightLayout) this.miFlashlightLayoutProvider.get(), (Lifecycle) this.lifecycleProvider.get(), (MiFlashlightManager) this.miFlashlightManagerProvider.get(), (Handler) this.mainHandlerProvider.get(), (MiFlashlightUiOpenGl) this.miFlashlightUiProvider.get(), (HapticFeedback) this.hapticFeedbackProvider.get());
        MiFlashlightController_MembersInjector.injectTalkBackUtils(miFlashlightControllerNewInstance, (TalkBackUtils) this.talkBackUtilsProvider.get());
        return miFlashlightControllerNewInstance;
    }
}
