package miui.systemui.notification.focus.model;

/* JADX INFO: loaded from: classes4.dex */
public final class HighlightInfo extends TextAndColorInfo {
    private String picFunction;
    private String picFunctionDark;
    private TimerInfo timerInfo;
    private int type = 1;

    public final String getPicFunction() {
        return this.picFunction;
    }

    public final String getPicFunctionDark() {
        return this.picFunctionDark;
    }

    public final TimerInfo getTimerInfo() {
        return this.timerInfo;
    }

    public final int getType() {
        return this.type;
    }

    public final void setPicFunction(String str) {
        this.picFunction = str;
    }

    public final void setPicFunctionDark(String str) {
        this.picFunctionDark = str;
    }

    public final void setTimerInfo(TimerInfo timerInfo) {
        this.timerInfo = timerInfo;
    }

    public final void setType(int i2) {
        this.type = i2;
    }
}
