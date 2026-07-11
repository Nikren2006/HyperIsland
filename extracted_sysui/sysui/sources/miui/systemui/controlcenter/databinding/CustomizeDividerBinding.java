package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class CustomizeDividerBinding implements ViewBinding {

    @NonNull
    public final View divider;

    @NonNull
    public final FrameLayout dividerContainer;

    @NonNull
    private final FrameLayout rootView;

    private CustomizeDividerBinding(@NonNull FrameLayout frameLayout, @NonNull View view, @NonNull FrameLayout frameLayout2) {
        this.rootView = frameLayout;
        this.divider = view;
        this.dividerContainer = frameLayout2;
    }

    @NonNull
    public static CustomizeDividerBinding bind(@NonNull View view) {
        int i2 = R.id.divider;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i2);
        if (viewFindChildViewById == null) {
            throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
        }
        FrameLayout frameLayout = (FrameLayout) view;
        return new CustomizeDividerBinding(frameLayout, viewFindChildViewById, frameLayout);
    }

    @NonNull
    public static CustomizeDividerBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static CustomizeDividerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.customize_divider, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public FrameLayout getRoot() {
        return this.rootView;
    }
}
