package miui.systemui.controlcenter.panel.main.qs;

import I0.u;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;

/* JADX INFO: loaded from: classes.dex */
public final class CompactQSListController$broadcastReceiver$2 extends o implements Function0 {
    final /* synthetic */ CompactQSListController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompactQSListController$broadcastReceiver$2(CompactQSListController compactQSListController) {
        super(0);
        this.this$0 = compactQSListController;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [miui.systemui.controlcenter.panel.main.qs.CompactQSListController$broadcastReceiver$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        final CompactQSListController compactQSListController = this.this$0;
        return new BroadcastReceiver() { // from class: miui.systemui.controlcenter.panel.main.qs.CompactQSListController$broadcastReceiver$2.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                n.g(context, "context");
                n.g(intent, "intent");
                Log.d("CompactQSListController", "onReceive pkg change");
                Uri data = intent.getData();
                String encodedSchemeSpecificPart = data != null ? data.getEncodedSchemeSpecificPart() : null;
                List list = compactQSListController.flipPkgNameStockPool;
                if (list == null) {
                    n.w("flipPkgNameStockPool");
                    list = null;
                }
                if (u.F(list, encodedSchemeSpecificPart)) {
                    compactQSListController.getMainHandler().removeCallbacksAndMessages(null);
                    Handler mainHandler = compactQSListController.getMainHandler();
                    Message messageObtain = Message.obtain();
                    messageObtain.what = 0;
                    mainHandler.sendMessage(messageObtain);
                }
            }
        };
    }
}
