package miui.service.controls.actions;

import android.service.controls.actions.ControlAction;
import android.service.controls.actions.ControlActionWrapper;
import com.miui.expose.utils.ConstructorHolder;

/* JADX INFO: loaded from: classes2.dex */
public class ControlActionWrapperExpose {
    private static final ConstructorHolder<ControlActionWrapper> init = new ConstructorHolder<>(ControlActionWrapper.class, ControlAction.class);

    public static ControlActionWrapper newInstance(ControlAction controlAction) {
        return init.newInstance(controlAction);
    }
}
