package miui.systemui.controlcenter.panel.main.devicecenter.devices;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import kotlin.jvm.functions.Function1;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.databinding.DeviceCenterOtherItemBinding;
import miui.systemui.util.CommonUtils;
import miui.systemui.widget.ConstraintLayout;

/* JADX INFO: loaded from: classes.dex */
public final class OtherDeviceViewHolder extends DeviceViewHolder {
    private final String TAG;
    private final DeviceCenterOtherItemBinding binding;
    private Function1 clickAction;

    /* JADX WARN: Illegal instructions before constructor call */
    public OtherDeviceViewHolder(DeviceCenterOtherItemBinding binding) {
        kotlin.jvm.internal.n.g(binding, "binding");
        ConstraintLayout root = binding.getRoot();
        kotlin.jvm.internal.n.f(root, "getRoot(...)");
        super(root);
        this.binding = binding;
        this.TAG = "OtherDeviceViewHolder";
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.controlcenter.panel.main.devicecenter.devices.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OtherDeviceViewHolder._init_$lambda$0(this.f5372a, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0(OtherDeviceViewHolder this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Function1 function1 = this$0.clickAction;
        if (function1 != null) {
            function1.invoke(view);
        }
    }

    public final void bindDeviceInfo(DevicesServiceInfo devicesServiceInfo, Function1 clickAction) {
        kotlin.jvm.internal.n.g(devicesServiceInfo, "devicesServiceInfo");
        kotlin.jvm.internal.n.g(clickAction, "clickAction");
        this.clickAction = clickAction;
        Drawable drawableLoadNormalIcon = devicesServiceInfo.loadNormalIcon();
        if (drawableLoadNormalIcon == null) {
            this.binding.icon.setVisibility(4);
        } else {
            this.binding.icon.setVisibility(0);
            ImageView imageView = this.binding.icon;
            Drawable.ConstantState constantState = drawableLoadNormalIcon.getConstantState();
            imageView.setImageDrawable(constantState != null ? constantState.newDrawable() : null);
        }
        this.binding.title.setText(devicesServiceInfo.loadLabel());
    }

    public final DeviceCenterOtherItemBinding getBinding() {
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
    }
}
