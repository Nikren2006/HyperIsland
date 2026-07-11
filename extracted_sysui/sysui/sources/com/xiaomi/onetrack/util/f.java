package com.xiaomi.onetrack.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.onetrack.api.ah;

/* JADX INFO: loaded from: classes2.dex */
class f extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f3509a;

    public f(d dVar) {
        this.f3509a = dVar;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            String stringExtra = intent.getStringExtra(ah.f2798D);
            String stringExtra2 = intent.getStringExtra("packagename");
            String stringExtra3 = intent.getStringExtra("projectId");
            String stringExtra4 = intent.getStringExtra("user");
            boolean booleanExtra = intent.getBooleanExtra("logon", false);
            boolean booleanExtra2 = intent.getBooleanExtra("quickuploadon", false);
            String strE = com.xiaomi.onetrack.f.a.e();
            if (!TextUtils.isEmpty(stringExtra2) && !"".equals(stringExtra2) && strE.equals(stringExtra2)) {
                q.f3627a = booleanExtra;
                q.f3628b = booleanExtra2;
                if (booleanExtra2 && this.f3509a.a(stringExtra)) {
                    this.f3509a.a(stringExtra, stringExtra3, stringExtra4);
                }
            }
        } catch (Exception e2) {
            q.b(d.f3496a, e2.getMessage());
        }
    }
}
