package u1;

/* JADX INFO: loaded from: classes2.dex */
public abstract class q {
    public static /* synthetic */ void c(q qVar, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: ensureCapacity");
        }
        if ((i3 & 1) != 0) {
            i2 = qVar.d() + 1;
        }
        qVar.b(i2);
    }

    public abstract Object a();

    public abstract void b(int i2);

    public abstract int d();
}
