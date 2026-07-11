package androidx.collection;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
class ArraySetJvmUtil {
    private ArraySetJvmUtil() {
    }

    public static <T> T[] resizeForToArray(T[] tArr, int i2) {
        if (tArr.length < i2) {
            return (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i2));
        }
        if (tArr.length > i2) {
            tArr[i2] = null;
        }
        return tArr;
    }
}
