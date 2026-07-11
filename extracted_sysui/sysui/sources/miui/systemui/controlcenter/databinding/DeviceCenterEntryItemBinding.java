package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryFrameLayout;
import miui.systemui.controlcenter.panel.main.devicecenter.entry.DeviceCenterEntryRecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceCenterEntryItemBinding implements ViewBinding {

    @NonNull
    public final DeviceCenterEntryRecyclerView list;

    @NonNull
    private final DeviceCenterEntryFrameLayout rootView;

    private DeviceCenterEntryItemBinding(@NonNull DeviceCenterEntryFrameLayout deviceCenterEntryFrameLayout, @NonNull DeviceCenterEntryRecyclerView deviceCenterEntryRecyclerView) {
        this.rootView = deviceCenterEntryFrameLayout;
        this.list = deviceCenterEntryRecyclerView;
    }

    @NonNull
    public static DeviceCenterEntryItemBinding bind(@NonNull View view) {
        int i2 = R.id.list;
        DeviceCenterEntryRecyclerView deviceCenterEntryRecyclerView = (DeviceCenterEntryRecyclerView) ViewBindings.findChildViewById(view, i2);
        if (deviceCenterEntryRecyclerView != null) {
            return new DeviceCenterEntryItemBinding((DeviceCenterEntryFrameLayout) view, deviceCenterEntryRecyclerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DeviceCenterEntryItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DeviceCenterEntryItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.device_center_entry_item, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public DeviceCenterEntryFrameLayout getRoot() {
        return this.rootView;
    }
}
