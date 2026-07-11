package H;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

/* JADX INFO: loaded from: classes2.dex */
public abstract class d extends LinearLayoutCompat {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Drawable f252a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f253b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f254c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f255d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f256e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f257f;

    public d(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f253b = new Rect();
        this.f254c = new Rect();
        this.f255d = 119;
        this.f256e = true;
        this.f257f = false;
        TypedArray typedArrayI = k.i(context, attributeSet, t.j.f6818w1, i2, 0, new int[0]);
        this.f255d = typedArrayI.getInt(t.j.f6824y1, this.f255d);
        Drawable drawable = typedArrayI.getDrawable(t.j.f6821x1);
        if (drawable != null) {
            setForeground(drawable);
        }
        this.f256e = typedArrayI.getBoolean(t.j.f6827z1, true);
        typedArrayI.recycle();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Drawable drawable = this.f252a;
        if (drawable != null) {
            if (this.f257f) {
                this.f257f = false;
                Rect rect = this.f253b;
                Rect rect2 = this.f254c;
                int right = getRight() - getLeft();
                int bottom = getBottom() - getTop();
                if (this.f256e) {
                    rect.set(0, 0, right, bottom);
                } else {
                    rect.set(getPaddingLeft(), getPaddingTop(), right - getPaddingRight(), bottom - getPaddingBottom());
                }
                Gravity.apply(this.f255d, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), rect, rect2);
                drawable.setBounds(rect2);
            }
            drawable.draw(canvas);
        }
    }

    @Override // android.view.View
    public void drawableHotspotChanged(float f2, float f3) {
        super.drawableHotspotChanged(f2, f3);
        Drawable drawable = this.f252a;
        if (drawable != null) {
            drawable.setHotspot(f2, f3);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f252a;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        this.f252a.setState(getDrawableState());
    }

    @Override // android.view.View
    @Nullable
    public Drawable getForeground() {
        return this.f252a;
    }

    @Override // android.view.View
    public int getForegroundGravity() {
        return this.f255d;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f252a;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        this.f257f = z2 | this.f257f;
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.f257f = true;
    }

    @Override // android.view.View
    public void setForeground(@Nullable Drawable drawable) {
        Drawable drawable2 = this.f252a;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
                unscheduleDrawable(this.f252a);
            }
            this.f252a = drawable;
            this.f257f = true;
            if (drawable != null) {
                setWillNotDraw(false);
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                if (this.f255d == 119) {
                    drawable.getPadding(new Rect());
                }
            } else {
                setWillNotDraw(true);
            }
            requestLayout();
            invalidate();
        }
    }

    @Override // android.view.View
    public void setForegroundGravity(int i2) {
        if (this.f255d != i2) {
            if ((8388615 & i2) == 0) {
                i2 |= 8388611;
            }
            if ((i2 & 112) == 0) {
                i2 |= 48;
            }
            this.f255d = i2;
            if (i2 == 119 && this.f252a != null) {
                this.f252a.getPadding(new Rect());
            }
            requestLayout();
        }
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f252a;
    }
}
