package miui.systemui.controlcenter.flipQs;

import I0.AbstractC0181i;
import I0.u;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.xiaomi.onetrack.util.aa;
import f1.o;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.events.QSFlipEventTracker;
import miui.systemui.controlcenter.flipQs.adapter.QSFlipAdapter;
import miui.systemui.controlcenter.flipQs.listener.QSFlipItemTouchCallback;
import miui.systemui.controlcenter.flipQs.utils.QSFlipUtils;
import miui.systemui.controlcenter.flipQs.wrap.QSFlipTileWrap;
import miuix.appcompat.app.AppCompatActivity;
import miuix.appcompat.app.LayoutUiModeHelper;
import miuix.autodensity.AutoDensityConfig;
import miuix.autodensity.IDensity;
import miuix.os.Build;
import miuix.recyclerview.widget.RecyclerView;
import systemui.plugin.eventtracking.EventTracker;
import systemui.plugin.eventtracking.trackers.BaseEventTracker;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class QSFlipQuickSettingActivity extends AppCompatActivity implements IDensity {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "QSFlipQuickSettingActivity";
    private List<String> addedTilesList;
    private List<String> defaultTiles;
    private QSFlipAdapter mAdapter;
    private List<QSFlipTileWrap> mAppInfoList = new ArrayList();
    private QSFlipItemTouchCallback qSFlipItemTouchCallback;
    private RecyclerView recyclerView;
    private List<String> staticStockTilesPool;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final void addTiles() {
        QSFlipAdapter qSFlipAdapter;
        String string = Settings.Secure.getString(getContentResolver(), QSFlipUtils.SYSTEM_UI_FLIP_QS_TILES);
        String str = string == null ? "" : string;
        this.addedTilesList = str.length() > 0 ? u.m0(o.T(str, new String[]{aa.f3429b}, false, 0, 6, null)) : new ArrayList<>();
        String string2 = Settings.Secure.getString(getContentResolver(), QSFlipUtils.SYSTEM_UI_SUPPORT_FLIP_QS_TILES);
        String str2 = string2 == null ? "" : string2;
        List listM0 = str2.length() > 0 ? u.m0(o.T(str2, new String[]{aa.f3429b}, false, 0, 6, null)) : new ArrayList();
        List<String> list = this.addedTilesList;
        if (list == null) {
            n.w("addedTilesList");
            list = null;
        }
        if (list.isEmpty()) {
            List<String> list2 = this.addedTilesList;
            if (list2 == null) {
                n.w("addedTilesList");
                list2 = null;
            }
            List<String> list3 = this.defaultTiles;
            if (list3 == null) {
                n.w("defaultTiles");
                list3 = null;
            }
            list2.addAll(list3);
        } else {
            List<String> list4 = this.addedTilesList;
            if (list4 == null) {
                n.w("addedTilesList");
                list4 = null;
            }
            Iterator<String> it = list4.iterator();
            while (it.hasNext()) {
                String next = it.next();
                List<String> list5 = this.staticStockTilesPool;
                if (list5 == null) {
                    n.w("staticStockTilesPool");
                    list5 = null;
                }
                if (!list5.contains(next) || !listM0.contains(next)) {
                    it.remove();
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        List<String> list6 = this.staticStockTilesPool;
        if (list6 == null) {
            n.w("staticStockTilesPool");
            list6 = null;
        }
        for (String str3 : list6) {
            List<String> list7 = this.addedTilesList;
            if (list7 == null) {
                n.w("addedTilesList");
                list7 = null;
            }
            if (!list7.contains(str3) && listM0.contains(str3)) {
                arrayList.add(str3);
            }
        }
        QSFlipUtils qSFlipUtils = QSFlipUtils.INSTANCE;
        List<String> list8 = this.addedTilesList;
        if (list8 == null) {
            n.w("addedTilesList");
            list8 = null;
        }
        List<QSFlipTileWrap> listCreateTileWrap = qSFlipUtils.createTileWrap(list8, true);
        List<QSFlipTileWrap> listCreateTileWrap2 = qSFlipUtils.createTileWrap(arrayList, false);
        List<QSFlipTileWrap> list9 = this.mAppInfoList;
        QSFlipAdapter qSFlipAdapter2 = this.mAdapter;
        if (qSFlipAdapter2 == null) {
            n.w("mAdapter");
            qSFlipAdapter = null;
        } else {
            qSFlipAdapter = qSFlipAdapter2;
        }
        qSFlipUtils.onShowTilesList(listCreateTileWrap, listCreateTileWrap2, list9, qSFlipAdapter, true);
    }

    private final void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        View viewRequireViewById = requireViewById(R.id.flip_qs_recycler_view);
        n.f(viewRequireViewById, "requireViewById(...)");
        RecyclerView recyclerView = (RecyclerView) viewRequireViewById;
        this.recyclerView = recyclerView;
        RecyclerView recyclerView2 = null;
        if (recyclerView == null) {
            n.w("recyclerView");
            recyclerView = null;
        }
        recyclerView.setLayoutManager(gridLayoutManager);
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 == null) {
            n.w("recyclerView");
            recyclerView3 = null;
        }
        this.mAdapter = new QSFlipAdapter(recyclerView3);
        setSpanSizeLookup(gridLayoutManager);
        RecyclerView recyclerView4 = this.recyclerView;
        if (recyclerView4 == null) {
            n.w("recyclerView");
            recyclerView4 = null;
        }
        QSFlipAdapter qSFlipAdapter = this.mAdapter;
        if (qSFlipAdapter == null) {
            n.w("mAdapter");
            qSFlipAdapter = null;
        }
        recyclerView4.setAdapter(qSFlipAdapter);
        QSFlipItemTouchCallback qSFlipItemTouchCallback = new QSFlipItemTouchCallback();
        this.qSFlipItemTouchCallback = qSFlipItemTouchCallback;
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(qSFlipItemTouchCallback);
        RecyclerView recyclerView5 = this.recyclerView;
        if (recyclerView5 == null) {
            n.w("recyclerView");
        } else {
            recyclerView2 = recyclerView5;
        }
        itemTouchHelper.attachToRecyclerView(recyclerView2);
    }

    private final void setSpanSizeLookup(GridLayoutManager gridLayoutManager) {
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: miui.systemui.controlcenter.flipQs.QSFlipQuickSettingActivity.setSpanSizeLookup.1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i2) {
                QSFlipAdapter qSFlipAdapter = QSFlipQuickSettingActivity.this.mAdapter;
                if (qSFlipAdapter == null) {
                    n.w("mAdapter");
                    qSFlipAdapter = null;
                }
                return qSFlipAdapter.getItemViewType(i2) == 1 ? 1 : 3;
            }
        });
    }

    private final void syncData() {
        QSFlipAdapter qSFlipAdapter = this.mAdapter;
        List<String> list = null;
        if (qSFlipAdapter == null) {
            n.w("mAdapter");
            qSFlipAdapter = null;
        }
        List<QSFlipTileWrap> addedItems = qSFlipAdapter.getAddedItems();
        ArrayList arrayList = new ArrayList(I0.n.o(addedItems, 10));
        Iterator<T> it = addedItems.iterator();
        while (it.hasNext()) {
            arrayList.add(((QSFlipTileWrap) it.next()).getSpec());
        }
        String strJoin = TextUtils.join(aa.f3429b, arrayList);
        List<String> list2 = this.addedTilesList;
        if (list2 == null) {
            n.w("addedTilesList");
        } else {
            list = list2;
        }
        String strJoin2 = TextUtils.join(aa.f3429b, list);
        boolean zC = n.c(strJoin, strJoin2);
        boolean z2 = !zC;
        Log.d(TAG, "syncData: isChanged: " + z2);
        if (!zC && arrayList.size() >= 6) {
            Settings.Secure.putStringForUser(getContentResolver(), QSFlipUtils.SYSTEM_UI_FLIP_QS_TILES, strJoin, null, false, -2, true);
        }
        QSFlipEventTracker.Companion companion = QSFlipEventTracker.Companion;
        n.d(strJoin2);
        n.d(strJoin);
        companion.trackQsFlipQuitEvent(z2, strJoin2, strJoin);
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AutoDensityConfig.init(getApplication());
        LayoutUiModeHelper.autoSetLayoutUiMode(this);
        setContentView(R.layout.qs_flip_quick_setting);
        String string = getResources().getString(Build.IS_INTERNATIONAL_BUILD ? R.string.qs_flip_tiles_stock_global : R.string.qs_flip_tiles_stock);
        n.f(string, "getString(...)");
        this.staticStockTilesPool = u.m0(o.T(string, new String[]{aa.f3429b}, false, 0, 6, null));
        String[] stringArray = getResources().getStringArray(R.array.compact_tiles);
        n.f(stringArray, "getStringArray(...)");
        this.defaultTiles = AbstractC0181i.S(stringArray);
        initView();
        QSFlipUtils qSFlipUtils = QSFlipUtils.INSTANCE;
        Context applicationContext = getApplicationContext();
        n.f(applicationContext, "getApplicationContext(...)");
        qSFlipUtils.init(applicationContext);
        addTiles();
        BaseEventTracker.Companion companion = BaseEventTracker.Companion.get();
        Context applicationContext2 = getApplicationContext();
        n.f(applicationContext2, "getApplicationContext(...)");
        companion.setEventTracker(new EventTracker(applicationContext2));
        QSFlipEventTracker.Companion.trackQsFlipEnterEvent();
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        QSFlipUtils.INSTANCE.onDestroy();
        BaseEventTracker.Companion.get().destroy();
    }

    @Override // miuix.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        syncData();
    }

    @Override // miuix.autodensity.IDensity
    public boolean shouldAdaptAutoDensity() {
        return true;
    }
}
