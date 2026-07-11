package miui.systemui.controlcenter.panel.secondary;

import com.android.systemui.plugins.qs.DetailAdapter;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.qs.TileSpecsKt;

/* JADX INFO: loaded from: classes.dex */
public final class SecondaryParamsKt {
    public static final int FROM_BT = 150;
    public static final int FROM_CELL = 117;
    public static final int FROM_WIFI = 152;

    public static final String from(DetailAdapter detailAdapter) {
        n.g(detailAdapter, "<this>");
        int metricsCategory = detailAdapter.getMetricsCategory();
        if (metricsCategory == 117) {
            return "cell";
        }
        if (metricsCategory == 150) {
            return "bt";
        }
        if (metricsCategory != 152) {
            return null;
        }
        return TileSpecsKt.TILE_SPEC_WIFI;
    }

    public static final boolean fromBT(DetailAdapter detailAdapter) {
        n.g(detailAdapter, "<this>");
        return n.c(from(detailAdapter), "bt");
    }

    public static final boolean fromCell(DetailAdapter detailAdapter) {
        n.g(detailAdapter, "<this>");
        return n.c(from(detailAdapter), "cell");
    }

    public static final boolean fromWiFi(DetailAdapter detailAdapter) {
        n.g(detailAdapter, "<this>");
        return n.c(from(detailAdapter), TileSpecsKt.TILE_SPEC_WIFI);
    }
}
