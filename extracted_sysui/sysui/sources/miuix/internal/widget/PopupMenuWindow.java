package miuix.internal.widget;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import miuix.popupwidget.widget.PopupWindow;

/* JADX INFO: loaded from: classes3.dex */
public class PopupMenuWindow extends PopupWindow {
    private PopupMenuAdapter mAdapter;
    private View mLastAnchor;

    public PopupMenuWindow(Context context) {
        super(context);
        PopupMenuAdapter popupMenuAdapter = new PopupMenuAdapter(context);
        this.mAdapter = popupMenuAdapter;
        setAdapter(popupMenuAdapter);
        setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: miuix.internal.widget.l
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
                this.f6113a.lambda$new$1(adapterView, view, i2, j2);
            }
        });
        setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: miuix.internal.widget.m
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                this.f6114a.onDismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(SubMenu subMenu) {
        setOnDismissListener(null);
        update(subMenu);
        showAsDropDown(this.mLastAnchor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(AdapterView adapterView, View view, int i2, long j2) {
        MenuItem item = this.mAdapter.getItem(i2);
        if (item.hasSubMenu()) {
            final SubMenu subMenu = item.getSubMenu();
            setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: miuix.internal.widget.k
                @Override // android.widget.PopupWindow.OnDismissListener
                public final void onDismiss() {
                    this.f6111a.lambda$new$0(subMenu);
                }
            });
        } else {
            onMenuItemClick(item);
        }
        dismiss();
    }

    public void onDismiss() {
    }

    public void onMenuItemClick(MenuItem menuItem) {
    }

    @Override // miuix.popupwidget.widget.PopupWindow, miuix.appcompat.internal.view.menu.ImmersionMenuPopupWindow
    @Deprecated
    public void show(View view, ViewGroup viewGroup) {
        showAsDropDown(view);
    }

    @Override // miuix.popupwidget.widget.PopupWindow, android.widget.PopupWindow
    public void showAsDropDown(@NonNull View view) {
        this.mLastAnchor = view;
        if (prepareShow(view)) {
            super.showAsDropDown(view);
        }
    }

    @Override // miuix.popupwidget.widget.PopupWindow, android.widget.PopupWindow
    public void showAtLocation(View view, int i2, int i3, int i4) {
        if (prepareShow(view)) {
            super.showAtLocation(view.getRootView(), i2, i3, i4);
        }
    }

    public void update(Menu menu) {
        this.mAdapter.update(menu);
    }
}
