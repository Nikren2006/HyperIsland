package h1;

import H0.s;
import L0.g;
import android.os.Handler;
import android.os.Looper;
import c1.f;
import g1.AbstractC0388p0;
import g1.InterfaceC0377k;
import g1.L;
import g1.Q;
import java.util.concurrent.CancellationException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes2.dex */
public final class c extends d implements L {
    private volatile c _immediate;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Handler f4486a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4487b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f4488c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f4489d;

    public static final class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ InterfaceC0377k f4490a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ c f4491b;

        public a(InterfaceC0377k interfaceC0377k, c cVar) {
            this.f4490a = interfaceC0377k;
            this.f4491b = cVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.f4490a.p(this.f4491b, s.f314a);
        }
    }

    public static final class b extends o implements Function1 {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Runnable f4493b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Runnable runnable) {
            super(1);
            this.f4493b = runnable;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return s.f314a;
        }

        public final void invoke(Throwable th) {
            c.this.f4486a.removeCallbacks(this.f4493b);
        }
    }

    public c(Handler handler, String str, boolean z2) {
        super(null);
        this.f4486a = handler;
        this.f4487b = str;
        this.f4488c = z2;
        this._immediate = z2 ? this : null;
        c cVar = this._immediate;
        if (cVar == null) {
            cVar = new c(handler, str, true);
            this._immediate = cVar;
        }
        this.f4489d = cVar;
    }

    public final void D(g gVar, Runnable runnable) {
        AbstractC0388p0.c(gVar, new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed"));
        Q.b().dispatch(gVar, runnable);
    }

    @Override // g1.w0
    /* JADX INFO: renamed from: E, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public c z() {
        return this.f4489d;
    }

    @Override // g1.B
    public void dispatch(g gVar, Runnable runnable) {
        if (this.f4486a.post(runnable)) {
            return;
        }
        D(gVar, runnable);
    }

    public boolean equals(Object obj) {
        return (obj instanceof c) && ((c) obj).f4486a == this.f4486a;
    }

    public int hashCode() {
        return System.identityHashCode(this.f4486a);
    }

    @Override // g1.B
    public boolean isDispatchNeeded(g gVar) {
        return (this.f4488c && n.c(Looper.myLooper(), this.f4486a.getLooper())) ? false : true;
    }

    @Override // g1.L
    public void t(long j2, InterfaceC0377k interfaceC0377k) {
        a aVar = new a(interfaceC0377k, this);
        if (this.f4486a.postDelayed(aVar, f.g(j2, 4611686018427387903L))) {
            interfaceC0377k.g(new b(aVar));
        } else {
            D(interfaceC0377k.getContext(), aVar);
        }
    }

    @Override // g1.B
    public String toString() {
        String strA = A();
        if (strA != null) {
            return strA;
        }
        String string = this.f4487b;
        if (string == null) {
            string = this.f4486a.toString();
        }
        if (!this.f4488c) {
            return string;
        }
        return string + ".immediate";
    }

    public /* synthetic */ c(Handler handler, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(handler, (i2 & 2) != 0 ? null : str);
    }

    public c(Handler handler, String str) {
        this(handler, str, false);
    }
}
