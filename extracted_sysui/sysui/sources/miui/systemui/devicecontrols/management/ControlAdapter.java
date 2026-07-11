package miui.systemui.devicecontrols.management;

import H0.s;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.controlcenter.ConfigUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlAdapter extends RecyclerView.Adapter<BaseHolder> implements ConfigUtils.OnConfigChangeListener {
    public static final String CONFIG_PAYLOAD = "config_payload";
    public static final Companion Companion = new Companion(null);
    public static final String MARK_PAYLOAD = "mark_payload";
    public static final int MAX_COLUMNS_LANDSCAPE = 4;
    public static final int MAX_COLUMNS_PORTRAIT = 2;
    private RecyclerView attachedRecyclerView;
    private final GridLayoutManager.SpanSizeLookup controlSpanSizeLookup;
    private final MarginItemDecorator itemDecorator;
    private ItemTouchHelper itemTouchHelper;
    private boolean landscape;
    private final ControlAdapter$layoutManager$1 layoutManager;
    private ControlsModel model;
    private final Context pluginContext;
    private final ViewHolderFactory viewHolderFactory;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [androidx.recyclerview.widget.GridLayoutManager, miui.systemui.devicecontrols.management.ControlAdapter$layoutManager$1] */
    public ControlAdapter(final Context pluginContext, ViewHolderFactory viewHolderFactory) {
        kotlin.jvm.internal.n.g(pluginContext, "pluginContext");
        kotlin.jvm.internal.n.g(viewHolderFactory, "viewHolderFactory");
        this.pluginContext = pluginContext;
        this.viewHolderFactory = viewHolderFactory;
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() { // from class: miui.systemui.devicecontrols.management.ControlAdapter$controlSpanSizeLookup$1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i2) {
                if (i2 >= 0 && i2 < this.this$0.getItemCount()) {
                    int itemViewType = this.this$0.getItemViewType(i2);
                    if (itemViewType == -1) {
                        return 1;
                    }
                    return this.this$0.viewHolderFactory.getSpanSize(itemViewType, this.this$0.landscape);
                }
                Log.e("ControlAdapter", "getSpanSize: Index " + i2 + " out of bounds for length " + this.this$0.getItemCount());
                return 1;
            }
        };
        this.controlSpanSizeLookup = spanSizeLookup;
        this.itemDecorator = new MarginItemDecorator();
        ?? r02 = new GridLayoutManager(pluginContext) { // from class: miui.systemui.devicecontrols.management.ControlAdapter$layoutManager$1
            @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
                kotlin.jvm.internal.n.g(recycler, "recycler");
                kotlin.jvm.internal.n.g(state, "state");
                int rowCountForAccessibility = super.getRowCountForAccessibility(recycler, state);
                return rowCountForAccessibility > 0 ? rowCountForAccessibility - 1 : rowCountForAccessibility;
            }
        };
        r02.setSpanSizeLookup(spanSizeLookup);
        this.layoutManager = r02;
    }

    private final void attachItemTouchHelper(RecyclerView recyclerView) {
        ItemTouchHelper.Callback itemTouchHelperCallback;
        ControlsModel controlsModel = this.model;
        if (controlsModel == null || (itemTouchHelperCallback = controlsModel.getItemTouchHelperCallback()) == null) {
            return;
        }
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        this.itemTouchHelper = itemTouchHelper;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void updateLayoutManager() {
        /*
            r4 = this;
            androidx.recyclerview.widget.RecyclerView r0 = r4.attachedRecyclerView
            if (r0 != 0) goto L5
            return
        L5:
            miui.systemui.util.CommonUtils r1 = miui.systemui.util.CommonUtils.INSTANCE
            boolean r1 = r1.useAlignEndStyle()
            if (r1 != 0) goto L20
            miui.systemui.controlcenter.ConfigUtils r1 = miui.systemui.controlcenter.ConfigUtils.INSTANCE
            android.content.Context r2 = r0.getContext()
            java.lang.String r3 = "getContext(...)"
            kotlin.jvm.internal.n.f(r2, r3)
            boolean r1 = r1.isLandscape(r2)
            if (r1 == 0) goto L20
            r1 = 1
            goto L21
        L20:
            r1 = 0
        L21:
            r4.landscape = r1
            if (r1 == 0) goto L27
            r1 = 4
            goto L28
        L27:
            r1 = 2
        L28:
            miui.systemui.devicecontrols.management.ControlAdapter$layoutManager$1 r2 = r4.layoutManager
            r0.setLayoutManager(r2)
            miui.systemui.devicecontrols.management.ControlAdapter$layoutManager$1 r2 = r4.layoutManager
            int r2 = r2.getSpanCount()
            if (r2 == r1) goto L3f
            miui.systemui.devicecontrols.management.ControlAdapter$layoutManager$1 r2 = r4.layoutManager
            r2.setSpanCount(r1)
            androidx.recyclerview.widget.GridLayoutManager$SpanSizeLookup r1 = r4.controlSpanSizeLookup
            r2.setSpanSizeLookup(r1)
        L3f:
            miui.systemui.devicecontrols.management.MarginItemDecorator r1 = r4.itemDecorator
            r0.removeItemDecoration(r1)
            miui.systemui.devicecontrols.management.MarginItemDecorator r4 = r4.itemDecorator
            r0.addItemDecoration(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.devicecontrols.management.ControlAdapter.updateLayoutManager():void");
    }

    public final void changeModel(ControlsModel model) {
        kotlin.jvm.internal.n.g(model, "model");
        this.model = model;
        RecyclerView recyclerView = this.attachedRecyclerView;
        if (recyclerView == null || this.itemTouchHelper != null) {
            return;
        }
        attachItemTouchHelper(recyclerView);
        s sVar = s.f314a;
    }

    public final void changeModelWithNotify(ControlsModel model) {
        kotlin.jvm.internal.n.g(model, "model");
        changeModel(model);
        notifyDataSetChanged();
    }

    public final GridLayoutManager.SpanSizeLookup getControlSpanSizeLookup() {
        return this.controlSpanSizeLookup;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ElementWrapper> elements;
        ControlsModel controlsModel = this.model;
        if (controlsModel == null || (elements = controlsModel.getElements()) == null) {
            return 0;
        }
        return elements.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        ControlsModel controlsModel = this.model;
        if (controlsModel == null) {
            throw new IllegalStateException("Getting item type for null model");
        }
        if (i2 >= 0 && i2 < getItemCount()) {
            return this.viewHolderFactory.getItemViewType(controlsModel.getElements().get(i2));
        }
        Log.e("ControlAdapter", "getItemViewType: Index " + i2 + " out of bounds for length " + getItemCount());
        return -1;
    }

    public final ControlsModel getModel() {
        return this.model;
    }

    public final Context getPluginContext() {
        return this.pluginContext;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        kotlin.jvm.internal.n.g(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        this.attachedRecyclerView = recyclerView;
        attachItemTouchHelper(recyclerView);
        updateLayoutManager();
    }

    @Override // miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zDimensionsChanged = configUtils.dimensionsChanged(i2);
        if (zDimensionsChanged || configUtils.orientationChanged(i2)) {
            updateLayoutManager();
        }
        if (zDimensionsChanged) {
            notifyItemRangeChanged(0, getItemCount(), CONFIG_PAYLOAD);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        kotlin.jvm.internal.n.g(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        this.attachedRecyclerView = null;
        ItemTouchHelper itemTouchHelper = this.itemTouchHelper;
        if (itemTouchHelper != null) {
            itemTouchHelper.attachToRecyclerView(null);
        }
        recyclerView.removeItemDecoration(this.itemDecorator);
    }

    public final void onItemInsert(int i2) {
        RecyclerView recyclerView = this.attachedRecyclerView;
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(i2);
        }
    }

    public final void setModel(ControlsModel controlsModel) {
        this.model = controlsModel;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2, List list) {
        onBindViewHolder((BaseHolder) viewHolder, i2, (List<Object>) list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public BaseHolder onCreateViewHolder(ViewGroup parent, int i2) {
        kotlin.jvm.internal.n.g(parent, "parent");
        return this.viewHolderFactory.createViewHolder(parent, i2, this.model);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(BaseHolder holder, int i2) {
        kotlin.jvm.internal.n.g(holder, "holder");
        ControlsModel controlsModel = this.model;
        if (controlsModel != null) {
            holder.bindData(controlsModel.getElements().get(i2));
        }
    }

    public void onBindViewHolder(BaseHolder holder, int i2, List<Object> payloads) {
        kotlin.jvm.internal.n.g(holder, "holder");
        kotlin.jvm.internal.n.g(payloads, "payloads");
        super.onBindViewHolder(holder, i2, payloads);
        ControlsModel controlsModel = this.model;
        if (controlsModel != null) {
            holder.bindData(controlsModel.getElements().get(i2), i2, payloads);
        }
    }
}
