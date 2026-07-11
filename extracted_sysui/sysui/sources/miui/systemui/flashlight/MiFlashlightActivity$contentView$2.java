package miui.systemui.flashlight;

import android.widget.FrameLayout;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightActivity$contentView$2 extends o implements Function0 {
    final /* synthetic */ MiFlashlightActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiFlashlightActivity$contentView$2(MiFlashlightActivity miFlashlightActivity) {
        super(0);
        this.this$0 = miFlashlightActivity;
    }

    @Override // kotlin.jvm.functions.Function0
    public final FrameLayout invoke() {
        FrameLayout frameLayout = new FrameLayout(this.this$0);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return frameLayout;
    }
}
