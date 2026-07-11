package k1;

import j1.I;

/* JADX INFO: loaded from: classes2.dex */
public final class y extends j1.z implements I {
    public y(int i2) {
        super(1, Integer.MAX_VALUE, i1.a.DROP_OLDEST);
        b(Integer.valueOf(i2));
    }

    @Override // j1.I
    /* JADX INFO: renamed from: X, reason: merged with bridge method [inline-methods] */
    public Integer getValue() {
        Integer numValueOf;
        synchronized (this) {
            numValueOf = Integer.valueOf(((Number) K()).intValue());
        }
        return numValueOf;
    }

    public final boolean Y(int i2) {
        boolean zB;
        synchronized (this) {
            zB = b(Integer.valueOf(((Number) K()).intValue() + i2));
        }
        return zB;
    }
}
