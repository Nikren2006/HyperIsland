package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = DeviceCenterEventsKt.EVENT_DEVICE_CENTER_FOUND_DURATION)
public final class DeviceCenterDeviceFoundDurationEvent implements DeviceCenterEvent {

    @EventKey(key = "device_list")
    private final String device;

    @EventKey(key = "group")
    private final String group;

    @EventKey(key = "page")
    private final String page;

    public DeviceCenterDeviceFoundDurationEvent(String device, String page, String group) {
        n.g(device, "device");
        n.g(page, "page");
        n.g(group, "group");
        this.device = device;
        this.page = page;
        this.group = group;
    }

    public static /* synthetic */ DeviceCenterDeviceFoundDurationEvent copy$default(DeviceCenterDeviceFoundDurationEvent deviceCenterDeviceFoundDurationEvent, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = deviceCenterDeviceFoundDurationEvent.device;
        }
        if ((i2 & 2) != 0) {
            str2 = deviceCenterDeviceFoundDurationEvent.page;
        }
        if ((i2 & 4) != 0) {
            str3 = deviceCenterDeviceFoundDurationEvent.group;
        }
        return deviceCenterDeviceFoundDurationEvent.copy(str, str2, str3);
    }

    public final String component1() {
        return this.device;
    }

    public final String component2() {
        return this.page;
    }

    public final String component3() {
        return this.group;
    }

    public final DeviceCenterDeviceFoundDurationEvent copy(String device, String page, String group) {
        n.g(device, "device");
        n.g(page, "page");
        n.g(group, "group");
        return new DeviceCenterDeviceFoundDurationEvent(device, page, group);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceCenterDeviceFoundDurationEvent)) {
            return false;
        }
        DeviceCenterDeviceFoundDurationEvent deviceCenterDeviceFoundDurationEvent = (DeviceCenterDeviceFoundDurationEvent) obj;
        return n.c(this.device, deviceCenterDeviceFoundDurationEvent.device) && n.c(this.page, deviceCenterDeviceFoundDurationEvent.page) && n.c(this.group, deviceCenterDeviceFoundDurationEvent.group);
    }

    public final String getDevice() {
        return this.device;
    }

    public final String getGroup() {
        return this.group;
    }

    public final String getPage() {
        return this.page;
    }

    public int hashCode() {
        return (((this.device.hashCode() * 31) + this.page.hashCode()) * 31) + this.group.hashCode();
    }

    public String toString() {
        return "DeviceCenterDeviceFoundDurationEvent(device=" + this.device + ", page=" + this.page + ", group=" + this.group + ")";
    }

    public /* synthetic */ DeviceCenterDeviceFoundDurationEvent(String str, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? DeviceCenterEventsKt.PAGE_DEVICE_CENTER_EXPOSE : str2, (i2 & 4) != 0 ? "device" : str3);
    }
}
