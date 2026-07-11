package miui.systemui.controlcenter.panel.main.devicecenter.devices;

import android.view.View;
import android.widget.Button;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.databinding.DeviceCenterDeviceItemBinding;
import miui.systemui.controlcenter.panel.main.devicecenter.customview.CustomRootView;
import miui.systemui.util.CommonUtils;
import miui.systemui.widget.FrameLayout;
import miui.systemui.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public final class DetailViewHolder extends DeviceViewHolder {
    private final DeviceCenterDeviceItemBinding binding;

    /* JADX WARN: Illegal instructions before constructor call */
    public DetailViewHolder(DeviceCenterDeviceItemBinding binding) {
        kotlin.jvm.internal.n.g(binding, "binding");
        CustomRootView root = binding.getRoot();
        kotlin.jvm.internal.n.f(root, "getRoot(...)");
        super(root);
        this.binding = binding;
    }

    public final DeviceCenterDeviceItemBinding getBinding() {
        return this.binding;
    }

    public final void onBind() {
        View view = this.itemView;
        view.setContentDescription(view.getResources().getString(R.string.mi_smart_hub_entry_title));
        this.binding.fullIcon.setVisibility(0);
        this.binding.battery.setVisibility(8);
        this.binding.indicator.setVisibility(8);
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        FrameLayout container = this.binding.container;
        kotlin.jvm.internal.n.f(container, "container");
        CommonUtils.setBackgroundResourceEx$default(commonUtils, container, R.drawable.ic_device_center_item_background_default, false, 2, null);
        this.binding.fullIcon.setImageResource(R.drawable.ic_device_center_detail_item);
        ViewCompat.setAccessibilityDelegate(this.itemView, new AccessibilityDelegateCompat() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.DetailViewHolder.onBind.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                kotlin.jvm.internal.n.g(host, "host");
                kotlin.jvm.internal.n.g(info, "info");
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(Button.class.getName());
            }
        });
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
        }
        if (configUtils.colorsChanged(i2)) {
            CommonUtils commonUtils2 = CommonUtils.INSTANCE;
            FrameLayout container2 = this.binding.container;
            kotlin.jvm.internal.n.f(container2, "container");
            CommonUtils.setBackgroundResourceEx$default(commonUtils2, container2, R.drawable.ic_device_center_item_background_default, false, 2, null);
            this.binding.fullIcon.setImageResource(R.drawable.ic_device_center_detail_item);
        }
    }
}
