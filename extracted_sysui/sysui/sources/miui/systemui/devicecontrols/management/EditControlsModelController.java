package miui.systemui.devicecontrols.management;

import H0.s;
import I0.F;
import I0.K;
import I0.L;
import I0.t;
import I0.u;
import android.content.ComponentName;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.ListPopupWindow;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.devicecontrols.ControlInterface;
import miui.systemui.devicecontrols.ControlStatus;
import miui.systemui.devicecontrols.R;
import miui.systemui.devicecontrols.controller.ControlInfo;
import miui.systemui.devicecontrols.controller.ControlsController;
import miui.systemui.devicecontrols.controller.StructureInfo;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControls;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControlsScope;
import miui.systemui.devicecontrols.eventtracking.DeviceControlsEventTracker;
import miui.systemui.devicecontrols.management.EditControlsModelController;
import miui.systemui.devicecontrols.ui.MiuiControlsUiController;
import miui.systemui.devicecontrols.ui.SelectionItem;
import miui.systemui.devicecontrols.util.ControlsUtils;
import miui.systemui.ui.GlobalActionsPopupMenu;
import miui.systemui.util.CommonUtils;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
@DeviceControlsScope
@RequiresApi(30)
public final class EditControlsModelController {
    private static final String TAG = "EditControlsModelController";
    private Set<EditStructureInfo> allStructures;
    private View anchor;
    private final DelayableExecutor bgExecutor;
    private Function2 callback;
    private Runnable cancelLoadRunnable;
    private final Collator collator;
    private final Context context;
    private final E0.a controlsController;
    private final E0.a controlsUiController;
    private final EditControlsFavoriteModel editFavoriteModel;
    private final EditControlsRemovedModel editRemovedModel;
    private volatile boolean error;
    private boolean isEditForExpose;
    private volatile boolean loading;
    private final Comparator<EditStructureInfo> localeComparator;
    private final DelayableExecutor mainExecutor;
    private ListPopupWindow popup;
    private int reTryCount;
    private EditStructureInfo selectedStructure;
    private final StatusBarStateController statusBarStateController;
    private View title;
    private View titleContainer;
    public static final Companion Companion = new Companion(null);
    private static final String MOCK_COMPONENT_NAME = "controls.mock/.ControlsMockService";
    private static final ComponentName MOCK_COMPONENT = ComponentName.unflattenFromString(MOCK_COMPONENT_NAME);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.management.EditControlsModelController$saveControls$2, reason: invalid class name */
    public static final class AnonymousClass2 extends kotlin.jvm.internal.o implements Function0 {
        public AnonymousClass2() {
            super(0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$2(EditControlsModelController this$0) {
            kotlin.jvm.internal.n.g(this$0, "this$0");
            MiuiControlsUiController miuiControlsUiController = (MiuiControlsUiController) this$0.controlsUiController.get();
            EditStructureInfo editStructureInfo = this$0.selectedStructure;
            if (editStructureInfo != null) {
                miuiControlsUiController.updatePreferences(editStructureInfo.getComponentName(), editStructureInfo.getStructure());
            }
            miuiControlsUiController.loadStructure(EditControlsModelController$saveControls$2$1$1$2.INSTANCE);
            this$0.setEditForExpose$miui_devicecontrols_release(false);
            ((MiuiControlsUiController) this$0.controlsUiController.get()).setShowForExpose(false);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Object invoke() {
            m117invoke();
            return s.f314a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m117invoke() {
            DelayableExecutor mainExecutor = EditControlsModelController.this.getMainExecutor();
            final EditControlsModelController editControlsModelController = EditControlsModelController.this;
            mainExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.management.l
                @Override // java.lang.Runnable
                public final void run() {
                    EditControlsModelController.AnonymousClass2.invoke$lambda$2(editControlsModelController);
                }
            });
        }
    }

    public EditControlsModelController(@DeviceControls Context context, E0.a controlsController, E0.a controlsUiController, StatusBarStateController statusBarStateController, @Main DelayableExecutor mainExecutor, @Background DelayableExecutor bgExecutor) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(controlsController, "controlsController");
        kotlin.jvm.internal.n.g(controlsUiController, "controlsUiController");
        kotlin.jvm.internal.n.g(statusBarStateController, "statusBarStateController");
        kotlin.jvm.internal.n.g(mainExecutor, "mainExecutor");
        kotlin.jvm.internal.n.g(bgExecutor, "bgExecutor");
        this.context = context;
        this.controlsController = controlsController;
        this.controlsUiController = controlsUiController;
        this.statusBarStateController = statusBarStateController;
        this.mainExecutor = mainExecutor;
        this.bgExecutor = bgExecutor;
        this.editFavoriteModel = new EditControlsFavoriteModel();
        this.editRemovedModel = new EditControlsRemovedModel();
        this.allStructures = K.b();
        final Collator collator = Collator.getInstance(context.getResources().getConfiguration().getLocales().get(0));
        this.collator = collator;
        kotlin.jvm.internal.n.f(collator, "collator");
        this.localeComparator = new Comparator() { // from class: miui.systemui.devicecontrols.management.EditControlsModelController$special$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t3) {
                return collator.compare(((EditStructureInfo) t2).getStructure(), ((EditStructureInfo) t3).getStructure());
            }
        };
    }

    public static /* synthetic */ void init$default(EditControlsModelController editControlsModelController, View view, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            view = null;
        }
        editControlsModelController.init(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadAllControls$lambda$26$lambda$25(final EditControlsModelController this$0, final SelectionItem item, final Function2 callback) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(item, "$item");
        kotlin.jvm.internal.n.g(callback, "$callback");
        this$0.loading = true;
        ComponentName componentName = item.getComponentName();
        CharSequence appName = item.getAppName();
        Log.d(TAG, "start loading for " + componentName + " " + ((Object) appName) + ", try count " + this$0.reTryCount);
        ((ControlsController) this$0.controlsController.get()).loadForComponent(item.getComponentName(), new Consumer() { // from class: miui.systemui.devicecontrols.management.i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                EditControlsModelController.loadAllControls$lambda$26$lambda$25$lambda$23(this.f5623a, item, callback, (ControlsController.LoadData) obj);
            }
        }, new Consumer() { // from class: miui.systemui.devicecontrols.management.j
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                EditControlsModelController.loadAllControls$lambda$26$lambda$25$lambda$24(this.f5626a, (Runnable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadAllControls$lambda$26$lambda$25$lambda$23(final EditControlsModelController this$0, final SelectionItem item, final Function2 callback, ControlsController.LoadData data) {
        String strFlattenToString;
        Object next;
        Map<String, ControlInterface> all;
        ComponentName componentName;
        int i2;
        Object next2;
        Object next3;
        Map<String, ControlInterface> all2;
        List<ControlInfo> controls;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(item, "$item");
        kotlin.jvm.internal.n.g(callback, "$callback");
        kotlin.jvm.internal.n.g(data, "data");
        List<ControlStatus> allControls = data.getAllControls();
        int i3 = 10;
        ArrayList arrayList = new ArrayList(I0.n.o(allControls, 10));
        Iterator<T> it = allControls.iterator();
        while (true) {
            CharSequence charSequence = "";
            if (!it.hasNext()) {
                break;
            }
            CharSequence structure = ((ControlStatus) it.next()).getControl().getStructure();
            if (structure != null) {
                kotlin.jvm.internal.n.d(structure);
                charSequence = structure;
            }
            arrayList.add(charSequence);
        }
        Set setO0 = u.o0(arrayList);
        List<StructureInfo> favoritesForComponent = ((ControlsController) this$0.controlsController.get()).getFavoritesForComponent(item.getComponentName());
        LinkedHashMap linkedHashMap = new LinkedHashMap(c1.f.c(F.c(I0.n.o(favoritesForComponent, 10)), 16));
        for (Object obj : favoritesForComponent) {
            linkedHashMap.put(((StructureInfo) obj).getStructure(), obj);
        }
        Set<CharSequence> setG = L.g(setO0, linkedHashMap.keySet());
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (CharSequence charSequence2 : setG) {
            StructureInfo structureInfo = (StructureInfo) linkedHashMap.get(charSequence2);
            LinkedHashMap linkedHashMap3 = new LinkedHashMap();
            if (structureInfo != null && (controls = structureInfo.getControls()) != null) {
                ArrayList arrayList2 = new ArrayList(I0.n.o(controls, i3));
                Iterator<T> it2 = controls.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new ControlInfoWrapper(item.getComponentName(), (ControlInfo) it2.next(), true, false, 8, null));
                }
                for (Object obj2 : arrayList2) {
                    linkedHashMap3.put(((ControlInterface) obj2).getControlId(), obj2);
                }
            }
            LinkedHashMap linkedHashMap4 = new LinkedHashMap();
            linkedHashMap4.putAll(linkedHashMap3);
            linkedHashMap2.put(charSequence2, new EditStructureInfo(item.getComponentName(), charSequence2, linkedHashMap3, linkedHashMap4, false, 16, null));
            i3 = 10;
        }
        Iterator<T> it3 = data.getFavoritesControls().iterator();
        while (true) {
            strFlattenToString = null;
            if (!it3.hasNext()) {
                break;
            }
            ControlStatus controlStatus = (ControlStatus) it3.next();
            CharSequence structure2 = controlStatus.getControl().getStructure();
            if (structure2 == null) {
                structure2 = "";
            }
            EditStructureInfo editStructureInfo = (EditStructureInfo) linkedHashMap2.get(structure2);
            if (editStructureInfo != null) {
                editStructureInfo.getAdded().put(controlStatus.getControlId(), new ControlStatusWrapper(controlStatus, false, 2, null));
                editStructureInfo.getAll().put(controlStatus.getControlId(), new ControlStatusWrapper(controlStatus, false, 2, null));
            }
        }
        for (ControlStatus controlStatus2 : data.getRemovedControls()) {
            CharSequence structure3 = controlStatus2.getControl().getStructure();
            if (structure3 == null) {
                structure3 = "";
            }
            EditStructureInfo editStructureInfo2 = (EditStructureInfo) linkedHashMap2.get(structure3);
            if (editStructureInfo2 != null && (all2 = editStructureInfo2.getAll()) != null) {
                all2.put(controlStatus2.getControlId(), new ControlStatusWrapper(controlStatus2, false, 2, null));
            }
        }
        Collection collectionValues = linkedHashMap2.values();
        kotlin.jvm.internal.n.f(collectionValues, "<get-values>(...)");
        Iterator it4 = collectionValues.iterator();
        while (true) {
            if (!it4.hasNext()) {
                next = null;
                break;
            }
            next = it4.next();
            EditStructureInfo editStructureInfo3 = (EditStructureInfo) next;
            if (kotlin.jvm.internal.n.c(editStructureInfo3.getComponentName().getPackageName(), "com.xiaomi.smarthome") && editStructureInfo3.getStructure().length() > 0 && kotlin.jvm.internal.n.c(editStructureInfo3.getStructure(), ((MiuiControlsUiController) this$0.controlsUiController.get()).getCurrentMiHome())) {
                break;
            }
        }
        EditStructureInfo editStructureInfo4 = (EditStructureInfo) next;
        if (editStructureInfo4 == null) {
            Collection collectionValues2 = linkedHashMap2.values();
            kotlin.jvm.internal.n.f(collectionValues2, "<get-values>(...)");
            Iterator it5 = collectionValues2.iterator();
            while (true) {
                if (!it5.hasNext()) {
                    next2 = null;
                    break;
                }
                next2 = it5.next();
                EditStructureInfo editStructureInfo5 = (EditStructureInfo) next2;
                if (kotlin.jvm.internal.n.c(editStructureInfo5.getComponentName().getPackageName(), "com.xiaomi.smarthome") && editStructureInfo5.getStructure().length() > 0) {
                    break;
                }
            }
            editStructureInfo4 = (EditStructureInfo) next2;
            if (editStructureInfo4 == null) {
                Collection collectionValues3 = linkedHashMap2.values();
                kotlin.jvm.internal.n.f(collectionValues3, "<get-values>(...)");
                Iterator it6 = collectionValues3.iterator();
                while (true) {
                    if (it6.hasNext()) {
                        next3 = it6.next();
                        if (kotlin.jvm.internal.n.c(((EditStructureInfo) next3).getComponentName().getPackageName(), "com.xiaomi.smarthome")) {
                            break;
                        }
                    } else {
                        next3 = null;
                        break;
                    }
                }
                editStructureInfo4 = (EditStructureInfo) next3;
            }
        }
        Collection collectionValues4 = linkedHashMap2.values();
        kotlin.jvm.internal.n.f(collectionValues4, "<get-values>(...)");
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : collectionValues4) {
            EditStructureInfo editStructureInfo6 = (EditStructureInfo) obj3;
            if (!editStructureInfo6.getAll().isEmpty() && !kotlin.jvm.internal.n.c(editStructureInfo6.getComponentName().getPackageName(), "com.xiaomi.smarthome")) {
                arrayList3.add(obj3);
            }
        }
        Set setN0 = u.n0(arrayList3);
        if (editStructureInfo4 != null) {
            setN0.add(editStructureInfo4);
        }
        final SortedSet<EditStructureInfo> sortedSetD = t.D(setN0, this$0.localeComparator);
        if (sortedSetD.isEmpty()) {
            sortedSetD.add(new EditStructureInfo(item.getComponentName(), item.getStructure(), new LinkedHashMap(), new LinkedHashMap(), false, 16, null));
        }
        int size = sortedSetD.size();
        ArrayList arrayList4 = new ArrayList(I0.n.o(sortedSetD, 10));
        for (EditStructureInfo editStructureInfo7 : sortedSetD) {
            CharSequence structure4 = editStructureInfo7.getStructure();
            Collection<ControlInterface> collectionValues5 = editStructureInfo7.getAdded().values();
            ArrayList arrayList5 = new ArrayList(I0.n.o(collectionValues5, 10));
            Iterator<T> it7 = collectionValues5.iterator();
            while (it7.hasNext()) {
                arrayList5.add(((ControlInterface) it7.next()).getTitle());
            }
            Collection<ControlInterface> collectionValues6 = editStructureInfo7.getAll().values();
            ArrayList arrayList6 = new ArrayList(I0.n.o(collectionValues6, 10));
            Iterator<T> it8 = collectionValues6.iterator();
            while (it8.hasNext()) {
                arrayList6.add(((ControlInterface) it8.next()).getTitle());
            }
            arrayList4.add(((Object) structure4) + " " + arrayList5 + " " + arrayList6);
        }
        Log.d(TAG, "loaded structures " + size + " " + arrayList4);
        this$0.error = data.getErrorOnLoad();
        this$0.mainExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.management.h
            @Override // java.lang.Runnable
            public final void run() {
                EditControlsModelController.loadAllControls$lambda$26$lambda$25$lambda$23$lambda$22(this.f5619a, callback, sortedSetD, item);
            }
        });
        if (this$0.error && (i2 = this$0.reTryCount) < 1) {
            int i4 = i2 + 1;
            this$0.reTryCount = i4;
            Log.d(TAG, "load edit error, retry " + i4);
            this$0.loadAllControls(callback);
        }
        DeviceControlsEventTracker deviceControlsEventTracker = DeviceControlsEventTracker.INSTANCE;
        SelectionItem selectedItem = ((MiuiControlsUiController) this$0.controlsUiController.get()).getSelectedItem();
        String strValueOf = String.valueOf(selectedItem != null ? selectedItem.getAppName() : null);
        SelectionItem selectedItem2 = ((MiuiControlsUiController) this$0.controlsUiController.get()).getSelectedItem();
        if (selectedItem2 != null && (componentName = selectedItem2.getComponentName()) != null) {
            strFlattenToString = componentName.flattenToString();
        }
        EditStructureInfo editStructureInfo8 = this$0.selectedStructure;
        deviceControlsEventTracker.trackControlsEditExposedEvent(strValueOf, strFlattenToString, (editStructureInfo8 == null || (all = editStructureInfo8.getAll()) == null || all.size() != 0) ? DeviceControlsEventTracker.NOT_SKIP : DeviceControlsEventTracker.IS_SKIP, CommonUtils.isLocked(this$0.statusBarStateController));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadAllControls$lambda$26$lambda$25$lambda$23$lambda$22(EditControlsModelController this$0, Function2 callback, SortedSet filteredStructures, SelectionItem item) {
        Object next;
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(callback, "$callback");
        kotlin.jvm.internal.n.g(filteredStructures, "$filteredStructures");
        kotlin.jvm.internal.n.g(item, "$item");
        this$0.callback = callback;
        this$0.allStructures = filteredStructures;
        Iterator it = filteredStructures.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (kotlin.jvm.internal.n.c(((EditStructureInfo) next).getStructure(), item.getStructure())) {
                    break;
                }
            }
        }
        EditStructureInfo editStructureInfo = (EditStructureInfo) next;
        if (editStructureInfo == null) {
            editStructureInfo = (EditStructureInfo) filteredStructures.first();
        }
        kotlin.jvm.internal.n.d(editStructureInfo);
        this$0.selectStructure(editStructureInfo);
        this$0.updateHeader();
        this$0.loading = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadAllControls$lambda$26$lambda$25$lambda$24(EditControlsModelController this$0, Runnable runnable) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(runnable, "runnable");
        this$0.cancelLoadRunnable = runnable;
    }

    private final void saveToStructure() {
        EditStructureInfo editStructureInfo = this.selectedStructure;
        if (editStructureInfo != null) {
            editStructureInfo.getAdded().clear();
            for (Object obj : this.editFavoriteModel.getElements()) {
                ControlInterface controlInterface = obj instanceof ControlInterface ? (ControlInterface) obj : null;
                if (controlInterface != null) {
                    editStructureInfo.getAdded().put(controlInterface.getControlId(), controlInterface);
                }
            }
            editStructureInfo.setEdit(!this.editRemovedModel.getGuideWrapper().getVisibility());
        }
    }

    private final void selectStructure(EditStructureInfo editStructureInfo) {
        SelectionItem selectedItem;
        saveToStructure();
        this.selectedStructure = editStructureInfo;
        EditControlsFavoriteModel editControlsFavoriteModel = this.editFavoriteModel;
        Collection<ControlInterface> collectionValues = editStructureInfo.getAdded().values();
        ArrayList arrayList = new ArrayList(I0.n.o(collectionValues, 10));
        for (Object obj : collectionValues) {
            kotlin.jvm.internal.n.e(obj, "null cannot be cast to non-null type miui.systemui.devicecontrols.management.ElementWrapper");
            arrayList.add((ElementWrapper) obj);
        }
        editControlsFavoriteModel.changeElements(arrayList);
        Collection<ControlInterface> collectionValues2 = editStructureInfo.getAll().values();
        ArrayList arrayList2 = new ArrayList(I0.n.o(collectionValues2, 10));
        for (Object obj2 : collectionValues2) {
            kotlin.jvm.internal.n.e(obj2, "null cannot be cast to non-null type miui.systemui.devicecontrols.management.ElementWrapper");
            arrayList2.add((ElementWrapper) obj2);
        }
        if (arrayList2.isEmpty() && (selectedItem = ((MiuiControlsUiController) this.controlsUiController.get()).getSelectedItem()) != null) {
            this.editRemovedModel.updateEmptyGuide(selectedItem.getAppName(), selectedItem.getComponentName(), selectedItem.getPanelActivity(), true);
        }
        this.editRemovedModel.changeElements(arrayList2);
        this.editRemovedModel.updateMarkVisible(this.editFavoriteModel.getSenseControls().size() < 4, this.editFavoriteModel.getNormalControls().size() < 50, false);
        Function2 function2 = this.callback;
        if (function2 != null) {
            function2.invoke(this.editFavoriteModel, this.editRemovedModel);
        }
        View view = this.title;
        TextView textView = view instanceof TextView ? (TextView) view : null;
        if (textView == null) {
            return;
        }
        textView.setText(kotlin.jvm.internal.n.c(editStructureInfo.getComponentName().getPackageName(), "com.xiaomi.smarthome") ? this.context.getResources().getString(R.string.controls_edit_added_title) : (editStructureInfo.getStructure().length() != 0 || this.allStructures.size() > 1) ? editStructureInfo.getStructure().length() == 0 ? this.context.getResources().getString(R.string.controls_default_structure) : editStructureInfo.getStructure() : this.context.getResources().getString(R.string.controls_edit_added_title));
    }

    private final void updateHeader() {
        if (this.allStructures.size() <= 1) {
            View view = this.anchor;
            if (view != null) {
                view.setVisibility(8);
            }
            View view2 = this.titleContainer;
            if (view2 != null) {
                view2.setOnClickListener(null);
                return;
            }
            return;
        }
        View view3 = this.anchor;
        if (view3 != null) {
            view3.setVisibility(0);
        }
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: miui.systemui.devicecontrols.management.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view4) {
                EditControlsModelController.updateHeader$lambda$33(this.f5627a, view4);
            }
        };
        View view4 = this.titleContainer;
        if (view4 != null) {
            view4.setOnClickListener(onClickListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateHeader$lambda$33(final EditControlsModelController this$0, View view) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        ItemAdapter itemAdapter = new ItemAdapter(this$0.context, R.layout.controls_selection_item, this$0.selectedStructure);
        itemAdapter.addAll(this$0.allStructures);
        final GlobalActionsPopupMenu globalActionsPopupMenu = new GlobalActionsPopupMenu(this$0.context, true);
        globalActionsPopupMenu.setAnchorView(this$0.anchor);
        globalActionsPopupMenu.setWidth(this$0.context.getResources().getDimensionPixelSize(R.dimen.device_controls_selection_popup_width));
        globalActionsPopupMenu.setAdapter(itemAdapter);
        globalActionsPopupMenu.setListSelector(this$0.context.getDrawable(R.drawable.controls_selection_item_bg));
        globalActionsPopupMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: miui.systemui.devicecontrols.management.g
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view2, int i2, long j2) {
                EditControlsModelController.updateHeader$lambda$33$lambda$32$lambda$31(this.f5617a, globalActionsPopupMenu, adapterView, view2, i2, j2);
            }
        });
        globalActionsPopupMenu.show();
        this$0.popup = globalActionsPopupMenu;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateHeader$lambda$33$lambda$32$lambda$31(EditControlsModelController this$0, GlobalActionsPopupMenu this_apply, AdapterView adapterView, View view, int i2, long j2) {
        kotlin.jvm.internal.n.g(this$0, "this$0");
        kotlin.jvm.internal.n.g(this_apply, "$this_apply");
        kotlin.jvm.internal.n.g(adapterView, "adapterView");
        kotlin.jvm.internal.n.g(view, "<anonymous parameter 1>");
        Object itemAtPosition = adapterView.getItemAtPosition(i2);
        kotlin.jvm.internal.n.e(itemAtPosition, "null cannot be cast to non-null type miui.systemui.devicecontrols.management.EditStructureInfo");
        this$0.selectStructure((EditStructureInfo) itemAtPosition);
        this_apply.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateMarkVisible(boolean z2, boolean z3) {
        EditControlsModel.updateMarkVisible$default(this.editRemovedModel, z2, z3, false, 4, null);
    }

    public final void attach(Function2 attachEachOther) {
        kotlin.jvm.internal.n.g(attachEachOther, "attachEachOther");
        attachEachOther.invoke(this.editFavoriteModel, this.editRemovedModel);
    }

    public final void changeFavoriteStatus(String controlId, boolean z2) {
        ControlInterface controlInterface;
        Map<String, ControlInterface> all;
        Collection<ControlInterface> collectionValues;
        Object next;
        kotlin.jvm.internal.n.g(controlId, "controlId");
        EditStructureInfo editStructureInfo = this.selectedStructure;
        if (editStructureInfo == null || (all = editStructureInfo.getAll()) == null || (collectionValues = all.values()) == null) {
            controlInterface = null;
        } else {
            Iterator<T> it = collectionValues.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                } else {
                    next = it.next();
                    if (TextUtils.equals(((ControlInterface) next).getControlId(), controlId)) {
                        break;
                    }
                }
            }
            controlInterface = (ControlInterface) next;
        }
        Log.d(TAG, "changeFavoriteStatus : " + controlId + " " + z2);
        if (controlInterface != null) {
            controlInterface.setFavorite(z2);
            if (z2) {
                EditControlsModel.removeFromElements$default(this.editRemovedModel, controlInterface, null, 2, null);
                this.editFavoriteModel.addToElements(controlInterface, new EditControlsModelController$changeFavoriteStatus$1$1(this));
            } else {
                this.editFavoriteModel.removeFromElements(controlInterface, new EditControlsModelController$changeFavoriteStatus$1$2(this));
                EditControlsModel.addToElements$default(this.editRemovedModel, controlInterface, null, 2, null);
            }
        }
    }

    public final void destroy() {
        ListPopupWindow listPopupWindow = this.popup;
        if (listPopupWindow != null) {
            listPopupWindow.dismiss();
        }
        this.popup = null;
        Runnable runnable = this.cancelLoadRunnable;
        if (runnable != null) {
            runnable.run();
        }
        this.loading = false;
        this.error = false;
    }

    public final DelayableExecutor getBgExecutor() {
        return this.bgExecutor;
    }

    public final DelayableExecutor getMainExecutor() {
        return this.mainExecutor;
    }

    public final void init(View view) {
        this.titleContainer = view;
        this.title = view != null ? view.findViewById(android.R.id.title) : null;
        View viewFindViewById = view != null ? view.findViewById(android.R.id.icon) : null;
        this.anchor = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(8);
        }
        this.reTryCount = 0;
        this.editFavoriteModel.changeElements(I0.m.h());
        this.editRemovedModel.changeElements(I0.m.h());
    }

    public final boolean isEditForExpose$miui_devicecontrols_release() {
        return this.isEditForExpose;
    }

    public final void loadAllControls(final Function2 callback) {
        kotlin.jvm.internal.n.g(callback, "callback");
        final SelectionItem selectedItem = ((MiuiControlsUiController) this.controlsUiController.get()).getSelectedItem();
        if (selectedItem != null) {
            Log.d(TAG, "load for " + selectedItem.getComponentName() + " " + ((Object) selectedItem.getAppName()));
            this.editRemovedModel.updateEmptyGuide(selectedItem.getAppName(), selectedItem.getComponentName(), selectedItem.getPanelActivity(), true);
            this.bgExecutor.execute(new Runnable() { // from class: miui.systemui.devicecontrols.management.f
                @Override // java.lang.Runnable
                public final void run() {
                    EditControlsModelController.loadAllControls$lambda$26$lambda$25(this.f5614a, selectedItem, callback);
                }
            });
        }
    }

    public final void saveControls() {
        ((MiuiControlsUiController) this.controlsUiController.get()).setShowForExpose(true);
        this.isEditForExpose = true;
        Log.d(TAG, "save controls");
        this.reTryCount = 0;
        View view = this.anchor;
        if (view != null) {
            view.setVisibility(8);
        }
        View view2 = this.titleContainer;
        if (view2 != null) {
            view2.setOnClickListener(null);
        }
        ListPopupWindow listPopupWindow = this.popup;
        if (listPopupWindow != null) {
            listPopupWindow.dismiss();
        }
        this.popup = null;
        Runnable runnable = this.cancelLoadRunnable;
        if (runnable != null) {
            runnable.run();
        }
        this.cancelLoadRunnable = null;
        this.callback = null;
        if (this.loading || this.error) {
            return;
        }
        saveToStructure();
        ControlsController controlsController = (ControlsController) this.controlsController.get();
        Set<EditStructureInfo> set = this.allStructures;
        ArrayList arrayList = new ArrayList(I0.n.o(set, 10));
        for (EditStructureInfo editStructureInfo : set) {
            ComponentName componentName = editStructureInfo.getComponentName();
            CharSequence structure = editStructureInfo.getStructure();
            Map<String, ControlInterface> added = editStructureInfo.getAdded();
            ArrayList arrayList2 = new ArrayList(added.size());
            Iterator<Map.Entry<String, ControlInterface>> it = added.entrySet().iterator();
            while (it.hasNext()) {
                arrayList2.add(ControlsUtils.INSTANCE.toControlInfo(it.next().getValue()));
            }
            arrayList.add(new StructureInfo(componentName, structure, arrayList2, editStructureInfo.getEdit()));
        }
        controlsController.replaceFavoritesForComponent(arrayList, new AnonymousClass2());
        this.loading = false;
    }

    public final void setEditForExpose$miui_devicecontrols_release(boolean z2) {
        this.isEditForExpose = z2;
    }
}
