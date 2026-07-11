package miui.systemui.devicecontrols.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsHomeTitleBinding implements ViewBinding {

    @NonNull
    private final TextView rootView;

    private ControlsHomeTitleBinding(@NonNull TextView textView) {
        this.rootView = textView;
    }

    @NonNull
    public static ControlsHomeTitleBinding bind(@NonNull View view) {
        if (view != null) {
            return new ControlsHomeTitleBinding((TextView) view);
        }
        throw new NullPointerException("rootView");
    }

    @NonNull
    public static ControlsHomeTitleBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ControlsHomeTitleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.controls_home_title, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public TextView getRoot() {
        return this.rootView;
    }
}
