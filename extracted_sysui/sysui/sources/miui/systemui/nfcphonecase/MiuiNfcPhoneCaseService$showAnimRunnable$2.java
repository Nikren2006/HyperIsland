package miui.systemui.nfcphonecase;

import H0.s;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miuix.animation.Folme;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.controller.AnimState;

/* JADX INFO: loaded from: classes4.dex */
public final class MiuiNfcPhoneCaseService$showAnimRunnable$2 extends o implements Function0 {
    final /* synthetic */ MiuiNfcPhoneCaseService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiuiNfcPhoneCaseService$showAnimRunnable$2(MiuiNfcPhoneCaseService miuiNfcPhoneCaseService) {
        super(0);
        this.this$0 = miuiNfcPhoneCaseService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1(MiuiNfcPhoneCaseService this$0) {
        n.g(this$0, "this$0");
        IStateStyle to = Folme.useValue(this$0).setTo(NFCStateKt.getInitState(this$0.getRingSize()));
        AnimState showOneStepState = NFCStateKt.getShowOneStepState();
        AnimConfig showOneAnimConfig = NFCStateKt.getShowOneAnimConfig();
        showOneAnimConfig.addListeners(this$0.mNfcTransitionListener);
        s sVar = s.f314a;
        to.to(showOneStepState, showOneAnimConfig);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Runnable invoke() {
        final MiuiNfcPhoneCaseService miuiNfcPhoneCaseService = this.this$0;
        return new Runnable() { // from class: miui.systemui.nfcphonecase.g
            @Override // java.lang.Runnable
            public final void run() {
                MiuiNfcPhoneCaseService$showAnimRunnable$2.invoke$lambda$1(miuiNfcPhoneCaseService);
            }
        };
    }
}
