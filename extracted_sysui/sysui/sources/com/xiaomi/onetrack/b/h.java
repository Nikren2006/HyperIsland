package com.xiaomi.onetrack.b;

import android.content.ContentValues;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.onetrack.util.q;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3071a = "ConfigDbManager";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int f3072e = 100;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private g f3073b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ConcurrentHashMap<String, l> f3074c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ConcurrentHashMap<String, Boolean> f3075d;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final h f3076a = new h(null);

        private a() {
        }
    }

    public /* synthetic */ h(i iVar) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(ArrayList<l> arrayList) throws Throwable {
        StringBuilder sb;
        SQLiteDatabase writableDatabase;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                writableDatabase = this.f3073b.getWritableDatabase();
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
        }
        try {
            writableDatabase.beginTransaction();
            for (l lVar : arrayList) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(g.f3064d, lVar.f3083a);
                contentValues.put("timestamp", Long.valueOf(lVar.f3085c));
                JSONObject jSONObject = lVar.f3087e;
                if (jSONObject != null) {
                    contentValues.put(g.f3065e, jSONObject.toString());
                }
                String str = lVar.f3086d;
                if (str != null) {
                    contentValues.put(g.f3066f, str);
                }
                if (DatabaseUtils.queryNumEntries(writableDatabase, g.f3062b, "app_id=?", new String[]{lVar.f3083a}) > 0) {
                    q.a(f3071a, "database updated, row: " + writableDatabase.update(g.f3062b, contentValues, "app_id=?", new String[]{lVar.f3083a}));
                } else {
                    q.a(f3071a, "database inserted, row: " + writableDatabase.insert(g.f3062b, null, contentValues));
                }
                this.f3075d.put(lVar.f3083a, Boolean.TRUE);
            }
            writableDatabase.setTransactionSuccessful();
            try {
                writableDatabase.endTransaction();
            } catch (Exception e3) {
                e = e3;
                sb = new StringBuilder();
                sb.append("Exception while endTransaction:");
                sb.append(e);
                q.b(f3071a, sb.toString());
            }
        } catch (Exception e4) {
            e = e4;
            sQLiteDatabase = writableDatabase;
            q.b(f3071a, "updateToDb error: ", e);
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e5) {
                    e = e5;
                    sb = new StringBuilder();
                    sb.append("Exception while endTransaction:");
                    sb.append(e);
                    q.b(f3071a, sb.toString());
                }
            }
        } catch (Throwable th2) {
            th = th2;
            sQLiteDatabase = writableDatabase;
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e6) {
                    q.b(f3071a, "Exception while endTransaction:" + e6);
                }
            }
            throw th;
        }
    }

    private JSONObject c(String str, String str2) {
        JSONObject jSONObject;
        JSONArray jSONArrayOptJSONArray;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                if (this.f3074c.get(str) == null || (this.f3075d.containsKey(str) && this.f3075d.get(str).booleanValue())) {
                    b(str);
                }
                l lVar = this.f3074c.get(str);
                if (lVar != null && (jSONObject = lVar.f3087e) != null && (jSONArrayOptJSONArray = jSONObject.optJSONArray("events")) != null) {
                    for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i2);
                        if (TextUtils.equals(str2, jSONObject2.optString("event"))) {
                            if (q.f3627a) {
                                q.a(f3071a, "getEventConfig:" + jSONObject2.toString());
                            }
                            return jSONObject2;
                        }
                    }
                }
            } catch (Exception e2) {
                Log.e(f3071a, "getEventConfig error: " + e2.toString());
            }
        }
        return null;
    }

    public String d(String str) {
        l lVarF = f(str);
        return lVarF != null ? lVarF.f3086d : "";
    }

    public int e(String str) {
        JSONObject jSONObject;
        l lVarF = f(str);
        if (lVarF == null || (jSONObject = lVarF.f3087e) == null) {
            return 0;
        }
        return jSONObject.optInt("version");
    }

    public l f(String str) {
        q.a(f3071a, "getAppConfigData start, appId: " + str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            if (this.f3074c.get(str) == null || (this.f3075d.containsKey(str) && this.f3075d.get(str).booleanValue())) {
                b(str);
            }
        } catch (Exception e2) {
            q.b(f3071a, "getConfig error: " + e2.getMessage());
        }
        return this.f3074c.get(str);
    }

    private h() {
        this.f3074c = new ConcurrentHashMap<>();
        this.f3075d = new ConcurrentHashMap<>();
        this.f3073b = new g(com.xiaomi.onetrack.f.a.a());
    }

    public static h a() {
        return a.f3076a;
    }

    public void a(ArrayList<l> arrayList) {
        com.xiaomi.onetrack.c.c.a(new i(this, arrayList));
    }

    public void a(String str) {
        FutureTask futureTask = new FutureTask(new j(this, str), null);
        com.xiaomi.onetrack.c.c.a(futureTask);
        try {
            futureTask.get();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean a(String str, String str2, String str3, boolean z2) {
        try {
            JSONObject jSONObjectC = c(str, str2);
            if (jSONObjectC == null) {
                q.a(f3071a, "config not available, use default value");
                return z2;
            }
            return jSONObjectC.getBoolean(str3);
        } catch (Exception e2) {
            q.b(f3071a, "getBoolean: " + e2.toString());
            return z2;
        }
    }

    public String c(String str) {
        JSONObject jSONObject;
        l lVarF = f(str);
        if (lVarF != null && (jSONObject = lVarF.f3087e) != null) {
            return jSONObject.optString(com.xiaomi.onetrack.b.a.f3027g);
        }
        return "";
    }

    public String a(String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObjectC = c(str, str2);
            if (jSONObjectC == null) {
                q.a(f3071a, "config not available, use default value");
                return str4;
            }
            return jSONObjectC.getString(str3);
        } catch (Exception e2) {
            q.b(f3071a, "getString: " + e2.toString());
            return str4;
        }
    }

    public int a(String str, String str2, String str3, int i2) {
        try {
            JSONObject jSONObjectC = c(str, str2);
            if (jSONObjectC == null) {
                q.a(f3071a, "config not available, use default value");
                return i2;
            }
            return jSONObjectC.getInt(str3);
        } catch (Exception e2) {
            q.b(f3071a, "getInt: " + e2.toString());
            return i2;
        }
    }

    public long a(String str, String str2, String str3, long j2) {
        try {
            JSONObject jSONObjectC = c(str, str2);
            if (jSONObjectC == null) {
                q.a(f3071a, "config not available, use default value");
                return j2;
            }
            return jSONObjectC.getLong(str3);
        } catch (Exception e2) {
            q.b(f3071a, "getLong: " + e2.toString());
            return j2;
        }
    }

    public double a(String str, String str2, String str3, double d2) {
        try {
            JSONObject jSONObjectC = c(str, str2);
            if (jSONObjectC == null) {
                q.a(f3071a, "config not available, use default value");
                return d2;
            }
            return jSONObjectC.getDouble(str3);
        } catch (Exception e2) {
            q.b(f3071a, "getDouble: " + e2.toString());
            return d2;
        }
    }

    public void b(String str) {
        FutureTask futureTask = new FutureTask(new k(this, str));
        com.xiaomi.onetrack.c.c.a(futureTask);
        try {
            l lVar = (l) futureTask.get(5L, TimeUnit.SECONDS);
            if (lVar != null) {
                this.f3074c.put(str, lVar);
                this.f3075d.put(str, Boolean.FALSE);
            }
        } catch (Exception e2) {
            q.b(f3071a, "getConfig error: " + e2.toString());
        }
    }

    public boolean a(String str, String str2) {
        JSONObject jSONObject;
        try {
            l lVarF = f(str);
            if (lVarF == null || (jSONObject = lVarF.f3087e) == null || !jSONObject.has(str2)) {
                return false;
            }
            return lVarF.f3087e.optBoolean(str2);
        } catch (Exception e2) {
            q.b(f3071a, "getAppLevelBoolean" + e2.toString());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int b(JSONObject jSONObject) {
        try {
            int iOptInt = jSONObject.optInt(com.xiaomi.onetrack.b.a.f3025e, 100);
            if (iOptInt < 0 || iOptInt > 100) {
                return 100;
            }
            return iOptInt;
        } catch (Exception e2) {
            q.a(f3071a, "getCommonSample Exception:" + e2.getMessage());
            return 100;
        }
    }

    public long b(String str, String str2) {
        l lVar;
        if (TextUtils.isEmpty(str)) {
            return 100L;
        }
        try {
            if (this.f3074c.get(str) == null) {
                b(str);
            }
            if (this.f3074c.get(str) != null) {
                int iA = a(str, str2, com.xiaomi.onetrack.b.a.f3025e, -1);
                if (iA == -1 && (lVar = this.f3074c.get(str)) != null) {
                    q.a(f3071a, "will return common sample " + lVar.f3084b);
                    return lVar.f3084b;
                }
                q.a(f3071a, "will return event sample " + iA);
                return iA;
            }
        } catch (Exception e2) {
            q.b(f3071a, "getAppEventSample" + e2.toString());
        }
        q.a(f3071a, "will return def sample");
        return 100L;
    }
}
