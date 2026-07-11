package miuix.appcompat.internal.app.widget;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
class DynamicFragmentPagerAdapter extends PagerAdapter {
    private Context mContext;
    private FragmentManager mFragmentManager;
    private ArrayList<FragmentInfo> mFragmentInfos = new ArrayList<>();
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;

    public class FragmentInfo {
        Bundle args;
        Class<? extends Fragment> clazz;
        Fragment fragment = null;
        boolean hasActionMenu;
        ActionBar.Tab tab;
        String tag;

        public FragmentInfo(String str, Class<? extends Fragment> cls, Bundle bundle, ActionBar.Tab tab, boolean z2) {
            this.tag = str;
            this.clazz = cls;
            this.args = bundle;
            this.tab = tab;
            this.hasActionMenu = z2;
        }
    }

    public DynamicFragmentPagerAdapter(Context context, FragmentManager fragmentManager) {
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
    }

    private void removeAllFragmentFromManager() {
        FragmentTransaction fragmentTransactionBeginTransaction = this.mFragmentManager.beginTransaction();
        int size = this.mFragmentInfos.size();
        for (int i2 = 0; i2 < size; i2++) {
            fragmentTransactionBeginTransaction.remove(getFragment(i2, false));
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        this.mFragmentManager.executePendingTransactions();
    }

    private void removeFragmentFromManager(Fragment fragment) {
        FragmentManager fragmentManager;
        if (fragment == null || (fragmentManager = fragment.getFragmentManager()) == null) {
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        fragmentTransactionBeginTransaction.remove(fragment);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    public int addFragment(String str, Class<? extends Fragment> cls, Bundle bundle, ActionBar.Tab tab, boolean z2) {
        if (isRTL()) {
            this.mFragmentInfos.add(0, new FragmentInfo(str, cls, bundle, tab, z2));
        } else {
            this.mFragmentInfos.add(new FragmentInfo(str, cls, bundle, tab, z2));
        }
        notifyDataSetChanged();
        return this.mFragmentInfos.size() - 1;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i2, Object obj) {
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        this.mCurTransaction.detach((Fragment) obj);
    }

    public int findPositionByTag(String str) {
        int size = this.mFragmentInfos.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.mFragmentInfos.get(i2).tag.equals(str)) {
                return toIndexForRTL(i2);
            }
        }
        return -1;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void finishUpdate(@NonNull ViewGroup viewGroup) {
        FragmentTransaction fragmentTransaction = this.mCurTransaction;
        if (fragmentTransaction != null) {
            fragmentTransaction.commitAllowingStateLoss();
            this.mCurTransaction = null;
            this.mFragmentManager.executePendingTransactions();
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mFragmentInfos.size();
    }

    public Fragment getFragment(int i2, boolean z2) {
        return getFragment(i2, z2, true);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        int size = this.mFragmentInfos.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (obj == this.mFragmentInfos.get(i2).fragment) {
                return i2;
            }
        }
        return -2;
    }

    public ActionBar.Tab getTabAt(int i2) {
        return this.mFragmentInfos.get(i2).tab;
    }

    public boolean hasActionMenu(int i2) {
        if (i2 < 0 || i2 >= this.mFragmentInfos.size()) {
            return false;
        }
        return this.mFragmentInfos.get(i2).hasActionMenu;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        if (this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        Fragment fragment = getFragment(i2, true, false);
        if (fragment.getFragmentManager() != null) {
            this.mCurTransaction.attach(fragment);
        } else {
            this.mCurTransaction.add(viewGroup.getId(), fragment, this.mFragmentInfos.get(i2).tag);
        }
        if (fragment != this.mCurrentPrimaryItem) {
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }
        return fragment;
    }

    public boolean isRTL() {
        return this.mContext.getResources().getConfiguration().getLayoutDirection() == 1;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    public void removeAllFragment() {
        removeAllFragmentFromManager();
        this.mFragmentInfos.clear();
        this.mCurrentPrimaryItem = null;
        notifyDataSetChanged();
    }

    public int removeFragment(ActionBar.Tab tab) {
        int size = this.mFragmentInfos.size();
        for (int i2 = 0; i2 < size; i2++) {
            FragmentInfo fragmentInfo = this.mFragmentInfos.get(i2);
            if (fragmentInfo.tab == tab) {
                removeFragmentFromManager(fragmentInfo.fragment);
                this.mFragmentInfos.remove(i2);
                if (this.mCurrentPrimaryItem == fragmentInfo.fragment) {
                    this.mCurrentPrimaryItem = null;
                }
                notifyDataSetChanged();
                return toIndexForRTL(i2);
            }
        }
        return -1;
    }

    public void removeFragmentAt(int i2) {
        removeFragmentFromManager(getFragment(i2, false));
        this.mFragmentInfos.remove(toIndexForRTL(i2));
        notifyDataSetChanged();
    }

    public void replaceFragmentAt(String str, int i2, Class<? extends Fragment> cls, Bundle bundle, ActionBar.Tab tab, boolean z2) {
        removeFragmentFromManager(getFragment(i2, false));
        this.mFragmentInfos.remove(toIndexForRTL(i2));
        FragmentInfo fragmentInfo = new FragmentInfo(str, cls, bundle, tab, z2);
        if (!isRTL()) {
            this.mFragmentInfos.add(i2, fragmentInfo);
        } else if (i2 >= this.mFragmentInfos.size()) {
            this.mFragmentInfos.add(0, fragmentInfo);
        } else {
            this.mFragmentInfos.add(toIndexForRTL(i2) + 1, fragmentInfo);
        }
        notifyDataSetChanged();
    }

    public void setFragmentActionMenuAt(int i2, boolean z2) {
        FragmentInfo fragmentInfo = this.mFragmentInfos.get(toIndexForRTL(i2));
        if (fragmentInfo.hasActionMenu != z2) {
            fragmentInfo.hasActionMenu = z2;
            notifyDataSetChanged();
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(@NonNull ViewGroup viewGroup, int i2, Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.mCurrentPrimaryItem;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.setMenuVisibility(false);
                this.mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            this.mCurrentPrimaryItem = fragment;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void startUpdate(@NonNull ViewGroup viewGroup) {
    }

    public int toIndexForRTL(int i2) {
        if (!isRTL()) {
            return i2;
        }
        int size = this.mFragmentInfos.size() - 1;
        if (size > i2) {
            return size - i2;
        }
        return 0;
    }

    public Fragment getFragment(int i2, boolean z2, boolean z3) {
        Class<? extends Fragment> cls;
        if (this.mFragmentInfos.isEmpty() || i2 < 0 || i2 > this.mFragmentInfos.size() - 1) {
            return null;
        }
        ArrayList<FragmentInfo> arrayList = this.mFragmentInfos;
        if (z3) {
            i2 = toIndexForRTL(i2);
        }
        FragmentInfo fragmentInfo = arrayList.get(i2);
        if (fragmentInfo.fragment == null) {
            Fragment fragmentFindFragmentByTag = this.mFragmentManager.findFragmentByTag(fragmentInfo.tag);
            fragmentInfo.fragment = fragmentFindFragmentByTag;
            if (fragmentFindFragmentByTag == null && z2 && (cls = fragmentInfo.clazz) != null) {
                fragmentInfo.fragment = Fragment.instantiate(this.mContext, cls.getName(), fragmentInfo.args);
                fragmentInfo.clazz = null;
                fragmentInfo.args = null;
            }
        }
        return fragmentInfo.fragment;
    }

    public int addFragment(String str, int i2, Class<? extends Fragment> cls, Bundle bundle, ActionBar.Tab tab, boolean z2) {
        FragmentInfo fragmentInfo = new FragmentInfo(str, cls, bundle, tab, z2);
        if (isRTL()) {
            if (i2 >= this.mFragmentInfos.size()) {
                this.mFragmentInfos.add(0, fragmentInfo);
            } else {
                this.mFragmentInfos.add(toIndexForRTL(i2) + 1, fragmentInfo);
            }
        } else {
            this.mFragmentInfos.add(i2, fragmentInfo);
        }
        notifyDataSetChanged();
        return i2;
    }

    public int removeFragment(Fragment fragment) {
        int size = this.mFragmentInfos.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (getFragment(i2, false, false) == fragment) {
                removeFragmentFromManager(fragment);
                this.mFragmentInfos.remove(i2);
                if (this.mCurrentPrimaryItem == fragment) {
                    this.mCurrentPrimaryItem = null;
                }
                notifyDataSetChanged();
                return toIndexForRTL(i2);
            }
        }
        return -1;
    }
}
