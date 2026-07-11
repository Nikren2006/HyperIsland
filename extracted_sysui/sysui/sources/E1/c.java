package E1;

import E1.a;
import E1.f;
import E1.g;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public class c implements AutoCloseable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public E1.g f117a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final E1.a f118b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f119c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public E1.f f120d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Context f121e;

    public class a extends g.a {
        public a() {
        }

        @Override // E1.g
        public void R0(String str) {
            Log.d("se.dirac.acs-api", "Set user callback received");
            c.d(c.this);
        }

        @Override // E1.g
        public void l0(E1.h hVar, i iVar) {
            Log.d("se.dirac.acs-api", "Settings changed callback received");
            c.e(c.this);
        }

        @Override // E1.g
        public void r(int i2) {
            Log.d("se.dirac.acs-api", "Routing change callback received");
            c.f(c.this);
        }

        @Override // E1.g
        public void w() {
            Log.d("se.dirac.acs-api", "Sync done callback received");
            c.c(c.this);
        }

        @Override // E1.g
        public void x(long j2, int[] iArr) {
            c.a(c.this);
        }
    }

    public class b implements a.InterfaceC0005a {
        public b() {
        }
    }

    /* JADX INFO: renamed from: E1.c$c, reason: collision with other inner class name */
    public static abstract class AbstractC0006c {
        private Context context;
        private final ServiceConnection serviceConnection = new a();

        /* JADX INFO: renamed from: E1.c$c$a */
        public class a implements ServiceConnection {
            public a() {
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                AbstractC0006c abstractC0006c = AbstractC0006c.this;
                abstractC0006c.onServiceConnected(new c(abstractC0006c.context, f.a.Z0(iBinder), null));
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                AbstractC0006c.this.onServiceDisconnected();
            }
        }

        public abstract void onServiceConnected(c cVar);

        public abstract void onServiceDisconnected();
    }

    public interface d {
    }

    public interface e {
    }

    public interface f {
    }

    public interface g {
    }

    public interface h {
    }

    public /* synthetic */ c(Context context, E1.f fVar, E1.b bVar) {
        this(context, fVar);
    }

    public static /* synthetic */ d a(c cVar) {
        cVar.getClass();
        return null;
    }

    public static /* synthetic */ h c(c cVar) {
        cVar.getClass();
        return null;
    }

    public static /* synthetic */ f d(c cVar) {
        cVar.getClass();
        return null;
    }

    public static /* synthetic */ g e(c cVar) {
        cVar.getClass();
        return null;
    }

    public static /* synthetic */ e f(c cVar) {
        cVar.getClass();
        return null;
    }

    public static boolean l(Context context, AbstractC0006c abstractC0006c) {
        Intent intentT = t();
        context.startService(intentT);
        abstractC0006c.context = context;
        return context.bindService(intentT, abstractC0006c.serviceConnection, 0);
    }

    public static Intent t() {
        return new Intent().setClassName("se.dirac.acs", "se.dirac.acs.AudioControlService");
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        try {
            this.f120d.x0(this.f117a);
        } catch (RemoteException e2) {
            throw new RuntimeException("Exception thrown in unregisterCallback", e2);
        }
    }

    public E1.d n(long j2) {
        try {
            return this.f120d.u(j2, this.f119c);
        } catch (RemoteException e2) {
            throw new RuntimeException("Exception thrown in getDevice call", e2);
        }
    }

    public i r(E1.h hVar) {
        try {
            return this.f120d.I(hVar);
        } catch (RemoteException e2) {
            throw new RuntimeException("Exception thrown in getOutput", e2);
        }
    }

    public List u(E1.h hVar) {
        return w(this.f119c, hVar);
    }

    public final List w(String str, E1.h hVar) {
        try {
            return this.f120d.p0(str, hVar);
        } catch (RemoteException e2) {
            throw new RuntimeException("Exception thrown in listDevices", e2);
        }
    }

    public void x(E1.h hVar) {
        try {
            this.f120d.Q0(hVar);
        } catch (RemoteException e2) {
            throw new RuntimeException("Exception thrown in setDisabled", e2);
        }
    }

    public boolean z(i iVar) {
        if (iVar != null) {
            try {
                if (this.f120d.Y(iVar)) {
                    return true;
                }
            } catch (RemoteException e2) {
                throw new RuntimeException("Exception thrown in setOutput", e2);
            }
        }
        return false;
    }

    public c(Context context, E1.f fVar) {
        this.f117a = new a();
        this.f118b = new E1.a(new b());
        this.f120d = fVar;
        this.f121e = context;
        this.f119c = Locale.getDefault().getLanguage();
        try {
            fVar.S(this.f117a);
        } catch (RemoteException e2) {
            throw new RuntimeException("Exception thrown in registerCallback", e2);
        }
    }
}
