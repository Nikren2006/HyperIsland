package miui.systemui.devicecontrols.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.service.controls.Control;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.xiaomi.onetrack.util.aa;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.os.Build;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.devicecontrols.ControlsServiceInfo;
import miui.systemui.devicecontrols.CustomIconCache;
import miui.systemui.devicecontrols.DCEntryInfo;
import miui.systemui.devicecontrols.R;
import miui.systemui.devicecontrols.controller.ControlInfo;
import miui.systemui.devicecontrols.controller.ControlsController;
import miui.systemui.devicecontrols.controller.SeedResponse;
import miui.systemui.devicecontrols.controller.StructureInfo;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControls;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControlsScope;
import miui.systemui.devicecontrols.databinding.ControlsWithFavoritesHeaderBinding;
import miui.systemui.devicecontrols.databinding.FavoriteControlsBinding;
import miui.systemui.devicecontrols.eventtracking.DeviceControlsEventTracker;
import miui.systemui.devicecontrols.management.ControlAdapter;
import miui.systemui.devicecontrols.management.ControlsCustomizeAdapter;
import miui.systemui.devicecontrols.management.ControlsListingController;
import miui.systemui.devicecontrols.management.ControlsModel;
import miui.systemui.devicecontrols.management.EditControlsModelController;
import miui.systemui.devicecontrols.management.ElementWrapper;
import miui.systemui.devicecontrols.management.FavoritesModel;
import miui.systemui.devicecontrols.management.ViewHolderFactory;
import miui.systemui.devicecontrols.ui.widget.SpringBackLayout;
import miui.systemui.devicecontrols.util.ControlsUtils;
import miui.systemui.ui.GlobalActionsPopupMenu;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.concurrency.DelayableExecutor;
import miui.systemui.widget.RelativeLayout;
import miuix.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
@DeviceControlsScope
public final class MiuiControlsUiControllerImpl implements MiuiControlsUiController {
    public static final Companion Companion = new Companion(null);
    private static final ComponentName EMPTY_COMPONENT;
    private static final StructureInfo EMPTY_STRUCTURE;
    private static final long FADE_IN_MILLIS = 200;
    private static final String PREF_COMPONENT = "controls_component";
    private static final String PREF_MIHOME_STRUCTURE = "controls_mihome_structure";
    private static final String PREF_STRUCTURE = "controls_structure";
    public static final int SENSE_AUTO = -1;
    public static final int SENSE_EMPTY = 0;
    public static final int SENSE_FAVORITE_CONTROLS = 2;
    public static final int SENSE_LOADING = 1;
    private static final String TAG = "MiuiControlsUiController";
    private final ActivityStarter activityStarter;
    private List<StructureInfo> allStructures;
    private final MiuiControlsUiControllerImpl$appComparator$1 appComparator;
    private final DelayableExecutor bgExecutor;
    private final FavoriteControlsBinding binding;
    private final Collator collator;
    private final Context context;
    private final ControlActionCoordinator controlActionCoordinator;
    private final E0.a controlsController;
    private final E0.a controlsListingController;
    private boolean created;
    private CharSequence currentMiHome;
    private int currentSense;
    private final EditControlsModelController editControlsModelController;
    private Set<Consumer<DCEntryInfo>> entryInfoCallbacks;
    private final ControlAdapter favoritesAdapter;
    private final ControlsWithFavoritesHeaderBinding headerBinding;
    private boolean hidden;
    private final CustomIconCache iconCache;
    private boolean isShowForExpose;
    private final MiuiControlsUiControllerImpl$listingCallback$1 listingCallback;
    private boolean loadingStructure;
    private final Comparator<SelectionItem> localeComparator;
    private Runnable onDismiss;
    private ListPopupWindow popup;
    private boolean retainCache;
    private SelectionItem selectedItem;
    private StructureInfo selectedStructure;
    private volatile List<SelectionItem> selectionItems;
    private final SharedPreferences sharedPreferences;
    private final StatusBarStateController statusBarStateController;
    private final DelayableExecutor uiExecutor;
    private final ViewHolderFactory viewHolderFactory;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.ui.MiuiControlsUiControllerImpl$createDCView$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function0 {
        final /* synthetic */ Function0 $back;
        final /* synthetic */ Function1 $edit;
        final /* synthetic */ Function0 $hideCustomize;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Function0 function0, Function1 function1, Function0 function02) {
            super(0);
            this.$back = function0;
            this.$edit = function1;
            this.$hideCustomize = function02;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m126invoke();
            return H0.s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m126invoke() {
            MiuiControlsUiControllerImpl.this.createDCViewInternal(this.$back, this.$edit, this.$hideCustomize);
        }
    }

    static {
        ComponentName componentName = new ComponentName("", "");
        EMPTY_COMPONENT = componentName;
        EMPTY_STRUCTURE = new StructureInfo(componentName, "", new ArrayList(), false, 8, null);
    }

    /* JADX WARN: Type inference failed for: r2v14, types: [miui.systemui.devicecontrols.ui.MiuiControlsUiControllerImpl$appComparator$1] */
    public MiuiControlsUiControllerImpl(E0.a controlsController, @DeviceControls Context context, @Main DelayableExecutor uiExecutor, @Background DelayableExecutor bgExecutor, E0.a controlsListingController, @Main SharedPreferences sharedPreferences, ControlActionCoordinator controlActionCoordinator, ActivityStarter activityStarter, CustomIconCache iconCache, StatusBarStateController statusBarStateController, ViewHolderFactory viewHolderFactory, EditControlsModelController editControlsModelController) {
        kotlin.jvm.internal.n.g(controlsController, "controlsController");
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(uiExecutor, "uiExecutor");
        kotlin.jvm.internal.n.g(bgExecutor, "bgExecutor");
        kotlin.jvm.internal.n.g(controlsListingController, "controlsListingController");
        kotlin.jvm.internal.n.g(sharedPreferences, "sharedPreferences");
        kotlin.jvm.internal.n.g(controlActionCoordinator, "controlActionCoordinator");
        kotlin.jvm.internal.n.g(activityStarter, "activityStarter");
        kotlin.jvm.internal.n.g(iconCache, "iconCache");
        kotlin.jvm.internal.n.g(statusBarStateController, "statusBarStateController");
        kotlin.jvm.internal.n.g(viewHolderFactory, "viewHolderFactory");
        kotlin.jvm.internal.n.g(editControlsModelController, "editControlsModelController");
        this.controlsController = controlsController;
        this.context = context;
        this.uiExecutor = uiExecutor;
        this.bgExecutor = bgExecutor;
        this.controlsListingController = controlsListingController;
        this.sharedPreferences = sharedPreferences;
        this.controlActionCoordinator = controlActionCoordinator;
        this.activityStarter = activityStarter;
        this.iconCache = iconCache;
        this.statusBarStateController = statusBarStateController;
        this.viewHolderFactory = viewHolderFactory;
        this.editControlsModelController = editControlsModelController;
        this.selectedStructure = EMPTY_STRUCTURE;
        this.favoritesAdapter = new ControlAdapter(context, viewHolderFactory);
        FavoriteControlsBinding favoriteControlsBindingInflate = FavoriteControlsBinding.inflate(LayoutInflater.from(context));
        kotlin.jvm.internal.n.f(favoriteControlsBindingInflate, "inflate(...)");
        this.binding = favoriteControlsBindingInflate;
        ControlsWithFavoritesHeaderBinding controlsWithFavoritesHeader = favoriteControlsBindingInflate.controlsWithFavoritesHeader;
        kotlin.jvm.internal.n.f(controlsWithFavoritesHeader, "controlsWithFavoritesHeader");
        this.headerBinding = controlsWithFavoritesHeader;
        this.currentSense = -1;
        this.hidden = true;
        this.isShowForExpose = true;
        this.currentMiHome = "";
        final Collator collator = Collator.getInstance(context.getResources().getConfiguration().getLocales().get(0));
        this.collator = collator;
        kotlin.jvm.internal.n.f(collator, "collator");
        this.localeComparator = new Comparator() { // from class: miui.systemui.devicecontrols.ui.MiuiControlsUiControllerImpl$special$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t3) {
                return collator.compare(((SelectionItem) t2).getTitle(), ((SelectionItem) t3).getTitle());
            }
        };
        this.appComparator = new Comparator<ControlsServiceInfo>() { // from class: miui.systemui.devicecontrols.ui.MiuiControlsUiControllerImpl$appComparator$1
            @Override // java.util.Comparator
            public int compare(ControlsServiceInfo p02, ControlsServiceInfo p12) {
                kotlin.jvm.internal.n.g(p02, "p0");
                kotlin.jvm.internal.n.g(p12, "p1");
                if (Build.IS_INTERNATIONAL_BUILD || !kotlin.jvm.internal.n.c(p02.componentName.getPackageName(), "com.xiaomi.smarthome")) {
                    return kotlin.jvm.internal.n.c(p12.componentName.getPackageName(), "com.xiaomi.smarthome") ? 1 : 0;
                }
                return -1;
            }
        };
        this.listingCallback = new MiuiControlsUiControllerImpl$listingCallback$1(this);
        this.entryInfoCallbacks = new LinkedHashSet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DCEntryInfo buildDCEntryInfo() {
        Object next;
        List<SelectionItem> list = this.selectionItems;
        if (list == null || list.isEmpty()) {
            return new DCEntryInfo(false);
        }
        DCEntryInfo dCEntryInfo = new DCEntryInfo(true);
        List<SelectionItem> list2 = this.selectionItems;
        H0.s sVar = null;
        if (list2 != null) {
            Iterator<T> it = list2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (kotlin.jvm.internal.n.c(((SelectionItem) next).getComponentName(), getSelectedStructure().getComponentName())) {
                    break;
                }
            }
            SelectionItem selectionItem = (SelectionItem) next;
            if (selectionItem != null) {
                dCEntryInfo.setComponentName(selectionItem.getComponentName());
                dCEntryInfo.setAppIcon(selectionItem.getIcon());
                dCEntryInfo.setAppLabel(selectionItem.getAppName().toString());
                sVar = H0.s.f314a;
            }
        }
        if (sVar != null) {
            return dCEntryInfo;
        }
        List<SelectionItem> list3 = this.selectionItems;
        kotlin.jvm.internal.n.d(list3);
        dCEntryInfo.setComponentName(list3.get(0).getComponentName());
        List<SelectionItem> list4 = this.selectionItems;
        kotlin.jvm.internal.n.d(list4);
        dCEntryInfo.setAppLabel(list4.get(0).getAppName().toString());
        List<SelectionItem> list5 = this.selectionItems;
        kotlin.jvm.internal.n.d(list5);
        dCEntryInfo.setAppIcon(list5.get(0).getIcon());
        return dCEntryInfo;
    }

    private final FavoritesModel buildFavoritesModel() {
        CharSequence structure = getSelectedStructure().getStructure();
        List<ControlInfo> controls = getSelectedStructure().getControls();
        ArrayList arrayList = new ArrayList(I0.n.o(controls, 10));
        Iterator<T> it = controls.iterator();
        while (it.hasNext()) {
            arrayList.add(new ControlWithState(getSelectedStructure().getComponentName(), (ControlInfo) it.next(), null));
        }
        return new FavoritesModel(structure, arrayList);
    }

    private final void createControlsHeaderView(final Function0 function0, final Function1 function1, Function0 function02) {
        final ControlsCustomizeAdapter controlsCustomizeAdapter = new ControlsCustomizeAdapter(this.context, this.viewHolderFactory, this.editControlsModelController, function02);
        this.headerBinding.ivBackIcon.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MiuiControlsUiControllerImpl.createControlsHeaderView$lambda$28(function0, view);
            }
        });
        this.headerBinding.ivEditIcon.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MiuiControlsUiControllerImpl.createControlsHeaderView$lambda$29(function1, controlsCustomizeAdapter, view);
            }
        });
        this.binding.btAddDevice.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MiuiControlsUiControllerImpl.createControlsHeaderView$lambda$30(function1, controlsCustomizeAdapter, view);
            }
        });
        updateHeader();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createControlsHeaderView$lambda$28(Function0 back, View view) {
        kotlin.jvm.internal.n.g(back, "$back");
        back.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createControlsHeaderView$lambda$29(Function1 edit, ControlsCustomizeAdapter adapter, View view) {
        kotlin.jvm.internal.n.g(edit, "$edit");
        kotlin.jvm.internal.n.g(adapter, "$adapter");
        edit.invoke(adapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createControlsHeaderView$lambda$30(Function1 edit, ControlsCustomizeAdapter adapter, View view) {
        kotlin.jvm.internal.n.g(edit, "$edit");
        kotlin.jvm.internal.n.g(adapter, "$adapter");
        edit.invoke(adapter);
    }

    private final void createControlsView() {
        FavoritesModel favoritesModelBuildFavoritesModel = buildFavoritesModel();
        RecyclerView recyclerView = this.binding.rvControlsList;
        recyclerView.setAdapter(this.favoritesAdapter);
        RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        kotlin.jvm.internal.n.e(itemAnimator, "null cannot be cast to non-null type androidx.recyclerview.widget.SimpleItemAnimator");
        ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        this.favoritesAdapter.changeModel(favoritesModelBuildFavoritesModel);
        favoritesModelBuildFavoritesModel.attachAdapter(this.favoritesAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createDCViewInternal(Function0 function0, Function1 function1, Function0 function02) {
        ((ControlsListingController) this.controlsListingController.get()).addCallback(this.listingCallback);
        createControlsView();
        createControlsHeaderView(function0, function1, function02);
        updateOrientation();
        this.created = true;
    }

    private final void createDropDown(List<SelectionItem> list) {
        for (SelectionItem selectionItem : list) {
            RenderInfo.Companion.registerComponentIcon(selectionItem.getComponentName(), selectionItem.getIcon());
        }
        final ItemAdapter itemAdapter = new ItemAdapter(this.context, R.layout.controls_selection_item, getSelectedItem());
        itemAdapter.addAll(list);
        this.headerBinding.llDropDown.setOnClickListener(new View.OnClickListener() { // from class: miui.systemui.devicecontrols.ui.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MiuiControlsUiControllerImpl.createDropDown$lambda$45(this.f5712a, itemAdapter, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createDropDown$lambda$45(final MiuiControlsUiControllerImpl this$0, ItemAdapter selectionItemAdapter, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(selectionItemAdapter, "$selectionItemAdapter");
        final GlobalActionsPopupMenu globalActionsPopupMenu = new GlobalActionsPopupMenu(this$0.context, true);
        globalActionsPopupMenu.setAnchorView(this$0.headerBinding.llDropDown);
        globalActionsPopupMenu.setWidth(this$0.context.getResources().getDimensionPixelSize(R.dimen.device_controls_selection_popup_width));
        globalActionsPopupMenu.setAdapter(selectionItemAdapter);
        globalActionsPopupMenu.setListSelector(this$0.context.getDrawable(R.drawable.controls_selection_item_bg));
        ComponentName componentName = this$0.buildDCEntryInfo().getComponentName();
        final String strFlattenToString = componentName != null ? componentName.flattenToString() : null;
        globalActionsPopupMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: miui.systemui.devicecontrols.ui.A
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view2, int i2, long j2) {
                MiuiControlsUiControllerImpl.createDropDown$lambda$45$lambda$44$lambda$43(this.f5634a, strFlattenToString, globalActionsPopupMenu, adapterView, view2, i2, j2);
            }
        });
        globalActionsPopupMenu.show();
        this$0.popup = globalActionsPopupMenu;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createDropDown$lambda$45$lambda$44$lambda$43(MiuiControlsUiControllerImpl this$0, String str, GlobalActionsPopupMenu this_apply, AdapterView adapterView, View view, int i2, long j2) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(this_apply, "$this_apply");
        kotlin.jvm.internal.n.g(adapterView, "adapterView");
        kotlin.jvm.internal.n.g(view, "<anonymous parameter 1>");
        Object itemAtPosition = adapterView.getItemAtPosition(i2);
        kotlin.jvm.internal.n.e(itemAtPosition, "null cannot be cast to non-null type miui.systemui.devicecontrols.ui.SelectionItem");
        SelectionItem selectionItem = (SelectionItem) itemAtPosition;
        this$0.setSelectedItem(selectionItem);
        this$0.switchAppOrStructure(selectionItem.getStructure(), selectionItem.getComponentName());
        ComponentName componentName = this$0.buildDCEntryInfo().getComponentName();
        String strFlattenToString = componentName != null ? componentName.flattenToString() : null;
        if (!f1.n.m(strFlattenToString, str, false, 2, null)) {
            boolean zIsLocked = CommonUtils.isLocked(this$0.statusBarStateController);
            DeviceControlsEventTracker deviceControlsEventTracker = DeviceControlsEventTracker.INSTANCE;
            deviceControlsEventTracker.trackAppChangeEvent(zIsLocked);
            if (this$0.getSelectedStructure().getControls().isEmpty()) {
                String appLabel = this$0.buildDCEntryInfo().getAppLabel();
                List<SelectionItem> list = this$0.selectionItems;
                deviceControlsEventTracker.trackDevicesNotBindExposedEvent(appLabel, strFlattenToString, DeviceControlsEventTracker.OPEN_WAY_CHANGE, list != null ? list.size() : 0, zIsLocked);
            } else {
                String appLabel2 = this$0.buildDCEntryInfo().getAppLabel();
                int size = this$0.getSelectedStructure().getControls().size();
                List<ControlInfo> controls = this$0.getSelectedStructure().getControls();
                ArrayList arrayList = new ArrayList();
                for (Object obj : controls) {
                    if (ControlsUtils.INSTANCE.checkSenseType(((ControlInfo) obj).getControlId())) {
                        arrayList.add(obj);
                    }
                }
                int size2 = arrayList.size();
                List<SelectionItem> list2 = this$0.selectionItems;
                deviceControlsEventTracker.trackDevicesBindExposedEvent(appLabel2, strFlattenToString, DeviceControlsEventTracker.OPEN_WAY_CHANGE, size, size2, list2 != null ? list2.size() : 0, zIsLocked);
            }
        }
        this_apply.dismiss();
    }

    private final List<SelectionItem> createDropDownItems() {
        LinkedHashMap linkedHashMap;
        SelectionItem selectionItem;
        SelectionItem selectionItem2;
        Object next;
        List<SelectionItem> list = this.selectionItems;
        if (list == null || list.isEmpty()) {
            return I0.m.h();
        }
        List<SelectionItem> list2 = this.selectionItems;
        ArrayList arrayList = null;
        if (list2 != null) {
            linkedHashMap = new LinkedHashMap(c1.f.c(I0.F.c(I0.n.o(list2, 10)), 16));
            for (Object obj : list2) {
                linkedHashMap.put(((SelectionItem) obj).getComponentName(), obj);
            }
        } else {
            linkedHashMap = null;
        }
        List<SelectionItem> list3 = this.selectionItems;
        if (list3 != null) {
            Iterator<T> it = list3.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (TextUtils.equals("com.xiaomi.smarthome", ((SelectionItem) next).getComponentName().getPackageName())) {
                    break;
                }
            }
            selectionItem = (SelectionItem) next;
        } else {
            selectionItem = null;
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        List<StructureInfo> list4 = this.allStructures;
        if (list4 == null) {
            kotlin.jvm.internal.n.w("allStructures");
            list4 = null;
        }
        for (StructureInfo structureInfo : list4) {
            SelectionItem selectionItemCopy$default = (structureInfo.getControls().isEmpty() || linkedHashMap == null || (selectionItem2 = (SelectionItem) linkedHashMap.get(structureInfo.getComponentName())) == null) ? null : SelectionItem.copy$default(selectionItem2, null, structureInfo.getStructure(), null, null, 0, null, 61, null);
            if (selectionItemCopy$default != null) {
                arrayList2.add(selectionItemCopy$default);
            }
        }
        List<SelectionItem> list5 = this.selectionItems;
        if (list5 != null) {
            for (SelectionItem selectionItem3 : list5) {
                arrayList3.add(new SelectionItem(selectionItem3.getAppName(), "", selectionItem3.getIcon(), selectionItem3.getComponentName(), selectionItem3.getUid(), selectionItem3.getPanelActivity()));
            }
        }
        ArrayList arrayList4 = new ArrayList(I0.n.o(arrayList2, 10));
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            arrayList4.add(((SelectionItem) it2.next()).getComponentName());
        }
        ArrayList arrayList5 = new ArrayList();
        for (Object obj2 : arrayList3) {
            if (!arrayList4.contains(((SelectionItem) obj2).getComponentName())) {
                arrayList5.add(obj2);
            }
        }
        List<SelectionItem> listM0 = I0.u.m0(I0.u.a0(arrayList2, arrayList5));
        if (CommonUtils.INSTANCE.getDEBUG()) {
            ArrayList arrayList6 = new ArrayList(I0.n.o(listM0, 10));
            Iterator<T> it3 = listM0.iterator();
            while (it3.hasNext()) {
                arrayList6.add(((SelectionItem) it3.next()).getComponentName());
            }
            List<SelectionItem> list6 = this.selectionItems;
            if (list6 != null) {
                arrayList = new ArrayList(I0.n.o(list6, 10));
                Iterator<T> it4 = list6.iterator();
                while (it4.hasNext()) {
                    arrayList.add(((SelectionItem) it4.next()).getComponentName());
                }
            }
            Log.d(TAG, "all items " + arrayList6 + " " + arrayList);
        }
        I0.q.r(listM0, this.localeComparator);
        if (selectionItem != null) {
            I0.r.z(listM0, MiuiControlsUiControllerImpl$createDropDownItems$5$1.INSTANCE);
            listM0.add(0, new SelectionItem(selectionItem.getAppName(), getCurrentMiHome(), selectionItem.getIcon(), selectionItem.getComponentName(), selectionItem.getUid(), selectionItem.getPanelActivity()));
        }
        return listM0;
    }

    private final SelectionItem findSelectedItem(StructureInfo structureInfo, List<SelectionItem> list) {
        Object obj;
        Object next;
        Iterator<T> it = list.iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            SelectionItem selectionItem = (SelectionItem) next;
            if (kotlin.jvm.internal.n.c(selectionItem.getComponentName(), structureInfo.getComponentName()) && kotlin.jvm.internal.n.c(selectionItem.getStructure(), structureInfo.getStructure())) {
                break;
            }
        }
        SelectionItem selectionItem2 = (SelectionItem) next;
        if (selectionItem2 != null) {
            return selectionItem2;
        }
        Iterator<T> it2 = list.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object next2 = it2.next();
            if (kotlin.jvm.internal.n.c(((SelectionItem) next2).getComponentName(), structureInfo.getComponentName())) {
                obj = next2;
                break;
            }
        }
        return (SelectionItem) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadStructure$lambda$13(final MiuiControlsUiControllerImpl this$0, final Function0 onLoadFinish) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(onLoadFinish, "$onLoadFinish");
        final List<StructureInfo> favorites = ((ControlsController) this$0.controlsController.get()).getFavorites();
        final StructureInfo preferredStructure = this$0.getPreferredStructure(favorites);
        if (CommonUtils.INSTANCE.getDEBUG()) {
            ArrayList arrayList = new ArrayList(I0.n.o(favorites, 10));
            Iterator<T> it = favorites.iterator();
            while (it.hasNext()) {
                arrayList.add(((StructureInfo) it.next()).getStructure());
            }
            CharSequence structure = preferredStructure.getStructure();
            Log.i(TAG, "all:" + arrayList + ", selected:" + ((Object) structure) + ", " + preferredStructure.getComponentName().getClassName() + ",  equal: " + preferredStructure.equals(this$0.getSelectedStructure()));
        }
        this$0.uiExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.ui.y
            @Override // java.lang.Runnable
            public final void run() {
                MiuiControlsUiControllerImpl.loadStructure$lambda$13$lambda$12(this.f5708a, favorites, preferredStructure, onLoadFinish);
            }
        });
        this$0.trackForExpose();
        this$0.setShowForExpose(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadStructure$lambda$13$lambda$12(MiuiControlsUiControllerImpl this$0, List all, StructureInfo selected, Function0 onLoadFinish) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(all, "$all");
        kotlin.jvm.internal.n.g(selected, "$selected");
        kotlin.jvm.internal.n.g(onLoadFinish, "$onLoadFinish");
        this$0.allStructures = all;
        this$0.setSelectedStructure(selected);
        this$0.loadingStructure = false;
        onLoadFinish.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onRefreshState$lambda$21$lambda$20$lambda$19$lambda$18(MiuiControlsUiControllerImpl this$0, kotlin.jvm.internal.x position) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(position, "$position");
        this$0.favoritesAdapter.notifyItemChanged(position.f5058a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Consumer<SeedResponse> onSeedingComplete(final ComponentName componentName, final CharSequence charSequence) {
        return new Consumer() { // from class: miui.systemui.devicecontrols.ui.x
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                MiuiControlsUiControllerImpl.onSeedingComplete$lambda$5(this.f5705a, componentName, charSequence, (SeedResponse) obj);
            }
        };
    }

    public static /* synthetic */ Consumer onSeedingComplete$default(MiuiControlsUiControllerImpl miuiControlsUiControllerImpl, ComponentName componentName, CharSequence charSequence, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            charSequence = "";
        }
        return miuiControlsUiControllerImpl.onSeedingComplete(componentName, charSequence);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onSeedingComplete$lambda$5(final MiuiControlsUiControllerImpl this$0, ComponentName componentName, CharSequence structure, SeedResponse seedResponse) {
        Object next;
        Object next2;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(componentName, "$componentName");
        kotlin.jvm.internal.n.g(structure, "$structure");
        List<StructureInfo> favoritesForComponent = ((ControlsController) this$0.controlsController.get()).getFavoritesForComponent(componentName);
        Iterator<T> it = favoritesForComponent.iterator();
        while (true) {
            next = null;
            if (!it.hasNext()) {
                next2 = null;
                break;
            } else {
                next2 = it.next();
                if (TextUtils.equals(((StructureInfo) next2).getStructure(), structure)) {
                    break;
                }
            }
        }
        StructureInfo structureInfo = (StructureInfo) next2;
        if (structureInfo == null) {
            Iterator<T> it2 = favoritesForComponent.iterator();
            if (it2.hasNext()) {
                next = it2.next();
                if (it2.hasNext()) {
                    int size = ((StructureInfo) next).getControls().size();
                    do {
                        Object next3 = it2.next();
                        int size2 = ((StructureInfo) next3).getControls().size();
                        if (size < size2) {
                            next = next3;
                            size = size2;
                        }
                    } while (it2.hasNext());
                }
            }
            structureInfo = (StructureInfo) next;
        }
        if (structureInfo == null) {
            structureInfo = EMPTY_STRUCTURE;
        }
        this$0.setSelectedStructure(structureInfo);
        if (CommonUtils.INSTANCE.getDEBUG()) {
            CharSequence structure2 = this$0.getSelectedStructure().getStructure();
            Log.d(TAG, "onSeedingComplete: selected: " + ((Object) structure2) + " , " + this$0.getSelectedStructure().getComponentName());
        }
        this$0.updatePreferences(this$0.getSelectedStructure());
        this$0.uiExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.ui.q
            @Override // java.lang.Runnable
            public final void run() {
                MiuiControlsUiControllerImpl.onSeedingComplete$lambda$5$lambda$4(this.f5694a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onSeedingComplete$lambda$5$lambda$4(MiuiControlsUiControllerImpl this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        if (this$0.hidden) {
            this$0.favoritesAdapter.changeModelWithNotify(this$0.buildFavoritesModel());
        } else {
            MiuiControlsUiController.show$default(this$0, 0, 1, null);
            this$0.subscribe();
        }
    }

    private final void onSelectedStructureChanged() {
        if (CommonUtils.INSTANCE.getDEBUG()) {
            List<ControlInfo> controls = getSelectedStructure().getControls();
            ArrayList arrayList = new ArrayList(I0.n.o(controls, 10));
            Iterator<T> it = controls.iterator();
            while (it.hasNext()) {
                arrayList.add(((ControlInfo) it.next()).getControlTitle());
            }
            Log.d(TAG, "onSelectedStructureChanged : " + arrayList);
        }
        this.uiExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.ui.r
            @Override // java.lang.Runnable
            public final void run() {
                MiuiControlsUiControllerImpl.onSelectedStructureChanged$lambda$16(this.f5695a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onSelectedStructureChanged$lambda$16(MiuiControlsUiControllerImpl this$0) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        Iterator<T> it = this$0.entryInfoCallbacks.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(this$0.buildDCEntryInfo());
        }
        if (this$0.hidden) {
            this$0.favoritesAdapter.changeModelWithNotify(this$0.buildFavoritesModel());
        } else {
            this$0.reload();
        }
    }

    private final void trackForExpose() {
        if (this.editControlsModelController.isEditForExpose$miui_devicecontrols_release()) {
            return;
        }
        if (getSelectedStructure().getControls().isEmpty()) {
            DeviceControlsEventTracker deviceControlsEventTracker = DeviceControlsEventTracker.INSTANCE;
            String appLabel = buildDCEntryInfo().getAppLabel();
            ComponentName componentName = buildDCEntryInfo().getComponentName();
            String strFlattenToString = componentName != null ? componentName.flattenToString() : null;
            String str = (!isShowForExpose() || this.editControlsModelController.isEditForExpose$miui_devicecontrols_release()) ? "其他" : DeviceControlsEventTracker.OPEN_WAY_CLICK;
            List<SelectionItem> list = this.selectionItems;
            deviceControlsEventTracker.trackDevicesNotBindExposedEvent(appLabel, strFlattenToString, str, list != null ? list.size() : 0, CommonUtils.isLocked(this.statusBarStateController));
            return;
        }
        DeviceControlsEventTracker deviceControlsEventTracker2 = DeviceControlsEventTracker.INSTANCE;
        String appLabel2 = buildDCEntryInfo().getAppLabel();
        ComponentName componentName2 = buildDCEntryInfo().getComponentName();
        String strFlattenToString2 = componentName2 != null ? componentName2.flattenToString() : null;
        String str2 = (!isShowForExpose() || this.editControlsModelController.isEditForExpose$miui_devicecontrols_release()) ? "其他" : DeviceControlsEventTracker.OPEN_WAY_CLICK;
        int size = getSelectedStructure().getControls().size();
        List<ControlInfo> controls = getSelectedStructure().getControls();
        ArrayList arrayList = new ArrayList();
        for (Object obj : controls) {
            if (ControlsUtils.INSTANCE.checkSenseType(((ControlInfo) obj).getControlId())) {
                arrayList.add(obj);
            }
        }
        int size2 = arrayList.size();
        List<SelectionItem> list2 = this.selectionItems;
        deviceControlsEventTracker2.trackDevicesBindExposedEvent(appLabel2, strFlattenToString2, str2, size, size2, list2 != null ? list2.size() : 0, CommonUtils.isLocked(this.statusBarStateController));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateHeader() {
        List<SelectionItem> list;
        List<SelectionItem> listCreateDropDownItems = createDropDownItems();
        TextView textView = this.headerBinding.tvAppName;
        String appLabel = buildDCEntryInfo().getAppLabel();
        if (appLabel == null) {
            appLabel = this.context.getString(R.string.smart_home_title);
        }
        textView.setText(appLabel);
        this.headerBinding.llDropDown.setClickable(true);
        this.headerBinding.ivAnchor.setVisibility(0);
        SelectionItem selectionItemFindSelectedItem = findSelectedItem(getSelectedStructure(), listCreateDropDownItems);
        if (selectionItemFindSelectedItem == null) {
            List<SelectionItem> list2 = this.selectionItems;
            selectionItemFindSelectedItem = (list2 == null || list2.isEmpty() || (list = this.selectionItems) == null) ? null : list.get(0);
        }
        setSelectedItem(selectionItemFindSelectedItem);
        if (!listCreateDropDownItems.isEmpty() && listCreateDropDownItems.size() != 1) {
            createDropDown(listCreateDropDownItems);
        } else {
            this.headerBinding.llDropDown.setClickable(false);
            this.headerBinding.ivAnchor.setVisibility(8);
        }
    }

    private final void updateLocale() {
        this.binding.tvLoading.setText(R.string.controls_loading_device);
        this.binding.tvAddDevice.setText(R.string.controls_add_device_guide);
        this.binding.btAddDevice.setText(R.string.controls_click_to_add_device);
        this.headerBinding.ivBackIcon.setContentDescription(this.context.getString(R.string.accessibility_back));
        this.headerBinding.ivEditIcon.setContentDescription(this.context.getString(R.string.accessibility_desc_edit));
    }

    private final void updateOrientation() {
        ListPopupWindow listPopupWindow;
        Resources resources = this.context.getResources();
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        boolean z2 = !commonUtils.useAlignEndStyle() && ConfigUtils.INSTANCE.isLandscape(this.context);
        RelativeLayout root = this.binding.getRoot();
        root.setPadding(root.getPaddingLeft(), resources.getDimensionPixelSize(R.dimen.device_controls_margin_top), root.getPaddingRight(), root.getPaddingBottom());
        int dimensionPixelSize = resources.getDimensionPixelSize(z2 ? R.dimen.device_controls_panel_land_width : R.dimen.device_controls_panel_width);
        RelativeLayout llRoot = this.binding.llRoot;
        kotlin.jvm.internal.n.f(llRoot, "llRoot");
        commonUtils.setLayoutWidth(llRoot, dimensionPixelSize, true);
        this.headerBinding.ivEditIcon.setVisibility((z2 || this.currentSense != 2) ? 8 : 0);
        this.binding.btAddDevice.setVisibility(z2 ? 8 : 0);
        if (commonUtils.getIS_FOLD() || commonUtils.getIS_TABLET()) {
            RelativeLayout llRoot2 = this.binding.llRoot;
            kotlin.jvm.internal.n.f(llRoot2, "llRoot");
            commonUtils.setMarginRight(llRoot2, commonUtils.useAlignEndStyle() ? resources.getDimensionPixelOffset(R.dimen.control_center_align_end_style_padding_right) : 0, true);
            ViewGroup.LayoutParams layoutParams = this.binding.llRoot.getLayoutParams();
            if (layoutParams != null && (layoutParams instanceof FrameLayout.LayoutParams)) {
                ((FrameLayout.LayoutParams) layoutParams).gravity = commonUtils.useAlignEndStyle() ? 5 : 17;
            }
        }
        ListPopupWindow listPopupWindow2 = this.popup;
        if (listPopupWindow2 == null || !listPopupWindow2.isShowing() || (listPopupWindow = this.popup) == null) {
            return;
        }
        listPopupWindow.postShow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatePreferences(StructureInfo structureInfo) {
        if (kotlin.jvm.internal.n.c(structureInfo, EMPTY_STRUCTURE)) {
            return;
        }
        updatePreferences(structureInfo.getComponentName(), structureInfo.getStructure());
    }

    private final void updateSize() {
        Resources resources = this.context.getResources();
        this.headerBinding.tvAppName.setTextSize(0, resources.getDimension(R.dimen.device_controls_header_text_size));
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.device_controls_header_icon_size);
        CommonUtils commonUtils = CommonUtils.INSTANCE;
        ImageView ivBackIcon = this.headerBinding.ivBackIcon;
        kotlin.jvm.internal.n.f(ivBackIcon, "ivBackIcon");
        CommonUtils.setLayoutSize$default(commonUtils, ivBackIcon, dimensionPixelSize, dimensionPixelSize, false, 4, null);
        ImageView ivEditIcon = this.headerBinding.ivEditIcon;
        kotlin.jvm.internal.n.f(ivEditIcon, "ivEditIcon");
        CommonUtils.setLayoutSize$default(commonUtils, ivEditIcon, dimensionPixelSize, dimensionPixelSize, false, 4, null);
        ImageView imageView = this.headerBinding.ivAnchor;
        kotlin.jvm.internal.n.d(imageView);
        CommonUtils.setLayoutSize$default(commonUtils, imageView, resources.getDimensionPixelOffset(R.dimen.device_controls_anchor_icon_width), resources.getDimensionPixelOffset(R.dimen.device_controls_anchor_icon_height), false, 4, null);
        CommonUtils.setMarginStart$default(commonUtils, imageView, resources.getDimensionPixelSize(R.dimen.device_controls_edit_item_icon_margin_start), false, 2, null);
        SpringBackLayout controlsListContainer = this.binding.controlsListContainer;
        kotlin.jvm.internal.n.f(controlsListContainer, "controlsListContainer");
        CommonUtils.setMarginTop$default(commonUtils, controlsListContainer, resources.getDimensionPixelSize(R.dimen.device_controls_controls_layout_margin_top), false, 2, null);
        TextView textView = this.binding.tvLoading;
        int i2 = R.dimen.device_controls_subtitle_text_size;
        textView.setTextSize(0, resources.getDimension(i2));
        this.binding.tvAddDevice.setTextSize(0, resources.getDimension(i2));
        this.binding.btAddDevice.setTextSize(0, resources.getDimension(i2));
    }

    private final void updateTextAppearance() {
        this.headerBinding.ivBackIcon.setImageDrawable(this.context.getDrawable(R.drawable.ic_arrow_back));
        TextView textView = this.headerBinding.tvAppName;
        Context context = this.context;
        int i2 = R.color.controls_header_color;
        textView.setTextColor(context.getColor(i2));
        this.binding.tvAddDevice.setTextColor(this.context.getColor(R.color.controls_subtitle_color));
        this.binding.btAddDevice.setTextColor(this.context.getColor(i2));
    }

    private final void updateViewsVisiable(int i2) {
        this.currentSense = i2;
        int i3 = 8;
        this.binding.llEmpty.setVisibility(i2 == 0 ? 0 : 8);
        this.binding.tvLoading.setVisibility(i2 == 1 ? 0 : 8);
        this.binding.rvControlsList.setVisibility(i2 == 2 ? 0 : 8);
        ImageView imageView = this.headerBinding.ivEditIcon;
        if ((CommonUtils.INSTANCE.useAlignEndStyle() || !ConfigUtils.INSTANCE.isLandscape(this.context)) && i2 != 1 && i2 != 0) {
            i3 = 0;
        }
        imageView.setVisibility(i3);
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public void addDCEntryInfoCallback(Consumer<DCEntryInfo> onInfoUpdate) {
        kotlin.jvm.internal.n.g(onInfoUpdate, "onInfoUpdate");
        this.entryInfoCallbacks.add(onInfoUpdate);
    }

    @Override // miui.systemui.devicecontrols.ui.ControlsUiController
    public void closeDialogs(boolean z2) {
        ListPopupWindow listPopupWindow = this.popup;
        if (listPopupWindow != null) {
            listPopupWindow.dismiss();
        }
        this.popup = null;
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public View createDCView(Function0 back, Function1 edit, Function0 hideCustomize) {
        kotlin.jvm.internal.n.g(back, "back");
        kotlin.jvm.internal.n.g(edit, "edit");
        kotlin.jvm.internal.n.g(hideCustomize, "hideCustomize");
        setCurrentMiHome(this.sharedPreferences.getString(PREF_MIHOME_STRUCTURE, "").toString());
        if (this.created) {
            createControlsHeaderView(back, edit, hideCustomize);
            RelativeLayout root = this.binding.getRoot();
            kotlin.jvm.internal.n.f(root, "getRoot(...)");
            return root;
        }
        loadStructure(new AnonymousClass1(back, edit, hideCustomize));
        RelativeLayout root2 = this.binding.getRoot();
        kotlin.jvm.internal.n.f(root2, "getRoot(...)");
        return root2;
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public void destroy() {
        if (!this.hidden) {
            hide();
        }
        ((ControlsListingController) this.controlsListingController.get()).removeCallback(this.listingCallback);
        ((ControlsListingController) this.controlsListingController.get()).release();
        ((ControlsController) this.controlsController.get()).destroy();
        this.created = false;
        this.entryInfoCallbacks.clear();
        Log.d(TAG, "controls destroy !");
    }

    public final DelayableExecutor getBgExecutor() {
        return this.bgExecutor;
    }

    public final Context getContext() {
        return this.context;
    }

    public final ControlActionCoordinator getControlActionCoordinator() {
        return this.controlActionCoordinator;
    }

    public final E0.a getControlsController() {
        return this.controlsController;
    }

    public final E0.a getControlsListingController() {
        return this.controlsListingController;
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public CharSequence getCurrentMiHome() {
        return this.currentMiHome;
    }

    @Override // miui.systemui.devicecontrols.ui.ControlsUiController
    public StructureInfo getPreferredStructure(List<StructureInfo> structures) {
        Object next;
        Object next2;
        kotlin.jvm.internal.n.g(structures, "structures");
        if (structures.isEmpty()) {
            return EMPTY_STRUCTURE;
        }
        Object obj = null;
        String string = this.sharedPreferences.getString(PREF_COMPONENT, null);
        ComponentName componentNameUnflattenFromString = string != null ? ComponentName.unflattenFromString(string) : null;
        if (componentNameUnflattenFromString == null) {
            componentNameUnflattenFromString = EMPTY_COMPONENT;
        }
        String string2 = this.sharedPreferences.getString(PREF_STRUCTURE, "");
        if (CommonUtils.INSTANCE.getDEBUG()) {
            Log.d(TAG, "get Preferred structure from sharedP " + string2 + " , component:" + componentNameUnflattenFromString);
        }
        Iterator<T> it = structures.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            StructureInfo structureInfo = (StructureInfo) next;
            if (kotlin.jvm.internal.n.c(componentNameUnflattenFromString, structureInfo.getComponentName()) && kotlin.jvm.internal.n.c(string2, structureInfo.getStructure()) && !structureInfo.getControls().isEmpty()) {
                break;
            }
        }
        StructureInfo structureInfo2 = (StructureInfo) next;
        if (structureInfo2 != null) {
            return structureInfo2;
        }
        Iterator<T> it2 = structures.iterator();
        while (true) {
            if (!it2.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it2.next();
            StructureInfo structureInfo3 = (StructureInfo) next2;
            if (kotlin.jvm.internal.n.c(componentNameUnflattenFromString, structureInfo3.getComponentName()) && !structureInfo3.getControls().isEmpty()) {
                break;
            }
        }
        StructureInfo structureInfo4 = (StructureInfo) next2;
        if (structureInfo4 != null) {
            return structureInfo4;
        }
        Iterator<T> it3 = structures.iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            Object next3 = it3.next();
            if (kotlin.jvm.internal.n.c(componentNameUnflattenFromString, ((StructureInfo) next3).getComponentName())) {
                obj = next3;
                break;
            }
        }
        StructureInfo structureInfo5 = (StructureInfo) obj;
        return structureInfo5 == null ? structures.get(0) : structureInfo5;
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public SelectionItem getSelectedItem() {
        return this.selectedItem;
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public StructureInfo getSelectedStructure() {
        return this.selectedStructure;
    }

    public final SharedPreferences getSharedPreferences() {
        return this.sharedPreferences;
    }

    public final DelayableExecutor getUiExecutor() {
        return this.uiExecutor;
    }

    @Override // miui.systemui.devicecontrols.ui.ControlsUiController
    public void hide() {
        this.hidden = true;
        closeDialogs(true);
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public boolean isShowForExpose() {
        return this.isShowForExpose;
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public void loadStructure(final Function0 onLoadFinish) {
        kotlin.jvm.internal.n.g(onLoadFinish, "onLoadFinish");
        setShowForExpose(true);
        this.loadingStructure = true;
        this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.ui.s
            @Override // java.lang.Runnable
            public final void run() {
                MiuiControlsUiControllerImpl.loadStructure$lambda$13(this.f5696a, onLoadFinish);
            }
        });
    }

    @Override // miui.systemui.devicecontrols.ui.ControlsUiController
    public void onActionResponse(ComponentName componentName, String controlId, int i2) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(controlId, "controlId");
        this.controlActionCoordinator.enableActionOnTouch(controlId);
    }

    @Override // miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        ConfigUtils configUtils = ConfigUtils.INSTANCE;
        boolean zOrientationChanged = configUtils.orientationChanged(i2);
        boolean zDimensionsChanged = configUtils.dimensionsChanged(i2);
        this.binding.getRoot().suppressLayout(false);
        if (zOrientationChanged || configUtils.dimensionsChanged(i2)) {
            updateOrientation();
        }
        if (zDimensionsChanged) {
            updateSize();
        }
        if (configUtils.textsChanged(i2)) {
            ((ControlsListingController) this.controlsListingController.get()).reloadServices(true);
            updateLocale();
        }
        this.favoritesAdapter.onConfigurationChanged(i2);
        this.binding.getRoot().suppressLayout(false);
        if (zOrientationChanged) {
            miuix.recyclerview.widget.RecyclerView recyclerView = this.binding.rvControlsList;
            if (recyclerView.getScrollY() != 0) {
                recyclerView.scrollTo(0, 0);
            }
        }
        if (configUtils.colorsChanged(i2)) {
            updateTextAppearance();
        }
        if (configUtils.themeChanged(i2)) {
            this.listingCallback.onServicesUpdated(((ControlsListingController) this.controlsListingController.get()).getCurrentServices());
        }
    }

    @Override // miui.systemui.devicecontrols.ui.ControlsUiController
    public void onRefreshState(ComponentName componentName, List<Control> controls) {
        Object next;
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(controls, "controls");
        ControlsModel model = this.favoritesAdapter.getModel();
        kotlin.jvm.internal.n.e(model, "null cannot be cast to non-null type miui.systemui.devicecontrols.management.FavoritesModel");
        List<ElementWrapper> elements = ((FavoritesModel) model).getElements();
        if (((ControlsController) this.controlsController.get()).getLoadingData()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : elements) {
            if (obj instanceof ControlWithState) {
                arrayList.add(obj);
            }
        }
        for (Control control : controls) {
            if (CommonUtils.INSTANCE.getDEBUG()) {
                CharSequence title = control.getTitle();
                Log.i(TAG, "controls: " + ((Object) title) + aa.f3429b + control.getControlId() + ", " + ((Object) control.getStructure()));
            }
            Iterator it = arrayList.iterator();
            while (true) {
                if (it.hasNext()) {
                    next = it.next();
                    if (TextUtils.equals(((ControlWithState) next).getCi().getControlId(), control.getControlId())) {
                        break;
                    }
                } else {
                    next = null;
                    break;
                }
            }
            ControlWithState controlWithState = (ControlWithState) next;
            if (controlWithState != null) {
                final kotlin.jvm.internal.x xVar = new kotlin.jvm.internal.x();
                int iIndexOf = elements.indexOf(controlWithState);
                xVar.f5058a = iIndexOf;
                elements.set(iIndexOf, new ControlWithState(componentName, controlWithState.getCi(), control));
                CustomIconCache customIconCache = this.iconCache;
                String controlId = controlWithState.getCi().getControlId();
                Control control2 = controlWithState.getControl();
                customIconCache.store(componentName, controlId, control2 != null ? control2.getCustomIcon() : null);
                this.uiExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.ui.w
                    @Override // java.lang.Runnable
                    public final void run() {
                        MiuiControlsUiControllerImpl.onRefreshState$lambda$21$lambda$20$lambda$19$lambda$18(this.f5703a, xVar);
                    }
                });
            } else {
                Log.d(TAG, "onRefreshStateError: controls id not Found: " + control.getControlId() + aa.f3429b + ((Object) control.getTitle()));
            }
        }
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public void reload() {
        if (this.hidden) {
            return;
        }
        ((ControlsController) this.controlsController.get()).unsubscribe();
        MiuiControlsUiController.show$default(this, 0, 1, null);
        this.favoritesAdapter.changeModelWithNotify(buildFavoritesModel());
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.binding.rvControlsList, "alpha", 0.0f, 1.0f);
        objectAnimatorOfFloat.setInterpolator(new DecelerateInterpolator(1.0f));
        objectAnimatorOfFloat.setDuration(200L);
        kotlin.jvm.internal.n.d(objectAnimatorOfFloat);
        objectAnimatorOfFloat.addListener(new Animator.AnimatorListener() { // from class: miui.systemui.devicecontrols.ui.MiuiControlsUiControllerImpl$reload$lambda$47$$inlined$addListener$default$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                this.this$0.subscribe();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        objectAnimatorOfFloat.start();
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public void removeDCEntryInfoCallback(Consumer<DCEntryInfo> onInfoUpdate) {
        kotlin.jvm.internal.n.g(onInfoUpdate, "onInfoUpdate");
        this.entryInfoCallbacks.remove(onInfoUpdate);
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public void setCurrentMiHome(CharSequence value) {
        kotlin.jvm.internal.n.g(value, "value");
        this.currentMiHome = value;
        if (CommonUtils.INSTANCE.getDEBUG()) {
            Log.i(TAG, "set CurrentMiHome " + ((Object) value));
        }
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public void setSelectedItem(SelectionItem selectionItem) {
        this.selectedItem = selectionItem;
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public void setSelectedStructure(StructureInfo value) {
        kotlin.jvm.internal.n.g(value, "value");
        if (kotlin.jvm.internal.n.c(this.selectedStructure, value)) {
            return;
        }
        this.selectedStructure = value;
        onSelectedStructureChanged();
        if (kotlin.jvm.internal.n.c("com.xiaomi.smarthome", value.getComponentName().getPackageName())) {
            setCurrentMiHome(value.getStructure());
        }
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public void setShowForExpose(boolean z2) {
        this.isShowForExpose = z2;
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public void show(int i2) {
        this.hidden = false;
        if (this.loadingStructure) {
            return;
        }
        if (i2 != -1) {
            updateViewsVisiable(i2);
        } else if (((ControlsController) this.controlsController.get()).getLoadingData()) {
            updateViewsVisiable(1);
        } else if (getSelectedStructure().getControls().isEmpty()) {
            updateViewsVisiable(0);
        } else {
            updateViewsVisiable(2);
        }
        updateHeader();
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public void subscribe() {
        if (((ControlsController) this.controlsController.get()).getLoadingData()) {
            return;
        }
        ((ControlsController) this.controlsController.get()).subscribeToFavorites(getSelectedStructure());
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public void switchAppOrStructure(CharSequence structure, ComponentName componentName) {
        Object next;
        kotlin.jvm.internal.n.g(structure, "structure");
        kotlin.jvm.internal.n.g(componentName, "componentName");
        List<StructureInfo> list = this.allStructures;
        H0.s sVar = null;
        if (list == null) {
            kotlin.jvm.internal.n.w("allStructures");
            list = null;
        }
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            StructureInfo structureInfo = (StructureInfo) next;
            if (kotlin.jvm.internal.n.c(structureInfo.getStructure(), structure) && kotlin.jvm.internal.n.c(structureInfo.getComponentName(), componentName)) {
                break;
            }
        }
        StructureInfo structureInfo2 = (StructureInfo) next;
        if (structureInfo2 != null) {
            if (!kotlin.jvm.internal.n.c(structureInfo2, getSelectedStructure())) {
                setSelectedStructure(structureInfo2);
                updatePreferences(getSelectedStructure());
            }
            sVar = H0.s.f314a;
        }
        if (sVar == null) {
            ((ControlsController) this.controlsController.get()).unsubscribe();
            setSelectedStructure(new StructureInfo(componentName, structure, I0.m.h(), false, 8, null));
            ((ControlsController) this.controlsController.get()).replaceFavoritesForStructure(getSelectedStructure(), new MiuiControlsUiControllerImpl$switchAppOrStructure$2$1(this, componentName, structure));
        }
    }

    @Override // miui.systemui.devicecontrols.ui.MiuiControlsUiController
    public void updatePreferences(ComponentName componentName, CharSequence structure) {
        kotlin.jvm.internal.n.g(componentName, "componentName");
        kotlin.jvm.internal.n.g(structure, "structure");
        if (CommonUtils.INSTANCE.getDEBUG()) {
            Log.d(TAG, "updatePreference: " + componentName.flattenToString() + " " + ((Object) structure));
        }
        if (componentName.compareTo(EMPTY_COMPONENT) == 0) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putString(PREF_COMPONENT, componentName.flattenToString());
        editorEdit.putString(PREF_STRUCTURE, structure.toString());
        if (kotlin.jvm.internal.n.c(componentName.getPackageName(), "com.xiaomi.smarthome")) {
            CharSequence currentMiHome = getCurrentMiHome();
            editorEdit.putString(PREF_MIHOME_STRUCTURE, currentMiHome != null ? currentMiHome.toString() : null);
        }
        editorEdit.apply();
    }
}
