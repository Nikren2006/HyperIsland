package miui.systemui.controlcenter.panel.secondary.media;

import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.MediaPanelBinding;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.secondary.MediaFromView;
import miui.systemui.controlcenter.panel.secondary.MediaPanelParams;
import miui.systemui.controlcenter.panel.secondary.MediaToView;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase;
import miui.systemui.controlcenter.widget.MaxHeightFrameLayout;
import miui.systemui.util.CommonUtils;
import miui.systemui.widget.FrameLayout;
import miui.systemui.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class MediaPanelController extends SecondaryPanelControllerBase<MediaPanelParams> implements MediaToView {
    private final MediaPanelAnimator animator;
    private final MediaPanelBinding binding;
    private final MediaPanelDelegate delegate;
    private final Lifecycle lifecycle;
    private final E0.a mainPanelController;
    private final ControlCenterSecondaryBinding secondaryBinding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaPanelController(ControlCenterSecondaryBinding secondaryBinding, MediaPanelBinding binding, E0.a mainPanelController, MediaPanelDelegate delegate, MediaPanelAnimator animator, @Qualifiers.ControlCenter Lifecycle lifecycle) {
        super(secondaryBinding, mainPanelController, delegate, animator, lifecycle);
        n.g(secondaryBinding, "secondaryBinding");
        n.g(binding, "binding");
        n.g(mainPanelController, "mainPanelController");
        n.g(delegate, "delegate");
        n.g(animator, "animator");
        n.g(lifecycle, "lifecycle");
        this.secondaryBinding = secondaryBinding;
        this.binding = binding;
        this.mainPanelController = mainPanelController;
        this.delegate = delegate;
        this.animator = animator;
        this.lifecycle = lifecycle;
    }

    private final void updateSize() {
        getItemFrame().setMaxHeight(CommonUtils.INSTANCE.getControlCenterDetailMaxHeight(getContext()));
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public int getSpecificWidth() {
        return -2;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public boolean getSupportWideMode() {
        return n.c(((MainPanelController) this.mainPanelController.get()).getUseSeparatedPanels(), Boolean.TRUE);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public boolean getUseSpecificWidth() {
        return getSupportWideMode();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        super.onConfigurationChanged(i2);
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        if (configUtils.dimensionsChanged(i2) || configUtils.orientationChanged(i2)) {
            updateSize();
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public void onSecondaryVisible(boolean z2, boolean z3) {
        MediaFromView fromView;
        ViewGroup contentView;
        MediaFromView fromView2;
        super.onSecondaryVisible(z2, z3);
        MediaPanelParams panelParams = getPanelParams();
        MainPanelItemViewHolder viewHolder = (panelParams == null || (fromView2 = panelParams.getFromView()) == null) ? null : fromView2.getViewHolder();
        if (viewHolder != null) {
            viewHolder.setIgnoreHolderAlpha(z2);
        }
        MediaPanelParams panelParams2 = getPanelParams();
        if (panelParams2 == null || (fromView = panelParams2.getFromView()) == null || (contentView = fromView.getContentView()) == null) {
            return;
        }
        CommonUtils.INSTANCE.setAlphaEx(contentView, z2 ? 0.0f : 1.0f);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public void setSpecificWidth(int i2) {
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public void setSupportWideMode(boolean z2) {
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public void setUseSpecificWidth(boolean z2) {
    }

    @Override // miui.systemui.controlcenter.panel.secondary.ChangeToView
    public MaxHeightFrameLayout getChangeFrame() {
        return getItemFrame();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.MediaToView
    public LinearLayout getContent() {
        LinearLayout mediaContent = this.binding.mediaContent;
        n.f(mediaContent, "mediaContent");
        return mediaContent;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.controlcenter.panel.secondary.ContentBgToView
    public FrameLayout getContentBg() {
        FrameLayout mediaPanelBg = this.binding.mediaPanelBg;
        n.f(mediaPanelBg, "mediaPanelBg");
        return mediaPanelBg;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.MediaToView
    public MaxHeightFrameLayout getItemFrame() {
        MaxHeightFrameLayout mediaContainer = this.binding.mediaContainer;
        n.f(mediaContainer, "mediaContainer");
        return mediaContainer;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public MaxHeightFrameLayout getSecondaryPanelContainer() {
        return getItemFrame();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public boolean prepareShow(MediaPanelParams mediaPanelParams) {
        updateSize();
        return super.prepareShow(mediaPanelParams);
    }
}
