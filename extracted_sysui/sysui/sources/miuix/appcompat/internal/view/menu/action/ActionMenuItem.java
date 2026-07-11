package miuix.appcompat.internal.view.menu.action;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public class ActionMenuItem implements MenuItem {
    private static final int CHECKABLE = 1;
    private static final int CHECKED = 2;
    private static final int ENABLED = 16;
    private static final int EXCLUSIVE = 4;
    private static final int HIDDEN = 8;
    private final int mCategoryOrder;
    private MenuItem.OnMenuItemClickListener mClickListener;
    private Context mContext;
    private int mFlags = 16;
    private final int mGroup;
    private Drawable mIconDrawable;
    private final int mId;
    private Intent mIntent;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private char mShortcutNumericChar;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;

    public ActionMenuItem(Context context, int i2, int i3, int i4, int i5, CharSequence charSequence) {
        this.mContext = context;
        this.mId = i3;
        this.mGroup = i2;
        this.mCategoryOrder = i4;
        this.mOrdering = i5;
        this.mTitle = charSequence;
    }

    @Override // android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public View getActionView() {
        return null;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.mGroup;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.mIconDrawable;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.mIntent;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.mId;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.mOrdering;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    public ActionProvider getSupportActionProvider() {
        return null;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.mTitle;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        return this.mTitleCondensed;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    public boolean invoke() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.mClickListener;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        Intent intent = this.mIntent;
        if (intent == null) {
            return false;
        }
        this.mContext.startActivity(intent);
        return true;
    }

    @Override // android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.mFlags & 1) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.mFlags & 2) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.mFlags & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return (this.mFlags & 8) == 0;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        this.mShortcutAlphabeticChar = c2;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z2) {
        this.mFlags = (z2 ? 1 : 0) | (this.mFlags & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z2) {
        this.mFlags = (z2 ? 2 : 0) | (this.mFlags & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z2) {
        this.mFlags = (z2 ? 16 : 0) | (this.mFlags & (-17));
        return this;
    }

    public ActionMenuItem setExclusiveCheckable(boolean z2) {
        this.mFlags = (z2 ? 4 : 0) | (this.mFlags & (-5));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.mIconDrawable = drawable;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        this.mShortcutNumericChar = c2;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.mClickListener = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        this.mShortcutNumericChar = c2;
        this.mShortcutAlphabeticChar = c3;
        return this;
    }

    @Override // android.view.MenuItem
    public void setShowAsAction(int i2) {
    }

    @Override // android.view.MenuItem
    public MenuItem setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    public MenuItem setSupportActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setSupportOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.mTitleCondensed = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z2) {
        this.mFlags = (this.mFlags & 8) | (z2 ? 0 : 8);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i2) {
        this.mIconDrawable = this.mContext.getResources().getDrawable(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i2) {
        this.mTitle = this.mContext.getResources().getString(i2);
        return this;
    }
}
