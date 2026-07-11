package miui.systemui.controlcenter.widget;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miui.systemui.widget.RelativeSeekBarInjector;

/* JADX INFO: loaded from: classes.dex */
public final class VerticalSeekBar$injector$2 extends o implements Function0 {
    final /* synthetic */ VerticalSeekBar this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VerticalSeekBar$injector$2(VerticalSeekBar verticalSeekBar) {
        super(0);
        this.this$0 = verticalSeekBar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final RelativeSeekBarInjector invoke() {
        return new RelativeSeekBarInjector(this.this$0, false);
    }
}
