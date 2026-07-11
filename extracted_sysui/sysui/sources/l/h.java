package l;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import d.F;
import d.K;
import e.C0333a;
import g.AbstractC0355a;
import g.q;

/* JADX INFO: loaded from: classes.dex */
public class h extends AbstractC0432b {

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final RectF f5150D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final Paint f5151E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final float[] f5152F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final Path f5153G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final C0435e f5154H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public AbstractC0355a f5155I;

    public h(F f2, C0435e c0435e) {
        super(f2, c0435e);
        this.f5150D = new RectF();
        C0333a c0333a = new C0333a();
        this.f5151E = c0333a;
        this.f5152F = new float[8];
        this.f5153G = new Path();
        this.f5154H = c0435e;
        c0333a.setAlpha(0);
        c0333a.setStyle(Paint.Style.FILL);
        c0333a.setColor(c0435e.o());
    }

    @Override // l.AbstractC0432b, i.f
    public void e(Object obj, com.airbnb.lottie.value.c cVar) {
        super.e(obj, cVar);
        if (obj == K.f3782K) {
            if (cVar == null) {
                this.f5155I = null;
            } else {
                this.f5155I = new q(cVar);
            }
        }
    }

    @Override // l.AbstractC0432b, f.e
    public void f(RectF rectF, Matrix matrix, boolean z2) {
        super.f(rectF, matrix, z2);
        this.f5150D.set(0.0f, 0.0f, this.f5154H.q(), this.f5154H.p());
        this.f5080o.mapRect(this.f5150D);
        rectF.set(this.f5150D);
    }

    @Override // l.AbstractC0432b
    public void u(Canvas canvas, Matrix matrix, int i2) {
        int iAlpha = Color.alpha(this.f5154H.o());
        if (iAlpha == 0) {
            return;
        }
        int iIntValue = (int) ((i2 / 255.0f) * (((iAlpha / 255.0f) * (this.f5089x.h() == null ? 100 : ((Integer) this.f5089x.h().h()).intValue())) / 100.0f) * 255.0f);
        this.f5151E.setAlpha(iIntValue);
        AbstractC0355a abstractC0355a = this.f5155I;
        if (abstractC0355a != null) {
            this.f5151E.setColorFilter((ColorFilter) abstractC0355a.h());
        }
        if (iIntValue > 0) {
            float[] fArr = this.f5152F;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            fArr[2] = this.f5154H.q();
            float[] fArr2 = this.f5152F;
            fArr2[3] = 0.0f;
            fArr2[4] = this.f5154H.q();
            this.f5152F[5] = this.f5154H.p();
            float[] fArr3 = this.f5152F;
            fArr3[6] = 0.0f;
            fArr3[7] = this.f5154H.p();
            matrix.mapPoints(this.f5152F);
            this.f5153G.reset();
            Path path = this.f5153G;
            float[] fArr4 = this.f5152F;
            path.moveTo(fArr4[0], fArr4[1]);
            Path path2 = this.f5153G;
            float[] fArr5 = this.f5152F;
            path2.lineTo(fArr5[2], fArr5[3]);
            Path path3 = this.f5153G;
            float[] fArr6 = this.f5152F;
            path3.lineTo(fArr6[4], fArr6[5]);
            Path path4 = this.f5153G;
            float[] fArr7 = this.f5152F;
            path4.lineTo(fArr7[6], fArr7[7]);
            Path path5 = this.f5153G;
            float[] fArr8 = this.f5152F;
            path5.lineTo(fArr8[0], fArr8[1]);
            this.f5153G.close();
            canvas.drawPath(this.f5153G, this.f5151E);
        }
    }
}
