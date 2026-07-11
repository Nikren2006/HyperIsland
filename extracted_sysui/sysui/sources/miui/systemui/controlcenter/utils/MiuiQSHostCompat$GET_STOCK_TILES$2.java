package miui.systemui.controlcenter.utils;

import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class MiuiQSHostCompat$GET_STOCK_TILES$2 extends o implements Function0 {
    public static final MiuiQSHostCompat$GET_STOCK_TILES$2 INSTANCE = new MiuiQSHostCompat$GET_STOCK_TILES$2();

    public MiuiQSHostCompat$GET_STOCK_TILES$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            return MiuiQSHost.class.getMethod("getStockTiles", null);
        } catch (Throwable unused) {
            return null;
        }
    }
}
