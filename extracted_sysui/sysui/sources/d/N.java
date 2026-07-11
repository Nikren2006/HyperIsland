package d;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import p.AbstractC0724d;

/* JADX INFO: loaded from: classes.dex */
public class N {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Executor f3816e = Executors.newCachedThreadPool();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set f3817a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Set f3818b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f3819c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile L f3820d;

    public class a extends FutureTask {
        public a(Callable callable) {
            super(callable);
        }

        @Override // java.util.concurrent.FutureTask
        public void done() {
            if (isCancelled()) {
                return;
            }
            try {
                N.this.k((L) get());
            } catch (InterruptedException | ExecutionException e2) {
                N.this.k(new L(e2));
            }
        }
    }

    public N(Callable callable) {
        this(callable, false);
    }

    public synchronized N c(H h2) {
        try {
            L l2 = this.f3820d;
            if (l2 != null && l2.a() != null) {
                h2.onResult(l2.a());
            }
            this.f3818b.add(h2);
        } catch (Throwable th) {
            throw th;
        }
        return this;
    }

    public synchronized N d(H h2) {
        try {
            L l2 = this.f3820d;
            if (l2 != null && l2.b() != null) {
                h2.onResult(l2.b());
            }
            this.f3817a.add(h2);
        } catch (Throwable th) {
            throw th;
        }
        return this;
    }

    public final /* synthetic */ void e() {
        L l2 = this.f3820d;
        if (l2 == null) {
            return;
        }
        if (l2.b() != null) {
            h(l2.b());
        } else {
            f(l2.a());
        }
    }

    public final synchronized void f(Throwable th) {
        ArrayList arrayList = new ArrayList(this.f3818b);
        if (arrayList.isEmpty()) {
            AbstractC0724d.d("Lottie encountered an error but no failure listener was added:", th);
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((H) it.next()).onResult(th);
        }
    }

    public final void g() {
        this.f3819c.post(new Runnable() { // from class: d.M
            @Override // java.lang.Runnable
            public final void run() {
                this.f3815a.e();
            }
        });
    }

    public final synchronized void h(Object obj) {
        Iterator it = new ArrayList(this.f3817a).iterator();
        while (it.hasNext()) {
            ((H) it.next()).onResult(obj);
        }
    }

    public synchronized N i(H h2) {
        this.f3818b.remove(h2);
        return this;
    }

    public synchronized N j(H h2) {
        this.f3817a.remove(h2);
        return this;
    }

    public final void k(L l2) {
        if (this.f3820d != null) {
            throw new IllegalStateException("A task may only be set once.");
        }
        this.f3820d = l2;
        g();
    }

    public N(Callable callable, boolean z2) {
        this.f3817a = new LinkedHashSet(1);
        this.f3818b = new LinkedHashSet(1);
        this.f3819c = new Handler(Looper.getMainLooper());
        this.f3820d = null;
        if (!z2) {
            f3816e.execute(new a(callable));
            return;
        }
        try {
            k((L) callable.call());
        } catch (Throwable th) {
            k(new L(th));
        }
    }
}
