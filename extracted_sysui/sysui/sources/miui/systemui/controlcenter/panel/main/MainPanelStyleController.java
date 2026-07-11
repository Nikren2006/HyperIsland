package miui.systemui.controlcenter.panel.main;

import android.content.Context;
import android.util.Log;
import androidx.annotation.MainThread;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.dagger.qualifiers.Qualifiers;
import miui.systemui.controlcenter.panel.main.MainPanelController;
import miui.systemui.controlcenter.utils.ControlCenterViewController;
import miui.systemui.controlcenter.windowview.ControlCenterWindowViewImpl;
import miui.systemui.dagger.qualifiers.SystemUI;
import miui.systemui.util.CommonUtils;
import miuix.os.DeviceHelper;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class MainPanelStyleController extends ControlCenterViewController<ControlCenterWindowViewImpl> {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "MainPanelStyleController";
    private MainPanelController.Style _style;
    private final E0.a distributor;
    private final boolean foldDevice;
    private final ArrayList<OnStyleChangedListener> listeners;
    private final E0.a mainPanelController;
    private final Context sysUIContext;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public interface OnStyleChangedListener {
        void onStyleChanged();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainPanelStyleController(@Qualifiers.WindowView ControlCenterWindowViewImpl windowView, @SystemUI Context sysUIContext, E0.a distributor, E0.a mainPanelController) {
        super(windowView);
        n.g(windowView, "windowView");
        n.g(sysUIContext, "sysUIContext");
        n.g(distributor, "distributor");
        n.g(mainPanelController, "mainPanelController");
        this.sysUIContext = sysUIContext;
        this.distributor = distributor;
        this.mainPanelController = mainPanelController;
        this.listeners = new ArrayList<>();
        this._style = MainPanelController.Style.VERTICAL;
        this.foldDevice = DeviceHelper.detectType(getContext()) == 4 || CommonUtils.isFlipDevice();
    }

    private final void set_style(MainPanelController.Style style) {
        if (this._style == style) {
            return;
        }
        this._style = style;
        Log.i(TAG, "main panel style changed to " + style);
        ((MainPanelController) this.mainPanelController.get()).updateResources();
        Object obj = this.distributor.get();
        n.f(obj, "get(...)");
        MainPanelContentDistributor.distributePanels$default((MainPanelContentDistributor) obj, false, 1, null);
        Object obj2 = this.distributor.get();
        n.f(obj2, "get(...)");
        MainPanelContentDistributor.handleNotifyChanged$default((MainPanelContentDistributor) obj2, false, 1, null);
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((OnStyleChangedListener) it.next()).onStyleChanged();
        }
    }

    private final void updateStyle() {
        MainPanelController.Style style;
        Log.d(TAG, f1.g.e("\n                [updateStyle] context [orientation:" + getContext().getResources().getConfiguration().orientation + "]\n                [config: " + getContext().getResources().getConfiguration() + "]\n                SysUI [orientation:" + this.sysUIContext.getResources().getConfiguration().orientation + "]\n                [config: " + this.sysUIContext.getResources().getConfiguration() + "]\n                "));
        if (this.foldDevice && CommonUtils.isTinyScreen(getContext())) {
            style = MainPanelController.Style.COMPACT;
        } else if (this.foldDevice && DeviceHelper.isWideScreen(getContext())) {
            style = MainPanelController.Style.WIDE_VERTICAL;
        } else {
            CommonUtils commonUtils = CommonUtils.INSTANCE;
            style = commonUtils.getForceVertical() ? MainPanelController.Style.WIDE_VERTICAL : commonUtils.getHorizontal(getContext()) ? MainPanelController.Style.HORIZONTAL : MainPanelController.Style.VERTICAL;
        }
        set_style(style);
    }

    @MainThread
    public final void addOnStyleChangedListener(OnStyleChangedListener listener) {
        n.g(listener, "listener");
        this.listeners.add(listener);
    }

    public final MainPanelController.Style getStyle() {
        return this._style;
    }

    @Override // miui.systemui.controlcenter.utils.ControlCenterViewController, miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        Log.i(TAG, "on config changed " + this.foldDevice + " " + CommonUtils.isTinyScreen(getContext()));
        updateStyle();
    }

    @Override // miui.systemui.util.ViewController
    public void onCreate() {
        updateStyle();
    }

    @MainThread
    public final void removeOnStyleChangedListener(OnStyleChangedListener listener) {
        n.g(listener, "listener");
        this.listeners.remove(listener);
    }
}
