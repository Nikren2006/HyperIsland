package miui.systemui.controlcenter.windowview;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import java.util.Collection;
import miui.systemui.util.FolmeUtils;
import miuix.animation.IStateStyle;
import miuix.animation.base.AnimConfig;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;
import miuix.animation.property.FloatProperty;

/* JADX INFO: loaded from: classes.dex */
public final class ControlCenterExpandController$expansionAnimator$1 extends FloatProperty<Object> implements LifecycleOwner {
    private final AnimConfig animConfig;
    private final IStateStyle animator;
    private final LifecycleRegistry lifecycleRegistry;
    final /* synthetic */ ControlCenterExpandController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ControlCenterExpandController$expansionAnimator$1(ControlCenterExpandController controlCenterExpandController) {
        super("expand_progress");
        this.this$0 = controlCenterExpandController;
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        this.lifecycleRegistry = lifecycleRegistry;
        this.animator = FolmeUtils.INSTANCE.useValue(this, 0.001f);
        this.animConfig = new AnimConfig().addListeners(new TransitionListener() { // from class: miui.systemui.controlcenter.windowview.ControlCenterExpandController$expansionAnimator$1$animConfig$1
            @Override // miuix.animation.listener.TransitionListener
            public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
                boolean z2;
                boolean z3 = true;
                if (collection != null) {
                    loop0: while (true) {
                        for (UpdateInfo updateInfo : collection) {
                            z2 = z2 && updateInfo.isCompleted;
                        }
                    }
                    z3 = z2;
                }
                if (z3) {
                    this.this$0.lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
                }
            }
        });
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }

    public static /* synthetic */ void setExpandProgress$default(ControlCenterExpandController$expansionAnimator$1 controlCenterExpandController$expansionAnimator$1, boolean z2, float f2, boolean z3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        if ((i2 & 2) != 0) {
            f2 = z2 ? 1.0f : 0.0f;
        }
        if ((i2 & 4) != 0) {
            z3 = true;
        }
        controlCenterExpandController$expansionAnimator$1.setExpandProgress(z2, f2, z3);
    }

    public final void cleanAnim() {
        FolmeUtils.INSTANCE.clean(this);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }

    @Override // miuix.animation.property.FloatProperty
    public float getValue(Object target) {
        kotlin.jvm.internal.n.g(target, "target");
        return this.this$0.getExpansion();
    }

    public final void setExpandProgress(boolean z2, float f2, boolean z3) {
        if (z3) {
            this.lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
            this.animator.to(this, Float.valueOf(f2), this.animConfig);
        } else {
            this.lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
            this.animator.setTo(this, Float.valueOf(f2), this.animConfig);
        }
    }

    @Override // miuix.animation.property.FloatProperty
    public void setValue(Object target, float f2) {
        kotlin.jvm.internal.n.g(target, "target");
        this.this$0.setExpansion(f2);
    }
}
