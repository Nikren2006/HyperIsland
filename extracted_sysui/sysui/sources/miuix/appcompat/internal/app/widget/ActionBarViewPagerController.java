package miuix.appcompat.internal.app.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.OriginalViewPager;
import java.util.ArrayList;
import java.util.Iterator;
import miuix.appcompat.R;
import miuix.appcompat.app.ActionBar;
import miuix.appcompat.internal.app.widget.ActionBarImpl;
import miuix.internal.util.DeviceHelper;
import miuix.springback.view.SpringBackLayout;
import miuix.viewpager.widget.ViewPager;

/* JADX INFO: loaded from: classes3.dex */
public class ActionBarViewPagerController {
    private ActionBarImpl mActionBar;
    private ObjectAnimator mActionMenuChangeAnimator;
    private ActionMenuChangeAnimatorObject mActionMenuChangeAnimatorObject;
    private ArrayList<ActionBar.FragmentViewPagerChangeListener> mListeners;
    private DynamicFragmentPagerAdapter mPagerAdapter;
    private SpringBackLayout mSpringBackLayout;
    private ActionBar.TabListener mTabListener = new ActionBar.TabListener() { // from class: miuix.appcompat.internal.app.widget.ActionBarViewPagerController.1
        @Override // androidx.appcompat.app.ActionBar.TabListener
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        }

        @Override // androidx.appcompat.app.ActionBar.TabListener
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            int count = ActionBarViewPagerController.this.mPagerAdapter.getCount();
            for (int i2 = 0; i2 < count; i2++) {
                if (ActionBarViewPagerController.this.mPagerAdapter.getTabAt(i2) == tab) {
                    ActionBarViewPagerController.this.mViewPager.setCurrentItem(i2, tab instanceof ActionBarImpl.TabImpl ? ((ActionBarImpl.TabImpl) tab).mWithAnim : true);
                    return;
                }
            }
        }

        @Override // androidx.appcompat.app.ActionBar.TabListener
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        }
    };
    private ViewPager mViewPager;
    private View mViewPagerDecor;

    public class ActionMenuChangeAnimatorObject {
        private int mPosition;
        private boolean mShowActionMenu;

        public ActionMenuChangeAnimatorObject() {
        }

        public void reset(int i2, boolean z2) {
            this.mPosition = i2;
            this.mShowActionMenu = z2;
        }

        public void setValue(float f2) {
            if (ActionBarViewPagerController.this.mListeners != null) {
                for (ActionBar.FragmentViewPagerChangeListener fragmentViewPagerChangeListener : ActionBarViewPagerController.this.mListeners) {
                    if (fragmentViewPagerChangeListener instanceof ActionBarContainer) {
                        boolean z2 = this.mShowActionMenu;
                        fragmentViewPagerChangeListener.onPageScrolled(this.mPosition, 1.0f - f2, z2, !z2);
                    }
                }
            }
        }
    }

    public static class ScrollStatus {
        private static final float THRESHOLD = 1.0E-4f;
        int mFromPos;
        private float mOffsetAtScroll;
        private int mPosAtScroll;
        boolean mScrollBegin;
        boolean mScrollEnd;
        int mToPos;

        private ScrollStatus() {
            this.mPosAtScroll = -1;
        }

        private void onScrollBegin(int i2, float f2) {
            this.mScrollBegin = false;
            boolean z2 = f2 > this.mOffsetAtScroll;
            this.mFromPos = z2 ? i2 : i2 + 1;
            if (z2) {
                i2++;
            }
            this.mToPos = i2;
        }

        private void onScrollEnd() {
            this.mFromPos = this.mToPos;
            this.mPosAtScroll = -1;
            this.mOffsetAtScroll = 0.0f;
            this.mScrollEnd = true;
        }

        private void onScrollPositionChange(int i2, float f2) {
            this.mPosAtScroll = i2;
            this.mOffsetAtScroll = f2;
            this.mScrollBegin = true;
            this.mScrollEnd = false;
        }

        public void update(int i2, float f2) {
            if (f2 < THRESHOLD) {
                onScrollEnd();
            } else if (this.mPosAtScroll != i2) {
                onScrollPositionChange(i2, f2);
            } else if (this.mScrollBegin) {
                onScrollBegin(i2, f2);
            }
        }
    }

    public ActionBarViewPagerController(ActionBarImpl actionBarImpl, FragmentManager fragmentManager, Lifecycle lifecycle, boolean z2) {
        this.mActionBar = actionBarImpl;
        ActionBarOverlayLayout actionBarOverlayLayout = actionBarImpl.getActionBarOverlayLayout();
        Context context = actionBarOverlayLayout.getContext();
        int i2 = R.id.view_pager;
        View viewFindViewById = actionBarOverlayLayout.findViewById(i2);
        if (viewFindViewById instanceof ViewPager) {
            this.mViewPager = (ViewPager) viewFindViewById;
        } else {
            ViewPager viewPager = new ViewPager(context);
            this.mViewPager = viewPager;
            viewPager.setId(i2);
            SpringBackLayout springBackLayout = new SpringBackLayout(context);
            this.mSpringBackLayout = springBackLayout;
            springBackLayout.setScrollOrientation(5);
            springBackLayout.addView(this.mViewPager, new OriginalViewPager.LayoutParams());
            springBackLayout.setTarget(this.mViewPager);
            springBackLayout.setSpringBackEnable(this.mViewPager.isDraggable());
            ((ViewGroup) actionBarOverlayLayout.findViewById(android.R.id.content)).addView(springBackLayout, new ViewGroup.LayoutParams(-1, -1));
        }
        DynamicFragmentPagerAdapter dynamicFragmentPagerAdapter = new DynamicFragmentPagerAdapter(context, fragmentManager);
        this.mPagerAdapter = dynamicFragmentPagerAdapter;
        this.mViewPager.setAdapter(dynamicFragmentPagerAdapter);
        this.mViewPager.addOnPageChangeListener(new OriginalViewPager.OnPageChangeListener() { // from class: miuix.appcompat.internal.app.widget.ActionBarViewPagerController.2
            ScrollStatus mStatus = new ScrollStatus();

            @Override // androidx.viewpager.widget.OriginalViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i3) {
                if (ActionBarViewPagerController.this.mListeners != null) {
                    Iterator it = ActionBarViewPagerController.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((ActionBar.FragmentViewPagerChangeListener) it.next()).onPageScrollStateChanged(i3);
                    }
                }
            }

            @Override // androidx.viewpager.widget.OriginalViewPager.OnPageChangeListener
            public void onPageScrolled(int i3, float f2, int i4) {
                this.mStatus.update(i3, f2);
                if (this.mStatus.mScrollBegin || ActionBarViewPagerController.this.mListeners == null) {
                    return;
                }
                boolean zHasActionMenu = ActionBarViewPagerController.this.mPagerAdapter.hasActionMenu(this.mStatus.mFromPos);
                boolean zHasActionMenu2 = ActionBarViewPagerController.this.mPagerAdapter.hasActionMenu(this.mStatus.mToPos);
                if (ActionBarViewPagerController.this.mPagerAdapter.isRTL()) {
                    i3 = ActionBarViewPagerController.this.mPagerAdapter.toIndexForRTL(i3);
                    if (!this.mStatus.mScrollEnd) {
                        i3--;
                        f2 = 1.0f - f2;
                    }
                }
                Iterator it = ActionBarViewPagerController.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((ActionBar.FragmentViewPagerChangeListener) it.next()).onPageScrolled(i3, f2, zHasActionMenu, zHasActionMenu2);
                }
            }

            @Override // androidx.viewpager.widget.OriginalViewPager.OnPageChangeListener
            public void onPageSelected(int i3) {
                int indexForRTL = ActionBarViewPagerController.this.mPagerAdapter.toIndexForRTL(i3);
                ActionBarViewPagerController.this.mActionBar.setSelectedNavigationItem(indexForRTL);
                ActionBarViewPagerController.this.mPagerAdapter.setPrimaryItem((ViewGroup) ActionBarViewPagerController.this.mViewPager, i3, (Object) ActionBarViewPagerController.this.mPagerAdapter.getFragment(i3, false, false));
                if (ActionBarViewPagerController.this.mListeners != null) {
                    Iterator it = ActionBarViewPagerController.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((ActionBar.FragmentViewPagerChangeListener) it.next()).onPageSelected(indexForRTL);
                    }
                }
            }
        });
        if (z2 && DeviceHelper.isFeatureWholeAnim()) {
            addOnFragmentViewPagerChangeListener(new ViewPagerScrollEffect(this.mViewPager, this.mPagerAdapter));
        }
    }

    public int addFragmentTab(String str, ActionBar.Tab tab, Class<? extends Fragment> cls, Bundle bundle, boolean z2) {
        ((ActionBarImpl.TabImpl) tab).setInternalTabListener(this.mTabListener);
        this.mActionBar.internalAddTab(tab);
        int iAddFragment = this.mPagerAdapter.addFragment(str, cls, bundle, tab, z2);
        if (this.mPagerAdapter.isRTL()) {
            this.mViewPager.setCurrentItem(this.mPagerAdapter.getCount() - 1);
        }
        return iAddFragment;
    }

    public void addOnFragmentViewPagerChangeListener(ActionBar.FragmentViewPagerChangeListener fragmentViewPagerChangeListener) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        this.mListeners.add(fragmentViewPagerChangeListener);
    }

    public Fragment getFragmentAt(int i2) {
        return this.mPagerAdapter.getFragment(i2, true);
    }

    public int getFragmentTabCount() {
        return this.mPagerAdapter.getCount();
    }

    public int getViewPagerOffscreenPageLimit() {
        return this.mViewPager.getOffscreenPageLimit();
    }

    public void removeAllFragmentTab() {
        this.mActionBar.internalRemoveAllTabs();
        this.mPagerAdapter.removeAllFragment();
    }

    public void removeFragment(Fragment fragment) {
        int iRemoveFragment = this.mPagerAdapter.removeFragment(fragment);
        if (iRemoveFragment >= 0) {
            this.mActionBar.internalRemoveTabAt(iRemoveFragment);
        }
    }

    public void removeFragmentAt(int i2) {
        this.mPagerAdapter.removeFragmentAt(i2);
        this.mActionBar.internalRemoveTabAt(i2);
    }

    public void removeFragmentTab(String str) {
        int iFindPositionByTag = this.mPagerAdapter.findPositionByTag(str);
        if (iFindPositionByTag >= 0) {
            removeFragmentAt(iFindPositionByTag);
        }
    }

    public void removeOnFragmentViewPagerChangeListener(ActionBar.FragmentViewPagerChangeListener fragmentViewPagerChangeListener) {
        ArrayList<ActionBar.FragmentViewPagerChangeListener> arrayList = this.mListeners;
        if (arrayList != null) {
            arrayList.remove(fragmentViewPagerChangeListener);
        }
    }

    public void replaceFragmentTab(String str, int i2, Class<? extends Fragment> cls, Bundle bundle, boolean z2) {
        this.mActionBar.updateTab(i2);
        this.mPagerAdapter.replaceFragmentAt(str, i2, cls, bundle, this.mActionBar.getTabAt(i2), z2);
    }

    public void setFragmentActionMenuAt(int i2, boolean z2) {
        this.mPagerAdapter.setFragmentActionMenuAt(i2, z2);
        if (i2 == this.mViewPager.getCurrentItem()) {
            if (this.mActionMenuChangeAnimatorObject == null) {
                ActionMenuChangeAnimatorObject actionMenuChangeAnimatorObject = new ActionMenuChangeAnimatorObject();
                this.mActionMenuChangeAnimatorObject = actionMenuChangeAnimatorObject;
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(actionMenuChangeAnimatorObject, "Value", 0.0f, 1.0f);
                this.mActionMenuChangeAnimator = objectAnimatorOfFloat;
                objectAnimatorOfFloat.setDuration(DeviceHelper.isFeatureWholeAnim() ? this.mViewPager.getContext().getResources().getInteger(android.R.integer.config_shortAnimTime) : 0L);
            }
            this.mActionMenuChangeAnimatorObject.reset(i2, z2);
            this.mActionMenuChangeAnimator.start();
        }
    }

    public void setViewPagerDecor(View view) {
        View view2 = this.mViewPagerDecor;
        if (view2 != null) {
            this.mViewPager.removeView(view2);
        }
        if (view != null) {
            this.mViewPagerDecor = view;
            OriginalViewPager.LayoutParams layoutParams = new OriginalViewPager.LayoutParams();
            layoutParams.isDecor = true;
            this.mViewPager.addView(this.mViewPagerDecor, -1, layoutParams);
        }
    }

    public void setViewPagerDraggable(boolean z2) {
        this.mViewPager.setDraggable(z2);
        SpringBackLayout springBackLayout = this.mSpringBackLayout;
        if (springBackLayout != null) {
            springBackLayout.setSpringBackEnable(z2);
        }
    }

    public void setViewPagerOffscreenPageLimit(int i2) {
        this.mViewPager.setOffscreenPageLimit(i2);
    }

    public void removeFragmentTab(ActionBar.Tab tab) {
        this.mActionBar.internalRemoveTab(tab);
        this.mPagerAdapter.removeFragment(tab);
    }

    public int addFragmentTab(String str, ActionBar.Tab tab, int i2, Class<? extends Fragment> cls, Bundle bundle, boolean z2) {
        ((ActionBarImpl.TabImpl) tab).setInternalTabListener(this.mTabListener);
        this.mActionBar.internalAddTab(tab, i2);
        int iAddFragment = this.mPagerAdapter.addFragment(str, i2, cls, bundle, tab, z2);
        if (this.mPagerAdapter.isRTL()) {
            this.mViewPager.setCurrentItem(this.mPagerAdapter.getCount() - 1);
        }
        return iAddFragment;
    }
}
