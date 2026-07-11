package miui.systemui.dynamicisland.window;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.plugins.miui.dynamicisland.DynamicIslandContent;
import java.util.Iterator;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.dynamicisland.DynamicFeatureConfig;
import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.R;
import miui.systemui.dynamicisland.dagger.DynamicIslandViewComponent;
import miui.systemui.util.ThemeUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandWindowViewCreator implements LifecycleOwner {
    private final E0.a context;
    private final LifecycleRegistry lifecycle;
    private final DynamicIslandViewComponent.Factory viewComponentFactory;
    private DynamicIslandWindowView windowView;

    public DynamicIslandWindowViewCreator(@Plugin E0.a context, DynamicIslandViewComponent.Factory viewComponentFactory) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(viewComponentFactory, "viewComponentFactory");
        this.context = context;
        this.viewComponentFactory = viewComponentFactory;
        this.lifecycle = new LifecycleRegistry(this);
    }

    public final DynamicIslandContent createView() {
        if (!DynamicFeatureConfig.INSTANCE.getFEATURE_DYNAMIC_ISLAND()) {
            Log.w("DynamicIslandWindowViewCreator", "feature dynamic island is not enabled.");
            DynamicIslandWindowView dynamicIslandWindowView = this.windowView;
            if (dynamicIslandWindowView != null) {
                dynamicIslandWindowView.destroy();
            }
            return null;
        }
        ThemeUtils themeUtils = ThemeUtils.INSTANCE;
        Object obj = this.context.get();
        kotlin.jvm.internal.n.f(obj, "get(...)");
        themeUtils.fixResourcesPackage((Context) obj);
        DynamicIslandWindowView dynamicIslandWindowView2 = this.windowView;
        if (dynamicIslandWindowView2 != null) {
            Log.w("DynamicIslandWindowViewCreator", "destroy window view " + dynamicIslandWindowView2 + " caused by new instance created.");
            dynamicIslandWindowView2.destroy();
        }
        View viewInflate = LayoutInflater.from((Context) this.context.get()).inflate(R.layout.dynamic_island_window_view, (ViewGroup) null);
        kotlin.jvm.internal.n.e(viewInflate, "null cannot be cast to non-null type miui.systemui.dynamicisland.window.DynamicIslandWindowView");
        DynamicIslandWindowView dynamicIslandWindowView3 = (DynamicIslandWindowView) viewInflate;
        this.windowView = dynamicIslandWindowView3;
        DynamicIslandViewComponent dynamicIslandViewComponentCreate = this.viewComponentFactory.create(dynamicIslandWindowView3);
        Iterator<T> it = dynamicIslandViewComponentCreate.getStartables().values().iterator();
        while (it.hasNext()) {
            ((DynamicIslandStartable) it.next()).start();
        }
        dynamicIslandWindowView3.setViewComponent(dynamicIslandViewComponentCreate);
        return dynamicIslandWindowView3.getWindowViewController();
    }

    public final DynamicIslandWindowView getWindowView() {
        return this.windowView;
    }

    public final void onPluginDestroy() {
        Log.w("DynamicIslandWindowViewCreator", "destroy window view " + this.windowView + " caused by plugin destroyed.");
        DynamicIslandWindowView dynamicIslandWindowView = this.windowView;
        if (dynamicIslandWindowView != null) {
            dynamicIslandWindowView.destroy();
        }
        this.windowView = null;
    }

    public final void setWindowView(DynamicIslandWindowView dynamicIslandWindowView) {
        this.windowView = dynamicIslandWindowView;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public LifecycleRegistry getLifecycle() {
        return this.lifecycle;
    }
}
