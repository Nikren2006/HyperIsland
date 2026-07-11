package p0;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import l0.C0439b;
import l0.InterfaceC0441d;
import l0.InterfaceC0444g;
import x0.v;

/* JADX INFO: loaded from: classes2.dex */
public class d extends AbstractC0729b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0730c f6371a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f6372b;

    public static class a implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference f6373a;

        public a(v vVar) {
            this.f6373a = new WeakReference(vVar);
        }

        @Override // p0.g
        public int a(int i2) {
            v vVar = (v) this.f6373a.get();
            if (vVar != null) {
                return vVar.a(i2);
            }
            return 0;
        }
    }

    public static class b extends InterfaceC0441d.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AbstractC0728a f6374a;

        public b(AbstractC0728a abstractC0728a) {
            this.f6374a = abstractC0728a;
        }

        @Override // l0.InterfaceC0441d
        public int D0(int i2) {
            return this.f6374a.a(i2);
        }

        @Override // l0.InterfaceC0441d
        public InterfaceC0444g T0() {
            return new InterfaceC0444g.a();
        }

        @Override // l0.InterfaceC0441d
        public int i0() {
            return this.f6374a.a(0);
        }
    }

    public interface c {
        void q(List list);
    }

    public d(v vVar) {
        this.f6372b = vVar;
        this.f6371a = new f(vVar.b0(), this, new a(vVar));
    }

    @Override // p0.i
    public void b() {
        Collection collectionL = this.f6371a.l();
        z0.e.c("DeviceManager_export-api", "onDeviceListChange() , size: " + collectionL.size());
        this.f6372b.q(e(collectionL));
    }

    public final List e(Collection collection) {
        ArrayList arrayList = new ArrayList();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            AbstractC0728a abstractC0728a = (AbstractC0728a) it.next();
            arrayList.add(new m0.i(new C0439b(abstractC0728a.b() + "_SystemUI", abstractC0728a.c(), new b(abstractC0728a), abstractC0728a.d(0))));
        }
        return arrayList;
    }

    public List f() {
        return e(this.f6371a.l());
    }

    public void g() {
        this.f6371a.z();
    }

    public boolean h() {
        return this.f6371a.C();
    }
}
