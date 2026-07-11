package miui.systemui.controlcenter.panel.secondary.volume;

import android.view.KeyEvent;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.VolumePanelBinding;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase;
import miui.systemui.controlcenter.panel.secondary.SliderFromView;
import miui.systemui.controlcenter.panel.secondary.VolumePanelParams;
import miui.systemui.controlcenter.panel.secondary.VolumeToView;
import miui.systemui.util.CommonUtils;
import miui.systemui.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class VolumePanelController extends SecondaryPanelControllerBase<VolumePanelParams> implements VolumeToView {
    private float _containerRadius;
    private float _progressRadius;
    private final VolumePanelAnimator animator;
    private final VolumePanelBinding binding;
    private final VolumePanelDelegate delegate;
    private final Lifecycle lifecycle;
    private boolean listening;
    private final E0.a mainPanelController;
    private final ControlCenterSecondaryBinding secondaryBinding;
    private boolean useDefaultContainerConstraint;
    private int volumeKeyPressCount;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VolumePanelController(ControlCenterSecondaryBinding secondaryBinding, VolumePanelBinding binding, E0.a mainPanelController, VolumePanelDelegate delegate, VolumePanelAnimator animator, @Qualifiers.ControlCenter Lifecycle lifecycle) {
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

    private final boolean isLongPressVolumeKey() {
        return this.volumeKeyPressCount > 2;
    }

    private final void performKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 24 || keyEvent.getKeyCode() == 25) {
            this.delegate.performKeyAnim(keyEvent);
            if (keyEvent.getAction() == 1) {
                this.volumeKeyPressCount = 0;
            } else if (keyEvent.getAction() == 0) {
                this.volumeKeyPressCount++;
                this.delegate.performHapticFeedback(isLongPressVolumeKey());
            }
        }
    }

    private final void updateSize() {
        float dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.detail_panel_background_corner_radius);
        this._containerRadius = dimensionPixelSize;
        setCornerRadius(dimensionPixelSize);
        this._progressRadius = getResources().getDimensionPixelSize(R.dimen.brightness_panel_slider_clip_radius_large);
        setProgressRadius(getCornerRadius());
    }

    @Override // miui.systemui.controlcenter.panel.secondary.VolumeToView
    public float getCornerRadius() {
        return this._containerRadius;
    }

    public final VolumePanelDelegate getDelegate() {
        return this.delegate;
    }

    public final boolean getListening() {
        return this.listening;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.VolumeToView
    public float getProgressRadius() {
        return this._progressRadius;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public boolean getUseDefaultContainerConstraint() {
        return this.useDefaultContainerConstraint;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        super.onConfigurationChanged(i2);
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        if (configUtils.orientationChanged(i2) || configUtils.dimensionsChanged(i2)) {
            updateSize();
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public Boolean onKeyEvent(KeyEvent event) {
        n.g(event, "event");
        if (event.getAction() == 1 && (event.getKeyCode() == 4 || event.getKeyCode() == 82)) {
            hide();
            return Boolean.TRUE;
        }
        performKeyEvent(event);
        return ((MainPanelController) this.mainPanelController.get()).onKeyEvent(event);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public void onSecondaryVisible(boolean z2, boolean z3) {
        SliderFromView fromView;
        ViewGroup content;
        SliderFromView fromView2;
        ViewGroup content2;
        super.onSecondaryVisible(z2, z3);
        if (z2) {
            VolumePanelParams panelParams = getPanelParams();
            if (panelParams == null || (fromView2 = panelParams.getFromView()) == null || (content2 = fromView2.getContent()) == null) {
                return;
            }
            CommonUtils.INSTANCE.setInvisible(content2);
            return;
        }
        VolumePanelParams panelParams2 = getPanelParams();
        if (panelParams2 == null || (fromView = panelParams2.getFromView()) == null || (content = fromView.getContent()) == null) {
            return;
        }
        CommonUtils.INSTANCE.setVisible(content);
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.controlcenter.panel.SecondaryPanelRouter.SecondaryPanel
    public void prepareHide() {
        OriginalVolumeCallback originalVolumeCallback;
        super.prepareHide();
        VolumePanelParams panelParams = getPanelParams();
        if (panelParams == null || (originalVolumeCallback = panelParams.getOriginalVolumeCallback()) == null) {
            return;
        }
        originalVolumeCallback.invoke(this.delegate.getOriginalVolumeFromPanel());
    }

    @Override // miui.systemui.controlcenter.panel.secondary.VolumeToView
    public void setCornerRadius(float f2) {
    }

    public final void setListening(boolean z2) {
        if (this.listening == z2) {
            return;
        }
        this.listening = z2;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.VolumeToView
    public void setProgressRadius(float f2) {
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public void setUseDefaultContainerConstraint(boolean z2) {
        this.useDefaultContainerConstraint = z2;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public FrameLayout getSecondaryPanelContainer() {
        FrameLayout volumeContainer = this.binding.volumeContainer;
        n.f(volumeContainer, "volumeContainer");
        return volumeContainer;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public boolean prepareShow(VolumePanelParams volumePanelParams) {
        updateSize();
        return super.prepareShow(volumePanelParams);
    }
}
