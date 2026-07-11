package miui.systemui.controlcenter.utils;

import H0.d;
import H0.e;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import java.lang.reflect.Method;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class MiuiQSHostCompat {
    public static final MiuiQSHostCompat INSTANCE = new MiuiQSHostCompat();
    private static final d GET_STOCK_TILES$delegate = e.b(MiuiQSHostCompat$GET_STOCK_TILES$2.INSTANCE);

    private MiuiQSHostCompat() {
    }

    private final Method getGET_STOCK_TILES() {
        return (Method) GET_STOCK_TILES$delegate.getValue();
    }

    public final String getStockTilesCompat(MiuiQSHost miuiQSHost) {
        n.g(miuiQSHost, "<this>");
        try {
            Method get_stock_tiles = getGET_STOCK_TILES();
            Object objInvoke = get_stock_tiles != null ? get_stock_tiles.invoke(miuiQSHost, null) : null;
            if (objInvoke instanceof String) {
                return (String) objInvoke;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
