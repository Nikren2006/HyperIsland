package com.google.android.material.button;

import L.c;
import O.h;
import O.k;
import O.n;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import java.util.Iterator;
import java.util.LinkedHashSet;
import t.AbstractC0741a;
import t.i;
import t.j;
import x.C0769a;

/* JADX INFO: loaded from: classes2.dex */
public class MaterialButton extends AppCompatButton implements Checkable, n {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final int[] f1720o = {R.attr.state_checkable};

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final int[] f1721p = {R.attr.state_checked};

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final int f1722q = i.f6685h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0769a f1723a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashSet f1724b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a f1725c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PorterDuff.Mode f1726d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ColorStateList f1727e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f1728f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f1729g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1730h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1731i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1732j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1733k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f1734l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1735m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1736n;

    public interface a {
        void a(MaterialButton materialButton, boolean z2);
    }

    public static class b extends AbsSavedState {
        public static final Parcelable.Creator<b> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1737a;

        public class a implements Parcelable.ClassLoaderCreator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public b createFromParcel(Parcel parcel) {
                return new b(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public b createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new b(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
            public b[] newArray(int i2) {
                return new b[i2];
            }
        }

        public b(Parcelable parcelable) {
            super(parcelable);
        }

        public final void q(Parcel parcel) {
            this.f1737a = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f1737a ? 1 : 0);
        }

        public b(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                getClass().getClassLoader();
            }
            q(parcel);
        }
    }

    public MaterialButton(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0741a.f6518r);
    }

    private Layout.Alignment getActualTextAlignment() {
        int textAlignment = getTextAlignment();
        return textAlignment != 1 ? (textAlignment == 6 || textAlignment == 3) ? Layout.Alignment.ALIGN_OPPOSITE : textAlignment != 4 ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_CENTER : getGravityTextAlignment();
    }

    private Layout.Alignment getGravityTextAlignment() {
        int gravity = getGravity() & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        return gravity != 1 ? (gravity == 5 || gravity == 8388613) ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_CENTER;
    }

    private int getTextHeight() {
        if (getLineCount() > 1) {
            return getLayout().getHeight();
        }
        TextPaint paint = getPaint();
        String string = getText().toString();
        if (getTransformationMethod() != null) {
            string = getTransformationMethod().getTransformation(string, this).toString();
        }
        Rect rect = new Rect();
        paint.getTextBounds(string, 0, string.length(), rect);
        return Math.min(rect.height(), getLayout().getHeight());
    }

    private int getTextLayoutWidth() {
        int lineCount = getLineCount();
        float fMax = 0.0f;
        for (int i2 = 0; i2 < lineCount; i2++) {
            fMax = Math.max(fMax, getLayout().getLineWidth(i2));
        }
        return (int) Math.ceil(fMax);
    }

    public boolean a() {
        C0769a c0769a = this.f1723a;
        return c0769a != null && c0769a.p();
    }

    public final boolean b() {
        int i2 = this.f1736n;
        return i2 == 3 || i2 == 4;
    }

    public final boolean c() {
        int i2 = this.f1736n;
        return i2 == 1 || i2 == 2;
    }

    public final boolean d() {
        int i2 = this.f1736n;
        return i2 == 16 || i2 == 32;
    }

    public final boolean e() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    public final boolean f() {
        C0769a c0769a = this.f1723a;
        return (c0769a == null || c0769a.o()) ? false : true;
    }

    public final void g() {
        if (c()) {
            TextViewCompat.setCompoundDrawablesRelative(this, this.f1728f, null, null, null);
        } else if (b()) {
            TextViewCompat.setCompoundDrawablesRelative(this, null, null, this.f1728f, null);
        } else if (d()) {
            TextViewCompat.setCompoundDrawablesRelative(this, null, this.f1728f, null, null);
        }
    }

    @NonNull
    public String getA11yClassName() {
        if (TextUtils.isEmpty(this.f1729g)) {
            return (a() ? CompoundButton.class : Button.class).getName();
        }
        return this.f1729g;
    }

    @Override // android.view.View
    @Nullable
    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    @Override // android.view.View
    @Nullable
    public PorterDuff.Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    @Px
    public int getCornerRadius() {
        if (f()) {
            return this.f1723a.b();
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.f1728f;
    }

    public int getIconGravity() {
        return this.f1736n;
    }

    @Px
    public int getIconPadding() {
        return this.f1733k;
    }

    @Px
    public int getIconSize() {
        return this.f1730h;
    }

    public ColorStateList getIconTint() {
        return this.f1727e;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.f1726d;
    }

    @Dimension
    public int getInsetBottom() {
        return this.f1723a.c();
    }

    @Dimension
    public int getInsetTop() {
        return this.f1723a.d();
    }

    @Nullable
    public ColorStateList getRippleColor() {
        if (f()) {
            return this.f1723a.h();
        }
        return null;
    }

    @NonNull
    public k getShapeAppearanceModel() {
        if (f()) {
            return this.f1723a.i();
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (f()) {
            return this.f1723a.j();
        }
        return null;
    }

    @Px
    public int getStrokeWidth() {
        if (f()) {
            return this.f1723a.k();
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, androidx.core.view.TintableBackgroundView
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ColorStateList getSupportBackgroundTintList() {
        return f() ? this.f1723a.l() : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton, androidx.core.view.TintableBackgroundView
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return f() ? this.f1723a.m() : super.getSupportBackgroundTintMode();
    }

    public final void h(boolean z2) {
        Drawable drawable = this.f1728f;
        if (drawable != null) {
            Drawable drawableMutate = DrawableCompat.wrap(drawable).mutate();
            this.f1728f = drawableMutate;
            DrawableCompat.setTintList(drawableMutate, this.f1727e);
            PorterDuff.Mode mode = this.f1726d;
            if (mode != null) {
                DrawableCompat.setTintMode(this.f1728f, mode);
            }
            int intrinsicWidth = this.f1730h;
            if (intrinsicWidth == 0) {
                intrinsicWidth = this.f1728f.getIntrinsicWidth();
            }
            int intrinsicHeight = this.f1730h;
            if (intrinsicHeight == 0) {
                intrinsicHeight = this.f1728f.getIntrinsicHeight();
            }
            Drawable drawable2 = this.f1728f;
            int i2 = this.f1731i;
            int i3 = this.f1732j;
            drawable2.setBounds(i2, i3, intrinsicWidth + i2, intrinsicHeight + i3);
            this.f1728f.setVisible(true, z2);
        }
        if (z2) {
            g();
            return;
        }
        Drawable[] compoundDrawablesRelative = TextViewCompat.getCompoundDrawablesRelative(this);
        Drawable drawable3 = compoundDrawablesRelative[0];
        Drawable drawable4 = compoundDrawablesRelative[1];
        Drawable drawable5 = compoundDrawablesRelative[2];
        if ((!c() || drawable3 == this.f1728f) && ((!b() || drawable5 == this.f1728f) && (!d() || drawable4 == this.f1728f))) {
            return;
        }
        g();
    }

    public final void i(int i2, int i3) {
        if (this.f1728f == null || getLayout() == null) {
            return;
        }
        if (!c() && !b()) {
            if (d()) {
                this.f1731i = 0;
                if (this.f1736n == 16) {
                    this.f1732j = 0;
                    h(false);
                    return;
                }
                int intrinsicHeight = this.f1730h;
                if (intrinsicHeight == 0) {
                    intrinsicHeight = this.f1728f.getIntrinsicHeight();
                }
                int iMax = Math.max(0, (((((i3 - getTextHeight()) - getPaddingTop()) - intrinsicHeight) - this.f1733k) - getPaddingBottom()) / 2);
                if (this.f1732j != iMax) {
                    this.f1732j = iMax;
                    h(false);
                    return;
                }
                return;
            }
            return;
        }
        this.f1732j = 0;
        Layout.Alignment actualTextAlignment = getActualTextAlignment();
        int i4 = this.f1736n;
        if (i4 == 1 || i4 == 3 || ((i4 == 2 && actualTextAlignment == Layout.Alignment.ALIGN_NORMAL) || (i4 == 4 && actualTextAlignment == Layout.Alignment.ALIGN_OPPOSITE))) {
            this.f1731i = 0;
            h(false);
            return;
        }
        int intrinsicWidth = this.f1730h;
        if (intrinsicWidth == 0) {
            intrinsicWidth = this.f1728f.getIntrinsicWidth();
        }
        int textLayoutWidth = ((((i2 - getTextLayoutWidth()) - ViewCompat.getPaddingEnd(this)) - intrinsicWidth) - this.f1733k) - ViewCompat.getPaddingStart(this);
        if (actualTextAlignment == Layout.Alignment.ALIGN_CENTER) {
            textLayoutWidth /= 2;
        }
        if (e() != (this.f1736n == 4)) {
            textLayoutWidth = -textLayoutWidth;
        }
        if (this.f1731i != textLayoutWidth) {
            this.f1731i = textLayoutWidth;
            h(false);
        }
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f1734l;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (f()) {
            h.f(this, this.f1723a.f());
        }
    }

    @Override // android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 2);
        if (a()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, f1720o);
        }
        if (isChecked()) {
            Button.mergeDrawableStates(iArrOnCreateDrawableState, f1721p);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        accessibilityNodeInfo.setCheckable(a());
        accessibilityNodeInfo.setChecked(isChecked());
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        i(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof b)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        b bVar = (b) parcelable;
        super.onRestoreInstanceState(bVar.getSuperState());
        setChecked(bVar.f1737a);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        b bVar = new b(super.onSaveInstanceState());
        bVar.f1737a = this.f1734l;
        return bVar;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        i(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public boolean performClick() {
        if (this.f1723a.q()) {
            toggle();
        }
        return super.performClick();
    }

    @Override // android.view.View
    public void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.f1728f != null) {
            if (this.f1728f.setState(getDrawableState())) {
                invalidate();
            }
        }
    }

    public void setA11yClassName(@Nullable String str) {
        this.f1729g = str;
    }

    @Override // android.view.View
    public void setBackground(@NonNull Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(@ColorInt int i2) {
        if (f()) {
            this.f1723a.s(i2);
        } else {
            super.setBackgroundColor(i2);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundDrawable(@NonNull Drawable drawable) {
        if (!f()) {
            super.setBackgroundDrawable(drawable);
        } else {
            if (drawable == getBackground()) {
                getBackground().setState(drawable.getState());
                return;
            }
            Log.w("MaterialButton", "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
            this.f1723a.t();
            super.setBackgroundDrawable(drawable);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(@DrawableRes int i2) {
        setBackgroundDrawable(i2 != 0 ? AppCompatResources.getDrawable(getContext(), i2) : null);
    }

    @Override // android.view.View
    public void setBackgroundTintList(@Nullable ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    @Override // android.view.View
    public void setBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public void setCheckable(boolean z2) {
        if (f()) {
            this.f1723a.u(z2);
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z2) {
        if (a() && isEnabled() && this.f1734l != z2) {
            this.f1734l = z2;
            refreshDrawableState();
            if (getParent() instanceof MaterialButtonToggleGroup) {
                ((MaterialButtonToggleGroup) getParent()).m(this, this.f1734l);
            }
            if (this.f1735m) {
                return;
            }
            this.f1735m = true;
            Iterator it = this.f1724b.iterator();
            if (it.hasNext()) {
                android.support.v4.media.a.a(it.next());
                throw null;
            }
            this.f1735m = false;
        }
    }

    public void setCornerRadius(@Px int i2) {
        if (f()) {
            this.f1723a.v(i2);
        }
    }

    public void setCornerRadiusResource(@DimenRes int i2) {
        if (f()) {
            setCornerRadius(getResources().getDimensionPixelSize(i2));
        }
    }

    @Override // android.view.View
    @RequiresApi(21)
    public void setElevation(float f2) {
        super.setElevation(f2);
        if (f()) {
            this.f1723a.f().S(f2);
        }
    }

    public void setIcon(@Nullable Drawable drawable) {
        if (this.f1728f != drawable) {
            this.f1728f = drawable;
            h(true);
            i(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconGravity(int i2) {
        if (this.f1736n != i2) {
            this.f1736n = i2;
            i(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconPadding(@Px int i2) {
        if (this.f1733k != i2) {
            this.f1733k = i2;
            setCompoundDrawablePadding(i2);
        }
    }

    public void setIconResource(@DrawableRes int i2) {
        setIcon(i2 != 0 ? AppCompatResources.getDrawable(getContext(), i2) : null);
    }

    public void setIconSize(@Px int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.f1730h != i2) {
            this.f1730h = i2;
            h(true);
        }
    }

    public void setIconTint(@Nullable ColorStateList colorStateList) {
        if (this.f1727e != colorStateList) {
            this.f1727e = colorStateList;
            h(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.f1726d != mode) {
            this.f1726d = mode;
            h(false);
        }
    }

    public void setIconTintResource(@ColorRes int i2) {
        setIconTint(AppCompatResources.getColorStateList(getContext(), i2));
    }

    public void setInsetBottom(@Dimension int i2) {
        this.f1723a.w(i2);
    }

    public void setInsetTop(@Dimension int i2) {
        this.f1723a.x(i2);
    }

    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setOnPressedChangeListenerInternal(@Nullable a aVar) {
        this.f1725c = aVar;
    }

    @Override // android.view.View
    public void setPressed(boolean z2) {
        a aVar = this.f1725c;
        if (aVar != null) {
            aVar.a(this, z2);
        }
        super.setPressed(z2);
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        if (f()) {
            this.f1723a.y(colorStateList);
        }
    }

    public void setRippleColorResource(@ColorRes int i2) {
        if (f()) {
            setRippleColor(AppCompatResources.getColorStateList(getContext(), i2));
        }
    }

    @Override // O.n
    public void setShapeAppearanceModel(@NonNull k kVar) {
        if (!f()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.f1723a.z(kVar);
    }

    public void setShouldDrawSurfaceColorStroke(boolean z2) {
        if (f()) {
            this.f1723a.A(z2);
        }
    }

    public void setStrokeColor(@Nullable ColorStateList colorStateList) {
        if (f()) {
            this.f1723a.B(colorStateList);
        }
    }

    public void setStrokeColorResource(@ColorRes int i2) {
        if (f()) {
            setStrokeColor(AppCompatResources.getColorStateList(getContext(), i2));
        }
    }

    public void setStrokeWidth(@Px int i2) {
        if (f()) {
            this.f1723a.C(i2);
        }
    }

    public void setStrokeWidthResource(@DimenRes int i2) {
        if (f()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i2));
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, androidx.core.view.TintableBackgroundView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        if (f()) {
            this.f1723a.D(colorStateList);
        } else {
            super.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, androidx.core.view.TintableBackgroundView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        if (f()) {
            this.f1723a.E(mode);
        } else {
            super.setSupportBackgroundTintMode(mode);
        }
    }

    @Override // android.view.View
    @RequiresApi(17)
    public void setTextAlignment(int i2) {
        super.setTextAlignment(i2);
        i(getMeasuredWidth(), getMeasuredHeight());
    }

    public void setToggleCheckedStateOnClick(boolean z2) {
        this.f1723a.F(z2);
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.f1734l);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButton(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f1722q;
        super(S.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f1724b = new LinkedHashSet();
        this.f1734l = false;
        this.f1735m = false;
        Context context2 = getContext();
        TypedArray typedArrayI = H.k.i(context2, attributeSet, j.a2, i2, i3, new int[0]);
        this.f1733k = typedArrayI.getDimensionPixelSize(j.n2, 0);
        this.f1726d = H.n.i(typedArrayI.getInt(j.q2, -1), PorterDuff.Mode.SRC_IN);
        this.f1727e = c.a(getContext(), typedArrayI, j.p2);
        this.f1728f = c.d(getContext(), typedArrayI, j.l2);
        this.f1736n = typedArrayI.getInteger(j.m2, 1);
        this.f1730h = typedArrayI.getDimensionPixelSize(j.o2, 0);
        C0769a c0769a = new C0769a(this, k.e(context2, attributeSet, i2, i3).m());
        this.f1723a = c0769a;
        c0769a.r(typedArrayI);
        typedArrayI.recycle();
        setCompoundDrawablePadding(this.f1733k);
        h(this.f1728f != null);
    }
}
