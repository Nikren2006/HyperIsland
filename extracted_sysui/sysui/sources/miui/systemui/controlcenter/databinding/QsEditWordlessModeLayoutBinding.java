package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class QsEditWordlessModeLayoutBinding implements ViewBinding {

    @NonNull
    public final QsEditWordlessModeBinding qsEditWordlessMode;

    @NonNull
    public final FrameLayout qsWordlessMode;

    @NonNull
    private final FrameLayout rootView;

    private QsEditWordlessModeLayoutBinding(@NonNull FrameLayout frameLayout, @NonNull QsEditWordlessModeBinding qsEditWordlessModeBinding, @NonNull FrameLayout frameLayout2) {
        this.rootView = frameLayout;
        this.qsEditWordlessMode = qsEditWordlessModeBinding;
        this.qsWordlessMode = frameLayout2;
    }

    @NonNull
    public static QsEditWordlessModeLayoutBinding bind(@NonNull View view) {
        int i2 = R.id.qs_edit_wordless_mode;
        View viewFindChildViewById = ViewBindings.findChildViewById(view, i2);
        if (viewFindChildViewById != null) {
            QsEditWordlessModeBinding qsEditWordlessModeBindingBind = QsEditWordlessModeBinding.bind(viewFindChildViewById);
            int i3 = R.id.qs_wordless_mode;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i3);
            if (frameLayout != null) {
                return new QsEditWordlessModeLayoutBinding((FrameLayout) view, qsEditWordlessModeBindingBind, frameLayout);
            }
            i2 = i3;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static QsEditWordlessModeLayoutBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static QsEditWordlessModeLayoutBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.qs_edit_wordless_mode_layout, viewGroup, false);
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
