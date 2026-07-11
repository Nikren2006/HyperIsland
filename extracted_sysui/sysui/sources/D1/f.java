package D1;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;
import miuix.animation.internal.TransitionInfo;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends AbstractList implements RandomAccess {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d[] f88a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f89b;

    public f(d[] dVarArr, int[] iArr) {
        this.f88a = dVarArr;
        this.f89b = iArr;
    }

    public static void a(long j2, a aVar, int i2, List list, int i3, int i4, List list2) {
        int iIntValue;
        int i5;
        int i6;
        int i7;
        int i8;
        a aVar2;
        if (i3 >= i4) {
            throw new AssertionError();
        }
        for (int i9 = i3; i9 < i4; i9++) {
            if (((d) list.get(i9)).j() < i2) {
                throw new AssertionError();
            }
        }
        d dVar = (d) list.get(i3);
        d dVar2 = (d) list.get(i4 - 1);
        if (i2 == dVar.j()) {
            int i10 = i3 + 1;
            i5 = i10;
            iIntValue = ((Integer) list2.get(i3)).intValue();
            dVar = (d) list.get(i10);
        } else {
            iIntValue = -1;
            i5 = i3;
        }
        if (dVar.d(i2) == dVar2.d(i2)) {
            int iMin = Math.min(dVar.j(), dVar2.j());
            int i11 = 0;
            for (int i12 = i2; i12 < iMin && dVar.d(i12) == dVar2.d(i12); i12++) {
                i11++;
            }
            long jC = 1 + j2 + ((long) c(aVar)) + 2 + ((long) i11);
            aVar.L(-i11);
            aVar.L(iIntValue);
            int i13 = i2;
            while (true) {
                i6 = i2 + i11;
                if (i13 >= i6) {
                    break;
                }
                aVar.L(dVar.d(i13) & TransitionInfo.INIT);
                i13++;
            }
            if (i5 + 1 == i4) {
                if (i6 != ((d) list.get(i5)).j()) {
                    throw new AssertionError();
                }
                aVar.L(((Integer) list2.get(i5)).intValue());
                return;
            } else {
                a aVar3 = new a();
                aVar.L((int) ((((long) c(aVar3)) + jC) * (-1)));
                a(jC, aVar3, i6, list, i5, i4, list2);
                aVar.J(aVar3, aVar3.E());
                return;
            }
        }
        int i14 = 1;
        for (int i15 = i5 + 1; i15 < i4; i15++) {
            if (((d) list.get(i15 - 1)).d(i2) != ((d) list.get(i15)).d(i2)) {
                i14++;
            }
        }
        long jC2 = j2 + ((long) c(aVar)) + 2 + ((long) (i14 * 2));
        aVar.L(i14);
        aVar.L(iIntValue);
        for (int i16 = i5; i16 < i4; i16++) {
            byte bD = ((d) list.get(i16)).d(i2);
            if (i16 == i5 || bD != ((d) list.get(i16 - 1)).d(i2)) {
                aVar.L(bD & TransitionInfo.INIT);
            }
        }
        a aVar4 = new a();
        int i17 = i5;
        while (i17 < i4) {
            byte bD2 = ((d) list.get(i17)).d(i2);
            int i18 = i17 + 1;
            int i19 = i18;
            while (true) {
                if (i19 >= i4) {
                    i7 = i4;
                    break;
                } else {
                    if (bD2 != ((d) list.get(i19)).d(i2)) {
                        i7 = i19;
                        break;
                    }
                    i19++;
                }
            }
            if (i18 == i7 && i2 + 1 == ((d) list.get(i17)).j()) {
                aVar.L(((Integer) list2.get(i17)).intValue());
                i8 = i7;
                aVar2 = aVar4;
            } else {
                aVar.L((int) ((((long) c(aVar4)) + jC2) * (-1)));
                i8 = i7;
                aVar2 = aVar4;
                a(jC2, aVar4, i2 + 1, list, i17, i7, list2);
            }
            aVar4 = aVar2;
            i17 = i8;
        }
        a aVar5 = aVar4;
        aVar.J(aVar5, aVar5.E());
    }

    public static int c(a aVar) {
        return (int) (aVar.E() / 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ba, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static D1.f d(D1.d... r11) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: D1.f.d(D1.d[]):D1.f");
    }

    @Override // java.util.AbstractList, java.util.List
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public d get(int i2) {
        return this.f88a[i2];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f88a.length;
    }
}
