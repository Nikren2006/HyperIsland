package miui.systemui.dynamicisland.domain.interactor;

import H0.s;
import L0.d;
import N0.f;
import N0.l;
import V0.p;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$regionSampling$1", f = "DynamicIslandRegionSamplingInteractor.kt", l = {}, m = "invokeSuspend")
public final class DynamicIslandRegionSamplingInteractor$regionSampling$1 extends l implements p {
    /* synthetic */ int I$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    /* synthetic */ boolean Z$3;
    int label;

    public DynamicIslandRegionSamplingInteractor$regionSampling$1(d dVar) {
        super(6, dVar);
    }

    @Override // V0.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue(), ((Boolean) obj3).booleanValue(), ((Number) obj4).intValue(), ((Boolean) obj5).booleanValue(), (d) obj6);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0020  */
    @Override // N0.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r4) throws java.lang.Throwable {
        /*
            r3 = this;
            M0.c.c()
            int r0 = r3.label
            if (r0 != 0) goto L26
            H0.k.b(r4)
            boolean r4 = r3.Z$0
            boolean r0 = r3.Z$1
            boolean r1 = r3.Z$2
            int r2 = r3.I$0
            boolean r3 = r3.Z$3
            if (r4 == 0) goto L20
            if (r3 != 0) goto L20
            if (r0 != 0) goto L20
            r3 = 1
            if (r1 == 0) goto L21
            if (r2 != r3) goto L20
            goto L21
        L20:
            r3 = 0
        L21:
            java.lang.Boolean r3 = N0.b.a(r3)
            return r3
        L26:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$regionSampling$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(boolean z2, boolean z3, boolean z4, int i2, boolean z5, d dVar) {
        DynamicIslandRegionSamplingInteractor$regionSampling$1 dynamicIslandRegionSamplingInteractor$regionSampling$1 = new DynamicIslandRegionSamplingInteractor$regionSampling$1(dVar);
        dynamicIslandRegionSamplingInteractor$regionSampling$1.Z$0 = z2;
        dynamicIslandRegionSamplingInteractor$regionSampling$1.Z$1 = z3;
        dynamicIslandRegionSamplingInteractor$regionSampling$1.Z$2 = z4;
        dynamicIslandRegionSamplingInteractor$regionSampling$1.I$0 = i2;
        dynamicIslandRegionSamplingInteractor$regionSampling$1.Z$3 = z5;
        return dynamicIslandRegionSamplingInteractor$regionSampling$1.invokeSuspend(s.f314a);
    }
}
