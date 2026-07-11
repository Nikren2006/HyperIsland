package X;

import U.q;
import U.r;
import b0.C0223a;
import c0.C0226a;
import c0.C0228c;
import c0.EnumC0227b;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class c extends q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final r f843b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f844a;

    public class a implements r {
        @Override // U.r
        public q a(U.d dVar, C0223a c0223a) {
            if (c0223a.getRawType() == Date.class) {
                return new c();
            }
            return null;
        }
    }

    public c() {
        ArrayList arrayList = new ArrayList();
        this.f844a = arrayList;
        Locale locale = Locale.US;
        arrayList.add(DateFormat.getDateTimeInstance(2, 2, locale));
        if (!Locale.getDefault().equals(locale)) {
            arrayList.add(DateFormat.getDateTimeInstance(2, 2));
        }
        if (W.e.d()) {
            arrayList.add(W.j.c(2, 2));
        }
    }

    public final Date e(C0226a c0226a) throws IOException {
        String strK = c0226a.K();
        synchronized (this.f844a) {
            try {
                Iterator it = this.f844a.iterator();
                while (it.hasNext()) {
                    try {
                        return ((DateFormat) it.next()).parse(strK);
                    } catch (ParseException unused) {
                    }
                }
                try {
                    return Y.a.c(strK, new ParsePosition(0));
                } catch (ParseException e2) {
                    throw new U.l("Failed parsing '" + strK + "' as Date; at path " + c0226a.w(), e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // U.q
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Date b(C0226a c0226a) throws IOException {
        if (c0226a.M() != EnumC0227b.NULL) {
            return e(c0226a);
        }
        c0226a.I();
        return null;
    }

    @Override // U.q
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public void d(C0228c c0228c, Date date) throws IOException {
        String str;
        if (date == null) {
            c0228c.A();
            return;
        }
        DateFormat dateFormat = (DateFormat) this.f844a.get(0);
        synchronized (this.f844a) {
            str = dateFormat.format(date);
        }
        c0228c.O(str);
    }
}
