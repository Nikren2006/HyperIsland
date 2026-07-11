package androidx.collection;

import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class IntListKt {
    private static final IntList EmptyIntList = new MutableIntList(0);

    public static final IntList emptyIntList() {
        return EmptyIntList;
    }

    public static final IntList intListOf() {
        return EmptyIntList;
    }

    public static final MutableIntList mutableIntListOf() {
        return new MutableIntList(0, 1, null);
    }

    public static final IntList intListOf(int i2) {
        return mutableIntListOf(i2);
    }

    public static final MutableIntList mutableIntListOf(int i2) {
        MutableIntList mutableIntList = new MutableIntList(1);
        mutableIntList.add(i2);
        return mutableIntList;
    }

    public static final IntList intListOf(int i2, int i3) {
        return mutableIntListOf(i2, i3);
    }

    public static final IntList intListOf(int i2, int i3, int i4) {
        return mutableIntListOf(i2, i3, i4);
    }

    public static final MutableIntList mutableIntListOf(int i2, int i3) {
        MutableIntList mutableIntList = new MutableIntList(2);
        mutableIntList.add(i2);
        mutableIntList.add(i3);
        return mutableIntList;
    }

    public static final IntList intListOf(int... elements) {
        n.g(elements, "elements");
        MutableIntList mutableIntList = new MutableIntList(elements.length);
        mutableIntList.plusAssign(elements);
        return mutableIntList;
    }

    public static final MutableIntList mutableIntListOf(int i2, int i3, int i4) {
        MutableIntList mutableIntList = new MutableIntList(3);
        mutableIntList.add(i2);
        mutableIntList.add(i3);
        mutableIntList.add(i4);
        return mutableIntList;
    }

    public static final MutableIntList mutableIntListOf(int... elements) {
        n.g(elements, "elements");
        MutableIntList mutableIntList = new MutableIntList(elements.length);
        mutableIntList.plusAssign(elements);
        return mutableIntList;
    }
}
