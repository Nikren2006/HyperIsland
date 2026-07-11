package miui.systemui.quicksettings.soundeffect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.SystemProperties;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import miui.systemui.quicksettings.DrawableIcon;
import miui.systemui.quicksettings.R;
import miui.systemui.quicksettings.soundeffect.dirac.AudioEffectCenter;
import miui.systemui.quicksettings.soundeffect.dirac.DiracUtils;
import miui.systemui.quicksettings.soundeffect.dirac.HeadsetUtil;
import miui.systemui.quicksettings.soundeffect.dirac.MiSoundUtils;
import r.C0734a;

/* JADX INFO: loaded from: classes4.dex */
@Requires(target = MiuiQSTilePlugin.class, version = 1)
public class DolbyAtomsSoundEffectTile implements MiuiQSTile {
    private static final String ACTION_SYSTEM_UI_DOLBY_EFFECT_SWITCH = "miui.intent.action.ACTION_SYSTEM_UI_DOLBY_EFFECT_SWITCH";
    private static final String ACTION_SYSTEM_UI_HARMAN_EFFECT_SWITCH = "miui.intent.action.ACTION_SYSTEM_UI_HARMAN_EFFECT_SWITCH";
    private static final String MISOUND_HEADSET_SETTINGS = "miui.intent.action.HEADSET_SETTINGS";
    private static final String NO_SUPPORT_BT_DOLBY_PROPERTY = "ro.vendor.audio.nosupport_bt_dolby";
    private static final String SYSTEM_HARMAN_EFFECT_SUPPORTED = "settings_system_harman_kardon_enable";
    private static final String TAG = "DolbyAtomsSoundEffectTile";
    private static final String TILE_SPEC = "dolbyatomssound";
    private AudioEffectCenter mAudioEffectCenter;
    private AudioManager mAudioManger;
    private final ArrayList<QSTile.Callback> mCallbacks;
    private DiracUtils mDiracUtil;
    private C0734a mDolbyAudio;
    private boolean mDolbyEffectSupported;
    private boolean mHarmanEffectSupported;
    private final boolean mIsSupportAudioCenter;
    private boolean mListening;
    private Context mPluginContext;
    private final Receiver mReceiver;
    private final QSTile.State mState;
    private Context mSysuiContext;

    public final class Receiver extends BroadcastReceiver {
        private boolean mRegistered;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.d(DolbyAtomsSoundEffectTile.TAG, "onReceive: ");
            String action = intent.getAction();
            if (DolbyAtomsSoundEffectTile.ACTION_SYSTEM_UI_HARMAN_EFFECT_SWITCH.equals(action)) {
                DolbyAtomsSoundEffectTile.this.refreshState(null);
                DolbyAtomsSoundEffectTile.this.refreshTile();
                return;
            }
            if (!"android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED".equals(action)) {
                if (AudioEffectCenter.ACTION_EFFECT_REFRESH.equals(action)) {
                    DolbyAtomsSoundEffectTile.this.refreshState(intent.getBundleExtra(AudioEffectCenter.KEY_BUNDLE));
                    DolbyAtomsSoundEffectTile.this.refreshTile();
                    return;
                }
                return;
            }
            int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
            if (intExtra == 0 || intExtra == 2) {
                DolbyAtomsSoundEffectTile.this.refreshState(null);
                DolbyAtomsSoundEffectTile.this.refreshTile();
            }
        }

        public void setListening(boolean z2) {
            if (!z2 || this.mRegistered) {
                if (z2 || !this.mRegistered) {
                    return;
                }
                Log.d(DolbyAtomsSoundEffectTile.TAG, "Unregistering receiver");
                DolbyAtomsSoundEffectTile.this.mPluginContext.unregisterReceiver(this);
                this.mRegistered = false;
                return;
            }
            Log.d(DolbyAtomsSoundEffectTile.TAG, "Registering receiver");
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(DolbyAtomsSoundEffectTile.ACTION_SYSTEM_UI_HARMAN_EFFECT_SWITCH);
            intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
            if (DolbyAtomsSoundEffectTile.this.mIsSupportAudioCenter) {
                intentFilter.addAction(AudioEffectCenter.ACTION_EFFECT_REFRESH);
            }
            DolbyAtomsSoundEffectTile.this.mPluginContext.registerReceiver(this, intentFilter, 2);
            this.mRegistered = true;
        }

        private Receiver() {
        }
    }

    public DolbyAtomsSoundEffectTile(Context context, Context context2) {
        boolean z2 = SystemProperties.getBoolean("ro.vendor.audio.fweffect", false);
        this.mIsSupportAudioCenter = z2;
        this.mReceiver = new Receiver();
        this.mDolbyAudio = null;
        this.mPluginContext = context2;
        this.mSysuiContext = context;
        QSTile.State state = new QSTile.State();
        this.mState = state;
        state.state = 1;
        this.mCallbacks = new ArrayList<>();
        this.mDolbyEffectSupported = SystemProperties.getBoolean("ro.vendor.audio.dolby.dax.support", false);
        this.mHarmanEffectSupported = SystemProperties.getBoolean("ro.vendor.audio.sfx.harmankardon", false);
        if (z2) {
            return;
        }
        updateDiacState();
    }

    private void collapseStatusBar(Context context) {
        try {
            Object systemService = context.getSystemService(VolumePanelViewController.SERVICE_STATUS_BAR);
            systemService.getClass().getMethod("collapsePanels", null).invoke(systemService, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void destroyDolbyAtomsEffect() {
        C0734a c0734a = this.mDolbyAudio;
        if (c0734a != null) {
            c0734a.release();
            this.mDolbyAudio = null;
        }
    }

    private void destroyMiSoundEffect() {
        DiracUtils diracUtils = this.mDiracUtil;
        if (diracUtils == null || !diracUtils.hasInitialized()) {
            return;
        }
        this.mDiracUtil.release();
        this.mDiracUtil = null;
    }

    private boolean getDolbyEffectStatus() {
        if (this.mIsSupportAudioCenter) {
            initAudioEffectCenter();
            return this.mAudioEffectCenter.isEffectActive(AudioEffectCenter.EFFECT_DOLBY);
        }
        try {
            C0734a c0734a = this.mDolbyAudio;
            if (c0734a != null) {
                return c0734a.d();
            }
            return false;
        } catch (Exception e2) {
            destroyDolbyAtomsEffect();
            e2.printStackTrace();
            return false;
        }
    }

    private void initAudioEffectCenter() {
        if (this.mAudioEffectCenter == null) {
            AudioEffectCenter audioEffectCenter = AudioEffectCenter.getInstance();
            this.mAudioEffectCenter = audioEffectCenter;
            audioEffectCenter.init(this.mSysuiContext);
            Log.d(TAG, "initAudioEffectCenter");
        }
    }

    private void initDolbyAtomsEffect() {
        if (this.mDolbyAudio == null) {
            Log.d(TAG, "initDolbyAtomsEffect: ");
            try {
                this.mDolbyAudio = new C0734a(0, 0);
            } catch (Throwable unused) {
                Log.e(TAG, "create DolbyAudioEffect instance failed.");
            }
        }
    }

    private void initMiSoundEffect() {
        DiracUtils diracUtils = this.mDiracUtil;
        if (diracUtils == null || !diracUtils.hasInitialized()) {
            updateDiacState();
        }
    }

    private boolean isDolbySwitchEnabled() {
        return (isNotSupportBTDolbyDevices() && HeadsetUtil.isBluetoothSetOn()) ? false : true;
    }

    private boolean isNotSupportBTDolbyDevices() {
        return SystemProperties.getBoolean(NO_SUPPORT_BT_DOLBY_PROPERTY, false);
    }

    private void sendUpdateStatusBroadCast() {
        Intent intent = new Intent(ACTION_SYSTEM_UI_DOLBY_EFFECT_SWITCH);
        intent.addFlags(822083584);
        this.mPluginContext.sendStickyBroadcastAsUser(intent, UserHandle.ALL);
    }

    private void setDolbyEffectEnable(boolean z2) {
        Log.d(TAG, "setDolbyEffectEnable: " + z2);
        try {
            if (this.mHarmanEffectSupported) {
                Settings.Global.putInt(this.mPluginContext.getContentResolver(), SYSTEM_HARMAN_EFFECT_SUPPORTED, !z2 ? 1 : 0);
            }
            if (!HeadsetUtil.isBtA2dpInUse(this.mPluginContext) || HeadsetUtil.isBluetoothMiSoundEnable()) {
                ((MiSoundUtils) this.mDiracUtil).setEffectEnable(!z2);
            }
            this.mDolbyAudio.k(z2);
            Settings.Global.putString(this.mPluginContext.getContentResolver(), "effect_implementer", z2 ? "dolby" : "misound");
            if (this.mAudioManger == null) {
                this.mAudioManger = (AudioManager) this.mPluginContext.getSystemService(DeviceInfo.AUDIO_SUPPORT);
            }
            String str = String.format(Locale.US, "dolby_mode=%b", Boolean.valueOf(z2));
            this.mAudioManger.setParameters(str);
            Log.d(TAG, "setDolbyEffectEnable: " + str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void setEffectAndReceiver(boolean z2) {
        if (this.mListening == z2) {
            return;
        }
        this.mListening = z2;
        if (z2) {
            if (this.mIsSupportAudioCenter) {
                initAudioEffectCenter();
            } else {
                initDolbyAtomsEffect();
                initMiSoundEffect();
            }
            this.mReceiver.setListening(true);
            return;
        }
        this.mReceiver.setListening(false);
        if (this.mIsSupportAudioCenter) {
            AudioEffectCenter audioEffectCenter = this.mAudioEffectCenter;
            if (audioEffectCenter != null) {
                audioEffectCenter.release();
                this.mAudioEffectCenter = null;
            }
        } else {
            destroyMiSoundEffect();
            destroyDolbyAtomsEffect();
        }
        this.mAudioManger = null;
    }

    private void updateDiacState() {
        DiracUtils.sCanInitialize = true;
        if (DiracUtils.isSupportDirac(this.mPluginContext)) {
            try {
                DiracUtils diracUtilsNewInstance = DiracUtils.newInstance();
                this.mDiracUtil = diracUtilsNewInstance;
                diracUtilsNewInstance.initialize();
            } catch (Exception unused) {
                Log.e(TAG, "dirac initial failed");
                DiracUtils.sCanInitialize = false;
            }
        }
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
        return new Intent("miui.intent.action.HEADSET_SETTINGS");
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
        if (this.mIsSupportAudioCenter) {
            initAudioEffectCenter();
            this.mAudioEffectCenter.setEffectActive(AudioEffectCenter.EFFECT_DOLBY, !this.mAudioEffectCenter.isEffectActive(AudioEffectCenter.EFFECT_DOLBY));
            return;
        }
        if (this.mDolbyEffectSupported && isDolbySwitchEnabled()) {
            if (!this.mListening) {
                setEffectAndReceiver(true);
            }
            C0734a c0734a = this.mDolbyAudio;
            if (c0734a != null && !c0734a.hasControl()) {
                destroyDolbyAtomsEffect();
            }
            initDolbyAtomsEffect();
            initMiSoundEffect();
            setDolbyEffectEnable(!getDolbyEffectStatus());
            sendUpdateStatusBroadCast();
            refreshState(null);
            refreshTile();
        }
    }

    public boolean isAvailable() {
        return this.mDolbyEffectSupported;
    }

    public QSTile.State newTileState() {
        return this.mState;
    }

    public void refreshState(Object obj) {
        boolean z2;
        boolean z3;
        Log.d(TAG, "refreshState: ");
        this.mState.label = this.mPluginContext.getString(R.string.dolby_atoms_sound_effect_label);
        if (obj instanceof Bundle) {
            Bundle bundle = (Bundle) obj;
            z2 = bundle.getBoolean(AudioEffectCenter.KEY_DOLBY_AVAILABLE);
            z3 = bundle.getBoolean(AudioEffectCenter.KEY_DOLBY_ACTIVE);
        } else {
            z2 = true;
            z3 = false;
        }
        if (!this.mDolbyEffectSupported || !isDolbySwitchEnabled() || !z2) {
            this.mState.icon = new DrawableIcon(this.mPluginContext.getResources().getDrawable(R.drawable.ic_qs_dolby_atoms_disable));
            this.mState.state = 0;
        } else if (z3 || getDolbyEffectStatus()) {
            this.mState.icon = new DrawableIcon(this.mPluginContext.getResources().getDrawable(R.drawable.ic_qs_dolby_atoms_enable));
            this.mState.state = 2;
        } else {
            this.mState.icon = new DrawableIcon(this.mPluginContext.getResources().getDrawable(R.drawable.ic_qs_dolby_atoms_disable));
            this.mState.state = 1;
        }
        this.mState.expandedAccessibilityClassName = Switch.class.getName();
        QSTile.State state = this.mState;
        state.contentDescription = state.label;
    }

    public void refreshTile() {
        Log.d(TAG, "refreshTile: ");
        Iterator it = new ArrayList(this.mCallbacks).iterator();
        while (it.hasNext()) {
            ((QSTile.Callback) it.next()).onStateChanged(this.mState);
        }
    }

    public void removeCallback(QSTile.Callback callback) {
        this.mCallbacks.remove(callback);
    }

    public void setListening(boolean z2) {
        Log.d(TAG, "setListening: " + z2);
        setEffectAndReceiver(z2);
    }
}
