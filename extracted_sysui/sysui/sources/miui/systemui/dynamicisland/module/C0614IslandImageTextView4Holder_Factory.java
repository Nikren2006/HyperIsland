package miui.systemui.dynamicisland.module;

import android.content.Context;
import android.view.ViewGroup;
import kotlin.jvm.functions.Function2;

/* JADX INFO: renamed from: miui.systemui.dynamicisland.module.IslandImageTextView4Holder_Factory, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C0614IslandImageTextView4Holder_Factory {
    private final G0.a contextProvider;

    public C0614IslandImageTextView4Holder_Factory(G0.a aVar) {
        this.contextProvider = aVar;
    }

    public static C0614IslandImageTextView4Holder_Factory create(G0.a aVar) {
        return new C0614IslandImageTextView4Holder_Factory(aVar);
    }

    public static IslandImageTextView4Holder newInstance(Context context, ViewGroup viewGroup, Function2 function2) {
        return new IslandImageTextView4Holder(context, viewGroup, function2);
    }

    public IslandImageTextView4Holder get(ViewGroup viewGroup, Function2 function2) {
        return newInstance((Context) this.contextProvider.get(), viewGroup, function2);
    }
}
