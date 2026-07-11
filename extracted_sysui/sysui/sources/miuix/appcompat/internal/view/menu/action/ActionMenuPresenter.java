package miuix.appcompat.internal.view.menu.action;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Iterator;
import miuix.appcompat.R;
import miuix.appcompat.internal.app.widget.ActionBarOverlayLayout;
import miuix.appcompat.internal.view.ActionBarPolicy;
import miuix.appcompat.internal.view.menu.BaseMenuPresenter;
import miuix.appcompat.internal.view.menu.ListMenuPresenter;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import miuix.appcompat.internal.view.menu.MenuDialogHelper;
import miuix.appcompat.internal.view.menu.MenuItemImpl;
import miuix.appcompat.internal.view.menu.MenuPopupHelper;
import miuix.appcompat.internal.view.menu.MenuPresenter;
import miuix.appcompat.internal.view.menu.MenuView;
import miuix.appcompat.internal.view.menu.SubMenuBuilder;
import miuix.appcompat.internal.view.menu.action.OverflowMenuButton;
import miuix.internal.util.AttributeResolver;

/* JADX INFO: loaded from: classes3.dex */
public class ActionMenuPresenter extends BaseMenuPresenter {
    private boolean isMaxItemCountSet;
    private final SparseBooleanArray mActionButtonGroups;
    private ActionButtonSubMenu mActionButtonPopup;
    private View mBottomMenuCustomView;
    protected ActionBarOverlayLayout mDecorView;
    private boolean mExpandedActionViewsExclusive;
    private int mListItemLayoutRes;
    private int mListLayoutRes;
    private OverflowMenu mListOverflowMenu;
    private int mMaxItems;
    int mOpenSubMenuId;
    protected View mOverflowButton;
    protected OverflowMenu mOverflowMenu;
    private int mOverflowMenuAttrs;
    private MenuItemImpl mOverflowMenuItem;
    final PopupPresenterCallback mPopupPresenterCallback;
    private OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private View mScrapActionButtonView;
    private boolean mStrictWidthLimit;
    private boolean mWidthLimitSet;

    public class ActionButtonSubMenu extends MenuDialogHelper {
        public ActionButtonSubMenu(SubMenuBuilder subMenuBuilder) {
            super(subMenuBuilder);
            ActionMenuPresenter.this.setCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        }

        @Override // miuix.appcompat.internal.view.menu.MenuDialogHelper, android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
            ActionMenuPresenter.this.mActionButtonPopup = null;
            ActionMenuPresenter.this.mOpenSubMenuId = 0;
        }
    }

    public class ListOverflowMenu implements OverflowMenu {
        private ListMenuPresenter mListMenuPresenter;

        private ListOverflowMenu() {
        }

        private ListMenuPresenter getListMenuPresenter(MenuBuilder menuBuilder) {
            if (this.mListMenuPresenter == null) {
                this.mListMenuPresenter = new ListMenuPresenter(((BaseMenuPresenter) ActionMenuPresenter.this).mContext, ActionMenuPresenter.this.mListLayoutRes, ActionMenuPresenter.this.mListItemLayoutRes);
            }
            menuBuilder.addMenuPresenter(this.mListMenuPresenter);
            return this.mListMenuPresenter;
        }

        @Override // miuix.appcompat.internal.view.menu.action.ActionMenuPresenter.OverflowMenu
        public void dismiss(boolean z2) {
            if (((BaseMenuPresenter) ActionMenuPresenter.this).mMenuView instanceof PhoneActionMenuView) {
                ((PhoneActionMenuView) ((BaseMenuPresenter) ActionMenuPresenter.this).mMenuView).hideOverflowMenu(ActionMenuPresenter.this.mDecorView);
            }
        }

        public View getOverflowMenuView(MenuBuilder menuBuilder) {
            if (menuBuilder == null || menuBuilder.getNonActionItems().size() <= 0) {
                return null;
            }
            return (View) getListMenuPresenter(menuBuilder).getMenuView((ViewGroup) ((BaseMenuPresenter) ActionMenuPresenter.this).mMenuView);
        }

        @Override // miuix.appcompat.internal.view.menu.action.ActionMenuPresenter.OverflowMenu
        public boolean isShowing() {
            if (((BaseMenuPresenter) ActionMenuPresenter.this).mMenuView instanceof PhoneActionMenuView) {
                return ((PhoneActionMenuView) ((BaseMenuPresenter) ActionMenuPresenter.this).mMenuView).isOverflowMenuShowing();
            }
            return false;
        }

        @Override // miuix.appcompat.internal.view.menu.action.ActionMenuPresenter.OverflowMenu
        public boolean tryShow() {
            if (((BaseMenuPresenter) ActionMenuPresenter.this).mMenuView instanceof PhoneActionMenuView) {
                return ((PhoneActionMenuView) ((BaseMenuPresenter) ActionMenuPresenter.this).mMenuView).showOverflowMenu(ActionMenuPresenter.this.mDecorView);
            }
            return false;
        }

        @Override // miuix.appcompat.internal.view.menu.action.ActionMenuPresenter.OverflowMenu
        public void update(MenuBuilder menuBuilder) {
            if (((BaseMenuPresenter) ActionMenuPresenter.this).mMenuView instanceof PhoneActionMenuView) {
                ((PhoneActionMenuView) ((BaseMenuPresenter) ActionMenuPresenter.this).mMenuView).setOverflowMenuView(getOverflowMenuView(menuBuilder));
            }
        }
    }

    public class OpenOverflowRunnable implements Runnable {
        private OverflowMenu mPopup;

        public OpenOverflowRunnable(OverflowMenu overflowMenu) {
            this.mPopup = overflowMenu;
        }

        @Override // java.lang.Runnable
        public void run() {
            View view = (View) ((BaseMenuPresenter) ActionMenuPresenter.this).mMenuView;
            if (view != null && view.getWindowToken() != null && this.mPopup.tryShow()) {
                ActionMenuPresenter.this.mOverflowMenu = this.mPopup;
            }
            ActionMenuPresenter.this.mPostedOpenRunnable = null;
        }
    }

    public interface OverflowMenu {
        void dismiss(boolean z2);

        boolean isShowing();

        boolean tryShow();

        void update(MenuBuilder menuBuilder);
    }

    public class PopupOverflowMenu extends MenuPopupHelper implements OverflowMenu {
        public PopupOverflowMenu(Context context, MenuBuilder menuBuilder, View view, View view2, boolean z2) {
            super(context, menuBuilder, view, view2, z2);
            TypedValue typedValueResolveTypedValue = AttributeResolver.resolveTypedValue(context, R.attr.overflowMenuMaxHeight);
            int dimensionPixelSize = (typedValueResolveTypedValue == null || typedValueResolveTypedValue.type != 5) ? 0 : typedValueResolveTypedValue.resourceId > 0 ? context.getResources().getDimensionPixelSize(typedValueResolveTypedValue.resourceId) : TypedValue.complexToDimensionPixelSize(typedValueResolveTypedValue.data, context.getResources().getDisplayMetrics());
            if (dimensionPixelSize > 0) {
                setPopupMaxHeight(dimensionPixelSize);
            }
            setCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
            setMenuItemLayout(R.layout.miuix_appcompat_overflow_popup_menu_item_layout);
            int overflowMenuAnimationGravity = ActionMenuPresenter.this.getOverflowMenuAnimationGravity(view);
            if (overflowMenuAnimationGravity != -1) {
                setAnimationGravity(overflowMenuAnimationGravity);
            }
        }

        @Override // miuix.appcompat.internal.view.menu.MenuPopupHelper, miuix.appcompat.internal.view.menu.action.ActionMenuPresenter.OverflowMenu
        public void dismiss(boolean z2) {
            super.dismiss(z2);
            View view = ActionMenuPresenter.this.mOverflowButton;
            if (view != null) {
                view.setSelected(false);
            }
        }

        @Override // miuix.appcompat.internal.view.menu.MenuPopupHelper, android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            super.onDismiss();
            ((BaseMenuPresenter) ActionMenuPresenter.this).mMenu.close();
            ActionMenuPresenter.this.mOverflowMenu = null;
        }

        @Override // miuix.appcompat.internal.view.menu.action.ActionMenuPresenter.OverflowMenu
        public void update(MenuBuilder menuBuilder) {
        }
    }

    public class PopupPresenterCallback implements MenuPresenter.Callback {
        private PopupPresenterCallback() {
        }

        @Override // miuix.appcompat.internal.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder menuBuilder, boolean z2) {
            if (menuBuilder instanceof SubMenuBuilder) {
                BaseMenuPresenter.close(menuBuilder.getRootMenu(), false);
            }
        }

        @Override // miuix.appcompat.internal.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (menuBuilder == null) {
                return false;
            }
            ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder) menuBuilder).getItem().getItemId();
            return false;
        }
    }

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: miuix.appcompat.internal.view.menu.action.ActionMenuPresenter.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        public int openSubMenuId;

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.openSubMenuId);
        }

        public SavedState(Parcel parcel) {
            this.openSubMenuId = parcel.readInt();
        }
    }

    public ActionMenuPresenter(Context context, ActionBarOverlayLayout actionBarOverlayLayout, int i2, int i3) {
        this(context, actionBarOverlayLayout, i2, i3, 0, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View findViewForItem(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if ((childAt instanceof MenuView.ItemView) && ((MenuView.ItemView) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createOverflowMenuButton$0() {
        MenuBuilder menuBuilder = this.mMenu;
        if (menuBuilder != null) {
            BaseMenuPresenter.dispatchMenuItemSelected(menuBuilder, menuBuilder.getRootMenu(), getOverflowMenuItem());
        }
        if (this.mOverflowButton.isSelected()) {
            hideOverflowMenu(true);
        } else {
            showOverflowMenu();
        }
    }

    public void addBadgeOnItemView(int i2) {
        addBadgeOnItemView(i2, 2);
    }

    public void addNumberBadgeOnItemView(int i2, int i3, int i4) {
        MenuItem menuItemFindItem = this.mMenu.findItem(i2);
        if (menuItemFindItem instanceof MenuItemImpl) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) menuItemFindItem;
            menuItemImpl.setBadgeNeeded(true, i4);
            updateBadgeOnItemView(menuItemImpl, i3);
        }
    }

    @Override // miuix.appcompat.internal.view.menu.BaseMenuPresenter
    public void bindItemView(MenuItemImpl menuItemImpl, MenuView.ItemView itemView) {
        itemView.initialize(menuItemImpl, 0);
        itemView.setItemInvoker((MenuBuilder.ItemInvoker) this.mMenuView);
    }

    public void clearBadgeOnItemView(int i2) {
        clearBadgeOnItemView(this.mMenu.findItem(i2));
    }

    public View createOverflowMenuButton(Context context) {
        OverflowMenuButton overflowMenuButton = new OverflowMenuButton(context, null, this.mOverflowMenuAttrs);
        overflowMenuButton.setOnOverflowMenuButtonClickListener(new OverflowMenuButton.OnOverflowMenuButtonClickListener() { // from class: miuix.appcompat.internal.view.menu.action.a
            @Override // miuix.appcompat.internal.view.menu.action.OverflowMenuButton.OnOverflowMenuButtonClickListener
            public final void onOverflowMenuButtonClick() {
                this.f6084a.lambda$createOverflowMenuButton$0();
            }
        });
        return overflowMenuButton;
    }

    public boolean dismissPopupMenus(boolean z2) {
        return hideOverflowMenu(z2);
    }

    @Override // miuix.appcompat.internal.view.menu.BaseMenuPresenter, miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean flagActionItems() {
        ArrayList<MenuItemImpl> visibleItems = this.mMenu.getVisibleItems();
        int size = visibleItems.size();
        int i2 = this.mMaxItems;
        if (i2 < size) {
            i2--;
        }
        int i3 = 0;
        while (true) {
            boolean z2 = true;
            if (i3 >= size || i2 <= 0) {
                break;
            }
            MenuItemImpl menuItemImpl = visibleItems.get(i3);
            if (!menuItemImpl.requestsActionButton() && !menuItemImpl.requiresActionButton()) {
                z2 = false;
            }
            menuItemImpl.setIsActionButton(z2);
            if (z2) {
                i2--;
            }
            i3++;
        }
        while (i3 < size) {
            visibleItems.get(i3).setIsActionButton(false);
            i3++;
        }
        return true;
    }

    public int getDefaultMaxItemCount() {
        Context context = this.mContext;
        if (context != null) {
            return AttributeResolver.resolveInt(context, R.attr.actionMenuItemLimit, 5);
        }
        return 5;
    }

    @Override // miuix.appcompat.internal.view.menu.BaseMenuPresenter
    public View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        View actionView = menuItemImpl.getActionView();
        if (actionView == null || menuItemImpl.hasCollapsibleActionView()) {
            if (!isConvertViewTypeAllowed(view)) {
                view = null;
            }
            actionView = super.getItemView(menuItemImpl, view, viewGroup);
        }
        actionView.setVisibility(menuItemImpl.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // miuix.appcompat.internal.view.menu.BaseMenuPresenter, miuix.appcompat.internal.view.menu.MenuPresenter
    @NonNull
    public MenuView getMenuView(ViewGroup viewGroup) {
        MenuView menuView = super.getMenuView(viewGroup);
        ((ActionMenuView) menuView).setPresenter(this);
        View view = this.mBottomMenuCustomView;
        if (view != null && view.getParent() == null && (menuView instanceof ResponsiveActionMenuView)) {
            ((ResponsiveActionMenuView) menuView).addCustomView(this.mBottomMenuCustomView);
        }
        return menuView;
    }

    public OverflowMenu getOverflowMenu() {
        if (shouldShowPopupOverflow()) {
            return new PopupOverflowMenu(this.mContext, this.mMenu, this.mOverflowButton, this.mDecorView, true);
        }
        if (this.mListOverflowMenu == null) {
            this.mListOverflowMenu = new ListOverflowMenu();
        }
        return this.mListOverflowMenu;
    }

    public int getOverflowMenuAnimationGravity(View view) {
        return -1;
    }

    public MenuItemImpl getOverflowMenuItem() {
        if (this.mOverflowMenuItem == null) {
            this.mOverflowMenuItem = BaseMenuPresenter.createMenuItemImpl(this.mMenu, 0, R.id.more, 0, 0, this.mContext.getString(R.string.more), 0);
        }
        return this.mOverflowMenuItem;
    }

    public boolean hideOverflowMenu(boolean z2) {
        if (this.mPostedOpenRunnable != null && this.mMenuView != null) {
            this.mOverflowButton.setSelected(false);
            ((View) this.mMenuView).removeCallbacks(this.mPostedOpenRunnable);
            this.mPostedOpenRunnable = null;
            return true;
        }
        OverflowMenu overflowMenu = this.mOverflowMenu;
        if (overflowMenu == null) {
            return false;
        }
        boolean zIsShowing = overflowMenu.isShowing();
        if (zIsShowing) {
            this.mOverflowButton.setSelected(false);
        }
        this.mOverflowMenu.dismiss(z2);
        return zIsShowing;
    }

    public boolean hideSubMenus() {
        ActionButtonSubMenu actionButtonSubMenu = this.mActionButtonPopup;
        if (actionButtonSubMenu == null) {
            return false;
        }
        actionButtonSubMenu.dismiss();
        return true;
    }

    @Override // miuix.appcompat.internal.view.menu.BaseMenuPresenter, miuix.appcompat.internal.view.menu.MenuPresenter
    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        super.initForMenu(context, menuBuilder);
        context.getResources();
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(context);
        if (!this.mReserveOverflowSet) {
            this.mReserveOverflow = actionBarPolicy.showsOverflowMenuButton();
        }
        if (!this.isMaxItemCountSet) {
            this.mMaxItems = getDefaultMaxItemCount();
        }
        if (!this.mReserveOverflow) {
            this.mOverflowButton = null;
        } else if (this.mOverflowButton == null) {
            this.mOverflowButton = createOverflowMenuButton(this.mSystemContext);
        }
        this.mScrapActionButtonView = null;
    }

    public boolean isConvertViewTypeAllowed(View view) {
        return view instanceof ActionMenuItemView;
    }

    public boolean isOverflowMenuShowing() {
        OverflowMenu overflowMenu = this.mOverflowMenu;
        return overflowMenu != null && overflowMenu.isShowing();
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    @Override // miuix.appcompat.internal.view.menu.BaseMenuPresenter, miuix.appcompat.internal.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z2) {
        dismissPopupMenus(true);
        super.onCloseMenu(menuBuilder, z2);
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.isMaxItemCountSet && this.mContext != null) {
            this.mMaxItems = getDefaultMaxItemCount();
        }
        MenuBuilder menuBuilder = this.mMenu;
        if (menuBuilder != null) {
            BaseMenuPresenter.notifyItemsChanged(menuBuilder, true);
        }
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        int i2 = ((SavedState) parcelable).openSubMenuId;
        if (i2 <= 0 || (menuItemFindItem = this.mMenu.findItem(i2)) == null) {
            return;
        }
        onSubMenuSelected((SubMenuBuilder) menuItemFindItem.getSubMenu());
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.openSubMenuId = this.mOpenSubMenuId;
        return savedState;
    }

    @Override // miuix.appcompat.internal.view.menu.BaseMenuPresenter, miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (subMenuBuilder2.getParentMenu() != this.mMenu) {
            subMenuBuilder2 = (SubMenuBuilder) subMenuBuilder2.getParentMenu();
        }
        if (findViewForItem(subMenuBuilder2.getItem()) == null && this.mOverflowButton == null) {
            return false;
        }
        this.mOpenSubMenuId = subMenuBuilder.getItem().getItemId();
        ActionButtonSubMenu actionButtonSubMenu = new ActionButtonSubMenu(subMenuBuilder);
        this.mActionButtonPopup = actionButtonSubMenu;
        actionButtonSubMenu.show(null);
        super.onSubMenuSelected(subMenuBuilder);
        return true;
    }

    public void onSubUiVisibilityChanged(boolean z2) {
        if (z2) {
            super.onSubMenuSelected(null);
        } else {
            BaseMenuPresenter.close(this.mMenu, false);
        }
    }

    public void removeBottomMenuCustomView() {
        if (this.mBottomMenuCustomView != null) {
            MenuView menuView = this.mMenuView;
            if (menuView instanceof ResponsiveActionMenuView) {
                ((ResponsiveActionMenuView) menuView).removeCustomView();
            }
            this.mBottomMenuCustomView = null;
        }
    }

    public void setActionEditMode(boolean z2) {
        if (z2) {
            this.mOverflowMenuAttrs = R.attr.actionModeOverflowButtonStyle;
        }
    }

    public void setBottomMenuCustomView(View view) {
        ViewGroup viewGroup;
        View view2 = this.mBottomMenuCustomView;
        if (view2 != null && view2 != view && (viewGroup = (ViewGroup) view2.getParent()) != null) {
            viewGroup.removeView(this.mBottomMenuCustomView);
        }
        this.mBottomMenuCustomView = view;
        if (view.getParent() == null) {
            MenuView menuView = this.mMenuView;
            if (menuView instanceof ResponsiveActionMenuView) {
                ((ResponsiveActionMenuView) menuView).addCustomView(view);
            }
        }
    }

    public void setExpandedActionViewsExclusive(boolean z2) {
        this.mExpandedActionViewsExclusive = z2;
    }

    public void setItemLimit(int i2) {
        this.isMaxItemCountSet = true;
        int i3 = this.mMaxItems;
        this.mMaxItems = i2;
        MenuBuilder menuBuilder = this.mMenu;
        if (menuBuilder == null || i3 == i2) {
            return;
        }
        menuBuilder.updateVisibleItemCountLimit();
    }

    public void setReserveOverflow(boolean z2) {
        this.mReserveOverflow = z2;
        this.mReserveOverflowSet = true;
    }

    public void setWidthLimit(int i2, boolean z2) {
        this.mStrictWidthLimit = z2;
        this.mWidthLimitSet = true;
    }

    @Override // miuix.appcompat.internal.view.menu.BaseMenuPresenter
    public boolean shouldIncludeItem(int i2, MenuItemImpl menuItemImpl) {
        return menuItemImpl.isActionButton();
    }

    public boolean shouldShowPopupOverflow() {
        View view = this.mOverflowButton;
        return (view == null || view.getParent() == null) ? false : true;
    }

    public boolean showOverflowMenu() {
        if (!this.mReserveOverflow || isOverflowMenuShowing() || this.mMenu == null || this.mMenuView == null || this.mPostedOpenRunnable != null || this.mOverflowButton == null) {
            return false;
        }
        OpenOverflowRunnable openOverflowRunnable = new OpenOverflowRunnable(getOverflowMenu());
        this.mPostedOpenRunnable = openOverflowRunnable;
        ((View) this.mMenuView).post(openOverflowRunnable);
        super.onSubMenuSelected(null);
        this.mOverflowButton.setSelected(true);
        return true;
    }

    public void updateBadgeOnItemView(MenuItemImpl menuItemImpl) {
        if (menuItemImpl.isVisible()) {
            menuItemImpl.updateBadgeDrawable();
        }
    }

    public void updateBadgeOnItemViews() {
        Iterator<MenuItemImpl> it = this.mMenu.getVisibleItems().iterator();
        while (it.hasNext()) {
            it.next().updateBadgeDrawable();
        }
    }

    @Override // miuix.appcompat.internal.view.menu.BaseMenuPresenter, miuix.appcompat.internal.view.menu.MenuPresenter
    public void updateMenuView(boolean z2) {
        super.updateMenuView(z2);
        if (this.mMenuView == null) {
            return;
        }
        MenuBuilder menuBuilder = this.mMenu;
        ArrayList<MenuItemImpl> nonActionItems = menuBuilder != null ? menuBuilder.getNonActionItems() : null;
        boolean z3 = false;
        if (this.mReserveOverflow && nonActionItems != null) {
            int size = nonActionItems.size();
            if (size == 1) {
                z3 = !nonActionItems.get(0).isActionViewExpanded();
            } else if (size > 0) {
                z3 = true;
            }
        }
        if (z3) {
            View view = this.mOverflowButton;
            if (view == null) {
                this.mOverflowButton = createOverflowMenuButton(this.mSystemContext);
            } else {
                view.setTranslationY(0.0f);
            }
            ViewGroup viewGroup = (ViewGroup) this.mOverflowButton.getParent();
            if (viewGroup != this.mMenuView) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.mOverflowButton);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.mMenuView;
                View view2 = this.mOverflowButton;
                actionMenuView.addView(view2, actionMenuView.generateOverflowButtonLayoutParams(view2));
            }
        } else {
            View view3 = this.mOverflowButton;
            if (view3 != null) {
                Object parent = view3.getParent();
                Object obj = this.mMenuView;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.mOverflowButton);
                }
            }
        }
        ((ActionMenuView) this.mMenuView).setOverflowReserved(this.mReserveOverflow);
        if (shouldShowPopupOverflow()) {
            return;
        }
        getOverflowMenu().update(this.mMenu);
    }

    public ActionMenuPresenter(Context context, ActionBarOverlayLayout actionBarOverlayLayout, int i2, int i3, int i4, int i5) {
        super(context, i2, i3);
        this.mOverflowMenuAttrs = android.R.attr.actionOverflowButtonStyle;
        this.mActionButtonGroups = new SparseBooleanArray();
        this.mPopupPresenterCallback = new PopupPresenterCallback();
        this.mListLayoutRes = i4;
        this.mListItemLayoutRes = i5;
        this.mDecorView = actionBarOverlayLayout;
    }

    public void addBadgeOnItemView(int i2, int i3) {
        addBadgeOnItemView(this.mMenu.findItem(i2), i3);
    }

    public void clearBadgeOnItemView(MenuItem menuItem) {
        if (menuItem instanceof MenuItemImpl) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) menuItem;
            menuItemImpl.setBadgeNeeded(false);
            updateBadgeOnItemView(menuItemImpl);
        }
    }

    public void updateBadgeOnItemView(MenuItemImpl menuItemImpl, int i2) {
        if (menuItemImpl.isVisible()) {
            menuItemImpl.updateBadgeDrawable(i2);
        }
    }

    public void addBadgeOnItemView(MenuItem menuItem) {
        addBadgeOnItemView(menuItem, 2);
    }

    public void addBadgeOnItemView(MenuItem menuItem, int i2) {
        if (menuItem instanceof MenuItemImpl) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) menuItem;
            menuItemImpl.setBadgeNeeded(true, i2);
            updateBadgeOnItemView(menuItemImpl);
        }
    }
}
