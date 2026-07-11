package miui.systemui.controlcenter.panel.main.devicecenter.devices;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.plugins.ActivityStarter;
import com.google.android.flexbox.FlexboxLayoutManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.panel.main.devicecenter.DeviceCenterTrackHelper;
import miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceItem;
import miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryController;
import miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.devicecenter.DeviceCenterController;
import miui.systemui.devicecenter.DeviceCenterListener;
import miui.systemui.devicecenter.devices.DeviceInfoWrapper;
import miui.systemui.util.ControlsUtils;
import miui.systemui.util.HapticFeedback;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
@SuppressLint({"ClickableViewAccessibility"})
public final class DeviceCenterCardController extends ControlCenterViewController<ControlCenterWindowViewImpl> implements DeviceCenterListener {
    public static final Companion Companion = new Companion(null);
    private static final long REFRESH_DELAY = 50;
    private static final String TAG = "DeviceCenterCardController";
    private final DeviceCenterCardController$_adapter$1 _adapter;
    private final ActivityStarter activityStarter;
    private final DeviceItem.DetailItem detailItem;
    private final DeviceCenterController deviceCenterController;
    private ArrayList<DeviceItem> deviceItems;
    private final DeviceItem.EmptyDeviceItem emptyDeviceItem;
    private final E0.a entryController;
    private final HapticFeedback hapticFeedback;
    private final DeviceCenterCardController$layoutManager$1 layoutManager;
    private List<DeviceInfoWrapper> pendingUpdateList;
    private final RecyclerView.RecycledViewPool recyclerViewPool;
    private DeviceCenterCardController$settingsObserver$1 settingsObserver;
    private boolean settingsPackageNameChanged;
    private final Handler uiHandler;
    private final Runnable updateTask;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class RecyclerViewObserver implements ViewTreeObserver.OnGlobalLayoutListener {
        private final RecyclerView recyclerView;

        public RecyclerViewObserver(RecyclerView recyclerView) {
            kotlin.jvm.internal.n.g(recyclerView, "recyclerView");
            this.recyclerView = recyclerView;
        }

        private final int calculateRecyclerViewMargin(RecyclerView recyclerView) {
            int width = (recyclerView.getWidth() - recyclerView.getPaddingLeft()) - recyclerView.getPaddingRight();
            return (width - ((width / 4) * 4)) / 2;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            Log.d(DeviceCenterCardController.TAG, "onGlobalLayout");
            this.recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            ViewGroup.LayoutParams layoutParams = this.recyclerView.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
            if (marginLayoutParams != null) {
                marginLayoutParams.leftMargin = calculateRecyclerViewMargin(this.recyclerView);
                this.recyclerView.setLayoutParams(marginLayoutParams);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v4, types: [miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceCenterCardController$settingsObserver$1] */
    /* JADX WARN: Type inference failed for: r3v1, types: [miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceCenterCardController$layoutManager$1] */
    public DeviceCenterCardController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, @Main final Handler uiHandler, DeviceCenterController deviceCenterController, ActivityStarter activityStarter, E0.a entryController, HapticFeedback hapticFeedback) {
        super(windowView);
        kotlin.jvm.internal.n.g(windowView, "windowView");
        kotlin.jvm.internal.n.g(uiHandler, "uiHandler");
        kotlin.jvm.internal.n.g(deviceCenterController, "deviceCenterController");
        kotlin.jvm.internal.n.g(activityStarter, "activityStarter");
        kotlin.jvm.internal.n.g(entryController, "entryController");
        kotlin.jvm.internal.n.g(hapticFeedback, "hapticFeedback");
        this.uiHandler = uiHandler;
        this.deviceCenterController = deviceCenterController;
        this.activityStarter = activityStarter;
        this.entryController = entryController;
        this.hapticFeedback = hapticFeedback;
        DeviceItem.EmptyDeviceItem emptyDeviceItem = new DeviceItem.EmptyDeviceItem();
        this.emptyDeviceItem = emptyDeviceItem;
        this.detailItem = new DeviceItem.DetailItem();
        this.deviceItems = I0.m.f(emptyDeviceItem);
        this.recyclerViewPool = new RecyclerView.RecycledViewPool();
        this.settingsObserver = new ContentObserver(uiHandler) { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceCenterCardController$settingsObserver$1
            @Override // android.database.ContentObserver
            public void onChange(boolean z2, Uri uri) {
                if (uri != null) {
                    DeviceCenterCardController deviceCenterCardController = this.this$0;
                    if (kotlin.jvm.internal.n.c(uri, ControlsUtils.INSTANCE.getURI_DEVICE_CONTROL_PACKAGE_NAME())) {
                        deviceCenterCardController.updateListForDCCardChanged();
                    }
                }
            }
        };
        this.updateTask = new Runnable() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.a
            @Override // java.lang.Runnable
            public final void run() {
                DeviceCenterCardController.updateTask$lambda$3(this.f5362a);
            }
        };
        this._adapter = new DeviceCenterCardController$_adapter$1(this);
        final Context context = getContext();
        this.layoutManager = new FlexboxLayoutManager(context) { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceCenterCardController$layoutManager$1
            @Override // com.google.android.flexbox.FlexboxLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override // com.google.android.flexbox.FlexboxLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DeviceCenterEntryViewHolder.Mode getMode() {
        return this.deviceItems.size() == 1 ? DeviceCenterEntryViewHolder.Mode.MODE_COLLAPSED : this.deviceItems.size() > 4 ? DeviceCenterEntryViewHolder.Mode.MODE_2_ROWS : DeviceCenterEntryViewHolder.Mode.MODE_1_ROW;
    }

    private final DeviceItem.OtherDeviceControllerItem getOtherDeviceControllerItem() {
        DeviceItem.OtherDeviceControllerItem otherDeviceControllerItem = new DeviceItem.OtherDeviceControllerItem();
        otherDeviceControllerItem.setPackagename(getPackageName());
        otherDeviceControllerItem.setDevicesServiceInfo(new DevicesServiceInfo(getContext(), getContext().getPackageManager(), getContext().getApplicationInfo().uid, new ComponentName(getPackageName(), "")));
        return otherDeviceControllerItem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getPackageName() {
        String string = Settings.Secure.getString(getContext().getContentResolver(), ControlsUtils.SETTING_KEY_CONTROL_CENTER_DEVICE_CONTROL);
        if (string != null && string.length() != 0) {
            ControlsUtils controlsUtils = ControlsUtils.INSTANCE;
            Context context = getContext();
            kotlin.jvm.internal.n.d(string);
            if (controlsUtils.isAppDevicesServiceStillLived(context, string)) {
                return string;
            }
        }
        return ControlsUtils.MI_SMART_HUB_ACTION;
    }

    private final void handleUpdateList(boolean z2) {
        List<DeviceInfoWrapper> list;
        if (((DeviceCenterEntryController) this.entryController.get()).getDeviceCenterListening() && (list = this.pendingUpdateList) != null) {
            final ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.deviceItems);
            final ArrayList<DeviceItem> arrayList2 = new ArrayList<>();
            for (DeviceInfoWrapper deviceInfoWrapper : list) {
                DeviceItem.DeviceInfoItem deviceInfoItem = new DeviceItem.DeviceInfoItem(deviceInfoWrapper.getId());
                deviceInfoItem.setDeviceInfo(deviceInfoWrapper);
                arrayList2.add(deviceInfoItem);
            }
            if (isNeedShowDevicesControl()) {
                arrayList2.clear();
                arrayList2.add(getOtherDeviceControllerItem());
            } else {
                arrayList2.add(arrayList2.isEmpty() ? this.emptyDeviceItem : this.detailItem);
            }
            this.deviceItems = arrayList2;
            DiffUtil.calculateDiff(new DiffUtil.Callback() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceCenterCardController.handleUpdateList.2
                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public boolean areContentsTheSame(int i2, int i3) {
                    DeviceItem deviceItem = arrayList.get(i2);
                    kotlin.jvm.internal.n.f(deviceItem, "get(...)");
                    DeviceItem deviceItem2 = deviceItem;
                    DeviceItem deviceItem3 = arrayList2.get(i3);
                    kotlin.jvm.internal.n.f(deviceItem3, "get(...)");
                    DeviceItem deviceItem4 = deviceItem3;
                    if (deviceItem2 instanceof DeviceItem.DeviceInfoItem) {
                        DeviceInfoWrapper deviceInfo = ((DeviceItem.DeviceInfoItem) deviceItem2).getDeviceInfo();
                        if (deviceInfo != null) {
                            DeviceItem.DeviceInfoItem deviceInfoItem2 = deviceItem4 instanceof DeviceItem.DeviceInfoItem ? (DeviceItem.DeviceInfoItem) deviceItem4 : null;
                            if (deviceInfo.isContentTheSame(deviceInfoItem2 != null ? deviceInfoItem2.getDeviceInfo() : null)) {
                                return true;
                            }
                        }
                        return false;
                    }
                    if (!(deviceItem2 instanceof DeviceItem.OtherDeviceControllerItem)) {
                        return deviceItem2 == deviceItem4;
                    }
                    DevicesServiceInfo devicesServiceInfo = ((DeviceItem.OtherDeviceControllerItem) deviceItem2).getDevicesServiceInfo();
                    if (devicesServiceInfo == null) {
                        return false;
                    }
                    DeviceItem.OtherDeviceControllerItem otherDeviceControllerItem = deviceItem4 instanceof DeviceItem.OtherDeviceControllerItem ? (DeviceItem.OtherDeviceControllerItem) deviceItem4 : null;
                    return kotlin.jvm.internal.n.c(devicesServiceInfo.isContentTheSame(otherDeviceControllerItem != null ? otherDeviceControllerItem.getDevicesServiceInfo() : null), Boolean.TRUE);
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public boolean areItemsTheSame(int i2, int i3) {
                    DeviceItem deviceItem = arrayList.get(i2);
                    kotlin.jvm.internal.n.f(deviceItem, "get(...)");
                    DeviceItem deviceItem2 = deviceItem;
                    DeviceItem deviceItem3 = arrayList2.get(i3);
                    kotlin.jvm.internal.n.f(deviceItem3, "get(...)");
                    DeviceItem deviceItem4 = deviceItem3;
                    if (deviceItem2 instanceof DeviceItem.DeviceInfoItem) {
                        DeviceInfoWrapper deviceInfo = ((DeviceItem.DeviceInfoItem) deviceItem2).getDeviceInfo();
                        if (deviceInfo != null) {
                            DeviceItem.DeviceInfoItem deviceInfoItem2 = deviceItem4 instanceof DeviceItem.DeviceInfoItem ? (DeviceItem.DeviceInfoItem) deviceItem4 : null;
                            if (deviceInfo.isWrapperTheSame(deviceInfoItem2 != null ? deviceInfoItem2.getDeviceInfo() : null)) {
                                return true;
                            }
                        }
                        return false;
                    }
                    if (!(deviceItem2 instanceof DeviceItem.OtherDeviceControllerItem)) {
                        return deviceItem2 == deviceItem4;
                    }
                    DevicesServiceInfo devicesServiceInfo = ((DeviceItem.OtherDeviceControllerItem) deviceItem2).getDevicesServiceInfo();
                    if (devicesServiceInfo == null) {
                        return false;
                    }
                    DeviceItem.OtherDeviceControllerItem otherDeviceControllerItem = deviceItem4 instanceof DeviceItem.OtherDeviceControllerItem ? (DeviceItem.OtherDeviceControllerItem) deviceItem4 : null;
                    return kotlin.jvm.internal.n.c(devicesServiceInfo.isWrapperTheSame(otherDeviceControllerItem != null ? otherDeviceControllerItem.getDevicesServiceInfo() : null), Boolean.TRUE);
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public Object getChangePayload(int i2, int i3) {
                    DeviceItem deviceItem = arrayList.get(i2);
                    kotlin.jvm.internal.n.f(deviceItem, "get(...)");
                    DeviceItem deviceItem2 = deviceItem;
                    DeviceItem deviceItem3 = arrayList2.get(i3);
                    kotlin.jvm.internal.n.f(deviceItem3, "get(...)");
                    DeviceItem deviceItem4 = deviceItem3;
                    if ((deviceItem2 instanceof DeviceItem.DeviceInfoItem) && (deviceItem4 instanceof DeviceItem.DeviceInfoItem)) {
                        return ((DeviceItem.DeviceInfoItem) deviceItem4).getDeviceInfo();
                    }
                    if ((deviceItem2 instanceof DeviceItem.OtherDeviceControllerItem) && (deviceItem4 instanceof DeviceItem.OtherDeviceControllerItem)) {
                        return ((DeviceItem.OtherDeviceControllerItem) deviceItem4).getDevicesServiceInfo();
                    }
                    return null;
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public int getNewListSize() {
                    return arrayList2.size();
                }

                @Override // androidx.recyclerview.widget.DiffUtil.Callback
                public int getOldListSize() {
                    return arrayList.size();
                }
            }).dispatchUpdatesTo(this._adapter);
            setJustifyContent(this.deviceItems.size() < 4 ? 5 : 0);
            MainPanelItemViewHolder holder = ((DeviceCenterEntryController) this.entryController.get()).getHolder();
            DeviceCenterEntryViewHolder deviceCenterEntryViewHolder = holder instanceof DeviceCenterEntryViewHolder ? (DeviceCenterEntryViewHolder) holder : null;
            if (deviceCenterEntryViewHolder != null) {
                DeviceCenterEntryViewHolder.changeMode$default(deviceCenterEntryViewHolder, getMode(), z2, false, false, 12, null);
            }
        }
    }

    public static /* synthetic */ void handleUpdateList$default(DeviceCenterCardController deviceCenterCardController, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        deviceCenterCardController.handleUpdateList(z2);
    }

    private final boolean isNeedShowDevicesControl() {
        boolean z2 = ControlsUtils.INSTANCE.isFeatureSupport(getContext()) && getPackageName().length() > 0 && !TextUtils.equals(getPackageName(), ControlsUtils.MI_SMART_HUB_ACTION);
        Log.d(TAG, "isNeedShowDevicesControl :" + z2);
        return z2;
    }

    private final void updateList() {
        this.uiHandler.removeCallbacks(this.updateTask);
        this.uiHandler.postDelayed(this.updateTask, REFRESH_DELAY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateListForDCCardChanged() {
        if (((DeviceCenterEntryController) this.entryController.get()).getDeviceCenterListening()) {
            handleUpdateList(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void updateTask$lambda$3(final DeviceCenterCardController this$0) {
        RecyclerView.ItemAnimator itemAnimator;
        RecyclerView.ItemAnimator itemAnimator2;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (((ControlCenterWindowViewImpl) this$0.getView()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            RecyclerView recyclerView = this$0._adapter.getRecyclerView();
            if (recyclerView != null && (itemAnimator2 = recyclerView.getItemAnimator()) != null && !itemAnimator2.isRunning()) {
                handleUpdateList$default(this$0, false, 1, null);
                return;
            }
            RecyclerView recyclerView2 = this$0._adapter.getRecyclerView();
            if (recyclerView2 == null || (itemAnimator = recyclerView2.getItemAnimator()) == null) {
                return;
            }
            itemAnimator.isRunning(new RecyclerView.ItemAnimator.ItemAnimatorFinishedListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.b
                @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator.ItemAnimatorFinishedListener
                public final void onAnimationsFinished() {
                    DeviceCenterCardController.updateTask$lambda$3$lambda$2(this.f5363a);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateTask$lambda$3$lambda$2(DeviceCenterCardController this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        this$0.updateList();
    }

    public final RecyclerView.Adapter<DeviceViewHolder> getAdapter() {
        return this._adapter;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        this._adapter.onConfigurationChanged();
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        ControlsUtils controlsUtils = ControlsUtils.INSTANCE;
        if (controlsUtils.isFeatureSupport(getContext())) {
            getContext().getContentResolver().registerContentObserver(controlsUtils.getURI_DEVICE_CONTROL_PACKAGE_NAME(), true, this.settingsObserver);
            this.deviceCenterController.setPackageChangeListening(true);
            if (isNeedShowDevicesControl()) {
                this.deviceItems = I0.m.f(getOtherDeviceControllerItem());
            }
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        if (ControlsUtils.INSTANCE.isFeatureSupport(getContext())) {
            getContext().getContentResolver().unregisterContentObserver(this.settingsObserver);
            this.deviceCenterController.setPackageChangeListening(false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // miui.systemui.devicecenter.DeviceCenterListener
    public void onDeviceListChanged(List<DeviceInfoWrapper> list) {
        kotlin.jvm.internal.n.g(list, "list");
        this.pendingUpdateList = list;
        if (((ControlCenterWindowViewImpl) getView()).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            updateList();
        }
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onResume() {
        updateList();
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStart() {
        this.deviceCenterController.addListener(this);
        this.deviceCenterController.setControlCenterIsStart(true);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onStop() {
        Log.i(TAG, "onStop and StopDiscover");
        this.settingsPackageNameChanged = false;
        this.deviceCenterController.stopDiscover();
        this.deviceCenterController.removeListener(this);
        this.deviceCenterController.setControlCenterIsStart(false);
        this.deviceCenterController.exitCard();
        DeviceCenterTrackHelper deviceCenterTrackHelper = DeviceCenterTrackHelper.INSTANCE;
        deviceCenterTrackHelper.trackExposed(this.deviceItems);
        deviceCenterTrackHelper.trackDeviceCard(this.deviceItems);
        deviceCenterTrackHelper.trackDeviceFoundDuration(this.deviceCenterController.getDevicesCacheTrackData(), this.deviceCenterController.getDevicesTrackData());
    }
}
