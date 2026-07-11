package miui.systemui.controlcenter.utils;

import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import com.android.systemui.plugins.miui.dump.Dumpable;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.ConfigUtils;
import miui.systemui.util.ViewController;

/* JADX INFO: loaded from: classes.dex */
public abstract class ControlCenterViewController<T extends View> extends ViewController<T> implements ConfigUtils.OnConfigChangeListener, Dumpable {
    private final Collection<ControlCenterViewController<?>> childControllers;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlCenterViewController(T view) {
        super(view);
        n.g(view, "view");
    }

    public final void dispatchApplyWindowInsets(WindowInsets insets) {
        n.g(insets, "insets");
        onApplyWindowInsets(insets);
        Collection<ControlCenterViewController<?>> childControllers = getChildControllers();
        if (childControllers != null) {
            Iterator<T> it = childControllers.iterator();
            while (it.hasNext()) {
                ControlCenterViewController controlCenterViewController = (ControlCenterViewController) it.next();
                if (controlCenterViewController != null) {
                    controlCenterViewController.dispatchApplyWindowInsets(insets);
                }
            }
        }
    }

    public final void dispatchConfigurationChanged(int i2) {
        if (getInited()) {
            onConfigurationChanged(i2);
            Collection<ControlCenterViewController<?>> childControllers = getChildControllers();
            if (childControllers != null) {
                Iterator<T> it = childControllers.iterator();
                while (it.hasNext()) {
                    ControlCenterViewController controlCenterViewController = (ControlCenterViewController) it.next();
                    if (controlCenterViewController != null) {
                        controlCenterViewController.dispatchConfigurationChanged(i2);
                    }
                }
            }
        }
    }

    public final void dispatchCreate() {
        if (getInited()) {
            return;
        }
        super.init();
        Collection<ControlCenterViewController<?>> childControllers = getChildControllers();
        if (childControllers != null) {
            Iterator<T> it = childControllers.iterator();
            while (it.hasNext()) {
                ControlCenterViewController controlCenterViewController = (ControlCenterViewController) it.next();
                if (controlCenterViewController != null) {
                    controlCenterViewController.dispatchCreate();
                }
            }
        }
        onChildrenCreated();
    }

    public final void dispatchDestroy() {
        if (getInited()) {
            getView().removeOnAttachStateChangeListener(getOnAttachStateListener());
            onDestroy();
            Collection<ControlCenterViewController<?>> childControllers = getChildControllers();
            if (childControllers != null) {
                Iterator<T> it = childControllers.iterator();
                while (it.hasNext()) {
                    ControlCenterViewController controlCenterViewController = (ControlCenterViewController) it.next();
                    if (controlCenterViewController != null) {
                        controlCenterViewController.dispatchDestroy();
                    }
                }
            }
            setInited(false);
        }
    }

    public final void dispatchDump(PrintWriter pw, String[] args) {
        n.g(pw, "pw");
        n.g(args, "args");
        dump(pw, args);
        Collection<ControlCenterViewController<?>> childControllers = getChildControllers();
        if (childControllers != null) {
            Iterator<T> it = childControllers.iterator();
            while (it.hasNext()) {
                ControlCenterViewController controlCenterViewController = (ControlCenterViewController) it.next();
                if (controlCenterViewController != null) {
                    controlCenterViewController.dispatchDump(pw, args);
                }
            }
        }
    }

    public final void dispatchPause() {
        if (getInited()) {
            onPause();
            Collection<ControlCenterViewController<?>> childControllers = getChildControllers();
            if (childControllers != null) {
                Iterator<T> it = childControllers.iterator();
                while (it.hasNext()) {
                    ControlCenterViewController controlCenterViewController = (ControlCenterViewController) it.next();
                    if (controlCenterViewController != null) {
                        controlCenterViewController.dispatchPause();
                    }
                }
            }
        }
    }

    public final void dispatchResume() {
        if (getInited()) {
            onResume();
            Collection<ControlCenterViewController<?>> childControllers = getChildControllers();
            if (childControllers != null) {
                Iterator<T> it = childControllers.iterator();
                while (it.hasNext()) {
                    ControlCenterViewController controlCenterViewController = (ControlCenterViewController) it.next();
                    if (controlCenterViewController != null) {
                        controlCenterViewController.dispatchResume();
                    }
                }
            }
        }
    }

    public final void dispatchStart() {
        if (getInited()) {
            Log.d("ControlCenterViewController", "dispatching start " + getClass().getSimpleName());
            if (!getStartedOnce()) {
                setStartedOnce(true);
                onStartOnce();
            }
            onStart();
            Collection<ControlCenterViewController<?>> childControllers = getChildControllers();
            if (childControllers != null) {
                Iterator<T> it = childControllers.iterator();
                while (it.hasNext()) {
                    ControlCenterViewController controlCenterViewController = (ControlCenterViewController) it.next();
                    if (controlCenterViewController != null) {
                        controlCenterViewController.dispatchStart();
                    }
                }
            }
        }
    }

    public final void dispatchStop() {
        if (getInited()) {
            onStop();
            Collection<ControlCenterViewController<?>> childControllers = getChildControllers();
            if (childControllers != null) {
                Iterator<T> it = childControllers.iterator();
                while (it.hasNext()) {
                    ControlCenterViewController controlCenterViewController = (ControlCenterViewController) it.next();
                    if (controlCenterViewController != null) {
                        controlCenterViewController.dispatchStop();
                    }
                }
            }
        }
    }

    public final void dispatchSuperPowerModeChanged(boolean z2) {
        if (getInited()) {
            onSuperPowerModeChanged(z2);
            Collection<ControlCenterViewController<?>> childControllers = getChildControllers();
            if (childControllers != null) {
                Iterator<T> it = childControllers.iterator();
                while (it.hasNext()) {
                    ControlCenterViewController controlCenterViewController = (ControlCenterViewController) it.next();
                    if (controlCenterViewController != null) {
                        controlCenterViewController.dispatchSuperPowerModeChanged(z2);
                    }
                }
            }
        }
    }

    public final void dispatchUserSwitched(int i2) {
        if (getInited()) {
            onUserSwitched(i2);
            Collection<ControlCenterViewController<?>> childControllers = getChildControllers();
            if (childControllers != null) {
                Iterator<T> it = childControllers.iterator();
                while (it.hasNext()) {
                    ControlCenterViewController controlCenterViewController = (ControlCenterViewController) it.next();
                    if (controlCenterViewController != null) {
                        controlCenterViewController.dispatchUserSwitched(i2);
                    }
                }
            }
        }
    }

    public void dump(PrintWriter pw, String[] args) {
        n.g(pw, "pw");
        n.g(args, "args");
    }

    public Collection<ControlCenterViewController<?>> getChildControllers() {
        return this.childControllers;
    }

    @Override // miui.systemui.util.ViewController
    public void init() {
        dispatchCreate();
    }

    public void onApplyWindowInsets(WindowInsets insets) {
        n.g(insets, "insets");
    }

    public void onChildrenCreated() {
    }

    public void onConfigurationChanged(int i2) {
    }

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public void onStartOnce() {
    }

    public void onStop() {
    }

    public void onSuperPowerModeChanged(boolean z2) {
    }

    public void onUserSwitched(int i2) {
    }
}
