package f;

import g.AbstractC0355a;
import java.util.ArrayList;
import java.util.List;
import k.s;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class u implements c, AbstractC0355a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4219a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f4220b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f4221c = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s.a f4222d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AbstractC0355a f4223e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AbstractC0355a f4224f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AbstractC0355a f4225g;

    public u(AbstractC0432b abstractC0432b, k.s sVar) {
        this.f4219a = sVar.c();
        this.f4220b = sVar.g();
        this.f4222d = sVar.f();
        AbstractC0355a abstractC0355aA = sVar.e().a();
        this.f4223e = abstractC0355aA;
        AbstractC0355a abstractC0355aA2 = sVar.b().a();
        this.f4224f = abstractC0355aA2;
        AbstractC0355a abstractC0355aA3 = sVar.d().a();
        this.f4225g = abstractC0355aA3;
        abstractC0432b.j(abstractC0355aA);
        abstractC0432b.j(abstractC0355aA2);
        abstractC0432b.j(abstractC0355aA3);
        abstractC0355aA.a(this);
        abstractC0355aA2.a(this);
        abstractC0355aA3.a(this);
    }

    @Override // g.AbstractC0355a.b
    public void a() {
        for (int i2 = 0; i2 < this.f4221c.size(); i2++) {
            ((AbstractC0355a.b) this.f4221c.get(i2)).a();
        }
    }

    @Override // f.c
    public void b(List list, List list2) {
    }

    public void e(AbstractC0355a.b bVar) {
        this.f4221c.add(bVar);
    }

    public AbstractC0355a g() {
        return this.f4224f;
    }

    public AbstractC0355a i() {
        return this.f4225g;
    }

    public AbstractC0355a j() {
        return this.f4223e;
    }

    public s.a k() {
        return this.f4222d;
    }

    public boolean l() {
        return this.f4220b;
    }
}
