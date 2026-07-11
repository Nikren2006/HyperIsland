package miui.systemui.controlcenter.qs;

import I0.AbstractC0181i;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.R;

/* JADX INFO: loaded from: classes.dex */
public final class QSController$cardStyleTileSpecs$2 extends o implements Function0 {
    final /* synthetic */ QSController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSController$cardStyleTileSpecs$2(QSController qSController) {
        super(0);
        this.this$0 = qSController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final List<String> invoke() {
        String[] stringArray = this.this$0.getResources().getStringArray(this.this$0.supportMobileTilesStyle() ? R.array.card_style_tiles_mobile : R.array.card_style_tiles_wifi);
        n.f(stringArray, "getStringArray(...)");
        return AbstractC0181i.S(stringArray);
    }
}
