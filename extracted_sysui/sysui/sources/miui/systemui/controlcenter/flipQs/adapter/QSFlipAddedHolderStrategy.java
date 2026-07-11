package miui.systemui.controlcenter.flipQs.adapter;

import I0.m;
import android.util.Log;
import java.util.List;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.flipQs.adapter.QSFlipHolderStrategy;
import miui.systemui.controlcenter.flipQs.utils.QSFlipUtils;
import miui.systemui.controlcenter.flipQs.wrap.QSFlipTileWrap;

/* JADX INFO: loaded from: classes.dex */
public final class QSFlipAddedHolderStrategy implements QSFlipHolderStrategy {
    private final String TAG = "QSFlipAddedHolderStrategy";

    private final void addNotAddedHeader(List<QSFlipTileWrap> list, QSFlipAdapter qSFlipAdapter) {
        Log.d(this.TAG, "addNotAddedHeader: " + list.size());
        QSFlipHolderStrategy.Companion companion = QSFlipHolderStrategy.Companion;
        if (list.indexOf(companion.getMNotAddedHeader()) == -1) {
            list.add(list.size() - 1, companion.getMNotAddedHeader());
            qSFlipAdapter.notifyItemInserted(list.size() - 1);
        }
    }

    @Override // miui.systemui.controlcenter.flipQs.adapter.QSFlipHolderStrategy
    public void addHeader(List<QSFlipTileWrap> itemList, QSFlipAdapter adapter) {
        n.g(itemList, "itemList");
        n.g(adapter, "adapter");
        addNotAddedHeader(itemList, adapter);
    }

    @Override // miui.systemui.controlcenter.flipQs.adapter.QSFlipHolderStrategy
    public int getInsertIndex(List<QSFlipTileWrap> itemList, QSFlipTileWrap qSFlipTileWrap) {
        n.g(itemList, "itemList");
        return itemList.indexOf(QSFlipHolderStrategy.Companion.getMFooter()) != -1 ? itemList.indexOf(r1.getMFooter()) - 1 : m.i(itemList);
    }

    @Override // miui.systemui.controlcenter.flipQs.adapter.QSFlipHolderStrategy
    public void removeHeader(List<QSFlipTileWrap> itemList, QSFlipAdapter adapter) {
        n.g(itemList, "itemList");
        n.g(adapter, "adapter");
        QSFlipHolderStrategy.Companion companion = QSFlipHolderStrategy.Companion;
        if (itemList.indexOf(companion.getMNotAddedHeader()) != 1 || itemList.indexOf(companion.getMAddedHeader()) == -1) {
            return;
        }
        itemList.remove(0);
        adapter.notifyItemRemoved(0);
        QSFlipUtils.INSTANCE.notifyHeaderUnderLine(itemList, adapter);
    }
}
