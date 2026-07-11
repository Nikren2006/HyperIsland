package l;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.collection.LongSparseArray;
import d.AbstractC0302c;
import d.C0307h;
import d.F;
import d.K;
import g.AbstractC0355a;
import g.q;
import j.C0409b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import l.C0435e;

/* JADX INFO: renamed from: l.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0433c extends AbstractC0432b {

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public AbstractC0355a f5094D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final List f5095E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final RectF f5096F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final RectF f5097G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final Paint f5098H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public Boolean f5099I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public Boolean f5100J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f5101K;

    /* JADX INFO: renamed from: l.c$a */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f5102a;

        static {
            int[] iArr = new int[C0435e.b.values().length];
            f5102a = iArr;
            try {
                iArr[C0435e.b.ADD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5102a[C0435e.b.INVERT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public C0433c(F f2, C0435e c0435e, List list, C0307h c0307h) {
        int i2;
        AbstractC0432b abstractC0432b;
        super(f2, c0435e);
        this.f5095E = new ArrayList();
        this.f5096F = new RectF();
        this.f5097G = new RectF();
        this.f5098H = new Paint();
        this.f5101K = true;
        C0409b c0409bU = c0435e.u();
        if (c0409bU != null) {
            AbstractC0355a abstractC0355aA = c0409bU.a();
            this.f5094D = abstractC0355aA;
            j(abstractC0355aA);
            this.f5094D.a(this);
        } else {
            this.f5094D = null;
        }
        LongSparseArray longSparseArray = new LongSparseArray(c0307h.k().size());
        int size = list.size() - 1;
        AbstractC0432b abstractC0432b2 = null;
        while (true) {
            if (size < 0) {
                break;
            }
            C0435e c0435e2 = (C0435e) list.get(size);
            AbstractC0432b abstractC0432bV = AbstractC0432b.v(this, c0435e2, f2, c0307h);
            if (abstractC0432bV != null) {
                longSparseArray.put(abstractC0432bV.z().d(), abstractC0432bV);
                if (abstractC0432b2 != null) {
                    abstractC0432b2.J(abstractC0432bV);
                    abstractC0432b2 = null;
                } else {
                    this.f5095E.add(0, abstractC0432bV);
                    int i3 = a.f5102a[c0435e2.h().ordinal()];
                    if (i3 == 1 || i3 == 2) {
                        abstractC0432b2 = abstractC0432bV;
                    }
                }
            }
            size--;
        }
        for (i2 = 0; i2 < longSparseArray.size(); i2++) {
            AbstractC0432b abstractC0432b3 = (AbstractC0432b) longSparseArray.get(longSparseArray.keyAt(i2));
            if (abstractC0432b3 != null && (abstractC0432b = (AbstractC0432b) longSparseArray.get(abstractC0432b3.z().j())) != null) {
                abstractC0432b3.L(abstractC0432b);
            }
        }
    }

    @Override // l.AbstractC0432b
    public void I(i.e eVar, int i2, List list, i.e eVar2) {
        for (int i3 = 0; i3 < this.f5095E.size(); i3++) {
            ((AbstractC0432b) this.f5095E.get(i3)).i(eVar, i2, list, eVar2);
        }
    }

    @Override // l.AbstractC0432b
    public void K(boolean z2) {
        super.K(z2);
        Iterator it = this.f5095E.iterator();
        while (it.hasNext()) {
            ((AbstractC0432b) it.next()).K(z2);
        }
    }

    @Override // l.AbstractC0432b
    public void M(float f2) {
        super.M(f2);
        if (this.f5094D != null) {
            f2 = ((((Float) this.f5094D.h()).floatValue() * this.f5082q.b().i()) - this.f5082q.b().p()) / (this.f5081p.L().e() + 0.01f);
        }
        if (this.f5094D == null) {
            f2 -= this.f5082q.r();
        }
        if (this.f5082q.v() != 0.0f && !"__container".equals(this.f5082q.i())) {
            f2 /= this.f5082q.v();
        }
        for (int size = this.f5095E.size() - 1; size >= 0; size--) {
            ((AbstractC0432b) this.f5095E.get(size)).M(f2);
        }
    }

    public boolean P() {
        if (this.f5100J == null) {
            for (int size = this.f5095E.size() - 1; size >= 0; size--) {
                AbstractC0432b abstractC0432b = (AbstractC0432b) this.f5095E.get(size);
                if (abstractC0432b instanceof C0437g) {
                    if (abstractC0432b.A()) {
                        this.f5100J = Boolean.TRUE;
                        return true;
                    }
                } else if ((abstractC0432b instanceof C0433c) && ((C0433c) abstractC0432b).P()) {
                    this.f5100J = Boolean.TRUE;
                    return true;
                }
            }
            this.f5100J = Boolean.FALSE;
        }
        return this.f5100J.booleanValue();
    }

    public boolean Q() {
        if (this.f5099I == null) {
            if (B()) {
                this.f5099I = Boolean.TRUE;
                return true;
            }
            for (int size = this.f5095E.size() - 1; size >= 0; size--) {
                if (((AbstractC0432b) this.f5095E.get(size)).B()) {
                    this.f5099I = Boolean.TRUE;
                    return true;
                }
            }
            this.f5099I = Boolean.FALSE;
        }
        return this.f5099I.booleanValue();
    }

    public void R(boolean z2) {
        this.f5101K = z2;
    }

    @Override // l.AbstractC0432b, i.f
    public void e(Object obj, com.airbnb.lottie.value.c cVar) {
        super.e(obj, cVar);
        if (obj == K.f3776E) {
            if (cVar == null) {
                AbstractC0355a abstractC0355a = this.f5094D;
                if (abstractC0355a != null) {
                    abstractC0355a.n(null);
                    return;
                }
                return;
            }
            q qVar = new q(cVar);
            this.f5094D = qVar;
            qVar.a(this);
            j(this.f5094D);
        }
    }

    @Override // l.AbstractC0432b, f.e
    public void f(RectF rectF, Matrix matrix, boolean z2) {
        super.f(rectF, matrix, z2);
        for (int size = this.f5095E.size() - 1; size >= 0; size--) {
            this.f5096F.set(0.0f, 0.0f, 0.0f, 0.0f);
            ((AbstractC0432b) this.f5095E.get(size)).f(this.f5096F, this.f5080o, true);
            rectF.union(this.f5096F);
        }
    }

    @Override // l.AbstractC0432b
    public void u(Canvas canvas, Matrix matrix, int i2) {
        AbstractC0302c.a("CompositionLayer#draw");
        this.f5097G.set(0.0f, 0.0f, this.f5082q.l(), this.f5082q.k());
        matrix.mapRect(this.f5097G);
        boolean z2 = this.f5081p.i0() && this.f5095E.size() > 1 && i2 != 255;
        if (z2) {
            this.f5098H.setAlpha(i2);
            p.h.m(canvas, this.f5097G, this.f5098H);
        } else {
            canvas.save();
        }
        if (z2) {
            i2 = 255;
        }
        for (int size = this.f5095E.size() - 1; size >= 0; size--) {
            if (((this.f5101K || !"__container".equals(this.f5082q.i())) && !this.f5097G.isEmpty()) ? canvas.clipRect(this.f5097G) : true) {
                ((AbstractC0432b) this.f5095E.get(size)).h(canvas, matrix, i2);
            }
        }
        canvas.restore();
        AbstractC0302c.b("CompositionLayer#draw");
    }
}
