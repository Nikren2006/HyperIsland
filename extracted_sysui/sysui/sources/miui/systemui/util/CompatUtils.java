package miui.systemui.util;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.UserHandle;
import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
public final class CompatUtils {
    public static final CompatUtils INSTANCE = new CompatUtils();

    public static final class Context {
        public static final int RECEIVER_EXPORTED = 2;
        private static final String TAG = "CompatUtils.Context";
        public static final Context INSTANCE = new Context();
        private static final H0.d REGISTER_RECEIVER_AS_USER$delegate = H0.e.b(CompatUtils$Context$REGISTER_RECEIVER_AS_USER$2.INSTANCE);

        private Context() {
        }

        private final Method getREGISTER_RECEIVER_AS_USER() {
            return (Method) REGISTER_RECEIVER_AS_USER$delegate.getValue();
        }

        public final Intent registerReceiverAsUserCompat(android.content.Context context, BroadcastReceiver broadcastReceiver, UserHandle userHandle, IntentFilter intentFilter, String str, Handler handler, int i2) {
            kotlin.jvm.internal.n.g(context, "<this>");
            try {
                Method register_receiver_as_user = getREGISTER_RECEIVER_AS_USER();
                return (Intent) (register_receiver_as_user != null ? register_receiver_as_user.invoke(context, broadcastReceiver, userHandle, intentFilter, str, handler, Integer.valueOf(i2)) : null);
            } catch (Throwable unused) {
                Log.e(TAG, "invoke registerReceiverAsUser method failed.");
                return context.registerReceiverAsUser(broadcastReceiver, userHandle, intentFilter, str, handler);
            }
        }
    }

    private CompatUtils() {
    }
}
