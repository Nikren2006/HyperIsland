package g1;

import com.xiaomi.onetrack.api.au;

/* JADX INFO: loaded from: classes2.dex */
public abstract class s0 extends AbstractC0396x implements S, InterfaceC0370g0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public t0 f4438d;

    @Override // g1.InterfaceC0370g0
    public x0 c() {
        return null;
    }

    @Override // g1.S
    public void dispose() {
        s().p0(this);
    }

    @Override // g1.InterfaceC0370g0
    public boolean isActive() {
        return true;
    }

    public final t0 s() {
        t0 t0Var = this.f4438d;
        if (t0Var != null) {
            return t0Var;
        }
        kotlin.jvm.internal.n.w(au.f2925e);
        return null;
    }

    public final void t(t0 t0Var) {
        this.f4438d = t0Var;
    }

    @Override // l1.q
    public String toString() {
        return I.a(this) + '@' + I.b(this) + "[job@" + I.b(s()) + ']';
    }
}
