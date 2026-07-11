package miui.systemui.notification.focus.model;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class ChatInfo extends TextAndColorInfo {
    private String appIconPkg;
    private String picProfile;
    private String picProfileDark;
    private TimerInfo timerInfo;

    public final String getAppIconPkg() {
        return this.appIconPkg;
    }

    public final String getPicProfile() {
        return this.picProfile;
    }

    public final String getPicProfileDark() {
        String str = this.picProfileDark;
        return (str == null || TextUtils.isEmpty(str)) ? this.picProfile : this.picProfileDark;
    }

    public final TimerInfo getTimerInfo() {
        return this.timerInfo;
    }

    public final void setAppIconPkg(String str) {
        this.appIconPkg = str;
    }

    public final void setPicProfile(String str) {
        this.picProfile = str;
    }

    public final void setPicProfileDark(String str) {
        this.picProfileDark = str;
    }

    public final void setTimerInfo(TimerInfo timerInfo) {
        this.timerInfo = timerInfo;
    }
}
