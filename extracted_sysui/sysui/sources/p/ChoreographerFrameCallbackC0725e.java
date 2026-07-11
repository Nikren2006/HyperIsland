package p;

import android.view.Choreographer;
import d.AbstractC0302c;
import d.C0307h;

/* JADX INFO: renamed from: p.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class ChoreographerFrameCallbackC0725e extends AbstractC0721a implements Choreographer.FrameCallback {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public C0307h f6335j;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f6328c = 1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f6329d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f6330e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f6331f = 0.0f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6332g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f6333h = -2.14748365E9f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f6334i = 2.14748365E9f;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f6336k = false;

    public final void A() {
        if (this.f6335j == null) {
            return;
        }
        float f2 = this.f6331f;
        if (f2 < this.f6333h || f2 > this.f6334i) {
            throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", Float.valueOf(this.f6333h), Float.valueOf(this.f6334i), Float.valueOf(this.f6331f)));
        }
    }

    @Override // p.AbstractC0721a
    public void a() {
        super.a();
        b(m());
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public void cancel() {
        a();
        q();
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j2) {
        p();
        if (this.f6335j == null || !isRunning()) {
            return;
        }
        AbstractC0302c.a("LottieValueAnimator#doFrame");
        float fI = (this.f6330e != 0 ? j2 - r1 : 0L) / i();
        float f2 = this.f6331f;
        if (m()) {
            fI = -fI;
        }
        float f3 = f2 + fI;
        this.f6331f = f3;
        boolean zD = AbstractC0727g.d(f3, k(), j());
        this.f6331f = AbstractC0727g.b(this.f6331f, k(), j());
        this.f6330e = j2;
        e();
        if (!zD) {
            if (getRepeatCount() == -1 || this.f6332g < getRepeatCount()) {
                c();
                this.f6332g++;
                if (getRepeatMode() == 2) {
                    this.f6329d = !this.f6329d;
                    t();
                } else {
                    this.f6331f = m() ? j() : k();
                }
                this.f6330e = j2;
            } else {
                this.f6331f = this.f6328c < 0.0f ? k() : j();
                q();
                b(m());
            }
        }
        A();
        AbstractC0302c.b("LottieValueAnimator#doFrame");
    }

    public void endAnimation() {
        q();
        b(m());
    }

    public void f() {
        this.f6335j = null;
        this.f6333h = -2.14748365E9f;
        this.f6334i = 2.14748365E9f;
    }

    public float g() {
        C0307h c0307h = this.f6335j;
        if (c0307h == null) {
            return 0.0f;
        }
        return (this.f6331f - c0307h.p()) / (this.f6335j.f() - this.f6335j.p());
    }

    @Override // android.animation.ValueAnimator
    public float getAnimatedFraction() {
        float fK;
        float fJ;
        float fK2;
        if (this.f6335j == null) {
            return 0.0f;
        }
        if (m()) {
            fK = j() - this.f6331f;
            fJ = j();
            fK2 = k();
        } else {
            fK = this.f6331f - k();
            fJ = j();
            fK2 = k();
        }
        return fK / (fJ - fK2);
    }

    @Override // android.animation.ValueAnimator
    public Object getAnimatedValue() {
        return Float.valueOf(g());
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public long getDuration() {
        C0307h c0307h = this.f6335j;
        if (c0307h == null) {
            return 0L;
        }
        return (long) c0307h.d();
    }

    public float h() {
        return this.f6331f;
    }

    public final float i() {
        C0307h c0307h = this.f6335j;
        if (c0307h == null) {
            return Float.MAX_VALUE;
        }
        return (1.0E9f / c0307h.i()) / Math.abs(this.f6328c);
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public boolean isRunning() {
        return this.f6336k;
    }

    public float j() {
        C0307h c0307h = this.f6335j;
        if (c0307h == null) {
            return 0.0f;
        }
        float f2 = this.f6334i;
        return f2 == 2.14748365E9f ? c0307h.f() : f2;
    }

    public float k() {
        C0307h c0307h = this.f6335j;
        if (c0307h == null) {
            return 0.0f;
        }
        float f2 = this.f6333h;
        return f2 == -2.14748365E9f ? c0307h.p() : f2;
    }

    public float l() {
        return this.f6328c;
    }

    public final boolean m() {
        return l() < 0.0f;
    }

    public void n() {
        q();
    }

    public void o() {
        this.f6336k = true;
        d(m());
        v((int) (m() ? j() : k()));
        this.f6330e = 0L;
        this.f6332g = 0;
        p();
    }

    public void p() {
        if (isRunning()) {
            r(false);
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    public void q() {
        r(true);
    }

    public void r(boolean z2) {
        Choreographer.getInstance().removeFrameCallback(this);
        if (z2) {
            this.f6336k = false;
        }
    }

    public void s() {
        this.f6336k = true;
        p();
        this.f6330e = 0L;
        if (m() && h() == k()) {
            this.f6331f = j();
        } else {
            if (m() || h() != j()) {
                return;
            }
            this.f6331f = k();
        }
    }

    @Override // android.animation.ValueAnimator
    public void setRepeatMode(int i2) {
        super.setRepeatMode(i2);
        if (i2 == 2 || !this.f6329d) {
            return;
        }
        this.f6329d = false;
        t();
    }

    public void t() {
        z(-l());
    }

    public void u(C0307h c0307h) {
        boolean z2 = this.f6335j == null;
        this.f6335j = c0307h;
        if (z2) {
            x(Math.max(this.f6333h, c0307h.p()), Math.min(this.f6334i, c0307h.f()));
        } else {
            x((int) c0307h.p(), (int) c0307h.f());
        }
        float f2 = this.f6331f;
        this.f6331f = 0.0f;
        v((int) f2);
        e();
    }

    public void v(float f2) {
        if (this.f6331f == f2) {
            return;
        }
        this.f6331f = AbstractC0727g.b(f2, k(), j());
        this.f6330e = 0L;
        e();
    }

    public void w(float f2) {
        x(this.f6333h, f2);
    }

    public void x(float f2, float f3) {
        if (f2 > f3) {
            throw new IllegalArgumentException(String.format("minFrame (%s) must be <= maxFrame (%s)", Float.valueOf(f2), Float.valueOf(f3)));
        }
        C0307h c0307h = this.f6335j;
        float fP = c0307h == null ? -3.4028235E38f : c0307h.p();
        C0307h c0307h2 = this.f6335j;
        float f4 = c0307h2 == null ? Float.MAX_VALUE : c0307h2.f();
        float fB = AbstractC0727g.b(f2, fP, f4);
        float fB2 = AbstractC0727g.b(f3, fP, f4);
        if (fB == this.f6333h && fB2 == this.f6334i) {
            return;
        }
        this.f6333h = fB;
        this.f6334i = fB2;
        v((int) AbstractC0727g.b(this.f6331f, fB, fB2));
    }

    public void y(int i2) {
        x(i2, (int) this.f6334i);
    }

    public void z(float f2) {
        this.f6328c = f2;
    }
}
