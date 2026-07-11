package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.module.IslandFixedWidthDigitViewHolder_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0609IslandFixedWidthDigitViewHolder_Factory {
    private final G0.a contextProvider;

    public C0609IslandFixedWidthDigitViewHolder_Factory(G0.a aVar) {
        this.contextProvider = aVar;
    }

    public static C0609IslandFixedWidthDigitViewHolder_Factory create(G0.a aVar) {
        return new C0609IslandFixedWidthDigitViewHolder_Factory(aVar);
    }

    public static IslandFixedWidthDigitViewHolder newInstance(Context context, ViewGroup viewGroup, Function2 function2) {
        return new IslandFixedWidthDigitViewHolder(context, viewGroup, function2);
    }

    public IslandFixedWidthDigitViewHolder get(ViewGroup viewGroup, Function2 function2) {
        return newInstance((Context) this.contextProvider.get(), viewGroup, function2);
    }
}
