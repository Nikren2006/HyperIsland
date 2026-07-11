package miui.systemui.devicecontrols;

import android.content.ComponentName;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.View;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.n;
import miui.systemui.dagger.qualifiers.Plugin;
import miui.systemui.devicecontrols.dagger.DeviceControlsComponent;
import miui.systemui.util.ThemeUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceControlsPresenterImpl implements DeviceControlsPresenter {
    private final Context context;
    private final DeviceControlsComponent.Factory deviceControlsComponentFactory;
    private Context deviceControlsContext;
    private DeviceControlsModel deviceControlsModel;
    private boolean isGrayBlurDim;

    public DeviceControlsPresenterImpl(DeviceControlsComponent.Factory deviceControlsComponentFactory, @Plugin Context context) {
        n.g(deviceControlsComponentFactory, "deviceControlsComponentFactory");
        n.g(context, "context");
        this.deviceControlsComponentFactory = deviceControlsComponentFactory;
        this.context = context;
    }

    private final int getThemeRes() {
        return (this.isGrayBlurDim || !ThemeUtils.INSTANCE.getDefaultPluginTheme()) ? R.style.DeviceControl : R.style.DeviceControl_Legacy;
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsPresenter
    public void addDCEntryInfoCallback(Consumer<DCEntryInfo> onServiceUpdate) {
        n.g(onServiceUpdate, "onServiceUpdate");
        DeviceControlsModel deviceControlsModel = this.deviceControlsModel;
        if (deviceControlsModel != null) {
            deviceControlsModel.addDCEntryInfoCallback(onServiceUpdate);
        }
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsPresenter
    public void create(boolean z2) {
        if (this.deviceControlsModel == null) {
            this.isGrayBlurDim = z2;
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.context, getThemeRes());
            this.deviceControlsContext = contextThemeWrapper;
            DeviceControlsComponent.Factory factory = this.deviceControlsComponentFactory;
            n.d(contextThemeWrapper);
            DeviceControlsModel deviceControlsModel = factory.create(contextThemeWrapper).getDeviceControlsModel();
            deviceControlsModel.create();
            this.deviceControlsModel = deviceControlsModel;
        }
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsPresenter
    public void destroy() {
        DeviceControlsModel deviceControlsModel = this.deviceControlsModel;
        if (deviceControlsModel != null) {
            deviceControlsModel.destroy();
        }
        this.deviceControlsModel = null;
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsPresenter
    public View getOrCreateControlsView(Function0 back, Function1 edit, Function0 hideCustomize) {
        n.g(back, "back");
        n.g(edit, "edit");
        n.g(hideCustomize, "hideCustomize");
        DeviceControlsModel deviceControlsModel = this.deviceControlsModel;
        if (deviceControlsModel != null) {
            return deviceControlsModel.getOrCreateControlsView(back, edit, hideCustomize);
        }
        return null;
    }

    @Override // miui.systemui.controlcenter.ConfigUtils.OnConfigChangeListener
    public void onConfigurationChanged(int i2) {
        Context context = this.deviceControlsContext;
        if (context != null) {
            context.setTheme(getThemeRes());
        }
        DeviceControlsModel deviceControlsModel = this.deviceControlsModel;
        if (deviceControlsModel != null) {
            deviceControlsModel.onConfigurationChanged(i2);
        }
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsPresenter
    public void onDCPreHide() {
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsPresenter
    public void onDCPreHideFinished() {
        DeviceControlsModel deviceControlsModel = this.deviceControlsModel;
        if (deviceControlsModel != null) {
            deviceControlsModel.unsubscribeFavorite();
        }
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsPresenter
    public void onDCPreShow(ComponentName componentName) {
        DeviceControlsModel deviceControlsModel = this.deviceControlsModel;
        if (deviceControlsModel != null) {
            deviceControlsModel.showControlsView();
        }
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsPresenter
    public void onDCShowFinished() {
        DeviceControlsModel deviceControlsModel = this.deviceControlsModel;
        if (deviceControlsModel != null) {
            DeviceControlsModel.subscribeFavorite$default(deviceControlsModel, null, 1, null);
        }
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsPresenter
    public void removeDCEntryInfoCallback(Consumer<DCEntryInfo> onServiceUpdate) {
        n.g(onServiceUpdate, "onServiceUpdate");
        DeviceControlsModel deviceControlsModel = this.deviceControlsModel;
        if (deviceControlsModel != null) {
            deviceControlsModel.removeDCEntryInfoCallback(onServiceUpdate);
        }
    }

    @Override // miui.systemui.devicecontrols.DeviceControlsPresenter
    public void setListening(boolean z2) {
        DeviceControlsModel deviceControlsModel = this.deviceControlsModel;
        if (deviceControlsModel != null) {
            deviceControlsModel.setListening(z2);
        }
    }
}
