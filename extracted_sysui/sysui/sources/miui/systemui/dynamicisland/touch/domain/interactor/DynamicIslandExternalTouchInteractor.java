package miui.systemui.dynamicisland.touch.domain.interactor;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.content.Context;
import android.view.MotionEvent;
import g1.AbstractC0369g;
import g1.E;
import j1.A;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import j1.t;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.touch.TouchEvent;
import miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandExternalTouchInteractor {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DynamicIslandExternalTouchHandler";
    private final t _externalInterceptTouchEvent;
    private final t _externalTouchEvent;
    private final Context context;
    private boolean downInTouchRegion;
    private final InterfaceC0418f externalInterceptTouchEvent;
    private final InterfaceC0418f externalTouchEvent;
    private final E scope;
    private final Context sysUIContext;
    private final DynamicIslandTouchRegionInteractor touchRegionInteractor;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$handleExternalIntercept$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$handleExternalIntercept$1", f = "DynamicIslandExternalTouchInteractor.kt", l = {55}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ TouchEvent $touchEvent;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(TouchEvent touchEvent, d dVar) {
            super(2, dVar);
            this.$touchEvent = touchEvent;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandExternalTouchInteractor.this.new AnonymousClass1(this.$touchEvent, dVar);
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
                t tVar = DynamicIslandExternalTouchInteractor.this._externalInterceptTouchEvent;
                TouchEvent touchEvent = this.$touchEvent;
                this.label = 1;
                if (tVar.emit(touchEvent, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$handleExternalTouch$1, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$handleExternalTouch$1", f = "DynamicIslandExternalTouchInteractor.kt", l = {61}, m = "invokeSuspend")
    public static final class C06301 extends l implements Function2 {
        final /* synthetic */ TouchEvent $touchEvent;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06301(TouchEvent touchEvent, d dVar) {
            super(2, dVar);
            this.$touchEvent = touchEvent;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return DynamicIslandExternalTouchInteractor.this.new C06301(this.$touchEvent, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, d dVar) {
            return ((C06301) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                t tVar = DynamicIslandExternalTouchInteractor.this._externalTouchEvent;
                TouchEvent touchEvent = this.$touchEvent;
                this.label = 1;
                if (tVar.emit(touchEvent, this) == objC) {
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

    public DynamicIslandExternalTouchInteractor(@DynamicIsland E scope, Context context, @SystemUI Context sysUIContext, DynamicIslandTouchRegionInteractor touchRegionInteractor) {
        n.g(scope, "scope");
        n.g(context, "context");
        n.g(sysUIContext, "sysUIContext");
        n.g(touchRegionInteractor, "touchRegionInteractor");
        this.scope = scope;
        this.context = context;
        this.sysUIContext = sysUIContext;
        this.touchRegionInteractor = touchRegionInteractor;
        final t tVarB = A.b(0, 0, null, 7, null);
        this._externalInterceptTouchEvent = tVarB;
        final t tVarB2 = A.b(0, 0, null, 7, null);
        this._externalTouchEvent = tVarB2;
        this.externalInterceptTouchEvent = new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$1

            /* JADX INFO: renamed from: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements InterfaceC0419g {
                final /* synthetic */ InterfaceC0419g $this_unsafeFlow;
                final /* synthetic */ DynamicIslandExternalTouchInteractor this$0;

                /* JADX INFO: renamed from: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$1$2$1, reason: invalid class name */
                @f(c = "miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$1$2", f = "DynamicIslandExternalTouchInteractor.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends N0.d {
                    Object L$0;
                    Object L$1;
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

                public AnonymousClass2(InterfaceC0419g interfaceC0419g, DynamicIslandExternalTouchInteractor dynamicIslandExternalTouchInteractor) {
                    this.$this_unsafeFlow = interfaceC0419g;
                    this.this$0 = dynamicIslandExternalTouchInteractor;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // j1.InterfaceC0419g
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r8, L0.d r9) throws java.lang.Throwable {
                    /*
                        r7 = this;
                        boolean r0 = r9 instanceof miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r9
                        miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$1$2$1 r0 = (miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$1$2$1 r0 = new miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$1$2$1
                        r0.<init>(r9)
                    L18:
                        java.lang.Object r9 = r0.result
                        java.lang.Object r1 = M0.c.c()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        H0.k.b(r9)
                        goto L81
                    L29:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L31:
                        H0.k.b(r9)
                        j1.g r9 = r7.$this_unsafeFlow
                        r2 = r8
                        miui.systemui.dynamicisland.touch.TouchEvent r2 = (miui.systemui.dynamicisland.touch.TouchEvent) r2
                        java.lang.String r4 = r2.getSource()
                        java.lang.String r5 = "dynamic_island"
                        boolean r4 = kotlin.jvm.internal.n.c(r4, r5)
                        if (r4 == 0) goto L47
                        r7 = 0
                        goto L76
                    L47:
                        android.view.MotionEvent r4 = r2.getEvent()
                        int r4 = r4.getActionMasked()
                        if (r4 != 0) goto L70
                        miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor r4 = r7.this$0
                        miui.systemui.dynamicisland.touch.TouchEvent$Companion r5 = miui.systemui.dynamicisland.touch.TouchEvent.Companion
                        android.view.MotionEvent r2 = r2.getEvent()
                        miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor r6 = r7.this$0
                        miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandTouchRegionInteractor r6 = miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor.access$getTouchRegionInteractor$p(r6)
                        j1.I r6 = r6.getTouchRegion()
                        java.lang.Object r6 = r6.getValue()
                        android.graphics.Region r6 = (android.graphics.Region) r6
                        boolean r2 = r5.inRegion(r2, r6)
                        miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor.access$setDownInTouchRegion$p(r4, r2)
                    L70:
                        miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor r7 = r7.this$0
                        boolean r7 = miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor.access$getDownInTouchRegion$p(r7)
                    L76:
                        if (r7 == 0) goto L81
                        r0.label = r3
                        java.lang.Object r7 = r9.emit(r8, r0)
                        if (r7 != r1) goto L81
                        return r1
                    L81:
                        H0.s r7 = H0.s.f314a
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                }
            }

            @Override // j1.InterfaceC0418f
            public Object collect(InterfaceC0419g interfaceC0419g, d dVar) {
                Object objCollect = tVarB.collect(new AnonymousClass2(interfaceC0419g, this), dVar);
                return objCollect == c.c() ? objCollect : s.f314a;
            }
        };
        this.externalTouchEvent = new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$2

            /* JADX INFO: renamed from: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$2$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements InterfaceC0419g {
                final /* synthetic */ InterfaceC0419g $this_unsafeFlow;
                final /* synthetic */ DynamicIslandExternalTouchInteractor this$0;

                /* JADX INFO: renamed from: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$2$2$1, reason: invalid class name */
                @f(c = "miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$2$2", f = "DynamicIslandExternalTouchInteractor.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends N0.d {
                    Object L$0;
                    Object L$1;
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

                public AnonymousClass2(InterfaceC0419g interfaceC0419g, DynamicIslandExternalTouchInteractor dynamicIslandExternalTouchInteractor) {
                    this.$this_unsafeFlow = interfaceC0419g;
                    this.this$0 = dynamicIslandExternalTouchInteractor;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // j1.InterfaceC0419g
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, L0.d r7) throws java.lang.Throwable {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$2$2$1 r0 = (miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$2$2$1 r0 = new miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$2$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        java.lang.Object r1 = M0.c.c()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        H0.k.b(r7)
                        goto L57
                    L29:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L31:
                        H0.k.b(r7)
                        j1.g r7 = r5.$this_unsafeFlow
                        r2 = r6
                        miui.systemui.dynamicisland.touch.TouchEvent r2 = (miui.systemui.dynamicisland.touch.TouchEvent) r2
                        java.lang.String r2 = r2.getSource()
                        java.lang.String r4 = "dynamic_island"
                        boolean r2 = kotlin.jvm.internal.n.c(r2, r4)
                        if (r2 == 0) goto L46
                        goto L57
                    L46:
                        miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor r5 = r5.this$0
                        boolean r5 = miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor.access$getDownInTouchRegion$p(r5)
                        if (r5 == 0) goto L57
                        r0.label = r3
                        java.lang.Object r5 = r7.emit(r6, r0)
                        if (r5 != r1) goto L57
                        return r1
                    L57:
                        H0.s r5 = H0.s.f314a
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.touch.domain.interactor.DynamicIslandExternalTouchInteractor$special$$inlined$filter$2.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                }
            }

            @Override // j1.InterfaceC0418f
            public Object collect(InterfaceC0419g interfaceC0419g, d dVar) {
                Object objCollect = tVarB2.collect(new AnonymousClass2(interfaceC0419g, this), dVar);
                return objCollect == c.c() ? objCollect : s.f314a;
            }
        };
    }

    public final InterfaceC0418f getExternalInterceptTouchEvent() {
        return this.externalInterceptTouchEvent;
    }

    public final InterfaceC0418f getExternalTouchEvent() {
        return this.externalTouchEvent;
    }

    public final Boolean handleExternalIntercept(MotionEvent event, String source) {
        n.g(event, "event");
        n.g(source, "source");
        TouchEvent touchEvent = new TouchEvent(event, source);
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(touchEvent, null), 3, null);
        return touchEvent.getResult();
    }

    public final Boolean handleExternalTouch(MotionEvent event, String source) {
        n.g(event, "event");
        n.g(source, "source");
        TouchEvent touchEvent = new TouchEvent(event, source);
        AbstractC0369g.b(this.scope, null, null, new C06301(touchEvent, null), 3, null);
        return touchEvent.getResult();
    }
}
