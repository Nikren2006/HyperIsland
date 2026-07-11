package miui.systemui.dynamicisland.window.domain.interactor;

import H0.d;
import H0.e;
import H0.k;
import H0.s;
import M0.c;
import N0.f;
import N0.l;
import android.content.Context;
import g1.AbstractC0369g;
import g1.E;
import j1.A;
import j1.AbstractC0420h;
import j1.I;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import j1.K;
import j1.t;
import j1.u;
import j1.y;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import kotlin.jvm.internal.w;
import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.touch.TouchEvent;
import miui.systemui.dynamicisland.window.DynamicIslandWindowView;
import miui.systemui.statusbar.data.repository.StatusBarAreaRepository;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandWindowViewTouchInteractor implements DynamicIslandStartable {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DynamicIslandWindowViewTouchHandler";
    private final t _dispatchInterceptTouchEvent;
    private final t _dispatchTouchEvent;
    private final t _islandInterceptTouchEvent;
    private final t _islandTouchEvent;
    private final Context context;
    private final y dispatchInterceptTouchEvent;
    private final y dispatchTouchEvent;
    private final d isTracking$delegate;
    private final y islandInterceptTouchEvent;
    private final y islandTouchEvent;
    private final InterfaceC0418f outsideInterceptTouchEvent;
    private final InterfaceC0418f outsideTouchEvent;
    private final E scope;
    private final StatusBarAreaRepository statusBarAreaRepository;
    private final DynamicIslandWindowView windowView;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$isTracking$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function0 {
        public AnonymousClass2() {
            super(0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invoke$lambda$1$isUpOrCancel(TouchEvent touchEvent) {
            int actionMasked = touchEvent.getEvent().getActionMasked();
            return actionMasked == 1 || actionMasked == 3;
        }

        @Override // kotlin.jvm.functions.Function0
        public final I invoke() {
            DynamicIslandWindowViewTouchInteractor dynamicIslandWindowViewTouchInteractor = DynamicIslandWindowViewTouchInteractor.this;
            u uVarA = K.a(Boolean.FALSE);
            AbstractC0369g.b(dynamicIslandWindowViewTouchInteractor.scope, null, null, new DynamicIslandWindowViewTouchInteractor$isTracking$2$1$1(dynamicIslandWindowViewTouchInteractor, uVarA, null), 3, null);
            AbstractC0369g.b(dynamicIslandWindowViewTouchInteractor.scope, null, null, new DynamicIslandWindowViewTouchInteractor$isTracking$2$1$2(dynamicIslandWindowViewTouchInteractor, uVarA, null), 3, null);
            return AbstractC0420h.b(uVarA);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1", f = "DynamicIslandWindowViewTouchInteractor.kt", l = {83}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ w $downInStatusBarArea;
        int label;

        /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$2, reason: invalid class name */
        public static final class AnonymousClass2<T> implements InterfaceC0419g {
            final /* synthetic */ w $downInStatusBarArea;
            final /* synthetic */ DynamicIslandWindowViewTouchInteractor this$0;

            public AnonymousClass2(w wVar, DynamicIslandWindowViewTouchInteractor dynamicIslandWindowViewTouchInteractor) {
                this.$downInStatusBarArea = wVar;
                this.this$0 = dynamicIslandWindowViewTouchInteractor;
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
            @Override // j1.InterfaceC0419g
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(miui.systemui.dynamicisland.touch.TouchEvent r8, L0.d r9) throws java.lang.Throwable {
                /*
                    r7 = this;
                    boolean r0 = r9 instanceof miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$2$emit$1
                    if (r0 == 0) goto L13
                    r0 = r9
                    miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$2$emit$1 r0 = (miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$2$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L18
                L13:
                    miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$2$emit$1 r0 = new miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$2$emit$1
                    r0.<init>(r7, r9)
                L18:
                    java.lang.Object r9 = r0.result
                    java.lang.Object r1 = M0.c.c()
                    int r2 = r0.label
                    r3 = 2
                    r4 = 1
                    if (r2 == 0) goto L42
                    if (r2 == r4) goto L35
                    if (r2 != r3) goto L2d
                    H0.k.b(r9)
                    goto La6
                L2d:
                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                    java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                    r7.<init>(r8)
                    throw r7
                L35:
                    java.lang.Object r7 = r0.L$1
                    r8 = r7
                    miui.systemui.dynamicisland.touch.TouchEvent r8 = (miui.systemui.dynamicisland.touch.TouchEvent) r8
                    java.lang.Object r7 = r0.L$0
                    miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$2 r7 = (miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor.AnonymousClass1.AnonymousClass2) r7
                    H0.k.b(r9)
                    goto L80
                L42:
                    H0.k.b(r9)
                    android.view.MotionEvent r9 = r8.getEvent()
                    int r9 = r9.getActionMasked()
                    if (r9 != 0) goto L6d
                    kotlin.jvm.internal.w r9 = r7.$downInStatusBarArea
                    miui.systemui.dynamicisland.touch.TouchEvent$Companion r2 = miui.systemui.dynamicisland.touch.TouchEvent.Companion
                    android.view.MotionEvent r5 = r8.getEvent()
                    miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor r6 = r7.this$0
                    miui.systemui.statusbar.data.repository.StatusBarAreaRepository r6 = miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor.access$getStatusBarAreaRepository$p(r6)
                    j1.I r6 = r6.getStatusBarArea()
                    java.lang.Object r6 = r6.getValue()
                    android.graphics.Rect r6 = (android.graphics.Rect) r6
                    boolean r2 = r2.inRect(r5, r6)
                    r9.f5057a = r2
                L6d:
                    miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor r9 = r7.this$0
                    j1.t r9 = miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor.access$get_islandInterceptTouchEvent$p(r9)
                    r0.L$0 = r7
                    r0.L$1 = r8
                    r0.label = r4
                    java.lang.Object r9 = r9.emit(r8, r0)
                    if (r9 != r1) goto L80
                    return r1
                L80:
                    java.lang.Boolean r9 = r8.getResult()
                    if (r9 == 0) goto L89
                    H0.s r7 = H0.s.f314a
                    return r7
                L89:
                    kotlin.jvm.internal.w r9 = r7.$downInStatusBarArea
                    boolean r9 = r9.f5057a
                    if (r9 == 0) goto La9
                    r9 = 0
                    r8.setResult(r9)
                    miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor r7 = r7.this$0
                    j1.t r7 = miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor.access$get_dispatchInterceptTouchEvent$p(r7)
                    r0.L$0 = r9
                    r0.L$1 = r9
                    r0.label = r3
                    java.lang.Object r7 = r7.emit(r8, r0)
                    if (r7 != r1) goto La6
                    return r1
                La6:
                    H0.s r7 = H0.s.f314a
                    return r7
                La9:
                    H0.s r7 = H0.s.f314a
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor.AnonymousClass1.AnonymousClass2.emit(miui.systemui.dynamicisland.touch.TouchEvent, L0.d):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(w wVar, L0.d dVar) {
            super(2, dVar);
            this.$downInStatusBarArea = wVar;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewTouchInteractor.this.new AnonymousClass1(this.$downInStatusBarArea, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                final y onInterceptTouchEvent = DynamicIslandWindowViewTouchInteractor.this.windowView.getOnInterceptTouchEvent();
                InterfaceC0418f interfaceC0418f = new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$invokeSuspend$$inlined$filter$1

                    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$invokeSuspend$$inlined$filter$1$2, reason: invalid class name */
                    public static final class AnonymousClass2<T> implements InterfaceC0419g {
                        final /* synthetic */ InterfaceC0419g $this_unsafeFlow;

                        /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$invokeSuspend$$inlined$filter$1$2$1, reason: invalid class name */
                        @f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$invokeSuspend$$inlined$filter$1$2", f = "DynamicIslandWindowViewTouchInteractor.kt", l = {223}, m = "emit")
                        public static final class AnonymousClass1 extends N0.d {
                            Object L$0;
                            Object L$1;
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
                                boolean r0 = r6 instanceof miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$invokeSuspend$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                                if (r0 == 0) goto L13
                                r0 = r6
                                miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$invokeSuspend$$inlined$filter$1$2$1 r0 = (miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$invokeSuspend$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                                int r1 = r0.label
                                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                r3 = r1 & r2
                                if (r3 == 0) goto L13
                                int r1 = r1 - r2
                                r0.label = r1
                                goto L18
                            L13:
                                miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$invokeSuspend$$inlined$filter$1$2$1 r0 = new miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$invokeSuspend$$inlined$filter$1$2$1
                                r0.<init>(r6)
                            L18:
                                java.lang.Object r6 = r0.result
                                java.lang.Object r1 = M0.c.c()
                                int r2 = r0.label
                                r3 = 1
                                if (r2 == 0) goto L31
                                if (r2 != r3) goto L29
                                H0.k.b(r6)
                                goto L4d
                            L29:
                                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                                r4.<init>(r5)
                                throw r4
                            L31:
                                H0.k.b(r6)
                                j1.g r4 = r4.$this_unsafeFlow
                                r6 = r5
                                miui.systemui.dynamicisland.touch.TouchEvent r6 = (miui.systemui.dynamicisland.touch.TouchEvent) r6
                                android.view.MotionEvent r6 = r6.getEvent()
                                int r6 = r6.getActionMasked()
                                r2 = 4
                                if (r6 == r2) goto L4d
                                r0.label = r3
                                java.lang.Object r4 = r4.emit(r5, r0)
                                if (r4 != r1) goto L4d
                                return r1
                            L4d:
                                H0.s r4 = H0.s.f314a
                                return r4
                            */
                            throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$1$invokeSuspend$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                        }
                    }

                    @Override // j1.InterfaceC0418f
                    public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
                        Object objCollect = onInterceptTouchEvent.collect(new AnonymousClass2(interfaceC0419g), dVar);
                        return objCollect == c.c() ? objCollect : s.f314a;
                    }
                };
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$downInStatusBarArea, DynamicIslandWindowViewTouchInteractor.this);
                this.label = 1;
                if (interfaceC0418f.collect(anonymousClass2, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2, reason: invalid class name and case insensitive filesystem */
    @f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2", f = "DynamicIslandWindowViewTouchInteractor.kt", l = {105}, m = "invokeSuspend")
    public static final class C06602 extends l implements Function2 {
        final /* synthetic */ w $downInStatusBarArea;
        int label;

        /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$2, reason: invalid class name and collision with other inner class name */
        public static final class C01432<T> implements InterfaceC0419g {
            final /* synthetic */ w $downInStatusBarArea;
            final /* synthetic */ DynamicIslandWindowViewTouchInteractor this$0;

            public C01432(DynamicIslandWindowViewTouchInteractor dynamicIslandWindowViewTouchInteractor, w wVar) {
                this.this$0 = dynamicIslandWindowViewTouchInteractor;
                this.$downInStatusBarArea = wVar;
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
            @Override // j1.InterfaceC0419g
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(miui.systemui.dynamicisland.touch.TouchEvent r6, L0.d r7) throws java.lang.Throwable {
                /*
                    r5 = this;
                    boolean r0 = r7 instanceof miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$2$emit$1
                    if (r0 == 0) goto L13
                    r0 = r7
                    miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$2$emit$1 r0 = (miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$2$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L18
                L13:
                    miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$2$emit$1 r0 = new miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$2$emit$1
                    r0.<init>(r5, r7)
                L18:
                    java.lang.Object r7 = r0.result
                    java.lang.Object r1 = M0.c.c()
                    int r2 = r0.label
                    r3 = 2
                    r4 = 1
                    if (r2 == 0) goto L41
                    if (r2 == r4) goto L34
                    if (r2 != r3) goto L2c
                    H0.k.b(r7)
                    goto L7d
                L2c:
                    java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                    java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                    r5.<init>(r6)
                    throw r5
                L34:
                    java.lang.Object r5 = r0.L$1
                    r6 = r5
                    miui.systemui.dynamicisland.touch.TouchEvent r6 = (miui.systemui.dynamicisland.touch.TouchEvent) r6
                    java.lang.Object r5 = r0.L$0
                    miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$2 r5 = (miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor.C06602.C01432) r5
                    H0.k.b(r7)
                    goto L60
                L41:
                    H0.k.b(r7)
                    java.lang.Boolean r7 = r6.getResult()
                    if (r7 == 0) goto L4d
                    H0.s r5 = H0.s.f314a
                    return r5
                L4d:
                    miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor r7 = r5.this$0
                    j1.t r7 = miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor.access$get_islandTouchEvent$p(r7)
                    r0.L$0 = r5
                    r0.L$1 = r6
                    r0.label = r4
                    java.lang.Object r7 = r7.emit(r6, r0)
                    if (r7 != r1) goto L60
                    return r1
                L60:
                    kotlin.jvm.internal.w r7 = r5.$downInStatusBarArea
                    boolean r7 = r7.f5057a
                    if (r7 == 0) goto L80
                    r7 = 0
                    r6.setResult(r7)
                    miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor r5 = r5.this$0
                    j1.t r5 = miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor.access$get_dispatchTouchEvent$p(r5)
                    r0.L$0 = r7
                    r0.L$1 = r7
                    r0.label = r3
                    java.lang.Object r5 = r5.emit(r6, r0)
                    if (r5 != r1) goto L7d
                    return r1
                L7d:
                    H0.s r5 = H0.s.f314a
                    return r5
                L80:
                    H0.s r5 = H0.s.f314a
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor.C06602.C01432.emit(miui.systemui.dynamicisland.touch.TouchEvent, L0.d):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06602(w wVar, L0.d dVar) {
            super(2, dVar);
            this.$downInStatusBarArea = wVar;
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewTouchInteractor.this.new C06602(this.$downInStatusBarArea, dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06602) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = c.c();
            int i2 = this.label;
            if (i2 == 0) {
                k.b(obj);
                final y onTouchEvent = DynamicIslandWindowViewTouchInteractor.this.windowView.getOnTouchEvent();
                InterfaceC0418f interfaceC0418f = new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$invokeSuspend$$inlined$filter$1

                    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$invokeSuspend$$inlined$filter$1$2, reason: invalid class name */
                    public static final class AnonymousClass2<T> implements InterfaceC0419g {
                        final /* synthetic */ InterfaceC0419g $this_unsafeFlow;

                        /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$invokeSuspend$$inlined$filter$1$2$1, reason: invalid class name */
                        @f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$invokeSuspend$$inlined$filter$1$2", f = "DynamicIslandWindowViewTouchInteractor.kt", l = {223}, m = "emit")
                        public static final class AnonymousClass1 extends N0.d {
                            Object L$0;
                            Object L$1;
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
                                boolean r0 = r6 instanceof miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$invokeSuspend$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                                if (r0 == 0) goto L13
                                r0 = r6
                                miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$invokeSuspend$$inlined$filter$1$2$1 r0 = (miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$invokeSuspend$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                                int r1 = r0.label
                                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                r3 = r1 & r2
                                if (r3 == 0) goto L13
                                int r1 = r1 - r2
                                r0.label = r1
                                goto L18
                            L13:
                                miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$invokeSuspend$$inlined$filter$1$2$1 r0 = new miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$invokeSuspend$$inlined$filter$1$2$1
                                r0.<init>(r6)
                            L18:
                                java.lang.Object r6 = r0.result
                                java.lang.Object r1 = M0.c.c()
                                int r2 = r0.label
                                r3 = 1
                                if (r2 == 0) goto L31
                                if (r2 != r3) goto L29
                                H0.k.b(r6)
                                goto L4d
                            L29:
                                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                                r4.<init>(r5)
                                throw r4
                            L31:
                                H0.k.b(r6)
                                j1.g r4 = r4.$this_unsafeFlow
                                r6 = r5
                                miui.systemui.dynamicisland.touch.TouchEvent r6 = (miui.systemui.dynamicisland.touch.TouchEvent) r6
                                android.view.MotionEvent r6 = r6.getEvent()
                                int r6 = r6.getActionMasked()
                                r2 = 4
                                if (r6 == r2) goto L4d
                                r0.label = r3
                                java.lang.Object r4 = r4.emit(r5, r0)
                                if (r4 != r1) goto L4d
                                return r1
                            L4d:
                                H0.s r4 = H0.s.f314a
                                return r4
                            */
                            throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$start$2$invokeSuspend$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                        }
                    }

                    @Override // j1.InterfaceC0418f
                    public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
                        Object objCollect = onTouchEvent.collect(new AnonymousClass2(interfaceC0419g), dVar);
                        return objCollect == c.c() ? objCollect : s.f314a;
                    }
                };
                C01432 c01432 = new C01432(DynamicIslandWindowViewTouchInteractor.this, this.$downInStatusBarArea);
                this.label = 1;
                if (interfaceC0418f.collect(c01432, this) == objC) {
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

    public DynamicIslandWindowViewTouchInteractor(@DynamicIsland E scope, Context context, DynamicIslandWindowView windowView, StatusBarAreaRepository statusBarAreaRepository) {
        n.g(scope, "scope");
        n.g(context, "context");
        n.g(windowView, "windowView");
        n.g(statusBarAreaRepository, "statusBarAreaRepository");
        this.scope = scope;
        this.context = context;
        this.windowView = windowView;
        this.statusBarAreaRepository = statusBarAreaRepository;
        t tVarB = A.b(0, 0, null, 7, null);
        this._islandInterceptTouchEvent = tVarB;
        t tVarB2 = A.b(0, 0, null, 7, null);
        this._islandTouchEvent = tVarB2;
        t tVarB3 = A.b(0, 0, null, 7, null);
        this._dispatchInterceptTouchEvent = tVarB3;
        t tVarB4 = A.b(0, 0, null, 7, null);
        this._dispatchTouchEvent = tVarB4;
        final y onInterceptTouchEvent = windowView.getOnInterceptTouchEvent();
        this.outsideInterceptTouchEvent = new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$1

            /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements InterfaceC0419g {
                final /* synthetic */ InterfaceC0419g $this_unsafeFlow;

                /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$1$2$1, reason: invalid class name */
                @f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$1$2", f = "DynamicIslandWindowViewTouchInteractor.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends N0.d {
                    Object L$0;
                    Object L$1;
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
                        boolean r0 = r6 instanceof miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$1$2$1 r0 = (miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$1$2$1 r0 = new miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = M0.c.c()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        H0.k.b(r6)
                        goto L4d
                    L29:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L31:
                        H0.k.b(r6)
                        j1.g r4 = r4.$this_unsafeFlow
                        r6 = r5
                        miui.systemui.dynamicisland.touch.TouchEvent r6 = (miui.systemui.dynamicisland.touch.TouchEvent) r6
                        android.view.MotionEvent r6 = r6.getEvent()
                        int r6 = r6.getActionMasked()
                        r2 = 4
                        if (r6 != r2) goto L4d
                        r0.label = r3
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4d
                        return r1
                    L4d:
                        H0.s r4 = H0.s.f314a
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                }
            }

            @Override // j1.InterfaceC0418f
            public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
                Object objCollect = onInterceptTouchEvent.collect(new AnonymousClass2(interfaceC0419g), dVar);
                return objCollect == c.c() ? objCollect : s.f314a;
            }
        };
        final y onTouchEvent = windowView.getOnTouchEvent();
        this.outsideTouchEvent = new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$2

            /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$2$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements InterfaceC0419g {
                final /* synthetic */ InterfaceC0419g $this_unsafeFlow;

                /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$2$2$1, reason: invalid class name */
                @f(c = "miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$2$2", f = "DynamicIslandWindowViewTouchInteractor.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends N0.d {
                    Object L$0;
                    Object L$1;
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
                        boolean r0 = r6 instanceof miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$2$2$1 r0 = (miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$2$2$1 r0 = new miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = M0.c.c()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        H0.k.b(r6)
                        goto L4d
                    L29:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L31:
                        H0.k.b(r6)
                        j1.g r4 = r4.$this_unsafeFlow
                        r6 = r5
                        miui.systemui.dynamicisland.touch.TouchEvent r6 = (miui.systemui.dynamicisland.touch.TouchEvent) r6
                        android.view.MotionEvent r6 = r6.getEvent()
                        int r6 = r6.getActionMasked()
                        r2 = 4
                        if (r6 != r2) goto L4d
                        r0.label = r3
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4d
                        return r1
                    L4d:
                        H0.s r4 = H0.s.f314a
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.domain.interactor.DynamicIslandWindowViewTouchInteractor$special$$inlined$filter$2.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                }
            }

            @Override // j1.InterfaceC0418f
            public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
                Object objCollect = onTouchEvent.collect(new AnonymousClass2(interfaceC0419g), dVar);
                return objCollect == c.c() ? objCollect : s.f314a;
            }
        };
        this.islandInterceptTouchEvent = AbstractC0420h.a(tVarB);
        this.islandTouchEvent = AbstractC0420h.a(tVarB2);
        this.dispatchInterceptTouchEvent = AbstractC0420h.a(tVarB3);
        this.dispatchTouchEvent = AbstractC0420h.a(tVarB4);
        this.isTracking$delegate = e.b(new AnonymousClass2());
    }

    public final y getDispatchInterceptTouchEvent() {
        return this.dispatchInterceptTouchEvent;
    }

    public final y getDispatchTouchEvent() {
        return this.dispatchTouchEvent;
    }

    public final y getIslandInterceptTouchEvent() {
        return this.islandInterceptTouchEvent;
    }

    public final y getIslandTouchEvent() {
        return this.islandTouchEvent;
    }

    public final InterfaceC0418f getOutsideInterceptTouchEvent() {
        return this.outsideInterceptTouchEvent;
    }

    public final InterfaceC0418f getOutsideTouchEvent() {
        return this.outsideTouchEvent;
    }

    public final I isTracking() {
        return (I) this.isTracking$delegate.getValue();
    }

    @Override // miui.systemui.dynamicisland.DynamicIslandStartable
    public void start() {
        w wVar = new w();
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(wVar, null), 3, null);
        AbstractC0369g.b(this.scope, null, null, new C06602(wVar, null), 3, null);
    }
}
