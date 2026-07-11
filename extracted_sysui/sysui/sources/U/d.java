package U;

import b0.C0223a;
import c0.C0226a;
import c0.C0228c;
import c0.EnumC0227b;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: loaded from: classes2.dex */
public final class d {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final U.c f708A = U.b.f700a;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public static final p f709B = o.f754a;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final p f710C = o.f755b;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final String f711z = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ThreadLocal f712a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentMap f713b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final W.c f714c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final X.e f715d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f716e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final W.d f717f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final U.c f718g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Map f719h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f720i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f721j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f722k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f723l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f724m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f725n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f726o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f727p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final String f728q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f729r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f730s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final m f731t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final List f732u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final List f733v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final p f734w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final p f735x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final List f736y;

    public class a extends q {
        public a() {
        }

        @Override // U.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Double b(C0226a c0226a) throws IOException {
            if (c0226a.M() != EnumC0227b.NULL) {
                return Double.valueOf(c0226a.D());
            }
            c0226a.I();
            return null;
        }

        @Override // U.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(C0228c c0228c, Number number) throws IOException {
            if (number == null) {
                c0228c.A();
                return;
            }
            double dDoubleValue = number.doubleValue();
            d.d(dDoubleValue);
            c0228c.K(dDoubleValue);
        }
    }

    public class b extends q {
        public b() {
        }

        @Override // U.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Float b(C0226a c0226a) throws IOException {
            if (c0226a.M() != EnumC0227b.NULL) {
                return Float.valueOf((float) c0226a.D());
            }
            c0226a.I();
            return null;
        }

        @Override // U.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(C0228c c0228c, Number number) throws IOException {
            if (number == null) {
                c0228c.A();
                return;
            }
            float fFloatValue = number.floatValue();
            d.d(fFloatValue);
            if (!(number instanceof Float)) {
                number = Float.valueOf(fFloatValue);
            }
            c0228c.N(number);
        }
    }

    public class c extends q {
        @Override // U.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(C0226a c0226a) throws IOException {
            if (c0226a.M() != EnumC0227b.NULL) {
                return Long.valueOf(c0226a.F());
            }
            c0226a.I();
            return null;
        }

        @Override // U.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(C0228c c0228c, Number number) throws IOException {
            if (number == null) {
                c0228c.A();
            } else {
                c0228c.O(number.toString());
            }
        }
    }

    /* JADX INFO: renamed from: U.d$d, reason: collision with other inner class name */
    public class C0021d extends q {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ q f739a;

        public C0021d(q qVar) {
            this.f739a = qVar;
        }

        @Override // U.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLong b(C0226a c0226a) {
            return new AtomicLong(((Number) this.f739a.b(c0226a)).longValue());
        }

        @Override // U.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(C0228c c0228c, AtomicLong atomicLong) {
            this.f739a.d(c0228c, Long.valueOf(atomicLong.get()));
        }
    }

    public class e extends q {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ q f740a;

        public e(q qVar) {
            this.f740a = qVar;
        }

        @Override // U.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicLongArray b(C0226a c0226a) throws IOException {
            ArrayList arrayList = new ArrayList();
            c0226a.a();
            while (c0226a.x()) {
                arrayList.add(Long.valueOf(((Number) this.f740a.b(c0226a)).longValue()));
            }
            c0226a.l();
            int size = arrayList.size();
            AtomicLongArray atomicLongArray = new AtomicLongArray(size);
            for (int i2 = 0; i2 < size; i2++) {
                atomicLongArray.set(i2, ((Long) arrayList.get(i2)).longValue());
            }
            return atomicLongArray;
        }

        @Override // U.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(C0228c c0228c, AtomicLongArray atomicLongArray) throws IOException {
            c0228c.d();
            int length = atomicLongArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                this.f740a.d(c0228c, Long.valueOf(atomicLongArray.get(i2)));
            }
            c0228c.l();
        }
    }

    public static class f extends X.k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public q f741a = null;

        private q f() {
            q qVar = this.f741a;
            if (qVar != null) {
                return qVar;
            }
            throw new IllegalStateException("Adapter for type with cyclic dependency has been used before dependency has been resolved");
        }

        @Override // U.q
        public Object b(C0226a c0226a) {
            return f().b(c0226a);
        }

        @Override // U.q
        public void d(C0228c c0228c, Object obj) {
            f().d(c0228c, obj);
        }

        @Override // X.k
        public q e() {
            return f();
        }

        public void g(q qVar) {
            if (this.f741a != null) {
                throw new AssertionError("Delegate is already set");
            }
            this.f741a = qVar;
        }
    }

    public d() {
        this(W.d.f783g, f708A, Collections.emptyMap(), false, false, false, true, false, false, false, true, m.f746a, f711z, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), f709B, f710C, Collections.emptyList());
    }

    public static void a(Object obj, C0226a c0226a) {
        if (obj != null) {
            try {
                if (c0226a.M() == EnumC0227b.END_DOCUMENT) {
                } else {
                    throw new l("JSON document was not fully consumed.");
                }
            } catch (c0.d e2) {
                throw new l(e2);
            } catch (IOException e3) {
                throw new g(e3);
            }
        }
    }

    public static q b(q qVar) {
        return new C0021d(qVar).a();
    }

    public static q c(q qVar) {
        return new e(qVar).a();
    }

    public static void d(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException(d2 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public static q o(m mVar) {
        return mVar == m.f746a ? X.m.f939t : new c();
    }

    public final q e(boolean z2) {
        return z2 ? X.m.f941v : new a();
    }

    public final q f(boolean z2) {
        return z2 ? X.m.f940u : new b();
    }

    public Object g(C0226a c0226a, C0223a c0223a) {
        boolean z2 = c0226a.z();
        boolean z3 = true;
        c0226a.R(true);
        try {
            try {
                try {
                    c0226a.M();
                    z3 = false;
                    return l(c0223a).b(c0226a);
                } catch (AssertionError e2) {
                    throw new AssertionError("AssertionError (GSON 2.10.1): " + e2.getMessage(), e2);
                } catch (IllegalStateException e3) {
                    throw new l(e3);
                }
            } catch (EOFException e4) {
                if (!z3) {
                    throw new l(e4);
                }
                c0226a.R(z2);
                return null;
            } catch (IOException e5) {
                throw new l(e5);
            }
        } finally {
            c0226a.R(z2);
        }
    }

    public Object h(Reader reader, C0223a c0223a) {
        C0226a c0226aP = p(reader);
        Object objG = g(c0226aP, c0223a);
        a(objG, c0226aP);
        return objG;
    }

    public Object i(String str, C0223a c0223a) {
        if (str == null) {
            return null;
        }
        return h(new StringReader(str), c0223a);
    }

    public Object j(String str, Class cls) {
        return W.k.b(cls).cast(i(str, C0223a.get(cls)));
    }

    public Object k(String str, Type type) {
        return i(str, C0223a.get(type));
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0051, code lost:
    
        r2.g(r4);
        r0.put(r7, r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public U.q l(b0.C0223a r7) {
        /*
            r6 = this;
            java.lang.String r0 = "type must not be null"
            java.util.Objects.requireNonNull(r7, r0)
            java.util.concurrent.ConcurrentMap r0 = r6.f713b
            java.lang.Object r0 = r0.get(r7)
            U.q r0 = (U.q) r0
            if (r0 == 0) goto L10
            return r0
        L10:
            java.lang.ThreadLocal r0 = r6.f712a
            java.lang.Object r0 = r0.get()
            java.util.Map r0 = (java.util.Map) r0
            if (r0 != 0) goto L26
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.ThreadLocal r1 = r6.f712a
            r1.set(r0)
            r1 = 1
            goto L30
        L26:
            java.lang.Object r1 = r0.get(r7)
            U.q r1 = (U.q) r1
            if (r1 == 0) goto L2f
            return r1
        L2f:
            r1 = 0
        L30:
            U.d$f r2 = new U.d$f     // Catch: java.lang.Throwable -> L58
            r2.<init>()     // Catch: java.lang.Throwable -> L58
            r0.put(r7, r2)     // Catch: java.lang.Throwable -> L58
            java.util.List r3 = r6.f716e     // Catch: java.lang.Throwable -> L58
            java.util.Iterator r3 = r3.iterator()     // Catch: java.lang.Throwable -> L58
            r4 = 0
        L3f:
            boolean r5 = r3.hasNext()     // Catch: java.lang.Throwable -> L58
            if (r5 == 0) goto L5a
            java.lang.Object r4 = r3.next()     // Catch: java.lang.Throwable -> L58
            U.r r4 = (U.r) r4     // Catch: java.lang.Throwable -> L58
            U.q r4 = r4.a(r6, r7)     // Catch: java.lang.Throwable -> L58
            if (r4 == 0) goto L3f
            r2.g(r4)     // Catch: java.lang.Throwable -> L58
            r0.put(r7, r4)     // Catch: java.lang.Throwable -> L58
            goto L5a
        L58:
            r7 = move-exception
            goto L82
        L5a:
            if (r1 == 0) goto L61
            java.lang.ThreadLocal r2 = r6.f712a
            r2.remove()
        L61:
            if (r4 == 0) goto L6b
            if (r1 == 0) goto L6a
            java.util.concurrent.ConcurrentMap r6 = r6.f713b
            r6.putAll(r0)
        L6a:
            return r4
        L6b:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "GSON (2.10.1) cannot handle "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r6.<init>(r7)
            throw r6
        L82:
            if (r1 == 0) goto L89
            java.lang.ThreadLocal r6 = r6.f712a
            r6.remove()
        L89:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: U.d.l(b0.a):U.q");
    }

    public q m(Class cls) {
        return l(C0223a.get(cls));
    }

    public q n(r rVar, C0223a c0223a) {
        if (!this.f716e.contains(rVar)) {
            rVar = this.f715d;
        }
        boolean z2 = false;
        for (r rVar2 : this.f716e) {
            if (z2) {
                q qVarA = rVar2.a(this, c0223a);
                if (qVarA != null) {
                    return qVarA;
                }
            } else if (rVar2 == rVar) {
                z2 = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + c0223a);
    }

    public C0226a p(Reader reader) {
        C0226a c0226a = new C0226a(reader);
        c0226a.R(this.f725n);
        return c0226a;
    }

    public C0228c q(Writer writer) throws IOException {
        if (this.f722k) {
            writer.write(")]}'\n");
        }
        C0228c c0228c = new C0228c(writer);
        if (this.f724m) {
            c0228c.G("  ");
        }
        c0228c.F(this.f723l);
        c0228c.H(this.f725n);
        c0228c.I(this.f720i);
        return c0228c;
    }

    public String r(U.f fVar) {
        StringWriter stringWriter = new StringWriter();
        v(fVar, stringWriter);
        return stringWriter.toString();
    }

    public String s(Object obj) {
        return obj == null ? r(h.f743a) : t(obj, obj.getClass());
    }

    public String t(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        x(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public String toString() {
        return "{serializeNulls:" + this.f720i + ",factories:" + this.f716e + ",instanceCreators:" + this.f714c + "}";
    }

    public void u(U.f fVar, C0228c c0228c) {
        boolean zU = c0228c.u();
        c0228c.H(true);
        boolean zT = c0228c.t();
        c0228c.F(this.f723l);
        boolean zR = c0228c.r();
        c0228c.I(this.f720i);
        try {
            try {
                W.m.a(fVar, c0228c);
            } catch (IOException e2) {
                throw new g(e2);
            } catch (AssertionError e3) {
                throw new AssertionError("AssertionError (GSON 2.10.1): " + e3.getMessage(), e3);
            }
        } finally {
            c0228c.H(zU);
            c0228c.F(zT);
            c0228c.I(zR);
        }
    }

    public void v(U.f fVar, Appendable appendable) {
        try {
            u(fVar, q(W.m.b(appendable)));
        } catch (IOException e2) {
            throw new g(e2);
        }
    }

    public void w(Object obj, Type type, C0228c c0228c) {
        q qVarL = l(C0223a.get(type));
        boolean zU = c0228c.u();
        c0228c.H(true);
        boolean zT = c0228c.t();
        c0228c.F(this.f723l);
        boolean zR = c0228c.r();
        c0228c.I(this.f720i);
        try {
            try {
                qVarL.d(c0228c, obj);
            } catch (IOException e2) {
                throw new g(e2);
            } catch (AssertionError e3) {
                throw new AssertionError("AssertionError (GSON 2.10.1): " + e3.getMessage(), e3);
            }
        } finally {
            c0228c.H(zU);
            c0228c.F(zT);
            c0228c.I(zR);
        }
    }

    public void x(Object obj, Type type, Appendable appendable) {
        try {
            w(obj, type, q(W.m.b(appendable)));
        } catch (IOException e2) {
            throw new g(e2);
        }
    }

    public d(W.d dVar, U.c cVar, Map map, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, m mVar, String str, int i2, int i3, List list, List list2, List list3, p pVar, p pVar2, List list4) {
        this.f712a = new ThreadLocal();
        this.f713b = new ConcurrentHashMap();
        this.f717f = dVar;
        this.f718g = cVar;
        this.f719h = map;
        W.c cVar2 = new W.c(map, z9, list4);
        this.f714c = cVar2;
        this.f720i = z2;
        this.f721j = z3;
        this.f722k = z4;
        this.f723l = z5;
        this.f724m = z6;
        this.f725n = z7;
        this.f726o = z8;
        this.f727p = z9;
        this.f731t = mVar;
        this.f728q = str;
        this.f729r = i2;
        this.f730s = i3;
        this.f732u = list;
        this.f733v = list2;
        this.f734w = pVar;
        this.f735x = pVar2;
        this.f736y = list4;
        ArrayList arrayList = new ArrayList();
        arrayList.add(X.m.f918W);
        arrayList.add(X.i.e(pVar));
        arrayList.add(dVar);
        arrayList.addAll(list3);
        arrayList.add(X.m.f898C);
        arrayList.add(X.m.f932m);
        arrayList.add(X.m.f926g);
        arrayList.add(X.m.f928i);
        arrayList.add(X.m.f930k);
        q qVarO = o(mVar);
        arrayList.add(X.m.b(Long.TYPE, Long.class, qVarO));
        arrayList.add(X.m.b(Double.TYPE, Double.class, e(z8)));
        arrayList.add(X.m.b(Float.TYPE, Float.class, f(z8)));
        arrayList.add(X.h.e(pVar2));
        arrayList.add(X.m.f934o);
        arrayList.add(X.m.f936q);
        arrayList.add(X.m.a(AtomicLong.class, b(qVarO)));
        arrayList.add(X.m.a(AtomicLongArray.class, c(qVarO)));
        arrayList.add(X.m.f938s);
        arrayList.add(X.m.f943x);
        arrayList.add(X.m.f900E);
        arrayList.add(X.m.f902G);
        arrayList.add(X.m.a(BigDecimal.class, X.m.f945z));
        arrayList.add(X.m.a(BigInteger.class, X.m.f896A));
        arrayList.add(X.m.a(W.g.class, X.m.f897B));
        arrayList.add(X.m.f904I);
        arrayList.add(X.m.f906K);
        arrayList.add(X.m.f910O);
        arrayList.add(X.m.f912Q);
        arrayList.add(X.m.f916U);
        arrayList.add(X.m.f908M);
        arrayList.add(X.m.f923d);
        arrayList.add(X.c.f843b);
        arrayList.add(X.m.f914S);
        if (a0.d.f978a) {
            arrayList.add(a0.d.f982e);
            arrayList.add(a0.d.f981d);
            arrayList.add(a0.d.f983f);
        }
        arrayList.add(X.a.f837c);
        arrayList.add(X.m.f921b);
        arrayList.add(new X.b(cVar2));
        arrayList.add(new X.g(cVar2, z3));
        X.e eVar = new X.e(cVar2);
        this.f715d = eVar;
        arrayList.add(eVar);
        arrayList.add(X.m.f919X);
        arrayList.add(new X.j(cVar2, cVar, dVar, eVar, list4));
        this.f716e = Collections.unmodifiableList(arrayList);
    }
}
