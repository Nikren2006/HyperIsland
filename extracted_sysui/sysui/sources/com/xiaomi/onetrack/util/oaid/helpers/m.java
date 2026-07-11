package com.xiaomi.onetrack.util.oaid.helpers;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.xiaomi.onetrack.api.ah;
import com.xiaomi.onetrack.util.q;

/* JADX INFO: loaded from: classes2.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3611a = "VivoDeviceIDHelper";

    public String a(Context context) {
        String string;
        string = "";
        try {
            Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID"), null, null, null, null);
            if (cursorQuery != null) {
                string = cursorQuery.moveToNext() ? cursorQuery.getString(cursorQuery.getColumnIndex(ah.f2836p)) : "";
                cursorQuery.close();
            }
        } catch (Exception e2) {
            q.a(f3611a, e2.getMessage());
        }
        return string;
    }
}
