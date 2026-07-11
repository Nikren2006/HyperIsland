package com.xiaomi.onetrack.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3616a = "p";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3617b = "content://com.miui.analytics.OneTrackProvider/insId";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3618c = "insId";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3619d = "pkg";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3620e = "sign";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static volatile p f3621f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static String f3622g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static String f3623j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f3626k = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Context f3624h = com.xiaomi.onetrack.f.a.a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Context f3625i = com.xiaomi.onetrack.f.a.b();

    private p() {
        f3623j = com.xiaomi.onetrack.f.a.e();
    }

    public static p a() {
        if (f3621f == null) {
            synchronized (p.class) {
                try {
                    if (f3621f == null) {
                        f3621f = new p();
                    }
                } finally {
                }
            }
        }
        return f3621f;
    }

    private String c() {
        String string = null;
        try {
            Uri.Builder builderBuildUpon = Uri.parse(f3617b).buildUpon();
            builderBuildUpon.appendQueryParameter(f3619d, f3623j);
            builderBuildUpon.appendQueryParameter(f3620e, com.xiaomi.onetrack.d.a.a(f3618c + f3623j));
            Cursor cursorQuery = this.f3625i.getContentResolver().query(builderBuildUpon.build(), null, null, null, null);
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    string = cursorQuery.getString(0);
                }
                cursorQuery.close();
            }
        } catch (Exception e2) {
            q.a(f3616a, "getRemoteCacheInstanceId e", e2);
        }
        return string;
    }

    private String d() {
        String strA = ab.a(this.f3624h);
        if (TextUtils.isEmpty(strA)) {
            return ab.m();
        }
        ab.e(strA);
        return strA;
    }

    public String b() {
        String strD;
        if (!TextUtils.isEmpty(f3622g)) {
            return f3622g;
        }
        if (this.f3626k) {
            strD = c();
            String strD2 = d();
            if (TextUtils.isEmpty(strD) && !TextUtils.isEmpty(strD2)) {
                b(strD2);
                strD = strD2;
            } else if (!TextUtils.isEmpty(strD) && TextUtils.isEmpty(strD2)) {
                ab.e(strD);
            }
        } else {
            strD = d();
        }
        if (TextUtils.isEmpty(strD)) {
            String string = UUID.randomUUID().toString();
            f3622g = string;
            if (this.f3626k) {
                b(string);
            }
            ab.e(f3622g);
        } else {
            f3622g = strD;
        }
        return f3622g;
    }

    public void a(Boolean bool) {
        this.f3626k = bool.booleanValue();
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f3622g = str;
        if (this.f3626k) {
            b(str);
        }
        ab.e(f3622g);
    }

    private void b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Uri uri = Uri.parse(f3617b);
            ContentValues contentValues = new ContentValues();
            contentValues.put(f3623j, str);
            this.f3625i.getContentResolver().insert(uri, contentValues);
        } catch (Exception e2) {
            ab.e(str);
            q.a(f3616a, "setRemoteCacheInstanceId e", e2);
        }
    }
}
