package miuix.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import miuix.appcompat.view.menu.HyperMenuContract;

/* JADX INFO: loaded from: classes3.dex */
public class HyperMenuAdapter extends HyperBaseAdapter {
    private static final int DEFAULT_FOREIGN_KEY = -1;
    private Map<Integer, ArrayList<HyperMenuContract.HyperMenuItem>> mGroupedMap;
    private boolean mOverflowOnly;
    private boolean mPrimaryCheckedMapFirstModified;
    private Map<Integer, Boolean> mPrimaryItemCheckedMap;
    private List<HyperMenuContract.HyperMenuItem> mPrimaryMenuItems;
    private boolean mSecondaryCheckedMapFirstModified;
    private Map<Integer, Boolean[]> mSecondaryItemCheckedMap;
    private Map<Integer, BaseAdapter> mSecondaryMenuMap;
    private Map<Integer, Boolean> mUserPreCheckedPrimaryMap;
    private Map<Integer, Boolean[]> mUserPreCheckedSecondaryMap;

    public HyperMenuAdapter(Context context) {
        this(context, null, false);
    }

    private void assembleGroupData(Map<Integer, ArrayList<HyperMenuContract.HyperMenuItem>> map, ArrayList<miuix.appcompat.internal.view.menu.MenuItemImpl> arrayList) {
        int intExtra;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            miuix.appcompat.internal.view.menu.MenuItemImpl menuItemImpl = arrayList.get(i2);
            int groupId = menuItemImpl.getGroupId();
            Intent intent = menuItemImpl.getIntent();
            if (intent != null) {
                groupId = intent.getIntExtra(HyperMenuContract.HYPER_MENU_GROUP_ID, menuItemImpl.getGroupId());
                intExtra = intent.getIntExtra(HyperMenuContract.HYPER_MENU_ITEM_FOREIGN_KEY, -1);
            } else {
                intExtra = -1;
            }
            ArrayList<HyperMenuContract.HyperMenuItem> arrayList2 = map.get(Integer.valueOf(groupId));
            if (arrayList2 == null) {
                arrayList2 = new ArrayList<>();
            }
            HyperMenuContract.HyperMenuTextItem hyperMenuTextItem = new HyperMenuContract.HyperMenuTextItem(menuItemImpl);
            if (intExtra != -1) {
                hyperMenuTextItem.isExpandable = true;
                hyperMenuTextItem.itemForeignKey = intExtra;
            } else {
                hyperMenuTextItem.isExpandable = false;
                hyperMenuTextItem.itemForeignKey = -1;
            }
            arrayList2.add(hyperMenuTextItem);
            map.put(Integer.valueOf(groupId), arrayList2);
        }
    }

    private void assembleSecondaryMenu(Map<Integer, ArrayList<HyperMenuContract.HyperMenuItem>> map, ArrayList<miuix.appcompat.internal.view.menu.MenuItemImpl> arrayList) {
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            miuix.appcompat.internal.view.menu.MenuItemImpl menuItemImpl = arrayList.get(i2);
            Intent intent = menuItemImpl.getIntent();
            int intExtra = intent != null ? intent.getIntExtra(HyperMenuContract.HYPER_MENU_GROUP_FOREIGN_KEY, -1) : -1;
            if (intExtra != -1) {
                ArrayList<HyperMenuContract.HyperMenuItem> arrayList2 = map.get(Integer.valueOf(menuItemImpl.getGroupId()));
                HyperMenuContract.HyperMenuItem hyperMenuItemFindPrimaryItemByForeignKey = findPrimaryItemByForeignKey(this.mPrimaryMenuItems, intExtra);
                if (arrayList2 != null && hyperMenuItemFindPrimaryItemByForeignKey != null && hyperMenuItemFindPrimaryItemByForeignKey.getMenuItem() != null) {
                    ArrayList arrayList3 = new ArrayList(arrayList2);
                    handleDefaultCheckedStatus(arrayList3, false, hyperMenuItemFindPrimaryItemByForeignKey.getMenuItem().getItemId());
                    HyperMenuContract.HyperMenuTextItem hyperMenuTextItem = new HyperMenuContract.HyperMenuTextItem(hyperMenuItemFindPrimaryItemByForeignKey.getMenuItem());
                    hyperMenuTextItem.isHeaderItem = true;
                    arrayList3.add(0, hyperMenuTextItem);
                    arrayList3.add(1, new HyperMenuContract.HyperMenuDivider());
                    this.mSecondaryMenuMap.put(Integer.valueOf(hyperMenuItemFindPrimaryItemByForeignKey.getItemId()), new HyperSecondaryAdapter(this.mInflater, arrayList3, this.mSecondaryItemCheckedMap));
                }
            }
        }
    }

    private void buildDefaultSecondaryMenuData(Map<Integer, ArrayList<HyperMenuContract.HyperMenuItem>> map, ArrayList<miuix.appcompat.internal.view.menu.MenuItemImpl> arrayList, ArrayList<Integer> arrayList2) {
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            miuix.appcompat.internal.view.menu.MenuItemImpl menuItemImpl = arrayList.get(i2);
            int groupId = menuItemImpl.getGroupId();
            if (!arrayList2.contains(Integer.valueOf(groupId))) {
                arrayList2.add(Integer.valueOf(groupId));
            }
            ArrayList<HyperMenuContract.HyperMenuItem> arrayList3 = map.get(Integer.valueOf(groupId));
            if (arrayList3 == null) {
                arrayList3 = new ArrayList<>();
            }
            boolean zHasSubMenu = menuItemImpl.hasSubMenu();
            HyperMenuContract.HyperMenuTextItem hyperMenuTextItem = new HyperMenuContract.HyperMenuTextItem(menuItemImpl);
            hyperMenuTextItem.isExpandable = zHasSubMenu;
            arrayList3.add(hyperMenuTextItem);
            map.put(Integer.valueOf(groupId), arrayList3);
            ArrayList<HyperMenuContract.HyperMenuItem> arrayListBuildDefaultSubMenuData = (zHasSubMenu && (menuItemImpl.getSubMenu() instanceof miuix.appcompat.internal.view.menu.SubMenuBuilder)) ? buildDefaultSubMenuData((miuix.appcompat.internal.view.menu.SubMenuBuilder) menuItemImpl.getSubMenu(), menuItemImpl.getItemId()) : null;
            if (arrayListBuildDefaultSubMenuData != null) {
                HyperMenuContract.HyperMenuTextItem hyperMenuTextItem2 = new HyperMenuContract.HyperMenuTextItem(menuItemImpl);
                hyperMenuTextItem2.isHeaderItem = true;
                arrayListBuildDefaultSubMenuData.add(0, hyperMenuTextItem2);
                arrayListBuildDefaultSubMenuData.add(1, new HyperMenuContract.HyperMenuDivider());
                this.mSecondaryMenuMap.put(Integer.valueOf(menuItemImpl.getItemId()), new HyperSecondaryAdapter(this.mInflater, arrayListBuildDefaultSubMenuData, this.mSecondaryItemCheckedMap));
            }
        }
    }

    private ArrayList<HyperMenuContract.HyperMenuItem> buildDefaultSubMenuData(miuix.appcompat.internal.view.menu.SubMenuBuilder subMenuBuilder, int i2) {
        boolean z2;
        if (subMenuBuilder != null && i2 != -1) {
            ArrayList<HyperMenuContract.HyperMenuItem> arrayList = new ArrayList<>();
            ArrayList<miuix.appcompat.internal.view.menu.MenuItemImpl> visibleItems = subMenuBuilder.getVisibleItems();
            if (visibleItems != null && visibleItems.size() != 0) {
                Boolean[] boolArr = this.mSecondaryItemCheckedMap.get(Integer.valueOf(i2));
                if (boolArr == null) {
                    boolArr = new Boolean[visibleItems.size()];
                    z2 = true;
                } else {
                    z2 = false;
                }
                for (int i3 = 0; i3 < visibleItems.size(); i3++) {
                    miuix.appcompat.internal.view.menu.MenuItemImpl menuItemImpl = visibleItems.get(i3);
                    if (z2) {
                        boolArr[i3] = Boolean.valueOf(menuItemImpl.isChecked());
                    }
                    HyperMenuContract.HyperMenuTextItem hyperMenuTextItem = new HyperMenuContract.HyperMenuTextItem(menuItemImpl);
                    if (menuItemImpl != null && menuItemImpl.isCheckable()) {
                        hyperMenuTextItem.checkStatus = Boolean.TRUE.equals(boolArr[i3]) ? HyperMenuContract.CheckableType.CHECKED : HyperMenuContract.CheckableType.NOT_CHECKED;
                        menuItemImpl.setChecked(hyperMenuTextItem.isChecked());
                    }
                    arrayList.add(hyperMenuTextItem);
                }
                this.mSecondaryItemCheckedMap.put(Integer.valueOf(i2), boolArr);
                return arrayList;
            }
        }
        return null;
    }

    private void buildGroupedMenuItems(miuix.appcompat.internal.view.menu.MenuBuilder menuBuilder) {
        Map<Integer, BaseAdapter> map;
        if (menuBuilder == null || (map = this.mSecondaryMenuMap) == null || this.mPrimaryMenuItems == null || this.mGroupedMap == null) {
            return;
        }
        map.clear();
        this.mPrimaryMenuItems.clear();
        this.mGroupedMap.clear();
        ArrayList<miuix.appcompat.internal.view.menu.MenuItemImpl> nonActionItems = this.mOverflowOnly ? menuBuilder.getNonActionItems() : menuBuilder.getVisibleItems();
        if (nonActionItems != null) {
            assembleGroupData(this.mGroupedMap, nonActionItems);
        }
        selectPrimaryMenu(this.mGroupedMap);
        if (nonActionItems != null) {
            assembleSecondaryMenu(this.mGroupedMap, nonActionItems);
        }
    }

    private void buildMenuItems(miuix.appcompat.internal.view.menu.MenuBuilder menuBuilder) {
        List<HyperMenuContract.HyperMenuItem> list;
        if (menuBuilder == null || this.mSecondaryMenuMap == null || (list = this.mPrimaryMenuItems) == null || this.mGroupedMap == null) {
            return;
        }
        list.clear();
        this.mSecondaryMenuMap.clear();
        this.mGroupedMap.clear();
        ArrayList<miuix.appcompat.internal.view.menu.MenuItemImpl> nonActionItems = this.mOverflowOnly ? menuBuilder.getNonActionItems() : menuBuilder.getVisibleItems();
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (nonActionItems != null) {
            buildDefaultSecondaryMenuData(this.mGroupedMap, nonActionItems, arrayList);
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            ArrayList<HyperMenuContract.HyperMenuItem> arrayList2 = this.mGroupedMap.get(arrayList.get(i2));
            List<HyperMenuContract.HyperMenuItem> list2 = this.mPrimaryMenuItems;
            list2.addAll(list2.size(), arrayList2);
            this.mPrimaryMenuItems.add(new HyperMenuContract.HyperMenuDivider());
        }
        List<HyperMenuContract.HyperMenuItem> list3 = this.mPrimaryMenuItems;
        list3.remove(list3.size() - 1);
        handleDefaultCheckedStatus(this.mPrimaryMenuItems, true, -1);
    }

    private HyperMenuContract.HyperMenuItem findPrimaryItemByForeignKey(List<HyperMenuContract.HyperMenuItem> list, int i2) {
        int i3 = 0;
        while (true) {
            if (i3 >= list.size()) {
                return null;
            }
            HyperMenuContract.HyperMenuItem hyperMenuItem = list.get(i3);
            Intent intent = hyperMenuItem.getMenuItem() != null ? hyperMenuItem.getMenuItem().getIntent() : null;
            int intExtra = intent != null ? intent.getIntExtra(HyperMenuContract.HYPER_MENU_ITEM_FOREIGN_KEY, -1) : -1;
            if (intExtra != -1 && intExtra == i2) {
                return hyperMenuItem;
            }
            i3++;
        }
    }

    private void handleDefaultCheckedStatus(List<HyperMenuContract.HyperMenuItem> list, boolean z2, int i2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        boolean z3 = true;
        boolean z4 = (z2 || i2 == -1) ? false : true;
        Boolean[] boolArr = z4 ? this.mSecondaryItemCheckedMap.get(Integer.valueOf(i2)) : null;
        if (z4 && boolArr == null) {
            boolArr = new Boolean[list.size()];
        } else {
            z3 = false;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            HyperMenuContract.HyperMenuItem hyperMenuItem = list.get(i3);
            miuix.appcompat.internal.view.menu.MenuItemImpl menuItem = hyperMenuItem instanceof HyperMenuContract.HyperMenuTextItem ? hyperMenuItem.getMenuItem() : null;
            if (menuItem == null || !menuItem.isCheckable()) {
                if (z2) {
                    this.mPrimaryItemCheckedMap.put(Integer.valueOf(i3), Boolean.FALSE);
                }
            } else if (z2) {
                Boolean bool = this.mPrimaryItemCheckedMap.get(Integer.valueOf(i3));
                this.mPrimaryItemCheckedMap.put(Integer.valueOf(i3), Boolean.valueOf(bool != null ? bool.booleanValue() : menuItem.isChecked()));
                ((HyperMenuContract.HyperMenuTextItem) hyperMenuItem).checkStatus = Boolean.TRUE.equals(this.mPrimaryItemCheckedMap.get(Integer.valueOf(i3))) ? HyperMenuContract.CheckableType.CHECKED : HyperMenuContract.CheckableType.NOT_CHECKED;
            } else if (z4) {
                if (z3) {
                    boolArr[i3] = Boolean.valueOf(menuItem.isChecked());
                }
                ((HyperMenuContract.HyperMenuTextItem) hyperMenuItem).checkStatus = Boolean.TRUE.equals(boolArr[i3]) ? HyperMenuContract.CheckableType.CHECKED : HyperMenuContract.CheckableType.NOT_CHECKED;
            }
        }
        if (z4) {
            this.mSecondaryItemCheckedMap.put(Integer.valueOf(i2), boolArr);
        }
    }

    private void selectPrimaryMenu(Map<Integer, ArrayList<HyperMenuContract.HyperMenuItem>> map) {
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            ArrayList<HyperMenuContract.HyperMenuItem> arrayList = map.get(it.next());
            if (arrayList != null && arrayList.size() > 0 && arrayList.get(0).getMenuItem().getGroupId() == 0) {
                List<HyperMenuContract.HyperMenuItem> list = this.mPrimaryMenuItems;
                list.addAll(list.size(), arrayList);
                this.mPrimaryMenuItems.add(new HyperMenuContract.HyperMenuDivider());
            }
        }
        List<HyperMenuContract.HyperMenuItem> list2 = this.mPrimaryMenuItems;
        list2.remove(list2.size() - 1);
        handleDefaultCheckedStatus(this.mPrimaryMenuItems, true, -1);
    }

    public void copyPrimaryCheckedData(Map<Integer, Boolean> map) {
        Map<Integer, Boolean> map2;
        if (map == null || (map2 = this.mPrimaryItemCheckedMap) == null) {
            return;
        }
        for (Integer num : map2.keySet()) {
            num.intValue();
            Boolean bool = this.mPrimaryItemCheckedMap.get(num);
            if (bool != null) {
                map.put(num, bool);
            }
        }
    }

    public void copySecondaryCheckedData(Map<Integer, Boolean[]> map) {
        Map<Integer, Boolean[]> map2;
        if (map == null || (map2 = this.mSecondaryItemCheckedMap) == null) {
            return;
        }
        for (Integer num : map2.keySet()) {
            num.intValue();
            Boolean[] boolArr = this.mSecondaryItemCheckedMap.get(num);
            Boolean[] boolArr2 = new Boolean[boolArr.length];
            System.arraycopy(boolArr, 0, boolArr2, 0, boolArr.length);
            map.put(num, boolArr2);
        }
    }

    @Override // miuix.appcompat.view.menu.HyperBaseAdapter
    public HyperMenuContract.HyperMenuItem getHyperMenuItem(int i2) {
        return this.mPrimaryMenuItems.get(i2);
    }

    @Override // miuix.appcompat.view.menu.HyperBaseAdapter, android.widget.Adapter
    public long getItemId(int i2) {
        return this.mPrimaryMenuItems.get(i2).getItemId();
    }

    public Map<Integer, Boolean> getPrimaryCheckedMap() {
        return this.mPrimaryItemCheckedMap;
    }

    public BaseAdapter getSecondaryAdapterByItemId(long j2) {
        return this.mSecondaryMenuMap.get(Integer.valueOf((int) j2));
    }

    public Map<Integer, Boolean[]> getSecondaryCheckedMap() {
        return this.mSecondaryItemCheckedMap;
    }

    public boolean hasSubMenu(long j2) {
        return this.mSecondaryMenuMap.get(Integer.valueOf((int) j2)) != null;
    }

    @Override // miuix.appcompat.view.menu.HyperBaseAdapter
    public void preCheckPrimaryItem(Map<Integer, Boolean> map) {
        if (map == null) {
            return;
        }
        this.mUserPreCheckedPrimaryMap = map;
        this.mPrimaryItemCheckedMap.clear();
        for (Integer num : map.keySet()) {
            num.intValue();
            this.mPrimaryItemCheckedMap.put(num, map.get(num));
        }
        this.mPrimaryCheckedMapFirstModified = true;
    }

    @Override // miuix.appcompat.view.menu.HyperBaseAdapter
    public void preCheckSecondaryItem(Map<Integer, Boolean[]> map) {
        if (map == null) {
            return;
        }
        this.mUserPreCheckedSecondaryMap = map;
        this.mSecondaryItemCheckedMap.clear();
        for (Integer num : map.keySet()) {
            num.intValue();
            this.mSecondaryItemCheckedMap.put(num, map.get(num));
        }
        this.mSecondaryCheckedMapFirstModified = true;
    }

    public void resumePrimaryItemClickStatus(int i2, int i3) {
        HyperMenuContract.HyperMenuItem hyperMenuItem;
        miuix.appcompat.internal.view.menu.MenuItemImpl menuItem;
        miuix.appcompat.internal.view.menu.MenuItemImpl menuItem2;
        List<HyperMenuContract.HyperMenuItem> list = this.mPrimaryMenuItems;
        if (list == null || list.size() == 0) {
            return;
        }
        int i4 = 0;
        while (true) {
            if (i4 >= this.mPrimaryMenuItems.size()) {
                hyperMenuItem = null;
                break;
            }
            hyperMenuItem = this.mPrimaryMenuItems.get(i4);
            if (hyperMenuItem.getItemId() == i2) {
                break;
            } else {
                i4++;
            }
        }
        if (hyperMenuItem == null || (menuItem = hyperMenuItem.getMenuItem()) == null) {
            return;
        }
        Intent intent = menuItem.getIntent();
        ArrayList<HyperMenuContract.HyperMenuItem> arrayList = this.mGroupedMap.get(Integer.valueOf(intent != null ? intent.getIntExtra(HyperMenuContract.HYPER_MENU_GROUP_ID, menuItem.getGroupId()) : menuItem.getGroupId()));
        int i5 = -1;
        for (int i6 = 0; i6 < arrayList.size(); i6++) {
            HyperMenuContract.HyperMenuItem hyperMenuItem2 = arrayList.get(i6);
            HyperMenuContract.HyperMenuTextItem hyperMenuTextItem = hyperMenuItem2 instanceof HyperMenuContract.HyperMenuTextItem ? (HyperMenuContract.HyperMenuTextItem) hyperMenuItem2 : null;
            if (hyperMenuTextItem != null) {
                menuItem2 = hyperMenuTextItem.getMenuItem();
                if (hyperMenuTextItem.getItemId() == i2) {
                    i5 = i6;
                }
            } else {
                menuItem2 = null;
            }
            if (menuItem2 != null && menuItem2.isCheckable() && !hyperMenuTextItem.isExpandable) {
                hyperMenuTextItem.checkStatus = hyperMenuTextItem.getItemId() == i2 ? HyperMenuContract.CheckableType.CHECKED : HyperMenuContract.CheckableType.NOT_CHECKED;
                menuItem2.setChecked(hyperMenuTextItem.isChecked());
            }
        }
        if (i5 != -1) {
            int i7 = i3 - i5;
            int size = ((i3 + arrayList.size()) - i5) - 1;
            int i8 = i7;
            while (i8 >= i7 && i8 <= size) {
                this.mPrimaryItemCheckedMap.put(Integer.valueOf(i8), Boolean.valueOf(i8 == i7 + i5));
                i8++;
            }
        }
        notifyDataSetChanged();
    }

    public void update(miuix.appcompat.internal.view.menu.MenuBuilder menuBuilder) {
        update(menuBuilder, false);
    }

    public HyperMenuAdapter(Context context, miuix.appcompat.internal.view.menu.MenuBuilder menuBuilder, boolean z2) {
        this.mPrimaryMenuItems = new ArrayList();
        this.mSecondaryMenuMap = new HashMap();
        this.mGroupedMap = new HashMap();
        this.mPrimaryItemCheckedMap = new HashMap();
        this.mSecondaryItemCheckedMap = new HashMap();
        this.mPrimaryCheckedMapFirstModified = true;
        this.mSecondaryCheckedMapFirstModified = true;
        this.mInflater = LayoutInflater.from(context);
        this.mMenuItemList = this.mPrimaryMenuItems;
        this.mOverflowOnly = z2;
        if (menuBuilder != null) {
            buildGroupedMenuItems(menuBuilder);
        }
    }

    @Override // miuix.appcompat.view.menu.HyperBaseAdapter, android.widget.Adapter
    public MenuItem getItem(int i2) {
        return this.mPrimaryMenuItems.get(i2).getMenuItem();
    }

    public boolean hasSubMenu() {
        return !this.mSecondaryMenuMap.isEmpty();
    }

    public void update(miuix.appcompat.internal.view.menu.MenuBuilder menuBuilder, boolean z2) {
        Map<Integer, Boolean> map = this.mUserPreCheckedPrimaryMap;
        if (map != null && this.mPrimaryItemCheckedMap != null && !this.mPrimaryCheckedMapFirstModified) {
            preCheckPrimaryItem(map);
        }
        Map<Integer, Boolean[]> map2 = this.mUserPreCheckedSecondaryMap;
        if (map2 != null && this.mSecondaryItemCheckedMap != null && !this.mSecondaryCheckedMapFirstModified) {
            preCheckSecondaryItem(map2);
        }
        if (z2) {
            buildGroupedMenuItems(menuBuilder);
        } else {
            buildMenuItems(menuBuilder);
        }
        this.mPrimaryCheckedMapFirstModified = false;
        this.mSecondaryCheckedMapFirstModified = false;
        notifyDataSetChanged();
    }
}
