package com.google.android.material.appbar;

import E.a;
import H.k;
import H.l;
import O.g;
import O.h;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import t.AbstractC0741a;
import t.i;
import t.j;

/* JADX INFO: loaded from: classes2.dex */
public class MaterialToolbar extends Toolbar {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f1594f = i.f6692o;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final ImageView.ScaleType[] f1595g = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Integer f1596a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1597b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1598c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ImageView.ScaleType f1599d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Boolean f1600e;

    public MaterialToolbar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0741a.f6500O);
    }

    public final Pair a(TextView textView, TextView textView2) {
        int measuredWidth = getMeasuredWidth();
        int i2 = measuredWidth / 2;
        int paddingLeft = getPaddingLeft();
        int paddingRight = measuredWidth - getPaddingRight();
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8 && childAt != textView && childAt != textView2) {
                if (childAt.getRight() < i2 && childAt.getRight() > paddingLeft) {
                    paddingLeft = childAt.getRight();
                }
                if (childAt.getLeft() > i2 && childAt.getLeft() < paddingRight) {
                    paddingRight = childAt.getLeft();
                }
            }
        }
        return new Pair(Integer.valueOf(paddingLeft), Integer.valueOf(paddingRight));
    }

    public final void b(Context context) {
        Drawable background = getBackground();
        ColorStateList colorStateListValueOf = background == null ? ColorStateList.valueOf(0) : a.f(background);
        if (colorStateListValueOf != null) {
            g gVar = new g();
            gVar.T(colorStateListValueOf);
            gVar.J(context);
            gVar.S(ViewCompat.getElevation(this));
            ViewCompat.setBackground(this, gVar);
        }
    }

    public final void c(View view, Pair pair) {
        int measuredWidth = getMeasuredWidth();
        int measuredWidth2 = view.getMeasuredWidth();
        int i2 = (measuredWidth / 2) - (measuredWidth2 / 2);
        int i3 = measuredWidth2 + i2;
        int iMax = Math.max(Math.max(((Integer) pair.first).intValue() - i2, 0), Math.max(i3 - ((Integer) pair.second).intValue(), 0));
        if (iMax > 0) {
            i2 += iMax;
            i3 -= iMax;
            view.measure(View.MeasureSpec.makeMeasureSpec(i3 - i2, BasicMeasure.EXACTLY), view.getMeasuredHeightAndState());
        }
        view.layout(i2, view.getTop(), i3, view.getBottom());
    }

    public final void d() {
        if (this.f1597b || this.f1598c) {
            TextView textViewE = l.e(this);
            TextView textViewC = l.c(this);
            if (textViewE == null && textViewC == null) {
                return;
            }
            Pair pairA = a(textViewE, textViewC);
            if (this.f1597b && textViewE != null) {
                c(textViewE, pairA);
            }
            if (!this.f1598c || textViewC == null) {
                return;
            }
            c(textViewC, pairA);
        }
    }

    public final Drawable e(Drawable drawable) {
        if (drawable == null || this.f1596a == null) {
            return drawable;
        }
        Drawable drawableWrap = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTint(drawableWrap, this.f1596a.intValue());
        return drawableWrap;
    }

    public final void f() {
        ImageView imageViewB = l.b(this);
        if (imageViewB != null) {
            Boolean bool = this.f1600e;
            if (bool != null) {
                imageViewB.setAdjustViewBounds(bool.booleanValue());
            }
            ImageView.ScaleType scaleType = this.f1599d;
            if (scaleType != null) {
                imageViewB.setScaleType(scaleType);
            }
        }
    }

    @Nullable
    public ImageView.ScaleType getLogoScaleType() {
        return this.f1599d;
    }

    @Nullable
    @ColorInt
    public Integer getNavigationIconTint() {
        return this.f1596a;
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void inflateMenu(int i2) {
        Menu menu = getMenu();
        boolean z2 = menu instanceof MenuBuilder;
        if (z2) {
            ((MenuBuilder) menu).stopDispatchingItemsChanged();
        }
        super.inflateMenu(i2);
        if (z2) {
            ((MenuBuilder) menu).startDispatchingItemsChanged();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        h.e(this);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        d();
        f();
    }

    @Override // android.view.View
    @RequiresApi(21)
    public void setElevation(float f2) {
        super.setElevation(f2);
        h.d(this, f2);
    }

    public void setLogoAdjustViewBounds(boolean z2) {
        Boolean bool = this.f1600e;
        if (bool == null || bool.booleanValue() != z2) {
            this.f1600e = Boolean.valueOf(z2);
            requestLayout();
        }
    }

    public void setLogoScaleType(@NonNull ImageView.ScaleType scaleType) {
        if (this.f1599d != scaleType) {
            this.f1599d = scaleType;
            requestLayout();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(@Nullable Drawable drawable) {
        super.setNavigationIcon(e(drawable));
    }

    public void setNavigationIconTint(@ColorInt int i2) {
        this.f1596a = Integer.valueOf(i2);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    public void setSubtitleCentered(boolean z2) {
        if (this.f1598c != z2) {
            this.f1598c = z2;
            requestLayout();
        }
    }

    public void setTitleCentered(boolean z2) {
        if (this.f1597b != z2) {
            this.f1597b = z2;
            requestLayout();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialToolbar(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f1594f;
        super(S.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        Context context2 = getContext();
        TypedArray typedArrayI = k.i(context2, attributeSet, j.z3, i2, i3, new int[0]);
        int i4 = j.C3;
        if (typedArrayI.hasValue(i4)) {
            setNavigationIconTint(typedArrayI.getColor(i4, -1));
        }
        this.f1597b = typedArrayI.getBoolean(j.E3, false);
        this.f1598c = typedArrayI.getBoolean(j.D3, false);
        int i5 = typedArrayI.getInt(j.B3, -1);
        if (i5 >= 0) {
            ImageView.ScaleType[] scaleTypeArr = f1595g;
            if (i5 < scaleTypeArr.length) {
                this.f1599d = scaleTypeArr[i5];
            }
        }
        int i6 = j.A3;
        if (typedArrayI.hasValue(i6)) {
            this.f1600e = Boolean.valueOf(typedArrayI.getBoolean(i6, false));
        }
        typedArrayI.recycle();
        b(context2);
    }
}
