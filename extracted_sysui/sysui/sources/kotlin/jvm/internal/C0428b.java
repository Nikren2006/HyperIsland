package kotlin.jvm.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: renamed from: kotlin.jvm.internal.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0428b implements Iterator, W0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f5042a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5043b;

    public C0428b(Object[] array) {
        n.g(array, "array");
        this.f5042a = array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f5043b < this.f5042a.length;
    }

    @Override // java.util.Iterator
    public Object next() {
        try {
            Object[] objArr = this.f5042a;
            int i2 = this.f5043b;
            this.f5043b = i2 + 1;
            return objArr[i2];
        } catch (ArrayIndexOutOfBoundsException e2) {
            this.f5043b--;
            throw new NoSuchElementException(e2.getMessage());
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
