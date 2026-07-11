package g;

import android.view.animation.Interpolator;
import d.AbstractC0302c;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: g.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0355a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f4263c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public com.airbnb.lottie.value.c f4265e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f4261a = new ArrayList(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4262b = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f4264d = 0.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f4266f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f4267g = -1.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f4268h = -1.0f;

    /* JADX INFO: renamed from: g.a$b */
    public interface b {
        void a();
    }

    /* JADX INFO: renamed from: g.a$c */
    public static final class c implements d {
        public c() {
        }

        @Override // g.AbstractC0355a.d
        public boolean a(float f2) {
            throw new IllegalStateException("not implemented");
        }

        @Override // g.AbstractC0355a.d
        public com.airbnb.lottie.value.a b() {
            throw new IllegalStateException("not implemented");
        }

        @Override // g.AbstractC0355a.d
        public boolean c(float f2) {
            return false;
        }

        @Override // g.AbstractC0355a.d
        public float d() {
            return 0.0f;
        }

        @Override // g.AbstractC0355a.d
        public float e() {
            return 1.0f;
        }

        @Override // g.AbstractC0355a.d
        public boolean isEmpty() {
            return true;
        }
    }

    /* JADX INFO: renamed from: g.a$d */
    public interface d {
        boolean a(float f2);

        com.airbnb.lottie.value.a b();

        boolean c(float f2);

        float d();

        float e();

        boolean isEmpty();
    }

    /* JADX INFO: renamed from: g.a$e */
    public static final class e implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List f4269a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public com.airbnb.lottie.value.a f4271c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public float f4272d = -1.0f;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public com.airbnb.lottie.value.a f4270b = f(0.0f);

        public e(List list) {
            this.f4269a = list;
        }

        @Override // g.AbstractC0355a.d
        public boolean a(float f2) {
            com.airbnb.lottie.value.a aVar = this.f4271c;
            com.airbnb.lottie.value.a aVar2 = this.f4270b;
            if (aVar == aVar2 && this.f4272d == f2) {
                return true;
            }
            this.f4271c = aVar2;
            this.f4272d = f2;
            return false;
        }

        @Override // g.AbstractC0355a.d
        public com.airbnb.lottie.value.a b() {
            return this.f4270b;
        }

        @Override // g.AbstractC0355a.d
        public boolean c(float f2) {
            if (this.f4270b.a(f2)) {
                return !this.f4270b.h();
            }
            this.f4270b = f(f2);
            return true;
        }

        @Override // g.AbstractC0355a.d
        public float d() {
            return ((com.airbnb.lottie.value.a) this.f4269a.get(0)).e();
        }

        @Override // g.AbstractC0355a.d
        public float e() {
            return ((com.airbnb.lottie.value.a) this.f4269a.get(r1.size() - 1)).b();
        }

        public final com.airbnb.lottie.value.a f(float f2) {
            List list = this.f4269a;
            com.airbnb.lottie.value.a aVar = (com.airbnb.lottie.value.a) list.get(list.size() - 1);
            if (f2 >= aVar.e()) {
                return aVar;
            }
            for (int size = this.f4269a.size() - 2; size >= 1; size--) {
                com.airbnb.lottie.value.a aVar2 = (com.airbnb.lottie.value.a) this.f4269a.get(size);
                if (this.f4270b != aVar2 && aVar2.a(f2)) {
                    return aVar2;
                }
            }
            return (com.airbnb.lottie.value.a) this.f4269a.get(0);
        }

        @Override // g.AbstractC0355a.d
        public boolean isEmpty() {
            return false;
        }
    }

    /* JADX INFO: renamed from: g.a$f */
    public static final class f implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final com.airbnb.lottie.value.a f4273a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f4274b = -1.0f;

        public f(List list) {
            this.f4273a = (com.airbnb.lottie.value.a) list.get(0);
        }

        @Override // g.AbstractC0355a.d
        public boolean a(float f2) {
            if (this.f4274b == f2) {
                return true;
            }
            this.f4274b = f2;
            return false;
        }

        @Override // g.AbstractC0355a.d
        public com.airbnb.lottie.value.a b() {
            return this.f4273a;
        }

        @Override // g.AbstractC0355a.d
        public boolean c(float f2) {
            return !this.f4273a.h();
        }

        @Override // g.AbstractC0355a.d
        public float d() {
            return this.f4273a.e();
        }

        @Override // g.AbstractC0355a.d
        public float e() {
            return this.f4273a.b();
        }

        @Override // g.AbstractC0355a.d
        public boolean isEmpty() {
            return false;
        }
    }

    public AbstractC0355a(List list) {
        this.f4263c = o(list);
    }

    public static d o(List list) {
        return list.isEmpty() ? new c() : list.size() == 1 ? new f(list) : new e(list);
    }

    public void a(b bVar) {
        this.f4261a.add(bVar);
    }

    public com.airbnb.lottie.value.a b() {
        AbstractC0302c.a("BaseKeyframeAnimation#getCurrentKeyframe");
        com.airbnb.lottie.value.a aVarB = this.f4263c.b();
        AbstractC0302c.b("BaseKeyframeAnimation#getCurrentKeyframe");
        return aVarB;
    }

    public float c() {
        if (this.f4268h == -1.0f) {
            this.f4268h = this.f4263c.e();
        }
        return this.f4268h;
    }

    public float d() {
        com.airbnb.lottie.value.a aVarB = b();
        if (aVarB == null || aVarB.h()) {
            return 0.0f;
        }
        return aVarB.f1395d.getInterpolation(e());
    }

    public float e() {
        if (this.f4262b) {
            return 0.0f;
        }
        com.airbnb.lottie.value.a aVarB = b();
        if (aVarB.h()) {
            return 0.0f;
        }
        return (this.f4264d - aVarB.e()) / (aVarB.b() - aVarB.e());
    }

    public float f() {
        return this.f4264d;
    }

    public final float g() {
        if (this.f4267g == -1.0f) {
            this.f4267g = this.f4263c.d();
        }
        return this.f4267g;
    }

    public Object h() {
        float fE = e();
        if (this.f4265e == null && this.f4263c.a(fE)) {
            return this.f4266f;
        }
        com.airbnb.lottie.value.a aVarB = b();
        Interpolator interpolator = aVarB.f1396e;
        Object objI = (interpolator == null || aVarB.f1397f == null) ? i(aVarB, d()) : j(aVarB, fE, interpolator.getInterpolation(fE), aVarB.f1397f.getInterpolation(fE));
        this.f4266f = objI;
        return objI;
    }

    public abstract Object i(com.airbnb.lottie.value.a aVar, float f2);

    public Object j(com.airbnb.lottie.value.a aVar, float f2, float f3, float f4) {
        throw new UnsupportedOperationException("This animation does not support split dimensions!");
    }

    public void k() {
        for (int i2 = 0; i2 < this.f4261a.size(); i2++) {
            ((b) this.f4261a.get(i2)).a();
        }
    }

    public void l() {
        this.f4262b = true;
    }

    public void m(float f2) {
        if (this.f4263c.isEmpty()) {
            return;
        }
        if (f2 < g()) {
            f2 = g();
        } else if (f2 > c()) {
            f2 = c();
        }
        if (f2 == this.f4264d) {
            return;
        }
        this.f4264d = f2;
        if (this.f4263c.c(f2)) {
            k();
        }
    }

    public void n(com.airbnb.lottie.value.c cVar) {
        com.airbnb.lottie.value.c cVar2 = this.f4265e;
        if (cVar2 != null) {
            cVar2.setAnimation(null);
        }
        this.f4265e = cVar;
        if (cVar != null) {
            cVar.setAnimation(this);
        }
    }
}
