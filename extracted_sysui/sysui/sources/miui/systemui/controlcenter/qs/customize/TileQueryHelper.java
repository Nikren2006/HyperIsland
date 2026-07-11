package miui.systemui.controlcenter.qs.customize;

import H0.d;
import H0.e;
import I0.AbstractC0181i;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Button;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.settings.UserTracker;
import com.miui.maml.folme.AnimatedPropertyType;
import com.xiaomi.onetrack.util.aa;
import f1.o;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.flipQs.utils.QSFlipUtils;
import miui.systemui.controlcenter.qs.DrawableIcon;
import miui.systemui.controlcenter.qs.QSController;
import miui.systemui.controlcenter.qs.TileSpecsKt;
import miui.systemui.controlcenter.utils.MiuiQSHostCompat;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.util.SystemUIResourcesHelper;
import miui.systemui.util.concurrency.DelayableExecutor;
import miuix.os.Build;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class TileQueryHelper {
    private static final int CALLBACK_TYPE_QS_CUSTOMIZER = 1005;
    public static final String CUSTOM_TILE_PREFIX = "custom(";
    public static final Companion Companion = new Companion(null);
    public static final String MEMORY_TILE_SPEC = "dbg:mem";
    private static final String TAG = "PluginTileQueryHelper";
    private final DelayableExecutor bgExecutor;
    private final Context context;
    private final MiuiQSHost host;
    private TileStateListener listener;
    private final ArrayList<QSTile> liveTiles;
    private final ArrayMap<String, TileInfo> pendingAdding;
    private final E0.a qsController;
    private final String staticStockTiles;
    private final Context sysUIContext;
    private final SystemUIResourcesHelper sysUIResHelper;
    private final d systemTilePackageList$delegate;
    private final UserTracker userTracker;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean filterNearby(String tileSpec) {
            n.g(tileSpec, "tileSpec");
            return n.c(tileSpec, "custom(com.google.android.gms/.nearby.sharing.SharingTileService)") && !Build.IS_INTERNATIONAL_BUILD;
        }

        public final boolean filterScreenRecord(String tileSpec, int i2) {
            n.g(tileSpec, "tileSpec");
            return i2 != 0 && TextUtils.equals(tileSpec, QSFlipUtils.TILE_TYPE_SCREEN_RECORD);
        }

        public final boolean isServiceRestricted(ServiceInfo info) {
            n.g(info, "info");
            Bundle bundle = info.metaData;
            boolean z2 = false;
            if (bundle == null) {
                return false;
            }
            String string = bundle.getString("com.android.device.restriction");
            if (string == null) {
                string = "";
            }
            String str = string;
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            String[] strArr = (String[]) o.T(str, new String[]{";"}, false, 0, 6, null).toArray(new String[0]);
            int length = strArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                String str2 = strArr[i2];
                int length2 = str2.length() - 1;
                int i3 = 0;
                boolean z3 = false;
                while (i3 <= length2) {
                    boolean z4 = n.i(str2.charAt(!z3 ? i3 : length2), 32) <= 0;
                    if (z3) {
                        if (!z4) {
                            break;
                        }
                        length2--;
                    } else if (z4) {
                        i3++;
                    } else {
                        z3 = true;
                    }
                }
                if (n.c(str2.subSequence(i3, length2 + 1).toString(), android.os.Build.DEVICE)) {
                    z2 = true;
                    break;
                }
                i2++;
            }
            return !z2;
        }

        public final String toSpec(ComponentName name) {
            n.g(name, "name");
            return TileQueryHelper.CUSTOM_TILE_PREFIX + name.flattenToShortString() + ")";
        }

        private Companion() {
        }
    }

    public interface TileStateListener {
        default void onTileChanged(TileInfo tile) {
            n.g(tile, "tile");
        }

        void onTilesChanged(List<TileInfo> list);
    }

    public TileQueryHelper(@Qualifiers.ControlCenter Context context, SystemUIResourcesHelper sysUIResHelper, @SystemUI Context context2, @Background DelayableExecutor bgExecutor, E0.a qsController, MiuiQSHost host, UserTracker userTracker) {
        n.g(context, "context");
        n.g(sysUIResHelper, "sysUIResHelper");
        n.g(bgExecutor, "bgExecutor");
        n.g(qsController, "qsController");
        n.g(host, "host");
        n.g(userTracker, "userTracker");
        this.context = context;
        this.sysUIResHelper = sysUIResHelper;
        this.sysUIContext = context2;
        this.bgExecutor = bgExecutor;
        this.qsController = qsController;
        this.host = host;
        this.userTracker = userTracker;
        this.systemTilePackageList$delegate = e.b(new TileQueryHelper$systemTilePackageList$2(this));
        String string = sysUIResHelper.getString("miui_quick_settings_tiles_stock");
        this.staticStockTiles = string == null ? context.getResources().getString(R.string.quick_settings_tiles_stock) : string;
        this.pendingAdding = new ArrayMap<>();
        this.liveTiles = new ArrayList<>();
    }

    private final void addPackageTiles(MiuiQSHost miuiQSHost) {
        Drawable drawableLoadIcon;
        Collection tiles = miuiQSHost.getTiles();
        PackageManager packageManager = this.context.getPackageManager();
        List<ResolveInfo> listQueryIntentServicesAsUser = packageManager.queryIntentServicesAsUser(new Intent("android.service.quicksettings.action.QS_TILE"), 128, ActivityManager.getCurrentUser());
        n.f(listQueryIntentServicesAsUser, "queryIntentServicesAsUser(...)");
        for (ResolveInfo resolveInfo : listQueryIntentServicesAsUser) {
            String str = resolveInfo.serviceInfo.packageName;
            ComponentName componentName = new ComponentName(str, resolveInfo.serviceInfo.name);
            String tilesStock = getTilesStock();
            n.f(tilesStock, "<get-tilesStock>(...)");
            String strFlattenToString = componentName.flattenToString();
            n.f(strFlattenToString, "flattenToString(...)");
            Object obj = null;
            if (!o.v(tilesStock, strFlattenToString, false, 2, null)) {
                Companion companion = Companion;
                ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                n.f(serviceInfo, "serviceInfo");
                if (companion.isServiceRestricted(serviceInfo)) {
                    Log.d(TAG, "Custom tile is restricted: " + str);
                } else {
                    CharSequence charSequenceLoadLabel = resolveInfo.serviceInfo.applicationInfo.loadLabel(packageManager);
                    boolean zIsSystemTile = isSystemTile(resolveInfo.serviceInfo);
                    String spec = companion.toSpec(componentName);
                    if (!companion.filterNearby(spec) && !companion.filterScreenRecord(spec, this.userTracker.getUserId())) {
                        n.d(tiles);
                        Iterator it = tiles.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Object next = it.next();
                            if (n.c(((QSTile) next).getTileSpec(), spec)) {
                                obj = next;
                                break;
                            }
                        }
                        QSTile qSTile = (QSTile) obj;
                        if (qSTile != null) {
                            TileInfo tileInfoCreatePackageTileInfo$default = createPackageTileInfo$default(this, qSTile.getState(), null, null, null, charSequenceLoadLabel, zIsSystemTile, 14, null);
                            if (tileInfoCreatePackageTileInfo$default != null) {
                                this.pendingAdding.put(spec, tileInfoCreatePackageTileInfo$default);
                            }
                        } else {
                            ServiceInfo serviceInfo2 = resolveInfo.serviceInfo;
                            if (serviceInfo2.icon != 0 || serviceInfo2.applicationInfo.icon != 0) {
                                if (n.c("android.permission.BIND_QUICK_SETTINGS_TILE", serviceInfo2.permission)) {
                                    ServiceInfo serviceInfo3 = resolveInfo.serviceInfo;
                                    int i2 = serviceInfo3.icon;
                                    if (i2 == 0) {
                                        i2 = serviceInfo3.applicationInfo.icon;
                                    }
                                    Icon iconCreateWithResource = Icon.createWithResource(str, i2);
                                    if (iconCreateWithResource == null) {
                                        drawableLoadIcon = resolveInfo.serviceInfo.loadIcon(packageManager);
                                    } else {
                                        try {
                                            Context context = this.sysUIContext;
                                            n.d(context);
                                            drawableLoadIcon = iconCreateWithResource.loadDrawable(context);
                                        } catch (Exception unused) {
                                            Log.w(TAG, "Invalid icon");
                                            drawableLoadIcon = resolveInfo.serviceInfo.loadIcon(packageManager);
                                        }
                                    }
                                    Drawable drawable = drawableLoadIcon;
                                    if (drawable != null) {
                                        drawable.mutate();
                                        drawable.setTint(this.context.getColor(android.R.color.white));
                                        TileInfo tileInfoCreatePackageTileInfo$default2 = createPackageTileInfo$default(this, null, spec, resolveInfo.serviceInfo.loadLabel(packageManager).toString(), drawable, charSequenceLoadLabel, zIsSystemTile, 1, null);
                                        if (tileInfoCreatePackageTileInfo$default2 != null) {
                                            this.pendingAdding.put(spec, tileInfoCreatePackageTileInfo$default2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private final void addStockTiles(MiuiQSHost miuiQSHost) {
        QSTile qSTileCreateTile;
        String string = Settings.Secure.getString(this.context.getContentResolver(), "sysui_qs_tiles");
        if (string == null) {
            string = "";
        }
        String str = string;
        List listT = str.length() > 0 ? o.T(str, new String[]{aa.f3429b}, false, 0, 6, null) : new ArrayList();
        ArrayList<String> arrayList = new ArrayList();
        if (!listT.isEmpty()) {
            arrayList.addAll(listT);
        }
        String tilesStock = getTilesStock();
        n.f(tilesStock, "<get-tilesStock>(...)");
        for (String str2 : o.T(tilesStock, new String[]{aa.f3429b}, false, 0, 6, null)) {
            if (!listT.contains(str2)) {
                arrayList.add(str2);
            }
        }
        Iterator<T> it = ((QSController) this.qsController.get()).getQsListExcludeTileSpecs().iterator();
        while (it.hasNext()) {
            arrayList.remove((String) it.next());
        }
        arrayList.remove(TileSpecsKt.TILE_SPEC_EDIT);
        ArrayList arrayList2 = new ArrayList();
        for (String str3 : arrayList) {
            if (!f1.n.s(str3, CUSTOM_TILE_PREFIX, false, 2, null) && (qSTileCreateTile = miuiQSHost.createTile(str3)) != null) {
                n.d(qSTileCreateTile);
                if (qSTileCreateTile.isAvailable()) {
                    qSTileCreateTile.setTileSpec(str3);
                    this.pendingAdding.put(str3, createStockTileInfo(qSTileCreateTile));
                    arrayList2.add(qSTileCreateTile);
                } else {
                    qSTileCreateTile.destroy();
                }
            }
        }
        this.liveTiles.addAll(arrayList2);
    }

    private final TileInfo createPackageTileInfo(QSTile.State state, String str, CharSequence charSequence, Drawable drawable, CharSequence charSequence2, boolean z2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        QSTile.State stateCopy = state != null ? state.copy() : null;
        if (stateCopy == null) {
            stateCopy = new QSTile.State();
        }
        stateCopy.state = 1;
        stateCopy.dualTarget = false;
        stateCopy.label = charSequence;
        stateCopy.contentDescription = charSequence;
        stateCopy.expandedAccessibilityClassName = Button.class.getName();
        if (drawable != null) {
            stateCopy.icon = new DrawableIcon(drawable);
        }
        if (z2 || TextUtils.equals(charSequence, charSequence2)) {
            charSequence2 = null;
        }
        stateCopy.secondaryLabel = charSequence2;
        return new TileInfo(str, stateCopy, z2);
    }

    public static /* synthetic */ TileInfo createPackageTileInfo$default(TileQueryHelper tileQueryHelper, QSTile.State state, String str, CharSequence charSequence, Drawable drawable, CharSequence charSequence2, boolean z2, int i2, Object obj) {
        String str2;
        CharSequence charSequence3;
        QSTile.State state2 = (i2 & 1) != 0 ? null : state;
        if ((i2 & 2) != 0) {
            String str3 = state2 != null ? state2.spec : null;
            if (str3 == null) {
                str3 = "";
            }
            str2 = str3;
        } else {
            str2 = str;
        }
        if ((i2 & 4) != 0) {
            CharSequence charSequence4 = state2 != null ? state2.label : null;
            if (charSequence4 == null) {
                charSequence4 = "";
            }
            charSequence3 = charSequence4;
        } else {
            charSequence3 = charSequence;
        }
        return tileQueryHelper.createPackageTileInfo(state2, str2, charSequence3, (i2 & 8) != 0 ? null : drawable, charSequence2, z2);
    }

    private final TileInfo createStockTileInfo(QSTile qSTile) {
        QSTile.State stateCopy = qSTile.getState().copy();
        CharSequence tileLabel = qSTile.getTileLabel();
        if (tileLabel == null) {
            tileLabel = stateCopy.label;
        }
        stateCopy.label = tileLabel;
        stateCopy.dualTarget = false;
        stateCopy.contentDescription = tileLabel;
        stateCopy.expandedAccessibilityClassName = Button.class.getName();
        stateCopy.secondaryLabel = null;
        String tileSpec = qSTile.getTileSpec();
        n.f(tileSpec, "getTileSpec(...)");
        n.d(stateCopy);
        return new TileInfo(tileSpec, stateCopy, true);
    }

    private final String[] getSystemTilePackageList() {
        return (String[]) this.systemTilePackageList$delegate.getValue();
    }

    private final String getTilesStock() {
        String stockTilesCompat = MiuiQSHostCompat.INSTANCE.getStockTilesCompat(this.host);
        return stockTilesCompat == null ? this.staticStockTiles : stockTilesCompat;
    }

    private final boolean isSystemTile(ServiceInfo serviceInfo) {
        if (serviceInfo == null) {
            return false;
        }
        String[] systemTilePackageList = getSystemTilePackageList();
        n.f(systemTilePackageList, "<get-systemTilePackageList>(...)");
        return AbstractC0181i.w(systemTilePackageList, serviceInfo.packageName) || serviceInfo.applicationInfo.isSignedWithPlatformKey();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void notifyCustomizeFinished$lambda$2(TileQueryHelper this$0) {
        n.g(this$0, "this$0");
        this$0.stopObserveStockTiles();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyTileChanged(QSTile qSTile) {
        TileStateListener tileStateListener = this.listener;
        if (tileStateListener != null) {
            tileStateListener.onTileChanged(createStockTileInfo(qSTile));
        }
    }

    private final void notifyTilesChanged() {
        TileStateListener tileStateListener = this.listener;
        if (tileStateListener != null) {
            tileStateListener.onTilesChanged(new ArrayList(this.pendingAdding.values()));
        }
        this.pendingAdding.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void queryTiles$lambda$1(TileQueryHelper this$0, MiuiQSHost host) {
        n.g(this$0, "this$0");
        n.g(host, "$host");
        this$0.stopObserveStockTiles();
        this$0.addStockTiles(host);
        this$0.addPackageTiles(host);
        this$0.notifyTilesChanged();
        this$0.startObserveStockTiles();
    }

    private final void startObserveStockTiles() {
        for (final QSTile qSTile : this.liveTiles) {
            qSTile.addCallback(new QSTile.Callback() { // from class: miui.systemui.controlcenter.qs.customize.TileQueryHelper$startObserveStockTiles$1$1
                public int getCallbackType() {
                    return AnimatedPropertyType.STROKE_COLOR;
                }

                public void onAnnouncementRequested(CharSequence charSequence) {
                }

                public void onScanStateChanged(boolean z2) {
                }

                public void onShowDetail(boolean z2) {
                }

                public void onStateChanged(QSTile.State state) {
                    this.this$0.notifyTileChanged(qSTile);
                }

                public void onToggleStateChanged(boolean z2) {
                }
            });
            qSTile.refreshState();
        }
    }

    private final void stopObserveStockTiles() {
        for (QSTile qSTile : this.liveTiles) {
            qSTile.removeCallbacksByType(1005);
            qSTile.destroy();
        }
        this.liveTiles.clear();
    }

    public final TileStateListener getListener() {
        return this.listener;
    }

    public final void notifyCustomizeFinished() {
        this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.controlcenter.qs.customize.b
            @Override // java.lang.Runnable
            public final void run() {
                TileQueryHelper.notifyCustomizeFinished$lambda$2(this.f5482a);
            }
        });
    }

    public final void queryTiles(final MiuiQSHost host) {
        n.g(host, "host");
        this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.controlcenter.qs.customize.a
            @Override // java.lang.Runnable
            public final void run() {
                TileQueryHelper.queryTiles$lambda$1(this.f5480a, host);
            }
        });
    }

    public final void saveSpecs(MiuiQSHost host, List<String> previousSpecs, List<String> specs) {
        n.g(host, "host");
        n.g(previousSpecs, "previousSpecs");
        n.g(specs, "specs");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(((QSController) this.qsController.get()).getQsListStartExcludeTileSpecs());
        arrayList.addAll(specs);
        arrayList.addAll(((QSController) this.qsController.get()).getQsListEndExcludeTileSpecs());
        host.changeTiles(previousSpecs, arrayList);
    }

    public final void setListener(TileStateListener tileStateListener) {
        this.listener = tileStateListener;
    }

    public static final class TileInfo {
        private final boolean isSystem;
        private final String spec;
        private final QSTile.State state;

        public TileInfo(String spec, QSTile.State state, boolean z2) {
            n.g(spec, "spec");
            n.g(state, "state");
            this.spec = spec;
            this.state = state;
            this.isSystem = z2;
        }

        public static /* synthetic */ TileInfo copy$default(TileInfo tileInfo, String str, QSTile.State state, boolean z2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = tileInfo.spec;
            }
            if ((i2 & 2) != 0) {
                state = tileInfo.state;
            }
            if ((i2 & 4) != 0) {
                z2 = tileInfo.isSystem;
            }
            return tileInfo.copy(str, state, z2);
        }

        public final String component1() {
            return this.spec;
        }

        public final QSTile.State component2() {
            return this.state;
        }

        public final boolean component3() {
            return this.isSystem;
        }

        public final TileInfo copy(String spec, QSTile.State state, boolean z2) {
            n.g(spec, "spec");
            n.g(state, "state");
            return new TileInfo(spec, state, z2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TileInfo)) {
                return false;
            }
            TileInfo tileInfo = (TileInfo) obj;
            return n.c(this.spec, tileInfo.spec) && n.c(this.state, tileInfo.state) && this.isSystem == tileInfo.isSystem;
        }

        public final String getSpec() {
            return this.spec;
        }

        public final QSTile.State getState() {
            return this.state;
        }

        public int hashCode() {
            return (((this.spec.hashCode() * 31) + this.state.hashCode()) * 31) + Boolean.hashCode(this.isSystem);
        }

        public final boolean isSystem() {
            return this.isSystem;
        }

        public String toString() {
            return "TileInfo(spec=" + this.spec + ", state=" + this.state + ", isSystem=" + this.isSystem + ")";
        }

        public /* synthetic */ TileInfo(String str, QSTile.State state, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, state, (i2 & 4) != 0 ? false : z2);
        }
    }
}
