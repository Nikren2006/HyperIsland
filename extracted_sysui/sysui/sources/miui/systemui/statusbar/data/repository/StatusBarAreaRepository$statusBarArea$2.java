package miui.systemui.statusbar.data.repository;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import V0.n;
import android.content.Context;
import android.graphics.Rect;
import miui.systemui.util.SystemBarUtilsCompat;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.statusbar.data.repository.StatusBarAreaRepository$statusBarArea$2", f = "StatusBarAreaRepository.kt", l = {}, m = "invokeSuspend")
public final class StatusBarAreaRepository$statusBarArea$2 extends l implements n {
    final /* synthetic */ Context $context;
    /* synthetic */ float F$0;
    /* synthetic */ boolean Z$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatusBarAreaRepository$statusBarArea$2(Context context, d dVar) {
        super(4, dVar);
        this.$context = context;
    }

    public final Object invoke(s sVar, boolean z2, float f2, d dVar) {
        StatusBarAreaRepository$statusBarArea$2 statusBarAreaRepository$statusBarArea$2 = new StatusBarAreaRepository$statusBarArea$2(this.$context, dVar);
        statusBarAreaRepository$statusBarArea$2.Z$0 = z2;
        statusBarAreaRepository$statusBarArea$2.F$0 = f2;
        return statusBarAreaRepository$statusBarArea$2.invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k.b(obj);
        int i2 = this.Z$0 ? (int) (this.$context.getResources().getDisplayMetrics().heightPixels * this.F$0) : 0;
        Integer statusBarHeightCompat = SystemBarUtilsCompat.INSTANCE.getStatusBarHeightCompat(this.$context);
        return new Rect(0, i2, this.$context.getResources().getDisplayMetrics().widthPixels, (statusBarHeightCompat != null ? statusBarHeightCompat.intValue() : 0) + i2);
    }

    @Override // V0.n
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return invoke((s) obj, ((Boolean) obj2).booleanValue(), ((Number) obj3).floatValue(), (d) obj4);
    }
}
