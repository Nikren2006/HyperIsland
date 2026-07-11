package miuix.viewpager.widget;

import android.util.Pair;
import android.view.View;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class SharedElementTransformer implements OnPageChangeListener {
    private PagerAdapter mPagerAdapter;
    private Set<Pair<Integer, Integer>> mSharedElementIds = new HashSet();

    public SharedElementTransformer(PagerAdapter pagerAdapter) {
        this.mPagerAdapter = pagerAdapter;
    }

    private void modifyPositions(View view, View view2, View view3, View view4, View view5, float f2) {
        float left = view2.getLeft() - view.getLeft();
        float top = view2.getTop() - view.getTop();
        float left2 = view4.getLeft() - view3.getLeft();
        float width = view.getWidth();
        float height = view.getHeight();
        float width2 = view2.getWidth();
        float height2 = view2.getHeight();
        float f3 = width2 - width;
        float f4 = height2 - height;
        int id = view.getId();
        int id2 = view2.getId();
        if (-1.0f < f2) {
            if (f2 < 1.0f) {
                float f5 = -f2;
                float f6 = (top + (f4 / 2.0f)) * f5;
                float f7 = (left + left2 + (f3 / 2.0f)) * f5;
                if (view5.findViewById(id) != null) {
                    view.setTranslationX(f7);
                    view.setTranslationY(f6);
                    float f8 = width == 0.0f ? 1.0f : ((f3 * f5) + width) / width;
                    float f9 = height == 0.0f ? 1.0f : ((f4 * f5) + height) / height;
                    view.setScaleX(f8);
                    view.setScaleY(f9);
                }
                if (view5.findViewById(id2) != null) {
                    view2.setTranslationX(f7);
                    view2.setTranslationY(f6);
                    float f10 = width2 == 0.0f ? 1.0f : ((f3 * f5) + width2) / width2;
                    float f11 = height2 != 0.0f ? ((f4 * f5) + height2) / height2 : 1.0f;
                    view2.setScaleX(f10);
                    view2.setScaleY(f11);
                }
            }
        }
    }

    private void resetView(int i2) {
        View view;
        PagerAdapter pagerAdapter = this.mPagerAdapter;
        if (pagerAdapter == null || (view = pagerAdapter.getView(i2)) == null) {
            return;
        }
        for (Pair<Integer, Integer> pair : this.mSharedElementIds) {
            View viewFindViewById = view.findViewById(((Integer) pair.first).intValue());
            View viewFindViewById2 = view.findViewById(((Integer) pair.second).intValue());
            if (viewFindViewById != null) {
                viewFindViewById.setTranslationX(0.0f);
                viewFindViewById.setTranslationY(0.0f);
                viewFindViewById.setScaleX(1.0f);
                viewFindViewById.setScaleY(1.0f);
            }
            if (viewFindViewById2 != null) {
                viewFindViewById2.setTranslationX(0.0f);
                viewFindViewById2.setTranslationY(0.0f);
                viewFindViewById2.setScaleX(1.0f);
                viewFindViewById2.setScaleY(1.0f);
            }
        }
    }

    public void addElement(int i2, int i3) {
        this.mSharedElementIds.add(new Pair<>(Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    public void clearElements() {
        this.mSharedElementIds.clear();
    }

    @Override // miuix.viewpager.widget.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // miuix.viewpager.widget.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
        View viewFindViewById;
        View viewFindViewById2;
        int i4 = i2 + 1;
        PagerAdapter pagerAdapter = this.mPagerAdapter;
        if (pagerAdapter == null) {
            return;
        }
        if (i4 >= pagerAdapter.getCount()) {
            resetView(i2);
            return;
        }
        for (Pair<Integer, Integer> pair : this.mSharedElementIds) {
            Integer num = (Integer) pair.first;
            Integer num2 = (Integer) pair.second;
            View view = this.mPagerAdapter.getView(i2);
            View view2 = this.mPagerAdapter.getView(i4);
            if (view == null || view2 == null) {
                viewFindViewById = null;
                viewFindViewById2 = null;
            } else {
                viewFindViewById2 = view.findViewById(num.intValue());
                viewFindViewById = view2.findViewById(num2.intValue());
                if (viewFindViewById2 == null) {
                    viewFindViewById2 = null;
                }
                if (viewFindViewById == null) {
                    viewFindViewById = null;
                }
            }
            if (viewFindViewById2 != null && viewFindViewById != null) {
                View view3 = viewFindViewById2;
                View view4 = viewFindViewById;
                modifyPositions(view3, view4, view, view2, view, -f2);
                modifyPositions(view3, view4, view, view2, view2, 1.0f - f2);
            }
        }
    }

    @Override // miuix.viewpager.widget.OnPageChangeListener
    public void onPageSelected(int i2) {
    }

    public void removeElement(int i2, int i3) {
        this.mSharedElementIds.remove(new Pair(Integer.valueOf(i2), Integer.valueOf(i3)));
    }
}
