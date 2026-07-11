package systemui.plugin.eventtracking.events;

import androidx.annotation.Keep;
import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@Keep
public final class DeviceCenterDeviceFoundDuration {

    @EventKey(key = "device")
    private final String device;

    @EventKey(key = "device_classification")
    private final String deviceClassification;

    @EventKey(key = "duration")
    private final long duration;

    @EventKey(key = "is_head_device")
    private final boolean isHead;

    @EventKey(key = "ref_device_id")
    private final String refDeviceId;

    @EventKey(key = "ref_device_model")
    private final String refDeviceModel;

    @EventKey(key = "report_method")
    private final String reportMethod;

    public DeviceCenterDeviceFoundDuration(String deviceClassification, String device, String refDeviceId, String refDeviceModel, String reportMethod, long j2, boolean z2) {
        n.g(deviceClassification, "deviceClassification");
        n.g(device, "device");
        n.g(refDeviceId, "refDeviceId");
        n.g(refDeviceModel, "refDeviceModel");
        n.g(reportMethod, "reportMethod");
        this.deviceClassification = deviceClassification;
        this.device = device;
        this.refDeviceId = refDeviceId;
        this.refDeviceModel = refDeviceModel;
        this.reportMethod = reportMethod;
        this.duration = j2;
        this.isHead = z2;
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
        return this.reportMethod;
    }

    public final long component6() {
        return this.duration;
    }

    public final boolean component7() {
        return this.isHead;
    }

    public final DeviceCenterDeviceFoundDuration copy(String deviceClassification, String device, String refDeviceId, String refDeviceModel, String reportMethod, long j2, boolean z2) {
        n.g(deviceClassification, "deviceClassification");
        n.g(device, "device");
        n.g(refDeviceId, "refDeviceId");
        n.g(refDeviceModel, "refDeviceModel");
        n.g(reportMethod, "reportMethod");
        return new DeviceCenterDeviceFoundDuration(deviceClassification, device, refDeviceId, refDeviceModel, reportMethod, j2, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceCenterDeviceFoundDuration)) {
            return false;
        }
        DeviceCenterDeviceFoundDuration deviceCenterDeviceFoundDuration = (DeviceCenterDeviceFoundDuration) obj;
        return n.c(this.deviceClassification, deviceCenterDeviceFoundDuration.deviceClassification) && n.c(this.device, deviceCenterDeviceFoundDuration.device) && n.c(this.refDeviceId, deviceCenterDeviceFoundDuration.refDeviceId) && n.c(this.refDeviceModel, deviceCenterDeviceFoundDuration.refDeviceModel) && n.c(this.reportMethod, deviceCenterDeviceFoundDuration.reportMethod) && this.duration == deviceCenterDeviceFoundDuration.duration && this.isHead == deviceCenterDeviceFoundDuration.isHead;
    }

    public final String getDevice() {
        return this.device;
    }

    public final String getDeviceClassification() {
        return this.deviceClassification;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final String getRefDeviceId() {
        return this.refDeviceId;
    }

    public final String getRefDeviceModel() {
        return this.refDeviceModel;
    }

    public final String getReportMethod() {
        return this.reportMethod;
    }

    public int hashCode() {
        return (((((((((((this.deviceClassification.hashCode() * 31) + this.device.hashCode()) * 31) + this.refDeviceId.hashCode()) * 31) + this.refDeviceModel.hashCode()) * 31) + this.reportMethod.hashCode()) * 31) + Long.hashCode(this.duration)) * 31) + Boolean.hashCode(this.isHead);
    }

    public final boolean isHead() {
        return this.isHead;
    }

    public String toString() {
        return "DeviceCenterDeviceFoundDuration(deviceClassification=" + this.deviceClassification + ", device=" + this.device + ", refDeviceId=" + this.refDeviceId + ", refDeviceModel=" + this.refDeviceModel + ", reportMethod=" + this.reportMethod + ", duration=" + this.duration + ", isHead=" + this.isHead + ")";
    }
}
