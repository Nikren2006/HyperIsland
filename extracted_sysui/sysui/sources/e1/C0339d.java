package e1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;

/* JADX INFO: renamed from: e1.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0339d implements InterfaceC0340e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Function0 f4054a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Function1 f4055b;

    /* JADX INFO: renamed from: e1.d$a */
    public static final class a implements Iterator, W0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Object f4056a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4057b = -2;

        public a() {
        }

        private final void c() {
            Object objInvoke;
            if (this.f4057b == -2) {
                objInvoke = C0339d.this.f4054a.invoke();
            } else {
                Function1 function1 = C0339d.this.f4055b;
                Object obj = this.f4056a;
                n.d(obj);
                objInvoke = function1.invoke(obj);
            }
            this.f4056a = objInvoke;
            this.f4057b = objInvoke == null ? 0 : 1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f4057b < 0) {
                c();
            }
            return this.f4057b == 1;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (this.f4057b < 0) {
                c();
            }
            if (this.f4057b == 0) {
                throw new NoSuchElementException();
            }
            Object obj = this.f4056a;
            n.e(obj, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
            this.f4057b = -1;
            return obj;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public C0339d(Function0 getInitialValue, Function1 getNextValue) {
        n.g(getInitialValue, "getInitialValue");
        n.g(getNextValue, "getNextValue");
        this.f4054a = getInitialValue;
        this.f4055b = getNextValue;
    }

    @Override // e1.InterfaceC0340e
    public Iterator iterator() {
        return new a();
    }
}
