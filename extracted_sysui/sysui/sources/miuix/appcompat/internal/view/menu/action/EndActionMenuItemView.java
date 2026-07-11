package miuix.appcompat.internal.view.menu.action;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import miuix.animation.Folme;
import miuix.animation.IHoverStyle;
import miuix.animation.ITouchStyle;
import miuix.animation.ViewHoverListener;
import miuix.animation.base.AnimConfig;
import miuix.appcompat.R;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import miuix.appcompat.internal.view.menu.MenuItemImpl;
import miuix.appcompat.internal.view.menu.MenuView;

/* JADX INFO: loaded from: classes3.dex */
public class EndActionMenuItemView extends LinearLayout implements MenuView.ItemView, ViewHoverListener {
    private ImageView mImageView;
    private boolean mIsCheckable;
    private boolean mIsHover;
    private MenuItemImpl mItemData;
    private MenuBuilder.ItemInvoker mItemInvoker;

    public EndActionMenuItemView(Context context) {
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
        if (TextUtils.isEmpty(menuItemImpl.getContentDescription())) {
            setContentDescription(menuItemImpl.getTitle());
        } else {
            setContentDescription(menuItemImpl.getContentDescription());
        }
    }

    @Override // miuix.animation.ViewHoverListener
    public boolean isHover() {
        return this.mIsHover;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // miuix.animation.ViewHoverListener
    public void onEnterHover() {
        this.mIsHover = true;
    }

    @Override // miuix.animation.ViewHoverListener
    public void onExitHover() {
        this.mIsHover = false;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        MenuItemImpl menuItemImpl = this.mItemData;
        if (menuItemImpl == null || !menuItemImpl.hasAttachBadge()) {
            return;
        }
        accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + getResources().getString(R.string.miuix_appcompat_accessibility_new_message));
    }

    @Override // miuix.animation.ViewHoverListener
    public void onMoveHover() {
    }

    @Override // android.view.View
    public boolean performClick() {
        if (super.performClick()) {
            return true;
        }
        int i2 = this.mItemData.getItemId() == R.id.more ? 1 : 0;
        MenuBuilder.ItemInvoker itemInvoker = this.mItemInvoker;
        if (itemInvoker == null || !itemInvoker.invokeItem(this.mItemData, i2)) {
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
        this.mImageView.setEnabled(z2);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void setIcon(Drawable drawable) {
        if (this.mImageView.getDrawable() != drawable) {
            this.mImageView.setImageDrawable(drawable);
        }
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
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public boolean showsIcon() {
        return true;
    }

    public EndActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EndActionMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setOrientation(1);
        setGravity(17);
        LinearLayout.inflate(context, R.layout.miuix_appcompat_action_end_menu_item_child_layout, this);
        ImageView imageView = (ImageView) findViewById(R.id.action_menu_item_child_icon);
        this.mImageView = imageView;
        imageView.setForceDarkAllowed(false);
        Folme.use((View) this.mImageView).touch().setScale(1.0f, new ITouchStyle.TouchType[0]).setAlpha(0.6f, ITouchStyle.TouchType.DOWN).setAlpha(1.0f, ITouchStyle.TouchType.UP).handleTouchOf(this, new AnimConfig[0]);
        Folme.use((View) this.mImageView).hover().setFeedbackRadius(60.0f);
        Folme.use((View) this.mImageView).hover().setEffect(IHoverStyle.HoverEffect.FLOATED_WRAPPED).handleHoverOf(this, new AnimConfig[0]);
    }
}
