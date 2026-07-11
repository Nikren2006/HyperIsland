package F0;

import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b {
    public static int a(int i2) {
        if (i2 < 3) {
            return i2 + 1;
        }
        if (i2 < 1073741824) {
            return (int) ((i2 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static LinkedHashMap b(int i2) {
        return new LinkedHashMap(a(i2));
    }
}
