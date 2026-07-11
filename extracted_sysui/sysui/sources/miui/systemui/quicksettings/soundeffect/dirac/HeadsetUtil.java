package miui.systemui.quicksettings.soundeffect.dirac;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioSystem;
import android.os.Build;
import android.os.SystemProperties;
import android.util.Log;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.miui.miplay.audio.data.DeviceInfo;

/* JADX INFO: loaded from: classes4.dex */
public class HeadsetUtil {
    public static final String BUTTON_JACK_MODE_KEY_PROPERTY = "persist.sys.button_jack_profile";
    public static final String BUTTON_JACK_SWITCH_KEY_PROPERTY = "persist.sys.button_jack_switch";
    private static final int DEVICE_OUT_USB_DEVICE = 16384;
    public static final String MODE_MUSIC = "music";
    public static final String MODE_VOLUME = "volume";
    public static final String NEW_BUTTON_JACK_MODE_KEY_PROPERTY = "persist.audio.button_jack.profile";
    public static final String NEW_BUTTON_JACK_SWITCH_KEY_PROPERTY = "persist.audio.button_jack.switch";
    private static final String[] NOT_SUPPORT_BLUETOOTH_EFFECT = {"atom", "bomb", "cezanne", "cannon", "cannong", "lancelot", "pyxis", "grus", "vela", "camellia", "camellian", "galahad", "rosemary", "secret", "maltose", "begonia", "begoniain"};
    public static final String P_BUTTON_JACK_MODE_KEY_PROPERTY = "persist.vendor.audio.button_jack.profile";
    public static final String P_BUTTON_JACK_SWITCH_KEY_PROPERTY = "persist.vendor.audio.button_jack.switch";
    private static final int P_VERSION_CODE = 28;
    public static final String SWITCH_OFF = "0";
    public static final String SWITCH_ON = "1";
    private static final String TAG = "HeadsetUtil";

    public static String getMode() {
        return SystemProperties.get(getModeProperty(), "volume");
    }

    private static String getModeProperty() {
        return "null".equals(SystemProperties.get(NEW_BUTTON_JACK_MODE_KEY_PROPERTY, "null")) ? useNewProperty() ? P_BUTTON_JACK_MODE_KEY_PROPERTY : BUTTON_JACK_MODE_KEY_PROPERTY : NEW_BUTTON_JACK_MODE_KEY_PROPERTY;
    }

    public static boolean getSwitchOn() {
        return "1".equals(SystemProperties.get(getSwitchProperty(), "0"));
    }

    private static String getSwitchProperty() {
        return "null".equals(SystemProperties.get(NEW_BUTTON_JACK_SWITCH_KEY_PROPERTY, "null")) ? useNewProperty() ? P_BUTTON_JACK_SWITCH_KEY_PROPERTY : BUTTON_JACK_SWITCH_KEY_PROPERTY : NEW_BUTTON_JACK_SWITCH_KEY_PROPERTY;
    }

    public static boolean isBluetoothMiSoundEnable() {
        return SystemProperties.getBoolean("ro.vendor.audio.bluetooth.support.misound", true);
    }

    public static boolean isBluetoothSetOn() {
        for (String str : NOT_SUPPORT_BLUETOOTH_EFFECT) {
            if (Build.DEVICE.equals(str)) {
                return false;
            }
        }
        return BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(2) == 2 || AudioSystem.getDeviceConnectionState(536870912, "") == 1;
    }

    public static boolean isBtA2dpInUse(Context context) {
        return ((AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT)).isBluetoothA2dpOn() && isBluetoothSetOn();
    }

    public static boolean isHeadSetOn(Context context) {
        return isWiredHeadsetOn(context) || isUsbHeadsetOn() || isBluetoothSetOn();
    }

    public static boolean isUsbHeadsetOn() {
        return AudioSystem.getDeviceConnectionState(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL, "") == 1;
    }

    public static boolean isWiredHeadsetOn(Context context) {
        return ((AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT)).isWiredHeadsetOn();
    }

    public static boolean isWiredOrUsbHeadseton(Context context) {
        return isWiredHeadsetOn(context) || isUsbHeadsetOn();
    }

    public static void setMode(String str, Context context) {
        Log.i(TAG, "new mode, mode=" + str);
        ((AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT)).setParameters(getModeProperty() + "=" + str);
    }

    public static void setSwitchOn(boolean z2, Context context) {
        Log.i(TAG, "set switch on = " + z2);
        AudioManager audioManager = (AudioManager) context.getSystemService(DeviceInfo.AUDIO_SUPPORT);
        StringBuilder sb = new StringBuilder();
        sb.append(getSwitchProperty());
        sb.append("=");
        sb.append(z2 ? "1" : "0");
        audioManager.setParameters(sb.toString());
    }

    private static boolean useNewProperty() {
        return true;
    }
}
