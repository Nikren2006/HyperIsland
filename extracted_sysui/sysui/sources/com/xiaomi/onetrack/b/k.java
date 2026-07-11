package com.xiaomi.onetrack.b;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes2.dex */
class k implements Callable<l> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f3081a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ h f3082b;

    public k(h hVar, String str) {
        this.f3082b = hVar;
        this.f3081a = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0099 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.xiaomi.onetrack.b.l call() throws java.lang.Throwable {
        /*
            r12 = this;
            java.lang.String r0 = "getConfig  cursor.close"
            java.lang.String r1 = "ConfigDbManager"
            r2 = 0
            java.lang.String r6 = "app_id=?"
            com.xiaomi.onetrack.b.h r3 = r12.f3082b     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L8a
            com.xiaomi.onetrack.b.g r3 = com.xiaomi.onetrack.b.h.a(r3)     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L8a
            android.database.sqlite.SQLiteDatabase r3 = r3.getWritableDatabase()     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L8a
            java.lang.String r4 = "events_cloud"
            java.lang.String r12 = r12.f3081a     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L8a
            java.lang.String[] r7 = new java.lang.String[]{r12}     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L8a
            r9 = 0
            r10 = 0
            r5 = 0
            r8 = 0
            android.database.Cursor r12 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L85 java.lang.Exception -> L8a
            java.lang.String r3 = "app_id"
            int r3 = r12.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r4 = "cloud_data"
            int r4 = r12.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r5 = "data_hash"
            int r5 = r12.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r6 = "timestamp"
            int r6 = r12.getColumnIndex(r6)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            boolean r7 = r12.moveToNext()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            if (r7 == 0) goto L7d
            com.xiaomi.onetrack.b.l r7 = new com.xiaomi.onetrack.b.l     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r7.<init>()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r3 = r12.getString(r3)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r7.f3083a = r3     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r3 = r12.getString(r4)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            if (r4 != 0) goto L60
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r4.<init>(r3)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r7.f3087e = r4     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            goto L60
        L5c:
            r2 = move-exception
            goto L97
        L5e:
            r3 = move-exception
            goto L8c
        L60:
            org.json.JSONObject r3 = r7.f3087e     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            int r3 = com.xiaomi.onetrack.b.h.a(r3)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            long r3 = (long) r3     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r7.f3084b = r3     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r3 = r12.getString(r5)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r7.f3086d = r3     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            long r3 = r12.getLong(r6)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r7.f3085c = r3     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r12.close()     // Catch: java.lang.Exception -> L79
            goto L7c
        L79:
            com.xiaomi.onetrack.util.q.a(r1, r0)
        L7c:
            return r7
        L7d:
            r12.close()     // Catch: java.lang.Exception -> L81
            goto L96
        L81:
            com.xiaomi.onetrack.util.q.a(r1, r0)
            goto L96
        L85:
            r12 = move-exception
            r11 = r2
            r2 = r12
            r12 = r11
            goto L97
        L8a:
            r3 = move-exception
            r12 = r2
        L8c:
            java.lang.String r3 = r3.getMessage()     // Catch: java.lang.Throwable -> L5c
            com.xiaomi.onetrack.util.q.a(r1, r3)     // Catch: java.lang.Throwable -> L5c
            if (r12 == 0) goto L96
            goto L7d
        L96:
            return r2
        L97:
            if (r12 == 0) goto La0
            r12.close()     // Catch: java.lang.Exception -> L9d
            goto La0
        L9d:
            com.xiaomi.onetrack.util.q.a(r1, r0)
        La0:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.onetrack.b.k.call():com.xiaomi.onetrack.b.l");
    }
}
