package Q;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes2.dex */
public class y extends LinearLayout {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextInputLayout f682a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextView f683b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public CharSequence f684c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CheckableImageButton f685d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ColorStateList f686e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public PorterDuff.Mode f687f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f688g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ImageView.ScaleType f689h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public View.OnLongClickListener f690i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f691j;

    public y(TextInputLayout textInputLayout, TintTypedArray tintTypedArray) {
        super(textInputLayout.getContext());
        this.f682a = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        CheckableImageButton checkableImageButton = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(t.g.f6639c, (ViewGroup) this, false);
        this.f685d = checkableImageButton;
        s.e(checkableImageButton);
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.f683b = appCompatTextView;
        j(tintTypedArray);
        i(tintTypedArray);
        addView(checkableImageButton);
        addView(appCompatTextView);
    }

    public void A(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (this.f683b.getVisibility() != 0) {
            accessibilityNodeInfoCompat.setTraversalAfter(this.f685d);
        } else {
            accessibilityNodeInfoCompat.setLabelFor(this.f683b);
            accessibilityNodeInfoCompat.setTraversalAfter(this.f683b);
        }
    }

    public void B() {
        EditText editText = this.f682a.f2163d;
        if (editText == null) {
            return;
        }
        ViewCompat.setPaddingRelative(this.f683b, k() ? 0 : ViewCompat.getPaddingStart(editText), editText.getCompoundPaddingTop(), getContext().getResources().getDimensionPixelSize(t.c.f6535C), editText.getCompoundPaddingBottom());
    }

    public final void C() {
        int i2 = (this.f684c == null || this.f691j) ? 8 : 0;
        setVisibility((this.f685d.getVisibility() == 0 || i2 == 0) ? 0 : 8);
        this.f683b.setVisibility(i2);
        this.f682a.o0();
    }

    public CharSequence a() {
        return this.f684c;
    }

    public ColorStateList b() {
        return this.f683b.getTextColors();
    }

    public int c() {
        return ViewCompat.getPaddingStart(this) + ViewCompat.getPaddingStart(this.f683b) + (k() ? this.f685d.getMeasuredWidth() + MarginLayoutParamsCompat.getMarginEnd((ViewGroup.MarginLayoutParams) this.f685d.getLayoutParams()) : 0);
    }

    public TextView d() {
        return this.f683b;
    }

    public CharSequence e() {
        return this.f685d.getContentDescription();
    }

    public Drawable f() {
        return this.f685d.getDrawable();
    }

    public int g() {
        return this.f688g;
    }

    public ImageView.ScaleType h() {
        return this.f689h;
    }

    public final void i(TintTypedArray tintTypedArray) {
        this.f683b.setVisibility(8);
        this.f683b.setId(t.e.f6607N);
        this.f683b.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        ViewCompat.setAccessibilityLiveRegion(this.f683b, 1);
        o(tintTypedArray.getResourceId(t.j.v6, 0));
        int i2 = t.j.w6;
        if (tintTypedArray.hasValue(i2)) {
            p(tintTypedArray.getColorStateList(i2));
        }
        n(tintTypedArray.getText(t.j.u6));
    }

    public final void j(TintTypedArray tintTypedArray) {
        if (L.c.g(getContext())) {
            MarginLayoutParamsCompat.setMarginEnd((ViewGroup.MarginLayoutParams) this.f685d.getLayoutParams(), 0);
        }
        u(null);
        v(null);
        int i2 = t.j.C6;
        if (tintTypedArray.hasValue(i2)) {
            this.f686e = L.c.b(getContext(), tintTypedArray, i2);
        }
        int i3 = t.j.D6;
        if (tintTypedArray.hasValue(i3)) {
            this.f687f = H.n.i(tintTypedArray.getInt(i3, -1), null);
        }
        int i4 = t.j.z6;
        if (tintTypedArray.hasValue(i4)) {
            s(tintTypedArray.getDrawable(i4));
            int i5 = t.j.y6;
            if (tintTypedArray.hasValue(i5)) {
                r(tintTypedArray.getText(i5));
            }
            q(tintTypedArray.getBoolean(t.j.x6, true));
        }
        t(tintTypedArray.getDimensionPixelSize(t.j.A6, getResources().getDimensionPixelSize(t.c.f6551S)));
        int i6 = t.j.B6;
        if (tintTypedArray.hasValue(i6)) {
            w(s.b(tintTypedArray.getInt(i6, -1)));
        }
    }

    public boolean k() {
        return this.f685d.getVisibility() == 0;
    }

    public void l(boolean z2) {
        this.f691j = z2;
        C();
    }

    public void m() {
        s.d(this.f682a, this.f685d, this.f686e);
    }

    public void n(CharSequence charSequence) {
        this.f684c = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.f683b.setText(charSequence);
        C();
    }

    public void o(int i2) {
        TextViewCompat.setTextAppearance(this.f683b, i2);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        B();
    }

    public void p(ColorStateList colorStateList) {
        this.f683b.setTextColor(colorStateList);
    }

    public void q(boolean z2) {
        this.f685d.setCheckable(z2);
    }

    public void r(CharSequence charSequence) {
        if (e() != charSequence) {
            this.f685d.setContentDescription(charSequence);
        }
    }

    public void s(Drawable drawable) {
        this.f685d.setImageDrawable(drawable);
        if (drawable != null) {
            s.a(this.f682a, this.f685d, this.f686e, this.f687f);
            z(true);
            m();
        } else {
            z(false);
            u(null);
            v(null);
            r(null);
        }
    }

    public void t(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("startIconSize cannot be less than 0");
        }
        if (i2 != this.f688g) {
            this.f688g = i2;
            s.g(this.f685d, i2);
        }
    }

    public void u(View.OnClickListener onClickListener) {
        s.h(this.f685d, onClickListener, this.f690i);
    }

    public void v(View.OnLongClickListener onLongClickListener) {
        this.f690i = onLongClickListener;
        s.i(this.f685d, onLongClickListener);
    }

    public void w(ImageView.ScaleType scaleType) {
        this.f689h = scaleType;
        s.j(this.f685d, scaleType);
    }

    public void x(ColorStateList colorStateList) {
        if (this.f686e != colorStateList) {
            this.f686e = colorStateList;
            s.a(this.f682a, this.f685d, colorStateList, this.f687f);
        }
    }

    public void y(PorterDuff.Mode mode) {
        if (this.f687f != mode) {
            this.f687f = mode;
            s.a(this.f682a, this.f685d, this.f686e, mode);
        }
    }

    public void z(boolean z2) {
        if (k() != z2) {
            this.f685d.setVisibility(z2 ? 0 : 8);
            B();
            C();
        }
    }
}
