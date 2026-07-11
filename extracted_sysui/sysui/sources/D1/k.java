package D1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends d {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final transient byte[][] f109f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final transient int[] f110g;

    public k(a aVar, int i2) {
        super(null);
        n.b(aVar.f78b, 0L, i2);
        i iVar = aVar.f77a;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            int i6 = iVar.f102c;
            int i7 = iVar.f101b;
            if (i6 == i7) {
                throw new AssertionError("s.limit == s.pos");
            }
            i4 += i6 - i7;
            i5++;
            iVar = iVar.f105f;
        }
        this.f109f = new byte[i5][];
        this.f110g = new int[i5 * 2];
        i iVar2 = aVar.f77a;
        int i8 = 0;
        while (i3 < i2) {
            byte[][] bArr = this.f109f;
            bArr[i8] = iVar2.f100a;
            int i9 = iVar2.f102c;
            int i10 = iVar2.f101b;
            i3 += i9 - i10;
            if (i3 > i2) {
                i3 = i2;
            }
            int[] iArr = this.f110g;
            iArr[i8] = i3;
            iArr[bArr.length + i8] = i10;
            iVar2.f103d = true;
            i8++;
            iVar2 = iVar2.f105f;
        }
    }

    @Override // D1.d
    public byte d(int i2) {
        n.b(this.f110g[this.f109f.length - 1], i2, 1L);
        int iN = n(i2);
        int i3 = iN == 0 ? 0 : this.f110g[iN - 1];
        int[] iArr = this.f110g;
        byte[][] bArr = this.f109f;
        return bArr[iN][(i2 - i3) + iArr[bArr.length + iN]];
    }

    @Override // D1.d
    public String e() {
        return p().e();
    }

    @Override // D1.d
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            if (dVar.j() == j() && h(0, dVar, 0, j())) {
                return true;
            }
        }
        return false;
    }

    @Override // D1.d
    public byte[] f() {
        return o();
    }

    @Override // D1.d
    public boolean h(int i2, d dVar, int i3, int i4) {
        if (i2 < 0 || i2 > j() - i4) {
            return false;
        }
        int iN = n(i2);
        while (i4 > 0) {
            int i5 = iN == 0 ? 0 : this.f110g[iN - 1];
            int iMin = Math.min(i4, ((this.f110g[iN] - i5) + i5) - i2);
            int[] iArr = this.f110g;
            byte[][] bArr = this.f109f;
            if (!dVar.i(i3, bArr[iN], (i2 - i5) + iArr[bArr.length + iN], iMin)) {
                return false;
            }
            i2 += iMin;
            i3 += iMin;
            i4 -= iMin;
            iN++;
        }
        return true;
    }

    @Override // D1.d
    public int hashCode() {
        int i2 = this.f83b;
        if (i2 != 0) {
            return i2;
        }
        int length = this.f109f.length;
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        while (i3 < length) {
            byte[] bArr = this.f109f[i3];
            int[] iArr = this.f110g;
            int i6 = iArr[length + i3];
            int i7 = iArr[i3];
            int i8 = (i7 - i5) + i6;
            while (i6 < i8) {
                i4 = (i4 * 31) + bArr[i6];
                i6++;
            }
            i3++;
            i5 = i7;
        }
        this.f83b = i4;
        return i4;
    }

    @Override // D1.d
    public boolean i(int i2, byte[] bArr, int i3, int i4) {
        if (i2 < 0 || i2 > j() - i4 || i3 < 0 || i3 > bArr.length - i4) {
            return false;
        }
        int iN = n(i2);
        while (i4 > 0) {
            int i5 = iN == 0 ? 0 : this.f110g[iN - 1];
            int iMin = Math.min(i4, ((this.f110g[iN] - i5) + i5) - i2);
            int[] iArr = this.f110g;
            byte[][] bArr2 = this.f109f;
            if (!n.a(bArr2[iN], (i2 - i5) + iArr[bArr2.length + iN], bArr, i3, iMin)) {
                return false;
            }
            i2 += iMin;
            i3 += iMin;
            i4 -= iMin;
            iN++;
        }
        return true;
    }

    @Override // D1.d
    public int j() {
        return this.f110g[this.f109f.length - 1];
    }

    @Override // D1.d
    public d l(int i2, int i3) {
        return p().l(i2, i3);
    }

    @Override // D1.d
    public String m() {
        return p().m();
    }

    public final int n(int i2) {
        int iBinarySearch = Arrays.binarySearch(this.f110g, 0, this.f109f.length, i2 + 1);
        return iBinarySearch >= 0 ? iBinarySearch : ~iBinarySearch;
    }

    public byte[] o() {
        int[] iArr = this.f110g;
        byte[][] bArr = this.f109f;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int[] iArr2 = this.f110g;
            int i4 = iArr2[length + i2];
            int i5 = iArr2[i2];
            System.arraycopy(this.f109f[i2], i4, bArr2, i3, i5 - i3);
            i2++;
            i3 = i5;
        }
        return bArr2;
    }

    public final d p() {
        return new d(o());
    }

    @Override // D1.d
    public String toString() {
        return p().toString();
    }
}
