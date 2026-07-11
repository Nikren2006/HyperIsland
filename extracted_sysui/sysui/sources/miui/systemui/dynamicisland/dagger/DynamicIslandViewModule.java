package miui.systemui.dynamicisland.dagger;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import g1.E;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;

/* JADX INFO: loaded from: classes3.dex */
public interface DynamicIslandViewModule {
    public static final Companion Companion = Companion.$$INSTANCE;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @DynamicIslandScope
        @DynamicIsland
        public final Lifecycle providesLifecycle(@DynamicIsland LifecycleOwner lifecycleOwner) {
            n.g(lifecycleOwner, "lifecycleOwner");
            return lifecycleOwner.getLifecycle();
        }

        @DynamicIslandScope
        @DynamicIsland
        public final LifecycleCoroutineScope providesLifecycleCoroutineScope(@DynamicIsland LifecycleOwner lifecycleOwner) {
            n.g(lifecycleOwner, "lifecycleOwner");
            return LifecycleOwnerKt.getLifecycleScope(lifecycleOwner);
        }
    }

    @DynamicIslandScope
    @DynamicIsland
    E bindsCoroutineScope(@DynamicIsland LifecycleCoroutineScope lifecycleCoroutineScope);

    @DynamicIslandScope
    @DynamicIsland
    LifecycleOwner bindsLifecycleOwner(DynamicIslandWindowView dynamicIslandWindowView);
}
