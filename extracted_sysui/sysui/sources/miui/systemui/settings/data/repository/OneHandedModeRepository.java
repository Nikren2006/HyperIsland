package miui.systemui.settings.data.repository;

import H0.s;
import M0.c;
import N0.d;
import N0.f;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import g1.E;
import j1.AbstractC0420h;
import j1.E;
import j1.I;
import j1.InterfaceC0418f;
import j1.InterfaceC0419g;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.ui.data.repository.ConfigurationRepository;
import miui.systemui.util.SystemUIResourcesHelper;
import miui.systemui.util.coroutines.flow.FlowConflatedKt;

/* JADX INFO: loaded from: classes4.dex */
public final class OneHandedModeRepository {
    public static final Companion Companion = new Companion(null);
    private static final float DEFAULT_CONFIG_ONE_HANDED_OFFSET = 0.4f;
    private static final String RES_NAME_CONFIG_ONE_HANDED_OFFSET = "config_one_handed_offset";
    private static final String TAG = "OneHandedModeRepository";
    private final ContentResolver contentResolver;
    private final I isActivated;
    private final I isEnabled;
    private final I offset;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public OneHandedModeRepository(@Plugin E scope, Context context, @Background Handler bgHandler, ConfigurationRepository configurationRepository, final SystemUIResourcesHelper sysUIResHelper) {
        n.g(scope, "scope");
        n.g(context, "context");
        n.g(bgHandler, "bgHandler");
        n.g(configurationRepository, "configurationRepository");
        n.g(sysUIResHelper, "sysUIResHelper");
        this.contentResolver = context.getContentResolver();
        InterfaceC0418f interfaceC0418fConflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new OneHandedModeRepository$isEnabled$1$1(this, bgHandler, null));
        E.a aVar = j1.E.f4648a;
        I iB = AbstractC0420h.B(interfaceC0418fConflatedCallbackFlow, scope, aVar.c(), Boolean.valueOf(isEnabled$lambda$0$isEnabled(this)));
        this.isEnabled = iB;
        this.isActivated = AbstractC0420h.B(AbstractC0420h.l(iB, FlowConflatedKt.conflatedCallbackFlow(new OneHandedModeRepository$isActivated$1$isActivated$1(this, bgHandler, null)), new OneHandedModeRepository$isActivated$1$1(null)), scope, aVar.c(), Boolean.valueOf(((Boolean) iB.getValue()).booleanValue() && isActivated$lambda$1$isActivated(this)));
        final InterfaceC0418f interfaceC0418fY = AbstractC0420h.y(configurationRepository.getOnConfigChanged(), new OneHandedModeRepository$offset$1(null));
        this.offset = AbstractC0420h.B(new InterfaceC0418f() { // from class: miui.systemui.settings.data.repository.OneHandedModeRepository$special$$inlined$map$1

            /* JADX INFO: renamed from: miui.systemui.settings.data.repository.OneHandedModeRepository$special$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements InterfaceC0419g {
                final /* synthetic */ SystemUIResourcesHelper $sysUIResHelper$inlined;
                final /* synthetic */ InterfaceC0419g $this_unsafeFlow;

                /* JADX INFO: renamed from: miui.systemui.settings.data.repository.OneHandedModeRepository$special$$inlined$map$1$2$1, reason: invalid class name */
                @f(c = "miui.systemui.settings.data.repository.OneHandedModeRepository$special$$inlined$map$1$2", f = "OneHandedModeRepository.kt", l = {223}, m = "emit")
                public static final class AnonymousClass1 extends d {
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

                public AnonymousClass2(InterfaceC0419g interfaceC0419g, SystemUIResourcesHelper systemUIResourcesHelper) {
                    this.$this_unsafeFlow = interfaceC0419g;
                    this.$sysUIResHelper$inlined = systemUIResourcesHelper;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // j1.InterfaceC0419g
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r11, L0.d r12) throws java.lang.Throwable {
                    /*
                        r10 = this;
                        boolean r0 = r12 instanceof miui.systemui.settings.data.repository.OneHandedModeRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r12
                        miui.systemui.settings.data.repository.OneHandedModeRepository$special$$inlined$map$1$2$1 r0 = (miui.systemui.settings.data.repository.OneHandedModeRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        miui.systemui.settings.data.repository.OneHandedModeRepository$special$$inlined$map$1$2$1 r0 = new miui.systemui.settings.data.repository.OneHandedModeRepository$special$$inlined$map$1$2$1
                        r0.<init>(r12)
                    L18:
                        java.lang.Object r12 = r0.result
                        java.lang.Object r1 = M0.c.c()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r3) goto L29
                        H0.k.b(r12)
                        goto L5b
                    L29:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r11)
                        throw r10
                    L31:
                        H0.k.b(r12)
                        j1.g r12 = r10.$this_unsafeFlow
                        H0.s r11 = (H0.s) r11
                        miui.systemui.util.SystemUIResourcesHelper r4 = r10.$sysUIResHelper$inlined
                        r8 = 6
                        r9 = 0
                        java.lang.String r5 = "config_one_handed_offset"
                        r6 = 0
                        r7 = 0
                        java.lang.Float r10 = miui.systemui.util.SystemUIResourcesHelper.getFraction$default(r4, r5, r6, r7, r8, r9)
                        if (r10 == 0) goto L4b
                        float r10 = r10.floatValue()
                        goto L4e
                    L4b:
                        r10 = 1053609165(0x3ecccccd, float:0.4)
                    L4e:
                        java.lang.Float r10 = N0.b.b(r10)
                        r0.label = r3
                        java.lang.Object r10 = r12.emit(r10, r0)
                        if (r10 != r1) goto L5b
                        return r1
                    L5b:
                        H0.s r10 = H0.s.f314a
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: miui.systemui.settings.data.repository.OneHandedModeRepository$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, L0.d):java.lang.Object");
                }
            }

            @Override // j1.InterfaceC0418f
            public Object collect(InterfaceC0419g interfaceC0419g, L0.d dVar) {
                Object objCollect = interfaceC0418fY.collect(new AnonymousClass2(interfaceC0419g, sysUIResHelper), dVar);
                return objCollect == c.c() ? objCollect : s.f314a;
            }
        }, scope, aVar.c(), Float.valueOf(0.4f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isActivated$lambda$1$isActivated(OneHandedModeRepository oneHandedModeRepository) {
        return Settings.Secure.getInt(oneHandedModeRepository.contentResolver, "one_handed_mode_activated", 0) > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isEnabled$lambda$0$isEnabled(OneHandedModeRepository oneHandedModeRepository) {
        return Settings.Secure.getInt(oneHandedModeRepository.contentResolver, "one_handed_mode_enabled", 0) > 0;
    }

    public final I getOffset() {
        return this.offset;
    }

    public final I isActivated() {
        return this.isActivated;
    }

    public final I isEnabled() {
        return this.isEnabled;
    }
}
