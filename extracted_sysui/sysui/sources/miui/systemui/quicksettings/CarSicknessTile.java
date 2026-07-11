package miui.systemui.quicksettings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.widget.Switch;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.qs.MiuiQSTile;
import com.android.systemui.plugins.miui.qs.MiuiQSTilePlugin;
import com.android.systemui.plugins.qs.QSTile;
import java.util.ArrayList;
import java.util.Iterator;
import miui.systemui.util.DeviceUtils;
import miui.systemui.util.ThreadUtils;

/* JADX INFO: loaded from: classes4.dex */
@Requires(target = MiuiQSTilePlugin.class, version = 1)
public class CarSicknessTile implements MiuiQSTile {
    private static final String ACTION_CARSICKNESS_STATE_CHANGED = "miui.intent.action.ACTION_CARSICKNESS_STATE_CHANGED";
    public static final String KEY_SETTINGS_CARSICKNESS_MODE = "settings_car_sickness_mode";
    public static final int KEY_SETTINGS_CARSICKNESS_MODE_CLOSE = 0;
    public static final int KEY_SETTINGS_CARSICKNESS_MODE_OPEN = 1;
    public static final String PKG_SECURITYCENTER = "com.miui.securitycenter";
    private static final String TAG = "CarSicknessTile";
    public static final String TARGET_ACTIVITY = "com.miui.carsickness.ui.CarSicknessReliefSettingsActivity";
    private static final String TILE_SPEC = "carsickness";
    private boolean mListening;
    private Context mPluginContext;
    private final Receiver mReceiver = new Receiver();
    private QSTile.State mState = new QSTile.State();
    private final ArrayList<QSTile.Callback> mCallbacks = new ArrayList<>();

    public final class Receiver extends BroadcastReceiver {
        boolean mRegistered;

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$setListening$0(boolean z2) {
            if (z2 && !this.mRegistered) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(CarSicknessTile.ACTION_CARSICKNESS_STATE_CHANGED);
                CarSicknessTile.this.mPluginContext.registerReceiver(this, intentFilter, 2);
                this.mRegistered = true;
                return;
            }
            if (z2 || !this.mRegistered) {
                return;
            }
            CarSicknessTile.this.mPluginContext.unregisterReceiver(this);
            this.mRegistered = false;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (CarSicknessTile.ACTION_CARSICKNESS_STATE_CHANGED.equals(intent.getAction())) {
                CarSicknessTile.this.refreshState(null);
                CarSicknessTile.this.updateUiCallback();
            }
        }

        public void setListening(final boolean z2) {
            ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: miui.systemui.quicksettings.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5880a.lambda$setListening$0(z2);
                }
            });
        }

        private Receiver() {
        }
    }

    public CarSicknessTile(Context context, Context context2) {
        this.mPluginContext = context2;
        if (isCarSicknessOpen()) {
            this.mState.state = 2;
        } else {
            this.mState.state = 1;
        }
    }

    private boolean isCarSicknessOpen() {
        return Settings.System.getInt(this.mPluginContext.getContentResolver(), KEY_SETTINGS_CARSICKNESS_MODE, 0) == 1;
    }

    private void setCarSickNessMode(int i2) {
        Settings.System.putInt(this.mPluginContext.getContentResolver(), KEY_SETTINGS_CARSICKNESS_MODE, i2);
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
        Intent intent = new Intent();
        intent.setPackage("com.miui.securitycenter");
        intent.setClassName("com.miui.securitycenter", TARGET_ACTIVITY);
        intent.putExtra("enter_way", "quick_setings");
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
        setCarSickNessMode(!isCarSicknessOpen() ? 1 : 0);
        refreshState(null);
        updateUiCallback();
    }

    public boolean isAvailable() {
        return DeviceUtils.isSupportCarSickness(this.mPluginContext);
    }

    public QSTile.State newTileState() {
        return this.mState;
    }

    public void refreshState(Object obj) {
        if (isCarSicknessOpen()) {
            this.mState.state = 2;
        } else {
            this.mState.state = 1;
        }
        this.mState.label = this.mPluginContext.getString(R.string.quick_settings_carsickness);
        this.mState.icon = new DrawableIcon(this.mPluginContext.getResources().getDrawable(R.drawable.ic_qs_carsickness));
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
        this.mReceiver.setListening(z2);
    }
}
