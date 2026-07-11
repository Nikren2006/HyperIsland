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
import miui.systemui.controlcenter.widget.SlidingButton;

/* JADX INFO: loaded from: classes.dex */
public final class DetailHeaderBinding implements ViewBinding {

    @NonNull
    public final LinearLayout detailHeader;

    @NonNull
    private final LinearLayout rootView;

    @NonNull
    public final TextView title;

    @NonNull
    public final SlidingButton toggle;

    private DetailHeaderBinding(@NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout2, @NonNull TextView textView, @NonNull SlidingButton slidingButton) {
        this.rootView = linearLayout;
        this.detailHeader = linearLayout2;
        this.title = textView;
        this.toggle = slidingButton;
    }

    @NonNull
    public static DetailHeaderBinding bind(@NonNull View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i2 = R.id.title;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
        if (textView != null) {
            i2 = android.R.id.toggle;
            SlidingButton slidingButton = (SlidingButton) ViewBindings.findChildViewById(view, android.R.id.toggle);
            if (slidingButton != null) {
                return new DetailHeaderBinding(linearLayout, linearLayout, textView, slidingButton);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DetailHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DetailHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.detail_header, viewGroup, false);
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
