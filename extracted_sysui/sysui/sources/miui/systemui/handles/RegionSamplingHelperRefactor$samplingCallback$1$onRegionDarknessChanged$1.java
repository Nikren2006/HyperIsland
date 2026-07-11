package miui.systemui.handles;

import H0.k;
import H0.s;
import L0.d;
import N0.f;
import N0.l;
import g1.E;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.handles.RegionSamplingHelperRefactor$samplingCallback$1$onRegionDarknessChanged$1", f = "RegionSamplingHelperRefactor.kt", l = {}, m = "invokeSuspend")
public final class RegionSamplingHelperRefactor$samplingCallback$1$onRegionDarknessChanged$1 extends l implements Function2 {
    final /* synthetic */ boolean $isRegionDark;
    int label;
    final /* synthetic */ RegionSamplingHelperRefactor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RegionSamplingHelperRefactor$samplingCallback$1$onRegionDarknessChanged$1(RegionSamplingHelperRefactor regionSamplingHelperRefactor, boolean z2, d dVar) {
        super(2, dVar);
        this.this$0 = regionSamplingHelperRefactor;
        this.$isRegionDark = z2;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new RegionSamplingHelperRefactor$samplingCallback$1$onRegionDarknessChanged$1(this.this$0, this.$isRegionDark, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((RegionSamplingHelperRefactor$samplingCallback$1$onRegionDarknessChanged$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        M0.c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k.b(obj);
        this.this$0._isRegionDark.setValue(N0.b.a(this.$isRegionDark));
        return s.f314a;
    }
}
