package miui.systemui.coroutines;

import android.os.Handler;
import android.os.Looper;
import h1.d;
import h1.e;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class Dispatchers$_Main$1 extends o implements Function0 {
    public static final Dispatchers$_Main$1 INSTANCE = new Dispatchers$_Main$1();

    public Dispatchers$_Main$1() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final d invoke() {
        Handler handlerCreateAsync = Handler.createAsync(Looper.getMainLooper());
        n.f(handlerCreateAsync, "createAsync(...)");
        return e.b(handlerCreateAsync, "PluginMainDispatcher");
    }
}
