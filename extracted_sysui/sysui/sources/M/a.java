package M;

import O.g;
import O.k;
import O.n;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.TintAwareDrawable;

/* JADX INFO: loaded from: classes2.dex */
public class a extends Drawable implements n, TintAwareDrawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f403a;

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a mutate() {
        this.f403a = new b(this.f403a);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        b bVar = this.f403a;
        if (bVar.f405b) {
            bVar.f404a.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f403a;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f403a.f404a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f403a.f404a.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        if (this.f403a.f404a.setState(iArr)) {
            zOnStateChange = true;
        }
        boolean zB = M.b.b(iArr);
        b bVar = this.f403a;
        if (bVar.f405b == zB) {
            return zOnStateChange;
        }
        bVar.f405b = zB;
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f403a.f404a.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f403a.f404a.setColorFilter(colorFilter);
    }

    @Override // O.n
    public void setShapeAppearanceModel(k kVar) {
        this.f403a.f404a.setShapeAppearanceModel(kVar);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTint(int i2) {
        this.f403a.f404a.setTint(i2);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList) {
        this.f403a.f404a.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.f403a.f404a.setTintMode(mode);
    }

    public a(k kVar) {
        this(new b(new g(kVar)));
    }

    public static final class b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public g f404a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f405b;

        public b(g gVar) {
            this.f404a = gVar;
            this.f405b = false;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a newDrawable() {
            return new a(new b(this));
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        public b(b bVar) {
            this.f404a = (g) bVar.f404a.getConstantState().newDrawable();
            this.f405b = bVar.f405b;
        }
    }

    public a(b bVar) {
        this.f403a = bVar;
    }
}
