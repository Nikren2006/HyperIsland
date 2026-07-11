package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "click")
public final class DeviceCenterCardClickHMEvent implements DeviceCenterEvent {

    @EventKey(key = "device")
    private final String device;

    @EventKey(key = "device_classification")
    private final String deviceClassification;

    @EventKey(key = "position")
    private final String deviceNupositionmberStatus;

    @EventKey(key = "group")
    private final String group;

    @EventKey(key = "hyper_mind_entrance_status")
    private final String hyperMindEntranceStatus;

    @EventKey(key = "page")
    private final String page;

    @EventKey(key = "ref_device_id")
    private final String refDeviceId;

    @EventKey(key = "ref_device_model")
    private final String refDeviceModel;

    @EventKey(key = "ref_device_status")
    private final String refDeviceStatus;

    @EventKey(key = "ref_platform_number")
    private final String refPlatformNumber;

    public DeviceCenterCardClickHMEvent(String deviceClassification, String device, String refDeviceId, String refDeviceModel, String refDeviceStatus, String hyperMindEntranceStatus, String refPlatformNumber, String deviceNupositionmberStatus, String page, String group) {
        n.g(deviceClassification, "deviceClassification");
        n.g(device, "device");
        n.g(refDeviceId, "refDeviceId");
        n.g(refDeviceModel, "refDeviceModel");
        n.g(refDeviceStatus, "refDeviceStatus");
        n.g(hyperMindEntranceStatus, "hyperMindEntranceStatus");
        n.g(refPlatformNumber, "refPlatformNumber");
        n.g(deviceNupositionmberStatus, "deviceNupositionmberStatus");
        n.g(page, "page");
        n.g(group, "group");
        this.deviceClassification = deviceClassification;
        this.device = device;
        this.refDeviceId = refDeviceId;
        this.refDeviceModel = refDeviceModel;
        this.refDeviceStatus = refDeviceStatus;
        this.hyperMindEntranceStatus = hyperMindEntranceStatus;
        this.refPlatformNumber = refPlatformNumber;
        this.deviceNupositionmberStatus = deviceNupositionmberStatus;
        this.page = page;
        this.group = group;
    }

    public final String component1() {
        return this.deviceClassification;
    }

    public final String component10() {
        return this.group;
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
        return this.hyperMindEntranceStatus;
    }

    public final String component7() {
        return this.refPlatformNumber;
    }

    public final String component8() {
        return this.deviceNupositionmberStatus;
    }

    public final String component9() {
        return this.page;
    }

    public final DeviceCenterCardClickHMEvent copy(String deviceClassification, String device, String refDeviceId, String refDeviceModel, String refDeviceStatus, String hyperMindEntranceStatus, String refPlatformNumber, String deviceNupositionmberStatus, String page, String group) {
        n.g(deviceClassification, "deviceClassification");
        n.g(device, "device");
        n.g(refDeviceId, "refDeviceId");
        n.g(refDeviceModel, "refDeviceModel");
        n.g(refDeviceStatus, "refDeviceStatus");
        n.g(hyperMindEntranceStatus, "hyperMindEntranceStatus");
        n.g(refPlatformNumber, "refPlatformNumber");
        n.g(deviceNupositionmberStatus, "deviceNupositionmberStatus");
        n.g(page, "page");
        n.g(group, "group");
        return new DeviceCenterCardClickHMEvent(deviceClassification, device, refDeviceId, refDeviceModel, refDeviceStatus, hyperMindEntranceStatus, refPlatformNumber, deviceNupositionmberStatus, page, group);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceCenterCardClickHMEvent)) {
            return false;
        }
        DeviceCenterCardClickHMEvent deviceCenterCardClickHMEvent = (DeviceCenterCardClickHMEvent) obj;
        return n.c(this.deviceClassification, deviceCenterCardClickHMEvent.deviceClassification) && n.c(this.device, deviceCenterCardClickHMEvent.device) && n.c(this.refDeviceId, deviceCenterCardClickHMEvent.refDeviceId) && n.c(this.refDeviceModel, deviceCenterCardClickHMEvent.refDeviceModel) && n.c(this.refDeviceStatus, deviceCenterCardClickHMEvent.refDeviceStatus) && n.c(this.hyperMindEntranceStatus, deviceCenterCardClickHMEvent.hyperMindEntranceStatus) && n.c(this.refPlatformNumber, deviceCenterCardClickHMEvent.refPlatformNumber) && n.c(this.deviceNupositionmberStatus, deviceCenterCardClickHMEvent.deviceNupositionmberStatus) && n.c(this.page, deviceCenterCardClickHMEvent.page) && n.c(this.group, deviceCenterCardClickHMEvent.group);
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

    public final String getHyperMindEntranceStatus() {
        return this.hyperMindEntranceStatus;
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

    public final String getRefPlatformNumber() {
        return this.refPlatformNumber;
    }

    public int hashCode() {
        return (((((((((((((((((this.deviceClassification.hashCode() * 31) + this.device.hashCode()) * 31) + this.refDeviceId.hashCode()) * 31) + this.refDeviceModel.hashCode()) * 31) + this.refDeviceStatus.hashCode()) * 31) + this.hyperMindEntranceStatus.hashCode()) * 31) + this.refPlatformNumber.hashCode()) * 31) + this.deviceNupositionmberStatus.hashCode()) * 31) + this.page.hashCode()) * 31) + this.group.hashCode();
    }

    public String toString() {
        return "DeviceCenterCardClickHMEvent(deviceClassification=" + this.deviceClassification + ", device=" + this.device + ", refDeviceId=" + this.refDeviceId + ", refDeviceModel=" + this.refDeviceModel + ", refDeviceStatus=" + this.refDeviceStatus + ", hyperMindEntranceStatus=" + this.hyperMindEntranceStatus + ", refPlatformNumber=" + this.refPlatformNumber + ", deviceNupositionmberStatus=" + this.deviceNupositionmberStatus + ", page=" + this.page + ", group=" + this.group + ")";
    }

    public /* synthetic */ DeviceCenterCardClickHMEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, (i2 & 256) != 0 ? DeviceCenterEventsKt.PAGE_DEVICE_CENTER_EXPOSE : str9, (i2 & 512) != 0 ? "device" : str10);
    }
}
