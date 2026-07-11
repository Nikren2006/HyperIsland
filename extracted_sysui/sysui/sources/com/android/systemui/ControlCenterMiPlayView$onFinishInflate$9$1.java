package com.android.systemui;

import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "com.android.systemui.ControlCenterMiPlayView$onFinishInflate$9$1", f = "ControlCenterMiPlayView.kt", l = {158}, m = "invokeSuspend")
public final class ControlCenterMiPlayView$onFinishInflate$9$1 extends N0.l implements Function2 {
    final /* synthetic */ kotlin.jvm.internal.y $updateJob;
    int label;
    final /* synthetic */ ControlCenterMiPlayView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlCenterMiPlayView$onFinishInflate$9$1(ControlCenterMiPlayView controlCenterMiPlayView, kotlin.jvm.internal.y yVar, L0.d dVar) {
        super(2, dVar);
        this.this$0 = controlCenterMiPlayView;
        this.$updateJob = yVar;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        return new ControlCenterMiPlayView$onFinishInflate$9$1(this.this$0, this.$updateJob, dVar);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(g1.E e2, L0.d dVar) {
        return ((ControlCenterMiPlayView$onFinishInflate$9$1) create(e2, dVar)).invokeSuspend(H0.s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            H0.k.b(obj);
            long prev_next_touch_interval = ((long) this.this$0.getPREV_NEXT_TOUCH_INTERVAL()) - (System.currentTimeMillis() - this.this$0.getPrevNextTouchTime());
            this.label = 1;
            if (g1.M.b(prev_next_touch_interval, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
        }
        ControlCenterMiPlayView.onFinishInflate$updatePlayButton(this.this$0);
        this.$updateJob.f5059a = null;
        return H0.s.f314a;
    }
}
