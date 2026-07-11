package miui.systemui.quicksettings.hearingassist;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Switch;
import com.android.systemui.miui.volume.VolumePanelViewController;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.qs.MiuiQSTile;
import com.android.systemui.plugins.miui.qs.MiuiQSTilePlugin;
import com.android.systemui.plugins.qs.QSTile;
import com.miui.miplay.audio.data.DeviceInfo;
import com.miui.misound.a;
import com.miui.misound.b;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import miui.systemui.quicksettings.DrawableIcon;
import miui.systemui.quicksettings.R;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes4.dex */
@Requires(target = MiuiQSTilePlugin.class, version = 1)
public class HearingAssistTile implements MiuiQSTile {
    private static final String ACTION_HEARING_ASSIST_CONFIRM_ACTIVITY = "miui.intent.action.ACTIVITY_HEARING_ASSIST_CONFIRM";
    private static final String HEAR_ASSIST_AUDIO_PARAMETERS = "sound_transmit_enable";
    private static final String HEAR_ASSIST_AUDIO_PARA_OPENED = "sound_transmit_enable=6";
    private static final String MISOUND_HEARING_ASSIST_SETTINGS = "miui.intent.action.MI_HEARING_ASSIST";
    private static final String SYSTEM_SETTINGS_HEARING_ASSIST_ENABLE = "system_settings_hearing_assist_enable";
    private static final String TAG = "HearingAssistTile";
    private static final String TILE_SPEC = "hearingassisttile";
    private boolean mBinding;
    private final ArrayList<QSTile.Callback> mCallbacks;
    private IBinder.DeathRecipient mDeathRecipient;
    private boolean mListening;
    private Context mPluginContext;
    private ServiceConnection mServiceConnection;
    private Intent mServiceIntent;
    private QSTile.State mState;
    private b.a mStateCallBack;
    private volatile boolean mStatus;
    private TransmissionTask mTask;
    private com.miui.misound.a mTransmitBinder;

    public class HATileCallback extends b.a {
        private final WeakReference<HearingAssistTile> mHearingAssistTile;

        public HATileCallback(HearingAssistTile hearingAssistTile) {
            this.mHearingAssistTile = new WeakReference<>(hearingAssistTile);
        }

        @Override // com.miui.misound.b
        public String getClientAppName() {
            return "miui.systemui.plugin";
        }

        @Override // com.miui.misound.b
        public void onBTStateChange(boolean z2) {
            HearingAssistTile hearingAssistTile = this.mHearingAssistTile.get();
            if (hearingAssistTile == null) {
                return;
            }
            Log.d(HearingAssistTile.TAG, "onBTStateChange: " + z2);
            hearingAssistTile.mStatus = HearingAssistTile.this.getAssistEnable();
            hearingAssistTile.refreshState(null);
            hearingAssistTile.refreshTile();
        }

        @Override // com.miui.misound.b
        public void onStateChange(boolean z2) {
            HearingAssistTile hearingAssistTile = this.mHearingAssistTile.get();
            if (hearingAssistTile == null) {
                return;
            }
            Log.d(HearingAssistTile.TAG, "onStateChange: " + z2);
            hearingAssistTile.mStatus = z2;
            hearingAssistTile.refreshState(null);
            hearingAssistTile.refreshTile();
        }

        @Override // com.miui.misound.b
        public void onVolumeChange(int i2) {
        }
    }

    public static class TransmissionTask extends Thread {
        private final WeakReference<HearingAssistTile> mHearingAssistTile;

        public TransmissionTask(HearingAssistTile hearingAssistTile) {
            this.mHearingAssistTile = new WeakReference<>(hearingAssistTile);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            HearingAssistTile hearingAssistTile = this.mHearingAssistTile.get();
            if (hearingAssistTile == null) {
                return;
            }
            try {
                hearingAssistTile.mTransmitBinder.E0(!hearingAssistTile.mStatus, 0);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public HearingAssistTile(Context context, Context context2) {
        this.mPluginContext = context2;
        QSTile.State state = new QSTile.State();
        this.mState = state;
        state.state = 1;
        this.mCallbacks = new ArrayList<>();
    }

    private void collapseStatusBar(Context context) {
        try {
            Object systemService = context.getSystemService(VolumePanelViewController.SERVICE_STATUS_BAR);
            systemService.getClass().getMethod("collapsePanels", null).invoke(systemService, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void destroyAll() {
        Log.d(TAG, "destroyAll");
        this.mStateCallBack = null;
        this.mDeathRecipient = null;
        this.mServiceConnection = null;
        this.mTask = null;
    }

    private Intent genTransmitIntent() {
        Intent intent = new Intent();
        intent.setClassName(HearingAssistConstant.MISOUND_PACKAGE_NAME, HearingAssistConstant.MISOUND_SERVICE_NAME);
        intent.putExtra(HearingAssistConstant.KEY_CONSTANT_FROM_APP, "miui.systemui.plugin");
        intent.setType("systemui");
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getAssistEnable() {
        AudioManager audioManager = (AudioManager) this.mPluginContext.getSystemService(DeviceInfo.AUDIO_SUPPORT);
        boolean zIsBluetoothScoOn = audioManager.isBluetoothScoOn();
        boolean z2 = Settings.Global.getInt(this.mPluginContext.getContentResolver(), SYSTEM_SETTINGS_HEARING_ASSIST_ENABLE, 0) == 1;
        boolean zEquals = audioManager.getParameters(HEAR_ASSIST_AUDIO_PARAMETERS).equals(HEAR_ASSIST_AUDIO_PARA_OPENED);
        Log.d(TAG, "sco: " + zIsBluetoothScoOn + " status: " + z2 + " statusInAS: " + zEquals);
        return (zIsBluetoothScoOn && z2) || zEquals;
    }

    private void init() {
        this.mStatus = getAssistEnable();
        Log.d(TAG, "init mStatus: " + this.mStatus);
        this.mStateCallBack = new HATileCallback(this);
        this.mDeathRecipient = new IBinder.DeathRecipient() { // from class: miui.systemui.quicksettings.hearingassist.HearingAssistTile.1
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                StringBuilder sb = new StringBuilder();
                sb.append("start binderDied: ");
                sb.append(HearingAssistTile.this.mTransmitBinder == null);
                Log.d(HearingAssistTile.TAG, sb.toString());
                if (HearingAssistTile.this.mTransmitBinder == null) {
                    return;
                }
                try {
                    HearingAssistTile.this.mTransmitBinder.A0(HearingAssistTile.this.mStateCallBack);
                    HearingAssistTile.this.mTransmitBinder.asBinder().unlinkToDeath(HearingAssistTile.this.mDeathRecipient, 0);
                } catch (Exception e2) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("binderDied mDeathRecipient: null ");
                    sb2.append(HearingAssistTile.this.mDeathRecipient == null);
                    Log.d(HearingAssistTile.TAG, sb2.toString());
                    e2.printStackTrace();
                }
                HearingAssistTile.this.mBinding = false;
                HearingAssistTile.this.mTransmitBinder = null;
                Settings.Global.putInt(HearingAssistTile.this.mPluginContext.getContentResolver(), HearingAssistTile.SYSTEM_SETTINGS_HEARING_ASSIST_ENABLE, 0);
            }
        };
        this.mServiceConnection = new ServiceConnection() { // from class: miui.systemui.quicksettings.hearingassist.HearingAssistTile.2
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                HearingAssistTile.this.mTransmitBinder = a.AbstractBinderC0064a.Z0(iBinder);
                if (HearingAssistTile.this.mTransmitBinder == null) {
                    Log.d(HearingAssistTile.TAG, "onServiceConnected: null");
                    return;
                }
                HearingAssistTile.this.mBinding = true;
                try {
                    HearingAssistTile.this.mTransmitBinder.asBinder().linkToDeath(HearingAssistTile.this.mDeathRecipient, 0);
                    if (HearingAssistTile.this.mStateCallBack != null) {
                        HearingAssistTile.this.mTransmitBinder.J0(HearingAssistTile.this.mStateCallBack);
                    }
                } catch (Exception e2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("onServiceConnected mDeathRecipient: null ");
                    sb.append(HearingAssistTile.this.mDeathRecipient == null);
                    Log.d(HearingAssistTile.TAG, sb.toString());
                    e2.printStackTrace();
                }
                HearingAssistTile.this.mTask = new TransmissionTask(HearingAssistTile.this);
                HearingAssistTile.this.mTask.start();
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                HearingAssistTile.this.mBinding = false;
            }
        };
    }

    private boolean needShowDialog() {
        return Settings.System.getInt(this.mPluginContext.getContentResolver(), HearingAssistConstant.DISABLE_SHOW_TIPS, 0) == 0 || !HearingAssistUtil.isBluetoothConnected();
    }

    private void showConfirmDialog() {
        Intent intent = new Intent(ACTION_HEARING_ASSIST_CONFIRM_ACTIVITY);
        intent.addFlags(268435456);
        this.mPluginContext.startActivityAsUser(intent, UserHandle.CURRENT);
    }

    private void startAndBindService() {
        try {
            this.mPluginContext.startService(this.mServiceIntent);
        } catch (IllegalStateException unused) {
            this.mPluginContext.startForegroundService(this.mServiceIntent);
            Log.e(TAG, "startService error, try to startForegroundService");
        }
        this.mPluginContext.bindService(this.mServiceIntent, this.mServiceConnection, 0);
    }

    public void addCallback(QSTile.Callback callback) {
        if (this.mCallbacks.contains(callback)) {
            return;
        }
        this.mCallbacks.add(callback);
        callback.onStateChanged(this.mState);
    }

    public String composeChangeAnnouncement() {
        return null;
    }

    public Intent getLongClickIntent() {
        return new Intent(MISOUND_HEARING_ASSIST_SETTINGS);
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

    public void handleClick() throws IOException {
        Log.d(TAG, String.format("handleClick mStatus: %s mBinding: %s", Boolean.valueOf(this.mStatus), Boolean.valueOf(this.mBinding)));
        if (!this.mListening) {
            init();
        }
        if (needShowDialog()) {
            collapseStatusBar(this.mPluginContext);
            showConfirmDialog();
            return;
        }
        boolean zCheckRecordPermission = HearingAssistUtil.checkRecordPermission(this.mPluginContext);
        Log.d(TAG, "handleClick: permission granted " + zCheckRecordPermission);
        if (!zCheckRecordPermission) {
            this.mPluginContext.startActivityAsUser(getLongClickIntent(), UserHandle.CURRENT);
            CommonUtils.INSTANCE.collapseControlCenter();
            return;
        }
        this.mStatus = !this.mStatus;
        refreshState(null);
        refreshTile();
        if (this.mServiceIntent == null) {
            this.mServiceIntent = genTransmitIntent();
        }
        if (!this.mBinding) {
            startAndBindService();
        } else if (this.mTransmitBinder != null) {
            TransmissionTask transmissionTask = new TransmissionTask(this);
            this.mTask = transmissionTask;
            transmissionTask.start();
        }
    }

    public boolean isAvailable() {
        return true;
    }

    public QSTile.State newTileState() {
        return this.mState;
    }

    public void refreshState(Object obj) {
        this.mState.label = this.mPluginContext.getString(R.string.hearing_assist_transmission_label);
        if (this.mStatus) {
            this.mState.icon = new DrawableIcon(this.mPluginContext.getDrawable(R.drawable.ic_qs_hearing_assist_enable));
            this.mState.state = 2;
        } else {
            this.mState.icon = new DrawableIcon(this.mPluginContext.getDrawable(R.drawable.ic_qs_hearing_assist_disable));
            this.mState.state = 1;
        }
        this.mState.expandedAccessibilityClassName = Switch.class.getName();
        QSTile.State state = this.mState;
        state.contentDescription = state.label;
    }

    public void refreshTile() {
        Iterator<QSTile.Callback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onStateChanged(this.mState);
        }
    }

    public void removeCallback(QSTile.Callback callback) {
    }

    public void setListening(boolean z2) {
        if (this.mListening == z2) {
            return;
        }
        this.mListening = z2;
        Log.d(TAG, "setListening: " + this.mListening);
        if (this.mListening) {
            init();
            return;
        }
        if (this.mBinding) {
            try {
                this.mTransmitBinder.A0(this.mStateCallBack);
                if (!this.mStatus) {
                    Log.d(TAG, "setListening: stop");
                    this.mPluginContext.stopService(this.mServiceIntent);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.mBinding = false;
        }
        destroyAll();
    }
}
