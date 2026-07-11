package miui.systemui.controlcenter.panel.secondary;

/* JADX INFO: loaded from: classes.dex */
public final class BrightnessPanelParams implements SecondaryParams {
    private final SliderFromView fromView;

    public BrightnessPanelParams(SliderFromView sliderFromView) {
        this.fromView = sliderFromView;
    }

    public final SliderFromView getFromView() {
        return this.fromView;
    }
}
