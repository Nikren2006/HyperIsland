package miui.systemui.dynamicisland.module;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandTextViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandTextViewHolder_Factory_Impl implements IslandTextViewHolder.Factory {
    private final C0627IslandTextViewHolder_Factory delegateFactory;

    public IslandTextViewHolder_Factory_Impl(C0627IslandTextViewHolder_Factory c0627IslandTextViewHolder_Factory) {
        this.delegateFactory = c0627IslandTextViewHolder_Factory;
    }

    @Override // miui.systemui.dynamicisland.module.IslandTextViewHolder.Factory
    public IslandTextViewHolder create(ViewGroup viewGroup, Function2 function2) {
        return this.delegateFactory.get(viewGroup, function2);
    }

    public static G0.a create(C0627IslandTextViewHolder_Factory c0627IslandTextViewHolder_Factory) {
        return F0.f.a(new IslandTextViewHolder_Factory_Impl(c0627IslandTextViewHolder_Factory));
    }
}
