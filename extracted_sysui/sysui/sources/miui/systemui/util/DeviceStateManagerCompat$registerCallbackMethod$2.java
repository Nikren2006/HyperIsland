package miui.systemui.util;

import android.hardware.devicestate.DeviceStateManager;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class DeviceStateManagerCompat$registerCallbackMethod$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final DeviceStateManagerCompat$registerCallbackMethod$2 INSTANCE = new DeviceStateManagerCompat$registerCallbackMethod$2();

    public DeviceStateManagerCompat$registerCallbackMethod$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            Class sDeviceStateManagerClass = DeviceStateManagerCompat.INSTANCE.getSDeviceStateManagerClass();
            if (sDeviceStateManagerClass != null) {
                return sDeviceStateManagerClass.getMethod("registerCallback", Executor.class, DeviceStateManager.DeviceStateCallback.class);
            }
            return null;
        } catch (Throwable th) {
            Log.e("DeviceStateManagerCompat", "get registerCallback method failed.", th);
            return null;
        }
    }
}
