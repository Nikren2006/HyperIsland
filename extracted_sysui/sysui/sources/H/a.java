package H;

import L.a;
import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import androidx.core.math.MathUtils;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import u.AbstractC0743a;

/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public static final boolean f176t0 = false;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public static final Paint f177u0 = null;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public Typeface f178A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public Typeface f179B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public Typeface f180C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public L.a f181D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public L.a f182E;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public CharSequence f184G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public CharSequence f185H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f186I;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f188K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public Bitmap f189L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public Paint f190M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public float f191N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public float f192O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public float f193P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public float f194Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public float f195R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public int f196S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public int[] f197T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public boolean f198U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public final TextPaint f199V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final TextPaint f200W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public TimeInterpolator f201X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public TimeInterpolator f202Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public float f203Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f204a;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public float f205a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f206b;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public float f207b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f208c;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public ColorStateList f209c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f210d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public float f211d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f212e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public float f213e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f214f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public float f215f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Rect f216g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public ColorStateList f217g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Rect f218h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public float f219h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final RectF f220i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public float f221i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public float f223j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public StaticLayout f225k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public float f227l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public float f229m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ColorStateList f230n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public float f231n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ColorStateList f232o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public CharSequence f233o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f234p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f236q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f238r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f240s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f242t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f243u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f244v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Typeface f245w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Typeface f246x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Typeface f247y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Typeface f248z;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f222j = 16;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f224k = 16;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f226l = 15.0f;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f228m = 15.0f;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public TextUtils.TruncateAt f183F = TextUtils.TruncateAt.END;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f187J = true;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public int f235p0 = 1;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public float f237q0 = 0.0f;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public float f239r0 = 1.0f;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public int f241s0 = g.f258n;

    /* JADX INFO: renamed from: H.a$a, reason: collision with other inner class name */
    public class C0009a implements a.InterfaceC0013a {
        public C0009a() {
        }

        @Override // L.a.InterfaceC0013a
        public void a(Typeface typeface) {
            a.this.T(typeface);
        }
    }

    public a(View view) {
        this.f204a = view;
        TextPaint textPaint = new TextPaint(129);
        this.f199V = textPaint;
        this.f200W = new TextPaint(textPaint);
        this.f218h = new Rect();
        this.f216g = new Rect();
        this.f220i = new RectF();
        this.f212e = e();
        H(view.getContext().getResources().getConfiguration());
    }

    public static boolean C(float f2, float f3) {
        return Math.abs(f2 - f3) < 1.0E-5f;
    }

    public static float G(float f2, float f3, float f4, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f4 = timeInterpolator.getInterpolation(f4);
        }
        return AbstractC0743a.a(f2, f3, f4);
    }

    public static boolean L(Rect rect, int i2, int i3, int i4, int i5) {
        return rect.left == i2 && rect.top == i3 && rect.right == i4 && rect.bottom == i5;
    }

    public static int a(int i2, int i3, float f2) {
        float f3 = 1.0f - f2;
        return Color.argb(Math.round((Color.alpha(i2) * f3) + (Color.alpha(i3) * f2)), Math.round((Color.red(i2) * f3) + (Color.red(i3) * f2)), Math.round((Color.green(i2) * f3) + (Color.green(i3) * f2)), Math.round((Color.blue(i2) * f3) + (Color.blue(i3) * f2)));
    }

    public final void A(TextPaint textPaint) {
        textPaint.setTextSize(this.f226l);
        textPaint.setTypeface(this.f248z);
        textPaint.setLetterSpacing(this.f221i0);
    }

    public final void B(float f2) {
        if (this.f208c) {
            this.f220i.set(f2 < this.f212e ? this.f216g : this.f218h);
            return;
        }
        this.f220i.left = G(this.f216g.left, this.f218h.left, f2, this.f201X);
        this.f220i.top = G(this.f236q, this.f238r, f2, this.f201X);
        this.f220i.right = G(this.f216g.right, this.f218h.right, f2, this.f201X);
        this.f220i.bottom = G(this.f216g.bottom, this.f218h.bottom, f2, this.f201X);
    }

    public final boolean D() {
        return ViewCompat.getLayoutDirection(this.f204a) == 1;
    }

    public final boolean E() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.f232o;
        return (colorStateList2 != null && colorStateList2.isStateful()) || ((colorStateList = this.f230n) != null && colorStateList.isStateful());
    }

    public final boolean F(CharSequence charSequence, boolean z2) {
        return (z2 ? TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL : TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR).isRtl(charSequence, 0, charSequence.length());
    }

    public void H(Configuration configuration) {
        Typeface typeface = this.f247y;
        if (typeface != null) {
            this.f246x = L.g.b(configuration, typeface);
        }
        Typeface typeface2 = this.f179B;
        if (typeface2 != null) {
            this.f178A = L.g.b(configuration, typeface2);
        }
        Typeface typeface3 = this.f246x;
        if (typeface3 == null) {
            typeface3 = this.f247y;
        }
        this.f245w = typeface3;
        Typeface typeface4 = this.f178A;
        if (typeface4 == null) {
            typeface4 = this.f179B;
        }
        this.f248z = typeface4;
        K(true);
    }

    public final float I(TextPaint textPaint, CharSequence charSequence) {
        return textPaint.measureText(charSequence, 0, charSequence.length());
    }

    public void J() {
        K(false);
    }

    public void K(boolean z2) {
        if ((this.f204a.getHeight() <= 0 || this.f204a.getWidth() <= 0) && !z2) {
            return;
        }
        b(z2);
        c();
    }

    public void M(ColorStateList colorStateList) {
        if (this.f232o == colorStateList && this.f230n == colorStateList) {
            return;
        }
        this.f232o = colorStateList;
        this.f230n = colorStateList;
        J();
    }

    public void N(int i2, int i3, int i4, int i5) {
        if (L(this.f218h, i2, i3, i4, i5)) {
            return;
        }
        this.f218h.set(i2, i3, i4, i5);
        this.f198U = true;
    }

    public void O(Rect rect) {
        N(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void P(int i2) {
        L.d dVar = new L.d(this.f204a.getContext(), i2);
        if (dVar.i() != null) {
            this.f232o = dVar.i();
        }
        if (dVar.j() != 0.0f) {
            this.f228m = dVar.j();
        }
        ColorStateList colorStateList = dVar.f372c;
        if (colorStateList != null) {
            this.f209c0 = colorStateList;
        }
        this.f205a0 = dVar.f377h;
        this.f207b0 = dVar.f378i;
        this.f203Z = dVar.f379j;
        this.f219h0 = dVar.f381l;
        L.a aVar = this.f182E;
        if (aVar != null) {
            aVar.c();
        }
        this.f182E = new L.a(new C0009a(), dVar.e());
        dVar.g(this.f204a.getContext(), this.f182E);
        J();
    }

    public final void Q(float f2) {
        this.f229m0 = f2;
        ViewCompat.postInvalidateOnAnimation(this.f204a);
    }

    public void R(ColorStateList colorStateList) {
        if (this.f232o != colorStateList) {
            this.f232o = colorStateList;
            J();
        }
    }

    public void S(int i2) {
        if (this.f224k != i2) {
            this.f224k = i2;
            J();
        }
    }

    public void T(Typeface typeface) {
        if (U(typeface)) {
            J();
        }
    }

    public final boolean U(Typeface typeface) {
        L.a aVar = this.f182E;
        if (aVar != null) {
            aVar.c();
        }
        if (this.f247y == typeface) {
            return false;
        }
        this.f247y = typeface;
        Typeface typefaceB = L.g.b(this.f204a.getContext().getResources().getConfiguration(), typeface);
        this.f246x = typefaceB;
        if (typefaceB == null) {
            typefaceB = this.f247y;
        }
        this.f245w = typefaceB;
        return true;
    }

    public void V(int i2, int i3, int i4, int i5) {
        if (L(this.f216g, i2, i3, i4, i5)) {
            return;
        }
        this.f216g.set(i2, i3, i4, i5);
        this.f198U = true;
    }

    public void W(Rect rect) {
        V(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void X(float f2) {
        if (this.f221i0 != f2) {
            this.f221i0 = f2;
            J();
        }
    }

    public final void Y(float f2) {
        this.f231n0 = f2;
        ViewCompat.postInvalidateOnAnimation(this.f204a);
    }

    public void Z(int i2) {
        if (this.f222j != i2) {
            this.f222j = i2;
            J();
        }
    }

    public void a0(float f2) {
        if (this.f226l != f2) {
            this.f226l = f2;
            J();
        }
    }

    public final void b(boolean z2) {
        StaticLayout staticLayout;
        i(1.0f, z2);
        CharSequence charSequence = this.f185H;
        if (charSequence != null && (staticLayout = this.f225k0) != null) {
            this.f233o0 = TextUtils.ellipsize(charSequence, this.f199V, staticLayout.getWidth(), this.f183F);
        }
        CharSequence charSequence2 = this.f233o0;
        float fI = 0.0f;
        if (charSequence2 != null) {
            this.f227l0 = I(this.f199V, charSequence2);
        } else {
            this.f227l0 = 0.0f;
        }
        int absoluteGravity = GravityCompat.getAbsoluteGravity(this.f224k, this.f186I ? 1 : 0);
        int i2 = absoluteGravity & 112;
        if (i2 == 48) {
            this.f238r = this.f218h.top;
        } else if (i2 != 80) {
            this.f238r = this.f218h.centerY() - ((this.f199V.descent() - this.f199V.ascent()) / 2.0f);
        } else {
            this.f238r = this.f218h.bottom + this.f199V.ascent();
        }
        int i3 = absoluteGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (i3 == 1) {
            this.f242t = this.f218h.centerX() - (this.f227l0 / 2.0f);
        } else if (i3 != 5) {
            this.f242t = this.f218h.left;
        } else {
            this.f242t = this.f218h.right - this.f227l0;
        }
        i(0.0f, z2);
        float height = this.f225k0 != null ? r10.getHeight() : 0.0f;
        StaticLayout staticLayout2 = this.f225k0;
        if (staticLayout2 == null || this.f235p0 <= 1) {
            CharSequence charSequence3 = this.f185H;
            if (charSequence3 != null) {
                fI = I(this.f199V, charSequence3);
            }
        } else {
            fI = staticLayout2.getWidth();
        }
        StaticLayout staticLayout3 = this.f225k0;
        this.f234p = staticLayout3 != null ? staticLayout3.getLineCount() : 0;
        int absoluteGravity2 = GravityCompat.getAbsoluteGravity(this.f222j, this.f186I ? 1 : 0);
        int i4 = absoluteGravity2 & 112;
        if (i4 == 48) {
            this.f236q = this.f216g.top;
        } else if (i4 != 80) {
            this.f236q = this.f216g.centerY() - (height / 2.0f);
        } else {
            this.f236q = (this.f216g.bottom - height) + this.f199V.descent();
        }
        int i5 = absoluteGravity2 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (i5 == 1) {
            this.f240s = this.f216g.centerX() - (fI / 2.0f);
        } else if (i5 != 5) {
            this.f240s = this.f216g.left;
        } else {
            this.f240s = this.f216g.right - fI;
        }
        j();
        d0(this.f206b);
    }

    public final boolean b0(Typeface typeface) {
        L.a aVar = this.f181D;
        if (aVar != null) {
            aVar.c();
        }
        if (this.f179B == typeface) {
            return false;
        }
        this.f179B = typeface;
        Typeface typefaceB = L.g.b(this.f204a.getContext().getResources().getConfiguration(), typeface);
        this.f178A = typefaceB;
        if (typefaceB == null) {
            typefaceB = this.f179B;
        }
        this.f248z = typefaceB;
        return true;
    }

    public final void c() {
        g(this.f206b);
    }

    public void c0(float f2) {
        float fClamp = MathUtils.clamp(f2, 0.0f, 1.0f);
        if (fClamp != this.f206b) {
            this.f206b = fClamp;
            c();
        }
    }

    public final float d(float f2) {
        float f3 = this.f212e;
        return f2 <= f3 ? AbstractC0743a.b(1.0f, 0.0f, this.f210d, f3, f2) : AbstractC0743a.b(0.0f, 1.0f, f3, 1.0f, f2);
    }

    public final void d0(float f2) {
        h(f2);
        boolean z2 = f176t0 && this.f191N != 1.0f;
        this.f188K = z2;
        if (z2) {
            n();
        }
        ViewCompat.postInvalidateOnAnimation(this.f204a);
    }

    public final float e() {
        float f2 = this.f210d;
        return f2 + ((1.0f - f2) * 0.5f);
    }

    public void e0(TimeInterpolator timeInterpolator) {
        this.f201X = timeInterpolator;
        J();
    }

    public final boolean f(CharSequence charSequence) {
        boolean zD = D();
        return this.f187J ? F(charSequence, zD) : zD;
    }

    public final boolean f0(int[] iArr) {
        this.f197T = iArr;
        if (!E()) {
            return false;
        }
        J();
        return true;
    }

    public final void g(float f2) {
        float f3;
        B(f2);
        if (!this.f208c) {
            this.f243u = G(this.f240s, this.f242t, f2, this.f201X);
            this.f244v = G(this.f236q, this.f238r, f2, this.f201X);
            d0(f2);
            f3 = f2;
        } else if (f2 < this.f212e) {
            this.f243u = this.f240s;
            this.f244v = this.f236q;
            d0(0.0f);
            f3 = 0.0f;
        } else {
            this.f243u = this.f242t;
            this.f244v = this.f238r - Math.max(0, this.f214f);
            d0(1.0f);
            f3 = 1.0f;
        }
        TimeInterpolator timeInterpolator = AbstractC0743a.f6835b;
        Q(1.0f - G(0.0f, 1.0f, 1.0f - f2, timeInterpolator));
        Y(G(1.0f, 0.0f, f2, timeInterpolator));
        if (this.f232o != this.f230n) {
            this.f199V.setColor(a(v(), t(), f3));
        } else {
            this.f199V.setColor(t());
        }
        float f4 = this.f219h0;
        float f5 = this.f221i0;
        if (f4 != f5) {
            this.f199V.setLetterSpacing(G(f5, f4, f2, timeInterpolator));
        } else {
            this.f199V.setLetterSpacing(f4);
        }
        this.f193P = G(this.f211d0, this.f203Z, f2, null);
        this.f194Q = G(this.f213e0, this.f205a0, f2, null);
        this.f195R = G(this.f215f0, this.f207b0, f2, null);
        int iA = a(u(this.f217g0), u(this.f209c0), f2);
        this.f196S = iA;
        this.f199V.setShadowLayer(this.f193P, this.f194Q, this.f195R, iA);
        if (this.f208c) {
            this.f199V.setAlpha((int) (d(f2) * this.f199V.getAlpha()));
        }
        ViewCompat.postInvalidateOnAnimation(this.f204a);
    }

    public void g0(CharSequence charSequence) {
        if (charSequence == null || !TextUtils.equals(this.f184G, charSequence)) {
            this.f184G = charSequence;
            this.f185H = null;
            j();
            J();
        }
    }

    public final void h(float f2) {
        i(f2, false);
    }

    public void h0(TimeInterpolator timeInterpolator) {
        this.f202Y = timeInterpolator;
        J();
    }

    public final void i(float f2, boolean z2) {
        float f3;
        float f4;
        Typeface typeface;
        if (this.f184G == null) {
            return;
        }
        float fWidth = this.f218h.width();
        float fWidth2 = this.f216g.width();
        if (C(f2, 1.0f)) {
            f3 = this.f228m;
            f4 = this.f219h0;
            this.f191N = 1.0f;
            typeface = this.f245w;
        } else {
            float f5 = this.f226l;
            float f6 = this.f221i0;
            Typeface typeface2 = this.f248z;
            if (C(f2, 0.0f)) {
                this.f191N = 1.0f;
            } else {
                this.f191N = G(this.f226l, this.f228m, f2, this.f202Y) / this.f226l;
            }
            float f7 = this.f228m / this.f226l;
            fWidth = (z2 || this.f208c || fWidth2 * f7 <= fWidth) ? fWidth2 : Math.min(fWidth / f7, fWidth2);
            f3 = f5;
            f4 = f6;
            typeface = typeface2;
        }
        if (fWidth > 0.0f) {
            boolean z3 = this.f192O != f3;
            boolean z4 = this.f223j0 != f4;
            boolean z5 = this.f180C != typeface;
            StaticLayout staticLayout = this.f225k0;
            boolean z6 = z3 || z4 || (staticLayout != null && (fWidth > ((float) staticLayout.getWidth()) ? 1 : (fWidth == ((float) staticLayout.getWidth()) ? 0 : -1)) != 0) || z5 || this.f198U;
            this.f192O = f3;
            this.f223j0 = f4;
            this.f180C = typeface;
            this.f198U = false;
            this.f199V.setLinearText(this.f191N != 1.0f);
            z = z6;
        }
        if (this.f185H == null || z) {
            this.f199V.setTextSize(this.f192O);
            this.f199V.setTypeface(this.f180C);
            this.f199V.setLetterSpacing(this.f223j0);
            this.f186I = f(this.f184G);
            StaticLayout staticLayoutK = k(j0() ? this.f235p0 : 1, fWidth, this.f186I);
            this.f225k0 = staticLayoutK;
            this.f185H = staticLayoutK.getText();
        }
    }

    public void i0(Typeface typeface) {
        boolean zU = U(typeface);
        boolean zB0 = b0(typeface);
        if (zU || zB0) {
            J();
        }
    }

    public final void j() {
        Bitmap bitmap = this.f189L;
        if (bitmap != null) {
            bitmap.recycle();
            this.f189L = null;
        }
    }

    public final boolean j0() {
        return this.f235p0 > 1 && (!this.f186I || this.f208c) && !this.f188K;
    }

    public final StaticLayout k(int i2, float f2, boolean z2) {
        return (StaticLayout) Preconditions.checkNotNull(g.b(this.f184G, this.f199V, (int) f2).d(this.f183F).g(z2).c(i2 == 1 ? Layout.Alignment.ALIGN_NORMAL : y()).f(false).i(i2).h(this.f237q0, this.f239r0).e(this.f241s0).j(null).a());
    }

    public void l(Canvas canvas) {
        int iSave = canvas.save();
        if (this.f185H == null || this.f220i.width() <= 0.0f || this.f220i.height() <= 0.0f) {
            return;
        }
        this.f199V.setTextSize(this.f192O);
        float f2 = this.f243u;
        float f3 = this.f244v;
        boolean z2 = this.f188K && this.f189L != null;
        float f4 = this.f191N;
        if (f4 != 1.0f && !this.f208c) {
            canvas.scale(f4, f4, f2, f3);
        }
        if (z2) {
            canvas.drawBitmap(this.f189L, f2, f3, this.f190M);
            canvas.restoreToCount(iSave);
            return;
        }
        if (!j0() || (this.f208c && this.f206b <= this.f212e)) {
            canvas.translate(f2, f3);
            this.f225k0.draw(canvas);
        } else {
            m(canvas, this.f243u - this.f225k0.getLineStart(0), f3);
        }
        canvas.restoreToCount(iSave);
    }

    public final void m(Canvas canvas, float f2, float f3) {
        int alpha = this.f199V.getAlpha();
        canvas.translate(f2, f3);
        if (!this.f208c) {
            this.f199V.setAlpha((int) (this.f231n0 * alpha));
            TextPaint textPaint = this.f199V;
            textPaint.setShadowLayer(this.f193P, this.f194Q, this.f195R, C.a.a(this.f196S, textPaint.getAlpha()));
            this.f225k0.draw(canvas);
        }
        if (!this.f208c) {
            this.f199V.setAlpha((int) (this.f229m0 * alpha));
        }
        TextPaint textPaint2 = this.f199V;
        textPaint2.setShadowLayer(this.f193P, this.f194Q, this.f195R, C.a.a(this.f196S, textPaint2.getAlpha()));
        int lineBaseline = this.f225k0.getLineBaseline(0);
        CharSequence charSequence = this.f233o0;
        float f4 = lineBaseline;
        canvas.drawText(charSequence, 0, charSequence.length(), 0.0f, f4, this.f199V);
        this.f199V.setShadowLayer(this.f193P, this.f194Q, this.f195R, this.f196S);
        if (this.f208c) {
            return;
        }
        String strTrim = this.f233o0.toString().trim();
        if (strTrim.endsWith("…")) {
            strTrim = strTrim.substring(0, strTrim.length() - 1);
        }
        String str = strTrim;
        this.f199V.setAlpha(alpha);
        canvas.drawText(str, 0, Math.min(this.f225k0.getLineEnd(0), str.length()), 0.0f, f4, (Paint) this.f199V);
    }

    public final void n() {
        if (this.f189L != null || this.f216g.isEmpty() || TextUtils.isEmpty(this.f185H)) {
            return;
        }
        g(0.0f);
        int width = this.f225k0.getWidth();
        int height = this.f225k0.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        this.f189L = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.f225k0.draw(new Canvas(this.f189L));
        if (this.f190M == null) {
            this.f190M = new Paint(3);
        }
    }

    public void o(RectF rectF, int i2, int i3) {
        this.f186I = f(this.f184G);
        rectF.left = Math.max(r(i2, i3), this.f218h.left);
        rectF.top = this.f218h.top;
        rectF.right = Math.min(s(rectF, i2, i3), this.f218h.right);
        rectF.bottom = this.f218h.top + q();
    }

    public ColorStateList p() {
        return this.f232o;
    }

    public float q() {
        z(this.f200W);
        return -this.f200W.ascent();
    }

    public final float r(int i2, int i3) {
        return (i3 == 17 || (i3 & 7) == 1) ? (i2 / 2.0f) - (this.f227l0 / 2.0f) : ((i3 & GravityCompat.END) == 8388613 || (i3 & 5) == 5) ? this.f186I ? this.f218h.left : this.f218h.right - this.f227l0 : this.f186I ? this.f218h.right - this.f227l0 : this.f218h.left;
    }

    public final float s(RectF rectF, int i2, int i3) {
        if (i3 == 17 || (i3 & 7) == 1) {
            return (i2 / 2.0f) + (this.f227l0 / 2.0f);
        }
        if ((i3 & GravityCompat.END) == 8388613 || (i3 & 5) == 5) {
            return this.f186I ? rectF.left + this.f227l0 : this.f218h.right;
        }
        if (this.f186I) {
            return this.f218h.right;
        }
        return this.f227l0 + rectF.left;
    }

    public int t() {
        return u(this.f232o);
    }

    public final int u(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.f197T;
        return iArr != null ? colorStateList.getColorForState(iArr, 0) : colorStateList.getDefaultColor();
    }

    public final int v() {
        return u(this.f230n);
    }

    public float w() {
        A(this.f200W);
        return -this.f200W.ascent();
    }

    public float x() {
        return this.f206b;
    }

    public final Layout.Alignment y() {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(this.f222j, this.f186I ? 1 : 0) & 7;
        return absoluteGravity != 1 ? absoluteGravity != 5 ? this.f186I ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL : this.f186I ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_CENTER;
    }

    public final void z(TextPaint textPaint) {
        textPaint.setTextSize(this.f228m);
        textPaint.setTypeface(this.f245w);
        textPaint.setLetterSpacing(this.f219h0);
    }
}
