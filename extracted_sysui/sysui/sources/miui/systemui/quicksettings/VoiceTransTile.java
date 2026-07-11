package miui.systemui.quicksettings;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.widget.Button;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.android.systemui.miui.volume.VolumePanelViewController;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.annotations.Requirements;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.qs.MiuiQSTile;
import com.android.systemui.plugins.miui.qs.MiuiQSTilePlugin;
import com.android.systemui.plugins.qs.QSTile;
import miui.systemui.util.ReflectBuilderUtil;

/* JADX INFO: loaded from: classes4.dex */
@Requirements({@Requires(target = MiuiQSTilePlugin.class, version = 1), @Requires(target = ActivityStarter.class, version = 2)})
public class VoiceTransTile implements MiuiQSTile {
    private static final String IS_NEW_CHAR_VOICE_TRANS_KEY_NAME = "isNewCharVoiceTrans";
    private static final String PACKAGE_NAME = "com.xiaomi.aiasst.vision";
    private static final String TAG = "VoiceTransTile";
    private static final String TILE_SPEC = "voicetrans";
    private E0.a mActivityStarter;
    private final Context mPluginContext;
    private final QSTile.State mState;
    private final Context mSysuiContext;

    public VoiceTransTile(Context context, Context context2, E0.a aVar) {
        this.mSysuiContext = context;
        this.mPluginContext = context2;
        QSTile.State state = new QSTile.State();
        this.mState = state;
        state.state = 1;
        this.mActivityStarter = aVar;
    }

    private void collapseStatusBar(Context context) {
        try {
            Object systemService = context.getSystemService(VolumePanelViewController.SERVICE_STATUS_BAR);
            systemService.getClass().getMethod("collapsePanels", null).invoke(systemService, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private Intent genStartVoiceTransIntent() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(PACKAGE_NAME, "com.xiaomi.aiasst.vision.ui.facetranslation.ui.AiVoiceTranslationActivity"));
        intent.putExtra("from", "systemui.plugin.tile.aisubtitles");
        intent.setFlags(268435456);
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

    private boolean isNewCharTransVersion(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(PACKAGE_NAME, 128).metaData;
            if (bundle != null) {
                return bundle.getBoolean(IS_NEW_CHAR_VOICE_TRANS_KEY_NAME, false);
            }
        } catch (Exception unused) {
            Log.w(TAG, "invoke isSupportVoiceTrans error!");
        }
        return false;
    }

    public static boolean isSupportVoiceTrans(Context context) {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = context.getContentResolver().query(Uri.parse("content://com.xiaomi.aiasst.vision/VoiceTransitionState"), null, null, null, null);
            } catch (Exception unused) {
                Log.w(TAG, "invoke isSupportVoiceTrans error!");
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v5, types: [android.content.Context, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Class, java.lang.Class<android.os.UserHandle>] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Class[]] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Class, java.lang.Class<android.os.Bundle>] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object[]] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0049 -> B:16:0x005b). Please report as a decompilation issue!!! */
    public void handleClick() {
        ?? r11;
        Class cls;
        Class cls2;
        ?? r12 = UserHandle.class;
        ?? r2 = Bundle.class;
        collapseStatusBar(this.mPluginContext);
        refreshState(null);
        Log.i(TAG, "VoiceTrans tile clicked");
        ActivityStarter activityStarter = (ActivityStarter) this.mActivityStarter.get();
        try {
            UserHandle userHandle = ReflectBuilderUtil.getUserHandle(ReflectBuilderUtil.getCurrentUserId());
            Intent intentGenStartVoiceTransIntent = genStartVoiceTransIntent();
            if (activityStarter == null) {
                ReflectBuilderUtil.callObjectMethod(this.mSysuiContext, "startActivityAsUser", new Class[]{Intent.class, r2, r12}, intentGenStartVoiceTransIntent, intentGenStartVoiceTransIntent.getExtras(), userHandle);
                cls2 = r12;
                cls = r2;
                this = this;
            } else {
                activityStarter.postStartActivityDismissingKeyguard(intentGenStartVoiceTransIntent, 0);
                cls2 = r12;
                cls = r2;
                this = this;
            }
        } catch (Exception e2) {
            Log.e(TAG, "can't open VoiceTrans: " + e2);
            cls2 = r12;
            cls = r2;
            r11 = this;
        }
        try {
            UserHandle userHandle2 = ReflectBuilderUtil.getUserHandle(ReflectBuilderUtil.getCurrentUserId());
            Intent intentGenStartVoiceTransIntent2 = r11.genStartVoiceTransIntent();
            this = r11.mSysuiContext;
            r12 = new Class[]{Intent.class, cls, cls2};
            r2 = new Object[]{intentGenStartVoiceTransIntent2, intentGenStartVoiceTransIntent2.getExtras(), userHandle2};
            ReflectBuilderUtil.callObjectMethod(this, "startActivityAsUser", r12, r2);
        } catch (Exception e3) {
            Log.e(TAG, "can't open VoiceTrans: " + e3);
        }
    }

    public boolean isAvailable() {
        boolean zIsSupportVoiceTrans = isSupportVoiceTrans(this.mSysuiContext);
        Log.i(TAG, "VoiceTrans tile is available: " + zIsSupportVoiceTrans);
        return zIsSupportVoiceTrans;
    }

    public QSTile.State newTileState() {
        return this.mState;
    }

    public void refreshState(Object obj) {
        this.mState.state = 1;
        if (isNewCharTransVersion(this.mPluginContext)) {
            QSTile.State state = this.mState;
            Context context = this.mPluginContext;
            state.label = getString(context, "voice_char_translation", PACKAGE_NAME, context.getString(R.string.quick_settings_voice_trans_label_new));
            this.mState.icon = new DrawableIcon(this.mPluginContext.getDrawable(R.drawable.ic_qs_char_voice_trans_new));
        } else {
            QSTile.State state2 = this.mState;
            Context context2 = this.mPluginContext;
            state2.label = getString(context2, "si_translate", PACKAGE_NAME, context2.getString(R.string.quick_settings_voice_trans_label));
            this.mState.icon = new DrawableIcon(this.mPluginContext.getDrawable(R.drawable.ic_qs_offine_voice_trans));
        }
        this.mState.expandedAccessibilityClassName = Button.class.getName();
        QSTile.State state3 = this.mState;
        state3.contentDescription = state3.label;
    }

    public void removeCallback(QSTile.Callback callback) {
    }

    public void setListening(boolean z2) {
    }
}
