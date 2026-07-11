package g1;

import kotlin.jvm.functions.Function2;

/* JADX INFO: renamed from: g1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0357a extends t0 implements InterfaceC0380l0, L0.d, E {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final L0.g f4411c;

    public AbstractC0357a(L0.g gVar, boolean z2, boolean z3) {
        super(z3);
        if (z2) {
            X((InterfaceC0380l0) gVar.get(InterfaceC0380l0.f4430z));
        }
        this.f4411c = gVar.plus(this);
    }

    public void B0(Object obj) {
        B(obj);
    }

    public void C0(Throwable th, boolean z2) {
    }

    public void D0(Object obj) {
    }

    public final void E0(G g2, Object obj, Function2 function2) {
        g2.b(function2, obj, this);
    }

    @Override // g1.t0
    public String H() {
        return I.a(this) + " was cancelled";
    }

    @Override // g1.t0
    public final void W(Throwable th) {
        D.a(this.f4411c, th);
    }

    @Override // g1.t0
    public String g0() {
        String strB = A.b(this.f4411c);
        if (strB == null) {
            return super.g0();
        }
        return '\"' + strB + "\":" + super.g0();
    }

    @Override // L0.d
    public final L0.g getContext() {
        return this.f4411c;
    }

    @Override // g1.E
    public L0.g getCoroutineContext() {
        return this.f4411c;
    }

    @Override // g1.t0, g1.InterfaceC0380l0
    public boolean isActive() {
        return super.isActive();
    }

    @Override // g1.t0
    public final void l0(Object obj) {
        if (!(obj instanceof C0394v)) {
            D0(obj);
        } else {
            C0394v c0394v = (C0394v) obj;
            C0(c0394v.f4464a, c0394v.a());
        }
    }

    @Override // L0.d
    public final void resumeWith(Object obj) {
        Object objE0 = e0(AbstractC0398z.d(obj, null, 1, null));
        if (objE0 == u0.f4457b) {
            return;
        }
        B0(objE0);
    }
}
