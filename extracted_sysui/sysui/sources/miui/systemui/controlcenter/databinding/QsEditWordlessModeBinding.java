package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.widget.SlidingButton;
import miui.systemui.widget.ConstraintLayout;
import miui.systemui.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class QsEditWordlessModeBinding implements ViewBinding {

    @NonNull
    private final ConstraintLayout rootView;

    @NonNull
    public final SlidingButton toggle;

    @NonNull
    public final ConstraintLayout wordlessModeText;

    @NonNull
    public final TextView wordlessModeTitle;

    private QsEditWordlessModeBinding(@NonNull ConstraintLayout constraintLayout, @NonNull SlidingButton slidingButton, @NonNull ConstraintLayout constraintLayout2, @NonNull TextView textView) {
        this.rootView = constraintLayout;
        this.toggle = slidingButton;
        this.wordlessModeText = constraintLayout2;
        this.wordlessModeTitle = textView;
    }

    @NonNull
    public static QsEditWordlessModeBinding bind(@NonNull View view) {
        int i2 = R.id.toggle;
        SlidingButton slidingButton = (SlidingButton) ViewBindings.findChildViewById(view, i2);
        if (slidingButton != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            int i3 = R.id.wordless_mode_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, i3);
            if (textView != null) {
                return new QsEditWordlessModeBinding(constraintLayout, slidingButton, constraintLayout, textView);
            }
            i2 = i3;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static QsEditWordlessModeBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static QsEditWordlessModeBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.qs_edit_wordless_mode, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public ConstraintLayout getRoot() {
        return this.rootView;
    }
}
