package i1;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes2.dex */
public final class h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f4604b = new b(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f4605c = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f4606a;

    public static final class a extends c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Throwable f4607a;

        public a(Throwable th) {
            this.f4607a = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof a) && kotlin.jvm.internal.n.c(this.f4607a, ((a) obj).f4607a);
        }

        public int hashCode() {
            Throwable th = this.f4607a;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        @Override // i1.h.c
        public String toString() {
            return "Closed(" + this.f4607a + ')';
        }
    }

    public static final class b {
        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Object a(Throwable th) {
            return h.c(new a(th));
        }

        public final Object b() {
            return h.c(h.f4605c);
        }

        public final Object c(Object obj) {
            return h.c(obj);
        }

        public b() {
        }
    }

    public static class c {
        public String toString() {
            return "Failed";
        }
    }

    public /* synthetic */ h(Object obj) {
        this.f4606a = obj;
    }

    public static final /* synthetic */ h b(Object obj) {
        return new h(obj);
    }

    public static Object c(Object obj) {
        return obj;
    }

    public static boolean d(Object obj, Object obj2) {
        return (obj2 instanceof h) && kotlin.jvm.internal.n.c(obj, ((h) obj2).k());
    }

    public static final Throwable e(Object obj) {
        a aVar = obj instanceof a ? (a) obj : null;
        if (aVar != null) {
            return aVar.f4607a;
        }
        return null;
    }

    public static final Object f(Object obj) {
        if (obj instanceof c) {
            return null;
        }
        return obj;
    }

    public static int g(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public static final boolean h(Object obj) {
        return obj instanceof a;
    }

    public static final boolean i(Object obj) {
        return !(obj instanceof c);
    }

    public static String j(Object obj) {
        if (obj instanceof a) {
            return ((a) obj).toString();
        }
        return "Value(" + obj + ')';
    }

    public boolean equals(Object obj) {
        return d(this.f4606a, obj);
    }

    public int hashCode() {
        return g(this.f4606a);
    }

    public final /* synthetic */ Object k() {
        return this.f4606a;
    }

    public String toString() {
        return j(this.f4606a);
    }
}
