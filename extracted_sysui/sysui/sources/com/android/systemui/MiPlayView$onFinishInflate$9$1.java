package com.android.systemui;

import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "com.android.systemui.MiPlayView$onFinishInflate$9$1", f = "MiPlayView.kt", l = {191}, m = "invokeSuspend")
public final class MiPlayView$onFinishInflate$9$1 extends N0.l implements Function2 {
    final /* synthetic */ kotlin.jvm.internal.y $updateJob;
    int label;
    final /* synthetic */ MiPlayView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiPlayView$onFinishInflate$9$1(MiPlayView miPlayView, kotlin.jvm.internal.y yVar, L0.d dVar) {
        super(2, dVar);
        this.this$0 = miPlayView;
        this.$updateJob = yVar;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new MiPlayView$onFinishInflate$9$1(this.this$0, this.$updateJob, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(g1.E e2, L0.d dVar) {
        return ((MiPlayView$onFinishInflate$9$1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            H0.k.b(obj);
            long jCurrentTimeMillis = ((long) 2000) - (System.currentTimeMillis() - this.this$0.getPrevNextTouchTime());
            this.label = 1;
            if (g1.M.b(jCurrentTimeMillis, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
        }
        MiPlayView.onFinishInflate$updatePlayButton(this.this$0);
        this.$updateJob.f5059a = null;
        return H0.s.f314a;
    }
}
