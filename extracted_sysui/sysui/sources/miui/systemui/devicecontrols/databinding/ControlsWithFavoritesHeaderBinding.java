package miui.systemui.devicecontrols.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.devicecontrols.R;
import miui.systemui.widget.ConstraintLayout;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsWithFavoritesHeaderBinding implements ViewBinding {

    @NonNull
    public final ImageView ivAnchor;

    @NonNull
    public final ImageView ivBackIcon;

    @NonNull
    public final ImageView ivEditIcon;

    @NonNull
    public final LinearLayout llDropDown;

    @NonNull
    private final ConstraintLayout rootView;

    @NonNull
    public final TextView tvAppName;

    private ControlsWithFavoritesHeaderBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ImageView imageView, @NonNull ImageView imageView2, @NonNull ImageView imageView3, @NonNull LinearLayout linearLayout, @NonNull TextView textView) {
        this.rootView = constraintLayout;
        this.ivAnchor = imageView;
        this.ivBackIcon = imageView2;
        this.ivEditIcon = imageView3;
        this.llDropDown = linearLayout;
        this.tvAppName = textView;
    }

    @NonNull
    public static ControlsWithFavoritesHeaderBinding bind(@NonNull View view) {
        int i2 = R.id.iv_anchor;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
        if (imageView != null) {
            i2 = R.id.iv_back_icon;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i2);
            if (imageView2 != null) {
                i2 = R.id.iv_edit_icon;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, i2);
                if (imageView3 != null) {
                    i2 = R.id.ll_drop_down;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
                    if (linearLayout != null) {
                        i2 = R.id.tv_app_name;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
                        if (textView != null) {
                            return new ControlsWithFavoritesHeaderBinding((ConstraintLayout) view, imageView, imageView2, imageView3, linearLayout, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ControlsWithFavoritesHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ControlsWithFavoritesHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.controls_with_favorites_header, viewGroup, false);
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
