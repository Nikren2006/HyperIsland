package androidx.core.transition;

import H0.s;
import android.transition.Transition;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class TransitionKt {

    /* JADX INFO: renamed from: androidx.core.transition.TransitionKt$addListener$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function1 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(Transition transition) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Transition) obj);
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: androidx.core.transition.TransitionKt$addListener$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function1 {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final void invoke(Transition transition) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Transition) obj);
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: androidx.core.transition.TransitionKt$addListener$3, reason: invalid class name */
    public static final class AnonymousClass3 extends o implements Function1 {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(1);
        }

        public final void invoke(Transition transition) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Transition) obj);
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: androidx.core.transition.TransitionKt$addListener$4, reason: invalid class name */
    public static final class AnonymousClass4 extends o implements Function1 {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
            super(1);
        }

        public final void invoke(Transition transition) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Transition) obj);
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: androidx.core.transition.TransitionKt$addListener$5, reason: invalid class name */
    public static final class AnonymousClass5 extends o implements Function1 {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        public AnonymousClass5() {
            super(1);
        }

        public final void invoke(Transition transition) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Transition) obj);
            return s.f314a;
        }
    }

    public static final Transition.TransitionListener addListener(Transition transition, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15) {
        TransitionKt$addListener$listener$1 transitionKt$addListener$listener$1 = new TransitionKt$addListener$listener$1(function1, function14, function15, function13, function12);
        transition.addListener(transitionKt$addListener$listener$1);
        return transitionKt$addListener$listener$1;
    }

    public static /* synthetic */ Transition.TransitionListener addListener$default(Transition transition, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            function1 = AnonymousClass1.INSTANCE;
        }
        if ((i2 & 2) != 0) {
            function12 = AnonymousClass2.INSTANCE;
        }
        Function1 function16 = function12;
        if ((i2 & 4) != 0) {
            function13 = AnonymousClass3.INSTANCE;
        }
        Function1 function17 = function13;
        if ((i2 & 8) != 0) {
            function14 = AnonymousClass4.INSTANCE;
        }
        if ((i2 & 16) != 0) {
            function15 = AnonymousClass5.INSTANCE;
        }
        TransitionKt$addListener$listener$1 transitionKt$addListener$listener$1 = new TransitionKt$addListener$listener$1(function1, function14, function15, function17, function16);
        transition.addListener(transitionKt$addListener$listener$1);
        return transitionKt$addListener$listener$1;
    }

    public static final Transition.TransitionListener doOnCancel(Transition transition, final Function1 function1) {
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnCancel$$inlined$addListener$default$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(Transition transition2) {
                function1.invoke(transition2);
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition2) {
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }

    public static final Transition.TransitionListener doOnEnd(Transition transition, final Function1 function1) {
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnEnd$$inlined$addListener$default$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition2) {
                function1.invoke(transition2);
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition2) {
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }

    public static final Transition.TransitionListener doOnPause(Transition transition, final Function1 function1) {
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnPause$$inlined$addListener$default$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition2) {
                function1.invoke(transition2);
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition2) {
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }

    public static final Transition.TransitionListener doOnResume(Transition transition, final Function1 function1) {
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnResume$$inlined$addListener$default$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition2) {
                function1.invoke(transition2);
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition2) {
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }

    public static final Transition.TransitionListener doOnStart(Transition transition, final Function1 function1) {
        Transition.TransitionListener transitionListener = new Transition.TransitionListener() { // from class: androidx.core.transition.TransitionKt$doOnStart$$inlined$addListener$default$1
            @Override // android.transition.Transition.TransitionListener
            public void onTransitionCancel(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionPause(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionResume(Transition transition2) {
            }

            @Override // android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition2) {
                function1.invoke(transition2);
            }
        };
        transition.addListener(transitionListener);
        return transitionListener;
    }
}
