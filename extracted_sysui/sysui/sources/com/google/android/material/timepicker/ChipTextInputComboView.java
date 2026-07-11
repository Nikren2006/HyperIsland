package com.google.android.material.timepicker;

import H.j;
import H.n;
import android.content.Context;
import android.content.res.Configuration;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;
import t.g;

/* JADX INFO: loaded from: classes2.dex */
class ChipTextInputComboView extends FrameLayout implements Checkable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Chip f2240a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextInputLayout f2241b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final EditText f2242c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextWatcher f2243d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f2244e;

    public class b extends j {
        public b() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(editable)) {
                ChipTextInputComboView.this.f2240a.setText(ChipTextInputComboView.this.c("00"));
                return;
            }
            String strC = ChipTextInputComboView.this.c(editable);
            Chip chip = ChipTextInputComboView.this.f2240a;
            if (TextUtils.isEmpty(strC)) {
                strC = ChipTextInputComboView.this.c("00");
            }
            chip.setText(strC);
        }
    }

    public ChipTextInputComboView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final String c(CharSequence charSequence) {
        return e.q(getResources(), charSequence);
    }

    public final void d() {
        this.f2242c.setImeHintLocales(getContext().getResources().getConfiguration().getLocales());
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f2240a.isChecked();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        d();
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z2) {
        this.f2240a.setChecked(z2);
        this.f2242c.setVisibility(z2 ? 0 : 4);
        this.f2240a.setVisibility(z2 ? 8 : 0);
        if (isChecked()) {
            n.k(this.f2242c, false);
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.f2240a.setOnClickListener(onClickListener);
    }

    @Override // android.view.View
    public void setTag(int i2, Object obj) {
        this.f2240a.setTag(i2, obj);
    }

    @Override // android.widget.Checkable
    public void toggle() {
        this.f2240a.toggle();
    }

    public ChipTextInputComboView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        Chip chip = (Chip) layoutInflaterFrom.inflate(g.f6643g, (ViewGroup) this, false);
        this.f2240a = chip;
        chip.setAccessibilityClassName("android.view.View");
        TextInputLayout textInputLayout = (TextInputLayout) layoutInflaterFrom.inflate(g.f6644h, (ViewGroup) this, false);
        this.f2241b = textInputLayout;
        EditText editText = textInputLayout.getEditText();
        this.f2242c = editText;
        editText.setVisibility(4);
        b bVar = new b();
        this.f2243d = bVar;
        editText.addTextChangedListener(bVar);
        d();
        addView(chip);
        addView(textInputLayout);
        this.f2244e = (TextView) findViewById(t.e.f6623n);
        editText.setId(ViewCompat.generateViewId());
        ViewCompat.setLabelFor(this.f2244e, editText.getId());
        editText.setSaveEnabled(false);
        editText.setLongClickable(false);
    }
}
