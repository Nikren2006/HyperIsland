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
public final class ControlsFavoriteSenseItemBinding implements ViewBinding {

    @NonNull
    public final ImageView icon;

    @NonNull
    public final LinearLayout llStatus;

    @NonNull
    private final ConstraintLayout rootView;

    @NonNull
    public final TextView status;

    @NonNull
    public final TextView subtitle;

    @NonNull
    public final TextView title;

    @NonNull
    public final View vBorder;

    @NonNull
    public final View vRipple;

    private ControlsFavoriteSenseItemBinding(@NonNull ConstraintLayout constraintLayout, @NonNull ImageView imageView, @NonNull LinearLayout linearLayout, @NonNull TextView textView, @NonNull TextView textView2, @NonNull TextView textView3, @NonNull View view, @NonNull View view2) {
        this.rootView = constraintLayout;
        this.icon = imageView;
        this.llStatus = linearLayout;
        this.status = textView;
        this.subtitle = textView2;
        this.title = textView3;
        this.vBorder = view;
        this.vRipple = view2;
    }

    @NonNull
    public static ControlsFavoriteSenseItemBinding bind(@NonNull View view) {
        View viewFindChildViewById;
        View viewFindChildViewById2;
        int i2 = R.id.icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
        if (imageView != null) {
            i2 = R.id.ll_status;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
            if (linearLayout != null) {
                i2 = R.id.status;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
                if (textView != null) {
                    i2 = R.id.subtitle;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i2);
                    if (textView2 != null) {
                        i2 = R.id.title;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, i2);
                        if (textView3 != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i2 = R.id.v_border))) != null && (viewFindChildViewById2 = ViewBindings.findChildViewById(view, (i2 = R.id.v_ripple))) != null) {
                            return new ControlsFavoriteSenseItemBinding((ConstraintLayout) view, imageView, linearLayout, textView, textView2, textView3, viewFindChildViewById, viewFindChildViewById2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ControlsFavoriteSenseItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ControlsFavoriteSenseItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.controls_favorite_sense_item, viewGroup, false);
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
