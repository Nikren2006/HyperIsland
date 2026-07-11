package miuix.appcompat.internal.view.menu.action;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import java.util.HashMap;
import java.util.Map;
import miuix.appcompat.R;
import miuix.appcompat.internal.app.widget.ActionBarOverlayLayout;
import miuix.appcompat.internal.view.menu.BaseMenuPresenter;
import miuix.appcompat.internal.view.menu.HyperPopupHelper;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import miuix.appcompat.internal.view.menu.action.ActionMenuPresenter;
import miuix.internal.util.AttributeResolver;

/* JADX INFO: loaded from: classes3.dex */
public class HyperActionMenuPresenter extends EndActionMenuPresenter {
    private Map<Integer, Boolean> mHyperMenuPrimaryCheckedMap;
    private Map<Integer, Boolean[]> mHyperMenuSecondaryCheckedMap;

    public class HyperPopupOverflowMenu extends HyperPopupHelper implements ActionMenuPresenter.OverflowMenu {
        public HyperPopupOverflowMenu(Context context, MenuBuilder menuBuilder, View view, View view2, boolean z2) {
            super(context, menuBuilder, view, view2, z2);
            TypedValue typedValueResolveTypedValue = AttributeResolver.resolveTypedValue(context, R.attr.overflowMenuMaxHeight);
            int dimensionPixelSize = (typedValueResolveTypedValue == null || typedValueResolveTypedValue.type != 5) ? 0 : typedValueResolveTypedValue.resourceId > 0 ? context.getResources().getDimensionPixelSize(typedValueResolveTypedValue.resourceId) : TypedValue.complexToDimensionPixelSize(typedValueResolveTypedValue.data, context.getResources().getDisplayMetrics());
            if (dimensionPixelSize > 0) {
                setPopupMaxHeight(dimensionPixelSize);
            }
            setCallback(HyperActionMenuPresenter.this.mPopupPresenterCallback);
            int overflowMenuAnimationGravity = HyperActionMenuPresenter.this.getOverflowMenuAnimationGravity(view);
            if (overflowMenuAnimationGravity != -1) {
                setAnimationGravity(overflowMenuAnimationGravity);
            }
        }

        @Override // miuix.appcompat.internal.view.menu.HyperPopupHelper, miuix.appcompat.internal.view.menu.action.ActionMenuPresenter.OverflowMenu
        public void dismiss(boolean z2) {
            super.dismiss(z2);
            View view = HyperActionMenuPresenter.this.mOverflowButton;
            if (view != null) {
                view.setSelected(false);
            }
        }

        @Override // miuix.appcompat.internal.view.menu.HyperPopupHelper, android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            super.onDismiss();
            ((BaseMenuPresenter) HyperActionMenuPresenter.this).mMenu.close();
            HyperActionMenuPresenter.this.mOverflowMenu = null;
        }

        @Override // miuix.appcompat.internal.view.menu.action.ActionMenuPresenter.OverflowMenu
        public void update(MenuBuilder menuBuilder) {
        }
    }

    public HyperActionMenuPresenter(Context context, ActionBarOverlayLayout actionBarOverlayLayout, int i2, int i3) {
        this(context, actionBarOverlayLayout, i2, i3, 0, 0);
    }

    public Map<Integer, Boolean> getHyperPrimaryCheckedData() {
        return this.mHyperMenuPrimaryCheckedMap;
    }

    public Map<Integer, Boolean[]> getHyperSecondaryCheckedData() {
        return this.mHyperMenuSecondaryCheckedMap;
    }

    @Override // miuix.appcompat.internal.view.menu.action.ActionMenuPresenter
    public ActionMenuPresenter.OverflowMenu getOverflowMenu() {
        if (!shouldShowPopupOverflow()) {
            return super.getOverflowMenu();
        }
        HyperPopupOverflowMenu hyperPopupOverflowMenu = new HyperPopupOverflowMenu(this.mContext, this.mMenu, this.mOverflowButton, this.mDecorView, true);
        hyperPopupOverflowMenu.restoreHyperMenuPrimaryCheckedData(this.mHyperMenuPrimaryCheckedMap);
        hyperPopupOverflowMenu.restoreHyperMenuSecondaryCheckedData(this.mHyperMenuSecondaryCheckedMap);
        return hyperPopupOverflowMenu;
    }

    public void restorePrimaryMenuCheckedData(Map<Integer, Boolean> map) {
        if (map == null) {
            return;
        }
        for (Integer num : map.keySet()) {
            num.intValue();
            Boolean bool = map.get(num);
            if (bool != null) {
                this.mHyperMenuPrimaryCheckedMap.put(num, bool);
            }
        }
    }

    public void restoreSecondaryMenuCheckedData(Map<Integer, Boolean[]> map) {
        if (map == null) {
            return;
        }
        for (Integer num : map.keySet()) {
            num.intValue();
            Boolean[] boolArr = map.get(num);
            Boolean[] boolArr2 = new Boolean[boolArr.length];
            System.arraycopy(boolArr, 0, boolArr2, 0, boolArr.length);
            this.mHyperMenuSecondaryCheckedMap.put(num, boolArr2);
        }
    }

    public HyperActionMenuPresenter(Context context, ActionBarOverlayLayout actionBarOverlayLayout, int i2, int i3, int i4, int i5) {
        super(context, actionBarOverlayLayout, i2, i3, i4, i5);
        this.mHyperMenuPrimaryCheckedMap = new HashMap();
        this.mHyperMenuSecondaryCheckedMap = new HashMap();
    }
}
