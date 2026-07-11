package miui.systemui.controlcenter.data.repository;

import H0.s;
import L0.d;
import N0.f;
import N0.l;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function3;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miuix.view.HapticFeedbackConstants;

/* JADX INFO: loaded from: classes.dex */
@f(c = "miui.systemui.controlcenter.data.repository.StatusBarStateRepositoryImpl$special$$inlined$flatMapLatest$1", f = "StatusBarStateRepositoryImpl.kt", l = {HapticFeedbackConstants.MIUI_KEYBOARD_CLICKY_DOWN_RTP}, m = "invokeSuspend")
public final class StatusBarStateRepositoryImpl$special$$inlined$flatMapLatest$1 extends l implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    public StatusBarStateRepositoryImpl$special$$inlined$flatMapLatest$1(d dVar) {
        super(3, dVar);
    }

    public final Object invoke(InterfaceC0419g interfaceC0419g, ControlCenterWindowViewImpl controlCenterWindowViewImpl, d dVar) {
        StatusBarStateRepositoryImpl$special$$inlined$flatMapLatest$1 statusBarStateRepositoryImpl$special$$inlined$flatMapLatest$1 = new StatusBarStateRepositoryImpl$special$$inlined$flatMapLatest$1(dVar);
        statusBarStateRepositoryImpl$special$$inlined$flatMapLatest$1.L$0 = interfaceC0419g;
        statusBarStateRepositoryImpl$special$$inlined$flatMapLatest$1.L$1 = controlCenterWindowViewImpl;
        return statusBarStateRepositoryImpl$special$$inlined$flatMapLatest$1.invokeSuspend(s.f314a);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0045 A[RETURN] */
    @Override // N0.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r4) throws java.lang.Throwable {
        /*
            r3 = this;
            java.lang.Object r0 = M0.c.c()
            int r1 = r3.label
            r2 = 1
            if (r1 == 0) goto L17
            if (r1 != r2) goto Lf
            H0.k.b(r4)
            goto L46
        Lf:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r4)
            throw r3
        L17:
            H0.k.b(r4)
            java.lang.Object r4 = r3.L$0
            j1.g r4 = (j1.InterfaceC0419g) r4
            java.lang.Object r1 = r3.L$1
            miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl r1 = (miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl) r1
            if (r1 == 0) goto L39
            miui.systemui.controlcenter.dagger.ControlCenterViewComponent r1 = r1.getViewComponent()
            if (r1 == 0) goto L39
            com.android.systemui.plugins.statusbar.StatusBarStateController r1 = r1.getStatusBarStateController()
            if (r1 == 0) goto L39
            kotlin.jvm.internal.n.d(r1)
            j1.f r1 = miui.systemui.statusbar.data.repository.StatusBarStateRepositoryKt.getStatusBarState(r1)
            if (r1 != 0) goto L3d
        L39:
            j1.f r1 = j1.AbstractC0420h.r()
        L3d:
            r3.label = r2
            java.lang.Object r3 = j1.AbstractC0420h.q(r4, r1, r3)
            if (r3 != r0) goto L46
            return r0
        L46:
            H0.s r3 = H0.s.f314a
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.data.repository.StatusBarStateRepositoryImpl$special$$inlined$flatMapLatest$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((InterfaceC0419g) obj, (ControlCenterWindowViewImpl) obj2, (d) obj3);
    }
}
