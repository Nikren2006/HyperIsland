package miui.systemui.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.UserHandle;
import android.util.Log;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes4.dex */
public final class CompatUtils$Context$REGISTER_RECEIVER_AS_USER$2 extends kotlin.jvm.internal.o implements Function0 {
    public static final CompatUtils$Context$REGISTER_RECEIVER_AS_USER$2 INSTANCE = new CompatUtils$Context$REGISTER_RECEIVER_AS_USER$2();

    public CompatUtils$Context$REGISTER_RECEIVER_AS_USER$2() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Method invoke() {
        try {
            Method declaredMethod = Context.class.getDeclaredMethod("registerReceiverAsUser", BroadcastReceiver.class, UserHandle.class, IntentFilter.class, String.class, Handler.class, Integer.TYPE);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            Log.e("CompatUtils.Context", "get registerReceiverAsUser method failed.");
            return null;
        }
    }
}
