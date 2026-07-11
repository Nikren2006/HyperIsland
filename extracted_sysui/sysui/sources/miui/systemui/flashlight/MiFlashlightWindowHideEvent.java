package miui.systemui.flashlight;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightWindowHideEvent extends MiFlashlightEvent {
    private final int flashlightLayoutHashCode;

    public MiFlashlightWindowHideEvent() {
        this(0, 1, null);
    }

    public final int getFlashlightLayoutHashCode() {
        return this.flashlightLayoutHashCode;
    }

    public MiFlashlightWindowHideEvent(int i2) {
        super(null);
        this.flashlightLayoutHashCode = i2;
    }

    public /* synthetic */ MiFlashlightWindowHideEvent(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2);
    }
}
