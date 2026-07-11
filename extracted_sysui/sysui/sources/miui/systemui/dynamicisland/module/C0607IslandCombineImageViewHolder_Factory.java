package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandIconViewHolder;
import miui.systemui.dynamicisland.module.IslandProgressViewHolder;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.module.IslandCombineImageViewHolder_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0607IslandCombineImageViewHolder_Factory {
    private final G0.a contextProvider;
    private final G0.a iconViewHolderFactoryProvider;
    private final G0.a progressViewHolderFactoryProvider;

    public C0607IslandCombineImageViewHolder_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.contextProvider = aVar;
        this.progressViewHolderFactoryProvider = aVar2;
        this.iconViewHolderFactoryProvider = aVar3;
    }

    public static C0607IslandCombineImageViewHolder_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new C0607IslandCombineImageViewHolder_Factory(aVar, aVar2, aVar3);
    }

    public static IslandCombineImageViewHolder newInstance(Context context, ViewGroup viewGroup, Function2 function2, IslandProgressViewHolder.Factory factory, IslandIconViewHolder.Factory factory2) {
        return new IslandCombineImageViewHolder(context, viewGroup, function2, factory, factory2);
    }

    public IslandCombineImageViewHolder get(ViewGroup viewGroup, Function2 function2) {
        return newInstance((Context) this.contextProvider.get(), viewGroup, function2, (IslandProgressViewHolder.Factory) this.progressViewHolderFactoryProvider.get(), (IslandIconViewHolder.Factory) this.iconViewHolderFactoryProvider.get());
    }
}
