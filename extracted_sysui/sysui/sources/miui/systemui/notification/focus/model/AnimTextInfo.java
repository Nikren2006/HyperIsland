package miui.systemui.notification.focus.model;

/* JADX INFO: loaded from: classes4.dex */
public final class AnimTextInfo extends TextAndColorInfo {
    private AnimIconInfo animIconInfo;
    private TimerInfo timerInfo;

    public final AnimIconInfo getAnimIconInfo() {
        return this.animIconInfo;
    }

    public final TimerInfo getTimerInfo() {
        return this.timerInfo;
    }

    public final void setAnimIconInfo(AnimIconInfo animIconInfo) {
        this.animIconInfo = animIconInfo;
    }

    public final void setTimerInfo(TimerInfo timerInfo) {
        this.timerInfo = timerInfo;
    }
}
