package miui.systemui.dynamicisland.module;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandFixedWidthDigitViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandFixedWidthDigitViewHolder_Factory_Impl implements IslandFixedWidthDigitViewHolder.Factory {
    private final C0609IslandFixedWidthDigitViewHolder_Factory delegateFactory;

    public IslandFixedWidthDigitViewHolder_Factory_Impl(C0609IslandFixedWidthDigitViewHolder_Factory c0609IslandFixedWidthDigitViewHolder_Factory) {
        this.delegateFactory = c0609IslandFixedWidthDigitViewHolder_Factory;
    }

    @Override // miui.systemui.dynamicisland.module.IslandFixedWidthDigitViewHolder.Factory
    public IslandFixedWidthDigitViewHolder create(ViewGroup viewGroup, Function2 function2) {
        return this.delegateFactory.get(viewGroup, function2);
    }

    public static G0.a create(C0609IslandFixedWidthDigitViewHolder_Factory c0609IslandFixedWidthDigitViewHolder_Factory) {
        return F0.f.a(new IslandFixedWidthDigitViewHolder_Factory_Impl(c0609IslandFixedWidthDigitViewHolder_Factory));
    }
}
