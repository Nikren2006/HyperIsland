package miui.systemui.notification.focus.model;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class AnimIconInfo extends TextAndColorInfo {
    private Boolean autoplay;
    private String effectColor;
    private String effectSrc;
    private Boolean loop;
    private int number = -1;
    private String src;
    private String srcDark;
    private Integer type;

    public final Boolean getAutoplay() {
        return this.autoplay;
    }

    public final String getEffectColor() {
        return this.effectColor;
    }

    public final String getEffectSrc() {
        return this.effectSrc;
    }

    public final Boolean getLoop() {
        return this.loop;
    }

    public final int getNumber() {
        return this.number;
    }

    public final String getSrc() {
        return this.src;
    }

    public final String getSrcDark() {
        String str = this.srcDark;
        return (str == null || TextUtils.isEmpty(str)) ? this.src : this.srcDark;
    }

    public final Integer getType() {
        return this.type;
    }

    public final void setAutoplay(Boolean bool) {
        this.autoplay = bool;
    }

    public final void setEffectColor(String str) {
        this.effectColor = str;
    }

    public final void setEffectSrc(String str) {
        this.effectSrc = str;
    }

    public final void setLoop(Boolean bool) {
        this.loop = bool;
    }

    public final void setNumber(int i2) {
        this.number = i2;
    }

    public final void setSrc(String str) {
        this.src = str;
    }

    public final void setSrcDark(String str) {
        this.srcDark = str;
    }

    public final void setType(Integer num) {
        this.type = num;
    }
}
