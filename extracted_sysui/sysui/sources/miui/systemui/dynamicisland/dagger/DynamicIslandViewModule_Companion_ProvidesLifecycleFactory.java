package miui.systemui.dynamicisland.dagger;

import F0.e;
import F0.i;
import G0.a;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandViewModule_Companion_ProvidesLifecycleFactory implements e {
    private final a lifecycleOwnerProvider;

    public DynamicIslandViewModule_Companion_ProvidesLifecycleFactory(a aVar) {
        this.lifecycleOwnerProvider = aVar;
    }

    public static DynamicIslandViewModule_Companion_ProvidesLifecycleFactory create(a aVar) {
        return new DynamicIslandViewModule_Companion_ProvidesLifecycleFactory(aVar);
    }

    public static Lifecycle providesLifecycle(LifecycleOwner lifecycleOwner) {
        return (Lifecycle) i.d(DynamicIslandViewModule.Companion.providesLifecycle(lifecycleOwner));
    }

    @Override // G0.a
    public Lifecycle get() {
        return providesLifecycle((LifecycleOwner) this.lifecycleOwnerProvider.get());
    }
}
