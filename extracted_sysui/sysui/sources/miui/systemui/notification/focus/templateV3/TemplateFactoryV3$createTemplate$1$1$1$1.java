package miui.systemui.notification.focus.templateV3;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.service.notification.StatusBarNotification;
import g1.E;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import miui.systemui.notification.focus.InflateAndAuthCallBack;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.notification.focus.templateV3.TemplateFactoryV3$createTemplate$1$1$1$1", f = "TemplateFactoryV3.kt", l = {}, m = "invokeSuspend")
public final class TemplateFactoryV3$createTemplate$1$1$1$1 extends l implements Function2 {
    final /* synthetic */ InflateAndAuthCallBack $inflateCallBack;
    final /* synthetic */ StatusBarNotification $sbn;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TemplateFactoryV3$createTemplate$1$1$1$1(InflateAndAuthCallBack inflateAndAuthCallBack, StatusBarNotification statusBarNotification, d dVar) {
        super(2, dVar);
        this.$inflateCallBack = inflateAndAuthCallBack;
        this.$sbn = statusBarNotification;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        return new TemplateFactoryV3$createTemplate$1$1$1$1(this.$inflateCallBack, this.$sbn, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(E e2, d dVar) {
        return ((TemplateFactoryV3$createTemplate$1$1$1$1) create(e2, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k.b(obj);
        InflateAndAuthCallBack inflateAndAuthCallBack = this.$inflateCallBack;
        String key = this.$sbn.getKey();
        n.f(key, "getKey(...)");
        inflateAndAuthCallBack.onInflateFinish(key);
        return s.f314a;
    }
}
