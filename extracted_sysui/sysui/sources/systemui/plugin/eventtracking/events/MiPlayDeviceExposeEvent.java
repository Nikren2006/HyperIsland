package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import miui.systemui.miplay.MiPlayDetailActivity;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_miplay_device_expose")
public final class MiPlayDeviceExposeEvent implements BaseMiPlayEvent {

    @EventKey(key = "content_type")
    private final String content_type;

    @EventKey(key = "device_sub_type")
    private final String deviceSubType;

    @EventKey(key = "device_type")
    private final int deviceType;

    @EventKey(key = "is_playing")
    private final boolean isPlaying;
    private final boolean music_program;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phone_type;

    @EventKey(key = "position")
    private final int position;

    @EventKey(key = MiPlayDetailActivity.EXTRA_PARAM_REF)
    private final String ref;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screen_type;

    @EventKey(key = MiPlayEventsKt.VALUE_SELECT)
    private final boolean select;

    @EventKey(key = "source_package")
    private final String source_package;

    public MiPlayDeviceExposeEvent(boolean z2, int i2, String deviceSubType, boolean z3, int i3, String str, boolean z4, String str2, String str3, String str4, String str5) {
        n.g(deviceSubType, "deviceSubType");
        this.select = z2;
        this.deviceType = i2;
        this.deviceSubType = deviceSubType;
        this.isPlaying = z3;
        this.position = i3;
        this.ref = str;
        this.music_program = z4;
        this.content_type = str2;
        this.source_package = str3;
        this.phone_type = str4;
        this.screen_type = str5;
    }

    public final boolean component1() {
        return this.select;
    }

    public final String component10() {
        return this.phone_type;
    }

    public final String component11() {
        return this.screen_type;
    }

    public final int component2() {
        return this.deviceType;
    }

    public final String component3() {
        return this.deviceSubType;
    }

    public final boolean component4() {
        return this.isPlaying;
    }

    public final int component5() {
        return this.position;
    }

    public final String component6() {
        return this.ref;
    }

    public final boolean component7() {
        return this.music_program;
    }

    public final String component8() {
        return this.content_type;
    }

    public final String component9() {
        return this.source_package;
    }

    public final MiPlayDeviceExposeEvent copy(boolean z2, int i2, String deviceSubType, boolean z3, int i3, String str, boolean z4, String str2, String str3, String str4, String str5) {
        n.g(deviceSubType, "deviceSubType");
        return new MiPlayDeviceExposeEvent(z2, i2, deviceSubType, z3, i3, str, z4, str2, str3, str4, str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MiPlayDeviceExposeEvent)) {
            return false;
        }
        MiPlayDeviceExposeEvent miPlayDeviceExposeEvent = (MiPlayDeviceExposeEvent) obj;
        return this.select == miPlayDeviceExposeEvent.select && this.deviceType == miPlayDeviceExposeEvent.deviceType && n.c(this.deviceSubType, miPlayDeviceExposeEvent.deviceSubType) && this.isPlaying == miPlayDeviceExposeEvent.isPlaying && this.position == miPlayDeviceExposeEvent.position && n.c(this.ref, miPlayDeviceExposeEvent.ref) && this.music_program == miPlayDeviceExposeEvent.music_program && n.c(this.content_type, miPlayDeviceExposeEvent.content_type) && n.c(this.source_package, miPlayDeviceExposeEvent.source_package) && n.c(this.phone_type, miPlayDeviceExposeEvent.phone_type) && n.c(this.screen_type, miPlayDeviceExposeEvent.screen_type);
    }

    public final String getContent_type() {
        return this.content_type;
    }

    public final String getDeviceSubType() {
        return this.deviceSubType;
    }

    public final int getDeviceType() {
        return this.deviceType;
    }

    public final boolean getMusic_program() {
        return this.music_program;
    }

    public final String getPhone_type() {
        return this.phone_type;
    }

    public final int getPosition() {
        return this.position;
    }

    public final String getRef() {
        return this.ref;
    }

    public final String getScreen_type() {
        return this.screen_type;
    }

    public final boolean getSelect() {
        return this.select;
    }

    public final String getSource_package() {
        return this.source_package;
    }

    public int hashCode() {
        int iHashCode = ((((((((Boolean.hashCode(this.select) * 31) + Integer.hashCode(this.deviceType)) * 31) + this.deviceSubType.hashCode()) * 31) + Boolean.hashCode(this.isPlaying)) * 31) + Integer.hashCode(this.position)) * 31;
        String str = this.ref;
        int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Boolean.hashCode(this.music_program)) * 31;
        String str2 = this.content_type;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.source_package;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.phone_type;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.screen_type;
        return iHashCode5 + (str5 != null ? str5.hashCode() : 0);
    }

    public final boolean isPlaying() {
        return this.isPlaying;
    }

    public String toString() {
        return "MiPlayDeviceExposeEvent(select=" + this.select + ", deviceType=" + this.deviceType + ", deviceSubType=" + this.deviceSubType + ", isPlaying=" + this.isPlaying + ", position=" + this.position + ", ref=" + this.ref + ", music_program=" + this.music_program + ", content_type=" + this.content_type + ", source_package=" + this.source_package + ", phone_type=" + this.phone_type + ", screen_type=" + this.screen_type + ")";
    }
}
