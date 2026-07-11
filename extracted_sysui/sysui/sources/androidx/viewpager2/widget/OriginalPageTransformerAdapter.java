package androidx.viewpager2.widget;

import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.OriginalViewPager2;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
final class OriginalPageTransformerAdapter extends OriginalViewPager2.OnPageChangeCallback {
    private final LinearLayoutManager mLayoutManager;
    private OriginalViewPager2.PageTransformer mPageTransformer;

    public OriginalPageTransformerAdapter(LinearLayoutManager linearLayoutManager) {
        this.mLayoutManager = linearLayoutManager;
    }

    public OriginalViewPager2.PageTransformer getPageTransformer() {
        return this.mPageTransformer;
    }

    @Override // androidx.viewpager2.widget.OriginalViewPager2.OnPageChangeCallback
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager2.widget.OriginalViewPager2.OnPageChangeCallback
    public void onPageScrolled(int i2, float f2, int i3) {
        if (this.mPageTransformer == null) {
            return;
        }
        float f3 = -f2;
        for (int i4 = 0; i4 < this.mLayoutManager.getChildCount(); i4++) {
            View childAt = this.mLayoutManager.getChildAt(i4);
            if (childAt == null) {
                throw new IllegalStateException(String.format(Locale.US, "LayoutManager returned a null child at pos %d/%d while transforming pages", Integer.valueOf(i4), Integer.valueOf(this.mLayoutManager.getChildCount())));
            }
            this.mPageTransformer.transformPage(childAt, (this.mLayoutManager.getPosition(childAt) - i2) + f3);
        }
    }

    @Override // androidx.viewpager2.widget.OriginalViewPager2.OnPageChangeCallback
    public void onPageSelected(int i2) {
    }

    public void setPageTransformer(@Nullable OriginalViewPager2.PageTransformer pageTransformer) {
        this.mPageTransformer = pageTransformer;
    }
}
