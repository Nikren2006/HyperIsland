package systemui.plugin.eventtracking.events;

import com.miui.miplay.audio.data.DeviceInfo;
import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "bluetooth_connect")
public final class MiPlayBluetoothConnectEvent implements BaseMiPlayEvent {

    @EventKey(key = "bluetooth_device_model")
    private final String bleDeviceModel;

    @EventKey(key = DeviceInfo.EXTRA_KEY_BLE_DEVICE_TYPE)
    private final String bleDeviceType;

    @EventKey(key = "duration")
    private final long duration;

    @EventKey(key = "connect_result")
    private final String mediaType;

    public MiPlayBluetoothConnectEvent(long j2, String mediaType, String bleDeviceModel, String bleDeviceType) {
        n.g(mediaType, "mediaType");
        n.g(bleDeviceModel, "bleDeviceModel");
        n.g(bleDeviceType, "bleDeviceType");
        this.duration = j2;
        this.mediaType = mediaType;
        this.bleDeviceModel = bleDeviceModel;
        this.bleDeviceType = bleDeviceType;
    }

    public static /* synthetic */ MiPlayBluetoothConnectEvent copy$default(MiPlayBluetoothConnectEvent miPlayBluetoothConnectEvent, long j2, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = miPlayBluetoothConnectEvent.duration;
        }
        long j3 = j2;
        if ((i2 & 2) != 0) {
            str = miPlayBluetoothConnectEvent.mediaType;
        }
        String str4 = str;
        if ((i2 & 4) != 0) {
            str2 = miPlayBluetoothConnectEvent.bleDeviceModel;
        }
        String str5 = str2;
        if ((i2 & 8) != 0) {
            str3 = miPlayBluetoothConnectEvent.bleDeviceType;
        }
        return miPlayBluetoothConnectEvent.copy(j3, str4, str5, str3);
    }

    public final long component1() {
        return this.duration;
    }

    public final String component2() {
        return this.mediaType;
    }

    public final String component3() {
        return this.bleDeviceModel;
    }

    public final String component4() {
        return this.bleDeviceType;
    }

    public final MiPlayBluetoothConnectEvent copy(long j2, String mediaType, String bleDeviceModel, String bleDeviceType) {
        n.g(mediaType, "mediaType");
        n.g(bleDeviceModel, "bleDeviceModel");
        n.g(bleDeviceType, "bleDeviceType");
        return new MiPlayBluetoothConnectEvent(j2, mediaType, bleDeviceModel, bleDeviceType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MiPlayBluetoothConnectEvent)) {
            return false;
        }
        MiPlayBluetoothConnectEvent miPlayBluetoothConnectEvent = (MiPlayBluetoothConnectEvent) obj;
        return this.duration == miPlayBluetoothConnectEvent.duration && n.c(this.mediaType, miPlayBluetoothConnectEvent.mediaType) && n.c(this.bleDeviceModel, miPlayBluetoothConnectEvent.bleDeviceModel) && n.c(this.bleDeviceType, miPlayBluetoothConnectEvent.bleDeviceType);
    }

    public final String getBleDeviceModel() {
        return this.bleDeviceModel;
    }

    public final String getBleDeviceType() {
        return this.bleDeviceType;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final String getMediaType() {
        return this.mediaType;
    }

    public int hashCode() {
        return (((((Long.hashCode(this.duration) * 31) + this.mediaType.hashCode()) * 31) + this.bleDeviceModel.hashCode()) * 31) + this.bleDeviceType.hashCode();
    }

    public String toString() {
        return "MiPlayBluetoothConnectEvent(duration=" + this.duration + ", mediaType=" + this.mediaType + ", bleDeviceModel=" + this.bleDeviceModel + ", bleDeviceType=" + this.bleDeviceType + ")";
    }
}
