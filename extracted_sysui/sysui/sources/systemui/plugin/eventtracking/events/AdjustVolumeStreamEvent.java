package systemui.plugin.eventtracking.events;

import kotlin.jvm.internal.n;
import systemui.plugin.eventtracking.EventID;
import systemui.plugin.eventtracking.EventKey;

/* JADX INFO: loaded from: classes5.dex */
@EventID(id = "event_volume_adjust_stream")
public final class AdjustVolumeStreamEvent {

    @EventKey(key = "stream_name")
    private final String stream;

    public AdjustVolumeStreamEvent(String stream) {
        n.g(stream, "stream");
        this.stream = stream;
    }

    public static /* synthetic */ AdjustVolumeStreamEvent copy$default(AdjustVolumeStreamEvent adjustVolumeStreamEvent, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = adjustVolumeStreamEvent.stream;
        }
        return adjustVolumeStreamEvent.copy(str);
    }

    public final String component1() {
        return this.stream;
    }

    public final AdjustVolumeStreamEvent copy(String stream) {
        n.g(stream, "stream");
        return new AdjustVolumeStreamEvent(stream);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof AdjustVolumeStreamEvent) && n.c(this.stream, ((AdjustVolumeStreamEvent) obj).stream);
    }

    public final String getStream() {
        return this.stream;
    }

    public int hashCode() {
        return this.stream.hashCode();
    }

    public String toString() {
        return "AdjustVolumeStreamEvent(stream=" + this.stream + ")";
    }
}
