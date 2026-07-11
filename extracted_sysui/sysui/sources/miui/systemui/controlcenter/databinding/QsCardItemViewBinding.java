package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.qs.tileview.QSCardItemView;
import miui.systemui.controlcenter.widget.FocusedTextView;
import miui.systemui.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public final class QsCardItemViewBinding implements ViewBinding {

    @NonNull
    public final QSCardItemView bigTile;

    @NonNull
    public final LinearLayout labelContainer;

    @NonNull
    private final QSCardItemView rootView;

    @NonNull
    public final LinearLayout scaleContent;

    @NonNull
    public final FocusedTextView status;

    @NonNull
    public final FocusedTextView title;

    private QsCardItemViewBinding(@NonNull QSCardItemView qSCardItemView, @NonNull QSCardItemView qSCardItemView2, @NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout2, @NonNull FocusedTextView focusedTextView, @NonNull FocusedTextView focusedTextView2) {
        this.rootView = qSCardItemView;
        this.bigTile = qSCardItemView2;
        this.labelContainer = linearLayout;
        this.scaleContent = linearLayout2;
        this.status = focusedTextView;
        this.title = focusedTextView2;
    }

    @NonNull
    public static QsCardItemViewBinding bind(@NonNull View view) {
        QSCardItemView qSCardItemView = (QSCardItemView) view;
        int i2 = R.id.label_container;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, i2);
        if (linearLayout != null) {
            i2 = R.id.scale_content;
            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, i2);
            if (linearLayout2 != null) {
                i2 = R.id.status;
                FocusedTextView focusedTextView = (FocusedTextView) ViewBindings.findChildViewById(view, i2);
                if (focusedTextView != null) {
                    i2 = R.id.title;
                    FocusedTextView focusedTextView2 = (FocusedTextView) ViewBindings.findChildViewById(view, i2);
                    if (focusedTextView2 != null) {
                        return new QsCardItemViewBinding(qSCardItemView, qSCardItemView, linearLayout, linearLayout2, focusedTextView, focusedTextView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static QsCardItemViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static QsCardItemViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.qs_card_item_view, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public QSCardItemView getRoot() {
        return this.rootView;
    }
}
