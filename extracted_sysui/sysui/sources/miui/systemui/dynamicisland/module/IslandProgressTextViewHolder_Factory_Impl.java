package miui.systemui.dynamicisland.module;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandProgressTextViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandProgressTextViewHolder_Factory_Impl implements IslandProgressTextViewHolder.Factory {
    private final C0616IslandProgressTextViewHolder_Factory delegateFactory;

    public IslandProgressTextViewHolder_Factory_Impl(C0616IslandProgressTextViewHolder_Factory c0616IslandProgressTextViewHolder_Factory) {
        this.delegateFactory = c0616IslandProgressTextViewHolder_Factory;
    }

    @Override // miui.systemui.dynamicisland.module.IslandProgressTextViewHolder.Factory
    public IslandProgressTextViewHolder create(ViewGroup viewGroup, Function2 function2) {
        return this.delegateFactory.get(viewGroup, function2);
    }

    public static G0.a create(C0616IslandProgressTextViewHolder_Factory c0616IslandProgressTextViewHolder_Factory) {
        return F0.f.a(new IslandProgressTextViewHolder_Factory_Impl(c0616IslandProgressTextViewHolder_Factory));
    }
}
