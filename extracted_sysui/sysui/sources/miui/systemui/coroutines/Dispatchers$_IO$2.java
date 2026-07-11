package miui.systemui.coroutines;

import H0.s;
import g1.AbstractC0360b0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class Dispatchers$_IO$2 extends o implements Function1 {
    public static final Dispatchers$_IO$2 INSTANCE = new Dispatchers$_IO$2();

    public Dispatchers$_IO$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((AbstractC0360b0) obj);
        return s.f314a;
    }

    public final void invoke(AbstractC0360b0 $receiver) {
        n.g($receiver, "$this$$receiver");
        $receiver.close();
    }
}
