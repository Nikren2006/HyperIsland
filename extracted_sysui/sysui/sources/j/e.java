package j;

import g.AbstractC0355a;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class e implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f4619a;

    public e(List list) {
        this.f4619a = list;
    }

    @Override // j.m
    public AbstractC0355a a() {
        return ((com.airbnb.lottie.value.a) this.f4619a.get(0)).h() ? new g.k(this.f4619a) : new g.j(this.f4619a);
    }

    @Override // j.m
    public boolean b() {
        return this.f4619a.size() == 1 && ((com.airbnb.lottie.value.a) this.f4619a.get(0)).h();
    }

    @Override // j.m
    public List getKeyframes() {
        return this.f4619a;
    }
}
