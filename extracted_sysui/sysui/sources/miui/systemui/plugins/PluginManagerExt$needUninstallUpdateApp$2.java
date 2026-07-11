package miui.systemui.plugins;

import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.plugins.PluginManagerExt;
import miui.systemui.util.CommonUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginManagerExt$needUninstallUpdateApp$2 extends o implements Function0 {
    final /* synthetic */ PluginManagerExt this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginManagerExt$needUninstallUpdateApp$2(PluginManagerExt pluginManagerExt) {
        super(0);
        this.this$0 = pluginManagerExt;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Boolean invoke() {
        boolean z2 = false;
        boolean z3 = CommonUtils.getHyperOsVersion() >= 3 && Build.VERSION.SDK_INT == 35;
        PluginManagerExt.Companion companion = PluginManagerExt.Companion;
        boolean zIsControlCenterSecondaryExist = companion.isControlCenterSecondaryExist();
        PackageManager packageManager = this.this$0.getPackageManager();
        n.f(packageManager, "access$getPackageManager(...)");
        boolean zIsUpdateSystemApp = companion.isUpdateSystemApp(packageManager, "miui.systemui.plugin");
        Log.i("PluginManagerExt", z3 + " " + zIsControlCenterSecondaryExist + " " + zIsUpdateSystemApp);
        if ((!z3 || !zIsControlCenterSecondaryExist) && zIsUpdateSystemApp) {
            z2 = true;
        }
        return Boolean.valueOf(z2);
    }
}
