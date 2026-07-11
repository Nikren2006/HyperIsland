package com.google.android.material.chip;

import H.i;
import H.k;
import H.n;
import L.c;
import L.d;
import M.b;
import O.g;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.core.text.BidiFormatter;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import t.j;
import u.C0745c;
import y.AbstractC0772a;

/* JADX INFO: loaded from: classes2.dex */
public class a extends g implements TintAwareDrawable, Drawable.Callback, i.b {

    /* JADX INFO: renamed from: P0, reason: collision with root package name */
    public static final int[] f1832P0 = {R.attr.state_enabled};

    /* JADX INFO: renamed from: Q0, reason: collision with root package name */
    public static final ShapeDrawable f1833Q0 = new ShapeDrawable(new OvalShape());

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public boolean f1834A0;

    /* JADX INFO: renamed from: B0, reason: collision with root package name */
    public int f1835B0;

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public int f1836C0;

    /* JADX INFO: renamed from: D0, reason: collision with root package name */
    public ColorFilter f1837D0;

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public PorterDuffColorFilter f1838E0;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public ColorStateList f1839F;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public ColorStateList f1840F0;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public ColorStateList f1841G;

    /* JADX INFO: renamed from: G0, reason: collision with root package name */
    public PorterDuff.Mode f1842G0;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public float f1843H;

    /* JADX INFO: renamed from: H0, reason: collision with root package name */
    public int[] f1844H0;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public float f1845I;

    /* JADX INFO: renamed from: I0, reason: collision with root package name */
    public boolean f1846I0;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public ColorStateList f1847J;

    /* JADX INFO: renamed from: J0, reason: collision with root package name */
    public ColorStateList f1848J0;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public float f1849K;

    /* JADX INFO: renamed from: K0, reason: collision with root package name */
    public WeakReference f1850K0;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public ColorStateList f1851L;

    /* JADX INFO: renamed from: L0, reason: collision with root package name */
    public TextUtils.TruncateAt f1852L0;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public CharSequence f1853M;

    /* JADX INFO: renamed from: M0, reason: collision with root package name */
    public boolean f1854M0;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public boolean f1855N;

    /* JADX INFO: renamed from: N0, reason: collision with root package name */
    public int f1856N0;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public Drawable f1857O;

    /* JADX INFO: renamed from: O0, reason: collision with root package name */
    public boolean f1858O0;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public ColorStateList f1859P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public float f1860Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f1861R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f1862S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public Drawable f1863T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public Drawable f1864U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public ColorStateList f1865V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public float f1866W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public CharSequence f1867X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public boolean f1868Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public boolean f1869Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public Drawable f1870a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public ColorStateList f1871b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public C0745c f1872c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public C0745c f1873d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public float f1874e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public float f1875f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public float f1876g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public float f1877h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public float f1878i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public float f1879j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public float f1880k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public float f1881l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final Context f1882m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final Paint f1883n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final Paint f1884o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public final Paint.FontMetrics f1885p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public final RectF f1886q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public final PointF f1887r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public final Path f1888s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public final i f1889t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public int f1890u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public int f1891v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public int f1892w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public int f1893x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public int f1894y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public int f1895z0;

    /* JADX INFO: renamed from: com.google.android.material.chip.a$a, reason: collision with other inner class name */
    public interface InterfaceC0055a {
        void a();
    }

    public a(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.f1845I = -1.0f;
        this.f1883n0 = new Paint(1);
        this.f1885p0 = new Paint.FontMetrics();
        this.f1886q0 = new RectF();
        this.f1887r0 = new PointF();
        this.f1888s0 = new Path();
        this.f1836C0 = 255;
        this.f1842G0 = PorterDuff.Mode.SRC_IN;
        this.f1850K0 = new WeakReference(null);
        J(context);
        this.f1882m0 = context;
        i iVar = new i(this);
        this.f1889t0 = iVar;
        this.f1853M = "";
        iVar.f().density = context.getResources().getDisplayMetrics().density;
        this.f1884o0 = null;
        int[] iArr = f1832P0;
        setState(iArr);
        f2(iArr);
        this.f1854M0 = true;
        if (b.f406a) {
            f1833Q0.setTint(-1);
        }
    }

    public static boolean h1(int[] iArr, int i2) {
        if (iArr == null) {
            return false;
        }
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    public static boolean l1(d dVar) {
        return (dVar == null || dVar.i() == null || !dVar.i().isStateful()) ? false : true;
    }

    public static boolean m1(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    public static boolean n1(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    public static a p0(Context context, AttributeSet attributeSet, int i2, int i3) {
        a aVar = new a(context, attributeSet, i2, i3);
        aVar.o1(attributeSet, i2, i3);
        return aVar;
    }

    public ColorStateList A0() {
        return this.f1871b0;
    }

    public void A1(int i2) {
        z1(AppCompatResources.getColorStateList(this.f1882m0, i2));
    }

    public void A2(float f2) {
        if (this.f1878i0 != f2) {
            this.f1878i0 = f2;
            invalidateSelf();
            p1();
        }
    }

    public ColorStateList B0() {
        return this.f1841G;
    }

    public void B1(float f2) {
        if (this.f1845I != f2) {
            this.f1845I = f2;
            setShapeAppearanceModel(A().w(f2));
        }
    }

    public void B2(int i2) {
        A2(this.f1882m0.getResources().getDimension(i2));
    }

    public float C0() {
        return this.f1858O0 ? C() : this.f1845I;
    }

    public void C1(int i2) {
        B1(this.f1882m0.getResources().getDimension(i2));
    }

    public void C2(float f2) {
        d dVarC1 = c1();
        if (dVarC1 != null) {
            dVarC1.l(f2);
            this.f1889t0.f().setTextSize(f2);
            a();
        }
    }

    public float D0() {
        return this.f1881l0;
    }

    public void D1(float f2) {
        if (this.f1881l0 != f2) {
            this.f1881l0 = f2;
            invalidateSelf();
            p1();
        }
    }

    public void D2(float f2) {
        if (this.f1877h0 != f2) {
            this.f1877h0 = f2;
            invalidateSelf();
            p1();
        }
    }

    public Drawable E0() {
        Drawable drawable = this.f1857O;
        if (drawable != null) {
            return DrawableCompat.unwrap(drawable);
        }
        return null;
    }

    public void E1(int i2) {
        D1(this.f1882m0.getResources().getDimension(i2));
    }

    public void E2(int i2) {
        D2(this.f1882m0.getResources().getDimension(i2));
    }

    public float F0() {
        return this.f1860Q;
    }

    public void F1(Drawable drawable) {
        Drawable drawableE0 = E0();
        if (drawableE0 != drawable) {
            float fG0 = g0();
            this.f1857O = drawable != null ? DrawableCompat.wrap(drawable).mutate() : null;
            float fG02 = g0();
            K2(drawableE0);
            if (I2()) {
                e0(this.f1857O);
            }
            invalidateSelf();
            if (fG0 != fG02) {
                p1();
            }
        }
    }

    public void F2(boolean z2) {
        if (this.f1846I0 != z2) {
            this.f1846I0 = z2;
            L2();
            onStateChange(getState());
        }
    }

    public ColorStateList G0() {
        return this.f1859P;
    }

    public void G1(int i2) {
        F1(AppCompatResources.getDrawable(this.f1882m0, i2));
    }

    public boolean G2() {
        return this.f1854M0;
    }

    public float H0() {
        return this.f1843H;
    }

    public void H1(float f2) {
        if (this.f1860Q != f2) {
            float fG0 = g0();
            this.f1860Q = f2;
            float fG02 = g0();
            invalidateSelf();
            if (fG0 != fG02) {
                p1();
            }
        }
    }

    public final boolean H2() {
        return this.f1869Z && this.f1870a0 != null && this.f1834A0;
    }

    public float I0() {
        return this.f1874e0;
    }

    public void I1(int i2) {
        H1(this.f1882m0.getResources().getDimension(i2));
    }

    public final boolean I2() {
        return this.f1855N && this.f1857O != null;
    }

    public ColorStateList J0() {
        return this.f1847J;
    }

    public void J1(ColorStateList colorStateList) {
        this.f1861R = true;
        if (this.f1859P != colorStateList) {
            this.f1859P = colorStateList;
            if (I2()) {
                DrawableCompat.setTintList(this.f1857O, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final boolean J2() {
        return this.f1862S && this.f1863T != null;
    }

    public float K0() {
        return this.f1849K;
    }

    public void K1(int i2) {
        J1(AppCompatResources.getColorStateList(this.f1882m0, i2));
    }

    public final void K2(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public Drawable L0() {
        Drawable drawable = this.f1863T;
        if (drawable != null) {
            return DrawableCompat.unwrap(drawable);
        }
        return null;
    }

    public void L1(int i2) {
        M1(this.f1882m0.getResources().getBoolean(i2));
    }

    public final void L2() {
        this.f1848J0 = this.f1846I0 ? b.a(this.f1851L) : null;
    }

    public CharSequence M0() {
        return this.f1867X;
    }

    public void M1(boolean z2) {
        if (this.f1855N != z2) {
            boolean zI2 = I2();
            this.f1855N = z2;
            boolean zI22 = I2();
            if (zI2 != zI22) {
                if (zI22) {
                    e0(this.f1857O);
                } else {
                    K2(this.f1857O);
                }
                invalidateSelf();
                p1();
            }
        }
    }

    public final void M2() {
        this.f1864U = new RippleDrawable(b.a(Z0()), this.f1863T, f1833Q0);
    }

    public float N0() {
        return this.f1880k0;
    }

    public void N1(float f2) {
        if (this.f1843H != f2) {
            this.f1843H = f2;
            invalidateSelf();
            p1();
        }
    }

    public float O0() {
        return this.f1866W;
    }

    public void O1(int i2) {
        N1(this.f1882m0.getResources().getDimension(i2));
    }

    public float P0() {
        return this.f1879j0;
    }

    public void P1(float f2) {
        if (this.f1874e0 != f2) {
            this.f1874e0 = f2;
            invalidateSelf();
            p1();
        }
    }

    public int[] Q0() {
        return this.f1844H0;
    }

    public void Q1(int i2) {
        P1(this.f1882m0.getResources().getDimension(i2));
    }

    public ColorStateList R0() {
        return this.f1865V;
    }

    public void R1(ColorStateList colorStateList) {
        if (this.f1847J != colorStateList) {
            this.f1847J = colorStateList;
            if (this.f1858O0) {
                Z(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void S0(RectF rectF) {
        j0(getBounds(), rectF);
    }

    public void S1(int i2) {
        R1(AppCompatResources.getColorStateList(this.f1882m0, i2));
    }

    public final float T0() {
        Drawable drawable = this.f1834A0 ? this.f1870a0 : this.f1857O;
        float f2 = this.f1860Q;
        if (f2 > 0.0f || drawable == null) {
            return f2;
        }
        float fCeil = (float) Math.ceil(n.c(this.f1882m0, 24));
        return ((float) drawable.getIntrinsicHeight()) <= fCeil ? drawable.getIntrinsicHeight() : fCeil;
    }

    public void T1(float f2) {
        if (this.f1849K != f2) {
            this.f1849K = f2;
            this.f1883n0.setStrokeWidth(f2);
            if (this.f1858O0) {
                super.a0(f2);
            }
            invalidateSelf();
        }
    }

    public final float U0() {
        Drawable drawable = this.f1834A0 ? this.f1870a0 : this.f1857O;
        float f2 = this.f1860Q;
        return (f2 > 0.0f || drawable == null) ? f2 : drawable.getIntrinsicWidth();
    }

    public void U1(int i2) {
        T1(this.f1882m0.getResources().getDimension(i2));
    }

    public TextUtils.TruncateAt V0() {
        return this.f1852L0;
    }

    public final void V1(ColorStateList colorStateList) {
        if (this.f1839F != colorStateList) {
            this.f1839F = colorStateList;
            onStateChange(getState());
        }
    }

    public C0745c W0() {
        return this.f1873d0;
    }

    public void W1(Drawable drawable) {
        Drawable drawableL0 = L0();
        if (drawableL0 != drawable) {
            float fK0 = k0();
            this.f1863T = drawable != null ? DrawableCompat.wrap(drawable).mutate() : null;
            if (b.f406a) {
                M2();
            }
            float fK02 = k0();
            K2(drawableL0);
            if (J2()) {
                e0(this.f1863T);
            }
            invalidateSelf();
            if (fK0 != fK02) {
                p1();
            }
        }
    }

    public float X0() {
        return this.f1876g0;
    }

    public void X1(CharSequence charSequence) {
        if (this.f1867X != charSequence) {
            this.f1867X = BidiFormatter.getInstance().unicodeWrap(charSequence);
            invalidateSelf();
        }
    }

    public float Y0() {
        return this.f1875f0;
    }

    public void Y1(float f2) {
        if (this.f1880k0 != f2) {
            this.f1880k0 = f2;
            invalidateSelf();
            if (J2()) {
                p1();
            }
        }
    }

    public ColorStateList Z0() {
        return this.f1851L;
    }

    public void Z1(int i2) {
        Y1(this.f1882m0.getResources().getDimension(i2));
    }

    @Override // H.i.b
    public void a() {
        p1();
        invalidateSelf();
    }

    public C0745c a1() {
        return this.f1872c0;
    }

    public void a2(int i2) {
        W1(AppCompatResources.getDrawable(this.f1882m0, i2));
    }

    public CharSequence b1() {
        return this.f1853M;
    }

    public void b2(float f2) {
        if (this.f1866W != f2) {
            this.f1866W = f2;
            invalidateSelf();
            if (J2()) {
                p1();
            }
        }
    }

    public d c1() {
        return this.f1889t0.e();
    }

    public void c2(int i2) {
        b2(this.f1882m0.getResources().getDimension(i2));
    }

    public float d1() {
        return this.f1878i0;
    }

    public void d2(float f2) {
        if (this.f1879j0 != f2) {
            this.f1879j0 = f2;
            invalidateSelf();
            if (J2()) {
                p1();
            }
        }
    }

    @Override // O.g, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int i2 = this.f1836C0;
        int iA = i2 < 255 ? AbstractC0772a.a(canvas, bounds.left, bounds.top, bounds.right, bounds.bottom, i2) : 0;
        u0(canvas, bounds);
        r0(canvas, bounds);
        if (this.f1858O0) {
            super.draw(canvas);
        }
        t0(canvas, bounds);
        w0(canvas, bounds);
        s0(canvas, bounds);
        q0(canvas, bounds);
        if (this.f1854M0) {
            y0(canvas, bounds);
        }
        v0(canvas, bounds);
        x0(canvas, bounds);
        if (this.f1836C0 < 255) {
            canvas.restoreToCount(iA);
        }
    }

    public final void e0(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        DrawableCompat.setLayoutDirection(drawable, DrawableCompat.getLayoutDirection(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.f1863T) {
            if (drawable.isStateful()) {
                drawable.setState(Q0());
            }
            DrawableCompat.setTintList(drawable, this.f1865V);
            return;
        }
        Drawable drawable2 = this.f1857O;
        if (drawable == drawable2 && this.f1861R) {
            DrawableCompat.setTintList(drawable2, this.f1859P);
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
    }

    public float e1() {
        return this.f1877h0;
    }

    public void e2(int i2) {
        d2(this.f1882m0.getResources().getDimension(i2));
    }

    public final void f0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (I2() || H2()) {
            float f2 = this.f1874e0 + this.f1875f0;
            float fU0 = U0();
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                float f3 = rect.left + f2;
                rectF.left = f3;
                rectF.right = f3 + fU0;
            } else {
                float f4 = rect.right - f2;
                rectF.right = f4;
                rectF.left = f4 - fU0;
            }
            float fT0 = T0();
            float fExactCenterY = rect.exactCenterY() - (fT0 / 2.0f);
            rectF.top = fExactCenterY;
            rectF.bottom = fExactCenterY + fT0;
        }
    }

    public final ColorFilter f1() {
        ColorFilter colorFilter = this.f1837D0;
        return colorFilter != null ? colorFilter : this.f1838E0;
    }

    public boolean f2(int[] iArr) {
        if (Arrays.equals(this.f1844H0, iArr)) {
            return false;
        }
        this.f1844H0 = iArr;
        if (J2()) {
            return q1(getState(), iArr);
        }
        return false;
    }

    public float g0() {
        if (I2() || H2()) {
            return this.f1875f0 + U0() + this.f1876g0;
        }
        return 0.0f;
    }

    public boolean g1() {
        return this.f1846I0;
    }

    public void g2(ColorStateList colorStateList) {
        if (this.f1865V != colorStateList) {
            this.f1865V = colorStateList;
            if (J2()) {
                DrawableCompat.setTintList(this.f1863T, colorStateList);
            }
            onStateChange(getState());
        }
    }

    @Override // O.g, android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f1836C0;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.f1837D0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.f1843H;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round(this.f1874e0 + g0() + this.f1877h0 + this.f1889t0.g(b1().toString()) + this.f1878i0 + k0() + this.f1881l0), this.f1856N0);
    }

    @Override // O.g, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // O.g, android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        if (this.f1858O0) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.f1845I);
        } else {
            outline.setRoundRect(bounds, this.f1845I);
        }
        outline.setAlpha(getAlpha() / 255.0f);
    }

    public final void h0(Rect rect, RectF rectF) {
        rectF.set(rect);
        if (J2()) {
            float f2 = this.f1881l0 + this.f1880k0 + this.f1866W + this.f1879j0 + this.f1878i0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.right = rect.right - f2;
            } else {
                rectF.left = rect.left + f2;
            }
        }
    }

    public void h2(int i2) {
        g2(AppCompatResources.getColorStateList(this.f1882m0, i2));
    }

    public final void i0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (J2()) {
            float f2 = this.f1881l0 + this.f1880k0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                float f3 = rect.right - f2;
                rectF.right = f3;
                rectF.left = f3 - this.f1866W;
            } else {
                float f4 = rect.left + f2;
                rectF.left = f4;
                rectF.right = f4 + this.f1866W;
            }
            float fExactCenterY = rect.exactCenterY();
            float f5 = this.f1866W;
            float f6 = fExactCenterY - (f5 / 2.0f);
            rectF.top = f6;
            rectF.bottom = f6 + f5;
        }
    }

    public boolean i1() {
        return this.f1868Y;
    }

    public void i2(boolean z2) {
        if (this.f1862S != z2) {
            boolean zJ2 = J2();
            this.f1862S = z2;
            boolean zJ22 = J2();
            if (zJ2 != zJ22) {
                if (zJ22) {
                    e0(this.f1863T);
                } else {
                    K2(this.f1863T);
                }
                invalidateSelf();
                p1();
            }
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // O.g, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return m1(this.f1839F) || m1(this.f1841G) || m1(this.f1847J) || (this.f1846I0 && m1(this.f1848J0)) || l1(this.f1889t0.e()) || o0() || n1(this.f1857O) || n1(this.f1870a0) || m1(this.f1840F0);
    }

    public final void j0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (J2()) {
            float f2 = this.f1881l0 + this.f1880k0 + this.f1866W + this.f1879j0 + this.f1878i0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                float f3 = rect.right;
                rectF.right = f3;
                rectF.left = f3 - f2;
            } else {
                int i2 = rect.left;
                rectF.left = i2;
                rectF.right = i2 + f2;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    public boolean j1() {
        return n1(this.f1863T);
    }

    public void j2(InterfaceC0055a interfaceC0055a) {
        this.f1850K0 = new WeakReference(interfaceC0055a);
    }

    public float k0() {
        if (J2()) {
            return this.f1879j0 + this.f1866W + this.f1880k0;
        }
        return 0.0f;
    }

    public boolean k1() {
        return this.f1862S;
    }

    public void k2(TextUtils.TruncateAt truncateAt) {
        this.f1852L0 = truncateAt;
    }

    public final void l0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.f1853M != null) {
            float fG0 = this.f1874e0 + g0() + this.f1877h0;
            float fK0 = this.f1881l0 + k0() + this.f1878i0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                rectF.left = rect.left + fG0;
                rectF.right = rect.right - fK0;
            } else {
                rectF.left = rect.left + fK0;
                rectF.right = rect.right - fG0;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    public void l2(C0745c c0745c) {
        this.f1873d0 = c0745c;
    }

    public final float m0() {
        this.f1889t0.f().getFontMetrics(this.f1885p0);
        Paint.FontMetrics fontMetrics = this.f1885p0;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    public void m2(int i2) {
        l2(C0745c.c(this.f1882m0, i2));
    }

    public Paint.Align n0(Rect rect, PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.f1853M != null) {
            float fG0 = this.f1874e0 + g0() + this.f1877h0;
            if (DrawableCompat.getLayoutDirection(this) == 0) {
                pointF.x = rect.left + fG0;
            } else {
                pointF.x = rect.right - fG0;
                align = Paint.Align.RIGHT;
            }
            pointF.y = rect.centerY() - m0();
        }
        return align;
    }

    public void n2(float f2) {
        if (this.f1876g0 != f2) {
            float fG0 = g0();
            this.f1876g0 = f2;
            float fG02 = g0();
            invalidateSelf();
            if (fG0 != fG02) {
                p1();
            }
        }
    }

    public final boolean o0() {
        return this.f1869Z && this.f1870a0 != null && this.f1868Y;
    }

    public final void o1(AttributeSet attributeSet, int i2, int i3) {
        TypedArray typedArrayI = k.i(this.f1882m0, attributeSet, j.f6754b0, i2, i3, new int[0]);
        this.f1858O0 = typedArrayI.hasValue(j.f6723M0);
        V1(c.a(this.f1882m0, typedArrayI, j.f6826z0));
        z1(c.a(this.f1882m0, typedArrayI, j.f6787m0));
        N1(typedArrayI.getDimension(j.f6811u0, 0.0f));
        int i4 = j.f6790n0;
        if (typedArrayI.hasValue(i4)) {
            B1(typedArrayI.getDimension(i4, 0.0f));
        }
        R1(c.a(this.f1882m0, typedArrayI, j.f6820x0));
        T1(typedArrayI.getDimension(j.f6823y0, 0.0f));
        s2(c.a(this.f1882m0, typedArrayI, j.f6721L0));
        x2(typedArrayI.getText(j.f6769g0));
        d dVarF = c.f(this.f1882m0, typedArrayI, j.f6757c0);
        dVarF.l(typedArrayI.getDimension(j.f6760d0, dVarF.j()));
        y2(dVarF);
        int i5 = typedArrayI.getInt(j.f6763e0, 0);
        if (i5 == 1) {
            k2(TextUtils.TruncateAt.START);
        } else if (i5 == 2) {
            k2(TextUtils.TruncateAt.MIDDLE);
        } else if (i5 == 3) {
            k2(TextUtils.TruncateAt.END);
        }
        M1(typedArrayI.getBoolean(j.f6808t0, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            M1(typedArrayI.getBoolean(j.f6799q0, false));
        }
        F1(c.d(this.f1882m0, typedArrayI, j.f6796p0));
        int i6 = j.f6805s0;
        if (typedArrayI.hasValue(i6)) {
            J1(c.a(this.f1882m0, typedArrayI, i6));
        }
        H1(typedArrayI.getDimension(j.f6802r0, -1.0f));
        i2(typedArrayI.getBoolean(j.f6711G0, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            i2(typedArrayI.getBoolean(j.f6697B0, false));
        }
        W1(c.d(this.f1882m0, typedArrayI, j.f6694A0));
        g2(c.a(this.f1882m0, typedArrayI, j.f6709F0));
        b2(typedArrayI.getDimension(j.f6703D0, 0.0f));
        r1(typedArrayI.getBoolean(j.f6772h0, false));
        y1(typedArrayI.getBoolean(j.f6784l0, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            y1(typedArrayI.getBoolean(j.f6778j0, false));
        }
        t1(c.d(this.f1882m0, typedArrayI, j.f6775i0));
        int i7 = j.f6781k0;
        if (typedArrayI.hasValue(i7)) {
            v1(c.a(this.f1882m0, typedArrayI, i7));
        }
        v2(C0745c.b(this.f1882m0, typedArrayI, j.f6725N0));
        l2(C0745c.b(this.f1882m0, typedArrayI, j.f6715I0));
        P1(typedArrayI.getDimension(j.f6817w0, 0.0f));
        p2(typedArrayI.getDimension(j.f6719K0, 0.0f));
        n2(typedArrayI.getDimension(j.f6717J0, 0.0f));
        D2(typedArrayI.getDimension(j.f6729P0, 0.0f));
        A2(typedArrayI.getDimension(j.f6727O0, 0.0f));
        d2(typedArrayI.getDimension(j.f6706E0, 0.0f));
        Y1(typedArrayI.getDimension(j.f6700C0, 0.0f));
        D1(typedArrayI.getDimension(j.f6793o0, 0.0f));
        r2(typedArrayI.getDimensionPixelSize(j.f6766f0, Integer.MAX_VALUE));
        typedArrayI.recycle();
    }

    public void o2(int i2) {
        n2(this.f1882m0.getResources().getDimension(i2));
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i2) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i2);
        if (I2()) {
            zOnLayoutDirectionChanged |= DrawableCompat.setLayoutDirection(this.f1857O, i2);
        }
        if (H2()) {
            zOnLayoutDirectionChanged |= DrawableCompat.setLayoutDirection(this.f1870a0, i2);
        }
        if (J2()) {
            zOnLayoutDirectionChanged |= DrawableCompat.setLayoutDirection(this.f1863T, i2);
        }
        if (!zOnLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i2) {
        boolean zOnLevelChange = super.onLevelChange(i2);
        if (I2()) {
            zOnLevelChange |= this.f1857O.setLevel(i2);
        }
        if (H2()) {
            zOnLevelChange |= this.f1870a0.setLevel(i2);
        }
        if (J2()) {
            zOnLevelChange |= this.f1863T.setLevel(i2);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    @Override // O.g, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        if (this.f1858O0) {
            super.onStateChange(iArr);
        }
        return q1(iArr, Q0());
    }

    public void p1() {
        InterfaceC0055a interfaceC0055a = (InterfaceC0055a) this.f1850K0.get();
        if (interfaceC0055a != null) {
            interfaceC0055a.a();
        }
    }

    public void p2(float f2) {
        if (this.f1875f0 != f2) {
            float fG0 = g0();
            this.f1875f0 = f2;
            float fG02 = g0();
            invalidateSelf();
            if (fG0 != fG02) {
                p1();
            }
        }
    }

    public final void q0(Canvas canvas, Rect rect) {
        if (H2()) {
            f0(rect, this.f1886q0);
            RectF rectF = this.f1886q0;
            float f2 = rectF.left;
            float f3 = rectF.top;
            canvas.translate(f2, f3);
            this.f1870a0.setBounds(0, 0, (int) this.f1886q0.width(), (int) this.f1886q0.height());
            this.f1870a0.draw(canvas);
            canvas.translate(-f2, -f3);
        }
    }

    public final boolean q1(int[] iArr, int[] iArr2) {
        boolean z2;
        boolean zOnStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList = this.f1839F;
        int iL = l(colorStateList != null ? colorStateList.getColorForState(iArr, this.f1890u0) : 0);
        boolean state = true;
        if (this.f1890u0 != iL) {
            this.f1890u0 = iL;
            zOnStateChange = true;
        }
        ColorStateList colorStateList2 = this.f1841G;
        int iL2 = l(colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.f1891v0) : 0);
        if (this.f1891v0 != iL2) {
            this.f1891v0 = iL2;
            zOnStateChange = true;
        }
        int i2 = C.a.i(iL, iL2);
        if ((this.f1892w0 != i2) | (v() == null)) {
            this.f1892w0 = i2;
            T(ColorStateList.valueOf(i2));
            zOnStateChange = true;
        }
        ColorStateList colorStateList3 = this.f1847J;
        int colorForState = colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.f1893x0) : 0;
        if (this.f1893x0 != colorForState) {
            this.f1893x0 = colorForState;
            zOnStateChange = true;
        }
        int colorForState2 = (this.f1848J0 == null || !b.b(iArr)) ? 0 : this.f1848J0.getColorForState(iArr, this.f1894y0);
        if (this.f1894y0 != colorForState2) {
            this.f1894y0 = colorForState2;
            if (this.f1846I0) {
                zOnStateChange = true;
            }
        }
        int colorForState3 = (this.f1889t0.e() == null || this.f1889t0.e().i() == null) ? 0 : this.f1889t0.e().i().getColorForState(iArr, this.f1895z0);
        if (this.f1895z0 != colorForState3) {
            this.f1895z0 = colorForState3;
            zOnStateChange = true;
        }
        boolean z3 = h1(getState(), R.attr.state_checked) && this.f1868Y;
        if (this.f1834A0 == z3 || this.f1870a0 == null) {
            z2 = false;
        } else {
            float fG0 = g0();
            this.f1834A0 = z3;
            if (fG0 != g0()) {
                zOnStateChange = true;
                z2 = true;
            } else {
                z2 = false;
                zOnStateChange = true;
            }
        }
        ColorStateList colorStateList4 = this.f1840F0;
        int colorForState4 = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.f1835B0) : 0;
        if (this.f1835B0 != colorForState4) {
            this.f1835B0 = colorForState4;
            this.f1838E0 = E.a.j(this, this.f1840F0, this.f1842G0);
        } else {
            state = zOnStateChange;
        }
        if (n1(this.f1857O)) {
            state |= this.f1857O.setState(iArr);
        }
        if (n1(this.f1870a0)) {
            state |= this.f1870a0.setState(iArr);
        }
        if (n1(this.f1863T)) {
            int[] iArr3 = new int[iArr.length + iArr2.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            state |= this.f1863T.setState(iArr3);
        }
        if (b.f406a && n1(this.f1864U)) {
            state |= this.f1864U.setState(iArr2);
        }
        if (state) {
            invalidateSelf();
        }
        if (z2) {
            p1();
        }
        return state;
    }

    public void q2(int i2) {
        p2(this.f1882m0.getResources().getDimension(i2));
    }

    public final void r0(Canvas canvas, Rect rect) {
        if (this.f1858O0) {
            return;
        }
        this.f1883n0.setColor(this.f1891v0);
        this.f1883n0.setStyle(Paint.Style.FILL);
        this.f1883n0.setColorFilter(f1());
        this.f1886q0.set(rect);
        canvas.drawRoundRect(this.f1886q0, C0(), C0(), this.f1883n0);
    }

    public void r1(boolean z2) {
        if (this.f1868Y != z2) {
            this.f1868Y = z2;
            float fG0 = g0();
            if (!z2 && this.f1834A0) {
                this.f1834A0 = false;
            }
            float fG02 = g0();
            invalidateSelf();
            if (fG0 != fG02) {
                p1();
            }
        }
    }

    public void r2(int i2) {
        this.f1856N0 = i2;
    }

    public final void s0(Canvas canvas, Rect rect) {
        if (I2()) {
            f0(rect, this.f1886q0);
            RectF rectF = this.f1886q0;
            float f2 = rectF.left;
            float f3 = rectF.top;
            canvas.translate(f2, f3);
            this.f1857O.setBounds(0, 0, (int) this.f1886q0.width(), (int) this.f1886q0.height());
            this.f1857O.draw(canvas);
            canvas.translate(-f2, -f3);
        }
    }

    public void s1(int i2) {
        r1(this.f1882m0.getResources().getBoolean(i2));
    }

    public void s2(ColorStateList colorStateList) {
        if (this.f1851L != colorStateList) {
            this.f1851L = colorStateList;
            L2();
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j2);
        }
    }

    @Override // O.g, android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (this.f1836C0 != i2) {
            this.f1836C0 = i2;
            invalidateSelf();
        }
    }

    @Override // O.g, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.f1837D0 != colorFilter) {
            this.f1837D0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // O.g, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList) {
        if (this.f1840F0 != colorStateList) {
            this.f1840F0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // O.g, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.f1842G0 != mode) {
            this.f1842G0 = mode;
            this.f1838E0 = E.a.j(this, this.f1840F0, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z2, boolean z3) {
        boolean visible = super.setVisible(z2, z3);
        if (I2()) {
            visible |= this.f1857O.setVisible(z2, z3);
        }
        if (H2()) {
            visible |= this.f1870a0.setVisible(z2, z3);
        }
        if (J2()) {
            visible |= this.f1863T.setVisible(z2, z3);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    public final void t0(Canvas canvas, Rect rect) {
        if (this.f1849K <= 0.0f || this.f1858O0) {
            return;
        }
        this.f1883n0.setColor(this.f1893x0);
        this.f1883n0.setStyle(Paint.Style.STROKE);
        if (!this.f1858O0) {
            this.f1883n0.setColorFilter(f1());
        }
        RectF rectF = this.f1886q0;
        float f2 = rect.left;
        float f3 = this.f1849K;
        rectF.set(f2 + (f3 / 2.0f), rect.top + (f3 / 2.0f), rect.right - (f3 / 2.0f), rect.bottom - (f3 / 2.0f));
        float f4 = this.f1845I - (this.f1849K / 2.0f);
        canvas.drawRoundRect(this.f1886q0, f4, f4, this.f1883n0);
    }

    public void t1(Drawable drawable) {
        if (this.f1870a0 != drawable) {
            float fG0 = g0();
            this.f1870a0 = drawable;
            float fG02 = g0();
            K2(this.f1870a0);
            e0(this.f1870a0);
            invalidateSelf();
            if (fG0 != fG02) {
                p1();
            }
        }
    }

    public void t2(int i2) {
        s2(AppCompatResources.getColorStateList(this.f1882m0, i2));
    }

    public final void u0(Canvas canvas, Rect rect) {
        if (this.f1858O0) {
            return;
        }
        this.f1883n0.setColor(this.f1890u0);
        this.f1883n0.setStyle(Paint.Style.FILL);
        this.f1886q0.set(rect);
        canvas.drawRoundRect(this.f1886q0, C0(), C0(), this.f1883n0);
    }

    public void u1(int i2) {
        t1(AppCompatResources.getDrawable(this.f1882m0, i2));
    }

    public void u2(boolean z2) {
        this.f1854M0 = z2;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final void v0(Canvas canvas, Rect rect) {
        if (J2()) {
            i0(rect, this.f1886q0);
            RectF rectF = this.f1886q0;
            float f2 = rectF.left;
            float f3 = rectF.top;
            canvas.translate(f2, f3);
            this.f1863T.setBounds(0, 0, (int) this.f1886q0.width(), (int) this.f1886q0.height());
            if (b.f406a) {
                this.f1864U.setBounds(this.f1863T.getBounds());
                this.f1864U.jumpToCurrentState();
                this.f1864U.draw(canvas);
            } else {
                this.f1863T.draw(canvas);
            }
            canvas.translate(-f2, -f3);
        }
    }

    public void v1(ColorStateList colorStateList) {
        if (this.f1871b0 != colorStateList) {
            this.f1871b0 = colorStateList;
            if (o0()) {
                DrawableCompat.setTintList(this.f1870a0, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void v2(C0745c c0745c) {
        this.f1872c0 = c0745c;
    }

    public final void w0(Canvas canvas, Rect rect) {
        this.f1883n0.setColor(this.f1894y0);
        this.f1883n0.setStyle(Paint.Style.FILL);
        this.f1886q0.set(rect);
        if (!this.f1858O0) {
            canvas.drawRoundRect(this.f1886q0, C0(), C0(), this.f1883n0);
        } else {
            h(new RectF(rect), this.f1888s0);
            super.q(canvas, this.f1883n0, this.f1888s0, s());
        }
    }

    public void w1(int i2) {
        v1(AppCompatResources.getColorStateList(this.f1882m0, i2));
    }

    public void w2(int i2) {
        v2(C0745c.c(this.f1882m0, i2));
    }

    public final void x0(Canvas canvas, Rect rect) {
        Paint paint = this.f1884o0;
        if (paint != null) {
            paint.setColor(ColorUtils.setAlphaComponent(ViewCompat.MEASURED_STATE_MASK, 127));
            canvas.drawRect(rect, this.f1884o0);
            if (I2() || H2()) {
                f0(rect, this.f1886q0);
                canvas.drawRect(this.f1886q0, this.f1884o0);
            }
            if (this.f1853M != null) {
                canvas.drawLine(rect.left, rect.exactCenterY(), rect.right, rect.exactCenterY(), this.f1884o0);
            }
            if (J2()) {
                i0(rect, this.f1886q0);
                canvas.drawRect(this.f1886q0, this.f1884o0);
            }
            this.f1884o0.setColor(ColorUtils.setAlphaComponent(-65536, 127));
            h0(rect, this.f1886q0);
            canvas.drawRect(this.f1886q0, this.f1884o0);
            this.f1884o0.setColor(ColorUtils.setAlphaComponent(-16711936, 127));
            j0(rect, this.f1886q0);
            canvas.drawRect(this.f1886q0, this.f1884o0);
        }
    }

    public void x1(int i2) {
        y1(this.f1882m0.getResources().getBoolean(i2));
    }

    public void x2(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (TextUtils.equals(this.f1853M, charSequence)) {
            return;
        }
        this.f1853M = charSequence;
        this.f1889t0.k(true);
        invalidateSelf();
        p1();
    }

    public final void y0(Canvas canvas, Rect rect) {
        if (this.f1853M != null) {
            Paint.Align alignN0 = n0(rect, this.f1887r0);
            l0(rect, this.f1886q0);
            if (this.f1889t0.e() != null) {
                this.f1889t0.f().drawableState = getState();
                this.f1889t0.l(this.f1882m0);
            }
            this.f1889t0.f().setTextAlign(alignN0);
            int iSave = 0;
            boolean z2 = Math.round(this.f1889t0.g(b1().toString())) > Math.round(this.f1886q0.width());
            if (z2) {
                iSave = canvas.save();
                canvas.clipRect(this.f1886q0);
            }
            CharSequence charSequenceEllipsize = this.f1853M;
            if (z2 && this.f1852L0 != null) {
                charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.f1889t0.f(), this.f1886q0.width(), this.f1852L0);
            }
            CharSequence charSequence = charSequenceEllipsize;
            int length = charSequence.length();
            PointF pointF = this.f1887r0;
            canvas.drawText(charSequence, 0, length, pointF.x, pointF.y, this.f1889t0.f());
            if (z2) {
                canvas.restoreToCount(iSave);
            }
        }
    }

    public void y1(boolean z2) {
        if (this.f1869Z != z2) {
            boolean zH2 = H2();
            this.f1869Z = z2;
            boolean zH22 = H2();
            if (zH2 != zH22) {
                if (zH22) {
                    e0(this.f1870a0);
                } else {
                    K2(this.f1870a0);
                }
                invalidateSelf();
                p1();
            }
        }
    }

    public void y2(d dVar) {
        this.f1889t0.j(dVar, this.f1882m0);
    }

    public Drawable z0() {
        return this.f1870a0;
    }

    public void z1(ColorStateList colorStateList) {
        if (this.f1841G != colorStateList) {
            this.f1841G = colorStateList;
            onStateChange(getState());
        }
    }

    public void z2(int i2) {
        y2(new d(this.f1882m0, i2));
    }
}
