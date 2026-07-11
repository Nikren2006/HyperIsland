package miui.systemui.statusbar.data.repository;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import i1.q;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.statusbar.data.repository.StatusBarStateRepositoryKt$statusBarState$1", f = "StatusBarStateRepository.kt", l = {20}, m = "invokeSuspend")
public final class StatusBarStateRepositoryKt$statusBarState$1 extends l implements Function2 {
    final /* synthetic */ StatusBarStateController $this_statusBarState;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: miui.systemui.statusbar.data.repository.StatusBarStateRepositoryKt$statusBarState$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        final /* synthetic */ StatusBarStateRepositoryKt$statusBarState$1$callback$1 $callback;
        final /* synthetic */ StatusBarStateController $this_statusBarState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(StatusBarStateController statusBarStateController, StatusBarStateRepositoryKt$statusBarState$1$callback$1 statusBarStateRepositoryKt$statusBarState$1$callback$1) {
            super(0);
            this.$this_statusBarState = statusBarStateController;
            this.$callback = statusBarStateRepositoryKt$statusBarState$1$callback$1;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m153invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m153invoke() {
            this.$this_statusBarState.removeCallback(this.$callback);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatusBarStateRepositoryKt$statusBarState$1(StatusBarStateController statusBarStateController, d dVar) {
        super(2, dVar);
        this.$this_statusBarState = statusBarStateController;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        StatusBarStateRepositoryKt$statusBarState$1 statusBarStateRepositoryKt$statusBarState$1 = new StatusBarStateRepositoryKt$statusBarState$1(this.$this_statusBarState, dVar);
        statusBarStateRepositoryKt$statusBarState$1.L$0 = obj;
        return statusBarStateRepositoryKt$statusBarState$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(q qVar, d dVar) {
        return ((StatusBarStateRepositoryKt$statusBarState$1) create(qVar, dVar)).invokeSuspend(s.f314a);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.plugins.statusbar.StatusBarStateController$StateListener, miui.systemui.statusbar.data.repository.StatusBarStateRepositoryKt$statusBarState$1$callback$1] */
    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            final q qVar = (q) this.L$0;
            ?? r12 = new StatusBarStateController.StateListener() { // from class: miui.systemui.statusbar.data.repository.StatusBarStateRepositoryKt$statusBarState$1$callback$1
                public void onStateChanged(int i3) {
                    qVar.j(Integer.valueOf(i3));
                }
            };
            this.$this_statusBarState.addCallback((StatusBarStateController.StateListener) r12);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_statusBarState, r12);
            this.label = 1;
            if (i1.o.a(qVar, anonymousClass1, this) == objC) {
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
