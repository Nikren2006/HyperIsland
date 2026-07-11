package miui.systemui.devicecontrols.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsHorizontalDividerWithEmptyBinding implements ViewBinding {

    @NonNull
    public final View divider;

    @NonNull
    public final View frame;

    @NonNull
    private final LinearLayout rootView;

    private ControlsHorizontalDividerWithEmptyBinding(@NonNull LinearLayout linearLayout, @NonNull View view, @NonNull View view2) {
        this.rootView = linearLayout;
        this.divider = view;
        this.frame = view2;
    }

    @NonNull
    public static ControlsHorizontalDividerWithEmptyBinding bind(@NonNull View view) {
        View viewFindChildViewById;
        int i2 = R.id.divider;
        View viewFindChildViewById2 = ViewBindings.findChildViewById(view, i2);
        if (viewFindChildViewById2 == null || (viewFindChildViewById = ViewBindings.findChildViewById(view, (i2 = R.id.frame))) == null) {
            throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
        }
        return new ControlsHorizontalDividerWithEmptyBinding((LinearLayout) view, viewFindChildViewById2, viewFindChildViewById);
    }

    @NonNull
    public static ControlsHorizontalDividerWithEmptyBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ControlsHorizontalDividerWithEmptyBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.controls_horizontal_divider_with_empty, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public LinearLayout getRoot() {
        return this.rootView;
    }
}
