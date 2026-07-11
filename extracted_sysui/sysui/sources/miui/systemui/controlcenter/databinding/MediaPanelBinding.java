package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.widget.MaxHeightFrameLayout;
import miui.systemui.widget.FrameLayout;
import miui.systemui.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public final class MediaPanelBinding implements ViewBinding {

    @NonNull
    public final MaxHeightFrameLayout mediaContainer;

    @NonNull
    public final LinearLayout mediaContent;

    @NonNull
    public final FrameLayout mediaPanelBg;

    @NonNull
    private final MaxHeightFrameLayout rootView;

    private MediaPanelBinding(@NonNull MaxHeightFrameLayout maxHeightFrameLayout, @NonNull MaxHeightFrameLayout maxHeightFrameLayout2, @NonNull LinearLayout linearLayout, @NonNull FrameLayout frameLayout) {
        this.rootView = maxHeightFrameLayout;
        this.mediaContainer = maxHeightFrameLayout2;
        this.mediaContent = linearLayout;
        this.mediaPanelBg = frameLayout;
    }

    @NonNull
    public static MediaPanelBinding bind(@NonNull View view) {
        MaxHeightFrameLayout maxHeightFrameLayout = (MaxHeightFrameLayout) view;
        int i2 = R.id.media_content;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
        if (linearLayout != null) {
            i2 = R.id.media_panel_bg;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i2);
            if (frameLayout != null) {
                return new MediaPanelBinding(maxHeightFrameLayout, maxHeightFrameLayout, linearLayout, frameLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static MediaPanelBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static MediaPanelBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.media_panel, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public MaxHeightFrameLayout getRoot() {
        return this.rootView;
    }
}
