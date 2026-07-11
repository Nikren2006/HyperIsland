package miui.systemui.devicecontrols.controller;

import android.content.ComponentName;
import android.service.controls.Control;
import android.service.controls.actions.ControlAction;
import java.util.List;
import java.util.function.Consumer;
import miui.systemui.devicecontrols.util.UserAwareController;

/* JADX INFO: loaded from: classes3.dex */
public interface ControlsBindingController extends UserAwareController {

    public interface LoadCallback extends Consumer<List<? extends Control>> {
        void error(String str);
    }

    void action(ComponentName componentName, ControlInfo controlInfo, ControlAction controlAction);

    Runnable bindAndLoad(ComponentName componentName, LoadCallback loadCallback);

    void bindAndLoadSuggested(ComponentName componentName, LoadCallback loadCallback);

    void bindService(ComponentName componentName);

    void onComponentRemoved(ComponentName componentName);

    void subscribe(StructureInfo structureInfo);

    void unbind();

    void unsubscribe();
}
