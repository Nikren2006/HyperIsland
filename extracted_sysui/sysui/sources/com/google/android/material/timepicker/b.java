package com.google.android.material.timepicker;

import android.text.InputFilter;
import android.text.Spanned;

/* JADX INFO: loaded from: classes2.dex */
public class b implements InputFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2295a;

    public b(int i2) {
        this.f2295a = i2;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
        try {
            StringBuilder sb = new StringBuilder(spanned);
            sb.replace(i4, i5, charSequence.subSequence(i2, i3).toString());
            if (Integer.parseInt(sb.toString()) <= this.f2295a) {
                return null;
            }
            return "";
        } catch (NumberFormatException unused) {
            return "";
        }
    }
}
