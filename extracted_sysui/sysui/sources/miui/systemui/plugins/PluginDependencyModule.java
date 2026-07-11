package miui.systemui.plugins;

import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.z;
import miui.systemui.plugins.PluginDependencyHolder;
import miui.systemui.plugins.PluginDependencyHolderImpl;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginDependencyModule {
    public final ActivityStarter providesActivityStarter(PluginDependencyHolder<ActivityStarter> holder) {
        n.g(holder, "holder");
        return holder.require();
    }

    public final PluginDependencyHolder<ActivityStarter> providesActivityStarterHolder(PluginDependencyHolderImpl.Factory holderFactory) {
        n.g(holderFactory, "holderFactory");
        return holderFactory.create(z.b(ActivityStarter.class));
    }

    public final PluginDependencyHolder.Factory providesPluginDependencyHolderFactory(PluginDependencyHolderImpl.Factory impl) {
        n.g(impl, "impl");
        return impl;
    }

    public final SuperSaveModeController providesSuperSaveModeController(PluginDependencyHolder<SuperSaveModeController> holder) {
        n.g(holder, "holder");
        return holder.require();
    }

    public final PluginDependencyHolder<SuperSaveModeController> providesSuperSaveModeControllerHolder(PluginDependencyHolderImpl.Factory holderFactory) {
        n.g(holderFactory, "holderFactory");
        return holderFactory.create(z.b(SuperSaveModeController.class));
    }
}
