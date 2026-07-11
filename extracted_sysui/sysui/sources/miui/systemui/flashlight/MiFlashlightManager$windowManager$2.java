package miui.systemui.flashlight;

import android.view.WindowManager;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightManager$windowManager$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightManager$windowManager$2(MiFlashlightManager miFlashlightManager) {
        super(0);
        this.this$0 = miFlashlightManager;
    }

    @Override // kotlin.jvm.functions.Function0
    public final WindowManager invoke() {
        Object systemService = this.this$0.context.getSystemService("window");
        n.e(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        return (WindowManager) systemService;
    }
}
