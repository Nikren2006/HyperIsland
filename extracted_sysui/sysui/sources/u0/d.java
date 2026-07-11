package u0;

import android.content.Context;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import l0.C0438a;
import m0.C0466a;
import m0.E;
import m0.InterfaceC0463A;
import m0.z;
import v0.C0755c;
import v0.C0759g;
import z0.e;

/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0755c f6850b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final z f6852d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f6849a = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final InterfaceC0463A f6851c = E.g();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f6853e = new Object();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f6854f = 0;

    public static class a implements C0755c.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f6855a;

        public a(d dVar) {
            this.f6855a = new WeakReference(dVar);
        }

        @Override // v0.C0755c.b
        public void a(boolean z2) {
            d dVar = (d) this.f6855a.get();
            if (dVar != null) {
                dVar.k(z2);
            }
        }

        @Override // v0.C0755c.b
        public void b(C0759g c0759g) {
            d dVar = (d) this.f6855a.get();
            if (dVar != null) {
                dVar.m(c0759g != null ? new C0466a(c0759g.r()) : null);
            }
        }
    }

    public d(Context context, z zVar) {
        this.f6852d = zVar;
        this.f6850b = new C0755c(context, Collections.singletonList(new a(this)));
    }

    public static /* synthetic */ boolean g(InterfaceC0747a interfaceC0747a, InterfaceC0747a interfaceC0747a2) {
        return interfaceC0747a == interfaceC0747a2;
    }

    public final List d(C0466a c0466a, C0466a c0466a2) {
        if (c0466a == null && c0466a2 == null) {
            return Collections.emptyList();
        }
        if (c0466a == null) {
            return Collections.singletonList(c0466a2);
        }
        if (c0466a2 == null) {
            return Collections.singletonList(c0466a);
        }
        ArrayList arrayList = new ArrayList();
        synchronized (this.f6853e) {
            try {
                if (this.f6854f == 0) {
                    arrayList.add(c0466a);
                    arrayList.add(c0466a2);
                } else {
                    arrayList.add(c0466a2);
                    arrayList.add(c0466a);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return arrayList;
    }

    public final boolean e(C0466a c0466a, C0466a c0466a2) {
        return (c0466a == null || c0466a2 == null || (!TextUtils.equals(c0466a.a().getPackageName(), c0466a2.a().getPackageName()) && c0466a.b().j() == 3)) ? false : true;
    }

    public final boolean f(C0466a c0466a, C0466a c0466a2) {
        return (c0466a == null || c0466a2 == null || c0466a2.b().j() == 3) ? false : true;
    }

    public final List h(List list) {
        if (list == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new C0466a((C0438a) it.next()));
        }
        return arrayList;
    }

    public final void i(boolean z2) {
        Iterator it = this.f6849a.iterator();
        while (it.hasNext()) {
            ((InterfaceC0747a) it.next()).a(z2);
        }
    }

    public final void j(List list) {
        Iterator it = this.f6849a.iterator();
        while (it.hasNext()) {
            ((InterfaceC0747a) it.next()).onActiveAudioSessionChange(list);
        }
    }

    public final void k(boolean z2) {
        this.f6851c.d(z2);
        i(z2);
    }

    public final void l(C0466a c0466a, C0466a c0466a2) {
        j(d(c0466a, c0466a2));
    }

    public final void m(C0466a c0466a) {
        z zVar = this.f6852d;
        if (zVar == null) {
            e.c("MediaManager", "onTopAudioSessionChanged, audioManager excepted null");
            return;
        }
        List listH = h(zVar.n());
        C0466a c0466a2 = listH.isEmpty() ? null : (C0466a) listH.get(0);
        if (e(c0466a, c0466a2)) {
            return;
        }
        synchronized (this.f6853e) {
            this.f6854f = 0;
        }
        l(c0466a, c0466a2);
    }

    public void n(C0466a c0466a) {
        e.c("MediaManager", "onTopVideoSessionChanged");
        this.f6851c.a(c0466a != null);
        List listO = o();
        C0466a c0466a2 = listO.isEmpty() ? null : (C0466a) listO.get(0);
        if (f(c0466a2, c0466a)) {
            return;
        }
        synchronized (this.f6853e) {
            this.f6854f = 1;
        }
        l(c0466a2, c0466a);
    }

    public final List o() {
        C0759g c0759gT = this.f6850b.t();
        return c0759gT != null ? Collections.singletonList(new C0466a(c0759gT.r())) : Collections.emptyList();
    }

    public List p(List list) {
        List listO = o();
        List listH = h(list);
        synchronized (this.f6853e) {
            try {
                if (this.f6854f == 0) {
                    return (List) Stream.concat(listO.stream(), listH.stream()).collect(Collectors.toList());
                }
                return (List) Stream.concat(listH.stream(), listO.stream()).collect(Collectors.toList());
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void q(InterfaceC0747a interfaceC0747a) {
        this.f6849a.add(interfaceC0747a);
    }

    public void r() {
        this.f6850b.v();
        this.f6849a.clear();
    }

    public void s(final InterfaceC0747a interfaceC0747a) {
        this.f6849a.removeIf(new Predicate() { // from class: u0.c
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return d.g(interfaceC0747a, (InterfaceC0747a) obj);
            }
        });
    }
}
