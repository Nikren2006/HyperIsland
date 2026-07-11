package androidx.activity;

import H0.k;
import H0.s;
import N0.l;
import android.view.View;
import android.view.ViewTreeObserver;
import i1.q;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
@N0.f(c = "androidx.activity.PipHintTrackerKt$trackPipAnimationHintView$flow$1", f = "PipHintTracker.kt", l = {85}, m = "invokeSuspend")
public final class PipHintTrackerKt$trackPipAnimationHintView$flow$1 extends l implements Function2 {
    final /* synthetic */ View $view;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: androidx.activity.PipHintTrackerKt$trackPipAnimationHintView$flow$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        final /* synthetic */ PipHintTrackerKt$trackPipAnimationHintView$flow$1$attachStateChangeListener$1 $attachStateChangeListener;
        final /* synthetic */ View.OnLayoutChangeListener $layoutChangeListener;
        final /* synthetic */ ViewTreeObserver.OnScrollChangedListener $scrollChangeListener;
        final /* synthetic */ View $view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(View view, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener, View.OnLayoutChangeListener onLayoutChangeListener, PipHintTrackerKt$trackPipAnimationHintView$flow$1$attachStateChangeListener$1 pipHintTrackerKt$trackPipAnimationHintView$flow$1$attachStateChangeListener$1) {
            super(0);
            this.$view = view;
            this.$scrollChangeListener = onScrollChangedListener;
            this.$layoutChangeListener = onLayoutChangeListener;
            this.$attachStateChangeListener = pipHintTrackerKt$trackPipAnimationHintView$flow$1$attachStateChangeListener$1;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m5invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m5invoke() {
            this.$view.getViewTreeObserver().removeOnScrollChangedListener(this.$scrollChangeListener);
            this.$view.removeOnLayoutChangeListener(this.$layoutChangeListener);
            this.$view.removeOnAttachStateChangeListener(this.$attachStateChangeListener);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PipHintTrackerKt$trackPipAnimationHintView$flow$1(View view, L0.d dVar) {
        super(2, dVar);
        this.$view = view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(q qVar, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (i2 == i6 && i4 == i8 && i3 == i7 && i5 == i9) {
            return;
        }
        qVar.j(PipHintTrackerKt.trackPipAnimationHintView$positionInWindow(view));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1(q qVar, View view) {
        qVar.j(PipHintTrackerKt.trackPipAnimationHintView$positionInWindow(view));
    }

    @Override // N0.a
    public final L0.d create(Object obj, L0.d dVar) {
        PipHintTrackerKt$trackPipAnimationHintView$flow$1 pipHintTrackerKt$trackPipAnimationHintView$flow$1 = new PipHintTrackerKt$trackPipAnimationHintView$flow$1(this.$view, dVar);
        pipHintTrackerKt$trackPipAnimationHintView$flow$1.L$0 = obj;
        return pipHintTrackerKt$trackPipAnimationHintView$flow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(q qVar, L0.d dVar) {
        return ((PipHintTrackerKt$trackPipAnimationHintView$flow$1) create(qVar, dVar)).invokeSuspend(s.f314a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.view.View$OnAttachStateChangeListener, androidx.activity.PipHintTrackerKt$trackPipAnimationHintView$flow$1$attachStateChangeListener$1] */
    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = M0.c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            final q qVar = (q) this.L$0;
            final View.OnLayoutChangeListener onLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: androidx.activity.i
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                    PipHintTrackerKt$trackPipAnimationHintView$flow$1.invokeSuspend$lambda$0(qVar, view, i3, i4, i5, i6, i7, i8, i9, i10);
                }
            };
            final View view = this.$view;
            final ViewTreeObserver.OnScrollChangedListener onScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() { // from class: androidx.activity.j
                @Override // android.view.ViewTreeObserver.OnScrollChangedListener
                public final void onScrollChanged() {
                    PipHintTrackerKt$trackPipAnimationHintView$flow$1.invokeSuspend$lambda$1(qVar, view);
                }
            };
            final View view2 = this.$view;
            ?? r3 = new View.OnAttachStateChangeListener() { // from class: androidx.activity.PipHintTrackerKt$trackPipAnimationHintView$flow$1$attachStateChangeListener$1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view3) {
                    qVar.j(PipHintTrackerKt.trackPipAnimationHintView$positionInWindow(view2));
                    view2.getViewTreeObserver().addOnScrollChangedListener(onScrollChangedListener);
                    view2.addOnLayoutChangeListener(onLayoutChangeListener);
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view3) {
                    view3.getViewTreeObserver().removeOnScrollChangedListener(onScrollChangedListener);
                    view3.removeOnLayoutChangeListener(onLayoutChangeListener);
                }
            };
            if (Api19Impl.INSTANCE.isAttachedToWindow(this.$view)) {
                qVar.j(PipHintTrackerKt.trackPipAnimationHintView$positionInWindow(this.$view));
                this.$view.getViewTreeObserver().addOnScrollChangedListener(onScrollChangedListener);
                this.$view.addOnLayoutChangeListener(onLayoutChangeListener);
            }
            this.$view.addOnAttachStateChangeListener(r3);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$view, onScrollChangedListener, onLayoutChangeListener, r3);
            this.label = 1;
            if (i1.o.a(qVar, anonymousClass1, this) == objC) {
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
