package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public final class CompactQsCardContainerBinding implements ViewBinding {

    @NonNull
    public final QsTileItemViewBinding qsTile1;

    @NonNull
    public final QsTileItemViewBinding qsTile2;

    @NonNull
    public final LinearLayout qsTileContainer;

    @NonNull
    private final LinearLayout rootView;

    private CompactQsCardContainerBinding(@NonNull LinearLayout linearLayout, @NonNull QsTileItemViewBinding qsTileItemViewBinding, @NonNull QsTileItemViewBinding qsTileItemViewBinding2, @NonNull LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.qsTile1 = qsTileItemViewBinding;
        this.qsTile2 = qsTileItemViewBinding2;
        this.qsTileContainer = linearLayout2;
    }

    @NonNull
    public static CompactQsCardContainerBinding bind(@NonNull View view) {
        int i2 = R.id.qs_tile1;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i2);
        if (viewFindChildViewById != null) {
            QsTileItemViewBinding qsTileItemViewBindingBind = QsTileItemViewBinding.bind(viewFindChildViewById);
            int i3 = R.id.qs_tile2;
            View viewFindChildViewById2 = ViewBindings.findChildViewById(view, i3);
            if (viewFindChildViewById2 != null) {
                LinearLayout linearLayout = (LinearLayout) view;
                return new CompactQsCardContainerBinding(linearLayout, qsTileItemViewBindingBind, QsTileItemViewBinding.bind(viewFindChildViewById2), linearLayout);
            }
            i2 = i3;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static CompactQsCardContainerBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static CompactQsCardContainerBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.compact_qs_card_container, viewGroup, false);
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
