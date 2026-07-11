package miui.systemui.quicksettings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Switch;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.qs.MiuiQSTile;
import com.android.systemui.plugins.miui.qs.MiuiQSTilePlugin;
import com.android.systemui.plugins.qs.QSTile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import miui.systemui.quicksettings.wireless.WirelessChargingUtil;
import miui.systemui.util.DeviceUtils;
import miui.systemui.util.ThreadUtils;
import miui.util.TypefaceUtils;

/* JADX INFO: loaded from: classes4.dex */
@Requires(target = MiuiQSTilePlugin.class, version = 1)
public class CameraHandleTile implements MiuiQSTile {
    private static final String ACTION_HANDLE_BATTERY_STATE_CHANGED = "miui.intent.action.ACTION_HANDLE_BATTERY_STATE_CHANGED";
    public static final String ANDROID_SHOW_FRAGMENT = ":android:show_fragment";
    public static final String COM_ANDROID_SETTINGS = "com.android.settings";
    public static final String COM_ANDROID_SETTINGS_CAMERAGRIP_CAMERA_GRIP_DETAIL = "com.android.settings.cameragrip.CameraGripDetail";
    public static final String COM_ANDROID_SETTINGS_SUB_SETTINGS = "com.android.settings.SubSettings";
    private static final String EXTRA_HANDLE_CONNECT_STATE = "miui.intent.extra.EXTRA_HANDLE_CONNECT_STATE";
    private static final int EXTRA_VALUE_CONNECTED_STATE = 1;
    private static final int EXTRA_VALUE_DISCONNECTED_STATE = 0;
    public static final String KEY_SETTINGS_CAMERA_HANDLE_MODE = "power_camera_handle_mode";
    public static final int KEY_SETTINGS_CAMERA_HANDLE_MODE_ALWAYS = 1;
    public static final int KEY_SETTINGS_CAMERA_HANDLE_MODE_EMERGENCY = 0;
    private static final String TAG = "CameraHandleTile";
    private static final String TILE_SPEC = "camerahandle";
    private final ArrayList<QSTile.Callback> mCallbacks;
    private boolean mListening;
    private final AtomicBoolean mOldConnectState;
    private Context mPluginContext;
    private final Receiver mReceiver = new Receiver();
    private QSTile.State mState;

    public final class Receiver extends BroadcastReceiver {
        boolean mRegistered;

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$setListening$0(boolean z2) {
            if (z2 && !this.mRegistered) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(CameraHandleTile.ACTION_HANDLE_BATTERY_STATE_CHANGED);
                CameraHandleTile.this.mPluginContext.registerReceiver(this, intentFilter, 2);
                this.mRegistered = true;
                return;
            }
            if (z2 || !this.mRegistered) {
                return;
            }
            CameraHandleTile.this.mPluginContext.unregisterReceiver(this);
            this.mRegistered = false;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (CameraHandleTile.ACTION_HANDLE_BATTERY_STATE_CHANGED.equals(intent.getAction())) {
                CameraHandleTile.this.mOldConnectState.set(intent.getIntExtra(CameraHandleTile.EXTRA_HANDLE_CONNECT_STATE, 0) == 1);
                CameraHandleTile.this.refreshState(null);
                CameraHandleTile.this.updateUiCallback();
            }
        }

        public void setListening(final boolean z2) {
            ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: miui.systemui.quicksettings.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5878a.lambda$setListening$0(z2);
                }
            });
        }

        private Receiver() {
        }
    }

    public CameraHandleTile(Context context, Context context2) {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        this.mOldConnectState = atomicBoolean;
        this.mPluginContext = context2;
        this.mState = new QSTile.State();
        this.mCallbacks = new ArrayList<>();
        if (!atomicBoolean.get()) {
            this.mState.state = 0;
        } else if (isCameraHandleModeAlways()) {
            this.mState.state = 2;
        } else {
            this.mState.state = 1;
        }
    }

    private boolean isCameraHandleModeAlways() {
        return WirelessChargingUtil.getIntForUser(TypefaceUtils.getContext().getContentResolver(), KEY_SETTINGS_CAMERA_HANDLE_MODE, 0, 0) == 1;
    }

    private void setCameraHandleMode(int i2) {
        WirelessChargingUtil.putIntForUser(TypefaceUtils.getContext().getContentResolver(), KEY_SETTINGS_CAMERA_HANDLE_MODE, i2, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUiCallback() {
        synchronized (this.mCallbacks) {
            try {
                Iterator<QSTile.Callback> it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    it.next().onStateChanged(this.mState);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void addCallback(QSTile.Callback callback) {
        synchronized (this.mCallbacks) {
            try {
                if (!this.mCallbacks.contains(callback)) {
                    this.mCallbacks.add(callback);
                    callback.onStateChanged(this.mState);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public String composeChangeAnnouncement() {
        return null;
    }

    public Intent getLongClickIntent() {
        if (!this.mOldConnectState.get()) {
            return null;
        }
        Intent intent = new Intent();
        intent.setPackage("com.android.settings");
        intent.setClassName("com.android.settings", COM_ANDROID_SETTINGS_SUB_SETTINGS);
        intent.putExtra(ANDROID_SHOW_FRAGMENT, COM_ANDROID_SETTINGS_CAMERAGRIP_CAMERA_GRIP_DETAIL);
        return intent;
    }

    public int getMetricsCategory() {
        return 0;
    }

    public QSTile.State getState() {
        return this.mState;
    }

    public String getTileSpec() {
        return TILE_SPEC;
    }

    public void handleClick() {
        if (this.mOldConnectState.get()) {
            setCameraHandleMode(!isCameraHandleModeAlways() ? 1 : 0);
            refreshState(null);
            updateUiCallback();
        }
    }

    public boolean isAvailable() {
        return DeviceUtils.isSupportCameraHandle();
    }

    public QSTile.State newTileState() {
        return this.mState;
    }

    public void refreshState(Object obj) {
        this.mState.label = this.mPluginContext.getString(R.string.quick_settings_camera_grip_charge);
        this.mState.icon = new DrawableIcon(this.mPluginContext.getResources().getDrawable(R.drawable.ic_camera_handle_charge));
        if (!this.mOldConnectState.get()) {
            this.mState.state = 0;
        } else if (isCameraHandleModeAlways()) {
            this.mState.state = 2;
        } else {
            this.mState.state = 1;
        }
        this.mState.expandedAccessibilityClassName = Switch.class.getName();
        QSTile.State state = this.mState;
        state.contentDescription = state.label;
    }

    public void removeCallback(QSTile.Callback callback) {
        synchronized (this.mCallbacks) {
            this.mCallbacks.remove(callback);
        }
    }

    public void setListening(boolean z2) {
        if (this.mListening == z2) {
            return;
        }
        this.mListening = z2;
        if (z2) {
            this.mReceiver.setListening(true);
        } else {
            this.mReceiver.setListening(false);
        }
    }
}
