package s1;

import java.util.Iterator;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d {

    public static final class a implements Iterator, W0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f6475a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ c f6476b;

        public a(c cVar) {
            this.f6476b = cVar;
            this.f6475a = cVar.d();
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public c next() {
            c cVar = this.f6476b;
            int iD = cVar.d();
            int i2 = this.f6475a;
            this.f6475a = i2 - 1;
            return cVar.g(iD - i2);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f6475a > 0;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public static final class b implements Iterable, W0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c f6477a;

        public b(c cVar) {
            this.f6477a = cVar;
        }

        @Override // java.lang.Iterable
        public Iterator iterator() {
            return new a(this.f6477a);
        }
    }

    public static final Iterable a(c cVar) {
        n.g(cVar, "<this>");
        return new b(cVar);
    }
}
