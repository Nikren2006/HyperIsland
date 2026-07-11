package miui.systemui.flashlight;

import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miuix.animation.FolmeEase;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController$switchAnimConfig$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$switchAnimConfig$2(MiFlashlightController miFlashlightController) {
        super(0);
        this.this$0 = miFlashlightController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final AnimConfig invoke() {
        AnimConfig animConfig = new AnimConfig();
        final MiFlashlightController miFlashlightController = this.this$0;
        return animConfig.addListeners(new TransitionListener() { // from class: miui.systemui.flashlight.MiFlashlightController$switchAnimConfig$2.1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<? extends UpdateInfo> collection) {
                UpdateInfo updateInfoFindByName = UpdateInfo.findByName(collection, "property_switch_progress");
                if (updateInfoFindByName == null) {
                    return;
                }
                miFlashlightController.getSwitchLottieView().setProgress(updateInfoFindByName.getFloatValue());
            }
        }).setEase(FolmeEase.cubicOut(250L));
    }
}
