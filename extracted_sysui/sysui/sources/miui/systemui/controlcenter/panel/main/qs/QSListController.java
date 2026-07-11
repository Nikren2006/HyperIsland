package miui.systemui.controlcenter.panel.main.qs;

import H0.s;
import I0.u;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.StringRes;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import com.android.systemui.plugins.qs.QSTile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.CustomizeDividerBinding;
import miui.systemui.controlcenter.databinding.CustomizeDividerLabelBinding;
import miui.systemui.controlcenter.panel.main.MainPanelContent;
import miui.systemui.controlcenter.panel.main.MainPanelContentDistributor;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelModeController;
import miui.systemui.controlcenter.panel.main.qs.QSItemViewHolder;
import miui.systemui.controlcenter.panel.main.qs.QSRecord;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem;
import miui.systemui.controlcenter.qs.QSController;
import miui.systemui.controlcenter.qs.TileSpecsKt;
import miui.systemui.controlcenter.qs.customize.TileQueryHelper;
import miui.systemui.controlcenter.qs.tileview.QSTileItemIconView;
import miui.systemui.controlcenter.qs.tileview.QSTileItemView;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.concurrency.DelayableExecutor;
import miui.systemui.widget.FrameLayout;
import miuix.animation.base.AnimConfig;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class QSListController extends ControlCenterViewController<ControlCenterWindowViewImpl> implements MainPanelContent, MiuiQSHost.Callback, TileQueryHelper.TileStateListener, MainPanelModeController.OnModeChangedListener {
    private static final long CHANGE_TILE_INTERVAL = 200;
    public static final Companion Companion = new Companion(null);
    private static final int MIN_TILE_COUNT = 8;
    private static final String TAG = "QSListController";
    private ArrayList<QSRecord> addedTiles;
    private final Handler bgHandler;
    private final ArrayList<QSRecord> copiedTiles;
    private final Runnable distributeTilesRunnable;
    private final E0.a distributor;
    private final EditModeDividerItem emptyLineDivider;
    private final QSItemViewHolder.Factory holderFactory;
    private final MiuiQSHost host;
    private long lastChangeTileTime;
    private final EditModeDividerItem lineDivider;
    private final E0.a mainPanelController;
    private final EditModeDividerTextItem packageTextDivider;
    private ArrayList<QSRecord> packageTiles;
    private MainPanelController.Mode pendingMode;
    private final int priority;
    private final QSCardsController qsCardsController;
    private final E0.a qsController;
    private final QSRecord.Factory recordFactory;
    private final Context sysUIContext;
    private final EditModeDividerTextItem systemTextDivider;
    private ArrayList<QSRecord> systemTiles;
    private TextMode textMode;
    private boolean tileCustomized;
    private final TileQueryHelper tileQueryHelper;
    private final DelayableExecutor uiExecutor;
    private final Handler uiHandler;
    private boolean voWifi1Available;
    private boolean voWifi2Available;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class EditModeDividerHolder extends MainPanelItemViewHolder {
        private final CustomizeDividerBinding binding;
        private boolean hasLine;

        /* JADX WARN: Illegal instructions before constructor call */
        public EditModeDividerHolder(CustomizeDividerBinding binding) {
            n.g(binding, "binding");
            FrameLayout root = binding.getRoot();
            n.f(root, "getRoot(...)");
            super(root);
            this.binding = binding;
            this.hasLine = true;
        }

        private final void updateContainerHeight() {
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            View itemView = this.itemView;
            n.f(itemView, "itemView");
            CommonUtils.setLayoutHeight$default(commonUtils, itemView, this.itemView.getResources().getDimensionPixelSize(this.hasLine ? R.dimen.edit_mode_divider_expanded_height : R.dimen.edit_mode_divider_collapsed_height), false, 2, null);
        }

        private final void updateSize() {
            int dimensionPixelSize = this.itemView.getResources().getDimensionPixelSize(R.dimen.edit_mode_divider_line_margin_bottom);
            int dimensionPixelSize2 = this.itemView.getResources().getDimensionPixelSize(R.dimen.edit_mode_divider_margin_horizontal);
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            View divider = this.binding.divider;
            n.f(divider, "divider");
            CommonUtils.setMargins$default(commonUtils, divider, dimensionPixelSize2, 0, dimensionPixelSize2, dimensionPixelSize, false, 18, null);
            View divider2 = this.binding.divider;
            n.f(divider2, "divider");
            CommonUtils.setLayoutHeight$default(commonUtils, divider2, this.itemView.getResources().getDimensionPixelSize(R.dimen.edit_mode_divider_line_height), false, 2, null);
        }

        public final CustomizeDividerBinding getBinding() {
            return this.binding;
        }

        public final boolean getHasLine() {
            return this.hasLine;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void onConfigurationChanged(int i2) {
            ConfigUtils configUtils = ConfigUtils.INSTANCE;
            if (configUtils.dimensionsChanged(i2)) {
                updateContainerHeight();
                updateSize();
            }
            if (configUtils.colorsChanged(i2)) {
                this.binding.divider.setBackgroundColor(getContext().getColor(R.color.customize_divider_color));
            }
        }

        public final void setHasLine(boolean z2) {
            if (this.hasLine == z2) {
                return;
            }
            this.hasLine = z2;
            updateContainerHeight();
            this.binding.divider.setVisibility(z2 ? 0 : 8);
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
        public void update(boolean z2, float f2) {
            getAnimatorConfigHelper().updateTranY(this, z2, f2);
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
        public AnimConfig updateAnimConfig(boolean z2, boolean z3, int i2, int i3) {
            return getAnimatorConfigHelper().updateAnimEase(this, z2, z3, i2, i3);
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void updateBlendBlur() {
        }
    }

    public static final class EditModeDividerItem implements MainPanelListItem {
        public static final Companion Companion = new Companion(null);
        public static final int ITEM_TYPE_DIVIDER = 3484337;
        private final boolean hasLine;
        private MainPanelItemViewHolder holder;
        private final int type = ITEM_TYPE_DIVIDER;
        private final int spanSize = 4;

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }

        public EditModeDividerItem(boolean z2) {
            this.hasLine = z2;
        }

        public final boolean getHasLine() {
            return this.hasLine;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public MainPanelItemViewHolder getHolder() {
            return this.holder;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public int getSpanSize() {
            return this.spanSize;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public int getType() {
            return this.type;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public void onBindViewHolder() {
            MainPanelItemViewHolder holder = getHolder();
            EditModeDividerHolder editModeDividerHolder = holder instanceof EditModeDividerHolder ? (EditModeDividerHolder) holder : null;
            if (editModeDividerHolder == null) {
                return;
            }
            editModeDividerHolder.setHasLine(this.hasLine);
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public void setHolder(MainPanelItemViewHolder mainPanelItemViewHolder) {
            this.holder = mainPanelItemViewHolder;
        }
    }

    public static final class EditModeDividerTextHolder extends MainPanelItemViewHolder {
        private final CustomizeDividerLabelBinding binding;
        private int textRes;

        /* JADX WARN: Illegal instructions before constructor call */
        public EditModeDividerTextHolder(CustomizeDividerLabelBinding binding) {
            n.g(binding, "binding");
            FrameLayout root = binding.getRoot();
            n.f(root, "getRoot(...)");
            super(root);
            this.binding = binding;
        }

        private final void updateText() {
            this.binding.label.setText(this.textRes);
        }

        public final CustomizeDividerLabelBinding getBinding() {
            return this.binding;
        }

        public final int getTextRes() {
            return this.textRes;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void onConfigurationChanged(int i2) {
            ConfigUtils configUtils = ConfigUtils.INSTANCE;
            if (configUtils.dimensionsChanged(i2)) {
                CommonUtils commonUtils = CommonUtils.INSTANCE;
                View itemView = this.itemView;
                n.f(itemView, "itemView");
                CommonUtils.setLayoutHeight$default(commonUtils, itemView, this.itemView.getResources().getDimensionPixelSize(R.dimen.edit_mode_divider_text_container_height), false, 2, null);
                int dimensionPixelSize = this.itemView.getResources().getDimensionPixelSize(R.dimen.edit_mode_divider_margin_horizontal);
                TextView label = this.binding.label;
                n.f(label, "label");
                CommonUtils.setMargins$default(commonUtils, label, dimensionPixelSize, 0, dimensionPixelSize, 0, false, 26, null);
            }
            if (configUtils.textAppearanceChanged(i2)) {
                this.binding.label.setTextAppearance(R.style.TextAppearance_Customize_DividerLabel);
            }
            if (configUtils.textsChanged(i2)) {
                updateText();
            }
        }

        public final void setTextRes(int i2) {
            if (this.textRes == i2) {
                return;
            }
            this.textRes = i2;
            updateText();
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
        public void update(boolean z2, float f2) {
            getAnimatorConfigHelper().updateTranY(this, z2, f2);
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.ControlCenterViewHolder
        public AnimConfig updateAnimConfig(boolean z2, boolean z3, int i2, int i3) {
            return getAnimatorConfigHelper().updateAnimEase(this, z2, z3, i2, i3);
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder
        public void updateBlendBlur() {
        }
    }

    public static final class EditModeDividerTextItem implements MainPanelListItem {
        public static final Companion Companion = new Companion(null);
        public static final int ITEM_TYPE_DIVIDER_TEXT = 8398;
        private MainPanelItemViewHolder holder;
        private final int textRes;
        private final int type = ITEM_TYPE_DIVIDER_TEXT;
        private final int spanSize = 4;

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }

        public EditModeDividerTextItem(@StringRes int i2) {
            this.textRes = i2;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public MainPanelItemViewHolder getHolder() {
            return this.holder;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public int getSpanSize() {
            return this.spanSize;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public int getType() {
            return this.type;
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public void onBindViewHolder() {
            MainPanelItemViewHolder holder = getHolder();
            EditModeDividerTextHolder editModeDividerTextHolder = holder instanceof EditModeDividerTextHolder ? (EditModeDividerTextHolder) holder : null;
            if (editModeDividerTextHolder == null) {
                return;
            }
            editModeDividerTextHolder.setTextRes(this.textRes);
        }

        @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
        public void setHolder(MainPanelItemViewHolder mainPanelItemViewHolder) {
            this.holder = mainPanelItemViewHolder;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class TextMode {
        private static final /* synthetic */ O0.a $ENTRIES;
        private static final /* synthetic */ TextMode[] $VALUES;
        public static final TextMode NO_TEXT = new TextMode("NO_TEXT", 0);
        public static final TextMode TEXT = new TextMode("TEXT", 1);

        private static final /* synthetic */ TextMode[] $values() {
            return new TextMode[]{NO_TEXT, TEXT};
        }

        static {
            TextMode[] textModeArr$values = $values();
            $VALUES = textModeArr$values;
            $ENTRIES = O0.b.a(textModeArr$values);
        }

        private TextMode(String str, int i2) {
        }

        public static O0.a getEntries() {
            return $ENTRIES;
        }

        public static TextMode valueOf(String str) {
            return (TextMode) Enum.valueOf(TextMode.class, str);
        }

        public static TextMode[] values() {
            return (TextMode[]) $VALUES.clone();
        }
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MainPanelController.Mode.values().length];
            try {
                iArr[MainPanelController.Mode.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MainPanelController.Mode.EDIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.main.qs.QSListController$addTile$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        final /* synthetic */ QSRecord $record;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(QSRecord qSRecord) {
            super(0);
            this.$record = qSRecord;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m92invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m92invoke() {
            this.$record.setAdded(true);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.main.qs.QSListController$distributeTiles$4, reason: invalid class name */
    public static final class AnonymousClass4 extends o implements Function0 {
        public AnonymousClass4() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m93invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m93invoke() {
            Iterator<T> it = QSListController.this.getAddedTiles().iterator();
            while (it.hasNext()) {
                ((QSRecord) it.next()).addCallback();
            }
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.main.qs.QSListController$moveElement$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04841 extends o implements Function0 {
        public C04841() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m94invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m94invoke() {
            QSListController.this.tileCustomized = true;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.main.qs.QSListController$moveElement$2, reason: invalid class name */
    public static final class AnonymousClass2 extends o implements Function0 {
        public AnonymousClass2() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m95invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m95invoke() {
            QSListController.this.tileCustomized = true;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.main.qs.QSListController$removeTile$1, reason: invalid class name and case insensitive filesystem */
    public static final class C04851 extends o implements Function0 {
        final /* synthetic */ QSRecord $record;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04851(QSRecord qSRecord) {
            super(0);
            this.$record = qSRecord;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m96invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m96invoke() {
            this.$record.setAdded(false);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSListController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, @SystemUI Context sysUIContext, E0.a qsController, MiuiQSHost host, E0.a distributor, E0.a mainPanelController, QSRecord.Factory recordFactory, TileQueryHelper tileQueryHelper, @Main DelayableExecutor uiExecutor, @Main Handler uiHandler, @Background Handler bgHandler, QSCardsController qsCardsController, QSItemViewHolder.Factory holderFactory) {
        super(windowView);
        n.g(windowView, "windowView");
        n.g(sysUIContext, "sysUIContext");
        n.g(qsController, "qsController");
        n.g(host, "host");
        n.g(distributor, "distributor");
        n.g(mainPanelController, "mainPanelController");
        n.g(recordFactory, "recordFactory");
        n.g(tileQueryHelper, "tileQueryHelper");
        n.g(uiExecutor, "uiExecutor");
        n.g(uiHandler, "uiHandler");
        n.g(bgHandler, "bgHandler");
        n.g(qsCardsController, "qsCardsController");
        n.g(holderFactory, "holderFactory");
        this.sysUIContext = sysUIContext;
        this.qsController = qsController;
        this.host = host;
        this.distributor = distributor;
        this.mainPanelController = mainPanelController;
        this.recordFactory = recordFactory;
        this.tileQueryHelper = tileQueryHelper;
        this.uiExecutor = uiExecutor;
        this.uiHandler = uiHandler;
        this.bgHandler = bgHandler;
        this.qsCardsController = qsCardsController;
        this.holderFactory = holderFactory;
        this.priority = 100;
        this.lineDivider = new EditModeDividerItem(true);
        this.emptyLineDivider = new EditModeDividerItem(false);
        this.systemTextDivider = new EditModeDividerTextItem(R.string.qs_customize_divider_label_system_tiles_text);
        this.packageTextDivider = new EditModeDividerTextItem(R.string.qs_customize_divider_label_packages_tiles_text);
        this.addedTiles = new ArrayList<>();
        this.systemTiles = new ArrayList<>();
        this.packageTiles = new ArrayList<>();
        this.copiedTiles = new ArrayList<>();
        this.textMode = TextMode.NO_TEXT;
        this.distributeTilesRunnable = new Runnable() { // from class: miui.systemui.controlcenter.panel.main.qs.d
            @Override // java.lang.Runnable
            public final void run() {
                QSListController.distributeTilesRunnable$lambda$4(this.f5427a);
            }
        };
    }

    private final void distributeTileInfo(List<TileQueryHelper.TileInfo> list) {
        Object next;
        Object next2;
        Object next3;
        this.copiedTiles.clear();
        this.copiedTiles.addAll(this.addedTiles);
        ArrayList<QSRecord> arrayList = new ArrayList<>();
        ArrayList<QSRecord> arrayList2 = new ArrayList<>();
        for (TileQueryHelper.TileInfo tileInfo : list) {
            Iterator<T> it = this.addedTiles.iterator();
            while (true) {
                if (it.hasNext()) {
                    next = it.next();
                    if (n.c(((QSRecord) next).getSpec(), tileInfo.getSpec())) {
                        break;
                    }
                } else {
                    next = null;
                    break;
                }
            }
            QSRecord qSRecord = (QSRecord) next;
            if (qSRecord != null) {
                qSRecord.attachTileInfo(tileInfo);
            } else if (tileInfo.isSystem()) {
                Iterator<T> it2 = this.systemTiles.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        next2 = it2.next();
                        if (n.c(((QSRecord) next2).getSpec(), tileInfo.getSpec())) {
                            break;
                        }
                    } else {
                        next2 = null;
                        break;
                    }
                }
                QSRecord qSRecordCreate$default = (QSRecord) next2;
                if (qSRecordCreate$default != null) {
                    qSRecordCreate$default.attachTileInfo(tileInfo);
                } else {
                    qSRecordCreate$default = QSRecord.Factory.create$default(this.recordFactory, tileInfo, false, 2, (Object) null);
                }
                qSRecordCreate$default.setAdded(false);
                arrayList.add(qSRecordCreate$default);
            } else {
                Iterator<T> it3 = this.packageTiles.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        next3 = it3.next();
                        if (n.c(((QSRecord) next3).getSpec(), tileInfo.getSpec())) {
                            break;
                        }
                    } else {
                        next3 = null;
                        break;
                    }
                }
                QSRecord qSRecordCreate$default2 = (QSRecord) next3;
                if (qSRecordCreate$default2 != null) {
                    qSRecordCreate$default2.attachTileInfo(tileInfo);
                } else {
                    qSRecordCreate$default2 = QSRecord.Factory.create$default(this.recordFactory, tileInfo, false, 2, (Object) null);
                }
                qSRecordCreate$default2.setAdded(false);
                arrayList2.add(qSRecordCreate$default2);
            }
        }
        this.systemTiles = arrayList;
        this.packageTiles = arrayList2;
        MainPanelController.Mode mode = this.pendingMode;
        if (mode == null) {
            return;
        }
        MainPanelModeController.changeMode$default(((MainPanelController) this.mainPanelController.get()).getModeController(), mode, 1, false, 4, null);
        this.pendingMode = null;
    }

    private final void distributeTiles() {
        Object next;
        QSRecord qSRecordAttachQSTile;
        updateVoWifiTiles();
        Iterator<T> it = this.addedTiles.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).removeCallback();
        }
        ArrayList<QSRecord> arrayList = new ArrayList<>();
        Collection<QSTile> tiles = this.host.getTiles();
        n.f(tiles, "getTiles(...)");
        for (QSTile qSTile : tiles) {
            QSRecord qSRecordCreate$default = null;
            if (!TextUtils.isEmpty(qSTile.getTileSpec()) && !((QSController) this.qsController.get()).getQsListExcludeTileSpecs().contains(qSTile.getTileSpec())) {
                Iterator<T> it2 = this.addedTiles.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        next = it2.next();
                        if (n.c(((QSRecord) next).getSpec(), qSTile.getTileSpec())) {
                            break;
                        }
                    } else {
                        next = null;
                        break;
                    }
                }
                QSRecord qSRecord = (QSRecord) next;
                if (qSRecord == null || (qSRecordAttachQSTile = qSRecord.attachQSTile(qSTile)) == null) {
                    QSRecord.Factory factory = this.recordFactory;
                    n.d(qSTile);
                    qSRecordCreate$default = QSRecord.Factory.create$default(factory, qSTile, false, 2, (Object) null);
                } else {
                    qSRecordCreate$default = qSRecordAttachQSTile;
                }
            }
            if (qSRecordCreate$default != null) {
                arrayList.add(qSRecordCreate$default);
            }
        }
        this.addedTiles = arrayList;
        ArrayList arrayList2 = new ArrayList(I0.n.o(arrayList, 10));
        Iterator<T> it3 = arrayList.iterator();
        while (it3.hasNext()) {
            arrayList2.add(((QSRecord) it3.next()).getSpec());
        }
        Log.d(TAG, "distributeTiles " + arrayList2);
        for (QSRecord qSRecord2 : this.addedTiles) {
            boolean z2 = true;
            qSRecord2.setAdded(true);
            if (this.addedTiles.size() <= 8) {
                z2 = false;
            }
            qSRecord2.setRemovable(z2);
        }
        ((MainPanelContentDistributor) this.distributor.get()).notifyChanged(new AnonymousClass4());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void distributeTilesRunnable$lambda$4(QSListController this$0) {
        n.g(this$0, "this$0");
        Log.i(TAG, "on tile changed");
        this$0.distributeTiles();
    }

    private static final void moveElement$print(QSListController qSListController) {
        ArrayList<QSRecord> arrayList = qSListController.addedTiles;
        ArrayList arrayList2 = new ArrayList(I0.n.o(arrayList, 10));
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((QSRecord) it.next()).getSpec());
        }
        Log.d(TAG, "moveElement " + arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTileChanged$lambda$9(QSListController this$0, TileQueryHelper.TileInfo tile) {
        Object obj;
        Object next;
        Object next2;
        n.g(this$0, "this$0");
        n.g(tile, "$tile");
        Iterator<T> it = this$0.addedTiles.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (n.c(((QSRecord) next).getSpec(), tile.getSpec())) {
                    break;
                }
            }
        }
        QSRecord qSRecord = (QSRecord) next;
        if (qSRecord == null || qSRecord.attachTileInfo(tile) == null) {
            Iterator<T> it2 = this$0.systemTiles.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    next2 = null;
                    break;
                } else {
                    next2 = it2.next();
                    if (n.c(((QSRecord) next2).getSpec(), tile.getSpec())) {
                        break;
                    }
                }
            }
            QSRecord qSRecord2 = (QSRecord) next2;
            if (qSRecord2 != null) {
                qSRecord2.attachTileInfo(tile);
                return;
            }
            Iterator<T> it3 = this$0.packageTiles.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                Object next3 = it3.next();
                if (n.c(((QSRecord) next3).getSpec(), tile.getSpec())) {
                    obj = next3;
                    break;
                }
            }
            QSRecord qSRecord3 = (QSRecord) obj;
            if (qSRecord3 != null) {
                qSRecord3.attachTileInfo(tile);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onTilesChanged$lambda$5(QSListController this$0, List tiles) {
        n.g(this$0, "this$0");
        n.g(tiles, "$tiles");
        this$0.distributeTileInfo(tiles);
    }

    private final boolean shouldChangeTile() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime <= this.lastChangeTileTime + 200) {
            return false;
        }
        this.lastChangeTileTime = jElapsedRealtime;
        return true;
    }

    private final void updateVoWifiTiles() {
        Collection<QSTile> tiles = this.host.getTiles();
        n.f(tiles, "getTiles(...)");
        boolean z2 = false;
        boolean z3 = false;
        for (QSTile qSTile : tiles) {
            if (n.c(qSTile.getTileSpec(), TileSpecsKt.TILE_SPEC_VOWIFI1)) {
                z2 = true;
            } else if (n.c(qSTile.getTileSpec(), TileSpecsKt.TILE_SPEC_VOWIFI2)) {
                z3 = true;
            }
        }
        Log.i(TAG, "updateVoWifiTiles " + z2 + " " + z3);
        if (this.voWifi1Available == z2 && this.voWifi2Available == z3) {
            return;
        }
        this.voWifi1Available = z2;
        this.voWifi2Available = z3;
        if (z2 || z3) {
            this.qsCardsController.distributeVoWifiTiles();
        } else {
            this.qsCardsController.clearVoWifiTiles();
        }
    }

    public final void addTile(QSRecord record) {
        n.g(record, "record");
        if (!shouldChangeTile() || ((MainPanelController) this.mainPanelController.get()).getModeController().getMode() == MainPanelController.Mode.NORMAL) {
            return;
        }
        this.systemTiles.remove(record);
        this.packageTiles.remove(record);
        this.addedTiles.add(record);
        ((MainPanelContentDistributor) this.distributor.get()).notifyChanged(new AnonymousClass1(record));
        Iterator<T> it = this.addedTiles.iterator();
        while (true) {
            boolean z2 = true;
            if (!it.hasNext()) {
                this.tileCustomized = true;
                return;
            }
            QSRecord qSRecord = (QSRecord) it.next();
            if (this.addedTiles.size() <= 8) {
                z2 = false;
            }
            qSRecord.setRemovable(z2);
        }
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public void applyPayload(MainPanelItemViewHolder holder, MainPanelListItem item, Object payload) {
        n.g(holder, "holder");
        n.g(item, "item");
        n.g(payload, "payload");
        if (payload instanceof TextMode) {
            QSItemViewHolder qSItemViewHolder = holder instanceof QSItemViewHolder ? (QSItemViewHolder) holder : null;
            if (qSItemViewHolder != null) {
                qSItemViewHolder.onTextModeChanged(this.textMode, true);
            }
        }
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean available(boolean z2) {
        return ((MainPanelController) this.mainPanelController.get()).getStyle() != MainPanelController.Style.COMPACT;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public MainPanelItemViewHolder createViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        if (i2 == 8398) {
            CustomizeDividerLabelBinding customizeDividerLabelBindingInflate = CustomizeDividerLabelBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            n.f(customizeDividerLabelBindingInflate, "inflate(...)");
            return new EditModeDividerTextHolder(customizeDividerLabelBindingInflate);
        }
        if (i2 != 8453) {
            if (i2 != 3484337) {
                return null;
            }
            CustomizeDividerBinding customizeDividerBindingInflate = CustomizeDividerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            n.f(customizeDividerBindingInflate, "inflate(...)");
            return new EditModeDividerHolder(customizeDividerBindingInflate);
        }
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.qs_tile_item_view, parent, false);
        n.e(viewInflate, "null cannot be cast to non-null type miui.systemui.controlcenter.qs.tileview.QSTileItemView");
        QSTileItemView qSTileItemView = (QSTileItemView) viewInflate;
        qSTileItemView.init(new QSTileItemIconView(getContext(), this.sysUIContext, false, 4, null));
        return this.holderFactory.create(qSTileItemView);
    }

    public final ArrayList<QSRecord> getAddedTiles() {
        return this.addedTiles;
    }

    public final boolean getInPendingEditMode() {
        return this.pendingMode == MainPanelController.Mode.EDIT;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public List<MainPanelListItem> getListItems() {
        int i2 = WhenMappings.$EnumSwitchMapping$0[((MainPanelController) this.mainPanelController.get()).getModeController().getMode().ordinal()];
        if (i2 == 1) {
            return this.addedTiles;
        }
        if (i2 != 2) {
            throw new H0.g();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.addedTiles);
        if (!this.systemTiles.isEmpty() || !this.packageTiles.isEmpty()) {
            arrayList.add(this.lineDivider);
        }
        if (!this.systemTiles.isEmpty()) {
            arrayList.add(this.systemTextDivider);
            arrayList.addAll(this.systemTiles);
        }
        if (!this.systemTiles.isEmpty() && !this.packageTiles.isEmpty()) {
            arrayList.add(this.emptyLineDivider);
        }
        if (this.packageTiles.isEmpty()) {
            return arrayList;
        }
        arrayList.add(this.packageTextDivider);
        arrayList.addAll(this.packageTiles);
        return arrayList;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public int getPriority() {
        return this.priority;
    }

    public final QSCardsController getQsCardsController() {
        return this.qsCardsController;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean getRightOrLeft() {
        return false;
    }

    public final TextMode getTextMode() {
        return this.textMode;
    }

    public final boolean getTileCustomized() {
        return this.tileCustomized;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean moveElement(MainPanelListItem mainPanelListItem, MainPanelListItem mainPanelListItem2) {
        int iO = u.O(this.addedTiles, mainPanelListItem);
        int iO2 = u.O(this.addedTiles, mainPanelListItem2);
        Log.d(TAG, "moveElement " + iO + " " + iO2);
        boolean z2 = false;
        if (iO >= 0 && iO2 >= 0 && iO != iO2) {
            z2 = true;
            if (iO < iO2) {
                while (iO < iO2) {
                    int i2 = iO + 1;
                    Collections.swap(this.addedTiles, iO, i2);
                    iO = i2;
                }
                ((MainPanelContentDistributor) this.distributor.get()).notifyChanged(new C04841());
                moveElement$print(this);
            } else {
                int i3 = iO2 + 1;
                if (i3 <= iO) {
                    while (true) {
                        Collections.swap(this.addedTiles, iO, iO - 1);
                        if (iO == i3) {
                            break;
                        }
                        iO--;
                    }
                }
                ((MainPanelContentDistributor) this.distributor.get()).notifyChanged(new AnonymousClass2());
                moveElement$print(this);
            }
        }
        return z2;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public void onBindViewHolder(MainPanelItemViewHolder holder, MainPanelListItem item) {
        n.g(holder, "holder");
        n.g(item, "item");
        super.onBindViewHolder(holder, item);
        QSItemViewHolder qSItemViewHolder = holder instanceof QSItemViewHolder ? (QSItemViewHolder) holder : null;
        if (qSItemViewHolder != null) {
            qSItemViewHolder.onTextModeChanged(this.textMode, false);
        }
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        this.host.addCallback(this);
        distributeTiles();
        ((MainPanelController) this.mainPanelController.get()).getModeController().addOnModeChangedListener(this);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        this.host.removeCallback(this);
        Iterator<T> it = this.addedTiles.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).removeCallback();
        }
        this.addedTiles.clear();
        this.copiedTiles.clear();
        this.systemTiles.clear();
        this.packageTiles.clear();
        ((MainPanelController) this.mainPanelController.get()).getModeController().removeOnModeChangedListener(this);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelModeController.OnModeChangedListener
    public void onModeChanged() {
        if (WhenMappings.$EnumSwitchMapping$0[((MainPanelController) this.mainPanelController.get()).getModeController().getMode().ordinal()] != 1) {
            this.tileCustomized = false;
            return;
        }
        if (this.tileCustomized) {
            Log.d(TAG, "change tiles");
            TileQueryHelper tileQueryHelper = this.tileQueryHelper;
            MiuiQSHost miuiQSHost = this.host;
            ArrayList<QSRecord> arrayList = this.copiedTiles;
            ArrayList arrayList2 = new ArrayList(I0.n.o(arrayList, 10));
            Iterator<T> it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((QSRecord) it.next()).getSpec());
            }
            ArrayList<QSRecord> arrayList3 = this.addedTiles;
            ArrayList arrayList4 = new ArrayList(I0.n.o(arrayList3, 10));
            Iterator<T> it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                arrayList4.add(((QSRecord) it2.next()).getSpec());
            }
            tileQueryHelper.saveSpecs(miuiQSHost, arrayList2, arrayList4);
            this.tileCustomized = false;
        }
        this.tileQueryHelper.notifyCustomizeFinished();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStart() {
        Iterator<T> it = this.addedTiles.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).startListening();
        }
        this.tileQueryHelper.setListener(this);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        Iterator<T> it = this.addedTiles.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).setListening(false);
        }
        QSTileItemIconView.Companion.onStop();
        this.tileQueryHelper.setListener(null);
        this.pendingMode = null;
    }

    @Override // miui.systemui.controlcenter.qs.customize.TileQueryHelper.TileStateListener
    public void onTileChanged(final TileQueryHelper.TileInfo tile) {
        n.g(tile, "tile");
        this.uiExecutor.execute(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.qs.c
            @Override // java.lang.Runnable
            public final void run() {
                QSListController.onTileChanged$lambda$9(this.f5425a, tile);
            }
        });
    }

    public void onTilesChanged() {
        this.uiHandler.removeCallbacks(this.distributeTilesRunnable);
        this.uiHandler.post(this.distributeTilesRunnable);
    }

    public final void removeTile(QSRecord record) {
        n.g(record, "record");
        if (!shouldChangeTile() || ((MainPanelController) this.mainPanelController.get()).getModeController().getMode() == MainPanelController.Mode.NORMAL) {
            return;
        }
        this.addedTiles.remove(record);
        TileQueryHelper.TileInfo tileInfo = record.getTileInfo();
        if (tileInfo == null || tileInfo.isSystem()) {
            this.systemTiles.add(record);
        } else {
            this.packageTiles.add(record);
        }
        ((MainPanelContentDistributor) this.distributor.get()).notifyChanged(new C04851(record));
        Iterator<T> it = this.addedTiles.iterator();
        while (true) {
            boolean z2 = true;
            if (!it.hasNext()) {
                this.tileCustomized = true;
                return;
            }
            QSRecord qSRecord = (QSRecord) it.next();
            if (this.addedTiles.size() <= 8) {
                z2 = false;
            }
            qSRecord.setRemovable(z2);
        }
    }

    public final void setAddedTiles(ArrayList<QSRecord> arrayList) {
        n.g(arrayList, "<set-?>");
        this.addedTiles = arrayList;
    }

    public final void setTextMode(TextMode value) {
        n.g(value, "value");
        if (this.textMode == value) {
            return;
        }
        this.textMode = value;
        ((MainPanelContentDistributor) this.distributor.get()).notifyChangedWithPayloads(this.textMode);
    }

    public final void startQuery(MainPanelController.Mode mode) {
        n.g(mode, "mode");
        if (this.pendingMode == mode) {
            return;
        }
        this.pendingMode = mode;
        this.tileQueryHelper.queryTiles(this.host);
    }

    @Override // miui.systemui.controlcenter.qs.customize.TileQueryHelper.TileStateListener
    public void onTilesChanged(final List<TileQueryHelper.TileInfo> tiles) {
        n.g(tiles, "tiles");
        this.uiExecutor.execute(new Runnable() { // from class: miui.systemui.controlcenter.panel.main.qs.b
            @Override // java.lang.Runnable
            public final void run() {
                QSListController.onTilesChanged$lambda$5(this.f5423a, tiles);
            }
        });
    }
}
