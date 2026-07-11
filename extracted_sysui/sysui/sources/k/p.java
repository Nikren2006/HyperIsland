package k;

import d.F;
import java.util.Arrays;
import java.util.List;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class p implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4909a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f4910b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f4911c;

    public p(String str, List list, boolean z2) {
        this.f4909a = str;
        this.f4910b = list;
        this.f4911c = z2;
    }

    @Override // k.c
    public f.c a(F f2, AbstractC0432b abstractC0432b) {
        return new f.d(f2, abstractC0432b, this);
    }

    public List b() {
        return this.f4910b;
    }

    public String c() {
        return this.f4909a;
    }

    public boolean d() {
        return this.f4911c;
    }

    public String toString() {
        return "ShapeGroup{name='" + this.f4909a + "' Shapes: " + Arrays.toString(this.f4910b.toArray()) + '}';
    }
}
