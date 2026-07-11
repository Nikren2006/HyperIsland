package miui.systemui.controlcenter.panel.secondary;

/* JADX INFO: loaded from: classes.dex */
public final class MediaPanelParams implements SecondaryParams {
    private final MediaFromView fromView;

    public MediaPanelParams(MediaFromView mediaFromView) {
        this.fromView = mediaFromView;
    }

    public final MediaFromView getFromView() {
        return this.fromView;
    }
}
