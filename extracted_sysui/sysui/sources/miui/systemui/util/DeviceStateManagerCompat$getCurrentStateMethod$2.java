package miui.systemui.util;

import android.util.Log;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class DeviceStateManagerCompat$getCurrentStateMethod$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final DeviceStateManagerCompat$getCurrentStateMethod$2 INSTANCE = new DeviceStateManagerCompat$getCurrentStateMethod$2();

    public DeviceStateManagerCompat$getCurrentStateMethod$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            Class sDeviceStateManagerClass = DeviceStateManagerCompat.INSTANCE.getSDeviceStateManagerClass();
            if (sDeviceStateManagerClass != null) {
                return sDeviceStateManagerClass.getMethod("getCurrentState", null);
            }
            return null;
        } catch (Throwable th) {
            Log.e("DeviceStateManagerCompat", "get getCurrentState method failed.", th);
            return null;
        }
    }
}
