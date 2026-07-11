package miui.systemui.controlcenter.panel.main.devicecenter.devices;

import android.content.Intent;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import miui.systemui.controlcenter.databinding.DeviceCenterDeviceItemBinding;
import miui.systemui.controlcenter.databinding.DeviceCenterEmptyItemBinding;
import miui.systemui.controlcenter.databinding.DeviceCenterOtherItemBinding;
import miui.systemui.controlcenter.events.ControlCenterEventTracker;
import miui.systemui.controlcenter.panel.main.devicecenter.DeviceCenterTrackHelper;
import miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceCenterCardController;
import miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceItem;
import miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryController;
import miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryViewHolder;
import miui.systemui.controlcenter.panel.main.recyclerview.MainPanelItemViewHolder;
import miui.systemui.devicecenter.devices.DeviceInfoWrapper;
import miui.systemui.miplay.MiPlayDetailActivity;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.ControlsUtils;
import miui.systemui.util.SmartDeviceUtils;
import miuix.animation.Folme;
import miuix.animation.base.AnimConfig;
import systemui.plugin.eventtracking.EventTracker;
import systemui.plugin.eventtracking.events.DeviceCenterEventsKt;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceCenterCardController$_adapter$1 extends RecyclerView.Adapter<DeviceViewHolder> {
    private RecyclerView recyclerView;
    final /* synthetic */ DeviceCenterCardController this$0;

    public DeviceCenterCardController$_adapter$1(DeviceCenterCardController deviceCenterCardController) {
        this.this$0 = deviceCenterCardController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$10(DeviceCenterCardController this$0, View view, MotionEvent motionEvent) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        MainPanelItemViewHolder holder = ((DeviceCenterEntryController) this$0.entryController.get()).getHolder();
        DeviceCenterEntryViewHolder deviceCenterEntryViewHolder = holder instanceof DeviceCenterEntryViewHolder ? (DeviceCenterEntryViewHolder) holder : null;
        if (deviceCenterEntryViewHolder == null) {
            return false;
        }
        MainPanelItemViewHolder holder2 = ((DeviceCenterEntryController) this$0.entryController.get()).getHolder();
        deviceCenterEntryViewHolder.onTouch(holder2 != null ? holder2.itemView : null, motionEvent);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$12(DeviceCenterCardController this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (!CommonUtils.isTinyScreen(this$0.getContext())) {
            ControlCenterEventTracker.Companion.trackMiSmartHubLongClickedEvent(EventTracker.Companion.getScreenType(this$0.getContext()), this$0.getContext().getResources().getConfiguration().orientation, false);
        }
        Log.i("DeviceCenterCardController", "Empty item onLongClick! Start Milink Main Activity , and Stop Discover device");
        this$0.hapticFeedback.longClick();
        this$0.activityStarter.postStartActivityDismissingKeyguard(SmartDeviceUtils.INSTANCE.getDeviceCardIntent(1, this$0.getContext()), 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$13(DeviceCenterCardController this$0, View view, MotionEvent motionEvent) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        MainPanelItemViewHolder holder = ((DeviceCenterEntryController) this$0.entryController.get()).getHolder();
        DeviceCenterEntryViewHolder deviceCenterEntryViewHolder = holder instanceof DeviceCenterEntryViewHolder ? (DeviceCenterEntryViewHolder) holder : null;
        if (deviceCenterEntryViewHolder == null) {
            return false;
        }
        MainPanelItemViewHolder holder2 = ((DeviceCenterEntryController) this$0.entryController.get()).getHolder();
        deviceCenterEntryViewHolder.onTouch(holder2 != null ? holder2.itemView : null, motionEvent);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$2$lambda$1(View view, MotionEvent motionEvent) {
        Folme.useAt(view).touch().setTint(0.0f, 0.0f, 0.0f, 0.0f).onMotionEventEx(view, motionEvent, new AnimConfig());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$3(View view, MotionEvent motionEvent) {
        Folme.useAt(view).touch().setTint(0.0f, 0.0f, 0.0f, 0.0f).onMotionEventEx(view, motionEvent, new AnimConfig());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$5(DeviceCenterCardController this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (!CommonUtils.isTinyScreen(this$0.getContext())) {
            ControlCenterEventTracker.Companion.trackMiSmartHubClickedEvent(EventTracker.Companion.getScreenType(this$0.getContext()), this$0.getContext().getResources().getConfiguration().orientation, false);
        }
        Log.i("DeviceCenterCardController", "DetailView item onClick! Start Milink Main Activity , and Stop Discover device");
        this$0.deviceCenterController.stopDiscover();
        Intent intent = new Intent(ControlsUtils.MI_SMART_HUB_ACTION);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra(MiPlayDetailActivity.EXTRA_PARAM_REF, DeviceCenterEventsKt.PAGE_DEVICE_CENTER_EXPOSE);
        this$0.activityStarter.startActivity(intent, false);
        DeviceCenterTrackHelper.INSTANCE.trackSecondaryPageClick(DeviceCenterTrackHelper.CLICK_CONTENT_MORE_DEVICE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$6(View view, MotionEvent motionEvent) {
        Folme.useAt(view).touch().setTint(0.0f, 0.0f, 0.0f, 0.0f).onMotionEventEx(view, motionEvent, new AnimConfig());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$8(DeviceCenterCardController this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (!CommonUtils.isTinyScreen(this$0.getContext())) {
            ControlCenterEventTracker.Companion.trackMiSmartHubClickedEvent(EventTracker.Companion.getScreenType(this$0.getContext()), this$0.getContext().getResources().getConfiguration().orientation, false);
        }
        Log.i("DeviceCenterCardController", "Empty item onClick! Start Milink Main Activity , and Stop Discover device");
        this$0.hapticFeedback.click();
        this$0.deviceCenterController.stopDiscover();
        Intent intent = new Intent(ControlsUtils.MI_SMART_HUB_ACTION);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra(MiPlayDetailActivity.EXTRA_PARAM_REF, DeviceCenterEventsKt.PAGE_DEVICE_CENTER_EXPOSE);
        this$0.activityStarter.startActivity(intent, false);
        DeviceCenterTrackHelper.INSTANCE.trackSecondaryPageClick(DeviceCenterTrackHelper.CLICK_CONTENT_HOTSPOT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$9(DeviceCenterCardController this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (!CommonUtils.isTinyScreen(this$0.getContext())) {
            ControlCenterEventTracker.Companion.trackMiSmartHubLongClickedEvent(EventTracker.Companion.getScreenType(this$0.getContext()), this$0.getContext().getResources().getConfiguration().orientation, false);
        }
        Log.i("DeviceCenterCardController", "Empty item onLongClick! Start Milink Main Activity , and Stop Discover device");
        this$0.hapticFeedback.longClick();
        this$0.activityStarter.postStartActivityDismissingKeyguard(SmartDeviceUtils.INSTANCE.getDeviceCardIntent(1, this$0.getContext()), 0);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.this$0.deviceItems.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return ((DeviceItem) this.this$0.deviceItems.get(i2)).getViewType();
    }

    public final RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        kotlin.jvm.internal.n.g(recyclerView, "recyclerView");
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(this.this$0.layoutManager);
        recyclerView.setRecycledViewPool(this.this$0.recyclerViewPool);
        MainPanelItemViewHolder holder = ((DeviceCenterEntryController) this.this$0.entryController.get()).getHolder();
        DeviceCenterEntryViewHolder deviceCenterEntryViewHolder = holder instanceof DeviceCenterEntryViewHolder ? (DeviceCenterEntryViewHolder) holder : null;
        if (deviceCenterEntryViewHolder != null) {
            DeviceCenterEntryViewHolder.changeMode$default(deviceCenterEntryViewHolder, this.this$0.getMode(), false, true, false, 8, null);
        }
    }

    public final void onConfigurationChanged() {
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new DeviceCenterCardController.RecyclerViewObserver(recyclerView));
        }
        notifyItemRangeChanged(0, getItemCount(), this.this$0.getContext().getResources().getConfiguration());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        kotlin.jvm.internal.n.g(recyclerView, "recyclerView");
        this.recyclerView = null;
        recyclerView.setLayoutManager(null);
    }

    public final void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2, List list) {
        onBindViewHolder((DeviceViewHolder) viewHolder, i2, (List<Object>) list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int i2) {
        kotlin.jvm.internal.n.g(parent, "parent");
        switch (i2) {
            case 36789:
                DeviceCenterEmptyItemBinding deviceCenterEmptyItemBindingInflate = DeviceCenterEmptyItemBinding.inflate(LayoutInflater.from(this.this$0.getContext()), parent, false);
                kotlin.jvm.internal.n.f(deviceCenterEmptyItemBindingInflate, "inflate(...)");
                return new EmptyDeviceViewHolder(deviceCenterEmptyItemBindingInflate);
            case 338245:
                DeviceCenterDeviceItemBinding deviceCenterDeviceItemBindingInflate = DeviceCenterDeviceItemBinding.inflate(LayoutInflater.from(this.this$0.getContext()), parent, false);
                kotlin.jvm.internal.n.f(deviceCenterDeviceItemBindingInflate, "inflate(...)");
                return new DetailViewHolder(deviceCenterDeviceItemBindingInflate);
            case 348423:
                DeviceCenterDeviceItemBinding deviceCenterDeviceItemBindingInflate2 = DeviceCenterDeviceItemBinding.inflate(LayoutInflater.from(this.this$0.getContext()), parent, false);
                kotlin.jvm.internal.n.f(deviceCenterDeviceItemBindingInflate2, "inflate(...)");
                return new DeviceItemViewHolder(deviceCenterDeviceItemBindingInflate2);
            case 348429:
                DeviceCenterOtherItemBinding deviceCenterOtherItemBindingInflate = DeviceCenterOtherItemBinding.inflate(LayoutInflater.from(this.this$0.getContext()), parent, false);
                kotlin.jvm.internal.n.f(deviceCenterOtherItemBindingInflate, "inflate(...)");
                return new OtherDeviceViewHolder(deviceCenterOtherItemBindingInflate);
            default:
                throw new IllegalArgumentException("wrong view type " + i2);
        }
    }

    public void onBindViewHolder(DeviceViewHolder holder, int i2, List<Object> payloads) {
        kotlin.jvm.internal.n.g(holder, "holder");
        kotlin.jvm.internal.n.g(payloads, "payloads");
        DeviceCenterCardController deviceCenterCardController = this.this$0;
        for (Object obj : payloads) {
            if (obj instanceof Configuration) {
                holder.updateConfiguration((Configuration) obj);
            }
            if (obj instanceof DeviceInfoWrapper) {
                DeviceItemViewHolder deviceItemViewHolder = holder instanceof DeviceItemViewHolder ? (DeviceItemViewHolder) holder : null;
                if (deviceItemViewHolder != null) {
                    ((DeviceItemViewHolder) holder).getBinding().container.setTag(obj);
                    deviceItemViewHolder.bindDeviceInfo((DeviceInfoWrapper) obj, new DeviceCenterCardController$_adapter$1$onBindViewHolder$1$1$1(deviceCenterCardController, obj, holder, i2));
                }
                holder.itemView.setOnTouchListener(new View.OnTouchListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.k
                    @Override // android.view.View.OnTouchListener
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        return DeviceCenterCardController$_adapter$1.onBindViewHolder$lambda$2$lambda$1(view, motionEvent);
                    }
                });
            }
            if (obj instanceof DevicesServiceInfo) {
                OtherDeviceViewHolder otherDeviceViewHolder = holder instanceof OtherDeviceViewHolder ? (OtherDeviceViewHolder) holder : null;
                if (otherDeviceViewHolder != null) {
                    otherDeviceViewHolder.bindDeviceInfo((DevicesServiceInfo) obj, new DeviceCenterCardController$_adapter$1$onBindViewHolder$1$3(deviceCenterCardController));
                }
            }
        }
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, i2, payloads);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(DeviceViewHolder holder, int i2) {
        DeviceInfoWrapper deviceInfo;
        kotlin.jvm.internal.n.g(holder, "holder");
        Configuration configuration = this.this$0.getContext().getResources().getConfiguration();
        kotlin.jvm.internal.n.f(configuration, "getConfiguration(...)");
        holder.updateConfiguration(configuration);
        if (holder instanceof DeviceItemViewHolder) {
            Object obj = this.this$0.deviceItems.get(i2);
            DeviceItem.DeviceInfoItem deviceInfoItem = obj instanceof DeviceItem.DeviceInfoItem ? (DeviceItem.DeviceInfoItem) obj : null;
            if (deviceInfoItem == null || (deviceInfo = deviceInfoItem.getDeviceInfo()) == null) {
                return;
            }
            DeviceItemViewHolder deviceItemViewHolder = (DeviceItemViewHolder) holder;
            deviceItemViewHolder.bindDeviceInfo(deviceInfo, new DeviceCenterCardController$_adapter$1$onBindViewHolder$2(deviceInfo, i2, this.this$0, holder));
            deviceItemViewHolder.getBinding().container.setTag(deviceInfo);
            holder.itemView.setOnTouchListener(new View.OnTouchListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.c
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return DeviceCenterCardController$_adapter$1.onBindViewHolder$lambda$3(view, motionEvent);
                }
            });
            return;
        }
        if (holder instanceof DetailViewHolder) {
            ((DetailViewHolder) holder).onBind();
            View view = holder.itemView;
            final DeviceCenterCardController deviceCenterCardController = this.this$0;
            view.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    DeviceCenterCardController$_adapter$1.onBindViewHolder$lambda$5(deviceCenterCardController, view2);
                }
            });
            holder.itemView.setOnTouchListener(new View.OnTouchListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.e
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view2, MotionEvent motionEvent) {
                    return DeviceCenterCardController$_adapter$1.onBindViewHolder$lambda$6(view2, motionEvent);
                }
            });
            return;
        }
        if (holder instanceof EmptyDeviceViewHolder) {
            View view2 = holder.itemView;
            final DeviceCenterCardController deviceCenterCardController2 = this.this$0;
            view2.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    DeviceCenterCardController$_adapter$1.onBindViewHolder$lambda$8(deviceCenterCardController2, view3);
                }
            });
            View view3 = holder.itemView;
            final DeviceCenterCardController deviceCenterCardController3 = this.this$0;
            view3.setOnLongClickListener(new View.OnLongClickListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.g
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view4) {
                    return DeviceCenterCardController$_adapter$1.onBindViewHolder$lambda$9(deviceCenterCardController3, view4);
                }
            });
            View view4 = holder.itemView;
            final DeviceCenterCardController deviceCenterCardController4 = this.this$0;
            view4.setOnTouchListener(new View.OnTouchListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.h
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view5, MotionEvent motionEvent) {
                    return DeviceCenterCardController$_adapter$1.onBindViewHolder$lambda$10(deviceCenterCardController4, view5, motionEvent);
                }
            });
            return;
        }
        if (holder instanceof OtherDeviceViewHolder) {
            Object obj2 = this.this$0.deviceItems.get(i2);
            DeviceItem.OtherDeviceControllerItem otherDeviceControllerItem = obj2 instanceof DeviceItem.OtherDeviceControllerItem ? (DeviceItem.OtherDeviceControllerItem) obj2 : null;
            if (otherDeviceControllerItem == null || TextUtils.isEmpty(otherDeviceControllerItem.getPackagename())) {
                return;
            }
            DevicesServiceInfo devicesServiceInfo = otherDeviceControllerItem.getDevicesServiceInfo();
            if (devicesServiceInfo != null) {
                ((OtherDeviceViewHolder) holder).bindDeviceInfo(devicesServiceInfo, new DeviceCenterCardController$_adapter$1$onBindViewHolder$9$1(this.this$0));
            }
            View view5 = holder.itemView;
            final DeviceCenterCardController deviceCenterCardController5 = this.this$0;
            view5.setOnLongClickListener(new View.OnLongClickListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.i
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view6) {
                    return DeviceCenterCardController$_adapter$1.onBindViewHolder$lambda$12(deviceCenterCardController5, view6);
                }
            });
            View view6 = holder.itemView;
            final DeviceCenterCardController deviceCenterCardController6 = this.this$0;
            view6.setOnTouchListener(new View.OnTouchListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.j
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view7, MotionEvent motionEvent) {
                    return DeviceCenterCardController$_adapter$1.onBindViewHolder$lambda$13(deviceCenterCardController6, view7, motionEvent);
                }
            });
        }
    }
}
