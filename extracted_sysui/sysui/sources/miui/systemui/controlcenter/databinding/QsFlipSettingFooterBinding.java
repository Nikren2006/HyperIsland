package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;

/* JADX INFO: loaded from: classes.dex */
public final class QsFlipSettingFooterBinding implements ViewBinding {

    @NonNull
    public final View footerDivider;

    @NonNull
    private final LinearLayout rootView;

    @NonNull
    public final TextView tvFooterTitle;

    private QsFlipSettingFooterBinding(@NonNull LinearLayout linearLayout, @NonNull View view, @NonNull TextView textView) {
        this.rootView = linearLayout;
        this.footerDivider = view;
        this.tvFooterTitle = textView;
    }

    @NonNull
    public static QsFlipSettingFooterBinding bind(@NonNull View view) {
        int i2 = R.id.footer_divider;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i2);
        if (viewFindChildViewById != null) {
            i2 = R.id.tv_footer_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
            if (textView != null) {
                return new QsFlipSettingFooterBinding((LinearLayout) view, viewFindChildViewById, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static QsFlipSettingFooterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static QsFlipSettingFooterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.qs_flip_setting_footer, viewGroup, false);
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
