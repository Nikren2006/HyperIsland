package a0;

import U.l;
import U.q;
import U.r;
import b0.C0223a;
import c0.C0226a;
import c0.C0228c;
import c0.EnumC0227b;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX INFO: renamed from: a0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0196a extends q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final r f972b = new C0028a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DateFormat f973a;

    /* JADX INFO: renamed from: a0.a$a, reason: collision with other inner class name */
    public class C0028a implements r {
        @Override // U.r
        public q a(U.d dVar, C0223a c0223a) {
            Class<Object> rawType = c0223a.getRawType();
            C0028a c0028a = null;
            if (rawType == Date.class) {
                return new C0196a(c0028a);
            }
            return null;
        }
    }

    public /* synthetic */ C0196a(C0028a c0028a) {
        this();
    }

    @Override // U.q
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public Date b(C0226a c0226a) throws IOException {
        java.util.Date date;
        if (c0226a.M() == EnumC0227b.NULL) {
            c0226a.I();
            return null;
        }
        String strK = c0226a.K();
        try {
            synchronized (this) {
                date = this.f973a.parse(strK);
            }
            return new Date(date.getTime());
        } catch (ParseException e2) {
            throw new l("Failed parsing '" + strK + "' as SQL Date; at path " + c0226a.w(), e2);
        }
    }

    @Override // U.q
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void d(C0228c c0228c, Date date) throws IOException {
        String str;
        if (date == null) {
            c0228c.A();
            return;
        }
        synchronized (this) {
            str = this.f973a.format((java.util.Date) date);
        }
        c0228c.O(str);
    }

    public C0196a() {
        this.f973a = new SimpleDateFormat("MMM d, yyyy");
    }
}
