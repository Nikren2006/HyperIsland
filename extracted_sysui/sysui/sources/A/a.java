package A;

import H.k;
import H.n;
import L.c;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import java.util.Iterator;
import java.util.LinkedHashSet;
import t.AbstractC0741a;
import t.d;
import t.e;
import t.h;
import t.i;
import t.j;

/* JADX INFO: loaded from: classes2.dex */
public class a extends AppCompatCheckBox {

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public static final int[] f2B;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final int[][] f3D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final int f4E;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashSet f6a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashSet f7b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ColorStateList f8c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f9d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f10e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f11f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public CharSequence f12g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f13h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Drawable f14i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f15j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ColorStateList f16k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ColorStateList f17l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public PorterDuff.Mode f18m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f19n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int[] f20o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f21p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public CharSequence f22q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public CompoundButton.OnCheckedChangeListener f23r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final AnimatedVectorDrawableCompat f24s;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final Animatable2Compat.AnimationCallback f25x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final int f5y = i.f6687j;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final int[] f1A = {AbstractC0741a.f6496K};

    /* JADX INFO: renamed from: A.a$a, reason: collision with other inner class name */
    public class C0000a extends Animatable2Compat.AnimationCallback {
        public C0000a() {
        }

        @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback
        public void onAnimationEnd(Drawable drawable) {
            super.onAnimationEnd(drawable);
            ColorStateList colorStateList = a.this.f16k;
            if (colorStateList != null) {
                DrawableCompat.setTintList(drawable, colorStateList);
            }
        }

        @Override // androidx.vectordrawable.graphics.drawable.Animatable2Compat.AnimationCallback
        public void onAnimationStart(Drawable drawable) {
            super.onAnimationStart(drawable);
            a aVar = a.this;
            ColorStateList colorStateList = aVar.f16k;
            if (colorStateList != null) {
                DrawableCompat.setTint(drawable, colorStateList.getColorForState(aVar.f20o, a.this.f16k.getDefaultColor()));
            }
        }
    }

    public static class b extends View.BaseSavedState {

        @NonNull
        public static final Parcelable.Creator<b> CREATOR = new C0001a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f27a;

        /* JADX INFO: renamed from: A.a$b$a, reason: collision with other inner class name */
        public class C0001a implements Parcelable.Creator {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public b createFromParcel(Parcel parcel) {
                return new b(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public b[] newArray(int i2) {
                return new b[i2];
            }
        }

        public /* synthetic */ b(Parcel parcel, C0000a c0000a) {
            this(parcel);
        }

        public final String q() {
            int i2 = this.f27a;
            return i2 != 1 ? i2 != 2 ? "unchecked" : "indeterminate" : "checked";
        }

        public String toString() {
            return "MaterialCheckBox.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " CheckedState=" + q() + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeValue(Integer.valueOf(this.f27a));
        }

        public b(Parcelable parcelable) {
            super(parcelable);
        }

        public b(Parcel parcel) {
            super(parcel);
            this.f27a = ((Integer) parcel.readValue(getClass().getClassLoader())).intValue();
        }
    }

    static {
        int i2 = AbstractC0741a.f6495J;
        f2B = new int[]{i2};
        f3D = new int[][]{new int[]{R.attr.state_enabled, i2}, new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
        f4E = Resources.getSystem().getIdentifier("btn_check_material_anim", "drawable", SystemMediaRouteProvider.PACKAGE_NAME);
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0741a.f6503c);
    }

    @NonNull
    private String getButtonStateDescription() {
        int i2 = this.f19n;
        return i2 == 1 ? getResources().getString(h.f6662h) : i2 == 0 ? getResources().getString(h.f6664j) : getResources().getString(h.f6663i);
    }

    private ColorStateList getMaterialThemeColorsTintList() {
        if (this.f8c == null) {
            int[][] iArr = f3D;
            int[] iArr2 = new int[iArr.length];
            int iD = C.a.d(this, AbstractC0741a.f6505e);
            int iD2 = C.a.d(this, AbstractC0741a.f6507g);
            int iD3 = C.a.d(this, AbstractC0741a.f6511k);
            int iD4 = C.a.d(this, AbstractC0741a.f6508h);
            iArr2[0] = C.a.j(iD3, iD2, 1.0f);
            iArr2[1] = C.a.j(iD3, iD, 1.0f);
            iArr2[2] = C.a.j(iD3, iD4, 0.54f);
            iArr2[3] = C.a.j(iD3, iD4, 0.38f);
            iArr2[4] = C.a.j(iD3, iD4, 0.38f);
            this.f8c = new ColorStateList(iArr, iArr2);
        }
        return this.f8c;
    }

    @Nullable
    private ColorStateList getSuperButtonTintList() {
        ColorStateList colorStateList = this.f16k;
        return colorStateList != null ? colorStateList : super.getButtonTintList() != null ? super.getButtonTintList() : getSupportButtonTintList();
    }

    private void setDefaultStateDescription() {
        if (this.f22q == null) {
            super.setStateDescription(getButtonStateDescription());
        }
    }

    public final boolean c(TintTypedArray tintTypedArray) {
        return tintTypedArray.getResourceId(j.X2, 0) == f4E && tintTypedArray.getResourceId(j.Y2, 0) == 0;
    }

    public boolean d() {
        return this.f11f;
    }

    public final void e() {
        this.f13h = E.a.c(this.f13h, this.f16k, CompoundButtonCompat.getButtonTintMode(this));
        this.f14i = E.a.c(this.f14i, this.f17l, this.f18m);
        f();
        g();
        super.setButtonDrawable(E.a.a(this.f13h, this.f14i));
        refreshDrawableState();
    }

    public final void f() {
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat;
        if (this.f15j) {
            AnimatedVectorDrawableCompat animatedVectorDrawableCompat2 = this.f24s;
            if (animatedVectorDrawableCompat2 != null) {
                animatedVectorDrawableCompat2.unregisterAnimationCallback(this.f25x);
                this.f24s.registerAnimationCallback(this.f25x);
            }
            Drawable drawable = this.f13h;
            if (!(drawable instanceof AnimatedStateListDrawable) || (animatedVectorDrawableCompat = this.f24s) == null) {
                return;
            }
            int i2 = e.f6611b;
            int i3 = e.f6609P;
            ((AnimatedStateListDrawable) drawable).addTransition(i2, i3, animatedVectorDrawableCompat, false);
            ((AnimatedStateListDrawable) this.f13h).addTransition(e.f6617h, i3, this.f24s, false);
        }
    }

    public final void g() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        Drawable drawable = this.f13h;
        if (drawable != null && (colorStateList2 = this.f16k) != null) {
            DrawableCompat.setTintList(drawable, colorStateList2);
        }
        Drawable drawable2 = this.f14i;
        if (drawable2 == null || (colorStateList = this.f17l) == null) {
            return;
        }
        DrawableCompat.setTintList(drawable2, colorStateList);
    }

    @Override // android.widget.CompoundButton
    @Nullable
    public Drawable getButtonDrawable() {
        return this.f13h;
    }

    @Nullable
    public Drawable getButtonIconDrawable() {
        return this.f14i;
    }

    @Nullable
    public ColorStateList getButtonIconTintList() {
        return this.f17l;
    }

    @NonNull
    public PorterDuff.Mode getButtonIconTintMode() {
        return this.f18m;
    }

    @Override // android.widget.CompoundButton
    @Nullable
    public ColorStateList getButtonTintList() {
        return this.f16k;
    }

    public int getCheckedState() {
        return this.f19n;
    }

    @Nullable
    public CharSequence getErrorAccessibilityLabel() {
        return this.f12g;
    }

    public final void h() {
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public boolean isChecked() {
        return this.f19n == 1;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f9d && this.f16k == null && this.f17l == null) {
            setUseMaterialThemeColors(true);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int i2) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i2 + 2);
        if (getCheckedState() == 2) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, f1A);
        }
        if (d()) {
            CheckBox.mergeDrawableStates(iArrOnCreateDrawableState, f2B);
        }
        this.f20o = E.a.e(iArrOnCreateDrawableState);
        h();
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        Drawable buttonDrawable;
        if (!this.f10e || !TextUtils.isEmpty(getText()) || (buttonDrawable = CompoundButtonCompat.getButtonDrawable(this)) == null) {
            super.onDraw(canvas);
            return;
        }
        int width = ((getWidth() - buttonDrawable.getIntrinsicWidth()) / 2) * (n.g(this) ? -1 : 1);
        int iSave = canvas.save();
        canvas.translate(width, 0.0f);
        super.onDraw(canvas);
        canvas.restoreToCount(iSave);
        if (getBackground() != null) {
            Rect bounds = buttonDrawable.getBounds();
            DrawableCompat.setHotspotBounds(getBackground(), bounds.left + width, bounds.top, bounds.right + width, bounds.bottom);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (accessibilityNodeInfo != null && d()) {
            accessibilityNodeInfo.setText(((Object) accessibilityNodeInfo.getText()) + ", " + ((Object) this.f12g));
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof b)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        b bVar = (b) parcelable;
        super.onRestoreInstanceState(bVar.getSuperState());
        setCheckedState(bVar.f27a);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        b bVar = new b(super.onSaveInstanceState());
        bVar.f27a = getCheckedState();
        return bVar;
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public void setButtonDrawable(@DrawableRes int i2) {
        setButtonDrawable(AppCompatResources.getDrawable(getContext(), i2));
    }

    public void setButtonIconDrawable(@Nullable Drawable drawable) {
        this.f14i = drawable;
        e();
    }

    public void setButtonIconDrawableResource(@DrawableRes int i2) {
        setButtonIconDrawable(AppCompatResources.getDrawable(getContext(), i2));
    }

    public void setButtonIconTintList(@Nullable ColorStateList colorStateList) {
        if (this.f17l == colorStateList) {
            return;
        }
        this.f17l = colorStateList;
        e();
    }

    public void setButtonIconTintMode(@NonNull PorterDuff.Mode mode) {
        if (this.f18m == mode) {
            return;
        }
        this.f18m = mode;
        e();
    }

    @Override // android.widget.CompoundButton
    public void setButtonTintList(@Nullable ColorStateList colorStateList) {
        if (this.f16k == colorStateList) {
            return;
        }
        this.f16k = colorStateList;
        e();
    }

    @Override // android.widget.CompoundButton
    public void setButtonTintMode(@Nullable PorterDuff.Mode mode) {
        setSupportButtonTintMode(mode);
        e();
    }

    public void setCenterIfNoTextEnabled(boolean z2) {
        this.f10e = z2;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z2) {
        setCheckedState(z2 ? 1 : 0);
    }

    public void setCheckedState(int i2) {
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
        if (this.f19n != i2) {
            this.f19n = i2;
            super.setChecked(i2 == 1);
            refreshDrawableState();
            setDefaultStateDescription();
            if (this.f21p) {
                return;
            }
            this.f21p = true;
            LinkedHashSet linkedHashSet = this.f7b;
            if (linkedHashSet != null) {
                Iterator it = linkedHashSet.iterator();
                if (it.hasNext()) {
                    android.support.v4.media.a.a(it.next());
                    throw null;
                }
            }
            if (this.f19n != 2 && (onCheckedChangeListener = this.f23r) != null) {
                onCheckedChangeListener.onCheckedChanged(this, isChecked());
            }
            AutofillManager autofillManager = (AutofillManager) getContext().getSystemService(AutofillManager.class);
            if (autofillManager != null) {
                autofillManager.notifyValueChanged(this);
            }
            this.f21p = false;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean z2) {
        super.setEnabled(z2);
        h();
    }

    public void setErrorAccessibilityLabel(@Nullable CharSequence charSequence) {
        this.f12g = charSequence;
    }

    public void setErrorAccessibilityLabelResource(@StringRes int i2) {
        setErrorAccessibilityLabel(i2 != 0 ? getResources().getText(i2) : null);
    }

    public void setErrorShown(boolean z2) {
        if (this.f11f == z2) {
            return;
        }
        this.f11f = z2;
        refreshDrawableState();
        Iterator it = this.f6a.iterator();
        if (it.hasNext()) {
            android.support.v4.media.a.a(it.next());
            throw null;
        }
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f23r = onCheckedChangeListener;
    }

    @Override // android.widget.CompoundButton, android.view.View
    @RequiresApi(30)
    public void setStateDescription(@Nullable CharSequence charSequence) {
        this.f22q = charSequence;
        if (charSequence == null) {
            setDefaultStateDescription();
        } else {
            super.setStateDescription(charSequence);
        }
    }

    public void setUseMaterialThemeColors(boolean z2) {
        this.f9d = z2;
        if (z2) {
            CompoundButtonCompat.setButtonTintList(this, getMaterialThemeColorsTintList());
        } else {
            CompoundButtonCompat.setButtonTintList(this, null);
        }
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        setChecked(!isChecked());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public a(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f5y;
        super(S.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f6a = new LinkedHashSet();
        this.f7b = new LinkedHashSet();
        this.f24s = AnimatedVectorDrawableCompat.create(getContext(), d.f6588e);
        this.f25x = new C0000a();
        Context context2 = getContext();
        this.f13h = CompoundButtonCompat.getButtonDrawable(this);
        this.f16k = getSuperButtonTintList();
        setSupportButtonTintList(null);
        TintTypedArray tintTypedArrayJ = k.j(context2, attributeSet, j.W2, i2, i3, new int[0]);
        this.f14i = tintTypedArrayJ.getDrawable(j.Z2);
        if (this.f13h != null && k.g(context2) && c(tintTypedArrayJ)) {
            super.setButtonDrawable((Drawable) null);
            this.f13h = AppCompatResources.getDrawable(context2, d.f6587d);
            this.f15j = true;
            if (this.f14i == null) {
                this.f14i = AppCompatResources.getDrawable(context2, d.f6589f);
            }
        }
        this.f17l = c.b(context2, tintTypedArrayJ, j.a3);
        this.f18m = n.i(tintTypedArrayJ.getInt(j.b3, -1), PorterDuff.Mode.SRC_IN);
        this.f9d = tintTypedArrayJ.getBoolean(j.g3, false);
        this.f10e = tintTypedArrayJ.getBoolean(j.c3, true);
        this.f11f = tintTypedArrayJ.getBoolean(j.f3, false);
        this.f12g = tintTypedArrayJ.getText(j.e3);
        int i4 = j.d3;
        if (tintTypedArrayJ.hasValue(i4)) {
            setCheckedState(tintTypedArrayJ.getInt(i4, 0));
        }
        tintTypedArrayJ.recycle();
        e();
    }

    @Override // androidx.appcompat.widget.AppCompatCheckBox, android.widget.CompoundButton
    public void setButtonDrawable(@Nullable Drawable drawable) {
        this.f13h = drawable;
        this.f15j = false;
        e();
    }
}
