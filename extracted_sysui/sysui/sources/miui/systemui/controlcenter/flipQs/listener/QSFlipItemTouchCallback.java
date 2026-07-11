package miui.systemui.controlcenter.flipQs.listener;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.flipQs.adapter.QSFlipAdapter;
import miui.systemui.controlcenter.flipQs.wrap.QSFlipTileWrap;

/* JADX INFO: loaded from: classes.dex */
public final class QSFlipItemTouchCallback extends ItemTouchHelper.Callback {
    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current, RecyclerView.ViewHolder target) {
        n.g(recyclerView, "recyclerView");
        n.g(current, "current");
        n.g(target, "target");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        n.e(adapter, "null cannot be cast to non-null type miui.systemui.controlcenter.flipQs.adapter.QSFlipAdapter");
        QSFlipAdapter qSFlipAdapter = (QSFlipAdapter) adapter;
        return qSFlipAdapter.isItemAdded(current.getAdapterPosition()) && qSFlipAdapter.isItemAdded(target.getAdapterPosition());
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        n.g(recyclerView, "recyclerView");
        n.g(viewHolder, "viewHolder");
        super.clearView(recyclerView, viewHolder);
        recyclerView.requestLayout();
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        n.g(recyclerView, "recyclerView");
        n.g(viewHolder, "viewHolder");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        n.e(adapter, "null cannot be cast to non-null type miui.systemui.controlcenter.flipQs.adapter.QSFlipAdapter");
        return !((QSFlipAdapter) adapter).isItemAdded(viewHolder.getAdapterPosition()) ? ItemTouchHelper.Callback.makeMovementFlags(0, 0) : ItemTouchHelper.Callback.makeMovementFlags(15, 0);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        n.g(recyclerView, "recyclerView");
        n.g(viewHolder, "viewHolder");
        n.g(target, "target");
        int adapterPosition = viewHolder.getAdapterPosition();
        int adapterPosition2 = target.getAdapterPosition();
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        n.e(adapter, "null cannot be cast to non-null type miui.systemui.controlcenter.flipQs.adapter.QSFlipAdapter");
        QSFlipAdapter qSFlipAdapter = (QSFlipAdapter) adapter;
        List<QSFlipTileWrap> items = qSFlipAdapter.getItems();
        qSFlipAdapter.setDataChanged(true);
        items.add(adapterPosition2, items.remove(adapterPosition));
        qSFlipAdapter.notifyItemMoved(adapterPosition, adapterPosition2);
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i2) {
        n.g(viewHolder, "viewHolder");
    }
}
