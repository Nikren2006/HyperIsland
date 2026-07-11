package miui.systemui.dagger;

import android.content.Context;
import android.util.Log;
import kotlin.jvm.internal.n;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.util.ContextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class PluginComponentInitializer {
    public static final PluginComponentInitializer INSTANCE = new PluginComponentInitializer();
    private static final String TAG = "PluginComponentInitializer";
    private static PluginComponent _pluginComponent;

    private PluginComponentInitializer() {
    }

    public static final void create(Context pluginContext) {
        n.g(pluginContext, "pluginContext");
        create$default(pluginContext, null, 2, null);
    }

    public static /* synthetic */ void create$default(Context context, Context context2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            context2 = null;
        }
        create(context, context2);
    }

    public static final PluginComponent getPluginComponent() {
        PluginComponent pluginComponent = _pluginComponent;
        if (pluginComponent != null) {
            return pluginComponent;
        }
        throw new IllegalStateException("Plugin component is not created or is already destroyed.");
    }

    public static /* synthetic */ void getPluginComponent$annotations() {
    }

    public final void destroy() {
        Log.i(TAG, "destroying PluginComponent.");
        _pluginComponent = null;
        Dispatchers.INSTANCE.onDestroy();
    }

    public static final void create(Context pluginContext, Context context) {
        n.g(pluginContext, "pluginContext");
        if (_pluginComponent != null) {
            return;
        }
        Log.i(TAG, "creating PluginComponent.");
        Dispatchers.INSTANCE.onCreate();
        ContextUtils.INSTANCE.clearDrawableConstructorCache();
        _pluginComponent = DaggerPluginComponent.builder().contextModule(new ContextModule(pluginContext, context)).build();
    }
}
