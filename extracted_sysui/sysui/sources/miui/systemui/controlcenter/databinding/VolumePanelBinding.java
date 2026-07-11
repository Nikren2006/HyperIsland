package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import miui.systemui.controlcenter.R;
import miui.systemui.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class VolumePanelBinding implements ViewBinding {

    @NonNull
    private final FrameLayout rootView;

    @NonNull
    public final FrameLayout volumeContainer;

    private VolumePanelBinding(@NonNull FrameLayout frameLayout, @NonNull FrameLayout frameLayout2) {
        this.rootView = frameLayout;
        this.volumeContainer = frameLayout2;
    }

    @NonNull
    public static VolumePanelBinding bind(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        FrameLayout frameLayout = (FrameLayout) view;
        return new VolumePanelBinding(frameLayout, frameLayout);
    }

    @NonNull
    public static VolumePanelBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static VolumePanelBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.volume_panel, viewGroup, false);
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
