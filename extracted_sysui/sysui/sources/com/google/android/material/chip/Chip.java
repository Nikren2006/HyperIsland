package com.google.android.material.chip;

import L.d;
import L.f;
import O.k;
import O.n;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.AnimatorRes;
import androidx.annotation.BoolRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.material.chip.a;
import java.util.List;
import t.AbstractC0741a;
import t.h;
import t.i;
import t.j;
import u.C0745c;

/* JADX INFO: loaded from: classes2.dex */
public class Chip extends AppCompatCheckBox implements a.InterfaceC0055a, n, Checkable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public com.google.android.material.chip.a f1811a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public InsetDrawable f1812b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RippleDrawable f1813c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View.OnClickListener f1814d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CompoundButton.OnCheckedChangeListener f1815e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1816f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1817g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1818h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1819i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1820j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1821k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1822l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public CharSequence f1823m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final c f1824n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1825o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Rect f1826p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final RectF f1827q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final f f1828r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final int f1808s = i.f6686i;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final Rect f1809x = new Rect();

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final int[] f1810y = {R.attr.state_selected};

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final int[] f1807A = {R.attr.state_checkable};

    public class a extends f {
        public a() {
        }

        @Override // L.f
        public void a(int i2) {
        }

        @Override // L.f
        public void b(Typeface typeface, boolean z2) {
            Chip chip = Chip.this;
            chip.setText(chip.f1811a.G2() ? Chip.this.f1811a.b1() : Chip.this.getText());
            Chip.this.requestLayout();
            Chip.this.invalidate();
        }
    }

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (Chip.this.f1811a != null) {
                Chip.this.f1811a.getOutline(outline);
            } else {
                outline.setAlpha(0.0f);
            }
        }
    }

    public class c extends ExploreByTouchHelper {
        public c(Chip chip) {
            super(chip);
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public int getVirtualViewAt(float f2, float f3) {
            return (Chip.this.n() && Chip.this.getCloseIconTouchBounds().contains(f2, f3)) ? 1 : 0;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void getVisibleVirtualViews(List list) {
            list.add(0);
            if (Chip.this.n() && Chip.this.s() && Chip.this.f1814d != null) {
                list.add(1);
            }
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public boolean onPerformActionForVirtualView(int i2, int i3, Bundle bundle) {
            if (i3 != 16) {
                return false;
            }
            if (i2 == 0) {
                return Chip.this.performClick();
            }
            if (i2 == 1) {
                return Chip.this.u();
            }
            return false;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.setCheckable(Chip.this.r());
            accessibilityNodeInfoCompat.setClickable(Chip.this.isClickable());
            accessibilityNodeInfoCompat.setClassName(Chip.this.getAccessibilityClassName());
            accessibilityNodeInfoCompat.setText(Chip.this.getText());
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onPopulateNodeForVirtualView(int i2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (i2 != 1) {
                accessibilityNodeInfoCompat.setContentDescription("");
                accessibilityNodeInfoCompat.setBoundsInParent(Chip.f1809x);
                return;
            }
            CharSequence closeIconContentDescription = Chip.this.getCloseIconContentDescription();
            if (closeIconContentDescription != null) {
                accessibilityNodeInfoCompat.setContentDescription(closeIconContentDescription);
            } else {
                CharSequence text = Chip.this.getText();
                accessibilityNodeInfoCompat.setContentDescription(Chip.this.getContext().getString(h.f6665k, TextUtils.isEmpty(text) ? "" : text).trim());
            }
            accessibilityNodeInfoCompat.setBoundsInParent(Chip.this.getCloseIconTouchBoundsInt());
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            accessibilityNodeInfoCompat.setEnabled(Chip.this.isEnabled());
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        public void onVirtualViewKeyboardFocusChanged(int i2, boolean z2) {
            if (i2 == 1) {
                Chip.this.f1819i = z2;
                Chip.this.refreshDrawableState();
            }
        }
    }

    public Chip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0741a.f6504d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public RectF getCloseIconTouchBounds() {
        this.f1827q.setEmpty();
        if (n() && this.f1814d != null) {
            this.f1811a.S0(this.f1827q);
        }
        return this.f1827q;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public Rect getCloseIconTouchBoundsInt() {
        RectF closeIconTouchBounds = getCloseIconTouchBounds();
        this.f1826p.set((int) closeIconTouchBounds.left, (int) closeIconTouchBounds.top, (int) closeIconTouchBounds.right, (int) closeIconTouchBounds.bottom);
        return this.f1826p;
    }

    @Nullable
    private d getTextAppearance() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.c1();
        }
        return null;
    }

    private void setCloseIconHovered(boolean z2) {
        if (this.f1818h != z2) {
            this.f1818h = z2;
            refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z2) {
        if (this.f1817g != z2) {
            this.f1817g = z2;
            refreshDrawableState();
        }
    }

    public final void A() {
        this.f1813c = new RippleDrawable(M.b.a(this.f1811a.Z0()), getBackgroundDrawable(), null);
        this.f1811a.F2(false);
        ViewCompat.setBackground(this, this.f1813c);
        B();
    }

    public final void B() {
        com.google.android.material.chip.a aVar;
        if (TextUtils.isEmpty(getText()) || (aVar = this.f1811a) == null) {
            return;
        }
        int iD0 = (int) (aVar.D0() + this.f1811a.d1() + this.f1811a.k0());
        int iI0 = (int) (this.f1811a.I0() + this.f1811a.e1() + this.f1811a.g0());
        if (this.f1812b != null) {
            Rect rect = new Rect();
            this.f1812b.getPadding(rect);
            iI0 += rect.left;
            iD0 += rect.right;
        }
        ViewCompat.setPaddingRelative(this, iI0, getPaddingTop(), iD0, getPaddingBottom());
    }

    public final void C() {
        TextPaint paint = getPaint();
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            paint.drawableState = aVar.getState();
        }
        d textAppearance = getTextAppearance();
        if (textAppearance != null) {
            textAppearance.n(getContext(), paint, this.f1828r);
        }
    }

    public final void D(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "background") != null) {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableLeft") != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableStart") != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableEnd") != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        if (attributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableRight") != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        if (!attributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "singleLine", true) || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "lines", 1) != 1 || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minLines", 1) != 1 || attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", 1) != 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        if (attributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity", 8388627) != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        }
    }

    @Override // com.google.android.material.chip.a.InterfaceC0055a
    public void a() {
        l(this.f1822l);
        requestLayout();
        invalidateOutline();
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return !this.f1825o ? super.dispatchHoverEvent(motionEvent) : this.f1824n.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!this.f1825o) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (!this.f1824n.dispatchKeyEvent(keyEvent) || this.f1824n.getKeyboardFocusedVirtualViewId() == Integer.MIN_VALUE) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        com.google.android.material.chip.a aVar = this.f1811a;
        if ((aVar == null || !aVar.j1()) ? false : this.f1811a.f2(k())) {
            invalidate();
        }
    }

    @Override // android.widget.CheckBox, android.widget.CompoundButton, android.widget.Button, android.widget.TextView, android.view.View
    @NonNull
    public CharSequence getAccessibilityClassName() {
        if (!TextUtils.isEmpty(this.f1823m)) {
            return this.f1823m;
        }
        if (!r()) {
            return isClickable() ? "android.widget.Button" : "android.view.View";
        }
        getParent();
        return "android.widget.Button";
    }

    @Nullable
    public Drawable getBackgroundDrawable() {
        InsetDrawable insetDrawable = this.f1812b;
        return insetDrawable == null ? this.f1811a : insetDrawable;
    }

    @Nullable
    public Drawable getCheckedIcon() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.z0();
        }
        return null;
    }

    @Nullable
    public ColorStateList getCheckedIconTint() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.A0();
        }
        return null;
    }

    @Nullable
    public ColorStateList getChipBackgroundColor() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.B0();
        }
        return null;
    }

    public float getChipCornerRadius() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return Math.max(0.0f, aVar.C0());
        }
        return 0.0f;
    }

    public Drawable getChipDrawable() {
        return this.f1811a;
    }

    public float getChipEndPadding() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.D0();
        }
        return 0.0f;
    }

    @Nullable
    public Drawable getChipIcon() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.E0();
        }
        return null;
    }

    public float getChipIconSize() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.F0();
        }
        return 0.0f;
    }

    @Nullable
    public ColorStateList getChipIconTint() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.G0();
        }
        return null;
    }

    public float getChipMinHeight() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.H0();
        }
        return 0.0f;
    }

    public float getChipStartPadding() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.I0();
        }
        return 0.0f;
    }

    @Nullable
    public ColorStateList getChipStrokeColor() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.J0();
        }
        return null;
    }

    public float getChipStrokeWidth() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.K0();
        }
        return 0.0f;
    }

    @Deprecated
    public CharSequence getChipText() {
        return getText();
    }

    @Nullable
    public Drawable getCloseIcon() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.L0();
        }
        return null;
    }

    @Nullable
    public CharSequence getCloseIconContentDescription() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.M0();
        }
        return null;
    }

    public float getCloseIconEndPadding() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.N0();
        }
        return 0.0f;
    }

    public float getCloseIconSize() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.O0();
        }
        return 0.0f;
    }

    public float getCloseIconStartPadding() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.P0();
        }
        return 0.0f;
    }

    @Nullable
    public ColorStateList getCloseIconTint() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.R0();
        }
        return null;
    }

    @Override // android.widget.TextView
    @Nullable
    public TextUtils.TruncateAt getEllipsize() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.V0();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public void getFocusedRect(Rect rect) {
        if (this.f1825o && (this.f1824n.getKeyboardFocusedVirtualViewId() == 1 || this.f1824n.getAccessibilityFocusedVirtualViewId() == 1)) {
            rect.set(getCloseIconTouchBoundsInt());
        } else {
            super.getFocusedRect(rect);
        }
    }

    @Nullable
    public C0745c getHideMotionSpec() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.W0();
        }
        return null;
    }

    public float getIconEndPadding() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.X0();
        }
        return 0.0f;
    }

    public float getIconStartPadding() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.Y0();
        }
        return 0.0f;
    }

    @Nullable
    public ColorStateList getRippleColor() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.Z0();
        }
        return null;
    }

    @NonNull
    public k getShapeAppearanceModel() {
        return this.f1811a.A();
    }

    @Nullable
    public C0745c getShowMotionSpec() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.a1();
        }
        return null;
    }

    public float getTextEndPadding() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.d1();
        }
        return 0.0f;
    }

    public float getTextStartPadding() {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            return aVar.e1();
        }
        return 0.0f;
    }

    public final void j(com.google.android.material.chip.a aVar) {
        aVar.j2(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    public final int[] k() {
        ?? IsEnabled = isEnabled();
        int i2 = IsEnabled;
        if (this.f1819i) {
            i2 = IsEnabled + 1;
        }
        int i3 = i2;
        if (this.f1818h) {
            i3 = i2 + 1;
        }
        int i4 = i3;
        if (this.f1817g) {
            i4 = i3 + 1;
        }
        int i5 = i4;
        if (isChecked()) {
            i5 = i4 + 1;
        }
        int[] iArr = new int[i5];
        int i6 = 0;
        if (isEnabled()) {
            iArr[0] = 16842910;
            i6 = 1;
        }
        if (this.f1819i) {
            iArr[i6] = 16842908;
            i6++;
        }
        if (this.f1818h) {
            iArr[i6] = 16843623;
            i6++;
        }
        if (this.f1817g) {
            iArr[i6] = 16842919;
            i6++;
        }
        if (isChecked()) {
            iArr[i6] = 16842913;
        }
        return iArr;
    }

    public boolean l(int i2) {
        this.f1822l = i2;
        if (!w()) {
            if (this.f1812b != null) {
                v();
            } else {
                z();
            }
            return false;
        }
        int iMax = Math.max(0, i2 - this.f1811a.getIntrinsicHeight());
        int iMax2 = Math.max(0, i2 - this.f1811a.getIntrinsicWidth());
        if (iMax2 <= 0 && iMax <= 0) {
            if (this.f1812b != null) {
                v();
            } else {
                z();
            }
            return false;
        }
        int i3 = iMax2 > 0 ? iMax2 / 2 : 0;
        int i4 = iMax > 0 ? iMax / 2 : 0;
        if (this.f1812b != null) {
            Rect rect = new Rect();
            this.f1812b.getPadding(rect);
            if (rect.top == i4 && rect.bottom == i4 && rect.left == i3 && rect.right == i3) {
                z();
                return true;
            }
        }
        if (getMinHeight() != i2) {
            setMinHeight(i2);
        }
        if (getMinWidth() != i2) {
            setMinWidth(i2);
        }
        q(i3, i4, i3, i4);
        z();
        return true;
    }

    public final void m() {
        if (getBackgroundDrawable() == this.f1812b && this.f1811a.getCallback() == null) {
            this.f1811a.setCallback(this.f1812b);
        }
    }

    public final boolean n() {
        com.google.android.material.chip.a aVar = this.f1811a;
        return (aVar == null || aVar.L0() == null) ? false : true;
    }

    public final void o(Context context, AttributeSet attributeSet, int i2) {
        TypedArray typedArrayI = H.k.i(context, attributeSet, j.f6754b0, i2, f1808s, new int[0]);
        this.f1820j = typedArrayI.getBoolean(j.f6713H0, false);
        this.f1822l = (int) Math.ceil(typedArrayI.getDimension(j.f6814v0, (float) Math.ceil(H.n.c(getContext(), 48))));
        typedArrayI.recycle();
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        O.h.f(this, this.f1811a);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 2);
        if (isChecked()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, f1810y);
        }
        if (r()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, f1807A);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z2, int i2, Rect rect) {
        super.onFocusChanged(z2, i2, rect);
        if (this.f1825o) {
            this.f1824n.onFocusChanged(z2, i2, rect);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7) {
            setCloseIconHovered(getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()));
        } else if (actionMasked == 10) {
            setCloseIconHovered(false);
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getAccessibilityClassName());
        accessibilityNodeInfo.setCheckable(r());
        accessibilityNodeInfo.setClickable(isClickable());
        getParent();
    }

    @Override // android.widget.Button, android.widget.TextView, android.view.View
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i2) {
        return (getCloseIconTouchBounds().contains(motionEvent.getX(), motionEvent.getY()) && isEnabled()) ? PointerIcon.getSystemIcon(getContext(), 1002) : super.onResolvePointerIcon(motionEvent, i2);
    }

    @Override // android.widget.TextView, android.view.View
    public void onRtlPropertiesChanged(int i2) {
        super.onRtlPropertiesChanged(i2);
        if (this.f1821k != i2) {
            this.f1821k = i2;
            B();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001e, code lost:
    
        if (r0 != 3) goto L22;
     */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            android.graphics.RectF r1 = r5.getCloseIconTouchBounds()
            float r2 = r6.getX()
            float r3 = r6.getY()
            boolean r1 = r1.contains(r2, r3)
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L3a
            if (r0 == r2) goto L2c
            r4 = 2
            if (r0 == r4) goto L21
            r1 = 3
            if (r0 == r1) goto L35
            goto L40
        L21:
            boolean r0 = r5.f1817g
            if (r0 == 0) goto L40
            if (r1 != 0) goto L2a
            r5.setCloseIconPressed(r3)
        L2a:
            r0 = r2
            goto L41
        L2c:
            boolean r0 = r5.f1817g
            if (r0 == 0) goto L35
            r5.u()
            r0 = r2
            goto L36
        L35:
            r0 = r3
        L36:
            r5.setCloseIconPressed(r3)
            goto L41
        L3a:
            if (r1 == 0) goto L40
            r5.setCloseIconPressed(r2)
            goto L2a
        L40:
            r0 = r3
        L41:
            if (r0 != 0) goto L4b
            boolean r5 = super.onTouchEvent(r6)
            if (r5 == 0) goto L4a
            goto L4b
        L4a:
            r2 = r3
        L4b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.Chip.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void p() {
        setOutlineProvider(new b());
    }

    public final void q(int i2, int i3, int i4, int i5) {
        this.f1812b = new InsetDrawable((Drawable) this.f1811a, i2, i3, i4, i5);
    }

    public boolean r() {
        com.google.android.material.chip.a aVar = this.f1811a;
        return aVar != null && aVar.i1();
    }

    public boolean s() {
        com.google.android.material.chip.a aVar = this.f1811a;
        return aVar != null && aVar.k1();
    }

    public void setAccessibilityClassName(@Nullable CharSequence charSequence) {
        this.f1823m = charSequence;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f1813c) {
            super.setBackground(drawable);
        } else {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        Log.w("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable == getBackgroundDrawable() || drawable == this.f1813c) {
            super.setBackgroundDrawable(drawable);
        } else {
            Log.w("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
        }
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.view.View
    public void setBackgroundResource(int i2) {
        Log.w("Chip", "Do not set the background resource; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(@Nullable ColorStateList colorStateList) {
        Log.w("Chip", "Do not set the background tint list; Chip manages its own background drawable.");
    }

    @Override // android.view.View
    public void setBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        Log.w("Chip", "Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setCheckable(boolean z2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.r1(z2);
        }
    }

    public void setCheckableResource(@BoolRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.s1(i2);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar == null) {
            this.f1816f = z2;
        } else if (aVar.i1()) {
            super.setChecked(z2);
        }
    }

    public void setCheckedIcon(@Nullable Drawable drawable) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.t1(drawable);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z2) {
        setCheckedIconVisible(z2);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(@BoolRes int i2) {
        setCheckedIconVisible(i2);
    }

    public void setCheckedIconResource(@DrawableRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.u1(i2);
        }
    }

    public void setCheckedIconTint(@Nullable ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.v1(colorStateList);
        }
    }

    public void setCheckedIconTintResource(@ColorRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.w1(i2);
        }
    }

    public void setCheckedIconVisible(@BoolRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.x1(i2);
        }
    }

    public void setChipBackgroundColor(@Nullable ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.z1(colorStateList);
        }
    }

    public void setChipBackgroundColorResource(@ColorRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.A1(i2);
        }
    }

    @Deprecated
    public void setChipCornerRadius(float f2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.B1(f2);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(@DimenRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.C1(i2);
        }
    }

    public void setChipDrawable(@NonNull com.google.android.material.chip.a aVar) {
        com.google.android.material.chip.a aVar2 = this.f1811a;
        if (aVar2 != aVar) {
            x(aVar2);
            this.f1811a = aVar;
            aVar.u2(false);
            j(this.f1811a);
            l(this.f1822l);
        }
    }

    public void setChipEndPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.D1(f2);
        }
    }

    public void setChipEndPaddingResource(@DimenRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.E1(i2);
        }
    }

    public void setChipIcon(@Nullable Drawable drawable) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.F1(drawable);
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z2) {
        setChipIconVisible(z2);
    }

    @Deprecated
    public void setChipIconEnabledResource(@BoolRes int i2) {
        setChipIconVisible(i2);
    }

    public void setChipIconResource(@DrawableRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.G1(i2);
        }
    }

    public void setChipIconSize(float f2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.H1(f2);
        }
    }

    public void setChipIconSizeResource(@DimenRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.I1(i2);
        }
    }

    public void setChipIconTint(@Nullable ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.J1(colorStateList);
        }
    }

    public void setChipIconTintResource(@ColorRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.K1(i2);
        }
    }

    public void setChipIconVisible(@BoolRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.L1(i2);
        }
    }

    public void setChipMinHeight(float f2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.N1(f2);
        }
    }

    public void setChipMinHeightResource(@DimenRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.O1(i2);
        }
    }

    public void setChipStartPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.P1(f2);
        }
    }

    public void setChipStartPaddingResource(@DimenRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.Q1(i2);
        }
    }

    public void setChipStrokeColor(@Nullable ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.R1(colorStateList);
        }
    }

    public void setChipStrokeColorResource(@ColorRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.S1(i2);
        }
    }

    public void setChipStrokeWidth(float f2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.T1(f2);
        }
    }

    public void setChipStrokeWidthResource(@DimenRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.U1(i2);
        }
    }

    @Deprecated
    public void setChipText(@Nullable CharSequence charSequence) {
        setText(charSequence);
    }

    @Deprecated
    public void setChipTextResource(@StringRes int i2) {
        setText(getResources().getString(i2));
    }

    public void setCloseIcon(@Nullable Drawable drawable) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.W1(drawable);
        }
        y();
    }

    public void setCloseIconContentDescription(@Nullable CharSequence charSequence) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.X1(charSequence);
        }
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z2) {
        setCloseIconVisible(z2);
    }

    @Deprecated
    public void setCloseIconEnabledResource(@BoolRes int i2) {
        setCloseIconVisible(i2);
    }

    public void setCloseIconEndPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.Y1(f2);
        }
    }

    public void setCloseIconEndPaddingResource(@DimenRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.Z1(i2);
        }
    }

    public void setCloseIconResource(@DrawableRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.a2(i2);
        }
        y();
    }

    public void setCloseIconSize(float f2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.b2(f2);
        }
    }

    public void setCloseIconSizeResource(@DimenRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.c2(i2);
        }
    }

    public void setCloseIconStartPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.d2(f2);
        }
    }

    public void setCloseIconStartPaddingResource(@DimenRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.e2(i2);
        }
    }

    public void setCloseIconTint(@Nullable ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.g2(colorStateList);
        }
    }

    public void setCloseIconTintResource(@ColorRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.h2(i2);
        }
    }

    public void setCloseIconVisible(@BoolRes int i2) {
        setCloseIconVisible(getResources().getBoolean(i2));
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        if (i2 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i4 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i2, i3, i4, i5);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        if (i2 != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (i4 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(i2, i3, i4, i5);
    }

    @Override // android.view.View
    @RequiresApi(21)
    public void setElevation(float f2) {
        super.setElevation(f2);
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.S(f2);
        }
    }

    @Override // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt truncateAt) {
        if (this.f1811a == null) {
            return;
        }
        if (truncateAt == TextUtils.TruncateAt.MARQUEE) {
            throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
        }
        super.setEllipsize(truncateAt);
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.k2(truncateAt);
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z2) {
        this.f1820j = z2;
        l(this.f1822l);
    }

    @Override // android.widget.TextView
    public void setGravity(int i2) {
        if (i2 != 8388627) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
        } else {
            super.setGravity(i2);
        }
    }

    public void setHideMotionSpec(@Nullable C0745c c0745c) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.l2(c0745c);
        }
    }

    public void setHideMotionSpecResource(@AnimatorRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.m2(i2);
        }
    }

    public void setIconEndPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.n2(f2);
        }
    }

    public void setIconEndPaddingResource(@DimenRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.o2(i2);
        }
    }

    public void setIconStartPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.p2(f2);
        }
    }

    public void setIconStartPaddingResource(@DimenRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.q2(i2);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setInternalOnCheckedChangeListener(@Nullable H.f fVar) {
    }

    @Override // android.view.View
    public void setLayoutDirection(int i2) {
        if (this.f1811a == null) {
            return;
        }
        super.setLayoutDirection(i2);
    }

    @Override // android.widget.TextView
    public void setLines(int i2) {
        if (i2 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setLines(i2);
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i2) {
        if (i2 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMaxLines(i2);
    }

    @Override // android.widget.TextView
    public void setMaxWidth(@Px int i2) {
        super.setMaxWidth(i2);
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.r2(i2);
        }
    }

    @Override // android.widget.TextView
    public void setMinLines(int i2) {
        if (i2 > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(i2);
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f1815e = onCheckedChangeListener;
    }

    public void setOnCloseIconClickListener(View.OnClickListener onClickListener) {
        this.f1814d = onClickListener;
        y();
    }

    public void setRippleColor(@Nullable ColorStateList colorStateList) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.s2(colorStateList);
        }
        if (this.f1811a.g1()) {
            return;
        }
        A();
    }

    public void setRippleColorResource(@ColorRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.t2(i2);
            if (this.f1811a.g1()) {
                return;
            }
            A();
        }
    }

    @Override // O.n
    public void setShapeAppearanceModel(@NonNull k kVar) {
        this.f1811a.setShapeAppearanceModel(kVar);
    }

    public void setShowMotionSpec(@Nullable C0745c c0745c) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.v2(c0745c);
        }
    }

    public void setShowMotionSpecResource(@AnimatorRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.w2(i2);
        }
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean z2) {
        if (!z2) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setSingleLine(z2);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar == null) {
            return;
        }
        if (charSequence == null) {
            charSequence = "";
        }
        super.setText(aVar.G2() ? null : charSequence, bufferType);
        com.google.android.material.chip.a aVar2 = this.f1811a;
        if (aVar2 != null) {
            aVar2.x2(charSequence);
        }
    }

    public void setTextAppearance(@Nullable d dVar) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.y2(dVar);
        }
        C();
    }

    public void setTextAppearanceResource(@StyleRes int i2) {
        setTextAppearance(getContext(), i2);
    }

    public void setTextEndPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.A2(f2);
        }
    }

    public void setTextEndPaddingResource(@DimenRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.B2(i2);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i2, float f2) {
        super.setTextSize(i2, f2);
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.C2(TypedValue.applyDimension(i2, f2, getResources().getDisplayMetrics()));
        }
        C();
    }

    public void setTextStartPadding(float f2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.D2(f2);
        }
    }

    public void setTextStartPaddingResource(@DimenRes int i2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.E2(i2);
        }
    }

    public final /* synthetic */ void t(CompoundButton compoundButton, boolean z2) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = this.f1815e;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(compoundButton, z2);
        }
    }

    public boolean u() {
        boolean z2 = false;
        playSoundEffect(0);
        View.OnClickListener onClickListener = this.f1814d;
        if (onClickListener != null) {
            onClickListener.onClick(this);
            z2 = true;
        }
        if (this.f1825o) {
            this.f1824n.sendEventForVirtualView(1, 1);
        }
        return z2;
    }

    public final void v() {
        if (this.f1812b != null) {
            this.f1812b = null;
            setMinWidth(0);
            setMinHeight((int) getChipMinHeight());
            z();
        }
    }

    public boolean w() {
        return this.f1820j;
    }

    public final void x(com.google.android.material.chip.a aVar) {
        if (aVar != null) {
            aVar.j2(null);
        }
    }

    public final void y() {
        if (n() && s() && this.f1814d != null) {
            ViewCompat.setAccessibilityDelegate(this, this.f1824n);
            this.f1825o = true;
        } else {
            ViewCompat.setAccessibilityDelegate(this, null);
            this.f1825o = false;
        }
    }

    public final void z() {
        if (M.b.f406a) {
            A();
            return;
        }
        this.f1811a.F2(true);
        ViewCompat.setBackground(this, getBackgroundDrawable());
        B();
        m();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Chip(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f1808s;
        super(S.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f1826p = new Rect();
        this.f1827q = new RectF();
        this.f1828r = new a();
        Context context2 = getContext();
        D(attributeSet);
        com.google.android.material.chip.a aVarP0 = com.google.android.material.chip.a.p0(context2, attributeSet, i2, i3);
        o(context2, attributeSet, i2);
        setChipDrawable(aVarP0);
        aVarP0.S(ViewCompat.getElevation(this));
        TypedArray typedArrayI = H.k.i(context2, attributeSet, j.f6754b0, i2, i3, new int[0]);
        boolean zHasValue = typedArrayI.hasValue(j.f6723M0);
        typedArrayI.recycle();
        this.f1824n = new c(this);
        y();
        if (!zHasValue) {
            p();
        }
        setChecked(this.f1816f);
        setText(aVarP0.b1());
        setEllipsize(aVarP0.V0());
        C();
        if (!this.f1811a.G2()) {
            setLines(1);
            setHorizontallyScrolling(true);
        }
        setGravity(8388627);
        B();
        if (w()) {
            setMinHeight(this.f1822l);
        }
        this.f1821k = ViewCompat.getLayoutDirection(this);
        super.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: B.a
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                this.f34a.t(compoundButton, z2);
            }
        });
    }

    public void setCloseIconVisible(boolean z2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.i2(z2);
        }
        y();
    }

    public void setCheckedIconVisible(boolean z2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.y1(z2);
        }
    }

    public void setChipIconVisible(boolean z2) {
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.M1(z2);
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable3 == null) {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
            return;
        }
        throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if (drawable3 == null) {
            super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
            return;
        }
        throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.z2(i2);
        }
        C();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i2) {
        super.setTextAppearance(i2);
        com.google.android.material.chip.a aVar = this.f1811a;
        if (aVar != null) {
            aVar.z2(i2);
        }
        C();
    }
}
