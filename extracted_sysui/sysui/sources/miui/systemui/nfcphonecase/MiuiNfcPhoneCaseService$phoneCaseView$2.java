package miui.systemui.nfcphonecase;

import android.view.View;
import android.widget.FrameLayout;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class MiuiNfcPhoneCaseService$phoneCaseView$2 extends o implements Function0 {
    final /* synthetic */ MiuiNfcPhoneCaseService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiuiNfcPhoneCaseService$phoneCaseView$2(MiuiNfcPhoneCaseService miuiNfcPhoneCaseService) {
        super(0);
        this.this$0 = miuiNfcPhoneCaseService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1$lambda$0(MiuiNfcPhoneCaseService this$0, View view) {
        n.g(this$0, "this$0");
        this$0.hideAnim();
    }

    @Override // kotlin.jvm.functions.Function0
    public final FrameLayout invoke() {
        FrameLayout frameLayout = new FrameLayout(this.this$0);
        final MiuiNfcPhoneCaseService miuiNfcPhoneCaseService = this.this$0;
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.nfcphonecase.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MiuiNfcPhoneCaseService$phoneCaseView$2.invoke$lambda$1$lambda$0(miuiNfcPhoneCaseService, view);
            }
        });
        frameLayout.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: miui.systemui.nfcphonecase.MiuiNfcPhoneCaseService$phoneCaseView$2$1$2
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v2) {
                n.g(v2, "v");
                miuiNfcPhoneCaseService.startAnim();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v2) {
                n.g(v2, "v");
            }
        });
        return frameLayout;
    }
}
