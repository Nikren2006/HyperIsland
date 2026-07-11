package miui.systemui.controlcenter.panel.main.qs;

import android.view.View;
import android.view.ViewGroup;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.panel.main.recyclerview.ScaleItemViewHolder;
import miui.systemui.controlcenter.panel.secondary.DetailFromView;
import miui.systemui.controlcenter.qs.tileview.QSItemView;
import miui.systemui.controlcenter.qs.tileview.QSTileItemIconView;
import miui.systemui.controlcenter.qs.tileview.QSTileItemView;
import miui.systemui.widget.FrameLayout;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes.dex */
public final class QSItemViewHolder extends ScaleItemViewHolder implements DetailFromView {
    private final QSItemView view;

    @ControlCenterScope
    public static final class Factory {
        private final E0.a mainPanelController;

        public Factory(E0.a mainPanelController) {
            n.g(mainPanelController, "mainPanelController");
            this.mainPanelController = mainPanelController;
        }

        public final QSItemViewHolder create(QSItemView view) {
            n.g(view, "view");
            Object obj = this.mainPanelController.get();
            n.f(obj, "get(...)");
            return new QSItemViewHolder(view, (MainPanelController) obj);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSItemViewHolder(QSItemView view, MainPanelController mainPanelController) {
        super(view.asView());
        n.g(view, "view");
        n.g(mainPanelController, "mainPanelController");
        this.view = view;
        getQsItemView().setMainPanelController(mainPanelController);
        getQsItemView().setTouchAnimator(this);
    }

    private final QSTileItemView getQsItemView() {
        View view = this.itemView;
        n.e(view, "null cannot be cast to non-null type miui.systemui.controlcenter.qs.tileview.QSTileItemView");
        return (QSTileItemView) view;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.DetailFromView
    public float getCornerRadius() {
        return getQsItemView().getIcon().getCornerRadius();
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

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
    public void onStyleChanged(MainPanelController.Style style) {
        n.g(style, "style");
        this.view.onStyleChanged(style);
    }

    public final void onTextModeChanged(QSListController.TextMode mode, boolean z2) {
        n.g(mode, "mode");
        this.view.onTextModeChanged(mode, z2);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
    public void recycle() {
        super.recycle();
        this.view.recycle();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.DetailFromView
    public void setCornerRadius(float f2) {
        getQsItemView().getIcon().setCornerRadius(f2);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
    public void update(boolean z2, float f2) {
        getAnimatorConfigHelper().updateTranY(this, z2, f2);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
    public AnimConfig updateAnimConfig(boolean z2, boolean z3, int i2, int i3) {
        return getAnimatorConfigHelper().updateAnimEase(this, z2, z3, i2, i3);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
    public void updateBlendBlur() {
    }

    @Override // miui.systemui.controlcenter.panel.secondary.DetailFromView
    public QSTileItemIconView getIcon() {
        return getQsItemView().getIcon();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.DetailFromView
    public FrameLayout getIconFrame() {
        FrameLayout iconFrame = getQsItemView().getBinding().iconFrame;
        n.f(iconFrame, "iconFrame");
        return iconFrame;
    }
}
