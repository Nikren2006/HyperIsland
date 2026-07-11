package androidx.collection;

/* JADX INFO: loaded from: classes.dex */
public final class LongLongPair {
    private final long first;
    private final long second;

    public LongLongPair(long j2, long j3) {
        this.first = j2;
        this.second = j3;
    }

    public final long component1() {
        return getFirst();
    }

    public final long component2() {
        return getSecond();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LongLongPair)) {
            return false;
        }
        LongLongPair longLongPair = (LongLongPair) obj;
        return longLongPair.first == this.first && longLongPair.second == this.second;
    }

    public final long getFirst() {
        return this.first;
    }

    public final long getSecond() {
        return this.second;
    }

    public int hashCode() {
        return Long.hashCode(this.second) ^ Long.hashCode(this.first);
    }

    public String toString() {
        return '(' + this.first + ", " + this.second + ')';
    }
}
