package miui.systemui.devicecontrols.management;

import H0.s;
import I0.u;
import android.util.Log;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.devicecontrols.ControlInterface;
import miui.systemui.devicecontrols.controller.ControlInfo;
import miui.systemui.devicecontrols.util.ControlsUtils;

/* JADX INFO: loaded from: classes3.dex */
public class EditControlsModel implements ControlsModel {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "EditControlsModel";
    private ControlAdapter adapter;
    private boolean availableForAction;
    private List<ElementWrapper> senseControls = u.m0(I0.m.h());
    private List<ElementWrapper> normalControls = u.m0(I0.m.h());
    private final List<ElementWrapper> elements = u.m0(I0.m.h());
    private final ItemTouchHelper.Callback itemTouchHelperCallback = new ItemTouchHelper.Callback() { // from class: miui.systemui.devicecontrols.management.EditControlsModel$itemTouchHelperCallback$1
        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current, RecyclerView.ViewHolder target) {
            kotlin.jvm.internal.n.g(recyclerView, "recyclerView");
            kotlin.jvm.internal.n.g(current, "current");
            kotlin.jvm.internal.n.g(target, "target");
            return this.this$0.getAvailableForAction();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            kotlin.jvm.internal.n.g(recyclerView, "recyclerView");
            kotlin.jvm.internal.n.g(viewHolder, "viewHolder");
            return ItemTouchHelper.Callback.makeMovementFlags(15, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isItemViewSwipeEnabled() {
            return this.this$0.getAvailableForAction();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            return this.this$0.getAvailableForAction();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder from, RecyclerView.ViewHolder to) {
            kotlin.jvm.internal.n.g(recyclerView, "recyclerView");
            kotlin.jvm.internal.n.g(from, "from");
            kotlin.jvm.internal.n.g(to, "to");
            this.this$0.onMoveItemInternal(from.getAdapterPosition(), to.getAdapterPosition());
            return this.this$0.getAvailableForAction();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder p02, int i2) {
            kotlin.jvm.internal.n.g(p02, "p0");
        }
    };

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.management.EditControlsModel$addToElements$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function2 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2);
        }

        public final void invoke(boolean z2, boolean z3) {
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue());
            return s.f314a;
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.management.EditControlsModel$removeFromElements$1, reason: invalid class name and case insensitive filesystem */
    public static final class C05021 extends kotlin.jvm.internal.o implements Function2 {
        public static final C05021 INSTANCE = new C05021();

        public C05021() {
            super(2);
        }

        public final void invoke(boolean z2, boolean z3) {
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue());
            return s.f314a;
        }
    }

    public static /* synthetic */ void addToElements$default(EditControlsModel editControlsModel, ControlInterface controlInterface, Function2 function2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addToElements");
        }
        if ((i2 & 2) != 0) {
            function2 = AnonymousClass1.INSTANCE;
        }
        editControlsModel.addToElements(controlInterface, function2);
    }

    private final void moveElement(int i2, int i3) {
        int i4;
        if (i2 < i3) {
            while (i2 < i3) {
                if (i2 >= 0 && (i4 = i2 + 1) < getElements().size()) {
                    Collections.swap(getElements(), i2, i4);
                }
                i2++;
            }
            return;
        }
        int i5 = i3 + 1;
        if (i5 > i2) {
            return;
        }
        while (true) {
            if (i2 > 0 && i2 < getElements().size()) {
                Collections.swap(getElements(), i2, i2 - 1);
            }
            if (i2 == i5) {
                return;
            } else {
                i2--;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onMoveItemInternal(int i2, int i3) {
        int dividerPosition = getDividerPosition();
        if (i2 == dividerPosition || i3 == dividerPosition) {
            return;
        }
        if ((i2 < dividerPosition && i3 < dividerPosition) || (i2 > dividerPosition && i3 > dividerPosition)) {
            moveElement(i2, i3);
            ControlAdapter controlAdapter = this.adapter;
            if (controlAdapter != null) {
                controlAdapter.notifyItemMoved(i2, i3);
            }
        }
        List<ControlInfo> favorites = getFavorites();
        ArrayList arrayList = new ArrayList(I0.n.o(favorites, 10));
        Iterator<T> it = favorites.iterator();
        while (it.hasNext()) {
            arrayList.add(((ControlInfo) it.next()).getControlTitle());
        }
        Log.d(TAG, "moved " + arrayList);
    }

    public static /* synthetic */ void removeFromElements$default(EditControlsModel editControlsModel, ControlInterface controlInterface, Function2 function2, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeFromElements");
        }
        if ((i2 & 2) != 0) {
            function2 = C05021.INSTANCE;
        }
        editControlsModel.removeFromElements(controlInterface, function2);
    }

    public static /* synthetic */ void updateMarkVisible$default(EditControlsModel editControlsModel, boolean z2, boolean z3, boolean z4, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateMarkVisible");
        }
        if ((i2 & 4) != 0) {
            z4 = true;
        }
        editControlsModel.updateMarkVisible(z2, z3, z4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addToElements(ControlInterface target, Function2 updateMark) {
        kotlin.jvm.internal.n.g(target, "target");
        kotlin.jvm.internal.n.g(updateMark, "updateMark");
        int dividerPosition = getDividerPosition();
        boolean z2 = false;
        if (ControlsUtils.INSTANCE.checkSenseType(target.getControlId())) {
            if (dividerPosition <= 0) {
                dividerPosition = 0;
            }
            getSenseControls().add(target);
        } else {
            getNormalControls().add(target);
            dividerPosition = getElements().size();
        }
        if (!getSenseControls().isEmpty() && !getNormalControls().isEmpty()) {
            z2 = true;
        }
        updateDividerVisible(z2);
        ControlAdapter controlAdapter = this.adapter;
        if (controlAdapter != null) {
            controlAdapter.notifyItemInserted(dividerPosition);
        }
        getElements().add(dividerPosition, target);
        ControlAdapter controlAdapter2 = this.adapter;
        if (controlAdapter2 != null) {
            controlAdapter2.onItemInsert(dividerPosition);
        }
    }

    @Override // miui.systemui.devicecontrols.management.ControlsModel
    public void attachAdapter(ControlAdapter adapter) {
        kotlin.jvm.internal.n.g(adapter, "adapter");
        this.adapter = adapter;
    }

    public final void changeElements(List<? extends ElementWrapper> list) {
        kotlin.jvm.internal.n.g(list, "list");
        getElements().clear();
        getSenseControls().clear();
        getNormalControls().clear();
        getElements().addAll(createWrapper(list));
    }

    public List<ElementWrapper> createWrapper(List<? extends ElementWrapper> controls) {
        kotlin.jvm.internal.n.g(controls, "controls");
        if (controls.isEmpty()) {
            return u.b0(I0.m.h(), new DividerWrapper(false, false, 3, null));
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : controls) {
            Object obj2 = (ElementWrapper) obj;
            if (obj2 instanceof ControlInterface ? ControlsUtils.INSTANCE.checkSenseType(((ControlInterface) obj2).getControlId()) : false) {
                arrayList.add(obj);
            }
        }
        setSenseControls(u.m0(arrayList));
        setNormalControls(u.m0(u.Y(controls, u.o0(getSenseControls()))));
        return u.a0(u.b0(getSenseControls(), new DividerWrapper(false, (getSenseControls().isEmpty() || getNormalControls().isEmpty()) ? false : true)), getNormalControls());
    }

    public final ControlAdapter getAdapter() {
        return this.adapter;
    }

    public boolean getAvailableForAction() {
        return this.availableForAction;
    }

    public final int getDividerPosition() {
        Iterator<ElementWrapper> it = getElements().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (it.next() instanceof DividerWrapper) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    @Override // miui.systemui.devicecontrols.management.ControlsModel
    public List<ElementWrapper> getElements() {
        return this.elements;
    }

    @Override // miui.systemui.devicecontrols.management.ControlsModel
    public ItemTouchHelper.Callback getItemTouchHelperCallback() {
        return this.itemTouchHelperCallback;
    }

    public List<ElementWrapper> getNormalControls() {
        return this.normalControls;
    }

    public List<ElementWrapper> getSenseControls() {
        return this.senseControls;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void removeFromElements(ControlInterface target, Function2 updateMark) {
        kotlin.jvm.internal.n.g(target, "target");
        kotlin.jvm.internal.n.g(updateMark, "updateMark");
        int iIndexOf = getElements().indexOf(target);
        if (ControlsUtils.INSTANCE.checkSenseType(target.getControlId())) {
            getSenseControls().remove(target);
        } else {
            getNormalControls().remove(target);
        }
        updateDividerVisible((getSenseControls().isEmpty() || getNormalControls().isEmpty()) ? false : true);
        getElements().remove(target);
        ControlAdapter controlAdapter = this.adapter;
        if (controlAdapter != null) {
            controlAdapter.notifyItemRemoved(iIndexOf);
        }
    }

    public final void setAdapter(ControlAdapter controlAdapter) {
        this.adapter = controlAdapter;
    }

    public void setAvailableForAction(boolean z2) {
        this.availableForAction = z2;
    }

    public void setNormalControls(List<ElementWrapper> list) {
        kotlin.jvm.internal.n.g(list, "<set-?>");
        this.normalControls = list;
    }

    public void setSenseControls(List<ElementWrapper> list) {
        kotlin.jvm.internal.n.g(list, "<set-?>");
        this.senseControls = list;
    }

    public final void updateDividerVisible(boolean z2) {
        Iterator<ElementWrapper> it = getElements().iterator();
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            } else if (it.next() instanceof DividerWrapper) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 < 0 || i2 >= getElements().size()) {
            Log.e(TAG, "updateDividerError: " + i2 + ", there must be something wrong!!");
            return;
        }
        ElementWrapper elementWrapper = getElements().get(i2);
        kotlin.jvm.internal.n.e(elementWrapper, "null cannot be cast to non-null type miui.systemui.devicecontrols.management.DividerWrapper");
        DividerWrapper dividerWrapper = (DividerWrapper) elementWrapper;
        if (dividerWrapper.getShowDivider() != z2) {
            dividerWrapper.setShowDivider(z2);
            ControlAdapter controlAdapter = this.adapter;
            if (controlAdapter != null) {
                controlAdapter.notifyItemChanged(i2);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void updateMarkVisible(boolean z2, boolean z3, boolean z4) {
        ControlAdapter controlAdapter;
        for (ElementWrapper elementWrapper : getElements()) {
            if (elementWrapper instanceof ControlInterface) {
                ControlsUtils controlsUtils = ControlsUtils.INSTANCE;
                if (controlsUtils.checkSenseType(((ControlInterface) elementWrapper).getControlId())) {
                    controlsUtils.setMarkVisible(elementWrapper, z2);
                } else {
                    controlsUtils.setMarkVisible(elementWrapper, z3);
                }
            }
        }
        if (!z4 || (controlAdapter = this.adapter) == null) {
            return;
        }
        controlAdapter.notifyItemRangeChanged(0, getElements().size(), ControlAdapter.MARK_PAYLOAD);
    }
}
