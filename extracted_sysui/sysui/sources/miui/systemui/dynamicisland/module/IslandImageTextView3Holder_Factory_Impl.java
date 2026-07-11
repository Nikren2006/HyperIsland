package miui.systemui.dynamicisland.module;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandImageTextView3Holder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandImageTextView3Holder_Factory_Impl implements IslandImageTextView3Holder.Factory {
    private final C0613IslandImageTextView3Holder_Factory delegateFactory;

    public IslandImageTextView3Holder_Factory_Impl(C0613IslandImageTextView3Holder_Factory c0613IslandImageTextView3Holder_Factory) {
        this.delegateFactory = c0613IslandImageTextView3Holder_Factory;
    }

    @Override // miui.systemui.dynamicisland.module.IslandImageTextView3Holder.Factory
    public IslandImageTextView3Holder create(ViewGroup viewGroup, Function2 function2) {
        return this.delegateFactory.get(viewGroup, function2);
    }

    public static G0.a create(C0613IslandImageTextView3Holder_Factory c0613IslandImageTextView3Holder_Factory) {
        return F0.f.a(new IslandImageTextView3Holder_Factory_Impl(c0613IslandImageTextView3Holder_Factory));
    }
}
