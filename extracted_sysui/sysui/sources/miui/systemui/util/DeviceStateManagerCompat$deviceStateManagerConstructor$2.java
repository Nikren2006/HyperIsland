package miui.systemui.util;

import android.util.Log;
import java.lang.reflect.Constructor;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class DeviceStateManagerCompat$deviceStateManagerConstructor$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final DeviceStateManagerCompat$deviceStateManagerConstructor$2 INSTANCE = new DeviceStateManagerCompat$deviceStateManagerConstructor$2();

    public DeviceStateManagerCompat$deviceStateManagerConstructor$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Constructor<? extends Object> invoke() {
        try {
            Class sDeviceStateManagerClass = DeviceStateManagerCompat.INSTANCE.getSDeviceStateManagerClass();
            if (sDeviceStateManagerClass != null) {
                return sDeviceStateManagerClass.getConstructor(null);
            }
            return null;
        } catch (Throwable th) {
            Log.e("DeviceStateManagerCompat", "get DeviceStateManager instance failed.", th);
            return null;
        }
    }
}
