package Q;

import O.g;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class h extends O.g {

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public b f603F;

    public static class c extends h {
        public c(b bVar) {
            super(bVar);
        }

        @Override // O.g
        public void r(Canvas canvas) {
            if (this.f603F.f604w.isEmpty()) {
                super.r(canvas);
                return;
            }
            canvas.save();
            canvas.clipOutRect(this.f603F.f604w);
            super.r(canvas);
            canvas.restore();
        }
    }

    public static h f0(O.k kVar) {
        if (kVar == null) {
            kVar = new O.k();
        }
        return g0(new b(kVar, new RectF()));
    }

    public static h g0(b bVar) {
        return new c(bVar);
    }

    public boolean h0() {
        return !this.f603F.f604w.isEmpty();
    }

    public void i0() {
        j0(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void j0(float f2, float f3, float f4, float f5) {
        if (f2 == this.f603F.f604w.left && f3 == this.f603F.f604w.top && f4 == this.f603F.f604w.right && f5 == this.f603F.f604w.bottom) {
            return;
        }
        this.f603F.f604w.set(f2, f3, f4, f5);
        invalidateSelf();
    }

    public void k0(RectF rectF) {
        j0(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    @Override // O.g, android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.f603F = new b(this.f603F);
        return this;
    }

    public static final class b extends g.c {

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public final RectF f604w;

        @Override // O.g.c, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            h hVarG0 = h.g0(this);
            hVarG0.invalidateSelf();
            return hVarG0;
        }

        public b(O.k kVar, RectF rectF) {
            super(kVar, null);
            this.f604w = rectF;
        }

        public b(b bVar) {
            super(bVar);
            this.f604w = bVar.f604w;
        }
    }

    public h(b bVar) {
        super(bVar);
        this.f603F = bVar;
    }
}
