package e1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;

/* JADX INFO: renamed from: e1.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0338c implements InterfaceC0340e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0340e f4047a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f4048b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Function1 f4049c;

    /* JADX INFO: renamed from: e1.c$a */
    public static final class a implements Iterator, W0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Iterator f4050a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4051b = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Object f4052c;

        public a() {
            this.f4050a = C0338c.this.f4047a.iterator();
        }

        public final void c() {
            while (this.f4050a.hasNext()) {
                Object next = this.f4050a.next();
                if (((Boolean) C0338c.this.f4049c.invoke(next)).booleanValue() == C0338c.this.f4048b) {
                    this.f4052c = next;
                    this.f4051b = 1;
                    return;
                }
            }
            this.f4051b = 0;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f4051b == -1) {
                c();
            }
            return this.f4051b == 1;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (this.f4051b == -1) {
                c();
            }
            if (this.f4051b == 0) {
                throw new NoSuchElementException();
            }
            Object obj = this.f4052c;
            this.f4052c = null;
            this.f4051b = -1;
            return obj;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public C0338c(InterfaceC0340e sequence, boolean z2, Function1 predicate) {
        n.g(sequence, "sequence");
        n.g(predicate, "predicate");
        this.f4047a = sequence;
        this.f4048b = z2;
        this.f4049c = predicate;
    }

    @Override // e1.InterfaceC0340e
    public Iterator iterator() {
        return new a();
    }
}
