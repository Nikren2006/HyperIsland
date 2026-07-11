package z0;

import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.text.TextUtils;
import java.util.List;

/* JADX INFO: renamed from: z0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0777b {
    public static int a(AudioManager audioManager, AudioAttributes audioAttributes) {
        try {
            List<AudioDeviceInfo> audioDevicesForAttributes = audioManager.getAudioDevicesForAttributes(audioAttributes);
            if (audioDevicesForAttributes.isEmpty()) {
                return 0;
            }
            return audioDevicesForAttributes.get(0).getType();
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String b() {
        String[] strArr = {"persist.private.device_name", "persist.sys.device_name", "ro.product.marketname", "ro.product.model"};
        String strA = null;
        for (int i2 = 0; i2 < 4; i2++) {
            String str = strArr[i2];
            if (!TextUtils.isEmpty(strA)) {
                break;
            }
            strA = j.a(str, "");
        }
        return TextUtils.isEmpty(strA) ? "手机" : strA;
    }

    public static boolean c(int i2) {
        return i2 == 0 || i2 == 2 || i2 == 24 || i2 == 22 || i2 == 3 || i2 == 4;
    }
}
