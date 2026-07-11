package miui.systemui.controlcenter.qs;

import I0.m;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class QSController$qsListEndExcludeTileSpecs$2 extends o implements Function0 {
    public static final QSController$qsListEndExcludeTileSpecs$2 INSTANCE = new QSController$qsListEndExcludeTileSpecs$2();

    public QSController$qsListEndExcludeTileSpecs$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final ArrayList<String> invoke() {
        return m.f(TileSpecsKt.TILE_SPEC_EDIT);
    }
}
