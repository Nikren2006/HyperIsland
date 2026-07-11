package miui.systemui.dynamicisland.window;

import H0.s;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewGroupKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes3.dex */
@N0.f(c = "miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$children$1", f = "DynamicIslandWindowViewController.kt", l = {137}, m = "invokeSuspend")
public final class DynamicIslandWindowViewController$children$1 extends N0.l implements Function2 {
    final /* synthetic */ DynamicIslandWindowView $view;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$children$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function0 {
        final /* synthetic */ DynamicIslandWindowView $view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DynamicIslandWindowView dynamicIslandWindowView) {
            super(0);
            this.$view = dynamicIslandWindowView;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m133invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m133invoke() {
            this.$view.setOnHierarchyChangeListener(null);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicIslandWindowViewController$children$1(DynamicIslandWindowView dynamicIslandWindowView, L0.d dVar) {
        super(2, dVar);
        this.$view = dynamicIslandWindowView;
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        DynamicIslandWindowViewController$children$1 dynamicIslandWindowViewController$children$1 = new DynamicIslandWindowViewController$children$1(this.$view, dVar);
        dynamicIslandWindowViewController$children$1.L$0 = obj;
        return dynamicIslandWindowViewController$children$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(i1.q qVar, L0.d dVar) {
        return ((DynamicIslandWindowViewController$children$1) create(qVar, dVar)).invokeSuspend(s.f314a);
    }

    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            H0.k.b(obj);
            final i1.q qVar = (i1.q) this.L$0;
            final DynamicIslandWindowView dynamicIslandWindowView = this.$view;
            this.$view.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() { // from class: miui.systemui.dynamicisland.window.DynamicIslandWindowViewController$children$1$listener$1
                @Override // android.view.ViewGroup.OnHierarchyChangeListener
                public void onChildViewAdded(View view, View view2) {
                    qVar.j(e1.l.r(ViewGroupKt.getChildren(dynamicIslandWindowView)));
                }

                @Override // android.view.ViewGroup.OnHierarchyChangeListener
                public void onChildViewRemoved(View view, View view2) {
                    qVar.j(e1.l.r(ViewGroupKt.getChildren(dynamicIslandWindowView)));
                }
            });
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$view);
            this.label = 1;
            if (i1.o.a(qVar, anonymousClass1, this) == objC) {
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
