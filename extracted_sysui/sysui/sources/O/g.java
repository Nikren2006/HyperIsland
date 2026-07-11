package O;

import O.k;
import O.l;
import O.m;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.core.util.ObjectsCompat;
import java.util.BitSet;
import t.AbstractC0741a;

/* JADX INFO: loaded from: classes2.dex */
public class g extends Drawable implements TintAwareDrawable, n {

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final String f452D = "g";

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final Paint f453E;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final RectF f454A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public boolean f455B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c f456a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m.g[] f457b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final m.g[] f458c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final BitSet f459d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f460e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Matrix f461f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Path f462g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Path f463h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final RectF f464i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final RectF f465j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Region f466k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Region f467l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public k f468m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Paint f469n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Paint f470o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final N.a f471p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final l.b f472q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final l f473r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public PorterDuffColorFilter f474s;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public PorterDuffColorFilter f475x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f476y;

    public class a implements l.b {
        public a() {
        }

        @Override // O.l.b
        public void a(m mVar, Matrix matrix, int i2) {
            g.this.f459d.set(i2, mVar.e());
            g.this.f457b[i2] = mVar.f(matrix);
        }

        @Override // O.l.b
        public void b(m mVar, Matrix matrix, int i2) {
            g.this.f459d.set(i2 + 4, mVar.e());
            g.this.f458c[i2] = mVar.f(matrix);
        }
    }

    public class b implements k.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ float f478a;

        public b(float f2) {
            this.f478a = f2;
        }

        @Override // O.k.c
        public O.c a(O.c cVar) {
            return cVar instanceof i ? cVar : new O.b(this.f478a, cVar);
        }
    }

    static {
        Paint paint = new Paint(1);
        f453E = paint;
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public g() {
        this(new k());
    }

    public static int O(int i2, int i3) {
        return (i2 * (i3 + (i3 >>> 7))) >>> 8;
    }

    public static g m(Context context, float f2, ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(C.a.c(context, AbstractC0741a.f6511k, g.class.getSimpleName()));
        }
        g gVar = new g();
        gVar.J(context);
        gVar.T(colorStateList);
        gVar.S(f2);
        return gVar;
    }

    public k A() {
        return this.f456a.f480a;
    }

    public final float B() {
        if (I()) {
            return this.f470o.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    public float C() {
        return this.f456a.f480a.r().a(s());
    }

    public float D() {
        return this.f456a.f480a.t().a(s());
    }

    public float E() {
        return this.f456a.f495p;
    }

    public float F() {
        return u() + E();
    }

    public final boolean G() {
        c cVar = this.f456a;
        int i2 = cVar.f496q;
        return i2 != 1 && cVar.f497r > 0 && (i2 == 2 || Q());
    }

    public final boolean H() {
        Paint.Style style = this.f456a.f501v;
        return style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.FILL;
    }

    public final boolean I() {
        Paint.Style style = this.f456a.f501v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.f470o.getStrokeWidth() > 0.0f;
    }

    public void J(Context context) {
        this.f456a.f481b = new F.a(context);
        d0();
    }

    public final void K() {
        super.invalidateSelf();
    }

    public boolean L() {
        F.a aVar = this.f456a.f481b;
        return aVar != null && aVar.d();
    }

    public boolean M() {
        return this.f456a.f480a.u(s());
    }

    public final void N(Canvas canvas) {
        if (G()) {
            canvas.save();
            P(canvas);
            if (!this.f455B) {
                n(canvas);
                canvas.restore();
                return;
            }
            int iWidth = (int) (this.f454A.width() - getBounds().width());
            int iHeight = (int) (this.f454A.height() - getBounds().height());
            if (iWidth < 0 || iHeight < 0) {
                throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(((int) this.f454A.width()) + (this.f456a.f497r * 2) + iWidth, ((int) this.f454A.height()) + (this.f456a.f497r * 2) + iHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap);
            float f2 = (getBounds().left - this.f456a.f497r) - iWidth;
            float f3 = (getBounds().top - this.f456a.f497r) - iHeight;
            canvas2.translate(-f2, -f3);
            n(canvas2);
            canvas.drawBitmap(bitmapCreateBitmap, f2, f3, (Paint) null);
            bitmapCreateBitmap.recycle();
            canvas.restore();
        }
    }

    public final void P(Canvas canvas) {
        canvas.translate(y(), z());
    }

    public boolean Q() {
        if (M()) {
            return false;
        }
        this.f462g.isConvex();
        return false;
    }

    public void R(O.c cVar) {
        setShapeAppearanceModel(this.f456a.f480a.x(cVar));
    }

    public void S(float f2) {
        c cVar = this.f456a;
        if (cVar.f494o != f2) {
            cVar.f494o = f2;
            d0();
        }
    }

    public void T(ColorStateList colorStateList) {
        c cVar = this.f456a;
        if (cVar.f483d != colorStateList) {
            cVar.f483d = colorStateList;
            onStateChange(getState());
        }
    }

    public void U(float f2) {
        c cVar = this.f456a;
        if (cVar.f490k != f2) {
            cVar.f490k = f2;
            this.f460e = true;
            invalidateSelf();
        }
    }

    public void V(int i2, int i3, int i4, int i5) {
        c cVar = this.f456a;
        if (cVar.f488i == null) {
            cVar.f488i = new Rect();
        }
        this.f456a.f488i.set(i2, i3, i4, i5);
        invalidateSelf();
    }

    public void W(float f2) {
        c cVar = this.f456a;
        if (cVar.f493n != f2) {
            cVar.f493n = f2;
            d0();
        }
    }

    public void X(float f2, int i2) {
        a0(f2);
        Z(ColorStateList.valueOf(i2));
    }

    public void Y(float f2, ColorStateList colorStateList) {
        a0(f2);
        Z(colorStateList);
    }

    public void Z(ColorStateList colorStateList) {
        c cVar = this.f456a;
        if (cVar.f484e != colorStateList) {
            cVar.f484e = colorStateList;
            onStateChange(getState());
        }
    }

    public void a0(float f2) {
        this.f456a.f491l = f2;
        invalidateSelf();
    }

    public final boolean b0(int[] iArr) {
        boolean z2;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.f456a.f483d == null || color2 == (colorForState2 = this.f456a.f483d.getColorForState(iArr, (color2 = this.f469n.getColor())))) {
            z2 = false;
        } else {
            this.f469n.setColor(colorForState2);
            z2 = true;
        }
        if (this.f456a.f484e == null || color == (colorForState = this.f456a.f484e.getColorForState(iArr, (color = this.f470o.getColor())))) {
            return z2;
        }
        this.f470o.setColor(colorForState);
        return true;
    }

    public final boolean c0() {
        PorterDuffColorFilter porterDuffColorFilter = this.f474s;
        PorterDuffColorFilter porterDuffColorFilter2 = this.f475x;
        c cVar = this.f456a;
        this.f474s = k(cVar.f486g, cVar.f487h, this.f469n, true);
        c cVar2 = this.f456a;
        this.f475x = k(cVar2.f485f, cVar2.f487h, this.f470o, false);
        c cVar3 = this.f456a;
        if (cVar3.f500u) {
            this.f471p.d(cVar3.f486g.getColorForState(getState(), 0));
        }
        return (ObjectsCompat.equals(porterDuffColorFilter, this.f474s) && ObjectsCompat.equals(porterDuffColorFilter2, this.f475x)) ? false : true;
    }

    public final void d0() {
        float F2 = F();
        this.f456a.f497r = (int) Math.ceil(0.75f * F2);
        this.f456a.f498s = (int) Math.ceil(F2 * 0.25f);
        c0();
        K();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.f469n.setColorFilter(this.f474s);
        int alpha = this.f469n.getAlpha();
        this.f469n.setAlpha(O(alpha, this.f456a.f492m));
        this.f470o.setColorFilter(this.f475x);
        this.f470o.setStrokeWidth(this.f456a.f491l);
        int alpha2 = this.f470o.getAlpha();
        this.f470o.setAlpha(O(alpha2, this.f456a.f492m));
        if (this.f460e) {
            i();
            g(s(), this.f462g);
            this.f460e = false;
        }
        N(canvas);
        if (H()) {
            o(canvas);
        }
        if (I()) {
            r(canvas);
        }
        this.f469n.setAlpha(alpha);
        this.f470o.setAlpha(alpha2);
    }

    public final PorterDuffColorFilter f(Paint paint, boolean z2) {
        if (!z2) {
            return null;
        }
        int color = paint.getColor();
        int iL = l(color);
        this.f476y = iL;
        if (iL != color) {
            return new PorterDuffColorFilter(iL, PorterDuff.Mode.SRC_IN);
        }
        return null;
    }

    public final void g(RectF rectF, Path path) {
        h(rectF, path);
        if (this.f456a.f489j != 1.0f) {
            this.f461f.reset();
            Matrix matrix = this.f461f;
            float f2 = this.f456a.f489j;
            matrix.setScale(f2, f2, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.f461f);
        }
        path.computeBounds(this.f454A, true);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f456a.f492m;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f456a;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        if (this.f456a.f496q == 2) {
            return;
        }
        if (M()) {
            outline.setRoundRect(getBounds(), C() * this.f456a.f490k);
        } else {
            g(s(), this.f462g);
            E.a.i(outline, this.f462g);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        Rect rect2 = this.f456a.f488i;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        this.f466k.set(getBounds());
        g(s(), this.f462g);
        this.f467l.setPath(this.f462g, this.f466k);
        this.f466k.op(this.f467l, Region.Op.DIFFERENCE);
        return this.f466k;
    }

    public final void h(RectF rectF, Path path) {
        l lVar = this.f473r;
        c cVar = this.f456a;
        lVar.d(cVar.f480a, cVar.f490k, rectF, this.f472q, path);
    }

    public final void i() {
        k kVarY = A().y(new b(-B()));
        this.f468m = kVarY;
        this.f473r.e(kVarY, this.f456a.f490k, t(), this.f463h);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.f460e = true;
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.f456a.f486g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.f456a.f485f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.f456a.f484e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.f456a.f483d) != null && colorStateList4.isStateful())));
    }

    public final PorterDuffColorFilter j(ColorStateList colorStateList, PorterDuff.Mode mode, boolean z2) {
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z2) {
            colorForState = l(colorForState);
        }
        this.f476y = colorForState;
        return new PorterDuffColorFilter(colorForState, mode);
    }

    public final PorterDuffColorFilter k(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z2) {
        return (colorStateList == null || mode == null) ? f(paint, z2) : j(colorStateList, mode, z2);
    }

    public int l(int i2) {
        float F2 = F() + x();
        F.a aVar = this.f456a.f481b;
        return aVar != null ? aVar.c(i2, F2) : i2;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f456a = new c(this.f456a);
        return this;
    }

    public final void n(Canvas canvas) {
        if (this.f459d.cardinality() > 0) {
            Log.w(f452D, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.f456a.f498s != 0) {
            canvas.drawPath(this.f462g, this.f471p.c());
        }
        for (int i2 = 0; i2 < 4; i2++) {
            this.f457b[i2].a(this.f471p, this.f456a.f497r, canvas);
            this.f458c[i2].a(this.f471p, this.f456a.f497r, canvas);
        }
        if (this.f455B) {
            int iY = y();
            int iZ = z();
            canvas.translate(-iY, -iZ);
            canvas.drawPath(this.f462g, f453E);
            canvas.translate(iY, iZ);
        }
    }

    public final void o(Canvas canvas) {
        p(canvas, this.f469n, this.f462g, this.f456a.f480a, s());
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.f460e = true;
        super.onBoundsChange(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean z2 = b0(iArr) || c0();
        if (z2) {
            invalidateSelf();
        }
        return z2;
    }

    public final void p(Canvas canvas, Paint paint, Path path, k kVar, RectF rectF) {
        if (!kVar.u(rectF)) {
            canvas.drawPath(path, paint);
        } else {
            float fA = kVar.t().a(rectF) * this.f456a.f490k;
            canvas.drawRoundRect(rectF, fA, fA, paint);
        }
    }

    public void q(Canvas canvas, Paint paint, Path path, RectF rectF) {
        p(canvas, paint, path, this.f456a.f480a, rectF);
    }

    public void r(Canvas canvas) {
        p(canvas, this.f470o, this.f463h, this.f468m, t());
    }

    public RectF s() {
        this.f464i.set(getBounds());
        return this.f464i;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        c cVar = this.f456a;
        if (cVar.f492m != i2) {
            cVar.f492m = i2;
            K();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f456a.f482c = colorFilter;
        K();
    }

    @Override // O.n
    public void setShapeAppearanceModel(k kVar) {
        this.f456a.f480a = kVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTint(int i2) {
        setTintList(ColorStateList.valueOf(i2));
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList) {
        this.f456a.f486g = colorStateList;
        c0();
        K();
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f456a;
        if (cVar.f487h != mode) {
            cVar.f487h = mode;
            c0();
            K();
        }
    }

    public final RectF t() {
        this.f465j.set(s());
        float fB = B();
        this.f465j.inset(fB, fB);
        return this.f465j;
    }

    public float u() {
        return this.f456a.f494o;
    }

    public ColorStateList v() {
        return this.f456a.f483d;
    }

    public float w() {
        return this.f456a.f490k;
    }

    public float x() {
        return this.f456a.f493n;
    }

    public int y() {
        return (int) (((double) this.f456a.f498s) * Math.sin(Math.toRadians(r4.f499t)));
    }

    public int z() {
        return (int) (((double) this.f456a.f498s) * Math.cos(Math.toRadians(r4.f499t)));
    }

    public g(Context context, AttributeSet attributeSet, int i2, int i3) {
        this(k.e(context, attributeSet, i2, i3).m());
    }

    public g(k kVar) {
        this(new c(kVar, null));
    }

    public g(c cVar) {
        l lVar;
        this.f457b = new m.g[4];
        this.f458c = new m.g[4];
        this.f459d = new BitSet(8);
        this.f461f = new Matrix();
        this.f462g = new Path();
        this.f463h = new Path();
        this.f464i = new RectF();
        this.f465j = new RectF();
        this.f466k = new Region();
        this.f467l = new Region();
        Paint paint = new Paint(1);
        this.f469n = paint;
        Paint paint2 = new Paint(1);
        this.f470o = paint2;
        this.f471p = new N.a();
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            lVar = l.k();
        } else {
            lVar = new l();
        }
        this.f473r = lVar;
        this.f454A = new RectF();
        this.f455B = true;
        this.f456a = cVar;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        c0();
        b0(getState());
        this.f472q = new a();
    }

    public static class c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public k f480a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public F.a f481b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ColorFilter f482c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ColorStateList f483d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ColorStateList f484e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ColorStateList f485f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ColorStateList f486g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public PorterDuff.Mode f487h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public Rect f488i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public float f489j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public float f490k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public float f491l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f492m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public float f493n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public float f494o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public float f495p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f496q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f497r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f498s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f499t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public boolean f500u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public Paint.Style f501v;

        public c(k kVar, F.a aVar) {
            this.f483d = null;
            this.f484e = null;
            this.f485f = null;
            this.f486g = null;
            this.f487h = PorterDuff.Mode.SRC_IN;
            this.f488i = null;
            this.f489j = 1.0f;
            this.f490k = 1.0f;
            this.f492m = 255;
            this.f493n = 0.0f;
            this.f494o = 0.0f;
            this.f495p = 0.0f;
            this.f496q = 0;
            this.f497r = 0;
            this.f498s = 0;
            this.f499t = 0;
            this.f500u = false;
            this.f501v = Paint.Style.FILL_AND_STROKE;
            this.f480a = kVar;
            this.f481b = aVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            g gVar = new g(this);
            gVar.f460e = true;
            return gVar;
        }

        public c(c cVar) {
            this.f483d = null;
            this.f484e = null;
            this.f485f = null;
            this.f486g = null;
            this.f487h = PorterDuff.Mode.SRC_IN;
            this.f488i = null;
            this.f489j = 1.0f;
            this.f490k = 1.0f;
            this.f492m = 255;
            this.f493n = 0.0f;
            this.f494o = 0.0f;
            this.f495p = 0.0f;
            this.f496q = 0;
            this.f497r = 0;
            this.f498s = 0;
            this.f499t = 0;
            this.f500u = false;
            this.f501v = Paint.Style.FILL_AND_STROKE;
            this.f480a = cVar.f480a;
            this.f481b = cVar.f481b;
            this.f491l = cVar.f491l;
            this.f482c = cVar.f482c;
            this.f483d = cVar.f483d;
            this.f484e = cVar.f484e;
            this.f487h = cVar.f487h;
            this.f486g = cVar.f486g;
            this.f492m = cVar.f492m;
            this.f489j = cVar.f489j;
            this.f498s = cVar.f498s;
            this.f496q = cVar.f496q;
            this.f500u = cVar.f500u;
            this.f490k = cVar.f490k;
            this.f493n = cVar.f493n;
            this.f494o = cVar.f494o;
            this.f495p = cVar.f495p;
            this.f497r = cVar.f497r;
            this.f499t = cVar.f499t;
            this.f485f = cVar.f485f;
            this.f501v = cVar.f501v;
            if (cVar.f488i != null) {
                this.f488i = new Rect(cVar.f488i);
            }
        }
    }
}
