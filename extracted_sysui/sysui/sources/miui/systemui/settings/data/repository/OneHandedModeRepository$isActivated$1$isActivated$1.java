package miui.systemui.settings.data.repository;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import i1.q;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.settings.data.repository.OneHandedModeRepository$isActivated$1$isActivated$1", f = "OneHandedModeRepository.kt", l = {74}, m = "invokeSuspend")
public final class OneHandedModeRepository$isActivated$1$isActivated$1 extends l implements Function2 {
    final /* synthetic */ Handler $bgHandler;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ OneHandedModeRepository this$0;

    /* JADX INFO: renamed from: miui.systemui.settings.data.repository.OneHandedModeRepository$isActivated$1$isActivated$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        final /* synthetic */ OneHandedModeRepository$isActivated$1$isActivated$1$observer$1 $observer;
        final /* synthetic */ OneHandedModeRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(OneHandedModeRepository oneHandedModeRepository, OneHandedModeRepository$isActivated$1$isActivated$1$observer$1 oneHandedModeRepository$isActivated$1$isActivated$1$observer$1) {
            super(0);
            this.this$0 = oneHandedModeRepository;
            this.$observer = oneHandedModeRepository$isActivated$1$isActivated$1$observer$1;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m149invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m149invoke() {
            this.this$0.contentResolver.unregisterContentObserver(this.$observer);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OneHandedModeRepository$isActivated$1$isActivated$1(OneHandedModeRepository oneHandedModeRepository, Handler handler, d dVar) {
        super(2, dVar);
        this.this$0 = oneHandedModeRepository;
        this.$bgHandler = handler;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        OneHandedModeRepository$isActivated$1$isActivated$1 oneHandedModeRepository$isActivated$1$isActivated$1 = new OneHandedModeRepository$isActivated$1$isActivated$1(this.this$0, this.$bgHandler, dVar);
        oneHandedModeRepository$isActivated$1$isActivated$1.L$0 = obj;
        return oneHandedModeRepository$isActivated$1$isActivated$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(q qVar, d dVar) {
        return ((OneHandedModeRepository$isActivated$1$isActivated$1) create(qVar, dVar)).invokeSuspend(s.f314a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.database.ContentObserver, miui.systemui.settings.data.repository.OneHandedModeRepository$isActivated$1$isActivated$1$observer$1] */
    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            final q qVar = (q) this.L$0;
            final Handler handler = this.$bgHandler;
            final OneHandedModeRepository oneHandedModeRepository = this.this$0;
            ?? r12 = new ContentObserver(handler) { // from class: miui.systemui.settings.data.repository.OneHandedModeRepository$isActivated$1$isActivated$1$observer$1
                @Override // android.database.ContentObserver
                public void onChange(boolean z2) {
                    qVar.j(Boolean.valueOf(OneHandedModeRepository.isActivated$lambda$1$isActivated(oneHandedModeRepository)));
                }
            };
            this.this$0.contentResolver.registerContentObserver(Settings.Secure.getUriFor("one_handed_mode_activated"), false, r12);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, r12);
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
