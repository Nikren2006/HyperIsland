package l1;

/* JADX INFO: renamed from: l1.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0459n {
    public static final void a(int i2) {
        if (i2 >= 1) {
            return;
        }
        throw new IllegalArgumentException(("Expected positive parallelism level, but got " + i2).toString());
    }
}
