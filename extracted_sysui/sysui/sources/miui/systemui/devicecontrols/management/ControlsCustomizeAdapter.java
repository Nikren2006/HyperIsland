package miui.systemui.devicecontrols.management;

import H0.s;
import android.content.Context;
import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import miui.systemui.controlcenter.customize.CustomizeAdapter;
import miui.systemui.devicecontrols.R;

/* JADX INFO: loaded from: classes3.dex */
public final class ControlsCustomizeAdapter implements CustomizeAdapter {
    private final ControlAdapter addedAdapter;
    private final Context context;
    private final EditControlsModelController editControlsModelController;
    private final Function0 hideCustomize;
    private final ControlAdapter notAddedAdapter;
    private final ViewHolderFactory viewHolderFactory;

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.management.ControlsCustomizeAdapter$onShowStart$1, reason: invalid class name */
    public /* synthetic */ class AnonymousClass1 extends kotlin.jvm.internal.l implements Function2 {
        public AnonymousClass1(Object obj) {
            super(2, obj, ControlsCustomizeAdapter.class, "attachModel", "attachModel(Lmiui/systemui/devicecontrols/management/ControlsModel;Lmiui/systemui/devicecontrols/management/ControlsModel;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((ControlsModel) obj, (ControlsModel) obj2);
            return s.f314a;
        }

        public final void invoke(ControlsModel p02, ControlsModel p12) {
            kotlin.jvm.internal.n.g(p02, "p0");
            kotlin.jvm.internal.n.g(p12, "p1");
            ((ControlsCustomizeAdapter) this.receiver).attachModel(p02, p12);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.management.ControlsCustomizeAdapter$onShowStart$2, reason: invalid class name */
    public /* synthetic */ class AnonymousClass2 extends kotlin.jvm.internal.l implements Function2 {
        public AnonymousClass2(Object obj) {
            super(2, obj, ControlsCustomizeAdapter.class, "attachModel", "attachModel(Lmiui/systemui/devicecontrols/management/ControlsModel;Lmiui/systemui/devicecontrols/management/ControlsModel;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((ControlsModel) obj, (ControlsModel) obj2);
            return s.f314a;
        }

        public final void invoke(ControlsModel p02, ControlsModel p12) {
            kotlin.jvm.internal.n.g(p02, "p0");
            kotlin.jvm.internal.n.g(p12, "p1");
            ((ControlsCustomizeAdapter) this.receiver).attachModel(p02, p12);
        }
    }

    public ControlsCustomizeAdapter(Context context, ViewHolderFactory viewHolderFactory, EditControlsModelController editControlsModelController, Function0 hideCustomize) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(viewHolderFactory, "viewHolderFactory");
        kotlin.jvm.internal.n.g(editControlsModelController, "editControlsModelController");
        kotlin.jvm.internal.n.g(hideCustomize, "hideCustomize");
        this.context = context;
        this.viewHolderFactory = viewHolderFactory;
        this.editControlsModelController = editControlsModelController;
        this.hideCustomize = hideCustomize;
        this.addedAdapter = new ControlAdapter(context, viewHolderFactory);
        this.notAddedAdapter = new ControlAdapter(context, viewHolderFactory);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void attachModel(ControlsModel controlsModel, ControlsModel controlsModel2) {
        ControlAdapter addedAdapter = getAddedAdapter();
        addedAdapter.changeModelWithNotify(controlsModel);
        controlsModel.attachAdapter(addedAdapter);
        ControlAdapter notAddedAdapter = getNotAddedAdapter();
        notAddedAdapter.changeModelWithNotify(controlsModel2);
        controlsModel2.attachAdapter(notAddedAdapter);
    }

    @Override // miui.systemui.controlcenter.customize.CustomizeAdapter
    public String getAddedSubtitle() {
        String string = this.context.getResources().getString(R.string.controls_edit_added_subtitle);
        kotlin.jvm.internal.n.f(string, "getString(...)");
        return string;
    }

    @Override // miui.systemui.controlcenter.customize.CustomizeAdapter
    public String getAddedTitle() {
        String string = this.context.getResources().getString(R.string.controls_edit_added_title);
        kotlin.jvm.internal.n.f(string, "getString(...)");
        return string;
    }

    @Override // miui.systemui.controlcenter.customize.CustomizeAdapter
    public String getNotAddedTitle() {
        String string = this.context.getResources().getString(R.string.controls_edit_not_added_title);
        kotlin.jvm.internal.n.f(string, "getString(...)");
        return string;
    }

    @Override // miui.systemui.controlcenter.customize.CustomizeAdapter
    public String getSave() {
        String string = this.context.getResources().getString(R.string.controls_edit_done_button);
        kotlin.jvm.internal.n.f(string, "getString(...)");
        return string;
    }

    @Override // miui.systemui.controlcenter.customize.CustomizeAdapter
    public void hide() {
        this.hideCustomize.invoke();
    }

    @Override // miui.systemui.controlcenter.customize.CustomizeAdapter
    public void onHideFinish() {
        this.editControlsModelController.destroy();
    }

    @Override // miui.systemui.controlcenter.customize.CustomizeAdapter
    public void onHideStart() {
        this.editControlsModelController.saveControls();
    }

    @Override // miui.systemui.controlcenter.customize.CustomizeAdapter
    public void onShowFinish() {
    }

    @Override // miui.systemui.controlcenter.customize.CustomizeAdapter
    public void onShowStart() {
        EditControlsModelController.init$default(this.editControlsModelController, null, 1, null);
        this.editControlsModelController.loadAllControls(new AnonymousClass1(this));
    }

    @Override // miui.systemui.controlcenter.customize.CustomizeAdapter
    public void show() {
    }

    @Override // miui.systemui.controlcenter.customize.CustomizeAdapter
    public ControlAdapter getAddedAdapter() {
        return this.addedAdapter;
    }

    @Override // miui.systemui.controlcenter.customize.CustomizeAdapter
    public ControlAdapter getNotAddedAdapter() {
        return this.notAddedAdapter;
    }

    @Override // miui.systemui.controlcenter.customize.CustomizeAdapter
    public void onShowStart(View titleContainer) {
        kotlin.jvm.internal.n.g(titleContainer, "titleContainer");
        this.editControlsModelController.init(titleContainer);
        this.editControlsModelController.loadAllControls(new AnonymousClass2(this));
    }
}
