package m0;

import com.miui.miplay.audio.data.AdvertisementParam;
import com.miui.miplay.audio.data.MediaMetaData;
import java.util.List;

/* JADX INFO: renamed from: m0.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public interface InterfaceC0464B {
    void onBufferStateChange(int i2);

    default void onCastModeChange(int i2, int i3) {
    }

    default void onCpAppStateChange(int i2, String str) {
    }

    default void onCpStateChange(String str, int i2) {
    }

    void onMediaMetaChange(MediaMetaData mediaMetaData);

    default void onPlaySpeedChange(float f2) {
    }

    default void onPlaySpeedListChange(List list) {
    }

    void onPlaybackStateChange(int i2);

    default void onPlayingAdvertisementChange(AdvertisementParam advertisementParam) {
    }

    void onPositionChange(long j2);
}
