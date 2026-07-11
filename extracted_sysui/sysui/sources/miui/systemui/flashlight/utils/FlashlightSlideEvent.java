package miui.systemui.flashlight.utils;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes3.dex */
@EventID(id = "slide")
public final class FlashlightSlideEvent implements FlashlightEvents {

    @EventKey(key = "after_value")
    private final int afterValue;

    @EventKey(key = "before_value")
    private final int beforeValue;

    @EventKey(key = "model_type")
    private final String modelType;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phoneType;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screenType;

    @EventKey(key = "tip")
    private final String tip;

    public FlashlightSlideEvent(String modelType, String phoneType, String screenType, int i2, int i3, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(tip, "tip");
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.beforeValue = i2;
        this.afterValue = i3;
        this.tip = tip;
    }

    public static /* synthetic */ FlashlightSlideEvent copy$default(FlashlightSlideEvent flashlightSlideEvent, String str, String str2, String str3, int i2, int i3, String str4, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = flashlightSlideEvent.modelType;
        }
        if ((i4 & 2) != 0) {
            str2 = flashlightSlideEvent.phoneType;
        }
        String str5 = str2;
        if ((i4 & 4) != 0) {
            str3 = flashlightSlideEvent.screenType;
        }
        String str6 = str3;
        if ((i4 & 8) != 0) {
            i2 = flashlightSlideEvent.beforeValue;
        }
        int i5 = i2;
        if ((i4 & 16) != 0) {
            i3 = flashlightSlideEvent.afterValue;
        }
        int i6 = i3;
        if ((i4 & 32) != 0) {
            str4 = flashlightSlideEvent.tip;
        }
        return flashlightSlideEvent.copy(str, str5, str6, i5, i6, str4);
    }

    public final String component1() {
        return this.modelType;
    }

    public final String component2() {
        return this.phoneType;
    }

    public final String component3() {
        return this.screenType;
    }

    public final int component4() {
        return this.beforeValue;
    }

    public final int component5() {
        return this.afterValue;
    }

    public final String component6() {
        return this.tip;
    }

    public final FlashlightSlideEvent copy(String modelType, String phoneType, String screenType, int i2, int i3, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(tip, "tip");
        return new FlashlightSlideEvent(modelType, phoneType, screenType, i2, i3, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlashlightSlideEvent)) {
            return false;
        }
        FlashlightSlideEvent flashlightSlideEvent = (FlashlightSlideEvent) obj;
        return n.c(this.modelType, flashlightSlideEvent.modelType) && n.c(this.phoneType, flashlightSlideEvent.phoneType) && n.c(this.screenType, flashlightSlideEvent.screenType) && this.beforeValue == flashlightSlideEvent.beforeValue && this.afterValue == flashlightSlideEvent.afterValue && n.c(this.tip, flashlightSlideEvent.tip);
    }

    public final int getAfterValue() {
        return this.afterValue;
    }

    public final int getBeforeValue() {
        return this.beforeValue;
    }

    public final String getModelType() {
        return this.modelType;
    }

    public final String getPhoneType() {
        return this.phoneType;
    }

    public final String getScreenType() {
        return this.screenType;
    }

    public final String getTip() {
        return this.tip;
    }

    public int hashCode() {
        return (((((((((this.modelType.hashCode() * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + Integer.hashCode(this.beforeValue)) * 31) + Integer.hashCode(this.afterValue)) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "FlashlightSlideEvent(modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", beforeValue=" + this.beforeValue + ", afterValue=" + this.afterValue + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ FlashlightSlideEvent(String str, String str2, String str3, int i2, int i3, String str4, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, i2, i3, (i4 & 32) != 0 ? TrackUtilsKt.EVENT_SLIDE_TIP : str4);
    }
}
