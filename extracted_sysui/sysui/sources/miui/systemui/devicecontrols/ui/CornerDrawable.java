package miui.systemui.devicecontrols.ui;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;

/* JADX INFO: loaded from: classes3.dex */
public final class CornerDrawable extends DrawableWrapper {
    private final float cornerRadius;
    private final Path path;
    private final Drawable wrapped;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CornerDrawable(Drawable wrapped, float f2) {
        super(wrapped);
        kotlin.jvm.internal.n.g(wrapped, "wrapped");
        this.wrapped = wrapped;
        this.cornerRadius = f2;
        this.path = new Path();
        updatePath(new RectF(getBounds()));
    }

    private final void updatePath(RectF rectF) {
        this.path.reset();
        Path path = this.path;
        float f2 = this.cornerRadius;
        path.addRoundRect(rectF, f2, f2, Path.Direction.CW);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        kotlin.jvm.internal.n.g(canvas, "canvas");
        canvas.clipPath(this.path);
        super.draw(canvas);
    }

    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    public final Path getPath() {
        return this.path;
    }

    public final Drawable getWrapped() {
        return this.wrapped;
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i2, int i3, int i4, int i5) {
        updatePath(new RectF(i2, i3, i4, i5));
        super.setBounds(i2, i3, i4, i5);
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(Rect r2) {
        kotlin.jvm.internal.n.g(r2, "r");
        updatePath(new RectF(r2));
        super.setBounds(r2);
    }
}
