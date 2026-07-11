package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.widget.ConstraintLayout;
import miui.systemui.widget.FrameLayout;
import miui.systemui.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class MainPanelMsgHeaderBinding implements ViewBinding {

    @NonNull
    public final TextView headerMsgA;

    @NonNull
    public final TextView headerMsgB;

    @NonNull
    public final ConstraintLayout msgHeader;

    @NonNull
    public final FrameLayout msgHeaderContainer;

    @NonNull
    public final Space referenceSpace;

    @NonNull
    private final FrameLayout rootView;

    private MainPanelMsgHeaderBinding(@NonNull FrameLayout frameLayout, @NonNull TextView textView, @NonNull TextView textView2, @NonNull ConstraintLayout constraintLayout, @NonNull FrameLayout frameLayout2, @NonNull Space space) {
        this.rootView = frameLayout;
        this.headerMsgA = textView;
        this.headerMsgB = textView2;
        this.msgHeader = constraintLayout;
        this.msgHeaderContainer = frameLayout2;
        this.referenceSpace = space;
    }

    @NonNull
    public static MainPanelMsgHeaderBinding bind(@NonNull View view) {
        int i2 = R.id.header_msg_a;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
        if (textView != null) {
            i2 = R.id.header_msg_b;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i2);
            if (textView2 != null) {
                i2 = R.id.msg_header;
                ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, i2);
                if (constraintLayout != null) {
                    FrameLayout frameLayout = (FrameLayout) view;
                    i2 = R.id.reference_space;
                    Space space = (Space) ViewBindings.findChildViewById(view, i2);
                    if (space != null) {
                        return new MainPanelMsgHeaderBinding(frameLayout, textView, textView2, constraintLayout, frameLayout, space);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static MainPanelMsgHeaderBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static MainPanelMsgHeaderBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.main_panel_msg_header, viewGroup, false);
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
