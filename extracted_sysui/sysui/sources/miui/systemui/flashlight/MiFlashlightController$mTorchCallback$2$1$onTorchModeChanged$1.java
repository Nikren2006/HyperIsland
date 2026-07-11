package miui.systemui.flashlight;

import H0.k;
import H0.s;
import L0.d;
import N0.f;
import N0.l;
import android.text.TextUtils;
import g1.E;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.flashlight.MiFlashlightController$mTorchCallback$2$1$onTorchModeChanged$1", f = "MiFlashlightController.kt", l = {672}, m = "invokeSuspend")
public final class MiFlashlightController$mTorchCallback$2$1$onTorchModeChanged$1 extends l implements Function2 {
    final /* synthetic */ String $cameraId;
    final /* synthetic */ boolean $enabled;
    int label;
    final /* synthetic */ MiFlashlightController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightController$mTorchCallback$2$1$onTorchModeChanged$1(MiFlashlightController miFlashlightController, String str, boolean z2, d dVar) {
        super(2, dVar);
        this.this$0 = miFlashlightController;
        this.$cameraId = str;
        this.$enabled = z2;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new MiFlashlightController$mTorchCallback$2$1$onTorchModeChanged$1(this.this$0, this.$cameraId, this.$enabled, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((MiFlashlightController$mTorchCallback$2$1$onTorchModeChanged$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            MiFlashlightManager miFlashlightManager = this.this$0.miFlashlightManager;
            this.label = 1;
            obj = miFlashlightManager.getCameraId(this);
            if (obj == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
        }
        String str = (String) obj;
        if (str != null && TextUtils.equals(this.$cameraId, str)) {
            this.this$0.setSwitchStatus(N0.b.a(this.$enabled));
            this.this$0.miFlashlightUi.setStatus(this.$enabled);
            this.this$0.miFlashlightManager.saveTorchStatus(this.$enabled);
        }
        return s.f314a;
    }
}
