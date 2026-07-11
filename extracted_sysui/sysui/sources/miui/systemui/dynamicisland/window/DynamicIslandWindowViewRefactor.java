package miui.systemui.dynamicisland.window;

import H0.s;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import com.android.systemui.settings.UserTracker;
import g1.AbstractC0369g;
import g1.E;
import g1.M;
import j1.I;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import j1.y;
import java.util.Iterator;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.Prefs;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.dynamicisland.DynamicIslandConstants;
import miui.systemui.dynamicisland.DynamicIslandStartable;
import miui.systemui.dynamicisland.dagger.DynamicIslandScope;
import miui.systemui.dynamicisland.dagger.qualifiers.DynamicIsland;
import miui.systemui.dynamicisland.event.DynamicIslandEventCoordinator;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentFakeView;
import miui.systemui.ui.data.repository.ConfigurationRepository;

/* JADX INFO: loaded from: classes3.dex */
@DynamicIslandScope
public final class DynamicIslandWindowViewRefactor implements DynamicIslandStartable {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DynamicIslandWindowViewRefactor";
    private final ConfigurationRepository configurationRepository;
    private final Context context;
    private final DynamicIslandEventCoordinator eventCoordinator;
    private final E scope;
    private final Context sysUIContext;
    private final Executor uiExecutor;
    private final UserTracker userTracker;
    private final DynamicIslandWindowView windowView;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForExpandedState$1, reason: invalid class name */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForExpandedState$1", f = "DynamicIslandWindowViewRefactor.kt", l = {53}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends N0.l implements Function2 {
        int label;

        public AnonymousClass1(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewRefactor.this.new AnonymousClass1(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((AnonymousClass1) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                final I expandedState = DynamicIslandWindowViewRefactor.this.eventCoordinator.getExpandedState();
                InterfaceC0418f interfaceC0418f = new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForExpandedState$1$invokeSuspend$$inlined$mapNotNull$1

                    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForExpandedState$1$invokeSuspend$$inlined$mapNotNull$1$2, reason: invalid class name */
                    public static final class AnonymousClass2<T> implements InterfaceC0419g {
                        final /* synthetic */ InterfaceC0419g $this_unsafeFlow;

                        /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForExpandedState$1$invokeSuspend$$inlined$mapNotNull$1$2$1, reason: invalid class name */
                        @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForExpandedState$1$invokeSuspend$$inlined$mapNotNull$1$2", f = "DynamicIslandWindowViewRefactor.kt", l = {225}, m = "emit")
                        public static final class AnonymousClass1 extends N0.d {
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
                                boolean r0 = r6 instanceof miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForExpandedState$1$invokeSuspend$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1
                                if (r0 == 0) goto L13
                                r0 = r6
                                miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForExpandedState$1$invokeSuspend$$inlined$mapNotNull$1$2$1 r0 = (miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForExpandedState$1$invokeSuspend$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1) r0
                                int r1 = r0.label
                                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                r3 = r1 & r2
                                if (r3 == 0) goto L13
                                int r1 = r1 - r2
                                r0.label = r1
                                goto L18
                            L13:
                                miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForExpandedState$1$invokeSuspend$$inlined$mapNotNull$1$2$1 r0 = new miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForExpandedState$1$invokeSuspend$$inlined$mapNotNull$1$2$1
                                r0.<init>(r6)
                            L18:
                                java.lang.Object r6 = r0.result
                                java.lang.Object r1 = M0.c.c()
                                int r2 = r0.label
                                r3 = 1
                                if (r2 == 0) goto L31
                                if (r2 != r3) goto L29
                                H0.k.b(r6)
                                goto L43
                            L29:
                                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                                r4.<init>(r5)
                                throw r4
                            L31:
                                H0.k.b(r6)
                                j1.g r4 = r4.$this_unsafeFlow
                                java.lang.Boolean r5 = (java.lang.Boolean) r5
                                if (r5 == 0) goto L43
                                r0.label = r3
                                java.lang.Object r4 = r4.emit(r5, r0)
                                if (r4 != r1) goto L43
                                return r1
                            L43:
                                H0.s r4 = H0.s.f314a
                                return r4
                            */
                            throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForExpandedState$1$invokeSuspend$$inlined$mapNotNull$1.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                        }
                    }

                    @Override // j1.InterfaceC0418f
                    public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
                        Object objCollect = expandedState.collect(new AnonymousClass2(interfaceC0419g), dVar);
                        return objCollect == M0.c.c() ? objCollect : s.f314a;
                    }
                };
                final DynamicIslandWindowViewRefactor dynamicIslandWindowViewRefactor = DynamicIslandWindowViewRefactor.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor.listenForExpandedState.1.2
                    @Override // j1.InterfaceC0419g
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, L0.d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), dVar);
                    }

                    public final Object emit(boolean z2, L0.d dVar) {
                        Log.e(DynamicIslandWindowViewRefactor.TAG, "expandedState " + z2);
                        Intent intent = new Intent(DynamicIslandConstants.ACTION_EXPANDED_STATE_CHANGED);
                        intent.putExtra(DynamicIslandConstants.EXTRA_EXPANDED_STATE, z2);
                        SharedPreferences notif = Prefs.getNotif(dynamicIslandWindowViewRefactor.sysUIContext);
                        kotlin.jvm.internal.n.f(notif, "getNotif(...)");
                        SharedPreferences.Editor editorEdit = notif.edit();
                        editorEdit.putBoolean(DynamicIslandConstants.EXTRA_EXPANDED_STATE, z2);
                        editorEdit.apply();
                        dynamicIslandWindowViewRefactor.context.sendBroadcast(intent);
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (interfaceC0418f.collect(interfaceC0419g, this) == objC) {
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

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForOrientationChanged$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForOrientationChanged$1", f = "DynamicIslandWindowViewRefactor.kt", l = {80}, m = "invokeSuspend")
    public static final class C06431 extends N0.l implements Function2 {
        int label;

        public C06431(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewRefactor.this.new C06431(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06431) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                y orientationChanged = DynamicIslandWindowViewRefactor.this.configurationRepository.getOrientationChanged();
                final DynamicIslandWindowViewRefactor dynamicIslandWindowViewRefactor = DynamicIslandWindowViewRefactor.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor.listenForOrientationChanged.1.1
                    @Override // j1.InterfaceC0419g
                    public final Object emit(s sVar, L0.d dVar) {
                        Iterator<T> it = dynamicIslandWindowViewRefactor.windowView.getFakeViews().iterator();
                        while (it.hasNext()) {
                            ((DynamicIslandContentFakeView) it.next()).suppressLayout(false);
                        }
                        return s.f314a;
                    }
                };
                this.label = 1;
                if (orientationChanged.collect(interfaceC0419g, this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForUserChanged$1, reason: invalid class name and case insensitive filesystem */
    @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForUserChanged$1", f = "DynamicIslandWindowViewRefactor.kt", l = {70}, m = "invokeSuspend")
    public static final class C06441 extends N0.l implements Function2 {
        int label;

        public C06441(L0.d dVar) {
            super(2, dVar);
        }

        @Override // N0.a
        public final L0.d create(Object obj, L0.d dVar) {
            return DynamicIslandWindowViewRefactor.this.new C06441(dVar);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e2, L0.d dVar) {
            return ((C06441) create(e2, dVar)).invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objC = M0.c.c();
            int i2 = this.label;
            if (i2 == 0) {
                H0.k.b(obj);
                DynamicIslandWindowViewRefactor.this.userTracker.addCallback(DynamicIslandWindowViewRefactor.this.windowView, DynamicIslandWindowViewRefactor.this.uiExecutor);
                this.label = 1;
                if (M.a(this) == objC) {
                    return objC;
                }
            } else {
                if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                H0.k.b(obj);
            }
            throw new H0.c();
        }
    }

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewRefactor$listenForUserChanged$2, reason: invalid class name */
    public static final class AnonymousClass2 extends kotlin.jvm.internal.o implements Function1 {
        public AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return s.f314a;
        }

        public final void invoke(Throwable th) {
            DynamicIslandWindowViewRefactor.this.userTracker.removeCallback(DynamicIslandWindowViewRefactor.this.windowView);
        }
    }

    public DynamicIslandWindowViewRefactor(@SystemUI Context sysUIContext, Context context, @DynamicIsland E scope, DynamicIslandWindowView windowView, DynamicIslandEventCoordinator eventCoordinator, UserTracker userTracker, @Main Executor uiExecutor, ConfigurationRepository configurationRepository) {
        kotlin.jvm.internal.n.g(sysUIContext, "sysUIContext");
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(scope, "scope");
        kotlin.jvm.internal.n.g(windowView, "windowView");
        kotlin.jvm.internal.n.g(eventCoordinator, "eventCoordinator");
        kotlin.jvm.internal.n.g(userTracker, "userTracker");
        kotlin.jvm.internal.n.g(uiExecutor, "uiExecutor");
        kotlin.jvm.internal.n.g(configurationRepository, "configurationRepository");
        this.sysUIContext = sysUIContext;
        this.context = context;
        this.scope = scope;
        this.windowView = windowView;
        this.eventCoordinator = eventCoordinator;
        this.userTracker = userTracker;
        this.uiExecutor = uiExecutor;
        this.configurationRepository = configurationRepository;
    }

    private final void listenForExpandedState() {
        AbstractC0369g.b(this.scope, null, null, new AnonymousClass1(null), 3, null);
    }

    private final void listenForOrientationChanged() {
        AbstractC0369g.b(this.scope, null, null, new C06431(null), 3, null);
    }

    private final void listenForUserChanged() {
        AbstractC0369g.b(this.scope, null, null, new C06441(null), 3, null).l(new AnonymousClass2());
    }

    @Override // miui.systemui.dynamicisland.DynamicIslandStartable
    public void start() {
        listenForExpandedState();
        listenForUserChanged();
        listenForOrientationChanged();
    }
}
