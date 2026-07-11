package miui.systemui.controlcenter.qs;

import java.util.ArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class QSController$qsListExcludeTileSpecs$2 extends o implements Function0 {
    final /* synthetic */ QSController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSController$qsListExcludeTileSpecs$2(QSController qSController) {
        super(0);
        this.this$0 = qSController;
    }

    @Override // kotlin.jvm.functions.Function0
    public final ArrayList<String> invoke() {
        ArrayList<String> arrayList = new ArrayList<>(this.this$0.getQsListStartExcludeTileSpecs());
        arrayList.addAll(this.this$0.getQsListEndExcludeTileSpecs());
        return arrayList;
    }
}
