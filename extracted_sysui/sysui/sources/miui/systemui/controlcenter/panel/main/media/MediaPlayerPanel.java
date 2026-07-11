package miui.systemui.controlcenter.panel.main.media;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;

/* JADX INFO: loaded from: classes.dex */
public final class MediaPlayerPanel extends ConstraintLayout {
    private MainPanelItemViewHolder.TouchAnimator touchAnimator;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MediaPlayerPanel(Context context) {
        this(context, null, 0, 6, null);
        n.g(context, "context");
    }

    public final MainPanelItemViewHolder.TouchAnimator getTouchAnimator() {
        return this.touchAnimator;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MainPanelItemViewHolder.TouchAnimator touchAnimator;
        Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getActionMasked()) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            MainPanelItemViewHolder.TouchAnimator touchAnimator2 = this.touchAnimator;
            if (touchAnimator2 != null) {
                touchAnimator2.touchDown(motionEvent);
            }
        } else if (numValueOf != null && numValueOf.intValue() == 1) {
            MainPanelItemViewHolder.TouchAnimator touchAnimator3 = this.touchAnimator;
            if (touchAnimator3 != null) {
                touchAnimator3.touchRelease();
            }
        } else if (numValueOf != null && numValueOf.intValue() == 3 && (touchAnimator = this.touchAnimator) != null) {
            touchAnimator.touchCancel();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        MainPanelItemViewHolder.TouchAnimator touchAnimator = this.touchAnimator;
        if (touchAnimator != null) {
            touchAnimator.touchCancel();
        }
    }

    public final void setTouchAnimator(MainPanelItemViewHolder.TouchAnimator touchAnimator) {
        this.touchAnimator = touchAnimator;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MediaPlayerPanel(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        n.g(context, "context");
    }

    public /* synthetic */ MediaPlayerPanel(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaPlayerPanel(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        n.g(context, "context");
    }
}
