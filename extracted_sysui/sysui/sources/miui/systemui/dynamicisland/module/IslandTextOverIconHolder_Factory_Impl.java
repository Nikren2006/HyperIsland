package miui.systemui.dynamicisland.module;

import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandTextOverIconHolder;

/* JADX INFO: loaded from: classes3.dex */
public final class IslandTextOverIconHolder_Factory_Impl implements IslandTextOverIconHolder.Factory {
    private final C0624IslandTextOverIconHolder_Factory delegateFactory;

    public IslandTextOverIconHolder_Factory_Impl(C0624IslandTextOverIconHolder_Factory c0624IslandTextOverIconHolder_Factory) {
        this.delegateFactory = c0624IslandTextOverIconHolder_Factory;
    }

    @Override // miui.systemui.dynamicisland.module.IslandTextOverIconHolder.Factory
    public IslandTextOverIconHolder create(ViewGroup viewGroup, Function2 function2) {
        return this.delegateFactory.get(viewGroup, function2);
    }

    public static G0.a create(C0624IslandTextOverIconHolder_Factory c0624IslandTextOverIconHolder_Factory) {
        return F0.f.a(new IslandTextOverIconHolder_Factory_Impl(c0624IslandTextOverIconHolder_Factory));
    }
}
