package f;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.collection.LongSparseArray;
import d.F;
import d.K;
import g.AbstractC0355a;
import l.AbstractC0432b;

/* JADX INFO: loaded from: classes.dex */
public class i extends AbstractC0346a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final AbstractC0355a f4148A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public g.q f4149B;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final String f4150r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f4151s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final LongSparseArray f4152t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final LongSparseArray f4153u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final RectF f4154v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final k.g f4155w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f4156x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final AbstractC0355a f4157y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final AbstractC0355a f4158z;

    public i(F f2, AbstractC0432b abstractC0432b, k.f fVar) {
        super(f2, abstractC0432b, fVar.b().a(), fVar.g().a(), fVar.i(), fVar.k(), fVar.m(), fVar.h(), fVar.c());
        this.f4152t = new LongSparseArray();
        this.f4153u = new LongSparseArray();
        this.f4154v = new RectF();
        this.f4150r = fVar.j();
        this.f4155w = fVar.f();
        this.f4151s = fVar.n();
        this.f4156x = (int) (f2.L().d() / 32.0f);
        AbstractC0355a abstractC0355aA = fVar.e().a();
        this.f4157y = abstractC0355aA;
        abstractC0355aA.a(this);
        abstractC0432b.j(abstractC0355aA);
        AbstractC0355a abstractC0355aA2 = fVar.l().a();
        this.f4158z = abstractC0355aA2;
        abstractC0355aA2.a(this);
        abstractC0432b.j(abstractC0355aA2);
        AbstractC0355a abstractC0355aA3 = fVar.d().a();
        this.f4148A = abstractC0355aA3;
        abstractC0355aA3.a(this);
        abstractC0432b.j(abstractC0355aA3);
    }

    @Override // f.AbstractC0346a, i.f
    public void e(Object obj, com.airbnb.lottie.value.c cVar) {
        super.e(obj, cVar);
        if (obj == K.f3783L) {
            g.q qVar = this.f4149B;
            if (qVar != null) {
                this.f4080f.H(qVar);
            }
            if (cVar == null) {
                this.f4149B = null;
                return;
            }
            g.q qVar2 = new g.q(cVar);
            this.f4149B = qVar2;
            qVar2.a(this);
            this.f4080f.j(this.f4149B);
        }
    }

    @Override // f.c
    public String getName() {
        return this.f4150r;
    }

    @Override // f.AbstractC0346a, f.e
    public void h(Canvas canvas, Matrix matrix, int i2) {
        if (this.f4151s) {
            return;
        }
        f(this.f4154v, matrix, false);
        Shader shaderM = this.f4155w == k.g.LINEAR ? m() : n();
        shaderM.setLocalMatrix(matrix);
        this.f4083i.setShader(shaderM);
        super.h(canvas, matrix, i2);
    }

    public final int[] k(int[] iArr) {
        g.q qVar = this.f4149B;
        if (qVar != null) {
            Integer[] numArr = (Integer[]) qVar.h();
            int i2 = 0;
            if (iArr.length == numArr.length) {
                while (i2 < iArr.length) {
                    iArr[i2] = numArr[i2].intValue();
                    i2++;
                }
            } else {
                iArr = new int[numArr.length];
                while (i2 < numArr.length) {
                    iArr[i2] = numArr[i2].intValue();
                    i2++;
                }
            }
        }
        return iArr;
    }

    public final int l() {
        int iRound = Math.round(this.f4158z.f() * this.f4156x);
        int iRound2 = Math.round(this.f4148A.f() * this.f4156x);
        int iRound3 = Math.round(this.f4157y.f() * this.f4156x);
        int i2 = iRound != 0 ? 527 * iRound : 17;
        if (iRound2 != 0) {
            i2 = i2 * 31 * iRound2;
        }
        return iRound3 != 0 ? i2 * 31 * iRound3 : i2;
    }

    public final LinearGradient m() {
        long jL = l();
        LinearGradient linearGradient = (LinearGradient) this.f4152t.get(jL);
        if (linearGradient != null) {
            return linearGradient;
        }
        PointF pointF = (PointF) this.f4158z.h();
        PointF pointF2 = (PointF) this.f4148A.h();
        k.d dVar = (k.d) this.f4157y.h();
        LinearGradient linearGradient2 = new LinearGradient(pointF.x, pointF.y, pointF2.x, pointF2.y, k(dVar.a()), dVar.b(), Shader.TileMode.CLAMP);
        this.f4152t.put(jL, linearGradient2);
        return linearGradient2;
    }

    public final RadialGradient n() {
        long jL = l();
        RadialGradient radialGradient = (RadialGradient) this.f4153u.get(jL);
        if (radialGradient != null) {
            return radialGradient;
        }
        PointF pointF = (PointF) this.f4158z.h();
        PointF pointF2 = (PointF) this.f4148A.h();
        k.d dVar = (k.d) this.f4157y.h();
        int[] iArrK = k(dVar.a());
        float[] fArrB = dVar.b();
        RadialGradient radialGradient2 = new RadialGradient(pointF.x, pointF.y, (float) Math.hypot(pointF2.x - r7, pointF2.y - r8), iArrK, fArrB, Shader.TileMode.CLAMP);
        this.f4153u.put(jL, radialGradient2);
        return radialGradient2;
    }
}
