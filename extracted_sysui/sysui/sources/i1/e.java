package i1;

import g1.AbstractC0357a;
import g1.C0382m0;
import g1.t0;
import java.util.concurrent.CancellationException;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class e extends AbstractC0357a implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f4603d;

    public e(L0.g gVar, d dVar, boolean z2, boolean z3) {
        super(gVar, z2, z3);
        this.f4603d = dVar;
    }

    @Override // g1.t0
    public void E(Throwable th) {
        CancellationException cancellationExceptionU0 = t0.u0(this, th, null, 1, null);
        this.f4603d.a(cancellationExceptionU0);
        C(cancellationExceptionU0);
    }

    public final d F0() {
        return this.f4603d;
    }

    @Override // g1.t0, g1.InterfaceC0380l0
    public final void a(CancellationException cancellationException) {
        if (Y()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new C0382m0(H(), null, this);
        }
        E(cancellationException);
    }

    @Override // i1.t
    public Object b(Object obj, L0.d dVar) {
        return this.f4603d.b(obj, dVar);
    }

    @Override // i1.t
    public void i(Function1 function1) {
        this.f4603d.i(function1);
    }

    @Override // i1.s
    public f iterator() {
        return this.f4603d.iterator();
    }

    @Override // i1.t
    public Object j(Object obj) {
        return this.f4603d.j(obj);
    }

    @Override // i1.s
    public Object k() {
        return this.f4603d.k();
    }

    @Override // i1.t
    public boolean o(Throwable th) {
        return this.f4603d.o(th);
    }

    @Override // i1.s
    public Object q(L0.d dVar) {
        Object objQ = this.f4603d.q(dVar);
        M0.c.c();
        return objQ;
    }

    @Override // i1.t
    public boolean s() {
        return this.f4603d.s();
    }
}
