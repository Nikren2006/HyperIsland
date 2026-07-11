package miuix.appcompat.internal.view.menu;

import android.content.Context;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import java.util.Map;
import miuix.appcompat.internal.view.menu.MenuPresenter;
import miuix.appcompat.view.menu.HyperMenuAdapter;
import miuix.appcompat.widget.HyperPopupWindow;

/* JADX INFO: loaded from: classes3.dex */
public class HyperPopupHelper implements HyperPopupWindow.OnMenuItemClickListener, View.OnKeyListener, PopupWindow.OnDismissListener, MenuPresenter {
    private View mAnchorView;
    private Context mContext;
    private View mFenceDecor;
    boolean mForceShowIcon;
    private HyperMenuAdapter mHyperMenuAdapter;
    private HyperPopupWindow mHyperPopup;
    private MenuBuilder mMenu;
    private boolean mOverflowOnly;
    private int mPopupAnimationGravity;
    private int mPopupHorizontalOffset;
    private int mPopupMaxHeight;
    private int mPopupVerticalOffset;
    private MenuPresenter.Callback mPresenterCallback;
    private Map<Integer, Boolean> mPrimaryCheckedMap;
    private Map<Integer, Boolean[]> mSecondaryCheckedMap;

    public HyperPopupHelper(Context context, MenuBuilder menuBuilder) {
        this(context, menuBuilder, null, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: saveData, reason: merged with bridge method [inline-methods] */
    public void lambda$dismiss$0() {
        HyperMenuAdapter hyperMenuAdapter = this.mHyperMenuAdapter;
        if (hyperMenuAdapter != null) {
            hyperMenuAdapter.copyPrimaryCheckedData(this.mPrimaryCheckedMap);
            this.mHyperMenuAdapter.copySecondaryCheckedData(this.mSecondaryCheckedMap);
        }
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public void dismiss(boolean z2) {
        if (isShowing()) {
            this.mHyperPopup.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: miuix.appcompat.internal.view.menu.a
                @Override // android.widget.PopupWindow.OnDismissListener
                public final void onDismiss() {
                    this.f6083a.lambda$dismiss$0();
                }
            });
            this.mHyperPopup.dismiss();
        }
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public int getId() {
        return 0;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup) {
        throw new UnsupportedOperationException("MenuPopupHelpers manage their own views");
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void initForMenu(Context context, MenuBuilder menuBuilder) {
    }

    public boolean isShowing() {
        HyperPopupWindow hyperPopupWindow = this.mHyperPopup;
        return hyperPopupWindow != null && hyperPopupWindow.isShowing();
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z2) {
        if (menuBuilder != this.mMenu) {
            return;
        }
        dismiss(true);
        MenuPresenter.Callback callback = this.mPresenterCallback;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z2);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        lambda$dismiss$0();
        this.mHyperPopup = null;
        this.mMenu.close();
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        dismiss(false);
        return true;
    }

    @Override // miuix.appcompat.widget.HyperPopupWindow.OnMenuItemClickListener
    public void onMenuItemClick(MenuItem menuItem) {
        this.mMenu.performItemAction(menuItem, 0);
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        return null;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        return true;
    }

    public void restoreHyperMenuPrimaryCheckedData(Map<Integer, Boolean> map) {
        this.mPrimaryCheckedMap = map;
    }

    public void restoreHyperMenuSecondaryCheckedData(Map<Integer, Boolean[]> map) {
        this.mSecondaryCheckedMap = map;
    }

    public void setAnchorView(View view) {
        this.mAnchorView = view;
    }

    public void setAnimationGravity(int i2) {
        this.mPopupAnimationGravity = i2;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void setCallback(MenuPresenter.Callback callback) {
        this.mPresenterCallback = callback;
    }

    public void setFenceDecor(View view) {
        this.mFenceDecor = view;
    }

    public void setForceShowIcon(boolean z2) {
        this.mForceShowIcon = z2;
    }

    public void setPopupMaxHeight(int i2) {
        this.mPopupMaxHeight = i2;
    }

    public void setVerticalOffset(int i2) {
        this.mPopupVerticalOffset = i2;
    }

    public void show() {
        if (!tryShow()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean tryShow() {
        HyperPopupWindow hyperPopupWindow = new HyperPopupWindow(this.mContext, this.mFenceDecor);
        this.mHyperPopup = hyperPopupWindow;
        hyperPopupWindow.setDropDownGravity(8388693);
        this.mHyperPopup.setOnDismissListener(this);
        this.mHyperPopup.setOnMenuItemClickListener(this);
        this.mHyperPopup.setWindowAnimationEnabled(false);
        HyperMenuAdapter hyperMenuAdapter = new HyperMenuAdapter(this.mContext, null, this.mOverflowOnly);
        this.mHyperMenuAdapter = hyperMenuAdapter;
        hyperMenuAdapter.setOptionalIconsVisible(this.mMenu.getOptionalIconsVisible());
        Map<Integer, Boolean> map = this.mPrimaryCheckedMap;
        if (map != null) {
            this.mHyperMenuAdapter.preCheckPrimaryItem(map);
        }
        Map<Integer, Boolean[]> map2 = this.mSecondaryCheckedMap;
        if (map2 != null) {
            this.mHyperMenuAdapter.preCheckSecondaryItem(map2);
        }
        this.mHyperMenuAdapter.update(this.mMenu);
        this.mHyperPopup.setAdapter(this.mHyperMenuAdapter);
        this.mHyperPopup.setSecondaryMenuEnabled(this.mHyperMenuAdapter.hasSubMenu());
        this.mHyperPopup.setHorizontalOffset(this.mPopupHorizontalOffset);
        this.mHyperPopup.setVerticalOffset(this.mPopupVerticalOffset);
        int i2 = this.mPopupMaxHeight;
        if (i2 > 0) {
            this.mHyperPopup.setMaxAllowedHeight(i2);
        }
        this.mHyperPopup.show(this.mAnchorView);
        this.mHyperPopup.getContentView().setOnKeyListener(this);
        return true;
    }

    @Override // miuix.appcompat.internal.view.menu.MenuPresenter
    public void updateMenuView(boolean z2) {
        HyperMenuAdapter hyperMenuAdapter = this.mHyperMenuAdapter;
        if (hyperMenuAdapter != null) {
            hyperMenuAdapter.notifyDataSetChanged();
        }
    }

    public HyperPopupHelper(Context context, MenuBuilder menuBuilder, View view) {
        this(context, menuBuilder, view, false);
    }

    public HyperPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z2) {
        this(context, menuBuilder, view, null, z2);
    }

    public HyperPopupHelper(Context context, MenuBuilder menuBuilder, View view, View view2, boolean z2) {
        this.mPopupHorizontalOffset = 0;
        this.mPopupAnimationGravity = -1;
        this.mPopupMaxHeight = 0;
        this.mContext = context;
        this.mMenu = menuBuilder;
        this.mOverflowOnly = z2;
        this.mAnchorView = view;
        this.mFenceDecor = view2;
        menuBuilder.addMenuPresenter(this);
    }
}
