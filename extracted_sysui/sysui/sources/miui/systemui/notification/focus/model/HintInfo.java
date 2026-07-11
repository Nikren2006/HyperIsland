package miui.systemui.notification.focus.model;

/* JADX INFO: loaded from: classes4.dex */
public final class HintInfo extends TextAndColorInfo {
    private ActionInfo actionInfo;
    private String colorContentBg;
    private String picContent;
    private TimerInfo timerInfo;
    private int titleLineCount;
    private Integer type;

    public final ActionInfo getActionInfo() {
        return this.actionInfo;
    }

    public final String getColorContentBg() {
        return this.colorContentBg;
    }

    public final String getPicContent() {
        return this.picContent;
    }

    public final TimerInfo getTimerInfo() {
        return this.timerInfo;
    }

    public final int getTitleLineCount() {
        return this.titleLineCount;
    }

    public final Integer getType() {
        return this.type;
    }

    public final void setActionInfo(ActionInfo actionInfo) {
        this.actionInfo = actionInfo;
    }

    public final void setColorContentBg(String str) {
        this.colorContentBg = str;
    }

    public final void setPicContent(String str) {
        this.picContent = str;
    }

    public final void setTimerInfo(TimerInfo timerInfo) {
        this.timerInfo = timerInfo;
    }

    public final void setTitleLineCount(int i2) {
        this.titleLineCount = i2;
    }

    public final void setType(Integer num) {
        this.type = num;
    }
}
