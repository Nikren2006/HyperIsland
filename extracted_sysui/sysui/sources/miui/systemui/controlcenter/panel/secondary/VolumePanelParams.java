package miui.systemui.controlcenter.panel.secondary;

import miui.systemui.controlcenter.panel.secondary.volume.OriginalVolumeCallback;

/* JADX INFO: loaded from: classes.dex */
public final class VolumePanelParams implements SecondaryParams {
    private final SliderFromView fromView;
    private final float originalVolume;
    private final OriginalVolumeCallback originalVolumeCallback;

    public VolumePanelParams(SliderFromView sliderFromView, float f2, OriginalVolumeCallback originalVolumeCallback) {
        this.fromView = sliderFromView;
        this.originalVolume = f2;
        this.originalVolumeCallback = originalVolumeCallback;
    }

    public final SliderFromView getFromView() {
        return this.fromView;
    }

    public final float getOriginalVolume() {
        return this.originalVolume;
    }

    public final OriginalVolumeCallback getOriginalVolumeCallback() {
        return this.originalVolumeCallback;
    }
}
