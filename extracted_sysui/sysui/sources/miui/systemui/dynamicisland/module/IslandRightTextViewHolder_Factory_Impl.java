package miui.systemui.dynamicisland.module;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandRightTextViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandRightTextViewHolder_Factory_Impl implements IslandRightTextViewHolder.Factory {
    private final C0621IslandRightTextViewHolder_Factory delegateFactory;

    public IslandRightTextViewHolder_Factory_Impl(C0621IslandRightTextViewHolder_Factory c0621IslandRightTextViewHolder_Factory) {
        this.delegateFactory = c0621IslandRightTextViewHolder_Factory;
    }

    @Override // miui.systemui.dynamicisland.module.IslandRightTextViewHolder.Factory
    public IslandRightTextViewHolder create(ViewGroup viewGroup, Function2 function2) {
        return this.delegateFactory.get(viewGroup, function2);
    }

    public static G0.a create(C0621IslandRightTextViewHolder_Factory c0621IslandRightTextViewHolder_Factory) {
        return F0.f.a(new IslandRightTextViewHolder_Factory_Impl(c0621IslandRightTextViewHolder_Factory));
    }
}
