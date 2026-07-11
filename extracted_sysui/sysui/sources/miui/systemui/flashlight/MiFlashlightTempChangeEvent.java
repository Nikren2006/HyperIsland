package miui.systemui.flashlight;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightTempChangeEvent extends MiFlashlightEvent {
    public static final MiFlashlightTempChangeEvent INSTANCE = new MiFlashlightTempChangeEvent();
    private static float progress = 1.0f;

    private MiFlashlightTempChangeEvent() {
        super(null);
    }

    public final float getProgress() {
        return progress;
    }

    public final void setProgress(float f2) {
        progress = f2;
    }
}
