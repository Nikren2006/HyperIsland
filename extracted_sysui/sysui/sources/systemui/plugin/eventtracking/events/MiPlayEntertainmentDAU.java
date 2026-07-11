package systemui.plugin.eventtracking.events;

import com.xiaomi.onetrack.api.a;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.events.DynamicIslandEventsConstants;
import miui.systemui.miplay.MiPlayDetailActivity;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = MiPlayEventsKt.EVENT_ENTERTAINMENT_DAU)
public final class MiPlayEntertainmentDAU implements BaseMiPlayEvent {

    @EventKey(key = a.f2741a)
    private final String action;

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

    public MiPlayEntertainmentDAU(String action, boolean z2, String selectResult, int i2, String deviceSubType, String str, boolean z3, boolean z4, boolean z5, String str2, boolean z6, String protocol_before_select, String device_type_before_select, String str3, String protocol_after_select, String device_type_after_select, String str4, String str5, String str6) {
        n.g(action, "action");
        n.g(selectResult, "selectResult");
        n.g(deviceSubType, "deviceSubType");
        n.g(protocol_before_select, "protocol_before_select");
        n.g(device_type_before_select, "device_type_before_select");
        n.g(protocol_after_select, "protocol_after_select");
        n.g(device_type_after_select, "device_type_after_select");
        this.action = action;
        this.select = z2;
        this.selectResult = selectResult;
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
        this.phone_type = str5;
        this.screen_type = str6;
    }

    public final String component1() {
        return this.action;
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
        return this.phone_type;
    }

    public final String component19() {
        return this.screen_type;
    }

    public final boolean component2() {
        return this.select;
    }

    public final String component3() {
        return this.selectResult;
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

    public final MiPlayEntertainmentDAU copy(String action, boolean z2, String selectResult, int i2, String deviceSubType, String str, boolean z3, boolean z4, boolean z5, String str2, boolean z6, String protocol_before_select, String device_type_before_select, String str3, String protocol_after_select, String device_type_after_select, String str4, String str5, String str6) {
        n.g(action, "action");
        n.g(selectResult, "selectResult");
        n.g(deviceSubType, "deviceSubType");
        n.g(protocol_before_select, "protocol_before_select");
        n.g(device_type_before_select, "device_type_before_select");
        n.g(protocol_after_select, "protocol_after_select");
        n.g(device_type_after_select, "device_type_after_select");
        return new MiPlayEntertainmentDAU(action, z2, selectResult, i2, deviceSubType, str, z3, z4, z5, str2, z6, protocol_before_select, device_type_before_select, str3, protocol_after_select, device_type_after_select, str4, str5, str6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MiPlayEntertainmentDAU)) {
            return false;
        }
        MiPlayEntertainmentDAU miPlayEntertainmentDAU = (MiPlayEntertainmentDAU) obj;
        return n.c(this.action, miPlayEntertainmentDAU.action) && this.select == miPlayEntertainmentDAU.select && n.c(this.selectResult, miPlayEntertainmentDAU.selectResult) && this.deviceType == miPlayEntertainmentDAU.deviceType && n.c(this.deviceSubType, miPlayEntertainmentDAU.deviceSubType) && n.c(this.macHash, miPlayEntertainmentDAU.macHash) && this.isGroup == miPlayEntertainmentDAU.isGroup && this.isHeadset == miPlayEntertainmentDAU.isHeadset && this.isTv == miPlayEntertainmentDAU.isTv && n.c(this.ref, miPlayEntertainmentDAU.ref) && this.music_program == miPlayEntertainmentDAU.music_program && n.c(this.protocol_before_select, miPlayEntertainmentDAU.protocol_before_select) && n.c(this.device_type_before_select, miPlayEntertainmentDAU.device_type_before_select) && n.c(this.device_id_before_select, miPlayEntertainmentDAU.device_id_before_select) && n.c(this.protocol_after_select, miPlayEntertainmentDAU.protocol_after_select) && n.c(this.device_type_after_select, miPlayEntertainmentDAU.device_type_after_select) && n.c(this.device_id_after_select, miPlayEntertainmentDAU.device_id_after_select) && n.c(this.phone_type, miPlayEntertainmentDAU.phone_type) && n.c(this.screen_type, miPlayEntertainmentDAU.screen_type);
    }

    public final String getAction() {
        return this.action;
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

    public int hashCode() {
        int iHashCode = ((((((((this.action.hashCode() * 31) + Boolean.hashCode(this.select)) * 31) + this.selectResult.hashCode()) * 31) + Integer.hashCode(this.deviceType)) * 31) + this.deviceSubType.hashCode()) * 31;
        String str = this.macHash;
        int iHashCode2 = (((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Boolean.hashCode(this.isGroup)) * 31) + Boolean.hashCode(this.isHeadset)) * 31) + Boolean.hashCode(this.isTv)) * 31;
        String str2 = this.ref;
        int iHashCode3 = (((((((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + Boolean.hashCode(this.music_program)) * 31) + this.protocol_before_select.hashCode()) * 31) + this.device_type_before_select.hashCode()) * 31;
        String str3 = this.device_id_before_select;
        int iHashCode4 = (((((iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.protocol_after_select.hashCode()) * 31) + this.device_type_after_select.hashCode()) * 31;
        String str4 = this.device_id_after_select;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.phone_type;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.screen_type;
        return iHashCode6 + (str6 != null ? str6.hashCode() : 0);
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
        return "MiPlayEntertainmentDAU(action=" + this.action + ", select=" + this.select + ", selectResult=" + this.selectResult + ", deviceType=" + this.deviceType + ", deviceSubType=" + this.deviceSubType + ", macHash=" + this.macHash + ", isGroup=" + this.isGroup + ", isHeadset=" + this.isHeadset + ", isTv=" + this.isTv + ", ref=" + this.ref + ", music_program=" + this.music_program + ", protocol_before_select=" + this.protocol_before_select + ", device_type_before_select=" + this.device_type_before_select + ", device_id_before_select=" + this.device_id_before_select + ", protocol_after_select=" + this.protocol_after_select + ", device_type_after_select=" + this.device_type_after_select + ", device_id_after_select=" + this.device_id_after_select + ", phone_type=" + this.phone_type + ", screen_type=" + this.screen_type + ")";
    }
}
