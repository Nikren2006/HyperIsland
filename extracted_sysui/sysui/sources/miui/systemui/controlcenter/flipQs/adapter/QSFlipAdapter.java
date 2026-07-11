package miui.systemui.controlcenter.flipQs.adapter;

import I0.u;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.flipQs.adapter.QSFlipHolderStrategy;
import miui.systemui.controlcenter.flipQs.listener.DebouncingOnClickListener;
import miui.systemui.controlcenter.flipQs.utils.QSFlipUtils;
import miui.systemui.controlcenter.flipQs.wrap.QSFlipTileWrap;
import miuix.core.util.MiuixUIUtils;

/* JADX INFO: loaded from: classes.dex */
public final class QSFlipAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final long CHANGE_TILE_INTERVAL = 300;
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "QSFlipAdapter";
    private boolean isDataChanged;
    private List<QSFlipTileWrap> itemList;
    private long lastChangeTileTime;
    private final RecyclerView rv;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class FooterViewHolder extends RecyclerView.ViewHolder {
        private final View footerDivider;
        private final TextView footerTv;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FooterViewHolder(View itemView) {
            super(itemView);
            n.g(itemView, "itemView");
            View viewFindViewById = itemView.findViewById(R.id.tv_footer_title);
            n.f(viewFindViewById, "findViewById(...)");
            this.footerTv = (TextView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.footer_divider);
            n.f(viewFindViewById2, "findViewById(...)");
            this.footerDivider = viewFindViewById2;
            int dimensionPixelSize = itemView.getResources().getDimensionPixelSize(R.dimen.qs_flip_tile_setting_rv_padding);
            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            int i2 = -dimensionPixelSize;
            marginLayoutParams.setMarginStart(i2);
            marginLayoutParams.setMarginEnd(i2);
            marginLayoutParams.topMargin = -MiuixUIUtils.dp2px(itemView.getContext(), 4.5f);
            itemView.setLayerType(2, null);
        }

        public final View getFooterDivider() {
            return this.footerDivider;
        }

        public final TextView getFooterTv() {
            return this.footerTv;
        }
    }

    public static final class HeaderViewHolder extends RecyclerView.ViewHolder {
        private final View divider;
        private ViewGroup.MarginLayoutParams marginLayoutParams;
        private final TextView subTitleTv;
        private final TextView titleTv;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HeaderViewHolder(View itemView) {
            super(itemView);
            n.g(itemView, "itemView");
            View viewFindViewById = itemView.findViewById(R.id.tv_title);
            n.f(viewFindViewById, "findViewById(...)");
            this.titleTv = (TextView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.tv_sub_title);
            n.f(viewFindViewById2, "findViewById(...)");
            this.subTitleTv = (TextView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(R.id.header_divider);
            n.f(viewFindViewById3, "findViewById(...)");
            this.divider = viewFindViewById3;
            int dimensionPixelSize = itemView.getResources().getDimensionPixelSize(R.dimen.qs_flip_tile_setting_rv_padding);
            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            n.e(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            this.marginLayoutParams = marginLayoutParams;
            if (marginLayoutParams != null) {
                marginLayoutParams.setMarginStart(-dimensionPixelSize);
            }
            ViewGroup.MarginLayoutParams marginLayoutParams2 = this.marginLayoutParams;
            if (marginLayoutParams2 != null) {
                marginLayoutParams2.setMarginEnd(-dimensionPixelSize);
            }
            itemView.setLayerType(2, null);
        }

        public final View getDivider() {
            return this.divider;
        }

        public final ViewGroup.MarginLayoutParams getMarginLayoutParams() {
            return this.marginLayoutParams;
        }

        public final TextView getSubTitleTv() {
            return this.subTitleTv;
        }

        public final TextView getTitleTv() {
            return this.titleTv;
        }

        public final void setMarginLayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            this.marginLayoutParams = marginLayoutParams;
        }
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView bgCardView;
        private final ImageView iconImage;
        private final ImageView operationImage;
        private QSFlipTileWrap shortcutInfo;
        private final TextView titleTv;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View itemView) {
            super(itemView);
            n.g(itemView, "itemView");
            View viewFindViewById = itemView.findViewById(R.id.tv_title);
            n.f(viewFindViewById, "findViewById(...)");
            this.titleTv = (TextView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.iv_icon);
            n.f(viewFindViewById2, "findViewById(...)");
            this.iconImage = (ImageView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(R.id.iv_icon_cardView);
            n.f(viewFindViewById3, "findViewById(...)");
            this.bgCardView = (CardView) viewFindViewById3;
            View viewFindViewById4 = itemView.findViewById(R.id.iv_operation);
            n.f(viewFindViewById4, "findViewById(...)");
            this.operationImage = (ImageView) viewFindViewById4;
        }

        public final CardView getBgCardView() {
            return this.bgCardView;
        }

        public final ImageView getIconImage() {
            return this.iconImage;
        }

        public final ImageView getOperationImage() {
            return this.operationImage;
        }

        public final QSFlipTileWrap getShortcutInfo() {
            return this.shortcutInfo;
        }

        public final TextView getTitleTv() {
            return this.titleTv;
        }

        public final void setShortcutInfo(QSFlipTileWrap qSFlipTileWrap) {
            this.shortcutInfo = qSFlipTileWrap;
        }
    }

    public QSFlipAdapter(RecyclerView rv) {
        n.g(rv, "rv");
        this.rv = rv;
        this.itemList = new ArrayList();
    }

    private final void bindFooterViewHolder(FooterViewHolder footerViewHolder) {
        footerViewHolder.getFooterTv().setVisibility(8);
    }

    private final void bindHeaderViewHolder(HeaderViewHolder headerViewHolder, int i2) {
        if (n.c(this.itemList.get(i2), QSFlipHolderStrategy.Companion.getMAddedHeader())) {
            headerViewHolder.getSubTitleTv().setVisibility(0);
            ViewGroup.MarginLayoutParams marginLayoutParams = headerViewHolder.getMarginLayoutParams();
            if (marginLayoutParams != null) {
                marginLayoutParams.topMargin = 0;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams2 = headerViewHolder.getMarginLayoutParams();
            if (marginLayoutParams2 != null) {
                marginLayoutParams2.bottomMargin = MiuixUIUtils.dp2px(this.rv.getContext(), 23.0f);
            }
            headerViewHolder.getDivider().setBackground(this.rv.getContext().getDrawable(QSFlipUtils.INSTANCE.isNightMode() ? R.drawable.flip_qs_card_top_corner_dark : R.drawable.flip_qs_card_top_corner_light));
        } else {
            headerViewHolder.getSubTitleTv().setVisibility(8);
            ViewGroup.MarginLayoutParams marginLayoutParams3 = headerViewHolder.getMarginLayoutParams();
            if (marginLayoutParams3 != null) {
                marginLayoutParams3.topMargin = -MiuixUIUtils.dp2px(this.rv.getContext(), 5.0f);
            }
            ViewGroup.MarginLayoutParams marginLayoutParams4 = headerViewHolder.getMarginLayoutParams();
            if (marginLayoutParams4 != null) {
                marginLayoutParams4.bottomMargin = MiuixUIUtils.dp2px(this.rv.getContext(), 23.0f);
            }
            headerViewHolder.getDivider().setBackground(this.rv.getContext().getDrawable(QSFlipUtils.INSTANCE.isNightMode() ? R.drawable.flip_qs_group_title_boundary_dark : R.drawable.flip_qs_group_title_boundary_light));
        }
        headerViewHolder.getTitleTv().setText(this.rv.getContext().getResources().getString(this.itemList.get(i2).getTitleRes()));
        headerViewHolder.getTitleTv().setContentDescription(this.rv.getContext().getResources().getString(this.itemList.get(i2).getTitleRes()));
    }

    private final void bindItemViewHolder(ViewHolder viewHolder, int i2, List<Object> list) {
        QSFlipTileWrap qSFlipTileWrap = this.itemList.get(i2);
        viewHolder.setShortcutInfo(qSFlipTileWrap);
        if (!list.isEmpty()) {
            viewHolder.getOperationImage().setImageResource(getOperaIconRes(qSFlipTileWrap));
            return;
        }
        if (placeHolderBasedOnDataValidity(viewHolder, qSFlipTileWrap)) {
            return;
        }
        viewHolder.getTitleTv().setText(qSFlipTileWrap.getName());
        Integer iconId = qSFlipTileWrap.getIconId();
        if (iconId != null) {
            viewHolder.getIconImage().setImageDrawable(this.rv.getContext().getDrawable(iconId.intValue()));
        }
        int notAddedHeaderPosition = QSFlipUtils.INSTANCE.getNotAddedHeaderPosition(this.itemList);
        if (notAddedHeaderPosition <= 7 && i2 < notAddedHeaderPosition) {
            viewHolder.getOperationImage().setVisibility(8);
            viewHolder.itemView.setContentDescription(qSFlipTileWrap.getName());
            setItemClickListener(viewHolder, qSFlipTileWrap, false);
            viewHolder.itemView.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.controlcenter.flipQs.adapter.QSFlipAdapter.bindItemViewHolder.2
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                    n.g(host, "host");
                    n.g(info, "info");
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.setClassName(Button.class.getName());
                    info.setClickable(false);
                    info.setFocusable(false);
                }
            });
            return;
        }
        viewHolder.getOperationImage().setVisibility(0);
        viewHolder.getOperationImage().setImageResource(getOperaIconRes(qSFlipTileWrap));
        viewHolder.itemView.setContentDescription(qSFlipTileWrap.getName() + "，" + getOperationImageDescription(qSFlipTileWrap.getType()));
        viewHolder.itemView.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: miui.systemui.controlcenter.flipQs.adapter.QSFlipAdapter.bindItemViewHolder.3
            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                n.g(host, "host");
                n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(Button.class.getName());
                info.setClickable(true);
                info.setFocusable(true);
            }
        });
        setItemClickListener$default(this, viewHolder, qSFlipTileWrap, false, 4, null);
    }

    private final void bindViewHolder(RecyclerView.ViewHolder viewHolder, int i2, List<Object> list) {
        if (viewHolder instanceof HeaderViewHolder) {
            bindHeaderViewHolder((HeaderViewHolder) viewHolder, i2);
        } else if (viewHolder instanceof ViewHolder) {
            bindItemViewHolder((ViewHolder) viewHolder, i2, list);
        } else if (viewHolder instanceof FooterViewHolder) {
            bindFooterViewHolder((FooterViewHolder) viewHolder);
        }
    }

    private final int getFooterCount() {
        return 1;
    }

    private final int getOperaIconRes(QSFlipTileWrap qSFlipTileWrap) {
        return qSFlipTileWrap.getType() == 10 ? R.drawable.qs_flip_tile_del : R.drawable.qs_flip_tile_add;
    }

    private final String getOperationImageDescription(int i2) {
        String string = this.rv.getContext().getString(i2 == 10 ? R.string.qs_flip_content_description_remove_operation : R.string.qs_flip_content_description_add_operation);
        n.f(string, "getString(...)");
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleAddedItemClick(ViewHolder viewHolder) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime < this.lastChangeTileTime + 300) {
            return;
        }
        this.lastChangeTileTime = jElapsedRealtime;
        QSFlipTileWrap qSFlipTileWrap = this.itemList.get(viewHolder.getAbsoluteAdapterPosition());
        QSFlipHolderStrategy.Companion companion = QSFlipHolderStrategy.Companion;
        String strategyType = companion.getStrategyType(qSFlipTileWrap);
        companion.getStrategy(strategyType).addHeader(this.itemList, this);
        QSFlipUtils qSFlipUtils = QSFlipUtils.INSTANCE;
        qSFlipUtils.removeAndAddItem(this.itemList, viewHolder.getAbsoluteAdapterPosition(), companion.getStrategy(strategyType).getInsertIndex(this.itemList, qSFlipTileWrap), 11, this);
        QSFlipTileWrap qSFlipTileWrap2 = this.itemList.get(viewHolder.getAdapterPosition());
        viewHolder.getOperationImage().setImageResource(getOperaIconRes(qSFlipTileWrap2));
        viewHolder.itemView.setContentDescription(qSFlipTileWrap2.getName() + "，" + getOperationImageDescription(qSFlipTileWrap2.getType()));
        if (qSFlipUtils.getNotAddedHeaderPosition(this.itemList) == 7) {
            notifyItemRangeChanged(1, 6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleNotAddedItemClick(ViewHolder viewHolder) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime < this.lastChangeTileTime + 300) {
            return;
        }
        this.lastChangeTileTime = jElapsedRealtime;
        QSFlipHolderStrategy.Companion companion = QSFlipHolderStrategy.Companion;
        String strategyType = companion.getStrategyType(this.itemList.get(viewHolder.getAbsoluteAdapterPosition()));
        QSFlipUtils qSFlipUtils = QSFlipUtils.INSTANCE;
        qSFlipUtils.removeAndAddItem(this.itemList, viewHolder.getAbsoluteAdapterPosition(), QSFlipHolderStrategy.getInsertIndex$default(companion.getStrategy(strategyType), this.itemList, null, 2, null), 10, this);
        QSFlipTileWrap qSFlipTileWrap = this.itemList.get(viewHolder.getAbsoluteAdapterPosition());
        viewHolder.getOperationImage().setImageResource(getOperaIconRes(qSFlipTileWrap));
        viewHolder.itemView.setContentDescription(qSFlipTileWrap.getName() + "，" + getOperationImageDescription(qSFlipTileWrap.getType()));
        companion.getStrategy(strategyType).removeHeader(this.itemList, this);
        if (qSFlipUtils.getNotAddedHeaderPosition(this.itemList) == 8) {
            notifyItemRangeChanged(1, 6);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [miui.systemui.controlcenter.flipQs.adapter.QSFlipAdapter$obtainDebounceItemClick$1] */
    private final AnonymousClass1 obtainDebounceItemClick(final ViewHolder viewHolder, final QSFlipTileWrap qSFlipTileWrap) {
        return new DebouncingOnClickListener() { // from class: miui.systemui.controlcenter.flipQs.adapter.QSFlipAdapter.obtainDebounceItemClick.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0L, 1, null);
            }

            @Override // miui.systemui.controlcenter.flipQs.listener.DebouncingOnClickListener
            public void onDebouncingClick(View v2) {
                n.g(v2, "v");
                QSFlipAdapter.this.setDataChanged(true);
                if (qSFlipTileWrap.getType() == 10) {
                    QSFlipAdapter.this.handleAddedItemClick(viewHolder);
                } else {
                    QSFlipAdapter.this.handleNotAddedItemClick(viewHolder);
                }
            }
        };
    }

    private final boolean placeHolderBasedOnDataValidity(ViewHolder viewHolder, QSFlipTileWrap qSFlipTileWrap) {
        boolean z2 = qSFlipTileWrap.getType() == 11;
        int i2 = (z2 && qSFlipTileWrap.getIconId() == null) ? 4 : 0;
        viewHolder.getTitleTv().setVisibility(i2);
        viewHolder.getOperationImage().setVisibility(i2);
        return z2 && qSFlipTileWrap.getIconId() == null;
    }

    public static /* synthetic */ void refresh$default(QSFlipAdapter qSFlipAdapter, List list, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        qSFlipAdapter.refresh(list, z2);
    }

    private final void setItemClickListener(ViewHolder viewHolder, QSFlipTileWrap qSFlipTileWrap, boolean z2) {
        if (z2) {
            viewHolder.getBgCardView().setOnClickListener(obtainDebounceItemClick(viewHolder, qSFlipTileWrap));
            viewHolder.getOperationImage().setOnClickListener(obtainDebounceItemClick(viewHolder, qSFlipTileWrap));
        } else {
            viewHolder.getIconImage().setOnClickListener(null);
            viewHolder.getOperationImage().setOnClickListener(null);
        }
    }

    public static /* synthetic */ void setItemClickListener$default(QSFlipAdapter qSFlipAdapter, ViewHolder viewHolder, QSFlipTileWrap qSFlipTileWrap, boolean z2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z2 = true;
        }
        qSFlipAdapter.setItemClickListener(viewHolder, qSFlipTileWrap, z2);
    }

    public final List<QSFlipTileWrap> getAddedItems() {
        QSFlipUtils qSFlipUtils = QSFlipUtils.INSTANCE;
        int notAddedHeaderPosition = qSFlipUtils.getNotAddedHeaderPosition(this.itemList);
        if (notAddedHeaderPosition == -1) {
            notAddedHeaderPosition = qSFlipUtils.getFooterPosition(this.itemList);
        }
        return this.itemList.subList(1, notAddedHeaderPosition);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        int type = this.itemList.get(i2).getType();
        if (type != 0) {
            return type != 2 ? 1 : 2;
        }
        return 0;
    }

    public final List<QSFlipTileWrap> getItems() {
        return this.itemList;
    }

    public final boolean isDataChanged() {
        return this.isDataChanged;
    }

    public final boolean isItemAdded(int i2) {
        return i2 < this.itemList.size() && this.itemList.get(i2).getType() == 10;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i2) {
        n.g(holder, "holder");
        bindViewHolder(holder, i2, new ArrayList());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        if (i2 == 0) {
            View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.qs_flip_setting_header_item, parent, false);
            n.f(viewInflate, "inflate(...)");
            return new HeaderViewHolder(viewInflate);
        }
        if (i2 != 1) {
            View viewInflate2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.qs_flip_setting_footer, parent, false);
            n.f(viewInflate2, "inflate(...)");
            return new FooterViewHolder(viewInflate2);
        }
        View viewInflate3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.qs_flip_setting_item, parent, false);
        n.f(viewInflate3, "inflate(...)");
        return new ViewHolder(viewInflate3);
    }

    public final void refresh(List<QSFlipTileWrap> appInfoList, boolean z2) {
        n.g(appInfoList, "appInfoList");
        this.itemList = u.m0(appInfoList);
        if (z2) {
            notifyDataSetChanged();
        }
    }

    public final void setDataChanged(boolean z2) {
        this.isDataChanged = z2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i2, List<Object> payloads) {
        n.g(holder, "holder");
        n.g(payloads, "payloads");
        bindViewHolder(holder, i2, payloads);
    }
}
