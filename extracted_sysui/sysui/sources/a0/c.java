package a0;

import U.q;
import U.r;
import b0.C0223a;
import c0.C0226a;
import c0.C0228c;
import java.sql.Timestamp;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public class c extends q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final r f976b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f977a;

    public class a implements r {
        @Override // U.r
        public q a(U.d dVar, C0223a c0223a) {
            a aVar = null;
            if (c0223a.getRawType() == Timestamp.class) {
                return new c(dVar.m(Date.class), aVar);
            }
            return null;
        }
    }

    public /* synthetic */ c(q qVar, a aVar) {
        this(qVar);
    }

    @Override // U.q
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public Timestamp b(C0226a c0226a) {
        Date date = (Date) this.f977a.b(c0226a);
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }

    @Override // U.q
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void d(C0228c c0228c, Timestamp timestamp) {
        this.f977a.d(c0228c, timestamp);
    }

    public c(q qVar) {
        this.f977a = qVar;
    }
}
