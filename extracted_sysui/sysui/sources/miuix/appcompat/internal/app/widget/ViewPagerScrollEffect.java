package miuix.appcompat.internal.app.widget;

import android.R;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Iterator;
import miuix.appcompat.app.ActionBar;
import miuix.internal.util.ViewUtils;
import miuix.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes3.dex */
class ViewPagerScrollEffect implements ActionBar.FragmentViewPagerChangeListener {
    DynamicFragmentPagerAdapter mPagerAdapter;
    ViewPager mViewPager;
    Rect sRect = new Rect();
    ArrayList<View> sList = new ArrayList<>();
    int mBaseItem = -1;
    boolean mBaseItemUpdated = true;
    int mScrollBasePosition = -1;
    int mIncomingPosition = -1;
    ViewGroup mListView = null;

    public ViewPagerScrollEffect(ViewPager viewPager, DynamicFragmentPagerAdapter dynamicFragmentPagerAdapter) {
        this.mViewPager = viewPager;
        this.mPagerAdapter = dynamicFragmentPagerAdapter;
    }

    public void clearTranslation(ArrayList<View> arrayList, ViewGroup viewGroup) {
        for (View view : arrayList) {
            if (viewGroup.indexOfChild(view) == -1 && view.getTranslationX() != 0.0f) {
                view.setTranslationX(0.0f);
            }
        }
    }

    public int computOffset(int i2, int i3, int i4, float f2) {
        float f3 = (i2 < i4 ? (i2 * i3) / i4 : i3) + ((0.1f - ((f2 * f2) / 0.9f)) * i3);
        if (f3 > 0.0f) {
            return (int) f3;
        }
        return 0;
    }

    public void fillList(ViewGroup viewGroup, ArrayList<View> arrayList) {
        clearTranslation(arrayList, viewGroup);
        arrayList.clear();
        ViewUtils.getContentRect(viewGroup, this.sRect);
        if (this.sRect.isEmpty()) {
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getVisibility() != 8 || childAt.getHeight() > 0) {
                arrayList.add(childAt);
            }
        }
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageScrollStateChanged(int i2) {
        if (i2 == 0) {
            this.mBaseItem = this.mViewPager.getCurrentItem();
            this.mBaseItemUpdated = true;
            ViewGroup viewGroup = this.mListView;
            if (viewGroup != null) {
                clearTranslation(viewGroup);
            }
        }
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageScrolled(int i2, float f2, boolean z2, boolean z3) {
        if (f2 == 0.0f) {
            this.mBaseItem = i2;
            this.mBaseItemUpdated = true;
            ViewGroup viewGroup = this.mListView;
            if (viewGroup != null) {
                clearTranslation(viewGroup);
            }
        }
        if (this.mScrollBasePosition != i2) {
            int i3 = this.mBaseItem;
            if (i3 < i2) {
                this.mBaseItem = i2;
            } else {
                int i4 = i2 + 1;
                if (i3 > i4) {
                    this.mBaseItem = i4;
                }
            }
            this.mScrollBasePosition = i2;
            this.mBaseItemUpdated = true;
            ViewGroup viewGroup2 = this.mListView;
            if (viewGroup2 != null) {
                clearTranslation(viewGroup2);
            }
        }
        if (f2 > 0.0f) {
            if (this.mBaseItemUpdated) {
                this.mBaseItemUpdated = false;
                if (this.mBaseItem != i2 || i2 >= this.mPagerAdapter.getCount() - 1) {
                    this.mIncomingPosition = i2;
                } else {
                    this.mIncomingPosition = i2 + 1;
                }
                Fragment fragment = this.mPagerAdapter.getFragment(this.mIncomingPosition, false);
                this.mListView = null;
                if (fragment != null && fragment.getView() != null) {
                    View viewFindViewById = fragment.getView().findViewById(R.id.list);
                    if (viewFindViewById instanceof ViewGroup) {
                        this.mListView = (ViewGroup) viewFindViewById;
                    }
                }
            }
            if (this.mIncomingPosition == i2) {
                f2 = 1.0f - f2;
            }
            float f3 = f2;
            ViewGroup viewGroup3 = this.mListView;
            if (viewGroup3 != null) {
                translateView(viewGroup3, viewGroup3.getWidth(), this.mListView.getHeight(), f3, this.mIncomingPosition != i2);
            }
        }
    }

    @Override // miuix.appcompat.app.ActionBar.FragmentViewPagerChangeListener
    public void onPageSelected(int i2) {
    }

    public void translateView(ViewGroup viewGroup, int i2, int i3, float f2, boolean z2) {
        fillList(viewGroup, this.sList);
        if (this.sList.isEmpty()) {
            return;
        }
        int i4 = 0;
        int top = this.sList.get(0).getTop();
        int i5 = Integer.MAX_VALUE;
        for (View view : this.sList) {
            if (i5 != view.getTop()) {
                int top2 = view.getTop();
                int iComputOffset = computOffset(top2 - top, i2, i3, f2);
                if (!z2) {
                    iComputOffset = -iComputOffset;
                }
                int i6 = iComputOffset;
                i5 = top2;
                i4 = i6;
            }
            view.setTranslationX(i4);
        }
    }

    public void clearTranslation(ViewGroup viewGroup) {
        fillList(viewGroup, this.sList);
        if (this.sList.isEmpty()) {
            return;
        }
        Iterator<View> it = this.sList.iterator();
        while (it.hasNext()) {
            it.next().setTranslationX(0.0f);
        }
    }
}
