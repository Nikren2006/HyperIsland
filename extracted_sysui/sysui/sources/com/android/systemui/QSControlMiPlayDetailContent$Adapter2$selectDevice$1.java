package com.android.systemui;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import kotlin.jvm.functions.Function2;
import miuix.responsive.ResponsivePolicy;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "com.android.systemui.QSControlMiPlayDetailContent$Adapter2$selectDevice$1", f = "QSControlMiPlayDetailContent.kt", l = {ResponsivePolicy.THRESHOLD_REGULAR_WINDOW}, m = "invokeSuspend")
public final class QSControlMiPlayDetailContent$Adapter2$selectDevice$1 extends N0.l implements Function2 {
    int label;

    public QSControlMiPlayDetailContent$Adapter2$selectDevice$1(L0.d dVar) {
        super(2, dVar);
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new QSControlMiPlayDetailContent$Adapter2$selectDevice$1(dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(g1.E e2, L0.d dVar) {
        return ((QSControlMiPlayDetailContent$Adapter2$selectDevice$1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            H0.k.b(obj);
            this.label = 1;
            if (g1.M.b(300L, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
        }
        Context context = MiPlayController.INSTANCE.getContext();
        Intent intent = new Intent("miui.intent.action.ACTIVITY_MIPLAY_INVISIBLE_MODE_DIALOG");
        intent.addFlags(268435456);
        context.startActivityAsUser(intent, UserHandle.CURRENT);
        return H0.s.f314a;
    }
}
