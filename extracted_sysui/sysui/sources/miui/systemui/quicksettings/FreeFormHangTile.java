package miui.systemui.quicksettings;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.android.systemui.miui.volume.VolumePanelViewController;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.qs.MiuiQSTile;
import com.android.systemui.plugins.miui.qs.MiuiQSTilePlugin;
import com.android.systemui.plugins.qs.QSTile;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
@Requires(target = MiuiQSTilePlugin.class, version = 1)
public class FreeFormHangTile implements MiuiQSTile {
    public static final int ALREADY_IN_SMALL_FREEFORM = 3;
    public static final int NO_AVALIABLE_APPLICATION = 2;
    public static final int QS_FREEFORM_HANG = 800;
    public static final int START_SAMLL_FREEFORM = 1;
    private static final String TAG = "FreeFormHangTile";
    private static final String TILE_SPEC = "freeformhang";
    private boolean isClick = false;
    private ArrayList<QSTile.Callback> mCallbacks;
    private Context mPluginContext;
    private QSTile.State mState;
    private Context mSysuiContext;

    public FreeFormHangTile(Context context, Context context2) {
        this.mSysuiContext = context;
        this.mPluginContext = context2;
        QSTile.State state = new QSTile.State();
        this.mState = state;
        state.state = 1;
        this.mCallbacks = new ArrayList<>();
    }

    private static <T> T callStaticObjectMethod(Class<?> cls, Class<T> cls2, String str, Class<?>[] clsArr, Object... objArr) throws NoSuchMethodException {
        Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
        declaredMethod.setAccessible(true);
        return (T) declaredMethod.invoke(null, objArr);
    }

    private void collapseStatusBar(Context context) {
        try {
            Object systemService = context.getSystemService(VolumePanelViewController.SERVICE_STATUS_BAR);
            systemService.getClass().getMethod("collapsePanels", null).invoke(systemService, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static boolean hasFunction(Class<?> cls, String str, Class<?>[] clsArr) {
        try {
            return cls.getDeclaredMethod(str, clsArr) != null;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private void startSmallFreeformAnimation() {
        try {
            if (hasFunction(Class.forName("android.util.MiuiMultiWindowUtils"), "startSmallFreeformForControlCenter", new Class[]{Context.class})) {
                String str = (String) callStaticObjectMethod(Class.forName("android.util.MiuiMultiWindowUtils"), String.class, "startSmallFreeformForControlCenter", new Class[]{Context.class}, this.mSysuiContext);
                if (!str.isEmpty()) {
                    Toast.makeText(this.mPluginContext, str, 1).show();
                }
            } else {
                int iIntValue = ((Integer) callStaticObjectMethod(Class.forName("android.util.MiuiMultiWindowUtils"), Integer.TYPE, "startSmallFreeform", new Class[]{Context.class}, this.mSysuiContext)).intValue();
                if (iIntValue == 2) {
                    Context context = this.mPluginContext;
                    Toast.makeText(context, context.getString(R.string.no_avaliable_application), 1).show();
                } else if (iIntValue == 3) {
                    Context context2 = this.mPluginContext;
                    Toast.makeText(context2, context2.getString(R.string.already_in_small_freeform), 1).show();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
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
        return this.mPluginContext.getString(R.string.quick_settings_hang_on);
    }

    public Intent getLongClickIntent() {
        return null;
    }

    public int getMetricsCategory() {
        return 800;
    }

    public QSTile.State getState() {
        return this.mState;
    }

    public String getTileSpec() {
        return TILE_SPEC;
    }

    public void handleClick() {
        Log.d(TAG, "handleClick");
        this.isClick = true;
        collapseStatusBar(this.mPluginContext);
        refreshState(null);
        refreshTile();
    }

    public boolean isAvailable() {
        boolean zBooleanValue = false;
        try {
            Class<?> cls = Class.forName("miui.util.MiuiMultiDisplayTypeInfo");
            Class cls2 = Boolean.TYPE;
            zBooleanValue = !((Boolean) callStaticObjectMethod(cls, cls2, "isFlipDevice", null, new Object[0])).booleanValue() ? ((Boolean) callStaticObjectMethod(Class.forName("miui.app.MiuiFreeFormManager"), cls2, "supportFreeform", null, new Object[0])).booleanValue() : ((Boolean) callStaticObjectMethod(Class.forName("android.util.MiuiMultiWindowUtils"), cls2, "supportMultiWindow", new Class[]{cls2, cls2}, Boolean.TRUE, Boolean.FALSE)).booleanValue();
        } catch (Exception e2) {
            Log.i(TILE_SPEC, "reflect error when get isAvailable state", e2);
        }
        return zBooleanValue;
    }

    public QSTile.State newTileState() {
        return this.mState;
    }

    public void refreshState(Object obj) {
        QSTile.State state = this.mState;
        state.state = 1;
        state.label = this.mPluginContext.getString(R.string.quick_settings_hang_label);
        this.mState.icon = new DrawableIcon(this.mPluginContext.getResources().getDrawable(R.drawable.ic_qs_hang));
        this.mState.expandedAccessibilityClassName = Button.class.getName();
        QSTile.State state2 = this.mState;
        state2.contentDescription = state2.label;
    }

    public void refreshTile() {
        Iterator<QSTile.Callback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onStateChanged(this.mState);
        }
    }

    public void removeCallback(QSTile.Callback callback) {
        this.mCallbacks.remove(callback);
    }

    public void setListening(boolean z2) {
        Log.d(TAG, "setListening mIsClick=" + this.isClick + " listening=" + z2);
        if (!this.isClick || z2) {
            return;
        }
        this.isClick = false;
        startSmallFreeformAnimation();
    }
}
