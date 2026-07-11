package miui.systemui.devicecontrols.management;

import H0.s;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import miui.systemui.dagger.qualifiers.Background;
import miui.systemui.dagger.qualifiers.Main;
import miui.systemui.devicecontrols.ControlInterface;
import miui.systemui.devicecontrols.CustomIconCache;
import miui.systemui.devicecontrols.R;
import miui.systemui.devicecontrols.controller.ControlsController;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControls;
import miui.systemui.devicecontrols.dagger.qualifiers.DeviceControlsScope;
import miui.systemui.devicecontrols.databinding.ControlsFavoriteSenseItemBinding;
import miui.systemui.devicecontrols.ui.ControlActionCoordinator;
import miui.systemui.devicecontrols.ui.ControlViewHolder;
import miui.systemui.devicecontrols.ui.ControlWithState;
import miui.systemui.devicecontrols.ui.EditControlViewHolder;
import miui.systemui.devicecontrols.ui.SenseControlViewHolder;
import miui.systemui.devicecontrols.util.ControlsUtils;
import miui.systemui.util.concurrency.DelayableExecutor;

/* JADX INFO: loaded from: classes3.dex */
@DeviceControlsScope
public final class ViewHolderFactory {
    public static final Companion Companion = new Companion(null);
    public static final int TYPE_CONTROL = 2;
    public static final int TYPE_DIVIDER = 3;
    public static final int TYPE_EDIT_CONTROL = 4;
    public static final int TYPE_EDIT_EMPTY_GUIDE = 6;
    public static final int TYPE_EDIT_SENSE_CONTROL = 5;
    public static final int TYPE_SENSE_CONTROL = 1;
    public static final int TYPE_TITLE = 0;
    private final ActivityStarter activityStarter;
    private final DelayableExecutor bgExecutor;
    private final Context context;
    private final ControlActionCoordinator controlActionCoordinator;
    private final E0.a controlsController;
    private final EditControlsModelController editControlsModelController;
    private final CustomIconCache iconCache;
    private final StatusBarStateController statusBarStateController;
    private final DelayableExecutor uiExecutor;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.management.ViewHolderFactory$createViewHolder$1, reason: invalid class name */
    public static final class AnonymousClass1 extends kotlin.jvm.internal.o implements Function2 {
        public AnonymousClass1() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((String) obj, ((Boolean) obj2).booleanValue());
            return s.f314a;
        }

        public final void invoke(String id, boolean z2) {
            kotlin.jvm.internal.n.g(id, "id");
            ViewHolderFactory.this.getEditControlsModelController().changeFavoriteStatus(id, z2);
        }
    }

    /* JADX INFO: renamed from: miui.systemui.devicecontrols.management.ViewHolderFactory$createViewHolder$2, reason: invalid class name */
    public static final class AnonymousClass2 extends kotlin.jvm.internal.o implements Function2 {
        public AnonymousClass2() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((String) obj, ((Boolean) obj2).booleanValue());
            return s.f314a;
        }

        public final void invoke(String id, boolean z2) {
            kotlin.jvm.internal.n.g(id, "id");
            ViewHolderFactory.this.getEditControlsModelController().changeFavoriteStatus(id, z2);
        }
    }

    public ViewHolderFactory(@DeviceControls Context context, E0.a controlsController, @Main DelayableExecutor uiExecutor, @Background DelayableExecutor bgExecutor, ActivityStarter activityStarter, CustomIconCache iconCache, ControlActionCoordinator controlActionCoordinator, EditControlsModelController editControlsModelController, StatusBarStateController statusBarStateController) {
        kotlin.jvm.internal.n.g(context, "context");
        kotlin.jvm.internal.n.g(controlsController, "controlsController");
        kotlin.jvm.internal.n.g(uiExecutor, "uiExecutor");
        kotlin.jvm.internal.n.g(bgExecutor, "bgExecutor");
        kotlin.jvm.internal.n.g(activityStarter, "activityStarter");
        kotlin.jvm.internal.n.g(iconCache, "iconCache");
        kotlin.jvm.internal.n.g(controlActionCoordinator, "controlActionCoordinator");
        kotlin.jvm.internal.n.g(editControlsModelController, "editControlsModelController");
        kotlin.jvm.internal.n.g(statusBarStateController, "statusBarStateController");
        this.context = context;
        this.controlsController = controlsController;
        this.uiExecutor = uiExecutor;
        this.bgExecutor = bgExecutor;
        this.activityStarter = activityStarter;
        this.iconCache = iconCache;
        this.controlActionCoordinator = controlActionCoordinator;
        this.editControlsModelController = editControlsModelController;
        this.statusBarStateController = statusBarStateController;
    }

    public final BaseHolder createViewHolder(ViewGroup parent, int i2, ControlsModel controlsModel) {
        kotlin.jvm.internal.n.g(parent, "parent");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.context);
        switch (i2) {
            case 0:
                View viewInflate = layoutInflaterFrom.inflate(R.layout.controls_home_title, parent, false);
                kotlin.jvm.internal.n.f(viewInflate, "inflate(...)");
                return new TitleHolder(viewInflate);
            case 1:
                ControlsFavoriteSenseItemBinding controlsFavoriteSenseItemBindingInflate = ControlsFavoriteSenseItemBinding.inflate(layoutInflaterFrom, parent, false);
                kotlin.jvm.internal.n.f(controlsFavoriteSenseItemBindingInflate, "inflate(...)");
                Object obj = this.controlsController.get();
                kotlin.jvm.internal.n.f(obj, "get(...)");
                return new SenseControlViewHolder(controlsFavoriteSenseItemBindingInflate, (ControlsController) obj, this.uiExecutor, this.bgExecutor, this.controlActionCoordinator, this.iconCache);
            case 2:
                View viewInflate2 = layoutInflaterFrom.inflate(R.layout.controls_favorite_item, parent, false);
                kotlin.jvm.internal.n.e(viewInflate2, "null cannot be cast to non-null type android.view.ViewGroup");
                ViewGroup viewGroup = (ViewGroup) viewInflate2;
                Object obj2 = this.controlsController.get();
                kotlin.jvm.internal.n.f(obj2, "get(...)");
                return new ControlViewHolder(viewGroup, (ControlsController) obj2, this.uiExecutor, this.bgExecutor, this.controlActionCoordinator, this.iconCache);
            case 3:
                View viewInflate3 = layoutInflaterFrom.inflate(R.layout.controls_horizontal_divider_with_empty, parent, false);
                kotlin.jvm.internal.n.f(viewInflate3, "inflate(...)");
                return new DividerHolder(viewInflate3);
            case 4:
                Context context = this.context;
                View viewInflate4 = layoutInflaterFrom.inflate(R.layout.controls_edit_item, parent, false);
                kotlin.jvm.internal.n.f(viewInflate4, "inflate(...)");
                return new EditControlViewHolder(context, viewInflate4, new AnonymousClass1());
            case 5:
                Context context2 = this.context;
                View viewInflate5 = layoutInflaterFrom.inflate(R.layout.controls_edit_sense_item, parent, false);
                kotlin.jvm.internal.n.f(viewInflate5, "inflate(...)");
                return new EditControlViewHolder(context2, viewInflate5, new AnonymousClass2());
            case 6:
                View viewInflate6 = layoutInflaterFrom.inflate(R.layout.controls_edit_empty_guide_layout, parent, false);
                kotlin.jvm.internal.n.f(viewInflate6, "inflate(...)");
                return new EditEmptyGuideHolder(viewInflate6, this.activityStarter, this.statusBarStateController);
            default:
                throw new IllegalStateException("Wrong viewType: " + i2);
        }
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

    public final EditControlsModelController getEditControlsModelController() {
        return this.editControlsModelController;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int getItemViewType(ElementWrapper wrapper) {
        kotlin.jvm.internal.n.g(wrapper, "wrapper");
        if (wrapper instanceof TitleWrapper) {
            return 0;
        }
        if (wrapper instanceof DividerWrapper) {
            return 3;
        }
        return wrapper instanceof ControlWithState ? ControlsUtils.INSTANCE.checkSenseType(((ControlWithState) wrapper).getCi().getControlId()) ? 1 : 2 : wrapper instanceof ControlInterface ? ControlsUtils.INSTANCE.checkSenseType(((ControlInterface) wrapper).getControlId()) ? 5 : 4 : wrapper instanceof EditEmptyGuideWrapper ? 6 : -1;
    }

    public final int getSpanSize(int i2, boolean z2) {
        switch (i2) {
            case 0:
                if (!z2) {
                    return 2;
                }
                break;
            case 1:
            case 2:
            case 4:
            case 5:
            default:
                return 1;
            case 3:
                if (!z2) {
                    return 2;
                }
                break;
            case 6:
                if (!z2) {
                    return 2;
                }
                break;
        }
        return 4;
    }

    public final DelayableExecutor getUiExecutor() {
        return this.uiExecutor;
    }
}
