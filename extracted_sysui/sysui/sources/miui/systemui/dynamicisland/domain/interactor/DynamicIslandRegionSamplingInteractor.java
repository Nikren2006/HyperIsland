package miui.systemui.dynamicisland.domain.interactor;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import V0.o;
import android.content.Context;
import android.graphics.Rect;
import android.os.SystemProperties;
import android.util.Log;
import g1.AbstractC0369g;
import g1.E;
import j1.AbstractC0420h;
import j1.E;
import j1.I;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.data.repository.ControlCenterExpandRepository;
import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.data.repository.DynamicIslandExternalStateRepository;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowStateInteractor;
import miui.systemui.handles.RegionSamplingHelperRefactor;
import miui.systemui.statusbar.data.repository.StatusBarAreaRepository;
import miui.systemui.statusbar.data.repository.StatusBarStateRepository;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandRegionSamplingInteractor implements DynamicIslandStartable {
    private static final String TAG = "DynamicIslandRegionSamplingInteractor";
    private final Context context;
    private final ControlCenterExpandRepository controlCenterExpandRepository;
    private final DynamicIslandExternalStateRepository externalStateRepository;
    private final I isRegionDark;
    private final I medianLuma;
    private final I regionSampling;
    private final RegionSamplingHelperRefactor regionSamplingHelper;
    private final E scope;
    private final StatusBarAreaRepository statusBarAreaRepository;
    private final StatusBarStateRepository statusBarStateRepository;
    private final DynamicIslandWindowStateInteractor windowStateInteractor;
    public static final Companion Companion = new Companion(null);
    private static final boolean DEBUG = SystemProperties.getBoolean("debug.sysui.notif.island.region_sampling", false);
    private static final float THRESHOLD = SystemProperties.getInt("debug.sysui.notif.island.region_sampling.threshold", 12) / 100.0f;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$isRegionDark$2, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$isRegionDark$2", f = "DynamicIslandRegionSamplingInteractor.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends l implements o {
        /* synthetic */ int I$0;
        /* synthetic */ boolean Z$0;
        /* synthetic */ boolean Z$1;
        /* synthetic */ boolean Z$2;
        int label;

        public AnonymousClass2(d dVar) {
            super(5, dVar);
        }

        @Override // V0.o
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue(), ((Boolean) obj3).booleanValue(), ((Number) obj4).intValue(), (d) obj5);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x001c  */
        @Override // N0.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r3) throws java.lang.Throwable {
            /*
                r2 = this;
                M0.c.c()
                int r0 = r2.label
                if (r0 != 0) goto L22
                H0.k.b(r3)
                boolean r3 = r2.Z$0
                boolean r0 = r2.Z$1
                boolean r1 = r2.Z$2
                int r2 = r2.I$0
                if (r3 == 0) goto L1c
                if (r0 != 0) goto L1c
                r3 = 1
                if (r1 == 0) goto L1d
                if (r2 != r3) goto L1c
                goto L1d
            L1c:
                r3 = 0
            L1d:
                java.lang.Boolean r2 = N0.b.a(r3)
                return r2
            L22:
                java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
                java.lang.String r3 = "call to 'resume' before 'invoke' with coroutine"
                r2.<init>(r3)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        public final Object invoke(boolean z2, boolean z3, boolean z4, int i2, d dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar);
            anonymousClass2.Z$0 = z2;
            anonymousClass2.Z$1 = z3;
            anonymousClass2.Z$2 = z4;
            anonymousClass2.I$0 = i2;
            return anonymousClass2.invokeSuspend(s.f314a);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$listenForRegionSampling$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$listenForRegionSampling$1", f = "DynamicIslandRegionSamplingInteractor.kt", l = {89}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        int label;

        public AnonymousClass1(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandRegionSamplingInteractor.this.new AnonymousClass1(dVar);
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
                I i3 = DynamicIslandRegionSamplingInteractor.this.regionSampling;
                final DynamicIslandRegionSamplingInteractor dynamicIslandRegionSamplingInteractor = DynamicIslandRegionSamplingInteractor.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor.listenForRegionSampling.1.1
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, d dVar) {
                        if (DynamicIslandRegionSamplingInteractor.DEBUG) {
                            Log.d(DynamicIslandRegionSamplingInteractor.TAG, "region sampling changed to " + z2);
                        }
                        dynamicIslandRegionSamplingInteractor.regionSamplingHelper.setRegionSampling(z2);
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (i3.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$listenForSamplingAreaChanged$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$listenForSamplingAreaChanged$1", f = "DynamicIslandRegionSamplingInteractor.kt", l = {80}, m = "invokeSuspend")
    public static final class C06031 extends l implements Function2 {
        int label;

        public C06031(d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandRegionSamplingInteractor.this.new C06031(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C06031) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                I statusBarArea = DynamicIslandRegionSamplingInteractor.this.statusBarAreaRepository.getStatusBarArea();
                final DynamicIslandRegionSamplingInteractor dynamicIslandRegionSamplingInteractor = DynamicIslandRegionSamplingInteractor.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor.listenForSamplingAreaChanged.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(Rect rect, d dVar) {
                        if (DynamicIslandRegionSamplingInteractor.DEBUG) {
                            Log.d(DynamicIslandRegionSamplingInteractor.TAG, "sampling area changed to " + rect);
                        }
                        dynamicIslandRegionSamplingInteractor.regionSamplingHelper.updateSamplingRect();
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (statusBarArea.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k.b(obj);
            }
            throw new H0.c();
        }
    }

    public DynamicIslandRegionSamplingInteractor(@DynamicIsland E scope, DynamicIslandWindowView windowView, Context context, DynamicIslandWindowStateInteractor windowStateInteractor, RegionSamplingHelperRefactor.Factory regionSamplingHelperFactory, StatusBarAreaRepository statusBarAreaRepository, StatusBarStateRepository statusBarStateRepository, ControlCenterExpandRepository controlCenterExpandRepository, DynamicIslandExternalStateRepository externalStateRepository) {
        n.g(scope, "scope");
        n.g(windowView, "windowView");
        n.g(context, "context");
        n.g(windowStateInteractor, "windowStateInteractor");
        n.g(regionSamplingHelperFactory, "regionSamplingHelperFactory");
        n.g(statusBarAreaRepository, "statusBarAreaRepository");
        n.g(statusBarStateRepository, "statusBarStateRepository");
        n.g(controlCenterExpandRepository, "controlCenterExpandRepository");
        n.g(externalStateRepository, "externalStateRepository");
        this.scope = scope;
        this.context = context;
        this.windowStateInteractor = windowStateInteractor;
        this.statusBarAreaRepository = statusBarAreaRepository;
        this.statusBarStateRepository = statusBarStateRepository;
        this.controlCenterExpandRepository = controlCenterExpandRepository;
        this.externalStateRepository = externalStateRepository;
        RegionSamplingHelperRefactor regionSamplingHelperRefactorCreate = regionSamplingHelperFactory.create(windowView, new DynamicIslandRegionSamplingInteractor$regionSamplingHelper$1(this));
        this.regionSamplingHelper = regionSamplingHelperRefactorCreate;
        InterfaceC0418f interfaceC0418fK = AbstractC0420h.k(windowStateInteractor.getVisible(), controlCenterExpandRepository.isVisible(), externalStateRepository.getNotificationVisible(), statusBarStateRepository.getState(), windowStateInteractor.isAppAnimRunning(), new DynamicIslandRegionSamplingInteractor$regionSampling$1(null));
        E.a aVar = j1.E.f4648a;
        j1.E eC = aVar.c();
        Boolean bool = Boolean.FALSE;
        this.regionSampling = AbstractC0420h.B(interfaceC0418fK, scope, eC, bool);
        final I medianLuma = regionSamplingHelperRefactorCreate.getMedianLuma();
        this.medianLuma = medianLuma;
        this.isRegionDark = AbstractC0420h.B(AbstractC0420h.j(new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$special$$inlined$map$1

            /* JADX INFO: renamed from: miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements InterfaceC0419g {
                final /* synthetic */ InterfaceC0419g $this_unsafeFlow;

                /* JADX INFO: renamed from: miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
                @f(c = "miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$special$$inlined$map$1$2", f = "DynamicIslandRegionSamplingInteractor.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends N0.d {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(d dVar) {
                        super(dVar);
                    }

                    @Override // N0.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(InterfaceC0419g interfaceC0419g) {
                    this.$this_unsafeFlow = interfaceC0419g;
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
                        boolean r0 = r6 instanceof miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$special$$inlined$map$1$2$1 r0 = (miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$special$$inlined$map$1$2$1 r0 = new miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = M0.c.c()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        H0.k.b(r6)
                        goto L54
                    L29:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L31:
                        H0.k.b(r6)
                        j1.g r4 = r4.$this_unsafeFlow
                        java.lang.Number r5 = (java.lang.Number) r5
                        float r5 = r5.floatValue()
                        float r6 = miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor.access$getTHRESHOLD$cp()
                        int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                        if (r5 >= 0) goto L46
                        r5 = r3
                        goto L47
                    L46:
                        r5 = 0
                    L47:
                        java.lang.Boolean r5 = N0.b.a(r5)
                        r0.label = r3
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L54
                        return r1
                    L54:
                        H0.s r4 = H0.s.f314a
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.domain.interactor.DynamicIslandRegionSamplingInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                }
            }

            @Override // j1.InterfaceC0418f
            public Object collect(InterfaceC0419g interfaceC0419g, d dVar) {
                Object objCollect = medianLuma.collect(new AnonymousClass2(interfaceC0419g), dVar);
                return objCollect == c.c() ? objCollect : s.f314a;
            }
        }, controlCenterExpandRepository.getAppearance(), externalStateRepository.getNotificationAppearance(), statusBarStateRepository.getState(), new AnonymousClass2(null)), scope, E.a.b(aVar, 0L, 0L, 3, null), bool);
    }

    private final void listenForRegionSampling() {
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(null), 3, null);
    }

    private final void listenForSamplingAreaChanged() {
        AbstractC0369g.b(this.scope, null, null, new C06031(null), 3, null);
    }

    private final void logStatesChanged() {
        if (DEBUG) {
            g1.E e2 = this.scope;
            AbstractC0369g.b(e2, null, null, new DynamicIslandRegionSamplingInteractor$logStatesChanged$1$1(this, null), 3, null);
            AbstractC0369g.b(e2, null, null, new DynamicIslandRegionSamplingInteractor$logStatesChanged$1$2(this, null), 3, null);
        }
    }

    public final I getMedianLuma() {
        return this.medianLuma;
    }

    public final I isRegionDark() {
        return this.isRegionDark;
    }

    @Override // miui.systemui.dynamicisland.DynamicIslandStartable
    public void start() {
        listenForSamplingAreaChanged();
        listenForRegionSampling();
        logStatesChanged();
    }
}
