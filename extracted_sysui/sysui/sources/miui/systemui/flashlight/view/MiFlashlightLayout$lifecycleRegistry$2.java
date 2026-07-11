package miui.systemui.flashlight.view;

import androidx.lifecycle.LifecycleRegistry;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightLayout$lifecycleRegistry$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightLayout this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightLayout$lifecycleRegistry$2(MiFlashlightLayout miFlashlightLayout) {
        super(0);
        this.this$0 = miFlashlightLayout;
    }

    @Override // kotlin.jvm.functions.Function0
    public final LifecycleRegistry invoke() {
        return new LifecycleRegistry(this.this$0);
    }
}
