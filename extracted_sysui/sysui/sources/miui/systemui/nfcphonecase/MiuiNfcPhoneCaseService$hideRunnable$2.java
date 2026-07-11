package miui.systemui.nfcphonecase;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class MiuiNfcPhoneCaseService$hideRunnable$2 extends o implements Function0 {
    final /* synthetic */ MiuiNfcPhoneCaseService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiuiNfcPhoneCaseService$hideRunnable$2(MiuiNfcPhoneCaseService miuiNfcPhoneCaseService) {
        super(0);
        this.this$0 = miuiNfcPhoneCaseService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(MiuiNfcPhoneCaseService this$0) {
        n.g(this$0, "this$0");
        this$0.hideAnim();
    }

    @Override // kotlin.jvm.functions.Function0
    public final Runnable invoke() {
        final MiuiNfcPhoneCaseService miuiNfcPhoneCaseService = this.this$0;
        return new Runnable() { // from class: miui.systemui.nfcphonecase.a
            @Override // java.lang.Runnable
            public final void run() {
                MiuiNfcPhoneCaseService$hideRunnable$2.invoke$lambda$0(miuiNfcPhoneCaseService);
            }
        };
    }
}
