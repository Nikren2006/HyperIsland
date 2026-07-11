package miui.systemui.dynamicisland.module;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandImageTextView2Holder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandImageTextView2Holder_Factory_Impl implements IslandImageTextView2Holder.Factory {
    private final C0612IslandImageTextView2Holder_Factory delegateFactory;

    public IslandImageTextView2Holder_Factory_Impl(C0612IslandImageTextView2Holder_Factory c0612IslandImageTextView2Holder_Factory) {
        this.delegateFactory = c0612IslandImageTextView2Holder_Factory;
    }

    @Override // miui.systemui.dynamicisland.module.IslandImageTextView2Holder.Factory
    public IslandImageTextView2Holder create(ViewGroup viewGroup, Function2 function2) {
        return this.delegateFactory.get(viewGroup, function2);
    }

    public static G0.a create(C0612IslandImageTextView2Holder_Factory c0612IslandImageTextView2Holder_Factory) {
        return F0.f.a(new IslandImageTextView2Holder_Factory_Impl(c0612IslandImageTextView2Holder_Factory));
    }
}
