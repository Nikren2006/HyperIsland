package miui.systemui.plugins;

import android.content.pm.PackageManager;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginManagerExt$packageManager$2 extends o implements Function0 {
    final /* synthetic */ PluginManagerExt this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginManagerExt$packageManager$2(PluginManagerExt pluginManagerExt) {
        super(0);
        this.this$0 = pluginManagerExt;
    }

    @Override // kotlin.jvm.functions.Function0
    public final PackageManager invoke() {
        return this.this$0.context.getPackageManager();
    }
}
