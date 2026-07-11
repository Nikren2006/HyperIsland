package miuix.appcompat.view.menu;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.List;
import java.util.Map;
import miuix.androidbasewidget.widget.CheckedTextView;
import miuix.appcompat.R;
import miuix.appcompat.view.menu.HyperMenuContract;
import miuix.internal.util.AnimHelper;
import miuix.internal.util.TaggingDrawableUtil;

/* JADX INFO: loaded from: classes3.dex */
public class HyperBaseAdapter extends BaseAdapter {
    public static final int TYPE_MENU_DIVIDER = 1;
    public static final int TYPE_MENU_ITEM = 0;
    protected LayoutInflater mInflater;
    protected List<HyperMenuContract.HyperMenuItem> mMenuItemList;
    private boolean mOptionalIconsVisible;

    public class MenuDividerHolder {
        public MenuDividerHolder() {
        }
    }

    public class MenuItemViewHolder {
        ImageView arrow;
        ImageView iconView;
        CheckedTextView titleView;

        public MenuItemViewHolder() {
        }
    }

    public HyperBaseAdapter() {
    }

    private View handleDivider(View view, ViewGroup viewGroup) {
        if (view != null && view.getTag().getClass() == MenuDividerHolder.class) {
            return view;
        }
        MenuDividerHolder menuDividerHolder = new MenuDividerHolder();
        View viewInflate = this.mInflater.inflate(R.layout.miuix_appcompat_popup_menu_divider, viewGroup, false);
        viewInflate.setTag(menuDividerHolder);
        return viewInflate;
    }

    private View handleMenuItem(int i2, View view, ViewGroup viewGroup) {
        MenuItemViewHolder menuItemViewHolder;
        if (view == null || view.getTag().getClass() != MenuItemViewHolder.class) {
            MenuItemViewHolder menuItemViewHolder2 = new MenuItemViewHolder();
            View viewInflate = this.mInflater.inflate(R.layout.miuix_appcompat_hyper_popup_menu_item, viewGroup, false);
            menuItemViewHolder2.titleView = (CheckedTextView) viewInflate.findViewById(android.R.id.text1);
            menuItemViewHolder2.iconView = (ImageView) viewInflate.findViewById(android.R.id.icon);
            menuItemViewHolder2.arrow = (ImageView) viewInflate.findViewById(R.id.arrow);
            viewInflate.setTag(menuItemViewHolder2);
            AnimHelper.addItemPressEffect(viewInflate);
            menuItemViewHolder = menuItemViewHolder2;
            view = viewInflate;
        } else {
            menuItemViewHolder = (MenuItemViewHolder) view.getTag();
        }
        HyperMenuContract.HyperMenuTextItem hyperMenuTextItem = (HyperMenuContract.HyperMenuTextItem) this.mMenuItemList.get(i2);
        menuItemViewHolder.titleView.setText(hyperMenuTextItem.getMenuItem().getTitle());
        menuItemViewHolder.titleView.setChecked(hyperMenuTextItem.isChecked());
        if (!this.mOptionalIconsVisible || hyperMenuTextItem.getMenuItem().getIcon() == null) {
            menuItemViewHolder.iconView.setVisibility(8);
        } else {
            menuItemViewHolder.iconView.setImageDrawable(hyperMenuTextItem.getMenuItem().getIcon());
            menuItemViewHolder.iconView.setVisibility(0);
        }
        menuItemViewHolder.arrow.setVisibility((hyperMenuTextItem.isExpandable || hyperMenuTextItem.isHeaderItem) ? 0 : 8);
        TaggingDrawableUtil.updateItemPadding(view, i2, this.mMenuItemList.size());
        if (HyperMenuContract.CheckableType.NON_SUPPORT.equals(hyperMenuTextItem.checkStatus)) {
            setAccessibilityDelegateNonCheckable(view, hyperMenuTextItem);
        } else {
            setAccessibilityDelegate(view, menuItemViewHolder.titleView);
        }
        return view;
    }

    private void setAccessibilityDelegate(View view, final CheckedTextView checkedTextView) {
        ViewCompat.setAccessibilityDelegate(view, new AccessibilityDelegateCompat() { // from class: miuix.appcompat.view.menu.HyperBaseAdapter.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(@NonNull View view2, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setCheckable(true);
                accessibilityNodeInfoCompat.setChecked(checkedTextView.isChecked());
                String string = checkedTextView.getText().toString();
                if (!TextUtils.isEmpty(string)) {
                    accessibilityNodeInfoCompat.setContentDescription(string);
                }
                if (checkedTextView.isChecked()) {
                    return;
                }
                accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            }
        });
        ViewCompat.setAccessibilityDelegate(checkedTextView, new AccessibilityDelegateCompat() { // from class: miuix.appcompat.view.menu.HyperBaseAdapter.2
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(@NonNull View view2, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setCheckable(false);
            }
        });
    }

    private void setAccessibilityDelegateNonCheckable(View view, final HyperMenuContract.HyperMenuTextItem hyperMenuTextItem) {
        ViewCompat.setAccessibilityDelegate(view, new AccessibilityDelegateCompat() { // from class: miuix.appcompat.view.menu.HyperBaseAdapter.3
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(@NonNull View view2, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setClickable(true);
                HyperMenuContract.HyperMenuTextItem hyperMenuTextItem2 = hyperMenuTextItem;
                if (hyperMenuTextItem2 != null) {
                    String string = hyperMenuTextItem2.getMenuItem() != null ? hyperMenuTextItem.getMenuItem().getTitle().toString() : null;
                    if (!TextUtils.isEmpty(string)) {
                        accessibilityNodeInfoCompat.setContentDescription(string);
                    }
                    HyperMenuContract.HyperMenuTextItem hyperMenuTextItem3 = hyperMenuTextItem;
                    if (hyperMenuTextItem3.isExpandable && !hyperMenuTextItem3.isHeaderItem) {
                        accessibilityNodeInfoCompat.setStateDescription(view2.getContext().getResources().getString(R.string.miuix_appcompat_accessibility_collapse_state));
                    } else if (hyperMenuTextItem3.isHeaderItem) {
                        accessibilityNodeInfoCompat.setStateDescription(view2.getContext().getResources().getString(R.string.miuix_appcompat_accessibility_expand_state));
                    }
                }
            }
        });
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mMenuItemList.size();
    }

    public HyperMenuContract.HyperMenuItem getHyperMenuItem(int i2) {
        return this.mMenuItemList.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return this.mMenuItemList.get(i2).getItemId();
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i2) {
        return this.mMenuItemList.get(i2) instanceof HyperMenuContract.HyperMenuDivider ? 1 : 0;
    }

    public boolean getOptionalIconsVisible() {
        return this.mOptionalIconsVisible;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i2);
        if (itemViewType == 0) {
            return handleMenuItem(i2, view, viewGroup);
        }
        if (itemViewType == 1) {
            return handleDivider(view, viewGroup);
        }
        return null;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 2;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i2) {
        return getItemViewType(i2) == 0;
    }

    public void preCheckPrimaryItem(Map<Integer, Boolean> map) {
    }

    public void preCheckSecondaryItem(Map<Integer, Boolean[]> map) {
    }

    public void setOptionalIconsVisible(boolean z2) {
        this.mOptionalIconsVisible = z2;
    }

    public HyperBaseAdapter(@NonNull LayoutInflater layoutInflater, @NonNull List<HyperMenuContract.HyperMenuItem> list) {
        this.mInflater = layoutInflater;
        this.mMenuItemList = list;
    }

    @Override // android.widget.Adapter
    public MenuItem getItem(int i2) {
        return this.mMenuItemList.get(i2).getMenuItem();
    }
}
