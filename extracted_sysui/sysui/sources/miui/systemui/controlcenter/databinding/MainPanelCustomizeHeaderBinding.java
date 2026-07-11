package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.widget.ConstraintLayout;
import miui.systemui.widget.FrameLayout;
import miui.systemui.widget.ImageView;
import miui.systemui.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class MainPanelCustomizeHeaderBinding implements ViewBinding {

    @NonNull
    public final ImageView completeButton;

    @NonNull
    public final ConstraintLayout customizeHeader;

    @NonNull
    public final FrameLayout customizeHeaderContainer;

    @NonNull
    public final TextView customizeTitle;

    @NonNull
    private final FrameLayout rootView;

    private MainPanelCustomizeHeaderBinding(@NonNull FrameLayout frameLayout, @NonNull ImageView imageView, @NonNull ConstraintLayout constraintLayout, @NonNull FrameLayout frameLayout2, @NonNull TextView textView) {
        this.rootView = frameLayout;
        this.completeButton = imageView;
        this.customizeHeader = constraintLayout;
        this.customizeHeaderContainer = frameLayout2;
        this.customizeTitle = textView;
    }

    @NonNull
    public static MainPanelCustomizeHeaderBinding bind(@NonNull View view) {
        int i2 = R.id.complete_button;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
        if (imageView != null) {
            i2 = R.id.customize_header;
            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i2);
            if (constraintLayout != null) {
                FrameLayout frameLayout = (FrameLayout) view;
                i2 = R.id.customize_title;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
                if (textView != null) {
                    return new MainPanelCustomizeHeaderBinding(frameLayout, imageView, constraintLayout, frameLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static MainPanelCustomizeHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static MainPanelCustomizeHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.main_panel_customize_header, viewGroup, false);
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
