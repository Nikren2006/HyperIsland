package miui.systemui.notification.unimportant;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.systemui.plugins.annotations.Requires;
import com.android.systemui.plugins.miui.notification.UnimportantSdkPlugin;
import java.util.List;
import java.util.Map;
import miui.systemui.plugins.PluginBase;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
@Requires(target = UnimportantSdkPlugin.class, version = 1)
public class UnimportantSdkPluginImpl extends PluginBase implements UnimportantSdkPlugin {
    private static final String TAG = "UnimportantSdkPluginImpl";
    private Context mPluginContext;

    public int foldReason(StatusBarNotification statusBarNotification, String str, Map<String, Integer> map, String str2) {
        return UnimportantSdkTool.getInstance().fold(statusBarNotification, str, map, str2);
    }

    public void init() {
        UnimportantSdkTool.getInstance().init(this.mPluginContext);
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onCreated() {
        this.mPluginContext = this.pluginContext;
        init();
        Log.i(TAG, "onCreate: UnimportantSdkPluginImpl");
    }

    @Override // miui.systemui.plugins.PluginBase
    public void onDestroyed() {
        Log.i(TAG, "onDestroy: UnimportantSdkPluginImpl");
    }

    public void updatePushFilter(List<JSONObject> list) {
        UnimportantSdkTool.getInstance().updatePushFilterLocalRules(list);
    }

    public int foldReason(StatusBarNotification statusBarNotification, String str, Map<String, Integer> map) {
        return UnimportantSdkTool.getInstance().fold(statusBarNotification, str, map, "0.5");
    }
}
