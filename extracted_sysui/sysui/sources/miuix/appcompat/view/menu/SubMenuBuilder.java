package miuix.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import miuix.appcompat.app.floatingactivity.multiapp.MethodCodeHelper;
import miuix.appcompat.view.menu.MenuBuilder;

/* JADX INFO: loaded from: classes3.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class SubMenuBuilder extends MenuBuilder implements SubMenu {
    private MenuItemImpl mItem;
    private MenuBuilder mParentMenu;

    public SubMenuBuilder(Context context, MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        super(context);
        this.mParentMenu = menuBuilder;
        this.mItem = menuItemImpl;
    }

    @Override // miuix.appcompat.view.menu.MenuBuilder
    public boolean collapseItemActionView(MenuItemImpl menuItemImpl) {
        return this.mParentMenu.collapseItemActionView(menuItemImpl);
    }

    @Override // miuix.appcompat.view.menu.MenuBuilder
    public boolean dispatchMenuItemSelected(@NonNull MenuBuilder menuBuilder, @NonNull MenuItem menuItem) {
        return super.dispatchMenuItemSelected(menuBuilder, menuItem) || this.mParentMenu.dispatchMenuItemSelected(menuBuilder, menuItem);
    }

    @Override // miuix.appcompat.view.menu.MenuBuilder
    public boolean expandItemActionView(MenuItemImpl menuItemImpl) {
        return this.mParentMenu.expandItemActionView(menuItemImpl);
    }

    @Override // miuix.appcompat.view.menu.MenuBuilder
    public String getActionViewStatesKey() {
        MenuItemImpl menuItemImpl = this.mItem;
        int itemId = menuItemImpl != null ? menuItemImpl.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.getActionViewStatesKey() + MethodCodeHelper.IDENTITY_INFO_SEPARATOR + itemId;
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.mItem;
    }

    public Menu getParentMenu() {
        return this.mParentMenu;
    }

    @Override // miuix.appcompat.view.menu.MenuBuilder
    public MenuBuilder getRootMenu() {
        return this.mParentMenu.getRootMenu();
    }

    @Override // miuix.appcompat.view.menu.MenuBuilder
    public boolean isGroupDividerEnabled() {
        return this.mParentMenu.isGroupDividerEnabled();
    }

    @Override // miuix.appcompat.view.menu.MenuBuilder
    public boolean isQwertyMode() {
        return this.mParentMenu.isQwertyMode();
    }

    @Override // miuix.appcompat.view.menu.MenuBuilder
    public boolean isShortcutsVisible() {
        return this.mParentMenu.isShortcutsVisible();
    }

    @Override // miuix.appcompat.view.menu.MenuBuilder
    public void setCallback(MenuBuilder.Callback callback) {
        this.mParentMenu.setCallback(callback);
    }

    @Override // miuix.appcompat.view.menu.MenuBuilder, androidx.core.internal.view.SupportMenu, android.view.Menu
    public void setGroupDividerEnabled(boolean z2) {
        this.mParentMenu.setGroupDividerEnabled(z2);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        return (SubMenu) super.setHeaderIconInt(drawable);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        return (SubMenu) super.setHeaderTitleInt(charSequence);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        return (SubMenu) super.setHeaderViewInt(view);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.mItem.setIcon(drawable);
        return this;
    }

    @Override // miuix.appcompat.view.menu.MenuBuilder, android.view.Menu
    public void setQwertyMode(boolean z2) {
        this.mParentMenu.setQwertyMode(z2);
    }

    @Override // miuix.appcompat.view.menu.MenuBuilder
    public void setShortcutsVisible(boolean z2) {
        this.mParentMenu.setShortcutsVisible(z2);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i2) {
        return (SubMenu) super.setHeaderIconInt(i2);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i2) {
        return (SubMenu) super.setHeaderTitleInt(i2);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i2) {
        this.mItem.setIcon(i2);
        return this;
    }
}
