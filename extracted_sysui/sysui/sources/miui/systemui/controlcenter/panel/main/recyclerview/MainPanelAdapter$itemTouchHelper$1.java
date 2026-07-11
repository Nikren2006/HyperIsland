package miui.systemui.controlcenter.panel.main.recyclerview;

import android.view.View;
import android.view.ViewParent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SpringItemTouchHelper;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;

/* JADX INFO: loaded from: classes.dex */
public final class MainPanelAdapter$itemTouchHelper$1 extends SpringItemTouchHelper {
    final /* synthetic */ MainPanelAdapter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainPanelAdapter$itemTouchHelper$1(MainPanelAdapter mainPanelAdapter, MainPanelAdapter$itemTouchHelper$2 mainPanelAdapter$itemTouchHelper$2) {
        super(mainPanelAdapter$itemTouchHelper$2);
        this.this$0 = mainPanelAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onStopDrag$lambda$0(RecyclerView.Adapter adapter) {
        n.g(adapter, "$adapter");
        adapter.notifyItemChanged(0);
    }

    @Override // androidx.recyclerview.widget.SpringItemTouchHelper
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        View view;
        super.onStartDrag(viewHolder);
        if (viewHolder != null && (view = viewHolder.itemView) != null) {
            view.announceForAccessibility(this.this$0.context.getString(R.string.qs_item_drag));
        }
        ControlCenterViewHolder controlCenterViewHolder = viewHolder instanceof ControlCenterViewHolder ? (ControlCenterViewHolder) viewHolder : null;
        if (controlCenterViewHolder != null) {
            controlCenterViewHolder.onStartDrag();
        }
        this.this$0._dragging.setValue(Boolean.TRUE);
    }

    @Override // androidx.recyclerview.widget.SpringItemTouchHelper
    public void onStopDrag(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager layoutManager;
        final RecyclerView.Adapter adapter;
        RecyclerView.ItemAnimator itemAnimator;
        View view;
        super.onStopDrag(viewHolder);
        ControlCenterViewHolder controlCenterViewHolder = viewHolder instanceof ControlCenterViewHolder ? (ControlCenterViewHolder) viewHolder : null;
        if (controlCenterViewHolder != null) {
            controlCenterViewHolder.onStopDrag();
        }
        this.this$0._dragging.setValue(Boolean.FALSE);
        ViewParent parent = (viewHolder == null || (view = viewHolder.itemView) == null) ? null : view.getParent();
        miuix.recyclerview.widget.RecyclerView recyclerView = parent instanceof miuix.recyclerview.widget.RecyclerView ? (miuix.recyclerview.widget.RecyclerView) parent : null;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || layoutManager.isViewPartiallyVisible(viewHolder.itemView, true, true) || (adapter = recyclerView.getAdapter()) == null || (itemAnimator = recyclerView.getItemAnimator()) == null) {
            return;
        }
        this.this$0.handler.postDelayed(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.c
            @Override // java.lang.Runnable
            public final void run() {
                MainPanelAdapter$itemTouchHelper$1.onStopDrag$lambda$0(adapter);
            }
        }, itemAnimator.getRemoveDuration());
    }
}
