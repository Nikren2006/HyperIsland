package k;

import d.F;
import l.AbstractC0432b;
import p.AbstractC0724d;

/* JADX INFO: loaded from: classes.dex */
public class i implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4864a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f4865b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f4866c;

    public enum a {
        MERGE,
        ADD,
        SUBTRACT,
        INTERSECT,
        EXCLUDE_INTERSECTIONS;

        public static a a(int i2) {
            return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? MERGE : EXCLUDE_INTERSECTIONS : INTERSECT : SUBTRACT : ADD : MERGE;
        }
    }

    public i(String str, a aVar, boolean z2) {
        this.f4864a = str;
        this.f4865b = aVar;
        this.f4866c = z2;
    }

    @Override // k.c
    public f.c a(F f2, AbstractC0432b abstractC0432b) {
        if (f2.F()) {
            return new f.l(this);
        }
        AbstractC0724d.c("Animation contains merge paths but they are disabled.");
        return null;
    }

    public a b() {
        return this.f4865b;
    }

    public String c() {
        return this.f4864a;
    }

    public boolean d() {
        return this.f4866c;
    }

    public String toString() {
        return "MergePaths{mode=" + this.f4865b + '}';
    }
}
