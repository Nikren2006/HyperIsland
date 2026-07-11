package miui.systemui.util;

import android.util.Log;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class DeviceStateManagerCompat$sFoldStateListenerClass$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final DeviceStateManagerCompat$sFoldStateListenerClass$2 INSTANCE = new DeviceStateManagerCompat$sFoldStateListenerClass$2();

    public DeviceStateManagerCompat$sFoldStateListenerClass$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Class<?> invoke() {
        try {
            return Class.forName("android.hardware.devicestate.DeviceStateManager$FoldStateListener");
        } catch (Throwable th) {
            Log.e("DeviceStateManagerCompat", "get FoldStateListener class failed.", th);
            return null;
        }
    }
}
