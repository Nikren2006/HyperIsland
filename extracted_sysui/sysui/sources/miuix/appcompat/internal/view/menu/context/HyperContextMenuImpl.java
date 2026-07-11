package miuix.appcompat.internal.view.menu.context;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupWindow;
import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.SupportMenuInflater;
import java.util.Map;
import miuix.appcompat.R;
import miuix.appcompat.internal.view.menu.MenuBuilder;
import miuix.appcompat.view.menu.HyperMenuInflater;
import miuix.appcompat.widget.HyperPopupWindow;
import miuix.popupwidget.internal.strategy.IPopupWindowStrategy;

/* JADX INFO: loaded from: classes3.dex */
public class HyperContextMenuImpl {
    private HyperContextMenuAdapter mAdapter;
    private final View mAnchor;
    private final Context mContext;
    private HyperPopupWindow mHyperPopupWindow;
    private final MenuBuilder mMenu;
    private Map<Integer, Boolean> mPrimaryPreCheckedMap;
    private Map<Integer, Boolean[]> mSecondaryPreCheckedMap;

    public HyperContextMenuImpl(@NonNull Context context, @NonNull View view) {
        this(context, view, 0);
    }

    private MenuInflater getDefaultMenuInflater() {
        return new SupportMenuInflater(this.mContext);
    }

    private MenuInflater getHyperMenuInflater() {
        return new HyperMenuInflater(this.mContext);
    }

    public void dismiss() {
        this.mHyperPopupWindow.dismiss();
    }

    public Menu getMenu() {
        return this.mMenu;
    }

    public void inflate(@MenuRes int i2, boolean z2) {
        getHyperMenuInflater().inflate(i2, this.mMenu);
        HyperContextMenuAdapter hyperContextMenuAdapter = new HyperContextMenuAdapter(this.mContext);
        this.mAdapter = hyperContextMenuAdapter;
        hyperContextMenuAdapter.preCheckPrimaryItem(this.mPrimaryPreCheckedMap);
        this.mAdapter.preCheckSecondaryItem(this.mSecondaryPreCheckedMap);
        this.mAdapter.update(this.mMenu, z2);
    }

    public boolean isShowing() {
        return this.mHyperPopupWindow.isShowing();
    }

    public boolean notifyDataChanged() {
        MenuBuilder menuBuilder;
        HyperContextMenuAdapter hyperContextMenuAdapter = this.mAdapter;
        if (hyperContextMenuAdapter == null || (menuBuilder = this.mMenu) == null) {
            return false;
        }
        hyperContextMenuAdapter.update(menuBuilder);
        return true;
    }

    public void preCheckPrimaryItem(Map<Integer, Boolean> map) {
        this.mPrimaryPreCheckedMap = map;
    }

    public void preCheckSecondaryItem(Map<Integer, Boolean[]> map) {
        this.mSecondaryPreCheckedMap = map;
    }

    public void savePrimaryCheckedMap(Map<Integer, Boolean> map) {
        HyperContextMenuAdapter hyperContextMenuAdapter = this.mAdapter;
        if (hyperContextMenuAdapter == null) {
            return;
        }
        hyperContextMenuAdapter.copyPrimaryCheckedData(map);
    }

    public void saveSecondaryCheckedMap(Map<Integer, Boolean[]> map) {
        HyperContextMenuAdapter hyperContextMenuAdapter = this.mAdapter;
        if (hyperContextMenuAdapter == null) {
            return;
        }
        hyperContextMenuAdapter.copySecondaryCheckedData(map);
    }

    public void setAutoDismiss(boolean z2) {
        this.mHyperPopupWindow.setAutoDismiss(z2);
    }

    public void setClippingEnabled(boolean z2) {
        this.mHyperPopupWindow.setClippingEnabled(z2);
    }

    public void setOnDismissListener(@Nullable PopupWindow.OnDismissListener onDismissListener) {
        this.mHyperPopupWindow.setOnDismissListener(onDismissListener);
    }

    public void setOnMenuItemClickListener(@Nullable HyperPopupWindow.OnMenuItemClickListener onMenuItemClickListener) {
        this.mHyperPopupWindow.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    public void setPopupWindowStrategy(IPopupWindowStrategy iPopupWindowStrategy) {
        this.mHyperPopupWindow.setPopupWindowStrategy(iPopupWindowStrategy);
    }

    public void setWindowAnimationEnabled(boolean z2) {
        this.mHyperPopupWindow.setWindowAnimationEnabled(z2);
    }

    public void show() {
        this.mHyperPopupWindow.setSecondaryMenuEnabled(this.mAdapter.hasSubMenu());
        this.mHyperPopupWindow.setAdapter(this.mAdapter);
        this.mHyperPopupWindow.show(this.mAnchor);
    }

    public void update() {
        this.mHyperPopupWindow.update();
    }

    public HyperContextMenuImpl(@NonNull Context context, @NonNull View view, int i2) {
        if (i2 == 0) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, R.styleable.miuiPopupMenu, R.attr.miuiPopupMenuStyle, 0);
            try {
                int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.miuiPopupMenu_miuiPopupTheme, 0);
                typedArrayObtainStyledAttributes.recycle();
                i2 = resourceId;
            } catch (Throwable th) {
                typedArrayObtainStyledAttributes.recycle();
                throw th;
            }
        }
        if (i2 != 0) {
            this.mContext = new ContextThemeWrapper(context, i2);
        } else {
            this.mContext = context;
        }
        this.mAnchor = view;
        this.mMenu = new MenuBuilder(this.mContext);
        HyperPopupWindow hyperPopupWindow = new HyperPopupWindow(context);
        this.mHyperPopupWindow = hyperPopupWindow;
        hyperPopupWindow.setWindowAnimationEnabled(false);
        setClippingEnabled(true);
        setAutoDismiss(false);
        setWindowAnimationEnabled(true);
    }

    public void inflate(@MenuRes int i2) {
        getDefaultMenuInflater().inflate(i2, this.mMenu);
        HyperContextMenuAdapter hyperContextMenuAdapter = new HyperContextMenuAdapter(this.mContext);
        this.mAdapter = hyperContextMenuAdapter;
        hyperContextMenuAdapter.preCheckPrimaryItem(this.mPrimaryPreCheckedMap);
        this.mAdapter.preCheckSecondaryItem(this.mSecondaryPreCheckedMap);
        this.mAdapter.update(this.mMenu);
    }

    public void inflate(MenuBuilder menuBuilder) {
        HyperContextMenuAdapter hyperContextMenuAdapter = new HyperContextMenuAdapter(this.mContext);
        this.mAdapter = hyperContextMenuAdapter;
        hyperContextMenuAdapter.preCheckPrimaryItem(this.mPrimaryPreCheckedMap);
        this.mAdapter.preCheckSecondaryItem(this.mSecondaryPreCheckedMap);
        this.mAdapter.update(menuBuilder);
    }
}
