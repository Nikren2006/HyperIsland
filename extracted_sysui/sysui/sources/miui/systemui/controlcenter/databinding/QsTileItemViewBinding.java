package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;
import miui.systemui.controlcenter.qs.tileview.QSTileItemView;
import miui.systemui.controlcenter.widget.QSMarkImageView;
import miui.systemui.widget.FrameLayout;
import miui.systemui.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class QsTileItemViewBinding implements ViewBinding {

    @NonNull
    public final FrameLayout iconFrame;

    @NonNull
    public final QSMarkImageView mark;

    @NonNull
    public final QSTileItemView qsTile;

    @NonNull
    private final QSTileItemView rootView;

    @NonNull
    public final TextView tileLabel;

    private QsTileItemViewBinding(@NonNull QSTileItemView qSTileItemView, @NonNull FrameLayout frameLayout, @NonNull QSMarkImageView qSMarkImageView, @NonNull QSTileItemView qSTileItemView2, @NonNull TextView textView) {
        this.rootView = qSTileItemView;
        this.iconFrame = frameLayout;
        this.mark = qSMarkImageView;
        this.qsTile = qSTileItemView2;
        this.tileLabel = textView;
    }

    @NonNull
    public static QsTileItemViewBinding bind(@NonNull View view) {
        int i2 = R.id.icon_frame;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, i2);
        if (frameLayout != null) {
            i2 = R.id.mark;
            QSMarkImageView qSMarkImageView = (QSMarkImageView) ViewBindings.findChildViewById(view, i2);
            if (qSMarkImageView != null) {
                QSTileItemView qSTileItemView = (QSTileItemView) view;
                i2 = R.id.tile_label;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
                if (textView != null) {
                    return new QsTileItemViewBinding(qSTileItemView, frameLayout, qSMarkImageView, qSTileItemView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static QsTileItemViewBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static QsTileItemViewBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.qs_tile_item_view, viewGroup, false);
        if (z2) {
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }

    @Override // androidx.viewbinding.ViewBinding
    @NonNull
    public QSTileItemView getRoot() {
        return this.rootView;
    }
}
