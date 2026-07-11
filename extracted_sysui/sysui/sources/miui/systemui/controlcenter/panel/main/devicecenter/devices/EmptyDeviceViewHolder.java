package miui.systemui.controlcenter.panel.main.devicecenter.devices;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.databinding.DeviceCenterEmptyItemBinding;
import miui.systemui.util.CommonUtils;
import miui.systemui.widget.ConstraintLayout;

/* JADX INFO: loaded from: classes.dex */
public final class EmptyDeviceViewHolder extends DeviceViewHolder {
    private final DeviceCenterEmptyItemBinding binding;

    /* JADX WARN: Illegal instructions before constructor call */
    public EmptyDeviceViewHolder(DeviceCenterEmptyItemBinding binding) {
        kotlin.jvm.internal.n.g(binding, "binding");
        ConstraintLayout root = binding.getRoot();
        kotlin.jvm.internal.n.f(root, "getRoot(...)");
        super(root);
        this.binding = binding;
    }

    public final DeviceCenterEmptyItemBinding getBinding() {
        return this.binding;
    }

    @Override // miui.systemui.controlcenter.panel.main.devicecenter.devices.DeviceViewHolder
    public void onConfigurationChanged(int i2) {
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        if (configUtils.dimensionsChanged(i2)) {
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            View itemView = this.itemView;
            kotlin.jvm.internal.n.f(itemView, "itemView");
            CommonUtils.setLayoutHeight$default(commonUtils, itemView, this.itemView.getResources().getDimensionPixelSize(R.dimen.device_center_empty_item_height), false, 2, null);
            ImageView imageView = this.binding.icon;
            int dimensionPixelSize = this.itemView.getResources().getDimensionPixelSize(R.dimen.device_center_entry_icon_size);
            kotlin.jvm.internal.n.d(imageView);
            CommonUtils.setLayoutSize$default(commonUtils, imageView, dimensionPixelSize, dimensionPixelSize, false, 4, null);
            CommonUtils.setMarginStart$default(commonUtils, imageView, this.itemView.getResources().getDimensionPixelSize(R.dimen.device_center_entry_icon_margin_start), false, 2, null);
            TextView title = this.binding.title;
            kotlin.jvm.internal.n.f(title, "title");
            CommonUtils.setMargins$default(commonUtils, title, this.itemView.getResources().getDimensionPixelSize(R.dimen.device_center_entry_title_text_margin_start), 0, this.itemView.getResources().getDimensionPixelSize(R.dimen.device_center_entry_title_text_margin_end), 0, false, 26, null);
        }
        if (configUtils.textAppearanceChanged(i2)) {
            this.binding.title.setTextAppearance(R.style.TextAppearance_DeviceCenter_Title);
        }
        if (configUtils.colorsChanged(i2)) {
            this.binding.icon.setImageResource(R.drawable.ic_device_center_logo);
        }
        if (configUtils.textsChanged(i2)) {
            this.binding.title.setText(R.string.mi_smart_hub_entry_title);
        }
    }
}
