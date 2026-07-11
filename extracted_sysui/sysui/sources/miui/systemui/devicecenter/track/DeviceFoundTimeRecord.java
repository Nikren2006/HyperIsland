package miui.systemui.devicecenter.track;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.devicecenter.devices.DeviceInfoWrapper;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceFoundTimeRecord {
    private final long delaySearchDuration;
    private final DeviceInfoWrapper device;
    private final long deviceFoundTime;
    private boolean isHeadInSameType;
    private final long startTime;

    public DeviceFoundTimeRecord(DeviceInfoWrapper device, long j2, long j3, long j4, boolean z2) {
        n.g(device, "device");
        this.device = device;
        this.deviceFoundTime = j2;
        this.startTime = j3;
        this.delaySearchDuration = j4;
        this.isHeadInSameType = z2;
    }

    public final long calculateDuration() {
        return (this.deviceFoundTime - this.startTime) - this.delaySearchDuration;
    }

    public final DeviceInfoWrapper component1() {
        return this.device;
    }

    public final long component2() {
        return this.deviceFoundTime;
    }

    public final long component3() {
        return this.startTime;
    }

    public final long component4() {
        return this.delaySearchDuration;
    }

    public final boolean component5() {
        return this.isHeadInSameType;
    }

    public final DeviceFoundTimeRecord copy(DeviceInfoWrapper device, long j2, long j3, long j4, boolean z2) {
        n.g(device, "device");
        return new DeviceFoundTimeRecord(device, j2, j3, j4, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceFoundTimeRecord)) {
            return false;
        }
        DeviceFoundTimeRecord deviceFoundTimeRecord = (DeviceFoundTimeRecord) obj;
        return n.c(this.device, deviceFoundTimeRecord.device) && this.deviceFoundTime == deviceFoundTimeRecord.deviceFoundTime && this.startTime == deviceFoundTimeRecord.startTime && this.delaySearchDuration == deviceFoundTimeRecord.delaySearchDuration && this.isHeadInSameType == deviceFoundTimeRecord.isHeadInSameType;
    }

    public final long getDelaySearchDuration() {
        return this.delaySearchDuration;
    }

    public final DeviceInfoWrapper getDevice() {
        return this.device;
    }

    public final long getDeviceFoundTime() {
        return this.deviceFoundTime;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public int hashCode() {
        return (((((((this.device.hashCode() * 31) + Long.hashCode(this.deviceFoundTime)) * 31) + Long.hashCode(this.startTime)) * 31) + Long.hashCode(this.delaySearchDuration)) * 31) + Boolean.hashCode(this.isHeadInSameType);
    }

    public final boolean isHeadInSameType() {
        return this.isHeadInSameType;
    }

    public final void setHeadInSameType(boolean z2) {
        this.isHeadInSameType = z2;
    }

    public String toString() {
        return "id: " + this.device.getDeviceInfo().getTitle() + ", deviceFoundTime: " + this.deviceFoundTime + ", startTime: " + this.startTime + ", delay: " + this.delaySearchDuration + ", calculateDuration: " + calculateDuration() + ", isHeadInSameType: " + this.isHeadInSameType;
    }

    public /* synthetic */ DeviceFoundTimeRecord(DeviceInfoWrapper deviceInfoWrapper, long j2, long j3, long j4, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(deviceInfoWrapper, j2, j3, (i2 & 8) != 0 ? 0L : j4, (i2 & 16) != 0 ? false : z2);
    }
}
