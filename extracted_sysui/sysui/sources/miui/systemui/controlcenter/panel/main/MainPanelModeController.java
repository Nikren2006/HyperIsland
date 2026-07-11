package miui.systemui.controlcenter.panel.main;

import android.util.Log;
import androidx.annotation.MainThread;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import j1.I;
import j1.K;
import j1.u;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterExpandController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class MainPanelModeController extends ControlCenterViewController<ControlCenterWindowViewImpl> {
    public static final int CHANGE_MODE_LEVEL_FORCE = 2;
    public static final int CHANGE_MODE_LEVEL_NORMAL = 1;
    public static final int CHANGE_MODE_LEVEL_WEAK = 0;
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "MainPanelModeController";
    private final u _mainPanelIsEditMode;
    private MainPanelController.Mode _mode;
    private int changeModeLevel;
    private final MainPanelContentDistributor distributor;
    private final ControlCenterExpandController expandController;
    private final RecyclerView leftMainPanel;
    private final Lifecycle lifecycle;
    private final ArrayList<OnModeChangedListener> listeners;
    private final I mainPanelIsEditMode;
    private MainPanelController.Mode pendingMode;
    private final RecyclerView rightMainPanel;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface OnModeChangedListener {
        void onModeChanged();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainPanelModeController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, @Qualifiers.RightMainPanel RecyclerView rightMainPanel, @Qualifiers.LeftMainPanel RecyclerView leftMainPanel, MainPanelContentDistributor distributor, @Qualifiers.ControlCenter Lifecycle lifecycle, ControlCenterExpandController expandController) {
        super(windowView);
        n.g(windowView, "windowView");
        n.g(rightMainPanel, "rightMainPanel");
        n.g(leftMainPanel, "leftMainPanel");
        n.g(distributor, "distributor");
        n.g(lifecycle, "lifecycle");
        n.g(expandController, "expandController");
        this.rightMainPanel = rightMainPanel;
        this.leftMainPanel = leftMainPanel;
        this.distributor = distributor;
        this.lifecycle = lifecycle;
        this.expandController = expandController;
        u uVarA = K.a(Boolean.FALSE);
        this._mainPanelIsEditMode = uVarA;
        this.mainPanelIsEditMode = uVarA;
        this._mode = MainPanelController.Mode.NORMAL;
        this.listeners = new ArrayList<>();
        this.changeModeLevel = 1;
    }

    private final void applyPendingMode() {
        if (!getDisableSwitch()) {
            if (this.lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                MainPanelController.Mode mode = this.pendingMode;
                if (mode == null) {
                    return;
                } else {
                    set_mode(mode);
                }
            }
            this.pendingMode = null;
            return;
        }
        Log.e(TAG, "drop " + this.pendingMode + " " + this.lifecycle.getCurrentState() + " " + this.expandController.getAppearance());
        this.pendingMode = null;
    }

    public static /* synthetic */ void changeMode$default(MainPanelModeController mainPanelModeController, MainPanelController.Mode mode, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            z2 = false;
        }
        mainPanelModeController.changeMode(mode, i2, z2);
    }

    private final void checkByAnimator(final boolean z2) {
        RecyclerView.ItemAnimator itemAnimator = this.leftMainPanel.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.isRunning(new RecyclerView.ItemAnimator.ItemAnimatorFinishedListener() { // from class: miui.systemui.controlcenter.panel.main.k
                @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator.ItemAnimatorFinishedListener
                public final void onAnimationsFinished() {
                    MainPanelModeController.checkByAnimator$lambda$3(this.f5410a, z2);
                }
            });
        } else {
            checkByAnimator$right(this, z2);
        }
    }

    public static /* synthetic */ void checkByAnimator$default(MainPanelModeController mainPanelModeController, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        mainPanelModeController.checkByAnimator(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkByAnimator$lambda$3(MainPanelModeController this$0, boolean z2) {
        n.g(this$0, "this$0");
        checkByAnimator$right(this$0, z2);
    }

    private static final void checkByAnimator$right(final MainPanelModeController mainPanelModeController, final boolean z2) {
        RecyclerView.ItemAnimator itemAnimator = mainPanelModeController.rightMainPanel.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.isRunning(new RecyclerView.ItemAnimator.ItemAnimatorFinishedListener() { // from class: miui.systemui.controlcenter.panel.main.j
                @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator.ItemAnimatorFinishedListener
                public final void onAnimationsFinished() {
                    MainPanelModeController.checkByAnimator$right$lambda$1(z2, mainPanelModeController);
                }
            });
        } else {
            mainPanelModeController.applyPendingMode();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkByAnimator$right$lambda$1(boolean z2, MainPanelModeController this$0) {
        n.g(this$0, "this$0");
        if (!z2 || this$0.rightMainPanel.getScrollState() != 1) {
            this$0.applyPendingMode();
        } else {
            Log.w(TAG, "Unable to exit edit mode");
            this$0.pendingMode = null;
        }
    }

    private final boolean getDisableSwitch() {
        return this.pendingMode == MainPanelController.Mode.EDIT && (this.lifecycle.getCurrentState().compareTo(Lifecycle.State.RESUMED) < 0 || !this.expandController.getAppearance());
    }

    private final void set_mode(MainPanelController.Mode mode) {
        Log.i(TAG, "mode changed: " + this._mode + " -> " + mode);
        if (this._mode == mode) {
            return;
        }
        this._mode = mode;
        MainPanelContentDistributor.distributePanels$default(this.distributor, false, 1, null);
        MainPanelContentDistributor.handleNotifyChanged$default(this.distributor, false, 1, null);
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((OnModeChangedListener) it.next()).onModeChanged();
        }
        this._mainPanelIsEditMode.setValue(Boolean.valueOf(mode == MainPanelController.Mode.EDIT));
    }

    @MainThread
    public final void addOnModeChangedListener(OnModeChangedListener listener) {
        n.g(listener, "listener");
        this.listeners.add(listener);
    }

    public final void changeMode(MainPanelController.Mode mode, int i2, boolean z2) {
        n.g(mode, "mode");
        this.changeModeLevel = i2;
        if (i2 == 0) {
            RecyclerView.ItemAnimator itemAnimator = this.rightMainPanel.getItemAnimator();
            if (itemAnimator == null || !itemAnimator.isRunning()) {
                RecyclerView.ItemAnimator itemAnimator2 = this.leftMainPanel.getItemAnimator();
                if ((itemAnimator2 == null || !itemAnimator2.isRunning()) && this.lifecycle.getCurrentState() == Lifecycle.State.RESUMED) {
                    this.pendingMode = null;
                    set_mode(mode);
                    return;
                }
                return;
            }
            return;
        }
        if (i2 != 1) {
            if (i2 != 2) {
                return;
            }
            this.pendingMode = null;
            set_mode(mode);
            return;
        }
        MainPanelController.Mode mode2 = this.pendingMode;
        if (mode2 == null) {
            mode2 = this._mode;
        }
        if (mode == mode2) {
            return;
        }
        this.pendingMode = mode;
        if (this.lifecycle.getCurrentState() == Lifecycle.State.RESUMED) {
            checkByAnimator(z2);
        }
    }

    public final int getChangeModeLevel() {
        return this.changeModeLevel;
    }

    public final boolean getInEditMode() {
        return getMode() == MainPanelController.Mode.EDIT;
    }

    public final boolean getInNormalMode() {
        return getMode() == MainPanelController.Mode.NORMAL;
    }

    public final boolean getInPendingEditMode() {
        return this.distributor.getQsListController().getInPendingEditMode();
    }

    public final I getMainPanelIsEditMode() {
        return this.mainPanelIsEditMode;
    }

    public final MainPanelController.Mode getMode() {
        return this._mode;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onResume() {
        checkByAnimator$default(this, false, 1, null);
    }

    @MainThread
    public final void removeOnModeChangedListener(OnModeChangedListener listener) {
        n.g(listener, "listener");
        this.listeners.remove(listener);
    }
}
