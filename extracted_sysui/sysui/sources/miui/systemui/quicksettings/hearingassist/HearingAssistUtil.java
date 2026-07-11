package miui.systemui.quicksettings.hearingassist;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;

/* JADX INFO: loaded from: classes4.dex */
public class HearingAssistUtil {
    public static boolean checkRecordPermission(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return (packageManager.checkPermission("android.permission.RECORD_AUDIO", HearingAssistConstant.MISOUND_PACKAGE_NAME) == 0) && (packageManager.checkPermission("android.permission.BLUETOOTH_CONNECT", HearingAssistConstant.MISOUND_PACKAGE_NAME) == 0);
    }

    public static boolean isBluetoothConnected() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled() && (defaultAdapter.getProfileConnectionState(1) == 2 || defaultAdapter.getProfileConnectionState(2) == 2);
    }
}
