package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "connect_peer_voice_time")
public final class MiPlayConnectPeerVoiceTimeEvent implements BaseMiPlayEvent {

    @EventKey(key = MiPlayEventsKt.EVENT_KEY_FIND_DEVICE_CONNECT_TYPE)
    private final String deviceType;

    @EventKey(key = "duration")
    private final long duration;

    @EventKey(key = "content_type")
    private final String mediaType;

    @EventKey(key = "peer_model")
    private final String peerModel;

    @EventKey(key = "peer_rom_version")
    private final String peerRomVersion;

    public MiPlayConnectPeerVoiceTimeEvent(long j2, String mediaType, String deviceType, String peerModel, String peerRomVersion) {
        n.g(mediaType, "mediaType");
        n.g(deviceType, "deviceType");
        n.g(peerModel, "peerModel");
        n.g(peerRomVersion, "peerRomVersion");
        this.duration = j2;
        this.mediaType = mediaType;
        this.deviceType = deviceType;
        this.peerModel = peerModel;
        this.peerRomVersion = peerRomVersion;
    }

    public static /* synthetic */ MiPlayConnectPeerVoiceTimeEvent copy$default(MiPlayConnectPeerVoiceTimeEvent miPlayConnectPeerVoiceTimeEvent, long j2, String str, String str2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = miPlayConnectPeerVoiceTimeEvent.duration;
        }
        long j3 = j2;
        if ((i2 & 2) != 0) {
            str = miPlayConnectPeerVoiceTimeEvent.mediaType;
        }
        String str5 = str;
        if ((i2 & 4) != 0) {
            str2 = miPlayConnectPeerVoiceTimeEvent.deviceType;
        }
        String str6 = str2;
        if ((i2 & 8) != 0) {
            str3 = miPlayConnectPeerVoiceTimeEvent.peerModel;
        }
        String str7 = str3;
        if ((i2 & 16) != 0) {
            str4 = miPlayConnectPeerVoiceTimeEvent.peerRomVersion;
        }
        return miPlayConnectPeerVoiceTimeEvent.copy(j3, str5, str6, str7, str4);
    }

    public final long component1() {
        return this.duration;
    }

    public final String component2() {
        return this.mediaType;
    }

    public final String component3() {
        return this.deviceType;
    }

    public final String component4() {
        return this.peerModel;
    }

    public final String component5() {
        return this.peerRomVersion;
    }

    public final MiPlayConnectPeerVoiceTimeEvent copy(long j2, String mediaType, String deviceType, String peerModel, String peerRomVersion) {
        n.g(mediaType, "mediaType");
        n.g(deviceType, "deviceType");
        n.g(peerModel, "peerModel");
        n.g(peerRomVersion, "peerRomVersion");
        return new MiPlayConnectPeerVoiceTimeEvent(j2, mediaType, deviceType, peerModel, peerRomVersion);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MiPlayConnectPeerVoiceTimeEvent)) {
            return false;
        }
        MiPlayConnectPeerVoiceTimeEvent miPlayConnectPeerVoiceTimeEvent = (MiPlayConnectPeerVoiceTimeEvent) obj;
        return this.duration == miPlayConnectPeerVoiceTimeEvent.duration && n.c(this.mediaType, miPlayConnectPeerVoiceTimeEvent.mediaType) && n.c(this.deviceType, miPlayConnectPeerVoiceTimeEvent.deviceType) && n.c(this.peerModel, miPlayConnectPeerVoiceTimeEvent.peerModel) && n.c(this.peerRomVersion, miPlayConnectPeerVoiceTimeEvent.peerRomVersion);
    }

    public final String getDeviceType() {
        return this.deviceType;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final String getMediaType() {
        return this.mediaType;
    }

    public final String getPeerModel() {
        return this.peerModel;
    }

    public final String getPeerRomVersion() {
        return this.peerRomVersion;
    }

    public int hashCode() {
        return (((((((Long.hashCode(this.duration) * 31) + this.mediaType.hashCode()) * 31) + this.deviceType.hashCode()) * 31) + this.peerModel.hashCode()) * 31) + this.peerRomVersion.hashCode();
    }

    public String toString() {
        return "MiPlayConnectPeerVoiceTimeEvent(duration=" + this.duration + ", mediaType=" + this.mediaType + ", deviceType=" + this.deviceType + ", peerModel=" + this.peerModel + ", peerRomVersion=" + this.peerRomVersion + ")";
    }
}
