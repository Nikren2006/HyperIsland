package androidx.core.view;

import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.ReplaceWith;
import androidx.annotation.RequiresApi;
import androidx.core.internal.view.SupportMenu;

/* JADX INFO: loaded from: classes.dex */
public final class MenuCompat {

    @RequiresApi(28)
    public static class Api28Impl {
        private Api28Impl() {
        }

        public static void setGroupDividerEnabled(Menu menu, boolean z2) {
            menu.setGroupDividerEnabled(z2);
        }
    }

    private MenuCompat() {
    }

    public static void setGroupDividerEnabled(Menu menu, boolean z2) {
        if (menu instanceof SupportMenu) {
            ((SupportMenu) menu).setGroupDividerEnabled(z2);
        } else {
            Api28Impl.setGroupDividerEnabled(menu, z2);
        }
    }

    @ReplaceWith(expression = "item.setShowAsAction(actionEnum)")
    @Deprecated
    public static void setShowAsAction(MenuItem menuItem, int i2) {
        menuItem.setShowAsAction(i2);
    }
}
