package miui.systemui.dynamicisland.module;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandImageTextView4Holder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandImageTextView4Holder_Factory_Impl implements IslandImageTextView4Holder.Factory {
    private final C0614IslandImageTextView4Holder_Factory delegateFactory;

    public IslandImageTextView4Holder_Factory_Impl(C0614IslandImageTextView4Holder_Factory c0614IslandImageTextView4Holder_Factory) {
        this.delegateFactory = c0614IslandImageTextView4Holder_Factory;
    }

    @Override // miui.systemui.dynamicisland.module.IslandImageTextView4Holder.Factory
    public IslandImageTextView4Holder create(ViewGroup viewGroup, Function2 function2) {
        return this.delegateFactory.get(viewGroup, function2);
    }

    public static G0.a create(C0614IslandImageTextView4Holder_Factory c0614IslandImageTextView4Holder_Factory) {
        return F0.f.a(new IslandImageTextView4Holder_Factory_Impl(c0614IslandImageTextView4Holder_Factory));
    }
}
