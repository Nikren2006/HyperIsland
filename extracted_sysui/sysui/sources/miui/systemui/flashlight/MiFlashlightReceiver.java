package miui.systemui.flashlight;

import H0.s;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.flashlight.utils.TrackUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class MiFlashlightReceiver extends BroadcastReceiver {
    public static final String ACTION_CLOSE = "miui.systemui.action.ACTION_CLOSE_FLASHLIGHT";
    public static final String ACTION_FLASHLIGHT = "miui.systemui.action.ACTION_TOGGLE_FLASHLIGHT";
    public static final String ACTION_SHOW = "miui.systemui.action.ACTION_SHOW_FLASHLIGHT";
    public static final String ACTION_SYSTEMUI_RESTART = "miui.systemui.action.ACTION_SYSTEMUI_RESTART";
    public static final String ACTION_SYS_LOW_POWER_CONTROL = "sys.action.lowbattery.control";
    public static final String ACTION_TEMP_STATE_CHANGE = "action_temp_state_change";
    public static final String BATTERY_LEVEL = "batterylevel";
    public static final int CANCEL_NOTIFICATION = 0;
    public static final Companion Companion = new Companion(null);
    public static final String FLASHLIGHT_CLOSE_STATUS = "falshClose";
    public static final String INTENT_EXTRA_TEMP_STATE_LEVEL = "temp_state";
    public static final int NO_NOTIFICATION = 3;
    public static final int RESEND_NOTIFICATION = 2;
    public static final int SEND_NOTIFICATION = 1;
    public static final String TAG = "MiFlash_MiFlashlightReceiver";
    private final MiFlashlightManager flashlightManager;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightReceiver$onClose$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        final /* synthetic */ BroadcastReceiver.PendingResult $pendingResult;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BroadcastReceiver.PendingResult pendingResult) {
            super(0);
            this.$pendingResult = pendingResult;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m138invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m138invoke() {
            this.$pendingResult.finish();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.flashlight.MiFlashlightReceiver$onTempStateChange$1, reason: invalid class name and case insensitive filesystem */
    public static final class C06681 extends o implements Function0 {
        final /* synthetic */ BroadcastReceiver.PendingResult $pendingResult;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06681(BroadcastReceiver.PendingResult pendingResult) {
            super(0);
            this.$pendingResult = pendingResult;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m139invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m139invoke() {
            this.$pendingResult.finish();
        }
    }

    public MiFlashlightReceiver(MiFlashlightManager flashlightManager) {
        n.g(flashlightManager, "flashlightManager");
        this.flashlightManager = flashlightManager;
    }

    private final void onClose(Context context) {
        this.flashlightManager.asyncOperate(false, new AnonymousClass1(goAsync()));
        trackFlashlightClose(context);
    }

    private final void onLowPower(Intent intent) {
        int intExtra = intent.getIntExtra(BATTERY_LEVEL, 0);
        int intExtra2 = intent.getIntExtra(FLASHLIGHT_CLOSE_STATUS, 0);
        Log.i(TAG, "onReceive low power control level: " + intExtra + "  flashlightCloseStatus: " + intExtra2);
        this.flashlightManager.onLowPowerControl(intExtra, intExtra2);
    }

    private final void onSystemUIRestart() {
        Log.i(TAG, "onSystemUIRestart");
        this.flashlightManager.resetStatus();
    }

    private final void onTempStateChange(Intent intent) {
        int intExtra = intent.getIntExtra(INTENT_EXTRA_TEMP_STATE_LEVEL, 0) % 10;
        Log.i(TAG, "onReceive temp state change: " + intExtra);
        if (intExtra != 1) {
            if (intExtra == 3) {
                this.flashlightManager.asyncReduceBy25(new C06681(goAsync()));
                return;
            } else if (intExtra != 4 && intExtra != 5) {
                return;
            }
        }
        this.flashlightManager.hideFlashlight();
    }

    private final void onToggleChange(Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra("flashlight_is_open_or_close", false);
        int intExtra = intent.getIntExtra("notification_operation", 0);
        Log.i(TAG, "onReceive isOpened = " + booleanExtra + ", notificationOperation = " + intExtra);
        this.flashlightManager.saveTorchStatus(booleanExtra);
        if (intExtra == 0) {
            if (this.flashlightManager.isHasFlashlightWindow()) {
                return;
            }
            this.flashlightManager.removeFocusNotification();
        } else if (intExtra == 1) {
            if (this.flashlightManager.isHasFlashlightWindow()) {
                return;
            }
            this.flashlightManager.sendFocusNotification();
        } else {
            if (intExtra != 2) {
                return;
            }
            this.flashlightManager.removeFocusNotification();
            this.flashlightManager.sendFocusNotification();
        }
    }

    private final void trackFlashlightClose(Context context) {
        Object systemService = context.getSystemService("keyguard");
        n.e(systemService, "null cannot be cast to non-null type android.app.KeyguardManager");
        TrackUtils.INSTANCE.trackClick(context, false, ((KeyguardManager) systemService).isKeyguardLocked() ? TrackUtils.CLICK_LOCATION_KEYGUARD : TrackUtils.CLICK_LOCATION_NOTIFICATION);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        n.g(context, "context");
        n.g(intent, "intent");
        Log.i(TAG, "onReceive " + intent);
        String action = intent.getAction();
        if (action != null) {
            switch (action.hashCode()) {
                case -1976061057:
                    if (action.equals(ACTION_SYS_LOW_POWER_CONTROL)) {
                        onLowPower(intent);
                        break;
                    }
                    break;
                case -1628016271:
                    if (action.equals(ACTION_SYSTEMUI_RESTART)) {
                        onSystemUIRestart();
                        break;
                    }
                    break;
                case -1313843414:
                    action.equals(ACTION_SHOW);
                    break;
                case 647674848:
                    if (action.equals(ACTION_TEMP_STATE_CHANGE)) {
                        onTempStateChange(intent);
                        break;
                    }
                    break;
                case 951703027:
                    if (action.equals(ACTION_FLASHLIGHT)) {
                        onToggleChange(intent);
                        break;
                    }
                    break;
                case 1904751275:
                    if (action.equals(ACTION_CLOSE)) {
                        onClose(context);
                        break;
                    }
                    break;
            }
        }
    }
}
