package miui.systemui.dynamicisland.domain.interactor;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.util.Log;
import g1.E;
import j1.I;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$logStatesChanged$1$1", f = "DynamicIslandRegionSamplingInteractor.kt", l = {99}, m = "invokeSuspend")
public final class DynamicIslandRegionSamplingInteractor$logStatesChanged$1$1 extends l implements Function2 {
    int label;
    final /* synthetic */ DynamicIslandRegionSamplingInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandRegionSamplingInteractor$logStatesChanged$1$1(DynamicIslandRegionSamplingInteractor dynamicIslandRegionSamplingInteractor, d dVar) {
        super(2, dVar);
        this.this$0 = dynamicIslandRegionSamplingInteractor;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new DynamicIslandRegionSamplingInteractor$logStatesChanged$1$1(this.this$0, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((DynamicIslandRegionSamplingInteractor$logStatesChanged$1$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            I medianLuma = this.this$0.getMedianLuma();
            InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$logStatesChanged$1$1.1
                public final Object emit(float f2, d dVar) {
                    Log.d("DynamicIslandRegionSamplingInteractor", "median luma changed to " + f2);
                    return s.f314a;
                }

                @Override // j1.InterfaceC0419g
                public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                    return emit(((Number) obj2).floatValue(), dVar);
                }
            };
            this.label = 1;
            if (medianLuma.collect(interfaceC0419g, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
        }
        throw new H0.c();
    }
}
