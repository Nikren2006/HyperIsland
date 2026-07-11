package miui.systemui.plugins;

import android.content.Context;
import android.util.Log;
import com.android.systemui.plugins.Plugin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.autodensity.AutoDensityControllerImpl;
import miui.systemui.dagger.PluginComponentInitializer;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.plugins.data.repository.PluginInstancesRepositoryImpl;

/* JADX INFO: loaded from: classes4.dex */
public abstract class PluginBase implements Plugin {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "PluginBase";
    public AutoDensityControllerImpl autoDensityController;
    public Context pluginContext;
    public PluginInstancesRepositoryImpl pluginInstancesRepository;
    public Context sysuiContext;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @SystemUI
    public static /* synthetic */ void getSysuiContext$annotations() {
    }

    public final AutoDensityControllerImpl getAutoDensityController() {
        AutoDensityControllerImpl autoDensityControllerImpl = this.autoDensityController;
        if (autoDensityControllerImpl != null) {
            return autoDensityControllerImpl;
        }
        n.w("autoDensityController");
        return null;
    }

    public final Context getPluginContext() {
        Context context = this.pluginContext;
        if (context != null) {
            return context;
        }
        n.w("pluginContext");
        return null;
    }

    public final PluginInstancesRepositoryImpl getPluginInstancesRepository() {
        PluginInstancesRepositoryImpl pluginInstancesRepositoryImpl = this.pluginInstancesRepository;
        if (pluginInstancesRepositoryImpl != null) {
            return pluginInstancesRepositoryImpl;
        }
        n.w("pluginInstancesRepository");
        return null;
    }

    public final Context getSysuiContext() {
        Context context = this.sysuiContext;
        if (context != null) {
            return context;
        }
        n.w("sysuiContext");
        return null;
    }

    public final void onCreate(Context sysUiContext, Context pluginContext) {
        n.g(sysUiContext, "sysUiContext");
        n.g(pluginContext, "pluginContext");
        if (new PluginManagerExt(pluginContext).getNeedUninstallUpdateApp()) {
            Log.w(TAG, "need to uninstall update app, do not create plugin component.");
            return;
        }
        PluginComponentInitializer.create(pluginContext, sysUiContext);
        PluginComponentInitializer.getPluginComponent().inject(this);
        getPluginInstancesRepository().onPluginLoaded(this);
        getAutoDensityController().replacePluginContextResources(pluginContext);
        onCreated();
    }

    public void onCreated() {
    }

    public final void onDestroy() {
        getPluginInstancesRepository().onPluginUnloaded(this);
        onDestroyed();
    }

    public void onDestroyed() {
    }

    public final void setAutoDensityController(AutoDensityControllerImpl autoDensityControllerImpl) {
        n.g(autoDensityControllerImpl, "<set-?>");
        this.autoDensityController = autoDensityControllerImpl;
    }

    public final void setPluginContext(Context context) {
        n.g(context, "<set-?>");
        this.pluginContext = context;
    }

    public final void setPluginInstancesRepository(PluginInstancesRepositoryImpl pluginInstancesRepositoryImpl) {
        n.g(pluginInstancesRepositoryImpl, "<set-?>");
        this.pluginInstancesRepository = pluginInstancesRepositoryImpl;
    }

    public final void setSysuiContext(Context context) {
        n.g(context, "<set-?>");
        this.sysuiContext = context;
    }
}
