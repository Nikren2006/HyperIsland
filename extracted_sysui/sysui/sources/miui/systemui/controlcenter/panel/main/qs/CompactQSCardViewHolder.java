package miui.systemui.controlcenter.panel.main.qs;

import H0.s;
import android.content.Context;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.databinding.CompactQsCardContainerBinding;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelFrameCallback;
import miui.systemui.controlcenter.panel.main.qs.QSItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelAdapter;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.qs.tileview.QSTileItemIconView;
import miui.systemui.controlcenter.qs.tileview.QSTileItemView;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public final class CompactQSCardViewHolder extends MainPanelItemViewHolder {
    private final CompactQsCardContainerBinding binding;
    private final QSItemViewHolder.Factory qsItemViewHolderFactory;
    private final QSItemViewHolder qsTileHolder1;
    private final QSItemViewHolder qsTileHolder2;
    private final QSTileItemView qsTileView1;
    private final QSTileItemView qsTileView2;
    private float scaleFactor;
    private final Context sysUIContext;

    @ControlCenterScope
    public static final class Factory {
        private final QSItemViewHolder.Factory qsItemViewHolderFactory;
        private final Context sysUIContext;

        public Factory(QSItemViewHolder.Factory qsItemViewHolderFactory, @SystemUI Context sysUIContext) {
            n.g(qsItemViewHolderFactory, "qsItemViewHolderFactory");
            n.g(sysUIContext, "sysUIContext");
            this.qsItemViewHolderFactory = qsItemViewHolderFactory;
            this.sysUIContext = sysUIContext;
        }

        public final CompactQSCardViewHolder create(CompactQsCardContainerBinding binding) {
            n.g(binding, "binding");
            return new CompactQSCardViewHolder(binding, this.qsItemViewHolderFactory, this.sysUIContext);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CompactQSCardViewHolder(CompactQsCardContainerBinding binding, QSItemViewHolder.Factory qsItemViewHolderFactory, Context sysUIContext) {
        n.g(binding, "binding");
        n.g(qsItemViewHolderFactory, "qsItemViewHolderFactory");
        n.g(sysUIContext, "sysUIContext");
        LinearLayout root = binding.getRoot();
        n.f(root, "getRoot(...)");
        super(root);
        this.binding = binding;
        this.qsItemViewHolderFactory = qsItemViewHolderFactory;
        this.sysUIContext = sysUIContext;
        QSTileItemView root2 = binding.qsTile1.getRoot();
        n.f(root2, "getRoot(...)");
        this.qsTileView1 = root2;
        QSTileItemView root3 = binding.qsTile2.getRoot();
        n.f(root3, "getRoot(...)");
        this.qsTileView2 = root3;
        Context context = this.itemView.getContext();
        n.f(context, "getContext(...)");
        root2.init(new QSTileItemIconView(context, sysUIContext, true));
        s sVar = s.f314a;
        this.qsTileHolder1 = qsItemViewHolderFactory.create(root2);
        Context context2 = this.itemView.getContext();
        n.f(context2, "getContext(...)");
        root3.init(new QSTileItemIconView(context2, sysUIContext, true));
        this.qsTileHolder2 = qsItemViewHolderFactory.create(root3);
    }

    private static final void onFrameCallback$update(CompactQSCardViewHolder compactQSCardViewHolder, QSItemViewHolder qSItemViewHolder) {
        qSItemViewHolder.setHolderScale(compactQSCardViewHolder.getHolderScale());
        qSItemViewHolder.setHolderAlpha(compactQSCardViewHolder.getHolderAlpha());
        qSItemViewHolder.setHolderTransX(compactQSCardViewHolder.getHolderTransX());
        qSItemViewHolder.setHolderTransY(compactQSCardViewHolder.getHolderTransY());
        qSItemViewHolder.setExpandAlpha(compactQSCardViewHolder.getExpandAlpha());
        qSItemViewHolder.setExpandScaleRatio(compactQSCardViewHolder.getExpandScaleRatio());
        qSItemViewHolder.setShrinkX(compactQSCardViewHolder.getShrinkX());
        qSItemViewHolder.setShrinkY(compactQSCardViewHolder.getShrinkY());
        qSItemViewHolder.setCenterX$miui_controlcenter_release(compactQSCardViewHolder.getCenterX$miui_controlcenter_release());
        qSItemViewHolder.setCenterY$miui_controlcenter_release(compactQSCardViewHolder.getCenterY$miui_controlcenter_release());
        qSItemViewHolder.setShrinkCenterY$miui_controlcenter_release(compactQSCardViewHolder.getShrinkCenterY$miui_controlcenter_release());
        qSItemViewHolder.setShrinkExpandTransY(compactQSCardViewHolder.getShrinkExpandTransY());
        qSItemViewHolder.setSpreadScale(compactQSCardViewHolder.getSpreadScale());
        qSItemViewHolder.onFrameCallback();
    }

    public final QSItemViewHolder getQsTileHolder1() {
        return this.qsTileHolder1;
    }

    public final QSItemViewHolder getQsTileHolder2() {
        return this.qsTileHolder2;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
    public float getScaleFactor() {
        return this.scaleFactor;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
    public void onConfigurationChanged(int i2) {
        this.qsTileHolder1.onConfigurationChanged(i2);
        this.qsTileHolder2.onConfigurationChanged(i2);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder, miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
    public void onFrameCallback() {
        super.onFrameCallback();
        onFrameCallback$update(this, this.qsTileHolder1);
        onFrameCallback$update(this, this.qsTileHolder2);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
    public void onModeChanged(MainPanelController.Mode mode, boolean z2) {
        n.g(mode, "mode");
        this.qsTileHolder1.onModeChanged(mode, z2);
        this.qsTileHolder2.onModeChanged(mode, z2);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
    public void onViewAttachedToWindow(MainPanelAdapter mainPanelAdapter, MainPanelFrameCallback frameCallback) {
        n.g(mainPanelAdapter, "mainPanelAdapter");
        n.g(frameCallback, "frameCallback");
        this.qsTileHolder1.onViewAttachedToWindow(mainPanelAdapter, frameCallback);
        this.qsTileHolder2.onViewAttachedToWindow(mainPanelAdapter, frameCallback);
        super.onViewAttachedToWindow(mainPanelAdapter, frameCallback);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
    public void onViewDetachedFromWindow() {
        super.onViewDetachedFromWindow();
        this.qsTileHolder1.onViewDetachedFromWindow();
        this.qsTileHolder2.onViewDetachedFromWindow();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
    public void setScaleFactor(float f2) {
        this.scaleFactor = f2;
        this.qsTileHolder1.setScaleFactor(f2);
        this.qsTileHolder2.setScaleFactor(f2);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
    public void updateBlendBlur() {
        this.qsTileHolder1.updateBlendBlur();
        this.qsTileHolder2.updateBlendBlur();
    }
}
