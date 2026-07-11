package t0;

import android.os.Bundle;
import com.xiaomi.cast.api.DeviceInfo;
import com.xiaomi.cast.api.IMiCastSDK;
import com.xiaomi.cast.api.MediaMetaData;
import l0.C0440c;
import l0.InterfaceC0441d;
import s0.g;
import s0.l;

/* JADX INFO: loaded from: classes2.dex */
public abstract class c {
    public static b a(DeviceInfo deviceInfo, IMiCastSDK iMiCastSDK, l lVar) {
        return new b(d(deviceInfo, iMiCastSDK, lVar), deviceInfo);
    }

    public static InterfaceC0441d b(DeviceInfo deviceInfo, IMiCastSDK iMiCastSDK, l lVar) {
        return new g(deviceInfo, iMiCastSDK, lVar);
    }

    public static com.miui.miplay.audio.data.DeviceInfo c(DeviceInfo deviceInfo) {
        return new com.miui.miplay.audio.data.DeviceInfo(deviceInfo.getName(), null, i(deviceInfo.getType()), g(deviceInfo.getExtra()));
    }

    public static C0440c d(DeviceInfo deviceInfo, IMiCastSDK iMiCastSDK, l lVar) {
        return new C0440c(deviceInfo.getId(), c(deviceInfo), b(deviceInfo, iMiCastSDK, lVar), e(deviceInfo.getConnectState()), 0);
    }

    public static int e(int i2) {
        return (i2 == 1 || i2 == 3) ? 3 : 0;
    }

    public static MediaMetaData f(com.miui.miplay.audio.data.MediaMetaData mediaMetaData) {
        if (mediaMetaData == null) {
            return new MediaMetaData();
        }
        MediaMetaData mediaMetaData2 = new MediaMetaData();
        mediaMetaData2.setAlbum(mediaMetaData.getAlbum());
        mediaMetaData2.setArtist(mediaMetaData.getArtist());
        mediaMetaData2.setBitmap(mediaMetaData.getArt());
        mediaMetaData2.setDuration(mediaMetaData.getDuration());
        mediaMetaData2.setTitle(mediaMetaData.getTitle());
        return mediaMetaData2;
    }

    public static Bundle g(Bundle bundle) {
        int i2;
        boolean z2 = false;
        int i3 = bundle.getInt(com.miui.miplay.audio.data.DeviceInfo.EXTRA_KEY_MI_PLAY_DEVICE_TYPE, 0);
        Bundle bundle2 = new Bundle(bundle);
        if (i3 == 1) {
            i2 = 2;
        } else {
            if (i3 == 3) {
                return bundle2;
            }
            i2 = 4;
            if (i3 == 1000) {
                z2 = true;
            }
        }
        bundle2.putInt(com.miui.miplay.audio.data.DeviceInfo.EXTRA_KEY_MI_PLAY_DEVICE_TYPE, i2);
        if (z2) {
            bundle2.putBoolean(com.miui.miplay.audio.data.DeviceInfo.EXTRA_KEY_IS_GROUP_DEVICE, true);
        }
        return bundle2;
    }

    public static int h(int i2) {
        int i3 = 1;
        if (i2 != 1) {
            i3 = 3;
            if (i2 != 3) {
                return 2;
            }
        }
        return i3;
    }

    public static int i(int i2) {
        if (i2 == -1) {
            return 0;
        }
        return i2 == 3 ? 2 : 3;
    }
}
