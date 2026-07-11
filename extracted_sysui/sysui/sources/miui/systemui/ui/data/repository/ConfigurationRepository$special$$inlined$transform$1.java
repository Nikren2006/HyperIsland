package miui.systemui.ui.data.repository;

import H0.k;
import H0.s;
import M0.c;
import N0.d;
import N0.f;
import N0.l;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$transform$1", f = "ConfigurationRepository.kt", l = {40}, m = "invokeSuspend")
public final class ConfigurationRepository$special$$inlined$transform$1 extends l implements Function2 {
    final /* synthetic */ InterfaceC0418f $this_transform;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$transform$1$1, reason: invalid class name */
    public static final class AnonymousClass1<T> implements InterfaceC0419g {
        final /* synthetic */ InterfaceC0419g $$this$flow;

        /* JADX INFO: renamed from: miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$transform$1$1$1, reason: invalid class name and collision with other inner class name */
        @f(c = "miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$transform$1$1", f = "ConfigurationRepository.kt", l = {223}, m = "emit")
        public static final class C01531 extends d {
            int label;
            /* synthetic */ Object result;

            public C01531(L0.d dVar) {
                super(dVar);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass1.this.emit(null, this);
            }
        }

        public AnonymousClass1(InterfaceC0419g interfaceC0419g) {
            this.$$this$flow = interfaceC0419g;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        @Override // j1.InterfaceC0419g
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(T r5, L0.d r6) throws java.lang.Throwable {
            /*
                r4 = this;
                boolean r0 = r6 instanceof miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$transform$1.AnonymousClass1.C01531
                if (r0 == 0) goto L13
                r0 = r6
                miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$transform$1$1$1 r0 = (miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$transform$1.AnonymousClass1.C01531) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$transform$1$1$1 r0 = new miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$transform$1$1$1
                r0.<init>(r6)
            L18:
                java.lang.Object r6 = r0.result
                java.lang.Object r1 = M0.c.c()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L31
                if (r2 != r3) goto L29
                H0.k.b(r6)
                goto L4f
            L29:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L31:
                H0.k.b(r6)
                j1.g r4 = r4.$$this$flow
                java.lang.Number r5 = (java.lang.Number) r5
                int r5 = r5.intValue()
                miui.systemui.controlcenter.ConfigUtils r6 = miui.systemui.controlcenter.ConfigUtils.INSTANCE
                boolean r5 = r6.orientationChanged(r5)
                if (r5 == 0) goto L4f
                H0.s r5 = H0.s.f314a
                r0.label = r3
                java.lang.Object r4 = r4.emit(r5, r0)
                if (r4 != r1) goto L4f
                return r1
            L4f:
                H0.s r4 = H0.s.f314a
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$transform$1.AnonymousClass1.emit(java.lang.Object, L0.d):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConfigurationRepository$special$$inlined$transform$1(InterfaceC0418f interfaceC0418f, L0.d dVar) {
        super(2, dVar);
        this.$this_transform = interfaceC0418f;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        ConfigurationRepository$special$$inlined$transform$1 configurationRepository$special$$inlined$transform$1 = new ConfigurationRepository$special$$inlined$transform$1(this.$this_transform, dVar);
        configurationRepository$special$$inlined$transform$1.L$0 = obj;
        return configurationRepository$special$$inlined$transform$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(InterfaceC0419g interfaceC0419g, L0.d dVar) {
        return ((ConfigurationRepository$special$$inlined$transform$1) create(interfaceC0419g, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            InterfaceC0419g interfaceC0419g = (InterfaceC0419g) this.L$0;
            InterfaceC0418f interfaceC0418f = this.$this_transform;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(interfaceC0419g);
            this.label = 1;
            if (interfaceC0418f.collect(anonymousClass1, this) == objC) {
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
