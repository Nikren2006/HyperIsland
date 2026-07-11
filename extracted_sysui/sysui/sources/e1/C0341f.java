package e1;

import H0.j;
import H0.s;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.n;

/* JADX INFO: renamed from: e1.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0341f extends AbstractC0342g implements Iterator, L0.d, W0.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4059a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f4060b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Iterator f4061c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public L0.d f4062d;

    @Override // e1.AbstractC0342g
    public Object c(Object obj, L0.d dVar) {
        this.f4060b = obj;
        this.f4059a = 3;
        this.f4062d = dVar;
        Object objC = M0.c.c();
        if (objC == M0.c.c()) {
            N0.h.c(dVar);
        }
        return objC == M0.c.c() ? objC : s.f314a;
    }

    @Override // e1.AbstractC0342g
    public Object e(Iterator it, L0.d dVar) {
        if (!it.hasNext()) {
            return s.f314a;
        }
        this.f4061c = it;
        this.f4059a = 2;
        this.f4062d = dVar;
        Object objC = M0.c.c();
        if (objC == M0.c.c()) {
            N0.h.c(dVar);
        }
        return objC == M0.c.c() ? objC : s.f314a;
    }

    public final Throwable f() {
        int i2 = this.f4059a;
        if (i2 == 4) {
            return new NoSuchElementException();
        }
        if (i2 == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.f4059a);
    }

    @Override // L0.d
    public L0.g getContext() {
        return L0.h.f402a;
    }

    @Override // java.util.Iterator
    public boolean hasNext() throws Throwable {
        while (true) {
            int i2 = this.f4059a;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 == 2 || i2 == 3) {
                        return true;
                    }
                    if (i2 == 4) {
                        return false;
                    }
                    throw f();
                }
                Iterator it = this.f4061c;
                n.d(it);
                if (it.hasNext()) {
                    this.f4059a = 2;
                    return true;
                }
                this.f4061c = null;
            }
            this.f4059a = 5;
            L0.d dVar = this.f4062d;
            n.d(dVar);
            this.f4062d = null;
            j.a aVar = H0.j.f299a;
            dVar.resumeWith(H0.j.a(s.f314a));
        }
    }

    public final Object i() {
        if (hasNext()) {
            return next();
        }
        throw new NoSuchElementException();
    }

    public final void j(L0.d dVar) {
        this.f4062d = dVar;
    }

    @Override // java.util.Iterator
    public Object next() throws Throwable {
        int i2 = this.f4059a;
        if (i2 == 0 || i2 == 1) {
            return i();
        }
        if (i2 == 2) {
            this.f4059a = 1;
            Iterator it = this.f4061c;
            n.d(it);
            return it.next();
        }
        if (i2 != 3) {
            throw f();
        }
        this.f4059a = 0;
        Object obj = this.f4060b;
        this.f4060b = null;
        return obj;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // L0.d
    public void resumeWith(Object obj) throws Throwable {
        H0.k.b(obj);
        this.f4059a = 4;
    }
}
