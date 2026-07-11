package miui.systemui.plugins.data.repository;

import E0.a;
import I0.L;
import j1.AbstractC0420h;
import j1.I;
import j1.K;
import j1.u;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.qualifiers.Plugin;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginInstancesRepositoryImpl implements PluginInstancesRepository {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "PluginInstanceRepository";
    private final u _plugins;
    private final I plugins;
    private final a scope;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public PluginInstancesRepositoryImpl(@Plugin a scope) {
        n.g(scope, "scope");
        this.scope = scope;
        u uVarA = K.a(I0.K.b());
        this._plugins = uVarA;
        this.plugins = AbstractC0420h.b(uVarA);
    }

    @Override // miui.systemui.plugins.data.repository.PluginInstancesRepository
    public I getPlugins() {
        return this.plugins;
    }

    public final void onPluginLoaded(com.android.systemui.plugins.Plugin plugin) {
        n.g(plugin, "plugin");
        u uVar = this._plugins;
        uVar.setValue(L.h((Set) uVar.getValue(), plugin));
    }

    public final void onPluginUnloaded(com.android.systemui.plugins.Plugin plugin) {
        n.g(plugin, "plugin");
        u uVar = this._plugins;
        uVar.setValue(L.f((Set) uVar.getValue(), plugin));
    }
}
