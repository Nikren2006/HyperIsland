package miui.systemui.controlcenter.panel.main.recyclerview;

import I0.r;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import c1.f;
import com.android.systemui.plugins.miui.shade.ShadeHeaderController;
import j1.AbstractC0420h;
import j1.I;
import j1.K;
import j1.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.panel.main.MainPanelContent;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelFrameCallback;
import miui.systemui.controlcenter.panel.main.anim.LowEndItemHolderAnimator;
import miui.systemui.controlcenter.panel.main.anim.SpreadRowsAnimator;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.widget.MainPanelRecyclerView;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NotifyDataSetChanged"})
public final class MainPanelAdapter extends RecyclerView.Adapter<MainPanelItemViewHolder> {
    private static final int COMPACT_SPAN_COUNT = 3;
    public static final int DEFAULT_SPAN_COUNT = 4;
    private static final int NORMAL_SPAN_COUNT = 4;
    private static final String TAG = "MainPanelAdapter";
    private final u _dragging;
    private final ArrayList<MainPanelItemViewHolder>[] attachedHolders;
    private final Runnable changeSpreadRunnable;
    private final List<MainPanelContent> contentList;
    private LinkedHashMap<MainPanelContent, List<MainPanelListItem>> contentMap;
    private final Context context;
    private final I dragging;
    private final E0.a expandController;
    private boolean expandVisble;
    private final MainPanelFrameCallback frameCallback;
    private final Handler handler;
    private final ControlCenterItemAnimator itemAnimator;
    private final MainPanelAdapter$itemTouchHelper$1 itemTouchHelper;
    private final GridLayoutManager layoutManager;
    private final Lifecycle lifecycle;
    private final LowEndItemHolderAnimator lowEndItemHolderAnimator;
    private final E0.a mainPanelController;
    private final Lifecycle mirrorLifecycle;
    private MainPanelController.Mode mode;
    private boolean modeChanged;
    private boolean pendingUpdateHolderLocation;
    private final QSListController qsListController;
    private final RecyclerView.RecycledViewPool recycledViewPool;
    private RecyclerView recyclerView;
    private final ShadeHeaderController shadeHeaderController;
    private int span;
    private final SpreadRowsAnimator spreadRowsAnimator;
    private MainPanelController.Style style;
    public static final Companion Companion = new Companion(null);
    private static final Object SUPER_SAVE_MODE_PAYLOAD = new Object();
    private static final int DRAG_FLAG = ItemTouchHelper.Callback.makeMovementFlags(15, 0);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final MainPanelContent getContent(Map<MainPanelContent, ? extends List<MainPanelListItem>> map, int i2) {
            for (Map.Entry<MainPanelContent, ? extends List<MainPanelListItem>> entry : map.entrySet()) {
                if (entry.getValue().size() > i2) {
                    return entry.getKey();
                }
                i2 -= entry.getValue().size();
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final MainPanelListItem getItem(Map<MainPanelContent, ? extends List<MainPanelListItem>> map, int i2) {
            for (Map.Entry<MainPanelContent, ? extends List<MainPanelListItem>> entry : map.entrySet()) {
                if (i2 < 0) {
                    return null;
                }
                if (entry.getValue().size() > i2) {
                    return entry.getValue().get(i2);
                }
                i2 -= entry.getValue().size();
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int getItemSize(Map<MainPanelContent, ? extends List<MainPanelListItem>> map) {
            Iterator<Map.Entry<MainPanelContent, ? extends List<MainPanelListItem>>> it = map.entrySet().iterator();
            int size = 0;
            while (it.hasNext()) {
                size += it.next().getValue().size();
            }
            return size;
        }

        public final Object getSUPER_SAVE_MODE_PAYLOAD$miui_controlcenter_release() {
            return MainPanelAdapter.SUPER_SAVE_MODE_PAYLOAD;
        }

        private Companion() {
        }
    }

    @ControlCenterScope
    public static final class Factory {
        private final Context context;
        private final E0.a expandController;
        private final E0.a frameCallback;
        private final Lifecycle lifecycle;
        private final E0.a mainPanelController;
        private final Lifecycle mirrorLifecycle;
        private final QSListController qsListController;
        private final ShadeHeaderController shadeHeaderController;
        private final SpreadRowsAnimator spreadRowsAnimator;

        public Factory(Context context, SpreadRowsAnimator spreadRowsAnimator, @Qualifiers.ControlCenter Lifecycle lifecycle, E0.a frameCallback, E0.a mainPanelController, ShadeHeaderController shadeHeaderController, QSListController qsListController, E0.a expandController, @Qualifiers.Mirror Lifecycle mirrorLifecycle) {
            n.g(context, "context");
            n.g(spreadRowsAnimator, "spreadRowsAnimator");
            n.g(lifecycle, "lifecycle");
            n.g(frameCallback, "frameCallback");
            n.g(mainPanelController, "mainPanelController");
            n.g(shadeHeaderController, "shadeHeaderController");
            n.g(qsListController, "qsListController");
            n.g(expandController, "expandController");
            n.g(mirrorLifecycle, "mirrorLifecycle");
            this.context = context;
            this.spreadRowsAnimator = spreadRowsAnimator;
            this.lifecycle = lifecycle;
            this.frameCallback = frameCallback;
            this.mainPanelController = mainPanelController;
            this.shadeHeaderController = shadeHeaderController;
            this.qsListController = qsListController;
            this.expandController = expandController;
            this.mirrorLifecycle = mirrorLifecycle;
        }

        public final MainPanelAdapter create(List<? extends MainPanelContent> contentList, RecyclerView.RecycledViewPool recycledViewPool) {
            n.g(contentList, "contentList");
            n.g(recycledViewPool, "recycledViewPool");
            Lifecycle lifecycle = this.lifecycle;
            Context context = this.context;
            SpreadRowsAnimator spreadRowsAnimator = this.spreadRowsAnimator;
            Object obj = this.frameCallback.get();
            n.f(obj, "get(...)");
            return new MainPanelAdapter(lifecycle, context, contentList, spreadRowsAnimator, recycledViewPool, (MainPanelFrameCallback) obj, this.mainPanelController, this.shadeHeaderController, this.qsListController, this.expandController, this.mirrorLifecycle, null);
        }
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MainPanelController.Style.values().length];
            try {
                iArr[MainPanelController.Style.COMPACT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ MainPanelAdapter(Lifecycle lifecycle, Context context, List list, SpreadRowsAnimator spreadRowsAnimator, RecyclerView.RecycledViewPool recycledViewPool, MainPanelFrameCallback mainPanelFrameCallback, E0.a aVar, ShadeHeaderController shadeHeaderController, QSListController qSListController, E0.a aVar2, Lifecycle lifecycle2, DefaultConstructorMarker defaultConstructorMarker) {
        this(lifecycle, context, list, spreadRowsAnimator, recycledViewPool, mainPanelFrameCallback, aVar, shadeHeaderController, qSListController, aVar2, lifecycle2);
    }

    public static /* synthetic */ void changeItemVisible$default(MainPanelAdapter mainPanelAdapter, boolean z2, boolean z3, boolean z4, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z4 = false;
        }
        mainPanelAdapter.changeItemVisible(z2, z3, z4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeSpreadRows() {
        this.handler.removeCallbacks(this.changeSpreadRunnable);
        this.handler.post(this.changeSpreadRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void changeSpreadRunnable$lambda$0(MainPanelAdapter this$0) {
        n.g(this$0, "this$0");
        this$0.handleChangeSpreadRows();
    }

    private final void distributeContent() {
        LinkedHashMap<MainPanelContent, List<MainPanelListItem>> linkedHashMap = new LinkedHashMap<>();
        for (MainPanelContent mainPanelContent : this.contentList) {
            List<MainPanelListItem> listItems = mainPanelContent.getListItems();
            if (linkedHashMap.get(mainPanelContent) == null) {
                linkedHashMap.put(mainPanelContent, new ArrayList());
            }
            List<MainPanelListItem> list = linkedHashMap.get(mainPanelContent);
            if (list != null) {
                list.clear();
                list.addAll(listItems);
            }
        }
        this.contentMap = linkedHashMap;
    }

    private final int getSpreadRow(MainPanelItemViewHolder mainPanelItemViewHolder) {
        return f.i(this.layoutManager.getSpanSizeLookup().getSpanGroupIndex(mainPanelItemViewHolder.getAbsoluteAdapterPosition(), this.span), 0, 9);
    }

    private final void handleChangeSpreadRow(MainPanelItemViewHolder mainPanelItemViewHolder) {
        int i2 = f.i(this.layoutManager.getSpanSizeLookup().getSpanGroupIndex(mainPanelItemViewHolder.getAbsoluteAdapterPosition(), 4), 0, 9);
        if (i2 == mainPanelItemViewHolder.getSpreadRow$miui_controlcenter_release()) {
            return;
        }
        if (this.attachedHolders[mainPanelItemViewHolder.getSpreadRow$miui_controlcenter_release()].remove(mainPanelItemViewHolder)) {
            this.attachedHolders[i2].add(mainPanelItemViewHolder);
        }
        mainPanelItemViewHolder.setSpreadRow$miui_controlcenter_release(i2);
        updateSpread(mainPanelItemViewHolder);
    }

    private final void handleChangeSpreadRows() {
        ArrayList<MainPanelItemViewHolder>[] arrayListArr = this.attachedHolders;
        ArrayList arrayList = new ArrayList();
        for (ArrayList<MainPanelItemViewHolder> arrayList2 : arrayListArr) {
            r.t(arrayList, arrayList2);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            handleChangeSpreadRow((MainPanelItemViewHolder) it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isAccessibilityEnabled() {
        Object systemService = this.context.getSystemService("accessibility");
        n.e(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        return ((AccessibilityManager) systemService).isEnabled();
    }

    public static /* synthetic */ void notifyChanged$default(MainPanelAdapter mainPanelAdapter, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        mainPanelAdapter.notifyChanged(z2);
    }

    private final void setExpandVisble(boolean z2) {
        if (this.expandVisble == z2) {
            return;
        }
        this.expandVisble = z2;
    }

    private final void setSpan(int i2) {
        if (this.span == i2) {
            return;
        }
        this.span = i2;
        this.layoutManager.setSpanCount(i2);
    }

    private final void updateSpanCount() {
        setSpan(WhenMappings.$EnumSwitchMapping$0[((MainPanelController) this.mainPanelController.get()).getStyle().ordinal()] == 1 ? 3 : 4);
        Log.i(TAG, "span count change to " + this.style + " " + this.span);
    }

    private final void updateSpread(MainPanelItemViewHolder mainPanelItemViewHolder) {
        MainPanelContent owner = mainPanelItemViewHolder.getOwner();
        if (owner != null) {
            owner.onSpreadChange(mainPanelItemViewHolder, this.spreadRowsAnimator.getScaleFactor(), this.spreadRowsAnimator.getSlideTransXValues()[mainPanelItemViewHolder.getSpreadRow$miui_controlcenter_release()]);
        }
    }

    public final void changeItemVisible(boolean z2, boolean z3, boolean z4) {
        MainPanelController.Companion companion = MainPanelController.Companion;
        Log.d(TAG, "changeItemVisible " + z2 + " " + z3 + " " + z4 + " " + companion.getLowEndAnim());
        setExpandVisble(z2);
        if (companion.getLowEndAnim()) {
            this.lowEndItemHolderAnimator.changeVisible(z2, z3, z4);
            return;
        }
        ArrayList<MainPanelItemViewHolder>[] arrayListArr = this.attachedHolders;
        ArrayList arrayList = new ArrayList();
        for (ArrayList<MainPanelItemViewHolder> arrayList2 : arrayListArr) {
            r.t(arrayList, arrayList2);
        }
        Log.d(TAG, "changeVisible holders: " + arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((MainPanelItemViewHolder) it.next()).changeVisible(z2, z3, z4);
        }
    }

    public final void changePanelExpand(boolean z2, boolean z3, float f2, float f3, float f4) {
        if (MainPanelController.Companion.getLowEndAnim()) {
            this.lowEndItemHolderAnimator.expandedStretch(f2, f3, z2, z3);
            return;
        }
        ArrayList<MainPanelItemViewHolder>[] arrayListArr = this.attachedHolders;
        ArrayList arrayList = new ArrayList();
        for (ArrayList<MainPanelItemViewHolder> arrayList2 : arrayListArr) {
            r.t(arrayList, arrayList2);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((MainPanelItemViewHolder) it.next()).changeExpand(f2, f3, f4, z3, z2);
        }
    }

    public final int getAddTileSpreadRow(MainPanelItemViewHolder holder) {
        n.g(holder, "holder");
        return getSpreadRow(holder);
    }

    public final int getCount() {
        return this.qsListController.getAddedTiles().size();
    }

    public final I getDragging() {
        return this.dragging;
    }

    public final boolean getExpandVisble() {
        return this.expandVisble;
    }

    public final float getHeaderHeight() {
        return this.shadeHeaderController.getCombinedHeaderHeight();
    }

    public final ControlCenterItemAnimator getItemAnimator() {
        return this.itemAnimator;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        Iterator<Map.Entry<MainPanelContent, List<MainPanelListItem>>> it = this.contentMap.entrySet().iterator();
        int size = 0;
        while (it.hasNext()) {
            size += it.next().getValue().size();
        }
        return size;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        MainPanelListItem item = Companion.getItem(this.contentMap, i2);
        if (item != null) {
            return item.getType();
        }
        return Integer.MIN_VALUE;
    }

    public final Lifecycle getMirrorLifecycle() {
        return this.mirrorLifecycle;
    }

    public final boolean getModeChanged() {
        return this.modeChanged;
    }

    public final boolean getPendingUpdateHolderLocation() {
        return this.pendingUpdateHolderLocation;
    }

    public final int getScreenHeight() {
        return (int) this.spreadRowsAnimator.getScreenHeight();
    }

    public final int getScreenWidth() {
        return (int) this.spreadRowsAnimator.getScreenWidth();
    }

    public final void notifyChanged(boolean z2) {
        final LinkedHashMap<MainPanelContent, List<MainPanelListItem>> linkedHashMap = this.contentMap;
        distributeContent();
        final boolean z3 = this.mode != ((MainPanelController) this.mainPanelController.get()).getMode();
        this.modeChanged = z3;
        boolean z4 = this.style != ((MainPanelController) this.mainPanelController.get()).getStyle();
        this.mode = ((MainPanelController) this.mainPanelController.get()).getMode();
        this.style = ((MainPanelController) this.mainPanelController.get()).getStyle();
        updateSpanCount();
        RecyclerView recyclerView = this.recyclerView;
        MainPanelRecyclerView mainPanelRecyclerView = recyclerView instanceof MainPanelRecyclerView ? (MainPanelRecyclerView) recyclerView : null;
        if (mainPanelRecyclerView != null) {
            mainPanelRecyclerView.setAcceptAllGesture(this.mode != MainPanelController.Mode.NORMAL);
        }
        if ((this.lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED) || !z2) && !z4) {
            DiffUtil.calculateDiff(new DiffUtil.Callback() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.MainPanelAdapter.notifyChanged.1
                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public boolean areContentsTheSame(int i2, int i3) {
                    return areItemsTheSame(i2, i3) && !z3;
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public boolean areItemsTheSame(int i2, int i3) {
                    Companion companion = MainPanelAdapter.Companion;
                    return companion.getItem(linkedHashMap, i2) == companion.getItem(this.contentMap, i3);
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public int getNewListSize() {
                    return MainPanelAdapter.Companion.getItemSize(this.contentMap);
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public int getOldListSize() {
                    return MainPanelAdapter.Companion.getItemSize(linkedHashMap);
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public MainPanelController.Mode getChangePayload(int i2, int i3) {
                    return this.mode;
                }
            }).dispatchUpdatesTo(this);
        } else {
            notifyDataSetChanged();
        }
    }

    public final void notifyChangedWithPayloads(Object payload) {
        n.g(payload, "payload");
        notifyItemRangeChanged(0, getItemCount(), payload);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        n.g(recyclerView, "recyclerView");
        this.recyclerView = recyclerView;
        recyclerView.setItemAnimator(this.itemAnimator);
        recyclerView.setLayoutManager(this.layoutManager);
        recyclerView.setRecycledViewPool(this.recycledViewPool);
        this.itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        n.g(recyclerView, "recyclerView");
        if (this.recyclerView == recyclerView) {
            this.recyclerView = null;
        }
        recyclerView.setLayoutManager(null);
        recyclerView.setRecycledViewPool(null);
        this.itemTouchHelper.attachToRecyclerView(null);
    }

    public final void onFrameCallback() {
        updateSpread();
    }

    public final void release() {
        this.lowEndItemHolderAnimator.release();
    }

    public final void setModeChanged(boolean z2) {
        this.modeChanged = z2;
    }

    public final void setPendingUpdateHolderLocation(boolean z2) {
        this.pendingUpdateHolderLocation = z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v8, types: [miui.systemui.controlcenter.panel.main.recyclerview.MainPanelAdapter$itemTouchHelper$2] */
    private MainPanelAdapter(Lifecycle lifecycle, Context context, List<? extends MainPanelContent> list, SpreadRowsAnimator spreadRowsAnimator, RecyclerView.RecycledViewPool recycledViewPool, MainPanelFrameCallback mainPanelFrameCallback, E0.a aVar, ShadeHeaderController shadeHeaderController, QSListController qSListController, E0.a aVar2, Lifecycle lifecycle2) {
        this.lifecycle = lifecycle;
        this.context = context;
        this.contentList = list;
        this.spreadRowsAnimator = spreadRowsAnimator;
        this.recycledViewPool = recycledViewPool;
        this.frameCallback = mainPanelFrameCallback;
        this.mainPanelController = aVar;
        this.shadeHeaderController = shadeHeaderController;
        this.qsListController = qSListController;
        this.expandController = aVar2;
        this.mirrorLifecycle = lifecycle2;
        ArrayList<MainPanelItemViewHolder>[] arrayListArr = new ArrayList[10];
        for (int i2 = 0; i2 < 10; i2++) {
            arrayListArr[i2] = new ArrayList<>();
        }
        this.attachedHolders = arrayListArr;
        this.handler = new Handler(Looper.getMainLooper());
        this.changeSpreadRunnable = new Runnable() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.b
            @Override // java.lang.Runnable
            public final void run() {
                MainPanelAdapter.changeSpreadRunnable$lambda$0(this.f5433a);
            }
        };
        u uVarA = K.a(Boolean.FALSE);
        this._dragging = uVarA;
        this.dragging = AbstractC0420h.b(uVarA);
        this.lowEndItemHolderAnimator = new LowEndItemHolderAnimator(this.expandController, this.frameCallback, this.spreadRowsAnimator, this.mainPanelController);
        this.itemTouchHelper = new MainPanelAdapter$itemTouchHelper$1(this, new ItemTouchHelper.Callback() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.MainPanelAdapter$itemTouchHelper$2
            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                n.g(recyclerView, "recyclerView");
                n.g(viewHolder, "viewHolder");
                if (isLongPressDragEnabled()) {
                    MainPanelItemViewHolder mainPanelItemViewHolder = viewHolder instanceof MainPanelItemViewHolder ? (MainPanelItemViewHolder) viewHolder : null;
                    if (mainPanelItemViewHolder != null && mainPanelItemViewHolder.getDraggable() && !recyclerView.isAnimating()) {
                        return MainPanelAdapter.DRAG_FLAG;
                    }
                }
                return 0;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean isLongPressDragEnabled() {
                return (this.this$0.mode == MainPanelController.Mode.NORMAL || ((MainPanelController) this.this$0.mainPanelController.get()).getMainPanelScrolling()) ? false : true;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                n.g(recyclerView, "recyclerView");
                n.g(viewHolder, "viewHolder");
                n.g(target, "target");
                MainPanelItemViewHolder mainPanelItemViewHolder = viewHolder instanceof MainPanelItemViewHolder ? (MainPanelItemViewHolder) viewHolder : null;
                if (mainPanelItemViewHolder == null) {
                    return false;
                }
                MainPanelItemViewHolder mainPanelItemViewHolder2 = target instanceof MainPanelItemViewHolder ? (MainPanelItemViewHolder) target : null;
                MainPanelListItem item$miui_controlcenter_release = mainPanelItemViewHolder2 != null ? mainPanelItemViewHolder2.getItem$miui_controlcenter_release() : null;
                int iO = I0.u.O(this.this$0.qsListController.getAddedTiles(), item$miui_controlcenter_release);
                if (this.this$0.isAccessibilityEnabled() && iO >= 0) {
                    mainPanelItemViewHolder.itemView.announceForAccessibility(this.this$0.context.getString(R.string.qs_position_hint, Integer.valueOf((iO / 4) + 1), Integer.valueOf((iO % 4) + 1)));
                }
                MainPanelContent owner = mainPanelItemViewHolder.getOwner();
                if (owner != null) {
                    return owner.moveElement(mainPanelItemViewHolder.getItem$miui_controlcenter_release(), item$miui_controlcenter_release);
                }
                return false;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i3) {
                n.g(viewHolder, "viewHolder");
            }
        });
        this.span = 4;
        this.contentMap = new LinkedHashMap<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, this.span);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.MainPanelAdapter$layoutManager$1$1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i3) {
                MainPanelListItem item = MainPanelAdapter.Companion.getItem(this.this$0.contentMap, i3);
                return f.i(item != null ? item.getSpanSize() : this.this$0.span, 1, this.this$0.span);
            }
        };
        spanSizeLookup.setSpanIndexCacheEnabled(true);
        spanSizeLookup.setSpanGroupIndexCacheEnabled(true);
        gridLayoutManager.setSpanSizeLookup(spanSizeLookup);
        this.layoutManager = gridLayoutManager;
        this.itemAnimator = new ControlCenterItemAnimator();
        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() { // from class: miui.systemui.controlcenter.panel.main.recyclerview.MainPanelAdapter.1
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                MainPanelAdapter.this.changeSpreadRows();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int i3, int i4, Object obj) {
                MainPanelAdapter.this.changeSpreadRows();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int i3, int i4) {
                MainPanelAdapter.this.changeSpreadRows();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeMoved(int i3, int i4, int i5) {
                MainPanelAdapter.this.changeSpreadRows();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int i3, int i4) {
                MainPanelAdapter.this.changeSpreadRows();
            }
        });
        this.mode = MainPanelController.Mode.NORMAL;
        this.style = MainPanelController.Style.VERTICAL;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2, List list) {
        onBindViewHolder((MainPanelItemViewHolder) viewHolder, i2, (List<Object>) list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MainPanelItemViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        for (Map.Entry<MainPanelContent, List<MainPanelListItem>> entry : this.contentMap.entrySet()) {
            MainPanelItemViewHolder mainPanelItemViewHolderCreateViewHolder = entry.getKey().createViewHolder(parent, i2);
            if (mainPanelItemViewHolderCreateViewHolder != null) {
                mainPanelItemViewHolderCreateViewHolder.setOwner(entry.getKey());
                mainPanelItemViewHolderCreateViewHolder.init();
                return mainPanelItemViewHolderCreateViewHolder;
            }
        }
        throw new IllegalStateException("could not create view holder for type " + i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public boolean onFailedToRecycleView(MainPanelItemViewHolder holder) {
        n.g(holder, "holder");
        Log.d(TAG, "onFailedToRecycleView " + holder);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(MainPanelItemViewHolder holder) {
        n.g(holder, "holder");
        holder.setAttached$miui_controlcenter_release(true);
        this.attachedHolders[holder.getSpreadRow$miui_controlcenter_release()].add(holder);
        holder.onViewAttachedToWindow(this, this.frameCallback);
        MainPanelListItem item$miui_controlcenter_release = holder.getItem$miui_controlcenter_release();
        if (item$miui_controlcenter_release != null) {
            item$miui_controlcenter_release.onViewAttachedToWindow();
        }
        updateSpread(holder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(MainPanelItemViewHolder holder) {
        n.g(holder, "holder");
        holder.setAttached$miui_controlcenter_release(false);
        this.attachedHolders[holder.getSpreadRow$miui_controlcenter_release()].remove(holder);
        holder.onViewDetachedFromWindow();
        MainPanelListItem item$miui_controlcenter_release = holder.getItem$miui_controlcenter_release();
        if (item$miui_controlcenter_release != null) {
            item$miui_controlcenter_release.onViewDetachedFromWindow();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(MainPanelItemViewHolder holder) {
        n.g(holder, "holder");
        holder.recycle();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MainPanelItemViewHolder holder, int i2) {
        MainPanelContent content;
        n.g(holder, "holder");
        Companion companion = Companion;
        MainPanelListItem item = companion.getItem(this.contentMap, i2);
        if (item == null || (content = companion.getContent(this.contentMap, i2)) == null) {
            return;
        }
        changeSpreadRows();
        MainPanelListItem item$miui_controlcenter_release = holder.getItem$miui_controlcenter_release();
        if (item$miui_controlcenter_release != null && item$miui_controlcenter_release != item) {
            MainPanelContent owner = holder.getOwner();
            if (owner != null) {
                owner.onUnbindViewHolder(holder, item$miui_controlcenter_release);
            }
            MainPanelItemViewHolder holder2 = item$miui_controlcenter_release.getHolder();
            if (holder2 != null) {
                holder2.setItem$miui_controlcenter_release(null);
            }
            item$miui_controlcenter_release.setHolder(null);
        }
        MainPanelItemViewHolder holder3 = item.getHolder();
        if (holder3 != null && holder3 != holder) {
            MainPanelContent owner2 = holder3.getOwner();
            if (owner2 != null) {
                owner2.onUnbindViewHolder(holder3, item);
            }
            MainPanelListItem item$miui_controlcenter_release2 = holder3.getItem$miui_controlcenter_release();
            if (item$miui_controlcenter_release2 != null) {
                item$miui_controlcenter_release2.setHolder(null);
            }
            holder3.setItem$miui_controlcenter_release(null);
        }
        MainPanelContent owner3 = holder.getOwner();
        if (owner3 != null && owner3 != content) {
            owner3.onUnbindViewHolder(holder, item);
        }
        holder.setOwner(content);
        holder.setItem$miui_controlcenter_release(item);
        item.setHolder(holder);
        MainPanelContent owner4 = holder.getOwner();
        if (owner4 != null) {
            Configuration configuration = this.context.getResources().getConfiguration();
            n.f(configuration, "getConfiguration(...)");
            owner4.updateConfiguration(holder, item, configuration);
        }
        MainPanelContent owner5 = holder.getOwner();
        if (owner5 != null) {
            owner5.updateSuperSaveMode(holder, item);
        }
        MainPanelContent owner6 = holder.getOwner();
        if (owner6 != null) {
            owner6.updateMode(holder, item, this.mode, false);
        }
        MainPanelContent owner7 = holder.getOwner();
        if (owner7 != null) {
            owner7.updateStyle(holder, item, this.style);
        }
        MainPanelContent owner8 = holder.getOwner();
        if (owner8 != null) {
            owner8.onBindViewHolder(holder, item);
        }
    }

    private final void updateSpread() {
        boolean z2 = MainPanelController.Companion.getLowEndAnim() && this.lowEndItemHolderAnimator.getExpandedStretchChanged();
        if (z2) {
            this.lowEndItemHolderAnimator.updateHeaderStretch();
        }
        ArrayList<MainPanelItemViewHolder>[] arrayListArr = this.attachedHolders;
        ArrayList<MainPanelItemViewHolder> arrayList = new ArrayList();
        for (ArrayList<MainPanelItemViewHolder> arrayList2 : arrayListArr) {
            r.t(arrayList, arrayList2);
        }
        for (MainPanelItemViewHolder mainPanelItemViewHolder : arrayList) {
            if (MainPanelController.Companion.getLowEndAnim()) {
                if (this.pendingUpdateHolderLocation && z2) {
                    mainPanelItemViewHolder.setPendingUpdateLocation(true);
                }
                this.lowEndItemHolderAnimator.onFrameCallback(mainPanelItemViewHolder, z2);
            }
            updateSpread(mainPanelItemViewHolder);
        }
        if (z2) {
            this.pendingUpdateHolderLocation = false;
        }
    }

    public void onBindViewHolder(MainPanelItemViewHolder holder, int i2, List<Object> payloads) {
        MainPanelContent owner;
        MainPanelContent owner2;
        MainPanelContent owner3;
        MainPanelContent owner4;
        MainPanelContent owner5;
        n.g(holder, "holder");
        n.g(payloads, "payloads");
        for (Object obj : payloads) {
            if (obj instanceof MainPanelController.Mode) {
                MainPanelListItem item$miui_controlcenter_release = holder.getItem$miui_controlcenter_release();
                if (item$miui_controlcenter_release != null && (owner = holder.getOwner()) != null) {
                    owner.updateMode(holder, item$miui_controlcenter_release, (MainPanelController.Mode) obj, this.layoutManager.isViewPartiallyVisible(holder.itemView, true, true));
                }
            } else if (obj instanceof MainPanelController.Style) {
                MainPanelListItem item$miui_controlcenter_release2 = holder.getItem$miui_controlcenter_release();
                if (item$miui_controlcenter_release2 != null && (owner2 = holder.getOwner()) != null) {
                    owner2.updateStyle(holder, item$miui_controlcenter_release2, (MainPanelController.Style) obj);
                }
            } else if (obj instanceof Configuration) {
                MainPanelListItem item$miui_controlcenter_release3 = holder.getItem$miui_controlcenter_release();
                if (item$miui_controlcenter_release3 != null && (owner3 = holder.getOwner()) != null) {
                    owner3.updateConfiguration(holder, item$miui_controlcenter_release3, (Configuration) obj);
                }
            } else if (n.c(obj, SUPER_SAVE_MODE_PAYLOAD)) {
                MainPanelListItem item$miui_controlcenter_release4 = holder.getItem$miui_controlcenter_release();
                if (item$miui_controlcenter_release4 != null && (owner4 = holder.getOwner()) != null) {
                    owner4.updateSuperSaveMode(holder, item$miui_controlcenter_release4);
                }
            } else {
                MainPanelListItem item$miui_controlcenter_release5 = holder.getItem$miui_controlcenter_release();
                if (item$miui_controlcenter_release5 != null && (owner5 = holder.getOwner()) != null) {
                    owner5.applyPayload(holder, item$miui_controlcenter_release5, obj);
                }
            }
        }
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, i2, payloads);
        }
    }
}
