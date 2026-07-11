package miui.systemui.dynamicisland.model;

/* JADX INFO: loaded from: classes3.dex */
public final class SameWidthDigitInfo {
    private String content;
    private String digit;
    private Boolean showHighlightColor = Boolean.FALSE;
    private TimerInfo timerInfo;
    private Boolean turnAnim;

    public final String getContent() {
        return this.content;
    }

    public final String getDigit() {
        return this.digit;
    }

    public final Boolean getShowHighlightColor() {
        return this.showHighlightColor;
    }

    public final TimerInfo getTimerInfo() {
        return this.timerInfo;
    }

    public final Boolean getTurnAnim() {
        return this.turnAnim;
    }

    public final void setContent(String str) {
        this.content = str;
    }

    public final void setDigit(String str) {
        this.digit = str;
    }

    public final void setShowHighlightColor(Boolean bool) {
        this.showHighlightColor = bool;
    }

    public final void setTimerInfo(TimerInfo timerInfo) {
        this.timerInfo = timerInfo;
    }

    public final void setTurnAnim(Boolean bool) {
        this.turnAnim = bool;
    }
}
