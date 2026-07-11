package miui.systemui.controlcenter.panel.main.qs;

import H0.s;
import android.content.Context;
import android.util.Log;
import android.view.View;
import com.android.systemui.plugins.miui.shade.ShadeSwitchController;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.util.BoostHelper;
import miui.systemui.util.CommonUtils;
import miui.systemui.widget.LinearLayout;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
public final class EditButtonController$onBindViewHolder$1$1 extends o implements Function1 {
    final /* synthetic */ LinearLayout $this_apply;
    final /* synthetic */ EditButtonController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditButtonController$onBindViewHolder$1$1(EditButtonController editButtonController, LinearLayout linearLayout) {
        super(1);
        this.this$0 = editButtonController;
        this.$this_apply = linearLayout;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return s.f314a;
    }

    public final void invoke(View view) {
        if (((ShadeSwitchController) this.this$0.shadeSwitchController.get()).getSwitching()) {
            Log.w("EditButtonController", "click: switching");
            return;
        }
        if (!((SecondaryPanelRouter) this.this$0.secondaryPanelRouter.get()).getInIdleState()) {
            Log.i("EditButtonController", "inIdleState is not true , return.");
            return;
        }
        BoostHelper.getInstance().boostWithCpuFreq(1000L, view);
        if (((MainPanelController) this.this$0.mainPanelController.get()).getExpandController().getInMirror()) {
            Log.w("EditButtonController", "click: in mirror");
            return;
        }
        MainPanelItemViewHolder.Companion.releaseTouchNow();
        ((QSListController) this.this$0.qsListController.get()).startQuery(MainPanelController.Mode.EDIT);
        Context context = this.$this_apply.getContext();
        n.f(context, "getContext(...)");
        if (CommonUtils.isTinyScreen(context)) {
            return;
        }
        ControlCenterEventTracker.Companion companion = ControlCenterEventTracker.Companion;
        EventTracker.Companion companion2 = EventTracker.Companion;
        Context context2 = this.$this_apply.getContext();
        n.f(context2, "getContext(...)");
        companion.trackEditClickEvent(companion2.getScreenType(context2));
        Context context3 = this.$this_apply.getContext();
        n.f(context3, "getContext(...)");
        companion.trackEditPanelOpenEvent(companion2.getScreenType(context3));
    }
}
