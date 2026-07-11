package miui.systemui.controlcenter.panel.main.qs;

import I0.m;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.systemui.plugins.qs.QSTile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.databinding.CompactQsCardContainerBinding;
import miui.systemui.controlcenter.panel.main.MainPanelContent;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.qs.CompactQSCardViewHolder;
import miui.systemui.controlcenter.panel.main.qs.QSRecord;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem;
import miui.systemui.controlcenter.qs.QSController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class CompactQSCardController extends MainPanelListItem.Controller<ControlCenterWindowViewImpl> implements MainPanelContent {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "CompatQSCardController";
    public static final int TYPE_COMPACT_QS_CARD = 22273;
    private final CompactQSCardViewHolder.Factory holderFactory;
    private final List<MainPanelListItem> listItems;
    private boolean listening;
    private final E0.a mainPanelController;
    private final int priority;
    private final E0.a qsController;
    private final ArrayList<QSRecord> qsRecords;
    private final QSRecord.Factory recordFactory;
    private final boolean rightOrLeft;
    private final int spanSize;
    private final int type;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompactQSCardController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, E0.a qsController, QSRecord.Factory recordFactory, CompactQSCardViewHolder.Factory holderFactory, E0.a mainPanelController) {
        super(windowView, windowView.getLifecycle());
        n.g(windowView, "windowView");
        n.g(qsController, "qsController");
        n.g(recordFactory, "recordFactory");
        n.g(holderFactory, "holderFactory");
        n.g(mainPanelController, "mainPanelController");
        this.qsController = qsController;
        this.recordFactory = recordFactory;
        this.holderFactory = holderFactory;
        this.mainPanelController = mainPanelController;
        this.type = TYPE_COMPACT_QS_CARD;
        this.spanSize = 1;
        this.priority = 19;
        this.rightOrLeft = true;
        this.listItems = m.f(this);
        this.qsRecords = new ArrayList<>();
    }

    private final CompactQSCardViewHolder getContainer() {
        MainPanelItemViewHolder holder = getHolder();
        if (holder instanceof CompactQSCardViewHolder) {
            return (CompactQSCardViewHolder) holder;
        }
        return null;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean available(boolean z2) {
        return ((MainPanelController) this.mainPanelController.get()).getStyle() == MainPanelController.Style.COMPACT && ((MainPanelController) this.mainPanelController.get()).getMode() == MainPanelController.Mode.NORMAL && !this.qsRecords.isEmpty();
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public MainPanelItemViewHolder createViewHolder(ViewGroup parent, int i2) {
        n.g(parent, "parent");
        if (i2 != 22273) {
            return null;
        }
        CompactQSCardViewHolder.Factory factory = this.holderFactory;
        CompactQsCardContainerBinding compactQsCardContainerBindingInflate = CompactQsCardContainerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        n.f(compactQsCardContainerBindingInflate, "inflate(...)");
        return factory.create(compactQsCardContainerBindingInflate);
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public List<MainPanelListItem> getListItems() {
        return this.listItems;
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem.Controller
    public boolean getListening() {
        return this.listening;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public int getPriority() {
        return this.priority;
    }

    @Override // miui.systemui.controlcenter.panel.main.MainPanelContent
    public boolean getRightOrLeft() {
        return this.rightOrLeft;
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
        CompactQSCardViewHolder container;
        if (this.qsRecords.isEmpty() || (container = getContainer()) == null) {
            return;
        }
        container.getQsTileHolder1().setItem$miui_controlcenter_release(this.qsRecords.get(0));
        this.qsRecords.get(0).setHolder(container.getQsTileHolder1());
        this.qsRecords.get(0).onBindViewHolder();
        container.getQsTileHolder2().setItem$miui_controlcenter_release(this.qsRecords.get(1));
        this.qsRecords.get(1).setHolder(container.getQsTileHolder2());
        this.qsRecords.get(1).onBindViewHolder();
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        List<String> compactCardStyleTileSpecs = ((QSController) this.qsController.get()).getCompactCardStyleTileSpecs();
        ArrayList arrayList = new ArrayList();
        for (String str : compactCardStyleTileSpecs) {
            QSController qSController = (QSController) this.qsController.get();
            n.d(str);
            QSTile qSTileCreateTile = qSController.createTile(str);
            if (qSTileCreateTile != null) {
                arrayList.add(qSTileCreateTile);
            }
        }
        if (arrayList.size() < 2) {
            return;
        }
        this.qsRecords.add(this.recordFactory.create((QSTile) arrayList.get(0), true));
        this.qsRecords.add(this.recordFactory.create((QSTile) arrayList.get(1), true));
        for (QSRecord qSRecord : this.qsRecords) {
            qSRecord.addCallback();
            qSRecord.setCompatTile(true);
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        Iterator<T> it = this.qsRecords.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).removeCallback();
        }
        this.qsRecords.clear();
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem
    public void onUnbindViewHolder() {
        for (QSRecord qSRecord : this.qsRecords) {
            qSRecord.onUnbindViewHolder();
            MainPanelItemViewHolder holder = qSRecord.getHolder();
            if (holder != null) {
                holder.setItem$miui_controlcenter_release(null);
            }
        }
    }

    @Override // miui.systemui.controlcenter.panel.main.recyclerview.MainPanelListItem.Controller
    public void setListening(boolean z2) {
        this.listening = z2;
        Iterator<T> it = this.qsRecords.iterator();
        while (it.hasNext()) {
            ((QSRecord) it.next()).setListening(getListening());
        }
    }
}
