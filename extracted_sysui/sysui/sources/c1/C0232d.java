package c1;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: renamed from: c1.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0232d extends C0230b implements InterfaceC0229a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f1359e = new a(null);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0232d f1360f = new C0232d(1, 0);

    /* JADX INFO: renamed from: c1.d$a */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final C0232d a() {
            return C0232d.f1360f;
        }

        public a() {
        }
    }

    public C0232d(int i2, int i3) {
        super(i2, i3, 1);
    }

    @Override // c1.C0230b
    public boolean equals(Object obj) {
        if (obj instanceof C0232d) {
            if (!isEmpty() || !((C0232d) obj).isEmpty()) {
                C0232d c0232d = (C0232d) obj;
                if (c() != c0232d.c() || d() != c0232d.d()) {
                }
            }
            return true;
        }
        return false;
    }

    public boolean h(int i2) {
        return c() <= i2 && i2 <= d();
    }

    @Override // c1.C0230b
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return d() + (c() * 31);
    }

    @Override // c1.InterfaceC0229a
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public Integer getEndInclusive() {
        return Integer.valueOf(d());
    }

    @Override // c1.C0230b
    public boolean isEmpty() {
        return c() > d();
    }

    @Override // c1.InterfaceC0229a
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public Integer getStart() {
        return Integer.valueOf(c());
    }

    @Override // c1.C0230b
    public String toString() {
        return c() + ".." + d();
    }
}
