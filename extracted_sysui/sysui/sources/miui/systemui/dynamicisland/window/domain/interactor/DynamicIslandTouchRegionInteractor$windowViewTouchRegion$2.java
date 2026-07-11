package miui.systemui.dynamicisland.window.domain.interactor;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import V0.n;
import android.graphics.Region;
import j1.AbstractC0420h;
import j1.E;
import j1.I;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandTouchRegionInteractor$windowViewTouchRegion$2 extends o implements Function0 {
    final /* synthetic */ DynamicIslandTouchRegionInteractor this$0;

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor$windowViewTouchRegion$2$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor$windowViewTouchRegion$2$1", f = "DynamicIslandTouchRegionInteractor.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements n {
        /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ boolean Z$0;
        int label;
        final /* synthetic */ DynamicIslandTouchRegionInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DynamicIslandTouchRegionInteractor dynamicIslandTouchRegionInteractor, d dVar) {
            super(4, dVar);
            this.this$0 = dynamicIslandTouchRegionInteractor;
        }

        @Override // V0.n
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            return invoke(((Boolean) obj).booleanValue(), (Region) obj2, (Region) obj3, (d) obj4);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            boolean z2 = this.Z$0;
            Region region = (Region) this.L$0;
            Region region2 = (Region) this.L$1;
            return z2 ? region.isEmpty() ? this.this$0.getFullscreenRegion() : this.this$0.mergeTouchRegions(region, region2) : region2;
        }

        public final Object invoke(boolean z2, Region region, Region region2, d dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, dVar);
            anonymousClass1.Z$0 = z2;
            anonymousClass1.L$0 = region;
            anonymousClass1.L$1 = region2;
            return anonymousClass1.invokeSuspend(s.f314a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandTouchRegionInteractor$windowViewTouchRegion$2(DynamicIslandTouchRegionInteractor dynamicIslandTouchRegionInteractor) {
        super(0);
        this.this$0 = dynamicIslandTouchRegionInteractor;
    }

    @Override // kotlin.jvm.functions.Function0
    public final I invoke() {
        return AbstractC0420h.B(AbstractC0420h.i(this.this$0.getWindowViewController().isFreeformAnimRunning(), this.this$0.getWindowViewController().getFreeformAnimRegion(), this.this$0.getTouchRegion(), new AnonymousClass1(this.this$0, null)), this.this$0.scope, E.f4648a.c(), new Region());
    }
}
