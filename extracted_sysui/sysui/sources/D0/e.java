package D0;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class e implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f70a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Uri f71b;

    public e(Context context) {
        n.g(context, "context");
        this.f70a = context;
        this.f71b = Uri.parse("content://com.xiaomi.smarthome.control_state");
    }

    @Override // D0.c
    public Bundle c(String method, Bundle bundle) {
        n.g(method, "method");
        return this.f70a.getContentResolver().call(this.f71b, method, "", bundle);
    }
}
