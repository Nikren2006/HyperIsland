package com.xiaomi.onetrack.c;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.Calendar;

/* JADX INFO: loaded from: classes2.dex */
class h implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f3146a;

    public h(d dVar) {
        this.f3146a = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f3146a.f3129k == null) {
            return;
        }
        synchronized (this.f3146a.f3129k) {
            Cursor cursorQuery = null;
            try {
                try {
                    SQLiteDatabase writableDatabase = this.f3146a.f3129k.getWritableDatabase();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(6, calendar.get(6) - 7);
                    calendar.set(11, 0);
                    calendar.set(12, 0);
                    calendar.set(13, 0);
                    String[] strArr = {Long.toString(calendar.getTimeInMillis())};
                    cursorQuery = writableDatabase.query("events", new String[]{"timestamp"}, "timestamp < ? ", strArr, null, null, "timestamp ASC");
                    if (cursorQuery.getCount() != 0) {
                        com.xiaomi.onetrack.util.q.a("EventManager", "*** deleted obsolete item count=" + writableDatabase.delete("events", "timestamp < ? ", strArr));
                    }
                    long jD = d.a().d();
                    com.xiaomi.onetrack.b.n.a(jD == 0);
                    com.xiaomi.onetrack.util.q.a("EventManager", "after delete obsolete record remains=" + jD);
                } catch (Exception e2) {
                    com.xiaomi.onetrack.util.q.d("EventManager", "remove obsolete events failed with " + e2);
                    if (cursorQuery != null) {
                    }
                }
                cursorQuery.close();
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }
    }
}
