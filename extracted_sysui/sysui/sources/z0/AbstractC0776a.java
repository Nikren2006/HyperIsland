package z0;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: z0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0776a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f7126a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f7127b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f7128c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f7129d;

    public static BluetoothClass a(BluetoothDevice bluetoothDevice) {
        try {
            if (f7127b == null) {
                Method method = BluetoothDevice.class.getMethod("getBluetoothClass", null);
                f7127b = method;
                method.setAccessible(true);
            }
            return (BluetoothClass) f7127b.invoke(bluetoothDevice, null);
        } catch (Exception e2) {
            e.b(f7126a, "getBluetoothClass", e2);
            return null;
        }
    }

    public static int b(BluetoothDevice bluetoothDevice) {
        try {
            if (f7129d == null) {
                Method declaredMethod = bluetoothDevice.getClass().getDeclaredMethod("getRemoteDeviceTypeData", Integer.TYPE);
                f7129d = declaredMethod;
                declaredMethod.setAccessible(true);
            }
            Method method = f7129d;
            if (method != null) {
                return ((Integer) method.invoke(bluetoothDevice, 0)).intValue();
            }
        } catch (Exception e2) {
            e.b(f7126a, "getRemoteDeviceTypeData", e2);
        }
        return 0;
    }

    public static int c(BluetoothDevice bluetoothDevice, String str) {
        try {
            if (f7128c == null) {
                Method method = BluetoothDevice.class.getMethod("getSpecificCodecStatus", String.class);
                f7128c = method;
                method.setAccessible(true);
            }
            int iIntValue = ((Integer) f7128c.invoke(bluetoothDevice, str)).intValue();
            e.c(f7126a, "getSpecificCodecStatus: " + iIntValue);
            return iIntValue;
        } catch (Exception e2) {
            e.b(f7126a, "getSpecificCodecStatus ", e2);
            return -1;
        }
    }

    public static boolean d(BluetoothDevice bluetoothDevice) {
        return bluetoothDevice.getBluetoothClass().getDeviceClass() == 1056 && c(bluetoothDevice, "bluetooth_eir_manufacture") == 911;
    }

    public static boolean e(BluetoothClass bluetoothClass, boolean z2) {
        if (bluetoothClass == null) {
            return false;
        }
        int deviceClass = bluetoothClass.getDeviceClass();
        return (!z2 && deviceClass == 1044) || deviceClass == 1052 || deviceClass == 1056 || deviceClass == 1060 || deviceClass == 1064;
    }

    public static boolean f(BluetoothDevice bluetoothDevice) {
        return c(bluetoothDevice, "ABSOLUTEVOLUME") == 1;
    }
}
