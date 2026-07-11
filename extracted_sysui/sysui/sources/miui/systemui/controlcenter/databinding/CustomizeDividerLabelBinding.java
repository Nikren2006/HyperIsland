package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class CustomizeDividerLabelBinding implements ViewBinding {

    @NonNull
    public final TextView label;

    @NonNull
    public final FrameLayout labelContainer;

    @NonNull
    private final FrameLayout rootView;

    private CustomizeDividerLabelBinding(@NonNull FrameLayout frameLayout, @NonNull TextView textView, @NonNull FrameLayout frameLayout2) {
        this.rootView = frameLayout;
        this.label = textView;
        this.labelContainer = frameLayout2;
    }

    @NonNull
    public static CustomizeDividerLabelBinding bind(@NonNull View view) {
        int i2 = R.id.label;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
        if (textView == null) {
            throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
        }
        FrameLayout frameLayout = (FrameLayout) view;
        return new CustomizeDividerLabelBinding(frameLayout, textView, frameLayout);
    }

    @NonNull
    public static CustomizeDividerLabelBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static CustomizeDividerLabelBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.customize_divider_label, viewGroup, false);
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
