package miuix.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import java.util.List;
import java.util.Map;
import miuix.appcompat.R;
import miuix.appcompat.view.menu.HyperMenuContract;

/* JADX INFO: loaded from: classes3.dex */
public class HyperSecondaryAdapter extends HyperBaseAdapter {
    private View mHeaderView;
    private Map<Integer, Boolean[]> mSecondaryItemCheckedMap;

    public HyperSecondaryAdapter(@NonNull LayoutInflater layoutInflater, @NonNull List<HyperMenuContract.HyperMenuItem> list, @NonNull Map<Integer, Boolean[]> map) {
        super(layoutInflater, list);
        this.mSecondaryItemCheckedMap = map;
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    @Override // miuix.appcompat.view.menu.HyperBaseAdapter, android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i2, view, viewGroup);
        if (i2 == 0) {
            view2.setId(R.id.tag_secondary_popup_menu_item_head);
            this.mHeaderView = view2;
        }
        return view2;
    }

    public void resumeSecondaryItemClickStatus(int i2) {
        List<HyperMenuContract.HyperMenuItem> list = this.mMenuItemList;
        if (list == null || list.size() <= 2) {
            return;
        }
        int itemId = this.mMenuItemList.get(0).getItemId();
        Boolean[] boolArr = this.mSecondaryItemCheckedMap.get(Integer.valueOf(itemId));
        if (boolArr == null) {
            boolArr = new Boolean[this.mMenuItemList.size() - 2];
        }
        for (int i3 = 0; i3 < this.mMenuItemList.size(); i3++) {
            HyperMenuContract.HyperMenuItem hyperMenuItem = this.mMenuItemList.get(i3);
            HyperMenuContract.HyperMenuTextItem hyperMenuTextItem = hyperMenuItem instanceof HyperMenuContract.HyperMenuTextItem ? (HyperMenuContract.HyperMenuTextItem) hyperMenuItem : null;
            miuix.appcompat.internal.view.menu.MenuItemImpl menuItem = hyperMenuTextItem != null ? hyperMenuTextItem.getMenuItem() : null;
            if (menuItem != null && menuItem.isCheckable() && !hyperMenuTextItem.isHeaderItem && i3 >= 2) {
                int i4 = i3 - 2;
                Boolean boolValueOf = Boolean.valueOf(hyperMenuTextItem.getItemId() == i2);
                boolArr[i4] = boolValueOf;
                hyperMenuTextItem.checkStatus = Boolean.TRUE.equals(boolValueOf) ? HyperMenuContract.CheckableType.CHECKED : HyperMenuContract.CheckableType.NOT_CHECKED;
                menuItem.setChecked(hyperMenuTextItem.isChecked());
            }
        }
        this.mSecondaryItemCheckedMap.put(Integer.valueOf(itemId), boolArr);
        notifyDataSetChanged();
    }
}
