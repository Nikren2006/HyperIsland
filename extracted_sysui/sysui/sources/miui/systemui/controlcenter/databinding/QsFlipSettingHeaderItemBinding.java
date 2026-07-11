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
public final class QsFlipSettingHeaderItemBinding implements ViewBinding {

    @NonNull
    public final View headerDivider;

    @NonNull
    private final LinearLayout rootView;

    @NonNull
    public final TextView tvSubTitle;

    @NonNull
    public final TextView tvTitle;

    private QsFlipSettingHeaderItemBinding(@NonNull LinearLayout linearLayout, @NonNull View view, @NonNull TextView textView, @NonNull TextView textView2) {
        this.rootView = linearLayout;
        this.headerDivider = view;
        this.tvSubTitle = textView;
        this.tvTitle = textView2;
    }

    @NonNull
    public static QsFlipSettingHeaderItemBinding bind(@NonNull View view) {
        int i2 = R.id.header_divider;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i2);
        if (viewFindChildViewById != null) {
            i2 = R.id.tv_sub_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
            if (textView != null) {
                i2 = R.id.tv_title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i2);
                if (textView2 != null) {
                    return new QsFlipSettingHeaderItemBinding((LinearLayout) view, viewFindChildViewById, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static QsFlipSettingHeaderItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static QsFlipSettingHeaderItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.qs_flip_setting_header_item, viewGroup, false);
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
