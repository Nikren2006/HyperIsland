package miui.systemui.dynamicisland;

import A0.a;
import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.os.SystemClock;
import g1.AbstractC0360b0;
import g1.AbstractC0367f;
import g1.AbstractC0369g;
import g1.E;
import kotlin.jvm.functions.Function2;
import miui.systemui.coroutines.Dispatchers;
import miui.systemui.util.concurrency.ConcurrencyModule;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandScenarioUtils {
    public static final DynamicIslandScenarioUtils INSTANCE = new DynamicIslandScenarioUtils();
    public static final long ISLAND_CLOSE_APP_WITH_EXPAND_ANIMATION = 359;
    public static final long ISLAND_CLOSE_APP_WITH_FREEFORM_ANIMATION = 361;
    public static final long ISLAND_OPEN_APP_WITH_EXPAND_ANIMATION = 358;
    public static final long ISLAND_OPEN_APP_WITH_FREEFORM_ANIMATION = 360;
    public static final long ISLAND_STATE_CHANGE_ANIMATION = 357;

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.DynamicIslandScenarioUtils$setDynamicIslandScenarioState$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.DynamicIslandScenarioUtils$setDynamicIslandScenarioState$1", f = "DynamicIslandScenarioUtils.kt", l = {21}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ boolean $isStart;
        final /* synthetic */ long $time;
        final /* synthetic */ long $type;
        int label;

        /* JADX INFO: renamed from: miui.systemui.dynamicisland.DynamicIslandScenarioUtils$setDynamicIslandScenarioState$1$1, reason: invalid class name and collision with other inner class name */
        @f(c = "miui.systemui.dynamicisland.DynamicIslandScenarioUtils$setDynamicIslandScenarioState$1$1", f = "DynamicIslandScenarioUtils.kt", l = {}, m = "invokeSuspend")
        public static final class C01121 extends l implements Function2 {
            final /* synthetic */ boolean $isStart;
            final /* synthetic */ long $time;
            final /* synthetic */ long $type;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01121(long j2, long j3, boolean z2, d dVar) {
                super(2, dVar);
                this.$type = j2;
                this.$time = j3;
                this.$isStart = z2;
            }

            @Override // N0.a
            public final d create(Object obj, d dVar) {
                return new C01121(this.$type, this.$time, this.$isStart, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(E e2, d dVar) {
                return ((C01121) create(e2, dVar)).invokeSuspend(s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                c.c();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
                a.a().b(this.$type, this.$time, this.$isStart);
                return s.f314a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(long j2, long j3, boolean z2, d dVar) {
            super(2, dVar);
            this.$type = j2;
            this.$time = j3;
            this.$isStart = z2;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return new AnonymousClass1(this.$type, this.$time, this.$isStart, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                AbstractC0360b0 io = Dispatchers.INSTANCE.getIO();
                C01121 c01121 = new C01121(this.$type, this.$time, this.$isStart, null);
                this.label = 1;
                if (AbstractC0367f.c(io, c01121, this) == objC) {
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

    private DynamicIslandScenarioUtils() {
    }

    public static /* synthetic */ void setDynamicIslandScenarioState$default(DynamicIslandScenarioUtils dynamicIslandScenarioUtils, long j2, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        dynamicIslandScenarioUtils.setDynamicIslandScenarioState(j2, z2);
    }

    public final void setDynamicIslandScenarioState(long j2, boolean z2) {
        if (j2 > 0) {
            AbstractC0369g.b(ConcurrencyModule.INSTANCE.getUiScope(), null, null, new AnonymousClass1(j2, SystemClock.elapsedRealtime(), z2, null), 3, null);
        }
    }
}
