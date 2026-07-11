package Q;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.annotation.ArrayRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.textfield.TextInputLayout;
import t.AbstractC0741a;

/* JADX INFO: loaded from: classes2.dex */
public class u extends AppCompatAutoCompleteTextView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ListPopupWindow f666a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AccessibilityManager f667b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f668c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f669d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f670e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorStateList f671f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f672g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ColorStateList f673h;

    public class a implements AdapterView.OnItemClickListener {
        public a() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
            u uVar = u.this;
            u.this.i(i2 < 0 ? uVar.f666a.getSelectedItem() : uVar.getAdapter().getItem(i2));
            AdapterView.OnItemClickListener onItemClickListener = u.this.getOnItemClickListener();
            if (onItemClickListener != null) {
                if (view == null || i2 < 0) {
                    view = u.this.f666a.getSelectedView();
                    i2 = u.this.f666a.getSelectedItemPosition();
                    j2 = u.this.f666a.getSelectedItemId();
                }
                onItemClickListener.onItemClick(u.this.f666a.getListView(), view, i2, j2);
            }
            u.this.f666a.dismiss();
        }
    }

    public class b extends ArrayAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ColorStateList f675a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public ColorStateList f676b;

        public b(Context context, int i2, String[] strArr) {
            super(context, i2, strArr);
            f();
        }

        public final ColorStateList a() {
            if (!c() || !d()) {
                return null;
            }
            int[] iArr = {R.attr.state_hovered, -16842919};
            int[] iArr2 = {R.attr.state_selected, -16842919};
            return new ColorStateList(new int[][]{iArr2, iArr, new int[0]}, new int[]{C.a.i(u.this.f672g, u.this.f673h.getColorForState(iArr2, 0)), C.a.i(u.this.f672g, u.this.f673h.getColorForState(iArr, 0)), u.this.f672g});
        }

        public final Drawable b() {
            if (!c()) {
                return null;
            }
            ColorDrawable colorDrawable = new ColorDrawable(u.this.f672g);
            if (this.f676b == null) {
                return colorDrawable;
            }
            DrawableCompat.setTintList(colorDrawable, this.f675a);
            return new RippleDrawable(this.f676b, colorDrawable, null);
        }

        public final boolean c() {
            return u.this.f672g != 0;
        }

        public final boolean d() {
            return u.this.f673h != null;
        }

        public final ColorStateList e() {
            if (!d()) {
                return null;
            }
            int[] iArr = {R.attr.state_pressed};
            return new ColorStateList(new int[][]{iArr, new int[0]}, new int[]{u.this.f673h.getColorForState(iArr, 0), 0});
        }

        public void f() {
            this.f676b = e();
            this.f675a = a();
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i2, view, viewGroup);
            if (view2 instanceof TextView) {
                TextView textView = (TextView) view2;
                ViewCompat.setBackground(textView, u.this.getText().toString().contentEquals(textView.getText()) ? b() : null);
            }
            return view2;
        }
    }

    public u(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0741a.f6501a);
    }

    @Override // android.widget.AutoCompleteTextView
    public void dismissDropDown() {
        if (f()) {
            this.f666a.dismiss();
        } else {
            super.dismissDropDown();
        }
    }

    public final TextInputLayout e() {
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            }
        }
        return null;
    }

    public final boolean f() {
        AccessibilityManager accessibilityManager = this.f667b;
        return accessibilityManager != null && accessibilityManager.isTouchExplorationEnabled();
    }

    public final int g() {
        ListAdapter adapter = getAdapter();
        TextInputLayout textInputLayoutE = e();
        int i2 = 0;
        if (adapter == null || textInputLayoutE == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMin = Math.min(adapter.getCount(), Math.max(0, this.f666a.getSelectedItemPosition()) + 15);
        View view = null;
        int iMax = 0;
        for (int iMax2 = Math.max(0, iMin - 15); iMax2 < iMin; iMax2++) {
            int itemViewType = adapter.getItemViewType(iMax2);
            if (itemViewType != i2) {
                view = null;
                i2 = itemViewType;
            }
            view = adapter.getView(iMax2, view, textInputLayoutE);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax = Math.max(iMax, view.getMeasuredWidth());
        }
        Drawable background = this.f666a.getBackground();
        if (background != null) {
            background.getPadding(this.f668c);
            Rect rect = this.f668c;
            iMax += rect.left + rect.right;
        }
        return iMax + textInputLayoutE.getEndIconView().getMeasuredWidth();
    }

    @Nullable
    public ColorStateList getDropDownBackgroundTintList() {
        return this.f671f;
    }

    @Override // android.widget.TextView
    @Nullable
    public CharSequence getHint() {
        TextInputLayout textInputLayoutE = e();
        return (textInputLayoutE == null || !textInputLayoutE.R()) ? super.getHint() : textInputLayoutE.getHint();
    }

    public float getPopupElevation() {
        return this.f670e;
    }

    public int getSimpleItemSelectedColor() {
        return this.f672g;
    }

    @Nullable
    public ColorStateList getSimpleItemSelectedRippleColor() {
        return this.f673h;
    }

    public final void h() {
        TextInputLayout textInputLayoutE = e();
        if (textInputLayoutE != null) {
            textInputLayoutE.r0();
        }
    }

    public final void i(Object obj) {
        setText(convertSelectionToString(obj), false);
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextInputLayout textInputLayoutE = e();
        if (textInputLayoutE != null && textInputLayoutE.R() && super.getHint() == null && H.e.b()) {
            setHint("");
        }
    }

    @Override // android.widget.AutoCompleteTextView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f666a.dismiss();
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), g()), View.MeasureSpec.getSize(i2)), getMeasuredHeight());
        }
    }

    @Override // android.widget.AutoCompleteTextView, android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean z2) {
        if (f()) {
            return;
        }
        super.onWindowFocusChanged(z2);
    }

    @Override // android.widget.AutoCompleteTextView
    public <T extends ListAdapter & Filterable> void setAdapter(@Nullable T t2) {
        super.setAdapter(t2);
        this.f666a.setAdapter(getAdapter());
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundDrawable(Drawable drawable) {
        super.setDropDownBackgroundDrawable(drawable);
        ListPopupWindow listPopupWindow = this.f666a;
        if (listPopupWindow != null) {
            listPopupWindow.setBackgroundDrawable(drawable);
        }
    }

    public void setDropDownBackgroundTint(@ColorInt int i2) {
        setDropDownBackgroundTintList(ColorStateList.valueOf(i2));
    }

    public void setDropDownBackgroundTintList(@Nullable ColorStateList colorStateList) {
        this.f671f = colorStateList;
        Drawable dropDownBackground = getDropDownBackground();
        if (dropDownBackground instanceof O.g) {
            ((O.g) dropDownBackground).T(this.f671f);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setOnItemSelectedListener(@Nullable AdapterView.OnItemSelectedListener onItemSelectedListener) {
        super.setOnItemSelectedListener(onItemSelectedListener);
        this.f666a.setOnItemSelectedListener(getOnItemSelectedListener());
    }

    @Override // android.widget.TextView
    public void setRawInputType(int i2) {
        super.setRawInputType(i2);
        h();
    }

    public void setSimpleItemSelectedColor(int i2) {
        this.f672g = i2;
        if (getAdapter() instanceof b) {
            ((b) getAdapter()).f();
        }
    }

    public void setSimpleItemSelectedRippleColor(@Nullable ColorStateList colorStateList) {
        this.f673h = colorStateList;
        if (getAdapter() instanceof b) {
            ((b) getAdapter()).f();
        }
    }

    public void setSimpleItems(@ArrayRes int i2) {
        setSimpleItems(getResources().getStringArray(i2));
    }

    @Override // android.widget.AutoCompleteTextView
    public void showDropDown() {
        if (f()) {
            this.f666a.show();
        } else {
            super.showDropDown();
        }
    }

    public u(Context context, AttributeSet attributeSet, int i2) {
        super(S.a.c(context, attributeSet, i2, 0), attributeSet, i2);
        this.f668c = new Rect();
        Context context2 = getContext();
        TypedArray typedArrayI = H.k.i(context2, attributeSet, t.j.S1, i2, t.i.f6679b, new int[0]);
        int i3 = t.j.T1;
        if (typedArrayI.hasValue(i3) && typedArrayI.getInt(i3, 0) == 0) {
            setKeyListener(null);
        }
        this.f669d = typedArrayI.getResourceId(t.j.W1, t.g.f6646j);
        this.f670e = typedArrayI.getDimensionPixelOffset(t.j.U1, t.c.f6549Q);
        int i4 = t.j.V1;
        if (typedArrayI.hasValue(i4)) {
            this.f671f = ColorStateList.valueOf(typedArrayI.getColor(i4, 0));
        }
        this.f672g = typedArrayI.getColor(t.j.X1, 0);
        this.f673h = L.c.a(context2, typedArrayI, t.j.Y1);
        this.f667b = (AccessibilityManager) context2.getSystemService("accessibility");
        ListPopupWindow listPopupWindow = new ListPopupWindow(context2);
        this.f666a = listPopupWindow;
        listPopupWindow.setModal(true);
        listPopupWindow.setAnchorView(this);
        listPopupWindow.setInputMethodMode(2);
        listPopupWindow.setAdapter(getAdapter());
        listPopupWindow.setOnItemClickListener(new a());
        int i5 = t.j.Z1;
        if (typedArrayI.hasValue(i5)) {
            setSimpleItems(typedArrayI.getResourceId(i5, 0));
        }
        typedArrayI.recycle();
    }

    public void setSimpleItems(@NonNull String[] strArr) {
        setAdapter(new b(getContext(), this.f669d, strArr));
    }
}
