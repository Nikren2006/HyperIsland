package miui.systemui.dynamicisland.anim;

import android.os.Trace;
import java.util.Collection;
import kotlin.jvm.internal.n;
import miui.systemui.dynamicisland.DynamicIslandUtils;
import miuix.animation.listener.TransitionListener;
import miuix.animation.listener.UpdateInfo;

/* JADX INFO: loaded from: classes3.dex */
public class DynamicIslandAnimListener extends TransitionListener {
    private final DynamicIslandAnimationDelegate delegate;

    public DynamicIslandAnimListener(DynamicIslandAnimationDelegate delegate) {
        n.g(delegate, "delegate");
        this.delegate = delegate;
    }

    @Override // miuix.animation.listener.TransitionListener
    public void onBegin(Object obj) {
        super.onBegin(obj);
        String traceName = DynamicIslandUtils.INSTANCE.getTraceName();
        n.e(obj, "null cannot be cast to non-null type kotlin.String");
        Trace.beginAsyncSection(traceName, ((String) obj).hashCode());
        this.delegate.onBegin(obj);
        this.delegate.setAnimating(true);
    }

    @Override // miuix.animation.listener.TransitionListener
    public void onCancel(Object obj) {
        super.onCancel(obj);
        String traceName = DynamicIslandUtils.INSTANCE.getTraceName();
        n.e(obj, "null cannot be cast to non-null type kotlin.String");
        Trace.endAsyncSection(traceName, ((String) obj).hashCode());
        this.delegate.onCancel(obj);
        this.delegate.setAnimating(false);
    }

    @Override // miuix.animation.listener.TransitionListener
    public void onComplete(Object obj) {
        super.onComplete(obj);
        String traceName = DynamicIslandUtils.INSTANCE.getTraceName();
        n.e(obj, "null cannot be cast to non-null type kotlin.String");
        Trace.endAsyncSection(traceName, ((String) obj).hashCode());
        this.delegate.onComplete(obj);
        this.delegate.setAnimating(false);
    }

    @Override // miuix.animation.listener.TransitionListener
    public void onUpdate(Object obj, Collection<UpdateInfo> collection) {
        super.onUpdate(obj, collection);
        this.delegate.scheduleUpdate();
    }
}
