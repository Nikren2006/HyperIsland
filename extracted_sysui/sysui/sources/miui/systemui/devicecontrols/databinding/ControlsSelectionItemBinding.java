package miui.systemui.devicecontrols.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.devicecontrols.R;
import miui.systemui.widget.LinearLayout;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsSelectionItemBinding implements ViewBinding {

    @NonNull
    public final ImageView ivAppIcon;

    @NonNull
    public final ImageView ivSelectIcon;

    @NonNull
    private final LinearLayout rootView;

    @NonNull
    public final TextView tvAppLabel;

    @NonNull
    public final TextView tvStructure;

    private ControlsSelectionItemBinding(@NonNull LinearLayout linearLayout, @NonNull ImageView imageView, @NonNull ImageView imageView2, @NonNull TextView textView, @NonNull TextView textView2) {
        this.rootView = linearLayout;
        this.ivAppIcon = imageView;
        this.ivSelectIcon = imageView2;
        this.tvAppLabel = textView;
        this.tvStructure = textView2;
    }

    @NonNull
    public static ControlsSelectionItemBinding bind(@NonNull View view) {
        int i2 = R.id.iv_app_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
        if (imageView != null) {
            i2 = R.id.iv_select_icon;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i2);
            if (imageView2 != null) {
                i2 = R.id.tv_app_label;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
                if (textView != null) {
                    i2 = R.id.tv_structure;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i2);
                    if (textView2 != null) {
                        return new ControlsSelectionItemBinding((LinearLayout) view, imageView, imageView2, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ControlsSelectionItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ControlsSelectionItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.controls_selection_item, viewGroup, false);
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
