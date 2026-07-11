package miui.systemui.flashlight;

import H0.s;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightActivity$showBgAnimConfig$2$1$onComplete$1 extends o implements Function0 {
    final /* synthetic */ MiFlashlightActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightActivity$showBgAnimConfig$2$1$onComplete$1(MiFlashlightActivity miFlashlightActivity) {
        super(0);
        this.this$0 = miFlashlightActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m137invoke();
        return s.f314a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m137invoke() {
        if (this.this$0.isShowBgAnimRunning) {
            this.this$0.isShowBgAnimRunning = false;
            this.this$0.showFlashlightAnim();
        }
    }
}
