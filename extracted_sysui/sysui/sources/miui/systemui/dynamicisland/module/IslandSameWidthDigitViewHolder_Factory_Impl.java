package miui.systemui.dynamicisland.module;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandSameWidthDigitViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandSameWidthDigitViewHolder_Factory_Impl implements IslandSameWidthDigitViewHolder.Factory {
    private final C0623IslandSameWidthDigitViewHolder_Factory delegateFactory;

    public IslandSameWidthDigitViewHolder_Factory_Impl(C0623IslandSameWidthDigitViewHolder_Factory c0623IslandSameWidthDigitViewHolder_Factory) {
        this.delegateFactory = c0623IslandSameWidthDigitViewHolder_Factory;
    }

    @Override // miui.systemui.dynamicisland.module.IslandSameWidthDigitViewHolder.Factory
    public IslandSameWidthDigitViewHolder create(ViewGroup viewGroup, Function2 function2) {
        return this.delegateFactory.get(viewGroup, function2);
    }

    public static G0.a create(C0623IslandSameWidthDigitViewHolder_Factory c0623IslandSameWidthDigitViewHolder_Factory) {
        return F0.f.a(new IslandSameWidthDigitViewHolder_Factory_Impl(c0623IslandSameWidthDigitViewHolder_Factory));
    }
}
