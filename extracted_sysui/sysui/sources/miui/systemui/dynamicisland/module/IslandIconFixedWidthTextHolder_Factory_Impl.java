package miui.systemui.dynamicisland.module;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandIconFixedWidthTextHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandIconFixedWidthTextHolder_Factory_Impl implements IslandIconFixedWidthTextHolder.Factory {
    private final C0610IslandIconFixedWidthTextHolder_Factory delegateFactory;

    public IslandIconFixedWidthTextHolder_Factory_Impl(C0610IslandIconFixedWidthTextHolder_Factory c0610IslandIconFixedWidthTextHolder_Factory) {
        this.delegateFactory = c0610IslandIconFixedWidthTextHolder_Factory;
    }

    @Override // miui.systemui.dynamicisland.module.IslandIconFixedWidthTextHolder.Factory
    public IslandIconFixedWidthTextHolder create(ViewGroup viewGroup, Function2 function2) {
        return this.delegateFactory.get(viewGroup, function2);
    }

    public static G0.a create(C0610IslandIconFixedWidthTextHolder_Factory c0610IslandIconFixedWidthTextHolder_Factory) {
        return F0.f.a(new IslandIconFixedWidthTextHolder_Factory_Impl(c0610IslandIconFixedWidthTextHolder_Factory));
    }
}
