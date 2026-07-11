package com.xiaomi.onetrack.c;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.IntentFilter;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.xiaomi.onetrack.util.aa;
import com.xiaomi.onetrack.util.ab;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3117a = "EventManager";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final boolean f3118b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f3119c = 204800;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f3120d = 512000;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int f3121e = 307200;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int f3122f = 300;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int f3123g = 300;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final String f3124h = "priority ASC, _id ASC";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final int f3125i = 7;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static d f3126j = null;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final int f3127l = 5242880;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private static BroadcastReceiver f3128m = new e();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private a f3129k;

    public static class a extends SQLiteOpenHelper {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final String f3130a = "onetrack";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final String f3131b = "events";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final String f3132c = "_id";

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final String f3133d = "appid";

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final String f3134e = "package";

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final String f3135f = "event_name";

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final String f3136g = "priority";

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final String f3137h = "data";

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final String f3138i = "timestamp";

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private static final int f3139j = 1;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private static final String f3140k = "CREATE TABLE events (_id INTEGER PRIMARY KEY AUTOINCREMENT,appid TEXT,package TEXT,event_name TEXT,priority INTEGER,data BLOB,timestamp INTEGER)";

        public a(Context context) {
            super(context, f3130a, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(f3140k);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        }
    }

    private d() {
        Context contextA = com.xiaomi.onetrack.f.a.a();
        this.f3129k = new a(contextA);
        c();
        b(contextA);
    }

    private static void b(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.registerReceiver(f3128m, intentFilter);
    }

    private void e() {
        try {
            this.f3129k.getWritableDatabase().delete("events", null, null);
            com.xiaomi.onetrack.util.q.a(f3117a, "delete table events");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void c() {
        c.a(new h(this), 1000L);
    }

    public long d() {
        try {
            return DatabaseUtils.queryNumEntries(this.f3129k.getReadableDatabase(), "events");
        } catch (Exception e2) {
            com.xiaomi.onetrack.util.q.b(f3117a, "getTotalEventsNumberSync failed with " + e2.getMessage());
            return 0L;
        }
    }

    public static d a() {
        if (f3126j == null) {
            a(com.xiaomi.onetrack.f.a.b());
        }
        return f3126j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(com.xiaomi.onetrack.f.b bVar, boolean z2) {
        synchronized (this.f3129k) {
            try {
                if (!bVar.i()) {
                    com.xiaomi.onetrack.util.q.c(f3117a, "addEventToDatabase event is inValid, event:" + bVar.e());
                    return;
                }
                SQLiteDatabase writableDatabase = this.f3129k.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("appid", bVar.c());
                contentValues.put("package", bVar.d());
                contentValues.put("event_name", bVar.e());
                contentValues.put("priority", Integer.valueOf(bVar.f()));
                contentValues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
                byte[] bArrB = com.xiaomi.onetrack.d.a.b(bVar.g().toString());
                if (!z2 && !"onetrack_bug_report".equals(bVar.e())) {
                    if (bArrB.length > f3119c) {
                        com.xiaomi.onetrack.util.q.b(f3117a, "Too large data, discard ***");
                        return;
                    }
                } else if (bArrB.length > f3120d) {
                    com.xiaomi.onetrack.util.q.b(f3117a, "ad or bug Too large data, discard ***");
                    return;
                }
                contentValues.put("data", bArrB);
                long jInsert = writableDatabase.insert("events", null, contentValues);
                com.xiaomi.onetrack.util.q.a(f3117a, "DB-Thread: EventManager.addEventToDatabase , row=" + jInsert);
                if (jInsert != -1) {
                    if (com.xiaomi.onetrack.util.q.f3627a) {
                        com.xiaomi.onetrack.util.q.a(f3117a, "添加后，DB 中事件个数为 " + d());
                    }
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (com.xiaomi.onetrack.util.a.f3416a.equals(bVar.e())) {
                        ab.a(jCurrentTimeMillis);
                    }
                    com.xiaomi.onetrack.b.n.a(false);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void a(Context context) {
        if (f3126j == null) {
            synchronized (d.class) {
                try {
                    if (f3126j == null) {
                        f3126j = new d();
                    }
                } finally {
                }
            }
        }
    }

    public synchronized void a(com.xiaomi.onetrack.f.b bVar, boolean z2) {
        c.a(new g(this, bVar, z2));
    }

    public boolean a(t tVar) {
        synchronized (this.f3129k) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                if (tVar != null) {
                    try {
                        ArrayList<s> arrayList = tVar.f3214a;
                        if (arrayList != null && arrayList.size() != 0) {
                            SQLiteDatabase writableDatabase = this.f3129k.getWritableDatabase();
                            try {
                                writableDatabase.beginTransaction();
                                for (s sVar : tVar.f3214a) {
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("appid", sVar.b());
                                    contentValues.put("package", sVar.c());
                                    contentValues.put("event_name", sVar.j());
                                    contentValues.put("priority", Integer.valueOf(sVar.g()));
                                    contentValues.put("timestamp", Long.valueOf(sVar.i()));
                                    contentValues.put("data", sVar.f());
                                    com.xiaomi.onetrack.util.q.a(f3117a, "DB-Thread: EventManager.addTransformDataToDB , row=" + writableDatabase.insert("events", null, contentValues));
                                }
                                com.xiaomi.onetrack.b.n.a(false);
                                writableDatabase.setTransactionSuccessful();
                                try {
                                    writableDatabase.endTransaction();
                                } catch (Exception e2) {
                                    com.xiaomi.onetrack.util.q.b(f3117a, "addTransformDataToDB Exception while endTransaction:" + e2);
                                }
                                return true;
                            } catch (Exception e3) {
                                e = e3;
                                sQLiteDatabase = writableDatabase;
                                com.xiaomi.onetrack.util.q.a(f3117a, "DB-Thread: addTransformDataToDB , exception:" + e.getMessage());
                                if (sQLiteDatabase != null) {
                                    try {
                                        sQLiteDatabase.endTransaction();
                                    } catch (Exception e4) {
                                        com.xiaomi.onetrack.util.q.b(f3117a, "addTransformDataToDB Exception while endTransaction:" + e4);
                                    }
                                }
                                return false;
                            } catch (Throwable th) {
                                th = th;
                                sQLiteDatabase = writableDatabase;
                                if (sQLiteDatabase != null) {
                                    try {
                                        sQLiteDatabase.endTransaction();
                                    } catch (Exception e5) {
                                        com.xiaomi.onetrack.util.q.b(f3117a, "addTransformDataToDB Exception while endTransaction:" + e5);
                                    }
                                }
                                throw th;
                            }
                        }
                    } catch (Exception e6) {
                        e = e6;
                    }
                }
                com.xiaomi.onetrack.util.q.a(f3117a, "TransformEvent记录为空，即将返回");
                return false;
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0096: MOVE (r2 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:11:0x0096 */
    /* JADX WARN: Removed duplicated region for block: B:55:0x010c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.xiaomi.onetrack.c.t b() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.onetrack.c.d.b():com.xiaomi.onetrack.c.t");
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x0075: MOVE (r3 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:11:0x0075 */
    /* JADX WARN: Removed duplicated region for block: B:65:0x013a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.xiaomi.onetrack.c.i a(int r23) {
        /*
            Method dump skipped, instruction units count: 318
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.onetrack.c.d.a(int):com.xiaomi.onetrack.c.i");
    }

    public int a(ArrayList<Long> arrayList) {
        synchronized (this.f3129k) {
            if (arrayList != null) {
                try {
                    if (arrayList.size() != 0) {
                        try {
                            SQLiteDatabase writableDatabase = this.f3129k.getWritableDatabase();
                            boolean z2 = true;
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
                            com.xiaomi.onetrack.util.q.a(f3117a, "deleted events count " + iDelete);
                            long jD = a().d();
                            if (jD != 0) {
                                z2 = false;
                            }
                            com.xiaomi.onetrack.b.n.a(z2);
                            com.xiaomi.onetrack.util.q.a(f3117a, "after delete DB record remains=" + jD);
                            return iDelete;
                        } catch (Exception e2) {
                            com.xiaomi.onetrack.util.q.b(f3117a, "e=" + e2);
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
