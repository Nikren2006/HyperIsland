package miui.systemui.devicecontrols.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.devicecontrols.R;
import miui.systemui.widget.RelativeLayout;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsEditEmptyGuideLayoutBinding implements ViewBinding {

    @NonNull
    public final RelativeLayout rlEditEmptyGuide;

    @NonNull
    private final RelativeLayout rootView;

    @NonNull
    public final TextView tvGo;

    @NonNull
    public final TextView tvGuideText;

    private ControlsEditEmptyGuideLayoutBinding(@NonNull RelativeLayout relativeLayout, @NonNull RelativeLayout relativeLayout2, @NonNull TextView textView, @NonNull TextView textView2) {
        this.rootView = relativeLayout;
        this.rlEditEmptyGuide = relativeLayout2;
        this.tvGo = textView;
        this.tvGuideText = textView2;
    }

    @NonNull
    public static ControlsEditEmptyGuideLayoutBinding bind(@NonNull View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        int i2 = R.id.tv_go;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
        if (textView != null) {
            i2 = R.id.tv_guide_text;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i2);
            if (textView2 != null) {
                return new ControlsEditEmptyGuideLayoutBinding(relativeLayout, relativeLayout, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ControlsEditEmptyGuideLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ControlsEditEmptyGuideLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.controls_edit_empty_guide_layout, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
