package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.module.IslandRightTextViewHolder_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0621IslandRightTextViewHolder_Factory {
    private final G0.a contextProvider;

    public C0621IslandRightTextViewHolder_Factory(G0.a aVar) {
        this.contextProvider = aVar;
    }

    public static C0621IslandRightTextViewHolder_Factory create(G0.a aVar) {
        return new C0621IslandRightTextViewHolder_Factory(aVar);
    }

    public static IslandRightTextViewHolder newInstance(Context context, ViewGroup viewGroup, Function2 function2) {
        return new IslandRightTextViewHolder(context, viewGroup, function2);
    }

    public IslandRightTextViewHolder get(ViewGroup viewGroup, Function2 function2) {
        return newInstance((Context) this.contextProvider.get(), viewGroup, function2);
    }
}
