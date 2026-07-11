package com.xiaomi.onetrack.c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteBlobTooBigException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.xiaomi.onetrack.c.b;
import com.xiaomi.onetrack.util.aa;
import java.io.Closeable;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f3165a = 104857600;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3166b = "SystemImpCacheManager";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3167c = "systemimp_cache";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static String f3168d = "systemimp_cache_%s";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static volatile m f3169e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static String f3170f = "system_imp_cache_manager";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final int f3171h = 307200;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final String f3172j = "_id ASC";

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final int f3173k = 100;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final int f3174l = 7;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Handler f3175g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private a f3176i;

    public static class a extends SQLiteOpenHelper {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final String f3177a = "onetrack_cache";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final String f3178b = "events";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final String f3179c = "_id";

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final String f3180d = "appid";

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final String f3181e = "event_name";

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final String f3182f = "data";

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final String f3183g = "attribute";

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final String f3184h = "timestamp";

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private static final int f3185i = 1;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private static final String f3186j = "CREATE TABLE events (_id INTEGER PRIMARY KEY AUTOINCREMENT,appid TEXT,event_name TEXT,data TEXT,attribute TEXT,timestamp INTEGER)";

        public a(Context context) {
            super(context, f3177a, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(f3186j);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        }
    }

    private m() {
        try {
            this.f3176i = new a(com.xiaomi.onetrack.f.a.a());
            HandlerThread handlerThread = new HandlerThread(f3170f);
            handlerThread.start();
            this.f3175g = new Handler(handlerThread.getLooper());
            c();
        } catch (Throwable th) {
            com.xiaomi.onetrack.util.q.b(f3166b, "SystemImpCacheManager init Throwable: " + th.getMessage());
        }
    }

    public static void b() {
        if (f3169e == null) {
            synchronized (m.class) {
                try {
                    if (f3169e == null) {
                        f3169e = new m();
                    }
                } finally {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2, String str3, String str4) {
        try {
            synchronized (this.f3176i) {
                try {
                    com.xiaomi.onetrack.util.q.a(f3166b, "addEvent->appId: " + str + ", eventName: " + str2);
                    File databasePath = com.xiaomi.onetrack.f.a.a().getDatabasePath(this.f3176i.getDatabaseName());
                    if (databasePath != null && databasePath.length() > 104857600) {
                        com.xiaomi.onetrack.util.q.c(f3166b, "DB size is " + databasePath.length() + " bytes, exceed max size!");
                        return;
                    }
                    SQLiteDatabase writableDatabase = this.f3176i.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("appid", str);
                    contentValues.put("event_name", str2);
                    contentValues.put(a.f3183g, str4);
                    contentValues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
                    byte[] bArrB = com.xiaomi.onetrack.d.a.b(str3);
                    if (bArrB == null) {
                        com.xiaomi.onetrack.util.q.c(f3166b, "encryptedBytes is null");
                        return;
                    }
                    contentValues.put("data", com.xiaomi.onetrack.d.c.a(bArrB));
                    long jInsert = writableDatabase.insert("events", null, contentValues);
                    com.xiaomi.onetrack.util.q.a(f3166b, "doSaveCacheData , row=" + jInsert);
                    if (jInsert == -1) {
                        com.xiaomi.onetrack.util.q.c(f3166b, "doSaveCacheData failed");
                    } else if (com.xiaomi.onetrack.util.q.f3627a) {
                        com.xiaomi.onetrack.util.q.a(f3166b, "添加后，DB 中事件个数为 " + d());
                    }
                } finally {
                }
            }
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b(f3166b, "doSaveData exception: ", e2);
        }
    }

    private static String e() {
        return com.xiaomi.onetrack.f.a.a().getFilesDir().getAbsolutePath() + File.separator + f3167c;
    }

    private void f() {
        try {
            this.f3176i.getWritableDatabase().delete("events", null, null);
            com.xiaomi.onetrack.util.q.a(f3166b, "delete table events");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public long d() {
        try {
            return DatabaseUtils.queryNumEntries(this.f3176i.getReadableDatabase(), "events");
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b(f3166b, "getTotalCacheEventsNumberSync failed with " + e2.getMessage());
            return 0L;
        }
    }

    public static m a() {
        if (f3169e == null) {
            b();
        }
        return f3169e;
    }

    public void a(String str, String str2, String str3) {
        if (this.f3175g == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.f3175g.post(new n(this, str, str2, str3));
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0075, code lost:
    
        r0[r1].delete();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void b(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = "SystemImpCacheManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            r1.<init>()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.lang.String r2 = "removeObsoleteFile->appId: "
            r1.append(r2)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            r1.append(r5)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            com.xiaomi.onetrack.util.q.a(r0, r1)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.io.File r0 = new java.io.File     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.lang.String r1 = e()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            boolean r1 = r0.exists()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            if (r1 == 0) goto L82
            boolean r1 = r0.isDirectory()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            if (r1 != 0) goto L2d
            goto L82
        L2d:
            java.lang.String r1 = com.xiaomi.onetrack.c.m.f3168d     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.lang.Object[] r5 = new java.lang.Object[]{r5}     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.lang.String r5 = java.lang.String.format(r1, r5)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.lang.String r1 = "SystemImpCacheManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            r2.<init>()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.lang.String r3 = "removeObsoleteFile->dirFile: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.lang.String r3 = r0.getAbsolutePath()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            r2.append(r3)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.lang.String r3 = ", fileName: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            r2.append(r5)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            com.xiaomi.onetrack.util.q.a(r1, r2)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.io.File[] r0 = r0.listFiles()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            r1 = 0
        L5e:
            int r2 = r0.length     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            if (r1 >= r2) goto L9e
            r2 = r0[r1]     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            boolean r2 = r2.isFile()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            if (r2 == 0) goto L7f
            r2 = r0[r1]     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.lang.String r2 = r2.getName()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            boolean r2 = r2.equalsIgnoreCase(r5)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            if (r2 == 0) goto L7f
            r5 = r0[r1]     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            r5.delete()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            goto L9e
        L7b:
            r5 = move-exception
            goto La0
        L7d:
            r5 = move-exception
            goto L84
        L7f:
            int r1 = r1 + 1
            goto L5e
        L82:
            monitor-exit(r4)
            return
        L84:
            java.lang.String r0 = "SystemImpCacheManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7b
            r1.<init>()     // Catch: java.lang.Throwable -> L7b
            java.lang.String r2 = "systemimp removeObsoleteEvent error: "
            r1.append(r2)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L7b
            r1.append(r5)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r5 = r1.toString()     // Catch: java.lang.Throwable -> L7b
            com.xiaomi.onetrack.util.q.b(r0, r5)     // Catch: java.lang.Throwable -> L7b
        L9e:
            monitor-exit(r4)
            return
        La0:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L7b
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.onetrack.c.m.b(java.lang.String):void");
    }

    public void a(String str, String str2, String str3, String str4) {
        if (this.f3175g == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.f3175g.post(new o(this, str, str2, str3, str4));
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x00b6: MOVE (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:22:0x00b6 */
    public b a(String str) {
        Closeable closeable;
        Cursor cursorQuery;
        Closeable closeable2;
        boolean z2;
        synchronized (this.f3176i) {
            try {
                try {
                    try {
                        com.xiaomi.onetrack.util.q.a(f3166b, "getCacheEventsDataSync start");
                        cursorQuery = this.f3176i.getReadableDatabase().query("events", new String[]{"_id", "event_name", "data", a.f3183g}, "appid = ? ", new String[]{str}, null, null, f3172j, String.valueOf(100));
                        try {
                            int columnIndex = cursorQuery.getColumnIndex("_id");
                            int columnIndex2 = cursorQuery.getColumnIndex("event_name");
                            int columnIndex3 = cursorQuery.getColumnIndex("data");
                            int columnIndex4 = cursorQuery.getColumnIndex(a.f3183g);
                            ArrayList arrayList = new ArrayList();
                            int length = 0;
                            while (cursorQuery.moveToNext()) {
                                try {
                                    long j2 = cursorQuery.getLong(columnIndex);
                                    String string = cursorQuery.getString(columnIndex2);
                                    String string2 = cursorQuery.getString(columnIndex3);
                                    String string3 = cursorQuery.getString(columnIndex4);
                                    String strA = string2 != null ? com.xiaomi.onetrack.d.a.a(com.xiaomi.onetrack.d.c.a(string2)) : null;
                                    if (TextUtils.isEmpty(strA)) {
                                        continue;
                                    } else {
                                        b.a aVar = new b.a();
                                        aVar.f3110a = j2;
                                        aVar.f3111b = string;
                                        aVar.f3112c = strA;
                                        aVar.f3113d = string3;
                                        arrayList.add(aVar);
                                        length += strA.length();
                                        if (length >= f3171h) {
                                            com.xiaomi.onetrack.util.q.a(f3166b, "reached max len: " + length);
                                            break;
                                        }
                                        continue;
                                    }
                                } catch (Throwable th) {
                                    com.xiaomi.onetrack.util.q.b(f3166b, "Finally *** error ***", th);
                                }
                            }
                            if (arrayList.size() > 0) {
                                if (!cursorQuery.isAfterLast()) {
                                    z2 = false;
                                } else if (arrayList.size() == 100) {
                                    com.xiaomi.onetrack.util.q.a(f3166b, "reach max number per one query ");
                                    z2 = false;
                                } else {
                                    com.xiaomi.onetrack.util.q.a(f3166b, "cursor isAfterLast");
                                    z2 = true;
                                }
                                b bVar = new b(arrayList, z2);
                                com.xiaomi.onetrack.util.n.a(cursorQuery);
                                return bVar;
                            }
                        } catch (SQLiteBlobTooBigException e2) {
                            e = e2;
                            com.xiaomi.onetrack.util.q.b(f3166b, "blob too big ***", e);
                            f();
                        } catch (Exception e3) {
                            e = e3;
                            com.xiaomi.onetrack.util.q.b(f3166b, "getEventsDataSync error", e);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        closeable = closeable2;
                        com.xiaomi.onetrack.util.n.a(closeable);
                        throw th;
                    }
                } catch (SQLiteBlobTooBigException e4) {
                    e = e4;
                    cursorQuery = null;
                } catch (Exception e5) {
                    e = e5;
                    cursorQuery = null;
                } catch (Throwable th3) {
                    th = th3;
                    closeable = null;
                    com.xiaomi.onetrack.util.n.a(closeable);
                    throw th;
                }
                com.xiaomi.onetrack.util.n.a(cursorQuery);
                return null;
            } finally {
            }
        }
    }

    public void b(String str, String str2, String str3, String str4) {
        if (this.f3175g != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
            this.f3175g.post(new q(this, str, str2, str3, str4));
        } else {
            com.xiaomi.onetrack.util.q.a(f3166b, "trackPubSubSystemImpCache has id is null");
        }
    }

    public void c(String str) {
        if (this.f3175g != null && !TextUtils.isEmpty(str)) {
            this.f3175g.post(new p(this, str));
        } else {
            com.xiaomi.onetrack.util.q.a(f3166b, "trackPubSubSystemImpCache has id is null");
        }
    }

    public void c() {
        this.f3175g.post(new r(this));
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00f2, code lost:
    
        com.xiaomi.onetrack.util.q.a(com.xiaomi.onetrack.c.m.f3166b, "满足条件的记录为空，即将返回, appId=" + r20);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized void a(java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, boolean r21) {
        /*
            Method dump skipped, instruction units count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.onetrack.c.m.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean):void");
    }

    public int a(ArrayList<Long> arrayList) {
        synchronized (this.f3176i) {
            if (arrayList != null) {
                try {
                    if (arrayList.size() != 0) {
                        try {
                            SQLiteDatabase writableDatabase = this.f3176i.getWritableDatabase();
                            StringBuilder sb = new StringBuilder(((Long.toString(arrayList.get(0).longValue()).length() + 1) * arrayList.size()) + 16);
                            sb.append("_id");
                            sb.append(" in (");
                            sb.append(arrayList.get(0));
                            int size = arrayList.size();
                            for (int i2 = 1; i2 < size; i2++) {
                                sb.append(aa.f3429b);
                                sb.append(arrayList.get(i2));
                            }
                            sb.append(")");
                            int iDelete = writableDatabase.delete("events", sb.toString(), null);
                            com.xiaomi.onetrack.util.q.a(f3166b, "*** *** deleted events count " + iDelete);
                            com.xiaomi.onetrack.util.q.a(f3166b, "after delete DB record remains=" + d.a().d());
                            return iDelete;
                        } catch (Exception e2) {
                            com.xiaomi.onetrack.util.q.b(f3166b, "deleteEventsSync error, e: ", e2);
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
}
