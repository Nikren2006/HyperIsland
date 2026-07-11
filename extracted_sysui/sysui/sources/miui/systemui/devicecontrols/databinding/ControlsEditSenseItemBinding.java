package miui.systemui.devicecontrols.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsEditSenseItemBinding implements ViewBinding {

    @NonNull
    public final ImageView icon;

    @NonNull
    public final RelativeLayout item;

    @NonNull
    public final ImageView mark;

    @NonNull
    private final FrameLayout rootView;

    @NonNull
    public final TextView subtitle;

    @NonNull
    public final TextView title;

    private ControlsEditSenseItemBinding(@NonNull FrameLayout frameLayout, @NonNull ImageView imageView, @NonNull RelativeLayout relativeLayout, @NonNull ImageView imageView2, @NonNull TextView textView, @NonNull TextView textView2) {
        this.rootView = frameLayout;
        this.icon = imageView;
        this.item = relativeLayout;
        this.mark = imageView2;
        this.subtitle = textView;
        this.title = textView2;
    }

    @NonNull
    public static ControlsEditSenseItemBinding bind(@NonNull View view) {
        int i2 = R.id.icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, i2);
        if (imageView != null) {
            i2 = R.id.item;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, i2);
            if (relativeLayout != null) {
                i2 = R.id.mark;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, i2);
                if (imageView2 != null) {
                    i2 = R.id.subtitle;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, i2);
                    if (textView != null) {
                        i2 = R.id.title;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, i2);
                        if (textView2 != null) {
                            return new ControlsEditSenseItemBinding((FrameLayout) view, imageView, relativeLayout, imageView2, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ControlsEditSenseItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ControlsEditSenseItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.controls_edit_sense_item, viewGroup, false);
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
