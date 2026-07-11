package l;

import d.C0307h;
import j.C0409b;
import j.j;
import j.k;
import j.l;
import java.util.List;
import java.util.Locale;
import k.C0425a;
import n.C0707j;

/* JADX INFO: renamed from: l.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0435e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f5109a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0307h f5110b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f5111c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f5112d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f5113e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f5114f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f5115g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List f5116h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final l f5117i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f5118j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f5119k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f5120l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f5121m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final float f5122n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f5123o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f5124p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final j f5125q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final k f5126r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C0409b f5127s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final List f5128t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final b f5129u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final boolean f5130v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C0425a f5131w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final C0707j f5132x;

    /* JADX INFO: renamed from: l.e$a */
    public enum a {
        PRE_COMP,
        SOLID,
        IMAGE,
        NULL,
        SHAPE,
        TEXT,
        UNKNOWN
    }

    /* JADX INFO: renamed from: l.e$b */
    public enum b {
        NONE,
        ADD,
        INVERT,
        LUMA,
        LUMA_INVERTED,
        UNKNOWN
    }

    public C0435e(List list, C0307h c0307h, String str, long j2, a aVar, long j3, String str2, List list2, l lVar, int i2, int i3, int i4, float f2, float f3, int i5, int i6, j jVar, k kVar, List list3, b bVar, C0409b c0409b, boolean z2, C0425a c0425a, C0707j c0707j) {
        this.f5109a = list;
        this.f5110b = c0307h;
        this.f5111c = str;
        this.f5112d = j2;
        this.f5113e = aVar;
        this.f5114f = j3;
        this.f5115g = str2;
        this.f5116h = list2;
        this.f5117i = lVar;
        this.f5118j = i2;
        this.f5119k = i3;
        this.f5120l = i4;
        this.f5121m = f2;
        this.f5122n = f3;
        this.f5123o = i5;
        this.f5124p = i6;
        this.f5125q = jVar;
        this.f5126r = kVar;
        this.f5128t = list3;
        this.f5129u = bVar;
        this.f5127s = c0409b;
        this.f5130v = z2;
        this.f5131w = c0425a;
        this.f5132x = c0707j;
    }

    public C0425a a() {
        return this.f5131w;
    }

    public C0307h b() {
        return this.f5110b;
    }

    public C0707j c() {
        return this.f5132x;
    }

    public long d() {
        return this.f5112d;
    }

    public List e() {
        return this.f5128t;
    }

    public a f() {
        return this.f5113e;
    }

    public List g() {
        return this.f5116h;
    }

    public b h() {
        return this.f5129u;
    }

    public String i() {
        return this.f5111c;
    }

    public long j() {
        return this.f5114f;
    }

    public int k() {
        return this.f5124p;
    }

    public int l() {
        return this.f5123o;
    }

    public String m() {
        return this.f5115g;
    }

    public List n() {
        return this.f5109a;
    }

    public int o() {
        return this.f5120l;
    }

    public int p() {
        return this.f5119k;
    }

    public int q() {
        return this.f5118j;
    }

    public float r() {
        return this.f5122n / this.f5110b.e();
    }

    public j s() {
        return this.f5125q;
    }

    public k t() {
        return this.f5126r;
    }

    public String toString() {
        return y("");
    }

    public C0409b u() {
        return this.f5127s;
    }

    public float v() {
        return this.f5121m;
    }

    public l w() {
        return this.f5117i;
    }

    public boolean x() {
        return this.f5130v;
    }

    public String y(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(i());
        sb.append("\n");
        C0435e c0435eT = this.f5110b.t(j());
        if (c0435eT != null) {
            sb.append("\t\tParents: ");
            sb.append(c0435eT.i());
            C0435e c0435eT2 = this.f5110b.t(c0435eT.j());
            while (c0435eT2 != null) {
                sb.append("->");
                sb.append(c0435eT2.i());
                c0435eT2 = this.f5110b.t(c0435eT2.j());
            }
            sb.append(str);
            sb.append("\n");
        }
        if (!g().isEmpty()) {
            sb.append(str);
            sb.append("\tMasks: ");
            sb.append(g().size());
            sb.append("\n");
        }
        if (q() != 0 && p() != 0) {
            sb.append(str);
            sb.append("\tBackground: ");
            sb.append(String.format(Locale.US, "%dx%d %X\n", Integer.valueOf(q()), Integer.valueOf(p()), Integer.valueOf(o())));
        }
        if (!this.f5109a.isEmpty()) {
            sb.append(str);
            sb.append("\tShapes:\n");
            for (Object obj : this.f5109a) {
                sb.append(str);
                sb.append("\t\t");
                sb.append(obj);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
