package x0;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.TimeUnit;
import m0.E;

/* JADX INFO: loaded from: classes2.dex */
public class x {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final long f7091c = TimeUnit.MINUTES.toMillis(15);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f7092a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Handler f7093b = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: x0.w
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return this.f7090a.c(message);
        }
    });

    public interface a {
        void p();
    }

    public x(a aVar) {
        this.f7092a = aVar;
    }

    public final boolean b() {
        return !E.g().c();
    }

    public final /* synthetic */ boolean c(Message message) {
        if (message.what == 1000) {
            z0.e.c("CoreServiceLifecycleManager", "safeUnBindService");
            if (!b()) {
                z0.e.c("CoreServiceLifecycleManager", "can not unbindService;");
                return true;
            }
            a aVar = this.f7092a;
            if (aVar != null) {
                aVar.p();
            }
        }
        return true;
    }

    public void d() {
        this.f7093b.removeCallbacksAndMessages(null);
    }

    public synchronized void e() {
        this.f7093b.removeCallbacksAndMessages(null);
        this.f7093b.sendEmptyMessageDelayed(1000, f7091c);
    }
}
