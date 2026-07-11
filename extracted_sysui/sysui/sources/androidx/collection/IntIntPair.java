package androidx.collection;

/* JADX INFO: loaded from: classes.dex */
public final class IntIntPair {
    public final long packedValue;

    private /* synthetic */ IntIntPair(long j2) {
        this.packedValue = j2;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ IntIntPair m18boximpl(long j2) {
        return new IntIntPair(j2);
    }

    /* JADX INFO: renamed from: component1-impl, reason: not valid java name */
    public static final int m19component1impl(long j2) {
        return (int) (j2 >> 32);
    }

    /* JADX INFO: renamed from: component2-impl, reason: not valid java name */
    public static final int m20component2impl(long j2) {
        return (int) (j2 & 4294967295L);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m22constructorimpl(long j2) {
        return j2;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m23equalsimpl(long j2, Object obj) {
        return (obj instanceof IntIntPair) && j2 == ((IntIntPair) obj).m29unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m24equalsimpl0(long j2, long j3) {
        return j2 == j3;
    }

    /* JADX INFO: renamed from: getFirst-impl, reason: not valid java name */
    public static final int m25getFirstimpl(long j2) {
        return (int) (j2 >> 32);
    }

    public static /* synthetic */ void getPackedValue$annotations() {
    }

    /* JADX INFO: renamed from: getSecond-impl, reason: not valid java name */
    public static final int m26getSecondimpl(long j2) {
        return (int) (j2 & 4294967295L);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m27hashCodeimpl(long j2) {
        return Long.hashCode(j2);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m28toStringimpl(long j2) {
        return '(' + m25getFirstimpl(j2) + ", " + m26getSecondimpl(j2) + ')';
    }

    public boolean equals(Object obj) {
        return m23equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m27hashCodeimpl(this.packedValue);
    }

    public String toString() {
        return m28toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m29unboximpl() {
        return this.packedValue;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m21constructorimpl(int i2, int i3) {
        return m22constructorimpl((((long) i3) & 4294967295L) | (((long) i2) << 32));
    }
}
