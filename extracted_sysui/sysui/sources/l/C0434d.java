package l;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import d.F;
import d.G;
import d.K;
import e.C0333a;
import g.AbstractC0355a;
import g.q;

/* JADX INFO: renamed from: l.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0434d extends AbstractC0432b {

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final Paint f5103D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final Rect f5104E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final Rect f5105F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final G f5106G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public AbstractC0355a f5107H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public AbstractC0355a f5108I;

    public C0434d(F f2, C0435e c0435e) {
        super(f2, c0435e);
        this.f5103D = new C0333a(3);
        this.f5104E = new Rect();
        this.f5105F = new Rect();
        this.f5106G = f2.R(c0435e.m());
    }

    public final Bitmap P() {
        Bitmap bitmap;
        AbstractC0355a abstractC0355a = this.f5108I;
        if (abstractC0355a != null && (bitmap = (Bitmap) abstractC0355a.h()) != null) {
            return bitmap;
        }
        Bitmap bitmapJ = this.f5081p.J(this.f5082q.m());
        if (bitmapJ != null) {
            return bitmapJ;
        }
        G g2 = this.f5106G;
        if (g2 != null) {
            return g2.a();
        }
        return null;
    }

    @Override // l.AbstractC0432b, i.f
    public void e(Object obj, com.airbnb.lottie.value.c cVar) {
        super.e(obj, cVar);
        if (obj == K.f3782K) {
            if (cVar == null) {
                this.f5107H = null;
                return;
            } else {
                this.f5107H = new q(cVar);
                return;
            }
        }
        if (obj == K.f3785N) {
            if (cVar == null) {
                this.f5108I = null;
            } else {
                this.f5108I = new q(cVar);
            }
        }
    }

    @Override // l.AbstractC0432b, f.e
    public void f(RectF rectF, Matrix matrix, boolean z2) {
        super.f(rectF, matrix, z2);
        if (this.f5106G != null) {
            float fE = p.h.e();
            rectF.set(0.0f, 0.0f, this.f5106G.e() * fE, this.f5106G.c() * fE);
            this.f5080o.mapRect(rectF);
        }
    }

    @Override // l.AbstractC0432b
    public void u(Canvas canvas, Matrix matrix, int i2) {
        Bitmap bitmapP = P();
        if (bitmapP == null || bitmapP.isRecycled() || this.f5106G == null) {
            return;
        }
        float fE = p.h.e();
        this.f5103D.setAlpha(i2);
        AbstractC0355a abstractC0355a = this.f5107H;
        if (abstractC0355a != null) {
            this.f5103D.setColorFilter((ColorFilter) abstractC0355a.h());
        }
        canvas.save();
        canvas.concat(matrix);
        this.f5104E.set(0, 0, bitmapP.getWidth(), bitmapP.getHeight());
        if (this.f5081p.S()) {
            this.f5105F.set(0, 0, (int) (this.f5106G.e() * fE), (int) (this.f5106G.c() * fE));
        } else {
            this.f5105F.set(0, 0, (int) (bitmapP.getWidth() * fE), (int) (bitmapP.getHeight() * fE));
        }
        canvas.drawBitmap(bitmapP, this.f5104E, this.f5105F, this.f5103D);
        canvas.restore();
    }
}
