package miui.systemui.flashlight;

import android.content.Context;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.flashlight.view.MiFlashlightLayout;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightActivity$flashlightLayout$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightActivity$flashlightLayout$2(MiFlashlightActivity miFlashlightActivity) {
        super(0);
        this.this$0 = miFlashlightActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    public final MiFlashlightLayout invoke() {
        MiFlashlightManager miFlashlightManager = this.this$0.flashlightManager;
        Context baseContext = this.this$0.getBaseContext();
        n.f(baseContext, "getBaseContext(...)");
        MiFlashlightLayout miFlashlightLayout = miFlashlightManager.getMiFlashlightLayout(baseContext, false);
        MiFlashlightActivity miFlashlightActivity = this.this$0;
        miFlashlightLayout.setAlpha(0.0f);
        miFlashlightLayout.setOnViewShowListener(new MiFlashlightActivity$flashlightLayout$2$1$1(miFlashlightActivity));
        return miFlashlightLayout;
    }
}
