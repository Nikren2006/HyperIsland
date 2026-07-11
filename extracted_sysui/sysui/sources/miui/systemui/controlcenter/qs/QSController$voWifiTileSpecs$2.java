package miui.systemui.controlcenter.qs;

import I0.AbstractC0181i;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.R;

/* JADX INFO: loaded from: classes.dex */
public final class QSController$voWifiTileSpecs$2 extends o implements Function0 {
    final /* synthetic */ QSController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSController$voWifiTileSpecs$2(QSController qSController) {
        super(0);
        this.this$0 = qSController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<String> invoke() {
        String[] stringArray = this.this$0.getResources().getStringArray(R.array.vowifi_tiles);
        n.f(stringArray, "getStringArray(...)");
        return AbstractC0181i.S(stringArray);
    }
}
