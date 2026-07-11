package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.panel.main.devicecenter.customview.CustomRootView;
import miui.systemui.devicecenter.view.MLBatteryView;
import miui.systemui.widget.FrameLayout;
import miui.systemui.widget.ImageView;
import miui.systemui.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceCenterDeviceItemBinding implements ViewBinding {

    @NonNull
    public final MLBatteryView battery;

    @NonNull
    public final ImageButton cameraGlassesIndicator;

    @NonNull
    public final FrameLayout container;

    @NonNull
    public final ImageView fullIcon;

    @NonNull
    public final ImageView indicator;

    @NonNull
    private final CustomRootView rootView;

    @NonNull
    public final ImageView textIcon;

    @NonNull
    public final LinearLayout textIconContainer;

    private DeviceCenterDeviceItemBinding(@NonNull CustomRootView customRootView, @NonNull MLBatteryView mLBatteryView, @NonNull ImageButton imageButton, @NonNull FrameLayout frameLayout, @NonNull ImageView imageView, @NonNull ImageView imageView2, @NonNull ImageView imageView3, @NonNull LinearLayout linearLayout) {
        this.rootView = customRootView;
        this.battery = mLBatteryView;
        this.cameraGlassesIndicator = imageButton;
        this.container = frameLayout;
        this.fullIcon = imageView;
        this.indicator = imageView2;
        this.textIcon = imageView3;
        this.textIconContainer = linearLayout;
    }

    @NonNull
    public static DeviceCenterDeviceItemBinding bind(@NonNull View view) {
        int i2 = R.id.battery;
        MLBatteryView mLBatteryView = (MLBatteryView) ViewBindings.findChildViewById(view, i2);
        if (mLBatteryView != null) {
            i2 = R.id.camera_glasses_indicator;
            ImageButton imageButton = (ImageButton) ViewBindings.findChildViewById(view, i2);
            if (imageButton != null) {
                i2 = R.id.container;
                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i2);
                if (frameLayout != null) {
                    i2 = R.id.full_icon;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
                    if (imageView != null) {
                        i2 = R.id.indicator;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i2);
                        if (imageView2 != null) {
                            i2 = R.id.text_icon;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i2);
                            if (imageView3 != null) {
                                i2 = R.id.text_icon_container;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
                                if (linearLayout != null) {
                                    return new DeviceCenterDeviceItemBinding((CustomRootView) view, mLBatteryView, imageButton, frameLayout, imageView, imageView2, imageView3, linearLayout);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DeviceCenterDeviceItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DeviceCenterDeviceItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.device_center_device_item, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public CustomRootView getRoot() {
        return this.rootView;
    }
}
