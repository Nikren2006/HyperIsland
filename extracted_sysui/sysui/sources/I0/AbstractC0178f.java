package I0;

import java.lang.reflect.Array;

/* JADX INFO: renamed from: I0.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0178f {
    public static final Object[] a(Object[] reference, int i2) {
        kotlin.jvm.internal.n.g(reference, "reference");
        Object objNewInstance = Array.newInstance(reference.getClass().getComponentType(), i2);
        kotlin.jvm.internal.n.e(objNewInstance, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.arrayOfNulls>");
        return (Object[]) objNewInstance;
    }

    public static final void b(int i2, int i3) {
        if (i2 <= i3) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i2 + ") is greater than size (" + i3 + ").");
    }
}
