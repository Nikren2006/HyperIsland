package g1;

import I0.C0177e;
import androidx.core.location.LocationRequestCompat;
import java.lang.reflect.InvocationTargetException;
import l1.AbstractC0459n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class V extends B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f4398a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4399b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public C0177e f4400c;

    public static /* synthetic */ void E(V v2, boolean z2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: incrementUseCount");
        }
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        v2.D(z2);
    }

    public final long A(boolean z2) {
        return z2 ? 4294967296L : 1L;
    }

    public final void B(O o2) {
        C0177e c0177e = this.f4400c;
        if (c0177e == null) {
            c0177e = new C0177e();
            this.f4400c = c0177e;
        }
        c0177e.addLast(o2);
    }

    public long C() {
        C0177e c0177e = this.f4400c;
        if (c0177e == null || c0177e.isEmpty()) {
            return LocationRequestCompat.PASSIVE_INTERVAL;
        }
        return 0L;
    }

    public final void D(boolean z2) {
        this.f4398a += A(z2);
        if (z2) {
            return;
        }
        this.f4399b = true;
    }

    public final boolean F() {
        return this.f4398a >= A(true);
    }

    public final boolean G() {
        C0177e c0177e = this.f4400c;
        if (c0177e != null) {
            return c0177e.isEmpty();
        }
        return true;
    }

    public final boolean H() throws IllegalAccessException, InvocationTargetException {
        O o2;
        C0177e c0177e = this.f4400c;
        if (c0177e == null || (o2 = (O) c0177e.l()) == null) {
            return false;
        }
        o2.run();
        return true;
    }

    public abstract void I();

    @Override // g1.B
    public final B limitedParallelism(int i2) {
        AbstractC0459n.a(i2);
        return this;
    }

    public final void z(boolean z2) {
        long jA = this.f4398a - A(z2);
        this.f4398a = jA;
        if (jA <= 0 && this.f4399b) {
            I();
        }
    }
}
