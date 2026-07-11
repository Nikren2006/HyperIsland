package miui.systemui.coroutines;

import g1.AbstractC0360b0;
import g1.I0;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class Dispatchers$_IO$1 extends o implements Function0 {
    public static final Dispatchers$_IO$1 INSTANCE = new Dispatchers$_IO$1();

    public Dispatchers$_IO$1() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final AbstractC0360b0 invoke() {
        return I0.a(Runtime.getRuntime().availableProcessors(), "PluginIoDispatcher");
    }
}
