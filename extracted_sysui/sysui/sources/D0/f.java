package D0;

import D0.b;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes2.dex */
public final class f implements c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a f72c = new a(null);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Intent f73d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f74a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final D0.a f75b;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean a(Context context) {
            n.g(context, "context");
            ResolveInfo resolveInfoResolveService = context.getPackageManager().resolveService(f.f73d, 0);
            return (resolveInfoResolveService == null ? null : resolveInfoResolveService.serviceInfo) != null;
        }

        public a() {
        }
    }

    static {
        Intent intent = new Intent("com.xiaomi.smarthome.control_state.alive").setPackage("com.xiaomi.smarthome");
        n.f(intent, "Intent(\"com.xiaomi.smart…e(\"com.xiaomi.smarthome\")");
        f73d = intent;
    }

    public f(Context context) {
        n.g(context, "context");
        this.f74a = context;
        this.f75b = new D0.a(context, f73d);
    }

    public final void b() {
        this.f75b.b();
    }

    @Override // D0.c
    public Bundle c(String method, Bundle bundle) {
        n.g(method, "method");
        return b.a.Z0(this.f75b.a()).c(method, bundle);
    }
}
