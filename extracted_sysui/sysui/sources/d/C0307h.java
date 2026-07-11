package d;

import android.graphics.Rect;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import l.C0435e;
import p.AbstractC0724d;
import p.AbstractC0727g;

/* JADX INFO: renamed from: d.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0307h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Map f3913c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Map f3914d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Map f3915e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List f3916f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public SparseArrayCompat f3917g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public LongSparseArray f3918h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public List f3919i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Rect f3920j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f3921k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f3922l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f3923m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3924n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final O f3911a = new O();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashSet f3912b = new HashSet();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f3925o = 0;

    public void a(String str) {
        AbstractC0724d.c(str);
        this.f3912b.add(str);
    }

    public Rect b() {
        return this.f3920j;
    }

    public SparseArrayCompat c() {
        return this.f3917g;
    }

    public float d() {
        return (long) ((e() / this.f3923m) * 1000.0f);
    }

    public float e() {
        return this.f3922l - this.f3921k;
    }

    public float f() {
        return this.f3922l;
    }

    public Map g() {
        return this.f3915e;
    }

    public float h(float f2) {
        return AbstractC0727g.i(this.f3921k, this.f3922l, f2);
    }

    public float i() {
        return this.f3923m;
    }

    public Map j() {
        return this.f3914d;
    }

    public List k() {
        return this.f3919i;
    }

    public i.h l(String str) {
        int size = this.f3916f.size();
        for (int i2 = 0; i2 < size; i2++) {
            i.h hVar = (i.h) this.f3916f.get(i2);
            if (hVar.a(str)) {
                return hVar;
            }
        }
        return null;
    }

    public int m() {
        return this.f3925o;
    }

    public O n() {
        return this.f3911a;
    }

    public List o(String str) {
        return (List) this.f3913c.get(str);
    }

    public float p() {
        return this.f3921k;
    }

    public boolean q() {
        return this.f3924n;
    }

    public void r(int i2) {
        this.f3925o += i2;
    }

    public void s(Rect rect, float f2, float f3, float f4, List list, LongSparseArray longSparseArray, Map map, Map map2, SparseArrayCompat sparseArrayCompat, Map map3, List list2) {
        this.f3920j = rect;
        this.f3921k = f2;
        this.f3922l = f3;
        this.f3923m = f4;
        this.f3919i = list;
        this.f3918h = longSparseArray;
        this.f3913c = map;
        this.f3914d = map2;
        this.f3917g = sparseArrayCompat;
        this.f3915e = map3;
        this.f3916f = list2;
    }

    public C0435e t(long j2) {
        return (C0435e) this.f3918h.get(j2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LottieComposition:\n");
        Iterator it = this.f3919i.iterator();
        while (it.hasNext()) {
            sb.append(((C0435e) it.next()).y("\t"));
        }
        return sb.toString();
    }

    public void u(boolean z2) {
        this.f3924n = z2;
    }

    public void v(boolean z2) {
        this.f3911a.b(z2);
    }
}
