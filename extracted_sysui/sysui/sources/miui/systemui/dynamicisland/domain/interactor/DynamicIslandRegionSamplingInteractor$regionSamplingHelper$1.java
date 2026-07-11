package miui.systemui.dynamicisland.domain.interactor;

import android.graphics.Rect;
import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandRegionSamplingInteractor$regionSamplingHelper$1 extends o implements Function1 {
    final /* synthetic */ DynamicIslandRegionSamplingInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandRegionSamplingInteractor$regionSamplingHelper$1(DynamicIslandRegionSamplingInteractor dynamicIslandRegionSamplingInteractor) {
        super(1);
        this.this$0 = dynamicIslandRegionSamplingInteractor;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Rect invoke(View create) {
        n.g(create, "$this$create");
        return (Rect) this.this$0.statusBarAreaRepository.getStatusBarArea().getValue();
    }
}
