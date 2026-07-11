package miui.systemui.flashlight.view;

import android.content.Context;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class MiBottomDrawUpView$finishBarHeight$2 extends o implements Function0 {
    final /* synthetic */ Context $context;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiBottomDrawUpView$finishBarHeight$2(Context context) {
        super(0);
        this.$context = context;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Double invoke() {
        return Double.valueOf(((double) (this.$context.getResources().getDisplayMetrics().density * 30)) + 0.5d);
    }
}
