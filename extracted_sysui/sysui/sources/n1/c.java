package n1;

import g1.B;
import l1.AbstractC0459n;

/* JADX INFO: loaded from: classes2.dex */
public final class c extends f {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c f6265g = new c();

    public c() {
        super(l.f6278c, l.f6279d, l.f6280e, l.f6276a);
    }

    @Override // g1.AbstractC0360b0, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // g1.B
    public B limitedParallelism(int i2) {
        AbstractC0459n.a(i2);
        return i2 >= l.f6278c ? this : super.limitedParallelism(i2);
    }

    @Override // g1.B
    public String toString() {
        return "Dispatchers.Default";
    }
}
