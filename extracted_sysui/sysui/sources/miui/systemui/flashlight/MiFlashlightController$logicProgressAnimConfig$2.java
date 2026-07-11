package miui.systemui.flashlight;

import c1.f;
import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miui.systemui.util.DeviceUtils;
import miuix.animation.FolmeEase;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController$logicProgressAnimConfig$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$logicProgressAnimConfig$2(MiFlashlightController miFlashlightController) {
        super(0);
        this.this$0 = miFlashlightController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final AnimConfig invoke() {
        AnimConfig animConfig = new AnimConfig();
        final MiFlashlightController miFlashlightController = this.this$0;
        return animConfig.addListeners(new TransitionListener() { // from class: miui.systemui.flashlight.MiFlashlightController$logicProgressAnimConfig$2.1
            private final void adjustNotification() {
                int i2 = miFlashlightController.operationNotificationFlag;
                if (i2 == 1) {
                    miFlashlightController.miFlashlightManager.sendFocusNotification();
                } else if (i2 == 2) {
                    miFlashlightController.miFlashlightManager.removeFocusNotification();
                }
                miFlashlightController.operationNotificationFlag = -1;
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onCancel(Object obj) {
                super.onCancel(obj);
                adjustNotification();
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onComplete(Object obj) {
                super.onComplete(obj);
                adjustNotification();
            }

            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<? extends UpdateInfo> collection) {
                UpdateInfo updateInfoFindByName = UpdateInfo.findByName(collection, "property_render_progress");
                if (updateInfoFindByName == null) {
                    return;
                }
                miFlashlightController.logicProgress = updateInfoFindByName.getFloatValue();
                miFlashlightController.updateFlashlightUi(updateInfoFindByName.getFloatValue());
                miFlashlightController.updateSeekBarProcess(updateInfoFindByName.getFloatValue());
                if (DeviceUtils.isLowEndDevice()) {
                    miFlashlightController.updateSwitchProcess(f.h(1.0f - updateInfoFindByName.getFloatValue(), 0.0f, 1.0f), false);
                }
            }
        }).setEase(FolmeEase.spring(0.9f, 0.3f));
    }
}
