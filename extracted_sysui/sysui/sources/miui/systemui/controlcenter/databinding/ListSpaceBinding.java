package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import miui.systemui.controlcenter.R;

/* JADX INFO: loaded from: classes.dex */
public final class ListSpaceBinding implements ViewBinding {

    @NonNull
    public final Space listSpace;

    @NonNull
    private final Space rootView;

    private ListSpaceBinding(@NonNull Space space, @NonNull Space space2) {
        this.rootView = space;
        this.listSpace = space2;
    }

    @NonNull
    public static ListSpaceBinding bind(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        Space space = (Space) view;
        return new ListSpaceBinding(space, space);
    }

    @NonNull
    public static ListSpaceBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ListSpaceBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.list_space, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public Space getRoot() {
        return this.rootView;
    }
}
