package miui.systemui.controlcenter.panel.secondary.detail;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.lifecycle.Lifecycle;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.DetailPanelBinding;
import miui.systemui.controlcenter.panel.secondary.DetailPanelParams;
import miui.systemui.controlcenter.panel.secondary.DetailToView;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase;
import miui.systemui.controlcenter.panel.secondary.SecondaryParamsKt;
import miui.systemui.controlcenter.widget.MaxHeightLinearLayout;
import miui.systemui.util.CommonUtils;
import miui.systemui.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class DetailPanelController extends SecondaryPanelControllerBase<DetailPanelParams> implements DetailToView {
    private final DetailPanelAnimator animator;
    private final DetailPanelBinding binding;
    private final DetailPanelDelegate delegate;
    private final Lifecycle lifecycle;
    private final E0.a mainPanelController;
    private final ControlCenterSecondaryBinding secondaryBinding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DetailPanelController(ControlCenterSecondaryBinding secondaryBinding, DetailPanelBinding binding, E0.a mainPanelController, DetailPanelDelegate delegate, DetailPanelAnimator animator, @Qualifiers.ControlCenter Lifecycle lifecycle) {
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
        getContainer().setMaxHeight(CommonUtils.INSTANCE.getControlCenterDetailMaxHeight(getContext()));
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.controlcenter.panel.secondary.ContentBgToView
    public float getContentBgColor() {
        return super.getContentBgColor();
    }

    public final DetailPanelDelegate getDelegate() {
        return this.delegate;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.FakeChangeToView
    public ViewGroup getFakeFrame() {
        return this.delegate.getTilesDelegate().getFakeView();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public boolean getRightOrLeft() {
        DetailPanelParams panelParams = getPanelParams();
        boolean z2 = false;
        if (panelParams != null && !panelParams.getRightOrLeft()) {
            z2 = true;
        }
        return !z2;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public int getSpecificHeight() {
        return CommonUtils.INSTANCE.getControlCenterDetailMaxHeight(getContext());
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public boolean getUseSpecificHeight() {
        DetailPanelParams panelParams = getPanelParams();
        return panelParams != null && panelParams.getUseSpecificHeight();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        super.onConfigurationChanged(i2);
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        if (configUtils.orientationChanged(i2) || configUtils.dimensionsChanged(i2)) {
            updateSize();
        }
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        updateSize();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.controlcenter.panel.secondary.ContentBgToView
    public void setContentBgColor(float f2) {
    }

    @Override // miui.systemui.controlcenter.panel.secondary.FakeChangeToView
    public void setFakeFrame(ViewGroup viewGroup) {
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public void setRightOrLeft(boolean z2) {
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public void setSpecificHeight(int i2) {
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public void setUseSpecificHeight(boolean z2) {
    }

    @Override // miui.systemui.controlcenter.panel.secondary.ChangeToView
    public MaxHeightLinearLayout getChangeFrame() {
        return getContainer();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.DetailToView
    public MaxHeightLinearLayout getContainer() {
        MaxHeightLinearLayout detailContainer = this.binding.detailContainer;
        n.f(detailContainer, "detailContainer");
        return detailContainer;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.DetailToView
    public FrameLayout getContent() {
        FrameLayout content = this.binding.content;
        n.f(content, "content");
        return content;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase, miui.systemui.controlcenter.panel.secondary.ContentBgToView
    public MaxHeightLinearLayout getContentBg() {
        return getContainer();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.DetailToView
    public LinearLayout getScaleContent() {
        LinearLayout scaleContent = this.binding.scaleContent;
        n.f(scaleContent, "scaleContent");
        return scaleContent;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public MaxHeightLinearLayout getSecondaryPanelContainer() {
        return getContainer();
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelControllerBase
    public boolean prepareShow(DetailPanelParams detailPanelParams) {
        if (detailPanelParams == null || detailPanelParams.getAdapter() == null) {
            return false;
        }
        Log.i(getTag(), "prepareShow " + SecondaryParamsKt.from(detailPanelParams.getAdapter()));
        return super.prepareShow(detailPanelParams);
    }
}
