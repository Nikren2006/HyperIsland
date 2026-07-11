package com.xiaomi.onetrack.util.oaid.helpers;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.miui.circulate.device.api.Column;
import com.xiaomi.onetrack.util.q;

/* JADX INFO: loaded from: classes2.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3597a = "NubiaDeviceIDHelper";

    public String a(Context context) {
        try {
            ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(Uri.parse("content://cn.nubia.identity/identity"));
            Bundle bundleCall = contentProviderClientAcquireContentProviderClient.call("getOAID", null, null);
            contentProviderClientAcquireContentProviderClient.close();
            return bundleCall.getInt(com.xiaomi.onetrack.g.a.f3351d, -1) == 0 ? bundleCall.getString(Column.ID) : "";
        } catch (Exception e2) {
            q.a(f3597a, e2.getMessage());
            return "";
        }
    }
}
