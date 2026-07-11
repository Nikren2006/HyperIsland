package miui.systemui.devicecontrols.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.devicecontrols.R;
import miui.systemui.devicecontrols.ui.widget.SpringBackLayout;
import miui.systemui.widget.RelativeLayout;
import miuix.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public final class FavoriteControlsBinding implements ViewBinding {

    @NonNull
    public final Button btAddDevice;

    @NonNull
    public final SpringBackLayout controlsListContainer;

    @NonNull
    public final ControlsWithFavoritesHeaderBinding controlsWithFavoritesHeader;

    @NonNull
    public final LinearLayout llEmpty;

    @NonNull
    public final RelativeLayout llRoot;

    @NonNull
    private final RelativeLayout rootView;

    @NonNull
    public final RecyclerView rvControlsList;

    @NonNull
    public final TextView tvAddDevice;

    @NonNull
    public final TextView tvLoading;

    private FavoriteControlsBinding(@NonNull RelativeLayout relativeLayout, @NonNull Button button, @NonNull SpringBackLayout springBackLayout, @NonNull ControlsWithFavoritesHeaderBinding controlsWithFavoritesHeaderBinding, @NonNull LinearLayout linearLayout, @NonNull RelativeLayout relativeLayout2, @NonNull RecyclerView recyclerView, @NonNull TextView textView, @NonNull TextView textView2) {
        this.rootView = relativeLayout;
        this.btAddDevice = button;
        this.controlsListContainer = springBackLayout;
        this.controlsWithFavoritesHeader = controlsWithFavoritesHeaderBinding;
        this.llEmpty = linearLayout;
        this.llRoot = relativeLayout2;
        this.rvControlsList = recyclerView;
        this.tvAddDevice = textView;
        this.tvLoading = textView2;
    }

    @NonNull
    public static FavoriteControlsBinding bind(@NonNull View view) {
        View viewFindChildViewById;
        int i2 = R.id.bt_add_device;
        Button button = (Button) ViewBindings.findChildViewById(view, i2);
        if (button != null) {
            i2 = R.id.controls_list_container;
            SpringBackLayout springBackLayout = (SpringBackLayout) ViewBindings.findChildViewById(view, i2);
            if (springBackLayout != null && (viewFindChildViewById = ViewBindings.findChildViewById(view, (i2 = R.id.controls_with_favorites_header))) != null) {
                ControlsWithFavoritesHeaderBinding controlsWithFavoritesHeaderBindingBind = ControlsWithFavoritesHeaderBinding.bind(viewFindChildViewById);
                i2 = R.id.ll_empty;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
                if (linearLayout != null) {
                    RelativeLayout relativeLayout = (RelativeLayout) view;
                    i2 = R.id.rv_controls_list;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, i2);
                    if (recyclerView != null) {
                        i2 = R.id.tv_add_device;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
                        if (textView != null) {
                            i2 = R.id.tv_loading;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i2);
                            if (textView2 != null) {
                                return new FavoriteControlsBinding(relativeLayout, button, springBackLayout, controlsWithFavoritesHeaderBindingBind, linearLayout, relativeLayout, recyclerView, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static FavoriteControlsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static FavoriteControlsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.favorite_controls, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public RelativeLayout getRoot() {
        return this.rootView;
    }
}
