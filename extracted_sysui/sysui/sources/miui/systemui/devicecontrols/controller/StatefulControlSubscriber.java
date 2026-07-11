package miui.systemui.devicecontrols.controller;

import android.os.IBinder;
import android.service.controls.Control;
import android.service.controls.IControlsSubscriber;
import android.service.controls.IControlsSubscription;
import android.util.Log;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class StatefulControlSubscriber extends IControlsSubscriber.Stub {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "StatefulControlSubscriber";
    private final DelayableExecutor bgExecutor;
    private final ControlsController controller;
    private final ControlsProviderLifecycleManager provider;
    private final long requestLimit;
    private IControlsSubscription subscription;
    private boolean subscriptionOpen;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.controller.StatefulControlSubscriber$onComplete$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function0 {
        public AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m113invoke();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m113invoke() {
            if (StatefulControlSubscriber.this.subscriptionOpen) {
                StatefulControlSubscriber.this.subscriptionOpen = false;
                Log.i(StatefulControlSubscriber.TAG, "onComplete receive from '" + StatefulControlSubscriber.this.provider.getComponentName() + "'");
            }
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.controller.StatefulControlSubscriber$onError$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04911 extends kotlin.jvm.internal.o implements Function0 {
        final /* synthetic */ String $error;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04911(String str) {
            super(0);
            this.$error = str;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m114invoke();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m114invoke() {
            if (StatefulControlSubscriber.this.subscriptionOpen) {
                StatefulControlSubscriber.this.subscriptionOpen = false;
                Log.e(StatefulControlSubscriber.TAG, "onError receive from '" + StatefulControlSubscriber.this.provider.getComponentName() + "': " + this.$error);
            }
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.controller.StatefulControlSubscriber$onNext$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04921 extends kotlin.jvm.internal.o implements Function0 {
        final /* synthetic */ Control $control;
        final /* synthetic */ IBinder $token;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04921(IBinder iBinder, Control control) {
            super(0);
            this.$token = iBinder;
            this.$control = control;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m115invoke();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m115invoke() {
            if (StatefulControlSubscriber.this.subscriptionOpen) {
                StatefulControlSubscriber.this.controller.refreshStatus(StatefulControlSubscriber.this.provider.getComponentName(), this.$control);
                return;
            }
            Log.w(StatefulControlSubscriber.TAG, "Refresh outside of window for token:" + this.$token);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.controller.StatefulControlSubscriber$onSubscribe$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04931 extends kotlin.jvm.internal.o implements Function0 {
        final /* synthetic */ IControlsSubscription $subs;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04931(IControlsSubscription iControlsSubscription) {
            super(0);
            this.$subs = iControlsSubscription;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m116invoke();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m116invoke() {
            StatefulControlSubscriber.this.subscriptionOpen = true;
            StatefulControlSubscriber.this.subscription = this.$subs;
            StatefulControlSubscriber.this.provider.startSubscription(this.$subs, StatefulControlSubscriber.this.requestLimit);
        }
    }

    public StatefulControlSubscriber(ControlsController controller, ControlsProviderLifecycleManager provider, DelayableExecutor bgExecutor, long j2) {
        kotlin.jvm.internal.n.g(controller, "controller");
        kotlin.jvm.internal.n.g(provider, "provider");
        kotlin.jvm.internal.n.g(bgExecutor, "bgExecutor");
        this.controller = controller;
        this.provider = provider;
        this.bgExecutor = bgExecutor;
        this.requestLimit = j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void cancel$lambda$2(StatefulControlSubscriber this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (this$0.subscriptionOpen) {
            this$0.subscriptionOpen = false;
            IControlsSubscription iControlsSubscription = this$0.subscription;
            if (iControlsSubscription != null) {
                this$0.provider.cancelSubscription(iControlsSubscription);
            }
            this$0.subscription = null;
        }
    }

    private final void run(IBinder iBinder, final Function0 function0) {
        Log.d(TAG, this.provider.getToken() + ",  token: " + iBinder);
        if (kotlin.jvm.internal.n.c(this.provider.getToken(), iBinder)) {
            this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.E
                @Override // java.lang.Runnable
                public final void run() {
                    StatefulControlSubscriber.run$lambda$0(function0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void run$lambda$0(Function0 f2) {
        kotlin.jvm.internal.n.g(f2, "$f");
        f2.invoke();
    }

    public final void cancel() {
        if (this.subscriptionOpen) {
            this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.controller.F
                @Override // java.lang.Runnable
                public final void run() {
                    StatefulControlSubscriber.cancel$lambda$2(this.f5532a);
                }
            });
        }
    }

    @Override // android.service.controls.IControlsSubscriber
    public void onComplete(IBinder token) {
        kotlin.jvm.internal.n.g(token, "token");
        run(token, new AnonymousClass1());
    }

    @Override // android.service.controls.IControlsSubscriber
    public void onError(IBinder token, String error) {
        kotlin.jvm.internal.n.g(token, "token");
        kotlin.jvm.internal.n.g(error, "error");
        run(token, new C04911(error));
    }

    @Override // android.service.controls.IControlsSubscriber
    public void onNext(IBinder token, Control control) {
        kotlin.jvm.internal.n.g(token, "token");
        kotlin.jvm.internal.n.g(control, "control");
        run(token, new C04921(token, control));
    }

    @Override // android.service.controls.IControlsSubscriber
    public void onSubscribe(IBinder token, IControlsSubscription subs) {
        kotlin.jvm.internal.n.g(token, "token");
        kotlin.jvm.internal.n.g(subs, "subs");
        run(token, new C04931(subs));
    }
}
