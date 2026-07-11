package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;
import androidx.core.internal.view.SupportMenuItem;

/* JADX INFO: loaded from: classes.dex */
public final class MenuItemCompat {

    @Deprecated
    public static final int SHOW_AS_ACTION_ALWAYS = 2;

    @Deprecated
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;

    @Deprecated
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;

    @Deprecated
    public static final int SHOW_AS_ACTION_NEVER = 0;

    @Deprecated
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";

    @RequiresApi(26)
    public static class Api26Impl {
        private Api26Impl() {
        }

        public static int getAlphabeticModifiers(MenuItem menuItem) {
            return menuItem.getAlphabeticModifiers();
        }

        public static CharSequence getContentDescription(MenuItem menuItem) {
            return menuItem.getContentDescription();
        }

        public static ColorStateList getIconTintList(MenuItem menuItem) {
            return menuItem.getIconTintList();
        }

        public static PorterDuff.Mode getIconTintMode(MenuItem menuItem) {
            return menuItem.getIconTintMode();
        }

        public static int getNumericModifiers(MenuItem menuItem) {
            return menuItem.getNumericModifiers();
        }

        public static CharSequence getTooltipText(MenuItem menuItem) {
            return menuItem.getTooltipText();
        }

        public static MenuItem setAlphabeticShortcut(MenuItem menuItem, char c2, int i2) {
            return menuItem.setAlphabeticShortcut(c2, i2);
        }

        public static MenuItem setContentDescription(MenuItem menuItem, CharSequence charSequence) {
            return menuItem.setContentDescription(charSequence);
        }

        public static MenuItem setIconTintList(MenuItem menuItem, ColorStateList colorStateList) {
            return menuItem.setIconTintList(colorStateList);
        }

        public static MenuItem setIconTintMode(MenuItem menuItem, PorterDuff.Mode mode) {
            return menuItem.setIconTintMode(mode);
        }

        public static MenuItem setNumericShortcut(MenuItem menuItem, char c2, int i2) {
            return menuItem.setNumericShortcut(c2, i2);
        }

        public static MenuItem setShortcut(MenuItem menuItem, char c2, char c3, int i2, int i3) {
            return menuItem.setShortcut(c2, c3, i2, i3);
        }

        public static MenuItem setTooltipText(MenuItem menuItem, CharSequence charSequence) {
            return menuItem.setTooltipText(charSequence);
        }
    }

    @Deprecated
    public interface OnActionExpandListener {
        boolean onMenuItemActionCollapse(MenuItem menuItem);

        boolean onMenuItemActionExpand(MenuItem menuItem);
    }

    private MenuItemCompat() {
    }

    @ReplaceWith(expression = "item.collapseActionView()")
    @Deprecated
    public static boolean collapseActionView(MenuItem menuItem) {
        return menuItem.collapseActionView();
    }

    @ReplaceWith(expression = "item.expandActionView()")
    @Deprecated
    public static boolean expandActionView(MenuItem menuItem) {
        return menuItem.expandActionView();
    }

    public static ActionProvider getActionProvider(MenuItem menuItem) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem) menuItem).getSupportActionProvider();
        }
        Log.w(TAG, "getActionProvider: item does not implement SupportMenuItem; returning null");
        return null;
    }

    @ReplaceWith(expression = "item.getActionView()")
    @Deprecated
    public static View getActionView(MenuItem menuItem) {
        return menuItem.getActionView();
    }

    public static int getAlphabeticModifiers(MenuItem menuItem) {
        return menuItem instanceof SupportMenuItem ? ((SupportMenuItem) menuItem).getAlphabeticModifiers() : Api26Impl.getAlphabeticModifiers(menuItem);
    }

    public static CharSequence getContentDescription(MenuItem menuItem) {
        return menuItem instanceof SupportMenuItem ? ((SupportMenuItem) menuItem).getContentDescription() : Api26Impl.getContentDescription(menuItem);
    }

    public static ColorStateList getIconTintList(MenuItem menuItem) {
        return menuItem instanceof SupportMenuItem ? ((SupportMenuItem) menuItem).getIconTintList() : Api26Impl.getIconTintList(menuItem);
    }

    public static PorterDuff.Mode getIconTintMode(MenuItem menuItem) {
        return menuItem instanceof SupportMenuItem ? ((SupportMenuItem) menuItem).getIconTintMode() : Api26Impl.getIconTintMode(menuItem);
    }

    public static int getNumericModifiers(MenuItem menuItem) {
        return menuItem instanceof SupportMenuItem ? ((SupportMenuItem) menuItem).getNumericModifiers() : Api26Impl.getNumericModifiers(menuItem);
    }

    public static CharSequence getTooltipText(MenuItem menuItem) {
        return menuItem instanceof SupportMenuItem ? ((SupportMenuItem) menuItem).getTooltipText() : Api26Impl.getTooltipText(menuItem);
    }

    @ReplaceWith(expression = "item.isActionViewExpanded()")
    @Deprecated
    public static boolean isActionViewExpanded(MenuItem menuItem) {
        return menuItem.isActionViewExpanded();
    }

    public static MenuItem setActionProvider(MenuItem menuItem, ActionProvider actionProvider) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem) menuItem).setSupportActionProvider(actionProvider);
        }
        Log.w(TAG, "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }

    @ReplaceWith(expression = "item.setActionView(view)")
    @Deprecated
    public static MenuItem setActionView(MenuItem menuItem, View view) {
        return menuItem.setActionView(view);
    }

    public static void setAlphabeticShortcut(MenuItem menuItem, char c2, int i2) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setAlphabeticShortcut(c2, i2);
        } else {
            Api26Impl.setAlphabeticShortcut(menuItem, c2, i2);
        }
    }

    public static void setContentDescription(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setContentDescription(charSequence);
        } else {
            Api26Impl.setContentDescription(menuItem, charSequence);
        }
    }

    public static void setIconTintList(MenuItem menuItem, ColorStateList colorStateList) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setIconTintList(colorStateList);
        } else {
            Api26Impl.setIconTintList(menuItem, colorStateList);
        }
    }

    public static void setIconTintMode(MenuItem menuItem, PorterDuff.Mode mode) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setIconTintMode(mode);
        } else {
            Api26Impl.setIconTintMode(menuItem, mode);
        }
    }

    public static void setNumericShortcut(MenuItem menuItem, char c2, int i2) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setNumericShortcut(c2, i2);
        } else {
            Api26Impl.setNumericShortcut(menuItem, c2, i2);
        }
    }

    @Deprecated
    public static MenuItem setOnActionExpandListener(MenuItem menuItem, final OnActionExpandListener onActionExpandListener) {
        return menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() { // from class: androidx.core.view.MenuItemCompat.1
            @Override // android.view.MenuItem.OnActionExpandListener
            public boolean onMenuItemActionCollapse(MenuItem menuItem2) {
                return onActionExpandListener.onMenuItemActionCollapse(menuItem2);
            }

            @Override // android.view.MenuItem.OnActionExpandListener
            public boolean onMenuItemActionExpand(MenuItem menuItem2) {
                return onActionExpandListener.onMenuItemActionExpand(menuItem2);
            }
        });
    }

    public static void setShortcut(MenuItem menuItem, char c2, char c3, int i2, int i3) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setShortcut(c2, c3, i2, i3);
        } else {
            Api26Impl.setShortcut(menuItem, c2, c3, i2, i3);
        }
    }

    @ReplaceWith(expression = "item.setShowAsAction(actionEnum)")
    @Deprecated
    public static void setShowAsAction(MenuItem menuItem, int i2) {
        menuItem.setShowAsAction(i2);
    }

    public static void setTooltipText(MenuItem menuItem, CharSequence charSequence) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setTooltipText(charSequence);
        } else {
            Api26Impl.setTooltipText(menuItem, charSequence);
        }
    }

    @ReplaceWith(expression = "item.setActionView(resId)")
    @Deprecated
    public static MenuItem setActionView(MenuItem menuItem, int i2) {
        return menuItem.setActionView(i2);
    }
}
