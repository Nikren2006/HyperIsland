package miui.systemui.flashlight;

import H0.s;
import android.util.Log;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightActivity$hideBgAnimConfig$2$1$onComplete$1 extends o implements Function0 {
    final /* synthetic */ MiFlashlightActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightActivity$hideBgAnimConfig$2$1$onComplete$1(MiFlashlightActivity miFlashlightActivity) {
        super(0);
        this.this$0 = miFlashlightActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Object invoke() {
        m136invoke();
        return s.f314a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m136invoke() {
        Log.i("MiFlash_MiFlashlightActivity", "Background hide anim complete!");
        this.this$0.getWindow().getDecorView().setVisibility(8);
        this.this$0.finish();
    }
}
