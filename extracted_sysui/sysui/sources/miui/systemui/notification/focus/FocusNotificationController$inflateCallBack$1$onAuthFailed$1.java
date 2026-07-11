package miui.systemui.notification.focus;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.util.Log;
import g1.E;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.notification.focus.FocusNotificationController$inflateCallBack$1$onAuthFailed$1", f = "FocusNotificationController.kt", l = {}, m = "invokeSuspend")
public final class FocusNotificationController$inflateCallBack$1$onAuthFailed$1 extends l implements Function2 {
    final /* synthetic */ String $key;
    final /* synthetic */ String $packageName;
    int label;
    final /* synthetic */ FocusNotificationController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FocusNotificationController$inflateCallBack$1$onAuthFailed$1(String str, String str2, FocusNotificationController focusNotificationController, d dVar) {
        super(2, dVar);
        this.$key = str;
        this.$packageName = str2;
        this.this$0 = focusNotificationController;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new FocusNotificationController$inflateCallBack$1$onAuthFailed$1(this.$key, this.$packageName, this.this$0, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((FocusNotificationController$inflateCallBack$1$onAuthFailed$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k.b(obj);
        Log.e(Const.TAG, "onAuthFailed " + this.$key + " " + this.$packageName);
        this.this$0.authResult.put(this.$key, N0.b.a(false));
        this.this$0.inflateFinishCallback(this.$key);
        return s.f314a;
    }
}
