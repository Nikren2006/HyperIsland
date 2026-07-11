package miui.systemui.dynamicisland;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import java.util.Collection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.animation.FolmeKt;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;

/* JADX INFO: loaded from: classes3.dex */
@SuppressLint({"UseCompatLoadingForDrawables"})
public final class DynamicIslandBackgroundView extends FrameLayout {
    public static final float MINI_VISIBLE_CHANGE = 0.1f;
    private int actualHeight;
    private int actualLeft;
    private int actualTop;
    private int actualWidth;
    private float backgroundAlpha;
    private Drawable drawable;
    private int stokeWidth;
    public static final Companion Companion = new Companion(null);
    private static final DynamicIslandBackgroundView$Companion$ALPHA$1 ALPHA = new FloatProperty<DynamicIslandBackgroundView>() { // from class: miui.systemui.dynamicisland.DynamicIslandBackgroundView$Companion$ALPHA$1
        @Override // miuix.animation.property.FloatProperty
        public float getValue(DynamicIslandBackgroundView view) {
            n.g(view, "view");
            return view.backgroundAlpha;
        }

        @Override // miuix.animation.property.FloatProperty
        public void setValue(DynamicIslandBackgroundView view, float f2) {
            n.g(view, "view");
            if (Float.isNaN(f2)) {
                return;
            }
            view.backgroundAlpha = f2;
        }
    };

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public /* synthetic */ DynamicIslandBackgroundView(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleUpdate() {
        Drawable drawable = this.drawable;
        if (drawable != null) {
            drawable.setAlpha((int) (255 * this.backgroundAlpha));
        }
        invalidate();
    }

    public final void alphaAnimation(float f2) {
        FolmeKt.getFolme(this).to(new AnimState().add(ALPHA, f2, new long[0]), new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.dynamicisland.DynamicIslandBackgroundView.alphaAnimation.1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                DynamicIslandBackgroundView.this.scheduleUpdate();
            }
        }));
    }

    public final int getActualHeight() {
        return this.actualHeight;
    }

    public final int getActualLeft() {
        return this.actualLeft;
    }

    public final int getActualTop() {
        return this.actualTop;
    }

    public final int getActualWidth() {
        return this.actualWidth;
    }

    public final Drawable getDrawable() {
        return this.drawable;
    }

    public final int getStokeWidth() {
        return this.stokeWidth;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas != null) {
            canvas.save();
        }
        Drawable drawable = this.drawable;
        if (drawable != null) {
            int i2 = this.actualTop;
            int i3 = this.stokeWidth;
            int i4 = i2 - i3;
            int i5 = this.actualHeight + i3;
            int i6 = this.actualLeft - i3;
            int i7 = this.actualWidth + i3;
            if (drawable != null) {
                drawable.setBounds(i6, i4, i7, i5);
            }
            Drawable drawable2 = this.drawable;
            if (drawable2 != null) {
                n.d(canvas);
                drawable2.draw(canvas);
            }
        }
        if (canvas != null) {
            canvas.restore();
        }
    }

    public final void setActualHeight(int i2) {
        this.actualHeight = i2;
    }

    public final void setActualLeft(int i2) {
        this.actualLeft = i2;
    }

    public final void setActualTop(int i2) {
        this.actualTop = i2;
    }

    public final void setActualWidth(int i2) {
        this.actualWidth = i2;
    }

    public final void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public final void setStokeWidth(int i2) {
        this.stokeWidth = i2;
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        setTransitionAlpha(1.0f);
        super.setVisibility(i2);
    }

    public final void superSetVisibility(int i2) {
        super.setVisibility(i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandBackgroundView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n.g(context, "context");
        FolmeKt.cleanFolmeWhenDetached(this);
        this.stokeWidth = getResources().getDimensionPixelSize(R.dimen.island_stroke);
    }
}
