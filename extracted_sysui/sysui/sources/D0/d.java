package D0;

import H0.s;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.util.Log;
import com.miui.circulate.device.api.DeviceInfo;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.D;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.notification.focus.Const;

/* JADX INFO: loaded from: classes2.dex */
public final class d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b f64d = new b(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final H0.d f65e = H0.e.b(a.f69a);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f66a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f67b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set f68c;

    public static final class a extends o implements Function0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f69a = new a();

        public a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final d invoke() {
            return new d(null);
        }
    }

    public static final class b {
        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final d a(Context context, boolean z2, String tag) {
            n.g(context, "context");
            n.g(tag, "tag");
            Log.i("MijiaControl", "get " + z2 + ' ' + context + ' ' + tag + " 1.6.6", new Exception());
            b().d(context, z2, tag);
            return b();
        }

        public final d b() {
            return (d) d.f65e.getValue();
        }

        public b() {
        }
    }

    public /* synthetic */ d(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final c c(Context context, boolean z2) {
        if (z2 && f.f72c.a(context)) {
            Log.i("MijiaControl", n.o("create ServiceCallable ", Boolean.valueOf(z2)));
            return new f(context);
        }
        Log.i("MijiaControl", n.o("create ProviderCallable ", Boolean.valueOf(z2)));
        return new e(context);
    }

    public final void d(Context context, boolean z2, String str) {
        boolean z3;
        synchronized (this) {
            try {
                z3 = true;
                if (this.f67b != null) {
                    if (this.f66a != z2) {
                        f(null);
                    } else {
                        z3 = false;
                    }
                }
                this.f66a = z2;
                this.f68c.add(str);
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z3) {
            c cVarC = c(context, z2);
            synchronized (this) {
                try {
                    if (this.f67b == null) {
                        this.f67b = cVarC;
                    }
                    s sVar = s.f314a;
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    public final int e(String from, DeviceInfo value) {
        n.g(from, "from");
        n.g(value, "value");
        c cVar = this.f67b;
        if (cVar == null) {
            Log.e("MijiaControl", "requestClickScene must call before stop", new Exception());
            return -6;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", value);
            bundle.putString("from", from);
            Bundle bundleC = cVar.c(Const.Param.SCENE, bundle);
            if (bundleC == null) {
                Log.e("MijiaControl", "requestClickScene call " + from + " null " + this.f66a + '/' + hashCode());
                return -5;
            }
            int i2 = bundleC.getInt(com.xiaomi.onetrack.g.a.f3351d);
            Log.e("MijiaControl", "requestClickScene " + from + ' ' + i2 + ' ' + this.f66a + '/' + hashCode());
            return i2;
        } catch (DeadObjectException e2) {
            Log.e("MijiaControl", "fatal", e2);
            return 0;
        } catch (IllegalArgumentException e3) {
            Log.e("MijiaControl", "fatal", e3);
            return 0;
        } catch (IllegalStateException e4) {
            Log.e("MijiaControl", "fatal", e4);
            return 0;
        } catch (Throwable th) {
            Log.e("MijiaControl", "fatal", th);
            return -4;
        }
    }

    public final void f(String str) {
        c cVar;
        String string;
        synchronized (this) {
            try {
                Set set = this.f68c;
                if (set == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
                }
                D.a(set).remove(str);
                if (this.f68c.isEmpty()) {
                    cVar = this.f67b;
                    this.f67b = null;
                } else {
                    cVar = null;
                }
                string = this.f68c.toString();
                s sVar = s.f314a;
            } catch (Throwable th) {
                throw th;
            }
        }
        f fVar = cVar instanceof f ? (f) cVar : null;
        if (fVar != null) {
            fVar.b();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("stop ");
        sb.append(cVar != null);
        sb.append(' ');
        sb.append(string);
        sb.append(".remove(");
        sb.append((Object) str);
        sb.append(") ");
        sb.append(this.f67b);
        Log.e("MijiaControl", sb.toString(), new Exception());
    }

    public d() {
        this.f68c = new LinkedHashSet();
    }
}
