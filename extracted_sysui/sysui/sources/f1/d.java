package f1;

import c1.C0232d;
import e1.InterfaceC0340e;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes2.dex */
public final class d implements InterfaceC0340e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharSequence f4244a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4245b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f4246c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Function2 f4247d;

    public static final class a implements Iterator, W0.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4248a = -1;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4249b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f4250c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public C0232d f4251d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f4252e;

        public a() {
            int i2 = c1.f.i(d.this.f4245b, 0, d.this.f4244a.length());
            this.f4249b = i2;
            this.f4250c = i2;
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final void c() {
            /*
                r6 = this;
                int r0 = r6.f4250c
                r1 = 0
                if (r0 >= 0) goto Lc
                r6.f4248a = r1
                r0 = 0
                r6.f4251d = r0
                goto L9e
            Lc:
                f1.d r0 = f1.d.this
                int r0 = f1.d.c(r0)
                r2 = -1
                r3 = 1
                if (r0 <= 0) goto L23
                int r0 = r6.f4252e
                int r0 = r0 + r3
                r6.f4252e = r0
                f1.d r4 = f1.d.this
                int r4 = f1.d.c(r4)
                if (r0 >= r4) goto L31
            L23:
                int r0 = r6.f4250c
                f1.d r4 = f1.d.this
                java.lang.CharSequence r4 = f1.d.b(r4)
                int r4 = r4.length()
                if (r0 <= r4) goto L47
            L31:
                c1.d r0 = new c1.d
                int r1 = r6.f4249b
                f1.d r4 = f1.d.this
                java.lang.CharSequence r4 = f1.d.b(r4)
                int r4 = f1.o.y(r4)
                r0.<init>(r1, r4)
                r6.f4251d = r0
                r6.f4250c = r2
                goto L9c
            L47:
                f1.d r0 = f1.d.this
                kotlin.jvm.functions.Function2 r0 = f1.d.a(r0)
                f1.d r4 = f1.d.this
                java.lang.CharSequence r4 = f1.d.b(r4)
                int r5 = r6.f4250c
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                java.lang.Object r0 = r0.invoke(r4, r5)
                H0.i r0 = (H0.i) r0
                if (r0 != 0) goto L77
                c1.d r0 = new c1.d
                int r1 = r6.f4249b
                f1.d r4 = f1.d.this
                java.lang.CharSequence r4 = f1.d.b(r4)
                int r4 = f1.o.y(r4)
                r0.<init>(r1, r4)
                r6.f4251d = r0
                r6.f4250c = r2
                goto L9c
            L77:
                java.lang.Object r2 = r0.a()
                java.lang.Number r2 = (java.lang.Number) r2
                int r2 = r2.intValue()
                java.lang.Object r0 = r0.b()
                java.lang.Number r0 = (java.lang.Number) r0
                int r0 = r0.intValue()
                int r4 = r6.f4249b
                c1.d r4 = c1.f.l(r4, r2)
                r6.f4251d = r4
                int r2 = r2 + r0
                r6.f4249b = r2
                if (r0 != 0) goto L99
                r1 = r3
            L99:
                int r2 = r2 + r1
                r6.f4250c = r2
            L9c:
                r6.f4248a = r3
            L9e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: f1.d.a.c():void");
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public C0232d next() {
            if (this.f4248a == -1) {
                c();
            }
            if (this.f4248a == 0) {
                throw new NoSuchElementException();
            }
            C0232d c0232d = this.f4251d;
            kotlin.jvm.internal.n.e(c0232d, "null cannot be cast to non-null type kotlin.ranges.IntRange");
            this.f4251d = null;
            this.f4248a = -1;
            return c0232d;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.f4248a == -1) {
                c();
            }
            return this.f4248a == 1;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public d(CharSequence input, int i2, int i3, Function2 getNextMatch) {
        kotlin.jvm.internal.n.g(input, "input");
        kotlin.jvm.internal.n.g(getNextMatch, "getNextMatch");
        this.f4244a = input;
        this.f4245b = i2;
        this.f4246c = i3;
        this.f4247d = getNextMatch;
    }

    @Override // e1.InterfaceC0340e
    public Iterator iterator() {
        return new a();
    }
}
