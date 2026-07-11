package v0;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.miui.miplay.audio.data.AppMetaData;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import w0.AbstractC0765a;
import w0.AbstractC0767c;
import w0.C0768d;
import w0.ExecutorC0766b;
import y0.c;

/* JADX INFO: renamed from: v0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0755c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0768d f6926a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final PackageManager f6927b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0169c f6928c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f6929d = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0760h f6930e = new C0760h();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List f6931f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Timer f6932g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final HandlerThread f6933h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Handler f6934i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Context f6935j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final y0.c f6936k;

    /* JADX INFO: renamed from: v0.c$b */
    public interface b {
        void a(boolean z2);

        void b(C0759g c0759g);
    }

    /* JADX INFO: renamed from: v0.c$c, reason: collision with other inner class name */
    public final class C0169c implements MediaSessionManager.OnActiveSessionsChangedListener {
        public C0169c() {
        }

        @Override // android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
        public void onActiveSessionsChanged(List list) {
            List listK = C0755c.this.k(list);
            C0755c.this.j(listK);
            C0755c.this.p(listK);
            C0755c.this.h((listK == null || listK.isEmpty()) ? false : true);
        }
    }

    /* JADX INFO: renamed from: v0.c$d */
    public static final class d extends TimerTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f6938a;

        public d(C0755c c0755c) {
            this.f6938a = new WeakReference(c0755c);
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            C0755c c0755c = (C0755c) this.f6938a.get();
            if (c0755c == null) {
                return;
            }
            try {
                Iterator<E> it = c0755c.f6930e.iterator();
                while (it.hasNext()) {
                    ((C0759g) it.next()).n();
                }
            } catch (Exception e2) {
                z0.e.b("ActiveAudioSessionManager_export-api", "refresh position", e2);
            }
        }
    }

    public C0755c(Context context, List list) {
        ArrayList arrayList = new ArrayList();
        this.f6931f = arrayList;
        HandlerThread handlerThread = new HandlerThread("ActiveAudioSessionManager_export-api");
        this.f6933h = handlerThread;
        this.f6935j = context;
        this.f6927b = context.getPackageManager();
        C0768d c0768d = new C0768d(context);
        this.f6926a = c0768d;
        C0169c c0169c = new C0169c();
        this.f6928c = c0169c;
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.f6934i = handler;
        c0768d.a(null, AbstractC0765a.a(), new ExecutorC0766b(handler), c0169c);
        arrayList.addAll(list);
        handler.post(new Runnable() { // from class: v0.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f6924a.u();
            }
        });
        this.f6936k = new y0.c(context, new c.b() { // from class: v0.b
            @Override // y0.c.b
            public final void a(int i2) {
                this.f6925a.q(i2);
            }
        });
    }

    public final void g(boolean z2) {
        if (z2 || n() == null) {
            return;
        }
        i();
    }

    public final void h(boolean z2) {
        z0.e.c("ActiveAudioSessionManager_export-api", "dispatchSessionsExistsChangeCallbackLocked :" + z2);
        synchronized (this.f6929d) {
            try {
                for (int size = this.f6931f.size() - 1; size >= 0; size--) {
                    ((b) this.f6931f.get(size)).a(z2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void i() {
        z0.e.c("ActiveAudioSessionManager_export-api", "dispatchTopActiveSessionChangeCallbackLocked, size:" + this.f6931f.size());
        for (int size = this.f6931f.size() + (-1); size >= 0; size--) {
            ((b) this.f6931f.get(size)).b(this.f6930e.d());
        }
    }

    public final void j(List list) {
        if (list == null) {
            return;
        }
        z0.e.c("ActiveAudioSessionManager_export-api", "controllerSize:" + list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            z0.e.c("ActiveAudioSessionManager_export-api", "packageName:" + ((MediaController) it.next()).getPackageName());
        }
    }

    public final List k(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MediaController mediaController = (MediaController) it.next();
            String packageName = mediaController.getPackageName();
            if (packageName != null && AbstractC0761i.f6956a.contains(packageName)) {
                z0.e.c("ActiveAudioSessionManager_export-api", "match the target blocked mediaSession: " + packageName);
                arrayList.remove(mediaController);
            }
        }
        return arrayList;
    }

    public Context l() {
        return this.f6935j;
    }

    public final List m() {
        ArrayList arrayList = new ArrayList();
        Iterator<E> it = this.f6930e.iterator();
        while (it.hasNext()) {
            arrayList.add(((C0759g) it.next()).o());
        }
        return arrayList;
    }

    public C0759g n() {
        C0759g c0759gD;
        synchronized (this.f6929d) {
            c0759gD = this.f6930e.d();
        }
        return c0759gD;
    }

    public final int o(String str) {
        try {
            return this.f6927b.getPackageUid(str, 0);
        } catch (PackageManager.NameNotFoundException e2) {
            z0.e.b("ActiveAudioSessionManager_export-api", "getUid, package: " + str, e2);
            return -1;
        }
    }

    public final void p(List list) {
        String strP;
        boolean z2;
        boolean z3;
        C0759g c0759gD;
        synchronized (this.f6929d) {
            try {
                C0759g c0759gD2 = this.f6930e.d();
                strP = c0759gD2 == null ? null : c0759gD2.p();
                ArrayList arrayList = new ArrayList();
                ArrayList<MediaController> arrayList2 = new ArrayList();
                AbstractC0767c.b(list, m(), arrayList, arrayList2);
                z2 = true;
                if (arrayList.isEmpty()) {
                    z3 = false;
                } else {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        C0759g c0759gC = this.f6930e.c((MediaController) it.next());
                        if (c0759gC != null) {
                            c0759gC.x();
                            this.f6930e.remove(c0759gC);
                        }
                    }
                    z3 = true;
                }
                if (arrayList2.isEmpty()) {
                    z2 = z3;
                } else {
                    for (MediaController mediaController : arrayList2) {
                        String packageName = mediaController.getPackageName();
                        this.f6930e.add(new C0759g(this, mediaController, new AppMetaData(packageName, o(packageName)), this.f6934i));
                    }
                }
            } finally {
            }
        }
        synchronized (this.f6929d) {
            c0759gD = this.f6930e.d();
        }
        if (z2) {
            boolean zEquals = TextUtils.equals(strP, c0759gD != null ? c0759gD.p() : null);
            synchronized (this.f6929d) {
                if (!zEquals) {
                    try {
                        i();
                    } finally {
                    }
                }
            }
        }
        if (this.f6930e.size() <= 0 || c0759gD == null || c0759gD.q() != 3) {
            x();
        } else {
            w();
        }
    }

    public final /* synthetic */ void q(int i2) {
        this.f6926a.c(this.f6928c);
        p(null);
        h(false);
        this.f6926a.a(null, AbstractC0765a.a(), new ExecutorC0766b(this.f6934i), this.f6928c);
        u();
    }

    public void r(C0759g c0759g, MediaMetadata mediaMetadata) {
        synchronized (this.f6929d) {
            try {
                if (this.f6930e.d() != c0759g) {
                    i();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void s(C0759g c0759g, PlaybackState playbackState) {
        if (playbackState != null) {
            synchronized (this.f6929d) {
                try {
                    boolean zE = this.f6930e.e(c0759g, playbackState.getState());
                    if (zE) {
                        i();
                    }
                    if (c0759g == this.f6930e.d()) {
                        if (playbackState.getState() == 3) {
                            g(zE);
                            w();
                        } else {
                            x();
                        }
                    }
                } finally {
                }
            }
        }
    }

    public C0759g t() {
        boolean zIsEmpty;
        C0759g c0759gD;
        synchronized (this.f6929d) {
            zIsEmpty = this.f6930e.isEmpty();
        }
        if (zIsEmpty) {
            u();
        }
        synchronized (this.f6929d) {
            c0759gD = this.f6930e.d();
        }
        return c0759gD;
    }

    public void u() {
        p(k(this.f6926a.b(null, AbstractC0765a.a())));
    }

    public void v() {
        this.f6926a.c(this.f6928c);
        synchronized (this.f6929d) {
            this.f6931f.clear();
        }
        x();
        this.f6933h.quit();
        this.f6936k.g();
    }

    public final void w() {
        z0.e.c("ActiveAudioSessionManager_export-api", "startPositionCallbackTask");
        synchronized (this.f6929d) {
            try {
                if (this.f6932g == null) {
                    this.f6932g = new Timer();
                    this.f6932g.scheduleAtFixedRate(new d(this), 0L, 1000L);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void x() {
        z0.e.c("ActiveAudioSessionManager_export-api", "stopPositionCallbackTask");
        synchronized (this.f6929d) {
            try {
                Timer timer = this.f6932g;
                if (timer != null) {
                    timer.cancel();
                    this.f6932g = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
