package androidx.collection;

/* JADX INFO: loaded from: classes.dex */
public final class FloatFloatPair {
    public final long packedValue;

    private /* synthetic */ FloatFloatPair(long j2) {
        this.packedValue = j2;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FloatFloatPair m6boximpl(long j2) {
        return new FloatFloatPair(j2);
    }

    /* JADX INFO: renamed from: component1-impl, reason: not valid java name */
    public static final float m7component1impl(long j2) {
        return Float.intBitsToFloat((int) (j2 >> 32));
    }

    /* JADX INFO: renamed from: component2-impl, reason: not valid java name */
    public static final float m8component2impl(long j2) {
        return Float.intBitsToFloat((int) (j2 & 4294967295L));
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m10constructorimpl(long j2) {
        return j2;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m11equalsimpl(long j2, Object obj) {
        return (obj instanceof FloatFloatPair) && j2 == ((FloatFloatPair) obj).m17unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m12equalsimpl0(long j2, long j3) {
        return j2 == j3;
    }

    /* JADX INFO: renamed from: getFirst-impl, reason: not valid java name */
    public static final float m13getFirstimpl(long j2) {
        return Float.intBitsToFloat((int) (j2 >> 32));
    }

    public static /* synthetic */ void getPackedValue$annotations() {
    }

    /* JADX INFO: renamed from: getSecond-impl, reason: not valid java name */
    public static final float m14getSecondimpl(long j2) {
        return Float.intBitsToFloat((int) (j2 & 4294967295L));
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m15hashCodeimpl(long j2) {
        return Long.hashCode(j2);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m16toStringimpl(long j2) {
        return '(' + Float.intBitsToFloat((int) (j2 >> 32)) + ", " + Float.intBitsToFloat((int) (j2 & 4294967295L)) + ')';
    }

    public boolean equals(Object obj) {
        return m11equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m15hashCodeimpl(this.packedValue);
    }

    public String toString() {
        return m16toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m17unboximpl() {
        return this.packedValue;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m9constructorimpl(float f2, float f3) {
        return m10constructorimpl((((long) Float.floatToRawIntBits(f3)) & 4294967295L) | (Float.floatToRawIntBits(f2) << 32));
    }
}
