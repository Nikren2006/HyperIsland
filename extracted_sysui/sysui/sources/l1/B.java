package l1;

import g1.AbstractC0357a;
import g1.AbstractC0398z;

/* JADX INFO: loaded from: classes2.dex */
public class B extends AbstractC0357a implements N0.e {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final L0.d f5191d;

    public B(L0.g gVar, L0.d dVar) {
        super(gVar, true, true);
        this.f5191d = dVar;
    }

    @Override // g1.t0
    public void B(Object obj) {
        AbstractC0456k.c(M0.b.b(this.f5191d), AbstractC0398z.a(obj, this.f5191d), null, 2, null);
    }

    @Override // g1.AbstractC0357a
    public void B0(Object obj) {
        L0.d dVar = this.f5191d;
        dVar.resumeWith(AbstractC0398z.a(obj, dVar));
    }

    @Override // g1.t0
    public final boolean a0() {
        return true;
    }

    @Override // N0.e
    public final N0.e getCallerFrame() {
        L0.d dVar = this.f5191d;
        if (dVar instanceof N0.e) {
            return (N0.e) dVar;
        }
        return null;
    }
}
