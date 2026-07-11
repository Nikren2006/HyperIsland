package miui.systemui.controlcenter.qs;

import H0.d;
import H0.e;
import android.app.ActivityManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import com.android.systemui.plugins.qs.DetailAdapter;
import com.android.systemui.plugins.qs.QSFactory;
import com.android.systemui.plugins.qs.QSTile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.os.Build;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.SecondaryPanelRouter;
import miui.systemui.controlcenter.panel.main.MainPanelContent;
import miui.systemui.controlcenter.panel.main.MainPanelModeController;
import miui.systemui.controlcenter.panel.main.header.MessageHeaderController;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.panel.main.qs.QSRecord;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.RotationItemViewHolder;
import miui.systemui.controlcenter.panel.secondary.DetailCallback;
import miui.systemui.controlcenter.panel.secondary.DetailFromView;
import miui.systemui.controlcenter.panel.secondary.DetailPanelParams;
import miui.systemui.controlcenter.panel.secondary.detail.DetailPanelController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.util.CommonUtils;
import miui.util.DeviceLevel;
import systemui.plugin.eventtracking.EventTracker;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class QSController extends ControlCenterViewController<ControlCenterWindowViewImpl> {
    private static final int ANNOUNCE_FOR_ACCESSIBILITY = 2;
    public static final Companion Companion = new Companion(null);
    private static final boolean LOW_END_DEVICE;
    private static final boolean PAD_SHARE_NETWORK_ENABLED;
    private static final int SHOW_DETAIL = 1;
    private static final int SHOW_EDIT = 1001;
    private static final String TAG = "QSController";
    private final d _longClickToDetail$delegate;
    private final d cardStyleTileSpecs$delegate;
    private final d compactCardStyleTileSpecs$delegate;
    private final d compactTileSpecs$delegate;
    private final d configCellularSharedSupport$delegate;
    private final QSController$detailCallback$1 detailCallback;
    private final E0.a detailController;
    private QSRecord detailRecord;
    private final QSController$handler$1 handler;
    private final MiuiQSHost host;
    private final d isSystemUISupportShareNetwork$delegate;
    private final E0.a modeController;
    private final E0.a msgHeaderController2;
    private final E0.a qsListController;
    private final d qsListEndExcludeTileSpecs$delegate;
    private final d qsListExcludeTileSpecs$delegate;
    private final d qsListStartExcludeTileSpecs$delegate;
    private final E0.a secondaryPanelRouter;
    private final d voWifiTileSpecs$delegate;
    private final E0.a windowViewController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.qs.QSController$isSystemUISupportShareNetwork$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function0 {
        public AnonymousClass2() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            boolean z2 = false;
            try {
                z2 = QSController.this.getContext().getPackageManager().getApplicationInfo("com.android.systemui", 128).metaData.getBoolean("com.android.systemui.share_network_enabled", false);
            } catch (Throwable unused) {
            }
            return Boolean.valueOf(z2);
        }
    }

    static {
        boolean z2 = DeviceLevel.IS_MIUI_LITE_VERSION || Build.IS_MIUI_LITE_VERSION || CommonUtils.INSTANCE.getMIUI_LITE_V2();
        LOW_END_DEVICE = z2;
        PAD_SHARE_NETWORK_ENABLED = (!CommonUtils.INSTANCE.getIS_TABLET() || Build.IS_INTERNATIONAL_BUILD || z2) ? false : true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v1, types: [miui.systemui.controlcenter.qs.QSController$handler$1] */
    /* JADX WARN: Type inference failed for: r2v22, types: [miui.systemui.controlcenter.qs.QSController$detailCallback$1] */
    public QSController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, @Main final Looper mainLooper, MiuiQSHost host, E0.a secondaryPanelRouter, E0.a detailController, E0.a qsListController, E0.a windowViewController, E0.a msgHeaderController2, E0.a modeController) {
        super(windowView);
        n.g(windowView, "windowView");
        n.g(mainLooper, "mainLooper");
        n.g(host, "host");
        n.g(secondaryPanelRouter, "secondaryPanelRouter");
        n.g(detailController, "detailController");
        n.g(qsListController, "qsListController");
        n.g(windowViewController, "windowViewController");
        n.g(msgHeaderController2, "msgHeaderController2");
        n.g(modeController, "modeController");
        this.host = host;
        this.secondaryPanelRouter = secondaryPanelRouter;
        this.detailController = detailController;
        this.qsListController = qsListController;
        this.windowViewController = windowViewController;
        this.msgHeaderController2 = msgHeaderController2;
        this.modeController = modeController;
        this.handler = new Handler(mainLooper) { // from class: miui.systemui.controlcenter.qs.QSController$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                n.g(msg, "msg");
                if (msg.what != 1) {
                    return;
                }
                QSController qSController = this;
                boolean z2 = msg.arg1 != 0;
                Object obj = msg.obj;
                n.e(obj, "null cannot be cast to non-null type miui.systemui.controlcenter.panel.main.qs.QSRecord");
                qSController.handleShowDetail(z2, (QSRecord) obj);
            }
        };
        this.configCellularSharedSupport$delegate = e.b(new QSController$configCellularSharedSupport$2(this));
        this.isSystemUISupportShareNetwork$delegate = e.b(new AnonymousClass2());
        this.cardStyleTileSpecs$delegate = e.b(new QSController$cardStyleTileSpecs$2(this));
        this.compactTileSpecs$delegate = e.b(new QSController$compactTileSpecs$2(this));
        this.compactCardStyleTileSpecs$delegate = e.b(new QSController$compactCardStyleTileSpecs$2(this));
        this.voWifiTileSpecs$delegate = e.b(new QSController$voWifiTileSpecs$2(this));
        this.qsListEndExcludeTileSpecs$delegate = e.b(QSController$qsListEndExcludeTileSpecs$2.INSTANCE);
        this.qsListStartExcludeTileSpecs$delegate = e.b(new QSController$qsListStartExcludeTileSpecs$2(this));
        this.qsListExcludeTileSpecs$delegate = e.b(new QSController$qsListExcludeTileSpecs$2(this));
        this._longClickToDetail$delegate = e.b(new QSController$_longClickToDetail$2(this));
        this.detailCallback = new DetailCallback() { // from class: miui.systemui.controlcenter.qs.QSController$detailCallback$1
            @Override // miui.systemui.controlcenter.panel.secondary.DetailCallback
            public void onDetailHidden() {
                QSTile tile;
                QSRecord qSRecord = this.this$0.detailRecord;
                if (qSRecord != null && (tile = qSRecord.getTile()) != null) {
                    tile.setDetailListening(false);
                }
                this.this$0.detailRecord = null;
            }

            @Override // miui.systemui.controlcenter.panel.secondary.DetailCallback
            public void onMoreButtonClicked() {
                QSTile tile;
                String string;
                QSRecord qSRecord = this.this$0.detailRecord;
                if (qSRecord == null || (tile = qSRecord.getTile()) == null) {
                    return;
                }
                ControlCenterEventTracker.Companion companion = ControlCenterEventTracker.Companion;
                String screenType = EventTracker.Companion.getScreenType(this.this$0.getContext());
                String str = qSRecord.isCard() ? ControlCenterEventTracker.QS_STYLE_CARD : "按钮";
                int iIndexOf = this.this$0.host.indexOf(tile.getTileSpec());
                int i2 = this.this$0.getContext().getResources().getConfiguration().orientation;
                String tileSpec = tile.getTileSpec();
                String str2 = tileSpec == null ? "" : tileSpec;
                CharSequence tileLabel = tile.getTileLabel();
                String str3 = (tileLabel == null || (string = tileLabel.toString()) == null) ? "" : string;
                QSTile.State state = tile.getState();
                companion.trackQuickSettingsClickEvent(screenType, str, iIndexOf, i2, str2, str3, "二级页更多按钮", ControlCenterEventTracker.OTHER_PAGE, state != null ? state.state : 0);
            }

            @Override // miui.systemui.controlcenter.panel.secondary.DetailCallback
            public void onToggleClicked(boolean z2) {
                QSTile tile;
                String string;
                QSRecord qSRecord = this.this$0.detailRecord;
                if (qSRecord == null || (tile = qSRecord.getTile()) == null) {
                    return;
                }
                ControlCenterEventTracker.Companion companion = ControlCenterEventTracker.Companion;
                String screenType = EventTracker.Companion.getScreenType(this.this$0.getContext());
                String str = qSRecord.isCard() ? ControlCenterEventTracker.QS_STYLE_CARD : "按钮";
                int iIndexOf = this.this$0.host.indexOf(tile.getTileSpec());
                int i2 = this.this$0.getContext().getResources().getConfiguration().orientation;
                String tileSpec = tile.getTileSpec();
                String str2 = tileSpec == null ? "" : tileSpec;
                CharSequence tileLabel = tile.getTileLabel();
                String str3 = (tileLabel == null || (string = tileLabel.toString()) == null) ? "" : string;
                QSTile.State state = tile.getState();
                companion.trackQuickSettingsClickEvent(screenType, str, iIndexOf, i2, str2, str3, ControlCenterEventTracker.ELEMENT_STYLE_DETAIL_TOGGLE, ControlCenterEventTracker.OTHER_PAGE, state != null ? state.state : 0);
            }
        };
    }

    private final boolean getConfigCellularSharedSupport() {
        return ((Boolean) this.configCellularSharedSupport$delegate.getValue()).booleanValue();
    }

    private final boolean get_longClickToDetail() {
        return ((Boolean) this._longClickToDetail$delegate.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void handleShowDetail(boolean z2, QSRecord qSRecord) {
        MainPanelContent owner;
        Log.d(TAG, "handleShowDetail " + z2 + " " + qSRecord + " " + this.detailRecord);
        QSRecord qSRecord2 = this.detailRecord;
        if (qSRecord2 != null || z2) {
            boolean rightOrLeft = false;
            if ((qSRecord2 != null) == z2 && qSRecord2 == qSRecord) {
                return;
            }
            QSTile tile = qSRecord.getTile();
            if ((tile != null ? tile.getDetailAdapter() : null) == null && z2) {
                return;
            }
            if (qSRecord.getHolder() == null) {
                Log.e(TAG, "handleShowDetail record.holder is null");
                return;
            }
            if (((ControlCenterWindowViewController) this.windowViewController.get()).getExpandController().getInMirror()) {
                Log.w(TAG, "long click: in mirror");
                return;
            }
            if (!((MainPanelModeController) this.modeController.get()).getInNormalMode() || ((QSListController) this.qsListController.get()).getInPendingEditMode()) {
                Log.w(TAG, "long click: not in normal mode");
                MainPanelItemViewHolder holder = qSRecord.getHolder();
                RotationItemViewHolder rotationItemViewHolder = holder instanceof RotationItemViewHolder ? (RotationItemViewHolder) holder : null;
                if (rotationItemViewHolder != null) {
                    rotationItemViewHolder.touchRelease();
                    return;
                }
                return;
            }
            if (((SecondaryPanelRouter) this.secondaryPanelRouter.get()).getToDetailPanel()) {
                Log.w(TAG, "long click: already to detail panel");
                return;
            }
            QSTile tile2 = qSRecord.getTile();
            if (tile2 != null) {
                tile2.setDetailListening(z2);
            }
            String spec = qSRecord.getSpec();
            boolean zC = n.c(spec, "bt") ? true : n.c(spec, TileSpecsKt.TILE_SPEC_WIFI);
            ((DetailPanelController) this.detailController.get()).getDelegate().setDetailCallBack(this.detailCallback);
            this.detailRecord = z2 ? qSRecord : null;
            if (!z2) {
                ((SecondaryPanelRouter) this.secondaryPanelRouter.get()).routeToMain();
                return;
            }
            SecondaryPanelRouter secondaryPanelRouter = (SecondaryPanelRouter) this.secondaryPanelRouter.get();
            QSTile tile3 = qSRecord.getTile();
            Object detailAdapter = tile3 != null ? tile3.getDetailAdapter() : null;
            n.e(detailAdapter, "null cannot be cast to non-null type com.android.systemui.plugins.qs.DetailAdapter");
            DetailAdapter detailAdapter2 = (DetailAdapter) detailAdapter;
            MainPanelItemViewHolder holder2 = qSRecord.getHolder();
            DetailFromView detailFromView = holder2 instanceof DetailFromView ? (DetailFromView) holder2 : null;
            MainPanelItemViewHolder holder3 = qSRecord.getHolder();
            if (holder3 != null && (owner = holder3.getOwner()) != null) {
                rightOrLeft = owner.getRightOrLeft();
            }
            secondaryPanelRouter.routeToDetail(new DetailPanelParams(detailAdapter2, detailFromView, rightOrLeft, zC));
        }
    }

    private final boolean isSystemUISupportShareNetwork() {
        return ((Boolean) this.isSystemUISupportShareNetwork$delegate.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showMessage$lambda$1(QSController this$0, CharSequence msg) {
        n.g(this$0, "this$0");
        n.g(msg, "$msg");
        ((MessageHeaderController) this$0.msgHeaderController2.get()).showMsg(msg);
    }

    public final void addTile(QSRecord record) {
        n.g(record, "record");
        ((QSListController) this.qsListController.get()).addTile(record);
    }

    public final QSTile createTile(String spec) {
        n.g(spec, "spec");
        ArrayList qsFactories = this.host.getQsFactories();
        if (qsFactories != null) {
            Iterator it = qsFactories.iterator();
            while (it.hasNext()) {
                QSTile qSTileCreateTile = ((QSFactory) it.next()).createTile(spec, true);
                if (qSTileCreateTile != null) {
                    n.d(qSTileCreateTile);
                    qSTileCreateTile.setTileSpec(spec);
                    qSTileCreateTile.userSwitch(ActivityManager.getCurrentUser());
                    qSTileCreateTile.refreshState();
                } else {
                    qSTileCreateTile = null;
                }
                if (qSTileCreateTile != null) {
                    return qSTileCreateTile;
                }
            }
        }
        return null;
    }

    public final void fireScanStateChanged(boolean z2) {
        ((DetailPanelController) this.detailController.get()).getDelegate().onScanStateChanged(z2);
    }

    public final void fireToggleStateChanged(QSRecord record, boolean z2) {
        n.g(record, "record");
        if (record == this.detailRecord) {
            ((DetailPanelController) this.detailController.get()).getDelegate().onToggleStateChanged(z2);
        }
    }

    public final List<String> getCardStyleTileSpecs() {
        return (List) this.cardStyleTileSpecs$delegate.getValue();
    }

    public final List<String> getCompactCardStyleTileSpecs() {
        return (List) this.compactCardStyleTileSpecs$delegate.getValue();
    }

    public final List<String> getCompactTileSpecs() {
        return (List) this.compactTileSpecs$delegate.getValue();
    }

    public final boolean getLongClickToDetail() {
        return get_longClickToDetail() && !((ControlCenterWindowViewController) this.windowViewController.get()).getSuperPowerMode();
    }

    public final E0.a getQsListController() {
        return this.qsListController;
    }

    public final ArrayList<String> getQsListEndExcludeTileSpecs() {
        return (ArrayList) this.qsListEndExcludeTileSpecs$delegate.getValue();
    }

    public final ArrayList<String> getQsListExcludeTileSpecs() {
        return (ArrayList) this.qsListExcludeTileSpecs$delegate.getValue();
    }

    public final ArrayList<String> getQsListStartExcludeTileSpecs() {
        return (ArrayList) this.qsListStartExcludeTileSpecs$delegate.getValue();
    }

    public final QSRecord getTile(String str) {
        for (QSRecord qSRecord : ((QSListController) this.qsListController.get()).getAddedTiles()) {
            if (n.c(qSRecord.getSpec(), str)) {
                return qSRecord;
            }
        }
        return ((QSListController) this.qsListController.get()).getQsCardsController().getTile(str);
    }

    public final List<String> getVoWifiTileSpecs() {
        return (List) this.voWifiTileSpecs$delegate.getValue();
    }

    public final void removeTile(QSRecord record) {
        n.g(record, "record");
        ((QSListController) this.qsListController.get()).removeTile(record);
    }

    public final void showDetail(boolean z2, QSRecord record) {
        n.g(record, "record");
        obtainMessage(1, z2 ? 1 : 0, 0, record).sendToTarget();
    }

    public final void showMessage(final CharSequence msg) {
        n.g(msg, "msg");
        post(new Runnable() { // from class: miui.systemui.controlcenter.qs.a
            @Override // java.lang.Runnable
            public final void run() {
                QSController.showMessage$lambda$1(this.f5478a, msg);
            }
        });
    }

    public final boolean supportMobileTilesStyle() {
        if (((TelephonyManager) getContext().getSystemService(TelephonyManager.class)).isDataCapable()) {
            return true;
        }
        if (!PAD_SHARE_NETWORK_ENABLED) {
            return false;
        }
        if (!getConfigCellularSharedSupport()) {
            Log.i(TAG, "supportMobileTilesStyle configCellularSharedSupport false");
            return false;
        }
        Log.i(TAG, "supportMobileTilesStyle isSystemUISupportShareNetwork " + isSystemUISupportShareNetwork());
        return isSystemUISupportShareNetwork();
    }

    public final void fireScanStateChanged(QSRecord record, boolean z2) {
        n.g(record, "record");
        if (record == this.detailRecord) {
            ((DetailPanelController) this.detailController.get()).getDelegate().onScanStateChanged(z2);
        }
    }
}
