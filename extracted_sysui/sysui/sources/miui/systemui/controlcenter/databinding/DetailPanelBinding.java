package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.widget.DetailPanelMoreButtonView;
import miui.systemui.controlcenter.widget.MaxHeightLinearLayout;
import miui.systemui.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public final class DetailPanelBinding implements ViewBinding {

    @NonNull
    public final FrameLayout content;

    @NonNull
    public final MaxHeightLinearLayout detailContainer;

    @NonNull
    public final DetailHeaderBinding detailHeader;

    @NonNull
    public final DetailPanelMoreButtonView moreButton;

    @NonNull
    private final MaxHeightLinearLayout rootView;

    @NonNull
    public final LinearLayout scaleContent;

    private DetailPanelBinding(@NonNull MaxHeightLinearLayout maxHeightLinearLayout, @NonNull FrameLayout frameLayout, @NonNull MaxHeightLinearLayout maxHeightLinearLayout2, @NonNull DetailHeaderBinding detailHeaderBinding, @NonNull DetailPanelMoreButtonView detailPanelMoreButtonView, @NonNull LinearLayout linearLayout) {
        this.rootView = maxHeightLinearLayout;
        this.content = frameLayout;
        this.detailContainer = maxHeightLinearLayout2;
        this.detailHeader = detailHeaderBinding;
        this.moreButton = detailPanelMoreButtonView;
        this.scaleContent = linearLayout;
    }

    @NonNull
    public static DetailPanelBinding bind(@NonNull View view) {
        int i2 = R.id.content;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i2);
        if (frameLayout != null) {
            MaxHeightLinearLayout maxHeightLinearLayout = (MaxHeightLinearLayout) view;
            i2 = R.id.detail_header;
            View viewFindChildViewById = ViewBindings.findChildViewById(view, i2);
            if (viewFindChildViewById != null) {
                DetailHeaderBinding detailHeaderBindingBind = DetailHeaderBinding.bind(viewFindChildViewById);
                i2 = R.id.more_button;
                DetailPanelMoreButtonView detailPanelMoreButtonView = (DetailPanelMoreButtonView) ViewBindings.findChildViewById(view, i2);
                if (detailPanelMoreButtonView != null) {
                    i2 = R.id.scale_content;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
                    if (linearLayout != null) {
                        return new DetailPanelBinding(maxHeightLinearLayout, frameLayout, maxHeightLinearLayout, detailHeaderBindingBind, detailPanelMoreButtonView, linearLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static DetailPanelBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static DetailPanelBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.detail_panel, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public MaxHeightLinearLayout getRoot() {
        return this.rootView;
    }
}
