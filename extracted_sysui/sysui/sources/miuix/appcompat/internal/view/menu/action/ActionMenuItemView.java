package miuix.appcompat.internal.view.menu.action;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import miuix.appcompat.R;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import miuix.appcompat.internal.view.menu.MenuItemImpl;
import miuix.appcompat.internal.view.menu.MenuView;
import miuix.core.util.MiuixUIUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ActionMenuItemView extends LinearLayout implements MenuView.ItemView {
    private final ActionMenuItemViewChildren mChildren;
    private boolean mIsCheckable;
    private MenuItemImpl mItemData;
    private MenuBuilder.ItemInvoker mItemInvoker;

    public ActionMenuItemView(Context context) {
        this(context, null, 0);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void initialize(MenuItemImpl menuItemImpl, int i2) {
        this.mItemData = menuItemImpl;
        setSelected(false);
        setTitle(menuItemImpl.getTitle());
        setIcon(menuItemImpl.getIcon());
        setCheckable(menuItemImpl.isCheckable());
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        setClickable(true);
        this.mChildren.setContentDescription(menuItemImpl.getContentDescription());
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mChildren.onConfigurationChanged(configuration);
    }

    @Override // android.view.View
    public boolean performClick() {
        if (super.performClick()) {
            return true;
        }
        MenuBuilder.ItemInvoker itemInvoker = this.mItemInvoker;
        if (itemInvoker == null || !itemInvoker.invokeItem(this.mItemData, 0)) {
            return false;
        }
        playSoundEffect(0);
        return true;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public boolean prefersCondensedTitle() {
        return false;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void setCheckable(boolean z2) {
        this.mIsCheckable = z2;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void setChecked(boolean z2) {
        if (this.mIsCheckable) {
            setSelected(z2);
        }
    }

    @Override // android.view.View, miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void setEnabled(boolean z2) {
        super.setEnabled(z2);
        this.mChildren.setEnabled(z2);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void setIcon(Drawable drawable) {
        this.mChildren.setIcon(drawable);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void setItemInvoker(MenuBuilder.ItemInvoker itemInvoker) {
        this.mItemInvoker = itemInvoker;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void setShortcut(boolean z2, char c2) {
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void setTitle(CharSequence charSequence) {
        this.mChildren.setText(charSequence);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public boolean showsIcon() {
        return true;
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        boolean z2 = false;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ActionMenuItemView, R.attr.actionButtonStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(R.styleable.ActionMenuItemView_largeFontAdaptationEnabled, true) && MiuixUIUtils.getFontLevel(context) == 2) {
            z2 = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuItemViewChildren actionMenuItemViewChildren = new ActionMenuItemViewChildren(this);
        this.mChildren = actionMenuItemViewChildren;
        actionMenuItemViewChildren.setLargeFontEnabled(z2);
    }
}
