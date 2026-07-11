package miui.systemui.flashlight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.UserHandle;
import android.util.Log;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.util.CompatUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightOperationReceiver extends BroadcastReceiver {
    public static final String ACTION_OPERATION = "miui.systemui.action.ACTION_OPERATE_FLASHLIGHT";
    public static final Companion Companion = new Companion(null);
    public static final String KEY_CAMERA_ID = "miui_flashlight_camera_id";
    public static final String KEY_IS_CAN_USE_STRENGTH_LEVEL = "miui_flashlight_is_can_use_strength_level";
    public static final String KEY_STRENGTH = "miui_flashlight_strength";
    public static final String TAG = "MiFlash_OperationReceiver";

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final void operateCamera(Context context, Intent intent) throws CameraAccessException {
        String stringExtra = intent.getStringExtra(KEY_CAMERA_ID);
        int intExtra = intent.getIntExtra(KEY_STRENGTH, -1);
        boolean booleanExtra = intent.getBooleanExtra(KEY_IS_CAN_USE_STRENGTH_LEVEL, false);
        Log.i(TAG, "onReceive cameraId=" + stringExtra + " strength=" + intExtra + " isCanUseStrengthLevel=" + booleanExtra + " code=" + hashCode());
        Object systemService = context.getSystemService("camera");
        CameraManager cameraManager = systemService instanceof CameraManager ? (CameraManager) systemService : null;
        if (cameraManager == null || stringExtra == null) {
            return;
        }
        if (intExtra == 0) {
            cameraManager.setTorchMode(stringExtra, false);
            return;
        }
        if (1 > intExtra || intExtra >= 101) {
            return;
        }
        if (booleanExtra) {
            cameraManager.turnOnTorchWithStrengthLevel(stringExtra, intExtra);
        } else {
            cameraManager.setTorchMode(stringExtra, true);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        n.g(context, "context");
        n.g(intent, "intent");
        if (n.c(intent.getAction(), ACTION_OPERATION)) {
            try {
                operateCamera(context, intent);
            } catch (Exception e2) {
                Log.e(TAG, "operateCamera error", e2);
            }
        }
    }

    public final void register(Context context) {
        n.g(context, "context");
        Log.d(TAG, "register");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_OPERATION);
        intentFilter.addCategory("android.intent.category.DEFAULT");
        CompatUtils.Context.INSTANCE.registerReceiverAsUserCompat(context, this, UserHandle.ALL, intentFilter, null, null, 4);
    }

    public final void unRegister(Context context) {
        n.g(context, "context");
        Log.d(TAG, "unRegister");
        context.unregisterReceiver(this);
    }
}
