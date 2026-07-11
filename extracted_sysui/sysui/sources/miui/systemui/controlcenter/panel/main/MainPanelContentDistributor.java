package miui.systemui.controlcenter.panel.main;

import I0.m;
import I0.q;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.MainThread;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.panel.main.brightness.BrightnessSliderController;
import miui.systemui.controlcenter.panel.main.footer.FooterSpaceController;
import miui.systemui.controlcenter.panel.main.header.HeaderSpaceController;
import miui.systemui.controlcenter.panel.main.media.MediaPlayerController;
import miui.systemui.controlcenter.panel.main.qs.CompactQSCardController;
import miui.systemui.controlcenter.panel.main.qs.CompactQSListController;
import miui.systemui.controlcenter.panel.main.qs.EditButtonController;
import miui.systemui.controlcenter.panel.main.qs.QSCardsController;
import miui.systemui.controlcenter.panel.main.qs.QSListController;
import miui.systemui.controlcenter.panel.main.qs.WordlessModeController;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelAdapter;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.panel.main.security.SecurityFooterController;
import miui.systemui.controlcenter.panel.main.volume.VolumeSliderController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.DeviceUtils;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class MainPanelContentDistributor extends ControlCenterViewController<ControlCenterWindowViewImpl> {
    public static final Companion Companion = new Companion(null);
    private static final int MSG_NOTIFY_CHANGED = 23;
    private final ArrayList<Function0> changeCallbacks;
    private final ArrayList<ControlCenterViewController<?>> childControllers;
    private boolean forceVertical;
    private final MainPanelContentDistributor$handler$1 handler;
    private final MainPanelAdapter leftAdapter;
    private final FooterSpaceController leftFooterSpace;
    private final HeaderSpaceController leftHeaderSpace;
    private final ArrayList<MainPanelContent> leftPanelContent;
    private final E0.a modeController;
    private final QSListController qsListController;
    private final RecyclerView.RecycledViewPool recyclerViewPool;
    private final MainPanelAdapter rightAdapter;
    private final FooterSpaceController rightFooterSpace;
    private final HeaderSpaceController rightHeaderSpace;
    private final ArrayList<MainPanelContent> rightPanelContent;
    private final Looper uiLooper;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v8, types: [miui.systemui.controlcenter.panel.main.MainPanelContentDistributor$handler$1] */
    public MainPanelContentDistributor(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, E0.a modeController, QSListController qsListController, QSCardsController qsCard, CompactQSCardController compactQSCard, CompactQSListController compactQSList, MediaPlayerController mediaPlayerController, BrightnessSliderController brightnessSliderController, VolumeSliderController volumeSliderController, EditButtonController editButtonController, WordlessModeController wordlessModeController, E0.a deviceControlsEntryController, E0.a deviceCenterController, SecurityFooterController securityFooterController, HeaderSpaceController.Factory headerFactory, FooterSpaceController.Factory footerFactory, MainPanelAdapter.Factory adapterFactory, @Main final Looper uiLooper) {
        super(windowView);
        n.g(windowView, "windowView");
        n.g(modeController, "modeController");
        n.g(qsListController, "qsListController");
        n.g(qsCard, "qsCard");
        n.g(compactQSCard, "compactQSCard");
        n.g(compactQSList, "compactQSList");
        n.g(mediaPlayerController, "mediaPlayerController");
        n.g(brightnessSliderController, "brightnessSliderController");
        n.g(volumeSliderController, "volumeSliderController");
        n.g(editButtonController, "editButtonController");
        n.g(wordlessModeController, "wordlessModeController");
        n.g(deviceControlsEntryController, "deviceControlsEntryController");
        n.g(deviceCenterController, "deviceCenterController");
        n.g(securityFooterController, "securityFooterController");
        n.g(headerFactory, "headerFactory");
        n.g(footerFactory, "footerFactory");
        n.g(adapterFactory, "adapterFactory");
        n.g(uiLooper, "uiLooper");
        this.modeController = modeController;
        this.qsListController = qsListController;
        this.uiLooper = uiLooper;
        HeaderSpaceController headerSpaceControllerCreate = headerFactory.create(false);
        this.leftHeaderSpace = headerSpaceControllerCreate;
        HeaderSpaceController headerSpaceControllerCreate2 = headerFactory.create(true);
        this.rightHeaderSpace = headerSpaceControllerCreate2;
        FooterSpaceController footerSpaceControllerCreate = footerFactory.create(false);
        this.leftFooterSpace = footerSpaceControllerCreate;
        FooterSpaceController footerSpaceControllerCreate2 = footerFactory.create(true);
        this.rightFooterSpace = footerSpaceControllerCreate2;
        ArrayList<MainPanelContent> arrayList = new ArrayList<>();
        this.rightPanelContent = arrayList;
        ArrayList<MainPanelContent> arrayList2 = new ArrayList<>();
        this.leftPanelContent = arrayList2;
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        this.recyclerViewPool = recycledViewPool;
        this.leftAdapter = adapterFactory.create(arrayList2, recycledViewPool);
        this.rightAdapter = adapterFactory.create(arrayList, recycledViewPool);
        this.changeCallbacks = new ArrayList<>();
        this.handler = new Handler(uiLooper) { // from class: miui.systemui.controlcenter.panel.main.MainPanelContentDistributor$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                n.g(msg, "msg");
                if (msg.what == 23) {
                    MainPanelContentDistributor.handleNotifyChanged$default(this.this$0, false, 1, null);
                }
            }
        };
        ArrayList<ControlCenterViewController<?>> arrayListF = m.f(qsCard, qsListController, compactQSCard, mediaPlayerController, brightnessSliderController, volumeSliderController, editButtonController, wordlessModeController, securityFooterController, headerSpaceControllerCreate, headerSpaceControllerCreate2, footerSpaceControllerCreate, footerSpaceControllerCreate2);
        if (DeviceUtils.isSupportControlDevices()) {
            arrayListF.add(deviceCenterController.get());
            arrayListF.add(deviceControlsEntryController.get());
        }
        if (CommonUtils.isFlipDevice()) {
            arrayListF.add(compactQSList);
        }
        if (arrayListF.size() > 1) {
            q.r(arrayListF, new Comparator() { // from class: miui.systemui.controlcenter.panel.main.MainPanelContentDistributor$childControllers$lambda$1$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t3) {
                    ConfigUtils.OnConfigChangeListener onConfigChangeListener = (ControlCenterViewController) t2;
                    MainPanelContent mainPanelContent = onConfigChangeListener instanceof MainPanelContent ? (MainPanelContent) onConfigChangeListener : null;
                    Integer numValueOf = Integer.valueOf(mainPanelContent != null ? mainPanelContent.getPriority() : Integer.MAX_VALUE);
                    ConfigUtils.OnConfigChangeListener onConfigChangeListener2 = (ControlCenterViewController) t3;
                    MainPanelContent mainPanelContent2 = onConfigChangeListener2 instanceof MainPanelContent ? (MainPanelContent) onConfigChangeListener2 : null;
                    return K0.a.a(numValueOf, Integer.valueOf(mainPanelContent2 != null ? mainPanelContent2.getPriority() : Integer.MAX_VALUE));
                }
            });
        }
        this.childControllers = arrayListF;
        this.forceVertical = CommonUtils.INSTANCE.getForceVertical();
    }

    public static /* synthetic */ void distributePanels$default(MainPanelContentDistributor mainPanelContentDistributor, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = !CommonUtils.INSTANCE.getInVerticalMode(mainPanelContentDistributor.getContext());
        }
        mainPanelContentDistributor.distributePanels(z2);
    }

    public static /* synthetic */ void handleNotifyChanged$default(MainPanelContentDistributor mainPanelContentDistributor, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        mainPanelContentDistributor.handleNotifyChanged(z2);
    }

    private final void suppressItemAnimator(boolean z2) {
        this.rightAdapter.getItemAnimator().setSuppressAnimation(z2);
        this.leftAdapter.getItemAnimator().setSuppressAnimation(z2);
    }

    public final void distributePanels(boolean z2) {
        if (getInited()) {
            this.rightPanelContent.clear();
            this.leftPanelContent.clear();
            if (!z2) {
                for (ConfigUtils.OnConfigChangeListener onConfigChangeListener : getChildControllers()) {
                    n.e(onConfigChangeListener, "null cannot be cast to non-null type miui.systemui.controlcenter.panel.main.MainPanelContent");
                    if (((MainPanelContent) onConfigChangeListener).available(z2)) {
                        this.rightPanelContent.add((MainPanelContent) onConfigChangeListener);
                    }
                }
                return;
            }
            for (ConfigUtils.OnConfigChangeListener onConfigChangeListener2 : getChildControllers()) {
                n.e(onConfigChangeListener2, "null cannot be cast to non-null type miui.systemui.controlcenter.panel.main.MainPanelContent");
                MainPanelContent mainPanelContent = (MainPanelContent) onConfigChangeListener2;
                if (mainPanelContent.available(z2)) {
                    if (mainPanelContent.getRightOrLeft()) {
                        this.rightPanelContent.add((MainPanelContent) onConfigChangeListener2);
                    } else {
                        this.leftPanelContent.add((MainPanelContent) onConfigChangeListener2);
                    }
                }
            }
        }
    }

    public final MainPanelAdapter getLeftAdapter() {
        return this.leftAdapter;
    }

    public final QSListController getQsListController() {
        return this.qsListController;
    }

    public final MainPanelAdapter getRightAdapter() {
        return this.rightAdapter;
    }

    @MainThread
    public final void handleNotifyChanged(boolean z2) {
        removeMessages(23);
        if (((MainPanelModeController) this.modeController.get()).getMode() == MainPanelController.Mode.EDIT) {
            suppressItemAnimator(false);
        }
        this.rightAdapter.notifyChanged(z2);
        this.leftAdapter.notifyChanged(z2);
        Iterator<T> it = this.changeCallbacks.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
        this.changeCallbacks.clear();
    }

    public final void notifyChanged(Function0 callback) {
        n.g(callback, "callback");
        this.changeCallbacks.add(callback);
        removeMessages(23);
        obtainMessage(23).sendToTarget();
    }

    public final void notifyChangedWithPayloads(Object payload) {
        n.g(payload, "payload");
        this.rightAdapter.notifyChangedWithPayloads(payload);
        this.leftAdapter.notifyChangedWithPayloads(payload);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onChildrenCreated() {
        super.onChildrenCreated();
        distributePanels$default(this, false, 1, null);
        handleNotifyChanged(false);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        if (ConfigUtils.INSTANCE.orientationChanged(i2) || this.forceVertical != CommonUtils.INSTANCE.getForceVertical()) {
            distributePanels$default(this, false, 1, null);
            handleNotifyChanged$default(this, false, 1, null);
            this.forceVertical = CommonUtils.INSTANCE.getForceVertical();
        }
        Configuration configuration = getContext().getResources().getConfiguration();
        n.f(configuration, "getConfiguration(...)");
        notifyChangedWithPayloads(configuration);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        this.rightPanelContent.clear();
        this.leftPanelContent.clear();
        this.leftAdapter.release();
        this.rightAdapter.release();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStart() {
        suppressItemAnimator(true);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        suppressItemAnimator(true);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onSuperPowerModeChanged(boolean z2) {
        distributePanels$default(this, false, 1, null);
        handleNotifyChanged$default(this, false, 1, null);
        notifyChangedWithPayloads(MainPanelAdapter.Companion.getSUPER_SAVE_MODE_PAYLOAD$miui_controlcenter_release());
    }

    public final void updateHeaderSpaceHeight() {
        MainPanelItemViewHolder holder = this.leftHeaderSpace.getHolder();
        HeaderSpaceController.HeaderSpaceViewHolder headerSpaceViewHolder = holder instanceof HeaderSpaceController.HeaderSpaceViewHolder ? (HeaderSpaceController.HeaderSpaceViewHolder) holder : null;
        if (headerSpaceViewHolder != null) {
            headerSpaceViewHolder.updateHeight();
        }
        MainPanelItemViewHolder holder2 = this.rightHeaderSpace.getHolder();
        HeaderSpaceController.HeaderSpaceViewHolder headerSpaceViewHolder2 = holder2 instanceof HeaderSpaceController.HeaderSpaceViewHolder ? (HeaderSpaceController.HeaderSpaceViewHolder) holder2 : null;
        if (headerSpaceViewHolder2 != null) {
            headerSpaceViewHolder2.updateHeight();
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public ArrayList<ControlCenterViewController<?>> getChildControllers() {
        return this.childControllers;
    }
}
