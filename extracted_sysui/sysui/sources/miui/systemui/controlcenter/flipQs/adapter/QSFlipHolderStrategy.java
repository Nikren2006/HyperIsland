package miui.systemui.controlcenter.flipQs.adapter;

import java.util.List;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.flipQs.wrap.QSFlipTileWrap;

/* JADX INFO: loaded from: classes.dex */
public interface QSFlipHolderStrategy {
    public static final Companion Companion = Companion.$$INSTANCE;

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final String TAG = "QSFlipHolderStrategy";
        private static final String TYPE_ADDED = "added";
        private static final String TYPE_NOT_ADDED = "notAdded";
        private static final String TYPE_NOT_UNKNOWN = "unknown";
        private static final QSFlipTileWrap mAddedHeader;
        private static final QSFlipTileWrap mFooter;
        private static final QSFlipTileWrap mNotAddedHeader;

        static {
            int i2 = R.string.qs_flip_item_tile_added;
            mAddedHeader = new QSFlipTileWrap(0, i2, null, null, null, 28, null);
            mNotAddedHeader = new QSFlipTileWrap(0, R.string.qs_flip_item_tile_not_added, null, null, null, 28, null);
            mFooter = new QSFlipTileWrap(2, i2, null, null, null, 28, null);
        }

        private Companion() {
        }

        public final QSFlipTileWrap getMAddedHeader() {
            return mAddedHeader;
        }

        public final QSFlipTileWrap getMFooter() {
            return mFooter;
        }

        public final QSFlipTileWrap getMNotAddedHeader() {
            return mNotAddedHeader;
        }

        public final QSFlipHolderStrategy getStrategy(String strategyType) {
            n.g(strategyType, "strategyType");
            if (n.c(strategyType, TYPE_ADDED)) {
                return new QSFlipAddedHolderStrategy();
            }
            if (n.c(strategyType, TYPE_NOT_ADDED)) {
                return new QSFlipNotAddedHolderStrategy();
            }
            throw new IllegalArgumentException("Unknown strategy type: " + strategyType);
        }

        public final String getStrategyType(QSFlipTileWrap infoWrap) {
            n.g(infoWrap, "infoWrap");
            return infoWrap.getType() == 10 ? TYPE_ADDED : TYPE_NOT_ADDED;
        }
    }

    static /* synthetic */ int getInsertIndex$default(QSFlipHolderStrategy qSFlipHolderStrategy, List list, QSFlipTileWrap qSFlipTileWrap, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getInsertIndex");
        }
        if ((i2 & 2) != 0) {
            qSFlipTileWrap = null;
        }
        return qSFlipHolderStrategy.getInsertIndex(list, qSFlipTileWrap);
    }

    void addHeader(List<QSFlipTileWrap> list, QSFlipAdapter qSFlipAdapter);

    int getInsertIndex(List<QSFlipTileWrap> list, QSFlipTileWrap qSFlipTileWrap);

    void removeHeader(List<QSFlipTileWrap> list, QSFlipAdapter qSFlipAdapter);
}
