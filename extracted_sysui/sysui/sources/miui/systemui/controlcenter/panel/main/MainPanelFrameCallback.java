package miui.systemui.controlcenter.panel.main;

import android.view.Choreographer;
import kotlin.jvm.internal.n;
import miui.systemui.controlcenter.dagger.ControlCenterScope;
import miui.systemui.controlcenter.panel.main.anim.SpreadRowsAnimator;

/* JADX INFO: loaded from: classes.dex */
@ControlCenterScope
public final class MainPanelFrameCallback {
    private final Choreographer choreographer;
    private final E0.a contentDistributor;
    private final Choreographer.FrameCallback frameCallback;
    private final E0.a spreadRowsAnimator;
    private boolean updateScheduled;

    public MainPanelFrameCallback(E0.a contentDistributor, E0.a spreadRowsAnimator) {
        n.g(contentDistributor, "contentDistributor");
        n.g(spreadRowsAnimator, "spreadRowsAnimator");
        this.contentDistributor = contentDistributor;
        this.spreadRowsAnimator = spreadRowsAnimator;
        this.choreographer = Choreographer.getInstance();
        this.frameCallback = new Choreographer.FrameCallback() { // from class: miui.systemui.controlcenter.panel.main.i
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j2) {
                MainPanelFrameCallback.frameCallback$lambda$1(this.f5407a, j2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void frameCallback$lambda$1(MainPanelFrameCallback this$0, long j2) {
        n.g(this$0, "this$0");
        this$0.updateScheduled = false;
        Object obj = this$0.spreadRowsAnimator.get();
        n.f(obj, "get(...)");
        SpreadRowsAnimator.onFrameCallback$default((SpreadRowsAnimator) obj, false, 1, null);
        MainPanelContentDistributor mainPanelContentDistributor = (MainPanelContentDistributor) this$0.contentDistributor.get();
        mainPanelContentDistributor.getLeftAdapter().onFrameCallback();
        mainPanelContentDistributor.getRightAdapter().onFrameCallback();
    }

    public final void scheduleUpdate() {
        if (this.updateScheduled) {
            return;
        }
        this.updateScheduled = true;
        this.choreographer.postFrameCallback(this.frameCallback);
    }
}
