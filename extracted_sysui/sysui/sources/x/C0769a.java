package x;

import L.c;
import M.b;
import O.g;
import O.k;
import O.n;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.button.MaterialButton;
import t.AbstractC0741a;
import t.j;

/* JADX INFO: renamed from: x.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0769a {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final boolean f7014u = true;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final boolean f7015v = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MaterialButton f7016a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public k f7017b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f7018c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f7019d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7020e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f7021f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f7022g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f7023h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public PorterDuff.Mode f7024i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ColorStateList f7025j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ColorStateList f7026k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ColorStateList f7027l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Drawable f7028m;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f7032q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public LayerDrawable f7034s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f7035t;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f7029n = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f7030o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f7031p = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f7033r = true;

    public C0769a(MaterialButton materialButton, k kVar) {
        this.f7016a = materialButton;
        this.f7017b = kVar;
    }

    public void A(boolean z2) {
        this.f7029n = z2;
        J();
    }

    public void B(ColorStateList colorStateList) {
        if (this.f7026k != colorStateList) {
            this.f7026k = colorStateList;
            J();
        }
    }

    public void C(int i2) {
        if (this.f7023h != i2) {
            this.f7023h = i2;
            J();
        }
    }

    public void D(ColorStateList colorStateList) {
        if (this.f7025j != colorStateList) {
            this.f7025j = colorStateList;
            if (f() != null) {
                DrawableCompat.setTintList(f(), this.f7025j);
            }
        }
    }

    public void E(PorterDuff.Mode mode) {
        if (this.f7024i != mode) {
            this.f7024i = mode;
            if (f() == null || this.f7024i == null) {
                return;
            }
            DrawableCompat.setTintMode(f(), this.f7024i);
        }
    }

    public void F(boolean z2) {
        this.f7033r = z2;
    }

    public final void G(int i2, int i3) {
        int paddingStart = ViewCompat.getPaddingStart(this.f7016a);
        int paddingTop = this.f7016a.getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(this.f7016a);
        int paddingBottom = this.f7016a.getPaddingBottom();
        int i4 = this.f7020e;
        int i5 = this.f7021f;
        this.f7021f = i3;
        this.f7020e = i2;
        if (!this.f7030o) {
            H();
        }
        ViewCompat.setPaddingRelative(this.f7016a, paddingStart, (paddingTop + i2) - i4, paddingEnd, (paddingBottom + i3) - i5);
    }

    public final void H() {
        this.f7016a.setInternalBackground(a());
        g gVarF = f();
        if (gVarF != null) {
            gVarF.S(this.f7035t);
            gVarF.setState(this.f7016a.getDrawableState());
        }
    }

    public final void I(k kVar) {
        if (f7015v && !this.f7030o) {
            int paddingStart = ViewCompat.getPaddingStart(this.f7016a);
            int paddingTop = this.f7016a.getPaddingTop();
            int paddingEnd = ViewCompat.getPaddingEnd(this.f7016a);
            int paddingBottom = this.f7016a.getPaddingBottom();
            H();
            ViewCompat.setPaddingRelative(this.f7016a, paddingStart, paddingTop, paddingEnd, paddingBottom);
            return;
        }
        if (f() != null) {
            f().setShapeAppearanceModel(kVar);
        }
        if (n() != null) {
            n().setShapeAppearanceModel(kVar);
        }
        if (e() != null) {
            e().setShapeAppearanceModel(kVar);
        }
    }

    public final void J() {
        g gVarF = f();
        g gVarN = n();
        if (gVarF != null) {
            gVarF.Y(this.f7023h, this.f7026k);
            if (gVarN != null) {
                gVarN.X(this.f7023h, this.f7029n ? C.a.d(this.f7016a, AbstractC0741a.f6511k) : 0);
            }
        }
    }

    public final InsetDrawable K(Drawable drawable) {
        return new InsetDrawable(drawable, this.f7018c, this.f7020e, this.f7019d, this.f7021f);
    }

    public final Drawable a() {
        g gVar = new g(this.f7017b);
        gVar.J(this.f7016a.getContext());
        DrawableCompat.setTintList(gVar, this.f7025j);
        PorterDuff.Mode mode = this.f7024i;
        if (mode != null) {
            DrawableCompat.setTintMode(gVar, mode);
        }
        gVar.Y(this.f7023h, this.f7026k);
        g gVar2 = new g(this.f7017b);
        gVar2.setTint(0);
        gVar2.X(this.f7023h, this.f7029n ? C.a.d(this.f7016a, AbstractC0741a.f6511k) : 0);
        if (f7014u) {
            g gVar3 = new g(this.f7017b);
            this.f7028m = gVar3;
            DrawableCompat.setTint(gVar3, -1);
            RippleDrawable rippleDrawable = new RippleDrawable(b.a(this.f7027l), K(new LayerDrawable(new Drawable[]{gVar2, gVar})), this.f7028m);
            this.f7034s = rippleDrawable;
            return rippleDrawable;
        }
        M.a aVar = new M.a(this.f7017b);
        this.f7028m = aVar;
        DrawableCompat.setTintList(aVar, b.a(this.f7027l));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gVar2, gVar, this.f7028m});
        this.f7034s = layerDrawable;
        return K(layerDrawable);
    }

    public int b() {
        return this.f7022g;
    }

    public int c() {
        return this.f7021f;
    }

    public int d() {
        return this.f7020e;
    }

    public n e() {
        LayerDrawable layerDrawable = this.f7034s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return this.f7034s.getNumberOfLayers() > 2 ? (n) this.f7034s.getDrawable(2) : (n) this.f7034s.getDrawable(1);
    }

    public g f() {
        return g(false);
    }

    public final g g(boolean z2) {
        LayerDrawable layerDrawable = this.f7034s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return f7014u ? (g) ((LayerDrawable) ((InsetDrawable) this.f7034s.getDrawable(0)).getDrawable()).getDrawable(!z2 ? 1 : 0) : (g) this.f7034s.getDrawable(!z2 ? 1 : 0);
    }

    public ColorStateList h() {
        return this.f7027l;
    }

    public k i() {
        return this.f7017b;
    }

    public ColorStateList j() {
        return this.f7026k;
    }

    public int k() {
        return this.f7023h;
    }

    public ColorStateList l() {
        return this.f7025j;
    }

    public PorterDuff.Mode m() {
        return this.f7024i;
    }

    public final g n() {
        return g(true);
    }

    public boolean o() {
        return this.f7030o;
    }

    public boolean p() {
        return this.f7032q;
    }

    public boolean q() {
        return this.f7033r;
    }

    public void r(TypedArray typedArray) {
        this.f7018c = typedArray.getDimensionPixelOffset(j.c2, 0);
        this.f7019d = typedArray.getDimensionPixelOffset(j.d2, 0);
        this.f7020e = typedArray.getDimensionPixelOffset(j.e2, 0);
        this.f7021f = typedArray.getDimensionPixelOffset(j.f2, 0);
        int i2 = j.j2;
        if (typedArray.hasValue(i2)) {
            int dimensionPixelSize = typedArray.getDimensionPixelSize(i2, -1);
            this.f7022g = dimensionPixelSize;
            z(this.f7017b.w(dimensionPixelSize));
            this.f7031p = true;
        }
        this.f7023h = typedArray.getDimensionPixelSize(j.t2, 0);
        this.f7024i = H.n.i(typedArray.getInt(j.i2, -1), PorterDuff.Mode.SRC_IN);
        this.f7025j = c.a(this.f7016a.getContext(), typedArray, j.h2);
        this.f7026k = c.a(this.f7016a.getContext(), typedArray, j.s2);
        this.f7027l = c.a(this.f7016a.getContext(), typedArray, j.r2);
        this.f7032q = typedArray.getBoolean(j.g2, false);
        this.f7035t = typedArray.getDimensionPixelSize(j.k2, 0);
        this.f7033r = typedArray.getBoolean(j.u2, true);
        int paddingStart = ViewCompat.getPaddingStart(this.f7016a);
        int paddingTop = this.f7016a.getPaddingTop();
        int paddingEnd = ViewCompat.getPaddingEnd(this.f7016a);
        int paddingBottom = this.f7016a.getPaddingBottom();
        if (typedArray.hasValue(j.b2)) {
            t();
        } else {
            H();
        }
        ViewCompat.setPaddingRelative(this.f7016a, paddingStart + this.f7018c, paddingTop + this.f7020e, paddingEnd + this.f7019d, paddingBottom + this.f7021f);
    }

    public void s(int i2) {
        if (f() != null) {
            f().setTint(i2);
        }
    }

    public void t() {
        this.f7030o = true;
        this.f7016a.setSupportBackgroundTintList(this.f7025j);
        this.f7016a.setSupportBackgroundTintMode(this.f7024i);
    }

    public void u(boolean z2) {
        this.f7032q = z2;
    }

    public void v(int i2) {
        if (this.f7031p && this.f7022g == i2) {
            return;
        }
        this.f7022g = i2;
        this.f7031p = true;
        z(this.f7017b.w(i2));
    }

    public void w(int i2) {
        G(this.f7020e, i2);
    }

    public void x(int i2) {
        G(i2, this.f7021f);
    }

    public void y(ColorStateList colorStateList) {
        if (this.f7027l != colorStateList) {
            this.f7027l = colorStateList;
            boolean z2 = f7014u;
            if (z2 && (this.f7016a.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.f7016a.getBackground()).setColor(b.a(colorStateList));
            } else {
                if (z2 || !(this.f7016a.getBackground() instanceof M.a)) {
                    return;
                }
                ((M.a) this.f7016a.getBackground()).setTintList(b.a(colorStateList));
            }
        }
    }

    public void z(k kVar) {
        this.f7017b = kVar;
        I(kVar);
    }
}
