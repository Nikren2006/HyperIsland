package miuix.androidbasewidget.widget;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.OriginalViewPager2;

/* JADX INFO: loaded from: classes4.dex */
public final class MarginPageTransformer implements OriginalViewPager2.PageTransformer {
    private final int mMarginPx;

    @SuppressLint({"RestrictedApi"})
    public MarginPageTransformer(@Px int i2) {
        Preconditions.checkArgumentNonnegative(i2, "Margin must be non-negative");
        this.mMarginPx = i2;
    }

    private OriginalViewPager2 requireViewPager(@NonNull View view) {
        ViewParent parent = view.getParent();
        ViewParent parent2 = parent.getParent();
        if ((parent instanceof RecyclerView) && (parent2 instanceof OriginalViewPager2)) {
            return (OriginalViewPager2) parent2;
        }
        throw new IllegalStateException("Expected the page view to be managed by a ViewPager2 instance.");
    }

    @Override // androidx.viewpager2.widget.OriginalViewPager2.PageTransformer
    public void transformPage(@NonNull View view, float f2) {
        OriginalViewPager2 originalViewPager2RequireViewPager = requireViewPager(view);
        float f3 = this.mMarginPx * f2;
        if (originalViewPager2RequireViewPager.getOrientation() != 0) {
            view.setTranslationY(f3);
            return;
        }
        if (originalViewPager2RequireViewPager.isRtl()) {
            f3 = -f3;
        }
        view.setTranslationX(f3);
    }
}
