package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.view.ViewGroup;
import g1.E;
import kotlin.jvm.functions.Function2;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.module.IslandIconViewHolder_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0611IslandIconViewHolder_Factory {
    private final G0.a contextProvider;
    private final G0.a scopeProvider;

    public C0611IslandIconViewHolder_Factory(G0.a aVar, G0.a aVar2) {
        this.contextProvider = aVar;
        this.scopeProvider = aVar2;
    }

    public static C0611IslandIconViewHolder_Factory create(G0.a aVar, G0.a aVar2) {
        return new C0611IslandIconViewHolder_Factory(aVar, aVar2);
    }

    public static IslandIconViewHolder newInstance(Context context, E e2, ViewGroup viewGroup, Function2 function2) {
        return new IslandIconViewHolder(context, e2, viewGroup, function2);
    }

    public IslandIconViewHolder get(ViewGroup viewGroup, Function2 function2) {
        return newInstance((Context) this.contextProvider.get(), (E) this.scopeProvider.get(), viewGroup, function2);
    }
}
