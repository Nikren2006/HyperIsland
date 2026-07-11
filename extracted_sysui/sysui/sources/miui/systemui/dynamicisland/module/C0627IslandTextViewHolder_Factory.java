package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.module.IslandTextViewHolder_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0627IslandTextViewHolder_Factory {
    private final G0.a contextProvider;

    public C0627IslandTextViewHolder_Factory(G0.a aVar) {
        this.contextProvider = aVar;
    }

    public static C0627IslandTextViewHolder_Factory create(G0.a aVar) {
        return new C0627IslandTextViewHolder_Factory(aVar);
    }

    public static IslandTextViewHolder newInstance(Context context, ViewGroup viewGroup, Function2 function2) {
        return new IslandTextViewHolder(context, viewGroup, function2);
    }

    public IslandTextViewHolder get(ViewGroup viewGroup, Function2 function2) {
        return newInstance((Context) this.contextProvider.get(), viewGroup, function2);
    }
}
