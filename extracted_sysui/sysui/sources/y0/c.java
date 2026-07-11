package y0;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import z0.e;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f7104a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f7105b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0171c f7106c = new C0171c();

    public interface b {
        void a(int i2);
    }

    /* JADX INFO: renamed from: y0.c$c, reason: collision with other inner class name */
    public class C0171c extends BroadcastReceiver {
        public C0171c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_SWITCHED".equals(intent.getAction())) {
                e.c("UserSwitchObserver", "user switched");
                c.this.f7105b.a(intent.getIntExtra("android.intent.extra.user_handle", 0));
            }
        }
    }

    public c(Context context, b bVar) {
        this.f7104a = context;
        this.f7105b = bVar;
        f();
    }

    public final /* synthetic */ void d() {
        this.f7104a.registerReceiver(this.f7106c, new IntentFilter("android.intent.action.USER_SWITCHED"));
    }

    public final /* synthetic */ void e() {
        this.f7104a.unregisterReceiver(this.f7106c);
    }

    public final void f() {
        h(new Runnable() { // from class: y0.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f7102a.d();
            }
        });
    }

    public void g() {
        h(new Runnable() { // from class: y0.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f7103a.e();
            }
        });
    }

    public final void h(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e2) {
            e2.printStackTrace();
            e.c("UserSwitchObserver", e2.getMessage());
        }
    }
}
