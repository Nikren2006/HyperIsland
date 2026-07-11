package u1;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final s1.c[] f6877a = new s1.c[0];

    public static final s1.c[] a(List list) {
        if (list == null || list.isEmpty()) {
            list = null;
        }
        if (list == null) {
            return f6877a;
        }
        Object[] array = list.toArray(new s1.c[0]);
        if (array != null) {
            return (s1.c[]) array;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }
}
