package g1;

import l1.AbstractC0459n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class w0 extends B {
    public final String A() {
        w0 w0VarZ;
        w0 w0VarC = Q.c();
        if (this == w0VarC) {
            return "Dispatchers.Main";
        }
        try {
            w0VarZ = w0VarC.z();
        } catch (UnsupportedOperationException unused) {
            w0VarZ = null;
        }
        if (this == w0VarZ) {
            return "Dispatchers.Main.immediate";
        }
        return null;
    }

    @Override // g1.B
    public B limitedParallelism(int i2) {
        AbstractC0459n.a(i2);
        return this;
    }

    public abstract w0 z();
}
