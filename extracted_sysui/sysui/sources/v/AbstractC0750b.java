package v;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;

/* JADX INFO: renamed from: v.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0750b extends AbstractC0751c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f6910d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f6911e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f6912f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6913g;

    public AbstractC0750b() {
        this.f6910d = new Rect();
        this.f6911e = new Rect();
        this.f6912f = 0;
    }

    public static int h(int i2) {
        if (i2 == 0) {
            return 8388659;
        }
        return i2;
    }

    @Override // v.AbstractC0751c
    public void b(CoordinatorLayout coordinatorLayout, View view, int i2) {
        View viewC = c(coordinatorLayout.getDependencies(view));
        if (viewC == null) {
            super.b(coordinatorLayout, view, i2);
            this.f6912f = 0;
            return;
        }
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
        Rect rect = this.f6910d;
        rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, viewC.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, ((coordinatorLayout.getHeight() + viewC.getBottom()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
        WindowInsetsCompat lastWindowInsets = coordinatorLayout.getLastWindowInsets();
        if (lastWindowInsets != null && ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(view)) {
            rect.left += lastWindowInsets.getSystemWindowInsetLeft();
            rect.right -= lastWindowInsets.getSystemWindowInsetRight();
        }
        Rect rect2 = this.f6911e;
        GravityCompat.apply(h(layoutParams.gravity), view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i2);
        int iD = d(viewC);
        view.layout(rect2.left, rect2.top - iD, rect2.right, rect2.bottom - iD);
        this.f6912f = rect2.top - viewC.getBottom();
    }

    public abstract View c(List list);

    public final int d(View view) {
        if (this.f6913g == 0) {
            return 0;
        }
        float fE = e(view);
        int i2 = this.f6913g;
        return MathUtils.clamp((int) (fE * i2), 0, i2);
    }

    public abstract float e(View view);

    public int f(View view) {
        return view.getMeasuredHeight();
    }

    public final int g() {
        return this.f6912f;
    }

    public final void i(int i2) {
        this.f6913g = i2;
    }

    public boolean j() {
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4, int i5) {
        View viewC;
        WindowInsetsCompat lastWindowInsets;
        int i6 = view.getLayoutParams().height;
        if ((i6 != -1 && i6 != -2) || (viewC = c(coordinatorLayout.getDependencies(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i4);
        if (size <= 0) {
            size = coordinatorLayout.getHeight();
        } else if (ViewCompat.getFitsSystemWindows(viewC) && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
            size += lastWindowInsets.getSystemWindowInsetTop() + lastWindowInsets.getSystemWindowInsetBottom();
        }
        int iF = size + f(viewC);
        int measuredHeight = viewC.getMeasuredHeight();
        if (j()) {
            view.setTranslationY(-measuredHeight);
        } else {
            view.setTranslationY(0.0f);
            iF -= measuredHeight;
        }
        coordinatorLayout.onMeasureChild(view, i2, i3, View.MeasureSpec.makeMeasureSpec(iF, i6 == -1 ? BasicMeasure.EXACTLY : Integer.MIN_VALUE), i5);
        return true;
    }

    public AbstractC0750b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6910d = new Rect();
        this.f6911e = new Rect();
        this.f6912f = 0;
    }
}
