package j;

import g.AbstractC0355a;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class i implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0409b f4620a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0409b f4621b;

    public i(C0409b c0409b, C0409b c0409b2) {
        this.f4620a = c0409b;
        this.f4621b = c0409b2;
    }

    @Override // j.m
    public AbstractC0355a a() {
        return new g.n(this.f4620a.a(), this.f4621b.a());
    }

    @Override // j.m
    public boolean b() {
        return this.f4620a.b() && this.f4621b.b();
    }

    @Override // j.m
    public List getKeyframes() {
        throw new UnsupportedOperationException("Cannot call getKeyframes on AnimatableSplitDimensionPathValue.");
    }
}
