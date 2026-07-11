package miui.systemui.devicecontrols.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsDialogPinBinding implements ViewBinding {

    @NonNull
    public final EditText controlsPinInput;

    @NonNull
    public final CheckBox controlsPinUseAlpha;

    @NonNull
    private final LinearLayout rootView;

    private ControlsDialogPinBinding(@NonNull LinearLayout linearLayout, @NonNull EditText editText, @NonNull CheckBox checkBox) {
        this.rootView = linearLayout;
        this.controlsPinInput = editText;
        this.controlsPinUseAlpha = checkBox;
    }

    @NonNull
    public static ControlsDialogPinBinding bind(@NonNull View view) {
        int i2 = R.id.controls_pin_input;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, i2);
        if (editText != null) {
            i2 = R.id.controls_pin_use_alpha;
            CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, i2);
            if (checkBox != null) {
                return new ControlsDialogPinBinding((LinearLayout) view, editText, checkBox);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }

    @NonNull
    public static ControlsDialogPinBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    @NonNull
    public static ControlsDialogPinBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z2) {
        View viewInflate = layoutInflater.inflate(R.layout.controls_dialog_pin, viewGroup, false);
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
