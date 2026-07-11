package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.widget.ConstraintLayout;

/* JADX INFO: loaded from: classes.dex */
public final class DeviceControlsEntryBinding implements ViewBinding {

    @NonNull
    public final ImageView entryIcon;

    @NonNull
    public final TextView entryTitle;

    @NonNull
    private final ConstraintLayout rootView;

    private DeviceControlsEntryBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ImageView imageView, @NonNull TextView textView) {
        this.rootView = constraintLayout;
        this.entryIcon = imageView;
        this.entryTitle = textView;
    }

    @NonNull
    public static DeviceControlsEntryBinding bind(@NonNull View view) {
        int i2 = R.id.entry_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
        if (imageView != null) {
            i2 = R.id.entry_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
            if (textView != null) {
                return new DeviceControlsEntryBinding((ConstraintLayout) view, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DeviceControlsEntryBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DeviceControlsEntryBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.device_controls_entry, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public ConstraintLayout getRoot() {
        return this.rootView;
    }
}
