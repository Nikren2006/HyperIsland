package miui.systemui.controlcenter.qs;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.R;

/* JADX INFO: loaded from: classes.dex */
public final class QSController$_longClickToDetail$2 extends o implements Function0 {
    final /* synthetic */ QSController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSController$_longClickToDetail$2(QSController qSController) {
        super(0);
        this.this$0 = qSController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Boolean invoke() {
        return Boolean.valueOf(this.this$0.getResources().getBoolean(R.bool.long_click_to_detail));
    }
}
