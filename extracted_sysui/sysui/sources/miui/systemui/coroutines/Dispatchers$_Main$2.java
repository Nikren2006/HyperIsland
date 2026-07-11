package miui.systemui.coroutines;

import H0.s;
import g1.r0;
import h1.d;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class Dispatchers$_Main$2 extends o implements Function1 {
    public static final Dispatchers$_Main$2 INSTANCE = new Dispatchers$_Main$2();

    public Dispatchers$_Main$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((d) obj);
        return s.f314a;
    }

    public final void invoke(d $receiver) {
        n.g($receiver, "$this$$receiver");
        r0.d($receiver.z(), null, 1, null);
        r0.d($receiver, null, 1, null);
    }
}
