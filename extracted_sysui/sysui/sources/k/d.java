package k;

import p.AbstractC0722b;
import p.AbstractC0727g;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f4827a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f4828b;

    public d(float[] fArr, int[] iArr) {
        this.f4827a = fArr;
        this.f4828b = iArr;
    }

    public int[] a() {
        return this.f4828b;
    }

    public float[] b() {
        return this.f4827a;
    }

    public int c() {
        return this.f4828b.length;
    }

    public void d(d dVar, d dVar2, float f2) {
        if (dVar.f4828b.length == dVar2.f4828b.length) {
            for (int i2 = 0; i2 < dVar.f4828b.length; i2++) {
                this.f4827a[i2] = AbstractC0727g.i(dVar.f4827a[i2], dVar2.f4827a[i2], f2);
                this.f4828b[i2] = AbstractC0722b.c(f2, dVar.f4828b[i2], dVar2.f4828b[i2]);
            }
            return;
        }
        throw new IllegalArgumentException("Cannot interpolate between gradients. Lengths vary (" + dVar.f4828b.length + " vs " + dVar2.f4828b.length + ")");
    }
}
