package miui.systemui.devicecontrols.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsStructurePageBinding implements ViewBinding {

    @NonNull
    public final RecyclerView listAll;

    @NonNull
    private final RecyclerView rootView;

    private ControlsStructurePageBinding(@NonNull RecyclerView recyclerView, @NonNull RecyclerView recyclerView2) {
        this.rootView = recyclerView;
        this.listAll = recyclerView2;
    }

    @NonNull
    public static ControlsStructurePageBinding bind(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        RecyclerView recyclerView = (RecyclerView) view;
        return new ControlsStructurePageBinding(recyclerView, recyclerView);
    }

    @NonNull
    public static ControlsStructurePageBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ControlsStructurePageBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.controls_structure_page, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public RecyclerView getRoot() {
        return this.rootView;
    }
}
