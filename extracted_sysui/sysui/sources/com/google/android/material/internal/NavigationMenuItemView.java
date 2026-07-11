package com.google.android.material.internal;

import H.d;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import t.c;
import t.e;
import t.g;

/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class NavigationMenuItemView extends d implements MenuView.ItemView {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final int[] f2049r = {R.attr.state_checked};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2050g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2051h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2052i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2053j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final CheckedTextView f2054k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public FrameLayout f2055l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public MenuItemImpl f2056m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ColorStateList f2057n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f2058o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Drawable f2059p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final AccessibilityDelegateCompat f2060q;

    public class a extends AccessibilityDelegateCompat {
        public a() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setCheckable(NavigationMenuItemView.this.f2052i);
        }
    }

    public NavigationMenuItemView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void setActionView(@Nullable View view) {
        if (view != null) {
            if (this.f2055l == null) {
                this.f2055l = (FrameLayout) ((ViewStub) findViewById(e.f6614e)).inflate();
            }
            this.f2055l.removeAllViews();
            this.f2055l.addView(view);
        }
    }

    public final void a() {
        if (c()) {
            this.f2054k.setVisibility(8);
            FrameLayout frameLayout = this.f2055l;
            if (frameLayout != null) {
                LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) frameLayout.getLayoutParams();
                ((LinearLayout.LayoutParams) layoutParams).width = -1;
                this.f2055l.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        this.f2054k.setVisibility(0);
        FrameLayout frameLayout2 = this.f2055l;
        if (frameLayout2 != null) {
            LinearLayoutCompat.LayoutParams layoutParams2 = (LinearLayoutCompat.LayoutParams) frameLayout2.getLayoutParams();
            ((LinearLayout.LayoutParams) layoutParams2).width = -2;
            this.f2055l.setLayoutParams(layoutParams2);
        }
    }

    public final StateListDrawable b() {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorControlHighlight, typedValue, true)) {
            return null;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(f2049r, new ColorDrawable(typedValue.data));
        stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
        return stateListDrawable;
    }

    public final boolean c() {
        return this.f2056m.getTitle() == null && this.f2056m.getIcon() == null && this.f2056m.getActionView() != null;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public MenuItemImpl getItemData() {
        return this.f2056m;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void initialize(MenuItemImpl menuItemImpl, int i2) {
        this.f2056m = menuItemImpl;
        if (menuItemImpl.getItemId() > 0) {
            setId(menuItemImpl.getItemId());
        }
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            ViewCompat.setBackground(this, b());
        }
        setCheckable(menuItemImpl.isCheckable());
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        setTitle(menuItemImpl.getTitle());
        setIcon(menuItemImpl.getIcon());
        setActionView(menuItemImpl.getActionView());
        setContentDescription(menuItemImpl.getContentDescription());
        TooltipCompat.setTooltipText(this, menuItemImpl.getTooltipText());
        a();
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        MenuItemImpl menuItemImpl = this.f2056m;
        if (menuItemImpl != null && menuItemImpl.isCheckable() && this.f2056m.isChecked()) {
            ViewGroup.mergeDrawableStates(iArrOnCreateDrawableState, f2049r);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public boolean prefersCondensedTitle() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setCheckable(boolean z2) {
        refreshDrawableState();
        if (this.f2052i != z2) {
            this.f2052i = z2;
            this.f2060q.sendAccessibilityEvent(this.f2054k, 2048);
        }
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setChecked(boolean z2) {
        refreshDrawableState();
        this.f2054k.setChecked(z2);
        CheckedTextView checkedTextView = this.f2054k;
        checkedTextView.setTypeface(checkedTextView.getTypeface(), (z2 && this.f2053j) ? 1 : 0);
    }

    public void setHorizontalPadding(int i2) {
        setPadding(i2, getPaddingTop(), i2, getPaddingBottom());
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setIcon(@Nullable Drawable drawable) {
        if (drawable != null) {
            if (this.f2058o) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = DrawableCompat.wrap(drawable).mutate();
                DrawableCompat.setTintList(drawable, this.f2057n);
            }
            int i2 = this.f2050g;
            drawable.setBounds(0, 0, i2, i2);
        } else if (this.f2051h) {
            if (this.f2059p == null) {
                Drawable drawable2 = ResourcesCompat.getDrawable(getResources(), t.d.f6593j, getContext().getTheme());
                this.f2059p = drawable2;
                if (drawable2 != null) {
                    int i3 = this.f2050g;
                    drawable2.setBounds(0, 0, i3, i3);
                }
            }
            drawable = this.f2059p;
        }
        TextViewCompat.setCompoundDrawablesRelative(this.f2054k, drawable, null, null, null);
    }

    public void setIconPadding(int i2) {
        this.f2054k.setCompoundDrawablePadding(i2);
    }

    public void setIconSize(@Dimension int i2) {
        this.f2050g = i2;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.f2057n = colorStateList;
        this.f2058o = colorStateList != null;
        MenuItemImpl menuItemImpl = this.f2056m;
        if (menuItemImpl != null) {
            setIcon(menuItemImpl.getIcon());
        }
    }

    public void setMaxLines(int i2) {
        this.f2054k.setMaxLines(i2);
    }

    public void setNeedsEmptyIcon(boolean z2) {
        this.f2051h = z2;
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setShortcut(boolean z2, char c2) {
    }

    public void setTextAppearance(int i2) {
        TextViewCompat.setTextAppearance(this.f2054k, i2);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.f2054k.setTextColor(colorStateList);
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public void setTitle(CharSequence charSequence) {
        this.f2054k.setText(charSequence);
    }

    @Override // androidx.appcompat.view.menu.MenuView.ItemView
    public boolean showsIcon() {
        return true;
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f2053j = true;
        a aVar = new a();
        this.f2060q = aVar;
        setOrientation(0);
        LayoutInflater.from(context).inflate(g.f6637a, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(c.f6559b));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(e.f6615f);
        this.f2054k = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        ViewCompat.setAccessibilityDelegate(checkedTextView, aVar);
    }
}
