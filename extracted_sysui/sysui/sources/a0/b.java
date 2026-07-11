package a0;

import U.l;
import U.q;
import U.r;
import b0.C0223a;
import c0.C0226a;
import c0.C0228c;
import c0.EnumC0227b;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public final class b extends q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final r f974b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DateFormat f975a;

    public class a implements r {
        @Override // U.r
        public q a(U.d dVar, C0223a c0223a) {
            Class<Object> rawType = c0223a.getRawType();
            a aVar = null;
            if (rawType == Time.class) {
                return new b(aVar);
            }
            return null;
        }
    }

    public /* synthetic */ b(a aVar) {
        this();
    }

    @Override // U.q
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public Time b(C0226a c0226a) throws IOException {
        Time time;
        if (c0226a.M() == EnumC0227b.NULL) {
            c0226a.I();
            return null;
        }
        String strK = c0226a.K();
        try {
            synchronized (this) {
                time = new Time(this.f975a.parse(strK).getTime());
            }
            return time;
        } catch (ParseException e2) {
            throw new l("Failed parsing '" + strK + "' as SQL Time; at path " + c0226a.w(), e2);
        }
    }

    @Override // U.q
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public void d(C0228c c0228c, Time time) throws IOException {
        String str;
        if (time == null) {
            c0228c.A();
            return;
        }
        synchronized (this) {
            str = this.f975a.format((Date) time);
        }
        c0228c.O(str);
    }

    public b() {
        this.f975a = new SimpleDateFormat("hh:mm:ss a");
    }
}
