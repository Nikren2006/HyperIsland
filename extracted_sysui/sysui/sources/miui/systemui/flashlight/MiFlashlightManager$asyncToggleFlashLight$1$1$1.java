package miui.systemui.flashlight;

import H0.k;
import H0.s;
import L0.d;
import N0.f;
import N0.l;
import android.content.Intent;
import g1.E;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.flashlight.MiFlashlightManager$asyncToggleFlashLight$1$1$1", f = "MiFlashlightManager.kt", l = {231}, m = "invokeSuspend")
public final class MiFlashlightManager$asyncToggleFlashLight$1$1$1 extends l implements Function2 {
    final /* synthetic */ String $id;
    final /* synthetic */ int $strength;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ MiFlashlightManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightManager$asyncToggleFlashLight$1$1$1(String str, int i2, MiFlashlightManager miFlashlightManager, d dVar) {
        super(2, dVar);
        this.$id = str;
        this.$strength = i2;
        this.this$0 = miFlashlightManager;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new MiFlashlightManager$asyncToggleFlashLight$1$1$1(this.$id, this.$strength, this.this$0, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((MiFlashlightManager$asyncToggleFlashLight$1$1$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Intent intent;
        Intent intent2;
        String str;
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            intent = new Intent(MiFlashlightOperationReceiver.ACTION_OPERATION);
            intent.putExtra(MiFlashlightOperationReceiver.KEY_CAMERA_ID, this.$id);
            intent.putExtra(MiFlashlightOperationReceiver.KEY_STRENGTH, this.$strength);
            MiFlashlightManager miFlashlightManager = this.this$0;
            this.L$0 = intent;
            this.L$1 = intent;
            this.L$2 = MiFlashlightOperationReceiver.KEY_IS_CAN_USE_STRENGTH_LEVEL;
            this.label = 1;
            obj = miFlashlightManager.isSupportStrengthLevel(this);
            if (obj == objC) {
                return objC;
            }
            intent2 = intent;
            str = MiFlashlightOperationReceiver.KEY_IS_CAN_USE_STRENGTH_LEVEL;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            str = (String) this.L$2;
            intent = (Intent) this.L$1;
            intent2 = (Intent) this.L$0;
            k.b(obj);
        }
        intent.putExtra(str, ((Boolean) obj).booleanValue());
        this.this$0.context.sendBroadcast(intent2);
        this.this$0.curStrength = this.$strength;
        return s.f314a;
    }
}
