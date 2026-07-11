package miui.systemui.util;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class DeviceStateManagerCompat$foldStateListenerConstructor$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final DeviceStateManagerCompat$foldStateListenerConstructor$2 INSTANCE = new DeviceStateManagerCompat$foldStateListenerConstructor$2();

    public DeviceStateManagerCompat$foldStateListenerConstructor$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Constructor<? extends Object> invoke() {
        try {
            Class sFoldStateListenerClass = DeviceStateManagerCompat.INSTANCE.getSFoldStateListenerClass();
            if (sFoldStateListenerClass != null) {
                return sFoldStateListenerClass.getConstructor(Context.class, Consumer.class);
            }
            return null;
        } catch (Throwable th) {
            Log.e("DeviceStateManagerCompat", "get FoldStateListener instance failed.", th);
            return null;
        }
    }
}
