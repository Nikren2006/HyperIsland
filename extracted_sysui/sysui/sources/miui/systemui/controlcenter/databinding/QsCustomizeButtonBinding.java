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
public final class QsCustomizeButtonBinding implements ViewBinding {

    @NonNull
    public final LinearLayout qsCustomizeButton;

    @NonNull
    private final LinearLayout rootView;

    @NonNull
    public final TextView text;

    @NonNull
    public final miui.systemui.widget.LinearLayout touchContainer;

    private QsCustomizeButtonBinding(@NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout2, @NonNull TextView textView, @NonNull miui.systemui.widget.LinearLayout linearLayout3) {
        this.rootView = linearLayout;
        this.qsCustomizeButton = linearLayout2;
        this.text = textView;
        this.touchContainer = linearLayout3;
    }

    @NonNull
    public static QsCustomizeButtonBinding bind(@NonNull View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        int i2 = R.id.text;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
        if (textView != null) {
            i2 = R.id.touch_container;
            miui.systemui.widget.LinearLayout linearLayout2 = (miui.systemui.widget.LinearLayout) ViewBindings.findChildViewById(view, i2);
            if (linearLayout2 != null) {
                return new QsCustomizeButtonBinding(linearLayout, linearLayout, textView, linearLayout2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static QsCustomizeButtonBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static QsCustomizeButtonBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.qs_customize_button, viewGroup, false);
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
