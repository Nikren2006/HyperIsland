package miui.systemui.dynamicisland.touch.data.repository;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.content.res.Configuration;
import i1.q;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.autodensity.AutoDensityController;

/* JADX INFO: loaded from: classes3.dex */
@f(c = "miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$densityChanged$1", f = "DynamicIslandTouchConstantsRepository.kt", l = {33, 35}, m = "invokeSuspend")
public final class DynamicIslandTouchConstantsRepository$densityChanged$1 extends l implements Function2 {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DynamicIslandTouchConstantsRepository this$0;

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$densityChanged$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        final /* synthetic */ DynamicIslandTouchConstantsRepository$densityChanged$1$listener$1 $listener;
        final /* synthetic */ DynamicIslandTouchConstantsRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DynamicIslandTouchConstantsRepository dynamicIslandTouchConstantsRepository, DynamicIslandTouchConstantsRepository$densityChanged$1$listener$1 dynamicIslandTouchConstantsRepository$densityChanged$1$listener$1) {
            super(0);
            this.this$0 = dynamicIslandTouchConstantsRepository;
            this.$listener = dynamicIslandTouchConstantsRepository$densityChanged$1$listener$1;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m131invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m131invoke() {
            this.this$0.autoDensityController.removeOnDensityChangeListener(this.$listener);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandTouchConstantsRepository$densityChanged$1(DynamicIslandTouchConstantsRepository dynamicIslandTouchConstantsRepository, d dVar) {
        super(2, dVar);
        this.this$0 = dynamicIslandTouchConstantsRepository;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        DynamicIslandTouchConstantsRepository$densityChanged$1 dynamicIslandTouchConstantsRepository$densityChanged$1 = new DynamicIslandTouchConstantsRepository$densityChanged$1(this.this$0, dVar);
        dynamicIslandTouchConstantsRepository$densityChanged$1.L$0 = obj;
        return dynamicIslandTouchConstantsRepository$densityChanged$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(q qVar, d dVar) {
        return ((DynamicIslandTouchConstantsRepository$densityChanged$1) create(qVar, dVar)).invokeSuspend(s.f314a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        q qVar;
        DynamicIslandTouchConstantsRepository$densityChanged$1$listener$1 dynamicIslandTouchConstantsRepository$densityChanged$1$listener$1;
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            final q qVar2 = (q) this.L$0;
            AutoDensityController.OnDensityChangeListener onDensityChangeListener = new AutoDensityController.OnDensityChangeListener() { // from class: miui.systemui.dynamicisland.touch.data.repository.DynamicIslandTouchConstantsRepository$densityChanged$1$listener$1
                @Override // miui.systemui.autodensity.AutoDensityController.OnDensityChangeListener
                public void onConfigChanged(Configuration config) {
                    n.g(config, "config");
                    qVar2.j(s.f314a);
                }
            };
            s sVar = s.f314a;
            this.L$0 = qVar2;
            this.L$1 = onDensityChangeListener;
            this.label = 1;
            if (qVar2.b(sVar, this) == objC) {
                return objC;
            }
            qVar = qVar2;
            dynamicIslandTouchConstantsRepository$densityChanged$1$listener$1 = onDensityChangeListener;
        } else {
            if (i2 != 1) {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
                return s.f314a;
            }
            DynamicIslandTouchConstantsRepository$densityChanged$1$listener$1 dynamicIslandTouchConstantsRepository$densityChanged$1$listener$12 = (DynamicIslandTouchConstantsRepository$densityChanged$1$listener$1) this.L$1;
            qVar = (q) this.L$0;
            k.b(obj);
            dynamicIslandTouchConstantsRepository$densityChanged$1$listener$1 = dynamicIslandTouchConstantsRepository$densityChanged$1$listener$12;
        }
        this.this$0.autoDensityController.addOnDensityChangeListener(dynamicIslandTouchConstantsRepository$densityChanged$1$listener$1);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, dynamicIslandTouchConstantsRepository$densityChanged$1$listener$1);
        this.L$0 = null;
        this.L$1 = null;
        this.label = 2;
        if (i1.o.a(qVar, anonymousClass1, this) == objC) {
            return objC;
        }
        return s.f314a;
    }
}
