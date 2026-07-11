package miui.systemui.devicecenter.track;

import I0.q;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.devicecenter.devices.DeviceInfoWrapper;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceCenterTracker {
    private long delaySearchTime;
    private long startTime;
    private final List<DeviceFoundTimeRecord> mDevicesFromCache = new ArrayList();
    private final List<DeviceFoundTimeRecord> mDevices = new ArrayList();

    /* JADX INFO: Access modifiers changed from: private */
    public static final int _get_devicesFromCache_$lambda$1$lambda$0(Function2 tmp0, Object obj, Object obj2) {
        n.g(tmp0, "$tmp0");
        return ((Number) tmp0.invoke(obj, obj2)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int _get_devices_$lambda$3$lambda$2(Function2 tmp0, Object obj, Object obj2) {
        n.g(tmp0, "$tmp0");
        return ((Number) tmp0.invoke(obj, obj2)).intValue();
    }

    private final void pickHeadDevice(List<DeviceFoundTimeRecord> list) {
        HashSet hashSet = new HashSet();
        for (DeviceFoundTimeRecord deviceFoundTimeRecord : list) {
            if (!hashSet.contains(deviceFoundTimeRecord.getDevice().getDeviceInfo().getDeviceType())) {
                hashSet.add(deviceFoundTimeRecord.getDevice().getDeviceInfo().getDeviceType());
                deviceFoundTimeRecord.setHeadInSameType(true);
            }
        }
    }

    public final List<DeviceFoundTimeRecord> getDevices() {
        List<DeviceFoundTimeRecord> list = this.mDevices;
        final DeviceCenterTracker$devices$1$1 deviceCenterTracker$devices$1$1 = DeviceCenterTracker$devices$1$1.INSTANCE;
        q.r(list, new Comparator() { // from class: miui.systemui.devicecenter.track.b
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return DeviceCenterTracker._get_devices_$lambda$3$lambda$2(deviceCenterTracker$devices$1$1, obj, obj2);
            }
        });
        pickHeadDevice(this.mDevices);
        return this.mDevices;
    }

    public final List<DeviceFoundTimeRecord> getDevicesFromCache() {
        List<DeviceFoundTimeRecord> list = this.mDevicesFromCache;
        final DeviceCenterTracker$devicesFromCache$1$1 deviceCenterTracker$devicesFromCache$1$1 = DeviceCenterTracker$devicesFromCache$1$1.INSTANCE;
        q.r(list, new Comparator() { // from class: miui.systemui.devicecenter.track.a
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return DeviceCenterTracker._get_devicesFromCache_$lambda$1$lambda$0(deviceCenterTracker$devicesFromCache$1$1, obj, obj2);
            }
        });
        pickHeadDevice(this.mDevicesFromCache);
        return this.mDevicesFromCache;
    }

    public final void onDeviceFound(List<DeviceInfoWrapper> newList, boolean z2) {
        n.g(newList, "newList");
        List<DeviceFoundTimeRecord> list = z2 ? this.mDevicesFromCache : this.mDevices;
        ArrayList arrayList = new ArrayList(I0.n.o(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((DeviceFoundTimeRecord) it.next()).getDevice().getDeviceInfo().getId());
        }
        for (DeviceInfoWrapper deviceInfoWrapper : newList) {
            if (!arrayList.contains(deviceInfoWrapper.getDeviceInfo().getId())) {
                list.add(new DeviceFoundTimeRecord(deviceInfoWrapper, SystemClock.elapsedRealtime(), this.startTime, this.delaySearchTime, false, 16, null));
            }
        }
    }

    public final void recordStartSearchTime(long j2) {
        this.mDevicesFromCache.clear();
        this.mDevices.clear();
        this.delaySearchTime = j2;
        this.startTime = SystemClock.elapsedRealtime();
    }
}
