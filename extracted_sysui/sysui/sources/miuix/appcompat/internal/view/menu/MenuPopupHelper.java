package miuix.appcompat.internal.view.menu;

import android.content.Context;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import java.util.ArrayList;
import miuix.appcompat.R;
import miuix.appcompat.internal.view.menu.MenuPresenter;
import miuix.appcompat.internal.view.menu.MenuView;
import miuix.internal.util.AnimHelper;
import miuix.internal.util.TaggingDrawableUtil;

/* JADX INFO: loaded from: classes3.dex */
public class MenuPopupHelper implements AdapterView.OnItemClickListener, View.OnKeyListener, PopupWindow.OnDismissListener, MenuPresenter {
    private static final int ITEM_LAYOUT = R.layout.miuix_appcompat_popup_menu_item_layout;
    private MenuAdapter mAdapter;
    private View mAnchorView;
    private Context mContext;
    private View mFenceDecor;
    boolean mForceShowIcon;
    private LayoutInflater mInflater;
    private MenuBuilder mMenu;
    private int mMenuItemLayout;
    private boolean mOverflowOnly;
    private miuix.popupwidget.widget.PopupWindow mPopup;
    private int mPopupAnimationGravity;
    private int mPopupHorizontalOffset;
    private int mPopupMaxHeight;
    private int mPopupVerticalOffset;
    private MenuPresenter.Callback mPresenterCallback;

    public class MenuAdapter extends BaseAdapter {
        private MenuBuilder mAdapterMenu;
        private int mExpandedIndex = -1;

        public MenuAdapter(MenuBuilder menuBuilder) {
            this.mAdapterMenu = menuBuilder;
            findExpandedIndex();
        }

        public void findExpandedIndex() {
            MenuItemImpl expandedItem = MenuPopupHelper.this.mMenu.getExpandedItem();
            if (expandedItem != null) {
                ArrayList<MenuItemImpl> nonActionItems = MenuPopupHelper.this.mMenu.getNonActionItems();
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
            return this.mExpandedIndex < 0 ? (MenuPopupHelper.this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems()).size() : r0.size() - 1;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = MenuPopupHelper.this.mInflater.inflate(MenuPopupHelper.this.mMenuItemLayout, viewGroup, false);
                AnimHelper.addItemPressEffect(view);
            }
            TaggingDrawableUtil.updateItemPadding(view, i2, getCount());
            MenuView.ItemView itemView = (MenuView.ItemView) view;
            if (MenuPopupHelper.this.mForceShowIcon) {
                ((ListMenuItemView) view).setForceShowIcon(true);
            }
            itemView.initialize(getItem(i2), 0);
            return view;
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            findExpandedIndex();
            super.notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public MenuItemImpl getItem(int i2) {
            ArrayList<MenuItemImpl> nonActionItems = MenuPopupHelper.this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
            int i3 = this.mExpandedIndex;
            if (i3 >= 0 && i2 >= i3) {
                i2++;
            }
            return nonActionItems.get(i2);
        }
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder) {
        this(context, menuBuilder, null, false);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public void dismiss(boolean z2) {
        if (isShowing()) {
            this.mPopup.dismiss();
        }
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public int getId() {
        return 0;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup) {
        throw new UnsupportedOperationException("MenuPopupHelpers manage their own views");
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void initForMenu(Context context, MenuBuilder menuBuilder) {
    }

    public boolean isShowing() {
        miuix.popupwidget.widget.PopupWindow popupWindow = this.mPopup;
        return popupWindow != null && popupWindow.isShowing();
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z2) {
        if (menuBuilder != this.mMenu) {
            return;
        }
        dismiss(true);
        MenuPresenter.Callback callback = this.mPresenterCallback;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z2);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.mPopup = null;
        this.mMenu.close();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        MenuAdapter menuAdapter = this.mAdapter;
        menuAdapter.mAdapterMenu.performItemAction(menuAdapter.getItem(i2), 0);
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        dismiss(false);
        return true;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        return null;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        boolean z2;
        if (subMenuBuilder.hasVisibleItems()) {
            MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.mContext, subMenuBuilder, this.mAnchorView, this.mFenceDecor, false);
            menuPopupHelper.setCallback(this.mPresenterCallback);
            int size = subMenuBuilder.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    z2 = false;
                    break;
                }
                MenuItem item = subMenuBuilder.getItem(i2);
                if (item.isVisible() && item.getIcon() != null) {
                    z2 = true;
                    break;
                }
                i2++;
            }
            menuPopupHelper.setForceShowIcon(z2);
            if (menuPopupHelper.tryShow()) {
                MenuPresenter.Callback callback = this.mPresenterCallback;
                if (callback != null) {
                    callback.onOpenSubMenu(subMenuBuilder);
                }
                return true;
            }
        }
        return false;
    }

    public void setAnchorView(View view) {
        this.mAnchorView = view;
    }

    public void setAnimationGravity(int i2) {
        this.mPopupAnimationGravity = i2;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.mPresenterCallback = callback;
    }

    public void setFenceDecor(View view) {
        this.mFenceDecor = view;
    }

    public void setForceShowIcon(boolean z2) {
        this.mForceShowIcon = z2;
    }

    public void setMenuItemLayout(int i2) {
        this.mMenuItemLayout = i2;
    }

    public void setPopupMaxHeight(int i2) {
        this.mPopupMaxHeight = i2;
    }

    public void setVerticalOffset(int i2) {
        this.mPopupVerticalOffset = i2;
    }

    public void show() {
        if (!tryShow()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean tryShow() {
        miuix.popupwidget.widget.PopupWindow popupWindow = new miuix.popupwidget.widget.PopupWindow(this.mContext, this.mFenceDecor);
        this.mPopup = popupWindow;
        popupWindow.setDropDownGravity(81);
        this.mPopup.setOnDismissListener(this);
        this.mPopup.setOnItemClickListener(this);
        MenuAdapter menuAdapter = new MenuAdapter(this.mMenu);
        this.mAdapter = menuAdapter;
        this.mPopup.setAdapter(menuAdapter);
        this.mPopup.setHorizontalOffset(this.mPopupHorizontalOffset);
        this.mPopup.setVerticalOffset(this.mPopupVerticalOffset);
        int i2 = this.mPopupMaxHeight;
        if (i2 > 0) {
            this.mPopup.setMaxAllowedHeight(i2);
        }
        if (!this.mPopup.prepareShow(this.mAnchorView)) {
            return true;
        }
        this.mPopup.showAsDropDown(this.mAnchorView, 81);
        this.mPopup.getListView().setOnKeyListener(this);
        return true;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void updateMenuView(boolean z2) {
        MenuAdapter menuAdapter = this.mAdapter;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view) {
        this(context, menuBuilder, view, false);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z2) {
        this(context, menuBuilder, view, null, z2);
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, View view2, boolean z2) {
        this.mMenuItemLayout = ITEM_LAYOUT;
        this.mPopupHorizontalOffset = 0;
        this.mPopupAnimationGravity = -1;
        this.mPopupMaxHeight = 0;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mMenu = menuBuilder;
        this.mOverflowOnly = z2;
        this.mAnchorView = view;
        this.mFenceDecor = view2;
        menuBuilder.addMenuPresenter(this);
    }
}
