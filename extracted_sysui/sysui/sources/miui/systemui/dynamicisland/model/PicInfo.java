package miui.systemui.dynamicisland.model;

/* JADX INFO: loaded from: classes3.dex */
public final class PicInfo {
    private Boolean autoplay;
    private String contentDescription;
    private String effectColor;
    private String effectSrc;
    private Boolean loop;
    private int number;
    private String pic;
    private Integer type;

    public PicInfo() {
        Boolean bool = Boolean.FALSE;
        this.loop = bool;
        this.number = -1;
        this.autoplay = bool;
    }

    public final Boolean getAutoplay() {
        return this.autoplay;
    }

    public final String getContentDescription() {
        return this.contentDescription;
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

    public final String getPic() {
        return this.pic;
    }

    public final Integer getType() {
        return this.type;
    }

    public final void setAutoplay(Boolean bool) {
        this.autoplay = bool;
    }

    public final void setContentDescription(String str) {
        this.contentDescription = str;
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

    public final void setPic(String str) {
        this.pic = str;
    }

    public final void setType(Integer num) {
        this.type = num;
    }
}
