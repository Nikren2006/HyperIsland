package miui.systemui.devicecontrols;

import F0.e;
import G0.a;
import android.content.Context;
import miui.systemui.devicecontrols.dagger.DeviceControlsComponent;

/* JADX INFO: loaded from: classes3.dex */
public final class DeviceControlsPresenterImpl_Factory implements e {
    private final a contextProvider;
    private final a deviceControlsComponentFactoryProvider;

    public DeviceControlsPresenterImpl_Factory(a aVar, a aVar2) {
        this.deviceControlsComponentFactoryProvider = aVar;
        this.contextProvider = aVar2;
    }

    public static DeviceControlsPresenterImpl_Factory create(a aVar, a aVar2) {
        return new DeviceControlsPresenterImpl_Factory(aVar, aVar2);
    }

    public static DeviceControlsPresenterImpl newInstance(DeviceControlsComponent.Factory factory, Context context) {
        return new DeviceControlsPresenterImpl(factory, context);
    }

    @Override // G0.a
    public DeviceControlsPresenterImpl get() {
        return newInstance((DeviceControlsComponent.Factory) this.deviceControlsComponentFactoryProvider.get(), (Context) this.contextProvider.get());
    }
}
