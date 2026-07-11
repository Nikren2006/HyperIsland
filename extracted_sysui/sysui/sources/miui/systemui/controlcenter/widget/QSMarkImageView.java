package miui.systemui.controlcenter.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miuix.animation.Folme;
import miuix.animation.ITouchStyle;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView", "ClickableViewAccessibility"})
public final class QSMarkImageView extends ImageView {
    private ITouchStyle iconMouseAnim;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSMarkImageView(Context context) {
        this(context, null, 0, 0, 14, null);
        n.g(context, "context");
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (getAlpha() == 0.0f) {
            return false;
        }
        if (this.iconMouseAnim == null) {
            this.iconMouseAnim = Folme.useAt(this).touch().setTint(0.0f, 0.0f, 0.0f, 0.0f);
        }
        ITouchStyle iTouchStyle = this.iconMouseAnim;
        if (iTouchStyle != null) {
            iTouchStyle.onMotionEventEx(this, motionEvent, new AnimConfig[0]);
        }
        return super.onTouchEvent(motionEvent);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSMarkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
        n.g(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public QSMarkImageView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0, 8, null);
        n.g(context, "context");
    }

    public /* synthetic */ QSMarkImageView(Context context, AttributeSet attributeSet, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i4 & 2) != 0 ? null : attributeSet, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSMarkImageView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        n.g(context, "context");
    }
}
