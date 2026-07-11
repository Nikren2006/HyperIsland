package androidx.collection;

import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class FloatListKt {
    private static final FloatList EmptyFloatList = new MutableFloatList(0);

    public static final FloatList emptyFloatList() {
        return EmptyFloatList;
    }

    public static final FloatList floatListOf() {
        return EmptyFloatList;
    }

    public static final MutableFloatList mutableFloatListOf() {
        return new MutableFloatList(0, 1, null);
    }

    public static final FloatList floatListOf(float f2) {
        return mutableFloatListOf(f2);
    }

    public static final MutableFloatList mutableFloatListOf(float f2) {
        MutableFloatList mutableFloatList = new MutableFloatList(1);
        mutableFloatList.add(f2);
        return mutableFloatList;
    }

    public static final FloatList floatListOf(float f2, float f3) {
        return mutableFloatListOf(f2, f3);
    }

    public static final FloatList floatListOf(float f2, float f3, float f4) {
        return mutableFloatListOf(f2, f3, f4);
    }

    public static final MutableFloatList mutableFloatListOf(float f2, float f3) {
        MutableFloatList mutableFloatList = new MutableFloatList(2);
        mutableFloatList.add(f2);
        mutableFloatList.add(f3);
        return mutableFloatList;
    }

    public static final FloatList floatListOf(float... elements) {
        n.g(elements, "elements");
        MutableFloatList mutableFloatList = new MutableFloatList(elements.length);
        mutableFloatList.plusAssign(elements);
        return mutableFloatList;
    }

    public static final MutableFloatList mutableFloatListOf(float f2, float f3, float f4) {
        MutableFloatList mutableFloatList = new MutableFloatList(3);
        mutableFloatList.add(f2);
        mutableFloatList.add(f3);
        mutableFloatList.add(f4);
        return mutableFloatList;
    }

    public static final MutableFloatList mutableFloatListOf(float... elements) {
        n.g(elements, "elements");
        MutableFloatList mutableFloatList = new MutableFloatList(elements.length);
        mutableFloatList.plusAssign(elements);
        return mutableFloatList;
    }
}
