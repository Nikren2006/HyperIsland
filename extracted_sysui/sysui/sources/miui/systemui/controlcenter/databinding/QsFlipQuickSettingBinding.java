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
import miuix.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class QsFlipQuickSettingBinding implements ViewBinding {

    @NonNull
    public final RecyclerView flipQsRecyclerView;

    @NonNull
    private final FrameLayout rootView;

    private QsFlipQuickSettingBinding(@NonNull FrameLayout frameLayout, @NonNull RecyclerView recyclerView) {
        this.rootView = frameLayout;
        this.flipQsRecyclerView = recyclerView;
    }

    @NonNull
    public static QsFlipQuickSettingBinding bind(@NonNull View view) {
        int i2 = R.id.flip_qs_recycler_view;
        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i2);
        if (recyclerView != null) {
            return new QsFlipQuickSettingBinding((FrameLayout) view, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static QsFlipQuickSettingBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static QsFlipQuickSettingBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.qs_flip_quick_setting, viewGroup, false);
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
