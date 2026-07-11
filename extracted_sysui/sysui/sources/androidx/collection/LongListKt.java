package androidx.collection;

import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class LongListKt {
    private static final LongList EmptyLongList = new MutableLongList(0);

    public static final LongList emptyLongList() {
        return EmptyLongList;
    }

    public static final LongList longListOf() {
        return EmptyLongList;
    }

    public static final MutableLongList mutableLongListOf() {
        return new MutableLongList(0, 1, null);
    }

    public static final LongList longListOf(long j2) {
        return mutableLongListOf(j2);
    }

    public static final MutableLongList mutableLongListOf(long j2) {
        MutableLongList mutableLongList = new MutableLongList(1);
        mutableLongList.add(j2);
        return mutableLongList;
    }

    public static final LongList longListOf(long j2, long j3) {
        return mutableLongListOf(j2, j3);
    }

    public static final LongList longListOf(long j2, long j3, long j4) {
        return mutableLongListOf(j2, j3, j4);
    }

    public static final MutableLongList mutableLongListOf(long j2, long j3) {
        MutableLongList mutableLongList = new MutableLongList(2);
        mutableLongList.add(j2);
        mutableLongList.add(j3);
        return mutableLongList;
    }

    public static final LongList longListOf(long... elements) {
        n.g(elements, "elements");
        MutableLongList mutableLongList = new MutableLongList(elements.length);
        mutableLongList.plusAssign(elements);
        return mutableLongList;
    }

    public static final MutableLongList mutableLongListOf(long j2, long j3, long j4) {
        MutableLongList mutableLongList = new MutableLongList(3);
        mutableLongList.add(j2);
        mutableLongList.add(j3);
        mutableLongList.add(j4);
        return mutableLongList;
    }

    public static final MutableLongList mutableLongListOf(long... elements) {
        n.g(elements, "elements");
        MutableLongList mutableLongList = new MutableLongList(elements.length);
        mutableLongList.plusAssign(elements);
        return mutableLongList;
    }
}
