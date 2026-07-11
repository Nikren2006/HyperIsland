package d;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import e.C0333a;
import h.C0399a;
import h.C0400b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import l.C0433c;
import p.AbstractC0724d;
import p.AbstractC0727g;
import p.ChoreographerFrameCallbackC0725e;

/* JADX INFO: loaded from: classes.dex */
public class F extends Drawable implements Drawable.Callback, Animatable {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public Bitmap f3728A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public Canvas f3729B;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public Rect f3730D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public RectF f3731E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public Paint f3732F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public Rect f3733G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public Rect f3734H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public RectF f3735I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public RectF f3736J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public Matrix f3737K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public Matrix f3738L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public boolean f3739M;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C0307h f3740a;
    private final ChoreographerFrameCallbackC0725e animator;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f3741b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3742c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3743d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f3744e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f3745f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ValueAnimator.AnimatorUpdateListener f3746g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public C0400b f3747h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f3748i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public C0399a f3749j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f3750k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f3751l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f3752m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C0433c f3753n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f3754o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f3755p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f3756q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f3757r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public S f3758s;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f3759x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Matrix f3760y;

    public class a implements ValueAnimator.AnimatorUpdateListener {
        public a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (F.this.f3753n != null) {
                F.this.f3753n.M(F.this.animator.g());
            }
        }
    }

    public interface b {
        void a(C0307h c0307h);
    }

    public enum c {
        NONE,
        PLAY,
        RESUME
    }

    public F() {
        ChoreographerFrameCallbackC0725e choreographerFrameCallbackC0725e = new ChoreographerFrameCallbackC0725e();
        this.animator = choreographerFrameCallbackC0725e;
        this.f3741b = true;
        this.f3742c = false;
        this.f3743d = false;
        this.f3744e = c.NONE;
        this.f3745f = new ArrayList();
        a aVar = new a();
        this.f3746g = aVar;
        this.f3751l = false;
        this.f3752m = true;
        this.f3754o = 255;
        this.f3758s = S.AUTOMATIC;
        this.f3759x = false;
        this.f3760y = new Matrix();
        this.f3739M = false;
        choreographerFrameCallbackC0725e.addUpdateListener(aVar);
    }

    public final void A(Rect rect, RectF rectF) {
        rectF.set(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void A0() {
        if (this.f3753n == null) {
            this.f3745f.add(new b() { // from class: d.A
                @Override // d.F.b
                public final void a(C0307h c0307h) {
                    this.f3718a.l0(c0307h);
                }
            });
            return;
        }
        z();
        if (v() || Y() == 0) {
            if (isVisible()) {
                this.animator.o();
                this.f3744e = c.NONE;
            } else {
                this.f3744e = c.PLAY;
            }
        }
        if (v()) {
            return;
        }
        P0((int) (a0() < 0.0f ? U() : T()));
        this.animator.endAnimation();
        if (isVisible()) {
            return;
        }
        this.f3744e = c.NONE;
    }

    public final void B(RectF rectF, Rect rect) {
        rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
    }

    public void B0() {
        this.animator.removeAllListeners();
    }

    public void C() {
    }

    public void C0() {
        this.animator.removeAllUpdateListeners();
        this.animator.addUpdateListener(this.f3746g);
    }

    public final void D(Canvas canvas) {
        C0433c c0433c = this.f3753n;
        C0307h c0307h = this.f3740a;
        if (c0433c == null || c0307h == null) {
            return;
        }
        this.f3760y.reset();
        if (!getBounds().isEmpty()) {
            this.f3760y.preScale(r2.width() / c0307h.b().width(), r2.height() / c0307h.b().height());
        }
        c0433c.h(canvas, this.f3760y, this.f3754o);
    }

    public void D0(Animator.AnimatorListener animatorListener) {
        this.animator.removeListener(animatorListener);
    }

    public void E(boolean z2) {
        if (this.f3750k == z2) {
            return;
        }
        this.f3750k = z2;
        if (this.f3740a != null) {
            w();
        }
    }

    public void E0(Animator.AnimatorPauseListener animatorPauseListener) {
        this.animator.removePauseListener(animatorPauseListener);
    }

    public boolean F() {
        return this.f3750k;
    }

    public void F0(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.animator.removeUpdateListener(animatorUpdateListener);
    }

    public void G() {
        this.f3745f.clear();
        this.animator.endAnimation();
        if (isVisible()) {
            return;
        }
        this.f3744e = c.NONE;
    }

    public final void G0(Canvas canvas, C0433c c0433c) {
        if (this.f3740a == null || c0433c == null) {
            return;
        }
        I();
        canvas.getMatrix(this.f3737K);
        canvas.getClipBounds(this.f3730D);
        A(this.f3730D, this.f3731E);
        this.f3737K.mapRect(this.f3731E);
        B(this.f3731E, this.f3730D);
        if (this.f3752m) {
            this.f3736J.set(0.0f, 0.0f, getIntrinsicWidth(), getIntrinsicHeight());
        } else {
            c0433c.f(this.f3736J, null, false);
        }
        this.f3737K.mapRect(this.f3736J);
        Rect bounds = getBounds();
        float fWidth = bounds.width() / getIntrinsicWidth();
        float fHeight = bounds.height() / getIntrinsicHeight();
        K0(this.f3736J, fWidth, fHeight);
        if (!f0()) {
            RectF rectF = this.f3736J;
            Rect rect = this.f3730D;
            rectF.intersect(rect.left, rect.top, rect.right, rect.bottom);
        }
        int iCeil = (int) Math.ceil(this.f3736J.width());
        int iCeil2 = (int) Math.ceil(this.f3736J.height());
        if (iCeil == 0 || iCeil2 == 0) {
            return;
        }
        H(iCeil, iCeil2);
        if (this.f3739M) {
            this.f3760y.set(this.f3737K);
            this.f3760y.preScale(fWidth, fHeight);
            Matrix matrix = this.f3760y;
            RectF rectF2 = this.f3736J;
            matrix.postTranslate(-rectF2.left, -rectF2.top);
            this.f3728A.eraseColor(0);
            c0433c.h(this.f3729B, this.f3760y, this.f3754o);
            this.f3737K.invert(this.f3738L);
            this.f3738L.mapRect(this.f3735I, this.f3736J);
            B(this.f3735I, this.f3734H);
        }
        this.f3733G.set(0, 0, iCeil, iCeil2);
        canvas.drawBitmap(this.f3728A, this.f3733G, this.f3734H, this.f3732F);
    }

    public final void H(int i2, int i3) {
        Bitmap bitmap = this.f3728A;
        if (bitmap == null || bitmap.getWidth() < i2 || this.f3728A.getHeight() < i3) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            this.f3728A = bitmapCreateBitmap;
            this.f3729B.setBitmap(bitmapCreateBitmap);
            this.f3739M = true;
            return;
        }
        if (this.f3728A.getWidth() > i2 || this.f3728A.getHeight() > i3) {
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(this.f3728A, 0, 0, i2, i3);
            this.f3728A = bitmapCreateBitmap2;
            this.f3729B.setBitmap(bitmapCreateBitmap2);
            this.f3739M = true;
        }
    }

    public List H0(i.e eVar) {
        if (this.f3753n == null) {
            AbstractC0724d.c("Cannot resolve KeyPath. Composition is not set yet.");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        this.f3753n.i(eVar, 0, arrayList, new i.e(new String[0]));
        return arrayList;
    }

    public final void I() {
        if (this.f3729B != null) {
            return;
        }
        this.f3729B = new Canvas();
        this.f3736J = new RectF();
        this.f3737K = new Matrix();
        this.f3738L = new Matrix();
        this.f3730D = new Rect();
        this.f3731E = new RectF();
        this.f3732F = new C0333a();
        this.f3733G = new Rect();
        this.f3734H = new Rect();
        this.f3735I = new RectF();
    }

    public void I0() {
        if (this.f3753n == null) {
            this.f3745f.add(new b() { // from class: d.w
                @Override // d.F.b
                public final void a(C0307h c0307h) {
                    this.f3960a.m0(c0307h);
                }
            });
            return;
        }
        z();
        if (v() || Y() == 0) {
            if (isVisible()) {
                this.animator.s();
                this.f3744e = c.NONE;
            } else {
                this.f3744e = c.RESUME;
            }
        }
        if (v()) {
            return;
        }
        P0((int) (a0() < 0.0f ? U() : T()));
        this.animator.endAnimation();
        if (isVisible()) {
            return;
        }
        this.f3744e = c.NONE;
    }

    public Bitmap J(String str) {
        C0400b c0400bP = P();
        if (c0400bP != null) {
            return c0400bP.a(str);
        }
        return null;
    }

    public void J0() {
        this.animator.t();
    }

    public boolean K() {
        return this.f3752m;
    }

    public final void K0(RectF rectF, float f2, float f3) {
        rectF.set(rectF.left * f2, rectF.top * f3, rectF.right * f2, rectF.bottom * f3);
    }

    public C0307h L() {
        return this.f3740a;
    }

    public void L0(boolean z2) {
        this.f3757r = z2;
    }

    public final Context M() {
        Drawable.Callback callback = getCallback();
        if (callback != null && (callback instanceof View)) {
            return ((View) callback).getContext();
        }
        return null;
    }

    public void M0(boolean z2) {
        if (z2 != this.f3752m) {
            this.f3752m = z2;
            C0433c c0433c = this.f3753n;
            if (c0433c != null) {
                c0433c.R(z2);
            }
            invalidateSelf();
        }
    }

    public final C0399a N() {
        if (getCallback() == null) {
            return null;
        }
        if (this.f3749j == null) {
            this.f3749j = new C0399a(getCallback(), null);
        }
        return this.f3749j;
    }

    public boolean N0(C0307h c0307h) {
        if (this.f3740a == c0307h) {
            return false;
        }
        this.f3739M = true;
        y();
        this.f3740a = c0307h;
        w();
        this.animator.u(c0307h);
        g1(this.animator.getAnimatedFraction());
        Iterator it = new ArrayList(this.f3745f).iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar != null) {
                bVar.a(c0307h);
            }
            it.remove();
        }
        this.f3745f.clear();
        c0307h.v(this.f3755p);
        z();
        Drawable.Callback callback = getCallback();
        if (callback instanceof ImageView) {
            ImageView imageView = (ImageView) callback;
            imageView.setImageDrawable(null);
            imageView.setImageDrawable(this);
        }
        return true;
    }

    public int O() {
        return (int) this.animator.h();
    }

    public void O0(AbstractC0300a abstractC0300a) {
        C0399a c0399a = this.f3749j;
        if (c0399a != null) {
            c0399a.c(abstractC0300a);
        }
    }

    public final C0400b P() {
        if (getCallback() == null) {
            return null;
        }
        C0400b c0400b = this.f3747h;
        if (c0400b != null && !c0400b.b(M())) {
            this.f3747h = null;
        }
        if (this.f3747h == null) {
            this.f3747h = new C0400b(getCallback(), this.f3748i, null, this.f3740a.j());
        }
        return this.f3747h;
    }

    public void P0(final int i2) {
        if (this.f3740a == null) {
            this.f3745f.add(new b() { // from class: d.D
                @Override // d.F.b
                public final void a(C0307h c0307h) {
                    this.f3723a.n0(i2, c0307h);
                }
            });
        } else {
            this.animator.v(i2);
        }
    }

    public String Q() {
        return this.f3748i;
    }

    public void Q0(boolean z2) {
        this.f3742c = z2;
    }

    public G R(String str) {
        C0307h c0307h = this.f3740a;
        if (c0307h == null) {
            return null;
        }
        return (G) c0307h.j().get(str);
    }

    public void R0(InterfaceC0301b interfaceC0301b) {
        C0400b c0400b = this.f3747h;
        if (c0400b != null) {
            c0400b.d(interfaceC0301b);
        }
    }

    public boolean S() {
        return this.f3751l;
    }

    public void S0(String str) {
        this.f3748i = str;
    }

    public float T() {
        return this.animator.j();
    }

    public void T0(boolean z2) {
        this.f3751l = z2;
    }

    public float U() {
        return this.animator.k();
    }

    public void U0(final int i2) {
        if (this.f3740a == null) {
            this.f3745f.add(new b() { // from class: d.s
                @Override // d.F.b
                public final void a(C0307h c0307h) {
                    this.f3950a.o0(i2, c0307h);
                }
            });
        } else {
            this.animator.w(i2 + 0.99f);
        }
    }

    public O V() {
        C0307h c0307h = this.f3740a;
        if (c0307h != null) {
            return c0307h.n();
        }
        return null;
    }

    public void V0(final String str) {
        C0307h c0307h = this.f3740a;
        if (c0307h == null) {
            this.f3745f.add(new b() { // from class: d.y
                @Override // d.F.b
                public final void a(C0307h c0307h2) {
                    this.f3965a.p0(str, c0307h2);
                }
            });
            return;
        }
        i.h hVarL = c0307h.l(str);
        if (hVarL != null) {
            U0((int) (hVarL.f4530b + hVarL.f4531c));
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public float W() {
        return this.animator.g();
    }

    public void W0(final float f2) {
        C0307h c0307h = this.f3740a;
        if (c0307h == null) {
            this.f3745f.add(new b() { // from class: d.v
                @Override // d.F.b
                public final void a(C0307h c0307h2) {
                    this.f3958a.q0(f2, c0307h2);
                }
            });
        } else {
            this.animator.w(AbstractC0727g.i(c0307h.p(), this.f3740a.f(), f2));
        }
    }

    public S X() {
        return this.f3759x ? S.SOFTWARE : S.HARDWARE;
    }

    public void X0(final int i2, final int i3) {
        if (this.f3740a == null) {
            this.f3745f.add(new b() { // from class: d.r
                @Override // d.F.b
                public final void a(C0307h c0307h) {
                    this.f3947a.r0(i2, i3, c0307h);
                }
            });
        } else {
            this.animator.x(i2, i3 + 0.99f);
        }
    }

    public int Y() {
        return this.animator.getRepeatCount();
    }

    public void Y0(final String str) {
        C0307h c0307h = this.f3740a;
        if (c0307h == null) {
            this.f3745f.add(new b() { // from class: d.q
                @Override // d.F.b
                public final void a(C0307h c0307h2) {
                    this.f3945a.s0(str, c0307h2);
                }
            });
            return;
        }
        i.h hVarL = c0307h.l(str);
        if (hVarL != null) {
            int i2 = (int) hVarL.f4530b;
            X0(i2, ((int) hVarL.f4531c) + i2);
        } else {
            throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
        }
    }

    public int Z() {
        return this.animator.getRepeatMode();
    }

    public void Z0(final String str, final String str2, final boolean z2) {
        C0307h c0307h = this.f3740a;
        if (c0307h == null) {
            this.f3745f.add(new b() { // from class: d.x
                @Override // d.F.b
                public final void a(C0307h c0307h2) {
                    this.f3961a.t0(str, str2, z2, c0307h2);
                }
            });
            return;
        }
        i.h hVarL = c0307h.l(str);
        if (hVarL == null) {
            throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
        }
        int i2 = (int) hVarL.f4530b;
        i.h hVarL2 = this.f3740a.l(str2);
        if (hVarL2 != null) {
            X0(i2, (int) (hVarL2.f4530b + (z2 ? 1.0f : 0.0f)));
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str2 + ".");
    }

    public float a0() {
        return this.animator.l();
    }

    public void a1(final float f2, final float f3) {
        C0307h c0307h = this.f3740a;
        if (c0307h == null) {
            this.f3745f.add(new b() { // from class: d.E
                @Override // d.F.b
                public final void a(C0307h c0307h2) {
                    this.f3725a.u0(f2, f3, c0307h2);
                }
            });
        } else {
            X0((int) AbstractC0727g.i(c0307h.p(), this.f3740a.f(), f2), (int) AbstractC0727g.i(this.f3740a.p(), this.f3740a.f(), f3));
        }
    }

    public U b0() {
        return null;
    }

    public void b1(final int i2) {
        if (this.f3740a == null) {
            this.f3745f.add(new b() { // from class: d.t
                @Override // d.F.b
                public final void a(C0307h c0307h) {
                    this.f3952a.v0(i2, c0307h);
                }
            });
        } else {
            this.animator.y(i2);
        }
    }

    public Typeface c0(String str, String str2) {
        C0399a c0399aN = N();
        if (c0399aN != null) {
            return c0399aN.b(str, str2);
        }
        return null;
    }

    public void c1(final String str) {
        C0307h c0307h = this.f3740a;
        if (c0307h == null) {
            this.f3745f.add(new b() { // from class: d.z
                @Override // d.F.b
                public final void a(C0307h c0307h2) {
                    this.f3967a.w0(str, c0307h2);
                }
            });
            return;
        }
        i.h hVarL = c0307h.l(str);
        if (hVarL != null) {
            b1((int) hVarL.f4530b);
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public boolean d0() {
        C0433c c0433c = this.f3753n;
        return c0433c != null && c0433c.P();
    }

    public void d1(final float f2) {
        C0307h c0307h = this.f3740a;
        if (c0307h == null) {
            this.f3745f.add(new b() { // from class: d.B
                @Override // d.F.b
                public final void a(C0307h c0307h2) {
                    this.f3719a.x0(f2, c0307h2);
                }
            });
        } else {
            b1((int) AbstractC0727g.i(c0307h.p(), this.f3740a.f(), f2));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        AbstractC0302c.a("Drawable#draw");
        if (this.f3743d) {
            try {
                if (this.f3759x) {
                    G0(canvas, this.f3753n);
                } else {
                    D(canvas);
                }
            } catch (Throwable th) {
                AbstractC0724d.b("Lottie crashed in draw!", th);
            }
        } else if (this.f3759x) {
            G0(canvas, this.f3753n);
        } else {
            D(canvas);
        }
        this.f3739M = false;
        AbstractC0302c.b("Drawable#draw");
    }

    public boolean e0() {
        C0433c c0433c = this.f3753n;
        return c0433c != null && c0433c.Q();
    }

    public void e1(boolean z2) {
        if (this.f3756q == z2) {
            return;
        }
        this.f3756q = z2;
        C0433c c0433c = this.f3753n;
        if (c0433c != null) {
            c0433c.K(z2);
        }
    }

    public final boolean f0() {
        Drawable.Callback callback = getCallback();
        if (!(callback instanceof View)) {
            return false;
        }
        if (((View) callback).getParent() instanceof ViewGroup) {
            return !((ViewGroup) r2).getClipChildren();
        }
        return false;
    }

    public void f1(boolean z2) {
        this.f3755p = z2;
        C0307h c0307h = this.f3740a;
        if (c0307h != null) {
            c0307h.v(z2);
        }
    }

    public boolean g0() {
        ChoreographerFrameCallbackC0725e choreographerFrameCallbackC0725e = this.animator;
        if (choreographerFrameCallbackC0725e == null) {
            return false;
        }
        return choreographerFrameCallbackC0725e.isRunning();
    }

    public void g1(final float f2) {
        if (this.f3740a == null) {
            this.f3745f.add(new b() { // from class: d.C
                @Override // d.F.b
                public final void a(C0307h c0307h) {
                    this.f3721a.y0(f2, c0307h);
                }
            });
            return;
        }
        AbstractC0302c.a("Drawable#setProgress");
        this.animator.v(this.f3740a.h(f2));
        AbstractC0302c.b("Drawable#setProgress");
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f3754o;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        C0307h c0307h = this.f3740a;
        if (c0307h == null) {
            return -1;
        }
        return c0307h.b().height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        C0307h c0307h = this.f3740a;
        if (c0307h == null) {
            return -1;
        }
        return c0307h.b().width();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public boolean h0() {
        if (isVisible()) {
            return this.animator.isRunning();
        }
        c cVar = this.f3744e;
        return cVar == c.PLAY || cVar == c.RESUME;
    }

    public void h1(S s2) {
        this.f3758s = s2;
        z();
    }

    public boolean i0() {
        return this.f3757r;
    }

    public void i1(int i2) {
        this.animator.setRepeatCount(i2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        if (this.f3739M) {
            return;
        }
        this.f3739M = true;
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return g0();
    }

    public boolean j0() {
        return this.f3750k;
    }

    public void j1(int i2) {
        this.animator.setRepeatMode(i2);
    }

    public final /* synthetic */ void k0(i.e eVar, Object obj, com.airbnb.lottie.value.c cVar, C0307h c0307h) {
        u(eVar, obj, cVar);
    }

    public void k1(boolean z2) {
        this.f3743d = z2;
    }

    public final /* synthetic */ void l0(C0307h c0307h) {
        A0();
    }

    public void l1(float f2) {
        this.animator.z(f2);
    }

    public final /* synthetic */ void m0(C0307h c0307h) {
        I0();
    }

    public void m1(Boolean bool) {
        this.f3741b = bool.booleanValue();
    }

    public final /* synthetic */ void n0(int i2, C0307h c0307h) {
        P0(i2);
    }

    public void n1(U u2) {
    }

    public final /* synthetic */ void o0(int i2, C0307h c0307h) {
        U0(i2);
    }

    public Bitmap o1(String str, Bitmap bitmap) {
        C0400b c0400bP = P();
        if (c0400bP == null) {
            AbstractC0724d.c("Cannot update bitmap. Most likely the drawable is not added to a View which prevents Lottie from getting a Context.");
            return null;
        }
        Bitmap bitmapE = c0400bP.e(str, bitmap);
        invalidateSelf();
        return bitmapE;
    }

    public final /* synthetic */ void p0(String str, C0307h c0307h) {
        V0(str);
    }

    public boolean p1() {
        return this.f3740a.c().size() > 0;
    }

    public final /* synthetic */ void q0(float f2, C0307h c0307h) {
        W0(f2);
    }

    public void r(Animator.AnimatorListener animatorListener) {
        this.animator.addListener(animatorListener);
    }

    public final /* synthetic */ void r0(int i2, int i3, C0307h c0307h) {
        X0(i2, i3);
    }

    public void s(Animator.AnimatorPauseListener animatorPauseListener) {
        this.animator.addPauseListener(animatorPauseListener);
    }

    public final /* synthetic */ void s0(String str, C0307h c0307h) {
        Y0(str);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.scheduleDrawable(this, runnable, j2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f3754o = i2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        AbstractC0724d.c("Use addColorFilter instead.");
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z2, boolean z3) {
        boolean zIsVisible = isVisible();
        boolean visible = super.setVisible(z2, z3);
        if (z2) {
            c cVar = this.f3744e;
            if (cVar == c.PLAY) {
                A0();
            } else if (cVar == c.RESUME) {
                I0();
            }
        } else if (this.animator.isRunning()) {
            z0();
            this.f3744e = c.RESUME;
        } else if (zIsVisible) {
            this.f3744e = c.NONE;
        }
        return visible;
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable.Callback callback = getCallback();
        if ((callback instanceof View) && ((View) callback).isInEditMode()) {
            return;
        }
        A0();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        G();
    }

    public void t(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.animator.addUpdateListener(animatorUpdateListener);
    }

    public final /* synthetic */ void t0(String str, String str2, boolean z2, C0307h c0307h) {
        Z0(str, str2, z2);
    }

    public void u(final i.e eVar, final Object obj, final com.airbnb.lottie.value.c cVar) {
        C0433c c0433c = this.f3753n;
        if (c0433c == null) {
            this.f3745f.add(new b() { // from class: d.u
                @Override // d.F.b
                public final void a(C0307h c0307h) {
                    this.f3954a.k0(eVar, obj, cVar, c0307h);
                }
            });
            return;
        }
        boolean zIsEmpty = true;
        if (eVar == i.e.f4524c) {
            c0433c.e(obj, cVar);
        } else if (eVar.d() != null) {
            eVar.d().e(obj, cVar);
        } else {
            List listH0 = H0(eVar);
            for (int i2 = 0; i2 < listH0.size(); i2++) {
                ((i.e) listH0.get(i2)).d().e(obj, cVar);
            }
            zIsEmpty = true ^ listH0.isEmpty();
        }
        if (zIsEmpty) {
            invalidateSelf();
            if (obj == K.f3776E) {
                g1(W());
            }
        }
    }

    public final /* synthetic */ void u0(float f2, float f3, C0307h c0307h) {
        a1(f2, f3);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.unscheduleDrawable(this, runnable);
    }

    public final boolean v() {
        return this.f3741b || this.f3742c;
    }

    public final /* synthetic */ void v0(int i2, C0307h c0307h) {
        b1(i2);
    }

    public final void w() {
        C0307h c0307h = this.f3740a;
        if (c0307h == null) {
            return;
        }
        C0433c c0433c = new C0433c(this, n.v.a(c0307h), c0307h.k(), c0307h);
        this.f3753n = c0433c;
        if (this.f3756q) {
            c0433c.K(true);
        }
        this.f3753n.R(this.f3752m);
    }

    public final /* synthetic */ void w0(String str, C0307h c0307h) {
        c1(str);
    }

    public void x() {
        this.f3745f.clear();
        this.animator.cancel();
        if (isVisible()) {
            return;
        }
        this.f3744e = c.NONE;
    }

    public final /* synthetic */ void x0(float f2, C0307h c0307h) {
        d1(f2);
    }

    public void y() {
        if (this.animator.isRunning()) {
            this.animator.cancel();
            if (!isVisible()) {
                this.f3744e = c.NONE;
            }
        }
        this.f3740a = null;
        this.f3753n = null;
        this.f3747h = null;
        this.animator.f();
        invalidateSelf();
    }

    public final /* synthetic */ void y0(float f2, C0307h c0307h) {
        g1(f2);
    }

    public final void z() {
        C0307h c0307h = this.f3740a;
        if (c0307h == null) {
            return;
        }
        this.f3759x = this.f3758s.a(Build.VERSION.SDK_INT, c0307h.q(), c0307h.m());
    }

    public void z0() {
        this.f3745f.clear();
        this.animator.n();
        if (isVisible()) {
            return;
        }
        this.f3744e = c.NONE;
    }
}
