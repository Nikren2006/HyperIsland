package com.android.systemui;

import com.android.systemui.QSControlMiPlayDetailContent;
import java.util.ArrayList;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "com.android.systemui.QSControlMiPlayDetailContent$mHeader$2$1$1$onSizeChanged$1", f = "QSControlMiPlayDetailContent.kt", l = {97}, m = "invokeSuspend")
public final class QSControlMiPlayDetailContent$mHeader$2$1$1$onSizeChanged$1 extends N0.l implements Function2 {
    int label;
    final /* synthetic */ QSControlMiPlayDetailContent this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContent$mHeader$2$1$1$onSizeChanged$1(QSControlMiPlayDetailContent qSControlMiPlayDetailContent, L0.d dVar) {
        super(2, dVar);
        this.this$0 = qSControlMiPlayDetailContent;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new QSControlMiPlayDetailContent$mHeader$2$1$1$onSizeChanged$1(this.this$0, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(g1.E e2, L0.d dVar) {
        return ((QSControlMiPlayDetailContent$mHeader$2$1$1$onSizeChanged$1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
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
        QSControlMiPlayDetailContent qSControlMiPlayDetailContent = this.this$0;
        ArrayList<QSControlMiPlayDetailContent.ListItem> mListItems = qSControlMiPlayDetailContent.getMListItems();
        kotlin.jvm.internal.n.d(mListItems);
        QSControlMiPlayDetailContent.updateHeight$default(qSControlMiPlayDetailContent, mListItems, false, false, 4, null);
        return H0.s.f314a;
    }
}
