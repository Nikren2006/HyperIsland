package miui.systemui.controlcenter.panel.main.qs;

import android.util.Log;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.panel.main.MainPanelController;

/* JADX INFO: loaded from: classes.dex */
public final class QSRecord$callback$2 extends o implements Function0 {
    final /* synthetic */ QSRecord this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSRecord$callback$2(QSRecord qSRecord) {
        super(0);
        this.this$0 = qSRecord;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [miui.systemui.controlcenter.panel.main.qs.QSRecord$callback$2$1] */
    @Override // kotlin.jvm.functions.Function0
    public final AnonymousClass1 invoke() {
        final QSRecord qSRecord = this.this$0;
        return new QSTile.Callback() { // from class: miui.systemui.controlcenter.panel.main.qs.QSRecord$callback$2.1
            public int getCallbackType() {
                return qSRecord.isCard() ? 2273 : 8453;
            }

            public void onAnnouncementRequested(CharSequence charSequence) {
            }

            public void onScanStateChanged(boolean z2) {
                qSRecord.qsController.fireScanStateChanged(qSRecord, z2);
            }

            public void onShowDetail(boolean z2) {
                qSRecord.qsController.showDetail(z2, qSRecord);
            }

            public void onShowStateMessage(CharSequence charSequence) {
                if (charSequence == null) {
                    return;
                }
                Log.d(qSRecord.tag, "onShowMessage " + ((Object) charSequence));
                qSRecord.qsController.showMessage(charSequence);
            }

            public void onStateChanged(QSTile.State state) {
                n.g(state, "state");
                if (qSRecord.mode == MainPanelController.Mode.NORMAL) {
                    removeMessages(78283);
                    QSRecord$uiHandler$1 qSRecord$uiHandler$1 = qSRecord.uiHandler;
                    QSTile tile = qSRecord.getTile();
                    int i2 = 0;
                    if (tile != null && tile.isConnected()) {
                        i2 = 1;
                    }
                    qSRecord$uiHandler$1.obtainMessage(78283, i2, qSRecord.getIconWithAnim(), state).sendToTarget();
                }
            }

            public void onToggleStateChanged(boolean z2) {
                qSRecord.qsController.fireToggleStateChanged(qSRecord, z2);
            }
        };
    }
}
