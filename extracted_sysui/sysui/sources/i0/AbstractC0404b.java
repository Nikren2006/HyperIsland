package i0;

import H0.s;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: renamed from: i0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0404b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Messenger f4539e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f4535a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f4536b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f4537c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final H0.d f4538d = H0.e.b(new c());

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final H0.d f4540f = H0.e.b(new a());

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final H0.d f4541g = H0.e.b(new d());

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ServiceConnection f4542h = new ServiceConnectionC0085b();

    /* JADX INFO: renamed from: i0.b$a */
    public static final class a extends o implements Function0 {
        public a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final Messenger invoke() {
            return new Messenger(AbstractC0404b.this.j());
        }
    }

    /* JADX INFO: renamed from: i0.b$b, reason: collision with other inner class name */
    public static final class ServiceConnectionC0085b implements ServiceConnection {
        public ServiceConnectionC0085b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            k0.b.e("[view_show]:", AbstractC0404b.this.l() + ": onServiceConnected");
            AbstractC0404b.this.y(new Messenger(iBinder));
            AbstractC0404b.this.u();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            k0.b.e("[view_hide]:", AbstractC0404b.this.l() + ": onServiceDisconnected");
            AbstractC0404b.this.r();
        }
    }

    /* JADX INFO: renamed from: i0.b$c */
    public static final class c extends o implements Function0 {

        /* JADX INFO: renamed from: i0.b$c$a */
        public static final class a extends Handler {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ AbstractC0404b f4546a;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(AbstractC0404b abstractC0404b, Looper looper) {
                super(looper);
                this.f4546a = abstractC0404b;
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                n.g(msg, "msg");
                super.handleMessage(msg);
                if (msg.what == 11) {
                    this.f4546a.n(msg);
                } else {
                    this.f4546a.q(msg);
                }
            }
        }

        public c() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final a invoke() {
            return new a(AbstractC0404b.this, Looper.getMainLooper());
        }
    }

    /* JADX INFO: renamed from: i0.b$d */
    public static final class d extends o implements Function0 {
        public d() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return AbstractC0404b.this.m();
        }
    }

    public static final void A(AbstractC0404b this$0) {
        n.g(this$0, "this$0");
        this$0.x();
    }

    public final void B() {
        k0.b.e("[view_hide]:", l() + ": unBindService");
        try {
            Context contextF = f();
            if (contextF != null) {
                contextF.unbindService(this.f4542h);
            }
        } catch (Exception unused) {
            k0.b.e(l(), "unbindService exception");
        }
        this.f4539e = null;
        p();
    }

    public final boolean c(InterfaceC0405c cardServerStateListener) {
        n.g(cardServerStateListener, "cardServerStateListener");
        return this.f4535a.add(cardServerStateListener);
    }

    public final boolean d(InterfaceC0406d stateListener) {
        n.g(stateListener, "stateListener");
        return this.f4536b.add(stateListener);
    }

    public final s e() throws PackageManager.NameNotFoundException {
        Context contextF = f();
        if (contextF == null) {
            return null;
        }
        ApplicationInfo applicationInfo = contextF.getPackageManager().getApplicationInfo(contextF.getPackageName(), 128);
        n.f(applicationInfo, "packageManager.getApplic…r.GET_META_DATA\n        )");
        Bundle bundle = applicationInfo.metaData;
        String string = bundle != null ? bundle.getString("miLink.cardFrame.service.package") : null;
        Intent intent = new Intent();
        intent.setAction("com.miLink.card.frame.show");
        intent.setPackage(string);
        boolean zBindService = contextF.bindService(intent, this.f4542h, 75497473);
        k0.b.e("[view_show]:", l() + ": bindService flags=75497473 result=" + zBindService);
        if (!zBindService) {
            t();
        }
        return s.f314a;
    }

    public abstract Context f();

    public final List g() {
        return this.f4537c;
    }

    public final List h() {
        return this.f4536b;
    }

    public final Messenger i() {
        return (Messenger) this.f4540f.getValue();
    }

    public final Handler j() {
        return (Handler) this.f4538d.getValue();
    }

    public final Messenger k() {
        return this.f4539e;
    }

    public final String l() {
        return (String) this.f4541g.getValue();
    }

    public abstract String m();

    public final void n(Message message) {
        String toastStr = message.getData().getString("keyCardToastString", "");
        int i2 = message.getData().getInt("keyCardToastDuration");
        k0.b.e("[toast]:", l() + ": receive message CARD_TOAST toastStr = " + toastStr + "  toastDuration=" + i2);
        n.f(toastStr, "toastStr");
        s(toastStr, i2 % 2);
    }

    public final boolean o() {
        return this.f4539e == null;
    }

    public abstract void p();

    public abstract void q(Message message);

    public void r() {
        Iterator it = this.f4535a.iterator();
        while (it.hasNext()) {
            ((InterfaceC0405c) it.next()).onServiceDisconnectedCallback();
        }
        this.f4539e = null;
        p();
    }

    public abstract void s(String str, int i2);

    public void t() {
        Iterator it = this.f4535a.iterator();
        while (it.hasNext()) {
            ((InterfaceC0405c) it.next()).onStartServiceFailCallback();
        }
        p();
    }

    public void u() {
        Iterator it = this.f4535a.iterator();
        while (it.hasNext()) {
            ((InterfaceC0405c) it.next()).onStartServiceSuccessCallback();
        }
        x();
    }

    public final boolean v(InterfaceC0405c cardServerStateListener) {
        n.g(cardServerStateListener, "cardServerStateListener");
        return this.f4535a.remove(cardServerStateListener);
    }

    public final boolean w(InterfaceC0406d stateListener) {
        n.g(stateListener, "stateListener");
        return this.f4536b.remove(stateListener);
    }

    public abstract s x();

    public final void y(Messenger messenger) {
        this.f4539e = messenger;
    }

    public final void z() {
        k0.b.e("[view_show]:", l() + ": showRemoteView");
        if (o()) {
            e();
        } else {
            j().post(new Runnable() { // from class: i0.a
                @Override // java.lang.Runnable
                public final void run() {
                    AbstractC0404b.A(this.f4534a);
                }
            });
        }
    }
}
