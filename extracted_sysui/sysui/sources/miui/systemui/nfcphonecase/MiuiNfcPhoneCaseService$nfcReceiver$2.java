package miui.systemui.nfcphonecase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes4.dex */
public final class MiuiNfcPhoneCaseService$nfcReceiver$2 extends o implements Function0 {
    final /* synthetic */ MiuiNfcPhoneCaseService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MiuiNfcPhoneCaseService$nfcReceiver$2(MiuiNfcPhoneCaseService miuiNfcPhoneCaseService) {
        super(0);
        this.this$0 = miuiNfcPhoneCaseService;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [miui.systemui.nfcphonecase.MiuiNfcPhoneCaseService$nfcReceiver$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        final MiuiNfcPhoneCaseService miuiNfcPhoneCaseService = this.this$0;
        return new BroadcastReceiver() { // from class: miui.systemui.nfcphonecase.MiuiNfcPhoneCaseService$nfcReceiver$2.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                n.g(context, "context");
                n.g(intent, "intent");
                Log.w(MiuiNfcPhoneCaseService.TAG, "onReceive " + intent);
                if (n.c(intent.getAction(), MiuiNfcPhoneCaseService.ACTION_DISMISS_NFC_PHONE_CASE)) {
                    Bundle extras = intent.getExtras();
                    Log.i(MiuiNfcPhoneCaseService.TAG, "onReceive ACTION_DISMISS_NFC reason:" + (extras != null ? extras.getString("reason") : null));
                    miuiNfcPhoneCaseService.hideAnim();
                }
            }
        };
    }
}
