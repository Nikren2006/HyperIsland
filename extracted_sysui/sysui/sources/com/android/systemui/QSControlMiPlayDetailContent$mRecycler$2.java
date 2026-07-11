package com.android.systemui;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.miplay.R;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: classes.dex */
public final class QSControlMiPlayDetailContent$mRecycler$2 extends kotlin.jvm.internal.o implements Function0 {
    final /* synthetic */ QSControlMiPlayDetailContent this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSControlMiPlayDetailContent$mRecycler$2(QSControlMiPlayDetailContent qSControlMiPlayDetailContent) {
        super(0);
        this.this$0 = qSControlMiPlayDetailContent;
    }

    @Override // kotlin.jvm.functions.Function0
    public final RecyclerView invoke() {
        View viewRequireViewById = this.this$0.requireViewById(R.id.list);
        kotlin.jvm.internal.n.e(viewRequireViewById, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        return (RecyclerView) viewRequireViewById;
    }
}
