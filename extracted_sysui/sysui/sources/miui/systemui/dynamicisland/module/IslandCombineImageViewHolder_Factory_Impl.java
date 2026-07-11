package miui.systemui.dynamicisland.module;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandCombineImageViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandCombineImageViewHolder_Factory_Impl implements IslandCombineImageViewHolder.Factory {
    private final C0607IslandCombineImageViewHolder_Factory delegateFactory;

    public IslandCombineImageViewHolder_Factory_Impl(C0607IslandCombineImageViewHolder_Factory c0607IslandCombineImageViewHolder_Factory) {
        this.delegateFactory = c0607IslandCombineImageViewHolder_Factory;
    }

    @Override // miui.systemui.dynamicisland.module.IslandCombineImageViewHolder.Factory
    public IslandCombineImageViewHolder create(ViewGroup viewGroup, Function2 function2) {
        return this.delegateFactory.get(viewGroup, function2);
    }

    public static G0.a create(C0607IslandCombineImageViewHolder_Factory c0607IslandCombineImageViewHolder_Factory) {
        return F0.f.a(new IslandCombineImageViewHolder_Factory_Impl(c0607IslandCombineImageViewHolder_Factory));
    }
}
