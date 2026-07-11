package androidx.slidingpanelayout.widget;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.app.Activity;
import androidx.slidingpanelayout.widget.FoldingFeatureObserver;
import androidx.window.layout.DisplayFeature;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.WindowLayoutInfo;
import g1.AbstractC0364d0;
import g1.AbstractC0369g;
import g1.E;
import g1.F;
import g1.InterfaceC0380l0;
import j1.AbstractC0420h;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import java.util.Iterator;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;

/* JADX INFO: loaded from: classes.dex */
public final class FoldingFeatureObserver {
    private final Executor executor;
    private InterfaceC0380l0 job;
    private OnFoldingFeatureChangeListener onFoldingFeatureChangeListener;
    private final WindowInfoTracker windowInfoTracker;

    public interface OnFoldingFeatureChangeListener {
        void onFoldingFeatureChange(FoldingFeature foldingFeature);
    }

    /* JADX INFO: renamed from: androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1, reason: invalid class name */
    @f(c = "androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1", f = "FoldingFeatureObserver.kt", l = {97}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function2 {
        final /* synthetic */ Activity $activity;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Activity activity, d dVar) {
            super(2, dVar);
            this.$activity = activity;
        }

        @Override // N0.a
        public final d create(Object obj, d dVar) {
            return FoldingFeatureObserver.this.new AnonymousClass1(this.$activity, dVar);
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
                final InterfaceC0418f interfaceC0418fWindowLayoutInfo = FoldingFeatureObserver.this.windowInfoTracker.windowLayoutInfo(this.$activity);
                final FoldingFeatureObserver foldingFeatureObserver = FoldingFeatureObserver.this;
                InterfaceC0418f interfaceC0418fN = AbstractC0420h.n(new InterfaceC0418f() { // from class: androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1

                    /* JADX INFO: renamed from: androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2, reason: invalid class name */
                    public static final class AnonymousClass2 implements InterfaceC0419g {
                        final /* synthetic */ InterfaceC0419g $this_unsafeFlow$inlined;
                        final /* synthetic */ FoldingFeatureObserver this$0;

                        /* JADX INFO: renamed from: androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2$1, reason: invalid class name */
                        @f(c = "androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2", f = "FoldingFeatureObserver.kt", l = {138}, m = "emit")
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

                        public AnonymousClass2(InterfaceC0419g interfaceC0419g, FoldingFeatureObserver foldingFeatureObserver) {
                            this.$this_unsafeFlow$inlined = interfaceC0419g;
                            this.this$0 = foldingFeatureObserver;
                        }

                        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                        @Override // j1.InterfaceC0419g
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public java.lang.Object emit(java.lang.Object r5, L0.d r6) throws java.lang.Throwable {
                            /*
                                r4 = this;
                                boolean r0 = r6 instanceof androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1
                                if (r0 == 0) goto L13
                                r0 = r6
                                androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2$1 r0 = (androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1) r0
                                int r1 = r0.label
                                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                r3 = r1 & r2
                                if (r3 == 0) goto L13
                                int r1 = r1 - r2
                                r0.label = r1
                                goto L18
                            L13:
                                androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2$1 r0 = new androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1$2$1
                                r0.<init>(r6)
                            L18:
                                java.lang.Object r6 = r0.result
                                java.lang.Object r1 = M0.c.c()
                                int r2 = r0.label
                                r3 = 1
                                if (r2 == 0) goto L31
                                if (r2 != r3) goto L29
                                H0.k.b(r6)
                                goto L4a
                            L29:
                                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                                r4.<init>(r5)
                                throw r4
                            L31:
                                H0.k.b(r6)
                                j1.g r6 = r4.$this_unsafeFlow$inlined
                                androidx.window.layout.WindowLayoutInfo r5 = (androidx.window.layout.WindowLayoutInfo) r5
                                androidx.slidingpanelayout.widget.FoldingFeatureObserver r4 = r4.this$0
                                androidx.window.layout.FoldingFeature r4 = androidx.slidingpanelayout.widget.FoldingFeatureObserver.access$getFoldingFeature(r4, r5)
                                if (r4 != 0) goto L41
                                goto L4a
                            L41:
                                r0.label = r3
                                java.lang.Object r4 = r6.emit(r4, r0)
                                if (r4 != r1) goto L4a
                                return r1
                            L4a:
                                H0.s r4 = H0.s.f314a
                                return r4
                            */
                            throw new UnsupportedOperationException("Method not decompiled: androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$mapNotNull$1.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                        }
                    }

                    @Override // j1.InterfaceC0418f
                    public Object collect(InterfaceC0419g interfaceC0419g, d dVar) {
                        Object objCollect = interfaceC0418fWindowLayoutInfo.collect(new AnonymousClass2(interfaceC0419g, foldingFeatureObserver), dVar);
                        return objCollect == c.c() ? objCollect : s.f314a;
                    }
                });
                final FoldingFeatureObserver foldingFeatureObserver2 = FoldingFeatureObserver.this;
                InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: androidx.slidingpanelayout.widget.FoldingFeatureObserver$registerLayoutStateChangeCallback$1$invokeSuspend$$inlined$collect$1
                    @Override // j1.InterfaceC0419g
                    public Object emit(FoldingFeature foldingFeature, d dVar) {
                        s sVar;
                        FoldingFeature foldingFeature2 = foldingFeature;
                        FoldingFeatureObserver.OnFoldingFeatureChangeListener onFoldingFeatureChangeListener = foldingFeatureObserver2.onFoldingFeatureChangeListener;
                        if (onFoldingFeatureChangeListener == null) {
                            sVar = null;
                        } else {
                            onFoldingFeatureChangeListener.onFoldingFeatureChange(foldingFeature2);
                            sVar = s.f314a;
                        }
                        return sVar == c.c() ? sVar : s.f314a;
                    }
                };
                this.label = 1;
                if (interfaceC0418fN.collect(interfaceC0419g, this) == objC) {
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

    public FoldingFeatureObserver(WindowInfoTracker windowInfoTracker, Executor executor) {
        n.g(windowInfoTracker, "windowInfoTracker");
        n.g(executor, "executor");
        this.windowInfoTracker = windowInfoTracker;
        this.executor = executor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FoldingFeature getFoldingFeature(WindowLayoutInfo windowLayoutInfo) {
        Object next;
        Iterator<T> it = windowLayoutInfo.getDisplayFeatures().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((DisplayFeature) next) instanceof FoldingFeature) {
                break;
            }
        }
        if (next instanceof FoldingFeature) {
            return (FoldingFeature) next;
        }
        return null;
    }

    public final void registerLayoutStateChangeCallback(Activity activity) {
        n.g(activity, "activity");
        InterfaceC0380l0 interfaceC0380l0 = this.job;
        if (interfaceC0380l0 != null) {
            InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
        }
        this.job = AbstractC0369g.b(F.a(AbstractC0364d0.a(this.executor)), null, null, new AnonymousClass1(activity, null), 3, null);
    }

    public final void setOnFoldingFeatureChangeListener(OnFoldingFeatureChangeListener onFoldingFeatureChangeListener) {
        n.g(onFoldingFeatureChangeListener, "onFoldingFeatureChangeListener");
        this.onFoldingFeatureChangeListener = onFoldingFeatureChangeListener;
    }

    public final void unregisterLayoutStateChangeCallback() {
        InterfaceC0380l0 interfaceC0380l0 = this.job;
        if (interfaceC0380l0 == null) {
            return;
        }
        InterfaceC0380l0.a.a(interfaceC0380l0, null, 1, null);
    }
}
