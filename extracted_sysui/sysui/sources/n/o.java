package n;

import android.graphics.Color;
import java.util.Arrays;
import java.util.List;
import p.AbstractC0727g;

/* JADX INFO: loaded from: classes.dex */
public class o implements N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6212a;

    public o(int i2) {
        this.f6212a = i2;
    }

    public static float[] e(float[] fArr, float[] fArr2) {
        if (fArr.length == 0) {
            return fArr2;
        }
        if (fArr2.length == 0) {
            return fArr;
        }
        int length = fArr.length + fArr2.length;
        float[] fArr3 = new float[length];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            float f2 = i3 < fArr.length ? fArr[i3] : Float.NaN;
            float f3 = i4 < fArr2.length ? fArr2[i4] : Float.NaN;
            if (Float.isNaN(f3) || f2 < f3) {
                fArr3[i5] = f2;
                i3++;
            } else if (Float.isNaN(f2) || f3 < f2) {
                fArr3[i5] = f3;
                i4++;
            } else {
                fArr3[i5] = f2;
                i3++;
                i4++;
                i2++;
            }
        }
        return i2 == 0 ? fArr3 : Arrays.copyOf(fArr3, length - i2);
    }

    public final k.d b(k.d dVar, List list) {
        int i2 = this.f6212a * 4;
        if (list.size() <= i2) {
            return dVar;
        }
        float[] fArrB = dVar.b();
        int[] iArrA = dVar.a();
        int size = (list.size() - i2) / 2;
        float[] fArr = new float[size];
        float[] fArr2 = new float[size];
        int i3 = 0;
        while (i2 < list.size()) {
            if (i2 % 2 == 0) {
                fArr[i3] = ((Float) list.get(i2)).floatValue();
            } else {
                fArr2[i3] = ((Float) list.get(i2)).floatValue();
                i3++;
            }
            i2++;
        }
        float[] fArrE = e(dVar.b(), fArr);
        int length = fArrE.length;
        int[] iArr = new int[length];
        for (int i4 = 0; i4 < length; i4++) {
            float f2 = fArrE[i4];
            int iBinarySearch = Arrays.binarySearch(fArrB, f2);
            int iBinarySearch2 = Arrays.binarySearch(fArr, f2);
            if (iBinarySearch < 0 || iBinarySearch2 > 0) {
                if (iBinarySearch2 < 0) {
                    iBinarySearch2 = -(iBinarySearch2 + 1);
                }
                iArr[i4] = c(f2, fArr2[iBinarySearch2], fArrB, iArrA);
            } else {
                iArr[i4] = d(f2, iArrA[iBinarySearch], fArr, fArr2);
            }
        }
        return new k.d(fArrE, iArr);
    }

    public final int c(float f2, float f3, float[] fArr, int[] iArr) {
        if (iArr.length < 2 || f2 == fArr[0]) {
            return iArr[0];
        }
        for (int i2 = 1; i2 < fArr.length; i2++) {
            float f4 = fArr[i2];
            if (f4 >= f2 || i2 == fArr.length - 1) {
                int i3 = i2 - 1;
                float f5 = fArr[i3];
                float f6 = (f2 - f5) / (f4 - f5);
                int i4 = iArr[i2];
                int i5 = iArr[i3];
                return Color.argb((int) (f3 * 255.0f), AbstractC0727g.j(Color.red(i5), Color.red(i4), f6), AbstractC0727g.j(Color.green(i5), Color.green(i4), f6), AbstractC0727g.j(Color.blue(i5), Color.blue(i4), f6));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }

    public final int d(float f2, int i2, float[] fArr, float[] fArr2) {
        float fI;
        if (fArr2.length < 2 || f2 <= fArr[0]) {
            return Color.argb((int) (fArr2[0] * 255.0f), Color.red(i2), Color.green(i2), Color.blue(i2));
        }
        for (int i3 = 1; i3 < fArr.length; i3++) {
            float f3 = fArr[i3];
            if (f3 >= f2 || i3 == fArr.length - 1) {
                if (f3 <= f2) {
                    fI = fArr2[i3];
                } else {
                    int i4 = i3 - 1;
                    float f4 = fArr[i4];
                    fI = AbstractC0727g.i(fArr2[i4], fArr2[i3], (f2 - f4) / (f3 - f4));
                }
                return Color.argb((int) (fI * 255.0f), Color.red(i2), Color.green(i2), Color.blue(i2));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00cf  */
    @Override // n.N
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public k.d a(o.AbstractC0715c r18, float r19) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: n.o.a(o.c, float):k.d");
    }
}
