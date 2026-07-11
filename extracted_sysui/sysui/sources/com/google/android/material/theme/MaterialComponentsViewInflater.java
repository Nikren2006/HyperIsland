package com.google.android.material.theme;

import A.a;
import Q.u;
import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.button.MaterialButton;

/* JADX INFO: loaded from: classes2.dex */
public class MaterialComponentsViewInflater extends AppCompatViewInflater {
    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatAutoCompleteTextView createAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new u(context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatButton createButton(Context context, AttributeSet attributeSet) {
        return new MaterialButton(context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatCheckBox createCheckBox(Context context, AttributeSet attributeSet) {
        return new a(context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatRadioButton createRadioButton(Context context, AttributeSet attributeSet) {
        return new K.a(context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatTextView createTextView(Context context, AttributeSet attributeSet) {
        return new R.a(context, attributeSet);
    }
}
