package miui.systemui.dynamicisland.window;

import H0.s;
import android.util.Log;
import j1.AbstractC0420h;
import j1.I;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import j1.K;
import j1.u;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dynamicisland.event.DynamicIslandState;
import miui.systemui.dynamicisland.window.content.DynamicIslandContentView;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandViewModel {
    public static final Companion Companion = new Companion(null);
    private static final H0.i DYNAMIC_ISLAND_INIT_SWIPE_INFO;
    private final u _state = K.a(null);
    private final u _stateName = K.a(null);
    private DynamicIslandViewStateChangeCallback dynamicIslandViewStateChangeCallback;
    private final InterfaceC0418f isExpanded;
    private DynamicIslandState oldState;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface DynamicIslandViewStateChangeCallback {
        void onStateChanged(DynamicIslandState dynamicIslandState, DynamicIslandState dynamicIslandState2);

        void onStateChanged(DynamicIslandContentView dynamicIslandContentView);
    }

    static {
        Float fValueOf = Float.valueOf(0.0f);
        DYNAMIC_ISLAND_INIT_SWIPE_INFO = new H0.i(fValueOf, fValueOf);
    }

    public DynamicIslandViewModel() {
        final I state = getState();
        this.isExpanded = AbstractC0420h.n(new InterfaceC0418f() { // from class: miui.systemui.dynamicisland.window.DynamicIslandViewModel$special$$inlined$map$1

            /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements InterfaceC0419g {
                final /* synthetic */ InterfaceC0419g $this_unsafeFlow;

                /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
                @N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandViewModel$special$$inlined$map$1$2", f = "DynamicIslandViewModel.kt", l = {223}, m = "emit")
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
                        boolean r0 = r6 instanceof miui.systemui.dynamicisland.window.DynamicIslandViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        miui.systemui.dynamicisland.window.DynamicIslandViewModel$special$$inlined$map$1$2$1 r0 = (miui.systemui.dynamicisland.window.DynamicIslandViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        miui.systemui.dynamicisland.window.DynamicIslandViewModel$special$$inlined$map$1$2$1 r0 = new miui.systemui.dynamicisland.window.DynamicIslandViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = M0.c.c()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        H0.k.b(r6)
                        goto L47
                    L29:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L31:
                        H0.k.b(r6)
                        j1.g r4 = r4.$this_unsafeFlow
                        miui.systemui.dynamicisland.event.DynamicIslandState r5 = (miui.systemui.dynamicisland.event.DynamicIslandState) r5
                        boolean r5 = r5 instanceof miui.systemui.dynamicisland.event.DynamicIslandState.Expanded
                        java.lang.Boolean r5 = N0.b.a(r5)
                        r0.label = r3
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L47
                        return r1
                    L47:
                        H0.s r4 = H0.s.f314a
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: miui.systemui.dynamicisland.window.DynamicIslandViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                }
            }

            @Override // j1.InterfaceC0418f
            public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
                Object objCollect = state.collect(new AnonymousClass2(interfaceC0419g), dVar);
                return objCollect == M0.c.c() ? objCollect : s.f314a;
            }
        });
        this.oldState = (DynamicIslandState) getState().getValue();
    }

    public final DynamicIslandState getOldState() {
        return this.oldState;
    }

    public final I getState() {
        return AbstractC0420h.b(this._state);
    }

    public final I getStateName() {
        return AbstractC0420h.b(this._stateName);
    }

    public final InterfaceC0418f isExpanded() {
        return this.isExpanded;
    }

    public final void registerDynamicIslandViewStateChangeCallback(DynamicIslandViewStateChangeCallback callback) {
        kotlin.jvm.internal.n.g(callback, "callback");
        this.dynamicIslandViewStateChangeCallback = callback;
    }

    public final void setOldState(DynamicIslandState dynamicIslandState) {
        this.oldState = dynamicIslandState;
    }

    public final void setState(DynamicIslandState dynamicIslandState, DynamicIslandState dynamicIslandState2) {
        Log.d("addState", "setState: " + dynamicIslandState + " -> " + dynamicIslandState2);
        DynamicIslandViewStateChangeCallback dynamicIslandViewStateChangeCallback = this.dynamicIslandViewStateChangeCallback;
        if (dynamicIslandViewStateChangeCallback != null) {
            dynamicIslandViewStateChangeCallback.onStateChanged(dynamicIslandState, dynamicIslandState2);
        }
        this.oldState = dynamicIslandState;
        this._state.setValue(dynamicIslandState2);
        this._stateName.setValue(dynamicIslandState2 != null ? dynamicIslandState2.getClass().getSimpleName() : null);
    }

    public final void updateState(DynamicIslandContentView dynamicIslandContentView) {
        DynamicIslandState state;
        String simpleName = null;
        Log.d("updateState", "updateState: " + (dynamicIslandContentView != null ? dynamicIslandContentView.getLastState() : null) + " -> " + (dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null));
        DynamicIslandViewStateChangeCallback dynamicIslandViewStateChangeCallback = this.dynamicIslandViewStateChangeCallback;
        if (dynamicIslandViewStateChangeCallback != null) {
            dynamicIslandViewStateChangeCallback.onStateChanged(dynamicIslandContentView);
        }
        this._state.setValue(dynamicIslandContentView != null ? dynamicIslandContentView.getState() : null);
        u uVar = this._stateName;
        if (dynamicIslandContentView != null && (state = dynamicIslandContentView.getState()) != null) {
            simpleName = state.getClass().getSimpleName();
        }
        uVar.setValue(simpleName);
    }
}
