package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandIconViewHolder;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.module.IslandIconFixedWidthTextHolder_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0610IslandIconFixedWidthTextHolder_Factory {
    private final G0.a contextProvider;
    private final G0.a iconViewHolderFactoryProvider;

    public C0610IslandIconFixedWidthTextHolder_Factory(G0.a aVar, G0.a aVar2) {
        this.contextProvider = aVar;
        this.iconViewHolderFactoryProvider = aVar2;
    }

    public static C0610IslandIconFixedWidthTextHolder_Factory create(G0.a aVar, G0.a aVar2) {
        return new C0610IslandIconFixedWidthTextHolder_Factory(aVar, aVar2);
    }

    public static IslandIconFixedWidthTextHolder newInstance(Context context, ViewGroup viewGroup, Function2 function2, IslandIconViewHolder.Factory factory) {
        return new IslandIconFixedWidthTextHolder(context, viewGroup, function2, factory);
    }

    public IslandIconFixedWidthTextHolder get(ViewGroup viewGroup, Function2 function2) {
        return newInstance((Context) this.contextProvider.get(), viewGroup, function2, (IslandIconViewHolder.Factory) this.iconViewHolderFactoryProvider.get());
    }
}
