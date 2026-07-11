package miui.systemui.controlcenter.panel.main.qs;

import I0.u;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.miui.settings.SuperSaveModeController;
import com.android.systemui.plugins.qs.QSTile;
import com.xiaomi.onetrack.util.aa;
import f1.o;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.flipQs.utils.QSFlipUtils;
import miui.systemui.controlcenter.panel.main.MainPanelContent;
import miui.systemui.controlcenter.panel.main.MainPanelContentDistributor;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.qs.CompactQSListController$broadcastReceiver$2;
import miui.systemui.controlcenter.panel.main.qs.QSItemViewHolder;
import miui.systemui.controlcenter.panel.main.qs.QSRecord;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem;
import miui.systemui.controlcenter.qs.QSController;
import miui.systemui.controlcenter.qs.customize.TileQueryHelper;
import miui.systemui.controlcenter.qs.tileview.QSTileItemIconView;
import miui.systemui.controlcenter.qs.tileview.QSTileItemView;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.SystemUI;
import miuix.os.Build;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class CompactQSListController extends ControlCenterViewController<ControlCenterWindowViewImpl> implements MainPanelContent {
    public static final Companion Companion = new Companion(null);
    private static final long DAILY_REGISTER_TIME = 2000;
    private static final int MSG_FORM_PKG_CHANGE = 0;
    private static final int MSG_NOT_FORM_PKG_CHANGE = 1;
    private static final String TAG = "CompactQSListController";
    public static final int TYPE_COMPACT_QS_TILE = 8453;
    private final Handler bgHandler;
    private final H0.d broadcastReceiver$delegate;
    private final E0.a distributor;
    private List<String> flipPkgNameStockPool;
    private List<String> flipPkgTileStockPool;
    private ContentObserver flipQsObserver;
    private List<String> flipTileStockPoolList;
    private final QSItemViewHolder.Factory holderFactory;
    private final Handler mainHandler;
    private final E0.a mainPanelController;
    private final ArrayList<QSTile> prepareShowQSList;
    private final int priority;
    private final E0.a qsController;
    private final E0.a qsListController;
    private final ArrayList<QSRecord> qsRecords;
    private final QSRecord.Factory recordFactory;
    private final boolean rightOrLeft;
    private final SuperSaveModeController superSaveModeController;
    private final ArrayList<String> supportTileList;
    private final Context sysUIContext;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompactQSListController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, @SystemUI Context sysUIContext, E0.a qsController, QSRecord.Factory recordFactory, QSItemViewHolder.Factory holderFactory, @Background Handler bgHandler, E0.a mainPanelController, E0.a qsListController, E0.a distributor, SuperSaveModeController superSaveModeController) {
        super(windowView);
        n.g(windowView, "windowView");
        n.g(sysUIContext, "sysUIContext");
        n.g(qsController, "qsController");
        n.g(recordFactory, "recordFactory");
        n.g(holderFactory, "holderFactory");
        n.g(bgHandler, "bgHandler");
        n.g(mainPanelController, "mainPanelController");
        n.g(qsListController, "qsListController");
        n.g(distributor, "distributor");
        n.g(superSaveModeController, "superSaveModeController");
        this.sysUIContext = sysUIContext;
        this.qsController = qsController;
        this.recordFactory = recordFactory;
        this.holderFactory = holderFactory;
        this.bgHandler = bgHandler;
        this.mainPanelController = mainPanelController;
        this.qsListController = qsListController;
        this.distributor = distributor;
        this.superSaveModeController = superSaveModeController;
        this.qsRecords = new ArrayList<>();
        this.prepareShowQSList = new ArrayList<>();
        this.supportTileList = new ArrayList<>();
        final Looper mainLooper = Looper.getMainLooper();
        this.mainHandler = new Handler(mainLooper) { // from class: miui.systemui.controlcenter.panel.main.qs.CompactQSListController$mainHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                n.g(msg, "msg");
                super.handleMessage(msg);
                int i2 = msg.what;
                if (i2 == 0 || i2 == 1) {
                    this.this$0.distributeTiles(true);
                }
            }
        };
        this.priority = 99;
        this.rightOrLeft = true;
        this.broadcastReceiver$delegate = H0.e.b(new CompactQSListController$broadcastReceiver$2(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void distributeTiles(boolean z2) {
        getAllSupportTiles(z2);
        getPrepareShowList();
        Iterator<T> it = this.qsRecords.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).removeCallback();
        }
        this.qsRecords.clear();
        ArrayList<QSRecord> arrayList = this.qsRecords;
        ArrayList<QSTile> arrayList2 = this.prepareShowQSList;
        ArrayList arrayList3 = new ArrayList(I0.n.o(arrayList2, 10));
        Iterator<T> it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            QSRecord qSRecordCreate = this.recordFactory.create((QSTile) it2.next(), false);
            qSRecordCreate.addCallback();
            qSRecordCreate.setCompatTile(true);
            arrayList3.add(qSRecordCreate);
        }
        arrayList.addAll(arrayList3);
        Object obj = this.distributor.get();
        n.f(obj, "get(...)");
        MainPanelContentDistributor.handleNotifyChanged$default((MainPanelContentDistributor) obj, false, 1, null);
    }

    public static /* synthetic */ void distributeTiles$default(CompactQSListController compactQSListController, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        compactQSListController.distributeTiles(z2);
    }

    private final void getAllSupportTiles(boolean z2) {
        ArrayList<String> arrayList = new ArrayList();
        List<String> list = this.flipTileStockPoolList;
        if (list == null) {
            n.w("flipTileStockPoolList");
            list = null;
        }
        arrayList.addAll(list);
        List<String> pkgTiles = getPkgTiles(z2);
        Iterator it = arrayList.iterator();
        n.f(it, "iterator(...)");
        while (it.hasNext()) {
            Object next = it.next();
            n.f(next, "next(...)");
            String str = (String) next;
            List<String> list2 = this.flipPkgTileStockPool;
            if (list2 == null) {
                n.w("flipPkgTileStockPool");
                list2 = null;
            }
            if (list2.contains(str) && !pkgTiles.isEmpty() && !pkgTiles.contains(str)) {
                it.remove();
            }
        }
        this.supportTileList.clear();
        for (String str2 : arrayList) {
            QSTile qSTileCreateTile = ((QSController) this.qsController.get()).createTile(str2);
            if (qSTileCreateTile != null && qSTileCreateTile.isAvailable()) {
                this.supportTileList.add(str2);
            }
            if (qSTileCreateTile != null) {
                qSTileCreateTile.destroy();
            }
        }
        String strJoin = TextUtils.join(aa.f3429b, this.supportTileList);
        n.f(strJoin, "join(...)");
        updateSupportTiles(strJoin);
    }

    public static /* synthetic */ void getAllSupportTiles$default(CompactQSListController compactQSListController, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        compactQSListController.getAllSupportTiles(z2);
    }

    private final CompactQSListController$broadcastReceiver$2.AnonymousClass1 getBroadcastReceiver() {
        return (CompactQSListController$broadcastReceiver$2.AnonymousClass1) this.broadcastReceiver$delegate.getValue();
    }

    private final List<String> getPkgTiles(boolean z2) {
        ArrayList arrayList = new ArrayList();
        Log.d(TAG, "getPkgTiles  " + z2);
        if (z2) {
            List<ResolveInfo> listQueryIntentServicesAsUser = this.sysUIContext.getPackageManager().queryIntentServicesAsUser(new Intent("android.service.quicksettings.action.QS_TILE"), 128, ActivityManager.getCurrentUser());
            n.f(listQueryIntentServicesAsUser, "queryIntentServicesAsUser(...)");
            for (ResolveInfo resolveInfo : listQueryIntentServicesAsUser) {
                arrayList.add(TileQueryHelper.Companion.toSpec(new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name)));
            }
        } else {
            String string = Settings.Secure.getString(getContext().getContentResolver(), QSFlipUtils.SYSTEM_UI_SUPPORT_FLIP_QS_TILES);
            if (string == null) {
                string = "";
            }
            String str = string;
            arrayList.addAll(str.length() > 0 ? u.m0(o.T(str, new String[]{aa.f3429b}, false, 0, 6, null)) : new ArrayList());
        }
        return arrayList;
    }

    private final void getPrepareShowList() {
        List listM0;
        String stringForUser = Settings.Secure.getStringForUser(getContext().getContentResolver(), QSFlipUtils.SYSTEM_UI_FLIP_QS_TILES, -2);
        if (stringForUser == null) {
            stringForUser = "";
        }
        String str = stringForUser;
        if (str.length() > 0) {
            listM0 = u.m0(o.T(str, new String[]{aa.f3429b}, false, 0, 6, null));
        } else {
            ContentResolver contentResolver = this.sysUIContext.getContentResolver();
            ContentObserver contentObserver = this.flipQsObserver;
            if (contentObserver == null) {
                n.w("flipQsObserver");
                contentObserver = null;
            }
            contentResolver.unregisterContentObserver(contentObserver);
            listM0 = u.m0(((QSController) this.qsController.get()).getCompactTileSpecs());
            String strJoin = TextUtils.join(aa.f3429b, listM0);
            n.f(strJoin, "join(...)");
            updateHasSetTiles(strJoin);
            this.mainHandler.postDelayed(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.qs.a
                @Override // java.lang.Runnable
                public final void run() {
                    CompactQSListController.getPrepareShowList$lambda$5(this.f5422a);
                }
            }, DAILY_REGISTER_TIME);
        }
        Iterator<T> it = this.prepareShowQSList.iterator();
        while (it.hasNext()) {
            ((QSTile) it.next()).destroy();
        }
        this.prepareShowQSList.clear();
        int size = listM0.size();
        Iterator it2 = listM0.iterator();
        while (it2.hasNext()) {
            String str2 = (String) it2.next();
            if (this.supportTileList.contains(str2)) {
                QSController qSController = (QSController) this.qsController.get();
                n.d(str2);
                QSTile qSTileCreateTile = qSController.createTile(str2);
                if (qSTileCreateTile != null) {
                    this.prepareShowQSList.add(qSTileCreateTile);
                }
            } else {
                it2.remove();
            }
        }
        if (size != listM0.size()) {
            String strJoin2 = TextUtils.join(aa.f3429b, listM0);
            n.f(strJoin2, "join(...)");
            updateHasSetTiles(strJoin2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getPrepareShowList$lambda$5(CompactQSListController this$0) {
        n.g(this$0, "this$0");
        this$0.registerHasSetTile();
    }

    private final void registerHasSetTile() {
        ContentResolver contentResolver = this.sysUIContext.getContentResolver();
        Uri uriFor = Settings.Secure.getUriFor(QSFlipUtils.SYSTEM_UI_FLIP_QS_TILES);
        ContentObserver contentObserver = this.flipQsObserver;
        if (contentObserver == null) {
            n.w("flipQsObserver");
            contentObserver = null;
        }
        contentResolver.registerContentObserver(uriFor, false, contentObserver, -2);
    }

    private final void updateHasSetTiles(String str) {
        Settings.Secure.putStringForUser(getContext().getContentResolver(), QSFlipUtils.SYSTEM_UI_FLIP_QS_TILES, str, null, false, -2, true);
    }

    private final void updateSupportTiles(String str) {
        Settings.Secure.putStringForUser(getContext().getContentResolver(), QSFlipUtils.SYSTEM_UI_SUPPORT_FLIP_QS_TILES, str, null, false, -2, true);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public void applyPayload(MainPanelItemViewHolder holder, MainPanelListItem item, Object payload) {
        n.g(holder, "holder");
        n.g(item, "item");
        n.g(payload, "payload");
        ((QSListController) this.qsListController.get()).applyPayload(holder, item, payload);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean available(boolean z2) {
        return !this.superSaveModeController.isActive() && ((MainPanelController) this.mainPanelController.get()).getStyle() == MainPanelController.Style.COMPACT && ((MainPanelController) this.mainPanelController.get()).getMode() == MainPanelController.Mode.NORMAL && !this.qsRecords.isEmpty();
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public MainPanelItemViewHolder createViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        if (i2 != 8453) {
            return null;
        }
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.qs_tile_item_view, parent, false);
        n.e(viewInflate, "null cannot be cast to non-null type miui.systemui.controlcenter.qs.tileview.QSTileItemView");
        QSTileItemView qSTileItemView = (QSTileItemView) viewInflate;
        qSTileItemView.init(new QSTileItemIconView(getContext(), this.sysUIContext, false, 4, null));
        return this.holderFactory.create(qSTileItemView);
    }

    public final Handler getMainHandler() {
        return this.mainHandler;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public int getPriority() {
        return this.priority;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean getRightOrLeft() {
        return this.rightOrLeft;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    @SuppressLint({"MissingSuperCall"})
    public void onBindViewHolder(MainPanelItemViewHolder holder, MainPanelListItem item) {
        n.g(holder, "holder");
        n.g(item, "item");
        ((QSListController) this.qsListController.get()).onBindViewHolder(holder, item);
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        this.flipQsObserver = new ContentObserver(this.bgHandler) { // from class: miui.systemui.controlcenter.panel.main.qs.CompactQSListController.onCreate.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z2) {
                Log.d(CompactQSListController.TAG, "flipQsObserver onChange");
                CompactQSListController.this.getMainHandler().removeCallbacksAndMessages(null);
                Handler mainHandler = CompactQSListController.this.getMainHandler();
                Message messageObtain = Message.obtain();
                messageObtain.what = 1;
                mainHandler.sendMessage(messageObtain);
            }
        };
        registerHasSetTile();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        this.sysUIContext.registerReceiver(getBroadcastReceiver(), intentFilter, null, this.bgHandler, 2);
        String string = getResources().getString(Build.IS_INTERNATIONAL_BUILD ? R.string.qs_flip_tiles_stock_global : R.string.qs_flip_tiles_stock);
        n.f(string, "getString(...)");
        this.flipTileStockPoolList = u.m0(o.T(string, new String[]{aa.f3429b}, false, 0, 6, null));
        String string2 = getResources().getString(R.string.qs_flip_pkg_tiles);
        n.f(string2, "getString(...)");
        this.flipPkgTileStockPool = u.m0(o.T(string2, new String[]{aa.f3429b}, false, 0, 6, null));
        String string3 = getResources().getString(R.string.qs_flip_pkg_tiles_pkg_name);
        n.f(string3, "getString(...)");
        this.flipPkgNameStockPool = u.m0(o.T(string3, new String[]{aa.f3429b}, false, 0, 6, null));
        distributeTiles$default(this, false, 1, null);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        Iterator<T> it = this.qsRecords.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).removeCallback();
        }
        this.qsRecords.clear();
        Iterator<T> it2 = this.prepareShowQSList.iterator();
        while (it2.hasNext()) {
            ((QSTile) it2.next()).destroy();
        }
        this.prepareShowQSList.clear();
        this.supportTileList.clear();
        ContentResolver contentResolver = this.sysUIContext.getContentResolver();
        ContentObserver contentObserver = this.flipQsObserver;
        if (contentObserver == null) {
            n.w("flipQsObserver");
            contentObserver = null;
        }
        contentResolver.unregisterContentObserver(contentObserver);
        this.sysUIContext.unregisterReceiver(getBroadcastReceiver());
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStart() {
        Iterator<T> it = this.qsRecords.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).startListening();
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        Iterator<T> it = this.qsRecords.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).setListening(false);
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onUserSwitched(int i2) {
        super.onUserSwitched(i2);
        Log.d(TAG, "onUserSwitched");
        ContentResolver contentResolver = this.sysUIContext.getContentResolver();
        ContentObserver contentObserver = this.flipQsObserver;
        if (contentObserver == null) {
            n.w("flipQsObserver");
            contentObserver = null;
        }
        contentResolver.unregisterContentObserver(contentObserver);
        registerHasSetTile();
        distributeTiles(true);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public ArrayList<QSRecord> getListItems() {
        return this.qsRecords;
    }
}
