package l;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import androidx.core.view.ViewCompat;
import d.AbstractC0302c;
import d.C0307h;
import d.F;
import e.C0333a;
import g.AbstractC0355a;
import g.p;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import k.C0425a;
import k.h;
import l.C0435e;
import n.C0707j;
import p.AbstractC0724d;

/* JADX INFO: renamed from: l.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0432b implements f.e, AbstractC0355a.b, i.f {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public Paint f5063A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public float f5064B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public BlurMaskFilter f5065C;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f5066a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Matrix f5067b = new Matrix();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix f5068c = new Matrix();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Paint f5069d = new C0333a(1);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Paint f5070e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Paint f5071f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Paint f5072g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Paint f5073h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final RectF f5074i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final RectF f5075j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final RectF f5076k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final RectF f5077l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final RectF f5078m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f5079n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Matrix f5080o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final F f5081p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C0435e f5082q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public g.h f5083r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public g.d f5084s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public AbstractC0432b f5085t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public AbstractC0432b f5086u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public List f5087v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final List f5088w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final p f5089x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f5090y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f5091z;

    /* JADX INFO: renamed from: l.b$a */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f5092a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f5093b;

        static {
            int[] iArr = new int[h.a.values().length];
            f5093b = iArr;
            try {
                iArr[h.a.MASK_MODE_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5093b[h.a.MASK_MODE_SUBTRACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5093b[h.a.MASK_MODE_INTERSECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5093b[h.a.MASK_MODE_ADD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[C0435e.a.values().length];
            f5092a = iArr2;
            try {
                iArr2[C0435e.a.SHAPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f5092a[C0435e.a.PRE_COMP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f5092a[C0435e.a.SOLID.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f5092a[C0435e.a.IMAGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f5092a[C0435e.a.NULL.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f5092a[C0435e.a.TEXT.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f5092a[C0435e.a.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public AbstractC0432b(F f2, C0435e c0435e) {
        PorterDuff.Mode mode = PorterDuff.Mode.DST_IN;
        this.f5070e = new C0333a(1, mode);
        PorterDuff.Mode mode2 = PorterDuff.Mode.DST_OUT;
        this.f5071f = new C0333a(1, mode2);
        C0333a c0333a = new C0333a(1);
        this.f5072g = c0333a;
        this.f5073h = new C0333a(PorterDuff.Mode.CLEAR);
        this.f5074i = new RectF();
        this.f5075j = new RectF();
        this.f5076k = new RectF();
        this.f5077l = new RectF();
        this.f5078m = new RectF();
        this.f5080o = new Matrix();
        this.f5088w = new ArrayList();
        this.f5090y = true;
        this.f5064B = 0.0f;
        this.f5081p = f2;
        this.f5082q = c0435e;
        this.f5079n = c0435e.i() + "#draw";
        if (c0435e.h() == C0435e.b.INVERT) {
            c0333a.setXfermode(new PorterDuffXfermode(mode2));
        } else {
            c0333a.setXfermode(new PorterDuffXfermode(mode));
        }
        p pVarB = c0435e.w().b();
        this.f5089x = pVarB;
        pVarB.b(this);
        if (c0435e.g() != null && !c0435e.g().isEmpty()) {
            g.h hVar = new g.h(c0435e.g());
            this.f5083r = hVar;
            Iterator it = hVar.a().iterator();
            while (it.hasNext()) {
                ((AbstractC0355a) it.next()).a(this);
            }
            for (AbstractC0355a abstractC0355a : this.f5083r.c()) {
                j(abstractC0355a);
                abstractC0355a.a(this);
            }
        }
        O();
    }

    public static AbstractC0432b v(C0433c c0433c, C0435e c0435e, F f2, C0307h c0307h) {
        switch (a.f5092a[c0435e.f().ordinal()]) {
            case 1:
                return new C0437g(f2, c0435e, c0433c);
            case 2:
                return new C0433c(f2, c0435e, c0307h.o(c0435e.m()), c0307h);
            case 3:
                return new h(f2, c0435e);
            case 4:
                return new C0434d(f2, c0435e);
            case 5:
                return new C0436f(f2, c0435e);
            case 6:
                return new i(f2, c0435e);
            default:
                AbstractC0724d.c("Unknown layer type " + c0435e.f());
                return null;
        }
    }

    public boolean A() {
        g.h hVar = this.f5083r;
        return (hVar == null || hVar.a().isEmpty()) ? false : true;
    }

    public boolean B() {
        return this.f5085t != null;
    }

    public final void C(RectF rectF, Matrix matrix) {
        this.f5076k.set(0.0f, 0.0f, 0.0f, 0.0f);
        if (A()) {
            int size = this.f5083r.b().size();
            for (int i2 = 0; i2 < size; i2++) {
                k.h hVar = (k.h) this.f5083r.b().get(i2);
                Path path = (Path) ((AbstractC0355a) this.f5083r.a().get(i2)).h();
                if (path != null) {
                    this.f5066a.set(path);
                    this.f5066a.transform(matrix);
                    int i3 = a.f5093b[hVar.a().ordinal()];
                    if (i3 == 1 || i3 == 2) {
                        return;
                    }
                    if ((i3 == 3 || i3 == 4) && hVar.d()) {
                        return;
                    }
                    this.f5066a.computeBounds(this.f5078m, false);
                    if (i2 == 0) {
                        this.f5076k.set(this.f5078m);
                    } else {
                        RectF rectF2 = this.f5076k;
                        rectF2.set(Math.min(rectF2.left, this.f5078m.left), Math.min(this.f5076k.top, this.f5078m.top), Math.max(this.f5076k.right, this.f5078m.right), Math.max(this.f5076k.bottom, this.f5078m.bottom));
                    }
                }
            }
            if (rectF.intersect(this.f5076k)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    public final void D(RectF rectF, Matrix matrix) {
        if (B() && this.f5082q.h() != C0435e.b.INVERT) {
            this.f5077l.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.f5085t.f(this.f5077l, matrix, true);
            if (rectF.intersect(this.f5077l)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    public final void E() {
        this.f5081p.invalidateSelf();
    }

    public final /* synthetic */ void F() {
        N(this.f5084s.p() == 1.0f);
    }

    public final void G(float f2) {
        this.f5081p.L().n().a(this.f5082q.i(), f2);
    }

    public void H(AbstractC0355a abstractC0355a) {
        this.f5088w.remove(abstractC0355a);
    }

    public void I(i.e eVar, int i2, List list, i.e eVar2) {
    }

    public void J(AbstractC0432b abstractC0432b) {
        this.f5085t = abstractC0432b;
    }

    public void K(boolean z2) {
        if (z2 && this.f5063A == null) {
            this.f5063A = new C0333a();
        }
        this.f5091z = z2;
    }

    public void L(AbstractC0432b abstractC0432b) {
        this.f5086u = abstractC0432b;
    }

    public void M(float f2) {
        this.f5089x.j(f2);
        if (this.f5083r != null) {
            for (int i2 = 0; i2 < this.f5083r.a().size(); i2++) {
                ((AbstractC0355a) this.f5083r.a().get(i2)).m(f2);
            }
        }
        g.d dVar = this.f5084s;
        if (dVar != null) {
            dVar.m(f2);
        }
        AbstractC0432b abstractC0432b = this.f5085t;
        if (abstractC0432b != null) {
            abstractC0432b.M(f2);
        }
        for (int i3 = 0; i3 < this.f5088w.size(); i3++) {
            ((AbstractC0355a) this.f5088w.get(i3)).m(f2);
        }
    }

    public final void N(boolean z2) {
        if (z2 != this.f5090y) {
            this.f5090y = z2;
            E();
        }
    }

    public final void O() {
        if (this.f5082q.e().isEmpty()) {
            N(true);
            return;
        }
        g.d dVar = new g.d(this.f5082q.e());
        this.f5084s = dVar;
        dVar.l();
        this.f5084s.a(new AbstractC0355a.b() { // from class: l.a
            @Override // g.AbstractC0355a.b
            public final void a() {
                this.f5062a.F();
            }
        });
        N(((Float) this.f5084s.h()).floatValue() == 1.0f);
        j(this.f5084s);
    }

    @Override // g.AbstractC0355a.b
    public void a() {
        E();
    }

    @Override // f.c
    public void b(List list, List list2) {
    }

    @Override // i.f
    public void e(Object obj, com.airbnb.lottie.value.c cVar) {
        this.f5089x.c(obj, cVar);
    }

    @Override // f.e
    public void f(RectF rectF, Matrix matrix, boolean z2) {
        this.f5074i.set(0.0f, 0.0f, 0.0f, 0.0f);
        s();
        this.f5080o.set(matrix);
        if (z2) {
            List list = this.f5087v;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.f5080o.preConcat(((AbstractC0432b) this.f5087v.get(size)).f5089x.f());
                }
            } else {
                AbstractC0432b abstractC0432b = this.f5086u;
                if (abstractC0432b != null) {
                    this.f5080o.preConcat(abstractC0432b.f5089x.f());
                }
            }
        }
        this.f5080o.preConcat(this.f5089x.f());
    }

    @Override // f.c
    public String getName() {
        return this.f5082q.i();
    }

    @Override // f.e
    public void h(Canvas canvas, Matrix matrix, int i2) {
        Paint paint;
        AbstractC0302c.a(this.f5079n);
        if (!this.f5090y || this.f5082q.x()) {
            AbstractC0302c.b(this.f5079n);
            return;
        }
        s();
        AbstractC0302c.a("Layer#parentMatrix");
        this.f5067b.reset();
        this.f5067b.set(matrix);
        for (int size = this.f5087v.size() - 1; size >= 0; size--) {
            this.f5067b.preConcat(((AbstractC0432b) this.f5087v.get(size)).f5089x.f());
        }
        AbstractC0302c.b("Layer#parentMatrix");
        int iIntValue = (int) ((((i2 / 255.0f) * (this.f5089x.h() == null ? 100 : ((Integer) this.f5089x.h().h()).intValue())) / 100.0f) * 255.0f);
        if (!B() && !A()) {
            this.f5067b.preConcat(this.f5089x.f());
            AbstractC0302c.a("Layer#drawLayer");
            u(canvas, this.f5067b, iIntValue);
            AbstractC0302c.b("Layer#drawLayer");
            G(AbstractC0302c.b(this.f5079n));
            return;
        }
        AbstractC0302c.a("Layer#computeBounds");
        f(this.f5074i, this.f5067b, false);
        D(this.f5074i, matrix);
        this.f5067b.preConcat(this.f5089x.f());
        C(this.f5074i, this.f5067b);
        this.f5075j.set(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight());
        canvas.getMatrix(this.f5068c);
        if (!this.f5068c.isIdentity()) {
            Matrix matrix2 = this.f5068c;
            matrix2.invert(matrix2);
            this.f5068c.mapRect(this.f5075j);
        }
        if (!this.f5074i.intersect(this.f5075j)) {
            this.f5074i.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
        AbstractC0302c.b("Layer#computeBounds");
        if (this.f5074i.width() >= 1.0f && this.f5074i.height() >= 1.0f) {
            AbstractC0302c.a("Layer#saveLayer");
            this.f5069d.setAlpha(255);
            p.h.m(canvas, this.f5074i, this.f5069d);
            AbstractC0302c.b("Layer#saveLayer");
            t(canvas);
            AbstractC0302c.a("Layer#drawLayer");
            u(canvas, this.f5067b, iIntValue);
            AbstractC0302c.b("Layer#drawLayer");
            if (A()) {
                p(canvas, this.f5067b);
            }
            if (B()) {
                AbstractC0302c.a("Layer#drawMatte");
                AbstractC0302c.a("Layer#saveLayer");
                p.h.n(canvas, this.f5074i, this.f5072g, 19);
                AbstractC0302c.b("Layer#saveLayer");
                t(canvas);
                this.f5085t.h(canvas, matrix, iIntValue);
                AbstractC0302c.a("Layer#restoreLayer");
                canvas.restore();
                AbstractC0302c.b("Layer#restoreLayer");
                AbstractC0302c.b("Layer#drawMatte");
            }
            AbstractC0302c.a("Layer#restoreLayer");
            canvas.restore();
            AbstractC0302c.b("Layer#restoreLayer");
        }
        if (this.f5091z && (paint = this.f5063A) != null) {
            paint.setStyle(Paint.Style.STROKE);
            this.f5063A.setColor(-251901);
            this.f5063A.setStrokeWidth(4.0f);
            canvas.drawRect(this.f5074i, this.f5063A);
            this.f5063A.setStyle(Paint.Style.FILL);
            this.f5063A.setColor(1357638635);
            canvas.drawRect(this.f5074i, this.f5063A);
        }
        G(AbstractC0302c.b(this.f5079n));
    }

    @Override // i.f
    public void i(i.e eVar, int i2, List list, i.e eVar2) {
        AbstractC0432b abstractC0432b = this.f5085t;
        if (abstractC0432b != null) {
            i.e eVarA = eVar2.a(abstractC0432b.getName());
            if (eVar.c(this.f5085t.getName(), i2)) {
                list.add(eVarA.i(this.f5085t));
            }
            if (eVar.h(getName(), i2)) {
                this.f5085t.I(eVar, eVar.e(this.f5085t.getName(), i2) + i2, list, eVarA);
            }
        }
        if (eVar.g(getName(), i2)) {
            if (!"__container".equals(getName())) {
                eVar2 = eVar2.a(getName());
                if (eVar.c(getName(), i2)) {
                    list.add(eVar2.i(this));
                }
            }
            if (eVar.h(getName(), i2)) {
                I(eVar, i2 + eVar.e(getName(), i2), list, eVar2);
            }
        }
    }

    public void j(AbstractC0355a abstractC0355a) {
        if (abstractC0355a == null) {
            return;
        }
        this.f5088w.add(abstractC0355a);
    }

    public final void k(Canvas canvas, Matrix matrix, AbstractC0355a abstractC0355a, AbstractC0355a abstractC0355a2) {
        this.f5066a.set((Path) abstractC0355a.h());
        this.f5066a.transform(matrix);
        this.f5069d.setAlpha((int) (((Integer) abstractC0355a2.h()).intValue() * 2.55f));
        canvas.drawPath(this.f5066a, this.f5069d);
    }

    public final void l(Canvas canvas, Matrix matrix, AbstractC0355a abstractC0355a, AbstractC0355a abstractC0355a2) {
        p.h.m(canvas, this.f5074i, this.f5070e);
        this.f5066a.set((Path) abstractC0355a.h());
        this.f5066a.transform(matrix);
        this.f5069d.setAlpha((int) (((Integer) abstractC0355a2.h()).intValue() * 2.55f));
        canvas.drawPath(this.f5066a, this.f5069d);
        canvas.restore();
    }

    public final void m(Canvas canvas, Matrix matrix, AbstractC0355a abstractC0355a, AbstractC0355a abstractC0355a2) {
        p.h.m(canvas, this.f5074i, this.f5069d);
        canvas.drawRect(this.f5074i, this.f5069d);
        this.f5066a.set((Path) abstractC0355a.h());
        this.f5066a.transform(matrix);
        this.f5069d.setAlpha((int) (((Integer) abstractC0355a2.h()).intValue() * 2.55f));
        canvas.drawPath(this.f5066a, this.f5071f);
        canvas.restore();
    }

    public final void n(Canvas canvas, Matrix matrix, AbstractC0355a abstractC0355a, AbstractC0355a abstractC0355a2) {
        p.h.m(canvas, this.f5074i, this.f5070e);
        canvas.drawRect(this.f5074i, this.f5069d);
        this.f5071f.setAlpha((int) (((Integer) abstractC0355a2.h()).intValue() * 2.55f));
        this.f5066a.set((Path) abstractC0355a.h());
        this.f5066a.transform(matrix);
        canvas.drawPath(this.f5066a, this.f5071f);
        canvas.restore();
    }

    public final void o(Canvas canvas, Matrix matrix, AbstractC0355a abstractC0355a, AbstractC0355a abstractC0355a2) {
        p.h.m(canvas, this.f5074i, this.f5071f);
        canvas.drawRect(this.f5074i, this.f5069d);
        this.f5071f.setAlpha((int) (((Integer) abstractC0355a2.h()).intValue() * 2.55f));
        this.f5066a.set((Path) abstractC0355a.h());
        this.f5066a.transform(matrix);
        canvas.drawPath(this.f5066a, this.f5071f);
        canvas.restore();
    }

    public final void p(Canvas canvas, Matrix matrix) {
        AbstractC0302c.a("Layer#saveLayer");
        p.h.n(canvas, this.f5074i, this.f5070e, 19);
        AbstractC0302c.b("Layer#saveLayer");
        for (int i2 = 0; i2 < this.f5083r.b().size(); i2++) {
            k.h hVar = (k.h) this.f5083r.b().get(i2);
            AbstractC0355a abstractC0355a = (AbstractC0355a) this.f5083r.a().get(i2);
            AbstractC0355a abstractC0355a2 = (AbstractC0355a) this.f5083r.c().get(i2);
            int i3 = a.f5093b[hVar.a().ordinal()];
            if (i3 != 1) {
                if (i3 == 2) {
                    if (i2 == 0) {
                        this.f5069d.setColor(ViewCompat.MEASURED_STATE_MASK);
                        this.f5069d.setAlpha(255);
                        canvas.drawRect(this.f5074i, this.f5069d);
                    }
                    if (hVar.d()) {
                        o(canvas, matrix, abstractC0355a, abstractC0355a2);
                    } else {
                        q(canvas, matrix, abstractC0355a);
                    }
                } else if (i3 != 3) {
                    if (i3 == 4) {
                        if (hVar.d()) {
                            m(canvas, matrix, abstractC0355a, abstractC0355a2);
                        } else {
                            k(canvas, matrix, abstractC0355a, abstractC0355a2);
                        }
                    }
                } else if (hVar.d()) {
                    n(canvas, matrix, abstractC0355a, abstractC0355a2);
                } else {
                    l(canvas, matrix, abstractC0355a, abstractC0355a2);
                }
            } else if (r()) {
                this.f5069d.setAlpha(255);
                canvas.drawRect(this.f5074i, this.f5069d);
            }
        }
        AbstractC0302c.a("Layer#restoreLayer");
        canvas.restore();
        AbstractC0302c.b("Layer#restoreLayer");
    }

    public final void q(Canvas canvas, Matrix matrix, AbstractC0355a abstractC0355a) {
        this.f5066a.set((Path) abstractC0355a.h());
        this.f5066a.transform(matrix);
        canvas.drawPath(this.f5066a, this.f5071f);
    }

    public final boolean r() {
        if (this.f5083r.a().isEmpty()) {
            return false;
        }
        for (int i2 = 0; i2 < this.f5083r.b().size(); i2++) {
            if (((k.h) this.f5083r.b().get(i2)).a() != h.a.MASK_MODE_NONE) {
                return false;
            }
        }
        return true;
    }

    public final void s() {
        if (this.f5087v != null) {
            return;
        }
        if (this.f5086u == null) {
            this.f5087v = Collections.emptyList();
            return;
        }
        this.f5087v = new ArrayList();
        for (AbstractC0432b abstractC0432b = this.f5086u; abstractC0432b != null; abstractC0432b = abstractC0432b.f5086u) {
            this.f5087v.add(abstractC0432b);
        }
    }

    public final void t(Canvas canvas) {
        AbstractC0302c.a("Layer#clearLayer");
        RectF rectF = this.f5074i;
        canvas.drawRect(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f, this.f5073h);
        AbstractC0302c.b("Layer#clearLayer");
    }

    public abstract void u(Canvas canvas, Matrix matrix, int i2);

    public C0425a w() {
        return this.f5082q.a();
    }

    public BlurMaskFilter x(float f2) {
        if (this.f5064B == f2) {
            return this.f5065C;
        }
        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(f2 / 2.0f, BlurMaskFilter.Blur.NORMAL);
        this.f5065C = blurMaskFilter;
        this.f5064B = f2;
        return blurMaskFilter;
    }

    public C0707j y() {
        return this.f5082q.c();
    }

    public C0435e z() {
        return this.f5082q;
    }
}
