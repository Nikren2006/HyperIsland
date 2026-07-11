package miui.systemui.dynamicisland.window;

import H0.s;
import android.util.Log;
import i1.t;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import miui.app.IFreeformCallback;
import miui.app.MiuiFreeFormManager;

/* JADX INFO: loaded from: classes3.dex */
@N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandDesktopStateController$freeFormStackMode$1", f = "DynamicIslandDesktopStateController.kt", l = {111}, m = "invokeSuspend")
public final class DynamicIslandDesktopStateController$freeFormStackMode$1 extends N0.l implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DynamicIslandDesktopStateController this$0;

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandDesktopStateController$freeFormStackMode$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function0 {
        final /* synthetic */ DynamicIslandDesktopStateController$freeFormStackMode$1$callback$1 $callback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DynamicIslandDesktopStateController$freeFormStackMode$1$callback$1 dynamicIslandDesktopStateController$freeFormStackMode$1$callback$1) {
            super(0);
            this.$callback = dynamicIslandDesktopStateController$freeFormStackMode$1$callback$1;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m132invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m132invoke() {
            MiuiFreeFormManager.unregisterFreeformCallback(this.$callback);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandDesktopStateController$freeFormStackMode$1(DynamicIslandDesktopStateController dynamicIslandDesktopStateController, L0.d dVar) {
        super(2, dVar);
        this.this$0 = dynamicIslandDesktopStateController;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        DynamicIslandDesktopStateController$freeFormStackMode$1 dynamicIslandDesktopStateController$freeFormStackMode$1 = new DynamicIslandDesktopStateController$freeFormStackMode$1(this.this$0, dVar);
        dynamicIslandDesktopStateController$freeFormStackMode$1.L$0 = obj;
        return dynamicIslandDesktopStateController$freeFormStackMode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(i1.q qVar, L0.d dVar) {
        return ((DynamicIslandDesktopStateController$freeFormStackMode$1) create(qVar, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            H0.k.b(obj);
            final i1.q qVar = (i1.q) this.L$0;
            if (MiuiFreeFormManager.getMiuiFreeformVersion() != 3) {
                this.this$0.getMiniWindowDataRepo().setSupport(false);
                t.a.a(qVar, null, 1, null);
                return s.f314a;
            }
            final DynamicIslandDesktopStateController dynamicIslandDesktopStateController = this.this$0;
            IFreeformCallback iFreeformCallback = new IFreeformCallback.Stub() { // from class: miui.systemui.dynamicisland.window.DynamicIslandDesktopStateController$freeFormStackMode$1$callback$1
                public void dispatchFreeFormStackModeChanged(int i3, MiuiFreeFormManager.MiuiFreeFormStackInfo stackInfo) {
                    kotlin.jvm.internal.n.g(stackInfo, "stackInfo");
                    qVar.j(Integer.valueOf(i3));
                    Log.d(dynamicIslandDesktopStateController.TAG, "dispatchFreeFormStackModeChanged action:" + i3);
                    if (i3 == 2 || i3 == 4) {
                        return;
                    }
                    dynamicIslandDesktopStateController.getMiniWindowDataRepo().updateDataInfo(stackInfo);
                    dynamicIslandDesktopStateController.exitFreeFrom(stackInfo);
                }
            };
            this.this$0.getMiniWindowDataRepo().setSupport(true);
            MiuiFreeFormManager.registerFreeformCallback(iFreeformCallback);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(iFreeformCallback);
            this.label = 1;
            if (i1.o.a(qVar, anonymousClass1, this) == objC) {
                return objC;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            H0.k.b(obj);
        }
        return s.f314a;
    }
}
