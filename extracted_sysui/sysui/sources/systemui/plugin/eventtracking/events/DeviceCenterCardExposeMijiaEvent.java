package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = DeviceCenterEventsKt.EVENT_DEVICE_CENTER_EXPOSE)
public final class DeviceCenterCardExposeMijiaEvent implements DeviceCenterEvent {

    @EventKey(key = "device")
    private final String device;

    @EventKey(key = "device_classification")
    private final String deviceClassification;

    @EventKey(key = "position")
    private final String deviceNupositionmberStatus;

    @EventKey(key = "group")
    private final String group;

    @EventKey(key = "page")
    private final String page;

    @EventKey(key = "ref_device_id")
    private final String refDeviceId;

    @EventKey(key = "ref_device_model")
    private final String refDeviceModel;

    @EventKey(key = "ref_device_status")
    private final String refDeviceStatus;

    @EventKey(key = "smarthome_device_type")
    private final String smarthomeDeviceType;

    public DeviceCenterCardExposeMijiaEvent(String deviceClassification, String device, String refDeviceId, String refDeviceModel, String refDeviceStatus, String smarthomeDeviceType, String deviceNupositionmberStatus, String page, String group) {
        n.g(deviceClassification, "deviceClassification");
        n.g(device, "device");
        n.g(refDeviceId, "refDeviceId");
        n.g(refDeviceModel, "refDeviceModel");
        n.g(refDeviceStatus, "refDeviceStatus");
        n.g(smarthomeDeviceType, "smarthomeDeviceType");
        n.g(deviceNupositionmberStatus, "deviceNupositionmberStatus");
        n.g(page, "page");
        n.g(group, "group");
        this.deviceClassification = deviceClassification;
        this.device = device;
        this.refDeviceId = refDeviceId;
        this.refDeviceModel = refDeviceModel;
        this.refDeviceStatus = refDeviceStatus;
        this.smarthomeDeviceType = smarthomeDeviceType;
        this.deviceNupositionmberStatus = deviceNupositionmberStatus;
        this.page = page;
        this.group = group;
    }

    public final String component1() {
        return this.deviceClassification;
    }

    public final String component2() {
        return this.device;
    }

    public final String component3() {
        return this.refDeviceId;
    }

    public final String component4() {
        return this.refDeviceModel;
    }

    public final String component5() {
        return this.refDeviceStatus;
    }

    public final String component6() {
        return this.smarthomeDeviceType;
    }

    public final String component7() {
        return this.deviceNupositionmberStatus;
    }

    public final String component8() {
        return this.page;
    }

    public final String component9() {
        return this.group;
    }

    public final DeviceCenterCardExposeMijiaEvent copy(String deviceClassification, String device, String refDeviceId, String refDeviceModel, String refDeviceStatus, String smarthomeDeviceType, String deviceNupositionmberStatus, String page, String group) {
        n.g(deviceClassification, "deviceClassification");
        n.g(device, "device");
        n.g(refDeviceId, "refDeviceId");
        n.g(refDeviceModel, "refDeviceModel");
        n.g(refDeviceStatus, "refDeviceStatus");
        n.g(smarthomeDeviceType, "smarthomeDeviceType");
        n.g(deviceNupositionmberStatus, "deviceNupositionmberStatus");
        n.g(page, "page");
        n.g(group, "group");
        return new DeviceCenterCardExposeMijiaEvent(deviceClassification, device, refDeviceId, refDeviceModel, refDeviceStatus, smarthomeDeviceType, deviceNupositionmberStatus, page, group);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceCenterCardExposeMijiaEvent)) {
            return false;
        }
        DeviceCenterCardExposeMijiaEvent deviceCenterCardExposeMijiaEvent = (DeviceCenterCardExposeMijiaEvent) obj;
        return n.c(this.deviceClassification, deviceCenterCardExposeMijiaEvent.deviceClassification) && n.c(this.device, deviceCenterCardExposeMijiaEvent.device) && n.c(this.refDeviceId, deviceCenterCardExposeMijiaEvent.refDeviceId) && n.c(this.refDeviceModel, deviceCenterCardExposeMijiaEvent.refDeviceModel) && n.c(this.refDeviceStatus, deviceCenterCardExposeMijiaEvent.refDeviceStatus) && n.c(this.smarthomeDeviceType, deviceCenterCardExposeMijiaEvent.smarthomeDeviceType) && n.c(this.deviceNupositionmberStatus, deviceCenterCardExposeMijiaEvent.deviceNupositionmberStatus) && n.c(this.page, deviceCenterCardExposeMijiaEvent.page) && n.c(this.group, deviceCenterCardExposeMijiaEvent.group);
    }

    public final String getDevice() {
        return this.device;
    }

    public final String getDeviceClassification() {
        return this.deviceClassification;
    }

    public final String getDeviceNupositionmberStatus() {
        return this.deviceNupositionmberStatus;
    }

    public final String getGroup() {
        return this.group;
    }

    public final String getPage() {
        return this.page;
    }

    public final String getRefDeviceId() {
        return this.refDeviceId;
    }

    public final String getRefDeviceModel() {
        return this.refDeviceModel;
    }

    public final String getRefDeviceStatus() {
        return this.refDeviceStatus;
    }

    public final String getSmarthomeDeviceType() {
        return this.smarthomeDeviceType;
    }

    public int hashCode() {
        return (((((((((((((((this.deviceClassification.hashCode() * 31) + this.device.hashCode()) * 31) + this.refDeviceId.hashCode()) * 31) + this.refDeviceModel.hashCode()) * 31) + this.refDeviceStatus.hashCode()) * 31) + this.smarthomeDeviceType.hashCode()) * 31) + this.deviceNupositionmberStatus.hashCode()) * 31) + this.page.hashCode()) * 31) + this.group.hashCode();
    }

    public String toString() {
        return "DeviceCenterCardExposeMijiaEvent(deviceClassification=" + this.deviceClassification + ", device=" + this.device + ", refDeviceId=" + this.refDeviceId + ", refDeviceModel=" + this.refDeviceModel + ", refDeviceStatus=" + this.refDeviceStatus + ", smarthomeDeviceType=" + this.smarthomeDeviceType + ", deviceNupositionmberStatus=" + this.deviceNupositionmberStatus + ", page=" + this.page + ", group=" + this.group + ")";
    }

    public /* synthetic */ DeviceCenterCardExposeMijiaEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, (i2 & 128) != 0 ? DeviceCenterEventsKt.PAGE_DEVICE_CENTER_EXPOSE : str8, (i2 & 256) != 0 ? "device" : str9);
    }
}
