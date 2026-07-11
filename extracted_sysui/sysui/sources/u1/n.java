package u1;

import I0.G;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import s1.c;
import s1.f;

/* JADX INFO: loaded from: classes2.dex */
public class n implements s1.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6878a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f6879b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f6880c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f6881d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String[] f6882e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List[] f6883f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean[] f6884g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Map f6885h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final H0.d f6886i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final H0.d f6887j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final H0.d f6888k;

    public static final class a extends kotlin.jvm.internal.o implements Function0 {
        public a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Integer invoke() {
            n nVar = n.this;
            return Integer.valueOf(o.a(nVar, nVar.n()));
        }
    }

    public static final class b extends kotlin.jvm.internal.o implements Function0 {
        public b() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final q1.b[] invoke() {
            q1.b[] bVarArrChildSerializers;
            g gVar = n.this.f6879b;
            return (gVar == null || (bVarArrChildSerializers = gVar.childSerializers()) == null) ? p.f6893a : bVarArrChildSerializers;
        }
    }

    public static final class c extends kotlin.jvm.internal.o implements Function1 {
        public c() {
            super(1);
        }

        public final CharSequence b(int i2) {
            return n.this.e(i2) + ": " + n.this.g(i2).h();
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return b(((Number) obj).intValue());
        }
    }

    public static final class d extends kotlin.jvm.internal.o implements Function0 {
        public d() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final s1.c[] invoke() {
            ArrayList arrayList;
            q1.b[] bVarArrTypeParametersSerializers;
            g gVar = n.this.f6879b;
            if (gVar == null || (bVarArrTypeParametersSerializers = gVar.typeParametersSerializers()) == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList(bVarArrTypeParametersSerializers.length);
                for (q1.b bVar : bVarArrTypeParametersSerializers) {
                    arrayList.add(bVar.getDescriptor());
                }
            }
            return m.a(arrayList);
        }
    }

    public n(String serialName, g gVar, int i2) {
        kotlin.jvm.internal.n.g(serialName, "serialName");
        this.f6878a = serialName;
        this.f6879b = gVar;
        this.f6880c = i2;
        this.f6881d = -1;
        String[] strArr = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            strArr[i3] = "[UNINITIALIZED]";
        }
        this.f6882e = strArr;
        int i4 = this.f6880c;
        this.f6883f = new List[i4];
        this.f6884g = new boolean[i4];
        this.f6885h = G.f();
        H0.f fVar = H0.f.f293b;
        this.f6886i = H0.e.a(fVar, new b());
        this.f6887j = H0.e.a(fVar, new d());
        this.f6888k = H0.e.a(fVar, new a());
    }

    private final int o() {
        return ((Number) this.f6888k.getValue()).intValue();
    }

    @Override // s1.c
    public boolean a() {
        return c.a.b(this);
    }

    @Override // s1.c
    public int b(String name) {
        kotlin.jvm.internal.n.g(name, "name");
        Integer num = (Integer) this.f6885h.get(name);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    @Override // s1.c
    public s1.e c() {
        return f.a.f6480a;
    }

    @Override // s1.c
    public final int d() {
        return this.f6880c;
    }

    @Override // s1.c
    public String e(int i2) {
        return this.f6882e[i2];
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof n) {
            s1.c cVar = (s1.c) obj;
            if (kotlin.jvm.internal.n.c(h(), cVar.h()) && Arrays.equals(n(), ((n) obj).n()) && d() == cVar.d()) {
                int iD = d();
                for (int i2 = 0; i2 < iD; i2++) {
                    if (kotlin.jvm.internal.n.c(g(i2).h(), cVar.g(i2).h()) && kotlin.jvm.internal.n.c(g(i2).c(), cVar.g(i2).c())) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override // s1.c
    public List f(int i2) {
        List list = this.f6883f[i2];
        return list == null ? I0.m.h() : list;
    }

    @Override // s1.c
    public s1.c g(int i2) {
        return m()[i2].getDescriptor();
    }

    @Override // s1.c
    public String h() {
        return this.f6878a;
    }

    public int hashCode() {
        return o();
    }

    @Override // s1.c
    public boolean i(int i2) {
        return this.f6884g[i2];
    }

    @Override // s1.c
    public boolean isInline() {
        return c.a.a(this);
    }

    public final void k(String name, boolean z2) {
        kotlin.jvm.internal.n.g(name, "name");
        String[] strArr = this.f6882e;
        int i2 = this.f6881d + 1;
        this.f6881d = i2;
        strArr[i2] = name;
        this.f6884g[i2] = z2;
        this.f6883f[i2] = null;
        if (i2 == this.f6880c - 1) {
            this.f6885h = l();
        }
    }

    public final Map l() {
        HashMap map = new HashMap();
        int length = this.f6882e.length;
        for (int i2 = 0; i2 < length; i2++) {
            map.put(this.f6882e[i2], Integer.valueOf(i2));
        }
        return map;
    }

    public final q1.b[] m() {
        return (q1.b[]) this.f6886i.getValue();
    }

    public final s1.c[] n() {
        return (s1.c[]) this.f6887j.getValue();
    }

    public String toString() {
        return I0.u.T(c1.f.l(0, this.f6880c), ", ", h() + '(', ")", 0, null, new c(), 24, null);
    }
}
