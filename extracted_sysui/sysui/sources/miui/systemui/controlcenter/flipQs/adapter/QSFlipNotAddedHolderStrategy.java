package miui.systemui.controlcenter.flipQs.adapter;

import java.util.List;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.flipQs.adapter.QSFlipHolderStrategy;
import miui.systemui.controlcenter.flipQs.utils.QSFlipUtils;
import miui.systemui.controlcenter.flipQs.wrap.QSFlipTileWrap;

/* JADX INFO: loaded from: classes.dex */
public final class QSFlipNotAddedHolderStrategy implements QSFlipHolderStrategy {
    private final String TAG = "QSFlipNotAddedHolderStrategy";

    @Override // miui.systemui.controlcenter.flipQs.adapter.QSFlipHolderStrategy
    public void addHeader(List<QSFlipTileWrap> itemList, QSFlipAdapter adapter) {
        n.g(itemList, "itemList");
        n.g(adapter, "adapter");
        QSFlipHolderStrategy.Companion companion = QSFlipHolderStrategy.Companion;
        if (itemList.indexOf(companion.getMAddedHeader()) == -1) {
            itemList.add(0, companion.getMAddedHeader());
            adapter.notifyItemInserted(0);
            QSFlipUtils.INSTANCE.notifyHeaderUnderLine(itemList, adapter);
        }
    }

    @Override // miui.systemui.controlcenter.flipQs.adapter.QSFlipHolderStrategy
    public int getInsertIndex(List<QSFlipTileWrap> itemList, QSFlipTileWrap qSFlipTileWrap) {
        n.g(itemList, "itemList");
        return itemList.indexOf(QSFlipHolderStrategy.Companion.getMNotAddedHeader());
    }

    @Override // miui.systemui.controlcenter.flipQs.adapter.QSFlipHolderStrategy
    public void removeHeader(List<QSFlipTileWrap> itemList, QSFlipAdapter adapter) {
        n.g(itemList, "itemList");
        n.g(adapter, "adapter");
        int size = itemList.size() - 2;
        if (itemList.indexOf(QSFlipHolderStrategy.Companion.getMNotAddedHeader()) == size) {
            itemList.remove(size);
            adapter.notifyItemRemoved(size);
        }
    }
}
