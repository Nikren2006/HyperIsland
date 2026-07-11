package miui.systemui.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
@SuppressLint({"PrivateApi"})
public final class DeviceStateManagerCompat {
    private static final String TAG = "DeviceStateManagerCompat";
    public static final DeviceStateManagerCompat INSTANCE = new DeviceStateManagerCompat();
    private static final H0.d sDeviceStateManagerClass$delegate = H0.e.b(DeviceStateManagerCompat$sDeviceStateManagerClass$2.INSTANCE);
    private static final H0.d sFoldStateListenerClass$delegate = H0.e.b(DeviceStateManagerCompat$sFoldStateListenerClass$2.INSTANCE);
    private static final H0.d deviceStateManagerConstructor$delegate = H0.e.b(DeviceStateManagerCompat$deviceStateManagerConstructor$2.INSTANCE);
    private static final H0.d foldStateListenerConstructor$delegate = H0.e.b(DeviceStateManagerCompat$foldStateListenerConstructor$2.INSTANCE);
    private static final H0.d registerCallbackMethod$delegate = H0.e.b(DeviceStateManagerCompat$registerCallbackMethod$2.INSTANCE);
    private static final H0.d unregisterCallbackMethod$delegate = H0.e.b(DeviceStateManagerCompat$unregisterCallbackMethod$2.INSTANCE);
    private static final H0.d getCurrentStateMethod$delegate = H0.e.b(DeviceStateManagerCompat$getCurrentStateMethod$2.INSTANCE);

    public interface FoldStateCallback {
        void onFoldStateChanged(boolean z2);
    }

    private DeviceStateManagerCompat() {
    }

    private final Constructor<? extends Object> getDeviceStateManagerConstructor() {
        return (Constructor) deviceStateManagerConstructor$delegate.getValue();
    }

    private final Constructor<? extends Object> getFoldStateListenerConstructor() {
        return (Constructor) foldStateListenerConstructor$delegate.getValue();
    }

    private final Method getGetCurrentStateMethod() {
        return (Method) getCurrentStateMethod$delegate.getValue();
    }

    private final Method getRegisterCallbackMethod() {
        return (Method) registerCallbackMethod$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> getSDeviceStateManagerClass() {
        return (Class) sDeviceStateManagerClass$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> getSFoldStateListenerClass() {
        return (Class) sFoldStateListenerClass$delegate.getValue();
    }

    private final Method getUnregisterCallbackMethod() {
        return (Method) unregisterCallbackMethod$delegate.getValue();
    }

    public final int getCurrentStateCompat(Object obj) {
        try {
            Method getCurrentStateMethod = getGetCurrentStateMethod();
            Object objInvoke = getCurrentStateMethod != null ? getCurrentStateMethod.invoke(obj, null) : null;
            Integer num = objInvoke instanceof Integer ? (Integer) objInvoke : null;
            if (num != null) {
                return num.intValue();
            }
            return 0;
        } catch (Throwable th) {
            Log.e(TAG, "Invoke getCurrentStateMethod failed.", th);
            return 0;
        }
    }

    public final Object getDeviceStateManagerInstance() {
        try {
            Constructor<? extends Object> deviceStateManagerConstructor = getDeviceStateManagerConstructor();
            if (deviceStateManagerConstructor != null) {
                return deviceStateManagerConstructor.newInstance(null);
            }
            return null;
        } catch (Throwable th) {
            Log.e(TAG, "Invoke deviceStateManagerConstructor failed.", th);
            return null;
        }
    }

    public final Object getFoldStateListenerInstance(Context context, Consumer<Boolean> consumer) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(consumer, "consumer");
        try {
            Constructor<? extends Object> foldStateListenerConstructor = getFoldStateListenerConstructor();
            if (foldStateListenerConstructor != null) {
                return foldStateListenerConstructor.newInstance(context, consumer);
            }
            return null;
        } catch (Throwable th) {
            Log.e(TAG, "Invoke foldStateListenerConstructor failed.", th);
            return null;
        }
    }

    public final Object registerCallbackCompat(Object obj, Executor executor, Object obj2) {
        kotlin.jvm.internal.n.g(executor, "executor");
        try {
            Method registerCallbackMethod = getRegisterCallbackMethod();
            if (registerCallbackMethod != null) {
                return registerCallbackMethod.invoke(obj, executor, obj2);
            }
            return null;
        } catch (Throwable th) {
            return Integer.valueOf(Log.e(TAG, "Invoke registerCallbackMethod failed.", th));
        }
    }

    public final Object unregisterCallbackCompat(Object obj, Object obj2) {
        try {
            Method unregisterCallbackMethod = getUnregisterCallbackMethod();
            if (unregisterCallbackMethod != null) {
                return unregisterCallbackMethod.invoke(obj, obj2);
            }
            return null;
        } catch (Throwable th) {
            return Integer.valueOf(Log.e(TAG, "Invoke unregisterCallbackMethod failed.", th));
        }
    }
}
