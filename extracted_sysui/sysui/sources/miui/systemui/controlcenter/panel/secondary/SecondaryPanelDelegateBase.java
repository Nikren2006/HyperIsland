package miui.systemui.controlcenter.panel.secondary;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.databinding.ControlCenterSecondaryBinding;
import miui.systemui.controlcenter.panel.secondary.SecondaryParams;
import miui.systemui.controlcenter.utils.ControlCenterViewController;

/* JADX INFO: loaded from: classes.dex */
public abstract class SecondaryPanelDelegateBase<P extends SecondaryParams> extends ControlCenterViewController<ConstraintLayout> {
    private final Collection<SecondaryPanelDelegateBase<P>> childDelegates;
    private P panelParams;
    private final ControlCenterSecondaryBinding secondaryBinding;

    /* JADX WARN: Illegal instructions before constructor call */
    public SecondaryPanelDelegateBase(ControlCenterSecondaryBinding secondaryBinding) {
        n.g(secondaryBinding, "secondaryBinding");
        miui.systemui.widget.ConstraintLayout root = secondaryBinding.getRoot();
        n.f(root, "getRoot(...)");
        super(root);
        this.secondaryBinding = secondaryBinding;
    }

    public void cancelPrepare() {
        Collection<SecondaryPanelDelegateBase<P>> childDelegates = getChildDelegates();
        if (childDelegates != null) {
            Iterator<T> it = childDelegates.iterator();
            while (it.hasNext()) {
                SecondaryPanelDelegateBase secondaryPanelDelegateBase = (SecondaryPanelDelegateBase) it.next();
                if (secondaryPanelDelegateBase != null) {
                    secondaryPanelDelegateBase.cancelPrepare();
                }
            }
        }
    }

    public Collection<SecondaryPanelDelegateBase<P>> getChildDelegates() {
        return this.childDelegates;
    }

    public final P getPanelParams() {
        return this.panelParams;
    }

    public void onHidden() {
        Collection<SecondaryPanelDelegateBase<P>> childDelegates = getChildDelegates();
        if (childDelegates != null) {
            Iterator<T> it = childDelegates.iterator();
            while (it.hasNext()) {
                SecondaryPanelDelegateBase secondaryPanelDelegateBase = (SecondaryPanelDelegateBase) it.next();
                if (secondaryPanelDelegateBase != null) {
                    secondaryPanelDelegateBase.onHidden();
                }
            }
        }
    }

    public void onShown() {
        Collection<SecondaryPanelDelegateBase<P>> childDelegates = getChildDelegates();
        if (childDelegates != null) {
            Iterator<T> it = childDelegates.iterator();
            while (it.hasNext()) {
                SecondaryPanelDelegateBase secondaryPanelDelegateBase = (SecondaryPanelDelegateBase) it.next();
                if (secondaryPanelDelegateBase != null) {
                    secondaryPanelDelegateBase.onShown();
                }
            }
        }
    }

    public void prepareHide() {
        Collection<SecondaryPanelDelegateBase<P>> childDelegates = getChildDelegates();
        if (childDelegates != null) {
            Iterator<T> it = childDelegates.iterator();
            while (it.hasNext()) {
                SecondaryPanelDelegateBase secondaryPanelDelegateBase = (SecondaryPanelDelegateBase) it.next();
                if (secondaryPanelDelegateBase != null) {
                    secondaryPanelDelegateBase.prepareHide();
                }
            }
        }
    }

    public void prepareShow(P p2) {
        this.panelParams = p2;
        Collection<SecondaryPanelDelegateBase<P>> childDelegates = getChildDelegates();
        if (childDelegates != null) {
            Iterator<T> it = childDelegates.iterator();
            while (it.hasNext()) {
                SecondaryPanelDelegateBase secondaryPanelDelegateBase = (SecondaryPanelDelegateBase) it.next();
                if (secondaryPanelDelegateBase != null) {
                    secondaryPanelDelegateBase.prepareShow(p2);
                }
            }
        }
    }

    public final void setPanelParams(P p2) {
        this.panelParams = p2;
    }

    public void startHide() {
        Collection<SecondaryPanelDelegateBase<P>> childDelegates = getChildDelegates();
        if (childDelegates != null) {
            Iterator<T> it = childDelegates.iterator();
            while (it.hasNext()) {
                SecondaryPanelDelegateBase secondaryPanelDelegateBase = (SecondaryPanelDelegateBase) it.next();
                if (secondaryPanelDelegateBase != null) {
                    secondaryPanelDelegateBase.startHide();
                }
            }
        }
    }

    public void startShow() {
        Collection<SecondaryPanelDelegateBase<P>> childDelegates = getChildDelegates();
        if (childDelegates != null) {
            Iterator<T> it = childDelegates.iterator();
            while (it.hasNext()) {
                SecondaryPanelDelegateBase secondaryPanelDelegateBase = (SecondaryPanelDelegateBase) it.next();
                if (secondaryPanelDelegateBase != null) {
                    secondaryPanelDelegateBase.startShow();
                }
            }
        }
    }
}
