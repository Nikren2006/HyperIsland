package com.xiaomi.onetrack.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.xiaomi.onetrack.b.n;
import com.xiaomi.onetrack.util.aa;
import com.xiaomi.onetrack.util.q;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2694a = "AdMonitorManager";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f2695b = 204800;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f2696c = 100;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f2697d = 4;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int f2698e = 300;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f2699f = "_id ASC";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int f2700g = 7;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static a f2701h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final C0067a f2702i = new C0067a(com.xiaomi.onetrack.f.a.a());

    /* JADX INFO: renamed from: com.xiaomi.onetrack.a.a$a, reason: collision with other inner class name */
    public static class C0067a extends SQLiteOpenHelper {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final String f2703a = "onetrack_ad";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final String f2704b = "monitor";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final String f2705c = "_id";

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final String f2706d = "appid";

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final String f2707e = "package";

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final String f2708f = "event_name";

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final String f2709g = "url";

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final String f2710h = "timestamp";

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final String f2711i = "send_count";

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private static final int f2712j = 1;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private static final String f2713k = "CREATE TABLE monitor (_id INTEGER PRIMARY KEY AUTOINCREMENT,appid TEXT,package TEXT,event_name TEXT,url TEXT,send_count INTEGER DEFAULT 0,timestamp INTEGER)";

        public C0067a(Context context) {
            super(context, f2703a, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(f2713k);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        }
    }

    private a() {
        c();
    }

    private void f() {
        try {
            this.f2702i.getWritableDatabase().delete(C0067a.f2704b, null, null);
            q.a(f2694a, "delete table monitor");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Not initialized variable reg: 10, insn: 0x0095: MOVE (r9 I:??[OBJECT, ARRAY]) = (r10 I:??[OBJECT, ARRAY]), block:B:17:0x0095 */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ec A[Catch: all -> 0x00d9, PHI: r10
      0x00ec: PHI (r10v4 android.database.Cursor) = (r10v3 android.database.Cursor), (r10v5 android.database.Cursor) binds: [B:42:0x00ea, B:46:0x00fa] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #3 {, blocks: (B:30:0x00d4, B:31:0x00d7, B:51:0x0101, B:52:0x0104, B:43:0x00ec, B:48:0x00fd), top: B:56:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0101 A[Catch: all -> 0x00d9, TryCatch #3 {, blocks: (B:30:0x00d4, B:31:0x00d7, B:51:0x0101, B:52:0x0104, B:43:0x00ec, B:48:0x00fd), top: B:56:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.xiaomi.onetrack.a.c.a b() {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.onetrack.a.a.b():com.xiaomi.onetrack.a.c.a");
    }

    public void c() {
        com.xiaomi.onetrack.a.a.a.a(new c(this), 1000L);
    }

    public void d() {
        synchronized (this.f2702i) {
            Cursor cursorQuery = null;
            try {
                try {
                    SQLiteDatabase writableDatabase = this.f2702i.getWritableDatabase();
                    String[] strArr = {Long.toString(4L)};
                    cursorQuery = writableDatabase.query(C0067a.f2704b, new String[]{"timestamp"}, "send_count >= ? ", strArr, null, null, f2699f);
                    if (cursorQuery.getCount() != 0) {
                        q.a(f2694a, "*** deleted obsolete ad monitor count=" + writableDatabase.delete(C0067a.f2704b, "send_count >= ? ", strArr));
                    }
                    if (q.f3627a) {
                        q.a(f2694a, "after delete obsolete ad monitor record remains=" + e());
                    }
                } catch (Exception e2) {
                    q.d(f2694a, "remove obsolete ad monitor failed with " + e2);
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

    public long e() {
        try {
            return DatabaseUtils.queryNumEntries(this.f2702i.getReadableDatabase(), C0067a.f2704b);
        } catch (Exception e2) {
            q.b(f2694a, "getTotalEventsNumberSync failed with " + e2.getMessage());
            return 0L;
        }
    }

    public static a a() {
        if (f2701h == null) {
            a(com.xiaomi.onetrack.f.a.a());
        }
        return f2701h;
    }

    public static void a(Context context) {
        if (f2701h == null) {
            synchronized (a.class) {
                try {
                    if (f2701h == null) {
                        f2701h = new a();
                    }
                } finally {
                }
            }
        }
    }

    public void a(com.xiaomi.onetrack.f.b bVar) {
        try {
            com.xiaomi.onetrack.a.a.a.a(new b(this, bVar));
        } catch (Throwable th) {
            q.a(f2694a, "filterAdMonitor Throwable：" + th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long a(com.xiaomi.onetrack.a.b.a aVar) {
        synchronized (this.f2702i) {
            try {
                if (!aVar.h()) {
                    q.c(f2694a, "addAdMonitorToDatabase event is inValid, event:" + aVar.a());
                    return -1L;
                }
                SQLiteDatabase writableDatabase = this.f2702i.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("appid", aVar.e());
                contentValues.put("package", aVar.f());
                contentValues.put("event_name", aVar.a());
                contentValues.put("timestamp", Long.valueOf(aVar.d()));
                contentValues.put(C0067a.f2709g, aVar.c());
                long jInsert = writableDatabase.insert(C0067a.f2704b, null, contentValues);
                q.a(f2694a, "DB-Thread: AdMonitorManager.addAdMonitorToDatabase , row=" + jInsert);
                if (q.f3627a) {
                    q.a(f2694a, "添加后，ad monitor url 中事件个数为 " + e());
                }
                return jInsert;
            } catch (Throwable th) {
                q.a(f2694a, "addAdMonitorToDatabase Throwable：" + th.getMessage());
                return -1L;
            }
        }
    }

    public int a(ArrayList<Integer> arrayList) {
        synchronized (this.f2702i) {
            if (arrayList != null) {
                try {
                    if (arrayList.size() != 0) {
                        try {
                            SQLiteDatabase writableDatabase = this.f2702i.getWritableDatabase();
                            StringBuilder sb = new StringBuilder(((Long.toString(arrayList.get(0).intValue()).length() + 1) * arrayList.size()) + 16);
                            sb.append("_id");
                            sb.append(" in (");
                            sb.append(arrayList.get(0));
                            int size = arrayList.size();
                            for (int i2 = 1; i2 < size; i2++) {
                                sb.append(aa.f3429b);
                                sb.append(arrayList.get(i2));
                            }
                            sb.append(")");
                            int iDelete = writableDatabase.delete(C0067a.f2704b, sb.toString(), null);
                            q.a(f2694a, "*** *** deleted ad monitor count " + iDelete);
                            if (q.f3627a) {
                                q.a(f2694a, "after delete ad monitor record remains=" + e());
                            }
                            return iDelete;
                        } catch (Exception e2) {
                            q.b(f2694a, "e=" + e2);
                            return 0;
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return 0;
        }
    }

    public void b(ArrayList<Integer> arrayList) {
        String str;
        String str2;
        synchronized (this.f2702i) {
            if (arrayList != null) {
                try {
                } catch (Throwable th) {
                    q.a(f2694a, "addAdMonitorsRetryCount Throwable:" + th.getMessage());
                } finally {
                }
                if (arrayList.size() > 0) {
                    SQLiteDatabase writableDatabase = null;
                    try {
                        try {
                            writableDatabase = this.f2702i.getWritableDatabase();
                            writableDatabase.beginTransaction();
                            for (Integer num : arrayList) {
                                num.intValue();
                                writableDatabase.execSQL(String.format("update %s set %s = %s + 1 where %s = %s", C0067a.f2704b, C0067a.f2711i, C0067a.f2711i, "_id", num));
                            }
                            writableDatabase.setTransactionSuccessful();
                            try {
                                writableDatabase.endTransaction();
                                writableDatabase.close();
                            } catch (Exception e2) {
                                str = f2694a;
                                str2 = "addAdMonitorsRetryCount endTransaction error: " + e2.getMessage();
                                q.b(str, str2);
                            }
                        } finally {
                        }
                    } catch (Exception e3) {
                        q.b(f2694a, "addAdMonitorsRetryCount Exception: " + e3.getMessage());
                        if (writableDatabase != null) {
                            try {
                                writableDatabase.endTransaction();
                                writableDatabase.close();
                            } catch (Exception e4) {
                                str = f2694a;
                                str2 = "addAdMonitorsRetryCount endTransaction error: " + e4.getMessage();
                                q.b(str, str2);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean a(long j2, long j3, int i2) {
        if (i2 <= 0) {
            return true;
        }
        if (i2 < 4) {
            return Math.abs(j2 - j3) >= ((long) n.a(i2 - 1));
        }
        return false;
    }
}
