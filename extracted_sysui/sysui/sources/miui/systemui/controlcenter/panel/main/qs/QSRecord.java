package miui.systemui.controlcenter.panel.main.qs;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.qs.QSRecord$callback$2;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem;
import miui.systemui.controlcenter.panel.secondary.detail.DetailPanelTilesDelegate;
import miui.systemui.controlcenter.qs.QSController;
import miui.systemui.controlcenter.qs.customize.TileQueryHelper;
import miui.systemui.controlcenter.qs.tileview.QSItemView;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.util.HapticFeedback;

/* JADX INFO: loaded from: classes.dex */
public final class QSRecord extends MainPanelListItem.Base {
    public static final int CALLBACK_TYPE_CARD = 2273;
    public static final int CALLBACK_TYPE_TILE = 8453;
    public static final Companion Companion = new Companion(null);
    private static final boolean DEBUG = SystemProperties.getBoolean("debug.sysui.ctrl", false);
    public static final int ITEM_TYPE_CARD = 2273;
    public static final int ITEM_TYPE_TILE = 8453;
    private static final int MSG_UPDATE_CUSTOMIZE_STATE = 287866;
    private static final int MSG_UPDATE_STATE = 78283;
    private static final int SPAN_SIZE_CARD = 2;
    private static final int SPAN_SIZE_TILE = 1;
    private static final String TAG = "QSRecord";
    private boolean added;
    private final H0.d callback$delegate;
    private final Function1 clickAction;
    private boolean compatTile;
    private final Context context;
    private final HapticFeedback hapticFeedback;
    private final MiuiQSHost host;
    private final boolean isCard;
    private boolean listening;
    private final Function1 longClickAction;
    private final Function1 markClickAction;
    private MainPanelController.Mode mode;
    private final QSController qsController;
    private boolean removable;
    private final Function1 secondaryClickAction;
    private final E0.a secondaryPanelRouter;
    private final int spanSize;
    private final String spec;
    private final String tag;
    private QSTile tile;
    private TileQueryHelper.TileInfo tileInfo;
    private DetailPanelTilesDelegate tilesDelegate;
    private final int type;
    private final QSRecord$uiHandler$1 uiHandler;
    private final Looper uiLooper;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @ControlCenterScope
    public static final class Factory {
        private final Context context;
        private final DetailPanelTilesDelegate detailPanelTilesDelegate;
        private final HapticFeedback hapticFeedback;
        private final MiuiQSHost host;
        private final Lifecycle lifecycle;
        private final E0.a qsController;
        private final E0.a secondaryPanelRouter;
        private final Looper uiLooper;

        public Factory(MiuiQSHost host, Context context, E0.a qsController, @Qualifiers.ControlCenter Lifecycle lifecycle, HapticFeedback hapticFeedback, @Main Looper uiLooper, DetailPanelTilesDelegate detailPanelTilesDelegate, E0.a secondaryPanelRouter) {
            n.g(host, "host");
            n.g(context, "context");
            n.g(qsController, "qsController");
            n.g(lifecycle, "lifecycle");
            n.g(hapticFeedback, "hapticFeedback");
            n.g(uiLooper, "uiLooper");
            n.g(detailPanelTilesDelegate, "detailPanelTilesDelegate");
            n.g(secondaryPanelRouter, "secondaryPanelRouter");
            this.host = host;
            this.context = context;
            this.qsController = qsController;
            this.lifecycle = lifecycle;
            this.hapticFeedback = hapticFeedback;
            this.uiLooper = uiLooper;
            this.detailPanelTilesDelegate = detailPanelTilesDelegate;
            this.secondaryPanelRouter = secondaryPanelRouter;
        }

        public static /* synthetic */ QSRecord create$default(Factory factory, String str, boolean z2, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                z2 = false;
            }
            return factory.create(str, z2);
        }

        public final QSRecord create(String spec, boolean z2) {
            n.g(spec, "spec");
            MiuiQSHost miuiQSHost = this.host;
            Context context = this.context;
            Object obj = this.qsController.get();
            n.f(obj, "get(...)");
            QSRecord qSRecord = new QSRecord(spec, z2, miuiQSHost, context, (QSController) obj, this.lifecycle, this.hapticFeedback, this.uiLooper, this.secondaryPanelRouter, null);
            qSRecord.setTilesDelegate(this.detailPanelTilesDelegate);
            return qSRecord;
        }

        public static /* synthetic */ QSRecord create$default(Factory factory, QSTile qSTile, boolean z2, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                z2 = false;
            }
            return factory.create(qSTile, z2);
        }

        public static /* synthetic */ QSRecord create$default(Factory factory, TileQueryHelper.TileInfo tileInfo, boolean z2, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                z2 = false;
            }
            return factory.create(tileInfo, z2);
        }

        public final QSRecord create(QSTile tile, boolean z2) {
            n.g(tile, "tile");
            String tileSpec = tile.getTileSpec();
            n.f(tileSpec, "getTileSpec(...)");
            QSRecord qSRecordCreate = create(tileSpec, z2);
            qSRecordCreate.attachQSTile(tile);
            return qSRecordCreate;
        }

        public final QSRecord create(TileQueryHelper.TileInfo tile, boolean z2) {
            n.g(tile, "tile");
            QSRecord qSRecordCreate = create(tile.getSpec(), z2);
            qSRecordCreate.attachTileInfo(tile);
            return qSRecordCreate;
        }
    }

    public /* synthetic */ QSRecord(String str, boolean z2, MiuiQSHost miuiQSHost, Context context, QSController qSController, Lifecycle lifecycle, HapticFeedback hapticFeedback, Looper looper, E0.a aVar, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z2, miuiQSHost, context, qSController, lifecycle, hapticFeedback, looper, aVar);
    }

    private final QSRecord$callback$2.AnonymousClass1 getCallback() {
        return (QSRecord$callback$2.AnonymousClass1) this.callback$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getIconWithAnim() {
        return (((SecondaryPanelRouter) this.secondaryPanelRouter.get()).getInMainPanel() && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) ? 1 : 0;
    }

    private final void setMode(MainPanelController.Mode mode) {
        if (this.mode == mode) {
            return;
        }
        this.mode = mode;
        setListening(mode == MainPanelController.Mode.NORMAL);
    }

    private final void updateDraggable() {
        MainPanelItemViewHolder holder = getHolder();
        if (holder == null) {
            return;
        }
        holder.setDraggable((this.isCard || !this.added || this.mode == MainPanelController.Mode.NORMAL) ? false : true);
    }

    public final void addCallback() {
        QSTile qSTile = this.tile;
        if (qSTile != null) {
            qSTile.addCallback(getCallback());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final miui.systemui.controlcenter.panel.main.qs.QSRecord attachQSTile(com.android.systemui.plugins.qs.QSTile r6) {
        /*
            r5 = this;
            com.android.systemui.plugins.qs.QSTile r0 = r5.tile
            if (r0 != r6) goto L5
            return r5
        L5:
            r1 = 0
            if (r0 == 0) goto L12
            r0.setListening(r5, r1)
            miui.systemui.controlcenter.panel.main.qs.QSRecord$callback$2$1 r2 = r5.getCallback()
            r0.removeCallback(r2)
        L12:
            r5.tile = r6
            if (r6 == 0) goto L24
            miui.systemui.controlcenter.panel.main.qs.QSRecord$callback$2$1 r0 = r5.getCallback()
            r6.addCallback(r0)
            boolean r0 = r5.getListening()
            r6.setListening(r5, r0)
        L24:
            miui.systemui.controlcenter.panel.main.qs.QSRecord$uiHandler$1 r0 = r5.uiHandler
            r2 = 78283(0x131cb, float:1.09698E-40)
            r0.removeMessages(r2)
            miui.systemui.controlcenter.panel.main.qs.QSRecord$uiHandler$1 r0 = r5.uiHandler
            if (r6 == 0) goto L38
            boolean r3 = r6.isConnected()
            r4 = 1
            if (r3 != r4) goto L38
            goto L39
        L38:
            r4 = r1
        L39:
            if (r6 == 0) goto L40
            com.android.systemui.plugins.qs.QSTile$State r6 = r6.getState()
            goto L41
        L40:
            r6 = 0
        L41:
            android.os.Message r6 = r0.obtainMessage(r2, r4, r1, r6)
            r6.sendToTarget()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.panel.main.qs.QSRecord.attachQSTile(com.android.systemui.plugins.qs.QSTile):miui.systemui.controlcenter.panel.main.qs.QSRecord");
    }

    public final QSRecord attachTileInfo(TileQueryHelper.TileInfo tileInfo) {
        n.g(tileInfo, "tileInfo");
        if (DEBUG) {
            Log.d(this.tag, "attachTileInfo " + tileInfo.getSpec() + " " + tileInfo.getState());
        }
        if (this.tileInfo == tileInfo) {
            return this;
        }
        this.tileInfo = tileInfo;
        removeMessages(MSG_UPDATE_CUSTOMIZE_STATE);
        obtainMessage(MSG_UPDATE_CUSTOMIZE_STATE, 0, 0, tileInfo.getState()).sendToTarget();
        return this;
    }

    public final boolean getAdded() {
        return this.added;
    }

    public final boolean getCompatTile() {
        return this.compatTile;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem.Base
    public boolean getListening() {
        return this.listening;
    }

    public final boolean getRemovable() {
        return this.removable;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public int getSpanSize() {
        return this.spanSize;
    }

    public final String getSpec() {
        return this.spec;
    }

    public final QSTile getTile() {
        return this.tile;
    }

    public final TileQueryHelper.TileInfo getTileInfo() {
        return this.tileInfo;
    }

    public final QSItemView getTileView() {
        MainPanelItemViewHolder holder = getHolder();
        View view = holder != null ? holder.itemView : null;
        if (view instanceof QSItemView) {
            return (QSItemView) view;
        }
        return null;
    }

    public final DetailPanelTilesDelegate getTilesDelegate() {
        return this.tilesDelegate;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public int getType() {
        return this.type;
    }

    public final boolean isCard() {
        return this.isCard;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0061  */
    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onBindViewHolder() {
        /*
            r6 = this;
            miui.systemui.controlcenter.qs.tileview.QSItemView r0 = r6.getTileView()
            if (r0 == 0) goto L11
            kotlin.jvm.functions.Function1 r1 = r6.clickAction
            kotlin.jvm.functions.Function1 r2 = r6.secondaryClickAction
            kotlin.jvm.functions.Function1 r3 = r6.markClickAction
            kotlin.jvm.functions.Function1 r4 = r6.longClickAction
            r0.attachListeners(r1, r2, r3, r4)
        L11:
            miui.systemui.controlcenter.panel.main.qs.QSRecord$uiHandler$1 r0 = r6.uiHandler
            r1 = 78283(0x131cb, float:1.09698E-40)
            r0.removeMessages(r1)
            miui.systemui.controlcenter.panel.main.qs.QSRecord$uiHandler$1 r0 = r6.uiHandler
            r1 = 287866(0x4647a, float:4.03386E-40)
            r0.removeMessages(r1)
            miui.systemui.controlcenter.qs.tileview.QSItemView r0 = r6.getTileView()
            r1 = 0
            if (r0 == 0) goto L2d
            boolean r2 = r6.added
            r0.updateAdded(r2, r1)
        L2d:
            miui.systemui.controlcenter.qs.tileview.QSItemView r0 = r6.getTileView()
            if (r0 != 0) goto L34
            goto L39
        L34:
            boolean r2 = r6.compatTile
            r0.setCompatTile(r2)
        L39:
            miui.systemui.controlcenter.qs.tileview.QSItemView r0 = r6.getTileView()
            if (r0 == 0) goto L44
            boolean r2 = r6.removable
            r0.updateRemovable(r2, r1)
        L44:
            miui.systemui.controlcenter.qs.tileview.QSItemView r0 = r6.getTileView()
            r2 = 0
            if (r0 == 0) goto L65
            com.android.systemui.plugins.qs.QSTile r3 = r6.tile
            if (r3 == 0) goto L54
            com.android.systemui.plugins.qs.QSTile$State r3 = r3.getState()
            goto L55
        L54:
            r3 = r2
        L55:
            com.android.systemui.plugins.qs.QSTile r4 = r6.tile
            if (r4 == 0) goto L61
            boolean r4 = r4.isConnected()
            r5 = 1
            if (r4 != r5) goto L61
            goto L62
        L61:
            r5 = r1
        L62:
            r0.updateState(r3, r5, r1)
        L65:
            miui.systemui.controlcenter.qs.tileview.QSItemView r0 = r6.getTileView()
            if (r0 == 0) goto L76
            miui.systemui.controlcenter.qs.customize.TileQueryHelper$TileInfo r6 = r6.tileInfo
            if (r6 == 0) goto L73
            com.android.systemui.plugins.qs.QSTile$State r2 = r6.getState()
        L73:
            r0.updateCustomizeState(r2, r1)
        L76:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.systemui.controlcenter.panel.main.qs.QSRecord.onBindViewHolder():void");
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public void onUnbindViewHolder() {
        QSItemView tileView = getTileView();
        if (tileView != null) {
            tileView.detachListeners();
        }
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem.Base, miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public void onViewAttachedToWindow() {
        startListening();
    }

    public final void removeCallback() {
        QSTile qSTile = this.tile;
        if (qSTile != null) {
            qSTile.removeCallback(getCallback());
        }
    }

    public final void setAdded(boolean z2) {
        if (this.added == z2) {
            return;
        }
        this.added = z2;
        QSItemView tileView = getTileView();
        if (tileView != null) {
            tileView.updateAdded(z2, true);
        }
        updateDraggable();
    }

    public final void setCompatTile(boolean z2) {
        if (this.compatTile == z2) {
            return;
        }
        this.compatTile = z2;
        QSItemView tileView = getTileView();
        if (tileView == null) {
            return;
        }
        tileView.setCompatTile(z2);
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem.Base
    public void setListening(boolean z2) {
        if (this.listening == z2) {
            return;
        }
        Log.d(this.tag, "set listening to " + z2);
        this.listening = z2;
        QSTile qSTile = this.tile;
        if (qSTile != null) {
            qSTile.setListening(this, z2);
        }
    }

    public final void setRemovable(boolean z2) {
        if (this.removable == z2) {
            return;
        }
        this.removable = z2;
        QSItemView tileView = getTileView();
        if (tileView != null) {
            tileView.updateRemovable(z2, true);
        }
    }

    public final void setTilesDelegate(DetailPanelTilesDelegate detailPanelTilesDelegate) {
        this.tilesDelegate = detailPanelTilesDelegate;
    }

    public final void startListening() {
        MainPanelItemViewHolder holder = getHolder();
        if (holder != null && holder.getAttached$miui_controlcenter_release() && this.mode == MainPanelController.Mode.NORMAL && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            setListening(true);
        }
    }

    public final void startMarquee() {
        QSItemView tileView;
        MainPanelItemViewHolder holder = getHolder();
        if (holder != null && holder.getAttached$miui_controlcenter_release() && this.mode == MainPanelController.Mode.NORMAL && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) && (tileView = getTileView()) != null) {
            tileView.startMarquee();
        }
    }

    public final void stopMarquee() {
        QSItemView tileView = getTileView();
        if (tileView != null) {
            tileView.stopMarquee();
        }
    }

    public String toString() {
        return this.spec;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public void updateMode(MainPanelController.Mode mode, boolean z2) {
        n.g(mode, "mode");
        setMode(mode);
        updateDraggable();
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [miui.systemui.controlcenter.panel.main.qs.QSRecord$uiHandler$1] */
    private QSRecord(String str, boolean z2, MiuiQSHost miuiQSHost, Context context, QSController qSController, Lifecycle lifecycle, HapticFeedback hapticFeedback, final Looper looper, E0.a aVar) {
        super(lifecycle);
        this.spec = str;
        this.isCard = z2;
        this.host = miuiQSHost;
        this.context = context;
        this.qsController = qSController;
        this.hapticFeedback = hapticFeedback;
        this.uiLooper = looper;
        this.secondaryPanelRouter = aVar;
        this.tag = "QSRecord " + str;
        this.removable = true;
        this.uiHandler = new Handler(looper) { // from class: miui.systemui.controlcenter.panel.main.qs.QSRecord$uiHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                DetailPanelTilesDelegate tilesDelegate;
                QSItemView tileView;
                n.g(msg, "msg");
                int i2 = msg.what;
                if (i2 != 78283) {
                    if (i2 == 287866 && (tileView = this.this$0.getTileView()) != null) {
                        Object obj = msg.obj;
                        tileView.updateCustomizeState(obj instanceof QSTile.State ? (QSTile.State) obj : null, msg.arg1 == 1);
                        return;
                    }
                    return;
                }
                QSItemView tileView2 = this.this$0.getTileView();
                if (tileView2 != null) {
                    Object obj2 = msg.obj;
                    tileView2.updateState(obj2 instanceof QSTile.State ? (QSTile.State) obj2 : null, msg.arg1 == 1, msg.arg2 == 1);
                }
                if (this.this$0.getCompatTile() || (tilesDelegate = this.this$0.getTilesDelegate()) == null) {
                    return;
                }
                tilesDelegate.updateTile(this.this$0.getTile());
            }
        };
        this.callback$delegate = H0.e.b(new QSRecord$callback$2(this));
        this.clickAction = new QSRecord$clickAction$1(this);
        this.longClickAction = new QSRecord$longClickAction$1(this);
        this.secondaryClickAction = new QSRecord$secondaryClickAction$1(this);
        this.markClickAction = new QSRecord$markClickAction$1(this);
        this.mode = MainPanelController.Mode.NORMAL;
        this.type = z2 ? 2273 : 8453;
        this.spanSize = z2 ? 2 : 1;
    }
}
