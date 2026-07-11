package miui.systemui.devicecontrols.ui;

/* JADX INFO: loaded from: classes3.dex */
public interface Behavior {
    static /* synthetic */ void bind$default(Behavior behavior, ControlWithState controlWithState, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: bind");
        }
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        behavior.bind(controlWithState, i2);
    }

    void bind(ControlWithState controlWithState, int i2);

    void initialize(ControlViewHolder controlViewHolder);
}
