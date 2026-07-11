package miui.systemui.widget;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.view.Gravity;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes4.dex */
public final class ProgressDrawable extends InsetDrawable {
    public static final Companion Companion = new Companion(null);
    private static final int MAX_LEVEL = 10000;
    private final Rect tempRect;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class RoundedCornerState extends Drawable.ConstantState {
        private final Drawable.ConstantState wrappedState;

        public RoundedCornerState(Drawable.ConstantState wrappedState) {
            n.g(wrappedState, "wrappedState");
            this.wrappedState = wrappedState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return true;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.wrappedState.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return newDrawable(null, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            Drawable drawableNewDrawable = this.wrappedState.newDrawable(resources, theme);
            n.e(drawableNewDrawable, "null cannot be cast to non-null type android.graphics.drawable.DrawableWrapper");
            return new ProgressDrawable(((DrawableWrapper) drawableNewDrawable).getDrawable());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ProgressDrawable() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = getDrawable();
        return (drawable != null ? drawable.canApplyTheme() : false) || super.canApplyTheme();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        n.g(canvas, "canvas");
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        if (drawable instanceof GradientDrawable) {
            super.draw(canvas);
            return;
        }
        if (drawable.getLevel() == 0) {
            return;
        }
        Rect rect = this.tempRect;
        Rect bounds = getBounds();
        int level = getLevel();
        int iWidth = bounds.width();
        int i2 = iWidth - (((10000 - level) * iWidth) / 10000);
        int iHeight = bounds.height();
        Gravity.apply(3, i2, iHeight, bounds, rect, getLayoutDirection());
        if (i2 <= 0 || iHeight <= 0) {
            return;
        }
        canvas.save();
        canvas.clipRect(rect);
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | 4096;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable.ConstantState constantState = super.getConstantState();
        if (constantState == null) {
            return null;
        }
        return new RoundedCornerState(constantState);
    }

    @Override // android.graphics.drawable.InsetDrawable, android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return -2;
        }
        if (drawable instanceof GradientDrawable) {
            return super.getOpacity();
        }
        if (drawable.getOpacity() == -2 || drawable.getLevel() == 0) {
            return -2;
        }
        if (getLevel() >= 10000) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.InsetDrawable, android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect bounds) {
        n.g(bounds, "bounds");
        if (getDrawable() instanceof GradientDrawable) {
            super.onBoundsChange(bounds);
            onLevelChange(getLevel());
        } else {
            Drawable drawable = getDrawable();
            if (drawable == null) {
                return;
            }
            drawable.setBounds(bounds);
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i2) {
        if (getDrawable() instanceof GradientDrawable) {
            onLevelChange(getLevel());
        }
        return super.onLayoutDirectionChanged(i2);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean onLevelChange(int i2) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return false;
        }
        if (!(drawable instanceof GradientDrawable)) {
            drawable.setLevel(i2);
            invalidateSelf();
            return true;
        }
        Rect bounds = ((GradientDrawable) drawable).getBounds();
        drawable.setBounds(getBounds().left, bounds.top, getBounds().left + ((int) Math.ceil(((getBounds().width() * i2) * 1.0f) / 10000)), bounds.bottom);
        return super.onLevelChange(i2);
    }

    public /* synthetic */ ProgressDrawable(Drawable drawable, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : drawable);
    }

    public ProgressDrawable(Drawable drawable) {
        super(drawable, 0);
        this.tempRect = new Rect();
    }
}
