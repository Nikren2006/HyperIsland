package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandIconViewHolder;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.module.IslandTextOverIconHolder_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0624IslandTextOverIconHolder_Factory {
    private final G0.a contextProvider;
    private final G0.a iconViewHolderFactoryProvider;

    public C0624IslandTextOverIconHolder_Factory(G0.a aVar, G0.a aVar2) {
        this.contextProvider = aVar;
        this.iconViewHolderFactoryProvider = aVar2;
    }

    public static C0624IslandTextOverIconHolder_Factory create(G0.a aVar, G0.a aVar2) {
        return new C0624IslandTextOverIconHolder_Factory(aVar, aVar2);
    }

    public static IslandTextOverIconHolder newInstance(Context context, ViewGroup viewGroup, Function2 function2, IslandIconViewHolder.Factory factory) {
        return new IslandTextOverIconHolder(context, viewGroup, function2, factory);
    }

    public IslandTextOverIconHolder get(ViewGroup viewGroup, Function2 function2) {
        return newInstance((Context) this.contextProvider.get(), viewGroup, function2, (IslandIconViewHolder.Factory) this.iconViewHolderFactoryProvider.get());
    }
}
