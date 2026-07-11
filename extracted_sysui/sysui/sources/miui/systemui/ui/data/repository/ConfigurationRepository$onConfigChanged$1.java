package miui.systemui.ui.data.repository;

import H0.k;
import H0.s;
import L0.d;
import M0.c;
import N0.f;
import N0.l;
import android.content.res.Configuration;
import i1.q;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.autodensity.AutoDensityController;

/* JADX INFO: loaded from: classes4.dex */
@f(c = "miui.systemui.ui.data.repository.ConfigurationRepository$onConfigChanged$1", f = "ConfigurationRepository.kt", l = {36}, m = "invokeSuspend")
public final class ConfigurationRepository$onConfigChanged$1 extends l implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ConfigurationRepository this$0;

    /* JADX INFO: renamed from: miui.systemui.ui.data.repository.ConfigurationRepository$onConfigChanged$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        final /* synthetic */ ConfigurationRepository$onConfigChanged$1$listener$1 $listener;
        final /* synthetic */ ConfigurationRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ConfigurationRepository configurationRepository, ConfigurationRepository$onConfigChanged$1$listener$1 configurationRepository$onConfigChanged$1$listener$1) {
            super(0);
            this.this$0 = configurationRepository;
            this.$listener = configurationRepository$onConfigChanged$1$listener$1;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m154invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m154invoke() {
            this.this$0.autoDensityController.removeOnDensityChangeListener(this.$listener);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConfigurationRepository$onConfigChanged$1(ConfigurationRepository configurationRepository, d dVar) {
        super(2, dVar);
        this.this$0 = configurationRepository;
    }

    @Override // N0.a
    public final d create(Object obj, d dVar) {
        ConfigurationRepository$onConfigChanged$1 configurationRepository$onConfigChanged$1 = new ConfigurationRepository$onConfigChanged$1(this.this$0, dVar);
        configurationRepository$onConfigChanged$1.L$0 = obj;
        return configurationRepository$onConfigChanged$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(q qVar, d dVar) {
        return ((ConfigurationRepository$onConfigChanged$1) create(qVar, dVar)).invokeSuspend(s.f314a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [miui.systemui.autodensity.AutoDensityController$OnDensityChangeListener, miui.systemui.ui.data.repository.ConfigurationRepository$onConfigChanged$1$listener$1] */
    @Override // N0.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objC = c.c();
        int i2 = this.label;
        if (i2 == 0) {
            k.b(obj);
            final q qVar = (q) this.L$0;
            ?? r12 = new AutoDensityController.OnDensityChangeListener() { // from class: miui.systemui.ui.data.repository.ConfigurationRepository$onConfigChanged$1$listener$1
                @Override // miui.systemui.autodensity.AutoDensityController.OnDensityChangeListener
                public void onConfigChanged(Configuration config) {
                    n.g(config, "config");
                    qVar.j(s.f314a);
                }
            };
            this.this$0.autoDensityController.addOnDensityChangeListener(r12);
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
