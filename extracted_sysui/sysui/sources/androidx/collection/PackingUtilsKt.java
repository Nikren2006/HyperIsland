package androidx.collection;

/* JADX INFO: loaded from: classes.dex */
public final class PackingUtilsKt {
    public static final long packFloats(float f2, float f3) {
        return (((long) Float.floatToRawIntBits(f3)) & 4294967295L) | (Float.floatToRawIntBits(f2) << 32);
    }

    public static final long packInts(int i2, int i3) {
        return (((long) i3) & 4294967295L) | (((long) i2) << 32);
    }
}
