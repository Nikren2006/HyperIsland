package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.widget.MainPanelRecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class MainPanelBinding implements ViewBinding {

    @NonNull
    public final MainPanelRecyclerView mainPanel;

    @NonNull
    private final MainPanelRecyclerView rootView;

    private MainPanelBinding(@NonNull MainPanelRecyclerView mainPanelRecyclerView, @NonNull MainPanelRecyclerView mainPanelRecyclerView2) {
        this.rootView = mainPanelRecyclerView;
        this.mainPanel = mainPanelRecyclerView2;
    }

    @NonNull
    public static MainPanelBinding bind(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        MainPanelRecyclerView mainPanelRecyclerView = (MainPanelRecyclerView) view;
        return new MainPanelBinding(mainPanelRecyclerView, mainPanelRecyclerView);
    }

    @NonNull
    public static MainPanelBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static MainPanelBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.main_panel, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public MainPanelRecyclerView getRoot() {
        return this.rootView;
    }
}
