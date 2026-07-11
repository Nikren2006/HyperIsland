package systemui.plugin.eventtracking.events;

import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "find_device_system_ui")
public final class MiPlayFindDeviceListEvent implements BaseMiPlayEvent {

    @EventKey(key = "find_device_list")
    private final List<Map<String, String>> deviceList;

    /* JADX WARN: Multi-variable type inference failed */
    public MiPlayFindDeviceListEvent(List<? extends Map<String, String>> deviceList) {
        n.g(deviceList, "deviceList");
        this.deviceList = deviceList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MiPlayFindDeviceListEvent copy$default(MiPlayFindDeviceListEvent miPlayFindDeviceListEvent, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = miPlayFindDeviceListEvent.deviceList;
        }
        return miPlayFindDeviceListEvent.copy(list);
    }

    public final List<Map<String, String>> component1() {
        return this.deviceList;
    }

    public final MiPlayFindDeviceListEvent copy(List<? extends Map<String, String>> deviceList) {
        n.g(deviceList, "deviceList");
        return new MiPlayFindDeviceListEvent(deviceList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof MiPlayFindDeviceListEvent) && n.c(this.deviceList, ((MiPlayFindDeviceListEvent) obj).deviceList);
    }

    public final List<Map<String, String>> getDeviceList() {
        return this.deviceList;
    }

    public int hashCode() {
        return this.deviceList.hashCode();
    }

    public String toString() {
        return "MiPlayFindDeviceListEvent(deviceList=" + this.deviceList + ")";
    }
}
