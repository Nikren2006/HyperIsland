package g;

import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class q extends AbstractC0355a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f4323i;

    public q(com.airbnb.lottie.value.c cVar) {
        this(cVar, null);
    }

    @Override // g.AbstractC0355a
    public float c() {
        return 1.0f;
    }

    @Override // g.AbstractC0355a
    public Object h() {
        com.airbnb.lottie.value.c cVar = this.f4265e;
        Object obj = this.f4323i;
        return cVar.getValueInternal(0.0f, 0.0f, obj, obj, f(), f(), f());
    }

    @Override // g.AbstractC0355a
    public Object i(com.airbnb.lottie.value.a aVar, float f2) {
        return h();
    }

    @Override // g.AbstractC0355a
    public void k() {
        if (this.f4265e != null) {
            super.k();
        }
    }

    @Override // g.AbstractC0355a
    public void m(float f2) {
        this.f4264d = f2;
    }

    public q(com.airbnb.lottie.value.c cVar, Object obj) {
        super(Collections.emptyList());
        n(cVar);
        this.f4323i = obj;
    }
}
