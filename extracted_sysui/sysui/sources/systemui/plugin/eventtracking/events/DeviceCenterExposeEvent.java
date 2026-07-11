package systemui.plugin.eventtracking.events;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = DeviceCenterEventsKt.EVENT_DEVICE_CENTER_EXPOSE)
public final class DeviceCenterExposeEvent implements DeviceCenterEvent {

    @EventKey(key = "sum_device_number")
    private final int deviceNumber;

    @EventKey(key = "device_number_status")
    private final List<String> deviceNumberStatus;

    @EventKey(key = "group")
    private final String group;

    @EventKey(key = "hyper_mind_entrance_status")
    private final String hyperMindEntranceStatus;

    @EventKey(key = "if_hyper_mind_entrance")
    private final String ifHyperMindEntrance;

    @EventKey(key = "page")
    private final String page;

    public DeviceCenterExposeEvent(List<String> deviceNumberStatus, int i2, String ifHyperMindEntrance, String hyperMindEntranceStatus, String page, String group) {
        n.g(deviceNumberStatus, "deviceNumberStatus");
        n.g(ifHyperMindEntrance, "ifHyperMindEntrance");
        n.g(hyperMindEntranceStatus, "hyperMindEntranceStatus");
        n.g(page, "page");
        n.g(group, "group");
        this.deviceNumberStatus = deviceNumberStatus;
        this.deviceNumber = i2;
        this.ifHyperMindEntrance = ifHyperMindEntrance;
        this.hyperMindEntranceStatus = hyperMindEntranceStatus;
        this.page = page;
        this.group = group;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DeviceCenterExposeEvent copy$default(DeviceCenterExposeEvent deviceCenterExposeEvent, List list, int i2, String str, String str2, String str3, String str4, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            list = deviceCenterExposeEvent.deviceNumberStatus;
        }
        if ((i3 & 2) != 0) {
            i2 = deviceCenterExposeEvent.deviceNumber;
        }
        int i4 = i2;
        if ((i3 & 4) != 0) {
            str = deviceCenterExposeEvent.ifHyperMindEntrance;
        }
        String str5 = str;
        if ((i3 & 8) != 0) {
            str2 = deviceCenterExposeEvent.hyperMindEntranceStatus;
        }
        String str6 = str2;
        if ((i3 & 16) != 0) {
            str3 = deviceCenterExposeEvent.page;
        }
        String str7 = str3;
        if ((i3 & 32) != 0) {
            str4 = deviceCenterExposeEvent.group;
        }
        return deviceCenterExposeEvent.copy(list, i4, str5, str6, str7, str4);
    }

    public final List<String> component1() {
        return this.deviceNumberStatus;
    }

    public final int component2() {
        return this.deviceNumber;
    }

    public final String component3() {
        return this.ifHyperMindEntrance;
    }

    public final String component4() {
        return this.hyperMindEntranceStatus;
    }

    public final String component5() {
        return this.page;
    }

    public final String component6() {
        return this.group;
    }

    public final DeviceCenterExposeEvent copy(List<String> deviceNumberStatus, int i2, String ifHyperMindEntrance, String hyperMindEntranceStatus, String page, String group) {
        n.g(deviceNumberStatus, "deviceNumberStatus");
        n.g(ifHyperMindEntrance, "ifHyperMindEntrance");
        n.g(hyperMindEntranceStatus, "hyperMindEntranceStatus");
        n.g(page, "page");
        n.g(group, "group");
        return new DeviceCenterExposeEvent(deviceNumberStatus, i2, ifHyperMindEntrance, hyperMindEntranceStatus, page, group);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceCenterExposeEvent)) {
            return false;
        }
        DeviceCenterExposeEvent deviceCenterExposeEvent = (DeviceCenterExposeEvent) obj;
        return n.c(this.deviceNumberStatus, deviceCenterExposeEvent.deviceNumberStatus) && this.deviceNumber == deviceCenterExposeEvent.deviceNumber && n.c(this.ifHyperMindEntrance, deviceCenterExposeEvent.ifHyperMindEntrance) && n.c(this.hyperMindEntranceStatus, deviceCenterExposeEvent.hyperMindEntranceStatus) && n.c(this.page, deviceCenterExposeEvent.page) && n.c(this.group, deviceCenterExposeEvent.group);
    }

    public final int getDeviceNumber() {
        return this.deviceNumber;
    }

    public final List<String> getDeviceNumberStatus() {
        return this.deviceNumberStatus;
    }

    public final String getGroup() {
        return this.group;
    }

    public final String getHyperMindEntranceStatus() {
        return this.hyperMindEntranceStatus;
    }

    public final String getIfHyperMindEntrance() {
        return this.ifHyperMindEntrance;
    }

    public final String getPage() {
        return this.page;
    }

    public int hashCode() {
        return (((((((((this.deviceNumberStatus.hashCode() * 31) + Integer.hashCode(this.deviceNumber)) * 31) + this.ifHyperMindEntrance.hashCode()) * 31) + this.hyperMindEntranceStatus.hashCode()) * 31) + this.page.hashCode()) * 31) + this.group.hashCode();
    }

    public String toString() {
        return "DeviceCenterExposeEvent(deviceNumberStatus=" + this.deviceNumberStatus + ", deviceNumber=" + this.deviceNumber + ", ifHyperMindEntrance=" + this.ifHyperMindEntrance + ", hyperMindEntranceStatus=" + this.hyperMindEntranceStatus + ", page=" + this.page + ", group=" + this.group + ")";
    }

    public /* synthetic */ DeviceCenterExposeEvent(List list, int i2, String str, String str2, String str3, String str4, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, i2, str, str2, (i3 & 16) != 0 ? DeviceCenterEventsKt.PAGE_DEVICE_CENTER_EXPOSE : str3, (i3 & 32) != 0 ? DeviceCenterEventsKt.GROUP_DEVICE_CENTER_EXPOSE : str4);
    }
}
