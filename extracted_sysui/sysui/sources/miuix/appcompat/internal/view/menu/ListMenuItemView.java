package miuix.appcompat.internal.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRadioButton;
import miuix.appcompat.R;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import miuix.appcompat.internal.view.menu.MenuView;

/* JADX INFO: loaded from: classes3.dex */
public class ListMenuItemView extends LinearLayout implements MenuView.ItemView {
    private Drawable mBackground;
    private AppCompatCheckBox mCheckBox;
    private Context mContext;
    private boolean mForceShowIcon;
    private AppCompatImageView mIconView;
    private LayoutInflater mInflater;
    private MenuItemImpl mItemData;
    private boolean mPreserveIconSpacing;
    private AppCompatRadioButton mRadioButton;
    private View mRelativeLayout;
    private TextView mShortcutView;
    private int mTextAppearance;
    private Context mTextAppearanceContext;
    private TextView mTitleView;

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        this.mContext = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MenuView, i2, 0);
        this.mBackground = typedArrayObtainStyledAttributes.getDrawable(R.styleable.MenuView_android_itemBackground);
        this.mTextAppearance = typedArrayObtainStyledAttributes.getResourceId(R.styleable.MenuView_android_itemTextAppearance, -1);
        this.mPreserveIconSpacing = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MenuView_preserveIconSpacing, false);
        this.mTextAppearanceContext = context;
        typedArrayObtainStyledAttributes.recycle();
    }

    private LayoutInflater getInflater() {
        if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from(this.mContext);
        }
        return this.mInflater;
    }

    private void insertCheckBox() {
        AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) getInflater().inflate(R.layout.miuix_appcompat_list_menu_item_checkbox, (ViewGroup) this, false);
        this.mCheckBox = appCompatCheckBox;
        addView(appCompatCheckBox);
    }

    private void insertIconView() {
        AppCompatImageView appCompatImageView = (AppCompatImageView) getInflater().inflate(R.layout.miuix_appcompat_list_menu_item_icon, (ViewGroup) this, false);
        this.mIconView = appCompatImageView;
        addView(appCompatImageView, 0);
    }

    private void insertRadioButton() {
        AppCompatRadioButton appCompatRadioButton = (AppCompatRadioButton) getInflater().inflate(R.layout.miuix_appcompat_list_menu_item_radio, (ViewGroup) this, false);
        this.mRadioButton = appCompatRadioButton;
        addView(appCompatRadioButton, 0);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void initialize(MenuItemImpl menuItemImpl, int i2) {
        this.mItemData = menuItemImpl;
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        setTitle(menuItemImpl.getTitleForItemView(this));
        setCheckable(menuItemImpl.isCheckable());
        setShortcut(menuItemImpl.shouldShowShortcut(), menuItemImpl.getShortcut());
        setIcon(menuItemImpl.getIcon());
        setEnabled(menuItemImpl.isEnabled());
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setBackground(this.mBackground);
        TextView textView = (TextView) findViewById(R.id.title);
        this.mTitleView = textView;
        int i2 = this.mTextAppearance;
        if (i2 != -1) {
            textView.setTextAppearance(this.mTextAppearanceContext, i2);
        }
        this.mShortcutView = (TextView) findViewById(R.id.shortcut);
        this.mRelativeLayout = getChildAt(0);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.mIconView != null && this.mPreserveIconSpacing) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mIconView.getLayoutParams();
            int i4 = layoutParams.height;
            if (i4 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i4;
            }
        }
        super.onMeasure(i2, i3);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public boolean prefersCondensedTitle() {
        return false;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void setCheckable(boolean z2) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (!z2 && this.mRadioButton == null && this.mCheckBox == null) {
            return;
        }
        if (this.mItemData.isExclusiveCheckable()) {
            if (this.mRadioButton == null) {
                insertRadioButton();
            }
            compoundButton = this.mRadioButton;
            compoundButton2 = this.mCheckBox;
        } else {
            if (this.mCheckBox == null) {
                insertCheckBox();
            }
            compoundButton = this.mCheckBox;
            compoundButton2 = this.mRadioButton;
        }
        if (z2) {
            compoundButton.setChecked(this.mItemData.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (compoundButton2 == null || compoundButton2.getVisibility() == 8) {
                return;
            }
            compoundButton2.setVisibility(8);
            return;
        }
        AppCompatCheckBox appCompatCheckBox = this.mCheckBox;
        if (appCompatCheckBox != null) {
            appCompatCheckBox.setVisibility(8);
        }
        AppCompatRadioButton appCompatRadioButton = this.mRadioButton;
        if (appCompatRadioButton != null) {
            appCompatRadioButton.setVisibility(8);
        }
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void setChecked(boolean z2) {
        CompoundButton compoundButton;
        if (this.mItemData.isExclusiveCheckable()) {
            if (this.mRadioButton == null) {
                insertRadioButton();
            }
            compoundButton = this.mRadioButton;
        } else {
            if (this.mCheckBox == null) {
                insertCheckBox();
            }
            compoundButton = this.mCheckBox;
        }
        compoundButton.setChecked(z2);
    }

    public void setForceShowIcon(boolean z2) {
        this.mForceShowIcon = z2;
        this.mPreserveIconSpacing = z2;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void setIcon(Drawable drawable) {
        boolean z2 = this.mItemData.shouldShowIcon() || this.mForceShowIcon;
        if (z2 || this.mPreserveIconSpacing) {
            AppCompatImageView appCompatImageView = this.mIconView;
            if (appCompatImageView == null && drawable == null && !this.mPreserveIconSpacing) {
                return;
            }
            if (appCompatImageView == null) {
                insertIconView();
            }
            if (drawable == null && !this.mPreserveIconSpacing) {
                this.mIconView.setVisibility(8);
                return;
            }
            AppCompatImageView appCompatImageView2 = this.mIconView;
            if (!z2) {
                drawable = null;
            }
            appCompatImageView2.setImageDrawable(drawable);
            if (this.mIconView.getVisibility() != 0) {
                this.mIconView.setVisibility(0);
            }
        }
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void setItemInvoker(MenuBuilder.ItemInvoker itemInvoker) {
        throw new UnsupportedOperationException();
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void setShortcut(boolean z2, char c2) {
        int i2 = (z2 && this.mItemData.shouldShowShortcut()) ? 0 : 8;
        if (i2 == 0) {
            this.mShortcutView.setText(this.mItemData.getShortcutLabel());
        }
        if (this.mShortcutView.getVisibility() != i2) {
            this.mShortcutView.setVisibility(i2);
        }
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.mTitleView.getVisibility() != 8) {
                this.mTitleView.setVisibility(8);
            }
        } else {
            this.mTitleView.setText(charSequence);
            if (this.mTitleView.getVisibility() != 0) {
                this.mTitleView.setVisibility(0);
            }
        }
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView.ItemView
    public boolean showsIcon() {
        return this.mForceShowIcon;
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
