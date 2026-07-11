package com.xiaomi.onetrack.a;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.xiaomi.onetrack.a.a;
import com.xiaomi.onetrack.util.q;
import java.util.Calendar;

/* JADX INFO: loaded from: classes2.dex */
class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ a f2732a;

    public c(a aVar) {
        this.f2732a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f2732a.f2702i == null) {
            return;
        }
        synchronized (this.f2732a.f2702i) {
            Cursor cursorQuery = null;
            try {
                try {
                    SQLiteDatabase writableDatabase = this.f2732a.f2702i.getWritableDatabase();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(6, calendar.get(6) - 7);
                    calendar.set(11, 0);
                    calendar.set(12, 0);
                    calendar.set(13, 0);
                    String[] strArr = {Long.toString(calendar.getTimeInMillis())};
                    cursorQuery = writableDatabase.query(a.C0067a.f2704b, new String[]{"timestamp"}, "timestamp < ? ", strArr, null, null, "timestamp ASC");
                    if (cursorQuery.getCount() != 0) {
                        q.a("AdMonitorManager", "*** deleted obsolete ad monitor count=" + writableDatabase.delete(a.C0067a.f2704b, "timestamp < ? ", strArr));
                    }
                    if (q.f3627a) {
                        q.a("AdMonitorManager", "after delete obsolete ad monitor record remains=" + this.f2732a.e());
                    }
                } catch (Exception e2) {
                    q.d("AdMonitorManager", "remove obsolete ad monitor failed with " + e2);
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
