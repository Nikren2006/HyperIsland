package miui.systemui.controlcenter.panel.main.qs;

import H0.s;
import android.util.Log;
import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.util.CommonUtils;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
public final class QSRecord$markClickAction$1 extends o implements Function1 {
    final /* synthetic */ QSRecord this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSRecord$markClickAction$1(QSRecord qSRecord) {
        super(1);
        this.this$0 = qSRecord;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return s.f314a;
    }

    public final void invoke(View view) {
        Log.d(this.this$0.tag, "mark clicked.");
        if (!this.this$0.getAdded()) {
            this.this$0.qsController.addTile(this.this$0);
            if (CommonUtils.isTinyScreen(this.this$0.context)) {
                return;
            }
            ControlCenterEventTracker.Companion.trackTilesEditEvent(EventTracker.Companion.getScreenType(this.this$0.context), false, this.this$0.getSpec(), this.this$0.host.indexOf(this.this$0.getSpec()));
            return;
        }
        if (this.this$0.getRemovable()) {
            this.this$0.qsController.removeTile(this.this$0);
        }
        if (CommonUtils.isTinyScreen(this.this$0.context)) {
            return;
        }
        ControlCenterEventTracker.Companion.trackTilesEditEvent(EventTracker.Companion.getScreenType(this.this$0.context), true, this.this$0.getSpec(), this.this$0.host.indexOf(this.this$0.getSpec()));
    }
}
