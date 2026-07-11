package miui.systemui.controlcenter.qs.customize;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.R;

/* JADX INFO: loaded from: classes.dex */
public final class TileQueryHelper$systemTilePackageList$2 extends o implements Function0 {
    final /* synthetic */ TileQueryHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TileQueryHelper$systemTilePackageList$2(TileQueryHelper tileQueryHelper) {
        super(0);
        this.this$0 = tileQueryHelper;
    }

    @Override // kotlin.jvm.functions.Function0
    public final String[] invoke() {
        return this.this$0.context.getResources().getStringArray(R.array.qs_system_tile_package_default);
    }
}
