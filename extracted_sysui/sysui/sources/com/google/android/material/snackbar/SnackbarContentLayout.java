package com.google.android.material.snackbar;

import J.d;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import t.AbstractC0741a;
import t.c;
import t.e;
import u.AbstractC0743a;

/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SnackbarContentLayout extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f2116a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Button f2117b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final TimeInterpolator f2118c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2119d;

    public SnackbarContentLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2118c = d.g(context, AbstractC0741a.f6489D, AbstractC0743a.f6835b);
    }

    public static void a(View view, int i2, int i3) {
        if (ViewCompat.isPaddingRelative(view)) {
            ViewCompat.setPaddingRelative(view, ViewCompat.getPaddingStart(view), i2, ViewCompat.getPaddingEnd(view), i3);
        } else {
            view.setPadding(view.getPaddingLeft(), i2, view.getPaddingRight(), i3);
        }
    }

    public final boolean b(int i2, int i3, int i4) {
        boolean z2;
        if (i2 != getOrientation()) {
            setOrientation(i2);
            z2 = true;
        } else {
            z2 = false;
        }
        if (this.f2116a.getPaddingTop() == i3 && this.f2116a.getPaddingBottom() == i4) {
            return z2;
        }
        a(this.f2116a, i3, i4);
        return true;
    }

    public Button getActionView() {
        return this.f2117b;
    }

    public TextView getMessageView() {
        return this.f2116a;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f2116a = (TextView) findViewById(e.f6600G);
        this.f2117b = (Button) findViewById(e.f6599F);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (getOrientation() == 1) {
            return;
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(c.f6561d);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(c.f6560c);
        Layout layout = this.f2116a.getLayout();
        boolean z2 = layout != null && layout.getLineCount() > 1;
        if (!z2 || this.f2119d <= 0 || this.f2117b.getMeasuredWidth() <= this.f2119d) {
            if (!z2) {
                dimensionPixelSize = dimensionPixelSize2;
            }
            if (!b(0, dimensionPixelSize, dimensionPixelSize)) {
                return;
            }
        } else if (!b(1, dimensionPixelSize, dimensionPixelSize - dimensionPixelSize2)) {
            return;
        }
        super.onMeasure(i2, i3);
    }

    public void setMaxInlineActionWidth(int i2) {
        this.f2119d = i2;
    }
}
