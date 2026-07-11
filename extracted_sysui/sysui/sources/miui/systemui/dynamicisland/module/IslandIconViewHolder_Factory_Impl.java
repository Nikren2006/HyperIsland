package miui.systemui.dynamicisland.module;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandIconViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandIconViewHolder_Factory_Impl implements IslandIconViewHolder.Factory {
    private final C0611IslandIconViewHolder_Factory delegateFactory;

    public IslandIconViewHolder_Factory_Impl(C0611IslandIconViewHolder_Factory c0611IslandIconViewHolder_Factory) {
        this.delegateFactory = c0611IslandIconViewHolder_Factory;
    }

    @Override // miui.systemui.dynamicisland.module.IslandIconViewHolder.Factory
    public IslandIconViewHolder create(ViewGroup viewGroup, Function2 function2) {
        return this.delegateFactory.get(viewGroup, function2);
    }

    public static G0.a create(C0611IslandIconViewHolder_Factory c0611IslandIconViewHolder_Factory) {
        return F0.f.a(new IslandIconViewHolder_Factory_Impl(c0611IslandIconViewHolder_Factory));
    }
}
