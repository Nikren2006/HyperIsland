package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;
import miui.systemui.dynamicisland.module.IslandProgressViewHolder;
import miui.systemui.dynamicisland.module.IslandRightTextViewHolder;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.module.IslandProgressTextViewHolder_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0616IslandProgressTextViewHolder_Factory {
    private final G0.a contextProvider;
    private final G0.a progressViewHolderFactoryProvider;
    private final G0.a rightTextViewHolderProvider;

    public C0616IslandProgressTextViewHolder_Factory(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        this.contextProvider = aVar;
        this.progressViewHolderFactoryProvider = aVar2;
        this.rightTextViewHolderProvider = aVar3;
    }

    public static C0616IslandProgressTextViewHolder_Factory create(G0.a aVar, G0.a aVar2, G0.a aVar3) {
        return new C0616IslandProgressTextViewHolder_Factory(aVar, aVar2, aVar3);
    }

    public static IslandProgressTextViewHolder newInstance(Context context, ViewGroup viewGroup, Function2 function2, IslandProgressViewHolder.Factory factory, IslandRightTextViewHolder.Factory factory2) {
        return new IslandProgressTextViewHolder(context, viewGroup, function2, factory, factory2);
    }

    public IslandProgressTextViewHolder get(ViewGroup viewGroup, Function2 function2) {
        return newInstance((Context) this.contextProvider.get(), viewGroup, function2, (IslandProgressViewHolder.Factory) this.progressViewHolderFactoryProvider.get(), (IslandRightTextViewHolder.Factory) this.rightTextViewHolderProvider.get());
    }
}
