package miuix.appcompat.internal.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import miuix.appcompat.internal.view.menu.MenuPresenter;
import miuix.appcompat.internal.view.menu.MenuView;
import miuix.internal.util.AsyncInflateLayoutManager;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseMenuPresenter implements MenuPresenter {
    private MenuPresenter.Callback mCallback;
    protected Context mContext;
    private int mId;
    protected LayoutInflater mInflater;
    private int mItemLayoutRes;
    protected MenuBuilder mMenu;
    private int mMenuLayoutRes;
    protected MenuView mMenuView;
    protected Context mSystemContext;
    protected LayoutInflater mSystemInflater;

    public BaseMenuPresenter(Context context, int i2, int i3) {
        this.mSystemContext = context;
        this.mSystemInflater = LayoutInflater.from(context);
        this.mMenuLayoutRes = i2;
        this.mItemLayoutRes = i3;
    }

    public static void close(MenuBuilder menuBuilder, boolean z2) {
        menuBuilder.closeInternal(z2);
    }

    public static MenuItemImpl createMenuItemImpl(MenuBuilder menuBuilder, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        return new MenuItemImpl(menuBuilder, i2, i3, i4, i5, charSequence, i6);
    }

    public static boolean dispatchMenuItemSelected(MenuBuilder menuBuilder, MenuBuilder menuBuilder2, MenuItem menuItem) {
        return menuBuilder.dispatchMenuItemSelected(menuBuilder2, menuItem);
    }

    public static void notifyItemsChanged(MenuBuilder menuBuilder, boolean z2) {
        menuBuilder.onItemsChanged(z2);
    }

    public void addItemView(View view, int i2) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.mMenuView).addView(view, i2);
    }

    public abstract void bindItemView(MenuItemImpl menuItemImpl, MenuView.ItemView itemView);

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public MenuView.ItemView createItemView(ViewGroup viewGroup) {
        return (MenuView.ItemView) AsyncInflateLayoutManager.getInstance().inflateViewById(Integer.valueOf(this.mItemLayoutRes), viewGroup, false);
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
        return this.mId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        MenuView.ItemView itemViewCreateItemView = view instanceof MenuView.ItemView ? (MenuView.ItemView) view : createItemView(viewGroup);
        bindItemView(menuItemImpl, itemViewCreateItemView);
        return (View) itemViewCreateItemView;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    @NonNull
    public MenuView getMenuView(ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            MenuView menuView = (MenuView) this.mSystemInflater.inflate(this.mMenuLayoutRes, viewGroup, false);
            this.mMenuView = menuView;
            menuView.initialize(this.mMenu);
            updateMenuView(true);
        }
        return this.mMenuView;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mMenu = menuBuilder;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z2) {
        MenuPresenter.Callback callback = this.mCallback;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z2);
        }
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        MenuPresenter.Callback callback = this.mCallback;
        return callback != null && callback.onOpenSubMenu(subMenuBuilder);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.mCallback = callback;
    }

    public void setId(int i2) {
        this.mId = i2;
    }

    public boolean shouldIncludeItem(int i2, MenuItemImpl menuItemImpl) {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r1v2, types: [miuix.appcompat.internal.view.menu.MenuView] */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r6v0, types: [miuix.appcompat.internal.view.menu.BaseMenuPresenter] */
    /* JADX WARN: Type inference failed for: r7v1, types: [miuix.appcompat.internal.view.menu.MenuView] */
    /* JADX WARN: Type inference failed for: r7v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [int] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [int] */
    /* JADX WARN: Type inference failed for: r7v7, types: [int] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9, types: [int] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void updateMenuView(boolean z2) {
        ?? r7 = this.mMenuView;
        ?? r02 = (ViewGroup) r7;
        if (r02 == 0) {
            return;
        }
        ?? HasBackgroundView = r7.hasBackgroundView();
        MenuBuilder menuBuilder = this.mMenu;
        if (menuBuilder != null) {
            menuBuilder.flagActionItems();
            for (MenuItemImpl menuItemImpl : this.mMenu.getVisibleItems()) {
                if (shouldIncludeItem(HasBackgroundView, menuItemImpl)) {
                    ?? childAt = r02.getChildAt(HasBackgroundView);
                    MenuItemImpl itemData = childAt instanceof MenuView.ItemView ? ((MenuView.ItemView) childAt).getItemData() : null;
                    View itemView = getItemView(menuItemImpl, childAt, r02);
                    if (menuItemImpl != itemData) {
                        itemView.setPressed(false);
                    }
                    if (itemView != childAt) {
                        addItemView(itemView, HasBackgroundView);
                    }
                    if (menuItemImpl != null) {
                        menuItemImpl.setView(itemView);
                        menuItemImpl.updateBadgeDrawable();
                    }
                    HasBackgroundView++;
                }
            }
        }
        while (HasBackgroundView < r02.getChildCount()) {
            if (!this.mMenuView.filterLeftoverView(HasBackgroundView)) {
                HasBackgroundView++;
            }
        }
    }
}
