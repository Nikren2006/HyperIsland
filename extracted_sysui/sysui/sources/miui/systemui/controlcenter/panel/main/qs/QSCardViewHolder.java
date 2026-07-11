package miui.systemui.controlcenter.panel.main.qs;

import android.view.View;
import android.view.ViewGroup;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.panel.main.recyclerview.RotationItemViewHolder;
import miui.systemui.controlcenter.panel.secondary.DetailFromView;
import miui.systemui.controlcenter.qs.tileview.QSCardItemIconView;
import miui.systemui.controlcenter.qs.tileview.QSCardItemView;
import miui.systemui.controlcenter.qs.tileview.QSItemView;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes.dex */
public final class QSCardViewHolder extends RotationItemViewHolder implements DetailFromView {
    private final Void iconFrame;
    private final QSItemView view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSCardViewHolder(QSItemView view) {
        super(view.asView());
        n.g(view, "view");
        this.view = view;
        getQsItemView().setTouchAnimator(this);
    }

    private final QSCardItemView getQsItemView() {
        View view = this.itemView;
        n.e(view, "null cannot be cast to non-null type miui.systemui.controlcenter.qs.tileview.QSCardItemView");
        return (QSCardItemView) view;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.DetailFromView
    public float getCornerRadius() {
        return getQsItemView().getCornerRadius();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.DetailFromView
    public /* bridge */ /* synthetic */ View getIconFrame() {
        return (View) m90getIconFrame();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.DetailFromView
    public ViewGroup getItemFrame() {
        View view = this.itemView;
        n.e(view, "null cannot be cast to non-null type android.view.ViewGroup");
        return (ViewGroup) view;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
    public void onConfigurationChanged(int i2) {
        this.view.onConfigurationChanged(i2);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
    public void onModeChanged(MainPanelController.Mode mode, boolean z2) {
        n.g(mode, "mode");
        this.view.onModeChanged(mode, z2);
    }

    public final void onTextModeChanged(QSListController.TextMode mode, boolean z2) {
        n.g(mode, "mode");
        this.view.onTextModeChanged(mode, z2);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.DetailFromView
    public void setCornerRadius(float f2) {
        getQsItemView().setCornerRadius(f2);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
    public AnimConfig updateAnimConfig(boolean z2, boolean z3, int i2, int i3) {
        return getAnimatorConfigHelper().updateAnimEase(this, z2, z3, i2, i3);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
    public void updateBlendBlur() {
    }

    @Override // miui.systemui.controlcenter.panel.secondary.DetailFromView
    public QSCardItemIconView getIcon() {
        return getQsItemView().getIcon();
    }

    /* JADX INFO: renamed from: getIconFrame, reason: collision with other method in class */
    public Void m90getIconFrame() {
        return this.iconFrame;
    }
}
