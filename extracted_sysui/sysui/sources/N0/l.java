package N0;

import kotlin.jvm.internal.n;
import kotlin.jvm.internal.z;

/* JADX INFO: loaded from: classes2.dex */
public abstract class l extends d implements kotlin.jvm.internal.j {
    private final int arity;

    public l(int i2, L0.d dVar) {
        super(dVar);
        this.arity = i2;
    }

    @Override // kotlin.jvm.internal.j
    public int getArity() {
        return this.arity;
    }

    @Override // N0.a
    public String toString() {
        if (getCompletion() != null) {
            return super.toString();
        }
        String strF = z.f(this);
        n.f(strF, "renderLambdaToString(...)");
        return strF;
    }
}
