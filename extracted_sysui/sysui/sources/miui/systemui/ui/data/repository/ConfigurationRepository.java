package miui.systemui.ui.data.repository;

import H0.s;
import M0.c;
import N0.d;
import N0.f;
import android.content.Context;
import android.content.res.Configuration;
import g1.E;
import j1.AbstractC0420h;
import j1.E;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import j1.r;
import j1.y;
import kotlin.jvm.internal.n;
import miui.systemui.autodensity.AutoDensityController;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.util.coroutines.flow.FlowConflatedKt;

/* JADX INFO: loaded from: classes4.dex */
public final class ConfigurationRepository {
    private final AutoDensityController autoDensityController;
    private final y configChanges;
    private final y configuration;
    private final Context context;
    private final y onConfigChanged;
    private final y orientationChanged;
    private final E scope;

    public ConfigurationRepository(@Plugin E scope, Context context, AutoDensityController autoDensityController) {
        n.g(scope, "scope");
        n.g(context, "context");
        n.g(autoDensityController, "autoDensityController");
        this.scope = scope;
        this.context = context;
        this.autoDensityController = autoDensityController;
        InterfaceC0418f interfaceC0418fConflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new ConfigurationRepository$onConfigChanged$1(this, null));
        E.a aVar = j1.E.f4648a;
        y yVarF = r.f(interfaceC0418fConflatedCallbackFlow, scope, E.a.b(aVar, 0L, 0L, 3, null), 0, 4, null);
        this.onConfigChanged = yVarF;
        final InterfaceC0418f interfaceC0418fY = AbstractC0420h.y(yVarF, new ConfigurationRepository$configuration$1(null));
        final y yVarZ = AbstractC0420h.z(new InterfaceC0418f() { // from class: miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$map$1

            /* JADX INFO: renamed from: miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements InterfaceC0419g {
                final /* synthetic */ InterfaceC0419g $this_unsafeFlow;
                final /* synthetic */ ConfigurationRepository this$0;

                /* JADX INFO: renamed from: miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$map$1$2$1, reason: invalid class name */
                @f(c = "miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$map$1$2", f = "ConfigurationRepository.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends d {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(L0.d dVar) {
                        super(dVar);
                    }

                    @Override // N0.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(InterfaceC0419g interfaceC0419g, ConfigurationRepository configurationRepository) {
                    this.$this_unsafeFlow = interfaceC0419g;
                    this.this$0 = configurationRepository;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // j1.InterfaceC0419g
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, L0.d r6) throws java.lang.Throwable {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$map$1$2$1 r0 = (miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$map$1$2$1 r0 = new miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$map$1$2$1
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
                        j1.g r6 = r4.$this_unsafeFlow
                        H0.s r5 = (H0.s) r5
                        miui.systemui.ui.data.repository.ConfigurationRepository r4 = r4.this$0
                        android.content.Context r4 = miui.systemui.ui.data.repository.ConfigurationRepository.access$getContext$p(r4)
                        android.content.res.Resources r4 = r4.getResources()
                        android.content.res.Configuration r4 = r4.getConfiguration()
                        r0.label = r3
                        java.lang.Object r4 = r6.emit(r4, r0)
                        if (r4 != r1) goto L4f
                        return r1
                    L4f:
                        H0.s r4 = H0.s.f314a
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: miui.systemui.ui.data.repository.ConfigurationRepository$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                }
            }

            @Override // j1.InterfaceC0418f
            public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
                Object objCollect = interfaceC0418fY.collect(new AnonymousClass2(interfaceC0419g, this), dVar);
                return objCollect == c.c() ? objCollect : s.f314a;
            }
        }, scope, E.a.b(aVar, 0L, 0L, 3, null), 1);
        this.configuration = yVarZ;
        final Configuration configuration = new Configuration(context.getResources().getConfiguration());
        y yVarF2 = r.f(new InterfaceC0418f() { // from class: miui.systemui.ui.data.repository.ConfigurationRepository$configChanges$lambda$2$$inlined$map$1

            /* JADX INFO: renamed from: miui.systemui.ui.data.repository.ConfigurationRepository$configChanges$lambda$2$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements InterfaceC0419g {
                final /* synthetic */ Configuration $config$inlined;
                final /* synthetic */ InterfaceC0419g $this_unsafeFlow;

                /* JADX INFO: renamed from: miui.systemui.ui.data.repository.ConfigurationRepository$configChanges$lambda$2$$inlined$map$1$2$1, reason: invalid class name */
                @f(c = "miui.systemui.ui.data.repository.ConfigurationRepository$configChanges$lambda$2$$inlined$map$1$2", f = "ConfigurationRepository.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends d {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(L0.d dVar) {
                        super(dVar);
                    }

                    @Override // N0.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(InterfaceC0419g interfaceC0419g, Configuration configuration) {
                    this.$this_unsafeFlow = interfaceC0419g;
                    this.$config$inlined = configuration;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // j1.InterfaceC0419g
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, L0.d r6) throws java.lang.Throwable {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof miui.systemui.ui.data.repository.ConfigurationRepository$configChanges$lambda$2$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        miui.systemui.ui.data.repository.ConfigurationRepository$configChanges$lambda$2$$inlined$map$1$2$1 r0 = (miui.systemui.ui.data.repository.ConfigurationRepository$configChanges$lambda$2$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        miui.systemui.ui.data.repository.ConfigurationRepository$configChanges$lambda$2$$inlined$map$1$2$1 r0 = new miui.systemui.ui.data.repository.ConfigurationRepository$configChanges$lambda$2$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = M0.c.c()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        H0.k.b(r6)
                        goto L50
                    L29:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L31:
                        H0.k.b(r6)
                        j1.g r6 = r4.$this_unsafeFlow
                        android.content.res.Configuration r5 = (android.content.res.Configuration) r5
                        miui.systemui.controlcenter.ConfigUtils r2 = miui.systemui.controlcenter.ConfigUtils.INSTANCE
                        android.content.res.Configuration r4 = r4.$config$inlined
                        kotlin.jvm.internal.n.d(r5)
                        int r4 = r2.update(r4, r5)
                        java.lang.Integer r4 = N0.b.c(r4)
                        r0.label = r3
                        java.lang.Object r4 = r6.emit(r4, r0)
                        if (r4 != r1) goto L50
                        return r1
                    L50:
                        H0.s r4 = H0.s.f314a
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: miui.systemui.ui.data.repository.ConfigurationRepository$configChanges$lambda$2$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                }
            }

            @Override // j1.InterfaceC0418f
            public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
                Object objCollect = yVarZ.collect(new AnonymousClass2(interfaceC0419g, configuration), dVar);
                return objCollect == c.c() ? objCollect : s.f314a;
            }
        }, scope, E.a.b(aVar, 0L, 0L, 3, null), 0, 4, null);
        this.configChanges = yVarF2;
        this.orientationChanged = r.f(AbstractC0420h.u(new ConfigurationRepository$special$$inlined$transform$1(yVarF2, null)), scope, E.a.b(aVar, 0L, 0L, 3, null), 0, 4, null);
    }

    public final y getConfigChanges() {
        return this.configChanges;
    }

    public final y getConfiguration() {
        return this.configuration;
    }

    public final y getOnConfigChanged() {
        return this.onConfigChanged;
    }

    public final y getOrientationChanged() {
        return this.orientationChanged;
    }
}
