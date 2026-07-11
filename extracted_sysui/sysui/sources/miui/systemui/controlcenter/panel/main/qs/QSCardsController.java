package miui.systemui.controlcenter.panel.main.qs;

import H0.s;
import I0.u;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.systemui.plugins.miui.qs.MiuiQSHost;
import com.android.systemui.plugins.qs.QSTile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.o;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.QsCardItemViewBinding;
import miui.systemui.controlcenter.panel.main.MainPanelContent;
import miui.systemui.controlcenter.panel.main.MainPanelContentDistributor;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.MainPanelModeController;
import miui.systemui.controlcenter.panel.main.MainPanelStyleController;
import miui.systemui.controlcenter.panel.main.qs.QSRecord;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem;
import miui.systemui.controlcenter.qs.QSController;
import miui.systemui.controlcenter.qs.tileview.QSCardItemIconView;
import miui.systemui.controlcenter.qs.tileview.QSCardItemView;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.dagger.qualifiers.SystemUI;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class QSCardsController extends ControlCenterViewController<ControlCenterWindowViewImpl> implements MainPanelContent {
    private final CopyOnWriteArrayList<QSRecord> cardRecords;
    private final CopyOnWriteArrayList<QSTile> cardTiles;
    private final E0.a distributor;
    private final MiuiQSHost host;
    private final H0.d layoutInflater$delegate;
    private final E0.a mainPanelModeController;
    private final E0.a mainPanelStyleController;
    private final int priority;
    private final E0.a qsController;
    private final QSRecord.Factory recordFactory;
    private final boolean rightOrLeft;
    private boolean showVoWifiTiles;
    private final Context sysUIContext;
    private final CopyOnWriteArrayList<QSRecord> voWifiRecords;
    private final CopyOnWriteArrayList<QSTile> voWifiTiles;

    public static abstract class VoWifiCallback implements QSTile.Callback {
        public static final int CALLBACK_TYPE = 869434;
        public static final Companion Companion = new Companion(null);

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }

        public int getCallbackType() {
            return CALLBACK_TYPE;
        }

        public void onAnnouncementRequested(CharSequence charSequence) {
        }

        public void onScanStateChanged(boolean z2) {
        }

        public void onShowDetail(boolean z2) {
        }

        public void onToggleStateChanged(boolean z2) {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.controlcenter.panel.main.qs.QSCardsController$distributeTiles$1, reason: invalid class name */
    public static final class AnonymousClass1 extends o implements Function0 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m91invoke() {
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m91invoke();
            return s.f314a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSCardsController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, @SystemUI Context sysUIContext, E0.a qsController, MiuiQSHost host, E0.a distributor, QSRecord.Factory recordFactory, E0.a mainPanelModeController, E0.a mainPanelStyleController) {
        super(windowView);
        n.g(windowView, "windowView");
        n.g(sysUIContext, "sysUIContext");
        n.g(qsController, "qsController");
        n.g(host, "host");
        n.g(distributor, "distributor");
        n.g(recordFactory, "recordFactory");
        n.g(mainPanelModeController, "mainPanelModeController");
        n.g(mainPanelStyleController, "mainPanelStyleController");
        this.sysUIContext = sysUIContext;
        this.qsController = qsController;
        this.host = host;
        this.distributor = distributor;
        this.recordFactory = recordFactory;
        this.mainPanelModeController = mainPanelModeController;
        this.mainPanelStyleController = mainPanelStyleController;
        this.priority = 20;
        this.rightOrLeft = true;
        this.layoutInflater$delegate = H0.e.b(new QSCardsController$layoutInflater$2(this));
        this.voWifiRecords = new CopyOnWriteArrayList<>();
        this.voWifiTiles = new CopyOnWriteArrayList<>();
        this.cardRecords = new CopyOnWriteArrayList<>();
        this.cardTiles = new CopyOnWriteArrayList<>();
    }

    private final void checkVoWifiTiles(boolean z2) {
        Iterator<T> it = this.voWifiTiles.iterator();
        boolean z3 = false;
        while (it.hasNext()) {
            if (((QSTile) it.next()).isAvailable()) {
                z3 = true;
            }
        }
        if (this.showVoWifiTiles != z3) {
            this.showVoWifiTiles = z3;
            if (z2) {
                distributeTiles();
            }
        }
    }

    public static /* synthetic */ void checkVoWifiTiles$default(QSCardsController qSCardsController, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        qSCardsController.checkVoWifiTiles(z2);
    }

    private final void clearCardTiles() {
        Iterator<T> it = this.cardRecords.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).removeCallback();
        }
        this.cardRecords.clear();
        Iterator<T> it2 = this.cardTiles.iterator();
        while (it2.hasNext()) {
            ((QSTile) it2.next()).removeCallbacks();
        }
        this.cardTiles.clear();
    }

    private final void createCardTiles() {
        clearCardTiles();
        CopyOnWriteArrayList<QSTile> copyOnWriteArrayList = this.cardTiles;
        List<String> cardStyleTileSpecs = ((QSController) this.qsController.get()).getCardStyleTileSpecs();
        ArrayList arrayList = new ArrayList();
        for (String str : cardStyleTileSpecs) {
            QSController qSController = (QSController) this.qsController.get();
            n.d(str);
            QSTile qSTileCreateTile = qSController.createTile(str);
            if (qSTileCreateTile != null) {
                arrayList.add(qSTileCreateTile);
            }
        }
        copyOnWriteArrayList.addAll(arrayList);
        CopyOnWriteArrayList<QSRecord> copyOnWriteArrayList2 = this.cardRecords;
        CopyOnWriteArrayList<QSTile> copyOnWriteArrayList3 = this.cardTiles;
        ArrayList arrayList2 = new ArrayList(I0.n.o(copyOnWriteArrayList3, 10));
        for (QSTile qSTile : copyOnWriteArrayList3) {
            QSRecord.Factory factory = this.recordFactory;
            n.d(qSTile);
            arrayList2.add(factory.create(qSTile, true));
        }
        copyOnWriteArrayList2.addAll(arrayList2);
        Iterator<T> it = this.cardRecords.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).addCallback();
        }
    }

    private final void createVoWifiTiles() {
        clearVoWifiTiles();
        CopyOnWriteArrayList<QSTile> copyOnWriteArrayList = this.voWifiTiles;
        List<String> voWifiTileSpecs = ((QSController) this.qsController.get()).getVoWifiTileSpecs();
        ArrayList arrayList = new ArrayList();
        for (String str : voWifiTileSpecs) {
            QSController qSController = (QSController) this.qsController.get();
            n.d(str);
            QSTile qSTileCreateTile = qSController.createTile(str);
            if (qSTileCreateTile != null) {
                qSTileCreateTile.addCallback(new VoWifiCallback() { // from class: miui.systemui.controlcenter.panel.main.qs.QSCardsController$createVoWifiTiles$1$1$1
                    public void onStateChanged(QSTile.State state) {
                        QSCardsController.checkVoWifiTiles$default(this.this$0, false, 1, null);
                    }
                });
            } else {
                qSTileCreateTile = null;
            }
            if (qSTileCreateTile != null) {
                arrayList.add(qSTileCreateTile);
            }
        }
        copyOnWriteArrayList.addAll(arrayList);
        CopyOnWriteArrayList<QSRecord> copyOnWriteArrayList2 = this.voWifiRecords;
        CopyOnWriteArrayList<QSTile> copyOnWriteArrayList3 = this.voWifiTiles;
        ArrayList arrayList2 = new ArrayList(I0.n.o(copyOnWriteArrayList3, 10));
        for (QSTile qSTile : copyOnWriteArrayList3) {
            QSRecord.Factory factory = this.recordFactory;
            n.d(qSTile);
            arrayList2.add(factory.create(qSTile, true));
        }
        copyOnWriteArrayList2.addAll(arrayList2);
        checkVoWifiTiles(false);
        Iterator<T> it = this.voWifiRecords.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).addCallback();
        }
    }

    private final void distributeTiles() {
        ((MainPanelContentDistributor) this.distributor.get()).notifyChanged(AnonymousClass1.INSTANCE);
    }

    private final LayoutInflater getLayoutInflater() {
        return (LayoutInflater) this.layoutInflater$delegate.getValue();
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean available(boolean z2) {
        return (((MainPanelStyleController) this.mainPanelStyleController.get()).getStyle() == MainPanelController.Style.COMPACT || ((MainPanelModeController) this.mainPanelModeController.get()).getMode() == MainPanelController.Mode.EDIT) ? false : true;
    }

    public final void clearVoWifiTiles() {
        this.showVoWifiTiles = false;
        Iterator<T> it = this.voWifiRecords.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).removeCallback();
        }
        this.voWifiRecords.clear();
        Iterator<T> it2 = this.voWifiTiles.iterator();
        while (it2.hasNext()) {
            ((QSTile) it2.next()).removeCallbacks();
        }
        this.voWifiTiles.clear();
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public MainPanelItemViewHolder createViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        if (i2 != 2273) {
            return null;
        }
        QsCardItemViewBinding qsCardItemViewBindingInflate = QsCardItemViewBinding.inflate(getLayoutInflater(), parent, false);
        n.f(qsCardItemViewBindingInflate, "inflate(...)");
        QSCardItemView root = qsCardItemViewBindingInflate.getRoot();
        Context context = root.getContext();
        n.f(context, "getContext(...)");
        root.init(new QSCardItemIconView(context, this.sysUIContext, null, 4, null));
        n.f(root, "apply(...)");
        return new QSCardViewHolder(root);
    }

    public final void distributeVoWifiTiles() {
        if (this.showVoWifiTiles) {
            return;
        }
        createVoWifiTiles();
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public List<MainPanelListItem> getListItems() {
        return this.showVoWifiTiles ? u.a0(this.voWifiRecords, this.cardRecords) : this.cardRecords;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public int getPriority() {
        return this.priority;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean getRightOrLeft() {
        return this.rightOrLeft;
    }

    public final QSRecord getTile(String str) {
        for (QSRecord qSRecord : this.cardRecords) {
            if (n.c(qSRecord.getSpec(), str)) {
                return qSRecord;
            }
        }
        for (QSRecord qSRecord2 : this.voWifiRecords) {
            if (n.c(qSRecord2.getSpec(), str)) {
                return qSRecord2;
            }
        }
        return null;
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        createVoWifiTiles();
        createCardTiles();
        distributeTiles();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        clearVoWifiTiles();
        clearCardTiles();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onResume() {
        Iterator<T> it = this.voWifiRecords.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).startMarquee();
        }
        Iterator<T> it2 = this.cardRecords.iterator();
        while (it2.hasNext()) {
            ((QSRecord) it2.next()).startMarquee();
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStart() {
        Iterator<T> it = this.voWifiRecords.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).startListening();
        }
        Iterator<T> it2 = this.cardRecords.iterator();
        while (it2.hasNext()) {
            ((QSRecord) it2.next()).startListening();
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        for (QSRecord qSRecord : this.voWifiRecords) {
            qSRecord.setListening(false);
            qSRecord.stopMarquee();
        }
        for (QSRecord qSRecord2 : this.cardRecords) {
            qSRecord2.setListening(false);
            qSRecord2.stopMarquee();
        }
    }

    public final void restartMarquee() {
        stopMarquee();
        startMarquee();
    }

    public final void startMarquee() {
        Iterator<T> it = this.voWifiRecords.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).startMarquee();
        }
        Iterator<T> it2 = this.cardRecords.iterator();
        while (it2.hasNext()) {
            ((QSRecord) it2.next()).startMarquee();
        }
    }

    public final void stopMarquee() {
        Iterator<T> it = this.voWifiRecords.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).stopMarquee();
        }
        Iterator<T> it2 = this.cardRecords.iterator();
        while (it2.hasNext()) {
            ((QSRecord) it2.next()).stopMarquee();
        }
    }
}
