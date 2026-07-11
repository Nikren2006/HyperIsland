package miui.systemui.flashlight;

import android.hardware.camera2.CameraManager;
import android.util.Log;
import g1.AbstractC0369g;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightController$mTorchCallback$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$mTorchCallback$2(MiFlashlightController miFlashlightController) {
        super(0);
        this.this$0 = miFlashlightController;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [miui.systemui.flashlight.MiFlashlightController$mTorchCallback$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        final MiFlashlightController miFlashlightController = this.this$0;
        return new CameraManager.TorchCallback() { // from class: miui.systemui.flashlight.MiFlashlightController$mTorchCallback$2.1
            @Override // android.hardware.camera2.CameraManager.TorchCallback
            public void onTorchModeChanged(String cameraId, boolean z2) {
                n.g(cameraId, "cameraId");
                super.onTorchModeChanged(cameraId, z2);
                Log.d("MiFlash_MiFlashlightController", "onTorchModeChanged cameraId=" + cameraId + " enabled=" + z2 + "  Thread=" + Thread.currentThread());
                AbstractC0369g.b(miFlashlightController.mainScope, null, null, new MiFlashlightController$mTorchCallback$2$1$onTorchModeChanged$1(miFlashlightController, cameraId, z2, null), 3, null);
            }

            @Override // android.hardware.camera2.CameraManager.TorchCallback
            public void onTorchModeUnavailable(String cameraId) {
                n.g(cameraId, "cameraId");
                super.onTorchModeUnavailable(cameraId);
                if (!n.c(cameraId, "0")) {
                    Log.d("MiFlash_MiFlashlightController", "Not rear camera torch, ignore this callback, " + cameraId + " Thread=" + Thread.currentThread());
                    return;
                }
                Log.d("MiFlash_MiFlashlightController", "onTorchModeUnavailable cameraId=" + cameraId + " Thread=" + Thread.currentThread());
                miFlashlightController.miFlashlightManager.dismiss(miFlashlightController.miFlashlightLayout.hashCode());
            }

            @Override // android.hardware.camera2.CameraManager.TorchCallback
            public void onTorchStrengthLevelChanged(String cameraId, int i2) {
                n.g(cameraId, "cameraId");
                super.onTorchStrengthLevelChanged(cameraId, i2);
                Log.d("MiFlash_MiFlashlightController", "onTorchStrengthLevelChanged cameraId=" + cameraId + " newStrengthLevel=" + i2 + "  Thread=" + Thread.currentThread());
            }
        };
    }
}
