package miui.systemui.controlcenter.dagger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleKt;
import androidx.recyclerview.widget.RecyclerView;
import g1.E;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.BrightnessPanelBinding;
import miui.systemui.controlcenter.databinding.ControlCenterBinding;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.CustomizerPanelBinding;
import miui.systemui.controlcenter.databinding.DetailPanelBinding;
import miui.systemui.controlcenter.databinding.MainPanelCustomizeHeaderBinding;
import miui.systemui.controlcenter.databinding.MainPanelHeaderBinding;
import miui.systemui.controlcenter.databinding.MainPanelMsgHeaderBinding;
import miui.systemui.controlcenter.databinding.MediaPanelBinding;
import miui.systemui.controlcenter.databinding.SmartHomePanelBinding;
import miui.systemui.controlcenter.databinding.VolumePanelBinding;
import miui.systemui.controlcenter.panel.main.brightness.BrightnessSliderController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"InflateParams"})
public final class ControlCenterViewModule {
    @ControlCenterScope
    @Qualifiers.LeftMainPanel
    public final RecyclerView createLeftMainPanel(@Qualifiers.ControlCenter LayoutInflater layoutInflater) {
        n.g(layoutInflater, "layoutInflater");
        View viewInflate = layoutInflater.inflate(R.layout.main_panel, (ViewGroup) null);
        n.e(viewInflate, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        return (RecyclerView) viewInflate;
    }

    @ControlCenterScope
    @Qualifiers.RightMainPanel
    public final RecyclerView createRightMainPanel(@Qualifiers.ControlCenter LayoutInflater layoutInflater) {
        n.g(layoutInflater, "layoutInflater");
        View viewInflate = layoutInflater.inflate(R.layout.main_panel, (ViewGroup) null);
        n.e(viewInflate, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        return (RecyclerView) viewInflate;
    }

    @ControlCenterScope
    @Qualifiers.Mirror
    public final Lifecycle provideBrightnessMirrorLifecycle(BrightnessSliderController brightnessSliderController) {
        n.g(brightnessSliderController, "brightnessSliderController");
        return brightnessSliderController.getLifecycle();
    }

    @ControlCenterScope
    public final BrightnessPanelBinding provideBrightnessPanelBinding(ControlCenterSecondaryBinding controlCenterSecondaryBinding) {
        n.g(controlCenterSecondaryBinding, "controlCenterSecondaryBinding");
        BrightnessPanelBinding brightnessPanel = controlCenterSecondaryBinding.brightnessPanel;
        n.f(brightnessPanel, "brightnessPanel");
        return brightnessPanel;
    }

    @ControlCenterScope
    @Qualifiers.ControlCenter
    public final Context provideContext(ControlCenterWindowViewImpl windowView) {
        n.g(windowView, "windowView");
        Context context = windowView.getContext();
        n.f(context, "getContext(...)");
        return context;
    }

    @ControlCenterScope
    public final ControlCenterBinding provideControlCenterBinding(ControlCenterWindowViewImpl windowView) {
        n.g(windowView, "windowView");
        ControlCenterBinding controlCenterBindingBind = ControlCenterBinding.bind(windowView);
        n.f(controlCenterBindingBind, "bind(...)");
        return controlCenterBindingBind;
    }

    @ControlCenterScope
    @Qualifiers.ControlCenter
    public final Lifecycle provideControlCenterLifecycle(ControlCenterWindowViewImpl windowView) {
        n.g(windowView, "windowView");
        return windowView.getLifecycle();
    }

    @ControlCenterScope
    public final ControlCenterSecondaryBinding provideControlCenterSecondaryBinding(@Qualifiers.ControlCenter LayoutInflater layoutInflater) {
        n.g(layoutInflater, "layoutInflater");
        ControlCenterSecondaryBinding controlCenterSecondaryBindingInflate = ControlCenterSecondaryBinding.inflate(layoutInflater);
        n.f(controlCenterSecondaryBindingInflate, "inflate(...)");
        return controlCenterSecondaryBindingInflate;
    }

    @ControlCenterScope
    @Qualifiers.ControlCenter
    public final E provideCoroutineScope(@Qualifiers.ControlCenter Lifecycle lifecycle) {
        n.g(lifecycle, "lifecycle");
        return LifecycleKt.getCoroutineScope(lifecycle);
    }

    @ControlCenterScope
    public final CustomizerPanelBinding provideCustomizerBinding(ControlCenterBinding controlCenterBinding) {
        n.g(controlCenterBinding, "controlCenterBinding");
        CustomizerPanelBinding customizerPanel = controlCenterBinding.customizerPanel;
        n.f(customizerPanel, "customizerPanel");
        return customizerPanel;
    }

    @ControlCenterScope
    public final DetailPanelBinding provideDetailPanelBinding(ControlCenterSecondaryBinding controlCenterSecondaryBinding) {
        n.g(controlCenterSecondaryBinding, "controlCenterSecondaryBinding");
        DetailPanelBinding detailPanel = controlCenterSecondaryBinding.detailPanel;
        n.f(detailPanel, "detailPanel");
        return detailPanel;
    }

    @ControlCenterScope
    @Qualifiers.ControlCenter
    public final LayoutInflater provideLayoutInflater(@Qualifiers.ControlCenter Context context) {
        n.g(context, "context");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        n.f(layoutInflaterFrom, "from(...)");
        return layoutInflaterFrom;
    }

    @ControlCenterScope
    @Qualifiers.ControlCenter
    public final LifecycleCoroutineScope provideLifecycleCoroutineScope(@Qualifiers.ControlCenter Lifecycle lifecycle) {
        n.g(lifecycle, "lifecycle");
        return LifecycleKt.getCoroutineScope(lifecycle);
    }

    @ControlCenterScope
    @Qualifiers.MainPanelContainer
    public final LinearLayout provideMainPanelContainer(ControlCenterBinding controlCenterBinding) {
        n.g(controlCenterBinding, "controlCenterBinding");
        miui.systemui.widget.LinearLayout mainPanelContainer = controlCenterBinding.mainPanelContainer;
        n.f(mainPanelContainer, "mainPanelContainer");
        return mainPanelContainer;
    }

    @ControlCenterScope
    public final MainPanelCustomizeHeaderBinding provideMainPanelCustomizeHeaderBinding(@Qualifiers.ControlCenter LayoutInflater layoutInflater) {
        n.g(layoutInflater, "layoutInflater");
        MainPanelCustomizeHeaderBinding mainPanelCustomizeHeaderBindingInflate = MainPanelCustomizeHeaderBinding.inflate(layoutInflater);
        n.f(mainPanelCustomizeHeaderBindingInflate, "inflate(...)");
        return mainPanelCustomizeHeaderBindingInflate;
    }

    @ControlCenterScope
    public final MainPanelHeaderBinding provideMainPanelHeaderBinding(ControlCenterBinding controlCenterBinding) {
        n.g(controlCenterBinding, "controlCenterBinding");
        MainPanelHeaderBinding mainPanelHeader = controlCenterBinding.mainPanelHeader;
        n.f(mainPanelHeader, "mainPanelHeader");
        return mainPanelHeader;
    }

    @ControlCenterScope
    public final MainPanelMsgHeaderBinding provideMainPanelMsgHeaderBinding(@Qualifiers.ControlCenter LayoutInflater layoutInflater) {
        n.g(layoutInflater, "layoutInflater");
        MainPanelMsgHeaderBinding mainPanelMsgHeaderBindingInflate = MainPanelMsgHeaderBinding.inflate(layoutInflater);
        n.f(mainPanelMsgHeaderBindingInflate, "inflate(...)");
        return mainPanelMsgHeaderBindingInflate;
    }

    @ControlCenterScope
    public final MediaPanelBinding provideMediaPanelBinding(ControlCenterSecondaryBinding controlCenterSecondaryBinding) {
        n.g(controlCenterSecondaryBinding, "controlCenterSecondaryBinding");
        MediaPanelBinding mediaPanel = controlCenterSecondaryBinding.mediaPanel;
        n.f(mediaPanel, "mediaPanel");
        return mediaPanel;
    }

    @ControlCenterScope
    public final SmartHomePanelBinding provideSmartHomeBinding(ControlCenterBinding controlCenterBinding) {
        n.g(controlCenterBinding, "controlCenterBinding");
        SmartHomePanelBinding smartHomePanel = controlCenterBinding.smartHomePanel;
        n.f(smartHomePanel, "smartHomePanel");
        return smartHomePanel;
    }

    @ControlCenterScope
    public final VolumePanelBinding provideVolumePanelBinding(ControlCenterSecondaryBinding controlCenterSecondaryBinding) {
        n.g(controlCenterSecondaryBinding, "controlCenterSecondaryBinding");
        VolumePanelBinding volumePanel = controlCenterSecondaryBinding.volumePanel;
        n.f(volumePanel, "volumePanel");
        return volumePanel;
    }

    @ControlCenterScope
    @Qualifiers.WindowView
    public final ControlCenterWindowViewImpl provideWindowView(ControlCenterWindowViewImpl windowView) {
        n.g(windowView, "windowView");
        return windowView;
    }
}
