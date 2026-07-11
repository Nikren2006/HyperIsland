package l;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import d.F;
import java.util.Collections;
import java.util.List;
import k.C0425a;
import k.p;
import n.C0707j;

/* JADX INFO: renamed from: l.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0437g extends AbstractC0432b {

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final f.d f5148D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final C0433c f5149E;

    public C0437g(F f2, C0435e c0435e, C0433c c0433c) {
        super(f2, c0435e);
        this.f5149E = c0433c;
        f.d dVar = new f.d(f2, this, new p("__container", c0435e.n(), false));
        this.f5148D = dVar;
        dVar.b(Collections.emptyList(), Collections.emptyList());
    }

    @Override // l.AbstractC0432b
    public void I(i.e eVar, int i2, List list, i.e eVar2) {
        this.f5148D.i(eVar, i2, list, eVar2);
    }

    @Override // l.AbstractC0432b, f.e
    public void f(RectF rectF, Matrix matrix, boolean z2) {
        super.f(rectF, matrix, z2);
        this.f5148D.f(rectF, this.f5080o, z2);
    }

    @Override // l.AbstractC0432b
    public void u(Canvas canvas, Matrix matrix, int i2) {
        this.f5148D.h(canvas, matrix, i2);
    }

    @Override // l.AbstractC0432b
    public C0425a w() {
        C0425a c0425aW = super.w();
        return c0425aW != null ? c0425aW : this.f5149E.w();
    }

    @Override // l.AbstractC0432b
    public C0707j y() {
        C0707j c0707jY = super.y();
        return c0707jY != null ? c0707jY : this.f5149E.y();
    }
}
