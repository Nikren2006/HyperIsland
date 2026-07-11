package miui.systemui.dynamicisland.module;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandProgressViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandProgressViewHolder_Factory_Impl implements IslandProgressViewHolder.Factory {
    private final C0617IslandProgressViewHolder_Factory delegateFactory;

    public IslandProgressViewHolder_Factory_Impl(C0617IslandProgressViewHolder_Factory c0617IslandProgressViewHolder_Factory) {
        this.delegateFactory = c0617IslandProgressViewHolder_Factory;
    }

    @Override // miui.systemui.dynamicisland.module.IslandProgressViewHolder.Factory
    public IslandProgressViewHolder create(ViewGroup viewGroup, Function2 function2) {
        return this.delegateFactory.get(viewGroup, function2);
    }

    public static G0.a create(C0617IslandProgressViewHolder_Factory c0617IslandProgressViewHolder_Factory) {
        return F0.f.a(new IslandProgressViewHolder_Factory_Impl(c0617IslandProgressViewHolder_Factory));
    }
}
