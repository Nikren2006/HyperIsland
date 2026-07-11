package j1;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: renamed from: j1.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0415c extends k1.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Function2 f4690d;

    public /* synthetic */ C0415c(Function2 function2, L0.g gVar, int i2, i1.a aVar, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(function2, (i3 & 2) != 0 ? L0.h.f402a : gVar, (i3 & 4) != 0 ? -2 : i2, (i3 & 8) != 0 ? i1.a.SUSPEND : aVar);
    }

    public static /* synthetic */ Object m(C0415c c0415c, i1.q qVar, L0.d dVar) {
        Object objInvoke = c0415c.f4690d.invoke(qVar, dVar);
        return objInvoke == M0.c.c() ? objInvoke : H0.s.f314a;
    }

    @Override // k1.e
    public Object g(i1.q qVar, L0.d dVar) {
        return m(this, qVar, dVar);
    }

    @Override // k1.e
    public k1.e h(L0.g gVar, int i2, i1.a aVar) {
        return new C0415c(this.f4690d, gVar, i2, aVar);
    }

    @Override // k1.e
    public String toString() {
        return "block[" + this.f4690d + "] -> " + super.toString();
    }

    public C0415c(Function2 function2, L0.g gVar, int i2, i1.a aVar) {
        super(gVar, i2, aVar);
        this.f4690d = function2;
    }
}
