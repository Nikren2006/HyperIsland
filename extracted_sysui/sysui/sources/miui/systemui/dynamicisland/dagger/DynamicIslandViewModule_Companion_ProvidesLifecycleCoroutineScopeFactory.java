package miui.systemui.dynamicisland.dagger;

import F0.e;
import F0.i;
import G0.a;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwner;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandViewModule_Companion_ProvidesLifecycleCoroutineScopeFactory implements e {
    private final a lifecycleOwnerProvider;

    public DynamicIslandViewModule_Companion_ProvidesLifecycleCoroutineScopeFactory(a aVar) {
        this.lifecycleOwnerProvider = aVar;
    }

    public static DynamicIslandViewModule_Companion_ProvidesLifecycleCoroutineScopeFactory create(a aVar) {
        return new DynamicIslandViewModule_Companion_ProvidesLifecycleCoroutineScopeFactory(aVar);
    }

    public static LifecycleCoroutineScope providesLifecycleCoroutineScope(LifecycleOwner lifecycleOwner) {
        return (LifecycleCoroutineScope) i.d(DynamicIslandViewModule.Companion.providesLifecycleCoroutineScope(lifecycleOwner));
    }

    @Override // G0.a
    public LifecycleCoroutineScope get() {
        return providesLifecycleCoroutineScope((LifecycleOwner) this.lifecycleOwnerProvider.get());
    }
}
