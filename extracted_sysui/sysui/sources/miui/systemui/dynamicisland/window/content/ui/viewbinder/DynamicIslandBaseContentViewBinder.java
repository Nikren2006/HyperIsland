package miui.systemui.dynamicisland.window.content.ui.viewbinder;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.b;
import N0.f;
import N0.l;
import V0.n;
import android.view.View;
import g1.AbstractC0369g;
import g1.E;
import j1.AbstractC0420h;
import j1.I;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import miui.systemui.dynamicisland.data.repository.DynamicIslandSizeRepository;
import miui.systemui.dynamicisland.window.content.DynamicIslandBaseContentView;
import miui.systemui.lifecycle.RepeatWhenAttachedKt;

/* JADX INFO: loaded from: classes3.dex */
public final class DynamicIslandBaseContentViewBinder {
    public static final DynamicIslandBaseContentViewBinder INSTANCE = new DynamicIslandBaseContentViewBinder();

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandBaseContentViewBinder$bind$1, reason: invalid class name */
    @f(c = "miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandBaseContentViewBinder$bind$1", f = "DynamicIslandBaseContentViewBinder.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends l implements Function3 {
        final /* synthetic */ DynamicIslandSizeRepository $sizeRepository;
        final /* synthetic */ DynamicIslandBaseContentView $view;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandBaseContentViewBinder$bind$1$1, reason: invalid class name and collision with other inner class name */
        @f(c = "miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandBaseContentViewBinder$bind$1$1", f = "DynamicIslandBaseContentViewBinder.kt", l = {17}, m = "invokeSuspend")
        public static final class C01351 extends l implements Function2 {
            final /* synthetic */ DynamicIslandSizeRepository $sizeRepository;
            final /* synthetic */ DynamicIslandBaseContentView $view;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01351(DynamicIslandSizeRepository dynamicIslandSizeRepository, DynamicIslandBaseContentView dynamicIslandBaseContentView, d dVar) {
                super(2, dVar);
                this.$sizeRepository = dynamicIslandSizeRepository;
                this.$view = dynamicIslandBaseContentView;
            }

            @Override // N0.a
            public final d create(Object obj, d dVar) {
                return new C01351(this.$sizeRepository, this.$view, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(E e2, d dVar) {
                return ((C01351) create(e2, dVar)).invokeSuspend(s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object objC = c.c();
                int i2 = this.label;
                if (i2 == 0) {
                    k.b(obj);
                    I cutoutY = this.$sizeRepository.getCutoutY();
                    final DynamicIslandBaseContentView dynamicIslandBaseContentView = this.$view;
                    InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandBaseContentViewBinder.bind.1.1.1
                        @Override // j1.InterfaceC0419g
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                            return emit(((Number) obj2).floatValue(), dVar);
                        }

                        public final Object emit(float f2, d dVar) {
                            dynamicIslandBaseContentView.setCutoutY(f2);
                            return s.f314a;
                        }
                    };
                    this.label = 1;
                    if (cutoutY.collect(interfaceC0419g, this) == objC) {
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

        /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandBaseContentViewBinder$bind$1$2, reason: invalid class name */
        @f(c = "miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandBaseContentViewBinder$bind$1$2", f = "DynamicIslandBaseContentViewBinder.kt", l = {28}, m = "invokeSuspend")
        public static final class AnonymousClass2 extends l implements Function2 {
            final /* synthetic */ DynamicIslandSizeRepository $sizeRepository;
            final /* synthetic */ DynamicIslandBaseContentView $view;
            int label;

            /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandBaseContentViewBinder$bind$1$2$1, reason: invalid class name and collision with other inner class name */
            @f(c = "miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandBaseContentViewBinder$bind$1$2$1", f = "DynamicIslandBaseContentViewBinder.kt", l = {}, m = "invokeSuspend")
            public static final class C01371 extends l implements n {
                /* synthetic */ float F$0;
                /* synthetic */ float F$1;
                /* synthetic */ float F$2;
                int label;

                public C01371(d dVar) {
                    super(4, dVar);
                }

                public final Object invoke(float f2, float f3, float f4, d dVar) {
                    C01371 c01371 = new C01371(dVar);
                    c01371.F$0 = f2;
                    c01371.F$1 = f3;
                    c01371.F$2 = f4;
                    return c01371.invokeSuspend(s.f314a);
                }

                @Override // N0.a
                public final Object invokeSuspend(Object obj) throws Throwable {
                    c.c();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k.b(obj);
                    return new H0.n(b.b(this.F$0), b.b(this.F$1), b.b(this.F$2));
                }

                @Override // V0.n
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    return invoke(((Number) obj).floatValue(), ((Number) obj2).floatValue(), ((Number) obj3).floatValue(), (d) obj4);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(DynamicIslandSizeRepository dynamicIslandSizeRepository, DynamicIslandBaseContentView dynamicIslandBaseContentView, d dVar) {
                super(2, dVar);
                this.$sizeRepository = dynamicIslandSizeRepository;
                this.$view = dynamicIslandBaseContentView;
            }

            @Override // N0.a
            public final d create(Object obj, d dVar) {
                return new AnonymousClass2(this.$sizeRepository, this.$view, dVar);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(E e2, d dVar) {
                return ((AnonymousClass2) create(e2, dVar)).invokeSuspend(s.f314a);
            }

            @Override // N0.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object objC = c.c();
                int i2 = this.label;
                if (i2 == 0) {
                    k.b(obj);
                    InterfaceC0418f interfaceC0418fI = AbstractC0420h.i(this.$sizeRepository.getIslandMaxWidth(), this.$sizeRepository.getIslandClockWidth(), this.$sizeRepository.getIslandBatteryWidth(), new C01371(null));
                    final DynamicIslandBaseContentView dynamicIslandBaseContentView = this.$view;
                    InterfaceC0419g interfaceC0419g = new InterfaceC0419g() { // from class: miui.systemui.dynamicisland.window.content.ui.viewbinder.DynamicIslandBaseContentViewBinder.bind.1.2.2
                        @Override // j1.InterfaceC0419g
                        public final Object emit(H0.n nVar, d dVar) {
                            dynamicIslandBaseContentView.setMaxWidth(((Number) nVar.a()).floatValue(), ((Number) nVar.b()).floatValue(), ((Number) nVar.c()).floatValue());
                            return s.f314a;
                        }
                    };
                    this.label = 1;
                    if (interfaceC0418fI.collect(interfaceC0419g, this) == objC) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DynamicIslandSizeRepository dynamicIslandSizeRepository, DynamicIslandBaseContentView dynamicIslandBaseContentView, d dVar) {
            super(3, dVar);
            this.$sizeRepository = dynamicIslandSizeRepository;
            this.$view = dynamicIslandBaseContentView;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(E e2, View view, d dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$sizeRepository, this.$view, dVar);
            anonymousClass1.L$0 = e2;
            return anonymousClass1.invokeSuspend(s.f314a);
        }

        @Override // N0.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            c.c();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k.b(obj);
            E e2 = (E) this.L$0;
            AbstractC0369g.b(e2, null, null, new C01351(this.$sizeRepository, this.$view, null), 3, null);
            AbstractC0369g.b(e2, null, null, new AnonymousClass2(this.$sizeRepository, this.$view, null), 3, null);
            return s.f314a;
        }
    }

    private DynamicIslandBaseContentViewBinder() {
    }

    public final void bind(DynamicIslandBaseContentView view, DynamicIslandSizeRepository sizeRepository) {
        kotlin.jvm.internal.n.g(view, "view");
        kotlin.jvm.internal.n.g(sizeRepository, "sizeRepository");
        RepeatWhenAttachedKt.repeatWhenAttached$default(view, null, new AnonymousClass1(sizeRepository, view, null), 1, null);
    }
}
