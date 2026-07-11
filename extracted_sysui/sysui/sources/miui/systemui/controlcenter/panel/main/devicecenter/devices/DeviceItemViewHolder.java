package miui.systemui.controlcenter.panel.main.devicecenter.devices;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import kotlin.jvm.functions.Function1;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.databinding.DeviceCenterDeviceItemBinding;
import miui.systemui.controlcenter.panel.main.devicecenter.customview.CustomRootView;
import miui.systemui.devicecenter.devices.DeviceInfoWrapper;
import miui.systemui.devicecenter.view.MLBatteryView;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.ThreadUtils;
import miui.systemui.widget.FrameLayout;
import miui.systemui.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceItemViewHolder extends DeviceViewHolder {
    private final String TAG;
    private final DeviceCenterDeviceItemBinding binding;
    private Function1 clickAction;

    /* JADX WARN: Illegal instructions before constructor call */
    public DeviceItemViewHolder(DeviceCenterDeviceItemBinding binding) {
        kotlin.jvm.internal.n.g(binding, "binding");
        CustomRootView root = binding.getRoot();
        kotlin.jvm.internal.n.f(root, "getRoot(...)");
        super(root);
        this.binding = binding;
        this.TAG = "DeviceItemViewHolder";
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceItemViewHolder._init_$lambda$0(this.f5370a, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(DeviceItemViewHolder this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Function1 function1 = this$0.clickAction;
        if (function1 != null) {
            function1.invoke(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindDeviceInfo$lambda$7$lambda$4$lambda$3(ImageView icon, Drawable drawable) {
        kotlin.jvm.internal.n.g(icon, "$icon");
        icon.setImageDrawable(drawable);
    }

    private final void showIndicator(DeviceInfoWrapper deviceInfoWrapper) {
        int indicatorRes = deviceInfoWrapper.getIndicatorRes();
        if (deviceInfoWrapper.getIndicatorRes() == 0) {
            this.binding.indicator.setVisibility(8);
        } else {
            this.binding.indicator.setVisibility(0);
            this.binding.indicator.setImageResource(indicatorRes);
        }
    }

    public final void bindDeviceInfo(DeviceInfoWrapper deviceInfo, Function1 clickAction) {
        kotlin.jvm.internal.n.g(deviceInfo, "deviceInfo");
        kotlin.jvm.internal.n.g(clickAction, "clickAction");
        this.clickAction = clickAction;
        View view = this.itemView;
        String title = deviceInfo.getTitle();
        Context context = this.itemView.getContext();
        kotlin.jvm.internal.n.f(context, "getContext(...)");
        view.setContentDescription(title + deviceInfo.state2ContentDescription(context));
        ViewCompat.setAccessibilityDelegate(this.itemView, new AccessibilityDelegateCompat() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceItemViewHolder.bindDeviceInfo.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                kotlin.jvm.internal.n.g(host, "host");
                kotlin.jvm.internal.n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(Button.class.getName());
            }
        });
        boolean hasBatteryInfo = deviceInfo.getHasBatteryInfo();
        boolean inSyncGlasses = deviceInfo.getInSyncGlasses();
        boolean z2 = hasBatteryInfo || inSyncGlasses;
        this.binding.textIconContainer.setVisibility(z2 ? 0 : 8);
        this.binding.fullIcon.setVisibility(z2 ? 8 : 0);
        DeviceCenterDeviceItemBinding deviceCenterDeviceItemBinding = this.binding;
        final ImageView imageView = z2 ? deviceCenterDeviceItemBinding.textIcon : deviceCenterDeviceItemBinding.fullIcon;
        kotlin.jvm.internal.n.d(imageView);
        Drawable drawable = deviceInfo.getDrawable();
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        } else {
            Icon icon = deviceInfo.getIcon();
            if (icon != null) {
                icon.loadDrawableAsync(imageView.getContext(), new Icon.OnDrawableLoadedListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.m
                    @Override // android.graphics.drawable.Icon.OnDrawableLoadedListener
                    public final void onDrawableLoaded(Drawable drawable2) {
                        DeviceItemViewHolder.bindDeviceInfo$lambda$7$lambda$4$lambda$3(imageView, drawable2);
                    }
                }, ThreadUtils.getUiThreadHandler());
            } else {
                Integer color = deviceInfo.getColor();
                if (color != null) {
                    imageView.setBackgroundColor(color.intValue());
                }
            }
        }
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        FrameLayout container = this.binding.container;
        kotlin.jvm.internal.n.f(container, "container");
        CommonUtils.setBackgroundResourceEx$default(commonUtils, container, R.drawable.ic_device_center_item_background_default, false, 2, null);
        if (inSyncGlasses) {
            Log.d(this.TAG, "isInSyncGlasses");
            this.binding.cameraGlassesIndicator.setImageResource(deviceInfo.getIndicatorRes());
            this.binding.cameraGlassesIndicator.setVisibility(0);
            this.binding.battery.setVisibility(8);
            this.binding.indicator.setVisibility(8);
            return;
        }
        this.binding.cameraGlassesIndicator.setVisibility(8);
        if (z2) {
            Double[] batteryValues = deviceInfo.getBatteryValues();
            if (batteryValues == null || batteryValues.length < 3) {
                this.binding.battery.setBattery(0);
                Log.i(this.TAG, "batteryArray is null or size < 3");
                return;
            }
            Log.i(this.TAG, "batteryArray is " + batteryValues[0] + "  " + batteryValues[1] + "  " + batteryValues[2]);
            double dDoubleValue = (batteryValues[1].doubleValue() < 0.0d || batteryValues[2].doubleValue() < 0.0d) ? batteryValues[1].doubleValue() < 0.0d ? batteryValues[2].doubleValue() : batteryValues[2].doubleValue() < 0.0d ? batteryValues[1].doubleValue() : batteryValues[0].doubleValue() : Math.min(batteryValues[1].doubleValue(), batteryValues[2].doubleValue());
            if (dDoubleValue < 0.0d || dDoubleValue > 100.0d) {
                Log.i(this.TAG, "batteryArray error: " + dDoubleValue);
                this.binding.battery.setVisibility(8);
            } else {
                this.binding.battery.setVisibility(0);
                this.binding.battery.setBattery(Integer.valueOf((int) dDoubleValue));
            }
        }
        showIndicator(deviceInfo);
    }

    public final DeviceCenterDeviceItemBinding getBinding() {
        return this.binding;
    }

    public final String getTAG() {
        return this.TAG;
    }

    @Override // miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceViewHolder
    public void onConfigurationChanged(int i2) {
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        if (configUtils.dimensionsChanged(i2)) {
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            View itemView = this.itemView;
            kotlin.jvm.internal.n.f(itemView, "itemView");
            CommonUtils.setLayoutHeight$default(commonUtils, itemView, this.itemView.getResources().getDimensionPixelSize(R.dimen.device_center_item_height), false, 2, null);
            int dimensionPixelSize = this.itemView.getResources().getDimensionPixelSize(R.dimen.device_center_device_item_container_size);
            FrameLayout container = this.binding.container;
            kotlin.jvm.internal.n.f(container, "container");
            CommonUtils.setLayoutSize$default(commonUtils, container, dimensionPixelSize, dimensionPixelSize, false, 4, null);
            int dimensionPixelSize2 = this.itemView.getResources().getDimensionPixelSize(R.dimen.device_center_device_item_full_icon_size);
            ImageView fullIcon = this.binding.fullIcon;
            kotlin.jvm.internal.n.f(fullIcon, "fullIcon");
            CommonUtils.setLayoutSize$default(commonUtils, fullIcon, dimensionPixelSize2, dimensionPixelSize2, false, 4, null);
            int dimensionPixelSize3 = this.itemView.getResources().getDimensionPixelSize(R.dimen.device_center_device_item_text_icon_size);
            ImageView textIcon = this.binding.textIcon;
            kotlin.jvm.internal.n.f(textIcon, "textIcon");
            CommonUtils.setLayoutSize$default(commonUtils, textIcon, dimensionPixelSize3, dimensionPixelSize3, false, 4, null);
            int dimensionPixelSize4 = this.itemView.getResources().getDimensionPixelSize(R.dimen.circulate_card_battery_icon_height);
            int dimensionPixelSize5 = this.itemView.getResources().getDimensionPixelSize(R.dimen.circulate_card_battery_icon_width);
            MLBatteryView battery = this.binding.battery;
            kotlin.jvm.internal.n.f(battery, "battery");
            CommonUtils.setLayoutSize$default(commonUtils, battery, dimensionPixelSize5, dimensionPixelSize4, false, 4, null);
            ImageView imageView = this.binding.indicator;
            int dimensionPixelSize6 = this.itemView.getResources().getDimensionPixelSize(R.dimen.device_center_device_item_indicator_icon_size);
            kotlin.jvm.internal.n.d(imageView);
            CommonUtils.setLayoutSize$default(commonUtils, imageView, dimensionPixelSize6, dimensionPixelSize6, false, 4, null);
            CommonUtils.setMargins$default(commonUtils, imageView, this.itemView.getResources().getDimensionPixelSize(R.dimen.device_center_device_item_indicator_icon_margins), false, 2, null);
        }
        if (configUtils.colorsChanged(i2)) {
            CommonUtils commonUtils2 = CommonUtils.INSTANCE;
            FrameLayout container2 = this.binding.container;
            kotlin.jvm.internal.n.f(container2, "container");
            CommonUtils.setBackgroundResourceEx$default(commonUtils2, container2, R.drawable.ic_device_center_item_background_default, false, 2, null);
        }
    }
}
