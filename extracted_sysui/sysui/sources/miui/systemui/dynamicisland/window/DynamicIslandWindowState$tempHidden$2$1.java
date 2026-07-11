package miui.systemui.dynamicisland.window;

import H0.s;
import android.util.Log;
import miui.systemui.dynamicisland.DynamicIslandConstants;

/* JADX INFO: loaded from: classes3.dex */
@N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowState$tempHidden$2$1", f = "DynamicIslandWindowState.kt", l = {}, m = "invokeSuspend")
public final class DynamicIslandWindowState$tempHidden$2$1 extends N0.l implements V0.o {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    /* synthetic */ boolean Z$3;
    int label;

    public DynamicIslandWindowState$tempHidden$2$1(L0.d dVar) {
        super(5, dVar);
    }

    @Override // V0.o
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue(), ((Boolean) obj3).booleanValue(), ((Boolean) obj4).booleanValue(), (L0.d) obj5);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        M0.c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        H0.k.b(obj);
        boolean z2 = this.Z$0;
        boolean z3 = this.Z$1;
        boolean z4 = this.Z$2;
        boolean z5 = this.Z$3;
        Log.d(DynamicIslandConstants.TAG_DEBUG_EVENT, "tempHidden2: tempHiddenResult:" + z2 + " showNotificationIcons:" + z3 + " showOncePropIsland:" + z5);
        return N0.b.a(z2 || (z4 && !z3) || z5);
    }

    public final Object invoke(boolean z2, boolean z3, boolean z4, boolean z5, L0.d dVar) {
        DynamicIslandWindowState$tempHidden$2$1 dynamicIslandWindowState$tempHidden$2$1 = new DynamicIslandWindowState$tempHidden$2$1(dVar);
        dynamicIslandWindowState$tempHidden$2$1.Z$0 = z2;
        dynamicIslandWindowState$tempHidden$2$1.Z$1 = z3;
        dynamicIslandWindowState$tempHidden$2$1.Z$2 = z4;
        dynamicIslandWindowState$tempHidden$2$1.Z$3 = z5;
        return dynamicIslandWindowState$tempHidden$2$1.invokeSuspend(s.f314a);
    }
}
