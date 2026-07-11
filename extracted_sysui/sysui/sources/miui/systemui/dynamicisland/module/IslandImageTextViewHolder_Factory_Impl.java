package miui.systemui.dynamicisland.module;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandImageTextViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandImageTextViewHolder_Factory_Impl implements IslandImageTextViewHolder.Factory {
    private final C0615IslandImageTextViewHolder_Factory delegateFactory;

    public IslandImageTextViewHolder_Factory_Impl(C0615IslandImageTextViewHolder_Factory c0615IslandImageTextViewHolder_Factory) {
        this.delegateFactory = c0615IslandImageTextViewHolder_Factory;
    }

    @Override // miui.systemui.dynamicisland.module.IslandImageTextViewHolder.Factory
    public IslandImageTextViewHolder create(ViewGroup viewGroup, Function2 function2) {
        return this.delegateFactory.get(viewGroup, function2);
    }

    public static G0.a create(C0615IslandImageTextViewHolder_Factory c0615IslandImageTextViewHolder_Factory) {
        return F0.f.a(new IslandImageTextViewHolder_Factory_Impl(c0615IslandImageTextViewHolder_Factory));
    }
}
