package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "function_guide_expose")
public final class TrackDeskTopFunctionGuideExposeEvent implements BaseDesktopModeEvents {

    @EventKey(key = "data_version")
    private final String dataVersion;

    @EventKey(key = "home_mode")
    private final String homeMode;

    @EventKey(key = "model_type")
    private final String modelType;

    @EventKey(key = "open_way")
    private final String openWay;

    @EventKey(key = "screen_orientation")
    private final String screenOrientation;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screenType;

    @EventKey(key = "tip")
    private final String tip;

    public TrackDeskTopFunctionGuideExposeEvent(String tip, String modelType, String screenOrientation, String screenType, String dataVersion, String homeMode, String openWay) {
        n.g(tip, "tip");
        n.g(modelType, "modelType");
        n.g(screenOrientation, "screenOrientation");
        n.g(screenType, "screenType");
        n.g(dataVersion, "dataVersion");
        n.g(homeMode, "homeMode");
        n.g(openWay, "openWay");
        this.tip = tip;
        this.modelType = modelType;
        this.screenOrientation = screenOrientation;
        this.screenType = screenType;
        this.dataVersion = dataVersion;
        this.homeMode = homeMode;
        this.openWay = openWay;
    }

    public static /* synthetic */ TrackDeskTopFunctionGuideExposeEvent copy$default(TrackDeskTopFunctionGuideExposeEvent trackDeskTopFunctionGuideExposeEvent, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = trackDeskTopFunctionGuideExposeEvent.tip;
        }
        if ((i2 & 2) != 0) {
            str2 = trackDeskTopFunctionGuideExposeEvent.modelType;
        }
        String str8 = str2;
        if ((i2 & 4) != 0) {
            str3 = trackDeskTopFunctionGuideExposeEvent.screenOrientation;
        }
        String str9 = str3;
        if ((i2 & 8) != 0) {
            str4 = trackDeskTopFunctionGuideExposeEvent.screenType;
        }
        String str10 = str4;
        if ((i2 & 16) != 0) {
            str5 = trackDeskTopFunctionGuideExposeEvent.dataVersion;
        }
        String str11 = str5;
        if ((i2 & 32) != 0) {
            str6 = trackDeskTopFunctionGuideExposeEvent.homeMode;
        }
        String str12 = str6;
        if ((i2 & 64) != 0) {
            str7 = trackDeskTopFunctionGuideExposeEvent.openWay;
        }
        return trackDeskTopFunctionGuideExposeEvent.copy(str, str8, str9, str10, str11, str12, str7);
    }

    public final String component1() {
        return this.tip;
    }

    public final String component2() {
        return this.modelType;
    }

    public final String component3() {
        return this.screenOrientation;
    }

    public final String component4() {
        return this.screenType;
    }

    public final String component5() {
        return this.dataVersion;
    }

    public final String component6() {
        return this.homeMode;
    }

    public final String component7() {
        return this.openWay;
    }

    public final TrackDeskTopFunctionGuideExposeEvent copy(String tip, String modelType, String screenOrientation, String screenType, String dataVersion, String homeMode, String openWay) {
        n.g(tip, "tip");
        n.g(modelType, "modelType");
        n.g(screenOrientation, "screenOrientation");
        n.g(screenType, "screenType");
        n.g(dataVersion, "dataVersion");
        n.g(homeMode, "homeMode");
        n.g(openWay, "openWay");
        return new TrackDeskTopFunctionGuideExposeEvent(tip, modelType, screenOrientation, screenType, dataVersion, homeMode, openWay);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TrackDeskTopFunctionGuideExposeEvent)) {
            return false;
        }
        TrackDeskTopFunctionGuideExposeEvent trackDeskTopFunctionGuideExposeEvent = (TrackDeskTopFunctionGuideExposeEvent) obj;
        return n.c(this.tip, trackDeskTopFunctionGuideExposeEvent.tip) && n.c(this.modelType, trackDeskTopFunctionGuideExposeEvent.modelType) && n.c(this.screenOrientation, trackDeskTopFunctionGuideExposeEvent.screenOrientation) && n.c(this.screenType, trackDeskTopFunctionGuideExposeEvent.screenType) && n.c(this.dataVersion, trackDeskTopFunctionGuideExposeEvent.dataVersion) && n.c(this.homeMode, trackDeskTopFunctionGuideExposeEvent.homeMode) && n.c(this.openWay, trackDeskTopFunctionGuideExposeEvent.openWay);
    }

    public final String getDataVersion() {
        return this.dataVersion;
    }

    public final String getHomeMode() {
        return this.homeMode;
    }

    public final String getModelType() {
        return this.modelType;
    }

    public final String getOpenWay() {
        return this.openWay;
    }

    public final String getScreenOrientation() {
        return this.screenOrientation;
    }

    public final String getScreenType() {
        return this.screenType;
    }

    public final String getTip() {
        return this.tip;
    }

    public int hashCode() {
        return (((((((((((this.tip.hashCode() * 31) + this.modelType.hashCode()) * 31) + this.screenOrientation.hashCode()) * 31) + this.screenType.hashCode()) * 31) + this.dataVersion.hashCode()) * 31) + this.homeMode.hashCode()) * 31) + this.openWay.hashCode();
    }

    public String toString() {
        return "TrackDeskTopFunctionGuideExposeEvent(tip=" + this.tip + ", modelType=" + this.modelType + ", screenOrientation=" + this.screenOrientation + ", screenType=" + this.screenType + ", dataVersion=" + this.dataVersion + ", homeMode=" + this.homeMode + ", openWay=" + this.openWay + ")";
    }

    public /* synthetic */ TrackDeskTopFunctionGuideExposeEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "621.0.0.0.29079" : str, str2, str3, str4, (i2 & 16) != 0 ? "23060800" : str5, str6, (i2 & 64) != 0 ? "控制中心" : str7);
    }
}
