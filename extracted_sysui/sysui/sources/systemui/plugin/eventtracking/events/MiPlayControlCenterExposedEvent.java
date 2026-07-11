package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_miplay_expose")
public final class MiPlayControlCenterExposedEvent implements BaseMiPlayEvent {

    @EventKey(key = "content_type")
    private final String content_type;
    private final boolean music_program;

    @EventKey(key = "screen_orientation")
    private final String orientation;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phone_type;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screen_type;

    @EventKey(key = "source_package")
    private final String source_package;

    @EventKey(key = "style")
    private final String style;

    @EventKey(key = "tip")
    private final String tip;

    @EventKey(key = "track_id")
    private final String trackId;

    @EventKey(key = "control_center_version")
    private final String version;

    public MiPlayControlCenterExposedEvent(String trackId, String orientation, String style, String version, String tip, boolean z2, String str, String str2, String str3, String str4) {
        n.g(trackId, "trackId");
        n.g(orientation, "orientation");
        n.g(style, "style");
        n.g(version, "version");
        n.g(tip, "tip");
        this.trackId = trackId;
        this.orientation = orientation;
        this.style = style;
        this.version = version;
        this.tip = tip;
        this.music_program = z2;
        this.content_type = str;
        this.source_package = str2;
        this.phone_type = str3;
        this.screen_type = str4;
    }

    public final String component1() {
        return this.trackId;
    }

    public final String component10() {
        return this.screen_type;
    }

    public final String component2() {
        return this.orientation;
    }

    public final String component3() {
        return this.style;
    }

    public final String component4() {
        return this.version;
    }

    public final String component5() {
        return this.tip;
    }

    public final boolean component6() {
        return this.music_program;
    }

    public final String component7() {
        return this.content_type;
    }

    public final String component8() {
        return this.source_package;
    }

    public final String component9() {
        return this.phone_type;
    }

    public final MiPlayControlCenterExposedEvent copy(String trackId, String orientation, String style, String version, String tip, boolean z2, String str, String str2, String str3, String str4) {
        n.g(trackId, "trackId");
        n.g(orientation, "orientation");
        n.g(style, "style");
        n.g(version, "version");
        n.g(tip, "tip");
        return new MiPlayControlCenterExposedEvent(trackId, orientation, style, version, tip, z2, str, str2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MiPlayControlCenterExposedEvent)) {
            return false;
        }
        MiPlayControlCenterExposedEvent miPlayControlCenterExposedEvent = (MiPlayControlCenterExposedEvent) obj;
        return n.c(this.trackId, miPlayControlCenterExposedEvent.trackId) && n.c(this.orientation, miPlayControlCenterExposedEvent.orientation) && n.c(this.style, miPlayControlCenterExposedEvent.style) && n.c(this.version, miPlayControlCenterExposedEvent.version) && n.c(this.tip, miPlayControlCenterExposedEvent.tip) && this.music_program == miPlayControlCenterExposedEvent.music_program && n.c(this.content_type, miPlayControlCenterExposedEvent.content_type) && n.c(this.source_package, miPlayControlCenterExposedEvent.source_package) && n.c(this.phone_type, miPlayControlCenterExposedEvent.phone_type) && n.c(this.screen_type, miPlayControlCenterExposedEvent.screen_type);
    }

    public final String getContent_type() {
        return this.content_type;
    }

    public final boolean getMusic_program() {
        return this.music_program;
    }

    public final String getOrientation() {
        return this.orientation;
    }

    public final String getPhone_type() {
        return this.phone_type;
    }

    public final String getScreen_type() {
        return this.screen_type;
    }

    public final String getSource_package() {
        return this.source_package;
    }

    public final String getStyle() {
        return this.style;
    }

    public final String getTip() {
        return this.tip;
    }

    public final String getTrackId() {
        return this.trackId;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int iHashCode = ((((((((((this.trackId.hashCode() * 31) + this.orientation.hashCode()) * 31) + this.style.hashCode()) * 31) + this.version.hashCode()) * 31) + this.tip.hashCode()) * 31) + Boolean.hashCode(this.music_program)) * 31;
        String str = this.content_type;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.source_package;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.phone_type;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.screen_type;
        return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "MiPlayControlCenterExposedEvent(trackId=" + this.trackId + ", orientation=" + this.orientation + ", style=" + this.style + ", version=" + this.version + ", tip=" + this.tip + ", music_program=" + this.music_program + ", content_type=" + this.content_type + ", source_package=" + this.source_package + ", phone_type=" + this.phone_type + ", screen_type=" + this.screen_type + ")";
    }

    public /* synthetic */ MiPlayControlCenterExposedEvent(String str, String str2, String str3, String str4, String str5, boolean z2, String str6, String str7, String str8, String str9, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i2 & 16) != 0 ? "178.1.1.1.18767" : str5, z2, str6, str7, str8, str9);
    }
}
