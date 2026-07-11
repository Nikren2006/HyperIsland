package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object[] f5052a = new Object[0];

    public static final Object[] a(Collection collection) {
        n.g(collection, "collection");
        int size = collection.size();
        if (size != 0) {
            Iterator it = collection.iterator();
            if (it.hasNext()) {
                Object[] objArrCopyOf = new Object[size];
                int i2 = 0;
                while (true) {
                    int i3 = i2 + 1;
                    objArrCopyOf[i2] = it.next();
                    if (i3 >= objArrCopyOf.length) {
                        if (!it.hasNext()) {
                            return objArrCopyOf;
                        }
                        int i4 = ((i3 * 3) + 1) >>> 1;
                        if (i4 <= i3) {
                            i4 = 2147483645;
                            if (i3 >= 2147483645) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArrCopyOf = Arrays.copyOf(objArrCopyOf, i4);
                        n.f(objArrCopyOf, "copyOf(...)");
                    } else if (!it.hasNext()) {
                        Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, i3);
                        n.f(objArrCopyOf2, "copyOf(...)");
                        return objArrCopyOf2;
                    }
                    i2 = i3;
                }
            }
        }
        return f5052a;
    }

    public static final Object[] b(Collection collection, Object[] objArr) {
        Object[] objArrCopyOf;
        n.g(collection, "collection");
        objArr.getClass();
        int size = collection.size();
        int i2 = 0;
        if (size == 0) {
            if (objArr.length <= 0) {
                return objArr;
            }
            objArr[0] = null;
            return objArr;
        }
        Iterator it = collection.iterator();
        if (!it.hasNext()) {
            if (objArr.length <= 0) {
                return objArr;
            }
            objArr[0] = null;
            return objArr;
        }
        if (size <= objArr.length) {
            objArrCopyOf = objArr;
        } else {
            Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
            n.e(objNewInstance, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArrCopyOf = (Object[]) objNewInstance;
        }
        while (true) {
            int i3 = i2 + 1;
            objArrCopyOf[i2] = it.next();
            if (i3 >= objArrCopyOf.length) {
                if (!it.hasNext()) {
                    return objArrCopyOf;
                }
                int i4 = ((i3 * 3) + 1) >>> 1;
                if (i4 <= i3) {
                    i4 = 2147483645;
                    if (i3 >= 2147483645) {
                        throw new OutOfMemoryError();
                    }
                }
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, i4);
                n.f(objArrCopyOf, "copyOf(...)");
            } else if (!it.hasNext()) {
                if (objArrCopyOf == objArr) {
                    objArr[i3] = null;
                    return objArr;
                }
                Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, i3);
                n.f(objArrCopyOf2, "copyOf(...)");
                return objArrCopyOf2;
            }
            i2 = i3;
        }
    }
}
