package u1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class d extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float[] f6863a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6864b;

    public d(float[] bufferWithData) {
        kotlin.jvm.internal.n.g(bufferWithData, "bufferWithData");
        this.f6863a = bufferWithData;
        this.f6864b = bufferWithData.length;
        b(10);
    }

    @Override // u1.q
    public void b(int i2) {
        float[] fArr = this.f6863a;
        if (fArr.length < i2) {
            float[] fArrCopyOf = Arrays.copyOf(fArr, c1.f.c(i2, fArr.length * 2));
            kotlin.jvm.internal.n.f(fArrCopyOf, "copyOf(this, newSize)");
            this.f6863a = fArrCopyOf;
        }
    }

    @Override // u1.q
    public int d() {
        return this.f6864b;
    }

    public final void e(float f2) {
        q.c(this, 0, 1, null);
        float[] fArr = this.f6863a;
        int iD = d();
        this.f6864b = iD + 1;
        fArr[iD] = f2;
    }

    @Override // u1.q
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public float[] a() {
        float[] fArrCopyOf = Arrays.copyOf(this.f6863a, d());
        kotlin.jvm.internal.n.f(fArrCopyOf, "copyOf(this, newSize)");
        return fArrCopyOf;
    }
}
