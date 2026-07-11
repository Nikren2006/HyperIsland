package miui.systemui.controlcenter.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.controlcenter.R;

/* JADX INFO: loaded from: classes.dex */
public final class QsFlipSettingItemBinding implements ViewBinding {

    @NonNull
    public final ImageView ivIcon;

    @NonNull
    public final CardView ivIconCardView;

    @NonNull
    public final ImageView ivOperation;

    @NonNull
    private final RelativeLayout rootView;

    @NonNull
    public final TextView tvTitle;

    private QsFlipSettingItemBinding(@NonNull RelativeLayout relativeLayout, @NonNull ImageView imageView, @NonNull CardView cardView, @NonNull ImageView imageView2, @NonNull TextView textView) {
        this.rootView = relativeLayout;
        this.ivIcon = imageView;
        this.ivIconCardView = cardView;
        this.ivOperation = imageView2;
        this.tvTitle = textView;
    }

    @NonNull
    public static QsFlipSettingItemBinding bind(@NonNull View view) {
        int i2 = R.id.iv_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
        if (imageView != null) {
            i2 = R.id.iv_icon_cardView;
            CardView cardView = (CardView) ViewBindings.findChildViewById(view, i2);
            if (cardView != null) {
                i2 = R.id.iv_operation;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i2);
                if (imageView2 != null) {
                    i2 = R.id.tv_title;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
                    if (textView != null) {
                        return new QsFlipSettingItemBinding((RelativeLayout) view, imageView, cardView, imageView2, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static QsFlipSettingItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static QsFlipSettingItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.qs_flip_setting_item, viewGroup, false);
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
