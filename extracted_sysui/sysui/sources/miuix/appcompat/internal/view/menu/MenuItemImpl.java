package miuix.appcompat.internal.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import miuix.appcompat.internal.view.menu.MenuView;
import miuix.appcompat.widget.BadgeDrawable;

/* JADX INFO: loaded from: classes3.dex */
public final class MenuItemImpl implements MenuItem {
    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private static final int IS_ACTION = 32;
    static final int NO_ICON = 0;
    private static final int SHOW_AS_ACTION_MASK = 3;
    private static final String TAG = "MenuItemImpl";
    private static String sDeleteShortcutLabel;
    private static String sEnterShortcutLabel;
    private static String sPrependShortcutLabel;
    private static String sSpaceShortcutLabel;
    private ActionProvider mActionProvider;
    private View mActionView;
    private BadgeDrawable mBadgeDrawable;
    private int mBadgeGravity;
    private final int mCategoryOrder;
    private MenuItem.OnMenuItemClickListener mClickListener;
    private CharSequence mContentDescription;
    private final int mGroup;
    private Drawable mIconDrawable;
    private final int mId;
    private Intent mIntent;
    private Runnable mItemCallback;
    private MenuBuilder mMenu;
    private ContextMenu.ContextMenuInfo mMenuInfo;
    private MenuItem.OnActionExpandListener mOnActionExpandListener;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private char mShortcutNumericChar;
    private int mShowAsAction;
    private SubMenuBuilder mSubMenu;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    private View mView;
    private boolean mNeedBadge = false;
    private boolean mAttachBadge = false;
    private int mIconResId = 0;
    private int mFlags = 16;
    private boolean mIsActionViewExpanded = false;

    public MenuItemImpl(MenuBuilder menuBuilder, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        this.mMenu = menuBuilder;
        this.mId = i3;
        this.mGroup = i2;
        this.mCategoryOrder = i4;
        this.mOrdering = i5;
        this.mTitle = charSequence;
        this.mShowAsAction = i6;
    }

    private BadgeDrawable getBadgeDrawable() {
        if (this.mBadgeDrawable == null) {
            this.mBadgeDrawable = new BadgeDrawable(this.mMenu.getContext());
        }
        return this.mBadgeDrawable;
    }

    public void actionFormatChanged() {
        this.mMenu.onItemActionRequestChanged(this);
    }

    @Override // android.view.MenuItem
    public boolean collapseActionView() {
        MenuItem.OnActionExpandListener onActionExpandListener;
        return (this.mShowAsAction & 8) != 0 && (this.mActionView == null || (((onActionExpandListener = this.mOnActionExpandListener) == null || onActionExpandListener.onMenuItemActionCollapse(this)) && this.mMenu.collapseItemActionView(this)));
    }

    @Override // android.view.MenuItem
    public boolean expandActionView() {
        MenuItem.OnActionExpandListener onActionExpandListener;
        return ((this.mShowAsAction & 8) == 0 || this.mActionView == null || ((onActionExpandListener = this.mOnActionExpandListener) != null && !onActionExpandListener.onMenuItemActionExpand(this)) || !this.mMenu.expandItemActionView(this)) ? false : true;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("Implementation should use getSupportActionProvider!");
    }

    @Override // android.view.MenuItem
    public View getActionView() {
        View view = this.mActionView;
        if (view != null) {
            return view;
        }
        ActionProvider actionProvider = this.mActionProvider;
        if (actionProvider == null) {
            return null;
        }
        View viewOnCreateActionView = actionProvider.onCreateActionView(this);
        this.mActionView = viewOnCreateActionView;
        return viewOnCreateActionView;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    public Runnable getCallback() {
        return this.mItemCallback;
    }

    @Override // android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.mGroup;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        Drawable drawable = this.mIconDrawable;
        if (drawable != null) {
            return drawable;
        }
        if (this.mIconResId == 0) {
            return null;
        }
        Drawable drawable2 = ResourcesCompat.getDrawable(this.mMenu.getResources(), this.mIconResId, this.mMenu.getContext().getTheme());
        this.mIconResId = 0;
        this.mIconDrawable = drawable2;
        return drawable2;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.mIntent;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.mId;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.mMenuInfo;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.mCategoryOrder;
    }

    public int getOrdering() {
        return this.mOrdering;
    }

    public char getShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    public String getShortcutLabel() {
        char shortcut = getShortcut();
        if (shortcut == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(sPrependShortcutLabel);
        if (shortcut == '\b') {
            sb.append(sDeleteShortcutLabel);
        } else if (shortcut == '\n') {
            sb.append(sEnterShortcutLabel);
        } else if (shortcut != ' ') {
            sb.append(shortcut);
        } else {
            sb.append(sSpaceShortcutLabel);
        }
        return sb.toString();
    }

    public int getShowAsAction() {
        return this.mShowAsAction;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return this.mSubMenu;
    }

    public ActionProvider getSupportActionProvider() {
        return this.mActionProvider;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.mTitle;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.mTitleCondensed;
        return charSequence != null ? charSequence : this.mTitle;
    }

    public CharSequence getTitleForItemView(MenuView.ItemView itemView) {
        return (itemView == null || !itemView.prefersCondensedTitle()) ? getTitle() : getTitleCondensed();
    }

    public View getView() {
        return this.mView;
    }

    public boolean hasAttachBadge() {
        return this.mAttachBadge;
    }

    public boolean hasCollapsibleActionView() {
        return ((this.mShowAsAction & 8) == 0 || this.mActionView == null) ? false : true;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.mSubMenu != null;
    }

    public boolean invoke() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.mClickListener;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        MenuBuilder menuBuilder = this.mMenu;
        if (menuBuilder.dispatchMenuItemSelected(menuBuilder.getRootMenu(), this)) {
            return true;
        }
        Runnable runnable = this.mItemCallback;
        if (runnable != null) {
            runnable.run();
            return true;
        }
        if (this.mIntent != null) {
            try {
                this.mMenu.getContext().startActivity(this.mIntent);
                return true;
            } catch (ActivityNotFoundException e2) {
                Log.e(TAG, "Can't find activity to handle intent; ignoring", e2);
            }
        }
        ActionProvider actionProvider = this.mActionProvider;
        return actionProvider != null && actionProvider.onPerformDefaultAction();
    }

    public boolean isActionButton() {
        return (this.mFlags & 32) == 32;
    }

    @Override // android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.mIsActionViewExpanded;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.mFlags & 1) == 1;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.mFlags & 2) == 2;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.mFlags & 16) != 0;
    }

    public boolean isExclusiveCheckable() {
        return (this.mFlags & 4) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        ActionProvider actionProvider = this.mActionProvider;
        return (actionProvider == null || !actionProvider.overridesItemVisibility()) ? (this.mFlags & 8) == 0 : (this.mFlags & 8) == 0 && this.mActionProvider.isVisible();
    }

    public boolean requestsActionButton() {
        return (this.mShowAsAction & 1) == 1;
    }

    public boolean requiresActionButton() {
        return (this.mShowAsAction & 2) == 2;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("Implementation should use setSupportActionProvider!");
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(View view) {
        int i2;
        this.mActionView = view;
        this.mView = view;
        this.mActionProvider = null;
        if (view != null && view.getId() == -1 && (i2 = this.mId) > 0) {
            view.setId(i2);
        }
        this.mMenu.onItemActionRequestChanged(this);
        return this;
    }

    public void setActionViewExpanded(boolean z2) {
        this.mIsActionViewExpanded = z2;
        this.mMenu.onItemsChanged(false);
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        if (this.mShortcutAlphabeticChar == c2) {
            return this;
        }
        this.mShortcutAlphabeticChar = Character.toLowerCase(c2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public void setBadgeNeeded(boolean z2) {
        setBadgeNeeded(z2, 2);
    }

    public MenuItem setCallback(Runnable runnable) {
        this.mItemCallback = runnable;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z2) {
        int i2 = this.mFlags;
        int i3 = (z2 ? 1 : 0) | (i2 & (-2));
        this.mFlags = i3;
        if (i2 != i3) {
            this.mMenu.onItemsChanged(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z2) {
        if ((this.mFlags & 4) != 0) {
            this.mMenu.setExclusiveItemChecked(this);
        } else {
            setCheckedInt(z2);
        }
        return this;
    }

    public void setCheckedInt(boolean z2) {
        int i2 = this.mFlags;
        int i3 = (z2 ? 2 : 0) | (i2 & (-3));
        this.mFlags = i3;
        if (i2 != i3) {
            this.mMenu.onItemsChanged(false);
        }
    }

    @Override // android.view.MenuItem
    public MenuItem setContentDescription(CharSequence charSequence) {
        this.mContentDescription = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z2) {
        if (z2) {
            this.mFlags |= 16;
        } else {
            this.mFlags &= -17;
        }
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public void setExclusiveCheckable(boolean z2) {
        this.mFlags = (z2 ? 4 : 0) | (this.mFlags & (-5));
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.mIconResId = 0;
        this.mIconDrawable = drawable;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    public void setIsActionButton(boolean z2) {
        if (z2) {
            this.mFlags |= 32;
        } else {
            this.mFlags &= -33;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void setMenu(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    public void setMenuInfo(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.mMenuInfo = contextMenuInfo;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        if (this.mShortcutNumericChar == c2) {
            return this;
        }
        this.mShortcutNumericChar = c2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        return setSupportOnActionExpandListener(onActionExpandListener);
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.mClickListener = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        this.mShortcutNumericChar = c2;
        this.mShortcutAlphabeticChar = Character.toLowerCase(c3);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public void setShowAsAction(int i2) {
        int i3 = i2 & 3;
        if (i3 != 0 && i3 != 1 && i3 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.mShowAsAction = i2;
        this.mMenu.onItemActionRequestChanged(this);
    }

    @Override // android.view.MenuItem
    public MenuItem setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    public void setSubMenu(SubMenuBuilder subMenuBuilder) {
        this.mSubMenu = subMenuBuilder;
        subMenuBuilder.setHeaderTitle(getTitle());
    }

    public MenuItem setSupportActionProvider(ActionProvider actionProvider) {
        ActionProvider actionProvider2 = this.mActionProvider;
        if (actionProvider2 == actionProvider) {
            return this;
        }
        this.mActionView = null;
        if (actionProvider2 != null) {
            actionProvider2.setVisibilityListener(null);
        }
        this.mActionProvider = actionProvider;
        this.mMenu.onItemsChanged(true);
        if (actionProvider != null) {
            actionProvider.setVisibilityListener(new ActionProvider.VisibilityListener() { // from class: miuix.appcompat.internal.view.menu.MenuItemImpl.2
                @Override // android.view.ActionProvider.VisibilityListener
                public void onActionProviderVisibilityChanged(boolean z2) {
                    MenuItemImpl.this.mMenu.onItemVisibleChanged(MenuItemImpl.this);
                }
            });
        }
        return this;
    }

    public MenuItem setSupportOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.mOnActionExpandListener = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        this.mMenu.onItemsChanged(false);
        SubMenuBuilder subMenuBuilder = this.mSubMenu;
        if (subMenuBuilder != null) {
            subMenuBuilder.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.mTitleCondensed = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public void setView(View view) {
        if (view != null) {
            ViewCompat.setAccessibilityDelegate(view, new AccessibilityDelegateCompat() { // from class: miuix.appcompat.internal.view.menu.MenuItemImpl.1
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(@NonNull View view2, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat);
                    accessibilityNodeInfoCompat.setClassName(Button.class.getName());
                }
            });
        }
        this.mView = view;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z2) {
        if (setVisibleInt(z2)) {
            this.mMenu.onItemVisibleChanged(this);
        }
        return this;
    }

    public boolean setVisibleInt(boolean z2) {
        int i2 = this.mFlags;
        int i3 = (z2 ? 0 : 8) | (i2 & (-9));
        this.mFlags = i3;
        return i2 != i3;
    }

    public boolean shouldShowIcon() {
        return this.mMenu.getOptionalIconsVisible();
    }

    public boolean shouldShowShortcut() {
        return this.mMenu.isShortcutsVisible() && getShortcut() != 0;
    }

    public boolean showsTextAsAction() {
        return (this.mShowAsAction & 4) == 4;
    }

    public String toString() {
        return this.mTitle.toString();
    }

    public void updateBadgeDrawable() {
        View view;
        if (this.mNeedBadge && (view = this.mView) != null && view.getVisibility() == 0) {
            getBadgeDrawable().attachBadgeDrawable(this.mView, this.mBadgeGravity);
            this.mAttachBadge = true;
            return;
        }
        BadgeDrawable badgeDrawable = this.mBadgeDrawable;
        if (badgeDrawable != null) {
            badgeDrawable.detachBadgeDrawable();
            this.mBadgeDrawable = null;
            this.mAttachBadge = false;
        } else {
            View view2 = this.mView;
            if (view2 != null) {
                view2.getOverlay().clear();
                this.mAttachBadge = false;
            }
        }
    }

    public void setBadgeNeeded(boolean z2, int i2) {
        this.mNeedBadge = z2;
        this.mBadgeGravity = i2;
    }

    private BadgeDrawable getBadgeDrawable(BadgeDrawable.BadgeConfig badgeConfig) {
        if (this.mBadgeDrawable == null) {
            this.mBadgeDrawable = new BadgeDrawable(this.mMenu.getContext(), badgeConfig);
        }
        return this.mBadgeDrawable;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i2) {
        this.mIconDrawable = null;
        this.mIconResId = i2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i2) {
        return setTitle(this.mMenu.getContext().getString(i2));
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(int i2) {
        Context context = this.mMenu.getContext();
        setActionView(LayoutInflater.from(context).inflate(i2, (ViewGroup) new LinearLayout(context), false));
        return this;
    }

    public void updateBadgeDrawable(int i2) {
        View view;
        if (this.mNeedBadge && (view = this.mView) != null && view.getVisibility() == 0) {
            getBadgeDrawable(BadgeDrawable.BadgeConfig.EXPAND_OUTSIDE).updateNumberOnBadge(i2, this.mView);
            this.mAttachBadge = true;
            return;
        }
        BadgeDrawable badgeDrawable = this.mBadgeDrawable;
        if (badgeDrawable != null) {
            badgeDrawable.detachBadgeDrawable();
            this.mBadgeDrawable = null;
            this.mAttachBadge = false;
        } else {
            View view2 = this.mView;
            if (view2 != null) {
                view2.getOverlay().clear();
                this.mAttachBadge = false;
            }
        }
    }
}
