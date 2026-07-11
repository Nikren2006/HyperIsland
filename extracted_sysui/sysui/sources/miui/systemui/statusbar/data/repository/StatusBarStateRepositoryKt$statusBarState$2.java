package miui.systemui.statusbar.data.repository;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.b;
import N0.f;
import N0.l;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.statusbar.data.repository.StatusBarStateRepositoryKt$statusBarState$2", f = "StatusBarStateRepository.kt", l = {21}, m = "invokeSuspend")
public final class StatusBarStateRepositoryKt$statusBarState$2 extends l implements Function2 {
    final /* synthetic */ StatusBarStateController $this_statusBarState;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatusBarStateRepositoryKt$statusBarState$2(StatusBarStateController statusBarStateController, d dVar) {
        super(2, dVar);
        this.$this_statusBarState = statusBarStateController;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        StatusBarStateRepositoryKt$statusBarState$2 statusBarStateRepositoryKt$statusBarState$2 = new StatusBarStateRepositoryKt$statusBarState$2(this.$this_statusBarState, dVar);
        statusBarStateRepositoryKt$statusBarState$2.L$0 = obj;
        return statusBarStateRepositoryKt$statusBarState$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(InterfaceC0419g interfaceC0419g, d dVar) {
        return ((StatusBarStateRepositoryKt$statusBarState$2) create(interfaceC0419g, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            InterfaceC0419g interfaceC0419g = (InterfaceC0419g) this.L$0;
            Integer numC = b.c(this.$this_statusBarState.getState());
            this.label = 1;
            if (interfaceC0419g.emit(numC, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
        }
        return s.f314a;
    }
}
