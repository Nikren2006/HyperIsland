package com.xiaomi.onetrack.f;

import android.content.Context;
import com.xiaomi.onetrack.util.i;

/* JADX INFO: loaded from: classes2.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3344a = "OneTrackApp";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static g f3345b;

    private g(Context context) {
        i.a(new h(this, context.getApplicationContext()));
    }

    public static void a(Context context) {
        if (f3345b == null) {
            f3345b = new g(context);
        }
    }
}
