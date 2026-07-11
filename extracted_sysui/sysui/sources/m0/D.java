package m0;

import android.os.Bundle;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface D {
    void onActiveAudioSessionChange(List list);

    void onAudioDeviceListChange(List list);

    default void onAudioParingStateChange() {
    }

    default void onAudioShareFinish() {
    }

    default void onBluetoothDeviceConnectFail(String str) {
    }

    default void onBluetoothDeviceConnectSuccess(String str) {
    }

    default void onCastStateChange(boolean z2) {
    }

    default void onDeviceStartPlaying(Bundle bundle) {
    }

    void onError(int i2, String str);

    void onProjectionStateChange(int i2);

    void onServiceStateChange(int i2);

    default void onVideoCastModeChange(int i2, int i3) {
    }

    default void onVideoCpAppStateChange(int i2, String str) {
    }
}
