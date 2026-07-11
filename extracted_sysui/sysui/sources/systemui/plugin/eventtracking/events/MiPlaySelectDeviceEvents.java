package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import miui.systemui.miplay.MiPlayDetailActivity;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = MiPlayEventsKt.EVENT_MIPLAY_SYSTEMUI_SELECT_DEVICE)
public final class MiPlaySelectDeviceEvents implements BaseMiPlayEvent {

    @EventKey(key = "content_type")
    private final String content_type;

    @EventKey(key = "device_sub_type")
    private final String deviceSubType;

    @EventKey(key = "device_type")
    private final int deviceType;

    @EventKey(key = "device_id_after_select")
    private final String device_id_after_select;

    @EventKey(key = "device_id_before_select")
    private final String device_id_before_select;

    @EventKey(key = "device_type_after_select")
    private final String device_type_after_select;

    @EventKey(key = "device_type_before_select")
    private final String device_type_before_select;

    @EventKey(key = "error_code")
    private final String errorCode;

    @EventKey(key = "is_group")
    private final boolean isGroup;

    @EventKey(key = "is_headset")
    private final boolean isHeadset;

    @EventKey(key = "is_tv")
    private final boolean isTv;

    @EventKey(key = "mac_hash")
    private final String macHash;
    private final boolean music_program;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.PHONE_TYPE)
    private final String phone_type;

    @EventKey(key = "protocol_after_select")
    private final String protocol_after_select;

    @EventKey(key = "protocol_before_select")
    private final String protocol_before_select;

    @EventKey(key = MiPlayDetailActivity.EXTRA_PARAM_REF)
    private final String ref;

    @EventKey(key = DynamicIslandEventsConstants.PublicParams.SCREEN_TYPE)
    private final String screen_type;

    @EventKey(key = MiPlayEventsKt.VALUE_SELECT)
    private final boolean select;

    @EventKey(key = "select_result")
    private final String selectResult;

    @EventKey(key = "source_package")
    private final String source_package;

    public MiPlaySelectDeviceEvents(boolean z2, String selectResult, String errorCode, int i2, String deviceSubType, String str, boolean z3, boolean z4, boolean z5, String str2, boolean z6, String protocol_before_select, String device_type_before_select, String str3, String protocol_after_select, String device_type_after_select, String str4, String str5, String str6, String str7, String str8) {
        n.g(selectResult, "selectResult");
        n.g(errorCode, "errorCode");
        n.g(deviceSubType, "deviceSubType");
        n.g(protocol_before_select, "protocol_before_select");
        n.g(device_type_before_select, "device_type_before_select");
        n.g(protocol_after_select, "protocol_after_select");
        n.g(device_type_after_select, "device_type_after_select");
        this.select = z2;
        this.selectResult = selectResult;
        this.errorCode = errorCode;
        this.deviceType = i2;
        this.deviceSubType = deviceSubType;
        this.macHash = str;
        this.isGroup = z3;
        this.isHeadset = z4;
        this.isTv = z5;
        this.ref = str2;
        this.music_program = z6;
        this.protocol_before_select = protocol_before_select;
        this.device_type_before_select = device_type_before_select;
        this.device_id_before_select = str3;
        this.protocol_after_select = protocol_after_select;
        this.device_type_after_select = device_type_after_select;
        this.device_id_after_select = str4;
        this.content_type = str5;
        this.source_package = str6;
        this.phone_type = str7;
        this.screen_type = str8;
    }

    public final boolean component1() {
        return this.select;
    }

    public final String component10() {
        return this.ref;
    }

    public final boolean component11() {
        return this.music_program;
    }

    public final String component12() {
        return this.protocol_before_select;
    }

    public final String component13() {
        return this.device_type_before_select;
    }

    public final String component14() {
        return this.device_id_before_select;
    }

    public final String component15() {
        return this.protocol_after_select;
    }

    public final String component16() {
        return this.device_type_after_select;
    }

    public final String component17() {
        return this.device_id_after_select;
    }

    public final String component18() {
        return this.content_type;
    }

    public final String component19() {
        return this.source_package;
    }

    public final String component2() {
        return this.selectResult;
    }

    public final String component20() {
        return this.phone_type;
    }

    public final String component21() {
        return this.screen_type;
    }

    public final String component3() {
        return this.errorCode;
    }

    public final int component4() {
        return this.deviceType;
    }

    public final String component5() {
        return this.deviceSubType;
    }

    public final String component6() {
        return this.macHash;
    }

    public final boolean component7() {
        return this.isGroup;
    }

    public final boolean component8() {
        return this.isHeadset;
    }

    public final boolean component9() {
        return this.isTv;
    }

    public final MiPlaySelectDeviceEvents copy(boolean z2, String selectResult, String errorCode, int i2, String deviceSubType, String str, boolean z3, boolean z4, boolean z5, String str2, boolean z6, String protocol_before_select, String device_type_before_select, String str3, String protocol_after_select, String device_type_after_select, String str4, String str5, String str6, String str7, String str8) {
        n.g(selectResult, "selectResult");
        n.g(errorCode, "errorCode");
        n.g(deviceSubType, "deviceSubType");
        n.g(protocol_before_select, "protocol_before_select");
        n.g(device_type_before_select, "device_type_before_select");
        n.g(protocol_after_select, "protocol_after_select");
        n.g(device_type_after_select, "device_type_after_select");
        return new MiPlaySelectDeviceEvents(z2, selectResult, errorCode, i2, deviceSubType, str, z3, z4, z5, str2, z6, protocol_before_select, device_type_before_select, str3, protocol_after_select, device_type_after_select, str4, str5, str6, str7, str8);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MiPlaySelectDeviceEvents)) {
            return false;
        }
        MiPlaySelectDeviceEvents miPlaySelectDeviceEvents = (MiPlaySelectDeviceEvents) obj;
        return this.select == miPlaySelectDeviceEvents.select && n.c(this.selectResult, miPlaySelectDeviceEvents.selectResult) && n.c(this.errorCode, miPlaySelectDeviceEvents.errorCode) && this.deviceType == miPlaySelectDeviceEvents.deviceType && n.c(this.deviceSubType, miPlaySelectDeviceEvents.deviceSubType) && n.c(this.macHash, miPlaySelectDeviceEvents.macHash) && this.isGroup == miPlaySelectDeviceEvents.isGroup && this.isHeadset == miPlaySelectDeviceEvents.isHeadset && this.isTv == miPlaySelectDeviceEvents.isTv && n.c(this.ref, miPlaySelectDeviceEvents.ref) && this.music_program == miPlaySelectDeviceEvents.music_program && n.c(this.protocol_before_select, miPlaySelectDeviceEvents.protocol_before_select) && n.c(this.device_type_before_select, miPlaySelectDeviceEvents.device_type_before_select) && n.c(this.device_id_before_select, miPlaySelectDeviceEvents.device_id_before_select) && n.c(this.protocol_after_select, miPlaySelectDeviceEvents.protocol_after_select) && n.c(this.device_type_after_select, miPlaySelectDeviceEvents.device_type_after_select) && n.c(this.device_id_after_select, miPlaySelectDeviceEvents.device_id_after_select) && n.c(this.content_type, miPlaySelectDeviceEvents.content_type) && n.c(this.source_package, miPlaySelectDeviceEvents.source_package) && n.c(this.phone_type, miPlaySelectDeviceEvents.phone_type) && n.c(this.screen_type, miPlaySelectDeviceEvents.screen_type);
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

    public final String getDevice_id_after_select() {
        return this.device_id_after_select;
    }

    public final String getDevice_id_before_select() {
        return this.device_id_before_select;
    }

    public final String getDevice_type_after_select() {
        return this.device_type_after_select;
    }

    public final String getDevice_type_before_select() {
        return this.device_type_before_select;
    }

    public final String getErrorCode() {
        return this.errorCode;
    }

    public final String getMacHash() {
        return this.macHash;
    }

    public final boolean getMusic_program() {
        return this.music_program;
    }

    public final String getPhone_type() {
        return this.phone_type;
    }

    public final String getProtocol_after_select() {
        return this.protocol_after_select;
    }

    public final String getProtocol_before_select() {
        return this.protocol_before_select;
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

    public final String getSelectResult() {
        return this.selectResult;
    }

    public final String getSource_package() {
        return this.source_package;
    }

    public int hashCode() {
        int iHashCode = ((((((((Boolean.hashCode(this.select) * 31) + this.selectResult.hashCode()) * 31) + this.errorCode.hashCode()) * 31) + Integer.hashCode(this.deviceType)) * 31) + this.deviceSubType.hashCode()) * 31;
        String str = this.macHash;
        int iHashCode2 = (((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Boolean.hashCode(this.isGroup)) * 31) + Boolean.hashCode(this.isHeadset)) * 31) + Boolean.hashCode(this.isTv)) * 31;
        String str2 = this.ref;
        int iHashCode3 = (((((((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + Boolean.hashCode(this.music_program)) * 31) + this.protocol_before_select.hashCode()) * 31) + this.device_type_before_select.hashCode()) * 31;
        String str3 = this.device_id_before_select;
        int iHashCode4 = (((((iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.protocol_after_select.hashCode()) * 31) + this.device_type_after_select.hashCode()) * 31;
        String str4 = this.device_id_after_select;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.content_type;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.source_package;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.phone_type;
        int iHashCode8 = (iHashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.screen_type;
        return iHashCode8 + (str8 != null ? str8.hashCode() : 0);
    }

    public final boolean isGroup() {
        return this.isGroup;
    }

    public final boolean isHeadset() {
        return this.isHeadset;
    }

    public final boolean isTv() {
        return this.isTv;
    }

    public String toString() {
        return "MiPlaySelectDeviceEvents(select=" + this.select + ", selectResult=" + this.selectResult + ", errorCode=" + this.errorCode + ", deviceType=" + this.deviceType + ", deviceSubType=" + this.deviceSubType + ", macHash=" + this.macHash + ", isGroup=" + this.isGroup + ", isHeadset=" + this.isHeadset + ", isTv=" + this.isTv + ", ref=" + this.ref + ", music_program=" + this.music_program + ", protocol_before_select=" + this.protocol_before_select + ", device_type_before_select=" + this.device_type_before_select + ", device_id_before_select=" + this.device_id_before_select + ", protocol_after_select=" + this.protocol_after_select + ", device_type_after_select=" + this.device_type_after_select + ", device_id_after_select=" + this.device_id_after_select + ", content_type=" + this.content_type + ", source_package=" + this.source_package + ", phone_type=" + this.phone_type + ", screen_type=" + this.screen_type + ")";
    }
}
