package a0;

import U.r;
import java.sql.Date;
import java.sql.Timestamp;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f978a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final X.d f979b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final X.d f980c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final r f981d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final r f982e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final r f983f;

    public class a extends X.d {
        public a(Class cls) {
            super(cls);
        }
    }

    public class b extends X.d {
        public b(Class cls) {
            super(cls);
        }
    }

    static {
        boolean z2;
        try {
            Class.forName("java.sql.Date");
            z2 = true;
        } catch (ClassNotFoundException unused) {
            z2 = false;
        }
        f978a = z2;
        if (z2) {
            f979b = new a(Date.class);
            f980c = new b(Timestamp.class);
            f981d = C0196a.f972b;
            f982e = a0.b.f974b;
            f983f = c.f976b;
            return;
        }
        f979b = null;
        f980c = null;
        f981d = null;
        f982e = null;
        f983f = null;
    }
}
