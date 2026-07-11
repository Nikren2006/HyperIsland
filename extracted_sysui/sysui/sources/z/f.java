package z;

import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public abstract class f {
    public static int[] a(int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr2[i2] = iArr[i2] * 2;
        }
        return iArr2;
    }

    public static float b(float f2, float f3, float f4) {
        return 1.0f - ((f2 - f4) / (f3 - f4));
    }

    public abstract com.google.android.material.carousel.b c(b bVar, View view);

    public abstract boolean d(b bVar, int i2);
}
