package miuix.appcompat.internal.view.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import miuix.appcompat.internal.view.menu.MenuBuilder;

/* JADX INFO: loaded from: classes3.dex */
public final class ExpandedMenuView extends ListView implements MenuBuilder.ItemInvoker, MenuView, AdapterView.OnItemClickListener {
    private int mAnimations;
    private MenuBuilder mMenu;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnItemClickListener(this);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView
    public boolean filterLeftoverView(int i2) {
        return false;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView
    public int getWindowAnimations() {
        return this.mAnimations;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView
    public boolean hasBackgroundView() {
        return false;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuView
    public void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuBuilder.ItemInvoker
    public boolean invokeItem(MenuItemImpl menuItemImpl, int i2) {
        return this.mMenu.performItemAction(menuItemImpl, i2);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        invokeItem((MenuItemImpl) getAdapter().getItem(i2), 0);
    }
}
