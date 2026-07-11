package miui.systemui.flashlight.utils;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes3.dex */
@EventID(id = TrackUtilsKt.EVENT_ENTER)
public final class FlashlightEnterEvent implements FlashlightEvents {

    @EventKey(key = "enter_brightness")
    private final int enterBrightness;

    @EventKey(key = "enter_way")
    private final String enterWay;

    @EventKey(key = "model_type")
    private final String modelType;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phoneType;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screenType;

    @EventKey(key = "tip")
    private final String tip;

    public FlashlightEnterEvent(String modelType, String phoneType, String screenType, String enterWay, int i2, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(enterWay, "enterWay");
        n.g(tip, "tip");
        this.modelType = modelType;
        this.phoneType = phoneType;
        this.screenType = screenType;
        this.enterWay = enterWay;
        this.enterBrightness = i2;
        this.tip = tip;
    }

    public static /* synthetic */ FlashlightEnterEvent copy$default(FlashlightEnterEvent flashlightEnterEvent, String str, String str2, String str3, String str4, int i2, String str5, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = flashlightEnterEvent.modelType;
        }
        if ((i3 & 2) != 0) {
            str2 = flashlightEnterEvent.phoneType;
        }
        String str6 = str2;
        if ((i3 & 4) != 0) {
            str3 = flashlightEnterEvent.screenType;
        }
        String str7 = str3;
        if ((i3 & 8) != 0) {
            str4 = flashlightEnterEvent.enterWay;
        }
        String str8 = str4;
        if ((i3 & 16) != 0) {
            i2 = flashlightEnterEvent.enterBrightness;
        }
        int i4 = i2;
        if ((i3 & 32) != 0) {
            str5 = flashlightEnterEvent.tip;
        }
        return flashlightEnterEvent.copy(str, str6, str7, str8, i4, str5);
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
        return this.enterWay;
    }

    public final int component5() {
        return this.enterBrightness;
    }

    public final String component6() {
        return this.tip;
    }

    public final FlashlightEnterEvent copy(String modelType, String phoneType, String screenType, String enterWay, int i2, String tip) {
        n.g(modelType, "modelType");
        n.g(phoneType, "phoneType");
        n.g(screenType, "screenType");
        n.g(enterWay, "enterWay");
        n.g(tip, "tip");
        return new FlashlightEnterEvent(modelType, phoneType, screenType, enterWay, i2, tip);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlashlightEnterEvent)) {
            return false;
        }
        FlashlightEnterEvent flashlightEnterEvent = (FlashlightEnterEvent) obj;
        return n.c(this.modelType, flashlightEnterEvent.modelType) && n.c(this.phoneType, flashlightEnterEvent.phoneType) && n.c(this.screenType, flashlightEnterEvent.screenType) && n.c(this.enterWay, flashlightEnterEvent.enterWay) && this.enterBrightness == flashlightEnterEvent.enterBrightness && n.c(this.tip, flashlightEnterEvent.tip);
    }

    public final int getEnterBrightness() {
        return this.enterBrightness;
    }

    public final String getEnterWay() {
        return this.enterWay;
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
        return (((((((((this.modelType.hashCode() * 31) + this.phoneType.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.enterWay.hashCode()) * 31) + Integer.hashCode(this.enterBrightness)) * 31) + this.tip.hashCode();
    }

    public String toString() {
        return "FlashlightEnterEvent(modelType=" + this.modelType + ", phoneType=" + this.phoneType + ", screenType=" + this.screenType + ", enterWay=" + this.enterWay + ", enterBrightness=" + this.enterBrightness + ", tip=" + this.tip + ")";
    }

    public /* synthetic */ FlashlightEnterEvent(String str, String str2, String str3, String str4, int i2, String str5, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, i2, (i3 & 32) != 0 ? TrackUtilsKt.EVENT_ENTER_TIP : str5);
    }
}
