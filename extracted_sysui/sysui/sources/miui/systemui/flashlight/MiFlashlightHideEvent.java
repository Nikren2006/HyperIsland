package miui.systemui.flashlight;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightHideEvent extends MiFlashlightEvent {
    private final int flashlightLayoutHashCode;

    public MiFlashlightHideEvent() {
        this(0, 1, null);
    }

    public final int getFlashlightLayoutHashCode() {
        return this.flashlightLayoutHashCode;
    }

    public MiFlashlightHideEvent(int i2) {
        super(null);
        this.flashlightLayoutHashCode = i2;
    }

    public /* synthetic */ MiFlashlightHideEvent(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2);
    }
}
