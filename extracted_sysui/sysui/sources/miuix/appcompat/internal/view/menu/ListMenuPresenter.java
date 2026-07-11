package miuix.appcompat.internal.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;
import miuix.appcompat.R;
import miuix.appcompat.internal.view.menu.MenuPresenter;
import miuix.appcompat.internal.view.menu.MenuView;
import miuix.internal.util.AnimHelper;

/* JADX INFO: loaded from: classes3.dex */
public class ListMenuPresenter implements MenuPresenter, AdapterView.OnItemClickListener {
    public static final String VIEWS_TAG = "miuix:menu:list";
    MenuAdapter mAdapter;
    private MenuPresenter.Callback mCallback;
    Context mContext;
    private int mId;
    LayoutInflater mInflater;
    private int mItemIndexOffset;
    int mItemLayoutRes;
    MenuBuilder mMenu;
    int mMenuLayout;
    ExpandedMenuView mMenuView;
    int mThemeRes;

    public class MenuAdapter extends BaseAdapter {
        private int mExpandedIndex = -1;

        public MenuAdapter() {
            findExpandedIndex();
        }

        public void findExpandedIndex() {
            MenuItemImpl expandedItem = ListMenuPresenter.this.mMenu.getExpandedItem();
            if (expandedItem != null) {
                ArrayList<MenuItemImpl> nonActionItems = ListMenuPresenter.this.mMenu.getNonActionItems();
                int size = nonActionItems.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (nonActionItems.get(i2) == expandedItem) {
                        this.mExpandedIndex = i2;
                        return;
                    }
                }
            }
            this.mExpandedIndex = -1;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            int size = ListMenuPresenter.this.mMenu.getNonActionItems().size() - ListMenuPresenter.this.mItemIndexOffset;
            return this.mExpandedIndex < 0 ? size : size - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                ListMenuPresenter listMenuPresenter = ListMenuPresenter.this;
                view = listMenuPresenter.mInflater.inflate(listMenuPresenter.mItemLayoutRes, viewGroup, false);
                AnimHelper.addPressAnimWithBg(view);
            }
            ((MenuView.ItemView) view).initialize(getItem(i2), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            findExpandedIndex();
            super.notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public MenuItemImpl getItem(int i2) {
            ArrayList<MenuItemImpl> nonActionItems = ListMenuPresenter.this.mMenu.getNonActionItems();
            int i3 = i2 + ListMenuPresenter.this.mItemIndexOffset;
            int i4 = this.mExpandedIndex;
            if (i4 >= 0 && i3 >= i4) {
                i3++;
            }
            return nonActionItems.get(i3);
        }
    }

    public ListMenuPresenter(Context context, int i2) {
        this(i2, 0);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    public ListAdapter getAdapter() {
        if (this.mAdapter == null) {
            this.mAdapter = new MenuAdapter();
        }
        return this.mAdapter;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public int getId() {
        return this.mId;
    }

    public int getItemIndexOffset() {
        return this.mItemIndexOffset;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup) {
        if (this.mAdapter == null) {
            this.mAdapter = new MenuAdapter();
        }
        if (this.mAdapter.isEmpty()) {
            return null;
        }
        if (this.mMenuView == null) {
            ExpandedMenuView expandedMenuView = (ExpandedMenuView) this.mInflater.inflate(this.mMenuLayout, viewGroup, false);
            this.mMenuView = expandedMenuView;
            expandedMenuView.setAdapter((ListAdapter) this.mAdapter);
            this.mMenuView.setOnItemClickListener(this);
        }
        return this.mMenuView;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        if (this.mThemeRes != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, this.mThemeRes);
            this.mContext = contextThemeWrapper;
            this.mInflater = LayoutInflater.from(contextThemeWrapper);
        } else if (this.mContext != null) {
            this.mContext = context;
            if (this.mInflater == null) {
                this.mInflater = LayoutInflater.from(context);
            }
        }
        MenuBuilder menuBuilder2 = this.mMenu;
        if (menuBuilder2 != null) {
            menuBuilder2.removeMenuPresenter(this);
        }
        this.mMenu = menuBuilder;
        MenuAdapter menuAdapter = this.mAdapter;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z2) {
        MenuPresenter.Callback callback = this.mCallback;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z2);
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        this.mMenu.performItemAction(this.mAdapter.getItem(i2), 0);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
        restoreHierarchyState((Bundle) parcelable);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        if (this.mMenuView == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        saveHierarchyState(bundle);
        return bundle;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        new MenuDialogHelper(subMenuBuilder).show(null);
        MenuPresenter.Callback callback = this.mCallback;
        if (callback == null) {
            return true;
        }
        callback.onOpenSubMenu(subMenuBuilder);
        return true;
    }

    public void restoreHierarchyState(Bundle bundle) {
        SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(VIEWS_TAG);
        if (sparseParcelableArray != null) {
            this.mMenuView.restoreHierarchyState(sparseParcelableArray);
        }
    }

    public void saveHierarchyState(Bundle bundle) {
        SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.mMenuView;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray(VIEWS_TAG, sparseArray);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.mCallback = callback;
    }

    public void setId(int i2) {
        this.mId = i2;
    }

    public void setItemIndexOffset(int i2) {
        this.mItemIndexOffset = i2;
        if (this.mMenuView != null) {
            updateMenuView(false);
        }
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void updateMenuView(boolean z2) {
        MenuAdapter menuAdapter = this.mAdapter;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }

    public ListMenuPresenter(Context context, int i2, int i3) {
        this(i2, i3, 0);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public ListMenuPresenter(int i2, int i3) {
        this(R.layout.miuix_appcompat_expanded_menu_layout, i2, i3);
    }

    public ListMenuPresenter(int i2, int i3, int i4) {
        this.mItemLayoutRes = i3;
        this.mMenuLayout = i2;
        this.mThemeRes = i4;
    }
}
