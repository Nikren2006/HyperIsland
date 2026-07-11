package com.xiaomi.onetrack.c;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.Calendar;

/* JADX INFO: loaded from: classes2.dex */
class r implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ m f3203a;

    public r(m mVar) {
        this.f3203a = mVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f3203a.f3176i == null) {
            return;
        }
        synchronized (this.f3203a.f3176i) {
            Cursor cursorQuery = null;
            try {
                try {
                    SQLiteDatabase writableDatabase = this.f3203a.f3176i.getWritableDatabase();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(6, calendar.get(6) - 7);
                    calendar.set(11, 0);
                    calendar.set(12, 0);
                    calendar.set(13, 0);
                    String[] strArr = {Long.toString(calendar.getTimeInMillis())};
                    cursorQuery = writableDatabase.query("events", new String[]{"timestamp"}, "timestamp < ? ", strArr, null, null, "timestamp ASC");
                    if (cursorQuery.getCount() != 0) {
                        com.xiaomi.onetrack.util.q.a("SystemImpCacheManager", "*** deleted obsolete item count=" + writableDatabase.delete("events", "timestamp < ? ", strArr));
                    }
                    com.xiaomi.onetrack.util.q.a("SystemImpCacheManager", "after delete obsolete record remains=" + this.f3203a.d());
                } catch (Throwable th) {
                    if (cursorQuery != null) {
                        try {
                            cursorQuery.close();
                        } catch (Exception unused) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e2) {
                com.xiaomi.onetrack.util.q.d("SystemImpCacheManager", "remove obsolete messages failed with " + e2);
                if (cursorQuery != null) {
                }
            }
            try {
                cursorQuery.close();
            } catch (Exception unused2) {
            }
        }
    }
}
