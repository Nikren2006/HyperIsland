package miui.systemui.quicksettings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.widget.Button;
import com.android.systemui.miui.volume.VolumePanelViewController;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.qs.MiuiQSTile;
import com.android.systemui.plugins.miui.qs.MiuiQSTilePlugin;
import com.android.systemui.plugins.qs.QSTile;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.util.ReflectBuilderUtil;
import miuix.os.Build;

/* JADX INFO: loaded from: classes4.dex */
@Requires(target = MiuiQSTilePlugin.class, version = 1)
public class AiTranslateTile implements MiuiQSTile {
    private static final String ACTION_XIAOMI_AI_TRANSLATE_SERVICE = "com.xiaomi.aiasst.vision.control.translation.AiTranslateService";
    private static final String KEY_APP_NAME = "app_name";
    private static final String KEY_APP_NAME_NEW = "app_name_new";
    private static final String PACKAGE_NAME_AI_TRANSLATE = "com.xiaomi.aiasst.vision";
    private static final String TAG = "AiTranslateTile";
    private static final String TILE_SPEC = "aitranslate";
    private static final String USE_NEW_APP_NAME = "useNewAppName";
    private Context mPluginContext;
    private QSTile.State mState;
    private Context mSysuiContext;

    public AiTranslateTile(Context context, Context context2) {
        this.mSysuiContext = context;
        this.mPluginContext = context2;
        QSTile.State state = new QSTile.State();
        this.mState = state;
        state.state = 1;
    }

    private void collapseStatusBar(Context context) {
        try {
            Object systemService = context.getSystemService(VolumePanelViewController.SERVICE_STATUS_BAR);
            systemService.getClass().getMethod("collapsePanels", null).invoke(systemService, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private Intent genStartAiTranslateIntent() {
        Intent intent = new Intent(ACTION_XIAOMI_AI_TRANSLATE_SERVICE);
        intent.setPackage(PACKAGE_NAME_AI_TRANSLATE);
        intent.putExtra("from", "systemui.plugin");
        return intent;
    }

    private boolean useNewAppName(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(PACKAGE_NAME_AI_TRANSLATE, 128).metaData;
            if (bundle != null) {
                return bundle.getBoolean(USE_NEW_APP_NAME, false);
            }
        } catch (Exception unused) {
            Log.w(TAG, "invoke useNewAppName error!");
        }
        return false;
    }

    public void addCallback(QSTile.Callback callback) {
    }

    public String composeChangeAnnouncement() {
        return null;
    }

    public Intent getLongClickIntent() {
        Intent intent = new Intent("miui.intent.action.APP_MANAGER_APPLICATION_DETAIL");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra(DynamicIslandConstants.EXTRA_PACKAGE_NAME, PACKAGE_NAME_AI_TRANSLATE);
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
        collapseStatusBar(this.mPluginContext);
        refreshState(null);
        Log.i(TAG, "ai translate tile clicked");
        try {
            ReflectBuilderUtil.callObjectMethod(this.mSysuiContext, "startServiceAsUser", new Class[]{Intent.class, UserHandle.class}, genStartAiTranslateIntent(), ReflectBuilderUtil.getUserHandle(ReflectBuilderUtil.getCurrentUserId()));
        } catch (Exception e2) {
            Log.e(TAG, "can't open ai translate: " + e2);
        }
    }

    public boolean isAvailable() {
        if (Build.IS_INTERNATIONAL_BUILD) {
            return false;
        }
        Intent intent = new Intent("android.service.quicksettings.action.QS_TILE");
        intent.setPackage(PACKAGE_NAME_AI_TRANSLATE);
        if (this.mSysuiContext.getPackageManager().resolveService(intent, 0) != null) {
            Log.i(TAG, "find tile service!");
            return false;
        }
        if (this.mSysuiContext.getPackageManager().resolveService(genStartAiTranslateIntent(), 0) != null) {
            Log.i(TAG, "ai translate tile is available");
            return true;
        }
        Log.i(TAG, "ai translate tile is not available");
        return false;
    }

    public QSTile.State newTileState() {
        return this.mState;
    }

    public void refreshState(Object obj) {
        String string;
        String str;
        this.mState.state = 1;
        if (useNewAppName(this.mPluginContext)) {
            string = this.mPluginContext.getString(R.string.quick_settings_aitranslate_label_new);
            str = KEY_APP_NAME_NEW;
        } else {
            string = this.mPluginContext.getString(R.string.quick_settings_aitranslate_label);
            str = "app_name";
        }
        this.mState.label = VoiceTransTile.getString(this.mPluginContext, str, PACKAGE_NAME_AI_TRANSLATE, string);
        this.mState.icon = new DrawableIcon(this.mPluginContext.getResources().getDrawable(R.drawable.ic_qs_aitranslate, null));
        this.mState.expandedAccessibilityClassName = Button.class.getName();
        QSTile.State state = this.mState;
        state.contentDescription = state.label;
        Log.i(TAG, "label " + ((Object) this.mState.label));
    }

    public void removeCallback(QSTile.Callback callback) {
    }

    public void setListening(boolean z2) {
    }
}
