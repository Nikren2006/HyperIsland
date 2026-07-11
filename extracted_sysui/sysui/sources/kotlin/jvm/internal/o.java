package kotlin.jvm.internal;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class o implements j, Serializable {
    private final int arity;

    public o(int i2) {
        this.arity = i2;
    }

    @Override // kotlin.jvm.internal.j
    public int getArity() {
        return this.arity;
    }

    public String toString() {
        String strG = z.g(this);
        n.f(strG, "renderLambdaToString(...)");
        return strG;
    }
}
