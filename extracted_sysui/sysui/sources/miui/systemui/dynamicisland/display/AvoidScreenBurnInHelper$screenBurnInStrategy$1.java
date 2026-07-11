package miui.systemui.dynamicisland.display;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.b;
import N0.f;
import N0.l;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.dynamicisland.display.AvoidScreenBurnInHelper$screenBurnInStrategy$1", f = "AvoidScreenBurnInHelper.kt", l = {}, m = "invokeSuspend")
public final class AvoidScreenBurnInHelper$screenBurnInStrategy$1 extends l implements Function3 {
    /* synthetic */ int I$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ AvoidScreenBurnInHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AvoidScreenBurnInHelper$screenBurnInStrategy$1(AvoidScreenBurnInHelper avoidScreenBurnInHelper, d dVar) {
        super(3, dVar);
        this.this$0 = avoidScreenBurnInHelper;
    }

    public final Object invoke(int i2, boolean z2, d dVar) {
        AvoidScreenBurnInHelper$screenBurnInStrategy$1 avoidScreenBurnInHelper$screenBurnInStrategy$1 = new AvoidScreenBurnInHelper$screenBurnInStrategy$1(this.this$0, dVar);
        avoidScreenBurnInHelper$screenBurnInStrategy$1.I$0 = i2;
        avoidScreenBurnInHelper$screenBurnInStrategy$1.Z$0 = z2;
        return avoidScreenBurnInHelper$screenBurnInStrategy$1.invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k.b(obj);
        int i2 = this.I$0;
        boolean z2 = this.Z$0;
        if (!this.this$0.ready) {
            i2 = -1;
        } else if (((Boolean) this.this$0._notificationPanelExpanded.getValue()).booleanValue()) {
            i2 = 4;
        } else if (z2) {
            i2 = 5;
        } else if (this.this$0.recoverBackgroundAlpha) {
            this.this$0.recoverBackgroundAlpha = false;
            i2 = 4;
        }
        return b.c(i2);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke(((Number) obj).intValue(), ((Boolean) obj2).booleanValue(), (d) obj3);
    }
}
