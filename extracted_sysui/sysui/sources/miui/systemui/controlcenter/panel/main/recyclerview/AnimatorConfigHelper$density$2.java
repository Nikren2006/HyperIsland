package miui.systemui.controlcenter.panel.main.recyclerview;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class AnimatorConfigHelper$density$2 extends o implements Function0 {
    final /* synthetic */ AnimatorConfigHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnimatorConfigHelper$density$2(AnimatorConfigHelper animatorConfigHelper) {
        super(0);
        this.this$0 = animatorConfigHelper;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Float invoke() {
        return Float.valueOf(this.this$0.context.getResources().getDisplayMetrics().density);
    }
}
