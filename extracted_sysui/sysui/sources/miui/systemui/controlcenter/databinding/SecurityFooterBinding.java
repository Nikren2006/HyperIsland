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
import miui.systemui.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public final class SecurityFooterBinding implements ViewBinding {

    @NonNull
    public final ImageView icon;

    @NonNull
    private final LinearLayout rootView;

    @NonNull
    public final LinearLayout securityFooter;

    @NonNull
    public final TextView securityMsg;

    private SecurityFooterBinding(@NonNull LinearLayout linearLayout, @NonNull ImageView imageView, @NonNull LinearLayout linearLayout2, @NonNull TextView textView) {
        this.rootView = linearLayout;
        this.icon = imageView;
        this.securityFooter = linearLayout2;
        this.securityMsg = textView;
    }

    @NonNull
    public static SecurityFooterBinding bind(@NonNull View view) {
        int i2 = R.id.icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
        if (imageView != null) {
            LinearLayout linearLayout = (LinearLayout) view;
            int i3 = R.id.security_msg;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i3);
            if (textView != null) {
                return new SecurityFooterBinding(linearLayout, imageView, linearLayout, textView);
            }
            i2 = i3;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static SecurityFooterBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static SecurityFooterBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.security_footer, viewGroup, false);
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
