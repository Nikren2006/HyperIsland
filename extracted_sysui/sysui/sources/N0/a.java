package N0;

import H0.j;
import java.io.Serializable;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a implements L0.d, e, Serializable {
    private final L0.d completion;

    public a(L0.d dVar) {
        this.completion = dVar;
    }

    public L0.d create(L0.d completion) {
        n.g(completion, "completion");
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    @Override // N0.e
    public e getCallerFrame() {
        L0.d dVar = this.completion;
        if (dVar instanceof e) {
            return (e) dVar;
        }
        return null;
    }

    public final L0.d getCompletion() {
        return this.completion;
    }

    public StackTraceElement getStackTraceElement() {
        return g.d(this);
    }

    public abstract Object invokeSuspend(Object obj);

    public void releaseIntercepted() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [L0.d] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    @Override // L0.d
    public final void resumeWith(Object obj) {
        Object objInvokeSuspend;
        ?? r2 = this;
        while (true) {
            h.b(r2);
            a aVar = (a) r2;
            L0.d dVar = aVar.completion;
            n.d(dVar);
            try {
                objInvokeSuspend = aVar.invokeSuspend(obj);
            } catch (Throwable th) {
                j.a aVar2 = H0.j.f299a;
                obj = H0.j.a(H0.k.a(th));
            }
            if (objInvokeSuspend == M0.c.c()) {
                return;
            }
            obj = H0.j.a(objInvokeSuspend);
            aVar.releaseIntercepted();
            if (!(dVar instanceof a)) {
                dVar.resumeWith(obj);
                return;
            }
            r2 = dVar;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Continuation at ");
        Object stackTraceElement = getStackTraceElement();
        if (stackTraceElement == null) {
            stackTraceElement = getClass().getName();
        }
        sb.append(stackTraceElement);
        return sb.toString();
    }

    public L0.d create(Object obj, L0.d completion) {
        n.g(completion, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }
}
