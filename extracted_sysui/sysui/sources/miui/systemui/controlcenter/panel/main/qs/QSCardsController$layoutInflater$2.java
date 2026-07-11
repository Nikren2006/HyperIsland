package miui.systemui.controlcenter.panel.main.qs;

import android.view.LayoutInflater;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class QSCardsController$layoutInflater$2 extends o implements Function0 {
    final /* synthetic */ QSCardsController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSCardsController$layoutInflater$2(QSCardsController qSCardsController) {
        super(0);
        this.this$0 = qSCardsController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final LayoutInflater invoke() {
        return LayoutInflater.from(this.this$0.getContext());
    }
}
