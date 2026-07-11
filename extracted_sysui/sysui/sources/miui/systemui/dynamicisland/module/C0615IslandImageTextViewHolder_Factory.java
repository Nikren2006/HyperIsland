package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandIconViewHolder;
import miui.systemui.dynamicisland.module.IslandTextViewHolder;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.module.IslandImageTextViewHolder_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0615IslandImageTextViewHolder_Factory {
    private final G0.a contextProvider;
    private final G0.a iconViewHolderFactoryProvider;
    private final G0.a textViewHolderFactoryProvider;

    public C0615IslandImageTextViewHolder_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.contextProvider = aVar;
        this.iconViewHolderFactoryProvider = aVar2;
        this.textViewHolderFactoryProvider = aVar3;
    }

    public static C0615IslandImageTextViewHolder_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new C0615IslandImageTextViewHolder_Factory(aVar, aVar2, aVar3);
    }

    public static IslandImageTextViewHolder newInstance(Context context, ViewGroup viewGroup, Function2 function2, IslandIconViewHolder.Factory factory, IslandTextViewHolder.Factory factory2) {
        return new IslandImageTextViewHolder(context, viewGroup, function2, factory, factory2);
    }

    public IslandImageTextViewHolder get(ViewGroup viewGroup, Function2 function2) {
        return newInstance((Context) this.contextProvider.get(), viewGroup, function2, (IslandIconViewHolder.Factory) this.iconViewHolderFactoryProvider.get(), (IslandTextViewHolder.Factory) this.textViewHolderFactoryProvider.get());
    }
}
