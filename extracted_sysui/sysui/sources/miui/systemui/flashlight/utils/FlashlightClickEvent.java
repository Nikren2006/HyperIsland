package miui.systemui.flashlight.utils;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes3.dex */
@EventID(id = "click")
public final class FlashlightClickEvent implements FlashlightEvents {

    @EventKey(key = "after_click_status")
    private final String afterClickStatus;

    @EventKey(key = "button_location")
    private final String buttonLocation;

    @EventKey(key = "model_type")
    private final String modelType;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phoneType;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screenType;

    @EventKey(key = "tip")
    private final String tip;

    public FlashlightClickEvent(String modelType, String phoneType, String screenType, String afterClickStatus, String buttonLocation, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(afterClickStatus, "afterClickStatus");
        n.g(buttonLocation, "buttonLocation");
        n.g(tip, "tip");
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.afterClickStatus = afterClickStatus;
        this.buttonLocation = buttonLocation;
        this.tip = tip;
    }

    public static /* synthetic */ FlashlightClickEvent copy$default(FlashlightClickEvent flashlightClickEvent, String str, String str2, String str3, String str4, String str5, String str6, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = flashlightClickEvent.modelType;
        }
        if ((i2 & 2) != 0) {
            str2 = flashlightClickEvent.phoneType;
        }
        String str7 = str2;
        if ((i2 & 4) != 0) {
            str3 = flashlightClickEvent.screenType;
        }
        String str8 = str3;
        if ((i2 & 8) != 0) {
            str4 = flashlightClickEvent.afterClickStatus;
        }
        String str9 = str4;
        if ((i2 & 16) != 0) {
            str5 = flashlightClickEvent.buttonLocation;
        }
        String str10 = str5;
        if ((i2 & 32) != 0) {
            str6 = flashlightClickEvent.tip;
        }
        return flashlightClickEvent.copy(str, str7, str8, str9, str10, str6);
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

    public final String component4() {
        return this.afterClickStatus;
    }

    public final String component5() {
        return this.buttonLocation;
    }

    public final String component6() {
        return this.tip;
    }

    public final FlashlightClickEvent copy(String modelType, String phoneType, String screenType, String afterClickStatus, String buttonLocation, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(afterClickStatus, "afterClickStatus");
        n.g(buttonLocation, "buttonLocation");
        n.g(tip, "tip");
        return new FlashlightClickEvent(modelType, phoneType, screenType, afterClickStatus, buttonLocation, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlashlightClickEvent)) {
            return false;
        }
        FlashlightClickEvent flashlightClickEvent = (FlashlightClickEvent) obj;
        return n.c(this.modelType, flashlightClickEvent.modelType) && n.c(this.phoneType, flashlightClickEvent.phoneType) && n.c(this.screenType, flashlightClickEvent.screenType) && n.c(this.afterClickStatus, flashlightClickEvent.afterClickStatus) && n.c(this.buttonLocation, flashlightClickEvent.buttonLocation) && n.c(this.tip, flashlightClickEvent.tip);
    }

    public final String getAfterClickStatus() {
        return this.afterClickStatus;
    }

    public final String getButtonLocation() {
        return this.buttonLocation;
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
        return (((((((((this.modelType.hashCode() * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.afterClickStatus.hashCode()) * 31) + this.buttonLocation.hashCode()) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "FlashlightClickEvent(modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", afterClickStatus=" + this.afterClickStatus + ", buttonLocation=" + this.buttonLocation + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ FlashlightClickEvent(String str, String str2, String str3, String str4, String str5, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, (i2 & 32) != 0 ? TrackUtilsKt.EVENT_CLICK_TIP : str6);
    }
}
