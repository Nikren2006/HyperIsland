package miuix.appcompat.internal.view.menu.action;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import miuix.appcompat.R;
import miuix.appcompat.internal.app.widget.ActionBarOverlayLayout;
import miuix.appcompat.internal.view.menu.BaseMenuPresenter;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import miuix.appcompat.internal.view.menu.MenuItemImpl;
import miuix.appcompat.internal.view.menu.MenuPopupHelper;
import miuix.appcompat.internal.view.menu.SubMenuBuilder;
import miuix.internal.util.ViewUtils;

/* JADX INFO: loaded from: classes3.dex */
public class EndActionMenuPresenter extends ActionMenuPresenter {
    private MenuItemImpl mMoreButtonItem;

    public class PopupSubMenu extends MenuPopupHelper {
        public PopupSubMenu(Context context, MenuBuilder menuBuilder, View view, View view2, boolean z2) {
            super(context, menuBuilder, view, view2, z2);
            setCallback(EndActionMenuPresenter.this.mPopupPresenterCallback);
            setMenuItemLayout(R.layout.miuix_appcompat_overflow_popup_menu_item_layout);
        }

        @Override // miuix.appcompat.internal.view.menu.MenuPopupHelper, miuix.appcompat.internal.view.menu.action.ActionMenuPresenter.OverflowMenu
        public void dismiss(boolean z2) {
            super.dismiss(z2);
            View view = EndActionMenuPresenter.this.mOverflowButton;
            if (view != null) {
                view.setSelected(false);
            }
        }

        @Override // miuix.appcompat.internal.view.menu.MenuPopupHelper, android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            super.onDismiss();
            ((BaseMenuPresenter) EndActionMenuPresenter.this).mMenu.close();
        }
    }

    public EndActionMenuPresenter(Context context, ActionBarOverlayLayout actionBarOverlayLayout, int i2, int i3) {
        this(context, actionBarOverlayLayout, i2, i3, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$createOverflowMenuButton$0(MenuItem menuItem) {
        MenuBuilder menuBuilder = this.mMenu;
        if (menuBuilder != null) {
            BaseMenuPresenter.dispatchMenuItemSelected(menuBuilder, menuBuilder.getRootMenu(), getOverflowMenuItem());
        }
        if (this.mOverflowButton.isSelected()) {
            hideOverflowMenu(true);
        } else {
            showOverflowMenu();
        }
        return true;
    }

    public void addBadgeOnMoreButton() {
        addBadgeOnMoreButton(2);
    }

    public void addNumberBadgeOnMoreButton(int i2, int i3) {
        MenuItemImpl menuItemImpl = this.mMoreButtonItem;
        if (menuItemImpl == null) {
            return;
        }
        menuItemImpl.setBadgeNeeded(true, i3);
        updateBadgeOnMoreButton(i2);
    }

    public void clearBadgeOnMoreButton() {
        MenuItemImpl menuItemImpl = this.mMoreButtonItem;
        if (menuItemImpl == null) {
            return;
        }
        menuItemImpl.setBadgeNeeded(false);
        updateBadgeOnMoreButton();
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuPresenter
    public View createOverflowMenuButton(Context context) {
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        if (viewGroup == null) {
            return null;
        }
        MenuBuilder menuBuilder = this.mMenu;
        int i2 = R.id.more;
        MenuItemImpl menuItemImplCreateMenuItemImpl = BaseMenuPresenter.createMenuItemImpl(menuBuilder, 0, i2, 0, 0, context.getString(R.string.more), 2);
        this.mMenu.stopDispatchingItemsChanged();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.endActionMoreButtonIcon});
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        typedArrayObtainStyledAttributes.recycle();
        menuItemImplCreateMenuItemImpl.setIcon(drawable);
        menuItemImplCreateMenuItemImpl.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: miuix.appcompat.internal.view.menu.action.b
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return this.f6085a.lambda$createOverflowMenuButton$0(menuItem);
            }
        });
        this.mMenu.setPreventDispatchingItemsChanged(false);
        View itemView = getItemView(menuItemImplCreateMenuItemImpl, null, viewGroup);
        itemView.setId(i2);
        this.mMoreButtonItem = menuItemImplCreateMenuItemImpl;
        menuItemImplCreateMenuItemImpl.setView(itemView);
        return itemView;
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuPresenter
    public int getDefaultMaxItemCount() {
        Context context = this.mContext;
        if (context != null) {
            return context.getResources().getInteger(R.integer.action_bar_end_menu_max_item_count);
        }
        return 5;
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuPresenter
    public int getOverflowMenuAnimationGravity(View view) {
        return ViewUtils.isLayoutRtl(view) ? 51 : 53;
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuPresenter
    public boolean isConvertViewTypeAllowed(View view) {
        if (view == null) {
            return false;
        }
        MenuItemImpl menuItemImpl = this.mMoreButtonItem;
        return (view instanceof EndActionMenuItemView) && !(menuItemImpl != null && menuItemImpl.getView() == view);
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuPresenter, miuix.appcompat.internal.view.menu.BaseMenuPresenter, miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        new PopupSubMenu(this.mContext, subMenuBuilder, this.mOverflowButton, this.mDecorView, true).tryShow();
        return true;
    }

    public void updateBadgeOnMoreButton() {
        MenuItemImpl menuItemImpl = this.mMoreButtonItem;
        if (menuItemImpl == null) {
            return;
        }
        menuItemImpl.updateBadgeDrawable();
    }

    public EndActionMenuPresenter(Context context, ActionBarOverlayLayout actionBarOverlayLayout, int i2, int i3, int i4, int i5) {
        super(context, actionBarOverlayLayout, i2, i3, i4, i5);
    }

    public void addBadgeOnMoreButton(int i2) {
        MenuItemImpl menuItemImpl = this.mMoreButtonItem;
        if (menuItemImpl == null) {
            return;
        }
        menuItemImpl.setBadgeNeeded(true, i2);
        updateBadgeOnMoreButton();
    }

    public void updateBadgeOnMoreButton(int i2) {
        MenuItemImpl menuItemImpl = this.mMoreButtonItem;
        if (menuItemImpl == null) {
            return;
        }
        menuItemImpl.updateBadgeDrawable(i2);
    }
}
