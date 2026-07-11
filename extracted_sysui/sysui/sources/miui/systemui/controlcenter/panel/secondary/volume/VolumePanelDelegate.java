package miui.systemui.controlcenter.panel.secondary.volume;

import I0.m;
import android.content.Context;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import com.android.systemui.miui.volume.MiuiRingerModeLayout;
import com.android.systemui.miui.volume.VolumeColumn;
import com.android.systemui.miui.volume.VolumePanelView;
import com.android.systemui.miui.volume.VolumePanelViewController;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.databinding.VolumePanelBinding;
import miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase;
import miui.systemui.controlcenter.panel.secondary.VolumePanelParams;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.util.CommonUtils;
import miui.systemui.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class VolumePanelDelegate extends SecondaryPanelDelegateBase<VolumePanelParams> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "VolumePanelContentController";
    private final VolumePanelBinding binding;
    private View content;
    private View contentBg;
    private View contentColumns;
    private View innerContent;
    private View ringModeContent;
    private View ringModeLayout;
    private final ControlCenterSecondaryBinding secondaryBinding;
    private final StatusBarStateController statusBarStateController;
    private final Context sysUIContext;
    private final VolumeDialogController volumeDialogController;
    private VolumePanelView volumePanelView;
    private VolumePanelViewController volumePanelViewController;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VolumePanelDelegate(@SystemUI Context sysUIContext, ControlCenterSecondaryBinding secondaryBinding, VolumePanelBinding binding, VolumeDialogController volumeDialogController, StatusBarStateController statusBarStateController) {
        super(secondaryBinding);
        n.g(sysUIContext, "sysUIContext");
        n.g(secondaryBinding, "secondaryBinding");
        n.g(binding, "binding");
        n.g(volumeDialogController, "volumeDialogController");
        n.g(statusBarStateController, "statusBarStateController");
        this.sysUIContext = sysUIContext;
        this.secondaryBinding = secondaryBinding;
        this.binding = binding;
        this.volumeDialogController = volumeDialogController;
        this.statusBarStateController = statusBarStateController;
    }

    private final void initView() {
        VolumePanelView volumePanelView;
        VolumePanelViewController volumePanelViewController;
        if (this.volumePanelView == null || (volumePanelViewController = this.volumePanelViewController) == null || volumePanelViewController.mNeedReInit) {
            initVolumePanelViewController();
            VolumePanelViewController volumePanelViewController2 = this.volumePanelViewController;
            if (volumePanelViewController2 != null && volumePanelViewController2.mNeedReInit && volumePanelViewController2 != null) {
                volumePanelViewController2.reInit();
            }
            VolumePanelViewController volumePanelViewController3 = this.volumePanelViewController;
            if (volumePanelViewController3 == null || (volumePanelView = volumePanelViewController3.mVolumePanelView) == null) {
                return;
            }
            this.volumePanelView = volumePanelView;
            if (volumePanelViewController3 != null) {
                this.content = volumePanelViewController3.getVolumeContent();
                this.contentBg = volumePanelViewController3.getVolumeContentBg();
                this.innerContent = volumePanelViewController3.getVolumeInnerContent();
                this.contentColumns = volumePanelViewController3.getVolumeContentColumns();
                this.ringModeLayout = volumePanelViewController3.getVolumeRingerModeLayout();
                this.ringModeContent = volumePanelViewController3.getVolumeRingerModeContent();
            }
            FrameLayout frameLayout = this.binding.volumeContainer;
            frameLayout.removeAllViews();
            frameLayout.addView(this.volumePanelView);
            View view = this.contentBg;
            if (view != null) {
                CommonUtils.removeAccessibilityClick$default(view, false, 2, null);
            }
        }
    }

    private final void initVolumePanelViewController() {
        if (this.volumePanelViewController == null) {
            VolumePanelViewController volumePanelViewController = new VolumePanelViewController(getContext(), this.sysUIContext, this.volumeDialogController, this.statusBarStateController);
            volumePanelViewController.setControlCenterPanel(true);
            volumePanelViewController.initController(false);
            this.volumePanelViewController = volumePanelViewController;
        }
    }

    private final void panelIdleInit(final boolean z2) {
        if (z2 && this.volumePanelViewController == null) {
            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: miui.systemui.controlcenter.panel.secondary.volume.a
                @Override // android.os.MessageQueue.IdleHandler
                public final boolean queueIdle() {
                    return VolumePanelDelegate.panelIdleInit$lambda$0(z2, this);
                }
            });
        }
    }

    public static /* synthetic */ void panelIdleInit$default(VolumePanelDelegate volumePanelDelegate, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = true;
        }
        volumePanelDelegate.panelIdleInit(z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean panelIdleInit$lambda$0(boolean z2, VolumePanelDelegate this$0) {
        n.g(this$0, "this$0");
        Log.d(TAG, "panelIdleInit: " + z2);
        this$0.initVolumePanelViewController();
        return false;
    }

    public final View getContent() {
        return this.content;
    }

    public final View getContentBg() {
        return this.contentBg;
    }

    public final View getContentColumns() {
        return this.contentColumns;
    }

    public final View getInnerContent() {
        return this.innerContent;
    }

    public final Float getOriginalVolumeFromPanel() {
        VolumePanelViewController volumePanelViewController = this.volumePanelViewController;
        if (volumePanelViewController != null) {
            return Float.valueOf(volumePanelViewController.getPanelOriginalVolume());
        }
        return null;
    }

    public final View getRingModeContent() {
        return this.ringModeContent;
    }

    public final View getRingModeLayout() {
        return this.ringModeLayout;
    }

    public final List<VolumeColumn> getVisibleColumns() {
        VolumePanelViewController volumePanelViewController = this.volumePanelViewController;
        if (volumePanelViewController == null) {
            return m.h();
        }
        List<VolumeColumn> columns = volumePanelViewController.getColumns();
        n.f(columns, "getColumns(...)");
        ArrayList arrayList = new ArrayList();
        for (Object obj : columns) {
            if (((VolumeColumn) obj).getView().getVisibility() == 0) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        super.onCreate();
        panelIdleInit$default(this, false, 1, null);
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController
    public void onDestroy() {
        super.onDestroy();
        VolumePanelViewController volumePanelViewController = this.volumePanelViewController;
        if (volumePanelViewController != null) {
            volumePanelViewController.destroy();
        }
        this.volumePanelViewController = null;
        this.volumePanelView = null;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void onHidden() {
        super.onHidden();
        VolumePanelViewController volumePanelViewController = this.volumePanelViewController;
        if (volumePanelViewController != null) {
            volumePanelViewController.dismissH(9);
            View view = this.ringModeLayout;
            MiuiRingerModeLayout miuiRingerModeLayout = view instanceof MiuiRingerModeLayout ? (MiuiRingerModeLayout) view : null;
            if (miuiRingerModeLayout != null) {
                miuiRingerModeLayout.scheduleTimerUpdateH(false);
            }
        }
    }

    public final void performHapticFeedback(boolean z2) {
        VolumePanelViewController volumePanelViewController = this.volumePanelViewController;
        if (volumePanelViewController != null) {
            volumePanelViewController.performHapticFeedBack(z2);
        }
    }

    public final void performKeyAnim(KeyEvent event) {
        n.g(event, "event");
        VolumePanelViewController volumePanelViewController = this.volumePanelViewController;
        if (volumePanelViewController != null) {
            volumePanelViewController.performKeyAnim(event);
        }
    }

    public final void setContent(View view) {
        this.content = view;
    }

    public final void setContentBg(View view) {
        this.contentBg = view;
    }

    public final void setContentColumns(View view) {
        this.contentColumns = view;
    }

    public final void setInnerContent(View view) {
        this.innerContent = view;
    }

    public final void setRingModeContent(View view) {
        this.ringModeContent = view;
    }

    public final void setRingModeLayout(View view) {
        this.ringModeLayout = view;
    }

    @Override // miui.systemui.controlcenter.panel.secondary.SecondaryPanelDelegateBase
    public void prepareShow(VolumePanelParams volumePanelParams) {
        super.prepareShow(volumePanelParams);
        initView();
        VolumePanelViewController volumePanelViewController = this.volumePanelViewController;
        if (volumePanelViewController != null) {
            volumePanelViewController.prepareShow(volumePanelParams != null ? volumePanelParams.getOriginalVolume() : 0.0f);
            volumePanelViewController.updateExpandedH(true, false, true);
            volumePanelViewController.showH(4);
        }
    }
}
