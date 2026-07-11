package com.android.systemui;

import android.content.Context;
import com.android.systemui.QSControlMiPlayDetailHeader;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "com.android.systemui.QSControlMiPlayDetailHeader$addObservers$4$2$1$1$isVisible$1", f = "QSControlMiPlayDetailHeader.kt", l = {}, m = "invokeSuspend")
public final class QSControlMiPlayDetailHeader$addObservers$4$2$1$1$isVisible$1 extends N0.l implements Function2 {
    final /* synthetic */ String $it;
    final /* synthetic */ QSControlMiPlayDetailHeader $this_run;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailHeader$addObservers$4$2$1$1$isVisible$1(QSControlMiPlayDetailHeader qSControlMiPlayDetailHeader, String str, L0.d dVar) {
        super(2, dVar);
        this.$this_run = qSControlMiPlayDetailHeader;
        this.$it = str;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new QSControlMiPlayDetailHeader$addObservers$4$2$1$1$isVisible$1(this.$this_run, this.$it, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(g1.E e2, L0.d dVar) {
        return ((QSControlMiPlayDetailHeader$addObservers$4$2$1$1$isVisible$1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        M0.c.c();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        H0.k.b(obj);
        QSControlMiPlayDetailHeader.Companion companion = QSControlMiPlayDetailHeader.Companion;
        Context context = this.$this_run.getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        return N0.b.a(companion.isInstalledAndVisible(context, this.$it));
    }
}
