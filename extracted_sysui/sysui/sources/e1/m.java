package e1;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class m implements InterfaceC0340e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0340e f4071a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Function1 f4072b;

    public static final class a implements Iterator, W0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Iterator f4073a;

        public a() {
            this.f4073a = m.this.f4071a.iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f4073a.hasNext();
        }

        @Override // java.util.Iterator
        public Object next() {
            return m.this.f4072b.invoke(this.f4073a.next());
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public m(InterfaceC0340e sequence, Function1 transformer) {
        n.g(sequence, "sequence");
        n.g(transformer, "transformer");
        this.f4071a = sequence;
        this.f4072b = transformer;
    }

    @Override // e1.InterfaceC0340e
    public Iterator iterator() {
        return new a();
    }
}
