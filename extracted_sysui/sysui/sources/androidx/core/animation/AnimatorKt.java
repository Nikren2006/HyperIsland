package androidx.core.animation;

import H0.s;
import android.animation.Animator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class AnimatorKt {

    /* JADX INFO: renamed from: androidx.core.animation.AnimatorKt$addListener$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(Animator animator) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Animator) obj);
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: androidx.core.animation.AnimatorKt$addListener$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function1 {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final void invoke(Animator animator) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Animator) obj);
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: androidx.core.animation.AnimatorKt$addListener$3, reason: invalid class name */
    public static final class AnonymousClass3 extends o implements Function1 {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(1);
        }

        public final void invoke(Animator animator) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Animator) obj);
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: androidx.core.animation.AnimatorKt$addListener$4, reason: invalid class name */
    public static final class AnonymousClass4 extends o implements Function1 {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
            super(1);
        }

        public final void invoke(Animator animator) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Animator) obj);
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: androidx.core.animation.AnimatorKt$addPauseListener$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02011 extends o implements Function1 {
        public static final C02011 INSTANCE = new C02011();

        public C02011() {
            super(1);
        }

        public final void invoke(Animator animator) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Animator) obj);
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: androidx.core.animation.AnimatorKt$addPauseListener$2, reason: invalid class name and case insensitive filesystem */
    public static final class C02022 extends o implements Function1 {
        public static final C02022 INSTANCE = new C02022();

        public C02022() {
            super(1);
        }

        public final void invoke(Animator animator) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Animator) obj);
            return s.f314a;
        }
    }

    public static final Animator.AnimatorListener addListener(Animator animator, Function1 function1, Function1 function12, Function1 function13, Function1 function14) {
        AnimatorKt$addListener$listener$1 animatorKt$addListener$listener$1 = new AnimatorKt$addListener$listener$1(function14, function1, function13, function12);
        animator.addListener(animatorKt$addListener$listener$1);
        return animatorKt$addListener$listener$1;
    }

    public static /* synthetic */ Animator.AnimatorListener addListener$default(Animator animator, Function1 function1, Function1 function12, Function1 function13, Function1 function14, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            function1 = AnonymousClass1.INSTANCE;
        }
        if ((i2 & 2) != 0) {
            function12 = AnonymousClass2.INSTANCE;
        }
        if ((i2 & 4) != 0) {
            function13 = AnonymousClass3.INSTANCE;
        }
        if ((i2 & 8) != 0) {
            function14 = AnonymousClass4.INSTANCE;
        }
        AnimatorKt$addListener$listener$1 animatorKt$addListener$listener$1 = new AnimatorKt$addListener$listener$1(function14, function1, function13, function12);
        animator.addListener(animatorKt$addListener$listener$1);
        return animatorKt$addListener$listener$1;
    }

    public static final Animator.AnimatorPauseListener addPauseListener(Animator animator, final Function1 function1, final Function1 function12) {
        Animator.AnimatorPauseListener animatorPauseListener = new Animator.AnimatorPauseListener() { // from class: androidx.core.animation.AnimatorKt$addPauseListener$listener$1
            @Override // android.animation.Animator.AnimatorPauseListener
            public void onAnimationPause(Animator animator2) {
                function12.invoke(animator2);
            }

            @Override // android.animation.Animator.AnimatorPauseListener
            public void onAnimationResume(Animator animator2) {
                function1.invoke(animator2);
            }
        };
        animator.addPauseListener(animatorPauseListener);
        return animatorPauseListener;
    }

    public static /* synthetic */ Animator.AnimatorPauseListener addPauseListener$default(Animator animator, Function1 function1, Function1 function12, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            function1 = C02011.INSTANCE;
        }
        if ((i2 & 2) != 0) {
            function12 = C02022.INSTANCE;
        }
        return addPauseListener(animator, function1, function12);
    }

    public static final Animator.AnimatorListener doOnCancel(Animator animator, final Function1 function1) {
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() { // from class: androidx.core.animation.AnimatorKt$doOnCancel$$inlined$addListener$default$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator2) {
                function1.invoke(animator2);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
            }
        };
        animator.addListener(animatorListener);
        return animatorListener;
    }

    public static final Animator.AnimatorListener doOnEnd(Animator animator, final Function1 function1) {
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() { // from class: androidx.core.animation.AnimatorKt$doOnEnd$$inlined$addListener$default$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                function1.invoke(animator2);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
            }
        };
        animator.addListener(animatorListener);
        return animatorListener;
    }

    public static final Animator.AnimatorPauseListener doOnPause(Animator animator, Function1 function1) {
        return addPauseListener$default(animator, null, function1, 1, null);
    }

    public static final Animator.AnimatorListener doOnRepeat(Animator animator, final Function1 function1) {
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() { // from class: androidx.core.animation.AnimatorKt$doOnRepeat$$inlined$addListener$default$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator2) {
                function1.invoke(animator2);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
            }
        };
        animator.addListener(animatorListener);
        return animatorListener;
    }

    public static final Animator.AnimatorPauseListener doOnResume(Animator animator, Function1 function1) {
        return addPauseListener$default(animator, function1, null, 2, null);
    }

    public static final Animator.AnimatorListener doOnStart(Animator animator, final Function1 function1) {
        Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() { // from class: androidx.core.animation.AnimatorKt$doOnStart$$inlined$addListener$default$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
                function1.invoke(animator2);
            }
        };
        animator.addListener(animatorListener);
        return animatorListener;
    }
}
