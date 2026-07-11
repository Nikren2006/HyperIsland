package miui.systemui.quicksettings;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.UserHandle;
import android.util.Log;
import android.widget.Button;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.android.systemui.miui.volume.VolumePanelViewController;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.qs.MiuiQSTile;
import com.android.systemui.plugins.miui.qs.MiuiQSTilePlugin;
import com.android.systemui.plugins.qs.QSTile;
import miui.systemui.util.ReflectBuilderUtil;
import miuix.os.Build;

/* JADX INFO: loaded from: classes4.dex */
@Requires(target = MiuiQSTilePlugin.class, version = 1)
public class AiSubtitlesTile implements MiuiQSTile {
    private static final String PACKAGE_NAME = "com.xiaomi.aiasst.vision";
    private static final String TAG = "AiSubtitlesTile";
    private static final String TILE_SPEC = "aisubtitles";
    private final boolean mIsInternationalBuild;
    private final Context mPluginContext;
    private final QSTile.State mState;
    private final Context mSysuiContext;

    public AiSubtitlesTile(Context context, Context context2) {
        this.mSysuiContext = context;
        this.mPluginContext = context2;
        QSTile.State state = new QSTile.State();
        this.mState = state;
        state.state = 1;
        this.mIsInternationalBuild = Build.IS_INTERNATIONAL_BUILD;
    }

    private void collapseStatusBar(Context context) {
        try {
            Object systemService = context.getSystemService(VolumePanelViewController.SERVICE_STATUS_BAR);
            systemService.getClass().getMethod("collapsePanels", null).invoke(systemService, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private Intent genStartAiSubtitlesIntent() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(PACKAGE_NAME, "com.xiaomi.aiasst.vision.control.translation.AiTranslateService"));
        intent.putExtra("from", "systemui.plugin.tile.aisubtitles");
        intent.putExtra("floatingWindowType", "startAiSubtitlesWindow");
        return intent;
    }

    public static String getString(Context context, String str, String str2, String str3) {
        try {
            Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(str2);
            return resourcesForApplication.getString(resourcesForApplication.getIdentifier(str, TypedValues.Custom.S_STRING, str2));
        } catch (Exception unused) {
            return str3;
        }
    }

    public static boolean isSupportAiPickSound(Context context) {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = context.getContentResolver().query(Uri.parse("content://com.xiaomi.aiasst.vision/pickSoundState"), null, null, null, null);
            } catch (Exception unused) {
                Log.w(TAG, "invoke isSupportAiPickSound error!");
                if (cursorQuery != null) {
                }
            }
            if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                return false;
            }
            boolean z2 = cursorQuery.getInt(0) > 0;
            cursorQuery.close();
            return z2;
            return false;
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    public void addCallback(QSTile.Callback callback) {
    }

    public String composeChangeAnnouncement() {
        return null;
    }

    public Intent getLongClickIntent() {
        return null;
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
        Log.i(TAG, "ai subtitles tile clicked");
        try {
            ReflectBuilderUtil.callObjectMethod(this.mSysuiContext, "startServiceAsUser", new Class[]{Intent.class, UserHandle.class}, genStartAiSubtitlesIntent(), ReflectBuilderUtil.getUserHandle(ReflectBuilderUtil.getCurrentUserId()));
        } catch (Exception e2) {
            Log.e(TAG, "can't open ai subtitles: " + e2);
        }
    }

    public boolean isAvailable() {
        boolean zIsSupportAiPickSound = isSupportAiPickSound(this.mSysuiContext);
        Log.i(TAG, "ai subtitles tile is available: " + zIsSupportAiPickSound);
        return zIsSupportAiPickSound;
    }

    public QSTile.State newTileState() {
        return this.mState;
    }

    public void refreshState(Object obj) {
        QSTile.State state = this.mState;
        state.state = 1;
        Context context = this.mPluginContext;
        state.label = getString(context, "ai_translate", PACKAGE_NAME, context.getString(R.string.quick_settings_aisubtitles_label));
        this.mState.icon = new DrawableIcon(this.mPluginContext.getResources().getDrawable(this.mIsInternationalBuild ? R.drawable.ic_qs_aisubtitles_global : R.drawable.ic_qs_aisubtitles, null));
        this.mState.expandedAccessibilityClassName = Button.class.getName();
        QSTile.State state2 = this.mState;
        state2.contentDescription = state2.label;
    }

    public void removeCallback(QSTile.Callback callback) {
    }

    public void setListening(boolean z2) {
    }
}
