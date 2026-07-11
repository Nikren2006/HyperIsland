package N0;

import L0.g;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d extends a {
    private final L0.g _context;
    private transient L0.d intercepted;

    public d(L0.d dVar, L0.g gVar) {
        super(dVar);
        this._context = gVar;
    }

    @Override // L0.d
    public L0.g getContext() {
        L0.g gVar = this._context;
        n.d(gVar);
        return gVar;
    }

    public final L0.d intercepted() {
        L0.d dVarInterceptContinuation = this.intercepted;
        if (dVarInterceptContinuation == null) {
            L0.e eVar = (L0.e) getContext().get(L0.e.f399t);
            if (eVar == null || (dVarInterceptContinuation = eVar.interceptContinuation(this)) == null) {
                dVarInterceptContinuation = this;
            }
            this.intercepted = dVarInterceptContinuation;
        }
        return dVarInterceptContinuation;
    }

    @Override // N0.a
    public void releaseIntercepted() {
        L0.d dVar = this.intercepted;
        if (dVar != null && dVar != this) {
            g.b bVar = getContext().get(L0.e.f399t);
            n.d(bVar);
            ((L0.e) bVar).releaseInterceptedContinuation(dVar);
        }
        this.intercepted = c.f441a;
    }

    public d(L0.d dVar) {
        this(dVar, dVar != null ? dVar.getContext() : null);
    }
}
